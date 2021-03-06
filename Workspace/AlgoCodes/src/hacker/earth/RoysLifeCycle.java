package hacker.earth;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RoysLifeCycle {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int n = Integer.parseInt(line);
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < n; i++) {
			s.append(br.readLine());
		}
		int len = 1440;
		int max1 = 0, max2 = 0;
		for (int i = 0; i < s.length();) {
			int t = i;
			while (i < s.length() && s.charAt(i) == 'C')
				i++;
			max2 = Math.max(max2, (i - t));
			i++;
		}
		for (int i = 0; i < n; i++) {
			int start = i * len;
			int end = (i + 1) * len;
			for (int j = start; j < end;) {
				int t = j;
				while (j < end && s.charAt(j) == 'C')
					j++;
				max1 = Math.max(max1, (j - t));
				j++;
			}
		}
		System.out.println(max1+" "+max2);
	}
}
