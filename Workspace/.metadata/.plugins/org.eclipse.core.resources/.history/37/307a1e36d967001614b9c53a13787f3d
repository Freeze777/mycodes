package algo.arrays;

import java.util.Arrays;

public class RotateArrayByk {
	public static void main(String[] args) {
		int A[] = { 7, 8, 10, 3, 2 ,1};
		int k = 5;
		rotateArray_method1(A, k);
		System.out.println(Arrays.toString(A));

	}

	public static void rotateArray_method1(int[] A, int k) {
		reverse(A, 0, A.length - 1);
		reverse(A, 0, k - 1);
		reverse(A, k, A.length - 1);
	}

	public static void reverse(int[] A, int from, int to) {
		while (to > from) {
			
			int temp = A[to];
			A[to] = A[from];
			A[from] = temp;

			from++;
			to--;
		}

	}
	public static void rotateArray_method2(int[] A, int k) {

		for (int i = 0; i < k; i++)
			rotateByOneStep(A);

	}

	public static void rotateByOneStep(int[] A) {
		for (int j = A.length - 1; j > 0; j--) {
			
			int temp = A[j];
			A[j] = A[j - 1];
			A[j - 1] = temp;
		}
	}

}
