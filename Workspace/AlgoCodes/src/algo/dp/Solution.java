package algo.dp;
import java.util.Scanner;



class StockMarket2Directi {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		while(t-->0){
			int n=sc.nextInt();
			long k=sc.nextLong();
			long[] cost=new long[n];
			for (int i = 0; i < cost.length; i++) {
				cost[i]=sc.nextLong();
			}
			long[][] dp=new long[n+1][2];
			dp[0][0]=0;
			dp[0][1]=Long.MIN_VALUE;
			for (int i = 1; i < dp.length; i++) {
				dp[i][0]=Math.max(dp[i-1][0], dp[i-1][1] + cost[i-1]-k);
				dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]-cost[i-1]-k);
				//Utils.printArray(dp);
			}
			System.out.println(dp[n][0]);
		}
	}
}
