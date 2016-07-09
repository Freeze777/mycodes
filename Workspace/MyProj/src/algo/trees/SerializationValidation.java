package algo.trees;

import java.util.Stack;

/*https://leetcode.com./problems/verify-preorder-serialization-of-a-binary-tree/*/
public class SerializationValidation {
	/*
	 * One way to serialize a binary tree is to use pre-order traversal. When we
	 * encounter a non-null node, we record the node's value. If it is a null
	 * node, we record using a sentinel value such as #.
	 * 
	 * eg: Input:"9,3,4,#,#,1,#,#,2,#,6,#,#"
	 * 
  	 _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
	 */
	public boolean isValidSerialization(String str) {
		if (str.length() == 0)
			return false;
		String[] preorder = str.split(",");//bug: dint noe input is , seperated string
		if (preorder.length == 1)
			return preorder[0].charAt(0) == '#';// empty tree
		if (preorder[0].charAt(0) == '#')// bug fix: failed for "###"
			return false;
		Stack<Integer> s = new Stack<Integer>();
		int[] count = new int[preorder.length];
		s.push(0);
		int i = 1;
		while (!s.isEmpty() && i < count.length) {
			count[s.peek()]++;
			if (count[s.peek()] == 2) {
				s.pop();
			}
			if (preorder[i].charAt(0) != '#') {
				s.push(i);
			}
			i++;
		}
		return (i == count.length) ? (s.size() == 0) : false;
	}

}
