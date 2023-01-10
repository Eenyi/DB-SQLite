package com.example.dbsqlite;

public class Student {
    private String Name;
    private String RollNumber;
    private String Degree;
    public Student(String name, String rollNumber, String degree) {
        Name = name;
        RollNumber = rollNumber;
        Degree = degree;
    }
    public String getName() { return Name; }
    public void setName(String name) { Name = name; }
    public String getRollNumber() { return RollNumber; }
    public void setRollNumber(String rollNumber) { RollNumber = rollNumber; }
    public String getDegree() { return Degree; }
    public void setDegree(String degree) { Degree = degree; }
}
