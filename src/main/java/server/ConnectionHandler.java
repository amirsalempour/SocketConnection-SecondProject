package server;

import client.Transaction;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Dotin school 6 on 4/12/2016.
 */
public class ConnectionHandler implements Runnable {
    private Socket socket;

    public ConnectionHandler(Socket socket) {
        this.socket = socket;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            Logger logger = Logger.getLogger("ServerLogs");
            FileHandler fileHandler = new FileHandler("src/logFiles/ServerLogs/result.log");
            try {
                ArrayList<Transaction> transactionList;
                logger.addHandler(fileHandler);
                SimpleFormatter formatter = new SimpleFormatter();
                fileHandler.setFormatter(formatter);
                logger.info("Server logs");
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                transactionList = (ArrayList<Transaction>) objectInputStream.readObject();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                for (Transaction trx : transactionList) {
//                    System.out.println(" Client attributes are: " + trx);

                      String str =  TransactionValidator.validateTransaction(trx);

                    objectOutputStream.writeObject(str);
                    logger.info(str);

                    }

                    objectInputStream.close();
                    objectOutputStream.close();
                    socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }catch (Exception e){
            System.out.println("Exception is: "+e);
        }

//    public int Deposit(int initialBalance, int amount) {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        synchronized (this) {
//            return initialBalance + amount;
//        }
    }

//    public int Withdraw(int initialBalance, int amount) {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        synchronized (this) {
//            return initialBalance - amount;
//        }

//    }

}