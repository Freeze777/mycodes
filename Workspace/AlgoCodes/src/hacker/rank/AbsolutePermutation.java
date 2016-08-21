package hacker.rank;
import java.io.*;
import java.util.*;

public class AbsolutePermutation {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();
		while (tst > 0) {
			int n = sc.nextInt();
			int k = sc.nextInt();
 
			boolean res = true;
			TreeSet<String> set = new TreeSet<String>();

			int pos = n;
			String num = "";
			findString(pos, k, n, set, num);
			if(set.size()==0)
				System.out.println(-1);
			else
				System.out.println(set.pollFirst());
				
			tst--;

		}	
	}

	private static void findString(int pos, int k, int n, TreeSet<String> set,
			String num) {
		int n1 = pos + k;
		int n2 = pos - k;
		if(pos==0)
			set.add(num);
		else if (n1 >= n + 1 && n2 >= 1)
			findString(pos-1, k, n, set, n2+" "+num);
		else if (n1 <= n && n2 <= 0)
			findString(pos-1, k, n, set, n1+" "+num);
		else if (n1 <= n && n2 >= 1)
		{	findString(pos-1, k, n, set, n2+" "+num);
			findString(pos-1, k, n, set, n1+" "+num);
		}
		

	}
}