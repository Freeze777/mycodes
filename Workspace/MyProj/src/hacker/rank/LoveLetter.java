package hacker.rank;

import java.util.Scanner;

public class LoveLetter {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();
		while (tst > 0) {

			char s[] = sc.next().toCharArray();
			int res = 0;
			for (int i = 0, j = s.length - 1; i < j; i++, j--)
				if (s[i] != s[j])
					res += Math.abs(s[i] - s[j]);

			System.out.println(res);
			tst--;
		}

	}
}
