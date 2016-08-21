package codeforces;
import java.util.*;
public class NumberOfWays {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] pref = new long[n];
		pref[0] = sc.nextInt();
		for (int i = 1; i < pref.length; i++) {
			pref[i] = sc.nextInt();
			pref[i] += pref[i - 1];
		}
		long sum = pref[n - 1];
		long ans=0;
		long count = 0;
		if (sum % 3 == 0) {
			for (int i = 0; i < pref.length - 1; i++) {
				if (pref[i] == (2 * (sum / 3)))
					ans+=count;
				if (pref[i] == (sum / 3))
					count++;
				
			}
		
		}
		System.out.println(ans);

	}

}
