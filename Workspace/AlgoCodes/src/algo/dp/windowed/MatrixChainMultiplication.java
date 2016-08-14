package algo.dp.windowed;

class Matrix {
	int m, n;

	public Matrix(int m, int n) {
		this.m = m;
		this.n = n;
	}
}

public class MatrixChainMultiplication {
	public static void main(String[] args) {
		Matrix[] mat = new Matrix[4];
		mat[0] = new Matrix(2, 3);
		mat[1] = new Matrix(3, 6);
		mat[2] = new Matrix(6, 4);
		mat[3] = new Matrix(4, 5);

	}
}
