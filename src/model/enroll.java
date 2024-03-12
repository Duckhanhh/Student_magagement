package model;

public class enroll {
    private String studentId;
    private String CourseId;

    public enroll() {
    }

    public enroll(String studentId, String courseId) {
        this.studentId = studentId;
        CourseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return CourseId;
    }

    public void setCourseId(String courseId) {
        CourseId = courseId;
    }
}
