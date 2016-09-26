import java.util.Scanner;

public class FibonnaciMatrixExponentiation {

	private static long a, b;	
	private static long[][] fibMatrix = { { 1, -1 }, { 1, 0 } };
	private static long getNthFibNum(long n, long m) {
		long[][] result = { { 1, 0 }, { 0, 1 } };
		
		while (n != 0) {

			if (n%2==1)
				moduloMatrixMul2x2(result, fibMatrix, m);

			long[][] temp = { { fibMatrix[0][0], fibMatrix[0][1] },
					{ fibMatrix[1][0], fibMatrix[1][1] } };
			moduloMatrixMul2x2(fibMatrix, temp, m);
			
			n/=2;

		}
		return ((b * result[1][0]) + (a * result[1][1])) % m;
	}

	private static int getFibPeriod(long m) {
		int k = 1;
		long[][] temp = { { 1, 0 }, { 0, 1 } };
		while (true) {
			moduloMatrixMul2x2(temp, fibMatrix, m);
			if (temp[0][0] == 1 && temp[0][1] == 0 && temp[1][0] == 0
					&& temp[1][1] == 1)
				break;
			k++;
		}
		return k;
	}

	private static void moduloMatrixMul2x2(long[][] A, long[][] B,long m) {
		long a00 = A[0][0], a01 = A[0][1], a10 = A[1][0], a11 = A[1][1];
		A[0][0] = (a00 * B[0][0] + a01 * B[1][0]) % m;
		A[0][1] = (a00 * B[0][1] + a01 * B[1][1]) % m;
		A[1][0] = (a10 * B[0][0] + a11 * B[1][0]) % m;
		A[1][1] = (a10 * B[0][1] + a11 * B[1][1]) % m;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		a = sc.nextLong();
		b = sc.nextLong();
		long n=sc.nextLong()-1;
		long m = 1000000007;
		long period = getFibPeriod(m);

		//System.out.println("Method 2:PERIODICITY");
		long nmod = n%period;
		long ans=getNthFibNum(nmod, m);
		System.out.println(ans>=0?ans:ans+m);

	}

}
