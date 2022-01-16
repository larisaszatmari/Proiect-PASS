public class Horoscop {

    public static void main(String[] args) {
        StudentDAO studConn = new StudentDaoXML();

        for(Student s: studConn.getAllStudents()) {
            System.out.println(s);
            if(studConn.predictMedie(s.getNumar_matricol())) {
                System.out.println("Media studentului va creste.\n");
            } else System.out.printf("Media studentului va scadea.\n");

            if(studConn.predictDay(s.getNumar_matricol()))
                System.out.println("Studentul va avea o zi buna.\n");
            else System.out.println("Studentul nu va avea o zi buna\n");
        }
        System.out.println("Numar de studenti cu media peste 8 care vor avea media mai mica: " + studConn.countOver8());
    }
}
