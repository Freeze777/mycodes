package algo.tree;

public class CheckBalanceOfTree {
	public int height(TreeNode root, boolean[] flag) {
		if (root == null)
			return 0;
		int left = height(root.left, flag);
		int right = height(root.right, flag);
		if (Math.abs(left - right) > 1)
			flag[0] = false;
		return Math.max(left, right) + 1;
	}

	public boolean isBalanced(TreeNode root) {
		boolean[] flag = { true };
		height(root, flag);
		return flag[0];
	}
}