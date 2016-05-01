package server;

import java.math.BigDecimal;

/**
 * Created by Dotin school 6 on 4/16/2016.
 */
public class Deposit {

    private String customerNumber;
    private String customerName;
    private BigDecimal initialBalance;
    private BigDecimal upperBound;


    public Deposit( String customerName,String customerNumber, BigDecimal initialBalance, BigDecimal upperBound) {
        this.customerName=customerName;
        this.customerNumber=customerNumber;
        this.initialBalance=initialBalance;
        this.upperBound=upperBound;
    }

    public Deposit() {
    }


    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
    public String getCustomerNumber() {
        return customerNumber;
    }





    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerName() {
        return customerName;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public BigDecimal getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(BigDecimal upperBound) {
        this.upperBound = upperBound;
    }




    public BigDecimal depositRunner(BigDecimal initialBalance, BigDecimal amount) throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            return new BigDecimal(String.valueOf(initialBalance.multiply(amount)));
        }
    }
    public BigDecimal withdrawRunner(BigDecimal initialBalance, BigDecimal amount) throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            return new BigDecimal(String.valueOf(initialBalance.subtract(amount)));
        }
    }
}