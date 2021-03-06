package hacker.rank.project.euler;
import java.util.Scanner;

public class LargestProductInSeries {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();

		while (tst > 0) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			long curr = 1, max = 0;
			String str = sc.next().trim();
			long[] nums = new long[str.length()];
			int i = 0;
			int zero_pos = -1;
			for (char ch : str.toCharArray()) {
				nums[i] = (ch - '0');
				if (nums[i] == 0)
					zero_pos = i;
				i++;
			}

			int x = (zero_pos < k) ? zero_pos + 1 : 0;
			while (x < nums.length - k) {
				int temp = 0;
				while (curr != 0 && temp < k) {
					curr *= nums[x + temp];

					if (nums[x + temp] == 0)
						zero_pos = x + temp;

					temp++;
				}
				if (curr == 0) {
					x = zero_pos + 1;
					curr = 1;
					continue;
				} else {
					max = (max < curr) ? curr : max;
					curr = 1;

				}

				x++;
			}
			System.out.println(max);
			tst--;
		}

	}
}