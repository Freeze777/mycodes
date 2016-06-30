package algo.sort;

import java.util.Scanner;

public class QuickSort {
	static int temp;

	public static void main(String[] args) {
		// int[] A = { 7, 2, 6, 2, 8, 0, 1, 5 };
		int[] A = { 5, 7, 11, 116, 3, 6, 10, 12, 6, 12 };
		/*
		 * Scanner sc = new Scanner(System.in); int n = sc.nextInt(); int A[] =
		 * new int[n]; for (int i = 0; i < n; i++) { A[i] = sc.nextInt(); }
		 */
		quicksort(A, 0, A.length - 1);

	}

	public static void quicksort(int[] A, int first, int last) {

		if (last > first) {
			// int pivot = getPivot(A, first, last);
			// int pIndex = partition(A, first, last - 1, pivot);
			int pIndex = partition(A, first, last);
			for (int i = 0; i < A.length; i++) {
				System.out.print(A[i] + " ");
			}
			System.out.println();
			quicksort(A, first, pIndex - 1);
			quicksort(A, pIndex + 1, last);

		}
	}

	public static int partition(int numbers[], int lhs, int rhs) {
		int pivot = numbers[(rhs + lhs) / 2];
		int lIndex = lhs - 1;
		int rIndex = rhs + 1;

		while (true) {
			while (numbers[++lIndex] < pivot)
				;
			while (numbers[--rIndex] > pivot)
				;

			if (lIndex > rIndex)
				break;

			int temp = numbers[lIndex];
			numbers[lIndex] = numbers[rIndex];
			numbers[rIndex] = temp;
		}

		return lIndex;
	}

	public static int partition(int[] A, int first, int last, int pivot) {
		int pivotPos = last + 1;
		while (first <= last) { // Fuck....!!! i was totally fucked up for not
								// putting the equality.!Think what happens when
								// we call with 2 elements
			while (first < pivotPos && A[first] <= pivot) {
				first++;
			}
			while (last >= 0 && A[last] > pivot) {
				last--;
			}
			if (first < last) {
				temp = A[first];
				A[first++] = A[last];
				A[last--] = temp;
			}

		}

		temp = A[pivotPos];
		A[pivotPos] = A[first];
		A[first] = temp;
		
		return first;
	}

	public static int getPivot(int[] A, int first, int last) {
		// TODO Auto-generated method stub
		// random pivot
		/*
		 * int index=(((int)Math.random()*100)%(last-first+1))+first;
		 * temp=A[last]; A[last]=A[index]; A[index]=temp;
		 */
		return A[last];
	}

}
