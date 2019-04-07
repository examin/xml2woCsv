package micro.examin.xml2woCsv;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class xml2woCsv2 {
    static ArrayList<Dimesion> allDimensions;
    static ArrayList<MeasureFolder> allMeasureFolders;
    static ArrayList<Measure> allMeasures;

    public static void main(String[] args) throws IOException, XMLStreamException {
        String xmlFilePath = ("/home/examin/Videos/model.xml");
        XMLInputFactory factory = XMLInputFactory.newInstance();

        try (BufferedReader bf = new BufferedReader(new FileReader(xmlFilePath))) {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(new File(xmlFilePath));
            document.getDocumentElement().normalize();
            allDimensions = getAllDimensions(document);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Dimesion> getAllDimensions(Document doc) throws XPathExpressionException {
        ArrayList<Dimesion> dimensionsList = new ArrayList<>();
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList dimensionNodeList = (NodeList) xPath.compile(Dimesion.ELEMENT_DIMENSION).evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < 1;/*dimensionNodeList.getLength();*/ i++) {
            Dimesion currDimension = null;
            Node nNode = dimensionNodeList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element dimElement = (Element) nNode;
                currDimension = new Dimesion(dimElement.getElementsByTagName("name").item(0).getTextContent());
                System.out.println("Dimension : " + currDimension.name + "\n");

                NodeList measureFolderNodeList = ((Element) nNode).getElementsByTagName("measureFolder");
//                System.out.println("MeasurefolderSize : " + measureFolderNodeList.getLength());
                for (int j = 0; j < measureFolderNodeList.getLength(); j++) {
                    MeasureFolder currMeasureFolder = null;
                    Node mfNode = measureFolderNodeList.item(j);
                    if (mfNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element mfelement = (Element) mfNode;
                        System.out.println("\n MeasurefolderName : " + mfelement.getElementsByTagName("name").item(0).getTextContent());

                        NodeList measuresNodeList = ((Element) mfNode).getElementsByTagName("measure");
                        System.out.println("MeasurefolderSize : " + measuresNodeList.getLength());
                        for (int k = 0; k < measuresNodeList.getLength(); k++) {
                            MeasureFolder currMeasure = null;
                            Node mNode = measuresNodeList.item(k);
                            if (mNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element melement = (Element) mNode;
                                System.out.println("Measure : " + melement.getElementsByTagName("name").item(0).getTextContent());


                            }

                        }


                    }

                }

            }
            dimensionsList.add(currDimension);
//            allMeasureFolders.add(getMeasureFolder(dimensionNodeList.item(i)));
        }

        return dimensionsList;
    }

    private static MeasureFolder getMeasureFolder(Node item) {


        return new MeasureFolder("name");
    }
}
