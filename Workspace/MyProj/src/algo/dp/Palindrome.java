
package algo.dp;
import java.util.Scanner;

public class Palindrome {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);	
		int tst=sc.nextInt();
		
		while(tst>0){
			int in = sc.nextInt(),d =sc.nextInt() ,r =sc.nextInt();
		char c[] = sc.next().toCharArray();
		int n = c.length;
		int t[][]=new int[3][c.length];
		int dp[][] = new int[c.length][c.length];

		// for all window size greater than 4
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < n - i; j++) {
				// for all window size= i
				if(c[j]==c[j+i])
				{
					dp[j][j+i]=dp[j+1][j+i-1];
				//	t[(j+j+i)%3][j+i]
				}else
				{
				int temp=Math.min(dp[j+1][j+i-1]+r,Math.min(dp[j+1][j+i]+in,dp[j][j+i-1]+d));
				int temp1=Math.min(dp[j][j+i-1]+in,dp[j+1][j+i]+d);
				dp[j][j+i]=Math.min(temp,temp1);
				}

			}
		}
		System.out.println("Case:"+dp[0][n-1]);
	tst--;
		}
		
	}

}
