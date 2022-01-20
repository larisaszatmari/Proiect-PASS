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

    public int predictMedie(String numar_matricol) {
        Student s = studConn.getStudent(numar_matricol);
        if(s==null) return -1;
        if(s.predictMedie()) return 1;
        return 0;
    }

    public int countOver8() {
        return studConn.getStudentsAverageOver(8.0f).size();
    }

    public int predictDay(String numar_matricol) {
        Student s = studConn.getStudent(numar_matricol);
        if(s==null) return -1;
        if(s.predictDay()) return 1;
        return 0;
    }

    public void printStudents() {
        for(Student s: studConn.getAllStudents())
            System.out.println(s + "\n");
    }
}
