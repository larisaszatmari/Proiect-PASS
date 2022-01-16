import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XMLParserSAX extends DefaultHandler {

    List<Student> studentList;

    String currVal, numar_matricol, nume;
    Float medie;

    public List<Student> getStudentList() {return studentList;}

    @Override
    public void startDocument() {
        studentList = new ArrayList<>();
    }

    @Override
    public void startElement(String uri,
                             String localName,
                             String qName,
                             Attributes attributes) {
        if(qName.equalsIgnoreCase("student")) {
            numar_matricol = attributes.getValue("numar_matricol");
        }
    }

    @Override
    public void endElement(String uri,
                           String localName,
                           String qName) {
        switch (qName) {
            case "nume":
                nume = currVal;
                break;
            case "medie":
                medie = Float.parseFloat(currVal);
                break;
            case "student":
                studentList.add(new Student(numar_matricol, nume, medie));
                break;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
        currVal = new String(ch, start, length).trim();
    }
}
