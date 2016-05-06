package hacker.rank;

import java.util.Arrays;

public class MergeSort {
static int count=0;
	public static void main(String[] args) {
		//int[] A = { 7, 2, 6, 2, 8, 0, 1, 5 };//power of 2 => symmetric
		//int[] A={5,3,0,7,6,1}; //not a power of 2 =>assymmetric
		count=0;
	//	mergeSort(A, 0, A.length - 1);

	}

	public static void mergeSort(int[] A, int first, int last) {
		//System.out.println("Entering with First=" + first + " last=" + last);
		//float[] cc={1,4,6,3};
		count++;
		//System.out.println(count);
		if (last >first) {

			int mid = (last + first) / 2;
			//System.out.println(" Mid=" + mid);
			mergeSort(A, first, mid);
			mergeSort(A, mid + 1, last);
			merge(A, first, mid, last);
		//	for (int i = 0; i < A.length; i++)
				//System.out.print(A[i] + " ");
			//System.out.println();

		}

		//System.out.println("Leaving with First=" + first + " last=" + last);
	}

	public static void merge(int[] A, int first, int mid, int last) {
		//System.out.println("Calling merge with First=" + first + " last="
				//+ last + " Mid=" + mid);
		int[] L = new int[mid - first + 1];
		int[] R = new int[last - mid];
		int n1 = mid - first + 1;
		int n2 = last - mid;
		int i = 0, j = 0, k = 0;
		for (i = first, j = 0; i <= mid; i++, j++)
			L[j] = A[i];
		for (i = mid + 1, k = 0; i <= last; i++, k++)
			R[k] = A[i];

		j = k = 0;
		for (i = first; i <= last;) {
			if (k == n2)
				A[i++] = L[j++];
			else if (j == n1)
				A[i++] = R[k++];
			else if (L[j] < R[k])
				A[i++] = L[j++];
			else
				A[i++] = R[k++];

		}

	}

}
