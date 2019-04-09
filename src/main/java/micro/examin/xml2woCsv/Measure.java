package micro.examin.xml2woCsv;

public class Measure {
    public static final String MEASURE = "measure";
    private  String name;
    private  String expression;
    private String aggregation = "none";

    public Measure(String name, String expression, String aggregation) {
        this.name = name;
        this.expression = replace(expression);
        this.aggregation = aggregation;
    }

    public Measure(String name, String expression) {
        this.name = name;
        this.expression = replace(expression);
    }

    @Override
    public String toString() {
        return " measure name : " + this.name + "\n measure exp : " + expression + "\n aggregation : " + aggregation;
    }

    public String replace(String expression) {
        //remove all new line character and [Measure]from expression
        expression = expression.replaceAll("((\\[MEASURES\\]\\.)[^\\]]*]\\.)", "");
        expression = expression.replaceAll("(\\[PHYSICAL_LAYER\\]\\.)[^\\]]*\\]\\.\\[", "");
        expression = expression.replaceAll("(?<=\\d)(?=[a-zA-Z])", " ");
        System.out.println(name + " " + " * " + expression);
        return expression;
    }
}
