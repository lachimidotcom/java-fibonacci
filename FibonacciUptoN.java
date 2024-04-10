import java.util.Scanner;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FibonacciUptoN {
    // Iteration
    public static List<Long> iterationFibonacci(int n) {
        List<Long> fib_series = new ArrayList<>();
        long a = 0, b = 1;
        while (a <= n) {
            fib_series.add(a);
            long next = a + b;
            a = b;
            b = next;
        }
        return fib_series;
    }

    // Recursion
    public static List<Long> recursiveFibonacci(int n) {
        List<Long> fib_series = new ArrayList<>();
        recursiveFibonacciHelper(fib_series, 0, 1, n);
        return fib_series;
    }

    private static void recursiveFibonacciHelper(List<Long> fib_series, long a, long b, int n) {
        if (a <= n) {
            fib_series.add(a);
            recursiveFibonacciHelper(fib_series, b, a + b, n);
        }
    }

    // Streams
    public static List<Long> streamsFibonacci(int n) {
        return Stream.iterate(new long[]{0, 1}, t -> new long[]{t[1], t[0] + t[1]})
                .limit(n+1) // Adjusted limit to include the nth number
                .map(t -> t[0])
                .filter(t -> t <= n) // Filter to include only numbers less than or equal to n
                .collect(Collectors.toList());
    }

    // Matrix Exponentiation
    public static List<Long> matrixFibonacci(int n) {
        List<Long> fib_series = new ArrayList<>();
        long[][] matrix = {{1, 1}, {1, 0}};
        long a = 0;
        for (int i = 0; a <= n; i++) {
            a = matrixPower(matrix, i)[0][1];
            if (a <= n) {
                fib_series.add(a);
            }
        }
        return fib_series;
    }

    private static long[][] matrixPower(long[][] matrix, int n) {
        long[][] result = {{1, 0}, {0, 1}};
        while (n > 0) {
            if (n % 2 != 0) {
                result = matrixMultiply(result, matrix);
            }
            n /= 2;
            matrix = matrixMultiply(matrix, matrix);
        }
        return result;
    }

    private static long[][] matrixMultiply(long[][] a, long[][] b) {
        long[][] result = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                result[i][j] = 0;
                for (int k = 0; k < 2; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a Range: ");
        int range = scanner.nextInt();
        scanner.close();

        System.out.println("Iterative Fibonacci: " + iterationFibonacci(range));
        System.out.println("Recursive Fibonacci: " + recursiveFibonacci(range));
        System.out.println("Streams Fibonacci: " + streamsFibonacci(range));
        System.out.println("Matrix Exponentiation Fibonacci: " + matrixFibonacci(range));
    }
}
