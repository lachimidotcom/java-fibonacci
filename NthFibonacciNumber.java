// find nth fibonacci number
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.Arrays;

public class NthFibonacciNumber {

    // Iteration
    public static long iterationFibonacci(int n) {
        if (n <= 1) return n;
        long a = 0, b = 1, c;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    // Recursion
    public static long recursiveFibonacci(int n) {
        if (n <= 1) return n;
        return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
    }

    // Streams
    public static long streamsFibonacci(int n) {
        return Stream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
                     .limit(n + 1)
                     .map(f -> f[0])
                     .reduce((first, second) -> second)
                     .orElse(0L);
    }

    // Matrix exponentiation
    public static long matrixFibonacci(int n) {
        if (n <= 1) return n;
        long[][] result = {{1, 0}, {0, 1}}; // identity matrix
        long[][] fibMatrix = {{1, 1}, {1, 0}};

        while (n > 0) {
            if (n % 2 != 0) {
                multiplyMatrix(result, fibMatrix);
            }
            n /= 2;
            multiplyMatrix(fibMatrix, fibMatrix);
        }

        return result[0][1];
    }

    private static void multiplyMatrix(long[][] m1, long[][] m2) {
        long a = m1[0][0] * m2[0][0] + m1[0][1] * m2[1][0];
        long b = m1[0][0] * m2[0][1] + m1[0][1] * m2[1][1];
        long c = m1[1][0] * m2[0][0] + m1[1][1] * m2[1][0];
        long d = m1[1][0] * m2[0][1] + m1[1][1] * m2[1][1];

        m1[0][0] = a;
        m1[0][1] = b;
        m1[1][0] = c;
        m1[1][1] = d;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the index for Fibonacci Series: ");
        int index = scanner.nextInt();
		scanner.close();

        System.out.println("Iterative Fibonacci: " + iterationFibonacci(index));
        System.out.println("Recursive Fibonacci: " + recursiveFibonacci(index));
        System.out.println("Streams Fibonacci: " + streamsFibonacci(index));
        System.out.println("Matrix Fibonacci: " + matrixFibonacci(index));

        scanner.close();
    }
}
