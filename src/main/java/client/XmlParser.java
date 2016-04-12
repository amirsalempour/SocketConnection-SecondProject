package client;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by Dotin school 6 on 4/12/2016.
 */
public class XmlParser {
    private String terminalId;
    private String serverIp;
    public String outLogPath;

public void readXML() throws Exception{


        File file=new File("src\\main\\java\\client/terminal.xml");
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
        Document doc=dBuilder.parse(file);
        doc.getDocumentElement().normalize();
NodeList n1= doc.getElementsByTagName("terminal");
        System.out.println("Root element :" + n1.getLength());



    }







//    public void ReadXml() {
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        try {
//
//
//            DocumentBuilder builder = factory.newDocumentBuilder();
//         //   InputSource inputSource=new InputSource("terminal.xml");
//
//            Document doc = builder.parse("terminal.xml");
//            System.out.println(doc);
//            XPath xPath= XPathFactory.newInstance().newXPath();
//            Node node=(Node)xPath.evaluate()
//          //  NodeList nodeList=doc.getElementsByTagName("server");
//            for (int i = 0; i <nodeList.getLength() ; i++) {
//                Node node= (Node) nodeList.item(i);
//                if (node.hasAttributes()){
//                    Attr attr=(Attr) node.getAttributes().getNamedItem("port");
//                    if ( attr !=null){
//                        String attribute=attr.getValue();
//                        System.out.println("attribute: "+ attribute);
//                    }
//                }
//
//            }
//
//          //  System.out.println("ID: " + document.getDocumentElement().getChildNodes().item(0).getFirstChild().getChildNodes().item(0).getAttributes().getNamedItem("terminal id").getNodeValue());
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }

//    }


}
