
/**
 * Static test methods for each of the Fibonacci implementations.
 * Records the time taken to run each method.
 * 
 * Optionally, you can include the output of the method to verify the result.
 */
public class Test {
    public static void rescursion(int n) {
        Fibonacci fib = new Fibonacci();

        long start = System.nanoTime();
        fib.recursiveFib(n);
        long end = System.nanoTime();
        System.out.println("Recursive: " + (end - start) + "ns");

    }

    public static void iteration(int n) {
        Fibonacci fib = new Fibonacci();

        long start = System.nanoTime();
        fib.iterativeFib(n);
        long end = System.nanoTime();
        System.out.println("Iterative: " + (end - start) + "ns");

    }

    public static void dynamic(int n) {
        Fibonacci fib = new Fibonacci();

        long start = System.nanoTime();
        fib.optimizedFib(n);
        long end = System.nanoTime();
        System.out.println("Dynamic: " + (end - start) + "ns");

    }
}
