package server;

import client.Transaction;

import java.math.BigDecimal;

/**
 * Created by Dotin school 6 on 4/27/2016.
 */
public class TransactionValidator {


//    List<Deposit> depositList = new ArrayList<Deposit>();

    public static BigDecimal depositResult;
    public static String strMessage;

    public static String validateTransaction(Transaction trx) throws Exception {
        JsonParser jsonParser = new JsonParser();
        if (!trx.getType().equals("deposit") && !trx.getType().equals("withdraw")) {

            return "the type of action with subject " + trx.getType().toString() + "  is not true";
        } else {
            if (jsonParser.searchInFile(trx.getDepositNumber()) != null) {
                for (Deposit deposit : jsonParser.depositList) {
                    if (trx.getDepositNumber().compareTo(deposit.getCustomerNumber()) == 0) {
                        if (trx.getType().equals("deposit")) {


//                            if (Integer.parseInt(trx.getAmount()) + Integer.parseInt(String.valueOf(deposit.getInitialBalance())) <= Integer.parseInt(String.valueOf(deposit.getUpperBound()))) {

                            if (((deposit.getUpperBound()).compareTo(trx.getAmount().multiply(deposit.getInitialBalance()))) <0) {
                                depositResult = deposit.depositRunner((trx.getAmount()), (deposit.getInitialBalance()));
                                strMessage = "deposit done and result of initialBalancce user " + deposit.getCustomerName().toString() + " is: " + depositResult;
                               System.out.println("********************************************** "+strMessage);
                            } else if (trx.getAmount().multiply(deposit.getInitialBalance()).compareTo(deposit.getUpperBound()) > 0)
//if (Integer.parseInt(trx.getAmount()) + Integer.parseInt(String.valueOf(deposit.getInitialBalance())) > Integer.parseInt(String.valueOf(deposit.getUpperBound()))) {
                                System.out.println("your request cannot be done because the result of: " + deposit.getCustomerName() + " will be higher than upperBalance");
                            strMessage = "your request cannot be done because the result of: " + deposit.getCustomerName() + " will be higher than upperBalance";
                        }
                    } else if (trx.getType().equals("withdraw")) {
if(deposit.getInitialBalance().compareTo(trx.getAmount())>=0){
// if (Integer.parseInt(String.valueOf(deposit.getInitialBalance())) - Integer.parseInt(trx.getAmount()) >= Integer.parseInt(String.valueOf(deposit.getUpperBound()))) {
                            BigDecimal withdrawResult;
    withdrawResult=deposit.getInitialBalance().subtract(trx.getAmount());
                           // withdrawResult = deposit.withdrawRunner(Integer.parseInt(String.valueOf(deposit.getInitialBalance())), Integer.parseInt(trx.getAmount()));
                            strMessage = "withdraw result of " + deposit.getCustomerName() + " is" + withdrawResult;

//                        } else if (Integer.parseInt(String.valueOf(deposit.getInitialBalance())) - Integer.parseInt(trx.getAmount()) < Integer.parseInt(String.valueOf(deposit.getUpperBound()))) {
//
//
//                            strMessage = "Can not withdraw user " + deposit.getCustomerName();
//                        }
                    }

                }
            }
        }

    }

    return strMessage;
}


//    public static int depositRunner(int initialBalance, int amount) throws InterruptedException {
//      Thread.sleep(1000);
//        //synchronized ()
//        return initialBalance + amount;
//    }

//    public static int withdrawRunner(int initialBalance, int amount) throws InterruptedException {
//      Thread.sleep(1000);
//        return initialBalance - amount;
//    }

}