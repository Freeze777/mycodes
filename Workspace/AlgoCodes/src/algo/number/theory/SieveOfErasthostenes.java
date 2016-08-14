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
		//highest repeating digit among prime numbers in range
		/*Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < 10; i++)
			map.put(i, 0);
		int ans = 0;
		int maxCount = 0;
		int l = 2, r = 9;
		for (int i = l; i <= r; i++) {
			if (isPrime[i] == 1) {
				int tmp = i;
				while (tmp != 0) {
					int dig = tmp % 10;
					tmp = tmp / 10;
					int count = map.get(dig);
					count++;
					map.put(dig, count);
					if (count > maxCount) {
						maxCount = count;
						ans = dig;
					} else if (count == maxCount) {
						ans = Math.max(ans, dig);
					}
				}
			}
		}
		System.out.println(ans);*/
		// prefix sum computation for number of primes in range
		for (int i = 2; i < isPrime.length; i++) {
			isPrime[i] += isPrime[i - 1];
		}
		System.out.println(isPrime[max]);
	}
}
