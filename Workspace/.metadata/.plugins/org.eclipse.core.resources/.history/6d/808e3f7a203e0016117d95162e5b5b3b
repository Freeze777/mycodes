package hacker.rank;


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class NegativePowerComputationModuloX {
	private static long pow( long a,long b, long x) {
		long result=1;
		if(b==0)
			return 1;
		else if(b<0)
		{
			a=findInverse( a, x);
			b=-b;
		}
		while(b>0)
		{	
			if(b%2==1)
				result=(result*a)%x;

			a=(a*a)% x;
			b/=2;

		}


		return result;
	}


	private static long findInverse(long a, long x) {
	long b=1;
		while(b<x)
		{
			if((b*a)%x==1)
				return b;
			b++;
		}
		return -1;
	}


	public static void main(String[] args) {
		/* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		Scanner sc =new Scanner(System.in);
		long tst=sc.nextLong();
		while(tst>0)
		{
			long a=sc.nextLong();
			long b=sc.nextLong();
			long x=sc.nextLong();
			System.out.println(pow(a,b,x));

			tst--;
		}
		


	}
}