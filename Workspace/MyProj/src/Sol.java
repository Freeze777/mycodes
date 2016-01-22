import java.util.Arrays;

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Sol{
	
    public int solution(int[] A) {
        // write your code in Java SE 8
    	
    	Arrays.sort(A);
    	int max=Integer.MIN_VALUE;
    	int[] B=new int[A.length-1];
    	for (int i = 0; i < B.length; i++) {
			B[i]=A[i+1]-A[i]-1;
			if(B[i]>max)
				max=B[i];
		}
    	//int max=A[A.length-1];
    	
    	return max/2;
    }
}