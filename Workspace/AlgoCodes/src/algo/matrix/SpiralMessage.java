package algo.matrix;
import java.io.*;
import java.util.*;
/*https://www.hackerrank.com/contests/ncr-codesprint/challenges/spiral-message*/
public class SpiralMessage {
   
   public static String printspiralOrder(char[][] A) {
      StringBuilder sb=new StringBuilder();
		int top = 0;
		int bottom = A.length - 1;// #rows
		int left = 0;
		int right = A[0].length - 1;// #cols
		while (true) {
         //printing bottom to top the leftmost column
			for (int i = bottom; i >= top; i--)
				sb.append(A[i][left]);
			left++;
			if(top > bottom || left > right)
				break;
			//printing left to right the top row
			for (int i = left; i <= right; i++)
				sb.append(A[top][i]);
			top++;
			if(top > bottom || left > right)
				break;
			//printing top to down rightmost column
			for (int i = top; i <= bottom; i++)
				sb.append(A[i][right]);
			right--;
			if(top > bottom || left > right)
				break;
			//printing right to left the bottom row
			for (int i = right; i >= left; i--)
				sb.append(A[bottom][i]);
			bottom--;
			if(top > bottom || left > right)
				break;
			
		}
      
      return sb.toString().replaceAll("#+"," ").trim();

}

    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
       int n=sc.nextInt();
       int m=sc.nextInt();
       char[][] matrix=new char[n][];
       for (int i = 0; i < matrix.length; i++) 
    	   matrix[i]=sc.next().toCharArray();
       String s=printspiralOrder(matrix);
       if(s.length()==0)
    	   System.out.println(0);
       else
    	   System.out.println(s.split(" ").length);
       
    }
}