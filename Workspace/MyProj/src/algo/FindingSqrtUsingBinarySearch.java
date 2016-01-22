package algo;

public class FindingSqrtUsingBinarySearch {

public static void main(String[] args) {
	
	int key=729;
	int high=key;
	int low=0;
	System.out.println(sqrtBinSearch(key,low,high));
}

private static int sqrtBinSearch(int key, int low, int high) {

	int mid =(low+high)/2;
	int sqr=mid*mid;
	if(low>high)
		return mid;
	if(sqr>key)
		return sqrtBinSearch(key, low, mid-1);
	else if(sqr<key)
		return sqrtBinSearch(key, mid+1,high);
	else
		return mid;
	
	
}
}
