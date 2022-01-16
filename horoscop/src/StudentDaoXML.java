import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StudentDaoXML implements StudentDAO {
    List<Student> studentList;

    SAXParserFactory factory = SAXParserFactory.newInstance();


    public StudentDaoXML() {
        try(InputStream is = getXMLFileAsStream()) {
            SAXParser parser = factory.newSAXParser();

            XMLParserSAX handler = new XMLParserSAX();

            parser.parse(is, handler);

            studentList = handler.getStudentList();
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private InputStream getXMLFileAsStream() {
        InputStream stream;
        try {
            stream = getClass().getClassLoader().getResourceAsStream("students.xml");
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
        return stream;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentList;
    }

    @Override
    public Student getStudent(String numar_matricol) {
        for (Student s: studentList) {
            if(s.getNumar_matricol().equals(numar_matricol)) return s;
        }
        System.out.println("Nu exista studentul cautat!\n");
        return null;
    }

    @Override
    public List<Student> getStudentsAverageOver(float medie) {
        List<Student> list = new ArrayList<>();

        for (Student s: studentList) {
            if(s.getMedie() > medie) list.add(s);
        }

        return list;
    }

}