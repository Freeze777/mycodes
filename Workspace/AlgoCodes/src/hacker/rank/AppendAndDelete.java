package hacker.rank;
import java.util.Scanner;

public class AppendAndDelete {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s1 = sc.next();
		String s = s1;
		String s2 = sc.next();
		String t = s2;
		int k = sc.nextInt();
		String small, large;
		if (s1.length() > s2.length()) {
			small = s2;
			large = s1;
		} else {
			small = s1;
			large = s2;
		}
		int commonPrefix = 0;
		for (char c : large.toCharArray()) {
			if (commonPrefix == small.length())
				break;
			if (c != small.charAt(commonPrefix))
				break;
			commonPrefix++;
		}
		// System.out.println(index);
		int deletes = (s.length() - commonPrefix);
		int appends = (t.length() - commonPrefix);
		if (deletes + appends > k) {
			System.out.println("No");
		} else if (deletes + appends == k) {
			System.out.println("Yes");
		} else {
			int leftover = k - (deletes + appends);
			if (leftover >= 2 * commonPrefix)
				System.out.println("Yes");
			else if (leftover % 2 == 0) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}

		}
	}
}