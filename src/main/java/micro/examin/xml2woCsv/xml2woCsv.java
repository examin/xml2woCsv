package micro.examin.xml2woCsv;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class xml2woCsv {
    public static void main(String[] args) throws IOException, XMLStreamException {
        String xmlFilePath = ("/home/examin/Videos/model.xml");
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(new File(xmlFilePath));
            document.getDocumentElement().normalize();
            getAllDimensions(document);


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
                String currDimensionName = dimElement.getElementsByTagName("name").item(0).getTextContent();
                currDimension = new Dimesion(currDimensionName, getMeasureFolder(measureFolderNodeList));
                System.out.println("Dimension : " + currDimension.getName() + "\n");
                createExcel(currDimension.getMeasureFoldersInDimension().getMeasures(), currDimensionName);
            }
            dimensionsList.add(currDimension);
//            allMeasureFolders.add(getMeasureFolder(dimensionNodeList.item(i)));
        }

        return dimensionsList;
    }

    private static MeasureFolder getMeasureFolder(NodeList measureFolderNodeList) {
        MeasureFolder allNameMeasureFolder = null;
        MeasureFolder nuMeasureFolder = null;
        MeasureFolder denMeasureFolder = null;
        MeasureFolder resolvedMeasureFolder = null;
        MeasureFolder directMeasureFolder = null;
        MeasureFolder derivedMeasureFolder = null;


        for (int j = 0; j < measureFolderNodeList.getLength(); j++) {
            Node mfNode = measureFolderNodeList.item(j);
            if (mfNode.getNodeType() == Node.ELEMENT_NODE) {
                Element mfelement = (Element) mfNode;
                NodeList measuresNodeList = ((Element) mfNode).getElementsByTagName("measure");

                switch (j) {
                    case 0:
                        allNameMeasureFolder = new MeasureFolder(mfelement.getElementsByTagName("name").item(0).getTextContent(), getMeasuresList(measuresNodeList));
                        System.out.println("\n MeasurefolderName : " + allNameMeasureFolder.getName());
                        System.out.println("MeasurefolderSize : " + measuresNodeList.getLength());
                        break;
                    case 2:
                        derivedMeasureFolder = new MeasureFolder(mfelement.getElementsByTagName("name").item(0).getTextContent(), getMeasuresList(measuresNodeList));
                        System.out.println("\n MeasurefolderName : " + derivedMeasureFolder.getName());
                        System.out.println("MeasurefolderSize : " + measuresNodeList.getLength());
                        break;
                    case 3:
                        directMeasureFolder = new MeasureFolder(mfelement.getElementsByTagName("name").item(0).getTextContent(), getMeasuresList(measuresNodeList));
                        System.out.println("\n MeasurefolderName : " + directMeasureFolder.getName());
                        System.out.println("MeasurefolderSize : " + measuresNodeList.getLength());
                        break;
                    case 4:
                        nuMeasureFolder = new MeasureFolder(mfelement.getElementsByTagName("name").item(0).getTextContent(), getMeasuresList(measuresNodeList));
                        System.out.println("\n MeasurefolderName : " + nuMeasureFolder.getName());
                        System.out.println("MeasurefolderSize : " + measuresNodeList.getLength());
                        break;
                    case 1:
                        denMeasureFolder = new MeasureFolder(mfelement.getElementsByTagName("name").item(0).getTextContent(), getMeasuresList(measuresNodeList));
                        System.out.println("\n MeasurefolderName : " + denMeasureFolder.getName());
                        System.out.println("MeasurefolderSize : " + measuresNodeList.getLength());
                }

            }
        }

        usingDirectResolve1(directMeasureFolder.getMeasures(), nuMeasureFolder, denMeasureFolder, derivedMeasureFolder);
        //todo : resolve for firstmeasurefolder and resturn it
        return directMeasureFolder;
    }

    private static void usingDirectResolve1(HashMap directMeasures, MeasureFolder numMeasureFolder, MeasureFolder denMeasureFolder, MeasureFolder derivedMeasureFolder) {
        //traverse num and deno
        HashMap<String, String> numerator = numMeasureFolder.getMeasures();
        HashMap<String, String> denomerator = denMeasureFolder.getMeasures();
        HashMap<String, String> derived = derivedMeasureFolder.getMeasures();
        Set<String> numIterator = numerator.keySet();
        Set<String> denoIterator = denomerator.keySet();
        Set<String> derivedIterator = derived.keySet();
//        for(String curr: numIterator) {
//            String expression = numerator.get(curr);
//            HashMap <String ,String> resolvedMap = getAlldependencies(expression);
//            Set<String> resolvedKeys = resolvedMap.keySet();
//            for(String replace :resolvedKeys){
//                expression.replaceAll(replace,resolvedMap.get(replace));
//            }
//
//        }
//        for(String curr: denoIterator) {
//            String expression = denomerator.get(curr);
//            HashMap<String, String> resolved = getAlldependencies(expression);
//            //todo: ""complete for num deno and also for derived then thin mof first
//        }
//        for(String curr: numIterator) {
//            String expression = numerator.get(curr);
//            HashMap <String ,String> resolvedMap = getAlldependencies(expression);
//            Set<String> resolvedKeys = resolvedMap.keySet();
//            for(String replace :resolvedKeys){
//                expression.replaceAll(replace,resolvedMap.get(replace));
//            }
//
//        }

    }

    private static HashMap<String, String> getAlldependencies(String expression) {
        return null;
    }

    private static HashMap<String, String> getMeasuresList(NodeList measuresNodeList) {
        HashMap<String, String> measureList = new HashMap<>();
        for (int k = 0; k < measuresNodeList.getLength(); k++) {
            Node mNode = measuresNodeList.item(k);
            if (mNode.getNodeType() == Node.ELEMENT_NODE) {
                Element melement = (Element) mNode;
                String name = melement.getElementsByTagName("name").item(0).getTextContent();
                String expression = melement.getElementsByTagName("expression").item(0).getTextContent();
                try {
                    String aggregation = melement.getElementsByTagName("regularAggregate").item(0).getTextContent();
                    expression = aggregation + " ( " + expression + " ) ";

                    //todo : fix [measures] and [primary] and space after them
                    measureList.put(name, expression);
                } catch (Exception e) {
                    e.getStackTrace();
                    measureList.put(name, expression);
                }

                // System.out.println("Measure : " + currMeasure.toString());

            }

        }
        return measureList;

    }

    private static void createExcel(HashMap<String, String> map, String name) {
        String excelFileName = "/home/examin/Videos/Axslogic.xlsx";

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();

            //XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(excelFileName));

            XSSFSheet sheet = workbook.createSheet(name);
            int rowNum = 0;
            Set<String> directKeys = map.keySet();
            for (String curr : directKeys) {
                Row row = sheet.createRow(rowNum++);
                Cell cell1 = row.createCell(0);
                cell1.setCellValue(curr);

                Cell cell2 = row.createCell(1);
                cell2.setCellValue(map.get(curr));
            }
            FileOutputStream outputStream = new FileOutputStream(excelFileName);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");

    }
}
