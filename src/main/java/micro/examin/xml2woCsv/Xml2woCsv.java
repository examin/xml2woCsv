package micro.examin.xml2woCsv;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertNotNull;

public class Xml2woCsv {
    public static SortedSet<String> microMeasureCondition = new TreeSet<>();

    public static void main(String[] args) throws IOException, XMLStreamException {
        String xmlFilePath = ("/home/examin/Videos/new/model.xml");
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(new File(xmlFilePath));
            document.getDocumentElement().normalize();
            getAllDimensions(document);
            System.out.println(microMeasureCondition.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Dimesion> getAllDimensions(Document doc) throws XPathExpressionException {
        ArrayList<Dimesion> dimensionsList = new ArrayList<>();
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList dimensionNodeList = (NodeList) xPath.compile(Dimesion.ELEMENT_DIMENSION).evaluate(doc, XPathConstants.NODESET);

//      System.out.println("Dimension Arraylist size : " + dimensionNodeList.getLength() + "\n");
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
            processForElastic(currDimension);
            dimensionsList.add(currDimension);

//            allMeasureFolders.add(getMeasureFolder(dimensionNodeList.item(i)));
        }
        createExcel(dimensionsList);
        return dimensionsList;
    }

    private static void processForElastic(Dimesion currDimension) {
        HashMap<String, String> measures = currDimension.getMeasureFoldersInDimension().getMeasures();
        HashSet<String> itr = new HashSet<>(measures.keySet());
        for (String measure : itr) {
            String measureValue = (measures.get(measure))
                    .replace("IF", "if")
                    .replaceAll(" END", "").trim()
                    .replaceAll(" AND ", " && ")
                    .replaceAll(" OR ", "||")
                    .replaceAll("(0)", "0")
                    .replaceAll("  ", " ")
                    .replaceAll("   ", " ")
                    .replaceAll("  ", " ")
                    .replaceAll("   ", " ")
                    .replaceAll("CASE WHEN", "if ( ")
                    .replaceAll(" THEN ", " ) return { ")
                    .replaceAll("else 0", "else { return 0 }")
                    .replaceAll("ELSE 0", "else { return 0 }")
                    .replaceAll("ELSE ( 0 )", "else { return 0 }")
                    .replaceAll("ELSE NULL", "else { return 0 }")
                    .replaceAll("END", "")
                    .replace("*", " * ")
                    .replace("/", " / ")
                    .replace("(", " ( ")
                    .replace("{", " } ")
                    .replace("}", " } ")
                    .replace(")", " ) ")
                    .replace("=", " = ")
                    // .replaceAll("if \\(.?\\((.+?)\\)\\)","if ( $1 )")
                    .replaceAll("  ", " ")
                    .replaceAll("   ", " ")
                    .replaceAll("  ", " ")
                    .replaceAll("   ", " ")
                    .replaceAll("average", "`average")
                    .replaceAll("sum", "`sum")
                    .replaceAll("count", "`count");
            measures.put(measure, measureValue);
            System.out.println(measureValue);
        }
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
                        allNameMeasureFolder = new MeasureFolder(mfelement.getElementsByTagName("name").item(0).getTextContent(), getMeasuresListNoAgg(measuresNodeList));
                        System.out.println("\n MeasurefolderName : " + allNameMeasureFolder.getName());
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

        usingDirectResolve1(directMeasureFolder.getMeasures(), nuMeasureFolder, denMeasureFolder, derivedMeasureFolder, allNameMeasureFolder);

        //todo : resolve for firstmeasurefolder and return it
        Set<String> measureValues = new HashSet<>(allNameMeasureFolder.getMeasures().values());
        int index = 1;
        Iterator itr = measureValues.iterator();
        while (itr.hasNext()) {
            String curr = itr.next().toString();
            System.out.printf("%s\n", curr);

            Pattern pattern = Pattern.compile("(\\w+)\\s?(=|!=|>|<|>=|<=)\\s?(\\w+)|(\\w+)\\s(in|any|not in|between|not between|like)\\s?(\\w+)", Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(curr);

            while (matcher.find()) {
                //use this to print measureconditions\n  System.out.println(matcher.group(0));
                microMeasureCondition.add(matcher.group(0));

            }
        }

        return allNameMeasureFolder;
    }

    private static void usingDirectResolve1(HashMap<String, String> directMeasures, MeasureFolder numMeasureFolder, MeasureFolder denMeasureFolder, MeasureFolder derivedMeasureFolder, MeasureFolder allNameMeasureFolder) {
        //traverse num and deno
        HashMap<String, String> numerator = numMeasureFolder.getMeasures();
        HashMap<String, String> denomerator = denMeasureFolder.getMeasures();
        HashMap<String, String> derived = derivedMeasureFolder.getMeasures();
        HashMap<String, String> allName = allNameMeasureFolder.getMeasures();

        Set<String> numIterator = numerator.keySet();
        Set<String> denoIterator = denomerator.keySet();
        Set<String> derivedIterator = derived.keySet();
        Set<String> allNameIterator = allName.keySet();
        Set<String> directIterator = directMeasures.keySet();

        for (String curr : directIterator) {
            String expression = directMeasures.get(curr);
            HashSet<String> allRef = getAlldependencies(expression);
            HashMap<String, String> resolvedMap = resolveDirectRef(allRef, directMeasures);

            Set<String> resolvedKeys = resolvedMap.keySet();
            if (!allRef.isEmpty()) {
                for (String replace : resolvedKeys) {
                    expression = expression.replace("[" + replace + "]", resolvedMap.get(replace));
                }
                directMeasures.put(curr, expression);
            }
        }

        for (String curr : numIterator) {
            String expression = numerator.get(curr);
            HashSet<String> allRef = getAlldependencies(expression);
            HashMap<String, String> resolvedMap = resolveDirectRef(allRef, directMeasures);

            Set<String> resolvedKeys = resolvedMap.keySet();
            if (!allRef.isEmpty()) {
                for (String replace : resolvedKeys) {
                    expression = expression.replace("[" + replace + "]", resolvedMap.get(replace));
                }
                numerator.put(curr, expression);
            }
        }
        for (String curr : denoIterator) {
            String expression = denomerator.get(curr);
            HashSet<String> allRef = getAlldependencies(expression);
            HashMap<String, String> resolvedMap = resolveDirectRef(allRef, directMeasures);

            Set<String> resolvedKeys = resolvedMap.keySet();
            if (!allRef.isEmpty()) {
                for (String replace : resolvedKeys) {
                    expression = expression.replace("[" + replace + "]", resolvedMap.get(replace));
                }
                denomerator.put(curr, expression);
            }
        }
        for (String curr : derivedIterator) {
            String expression = derived.get(curr);
            HashSet<String> allRef = getAlldependencies(expression);
            if (!allRef.isEmpty()) {
                HashMap<String, String> resolvedMap = resolveNDRef(allRef, numerator, denomerator, directMeasures);

                Set<String> resolvedKeys = resolvedMap.keySet();
                for (String replace : resolvedKeys) {
                    expression = expression.replace("[" + replace + "]", resolvedMap.get(replace));
                }
                derived.put(curr, expression);
            }

        }
        for (String curr : derivedIterator) {
            String expression = derived.get(curr);
            HashSet<String> allRef = getAlldependencies(expression);
            if (!allRef.isEmpty()) {
                HashMap<String, String> resolvedMap = resolveDirectRef(allRef, derived);

                Set<String> resolvedKeys = resolvedMap.keySet();
                for (String replace : resolvedKeys) {
                    expression = expression.replace("[" + replace + "]", resolvedMap.get(replace));
                }
                derived.put(curr, expression);
            }

        }
        for (String curr : allNameIterator) {
            String expression = allName.get(curr);
            HashSet<String> allRef = getAlldependencies(expression);
            if (!allRef.isEmpty()) {
                HashMap<String, String> resolvedMap = resolveDirectRef(allRef, directMeasures);

                Set<String> resolvedKeys = resolvedMap.keySet();
                for (String replace : resolvedKeys) {

                    expression = expression.replace("[" + replace + "]", resolvedMap.get(replace));

                }
                allName.put(curr, expression);
            }

        }
        for (String curr : allNameIterator) {
            String expression = allName.get(curr);
            HashSet<String> allRef = getAlldependencies(expression);
            if (!allRef.isEmpty()) {
                HashMap<String, String> resolvedMap = resolveNameRef(allRef, numerator, denomerator, derived);

                Set<String> resolvedKeys = resolvedMap.keySet();
                for (String replace : resolvedKeys) {
                    expression = expression.replace("[" + replace + "]", resolvedMap.get(replace));
                }
                allName.put(curr, expression);
            }

        }

        // System.out.println("remove this ");

    }

    private static HashMap resolveDirectRef(HashSet<String> allRefInExp, HashMap<String, String> direct) {
        HashMap<String, String> resolvedRef = new HashMap();
        for (String curr : allRefInExp) {
            if (direct.containsKey(curr)) {
                resolvedRef.put(curr, direct.get(curr));
                //System.out.println("resolveDirectRef direct resolved "+curr+" \n* "+ direct.get(curr));

            }
        }
        return resolvedRef;
    }

    private static HashMap resolveNameRef(HashSet<String> allRefInExp, HashMap<String, String> numerator, HashMap<String, String> denomerator, HashMap<String, String> derived) {
        HashMap<String, String> resolvedRef = new HashMap();
        for (String curr : allRefInExp) {
            if (numerator.containsKey(curr)) {
                resolvedRef.put(curr, numerator.get(curr));
//                System.out.println(" resolveNameRef numerator resolved "+curr+" \n* "+ numerator.get(curr));
            } else {
                if (denomerator.containsKey(curr)) {
                    resolvedRef.put(curr, denomerator.get(curr));
//                   System.out.println(" resolveNameRef deno resolved "+curr+" \n* "+ denomerator.get(curr));

                } else {
                    if (derived.containsKey(curr)) {
                        resolvedRef.put(curr, derived.get(curr));
//                        System.out.println("resolveNameRef drived resolved "+curr+" \n* "+ derived.get(curr));

                    }
                }

            }
        }
        return resolvedRef;
    }

    private static HashMap resolveNDRef(HashSet<String> allRefInExp, HashMap<String, String> numerator, HashMap<String, String> denomerator, HashMap<String, String> direct) {
        HashMap<String, String> resolvedRef = new HashMap();
        for (String curr : allRefInExp) {
            if (numerator.containsKey(curr)) {
                resolvedRef.put(curr, numerator.get(curr));
                //System.out.println("resolveNDRef( numerator resolved "+curr+" \n* "+ numerator.get(curr));

            } else {
                if (denomerator.containsKey(curr)) {
                    resolvedRef.put(curr, denomerator.get(curr));
//                    System.out.println("resolveNDRef( deno resolved "+curr+" \n* "+ denomerator.get(curr));


                } else if (direct.containsKey(curr)) {
                    resolvedRef.put(curr, direct.get(curr));
//                    System.out.println("resolveNDRef( deno resolved "+curr+" \n* "+ denomerator.get(curr));


                }
            }
        }
        return resolvedRef;
    }

    private static String preProcessExpression(String expression) {
        //https://regex101.com/r/FKMnFv/1/
        expression = expression.replaceAll("((\\[MEASURES\\]\\.)[^\\]]*]\\.)", " ");
        //https://regex101.com/r/qysWh5/1
        expression = expression.replaceAll("\\[PHYSICAL_LAYER\\]\\.[^\\]]*]\\.\\[(.*?(?=\\]))]", " $1 ");
        //expression = expression.replaceAll("(?<=\\d)(?=[a-zA-Z])", " ").replaceAll("\\s+"," ");
        expression = expression.replaceAll("(=|!=|>|<|>=|<=)", " $0 ").replaceAll("\\s+", " ");
        return expression.trim();
    }

    private static HashSet<String> getAlldependencies(String expression) {
        HashSet<String> allRef = new HashSet<>();
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

                expression = preProcessExpression(expression).trim();
                try {
                    String aggregation = melement.getElementsByTagName("regularAggregate").item(0).getTextContent();
                    System.out.println("***" + aggregation);
                    if (!aggregation.contains("calculat")) {
                        if (expression.startsWith("(")) {
                            expression = aggregation + expression;
                        } else {
                            expression = aggregation + " ( " + expression + " ) ";
                        }
                    } else {
                        if (expression.startsWith("(")) {
                            try {
                                expression = expression.replaceAll("\\((.*)\\)", "$1");
                            } catch (Exception e) {
                            }
                        }
                    }
                    System.out.println("***" + expression);
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

    private static HashMap<String, String> getMeasuresListNoAgg(NodeList measuresNodeList) {
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
                    measureList.put(name, expression.replace("\n", "").replace("\r", ""));
                } catch (Exception e) {
                    e.getStackTrace();
                    measureList.put(name, expression);
                }

                // System.out.println("Measure : " + currMeasure.toString());

            }

        }
        return measureList;

    }

    private static void createExcel(ArrayList<Dimesion> allDimesions) {

        String excelFileName = "/home/examin/Videos/new3/ELASTICv2.xlsx";
        File file = new File(excelFileName);
        XSSFWorkbook workbook = null;
        if (file.exists()) {
            try {
                workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            workbook = new XSSFWorkbook();
        }
        for (Dimesion currDimension : allDimesions) {

            String workBookname = currDimension.getName();
            XSSFSheet sheet = workbook.createSheet(workBookname);
            assertNotNull(workbook);
            int rowNum = 0;
            Set<String> directKeys = currDimension.getMeasureFoldersInDimension().getMeasures().keySet();
            for (String curr : directKeys) {
                Row row = sheet.createRow(rowNum++);
                Cell cell1 = row.createCell(1);
                cell1.setCellValue(curr);
                String measureValue = currDimension.getMeasureFoldersInDimension().getMeasures().get(curr);
                String[] microMeasures = measureValue.split("`");
                int numOfMicro = microMeasures.length;
                for (int i = 0; i < numOfMicro; i++) {
                    Row row2 = sheet.createRow(rowNum++);
                    Cell cell3 = row2.createCell(2);

                    cell3.setCellValue(microMeasures[i]);
                }

            }


            System.out.println("Done");

        }

        try {
            FileOutputStream outputStream = new FileOutputStream(excelFileName);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
