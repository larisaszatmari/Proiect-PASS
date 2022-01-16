import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        Horoscop h = null;

        enum MenuOption {EXIT, INIT, PRINT, PREDICT_AVG, COUNT_AVG_8, PREDICT_DAY};
        MenuOption menuOption;

        enum DbOption {XML, MySQL};
        DbOption dbOption;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String marca;

        while(true) {
            System.out.println("1. Initializare baza de date.");
            System.out.println("2. Afisare studenti.");
            System.out.println("3. Prezicere medie student.");
            System.out.println("4. Prezicere numar studenti cu media sub 8.");
            System.out.println("5. Prezicere zi.");
            System.out.println("0. Parasire aplicatie.");
            System.out.print("Introduceti optiunea: ");

            try {
                menuOption = MenuOption.values()[Integer.parseInt(reader.readLine())];
            } catch (Exception ex) {
                System.out.println("Optiune gresita!\n");
                continue;
            }

            switch (menuOption) {
                case EXIT:
                    System.exit(0);
                break;
                case INIT:
                    System.out.println("1. XML");
                    System.out.println("2. MySQL");
                    System.out.print("Introduceti optiunea: ");
                    try {
                        dbOption = DbOption.values()[Integer.parseInt(reader.readLine())-1];
                    } catch (Exception ex) {
                        System.out.println("Optiune gresita!\n");
                        break;
                    }

                    h = new Horoscop(dbOption.toString());
                break;
                case PRINT:
                    if(h==null) {
                        System.out.println("Conexiunea la db nu a fost initializata!\n");
                        continue;
                    }
                    h.printStudents();
                break;
                case PREDICT_AVG:
                    if(h==null) {
                        System.out.println("Conexiunea la db nu a fost initializata!\n");
                        continue;
                    }
                    System.out.print("Introduceti marca student:");
                    marca = reader.readLine().toUpperCase().trim();
                    if(h.predictMedie(marca))
                        System.out.println("Media studentului va creste.\n");
                    else System.out.println("Media studentului va descreste.\n");
                break;
                case COUNT_AVG_8:
                    if(h==null) {
                        System.out.println("Conexiunea la db nu a fost initializata!\n");
                        continue;
                    }
                    int count = h.countOver8();
                    System.out.println(String.format("Exista %d studenti cu media peste 8 a caror medie va scadea.\n", count));
                break;
                case PREDICT_DAY:
                    if(h==null) {
                        System.out.println("Conexiunea la db nu a fost initializata!\n");
                        continue;
                    }
                    System.out.print("Introduceti marca student:");
                    marca = reader.readLine().toUpperCase().trim();
                    if(h.predictDay(marca))
                        System.out.println("Studentul va avea o zi buna.\n");
                    else System.out.println("Studentul nu va avea o zi buna.\n");
                break;
            }

        }
        /*for(Student s: studConn.getAllStudents()) {
            System.out.println(s);
            if(studConn.predictMedie(s.getNumar_matricol())) {
                System.out.println("Media studentului va creste.\n");
            } else System.out.printf("Media studentului va scadea.\n");

            if(studConn.predictDay(s.getNumar_matricol()))
                System.out.println("Studentul va avea o zi buna.\n");
            else System.out.println("Studentul nu va avea o zi buna\n");
        }
        System.out.println("Numar de studenti cu media peste 8 care vor avea media mai mica: " + studConn.countOver8());*/
    }
}
