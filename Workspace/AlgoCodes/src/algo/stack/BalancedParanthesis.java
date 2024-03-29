package algo.stack;


import java.io.*;
import java.util.*;
//wrong--------> see python code

public class BalancedParanthesis {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();
		Map<Character, Character> map = new HashMap<Character, Character>();
		map.put('(', ')');
		map.put('[', ']');
		map.put('{', '}');

		while (tst-- > 0) {
			char[] s = sc.next().toCharArray();

			Stack<Character> st = new Stack<Character>();
			int i = 0;
			boolean flag = true;
			while (i < s.length && flag) {

				if (map.containsKey(s[i]))
					st.push(s[i]);
				else
					flag = flag & (!st.isEmpty() && map.get(st.pop()) == s[i]);

				i++;

			}

			System.out.println((!flag || !st.isEmpty()) ? "NO" : "YES");

		}
	}
}