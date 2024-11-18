package multithread;

import java.io.*;
import java.net.*;

public class MultiThreadedServer {

    public static void main(String[] args) throws IOException{
        int port = 12345;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server Started....");
    }
}

class ClientHandler extends Thread {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());

            Object receivedObject;
            while ((receivedObject = objectInput.readObject()) != null) {
                System.out.println("Received from client: " + receivedObject);

                // Echo back the received object
                objectOutput.writeObject("Server: " + receivedObject);
                objectOutput.flush(); // Ensure the object is sent immediately

                if ("bye".equalsIgnoreCase(receivedObject.toString())) {
                    System.out.println("Client disconnected");
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
