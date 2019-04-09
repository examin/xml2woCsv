package micro.examin.xml2woCsv;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;

public class xml2woCsv2 {
    static ArrayList<Dimesion> allDimensions;

    public static void main(String[] args) {
        String xmlFilePath = ("/home/examin/Videos/model.xml");

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(new File(xmlFilePath));
            document.getDocumentElement().normalize();
            allDimensions = getAllDimensions(document);
            System.out.println("dfd");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Dimesion> getAllDimensions(Document doc) throws XPathExpressionException {
        ArrayList<Dimesion> dimensionsList = new ArrayList<>();
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList dimensionNodeList = (NodeList) xPath.compile(Dimesion.ELEMENT_DIMENSION).evaluate(doc, XPathConstants.NODESET);

        //System.out.println("Dimension Arraylist size : " + dimensionNodeList.getLength() + "\n");
        for (int i = 0; i < dimensionNodeList.getLength(); i++) {
            Dimesion currDimension = null;
            Node nNode = dimensionNodeList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element dimElement = (Element) nNode;


                NodeList measureFolderNodeList = ((Element) nNode).getElementsByTagName("measureFolder");
//                System.out.println("MeasurefolderSize : " + measureFolderNodeList.getLength());

                currDimension = new Dimesion(dimElement.getElementsByTagName("name").item(0).getTextContent(), getMeasureFolderList(measureFolderNodeList));
                // 1System.out.println("Dimension : " + currDimension.name + "\n");
            }
            dimensionsList.add(currDimension);
//            allMeasureFolders.add(getMeasureFolder(dimensionNodeList.item(i)));
        }

        return dimensionsList;
    }

    private static ArrayList<MeasureFolder> getMeasureFolderList(NodeList measureFolderNodeList) {
        ArrayList<MeasureFolder> meaureFolderArraysList = new ArrayList<>();
        //System.out.println("MeasurefolderSize : " + measureFolderNodeList.getLength());


        for (int j = 0; j < measureFolderNodeList.getLength(); j++) {
            MeasureFolder currMeasureFolder = null;
            Node mfNode = measureFolderNodeList.item(j);
            if (mfNode.getNodeType() == Node.ELEMENT_NODE) {
                Element mfelement = (Element) mfNode;
                NodeList measuresNodeList = ((Element) mfNode).getElementsByTagName("measure");


                currMeasureFolder = new MeasureFolder(mfelement.getElementsByTagName("name").item(0).getTextContent(), getMeasuresList(measuresNodeList));
                meaureFolderArraysList.add(currMeasureFolder);


                // 1System.out.println("\n MeasurefolderName : " + currMeasureFolder.name);
                // 1System.out.println("MeasurefolderSize : " + measuresNodeList.getLength());
            }
        }

        return meaureFolderArraysList;
    }

    private static ArrayList<Measure> getMeasuresList(NodeList measuresNodeList) {
        ArrayList<Measure> measureArrayList = new ArrayList<>();
        for (int k = 0; k < measuresNodeList.getLength(); k++) {
            Measure currMeasure = null;
            Node mNode = measuresNodeList.item(k);
            if (mNode.getNodeType() == Node.ELEMENT_NODE) {
                Element melement = (Element) mNode;
                String name = melement.getElementsByTagName("name").item(0).getTextContent();
                String expression = melement.getElementsByTagName("expression").item(0).getTextContent();

                try {
                    String aggregation = melement.getElementsByTagName("regularAggregate").item(0).getTextContent();
                    currMeasure = new Measure(name, expression, aggregation);
                } catch (Exception e) {
                    e.getStackTrace();
                    currMeasure = new Measure(name, expression);
                }
                // System.out.println("Measure : " + currMeasure.toString());

            }
            measureArrayList.add(currMeasure);

        }
        return measureArrayList;
    }
}
