import java.util.List;

public interface StudentDAO {
    public List<Student> getAllStudents();
    public Student getStudent(String numar_matricol);
    public boolean predictMedie(String numar_matricol);
    public int countOver8();
    public boolean predictDay(String numar_matricol);

    //adaugareStudent
    //stergereStudent
    //salvareStudenti
}
