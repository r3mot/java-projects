
/**
 * Static test methods for each of the Fibonacci implementations.
 * Records the time taken to run each method.
 * 
 * Optionally, you can include the output of the method to verify the result.
 */
public class Test {
    public static void rescursion(int n, int runs) {
        Fibonacci fib = new Fibonacci();

        for (int i = 0; i < 10; i++) {
            fib.recursiveFib(i);
        }

        long total = 0;
        for (int run = 0; run < runs; run++) {
            long start = System.nanoTime();
            fib.recursiveFib(n);
            long end = System.nanoTime();
            total += (end - start);
        }

        long avg = total / runs;
        System.out.println("Recursive (n = " + n + "): " + avg + "ns");

    }

    public static void iteration(int n, int runs) {
        Fibonacci fib = new Fibonacci();

        // Warm-up
        for (int i = 0; i < 10; i++) {
            fib.iterativeFib(n);
        }

        long total = 0;

        for (int run = 0; run < runs; run++) {
            long start = System.nanoTime();
            fib.iterativeFib(n);
            long end = System.nanoTime();
            total += (end - start);
        }

        long avg = total / runs;
        System.out.println("Iterative (n = " + n + "): " + avg + "ns");
    }

    public static void dynamic(int n, int runs) {
        Fibonacci fib = new Fibonacci();

        for (int i = 0; i < 10; i++) {
            fib.optimizedFib(i);
        }

        long total = 0;
        for (int run = 0; run < runs; run++) {
            long start = System.nanoTime();
            fib.alternative(n);
            long end = System.nanoTime();
            total += (end - start);
        }

        long avg = total / runs;
        System.out.println("Optimized Recursion (n = " + n + "): " + avg + "ns");

    }
}
