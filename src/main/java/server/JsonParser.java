package server;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Dotin school 6 on 4/16/2016.
 */
public class JsonParser {

    List<Deposit> depositList = new ArrayList<Deposit>();
   public static Object object;

    public JsonParser() throws Exception {

        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(new FileReader("src/main/java/server/core.json"));

            JSONObject jsonObject = (JSONObject) object;

            JSONArray deposits = (JSONArray) jsonObject.get("deposits");
            Iterator iterator = deposits.iterator();

            while (iterator.hasNext()) {
                JSONObject parse = (JSONObject) iterator.next();
                String customerName = (String) parse.get("customer");
                String id = (String) parse.get("id");
                BigDecimal initialBalance = BigDecimal.valueOf(Integer.parseInt((String) parse.get("initialBalance")));
                BigDecimal upperBounds = BigDecimal.valueOf(Integer.parseInt((String) parse.get("upperBounds")));
                Deposit deposit = new Deposit(customerName, id, initialBalance, upperBounds);
                depositList.add(deposit);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String searchInFile(String customerNumber) {
        for (Deposit user : depositList) {
            if (user.getCustomerNumber().compareTo(customerNumber) == 0) {

                return user.getCustomerNumber();
            }
        }
     return null;
    }

    public JSONObject connect() { //todo :O give a better name
        JSONParser jasonParser = new JSONParser();
        try {
         object = jasonParser.parse(new FileReader("src\\main\\java\\server\\core.json"));
            return (JSONObject) object;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
