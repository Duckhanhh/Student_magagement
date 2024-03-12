package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Course {
    private String courseId;

    public Course() {

    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    private String courseName;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }
    public String getCourseName() {
        return courseName;
    }
    public Course input(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter courseId: ");
        String courseId = scanner.nextLine();
        System.out.print("Enter courseName: ");
        String courseName = scanner.nextLine();
        return new Course(courseId, courseName);
    }
}