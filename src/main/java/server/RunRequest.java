package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class RunRequest extends  UserType {

    private ServerSocket serverSocket;

public RunRequest(){

//    UserType userType=new UserType();

    try {
        JsonParser jsonParser=new JsonParser();
        org.json.simple.JSONObject jsonObject=jsonParser.Connection();
        int portNumber=Integer.parseInt(jsonObject.get("port").toString());
        serverSocket = new ServerSocket(portNumber);

    } catch (IOException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public void handleConnection() throws Exception {
        System.out.println("waiting for client message...");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                new Validator(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}