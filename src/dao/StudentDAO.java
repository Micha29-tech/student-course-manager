package dao;

import db.DatabaseConnection;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public void insert (Student s){
        String sql = "INSERT INTO students (first_name, last_name, email, age) VALUES (?,?,?,?)";

            try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
                stmt.setString(1, s.getFirstName());
                stmt.setString(2, s.getLastName());
                stmt.setString(3, s.getEmail());
                stmt.setInt(4, s.getAge());

                stmt.executeUpdate();
                System.out.println("Studente inserito!");

            } catch (SQLException e){
                if(e.getErrorCode()== 1062){
                    System.out.println("Email già presente, inserire un'altra mail...");
                }else{
                System.out.println("Errore insert...");
                e.printStackTrace();
                }
            }
    }

    public List<Student> getAll(){
        String sql= "SELECT id,first_name,last_name,email,age FROM students";
        List<Student> students = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()){
                    
                    while (rs.next()){
                        Student s = new Student(
                           rs.getInt("id"),
                           rs.getString("first_name"),
                            rs.getString("last_name"),
                             rs.getString("email"),
                             rs.getInt("age")
                        );
                        students.add(s);
                    }
                } 
                catch (SQLException e){
                System.out.println("Errore SELECT...");
                e.printStackTrace();
    }
    return students;
    }

    public void deleteById(int id){
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
           
            stmt.setInt(1, id);
           
            int rows = stmt.executeUpdate();
            
            if (rows > 0){
                System.out.println("Studente eliminato!");
            } else {
                System.out.println("Nessuno studente trovato con quell'ID.");
            }
        }  catch (SQLException e){
            System.out.println("Errore DELETE...");
            e.printStackTrace();
        }
    }

    public void updateEmailById(int id, String newEmail) {
    String sql = "UPDATE students SET email = ? WHERE id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, newEmail);
        stmt.setInt(2, id);

        int rows = stmt.executeUpdate();
        System.out.println(rows > 0 ? "Email aggiornata!" : "ID non trovato.");

    } catch (SQLException e) {
        if(e.getErrorCode() == 1062){   //1062 codice di errore MySQL, valore duplicato
            System.out.println("Questa mail è  già stata utilizzata da un altro studente");
        }else{
        System.out.println("Errore UPDATE email...");
        e.printStackTrace();
        }
    }
}

public void updateAgeById(int id, int newAge) {
    String sql = "UPDATE students SET age = ? WHERE id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, newAge);
        stmt.setInt(2, id);

        int rows = stmt.executeUpdate();
        System.out.println(rows > 0 ? "Età aggiornata!" : "ID non trovato.");

    } catch (SQLException e) {
        System.out.println("Errore UPDATE age...");
        e.printStackTrace();
    }
}
public List<Student> findByLastName(String lastName) {
    String sql = "SELECT id, first_name, last_name, email, age FROM students WHERE last_name = ?";
    List<Student> students = new ArrayList<>();

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, lastName);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getInt("age")
                );
                students.add(s);
            }
        }

    } catch (SQLException e) {
        System.out.println("Errore SEARCH...");
        e.printStackTrace();
    }

    return students;
}

}
