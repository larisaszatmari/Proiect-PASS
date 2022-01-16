import java.lang.reflect.Constructor;

public class Horoscop {

    private StudentDAO studConn = null;

    public Horoscop(String dbType) {
        String StudentDAO_className = String.format("StudentDao%s", dbType);
        try {
            Class<?> c = Class.forName(StudentDAO_className);
            Constructor<?> constructor = c.getConstructor();
            studConn = (StudentDAO)constructor.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean predictMedie(String numar_matricol) {
        Student s = studConn.getStudent(numar_matricol);
        if(s==null) return false;
        return s.predictMedie();
    }

    public int countOver8() {
        return studConn.getStudentsAverageOver(8.0f).size();
    }

    public boolean predictDay(String numar_matricol) {
        Student s = studConn.getStudent(numar_matricol);
        if(s==null) return false;
        return s.predictDay();
    }

    public void printStudents() {
        for(Student s: studConn.getAllStudents())
            System.out.println(s + "\n");
    }
}
