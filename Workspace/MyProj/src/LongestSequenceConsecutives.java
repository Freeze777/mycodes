/**
 *
 * NOTE : Class Name should be Main 
 *
 **/
import java.util.Arrays;
import java.util.Scanner;

public class LongestSequenceConsecutives {
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int A[] = new int[n];
		int i = 0;

		for (i = 0; i < n; i++)
			A[i] = sc.nextInt();

		Arrays.sort(A);

		int count = 0, max = Integer.MIN_VALUE;
		i = 0;
		while (i < n) {
			// System.out.println(i);
			while ((i < n - 1) && (A[i] + 1 == A[i + 1])) {
				count++;
				i++;

			}

			if (max < count)
				max = count;

			

				i++;

			count = 0;

		}

		System.out.println(max+1);
	}
}