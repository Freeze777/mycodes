package hacker.rank;

import java.util.Arrays;
import java.util.Scanner;

public class MorganAndString {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tst = Integer.parseInt(sc.nextLine());
		while (tst > 0) {

			StringBuilder t1 =new StringBuilder(sc.nextLine());
			StringBuilder t2 =new StringBuilder( sc.nextLine());
			char s1[]=t1.append('|').toString().toCharArray();
			char s2[]=t1.append('|').toString().toCharArray();

			StringBuilder res = new StringBuilder("");
			int i, j;
			for (i = 0, j = 0; i < s1.length && j < s2.length;) {
				if (s1[i] < s2[j])
					res.append(s1[i++]);
				else if (s1[i] > s2[j])
					res.append(s2[j++]);
				else {
					int temp_i = i;
					int temp_j = j;
					if(s1[i]=='|'&&s2[j]=='|')
						break;
					while (temp_i<s1.length&&temp_j<s2.length&&s1[temp_i] == s2[temp_j]) {
						temp_i++;
						temp_j++;
					}
					if(s1[temp_i]>s2[temp_j])
						res.append(s2[j++]);
					else
						res.append(s1[i++]);
				}
			}
			if (i == s1.length) {
				for (; j < s2.length-1; j++)
					res.append(s2[j]);

			} else if (j == s2.length) {
				for (; i < s1.length-1; i++)
					res.append(s1[i]);

			}
			System.out.println(res);
			tst--;
		}
	}

}