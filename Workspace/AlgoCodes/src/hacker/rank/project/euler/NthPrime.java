package hacker.rank.project.euler;
import java.util.Scanner;

public class NthPrime {
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		int max = 1000000;
		boolean isPrime[] = new boolean[max + 1];
		for (int i = 2; i <= max; i++)
			isPrime[i] = true;

		// mark non-primes <=N using Sieve of Eratosthenes
		for (int i = 2; i * i <= max; i++) {

			// if is a prime,then mark multiples of i as non prime
			for (int j = i; i * j <= max; j++) {
				isPrime[i * j] = false;
			}
		}
		
		int[] prime_i=new int[max];
		int count=0;
		for (int i = 2; i < isPrime.length; i++) {
			if(isPrime[i])
				prime_i[++count]=i;
			
		}
		int tst = sc.nextInt();
		while(tst>0){
			int n=sc.nextInt();
			System.out.println(prime_i[n]);			
			tst--;
		}
		

	}
}
