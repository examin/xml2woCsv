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
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                }
            dimensionsList.add(currDimension);
//            allMeasureFolders.add(getMeasureFolder(dimensionNodeList.item(i)));
        }
        createExcel(dimensionsList);
        return dimensionsList;
    }

    private static MeasureFolder getMeasureFolder(NodeList measureFolderNodeList) {
        MeasureFolder allNameMeasureFolder = null;
        MeasureFolder nuMeasureFolder = null;
        MeasureFolder denMeasureFolder = null;
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
//                        System.out.println("\n MeasurefolderName : " + allNameMeasureFolder.getName());
                        System.out.println("MeasurefolderSize : " + measuresNodeList.getLength());
                        break;
                    case 2:
                        derivedMeasureFolder = new MeasureFolder(mfelement.getElementsByTagName("name").item(0).getTextContent(), getMeasuresList(measuresNodeList));
                        break;
                    case 3:
                        directMeasureFolder = new MeasureFolder(mfelement.getElementsByTagName("name").item(0).getTextContent(), getMeasuresList(measuresNodeList));
                        break;
                    case 4:
                        nuMeasureFolder = new MeasureFolder(mfelement.getElementsByTagName("name").item(0).getTextContent(), getMeasuresList(measuresNodeList));
                        break;
                    case 1:
                        denMeasureFolder = new MeasureFolder(mfelement.getElementsByTagName("name").item(0).getTextContent(), getMeasuresList(measuresNodeList));
                        }

            }
        }

        usingDirectResolve1(directMeasureFolder.getMeasures(), nuMeasureFolder, denMeasureFolder, derivedMeasureFolder,allNameMeasureFolder);

        //todo : resolve for firstmeasurefolder and resturn it
        return allNameMeasureFolder;
    }

    private static void usingDirectResolve1(HashMap directMeasures, MeasureFolder numMeasureFolder, MeasureFolder denMeasureFolder, MeasureFolder derivedMeasureFolder, MeasureFolder allNameMeasureFolder) {
        //traverse num and deno
        HashMap<String, String> numerator = numMeasureFolder.getMeasures();
        HashMap<String, String> denomerator = denMeasureFolder.getMeasures();
        HashMap<String, String> derived = derivedMeasureFolder.getMeasures();
        HashMap<String, String> allName = allNameMeasureFolder.getMeasures();

        Set<String> numIterator = numerator.keySet();
        Set<String> denoIterator = denomerator.keySet();
        Set<String> derivedIterator = derived.keySet();
        Set<String> allNameIterator = allName.keySet();

        for(String curr: numIterator) {
            String expression = numerator.get(curr);
            HashSet<String> allRef =  getAlldependencies(expression);
            HashMap <String ,String> resolvedMap  = resolveDirectRef(allRef,directMeasures);

            Set<String> resolvedKeys = resolvedMap.keySet();
            if(!allRef.isEmpty()) {
                for (String replace : resolvedKeys) {
                    expression = expression.replace("["+replace+"]", resolvedMap.get(replace));
                }
                numerator.put(curr, expression);
            }


        }
        for(String curr: denoIterator) {
            String expression = denomerator.get(curr);
            HashSet<String> allRef =  getAlldependencies(expression);
            HashMap <String ,String> resolvedMap  = resolveDirectRef(allRef,directMeasures);

            Set<String> resolvedKeys = resolvedMap.keySet();
            if(!allRef.isEmpty()) {
                for (String replace : resolvedKeys) {
                    expression = expression.replace("["+replace+"]",resolvedMap.get(replace));
                }
                denomerator.put(curr, expression);
            }
            //todo: ""complete for num deno and also for derived then thin mof first
        }
        for(String curr: derivedIterator) {
            String expression = derived.get(curr);
            HashSet<String> allRef =  getAlldependencies(expression);
            if(!allRef.isEmpty()) {
                HashMap<String, String> resolvedMap = resolveNDRef(allRef, numerator, denomerator,directMeasures);

                Set<String> resolvedKeys = resolvedMap.keySet();
                for (String replace : resolvedKeys) {
                    expression = expression.replace("["+replace+"]", resolvedMap.get(replace));
                }
                derived.put(curr, expression);
            }

        }
        for(String curr: allNameIterator) {
            String expression = allName.get(curr);
            if(curr.matches(".*Score Overrides.*")){
                System.out.println("remove this");
            }
            HashSet<String> allRef =  getAlldependencies(expression);
            if(!allRef.isEmpty()) {
                HashMap<String, String> resolvedMap = resolveDirectRef(allRef, directMeasures);

                Set<String> resolvedKeys = resolvedMap.keySet();
                for (String replace : resolvedKeys) {

                        expression = expression.replace("["+replace+"]", resolvedMap.get(replace));

                }
                allName.put(curr, expression);
            }

        }
        for(String curr: allNameIterator) {
            String expression = allName.get(curr);
            if(curr.matches(".*Score Overrides.*")){
                System.out.println("remove this");
            }
            HashSet<String> allRef =  getAlldependencies(expression);
            if(!allRef.isEmpty()) {
                HashMap<String, String> resolvedMap = resolveNameRef(allRef, numerator, denomerator, derived);

                Set<String> resolvedKeys = resolvedMap.keySet();
                for (String replace : resolvedKeys) {
                    expression = expression.replace("["+replace+"]", resolvedMap.get(replace));
                }
                allName.put(curr, expression);
            }

        }

       // System.out.println("remove this ");

    }
        private static HashMap resolveDirectRef(HashSet<String> allRefInExp, HashMap<String, String> direct) {
        HashMap<String,String> resolvedRef =  new HashMap();
        for(String curr :allRefInExp){
            if(direct.containsKey(curr)){
                resolvedRef.put(curr,direct.get(curr));
                //System.out.println("resolveDirectRef direct resolved "+curr+" \n* "+ direct.get(curr));

            }
        }
        return resolvedRef;
    }
    private static HashMap resolveNameRef(HashSet<String> allRefInExp, HashMap<String, String> numerator, HashMap<String, String> denomerator,HashMap<String, String> derived) {
        HashMap<String,String> resolvedRef =  new HashMap();
        for(String curr : allRefInExp){
            if(numerator.containsKey(curr)){
                resolvedRef.put(curr,numerator.get(curr));
//                System.out.println(" resolveNameRef numerator resolved "+curr+" \n* "+ numerator.get(curr));
            }
            else {
                if(denomerator.containsKey(curr)){
                    resolvedRef.put(curr,denomerator.get(curr));
//                   System.out.println(" resolveNameRef deno resolved "+curr+" \n* "+ denomerator.get(curr));

                }
                else
                {
                    if(derived.containsKey(curr)){
                        resolvedRef.put(curr,derived.get(curr));
//                        System.out.println("resolveNameRef drived resolved "+curr+" \n* "+ derived.get(curr));

                    }
                }

            }
        }
        return resolvedRef;
    }
    private static HashMap resolveNDRef(HashSet<String> allRefInExp, HashMap<String, String> numerator, HashMap<String, String> denomerator ,  HashMap<String, String> direct) {
        HashMap<String,String> resolvedRef =  new HashMap();
        for(String curr :allRefInExp){
            if(numerator.containsKey(curr)){
                resolvedRef.put(curr,numerator.get(curr));
                //System.out.println("resolveNDRef( numerator resolved "+curr+" \n* "+ numerator.get(curr));

            }
            else {
                if(denomerator.containsKey(curr)){
                    resolvedRef.put(curr,denomerator.get(curr));
//                    System.out.println("resolveNDRef( deno resolved "+curr+" \n* "+ denomerator.get(curr));


                }
                else
                    if (direct.containsKey(curr)){
                        resolvedRef.put(curr,direct.get(curr));
//                    System.out.println("resolveNDRef( deno resolved "+curr+" \n* "+ denomerator.get(curr));


                    }
            }
        }
        return resolvedRef;
    }

    private static String preProcessExpression(String expression) {
        expression = expression.replaceAll("((\\[MEASURES\\]\\.)[^\\]]*]\\.)", " ");
        expression = expression.replaceAll("\\[PHYSICAL_LAYER\\]\\.[^\\]]*]\\.\\[(.*?(?=\\]))]", " $1 ");
        expression = expression.replaceAll("(?<=\\d)(?=[a-zA-Z])", " ");
        return expression.trim();
    }

    private static HashSet<String> getAlldependencies(String expression) {
        HashSet <String> allRef = new HashSet<>();
        final Pattern pattern = Pattern.compile("(?<=\\[).*?(?=\\])", Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            allRef.add(matcher.group(0).trim());
        }
        return allRef;
    }

    private static HashMap<String, String> getMeasuresList(NodeList measuresNodeList) {
        HashMap<String, String> measureList = new HashMap<>();
        for (int k = 0; k < measuresNodeList.getLength(); k++) {
            Node mNode = measuresNodeList.item(k);
            if (mNode.getNodeType() == Node.ELEMENT_NODE) {
                Element melement = (Element) mNode;
                String name = melement.getElementsByTagName("name").item(0).getTextContent();
                String expression = melement.getElementsByTagName("expression").item(0).getTextContent();

                expression = preProcessExpression(expression);
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

    private static void createExcel(ArrayList<Dimesion>  allDimesions){

        String excelFileName = "/home/examin/Videos/Axslogic.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook();
        Boolean firstTime =true;
        for(Dimesion currDimension: allDimesions ) {

            String workBookname =currDimension.getName();
            if(!firstTime) {
                try {
                    workbook = new XSSFWorkbook(new FileInputStream(excelFileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                firstTime =false;

            }
            XSSFSheet sheet = workbook.createSheet(workBookname);
                int rowNum = 0;
                Set<String> directKeys = currDimension.getMeasureFoldersInDimension().getMeasures().keySet();
                for (String curr : directKeys) {
                    Row row = sheet.createRow(rowNum++);
                    Cell cell1 = row.createCell(0);
                    cell1.setCellValue(curr);

                    Cell cell2 = row.createCell(1);
                    cell2.setCellValue(currDimension.getMeasureFoldersInDimension().getMeasures().get(curr));
                }


            System.out.println("Done");

        }

        try{FileOutputStream outputStream = new FileOutputStream(excelFileName);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
