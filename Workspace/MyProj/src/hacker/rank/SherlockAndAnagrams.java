package hacker.rank;
import java.io.*;
import java.util.*;

class Tuple {
	int start;
	int end;

	Tuple(int x, int y) {
		this.start = x;
		this.end = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + end;
		result = prime * result + start;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tuple other = (Tuple) obj;
		if (end != other.end)
			return false;
		if (start != other.start)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tuple [" + start + "," + end + "]";
	}

}

public class SherlockAndAnagrams {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();
		while (tst > 0) {
			char[] str = sc.next().toCharArray();
			Map<Tuple, Map<Character, Integer>> map = new HashMap<Tuple, Map<Character, Integer>>();
			int sum = 0;
			for (int w = 0; w < str.length; w++) {
				for (int s = 0; s < str.length - w; s++) {
					for (int e = s; e < str.length - w; e++) {
						Tuple t1 = new Tuple(s, s + w);
						Tuple t2 = new Tuple(e, e + w);
						sum += checkAnagram(str, t1, t2, map);
					}
				}

			}
			System.out.println(sum);
			tst--;
		}

	}

	public static int checkAnagram(char[] str, Tuple t1, Tuple t2,
			Map<Tuple, Map<Character, Integer>> map) {
		if(t1.equals(t2))
			return 0;
		//System.out.println("checking " + t1 + " and "+t2 );
		Map<Character, Integer> countMap1 = null;
		Map<Character, Integer> countMap2 = null;
		if (map.containsKey(t1)) {
			countMap1 = map.get(t1);
			//System.out.println("hit1");
		} else {
			countMap1 = new HashMap<Character, Integer>();
			for (int i = t1.start; i <= t1.end; i++) {
				int count = (countMap1.get(str[i]) == null) ? 0 : countMap1
						.get(str[i]);
				countMap1.put(str[i], count + 1);
			}
			map.put(t1, countMap1);
		}
		if (map.containsKey(t2)){
			countMap2 = map.get(t2);
			//System.out.println("hit2");
		}
		else {
			countMap2 = new HashMap<Character, Integer>();
			for (int i = t2.start; i <= t2.end; i++) {
				int count = (countMap2.get(str[i]) == null) ? 0 : countMap2
						.get(str[i]);
				countMap2.put(str[i], count + 1);
			}
			map.put(t2, countMap2);
		}
		
		//System.out.println(countMap1.equals(countMap2));
		return countMap1.equals(countMap2) ? 1 : 0;
		
	//	return 0;

	}
}