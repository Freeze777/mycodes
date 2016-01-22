package hacker.rank;

import java.util.Scanner;

public class AlternatingCharacters {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();
		while (tst > 0) {

			char s[] = sc.next().toCharArray();
			int res = 0;
			for (int i = 0, j = 1; i < s.length&&j<s.length;) {
				if (s[i] == s[j]) {
					res++;
					j++;
				}else
				{
					i=j;j++;
				}
			}
			System.out.println(res);
			tst--;
		}
	}
}
