
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();

		while (tst > 0) {
			int in = sc.nextInt(), d = sc.nextInt(), r = sc.nextInt();
			char c[] = sc.next().toCharArray();
			int n = c.length;
			int t[][] = new int[3][c.length];
			// int dp[][] = new int[c.length][c.length];

			// for all window size greater than 4
			for (int i = 1; i < n; i++) {
				for (int j = 0; j < n - i; j++) {
					// for all window size= i
					if (c[j] == c[j + i]) {
						// dp[j][j+i]=dp[j+1][j+i-1];
						t[j % 3][j + i] = t[(j + 1) % 3][j + i - 1];
					} else {
						int temp = Math.min(
								t[(j + 1) % 3][j + i - 1] + r,
								Math.min(t[(j + 1) % 3][j + i] + in, t[j % 3][j
										+ i - 1]
										+ d));
						int temp1 = Math.min(t[j % 3][j + i - 1] + in,
								t[(j + 1) % 3][j + i] + d);
						t[j % 3][j + i] = Math.min(temp, temp1);
					}

				}
			}
			System.out.println("Case:" + t[0][n - 1]);
			tst--;
		}

	}

}
