import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
Input:
5 5
3 2
6 4
5 2
2 8
1 10
Output:
2
 *
 **/
public class OverlapingCircles {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		double r = sc.nextDouble();
		int x[] = new int[n];
		int y[] = new int[n];
		int id[] = new int[n];

		for (int i = 0; i < y.length; i++) {
			x[i] = sc.nextInt();
			y[i] = sc.nextInt();
			id[i] = i;
		}
		
		
		for (int i = 0; i < y.length; i++) {

			for (int j = i + 1; j < y.length; j++) {
				int dx = (x[i] - x[j]);
				int dy = (y[i] - y[j]);

				double dist = Math.sqrt(dx * dx + dy * dy);
				if (dist <= 2 * r) {
					id[j] = id[i];

				}
			}

		}
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < id.length; i++) 
			set.add(id[i]);
		
		System.out.println(set.size());
	}
}
