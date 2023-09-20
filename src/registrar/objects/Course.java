package registrar.objects;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Course {
    private String mCourseCode;
    private Interval mSchedule;
    private Map<UUID, Student> mRoster;
    private int capacity = 5;

    public Course(int start, int end, String courseCode) {
        this.mCourseCode = courseCode;
        this.mSchedule = new Interval(start, end);
        this.mRoster = new HashMap<>();
    }

    /**
     * Get the roster of students in the course.
     * 
     * @return
     */
    public Map<UUID, Student> roster() {
        return mRoster;
    }

    /**
     * Add a student to the course.
     * 
     * @param student
     * @return
     * @complexity
     */
    public boolean add(Student student) {
        if (isOpen()) {
            mRoster.put(student.getId(), student);
            return true;
        }
        return false;
    }

    /**
     * Check if the course is open.
     * 
     * @return
     */
    public boolean isOpen() {
        return mRoster.size() < capacity;
    }

    /**
     * Drop a student from the course.
     * 
     * @param student
     * @complexity
     */
    public void drop(Student student) {
        mRoster.remove(student.getId());
    }

    /**
     * Get the course code.
     * 
     * @return
     */
    public String courseCode() {
        return mCourseCode;
    }

    /**
     * Get the schedule of the course. (Interval)
     * 
     * @return
     */
    public Interval shedule() {
        return mSchedule;
    }
}
