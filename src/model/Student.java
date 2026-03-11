package model;

public class Student{
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;

    public Student (String firstName,String lastName, String email, int age){
        this.firstName = firstName;
        this.lastName= lastName;
        this.email= email;
        this.age= age;
    }
 // metodi getters
    public Student (int id, String firstName, String lastName, String email, int age){
        this.id = id;
        this.firstName = firstName;
        this.lastName= lastName;
        this.email= email;
        this.age= age;

    }

    public int getId(){
        return id;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public int getAge(){
        return age;
    }

    @Override
    public String toString(){
        return id + "|" + firstName + "|" + lastName +"|"+ email + "| Age="+ age;
    }
}