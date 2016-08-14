package algo.dp;

import java.util.Arrays;
import java.util.Scanner;

public class MaximumSubArrayAndSubsequence {
	static long dp[];

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();
		while (tst > 0) {
			int n = sc.nextInt();
			int A[] = new int[n];
			boolean flag=true;
			dp = new long[n];
			
			for (int i = 0; i < A.length; i++)
			{	A[i] = sc.nextInt();
			dp[i]=Integer.MIN_VALUE;
			if(A[i]>=0)
				flag=false;
			}
			dp[0]=A[0];
			long maxSubSeq = getMaxSubsequence(A, A.length - 1);
		long maxSubArray = (flag)?maxSubSeq:runKadanesAlgorithm(A);
			//System.out.println(getMaxSubsequence(A,A.length-1));
	System.out.println(maxSubArray+" "+maxSubSeq);
			tst--;
		}
	}

	private static long getMaxSubsequence(int[] A, int len) {
		
		if (dp[len] == Integer.MIN_VALUE) {
			long sum = 0;
			for (int i =0; i<len; i++) {
				if(dp[i]==Integer.MIN_VALUE)
				sum = getMaxSubsequence(A, i);
				else
				sum=dp[i];
				
				dp[len]=Math.max(Math.max(sum+A[len],A[len]),sum);
				
				/*if(dp[len-1]==Integer.MIN_VALUE)
				i=len-2;*/
				
			}
		}
		return dp[len];
	}
/*	private static long getMaxSubsequence(int[] A, int len) {
		
		if (dp[len] == Integer.MIN_VALUE) {
			long sum = 0;
			sum = getMaxSubsequence(A, len-1);
			dp[len]=Math.max(Math.max(sum+A[len],A[len]),sum);
					
		}
		return dp[len];
	}*/

	private static long runKadanesAlgorithm(int[] A) {
		long sum = A[0], max = A[0];
		for (int i = 1; i < A.length; i++) {
			sum += A[i];
			if (sum < 0)
				sum = 0;
			else if (sum > max)
				max = sum;
		}

		return max;
	}
}
