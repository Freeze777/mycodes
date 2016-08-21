package hacker.rank;
import java.util.ArrayList;
import java.util.Scanner;

public class MakeOurCustomersHappy {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int n = sc.nextInt();
		ArrayList<String> orders = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			orders.add(sc.next());
		}
		int odr = getMaxOrder(orders, 0, a, b, c);
		System.out.println(odr);
	}

	private static int getMaxOrder(ArrayList<String> orders, int i, int a,
			int b, int c) {
		if (i == orders.size())
			return 0;
		if (a == 0 && b == 0 && c == 0)
			return 0;
		String[] odr = orders.get(i).split(",");
		int ta = 0, tb = 0, tc = 0;
		for (int j = 0; j < odr.length; j++) {
			if (odr[j].equals("A"))
				ta++;
			else if (odr[j].equals("B"))
				tb++;
			else if (odr[j].equals("C"))
				tc++;
		}
		int o1 = 0, o2 = 0;
		if (ta <= a && tb <= b && tc<= c)
			o1 = 1 + getMaxOrder(orders, i + 1, a - ta, b - tb, c - tc);
		o2 = getMaxOrder(orders, i + 1, a, b, c);
		return Math.max(o1, o2);

	}

}
