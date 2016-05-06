package hacker.rank;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BagOfApples {

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		ArrayList<Integer> A = new ArrayList<Integer>();
		int i = 1;
		while (i <= n) {
			A.add(sc.nextInt());
			i++;
		}
		int result = 0;

		for (Iterator<Integer> iterator = A.iterator(); iterator.hasNext();) {
			Integer x = (Integer) iterator.next();
			if (x % 3 == 0) {
				result += x;
				iterator.remove();
			}
		}
		// System.out.println(result);
		if (A.size() != 0) {
			Collections.sort(A);


			//for (Integer integer : A) { System.out.print(integer+" "); }

			while (A.size() > 1) {
				i = A.size();
				i--;
				int x = A.get(i), y = A.get(i - 1),z=0;
				//System.out.println(i);
				if(i==1)
				{
					if ((x + y) % 3 == 0) {
						result += x + y;
						A.remove(i);
						A.remove(i - 1);
					}
					break;

				}	else
				{
					z=A.get(i - 2);
				}


				if ((x + y) % 3 == 0) {
					result += x + y;
					A.remove(i);
					A.remove(i - 1);

				} else if ((x + y + z) % 3 == 0) {
					result += x + y + z;
					A.remove(i);
					A.remove(i - 1);
					A.remove(i - 2);
				} else if ((x + z) % 3 == 0) {
					result += x + z;
					A.remove(i);
					A.remove(i - 2);
				}
			}
			System.out.println(result);
		} else {
			System.out.println(result);
		}

	}
}