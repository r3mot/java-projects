package registrar.objects;

/**
 * Defines an interval of integers.
 * 
 * Used to represent a courses time slot.
 */
public class Interval {
    private int mStart;
    private int mEnd;

    public Interval(int start, int end) {
        this.mStart = start;
        this.mEnd = end;
    }

    /**
     * Checks if this interval conflicts with another interval.
     * 
     * @param other The other interval to check against.
     */
    public boolean conflicts(Interval other) {
        return mStart <= other.mEnd && other.mStart <= mEnd;
    }

    public int start() {
        return mStart;
    }

    public int end() {
        return mEnd;
    }
}
