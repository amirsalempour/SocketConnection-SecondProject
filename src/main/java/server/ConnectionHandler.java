package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Dotin school 6 on 4/12/2016.
 */
public class ConnectionHandler implements Runnable{
   private Socket socket;
    public ConnectionHandler(Socket socket){
        this.socket=socket;
        Thread t=new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try
        {
            //FileInputStream fileInputStream=new FileInputStream("result.txt");
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            String Message= null;
            try {
                Message = (String) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Message recieved"+Message);
            ObjectOutputStream oos =new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("Hi...");
            ois.close();
            oos.close();
            socket.close();
            System.out.println("waiting for client message...");
         } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
