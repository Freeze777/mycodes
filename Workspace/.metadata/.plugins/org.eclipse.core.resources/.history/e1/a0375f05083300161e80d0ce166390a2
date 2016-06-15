package algo;

public class FindSecondMaximum {
	public static void main(String[] args) {
		int A[]={3,7,1,9,4,10,24,20,21,24};
		int res=getSecondMaximum(A);
		System.out.println(res);
	}

	public static int getSecondMaximum(int[] A) {
		int first=A[0];
		int sec=A[1];
		for (int i =1; i < A.length; i++) {
			if(A[i]>first)
			{	sec=first;
				first=A[i];
				
			}else if(A[i]>sec&&A[i]!=first)
			{
				sec=A[i];
			}
		}
		return sec;
	}

}
