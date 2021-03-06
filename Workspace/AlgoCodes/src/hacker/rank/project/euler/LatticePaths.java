package hacker.rank.project.euler;
import java.util.Scanner;

public class LatticePaths {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int row=501,col=501;
		long mod=1000000007;
		long dp[][]=new long[row][col];
		for (int i =1; i < row; i++) {
			dp[0][i]=1;
			dp[i][0]=1;
		}
		
		for (int i = 1; i < row; i++) {
			for (int j =1; j < col; j++) {
				dp[i][j]=(dp[i-1][j]+dp[i][j-1])%mod;
			}
		}
		
		int tst=sc.nextInt();
		
		while(tst>0){
			
			int n=sc.nextInt();
			int m=sc.nextInt();
			System.out.println(dp[n][m]);
			tst--;
		}
		
	}
}
