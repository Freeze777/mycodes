package algo.greedy;

import java.util.Arrays;
import java.util.Scanner;

/*Codechef : HOTEL*/
public class MinimumRoomsHotel {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			int[] arr = new int[n];
			int[] dep = new int[n];
			for (int i = 0; i < arr.length; i++)
				arr[i] = sc.nextInt();

			for (int i = 0; i < dep.length; i++)
				dep[i] = sc.nextInt();

			Arrays.sort(arr);
			Arrays.sort(dep);
			int i = 0, j = 0, room = 0, max = 0;
			while (i < n && j < n) {
				if (arr[i] < dep[j]) {
					room++;
					i++;
					max = Math.max(max, room);
				} else if (arr[i] > dep[j]) {
					room--;
					if (room < 0) {
						System.out.println("Invalid Input");
					}
					j++;
				} else {
					i++;
					j++;
				}
			}
			System.out.println(max);
		}
	}
}
