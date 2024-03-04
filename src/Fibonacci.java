import java.util.HashMap;
import java.util.Map;

/**
 * Fibonacci implementations
 */
public class Fibonacci {

    /* Used to store the cached fibonacci values */
    private Map<Integer, Integer> cache;
    private Map<Integer, Integer> memo;

    public Fibonacci() {
        this.cache = new HashMap<>();
        this.memo = new HashMap<>();
        this.memo.put(0, 0);
        this.memo.put(1, 1);
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
        if (n < 2) {
            return n;
        }

        for (int i = 2; i <= n; i++) {
            int result = memo.get(i - 1) + memo.get(i - 2);
            memo.put(i, result);
        }

        return memo.get(n);
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
     * Optimized Fibonacci using dynamic programming
     * 
     * @time-complexity O(n)
     * @space-complexity O(n)
     */
    public int alternative(int n) {
        if (n < 2) {
            return n;
        }

        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        int result = alternative(n - 1) + alternative(n - 2);
        cache.put(n, result);

        return result;
    }

}
