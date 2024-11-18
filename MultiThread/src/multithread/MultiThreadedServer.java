package multithread;

import java.io.*;
import java.net.*;

public class MultiThreadedServer {

    private static int maxClient = 2;
    private static int clientCount = 0;


    public static void main(String[] args) throws IOException {
        int port = 12345;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server Started....");
            while (true) {
                Socket socket = serverSocket.accept();
                if (clientCount < maxClient) {
                    System.out.println("Client count: " + clientCount + 1);
                     clientCount++;
                    new ClientHandler(socket).start();
                } else {
                    socket.close();
                    System.out.println("Max limit reached. Server closing...");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Object clientResponse = ois.readObject();
            String clientMessage = (String) clientResponse;
            System.out.println("From client.. " + clientMessage);
            String serverMsg = clientMessage.toUpperCase();
            oos.writeObject(serverMsg);
            oos.flush();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
