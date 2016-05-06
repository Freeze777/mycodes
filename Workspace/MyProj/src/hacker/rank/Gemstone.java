package hacker.rank;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Gemstone {
	public static void main(String[] args) {

		Set<Character> set = new HashSet<Character>();
		for (char i = 'a'; i <= 'z'; i++)
			set.add(i);

		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		int res = 0;

		for (int i = 1; i <= n; i++) {
			char str[] = sc.nextLine().toCharArray();
			Set<Character> s = new HashSet<Character>();
			for (int j = 0; j < str.length; j++) {
				s.add(str[j]);

			}
			set.retainAll(s);
		}

		System.out.println(set.size());
	}

}
