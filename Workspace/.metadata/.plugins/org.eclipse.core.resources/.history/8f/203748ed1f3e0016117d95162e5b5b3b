package algo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CheckForAnangrams {
	public static void main(String[] args) {
		String s1 = "ttsss";
		String s2 = "tttss";
		boolean flag = checkAnagram_method3(s1.toCharArray(), s2.toCharArray());
		System.out.println(flag);
	}

	public static boolean checkAnagram_method1(char[] s1, char[] s2) {
		if (s1.length != s2.length)
			return false;
		Map<Character, Integer> countMap = new HashMap<Character, Integer>();
		for (int i = 0; i < s1.length; i++) {
			int count = (countMap.get(s1[i]) == null) ? 0 : countMap.get(s1[i]);
			countMap.put(s1[i], ++count);

		}
		for (int i = 0; i < s2.length; i++) {
			int count = (countMap.get(s2[i]) == null) ? 0 : countMap.get(s2[i]);
			countMap.put(s2[i], --count);

		}
		for (Integer val : countMap.values()) {
			if (val != 0)
				return false;
		}
		return true;
	}

	// not fully correct: wrong for "sttt" "ssst"
	public static boolean checkAnagram_method3(char[] s1, char[] s2) {
		if (s1.length != s2.length)
			return false;
		Set<Character> set1 = new HashSet<Character>();
		Set<Character> set2 = new HashSet<Character>();
		int x = 0;
		long sum=0L;
		for (int i = 0; i < s1.length; i++) {
			x = x ^ (1 << (s1[i] - 'a'));
			sum+=s1[i];
			set1.add(s1[i]);
		}
		for (int i = 0; i < s2.length; i++) {
			x = x ^ (1 << (s2[i] - 'a'));
			sum-=s2[i];
			set2.add(s2[i]);
		}
		return (set1.equals(set2) && (x == 0)&&(sum==0));
	}

}
