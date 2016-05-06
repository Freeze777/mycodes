package hacker.rank;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Anagram {

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		while (n > 0) {
			String s = sc.next();
			int len = s.length();
			int result = 0;
			if (len % 2 == 1) {
				result = -1;
			} else {
				Map<Character, Integer> countMap = new HashMap<Character, Integer>();
				int breakpoint = (len / 2) - 1;
				for (int i = breakpoint + 1; i < len; i++) {
					char ch = s.charAt(i);
					if (countMap.containsKey(ch))
						countMap.put(ch, countMap.get(ch) + 1);
					else
						countMap.put(ch, 1);

				}

				for (int i = 0; i <= breakpoint; i++) {
					char ch = s.charAt(i);
					if (countMap.containsKey(ch)) {
						if(countMap.get(ch)>0)
						countMap.put(ch, countMap.get(ch) - 1);
						else
							result++;
					} else
						result++;
				}
			}
			System.out.println(result);
		
			n--;
		}
	}
}