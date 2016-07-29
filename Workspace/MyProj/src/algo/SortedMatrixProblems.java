package algo;

import java.util.Arrays;

public class SortedMatrixProblems {
	public static void main(String[] args) {

		  int A[][] = { 	{10, 20, 30, 40},
		                    {15, 25, 35, 45},
		                    {27, 29, 37, 48},
		                    {32, 33, 39, 50},
		                  };
		System.out.println(searchMatrix(A,29));
		System.out.println(searchMatrix(A,90));
		System.out.println(searchMatrix(A,45));
		System.out.println(searchMatrix(A,21));
	}


    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0||matrix[0].length==0)
            return false;
        int i=0,j=matrix[0].length-1;
        boolean found=false;
        while(i>=0 && i<matrix.length && j>=0 && j<matrix[0].length && (!found)){
            if(target>matrix[i][j]){
                i++;
            }else if(target<matrix[i][j]){
                j--;
            }else{
                found=true;
            }
        }
        
        return found;
    }


}
