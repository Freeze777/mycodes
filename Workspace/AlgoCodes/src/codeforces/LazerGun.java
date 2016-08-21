package codeforces;
import java.util.*;

public class LazerGun {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Set<Double> slopes = new HashSet<Double>();
		int n = sc.nextInt();
		int x0 = sc.nextInt();
		int y0 = sc.nextInt();
		for (int i = 0; i < n; i++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			if(x1!=x0)
				slopes.add((double)(y1-y0)/(x1-x0));
			else
				slopes.add(Double.MAX_VALUE);
		}
		System.out.println(slopes.size());

	}

}
