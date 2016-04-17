package server;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Dotin school 6 on 4/16/2016.
 */
public class JsonParser extends UserType {
    List<UserType> userList=new ArrayList<UserType>();
    //  private int portNumber;
    //  private int depositNumber;
//    private String customerName;
    //  private int initialBalance;
    //  private BigDecimal upperBound;

    public JsonParser() throws Exception {

        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(new FileReader("src\\main\\java\\server\\core.json"));

            JSONObject jsonObject = (JSONObject) object;

//            String name = (String) jsonObject.get("name");
//            System.out.println(name);



            JSONArray deposits = (JSONArray) jsonObject.get("deposits");
            Iterator iterator = deposits.iterator();

            while (iterator.hasNext()) {

                UserType userType = new UserType();
                JSONObject parse = (JSONObject) iterator.next();
                userType.setCustomerName((String) parse.get("customer"));
                userType.setCustomerNumber((String) parse.get("id"));
                userType.setInitialBalance(Integer.parseInt((String) parse.get("initialBalance")));
                userType.setUpperBound(Integer.parseInt((String)parse.get("upperBounds")));


                System.out.println("initial balance is : "+userType.getInitialBalance());
                System.out.println("initial upperBound is : "+userType.getUpperBound());
                System.out.println("customer name is: " + userType.getCustomerName());
                System.out.println("customer number is: " + userType.getCustomerNumber());



//                System.out.println(iterator.next());
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public UserType SearchInFile(String customerNumber){
        for (UserType userType: userList){
            if(userType.getCustomerNumber().compareTo(customerNumber)==0){
                return (UserType) userList;
            }
        }
        return null;
    }
}
