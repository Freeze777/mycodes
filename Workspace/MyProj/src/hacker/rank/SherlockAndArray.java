package hacker.rank;

import java.util.Scanner;

public class SherlockAndArray {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();

		while (tst > 0) {
			int n = sc.nextInt();
			int[] a = new int[n];
			int lsum = 0, rsum = 0;
			for (int i = 0; i < a.length; i++) {
				a[i] = sc.nextInt();
				rsum += a[i];
			}
			rsum -= a[0];
			String res = "NO";
			for (int i = 0; i < a.length; i++) {
				if (lsum == rsum) {
					res="YES";
					break;
				}
				lsum+=a[i];
				if(i<=a.length-2)
				rsum-=a[i+1];
					
			}
			System.out.println(res);
			tst--;
		}
	}

}