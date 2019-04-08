package micro.examin.xml2woCsv;

import java.util.ArrayList;

public class MeasureFolder {
    public static String MEASURE_FOLDER = "measureFolder";

    String name;
    ArrayList<Measure> measures;


    public MeasureFolder(String name, ArrayList<Measure> measures) {
        this.name = name;
        this.measures = measures;
    }
}
