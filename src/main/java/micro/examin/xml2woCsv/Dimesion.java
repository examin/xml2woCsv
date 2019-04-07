package micro.examin.xml2woCsv;


import java.util.ArrayList;

public class Dimesion {
    public static final String ELEMENT_DIMENSION = "/project/namespace[1]/namespace[2]/namespace[2]/dimension";
    String name;
    ArrayList<MeasureFolder> measureFoldersInDimension;

    Dimesion(String name, ArrayList<MeasureFolder> measureFoldersInDimension) {
        this.name = name;
        this.measureFoldersInDimension = measureFoldersInDimension;
    }

    Dimesion(String name) {
        this.name = name;
        this.measureFoldersInDimension = measureFoldersInDimension;
    }
}
