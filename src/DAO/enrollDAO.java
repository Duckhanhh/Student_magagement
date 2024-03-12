package DAO;

import database.JDBCUtil;
import model.Course;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class enrollDAO {
    public static enrollDAO getInstance() {
        return new enrollDAO();
    }

    public void enroll(Student student, Course course) {
        Connection connection = JDBCUtil.getConnection();
        try {
            String sql = "INSERT INTO Course_Student(Student_id, CourseId) VALUES (?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, student.getId());
                statement.setString(2, course.getCourseId());
                statement.executeUpdate();
            }
            System.out.println("Enrolled!");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
