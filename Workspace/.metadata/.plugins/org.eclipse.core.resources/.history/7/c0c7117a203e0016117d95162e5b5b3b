package hacker.rank;

import java.util.Scanner;

public class Candies {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int A[] = new int[n];
		long cnt[] = new long[n];
		for (int i = 0; i < A.length; i++) {
			A[i] = sc.nextInt();
			cnt[i] = 1;
		}

		int i = 0;
		while (i < A.length) {
			while (i < A.length-1 && A[i+1] > A[i]) {
				cnt[i + 1] = cnt[i] + 1;
				i++;
			}
			i++;
		}
/*		for (int j = 0; j < cnt.length; j++) {
			System.out.print(cnt[j]+" ");
		}
		System.out.println();*/
		i=A.length-1;
		while (i >= 0) {
			while (i >0 && A[i-1] > A[i]) {
				if(cnt[i-1]<=cnt[i])
				cnt[i - 1] = cnt[i] + 1;
				i--;
			}
			i--;
		}
		
	/*	for (int j = 0; j < cnt.length; j++) {
			System.out.print(cnt[j]+" ");
		}
		System.out.println();*/
		long sum = 0;
		for (int j = 0; j < cnt.length; j++)
			sum += cnt[j];

		System.out.println(sum);

	}

}
