package hacker.rank;
import java.util.Scanner;

public class XORGame {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		long tst = sc.nextLong();
		while (tst > 0) {
			long l = sc.nextLong();
			long r = sc.nextLong();
			long mask =3l;
			long sum = 0l;
			switch ((int)(l & mask)) {
			case 1:
				sum = findSum(1, l, r);

				break;
			case 2:
				sum = findSum(l + 1, l, r);

				break;
			case 3:
				sum = findSum(0, l, r);

				break;
			case 0:
				sum = findSum(l, l, r);

				break;

			}
			System.out.println("\n"+sum);
			tst--;
		}

	}

	private static long findSum(long i, long l, long r) {
		long sum = 0;
		while (l<=r) {
			sum ^= i;
			i =i^(++l);
			
		}
		return sum;
	}

}