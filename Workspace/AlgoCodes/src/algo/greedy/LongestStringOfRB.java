package algo.greedy;

/*
 2
 RBR BBR RRR
 RRRR BBB R BBB RBBBB RRB BRBR RBBB BR
 9
 29
 * 
 * */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LongestStringOfRB {
	public static int getMaxLen(String[] s) {
		int ans = 0, rr = 0, bb = 0;
		List<Integer> rb = new ArrayList<Integer>();
		List<Integer> br = new ArrayList<Integer>();
		for (int i = 0; i < s.length; i++) {
			int c1 = s[i].charAt(0);
			int c2 = s[i].charAt(s[i].length() - 1);
			if (c1 == 'R' && c2 == 'R')
				rr += s[i].length();
			else if (c1 == 'B' && c2 == 'B')
				bb += s[i].length();
			else if (c1 == 'R' && c2 == 'B')
				rb.add(s[i].length());
			else
				br.add(s[i].length());
		}
		if (rb.size() == 0 && br.size() == 0)
			return Math.max(rr, bb);
		ans = rr + bb;
		Collections.sort(rb);
		Collections.sort(br);
		int i = rb.size() - 1;
		int j = br.size() - 1;
		while (i >= 0 && j >= 0) {
			ans += (rb.get(i) + br.get(j));
			i--;
			j--;
		}
		if (i < 0 && j >= 0)
			ans += br.get(j);
		else if (j < 0 && i >= 0)
			ans += rb.get(i);

		return ans;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = Integer.parseInt(sc.nextLine());
		while (t-- > 0) {
			String[] s = sc.nextLine().split(" ");
			int ans = getMaxLen(s);
			System.out.println(ans);
		}
	}

}
