import java.util.Scanner;


public class nCr {
	static long m=0;
	
	static long modPow(long a, long x, long p) {
	    //calculates a^x mod p in logarithmic time.
	    long res = 1;
	    while(x > 0) {
	        if( x % 2 != 0) {
	            res = (res * a) % p;
	        }
	        a = (a * a) % p;
	        x /= 2;
	    }
	    return res;
	}

	static long modInverse(long a, long p) {
	    //calculates the modular multiplicative of a mod m.
	    //(assuming p is prime).
	    return modPow(a, p-2, p);
	}
	static long modBinomial(long n, long k, long p) {
	// calculates C(n,k) mod p (assuming p is prime).

	    long numerator = 1; // n * (n-1) * ... * (n-k+1)
	    for (int i=0; i<k; i++) {
	        numerator = (numerator * (n-i) ) % p;
	    }

	    long denominator = 1; // k!
	    for (int i=1; i<=k; i++) {
	        denominator = (denominator * i) % p;
	    }

	    // numerator / denominator mod p.
	    return ( numerator* modInverse(denominator,p) ) % p;
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		m=(long)Math.pow(10,9)+7;
		long tst=sc.nextLong();
		System.out.println(modInverse(2,m));
		System.out.println(m);
		while(tst>0)
		{
			
			long n=sc.nextLong();
			long k=sc.nextLong();
		
			System.out.println(modBinomial(n-1,k-1,m));
			tst--;
		}
	}

}
