package hacker.rank;


import java.util.Scanner;

public class UnexpectedProblem {
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		String s = in.next();
		long m = in.nextLong();
		long mod=1000000007;
		long cal=0;
		int pre[] = new int[s.length()];
		computePrefix(s, pre,mod);
		cal = s.length() - pre[s.length() - 1];
		m=m%mod;
		//long cal_inv=power(cal,mod-2,mod);
		//System.out.println((m * cal_inv)%mod);
	}

	public static void computePrefix(String s, int[] pre,long mod) {
		int len = 0;
		int i;
		pre[0] = 0; // lps[0] is always 0
		i = 1;
		// the loop calculates lps[i] for i = 1 to M-1
		while (i < s.length()) {
			if (s.charAt(i) == s.charAt(len)) {
				len++;
				pre[i] = len;
				i++;
			} else // (pat[i] != pat[len])
			{
				if (len != 0) {
					// This is tricky. Consider the example
					// AAACAAAA and i = 7.
					len = pre[len - 1];

					// Also, note that we do not increment i here
				} else // if (len == 0)
				{
					pre[i] = 0;
					i++;
				}
			}
		}
	}
}
