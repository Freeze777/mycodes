package algo.recursion;

import java.util.*;

public class GenerateBalancedBracketsN {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n =sc.nextInt();
		
		char[] br = new char[2*n];
		//Set<String> set = new HashSet<String>();

		generate(br, 0,n, 0, 0);
		
	}

	private static void generate(char[] br, int curr, int n, int open, int close) {
		if (close == n) {
			System.out.println(String.valueOf(br));
			return;
		}				
		if (open > close) {
			br[curr] = '}';
			generate(br, curr + 1, n, open, close + 1);
		}
		if (open < n) {
			br[curr] = '{';
			generate(br, curr + 1, n, open + 1, close);
		}

	}
	//wrong.....!!
	public static void generate(char[] br, int n, int count, int low, int high,
			Set<String> set) {
		if (count == n) {
			// System.out.println(String.valueOf(br));
			set.add(String.valueOf(br));
			return;

		}
		// Correction for a Bug
		// The output has each repeated twice..!!!
		// both recursive call would be the same for a window of 2.. so handled
		// it seperately
		if ((high - low) + 1 == 2) {
			br[low] = '{';
			br[high] = '}';
			// System.out.println(String.valueOf(br));
			set.add(String.valueOf(br));
			return;

		}
		br[low] = '{';
		br[low + 1] = '}';
		generate(br, n, count + 2, low + 2, high, set);
		br[low] = '{';
		br[high] = '}';
		generate(br, n, count + 2, low + 1, high - 1, set);
		// correction for another bug.. missed the case of (())().. put high and
		// high-1
		br[high - 1] = '{';
		br[high] = '}';
		generate(br, n, count + 2, low, high - 2, set);

	}
}
