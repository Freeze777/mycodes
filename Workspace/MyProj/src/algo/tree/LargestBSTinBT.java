package algo.tree;
import java.util.*;

class ReturnObj{
	boolean isBST;
	int max;
	int min;
	int bstSize;
	public ReturnObj(){
		isBST=true;
		max=Integer.MIN_VALUE;
		min=Integer.MAX_VALUE;
		bstSize=0;
	
	}
}
public class LargestBSTinBT{


	public static void main(String[] args){
		TreeNode root = new TreeNode(50);
  		root.left =new TreeNode(10);
  		root.right =new TreeNode(60);
  		root.left.left  =new TreeNode(5);
  		root.left.right =new TreeNode(20);
  		root.right.left  =new TreeNode(55);
 		root.right.left.left  =new TreeNode(45);
		root.right.right =new TreeNode(70);
		root.right.right.left =new TreeNode(65);
		root.right.right.right =new TreeNode(80);


		ReturnObj obj= findLargestBSTinBT(root);
		System.out.println(obj.bstSize);
	}

	public static ReturnObj findLargestBSTinBT(TreeNode root){
		ReturnObj obj=new ReturnObj();

		if(root==null)
			return obj;
			
		ReturnObj leftObj=findLargestBSTinBT(root.left);
		ReturnObj rightObj=findLargestBSTinBT(root.right);

		obj.isBST=(leftObj.isBST &&	rightObj.isBST) && (root.data>leftObj.max) && (root.data<=rightObj.min);
		if(obj.isBST)
			{	//handle the leaf nodes carefully;
				obj.max=(root.right==null)?root.data:rightObj.max;
				obj.min=(root.left==null)?root.data:leftObj.min;
				obj.bstSize=leftObj.bstSize+rightObj.bstSize+1;
				return obj;
			}
		//subtree rooted at root is not a BST
		//pass the current BST size under this root
		obj.isBST=false;
		obj.bstSize=Math.max(rightObj.bstSize,leftObj.bstSize);
		return obj;



	}
}