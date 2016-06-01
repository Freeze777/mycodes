package hacker.rank;

import java.io.*;
import java.util.*;

public class SwapNode {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] left = new int[n + 1];
		int[] right = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			left[i] = sc.nextInt();
			right[i] = sc.nextInt();
		}
		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			int k = sc.nextInt();
			swapNode(1, left, right, 1, k);
			printInorder(1, left, right);
			System.out.println();

		}

		
	}

	private static void swapNode(int i, int[] left, int[] right, int depth,
			int k) {
		if (i == -1)
			return;
		swapNode(left[i], left, right, depth + 1, k);
		swapNode(right[i], left, right, depth + 1, k);
		if(depth%k==0)
		{
			int temp=left[i];
			left[i]=right[i];
			right[i]=temp;
		}

	}

	private static void printInorder(int i, int[] left, int[] right) {
		if (i == -1)
			return;
		printInorder(left[i], left, right);
		System.out.print(i + " ");
		printInorder(right[i], left, right);

	}
}