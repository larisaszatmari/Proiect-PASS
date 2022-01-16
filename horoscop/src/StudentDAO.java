import java.util.List;

public interface StudentDAO {
    public List<Student> getAllStudents();
    public Student getStudent(String numar_matricol);
    public List<Student> getStudentsAverageOver(float medie);

    //adaugareStudent
    //stergereStudent
    //salvareStudenti
}
