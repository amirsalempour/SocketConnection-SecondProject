package server;

/**
 * Created by Dotin school 6 on 4/16/2016.
 */
public class UserType {
    private String customerNumber;

    private String customerName;
    private int initialBalance;
    private int upperBound;
    private String depositNumber;

    public UserType(String customerNumber,String customerName, int initialBalance, int upperBound) {
        this.customerName=customerName;
        this.customerNumber=customerNumber;
        this.initialBalance=initialBalance;
        this.upperBound=upperBound;

    }

    public UserType() {
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(int initialBalance) {
        this.initialBalance = initialBalance;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }

    public String getDepositNumber() {
        return depositNumber;
    }

    public void setDepositNumber(String depositNumber) {
        this.depositNumber = depositNumber;
    }



}
