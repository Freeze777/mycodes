import java.io.*;
import java.util.*;

public class CutRibbons_fail {
	static Map<Integer, Integer> dp = new HashMap<Integer, Integer>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		
		System.out.println( max_rib(n, a, b, c));
	}

	private static int max_rib(int n, int a, int b, int c) {
		 if (n==0)
		      return 0;
		 if (n<a && n<b && n<c)
		      return -1;
		 if(dp.containsKey(n))
		      return dp.get(n);
		int max=1+Math.max(max_rib(n-c,a,b,c),Math.max(max_rib(n-a,a,b,c),max_rib(n-b,a,b,c)));
		dp.put(n,max);
		return max;
	}
}