import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<String> enrolledCourses = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Course[] courses = {
                new Course(7, 9, "CSC0"),
                new Course(8, 10, "CSC1"),
                new Course(11, 12, "CSC2"),
                new Course(11, 13, "CSC3"),
                new Course(14, 15, "CSC4"),
                new Course(9, 11, "CSC5"),
                new Course(17, 19, "CSC6"),
                new Course(17, 18, "CSC7"),
                new Course(13, 14, "CSC8"),
                new Course(16, 17, "CSC9")
        };
        int numCourses = 10;
        Registrar registrar = new Registrar(numCourses);
        registrar.dumpStudents();
        String choice = "0";
        System.out.println("---------------------------");
        System.out.print("Enter Student ID:");
        String id = sc.next();
        while(choice != "5") {
            System.out.println("---------------------------");
            System.out.println("1. Enroll");
            System.out.println("2. Manage Courses");
            System.out.println("3. Course Roster");
            System.out.println("4. My Schedule");
            System.out.println("5. Exit");
            System.out.print("Enter your option:");
            choice = sc.next();
            if (choice.equals("1")) {
                printAllCourses(courses);
                enrollStudent(sc, registrar, enrolledCourses, id);
            }
            if (choice.equals("2")) {

            }
            if (choice.equals("3")) {
                printEnrolledStudents(enrolledCourses, courses);
            }
            if (choice.equals("4")) {
                printEnrolledCourses(enrolledCourses, courses);
            }
        }

    }
    private static void enrollStudent(Scanner sc, Registrar registrar, ArrayList<String> enrolledCourses, String id) {

        System.out.println("Enter Course Name and Number you want to enroll in:");
        String choiceCourse = sc.next();
        Course selectedCourse = registrar.findCourse(choiceCourse);
        if (selectedCourse != null) {
            registrar.enrollStudent(id, choiceCourse);
            enrolledCourses.add(choiceCourse);
            System.out.println("Enrolled in " + choiceCourse);
        } else {
            System.out.println("Invalid course number.");
        }
    }
    private static void printAllCourses(Course[] courses) {
        System.out.println("All Courses:");
        for (Course course : courses) {
            System.out.println(course.courseCode() + " - " + course.shedule().start() + " to " + course.shedule().end());
        }
    }
    private static void printEnrolledCourses(ArrayList<String> enrolledCourses, Course[] courses) {
        System.out.println("Enrolled Courses:");
        for (String courseCode : enrolledCourses) {
            Course course = findCourseByCode(courseCode,courses);
            if (course != null) {
                System.out.println(courseCode + " - " + course.shedule().start() + " to " + course.shedule().end());
            }
        }
    }
    private static Course findCourseByCode(String courseCode, Course[] courses) {
        for (Course course : courses) {
            if (course.courseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
    private static void printEnrolledStudents(ArrayList<String> enrolledCourses, Course[] courses) {
        System.out.println("Enrolled Students:");

        for (String courseCode : enrolledCourses) {
            Course course = findCourseByCode(courseCode, courses);

            if (course != null) {
                System.out.println("Course: " + courseCode);

                Map<UUID, Student> roster = course.roster();

                if (!roster.isEmpty()) {
                    System.out.println("Enrolled Student IDs:");
                    for (UUID studentId : roster.keySet()) {
                        System.out.println("- " + studentId);
                    }
                } else {
                    System.out.println("No students enrolled.");
                }

                System.out.println(); // Add a blank line for separation
            }
        }
    }
}
