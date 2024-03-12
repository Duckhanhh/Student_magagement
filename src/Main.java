import DAO.courseDAO;
import DAO.enrollDAO;
import DAO.studentDAO;
import model.Course;
import model.Student;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("1. Get Students");
            System.out.println("2. Get Courses");
            System.out.println("3. Add Student");
            System.out.println("4. Remove Student");
            System.out.println("5. Add Course");
            System.out.println("6. Remove Course");
            System.out.println("7. Enroll Student in Course");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    studentDAO.getInstance().selectAll();
                    break;
                case 2:
                    courseDAO.getInstance().selectAll();
                    break;
                case 3:
                    Student newStudent = new Student();
                    studentDAO.getInstance().insert(newStudent.input());
                    break;
                case 4:
                    System.out.print("Enter ID: ");
                    String ID = scanner.nextLine();
                    Student student = new Student(ID, "", 0, "2");
                    studentDAO.getInstance().delete(student);
                    break;
                case 5:
                    Course newCourse = new Course();
                    courseDAO.getInstance().insert(newCourse.input());
                    break;
                case 6:
                    System.out.print("Enter CourseId: ");
                    String courseId = scanner.nextLine();
                    Course course = new Course(courseId, "");
                    courseDAO.getInstance().delete(course);
                    break;
                case 7:
                    System.out.print("Enter studentID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter CourseId: ");
                    String course_Id = scanner.nextLine();
                    Student student1 = new Student(studentID, "a", 1, "a");
                    Course course1 = new Course(course_Id, "d");
                    enrollDAO.getInstance().enroll(student1, course1);
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}