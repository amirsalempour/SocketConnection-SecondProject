package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Created by Dotin school 6 on 4/17/2016.
 */

public class Validator implements Runnable {


    DataOutputStream dataOutputStream;
    Socket socket;
    boolean stop = true;
    Logger logger = Logger.getLogger(Validator.class.getName());
    String[] deposits = new String[10];
    int temp = 0;

    public Validator() {
    }

    public Validator(Socket socket) {
        this.socket = socket;
       // this.logger = logger;

        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            OutputStream outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);
            logger.info("+++++++++++++++++++");
            while (stop) {
                deposits[temp] = dataInputStream.readUTF();
                logger.info("Read from Terminal: " + deposits[temp]);
               // if (deposits[temp].compareTo("End" == 0)) {
                    stop = false;
                    logger.info("Reading is Finished");

             //   }
                temp++;

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        logger.info("Start Validation");
        String[] result;
        for (int i = 0; i <temp-1 ; i++) {
            try{
                result =this.deposits[i].split("#");
                JsonParser jsonParser=new JsonParser();
                UserType userType= jsonParser.SearchInFile(result[3]);

                if(userType.getInitialBalance()<Integer.parseInt(result[2])&&(result[1].compareTo("withdraw")==0)){
                    dataOutputStream.writeUTF(userType.getCustomerNumber()+"#10#");
                    dataOutputStream.flush();
                    logger.info("not validated. id: "+userType.getCustomerNumber());
                    logger.info("initial balance Problem");
                    logger.info("Response code: 01");
                }
             else if (userType.getUpperBound() < Integer.parseInt(result[2]) && (result[1]).compareTo("withdraw") == 0) {

                    dataOutputStream.writeUTF(userType.getUpperBound() + "#02#");
                    dataOutputStream.flush();
                    logger.info("not validated. id: " + userType.getCustomerNumber());
                    logger.info("upper bound Problem");
                    logger.info("Response code: 02");
                } else {
                    Validator validator = new Validator();
                    Class aclass = validator.getClass();
                    Method operationMethod=aclass.getMethod(result[1],new Class[]{int.class,int.class });
                    int newBalance=(Integer) operationMethod.invoke(validator,new Object[]{userType.getInitialBalance(), Integer.parseInt(result[2])});

                    userType.setInitialBalance(newBalance);
                    System.out.println("getString1 returned: "+"#00#"+newBalance+"#");
                    dataOutputStream.writeUTF(userType.getCustomerNumber());
                    dataOutputStream.flush();
                    logger.info("Validated. id : " + userType.getCustomerNumber());
                    logger.info("Response code : 00");

                }

                } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        try {
            dataOutputStream.writeUTF("End");
            dataOutputStream.flush(); logger.info("Validation Finished");
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        }

    }


