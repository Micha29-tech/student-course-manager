package model;

public class Course {
    private int id;
    private String courseName;
    private String teacher;
    private int cfu;

    public Course (String courseName,String teacher, int cfu){
        this.courseName=courseName;
        this.teacher=teacher;
        this.cfu=cfu;
    }
     public Course (int id ,String courseName,String teacher, int cfu){
        this.id= id;
        this.courseName=courseName;
        this.teacher=teacher;
        this.cfu=cfu;
    }

    public int getId(){
        return id;
    }
    public String getCourseName(){
        return courseName;
    }
    public String getTeacher(){
        return teacher;
    }
    public int getCfu(){
        return cfu;
    }

    @Override
    public String toString(){
        return "Course{" +
        "id=" + id + ", courseName= '" + courseName +'\''+
        ", teacher= '" + teacher + '\'' + ", cfu= " + cfu + '}';
        
    }
    
}
