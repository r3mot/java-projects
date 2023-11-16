
/**
 * Title: Space Time Trade-off
 * Course: CSC 401
 * Authors: Corey Mendrella, Matthew Wimer, Deepak Anantha
 * Date: 11/19/2023
 * 
 * Purpose: Compare and analyze the time and space complexity
 * of different fibonacci implementations.
 * 
 */
public class App {
    public static void main(String[] args) throws Exception {
        Fibonacci fib = new Fibonacci();
        // int recursive = fib.recursiveFib(10);
        // int iterative = fib.iterativeFib(10);
        // int dynamic = fib.optimizedFib(10);

        // Tests
        Test.rescursion(10);
    }
}
