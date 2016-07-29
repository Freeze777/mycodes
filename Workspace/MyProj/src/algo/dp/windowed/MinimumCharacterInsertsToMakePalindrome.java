package algo.dp.windowed;

public class MinimumCharacterInsertsToMakePalindrome {
	public static void main(String[] args) {
		//String s = "aghbxcycmsbka";
		String s="azbzczdzez";
		// Logic : already we have a long palindromic subsequence which can be
		// ignored.For all the remaining character in the string we need to add
		// its duplicate in 'APPROPRIATE' places.
		int ans = LongestPalindromicSubsequence.LPS(s);
		System.out.println(s.length() - ans);

	}

}
