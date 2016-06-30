package algo.string;
import java.util.BitSet;
import java.util.HashSet;

public class DetectPanagrams {

	public static void main(String[] args) {

		String s = "pack my box with five dozen liquor jugs";

		System.out.println(method4(s.toLowerCase()));
	}

	private static boolean method4(String s) {
		boolean[] flag = new boolean[26];
		int count = 0;
		for (char ch : s.toCharArray())
			if (ch <= 'z' && ch >= 'a') {
				if (!flag[ch - 'a']) {
					flag[ch - 'a'] = true;
					count++;
				}
			}

		return (count == 26);
	}

	private static boolean method3(String s) {
		HashSet<Character> set = new HashSet<Character>();
		for (char ch : s.toCharArray())
			if (ch <= 'z' && ch >= 'a')
				set.add(ch);

		return (set.size() == 26);
	}

	private static boolean method2(String s) {
		int i = 0;
		for (char ch : s.toCharArray())
			if (ch <= 'z' && ch >= 'a')
				i = i | (1 << (ch - 'a'));

		return (i == 0x3ffffff);
	}

	private static boolean method1(String s) {
		BitSet bs = new BitSet(26);
		for (char ch : s.toCharArray())
			if (ch <= 'z' && ch >= 'a')
				bs.set(ch - 'a');
		return (bs.cardinality() == 26);
	}
}
