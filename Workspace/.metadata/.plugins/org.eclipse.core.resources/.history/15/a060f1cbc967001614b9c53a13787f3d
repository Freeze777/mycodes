import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class NumbersWith1234 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int l = sc.nextInt();
		int r = sc.nextInt();
		int count = 0;
		for (int i = l; i <= r; i++) {
			count += isValid(i) ? 1 : 0;
		}
		System.out.println(count);
	}

	private static boolean isValid(int i) {
		if(i==0)
			return false;
		while (i != 0) {
			int r = i % 10;
			i = i / 10;
			if (!(r == 1 || r == 2 || r == 3 || r == 4))
				return false;
		}
		return true;
	}
}
