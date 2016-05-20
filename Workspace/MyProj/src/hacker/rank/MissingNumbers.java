package hacker.rank;

import java.util.Scanner;

public class MissingNumbers {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] countArray = new int[10001];
		int n = sc.nextInt();
		for (int i = 0; i < n; i++)
			countArray[sc.nextInt()]++;
		int m = sc.nextInt();
		for (int i = 0; i < m; i++)
			countArray[sc.nextInt()]--;
		
		for (int i = 1; i < countArray.length; i++) {
			if(countArray[i]<0)
				System.out.print(i+" ");
		}
		

	}

}
