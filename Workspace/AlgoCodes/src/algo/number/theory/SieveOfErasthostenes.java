package algo.number.theory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SieveOfErasthostenes {
	public static void main(String[] args) {
		int max = 10000000;
		int[] isPrime = new int[max + 1];
		Arrays.fill(isPrime, 1);
		isPrime[0] = 0;
		isPrime[1] = 0;

		// consider all numbers less than sqrt(max) and mark them true
		for (int i = 2; i * i <= max; i++) {
			if(isPrime[i]==1){
				for (int j = i; i * j <= max; j++) {
					isPrime[i * j] = 0;
				}
			}
		}
		
		// prefix sum computation for number of primes in range
		for (int i = 2; i < isPrime.length; i++) {
			isPrime[i] += isPrime[i - 1];
		}
		System.out.println(isPrime[max]);//for testing against standard values
	}
}
