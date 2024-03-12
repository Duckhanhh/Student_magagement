package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import database.JDBCUtil;
import model.Student;

public class studentDAO implements DAOinterface<Student> {
    public static studentDAO getInstance() {
        return new studentDAO();
    }

    @Override
    public int insert(Student student) {
        Connection connection = JDBCUtil.getConnection();
        try {
            String sql = "INSERT INTO Student(Student_id, Student_name, age, grade) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, student.getId());
                statement.setString(2, student.getName());
                statement.setInt(3, student.getAge());
                statement.setString(4, student.getGrade());
                statement.executeUpdate();
            }
            JDBCUtil.closeConnection(connection);
            System.out.println("Update successfully");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Student student) {
        Connection connection = JDBCUtil.getConnection();
        try {
            String sql = "DELETE FROM Student WHERE Student_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, student.getId());
                statement.executeUpdate();
            }
            JDBCUtil.closeConnection(connection);
            System.out.println("Update successfully");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    @Override
    public Map<Student, List<String>> selectAll() {
        Connection connection = JDBCUtil.getConnection();
        Map<Student, List<String>> studentCourseMap = new LinkedHashMap<>();

        try {
            String sql = "SELECT Student.Student_id, Student_name, age, grade, courseName " +
                    "FROM Student " +
                    "LEFT JOIN Course_Student ON Student.Student_id = Course_Student.Student_id " +
                    "LEFT JOIN Course ON Course_Student.CourseId = Course.courseId";

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    String studentId = resultSet.getString("Student_id");
                    String studentName = resultSet.getString("Student_name");
                    int age = resultSet.getInt("age");
                    String grade = resultSet.getString("grade");
                    String courseName = resultSet.getString("courseName");

                    Student student = new Student(studentId, studentName, age, grade);
                    studentCourseMap.putIfAbsent(student, new ArrayList<>());

                    if (courseName != null) {
                        studentCourseMap.get(student).add(courseName);
                    }
                }
            }
            JDBCUtil.closeConnection(connection);
            System.out.println("Query executed successfully");

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        for (Map.Entry<Student, List<String>> entry : studentCourseMap.entrySet()) {
            Student student = entry.getKey();
            List<String> courses = entry.getValue();
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Grade: " + student.getGrade());

            if (courses.isEmpty()) {
                System.out.println("Courses: No courses");
            } else {
                System.out.println("Courses: " + String.join(", ", courses));
            }

            System.out.println();
        }
        return studentCourseMap;
    }

    @Override
    public Student selectByID(Student student) {
        Connection connection = JDBCUtil.getConnection();
        try {
            String sql = "Select * From Student Where Student_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, student.getId());
                statement.executeUpdate();
            }
            JDBCUtil.closeConnection(connection);
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Grade: " + student.getGrade());

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
