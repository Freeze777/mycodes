package algo.tree;

public class SortedArrayToBBST {
	public static void main(String[] args) {
		int A[]={2,3,5,7,8,12,14,20,29};
		
		TreeNode root=sortedArrayToBBST(A,0,A.length-1);
		TreeUtility.printIOT(root);
	}

	public static TreeNode sortedArrayToBBST(int[] A, int low, int high) {
		if(low>high)
			return null;
		int mid=(low+high)/2;
		TreeNode root=new TreeNode(A[mid]);
		root.left=sortedArrayToBBST(A, low, mid-1);
		root.right=sortedArrayToBBST(A, mid+1, high);
		
		return root;
	}

}
