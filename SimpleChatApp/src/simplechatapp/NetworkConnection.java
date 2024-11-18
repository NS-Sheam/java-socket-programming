package simplechatapp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkConnection {

    Socket socket;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    public NetworkConnection(Socket sock) throws IOException {
        socket = sock;
        ois = new ObjectInputStream(socket.getInputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
    }

    public NetworkConnection(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        ois = new ObjectInputStream(socket.getInputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
    }

    public void write(Object obj) {

        try {
            oos.writeObject(obj);
        } catch (IOException e) {
            System.out.println("Failed to write");
        }
    }

    public Object read() {
        Object obj;
        try {
            obj = ois.readObject();
        } catch (Exception e) {
            System.out.println("Failed to read");
            return null;
        }
        return obj;

    }

}
