package hacker.rank;
import java.util.HashMap;
import java.util.Map;

public class CircularGame {

	public static int passCount(int n, int m, int l) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int curr = 1;
		int count = 0;
		map.put(curr, 1);
		while (true) { // System.out.print(curr+"->");
			int pcount = (map.get(curr) == null) ? 0 : map.get(curr);
			if (pcount % 2 == 0) {
				curr = (curr + l) % n; // left
			} else {
				curr = (curr - l) % n;
			}
			count++;
			curr = (curr < 0) ? curr + n : curr;

			pcount = (map.get(curr) == null) ? 0 : map.get(curr);
			pcount += 1;

			if (pcount == m)
				break;

			map.put(curr, pcount);

			// System.out.print(curr+"\n");
		}

		return count;

	}
	/*
	 * public static void main(String[] args) {
	 * System.out.println(passCount(5,3,2)); }
	 */
}
