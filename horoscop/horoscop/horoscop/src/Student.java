public class Student {
    private String numar_matricol;
    private String nume;
    private Float medie;

    public Student() {
        numar_matricol = "";
        nume = "";
        medie = 0f;
    }

    public Student(String numar_matricol, String nume, Float medie) {
        this.numar_matricol = numar_matricol;
        this.nume = nume;
        this.medie = medie;
    }

    public String getNume() {return nume;}
    public String getNumar_matricol() {return numar_matricol;}
    public Float getMedie() {return medie;}

    public String toString() {
        String studentString = "";
        studentString += "Numar matricol: %s\n";
        studentString += "Nume: %s\n";
        studentString += "Medie: %.2f";

        return String.format(studentString, numar_matricol, nume, medie);
    }

}
