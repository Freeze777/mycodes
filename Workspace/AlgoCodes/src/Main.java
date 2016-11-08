import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static long mod = 1000000007;
	static int max = (int) 1e6;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			long arr[] = new long[n];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = sc.nextLong();
			}

			long min = Long.MAX_VALUE;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] < min) {
					for (int j = i + 1; j < arr.length; j++) {
						if (arr[j] < min) {
							long lcm = (arr[i] * arr[j]) / gcd(arr[i], arr[j]);
							min = Math.min(min, lcm);
						}

					}
				}
			}
			System.out.println(min);
		}
	}

	public static long gcd(long x, long y) {
		if (x % y == 0)
			return y;
		else
			return gcd(y, x % y);
	}
}