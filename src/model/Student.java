package model;

import model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student extends Person {
    private int age;
    private String grade;
    private List<Course> courses;

    public Student() {

    }

    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    public Student(String id, String name, int age, String grade) {
        super(id, name);
        this.age = age;
        this.grade = grade;
        this.courses = new ArrayList<>();
    }

    public Student(String id, String name) {
        super(id, name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public Student input(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ID: ");
        String ID = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();
        return new Student(ID, name, age, grade);
    }
}