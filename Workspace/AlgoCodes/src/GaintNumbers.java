import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class GaintNumbers {

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int arr[] = new int[n];
		int number[] = new int[100000 + 1];
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
			number[arr[i]]++;
		}
		int q = in.nextInt();
		while (q-- > 0) {
			int x = in.nextInt();
			long k = in.nextLong();
			long count = 0;
			int i = 1;
			int sqr = 1 + (int) Math.sqrt(x);
			while (i <= sqr && count < k) {
				if (x % i == 0) {
					count += number[i];
					count += number[x / i];
				}
				i++;
			}
			if (count >= k)
				System.out.println("Yes");
			else
				System.out.println("No");
		}
	}
}