package hacker.rank;

import java.util.Arrays;
import java.util.Scanner;

public class TwoStrings {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int tst = sc.nextInt();
	while (tst > 0) {

		char s1[] = sc.next().toCharArray();
		char s2[]=sc.next().toCharArray();
		Arrays.sort(s1);
		Arrays.sort(s2);
		String res="NO";
		for (int i = 0,j=0; i < s1.length&&j<s2.length;) {
			if(s1[i]==s2[j])
			{res="YES";
				break;
			}else if(s1[i]>s2[j])
			j++;
			else
			i++;
			
		}
		System.out.println(res);
		tst--;
	}
}
}
