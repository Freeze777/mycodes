package hacker.rank;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Print5sAnd3s {

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. print output to STDOUT.
		 * Your class should be named Solution.
		 */
		Scanner sc = new Scanner(System.in);
		StringBuilder result = new StringBuilder();
		long tst = sc.nextLong();
		while (tst > 0) {
			long i;
			long n = sc.nextLong();
			if (n % 3 == 0) {
				for (i = 0; i < n; i++) {
					result = result.append("5");

				}
				System.out.println(result);
			} else {
				long x = 0, y = 0; // y =#3s x=#5s
				for (y = 5; y <= n; y += 5) {
					if ((y - (n % 3)) % 3 == 0) {
						x = n - y;
						for (i = 0; i < x; i++)
							result = result.append("5");
						for (i = 0; i < y; i++)
							result = result.append("3");

						System.out.println(result);
						break;
					}
				}
				if (y > n) {
					System.out.println(-1);
				}
			}
			tst--;
			result.delete(0, result.capacity());
			// System.out.println(tst);
		}
	}
}