//package algo.graph.tushar;

import java.util.Scanner;

public class AllPairShortestPath {

	private static final long INF = Long.MAX_VALUE/2;

	public static void allPairShortestPath(long[][] distance) {
		for (int k = 0; k < distance.length; k++) {
			for (int i = 0; i < distance.length; i++) {
				for (int j = 0; j < distance.length; j++) {
					if (distance[i][k] == INF || distance[k][j] == INF) {
						continue;
					}
					if (distance[i][j] > distance[i][k] + distance[k][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
					}
				}
			}
		}
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[][] distance1 = new long[n][n];
		long[][] distance2 = new long[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				distance1[i][j] = distance1[j][i] = INF;
				distance2[i][j] = distance2[j][i] = INF;
			}

		}

		for (int i = 1; i < n; i++) {
			int v = sc.nextInt() - 1;
			distance1[v][i] = 1;
			distance2[i][v] = 1;
			distance2[v][i] = 1;
		}
		allPairShortestPath(distance1);
		allPairShortestPath(distance2);
		squareMatrix(distance2);
		int q = sc.nextInt();
		for (int i = 0; i < q; i++) {
			int vf = sc.nextInt() - 1;
			int von = sc.nextInt() - 1;
			long ans = 0;
			for (int v1 = 0; v1 < n; v1++) {
				if (distance1[von][v1] != INF) {
					ans += distance2[vf][v1];
				}
			}
			System.out.println(ans);
		}
	}

	private static void squareMatrix(long[][] distance) {
		for (int i = 0; i < distance.length; i++) {
			for (int j = 0; j < distance.length; j++) {
				if (distance[i][j] != 0 && distance[i][j] != INF) {
					long tmp = distance[i][j];
					distance[i][j] = (tmp * tmp);
				}
			}
		}

	}
}
