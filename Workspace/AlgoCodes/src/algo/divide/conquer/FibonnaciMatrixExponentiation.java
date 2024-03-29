package algo.divide.conquer;

import java.util.*;


public class FibonnaciMatrixExponentiation {

	private static long a, b;
	private static long[] ans=new long[2];
	private static long mod = 1000000007;
	private static long[][] fibMatrix = { { 1, 1 }, { 1, 0 } };
	private static long getNthFibNum(long n, long m) {
		long[][] result = { { 1, 0 }, { 0, 1 } };
		
		while (n != 0) {

			if (n%2==1)
				moduloMatrixMul2x2(result, fibMatrix, m);

			long[][] temp = { { fibMatrix[0][0], fibMatrix[0][1] },
					{ fibMatrix[1][0], fibMatrix[1][1] } };
			moduloMatrixMul2x2(fibMatrix, temp, m);
			
			n/=2;

		}
		return ((b * result[1][0]) + (a * result[1][1])) % m;
	}

	private static int getFibPeriod(long m) {
		//System.out.println("here");
		int k = 1;
		long[][] temp = { { 1, 0 }, { 0, 1 } };
		while (true) {
			//System.out.println("here");
			moduloMatrixMul2x2(temp, fibMatrix, m);
			if (temp[0][0] == 1 && temp[0][1] == 0 && temp[1][0] == 0
					&& temp[1][1] == 1)
				break;
			k++;
		}
		return k;
	}

	private static void moduloMatrixMul2x2(long[][] A, long[][] B,long m) {
		long a00 = A[0][0], a01 = A[0][1], a10 = A[1][0], a11 = A[1][1];
		A[0][0] = ((a00 * B[0][0])%m + (a01 * B[1][0])%m) % m;
		A[0][1] = ((a00 * B[0][1])%m + (a01 * B[1][1])%m) % m;
		A[1][0] = ((a10 * B[0][0])%m + (a11 * B[1][0])%m) % m;
		A[1][1] = ((a10 * B[0][1])%m + (a11 * B[1][1])%m) % m;
	}
	private static void fast_fib(long  n,long ans[])
	{	System.out.println("fib:"+n);
	    if(n == 0)
	    {
	        ans[0] = 0;
	        ans[1] = 1;
	        return;
	    }
	    fast_fib((n/2),ans);
	    long a = ans[0];             /* F(n) */
	    long b = ans[1];             /* F(n+1) */
	    long c = 2*b - a;
	    if(c < 0)
	        c += mod;
	    c = (a * c) % mod;      /* F(2n) */
	    long d = (a*a + b*b) % mod;  /* F(2n + 1) */
	    if(n%2 == 0)
	    {
	        ans[0] = c;
	        ans[1] = d;
	    }
	    else
	    {
	        ans[0] = d;
	        ans[1] = c+d;
	    }
	}

	public static void main(String[] args) {
	for (int i = 0; i < 10; i++) {
		fast_fib(i,ans);
		System.out.println(i+" "+ans[0]);
	}
	 /* Scanner sc = new Scanner(System.in);
      
      long m = 1000000007;
      //period=2000000016 of fib(n)%1000000007=fib(n%2000000016)
      long period = getFibPeriod(m);
      System.out.println(period);
      long t=sc.nextLong();
      while(t-->0){
         a = sc.nextLong();
         b = sc.nextLong();
         long n=sc.nextLong()-1;
         long nmod = n%period;
         long ans=getNthFibNum(nmod, m);
         System.out.println(ans>=0?ans:ans+m);
      }*/

	}

}
