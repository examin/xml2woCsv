package micro.examin.xml2woCsv;

public class Measure {
    public static final String ELEMENT_MEASURE = "/measure";
    private  String name;
    private  String expression;
    private  String aggregation;

    public Measure(String name, String expression, String aggregation) {
        this.name = name;
        this.expression = expression;
        this.aggregation = aggregation;
    }
}
