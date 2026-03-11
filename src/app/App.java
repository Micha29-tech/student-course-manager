package app;

import dao.StudentDAO;
import model.Student;
import java.util.List;
import java.util.Scanner;
import dao.CourseDAO;
import model.Course;
import dao.EnrollmentDAO;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        EnrollmentDAO enrollmentDAO =new EnrollmentDAO();

    
       
        while(true){
            System.out.println("\n ====STUDENT MANAGEMENT====");
            System.out.println("1) Inserisci studente");
            System.out.println("2) Visualizza studenti");
            System.out.println("3) Elimina studente in base a ID");
            System.out.println("4) Aggiorna email per ID");
            System.out.println("5) Aggiorna età per ID");
            System.out.println("6) Cerca per cognome");
            System.out.println("7) Inserisci corso");
            System.out.println("8) Visualizza corso");
            System.out.println("9) Iscrivi studente a corso");
            System.out.println("0) Esci");
            System.out.println("SCELTA: ");

            int choice = Integer.parseInt(sc.nextLine());

            if ( choice == 0){
                System.out.println("Bye!...");
                break;
            }

            switch (choice) {
                case 1-> {
                    System.out.print("Nome: ");
                    String firstName = sc.nextLine();

                    System.out.print("Cognome: ");
                    String lastName = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Età: ");
                    int age = Integer.parseInt(sc.nextLine());

                    dao.insert(new Student(firstName, lastName, email, age));
                }
                case 2 -> {
                    List <Student> all = dao.getAll();
                    System.out.println("\n --- LISTA STUDENTI---");
                    for (Student s : all ){
                        System.out.println(s);

                    }
                }
                case 3 -> {
                    System.out.println("Inserisci ID da eliminare: ");
                    int id = Integer.parseInt(sc.nextLine());
                    dao.deleteById(id);
                }
                case 4 -> {
                     System.out.print("ID: ");
                      int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Nuova email: ");
                        String email = sc.nextLine();
                        dao.updateEmailById(id, email);
}
                 case 5 -> {
                        System.out.print("ID: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Nuova età: ");
                        int age = Integer.parseInt(sc.nextLine());
                        dao.updateAgeById(id, age);
                    }
                    case 6 -> {
                        System.out.print("Cognome da cercare: ");
                        String ln = sc.nextLine();

                        var list = dao.findByLastName(ln);
                        if (list.isEmpty()) {
                            System.out.println("Nessun risultato.");
                        } else {
                            for (var s : list) System.out.println(s);
    }
}
                    case 7 ->{
                        System.out.println("Nome corso: ");
                        String courseName = sc.nextLine();

                        System.out.println("Docente: ");
                        String teacher = sc.nextLine();

                        System.out.println("CFU: ");
                        int cfu = Integer.parseInt(sc.nextLine());
                        sc.nextLine();

                        courseDAO.insert(new Course(courseName,teacher,cfu));
                    }
                    
                    case 8->{
                        List <Course> courses = courseDAO.getAll();
                        System.out.println("\n ----CORSI----");
                        for(Course c : courses){
                            System.out.println(c);
                        }
                    }
                    case 9->{
                        System.out.println("ID studente: ");
                        int studentId= Integer.parseInt(sc.nextLine());
                        System.out.println("ID corso: ");
                        int courseId= Integer.parseInt(sc.nextLine());

                        enrollmentDAO.enrollStudent(studentId, courseId);
                    }
            
            default -> System.out.println("Scelta non valida!...");
        }  

       
    }
    sc.close();
}

   
}
