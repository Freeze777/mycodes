package hacker.earth;
/*https://www.hackerearth.com/problem/algorithm/micro-and-prime-prime-1/*/
import java.util.Arrays;
import java.util.Scanner;

public class MicroAndPrimePrime {
	static int max = 1000000;
	static boolean isPrime[] = new boolean[max + 1];
	static int pref[] = new int[max + 1];

	public static void sieve() {
		Arrays.fill(pref, 1);
		Arrays.fill(isPrime, true);
		pref[0] = 0;
		isPrime[0] = false;
		pref[1] = 0;
		isPrime[1] = false;
		for (int i = 2; i * i <= max; i++) {
			if (isPrime[i]) {
				for (int j = i; i * j <= max; j++) {
					pref[i * j] = 0;
					isPrime[i * j] = false;
				}
			}
		}
		for (int i = 1; i <= max; i++) {
			pref[i] += pref[i - 1];
		}
		for (int i = 0; i <= max; i++) {
			if (isPrime[pref[i]]) {
				pref[i] = 1;
			} else {
				pref[i] = 0;
			}
		}
		for (int i = 1; i <= max; i++) {
			pref[i] += pref[i - 1];
		}
	}

	public static void main(String args[]) throws Exception {
		sieve();
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			int l = sc.nextInt();
			int r = sc.nextInt();
			System.out.println(pref[r] - pref[l - 1]);
		}
	}
}
