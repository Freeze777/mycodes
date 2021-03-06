package algo;

public class MultiplyWithOnlyAddOperator {
	public static void main(String[] args) {
		int a = 7323;
		int b = -7712;
		System.out.println(multiply_trivial(b, a));
		System.out.println(multiply_optimal(a, b));
		System.out.println((a*b));

	}

	private static int multiply_optimal(int a, int b) {
		if (a == 0 || b == 0)
			return 0;
		if (b == 1)
			return a;
		if ((b & 1) == 0)
			return multiply_optimal(a << 1, b >> 1);
		else
			return a + multiply_optimal(a << 1, b >> 1);

	}

	private static int multiply_trivial(int a, int b) {
		int min = Math.abs(Math.min(a, b));
		int max = Math.abs(Math.max(a, b));
		int sum = 0;
		for (int i = 0; i < min; i++)
			sum += max;

		int bit_mask = (1 << 31);
		return ((a & bit_mask) == (b & bit_mask)) ? sum : -sum;

	}

}
