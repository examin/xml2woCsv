package micro.examin.xml2woCsv;


public class Dimesion {
    public static final String ELEMENT_DIMENSION = "/project/namespace[1]/namespace[2]/namespace[2]/dimension";
    String name;
    MeasureFolder[] MeasureFolders;
    Dimesion(String name){
        this.name = name;
    }
}
