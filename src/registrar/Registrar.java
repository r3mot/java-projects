package registrar;

import registrar.objects.Course;
import registrar.objects.Student;
import registrar.tree.AVLTree;
import registrar.utility.Factory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Main Controller for the application.
 * 
 * This class is responsible for managing the data structures and handling
 * requests from the user.
 */
public class Registrar {
    private AVLTree<String, Course> mCourses;
    private Map<String, Student> mStudents;

    public Registrar(int numCourses) {
        this.mCourses = Factory.createCourses(numCourses);
        this.mStudents = Factory.createStudents(5);
    }

    /**
     * Enroll a student in a course.
     * 
     * @param studentId  of the student to enroll
     * @param courseCode of the course to enroll in
     * @complexity
     */
    public void enrollStudent(String studentId, String courseCode) {
        Course course = findCourse(courseCode);
        Student student = findStudent(studentId);

        if (course == null || student == null) {
            System.err.println("Course or student not found");
            return;
        }

        if (student.canAdd(course) && course.isOpen()) {
            student.add(course);
            course.add(student);

            System.out.println("Enrolled " + studentId + " in " + courseCode);
        } else {
            System.err.println("Could not enroll " + studentId + " in " + courseCode);
        }
    }

    /**
     * Remove a student from a course.
     * 
     * @param studentId  of the student to remove
     * @param courseCode of the course to remove from
     * @complexity
     */
    public void removeStudent(String studentId, String courseCode) {
        Course course = findCourse(courseCode);
        Student student = findStudent(studentId);

        if (course == null || student == null) {
            System.err.println("Course or student not found");
            return;
        }

        course.drop(student);
        student.drop(courseCode);

        System.out.println("Removed " + studentId + " from " + courseCode);
    }

    /**
     * Find a student by their student id.
     * 
     * @param studentId of the student to find
     * @return
     */
    public Student getStudentById(String studentId) {
        return findStudent(studentId);
    }

    /**
     * Find a course by its course code.
     * 
     * @param courseCode of the course to find
     * @return
     */
    public Course getCourseByCode(String courseCode) {
        return findCourse(courseCode);
    }

    /**
     * Print all courses
     */
    public void printAllCourses() {
        List<Course> courses = new LinkedList<>();
        mCourses.dump(courses);

        for (Course course : courses) {
            System.out.println(course.courseCode());
        }
    }

    /**
     * Prints tree view of courses
     */
    public void printCoursesAsTree() {
        mCourses.printTree();
    }

    /**
     * Print all the students in the system to stdout.
     * 
     * @complexity
     */
    public void printAllStudents() {
        for (Student student : mStudents.values()) {
            System.out.println(student.getId());
        }
    }

    /**
     * Find a course by its course code.
     * 
     * @complexity
     * 
     * @param courseCode
     * @return
     */
    private Course findCourse(String courseCode) {
        return mCourses.find(courseCode);
    }

    /**
     * Find a student by their student id.
     * 
     * @complexity
     * 
     * @param studentId
     * @return
     */
    private Student findStudent(String studentId) {
        return mStudents.get(studentId);
    }
}
