package server;

/**
 * Created by Dotin school 6 on 4/11/2016.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        JsonParser j=new JsonParser();

        RunRequest runRequest = new RunRequest();
        runRequest.handleConnection();


    }
}
