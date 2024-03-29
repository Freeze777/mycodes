package hacker.rank;
import java.io.*;
import java.util.*;

public class GridSearch {

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();
		while (tst > 0) {
			int gr = sc.nextInt();
			int gc = sc.nextInt();
			String[] g = new String[gr];
			for (int i = 0; i < g.length; i++) {
				g[i] = sc.next();
			}
			int pr = sc.nextInt();
			int pc = sc.nextInt();
			String[] p = new String[pr];
			for (int i = 0; i < p.length; i++) {
				p[i] = sc.next();
			}
			boolean found = false;
			for (int i = 0; i < g.length; i++) {
				int curr = -1;
				int start=0;
				while((curr = g[i].substring(curr+1).indexOf(p[0])) != -1) {
					start+=curr;
					if (searchGrid(g, p, i + 1, start)) {
						found = true;
						break;
					
					}
				}
			}
			if(found)
				System.out.println("YES");
			else
				System.out.println("NO");
			tst--;
		}
	}
	

	private static boolean searchGrid(String[] g, String[] p, int gIndex,
			int start) {
		System.out.println(gIndex+","+start);
		int pIndex = 1;
		for (int i = gIndex; i < g.length; i++) {
			while (start == g[i].indexOf(p[pIndex])) {
				pIndex++;
				if (pIndex == p.length)
					return true;
			}

		}
		return false;

	}
}