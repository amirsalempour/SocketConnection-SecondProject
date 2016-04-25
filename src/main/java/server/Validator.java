package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Dotin school 6 on 4/12/2016.
 */
public class Validator implements Runnable {
    private Socket socket;

    Validator() {

    }

    public Validator(Socket socket) {
        this.socket = socket;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {

            Logger logger = Logger.getLogger("MyLog");
            FileHandler fileHandler;
            fileHandler = new FileHandler("src\\logFiles\\ServerLogs/result.log");
            ArrayList Message = null;

            try {
                logger.addHandler(fileHandler);
                SimpleFormatter formatter = new SimpleFormatter();
                fileHandler.setFormatter(formatter);
                logger.info("my first Log");

                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());


                Message = null;

                try {
                    Message = (ArrayList) objectInputStream.readObject();


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                String[] userObject;
                for (int i = 0; i < Message.size(); i++) {
                    userObject = new String[4];

                    System.out.println("Client features are: " + Message.get(i));
                    StringTokenizer stringTokenizer = new StringTokenizer(Message.get(i).toString(), ",");

                    while (stringTokenizer.hasMoreElements()) {

                        for (int j = 0; j < 4; j++) {
                            userObject[j] = (String) stringTokenizer.nextElement();
                        }
                        JsonParser jsonParser = new JsonParser();

                        if (jsonParser.SearchInFile(userObject[3]) != null) {

                            Validator validator;
                            validator = new Validator();
                            if (userObject[1].compareTo("deposit") != 0 && userObject[1].compareTo("withdraw") != 0) {
                                logger.info("The user name with diposit  number of " + userObject[3] + " does not true action");
                                objectOutputStream.writeObject("The user name with deposit  number of " + userObject[3] + " does not true action");
                                break;
                            }


                            if ((userObject[1].compareTo("deposit") == 0) && (Integer.parseInt(userObject[2]) + Integer.parseInt(String.valueOf(jsonParser.userList.get(i).getInitialBalance())))
                                    <= Integer.parseInt(String.valueOf(jsonParser.userList.get(i).getUpperBound()))) {

                                jsonParser.userList.get(i).setInitialBalance(validator.Depoit(Integer.parseInt(userObject[2].toString()), Integer.parseInt(String.valueOf(jsonParser.userList.get(i).getInitialBalance()))));

                                logger.info("Deposit with username " + jsonParser.userList.get(i).getCustomerName().toString() + " done");
                                userObject[2] = String.valueOf(jsonParser.userList.get(i).getInitialBalance());
                                objectOutputStream.writeObject("Deposit with username " + jsonParser.userList.get(i).getCustomerName().toString() + " done and initial balane is: " + userObject[2]);

                            } else if ((userObject[1].compareTo("withdraw") == 0) && (Integer.parseInt(String.valueOf(jsonParser.userList.get(i).getInitialBalance())) - Integer.parseInt(userObject[2])) >= 0) {

                                jsonParser.userList.get(i).setInitialBalance(validator.Withdraw(Integer.parseInt(String.valueOf(jsonParser.userList.get(i).getInitialBalance())), Integer.parseInt(userObject[2].toString())));
                                userObject[2] = String.valueOf(jsonParser.userList.get(i).getInitialBalance());
                                objectOutputStream.writeObject("withdraw with user name " + jsonParser.userList.get(i).getCustomerName() + " is done and now initial Balance is: " + jsonParser.userList.get(i).getInitialBalance());
                                logger.info("Withdraw done and initial balance of " + jsonParser.userList.get(i).getCustomerName() + " is " + validator.Withdraw(Integer.parseInt(userObject[2].toString()), Integer.parseInt(String.valueOf(jsonParser.userList.get(i).getInitialBalance()))));
                            } else if (userObject[1].compareTo("withdraw") != 0 && userObject[1].compareTo("deposit") != 0) {
                                System.out.println("the request can not be execute ");
                                objectOutputStream.writeObject("the request can not be execute ");

                            } else if ((userObject[1].compareTo("withdraw") == 0) && Integer.parseInt(String.valueOf(jsonParser.userList.get(i).getInitialBalance())) - Integer.parseInt(userObject[2].toString()) < 0) {

                                logger.info("Initial Balance is lower than amount that username " + jsonParser.userList.get(i).getCustomerName().toString() + " wants withdraw");
                                objectOutputStream.writeObject("Initial Balance is lower than amount that username " + jsonParser.userList.get(i).getCustomerName().toString() + " wants withdraw");
                            }


                        } else {
                            System.out.println("\n the deposit number is not exist.");
                        }
                    }
                }

                objectInputStream.close();
                objectOutputStream.close();
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public  int Depoit(int initialBalance, int amount) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       synchronized (this){
            return initialBalance + amount;
        }
    }
    public  int Withdraw(int initialBalance, int amount) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this){
            return initialBalance - amount;
        }

    }

}