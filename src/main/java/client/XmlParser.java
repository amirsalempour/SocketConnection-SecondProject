package client;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * Created by Dotin school 6 on 4/12/2016.
 */
public class XmlParser {
    private String terminalId;
    private String serverIp;
    private int portNumber;
    public String outLogPath;
    private String Type;
    DocumentBuilderFactory dbFactory;
    DocumentBuilder dBuilder;
    Document doc;

    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();


    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = this.portNumber;
    }

    public String getOutLogPath() {
        return outLogPath;
    }

    public void setOutLogPath(String outLogPath) {
        this.outLogPath = outLogPath;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

//    public void setTransactions(ArrayList<Transaction> transactions) {
//        this.transactions = transactions;
//    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public ArrayList readXML(String filePath) throws Exception {


        File file = new File(filePath);
        dbFactory = DocumentBuilderFactory.newInstance().newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(file);
        NodeList n1 = doc.getElementsByTagName("terminal");
        NodeList server = doc.getElementsByTagName("server");
        serverIp = server.item(0).getAttributes().item(0).getNodeValue();
        portNumber = Integer.parseInt(server.item(0).getAttributes().item(1).getNodeValue());
        NodeList outLog = doc.getElementsByTagName("outLog");
        NodeList transaction = doc.getElementsByTagName("transaction");

        terminalId = n1.item(0).getAttributes().item(0).getNodeValue();
        Type = n1.item(0).getAttributes().item(1).getNodeValue();
        serverIp = server.item(0).getAttributes().item(0).getNodeValue();
        outLogPath = outLog.item(0).getAttributes().item(0).getNodeValue();
        XmlParser xmlParser = new XmlParser();
        xmlParser.setPortNumber(portNumber);

        for (int i = 0; i < transaction.getLength(); i++) {
            Transaction transactionItem = new Transaction();


            transactionItem.setId(transaction.item(i).getAttributes().getNamedItem("id").getNodeValue());
            transactionItem.setType( transaction.item(i).getAttributes().getNamedItem("type").getNodeValue());
            transactionItem.setAmount(new BigDecimal(transaction.item(i).getAttributes().getNamedItem("amount").getNodeValue()));
            transactionItem.setDepositNumber( transaction.item(i).getAttributes().getNamedItem("deposit").getNodeValue());

            transactions.add(transactionItem);


        }

        return transactions;
//        return null;
    }


}
