package micro.examin.xml2woCsv;

import java.util.ArrayList;
import java.util.HashMap;

public class MeasureFolder {
    public static String MEASURE_FOLDER = "measureFolder";

    String name;
    HashMap <String,Measure> measures;


    public MeasureFolder(String name, HashMap<String,Measure> measures) {
        this.name = name;
        this.measures = measures;
    }
    public MeasureFolder(String name) {
        this.name = name;
    }
}
