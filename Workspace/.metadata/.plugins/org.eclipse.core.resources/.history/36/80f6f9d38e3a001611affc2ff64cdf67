package algo.dp;

public class MaxNumberOfAUsingNKeyStrokes {
public static void main(String[] args) {
	int n=11;
	int  dp[]=new int[n+1];
	for(int i=0;i<=6;i++)
		dp[i]=i;
	
	for(int i=7;i<=n;i++)
	{
		
		for(int brk=i-2;brk>=1;brk--)
		{
			int temp=dp[brk]*(i-brk-1);
			
			if(temp>dp[i])
				dp[i]=temp;
		}
		
	}
	System.out.println(dp[n]);
}
}
