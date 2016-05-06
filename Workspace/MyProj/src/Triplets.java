import java.util.Arrays;
import java.util.Scanner;

public class Triplets {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int d = sc.nextInt();
		int count = 0;
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		for (int i = 0; i < arr.length;i++) {
			int a=arr[i];
			int b=a+d;
			
			if(Arrays.binarySearch(arr,b)>0)
			{	int c=b+d;
				if(Arrays.binarySearch(arr,c)>0)
					count++;
			}
		}
		
		
		System.out.println(count);

	}

}
