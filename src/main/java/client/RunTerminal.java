package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Dotin school 6 on 4/11/2016.
 */
public class RunTerminal {
    public RunTerminal(){
    try {
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = new Socket(host.getHostAddress(), 8080);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(" Hello There");
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        String Message = (String) ois.readObject();
        System.out.println("Message: " + Message);

        ois.close();
        oos.close();



    }catch (UnknownHostException e){
        e.printStackTrace();
    }catch (IOException e){
        e.printStackTrace();
    }catch (ClassNotFoundException e){
        e.printStackTrace();
    }
    }

}//update address , portNumber then run terminal
