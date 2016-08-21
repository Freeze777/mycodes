package algo.binary.search;

public class FindPeakinIncreasingThenDecreasing {
	
	public static void main(String[] args) {
		int[] A={1,11,23,150,148,147};
		int pos=findPeak(A,0,A.length-1);
		System.out.println(A[pos]);
	}

	public static int findPeak(int[] A, int low, int high) {
		int mid=(low+high)/2;
		if(A[mid]>A[mid+1]&&A[mid]>A[mid-1])
			return mid;
		else if(A[mid]<A[mid+1])
			return findPeak(A,mid+1, high);
		else
			return findPeak(A, low, mid-1);
	}

}
