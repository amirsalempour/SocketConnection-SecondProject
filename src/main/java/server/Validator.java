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
            fileHandler = new FileHandler("result.log");
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
                String[] m;
                for (int i = 0; i < Message.size(); i++) {
                    m = new String[4];

                    System.out.println("Client fetures are: " + Message.get(i));
                    StringTokenizer stringTokenizer = new StringTokenizer(Message.get(i).toString(), ",");

                    while (stringTokenizer.hasMoreElements()) {

                        for (int j = 0; j < 4; j++) {
                            m[j] = (String) stringTokenizer.nextElement();
                        }
                        JsonParser jsonParser = new JsonParser();

                        if (jsonParser.SearchInFile(m[3]) != null) {


                            //       System.out.println(jsonParser.userList.get(0).getInitialBalance());
//                            System.out.println("get 0 ===" + jsonParser.userList.get(0).getInitialBalance());
//                            System.out.println("get 1 ===" + jsonParser.userList.get(1).getInitialBalance());
//                            System.out.println("get upper bound avali ===" + jsonParser.userList.get(0).getUpperBound());
//                            System.out.println("get upper bound dovomi ===" + jsonParser.userList.get(1).getUpperBound());
                            Validator validator;
                            validator = new Validator();
                            if (m[1].compareTo("deposit") != 0 && m[1].compareTo("withdraw") != 0) {
                               logger.info("The user name with diposit  number of " +m[3] +  " does not true action");
                                break;
                            }
                            if ((m[1].compareTo("deposit") == 0) && (Integer.parseInt(m[2]) + Integer.parseInt(String.valueOf(jsonParser.userList.get(i).getInitialBalance())))
                                    <= Integer.parseInt(String.valueOf(jsonParser.userList.get(i).getUpperBound()))) {
                                validator.Depoit(Integer.parseInt(m[2].toString()), Integer.parseInt(String.valueOf(jsonParser.userList.get(i).getInitialBalance())));
                                logger.info("Deposit with username "+ jsonParser.userList.get(i).getCustomerName().toString()+" done");

                            } else if ((m[1].compareTo("withdraw") == 0) && (m[2].compareTo(String.valueOf(jsonParser.userList.get(i).getInitialBalance())) < 0) && (((jsonParser.userList.get(i).getInitialBalance()) - Integer.parseInt(m[2]))) >= 0) {
                                validator.Withdraw(Integer.parseInt(m[2].toString()), Integer.parseInt(String.valueOf(jsonParser.userList.get(i).getInitialBalance())));
                                System.out.println("withdrow initial balance of withdraw " + jsonParser.userList.get(i).getCustomerName() + " is " + validator.Withdraw(Integer.parseInt(m[2].toString()), Integer.parseInt(String.valueOf(jsonParser.userList.get(i).getInitialBalance()))));

                                logger.info("Withdraw initial balance of withdraw " + jsonParser.userList.get(i).getCustomerName() + " is " + validator.Withdraw(Integer.parseInt(m[2].toString()), Integer.parseInt(String.valueOf(jsonParser.userList.get(i).getInitialBalance()))));
                            } else if (m[1].compareTo("withdraw") != 0 && m[1].compareTo("deposit") != 0) {
                                System.out.println("the request can not be execute ");
                            }
                            else if((m[1].compareTo("withdraw") == 0) && (m[2].compareTo(String.valueOf(jsonParser.userList.get(i).getInitialBalance())) > 0) && (((jsonParser.userList.get(i).getInitialBalance()) - Integer.parseInt(m[2]))) >= 0) {

                                logger.info("Initial Balance is lower than amount that username " + jsonParser.userList.get(i).getCustomerName().toString() + " wants withdraw");

                            }


                        } else {
                            System.out.println("\n the deposit number is not existance.");
                        }
                    }
                    System.out.println("m3 is: " + m[3]);
                    System.out.println("m1 is: " + m[1]);
                    System.out.println("m0 is: " + m[0]);
                    System.out.println("m2 is " + m[2]);


//                    System.out.println("message size is: "+Message.size());
                }

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject("Hi...");
                objectInputStream.close();
                objectOutputStream.close();
                socket.close();


                System.out.println("waiting for client message...");

            } catch (IOException e) {
                e.printStackTrace();
            }

            // logger.info(Message);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public int Depoit(int initialBalance, int amount) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return initialBalance + amount;


    }

    public int Withdraw(int initialBalance, int amount) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return initialBalance - amount;

    }

}