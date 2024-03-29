package algo.sort;

import java.util.Arrays;
import java.util.Comparator;

class CustomComp implements Comparator<Integer> {
	@Override
	public int compare(Integer i1, Integer i2) {
		/*
		 * X and Y are two Integers.
		 * X should be preferred over Y if String(XY)>String(YX)
		 */
		return (i2+""+i1).compareTo(i1+""+i2);
	}

}

public class BigNumberFromArrayElements {
	public static void main(String[] args) {
		 Integer[] arr={1,34,3,98,9,76,45,4};//998764543431
		//Integer[] arr = { 54, 546, 548, 60 };// 6054854654
		String ans = getMaxNum(arr);
		System.out.println(ans);
	}

	public static String getMaxNum(Integer[] arr) {
		CustomComp myComp = new CustomComp();
		Arrays.sort(arr, myComp);
		StringBuilder sb = new StringBuilder();
		for (Integer i : arr)
			sb.append(i);
		return sb.toString();
	}

}
