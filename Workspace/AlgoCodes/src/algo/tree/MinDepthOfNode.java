package algo.tree;

/*tried to modify the hieght code from max to min and got fucked up
 * fails for     1
 * 				/ \
 * 			   2   null
 * */
public class MinDepthOfNode {
	public int minDepth(TreeNode root) {
		int[] min = { Integer.MAX_VALUE };
		minDepth(root, 1, min);
		return min[0] == Integer.MAX_VALUE ? 0 : min[0];
	}

	public void minDepth(TreeNode root, int depth, int[] min) {
		if (root == null)
			return;
		if (root.left == null && root.right == null) {
			min[0] = Math.min(min[0], depth);
			return;
		}
		minDepth(root.left, depth + 1, min);
		minDepth(root.right, depth + 1, min);
	}
}
