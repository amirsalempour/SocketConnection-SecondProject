package client;

import java.io.Serializable;

/**
 * Created by Dotin school 6 on 4/24/2016.
 */
public class Transaction implements Serializable{
    public String id;
   public String type;
   public String depositNumber;
    public String amount;

    @Override
    public String toString() {
    return id +","+ type +","+ amount+ "," + depositNumber;
    }
}
