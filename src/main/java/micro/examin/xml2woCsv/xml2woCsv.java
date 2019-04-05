package micro.examin.xml2woCsv;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.IOException;

public class xml2woCsv {

    public static void main(String args[]) throws IOException, XMLStreamException {
        String xmlFilePath = ("/home/examin/Videos/model.xml");
        XMLInputFactory factory = XMLInputFactory.newInstance();
        Dimesion[] allDimensions;
        MeasureFolder allMeasureFolders;
        Measure[] allMeasures;

        try {
            XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(xmlFilePath));
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                //try removing localpart what it do is unknown right now
                if (event.isStartElement() && event.asStartElement().getName().getLocalPart().equals("dimension")) {
                    StartElement startElement = event.asStartElement();
                    Attribute name = startElement.getAttributeByName(new QName("name"));
                    System.out.println(name);


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
