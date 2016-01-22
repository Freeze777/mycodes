package hacker.rank;

import java.util.Scanner;

public class LongestCommonSubsequence {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int r = n + 1;
		int c = m + 1;
		int A[] = new int[r];
		int B[] = new int[c];
		for (int i = 1; i < A.length; i++)
			A[i] = sc.nextInt();
		for (int i = 1; i < B.length; i++)
			B[i] = sc.nextInt();

		int dp[][] = new int[r][c];// A on row and B on column
		for (int i = 1; i < r; i++) {
			for (int j = 1; j < c; j++) {
				if (A[i] == B[j])
					dp[i][j] = 1 + dp[i - 1][j - 1];
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
							}
		}
		int count=dp[r-1][c-1];
	//	int out[]=new int[count];
		StringBuilder res = new StringBuilder();
		while (true) {

			if (dp[n][m] == dp[n - 1][m])
				n--;
			else if (dp[n][m] == dp[n][m - 1])
				m--;
			else {
				res.insert(0,A[n]+" ");
				//res.append();
				n--;
				m--;
				count--;
				if(count==0)
					break;
			}

		}
		
		System.out.println(res);
	}
}
