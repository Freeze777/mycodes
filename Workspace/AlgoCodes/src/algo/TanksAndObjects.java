package algo;
/*http://www.geeksforgeeks.org/walmart-labs-interview-experience/*/
import java.util.Scanner;

class Point {
	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class TanksAndObjects {
	public static char getDirection(int i, Point[] t, Point[] o) {
		char c = 0;
		boolean e = false, n = false, w = false, s = false;
		Point tank = t[i];
		for (int j = 0; j < o.length; j++) {
			if (tank.x == o[j].x) {
				if (tank.y > o[j].y)
					s = true;
				else
					n = true;
			} else if (tank.y == o[j].y) {
				if (tank.x > o[j].x)
					w = true;
				else
					e = true;
			}
		}
		for (int j = 0; j < t.length; j++) {
			if (j != i) {
				if (tank.x == t[j].x) {
					if (tank.y > t[j].y)
						s = true;
					else
						n = true;
				} else if (tank.y == t[j].y) {
					if (tank.x > t[j].x)
						e = true;
					else
						w = true;
				}
			}
		}

		if (!e)
			c = 'E';
		else if (!n)
			c = 'N';
		else if (!s)
			c = 'S';
		else if (!w)
			c = 'W';

		return c;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		Point[] t = new Point[n];
		Point[] o = new Point[m];
		for (int i = 0; i < t.length; i++)
			t[i] = new Point(sc.nextInt(), sc.nextInt());

		for (int i = 0; i < o.length; i++)
			o[i] = new Point(sc.nextInt(), sc.nextInt());

		for (int i = 0; i < t.length; i++) {
			char c = getDirection(i, t, o);
			System.out.println("Tank " + (i + 1) + ":" + c);
		}

	}
}
