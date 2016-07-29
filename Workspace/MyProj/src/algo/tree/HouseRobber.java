package algo.tree;

/* The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
 Determine the maximum amount of money the thief can rob tonight without alerting the police. */
public class HouseRobber {
	public int rob(TreeNode root, boolean parentTaken) {
		if (root == null)
			return 0;
		int sum1 = 0, sum2 = 0;
		if (!parentTaken)
			sum1 = root.data + rob(root.left, true) + rob(root.right, true);
		sum2 = rob(root.left, false) + rob(root.right, false);
		return Math.max(sum1, sum2);
	}

	public int rob(TreeNode root) {
		return rob(root, false);
	}
}