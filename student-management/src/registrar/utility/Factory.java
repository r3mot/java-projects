package registrar.utility;

import registrar.objects.Course;
import registrar.objects.Student;
import registrar.tree.AVLTree;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to create objects for testing.
 */
public class Factory {
    public static AVLTree<String, Course> createCourses(int numCourse) {

        AVLTree<String, Course> courseTree = new AVLTree<>();
        int count = 0;
        for (int i = 0; i < numCourse; i++) {

            if (i == numCourse / 2) {
                count = 0;
            } else {
                count = i;
            }
            Course course = new Course(count, count + 1, "CSC-" + (i + 1));
            courseTree.insert(course.courseCode(), course);
        }

        return courseTree;
    }

    public static Map<String, Student> createStudents(int numStudents) {
        Map<String, Student> students = new HashMap<>();
        for (int i = 0; i < numStudents; i++) {
            Student student = new Student();
            students.put(student.getId().toString(), student);
        }

        return students;
    }
}
