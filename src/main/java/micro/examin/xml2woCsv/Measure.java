package micro.examin.xml2woCsv;

public class Measure {
    public static final String MEASURE = "measure";
    private  String name;
    private  String expression;
    private String aggregation = "none";

    public Measure(String name, String expression, String aggregation) {
        this.name = name;
        this.expression = expression;
        this.aggregation = aggregation;
    }

    public Measure(String name, String expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return " measure name : " + this.name + "\n measure exp : " + expression + "\n aggregation : " + aggregation;
    }
}
