package client;

import server.Validator;

/**
 * Created by Dotin school 6 on 4/11/2016.
 */
public class Main {
    public static void main(String[] args) throws  Exception{

        new RunTerminal();
        XmlParser x=new XmlParser();
        x.readXML();
        Validator validator=new Validator();


    }
}