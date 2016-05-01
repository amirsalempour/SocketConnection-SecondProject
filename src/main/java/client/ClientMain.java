package client;

/**
 * Created by Dotin school 6 on 4/11/2016.
 */
public class ClientMain {
    public static void main(String[] args) throws Exception {
        new TerminalRunner("src/main/java/client/terminal.xml");
        new TerminalRunner("src/main/java/client/terminal2.xml");
        new TerminalRunner("src/main/java/client/terminal3.xml");
    }
}