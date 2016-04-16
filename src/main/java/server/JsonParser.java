package server;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


/**
 * Created by Dotin school 6 on 4/16/2016.
 */
public class JsonParser extends UserType {
  //  private int portNumber;
  //  private int depositNumber;
//    private String customerName;
  //  private int initialBalance;
  //  private BigDecimal upperBound;

    public JsonParser() throws Exception{

        JSONParser parser= new JSONParser();
            try{
            Object object=parser.parse(new FileReader("src\\main\\java\\server\\core.json"));
            JSONObject jsonObject=(JSONObject) object;

                System.out.println("the port number is "+  jsonObject.get("port"));
             //   portNumber=Integer.parseInt(jsonObject.get("port").toString());
                JSONArray deposits=(JSONArray) jsonObject.get("deposits");
                Iterator iterator=deposits.iterator();

                while(iterator.hasNext()){
                    UserType userType=new UserType();
                  //  userType.setCustomerName("1221");

                    System.out.println(iterator.next());
                }


        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

//        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
//        try{
//            Object object = parser.parse(new FileReader("core.json"));
//            JSONObject jsonObject=new JSONObject;
//            jsonObject.put("")
//           portNumber=(Integer) Integer.parseInt((String) jsonObject.get("port"));
//
//
//
//        }

    }
}
