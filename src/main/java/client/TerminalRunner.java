package client;

import org.json.JSONException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Dotin school 6 on 4/11/2016.
 */
public class TerminalRunner {

    public TerminalRunner(String filePath) throws JSONException {
        try {
            Logger logger = Logger.getLogger("ClientLogs");
            FileHandler fileHandler = new FileHandler("src/logFiles/ClientLogs/result1.log");
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.info("Client logs");
            XmlParser xmlParser = new XmlParser();
            int portNumber;
            ArrayList messages = null;

            messages = xmlParser.readXML(filePath);
            portNumber = xmlParser.getPortNumber();
            Socket socket = new Socket(InetAddress.getLocalHost(), portNumber);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

           for (Object messageOut: messages) {
               objectOutputStream.writeObject(messages);
           }
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            try {
                String Message = (String) objectInputStream.readObject();
                while (Message != null) {
             //       System.out.println("Message: " + Message);
                    logger.info(Message);
                    Message = (String) objectInputStream.readObject();

                }
                objectInputStream.close();
            } catch (Exception e) {

            }
          objectOutputStream.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }
    }

}
