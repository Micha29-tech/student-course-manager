package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DatabaseConnection;
import model.Course;

public class CourseDAO {
    public void insert (Course c){
        String sql = "INSERT INTO courses (course_name, teacher ,cfu) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1,c.getCourseName());
            stmt.setString(2, c.getTeacher());
            stmt.setInt(3, c.getCfu());

            stmt.executeUpdate();
            System.out.println("Corso inserito!");

        } catch(SQLException e){
            System.out.println("Errore insert course...");
            e.printStackTrace();

        }
    }

        public List<Course> getAll(){
            String sql = "SELECT id, course_name, teacher, cfu FROM courses";
            List<Course> courses = new ArrayList<>();
              
            try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()){
             while(rs.next()){
                Course c = new Course(
                rs.getInt("id"),
                rs.getString("course_name"),
                rs.getString("teacher"),
                rs.getInt("cfu")
                );
                courses.add(c);
             }
        }catch(SQLException e){
            System.out.println("Errore SELECT courses...");
            e.printStackTrace();
        }
       return courses;
        } 
    }

