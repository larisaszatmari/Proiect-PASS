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
            if(s.getNumar_matricol()==numar_matricol) return s;
        }
        return null;
    }

    @Override
    public boolean predictMedie(String numar_matricol) {
        Student s = getStudent(numar_matricol);
        int sum = 0;
        for(char i:s.getNume().trim().toCharArray()) sum+=i;
        for(char i:s.getNumar_matricol().trim().toCharArray()) sum+=i;

        return (sum%2==1);
    }

    @Override
    public int countOver8() {
        int count = 0;
        for (Student s: getAllStudents())
            if(s.getMedie()>8f && !predictMedie(s.getNumar_matricol()))
                count++;
        return count;
    }

    @Override
    public boolean predictDay(String numar_matricol) {
        Student s = getStudent(numar_matricol);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        int sum = c.get(Calendar.DAY_OF_MONTH) + s.getNume().toCharArray()[0];

        return (sum%2==1);
    }
}
