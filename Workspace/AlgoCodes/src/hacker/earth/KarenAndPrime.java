package hacker.earth;

import java.util.Arrays;
import java.util.Scanner;

/*https://www.hackerearth.com/problem/algorithm/karan-and-prime-numbers/*/
public class KarenAndPrime {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int max = 1000001;
		long[] p = new long[max];
		Arrays.fill(p, 1);
		p[0] = 0;
		p[1] = 0;
		for (int i = 2; i * i < max; i++) {
			if (p[i] == 1) {
				for (int j = i; j * i < max; j++)
					p[i * j] = 0;
			}
		}
		for (int i = 2; i < max; i++) {
			if (p[i] == 1) {
				p[i] = i + p[i - 1];
			} else {
				p[i] = p[i - 1];
			}
		}
		while (t-- > 0) {
			int l = sc.nextInt();
			int r = sc.nextInt();
			System.out.println(p[r] - p[l - 1]);
		}
	}
}