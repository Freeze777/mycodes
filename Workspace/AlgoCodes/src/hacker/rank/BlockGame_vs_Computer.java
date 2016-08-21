package hacker.rank;
import java.io.*;
/**/
public class BlockGame_vs_Computer {

	public static String[] amount_value(int input1, String[] input2) {
		int[][] arr = new int[input1][input1];
		int[][] res = new int[input1][input1];
		for (int i = 0; i < input2.length; i++) {
			String[] str_arr = input2[i].split("#");
			for (int j = 0; j < str_arr.length; j++) {
				arr[i][j] = Integer.parseInt(str_arr[j]);

			}

		}

		int max = Integer.MIN_VALUE;
		StringBuilder ans = new StringBuilder("");
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res.length; j++) {

				int min = arr[i][j];
				if (i >= 1)
					min = (min > arr[i - 1][j]) ? arr[i - 1][j] : min;
				if (i >= 1 && j >= 1)
					min = (min > arr[i - 1][j - 1]) ? arr[i - 1][j - 1] : min;
				if (j >= 1)
					min = (min > arr[i][j - 1]) ? arr[i][j - 1] : min;
				if (i <= res.length - 2)
					min = (min > arr[i + 1][j]) ? arr[i + 1][j] : min;
				if (j <= res.length - 2)
					min = (min > arr[i][j + 1]) ? arr[i][j + 1] : min;
				if (i <= res.length - 2 && j <= res.length - 2)
					min = (min > arr[i + 1][j + 1]) ? arr[i + 1][j + 1] : min;
				if (i >= 1 && j <= res.length - 2)
					min = (min > arr[i - 1][j + 1]) ? arr[i - 1][j + 1] : min;
				if (j >= 1 && i <= res.length - 2)
					min = (min > arr[i + 1][j - 1]) ? arr[i + 1][j - 1] : min;

				max = (max < min) ? min : max;
				res[i][j] = min;
			}

		}
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res.length; j++) {
				if (res[i][j] == max)
					ans.append((i + 1) + "#" + (j + 1) + ",");

			}

		}

		return ans.toString().split(",");
	}

	public static void main(String[] args) {
		String[] inp2 = { "12#45#33", "94#54#23", "98#59#27" };
		inp2 = amount_value(3, inp2);
		for (int i = 0; i < inp2.length; i++) {
			System.out.println(inp2[i]);

		}
	}
}