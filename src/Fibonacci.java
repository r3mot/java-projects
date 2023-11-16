import java.util.HashMap;
import java.util.Map;

/**
 * Fibonacci implementations
 */
public class Fibonacci {

    /* Used to store the cached fibonacci values */
    private Map<Integer, Integer> cache;

    public Fibonacci() {
        this.cache = new HashMap<>();
    }

    /**
     * Recursive Fibonacci
     * 
     * @time-complexity O(2^n)
     * @space-complexity O(n)
     */
    public int recursiveFib(int n) {
        if (n < 2) {
            return n;
        }
        return recursiveFib(n - 1) + recursiveFib(n - 2);
    }

    /**
     * Iterative Fibonacci
     * 
     * @time-complexity O(n)
     * @space-complexity O(1)
     */
    public int iterativeFib(int n) {
        int f1 = 0, f2 = 1;

        boolean swap = false;
        for (int i = 0; i < n; i++) {
            if (swap) {
                f1 += f2;
            } else {
                f2 += f1;
            }
            swap = !swap;
        }

        return swap ? f2 : f1;
    }

    /**
     * Optimized Fibonacci using dynamic programming
     * 
     * @time-complexity O(n)
     * @space-complexity O(n)
     */
    public int optimizedFib(int n) {
        int result = 0;

        if (cache.containsKey((n))) {
            return cache.get(n);
        } else if (n < 2) {
            result = 1;
        } else {
            result = optimizedFib(n - 1) + optimizedFib(n - 2);
        }

        cache.put(n, result);
        return result;
    }

    /**
     * Alternative optimized Fibonacci using dynamic programming
     * Uses Java's computeIfAbsent method.
     * It is still O(1) for lookups and O(n) for the first time.
     * 
     * @param n
     * @return
     */
    private int alternative(int n) {
        if (n < 2)
            return 1;
        return cache.computeIfAbsent(n, key -> alternative(key - 1) + alternative(key - 2));
    }
}
