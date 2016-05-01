package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class RequestRunner extends Deposit {
    private static ServerSocket serverSocket;
    private static int portNumber;

    public static void handleConnection() throws Exception {
        try {
            JsonParser jsonParser = new JsonParser();
            org.json.simple.JSONObject jsonObject = jsonParser.connect();
            portNumber = Integer.parseInt(jsonObject.get("port").toString());

            serverSocket = new ServerSocket(portNumber);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("waiting for client message...");
        while (true) {

            Socket socket = serverSocket.accept();
            new ConnectionHandler(socket);
        }
    }
}