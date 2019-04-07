package micro.examin.xml2woCsv;

public class MeasureFolder {
    public static String ELEMENT_MEASURE_FOLDER = "/measureFolder";

    String name;
    Measure[] Measures;

    public MeasureFolder(String name) {
        this.name = name;
    }
}
