package micro.examin.xml2woCsv;

import java.util.HashMap;

public class MeasureFolder {

    private String name;
    private HashMap<String, String> measures;

    public MeasureFolder(String name, HashMap measures) {
        this.name = name;
        this.measures = measures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, String> getMeasures() {
        return measures;
    }

    public void setMeasures(HashMap<String, String> measures) {
        this.measures = measures;
    }
}
