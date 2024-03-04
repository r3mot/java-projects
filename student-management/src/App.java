import registrar.Registrar;
import registrar.objects.Student;
import registrar.objects.Course;

public class App {
    public static void main(String[] args) throws Exception {

        int numCourses = 10;
        Registrar registrar = new Registrar(numCourses);

        registrar.printAllStudents(); // list view
        registrar.printAllCourses(); // list view
        registrar.printCoursesAsTree(); // tree view

        String studentId = "";
        Student student = registrar.getStudentById(studentId);
        student.printCourses();

        String courseCode = "";
        Course course = registrar.getCourseByCode(courseCode);
        course.printRoster();

        // adding
        registrar.enrollStudent(studentId, courseCode);

        // dropping
        registrar.removeStudent(studentId, courseCode);
    }
}
