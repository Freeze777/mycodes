package hacker.rank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FullCountingSort {
	static class Node {
		int index;
		String str;

		Node(int i, String s) {
			index = i;
			str = s;
			// data=d;
		}
	}

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */

		ArrayList<ArrayList<Node>> arr = new ArrayList<ArrayList<Node>>();
		for (int l = 0; l < 100; l++)
			arr.add(new ArrayList<Node>());
  
		try {
			// open input stream test.txt for reading purpose.
			String thisLine;
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			int n = Integer.parseInt(br.readLine().trim());
			int i = 0;
			while ((thisLine = br.readLine()) != null) {
				String[] inp = thisLine.split(" ");
				int num = Integer.parseInt(inp[0].trim());
				String ch = inp[1].trim();
				arr.get(num).add(new Node(i++, ch));

			}

			StringBuilder result = new StringBuilder();

			for (i = 0; i < 100; i++) {
				ArrayList<Node> ls = arr.get(i);
				if (ls.size() != 0) {
					for (Node n1 : ls) {
						if (n1.index >= n / 2)
							result.append(n1.str + " ");
						else
							result.append("- ");
					}
				}
			}

			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}