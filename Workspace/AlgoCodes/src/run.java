import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class run {
	static long mod = 1000000007;
	static int max = (int) 1e6 + 1;
	static int[] sp = new int[max];
	static boolean[] v = new boolean[max];

	static long isSquare(long x) {
		long sq = (long) (Math.sqrt(x) + 0.5);
		if (sq * sq == x)
			return sq;
		return 0;
	}

	static long isCube(long x) {
		long cb = (long) (Math.cbrt(x) + 0.5);
		if (cb * cb * cb == x)
			return cb;
		return 0;
	}

	static long isFourthPower(long x) {
		long fp = isSquare(x);
		if (fp == 0)
			return 0;
		fp = isSquare(fp);
		if (fp == 0)
			return 0;
		return fp;

	}

	static void sieve() {
		sp[1] = 1;
		for (int i = 2; i < max; i += 2)
			sp[i] = 2;
		for (int i = 3; i < max; i += 2) {
			if (!v[i]) {
				sp[i] = i;
				for (long j = i; (j * i) < max; j += 2) {
					int idx = (int) (j * i);
					if (!v[idx]) {
						v[idx] = true;
						sp[idx] = i;
					}
				}
			}
		}
	}

	private static boolean isSquareSum(int sq_sum) {
		if(sq_sum==0)return false;
		if (isSquare(sq_sum) != 0)
			return false;
		while (sq_sum > 1) {
			int count = 0, p = sp[sq_sum];
			while (p!=0 && sq_sum % p == 0) {
				sq_sum /= p;
				count++;
			}
			if (p % 4 == 3 && count % 2 == 1)
				return false;
		}

		return true;
	}

	private static void solve(Scanner sc, PrintStream out) {
		sieve();

		long f2, f3, f4, l, r, q, a = 0, b = 0, c = 0, cmax = 15000;
		q = sc.nextLong();
		while (q-- > 0) {
			f2 = sc.nextLong();
			f3 = sc.nextLong();
			f4 = sc.nextLong();
			l = sc.nextLong();
			r = sc.nextLong();
			for (long tc = 1; tc <= cmax; tc++) {
				int sq_sum = (int) (f2 - tc * tc);
				if (sq_sum >= 1 && isSquareSum(sq_sum)) {
					for (long tb =  tc; tb <= cmax - tc; tb++) {

						long sq = f2 - tc * tc - tb * tb;
						long cb = f3 - tc * tc * tc - tb * tb * tb;
						long fp = f4 - tc * tc * tc * tc - tb * tb * tb * tb;
						if (sq >= 1 && cb >= 1 && fp >= 1 && (isCube(cb) != 0)
								&& (isFourthPower(fp) != 0)) {
							a = isSquare(sq);
							b = tb;
							c = tc;
							break;
						}

					}
				}
			}
			
			long ans = 0;
			for (long i = l; i <= r; i++) {
				ans += pow(a, i, mod) % mod;
				ans += pow(b, i, mod) % mod;
				ans += pow(c, i, mod) % mod;
			}

			System.out.println(ans);

		}

	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		solve(in, out);
		in.close();
		out.close();
	}

	public static long gcd(long x, long y) {
		if (x % y == 0)
			return y;
		else
			return gcd(y, x % y);
	}

	public static long pow(long n, long p, long m) {
		long result = 1;
		if (p == 0)
			return 1;
		if (p == 1)
			return n;
		while (p != 0) {
			if (p % 2 == 1)
				result *= n;
			if (result >= m)
				result %= m;
			p >>= 1;
			n *= n;
			if (n >= m)
				n %= m;
		}
		return result;
	}

}