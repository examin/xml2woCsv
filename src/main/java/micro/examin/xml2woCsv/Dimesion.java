package micro.examin.xml2woCsv;


public class Dimesion {
    static final String ELEMENT_DIMENSION = "/project/namespace[1]/namespace[2]/namespace[2]/dimension";
    private String name;
    private MeasureFolder measureFoldersInDimension;

    Dimesion(String name, MeasureFolder measureFoldersInDimension) {
        this.name = name;
        this.measureFoldersInDimension = measureFoldersInDimension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MeasureFolder getMeasureFoldersInDimension() {
        return measureFoldersInDimension;
    }

    public void setMeasureFoldersInDimension(MeasureFolder measureFoldersInDimension) {
        this.measureFoldersInDimension = measureFoldersInDimension;
    }
}
