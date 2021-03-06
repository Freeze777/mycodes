package algo.arrays;


/*https://www.nayuki.io/page/next-lexicographical-permutation-algorithm*/
public class NextPermutation {

	public static void main(String[] args) {
		// String s = "1234";
		 String s = "4321";
		//String s = "1254322221";
		char[] arr = s.toCharArray();
		findNextPermutation(arr);
		
		System.out.println(String.valueOf(arr));

	}

	private static void findNextPermutation(char[] arr) {
		int i;
		/*
		 * finding pivot point pivot = starting point of weakly decreasing
		 * suffix(i.e suffix is sorted..!). Why finding suffix?? To get the
		 * 'next' permuatation we need to make changes as far away from the
		 * begining of the string. The pivot point is the place where we are far
		 * away from the begining of the array and we have an element on its
		 * right for replacement. After replacement we can further reduce the
		 * string by reversing the modified sufix making it weakly increasing
		 */
		for (i = arr.length - 1; (i >= 1) && (arr[i] <= arr[i - 1]); i--)
			;
		int pivot = i - 1;
		//System.out.println(pivot);
		if (pivot >= 0) {
			/* finding element just greater than A[pivot] */
			for (i = arr.length - 1; (i >pivot) && (arr[i] <= arr[pivot]); i--)
				;
			
			char temp = arr[i];
			arr[i] = arr[pivot];
			arr[pivot] = temp;
			/*for (int x = pivot + 1, y = arr.length - 1; x < y; x++, y--) {
			temp = arr[x];
			arr[x] = arr[y];
			arr[y] = temp;
		}*/

		}/*else{
			//when there is next greater permutation and no cycle back reqd
			return;
		}
		*/
		//to cycle back "4321"--->"1234"
		for (int x = pivot + 1, y = arr.length - 1; x < y; x++, y--) {
			char temp = arr[x];
			arr[x] = arr[y];
			arr[y] = temp;
		}
		
	}

}
