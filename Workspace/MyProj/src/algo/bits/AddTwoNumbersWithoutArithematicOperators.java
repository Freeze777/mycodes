package algo.bits;

public class AddTwoNumbersWithoutArithematicOperators {
	public static int getSum(int a, int b) {
		int mask = 1;
		while (mask != 0) {
			if ((b & mask) != 0) {// bug: wrote b&mask==1 instead..!! we are
									// shifting mask to left so this it cant be
									// 1 olways.. its can be mask or 0
				int t = mask;
				while ((a & t) != 0) {// bug: same thing as above it a&t can be
										// t or 0
					a = a ^ t;
					t = t << 1;
				}
				a = a ^ t;
			}
			mask = mask << 1;
		}
		return a;
	}

	public static void main(String[] args) {
		System.out.println(getSum(7, 31));
		System.out.println(getSum(-99, 1012));
		System.out.println(getSum(-991, -803));
		System.out.println(getSum(991, 77));
	}

}
