import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoMySQL implements StudentDAO{

    List<Student> studentList;

    private static final String connString = "jdbc:mysql://localhost:3306/horoscopdb";
    private static final String userId = "root";
    private static final String userPass = "admin";
    private static final String tableName = "students";

    Connection conn;

    public StudentDaoMySQL() throws SQLException {
        conn = DriverManager.getConnection(connString, userId, userPass);
    }

    @Override
    public List<Student> getAllStudents() {
        studentList = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(String.format("SELECT * from %s", tableName));
            while(rs.next()) {
                studentList.add(new Student(rs.getString("numar_matricol"), rs.getString("nume"), rs.getFloat("medie")));
            }

            return studentList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Student getStudent(String numar_matricol) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(String.format("SELECT * from %s WHERE `numar_matricol`=\"%s\"", tableName, numar_matricol));
            while(rs.next()) {
                return new Student(rs.getString("numar_matricol"), rs.getString("nume"), rs.getFloat("medie"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Nu exista studentul cautat!\n");
        return null;
    }

    @Override
    public List<Student> getStudentsAverageOver(float medie) {
        studentList = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(String.format("SELECT * from %s WHERE `medie`>%s", tableName,
                    String.format("%.2f", medie).replace(',', '.')));
            while(rs.next()) {
                studentList.add(new Student(rs.getString("numar_matricol"), rs.getString("nume"), rs.getFloat("medie")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }
}
