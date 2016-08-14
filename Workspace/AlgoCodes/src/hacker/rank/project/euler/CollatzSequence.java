package hacker.rank.project.euler;

import java.io.*;
import java.util.*;

public class CollatzSequence {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = 5000001;
		int[] init = { 0, 1, 7, 2, 5, 8, 16, 3, 19, 6, 14, 9, 9, 17, 17, 4, 12,
				20, 20, 7, 7, 15, 15, 10, 23, 10, 111, 18, 18, 18, 106, 5, 26,
				13, 13, 21, 21, 21, 34, 8, 109, 8, 29, 16, 16, 16, 104, 11, 24,
				24 };

		int[] dp = new int[size];

		for (int i = 0; i < init.length; i++)
			dp[i + 1] = init[i];

		for (int i = init.length; i < dp.length; i++){
			int count=1;
			int k=i;
			while(k>1){
				if(k<i)
				{
					count=dp[k]+count-1;
					break;
				}
				else
				{	count++;
					if(k%2==0)
						k/=2;
					else
						k=3*k+1;
					
				}
				
				
			}
			dp[i]=count;
		}
	

		int max_at = 1;
		long max = dp[max_at];
		for (int i = 2; i < dp.length; i++) {
			if (dp[i] >= max) {

				max = dp[i];
				max_at = i;
			}
			dp[i] = max_at;
		}

		int tst = sc.nextInt();
		while (tst > 0) {
			int n = sc.nextInt();
			System.out.println(dp[n]);

			tst--;
		}

	}

}
