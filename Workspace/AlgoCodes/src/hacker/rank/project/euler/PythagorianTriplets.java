package hacker.rank.project.euler;
import java.util.Scanner;

public class PythagorianTriplets {

	static int max = -1;

	public static void main(String[] args) {
		int a = 3, b = 4, c = 5;
		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();
		while (tst > 0) {
			int n = sc.nextInt();
			if (n > 12) {
				if (n % 12 == 0) {
					max = (n / 12) * (n / 12) * (n / 12) * a * b * c;
				}
				generatePythagorianTriplets(a, b, c, n);
				System.out.println(max);
			} else if (n == 12)
				System.out.println(a * b * c);
			else
				System.out.println(-1);

			tst--;
			max=-1;
		}

	}

	/*
	 * 1 -2 2 2 -1 2 2 -2 3
	 * 
	 * -1 2 2 -2 1 2 -2 2 3
	 * 
	 * 1 2 2 2 1 2 2 2 3
	 */
	private static void generatePythagorianTriplets(int a, int b, int c, int n) {

		int a1 = a + (-2 * b) + (2 * c);
		int b1 = (2 * a) + (-1 * b) + (2 * c);
		int c1 = (2 * a) + (-2 * b) + (3 * c);
		int sum = (a1 + b1 + c1);
		if (sum < n) {
			if (n % sum == 0) {
				int prod  = (n / sum) * (n / sum) * (n / sum) * a1 * b1 * c1;
				max = (max < prod) ? prod : max;
			}
			generatePythagorianTriplets(a1, b1, c1, n);

		} else if (sum == n) {
			int prod = a1 * b1 * c1;
			max = (max < prod) ? prod : max;
		}
		a1 = (-1 * a) + (2 * b) + (2 * c);
		b1 = (-2 * a) + (1 * b) + (2 * c);
		c1 = (-2 * a) + (2 * b) + (3 * c);

		sum = (a1 + b1 + c1);

		if (sum < n) {
			if (n % sum == 0) {
				int prod  = (n / sum) * (n / sum) * (n / sum) * a1 * b1 * c1;
				max = (max < prod) ? prod : max;
			}
			generatePythagorianTriplets(a1, b1, c1, n);
		} else if (sum == n) {
			int prod = a1 * b1 * c1;
			max = (max < prod) ? prod : max;
		}

		a1 = a + (2 * b) + (2 * c);
		b1 = (2 * a) + (1 * b) + (2 * c);
		c1 = (2 * a) + (2 * b) + (3 * c);

		sum = (a1 + b1 + c1);
		if (sum < n) {
			if (n % sum == 0) {
				int prod  = (n / sum) * (n / sum) * (n / sum) * a1 * b1 * c1;
				max = (max < prod) ? prod : max;
			}
			generatePythagorianTriplets(a1, b1, c1, n);
		} else if (sum == n) {
			int prod = a1 * b1 * c1;
			max = (max < prod) ? prod : max;
		}

	}

}
