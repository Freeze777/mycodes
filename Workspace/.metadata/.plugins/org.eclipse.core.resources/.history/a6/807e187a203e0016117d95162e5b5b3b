package hacker.rank;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SquaresBetweenAandB {

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 * 
		 * BEST SOLUTION= (floor(b)-ciel(a))
		 */
		Scanner sc = new Scanner(System.in);
	
		long tst = sc.nextLong();
		while (tst > 0) {
			long result = 0;
			long a = sc.nextLong();
			long b = sc.nextLong();
			while ((a <= b) && (Math.sqrt(a++)) % 1 != 0)
				;
			a--;
			if (a <= b && Math.sqrt(a)%1==0) {
				long base = (long) Math.sqrt(a);
				long sqr = 0;

				while (sqr <= b) {
					result++;
					base++;
					sqr = base * base;
				}
			}

			System.out.println(result);
			tst--;
		}
	}
}