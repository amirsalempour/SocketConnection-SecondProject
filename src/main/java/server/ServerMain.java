package server;

/**
 * Created by Dotin school 6 on 4/11/2016.
 */
public class ServerMain {
    public static void main(String[] args) throws Exception {
        //RequestRunner a = new RequestRunner();
        RequestRunner.handleConnection();
    }
}
