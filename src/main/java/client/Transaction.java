package client;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Dotin school 6 on 4/24/2016.
 */
public class Transaction implements Serializable {
    private String id;
    private String type;
    private String depositNumber;
    private BigDecimal amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDepositNumber() {
        return depositNumber;
    }

    public void setDepositNumber(String depositNumber) {
        this.depositNumber = depositNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return id + "," + type + "," + amount + "," + depositNumber;
    }
}
