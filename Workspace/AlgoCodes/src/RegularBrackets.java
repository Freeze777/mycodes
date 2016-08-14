import java.util.Scanner;
import java.util.Stack;

public class RegularBrackets {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String b = sc.next();
		int count = 0;
		Stack<Character> s = new Stack<Character>();
		for (int i = 0; i < b.length(); i++) {
			if (b.charAt(i) == '(')
				s.push('(');
			else {
				if (!s.isEmpty())
					s.pop();
				else
					count++;

			}
		}
		System.out.println(b.length() - s.size() - count);
	}

}
