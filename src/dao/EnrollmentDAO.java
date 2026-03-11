package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DatabaseConnection;

public class EnrollmentDAO {
    public void enrollStudent (int studentId, int courseId){
        String sql = "INSERT INTO enrollments (student_id,course_id,enrollment_date) VALUES (?,?, CURDATE())";

            try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setInt(1, studentId);
                stmt.setInt(2, courseId);

                stmt.executeUpdate();
                System.out.println("Studente iscritto al corso!");
            }catch (SQLException e){
                System.out.println("Errore iscrizione...");
                e.printStackTrace();
            }
                
        
    }
}
