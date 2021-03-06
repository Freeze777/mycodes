package algo.string;
import java.io.*;
import java.util.*;


public class NextPermutationOfString {

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();
		while (tst > 0) {
			char[] array = sc.next().toCharArray();
			int i = array.length - 1;
			while (i > 0 && array[i - 1] >= array[i]) {
				i--;
			}
			if (i <= 0) {
				System.out.println("no answer");
				continue;
			}
			int j = array.length - 1;

			while (array[j] <= array[i - 1]) {
				j--;
			}

			char temp = array[i - 1];
			array[i - 1] = array[j];
			array[j] = temp;

			j = array.length - 1;

			while (i < j) {
				temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				i++;
				j--;
			}
			
			System.out.println(String.valueOf(array));
			tst--;
		}

	}
}