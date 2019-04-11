package micro.examin.xml2woCsv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Measure {
    public static final String MEASURE = "measure";
    ArrayList<String> finalExpression = new ArrayList<>();
    ArrayList<String> allRef = new ArrayList<>();
    String[] resolvedRef ;
    private String name;
    private String expression;
    private String aggregation = "";


    public Measure(String name, String expression, String aggregation, HashMap directMap) {
        this.name = name;
        this.aggregation = aggregation;
        expression = expression.trim();
        if (!aggregation.equals("calculated")) {
            //System.out.println(name);
            expression = aggregation.toUpperCase() + "( " + expression + " )";
        }
        this.expression = manupulate(expression,directMap);

        System.out.println(this.expression);
    }
    public Measure(String name, String expression, String aggregation) {
        this.name = name;
        this.aggregation = aggregation;
        expression = expression.trim();
        if (!aggregation.equals("calculated")||!aggregation.equals("none")) {
            //System.out.println(name);
            expression = aggregation.toUpperCase() + "( " + expression + " )";
        }
        this.expression = expression;
        System.out.println(this.expression);

    }


    @Override
    public String toString() {
        return " measure name : " + this.name + "\n measure exp : " + expression + "\n aggregation : " + aggregation;
    }




    public String manupulate(String expression,HashMap direct) {
        //remove all new line character and [Measure]from expression
        expression = expression.replaceAll("((\\[MEASURES\\]\\.)[^\\]]*]\\.)", " ").trim();
        expression = expression.replaceAll("(\\[PHYSICAL_LAYER\\]\\.)[^\\]]*\\]\\.\\[", " ");
        expression = expression.replaceAll("(?<=\\d)(?=[a-zA-Z])", " ");
        String[] splitedExp = expression.split("\\[.*?\\]");

        final Pattern pattern = Pattern.compile("(?<=\\[).*?(?=\\])", Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            allRef.add(matcher.group(0));
        }
        resolvedRef = new String[allRef.size()+1];
        for(int i = 0 ;i<allRef.size();i++){
            if(direct.containsKey(allRef.get(i))) {
                resolvedRef[i]=direct.get(allRef.get(i)).toString();
            }
        }
        resolvedRef[allRef.size()] = " ";
        StringBuilder sb = new StringBuilder();
        int i =0;
        for(String curr :splitedExp ){


            try {
                sb.append(curr);

                if (resolvedRef[i].isEmpty()) {
                    sb.append(resolvedRef[i]);
                } else {
                    sb.append("[" + allRef.get(i) + "]");
                }
                i++;
            }
            catch (Exception e){

            }
        }
        //System.out.println(allRef);


        return sb.toString();
    }
}
