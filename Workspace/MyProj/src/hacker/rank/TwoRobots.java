package hacker.rank;

import java.io.*;
import java.util.*;

public class TwoRobots {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();
		while (tst > 0) {
			int m = sc.nextInt();
			int n = sc.nextInt();
			int dist = 0;
			int robot1 = -1, robot2 = -1;
			int ma[] = new int[n];
			int mb[] = new int[n];
			for (int i = 0; i < n; i++) {
				ma[i] = sc.nextInt();
				mb[i] = sc.nextInt();
			}

			dist = Math.min(compute(robot1, robot2, ma, mb, 0, n),
					compute(robot1, robot2, ma, mb, 0, n));

			System.out.println(dist);
			tst--;
		}

	}

	private static int compute(int robot1, int robot2, int[] ma, int[] mb,
			int i, int n) {
		int dist = 0;
		if (i == n)
			return 0;
		if (robot1 == ma[i])
			return dist
					+ Math.abs(ma[i] - mb[i])
					+ compute(mb[i],robot2 , ma, mb, i + 1, n);
		if(robot2 == ma[i])	
			return dist
					+ Math.abs(ma[i] - mb[i])
					+ compute(robot1, mb[i], ma, mb, i + 1, n);
		else
			return dist
					+ Math.abs(ma[i] - mb[i])
					+ Math.min(
							Math.abs(ma[i] - robot1)
									+ compute(mb[i], robot2, ma, mb, i + 1, n),
							Math.abs(ma[i] - robot2)
									+ compute(robot1, mb[i], ma, mb, i + 1, n));

	}
}