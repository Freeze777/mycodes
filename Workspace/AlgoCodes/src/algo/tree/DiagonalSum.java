package algo.tree;

import java.util.Map;
import java.util.TreeMap;

public class DiagonalSum {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);

		root.left = new TreeNode(10);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(17);
		root.left.left.left = new TreeNode(1);
		root.left.left.right = new TreeNode(2);

		root.right = new TreeNode(7);
		root.right.left = new TreeNode(8);
		root.right.right = new TreeNode(1);
		root.right.left.left = new TreeNode(3);
		root.right.left.right = new TreeNode(-10);
		Map<Integer, Integer> ans = new TreeMap<Integer, Integer>();
		getDiagSum(root, 0, ans);
		System.out.println(ans);
	}

	public static void getDiagSum(TreeNode root, int diagIdx,
			Map<Integer, Integer> ans) {
		if(root==null)
			return;
		ans.put(diagIdx,ans.get(diagIdx)==null?root.data:ans.get(diagIdx)+root.data);
		getDiagSum(root.left, diagIdx, ans);
		getDiagSum(root.right, diagIdx+1, ans);
	}

}
