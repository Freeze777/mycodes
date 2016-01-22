// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");
class Tree{
	
	public int x;
	public Tree l;
	public Tree r;
}
public class Solution {
	int count=0;
    public int solution(Tree T) {
        // write your code in Java SE 8
    	
    	solutionHelper(T,Integer.MIN_VALUE);
    	return count;
    }
	private void solutionHelper(Tree root, int max) {

		if(root==null)
			return;
		if(root.x >= max)
		{	count++;
			max=root.x;
		}
		solutionHelper(root.l, max);
		solutionHelper(root.r, max);
	}
}