package socketpractice;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(22222);
        System.out.println("Server started...");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected...");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); // when we client send something then we receieve with ObjectInputStream
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            try {
                Object clientMsg = ois.readObject();
                System.out.println("From Client: " + (String) clientMsg);
                String serverMsg = (String) clientMsg;
                serverMsg = serverMsg.toUpperCase();
                oos.writeObject(serverMsg);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}
