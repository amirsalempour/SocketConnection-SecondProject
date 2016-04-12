package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Dotin school 6 on 4/11/2016.
 */
public class RunRequest {

    private ServerSocket serverSocket;
    private int port;
    
    public void Validation(){


    }

    public RunRequest() throws Exception {

        port = 8080;
        try {
            serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleConnection() throws Exception {
        System.out.println("waiting for client message...");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                new ConnectionHandler(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }//ServerSocketExample,  run request, send result to terminal
    }
}