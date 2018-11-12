/**
 * The fuction calculates the factorial of requested n.
 * @author Omer zucker
 * @since 25/03/2017.
 * @version 1.0
 */
public class Factorial {
    /**
     * main function.
     * @param args input from user
     */
    public static void main(String[] args) {
        long n = Long.parseLong(args[0]);
        long result = factorialRecursive(n);
        System.out.println("recursive: " + result);
        result = factorialIter(n);
        System.out.println("iterative: " + result);
    }

    /**
     * The function calculates the factorial of n by recursion.
     * @param n the input
     * @return factorial of n
     */
    public static long factorialIter(long n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * The function calculates the factorial of n by iteration.
     * @param n input
     * @return factorial of n
     */
    public static long factorialRecursive(long n) {
        if (n >= 1) {
            return n * factorialRecursive(n - 1);
        } else {
            return 1;
        }
    }
}
