package client;


import jdk.internal.org.xml.sax.InputSource;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;
import java.io.StringReader;

/**
 * Created by Dotin school 6 on 4/12/2016.
 */
public class XmlParser {
    private String terminalId;
    private String serverIp;
    public String outLogPath;


    public void ReadXml() {
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is=new InputSource(new StringReader("terminal.xml"));

            Document doc = builder.parse(String.valueOf(is));
            NodeList nodeList=doc.getElementsByTagName("server");
            for (int i = 0; i <nodeList.getLength() ; i++) {
                Node node= (Node) nodeList.item(i);
                if (node.hasAttributes()){
                    Attr attr=(Attr) node.getAttributes().getNamedItem("port");
                    if ( attr !=null){
                        String attribute=attr.getValue();
                        System.out.println("attribute: "+ attribute);
                    }
                }

            }

          //  System.out.println("ID: " + document.getDocumentElement().getChildNodes().item(0).getFirstChild().getChildNodes().item(0).getAttributes().getNamedItem("terminal id").getNodeValue());



        } catch (Exception e) {
            e.printStackTrace();

        }

    }


}
