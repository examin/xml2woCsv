package micro.examin.xml2woCsv;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.ArrayList;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class xml2woCsv2 {
    static ArrayList<Dimesion> allDimensions;
    static MeasureFolder[] allMeasureFolders;
    static Measure[] allMeasures;

    public static void main(String args[]) throws IOException, XMLStreamException {
        String xmlFilePath = ("/home/examin/Videos/model.xml");
        XMLInputFactory factory = XMLInputFactory.newInstance();

        try(BufferedReader bf =  new BufferedReader(new FileReader(xmlFilePath))) {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder =  dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(new File(xmlFilePath));
            document.getDocumentElement().normalize();
            allDimensions =getAllDimensions(document);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Dimesion> getAllDimensions(Document doc) throws XPathExpressionException {
        ArrayList<Dimesion> dimensionsList = new ArrayList<>();
        XPath xPath  = XPathFactory.newInstance().newXPath();
        NodeList nodeList =(NodeList) xPath.compile(Dimesion.ELEMENT_DIMENSION).evaluate(doc, XPathConstants.NODESET);

        for(int i =0;i<nodeList.getLength();i++){
            Dimesion currDimension = null;
            Node nNode = nodeList.item(i);
            allMeasureFolders = getMeasureFolder(nodeList.item(i));
            if(nNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element)nNode;
                currDimension =  new Dimesion(element.getElementsByTagName("name").item(0).getTextContent());
                System.out.println("DimensionName" + element.getElementsByTagName("name").item(0).getTextContent());

            }
            dimensionsList.add(currDimension);
        }

        return dimensionsList;
    }

    private static MeasureFolder[] getMeasureFolder(Node item) {



        return new MeasureFolder[0];
    }
}
