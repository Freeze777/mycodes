package algo.number.theory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProductDivisors {
	
	public static void primeFactors(long n,Map<Long,Integer> primes) {
		
		for (long i = 2; i <= n / i; i++) {
			while (n % i == 0) {
				if(primes.containsKey(i))
					primes.put(i,primes.get(i)+1);
				else
					primes.put(i,1);
				n /= i;
			}
		}
		if (n > 1) {
			if(primes.containsKey(n))
				primes.put(n,primes.get(n)+1);
			else
				primes.put(n,1);
		}
		
	}

	public static void main(String[] args) {
		
		Map<Long,Integer> primes=new HashMap<Long, Integer>();
		Scanner sc=new Scanner(System.in);
		long n=sc.nextLong();
		long mod=1000000007;
		for (int i = 0; i < n; i++) {
			primeFactors(sc.nextLong(), primes);
		}
		long sum=1;
		for (Long key :primes.keySet()) {
			int count=primes.get(key);
			sum=(sum*(count+1))%mod;
			
		}
		System.out.println(sum);
		
	}
}
