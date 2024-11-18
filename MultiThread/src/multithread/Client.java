package multithread;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    public static void main(String[] args) throws IOException {
        String hostname = "localhost"; 
        int port = 12345;
        Socket socket = new Socket(hostname, port);
        System.out.println("Server Connected...");
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        
        System.out.println("Enter a message: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        oos.writeObject(input);
        try {
            Object serverResponse = ois.readObject();
            System.out.println("Server Response: " + (String) serverResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
