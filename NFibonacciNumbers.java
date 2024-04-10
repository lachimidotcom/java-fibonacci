// find n fibonacci numbers
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NFibonacciNumbers{
	// Iteration
	public static List<Long> iterationFibonacci(int n){
		List<Long> fib_series = new ArrayList<>();
		long a=0, b=1;
		fib_series.add(a);
		fib_series.add(b);
		for(int i=2; i<n; i++){
			b=a+b;
			a=b-a;
			fib_series.add(b);
		}
		return fib_series;
	}
	
	// Recursion
	private static long recursiveFibHelper(int n){
		if(n<2) return n;
		return recursiveFibHelper(n-1) + recursiveFibHelper(n-2);
	}
	
	public static List<Long> recursiveFibonacci(int n){
		List<Long> fib_series = new ArrayList<>();
		for(int i=0; i<n; i++){
			fib_series.add(recursiveFibHelper(i));
		}
		return fib_series;
	}
	
	
	// Streams
	public static List<Long> streamsFibonacci(int n){
		return Stream.iterate(new long[]{0,1}, f -> new long[]{f[1], f[0]+f[1]})
						.limit(n)
						.map(f -> f[0])
						.collect(Collectors.toList());
	}
	
	// Matrix exponentiation
	public static List<Long> matrixFibonacci(int n){
		List<Long> fib_series = new ArrayList<>();
		for(int i=0; i<n; i++){
			fib_series.add(matrixFibHelper(i));
		}
		return fib_series;
	}
	
	private static long matrixFibHelper(int n){
		if(n<=1) return n;
		long[][] result = {{1,0}, {0,1}};
		long[][] fibMatrix = {{1,1}, {1,0}};
		
		while(n>0){
			if(n%2 != 0){
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
	
	public static void main(String[] args){
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
