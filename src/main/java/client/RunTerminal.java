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
public class RunTerminal extends XmlParser {


    public RunTerminal() throws JSONException {

        try {
            Logger logger = Logger.getLogger("myLogs");
            FileHandler fileHandler = new FileHandler("src\\logFiles\\ClientLogs/result1.log");
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.info("my logs");
            XmlParser xmlParser = new XmlParser();
            int portNumber;
            InetAddress host = InetAddress.getLocalHost();
            ArrayList messages = null;

            messages = xmlParser.readXML();
            portNumber = xmlParser.getPortNumber();
            Socket socket = new Socket(host.getHostAddress(), portNumber);

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
                System.out.println("exception " + e);
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
