package client;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import java.util.ArrayList;



/**
 * Created by Dotin school 6 on 4/12/2016.
 */
public class XmlParser {
    private String terminalId;
    private String serverIp;
    private  int portNumber;
    public String outLogPath;
    private String Type;
    //private String

public void readXML() throws Exception {


    File file = new File("src\\main\\java\\client/terminal.xml");



    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(file);
    doc.getDocumentElement().normalize();

    NodeList n1 = doc.getElementsByTagName("terminal");
    System.out.println("Root element :" + n1.getLength());



    NodeList server = doc.getElementsByTagName("server");
    NodeList outLog = doc.getElementsByTagName("outLog");
    NodeList nList = doc.getElementsByTagName("transactions");
    NodeList transaction = doc.getElementsByTagName("transaction");

    for(int i =0;i <nList.getLength(); i++) {


        org.w3c.dom.Node nNode = nList.item(i);
        if (nNode.getNodeType() == javax.xml.soap.Node.ELEMENT_NODE) {


        }

    }



    terminalId= n1.item(0).getAttributes().item(0).getNodeValue();
    Type =n1.item(0).getAttributes().item(1).getNodeValue();
    serverIp=server.item(0).getAttributes().item(0).getNodeValue();
    portNumber=Integer.parseInt(server.item(0).getAttributes().item(1).getNodeValue());
    outLogPath= outLog.item(0).getAttributes().item(0).getNodeValue();


    ArrayList<String> transactions=new ArrayList<String>();

   for (int i=0;i<transaction.getLength();i++) {

       transactions.add(transaction.item(i).getAttributes().getNamedItem("id").getNodeValue());
       transactions.add(transaction.item(i).getAttributes().getNamedItem("type").getNodeValue());
       transactions.add(transaction.item(i).getAttributes().getNamedItem("amount").getNodeValue());
       transactions.add(transaction.item(i).getAttributes().getNamedItem("deposit").getNodeValue());

   }

    }



}
