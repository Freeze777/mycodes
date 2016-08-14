package algo.dp;

public class IntegerBreak {
    public int integerBreak(int n) {
        if(n==0||n==1||n==2)
            return 1;
        int[] dp=new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            for(int j=1;j<i;j++){
                dp[i]=Math.max(dp[i],Math.max(j,dp[j])*Math.max(i-j,dp[i-j]));
            }
        }
        return dp[n];
    }
}