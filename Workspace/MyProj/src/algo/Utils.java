package algo;
public class Utils {
	
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+",");
		}
		
	}
	public static void printArray(long[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+",");
		}
		
	}
	public static void printArray(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
			
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	public static void printArray(long[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
			
				System.out.print(arr[i][j]+",");
			}
			System.out.println();
		}
		
	}
}