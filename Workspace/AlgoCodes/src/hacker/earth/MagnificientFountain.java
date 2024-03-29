package hacker.earth;

/*https://www.hackerearth.com/problem/algorithm/magnificent-fountains/*/

import java.util.*;
import java.math.*;
 
class TestClass {
    public static void main(String args[] ) throws Exception {
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		BigInteger mod=new BigInteger("1000000007");
		while(t-->0){
			long n=sc.nextLong();
			BigInteger l=BigInteger.ONE;
			while(n-->0){
				BigInteger a=sc.nextBigInteger();
				BigInteger gcd=l.gcd(a);
				l=(l.multiply(a)).divide(gcd);
			}
			System.out.println(l.mod(mod));
		}
    }
}