package DAO;

import database.JDBCUtil;
import model.Course;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class courseDAO implements DAOinterface<Course> {
    public static courseDAO getInstance() {
        return new courseDAO();
    }

    @Override
    public int insert(Course course) {
        Connection connection = JDBCUtil.getConnection();
        try {
            String sql = "INSERT INTO Course(CourseId, CourseName) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, course.getCourseId());
                statement.setString(2, course.getCourseName());
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
    public int delete(Course course) {
        Connection connection = JDBCUtil.getConnection();
        try {
            String sql = "DELETE FROM Course WHERE Student_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, course.getCourseId());
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
    public Map<Course, List<String>> selectAll() {
        Connection connection = JDBCUtil.getConnection();
        Map<Course, List<String>> courseStudentMap = new HashMap<>();

        try {
            String sql = "select Course.courseId,courseName, Student_name " +
                    "from Student join Course_Student on Student.Student_id = Course_Student.Student_id " +
                    "join Course on Course_Student.courseId = Course.courseId";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String courseId = resultSet.getString("courseId");
                    String courseName = resultSet.getString("courseName");
                    Course newCourse = new Course(courseId, courseName);
                    String studentName = resultSet.getString("Student_name");
                    courseStudentMap.putIfAbsent(newCourse, new ArrayList<>());
                    if (studentName != null) {
                        courseStudentMap.get(newCourse).add(studentName);
                    }
                }
            }
            JDBCUtil.closeConnection(connection);
            }catch (Exception exception) {
                exception.printStackTrace();
        }
        for(Map.Entry<Course, List<String>> entry : courseStudentMap.entrySet()){
            Course course = entry.getKey();
            List<String> students = entry.getValue();
            System.out.println("Course's ID: " + course.getCourseId());
            System.out.println("Course's name: " + course.getCourseName());

            if(students.isEmpty()){
                System.out.println("Students: No student");
            }else {
                System.out.println("Students: " + String.join(", ", students));
            }
            System.out.println();
        }
        return courseStudentMap;
    }

    @Override
    public Course selectByID(Course course) {
        Connection connection = JDBCUtil.getConnection();
        try {
            String sql = "Select * From Student Where courseId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, course.getCourseId());
                statement.executeUpdate();
            }
            JDBCUtil.closeConnection(connection);
            System.out.println("ID: " + course.getCourseId());
            System.out.println("Name: " + course.getCourseName());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
