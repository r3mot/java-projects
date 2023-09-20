package registrar.objects;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Student {
    private UUID mId;
    private Map<String, Course> mCourses;

    public Student() {
        this.mId = UUID.randomUUID();
        this.mCourses = new HashMap<>();
    }

    /**
     * Add a course to the students schedule.
     * 
     * @param course
     * @complexity
     */
    public void add(Course course) {
        mCourses.put(course.courseCode(), course);
    }

    /**
     * Drop a course from the students schedule.
     * 
     * @param courseCode
     * @complexity
     */
    public void drop(String courseCode) {
        mCourses.remove(courseCode);
    }

    public UUID getId() {
        return mId;
    }

    /**
     * Check if the student can add a course.
     * 
     * @param course
     * @return
     * @complexity
     */
    public boolean canAdd(Course course) {
        return !hasConflict(course) && !isEnrolled(course);
    }

    /**
     * Check if the course has a conflict with any of the students current courses.
     * 
     * @param other
     * @return
     * @complexity
     */
    private boolean hasConflict(Course other) {
        for (Course course : mCourses.values()) {
            if (course.shedule().conflicts(other.shedule())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check if the student is enrolled in a course.
     * 
     * @param course
     * @return
     * @complexity
     */
    private boolean isEnrolled(Course course) {
        return mCourses.containsKey(course.courseCode());
    }

}
