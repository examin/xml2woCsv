package micro.examin.xml2woCsv;


import java.util.ArrayList;

public class Dimesion {
    public static final String ELEMENT_DIMENSION = "/project/namespace[1]/namespace[2]/namespace[2]/dimension";
    String name;
    MeasureFolder measureFoldersInDimension;

    Dimesion(String name, MeasureFolder measureFoldersInDimension) {
        this.name = name;
        this.measureFoldersInDimension = measureFoldersInDimension;
    }

    Dimesion(String name) {
        this.name = name;

    }
}
