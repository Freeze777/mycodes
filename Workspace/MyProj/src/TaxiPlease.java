import java.util.PriorityQueue;
import java.util.Scanner;

import algo.Utils;

public class TaxiPlease {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] taxi_service_end_time = new int[m];
		// System.out.println(pq);
		for (int i = 0; i < n; i++) {
			int s = sc.nextInt();
			int j = sc.nextInt();
			int t = 0;
			while (t < taxi_service_end_time.length
					&& taxi_service_end_time[t] > s)
				t++;
			if (t < taxi_service_end_time.length) {
				taxi_service_end_time[t] = s + j;
				System.out.print((t+1)+" ");
			} else {
				System.out.print("-1 ");
			}
			
			Utils.printArray(taxi_service_end_time);
			System.out.println();
			
		}

	}

}
