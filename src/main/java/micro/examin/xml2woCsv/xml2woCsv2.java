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
import java.util.HashMap;
import java.util.Map;

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

    private static MeasureFolder getMeasureFolderList(NodeList measureFolderNodeList) {
        //System.out.println("MeasurefolderSize : " + measureFolderNodeList.getLength());

        MeasureFolder firstMeasureFolder = new MeasureFolder("first", new HashMap<>());
        HashMap<String,String> directMap =new HashMap();
        MeasureFolder ndMeasureFolder = new MeasureFolder("rest", new HashMap<>());
        MeasureFolder derivedMeasureFolder = new MeasureFolder("direct", new HashMap<>());
        MeasureFolder restMeasureFolder = new MeasureFolder("rest", new HashMap<>());

        Node mfNode = measureFolderNodeList.item(3);
        if (mfNode.getNodeType() == Node.ELEMENT_NODE) {
            NodeList measuresNodeList = ((Element) mfNode).getElementsByTagName("measure");
            directMap = new HashMap<>(getFirstDirectMeasuresList(measuresNodeList));
//            System.out.println("\n MeasurefolderName : " + ((Element) mfNode).getElementsByTagName("name").item(0).getTextContent());
//            System.out.println("*Size : " + measuresNodeList.getLength());
        }



        mfNode = measureFolderNodeList.item(1);
        if (mfNode.getNodeType() == Node.ELEMENT_NODE) {
            NodeList measuresNodeList = ((Element) mfNode).getElementsByTagName("measure");
            ndMeasureFolder.measures.putAll(getMeasuresList(measuresNodeList,directMap));
//            System.out.println("\n MeasurefolderName : " + ((Element) mfNode).getElementsByTagName("name").item(0).getTextContent());
//            System.out.println("*Size : " + measuresNodeList.getLength());
        }
        mfNode = measureFolderNodeList.item(4);
        if (mfNode.getNodeType() == Node.ELEMENT_NODE) {
            NodeList measuresNodeList = ((Element) mfNode).getElementsByTagName("measure");
            ndMeasureFolder.measures.putAll(getMeasuresList(measuresNodeList,directMap));
//            System.out.println("\n MeasurefolderName : " + ((Element) mfNode).getElementsByTagName("name").item(0).getTextContent());
//            System.out.println("*Size : " + measuresNodeList.getLength());
        }


        mfNode = measureFolderNodeList.item(2);
        if (mfNode.getNodeType() == Node.ELEMENT_NODE) {
            NodeList measuresNodeList = ((Element) mfNode).getElementsByTagName("measure");
            derivedMeasureFolder.measures.putAll(getMeasuresList(measuresNodeList,directMap));
//            System.out.println("\n MeasurefolderName : " + ((Element) mfNode).getElementsByTagName("name").item(0).getTextContent());
//            System.out.println("*Size : " + measuresNodeList.getLength());
        }


//        mfNode = measureFolderNodeList.item(0);
//        if (mfNode.getNodeType() == Node.ELEMENT_NODE) {
//            NodeList measuresNodeList = ((Element) mfNode).getElementsByTagName("measure");
//            firstMeasureFolder.measures = getMeasuresList(measuresNodeList,directMap,);
//            System.out.println("\n MeasurefolderName : " + ((Element) mfNode).getElementsByTagName("name").item(0).getTextContent());
//            System.out.println("*Size : " + measuresNodeList.getLength());
//
//        }


        resolvedMeasures(restMeasureFolder, derivedMeasureFolder);
        return ndMeasureFolder;
    }

    public static MeasureFolder resolvedMeasures(MeasureFolder restMeasureFolder, MeasureFolder derivedMeasureFolder) {

        //triverse all derived and rest and put all derived

        return new MeasureFolder("rest");
    }

    private static HashMap<String, Measure> getMeasuresList(NodeList measuresNodeList, HashMap directMap) {
        HashMap<String, Measure> measureArrayList = new HashMap<>();
        for (int k = 0; k < measuresNodeList.getLength(); k++) {

            Node mNode = measuresNodeList.item(k);
            if (mNode.getNodeType() == Node.ELEMENT_NODE) {
                Measure currMeasure;
                Element melement = (Element) mNode;
                String name = melement.getElementsByTagName("name").item(0).getTextContent();
                String expression = melement.getElementsByTagName("expression").item(0).getTextContent();



                try {
                    String aggregation = melement.getElementsByTagName("regularAggregate").item(0).getTextContent();
                    currMeasure = new Measure(name, expression, aggregation, directMap);
                } catch (Exception e) {
                    e.getStackTrace();
                    currMeasure = new Measure(name, expression, "none", directMap);
                }
                // System.out.println("Measure : " + currMeasure.toString());
                measureArrayList.put(name, currMeasure);
            }

        }
        return measureArrayList;
    }
    private static HashMap getFirstDirectMeasuresList(NodeList measuresNodeList) {
        HashMap<String, String> measureArrayList = new HashMap<>();
        for (int k = 0; k < measuresNodeList.getLength(); k++) {

            Node mNode = measuresNodeList.item(k);
            if (mNode.getNodeType() == Node.ELEMENT_NODE) {
                String finalExpression ="";
                Element melement = (Element) mNode;
                String name = melement.getElementsByTagName("name").item(0).getTextContent();
                String expression = melement.getElementsByTagName("expression").item(0).getTextContent();
                try {
                    String aggregation = melement.getElementsByTagName("regularAggregate").item(0).getTextContent();
                    finalExpression = aggregation+"("+expression+")";
                } catch (Exception e) {
                    e.getStackTrace();

                }
                // System.out.println("Measure : " + currMeasure.toString());
                measureArrayList.put(name, finalExpression);
            }

        }
        return measureArrayList;
    }
}
