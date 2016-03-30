package hacker.rank;

import java.io.*;
import java.util.*;

public class ManasaAndStones {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int tst = sc.nextInt();
		while (tst > 0) {

			int n = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
			Set<Integer> set = new TreeSet<Integer>();
			int curr = 0;
			computeLastNum_method2(n - 1, a, b, set);
			tst--;
			for (Integer ele : set)
				System.out.print(ele + " ");

			System.out.println();

		}

	}

	private static void computeLastNum_method2(int n, int a, int b,
			Set<Integer> set) {
		
		for(int x=0;x<=n;x++)
			set.add(a*x+b*(n-x));
		
		
	}
/*
	private static void computeLastNum_method1(int n, int a, int b, int curr,
			Set<Integer> set) {
		if (n == 0) {
			set.add(curr);
			return;
		}
		computeLastNum(n - 1, a, b, curr + a, set);
		computeLastNum(n - 1, a, b, curr + b, set);
	}*/
}