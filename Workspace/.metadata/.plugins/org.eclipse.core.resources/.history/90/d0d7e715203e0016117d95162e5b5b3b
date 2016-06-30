package algo.matrix;

public class PrintMatrixSpirally {
	public static void main(String[] args) {
		int[][] A = { { 1, 2, 3, 4, 5, 6 }, { 7, 8, 9, 10, 11, 12 },
				{ 13, 14, 15, 16, 17, 18 } };
		printspiralOrder(A);

	}

	public static void printspiralOrder(int[][] A) {
		int top = 0;
		int bottom = A.length - 1;// #rows
		int left = 0;
		int right = A[0].length - 1;// #cols
		while (true) {
			//printing left to right the top row
			for (int i = left; i <= right; i++)
				System.out.print(A[top][i] + " ");
			top++;
			if(top > bottom || left > right)
				break;
			//printing top to down rightmost column
			for (int i = top; i <= bottom; i++)
				System.out.print(A[i][right] + " ");
			right--;
			if(top > bottom || left > right)
				break;
			//printing right to left the bottom row
			for (int i = right; i >= left; i--)
				System.out.print(A[bottom][i] + " ");
			bottom--;
			if(top > bottom || left > right)
				break;
			//printing bottom to top the leftmost column
			for (int i = bottom; i >= top; i--)
				System.out.print(A[i][left] + " ");
			left++;
			if(top > bottom || left > right)
				break;
		}

	}
}
