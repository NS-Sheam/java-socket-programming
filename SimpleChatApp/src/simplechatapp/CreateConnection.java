
package simplechatapp;

import java.util.HashMap;


public class CreateConnection implements Runnable{
    HashMap<String, Information> clientList;
    NetworkConnection nc;
    
    public CreateConnection(HashMap<String, Information> cList, NetworkConnection nConnection){
        clientList = cList;
        nc = nConnection;
    }
    
    @Override
    public void run(){
        Object userObj = nc.read();
        String userName = (String)userObj;
        System.out.println("User : "+userName+" connected...");
        clientList.put(userName, new Information(userName, nc));
        new Thread(new ReadWriterServer(userName, nc, clientList)).start();
    }
}
