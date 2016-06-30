package hacker.rank;

import java.util.Scanner;

public class StockMaximise {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();
		while (tst > 0) {
			int n = sc.nextInt();
			int[] stock = new int[n];
			for (int i = 0; i < stock.length; i++)
				stock[i] = sc.nextInt();

			long profit = 0;
			int maxFromRight = Integer.MIN_VALUE;
			for (int i = stock.length - 1; i >= 0; i--) {
				maxFromRight = Math.max(maxFromRight, stock[i]);
				
				profit+=(maxFromRight-stock[i]);
			}
			System.out.println(profit);
			tst--;
		}
	}
}