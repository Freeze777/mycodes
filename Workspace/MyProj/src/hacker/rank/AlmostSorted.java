package hacker.rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlmostSorted {
	public static boolean checkArrayisSorted(int[] arr, int low, int high) {
		high=(high>=arr.length)?arr.length-1:high;
		low=(low<0)?0:low;
		boolean flag = true;
		for (int i = low; i < high; i++) {
			if (arr[i] > arr[i + 1]) {
				flag = false;
				break;
			}

		}
		return flag;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] arr = new int[n];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}

		List<Integer> peaks = new ArrayList<Integer>();
		List<Integer> dips = new ArrayList<Integer>();
		for (int i = 1; i < arr.length - 1; i++) {
			if (arr[i] > arr[i + 1] && arr[i] > arr[i - 1])
				peaks.add(i + 1);
			else if (arr[i] < arr[i + 1] && arr[i] < arr[i - 1])
				dips.add(i + 1);
		}

		if (arr[0] > arr[1])
			peaks.add(1);
		if (arr[arr.length - 1] < arr[arr.length - 2])
			dips.add(arr.length);

		if ((peaks.size() == dips.size())) {
			if (peaks.size() == 1) {

				int peak_index = peaks.get(0) - 1;
				int dip_index = dips.get(0) - 1;
				int temp = arr[dip_index];
				arr[dip_index] = arr[peak_index];
				arr[peak_index] = temp;

				int low = Math.min(dip_index, peak_index);
				int high = Math.max(dip_index, peak_index);
				if (checkArrayisSorted(arr, low - 1, high + 1))
					System.out.println("yes\nswap " + (low+1) + " " + (high+1));
				else {

					for (int i = low + 1, j = high - 1; i < j; i++, j--) {
						temp = arr[i];
						arr[i] = arr[j];
						arr[j] = temp;

					}

					if (checkArrayisSorted(arr, low - 1, high+1))
						System.out.println("yes\nreverse " + (low+1) + " " + (high+1));
					else
						System.out.println("no");
				}

			}else if(peaks.size()==2)
			{
				int peak_index = Math.min(peaks.get(0) - 1,peaks.get(1)-1);
				int dip_index =Math.max(dips.get(0) - 1,dips.get(1)-1);
				int temp = arr[dip_index];
				arr[dip_index] = arr[peak_index];
				arr[peak_index] = temp;
				if (checkArrayisSorted(arr, peak_index-1, dip_index+1))
					System.out.println("yes\nswap " + (peak_index+1) + " " + (dip_index+1));
				else
					System.out.println("no");
			}
			else if (peaks.size() == 0) {
				System.out.println("yes");
			}else
				System.out.println("no");
		} else if (peaks.size() == 1 && dips.size() == 0) {
			int peak_index=peaks.get(0)-1;
			for (int i = peak_index,j=arr.length-1; i < j; i++,j--) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
			if (checkArrayisSorted(arr, peak_index-1, arr.length-1))
				System.out.println("yes\nreverse " + (peak_index+1) + " " +arr.length);
			else
				System.out.println("no");

		} else if (dips.size() == 1 && peaks.size() == 0) {
			int dip_index=dips.get(0)-1;
			for (int i = 0,j=dip_index;i<j;i++,j--) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
			if (checkArrayisSorted(arr,0, dip_index+1))
				System.out.println("yes\nreverse " + 1 + " " +( dip_index+1));
			else
				System.out.println("no");

		}
		else
			System.out.println("no");

	}
}