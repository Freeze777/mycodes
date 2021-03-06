package algo.dp;

import java.util.*;

class Pair implements Comparable<Pair> {
	int north;
	int south;

	Pair(int north, int south) {
		this.north = north;
		this.south = south;
	}

	// to sort on the south bank cordinates
	@Override
	public int compareTo(Pair o) {
		return this.south - o.south;
	}

	@Override
	public String toString() {
		return "Pair [north=" + north + ", south=" + south + "]";
	}

}

public class BuildingBridges {
	/*
	 * n cities: 0,1,2....n-1 north[i] contains the x coordinate of city i in
	 * the north bank. south[i] contains the x coordinate of city i in the south
	 * bank.
	 */
	public static void main(String[] args) {

		int[] north = { 1, 4, 2, 5, 3 };
		int[] south = { 2, 5, 3, 1, 4 };
		int n = north.length;
		Pair[] arr = new Pair[n];
		for (int i = 0; i < south.length; i++)
			arr[i] = new Pair(north[i], south[i]);
		Arrays.sort(arr);
		System.out.println(findLengthOflongestIncreasingSubsequence(arr));
	}

	public static int findLengthOflongestIncreasingSubsequence(Pair[] arr) {
		int[] dp = new int[arr.length];
		Arrays.fill(dp, 1);// every element is itself a increasing subsequence
		int max = 0;
		for (int i = 1; i < dp.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (arr[i].north > arr[j].north)
					dp[i] = Math.max(dp[i], 1 + dp[j]);
			}
			max = Math.max(max, dp[i]);
		}
		return max;
	}

}
