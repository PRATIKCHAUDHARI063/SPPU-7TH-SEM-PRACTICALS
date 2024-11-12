import java.util.Scanner;

public class fibonacci {

    // Recursive method to calculate Fibonacci
    public static int recur(int n) {
        if (n <= 1) { // Time-O(2^n) Space-O(n)
            return n;
        } else {
            return recur(n - 1) + recur(n - 2);
        }
    }

    // Iterative method to calculate Fibonacci
    public static void iterative(int n) { // Time-O(n) Space-O(1)
        int a = 0;
        int b = 1;
        System.out.println(a);
        if (n > 1) {
            System.out.println(b);
        }
        for (int i = 2; i < n; i++) {
            int next = a + b;
            System.out.println(next);
            a = b;
            b = next;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the nth number for series: ");
        int num = scanner.nextInt();

        if (num <= 0) {
            System.out.println("Please enter a positive integer.");
        } else {
            System.out.println("Fibonacci sequence with recursion:");
            for (int i = 0; i < num; i++) {
                System.out.println(recur(i));
            }

            System.out.println("Fibonacci series with iteration:");
            iterative(num);
        }
        scanner.close();
    }
}
