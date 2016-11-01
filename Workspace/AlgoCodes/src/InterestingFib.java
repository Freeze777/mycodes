import java.io.*;
import java.util.*;
import java.math.*;

public class InterestingFib {

	static final int mod = (int) 1e9 + 7;
	static final long inv2 = BigInteger.valueOf(2)
			.modInverse(BigInteger.valueOf(mod)).intValueExact();
	static int[] a;
	static SegNode segtree[];
	public static long cr[][] = new long[2][2];
	public static long s[][][] = new long[31][2][2];
	public static long mat[][] = { { 1, 1 }, { 1, 0 } };
	public static long res[][] = new long[2][2];

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter w = new PrintWriter(System.out);

		int m = in.nextInt();

		precompute_s();

		while (m-- > 0) {
			int n = in.nextInt();
			a = in.nextIntArray(n);
			segtree = new SegNode[4 * n];
			build(0, n - 1, 0);
			SegNode node=find(0 , n - 1 , 0 , 0 , n-1);
			w.println(node.l-node.f);
		}

		w.close();
	}

	public static void build(int s, int e, int c) {
		if (s == e) {
			segtree[c] = new SegNode(a[s]);
			return;
		}
		int m = (s + e) >> 1;
		build(s, m, 2 * c + 1);
		build(m + 1, e, 2 * c + 2);
		segtree[c] = merge(segtree[2 * c + 1], segtree[2 * c + 2]);
	}

	public static void update(int s, int e, int c, int x, int v) {
		if (s == e) {
			a[s] = v;
			segtree[c] = new SegNode(a[s]);
			return;
		}
		int m = (s + e) >> 1;
		if (x <= m)
			update(s, m, 2 * c + 1, x, v);
		else
			update(m + 1, e, 2 * c + 2, x, v);
		segtree[c] = merge(segtree[2 * c + 1], segtree[2 * c + 2]);
	}

	public static SegNode find(int s, int e, int c, int l, int r) {
		if (s == l && e == r)
			return segtree[c];
		int m = (s + e) >> 1;
		if (r <= m)
			return find(s, m, 2 * c + 1, l, r);
		if (l > m)
			return find(m + 1, e, 2 * c + 2, l, r);
		return merge(find(s, m, 2 * c + 1, l, m),
				find(m + 1, e, 2 * c + 2, m + 1, r));
	}

	static SegNode merge(SegNode a, SegNode b) {
		SegNode c = new SegNode(0, 0);
		c.f = a.f * b.l + a.l * b.f;
		c.f %= mod;
		c.f *= inv2;
		c.f += a.f + b.f;
		c.f %= mod;
		c.l = 5 * a.f * b.f + a.l * b.l;
		c.l %= mod;
		c.l *= inv2;
		c.l += a.l + b.l;
		c.l %= mod;
		return c;
	}

	static class SegNode {
		long f, l;

		SegNode(long f, long l) {
			this.f = f;
			this.l = l;
		}

		SegNode(int x) {
			matpowmod(x);
			f = fmod(res[1][0]);
			l = fmod(2 * res[1][1] + res[1][0]);
		}

		public String toString() {
			return f + " " + l;
		}
	}

	public static long fmod(long n) {
		return n % mod;
	}

	public static void matmulmod(long a[][], long b[][], long res[][]) {
		cr[0][0] = fmod(a[0][0] * b[0][0] + a[0][1] * b[1][0]);
		cr[0][1] = fmod(a[0][0] * b[0][1] + a[0][1] * b[1][1]);
		cr[1][0] = fmod(a[1][0] * b[0][0] + a[1][1] * b[1][0]);
		cr[1][1] = fmod(a[1][0] * b[0][1] + a[1][1] * b[1][1]);
		res[0][0] = cr[0][0];
		res[0][1] = cr[0][1];
		res[1][0] = cr[1][0];
		res[1][1] = cr[1][1];
	}

	public static void matpowmod(long b) { // res = mat ^ b
		res[0][0] = res[1][1] = 1;
		res[0][1] = res[1][0] = 0;
		int si = 0;
		while (b > 0) {
			if ((b & 1) == 1)
				matmulmod(res, s[si], res);
			b >>= 1;
			si++;
		}
	}

	public static void precompute_s() {
		s[0][0][0] = s[0][0][1] = s[0][1][0] = 1;
		s[0][1][1] = 0;
		for (int i = 1; i < 31; i++)
			matmulmod(s[i - 1], s[i - 1], s[i]);
	}

	static class InputReader {

		private InputStream stream;
		private byte[] buf = new byte[8192];
		private int curChar, snumChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int snext() {
			if (snumChars == -1)
				throw new InputMismatchException();
			if (curChar >= snumChars) {
				curChar = 0;
				try {
					snumChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (snumChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		public int nextInt() {
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = snext();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public long nextLong() {
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = snext();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public int[] nextIntArray(int n) {
			int a[] = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = nextInt();
			return a;
		}

		public String readString() {
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = snext();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public boolean isSpaceChar(int c) {
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}