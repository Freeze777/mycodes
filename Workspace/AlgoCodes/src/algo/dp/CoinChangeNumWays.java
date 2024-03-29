package algo.dp;

import java.util.Scanner;

public class CoinChangeNumWays {
	public static void main(String[] args) {
		/*Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			int[] coins = new int[n];
			for (int i = 0; i < coins.length; i++) {
				coins[i] = sc.nextInt();
			}
			int amt = sc.nextInt();
			System.out.println(numWays(coins, amt));
		}*/
		System.out.println(numWays(new int[]{1,2},4));
	}
	// O(mn) time , O(amt) space
	public static long numWays(int[] coins, int amt) {
		long[][] dp=new long[2][amt+1];
		/*bug fix: num ways making sum=0 is 1*/
		for (int i = 0; i < dp.length; i++) 
			dp[i][0]=1;
		/*first row of table*/
		for (int j = 1; j < dp[0].length; j++) 
			dp[0][j]=((j%coins[0])==0)?1:0;
		
		for (int i = 1; i < coins.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				dp[i%2][j]=dp[(i-1)%2][j]+((j>=coins[i])?dp[i%2][j-coins[i]]:0);
			}
		}
		
		return dp[(coins.length-1)%2][amt];
	}
}
