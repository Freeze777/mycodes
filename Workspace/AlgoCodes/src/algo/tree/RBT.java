package algo.tree;



import java.util.Scanner;


class Node {
	Long data;

	Node left, right, parent;
	Long noOfNodes;
	char color;
	static final char COLOR[] = { 'R', 'B' };
	static final int BLACK = 1;
	static final int RED = 0;

	public Node(Long data, Node left, Node right, Node parent, Long noOfNodes) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
		this.noOfNodes = noOfNodes;
		this.color = RED;
	}

	public Node(Long data, Node left, Node right) {
		this(data, left, right, null, 1L);
	}

	public Node(Long data) {
		this(data, null, null, null, 1L);
	}

}

public class RBT {

	private Node root;
	private int size;
	static final char BLACK = 1;
	static final char RED = 0;
	private static Node nullNode;

	/* static initializer for nullNode */

	static
	{
		nullNode = new Node(-1L);
		nullNode.left = nullNode;
		nullNode.right = nullNode;
		nullNode.parent=nullNode;
		nullNode.color=BLACK;
		nullNode.noOfNodes=0L;
	}

	public RBT() {
		root = null;
		size = 0;
	}

	private int compare(Long x, Long y) {
		return x.compareTo(y);

	}

	public int getSize() {
		return size;
	}


	public Node search(Long toSearch) {
		return search(root, toSearch);
	}

	private Node search(Node p, Long toSearch) {

		if(p==nullNode)
			return nullNode;			
		else if (compare(toSearch, p.data) == 0)
			return p;
		else if (compare(toSearch, p.data) < 0)
			return search(p.left, toSearch);
		else
			return search(p.right, toSearch);
	}
	public void inOrderTraversal() {
		inOrderHelper(root);
	}

	private void inOrderHelper(Node r) {
		if(r==nullNode )
			return;
		inOrderHelper(r.left);
		System.out.println(r.data);		
		inOrderHelper(r.right);

	}
	private void restoreRBTProperty(Node child) {
	//	System.out.println("In restoreProperty");
		while (child != root && child.color == BLACK) {
			//System.out.println("Entering with double black problem at child["+child.data+","+Node.COLOR[child.color]+"]");
			if (child == child.parent.left) {
				//DB node is the left child of parent--->sibling is the right child
				//System.out.println("sibling at the right");
				Node sibling = child.parent.right;  
				if (sibling.color == RED) {
					//CASE 1:##Sibling is red##-.parent is black and SL and SR are black
				//	System.out.println("CASE 1:RED sibling:Moving to CASE 2 or 3 after flipcolor");
					sibling.color = BLACK;	//flip sibling color with parent and left rotate at parent -.moves the violation down to parent with a new black sibling SL
					child.parent.color = RED;
					moveChildLeft(child.parent);
					sibling = child.parent.right;
				}
				if (sibling.left.color == BLACK && sibling.right.color == BLACK) {//CASE 2:##S,SR,SL ARE BLACK##
				//	System.out.println("CASE 3a or 3b:S,SL,SR=black and P="+Node.COLOR[child.parent.color]);
					sibling.color = RED;					//both sibling childs SL SR are black and sibling color is always black at here
					child = child.parent;					//parent color?? can be anything despite of parents color Sibling has to be made red always
				} 							//if parent was black we are done else DB propagates upward
				else {//one of the sibling child is red		
					if (sibling.right.color == BLACK) {	//SL is	 red-.	double rotation needed since sibling is right child		
					//	System.out.println("CASE 2a:R-L :Sibling has a left red child..!!(terminal case):Moving to CASE 2b");
						sibling.left.color = BLACK;
						sibling.color = RED;
						moveChildRight(sibling);
						sibling = child.parent.right;
					}
					//System.out.println("CASE 2b:R-R :Sibling has a right red child..!!(terminal case)");
					sibling.color = child.parent.color;
					child.parent.color = BLACK;
					sibling.right.color = BLACK;
					moveChildLeft(child.parent);
					child = root;
				}
			} else {
				Node sibling = child.parent.left;
				//System.out.println("sibling at the left");
				if (sibling.color == RED) {
					//System.out.println("CASE 1:RED sibling:Moving to CASE 2 or 3 after flipcolor");
					sibling.color = BLACK;
					child.parent.color = RED;
					moveChildRight(child.parent);
					sibling = child.parent.left;//change sibling and its color is black
				}
				if (sibling.right.color == BLACK && sibling.left.color == BLACK) {
					//System.out.println("CASE 3a or 3b:S,SL,SR=black and P="+Node.COLOR[child.parent.color]);
					sibling.color = RED;
					child = child.parent;
					//potential bug---> child can be nullNode and nullNode is a static variable(it is just a single object for all)
					//therefore nullNode.parent changes with insertions and deletions.
					//to handle P=R and P=B case the starting while has a child color check and after exiting child color is made black
				} else {

					if (sibling.left.color == BLACK) {
					//	System.out.println("CASE 2a:L-R :Sibling has a right red child..!!(terminal case):Moving to CASE 2b");
						sibling.right.color = BLACK;
						sibling.color = RED;
						moveChildLeft(sibling);
						sibling = child.parent.left;
					}
					//System.out.println("CASE 2b:L-L :Sibling has a left red child..!!(terminal case)");
					sibling.color = child.parent.color;
					child.parent.color = BLACK;
					sibling.left.color = BLACK;
					moveChildRight(child.parent);
					child = root;
				}
			}
		}
		child.color = BLACK;//deals with finishing off case:3 recolorings and also recoloring if node deleted was black with red child   
		//System.out.println("exiting restore property");
	}
	private void checkRBTViolations(Node child) {

		while (child!= root && child.parent.color== RED) {
			/* we have a violation */

			//System.out.println("RED-RED violation at (parent,child) ("+child.parent.data+","+child.data+")");
			if (child.parent == child.parent.parent.left) {
				//PARENT IS THE LEFT CHILD OF GRANDPA
				Node uncle = child.parent.parent.right;
				if (uncle.color == RED) {
					//System.out.println("RED UNCLE PROBLEM");
					/* uncle is RED */
					child.parent.color = BLACK;
					uncle.color = BLACK;
					child.parent.parent.color = RED;
					child = child.parent.parent;//to check violations at grandpa 
				} else {
					//System.out.println("BLACK UNCLE PROBLEM");
					/* uncle is BLACK */
				//	System.out.println("LEFT-RIGHT CASE at (parent,child) ("+child.parent.data+","+child.data+")");
					if (child == child.parent.right) {
						/* make x a left child */
						child = child.parent;//escalate problem to parent
						moveChildLeft(child);
					}

					/* recolor and rotate */
					child.parent.color = BLACK;
					child.parent.parent.color = RED;
					moveChildRight(child.parent.parent);
				}
			} else {

				/* mirror image of above code */
				//PARENT IS THE RIGHT CHILD OF GRANDPA
				Node uncle = child.parent.parent.left;
				if (uncle.color == RED) {

					/* uncle is RED */
					//System.out.println("RED UNCLE PROBLEM");
					child.parent.color = BLACK;
					uncle.color = BLACK;
					child.parent.parent.color = RED;
					child = child.parent.parent;//check violations at grandpa
				} else {

					/* uncle is BLACK */
					//System.out.println("BLACK UNCLE PROBLEM");
					if (child == child.parent.left) {
					//	System.out.println("RIGHT LEFT CASE at (parent,child) ("+child.parent.data+","+child.data+")");
						child = child.parent;
						moveChildRight(child);
					}
					child.parent.color = BLACK;
					child.parent.parent.color = RED;
					moveChildLeft(child.parent.parent);
				}
			}
		}
	}

	private void moveChildLeft(Node parent) {

		Node child = parent.right;

		/* establish parent.right link */
		parent.right = child.left;
		//checking for leaf childs of child
		if (child.left != nullNode) 
			child.left.parent = parent;

		/* establish child.parent link */
		//checking for leaf child
		if (child!=nullNode)
			child.parent = parent.parent;
		//establishing link back to the tree
		if (parent.parent!=null) {
			if (parent == parent.parent.left)
				parent.parent.left = child;
			else
				parent.parent.right = child;
		} else {
			root = child;
		}

		/* link child and parent */
		child.left = parent;

		if (parent != null){
			parent.parent = child;
			parent.noOfNodes=1+parent.left.noOfNodes+parent.right.noOfNodes;
			//System.out.println("updating hieght in left rotate at "+parent.data+" to "+parent.noOfNodes);
		}

		child.noOfNodes=1+child.left.noOfNodes+child.right.noOfNodes;
		//System.out.println("updating hieght in left rotate at "+child.data+" to "+child.noOfNodes);
	}

	private void moveChildRight(Node parent) {

		Node child = parent.left;
		/* establish parent.left link */
		parent.left = child.right;
		if (child.right != nullNode)
			child.right.parent = parent;

		/* establish child.parent link */
		if (child!=nullNode)
			child.parent = parent.parent;
		if (parent.parent!=null) {
			if (parent == parent.parent.right)
				parent.parent.right = child;
			else
				parent.parent.left = child;
		} else {
			root = child;
		}

		/* link parent and child */
		child.right = parent;

		if (parent != null){
			parent.parent = child;
			parent.noOfNodes=1+parent.left.noOfNodes+parent.right.noOfNodes;
		//	System.out.println("updating hieght in left rotate at "+parent.data+" to "+parent.noOfNodes);
		}

		child.noOfNodes=1+child.left.noOfNodes+child.right.noOfNodes;
		//System.out.println("updating hieght in right rotate at "+child.data+" to "+child.noOfNodes);
	}

	public Node insert(Long data) {
		//System.out.println("inserting ..."+data);
		Node parent=null,current=root,child;

		while(current!=null&&current!=nullNode)
		{	parent=current;

		if(data>current.data)
		{current=current.right;
		//System.out.println("Taking right at "+parent.data);
		}
		else if(data<current.data)
		{current=current.left;
		//System.out.println("Taking left at "+parent.data);
		}
		else
		{
		System.out.println("Element already present:"+data);
		return current;//already present

		}
		}

		child=new Node(data,nullNode,nullNode);
		child.parent=parent;
		if(parent!=null){
			if(data>parent.data)
				parent.right=child;
			else
				parent.left=child;
		}else{
			root=child;
		}

		checkRBTViolations(child);

		root.color=BLACK;
		Node x=child;
		while(x!=null){
			x.noOfNodes=x.left.noOfNodes+x.right.noOfNodes+1;
			//System.out.println("updating hieght in insert at "+x.data+" to "+x.noOfNodes);
			x=x.parent;
		}
		return child;
	}
	public void delete(Long val) {
		Node node=search(val);
		Node del,child;
		//System.out.println("deleting ...."+val);
		if ((node==nullNode)) {
		//	System.out.println("element not in the tree...returning");
			return;
		}
		//if the node to be deleted has has only one child..hold it

		if(node.left==nullNode||node.right==nullNode)
		{del=node;
		//System.out.println("node to be deleted has a leaf node");
		}
		else
		{	del=node.right;
		//System.out.println("finding inorder successor");
		while(del.left!=nullNode)del=del.left;//get inorder successor
		}
		if(del.left!=nullNode)
		{	//System.out.println("node to be deleted has left child only");
		child=del.left;
		}
		else if(del.right!=nullNode)
		{	//System.out.println("node to be deleted has right child only");
		child=del.right;
		}else
		{//System.out.println("node to be deleted has two leaves");
		child=del.right;
		}

		child.parent=del.parent;
		if(del.parent!=null)
		{	
			if(del.parent.left==del)
				del.parent.left=child;
			else
				del.parent.right=child;

		}else
		{
			root=child;
		}
		if (node !=del) node.data = del.data;

		//if node to be deleted was red... then we are lucky... NO VIOLATIONS
		Node x=child.parent;
		if (del.color==BLACK) 
			restoreRBTProperty(child);
		else
		{//	System.out.println("deleted node was red..no violations");
		}
		while(x!=null)
		{

			x.noOfNodes=x.left.noOfNodes+x.right.noOfNodes+1;
			//System.out.println("updating hieght in insert at "+x.data+" to "+x.noOfNodes);
			x=x.parent;
		}
	}

	private Node SelectIthElement(long rank) {
		// TODO Auto-generated method stub
		if(rank<=0||rank>root.noOfNodes)
			return null;
		Node r=root;
		nullNode.noOfNodes=0L;
		while(r!=nullNode&&rank>0)
		{	//rank of r is NN(r.right)+1
			long rankOfCurrentNode=r.right.noOfNodes+1;
			if(rankOfCurrentNode==rank)
			{	return r;
			}
			else if (rankOfCurrentNode>rank) 
			{r=r.right;
			}
			else
			{	rank=rank-rankOfCurrentNode;
			r=r.left;

			}
		}	
		return null;
	}

	private long getElementsRank(long val) {
		Node p=root;
		long rank=0L;
		while(p!=nullNode){
			if (val==p.data)
			{	
				rank+=p.right.noOfNodes;
				break;

			}
			else if (val>p.data)
			{	p=p.right;
			}
			else
			{	rank+=p.right.noOfNodes+1;
			p=p.left;

			}
		}
		return rank+1;
	}

	public static void main(String[] args) {
		RBT bst = new RBT();
		Scanner sc=new Scanner(System.in);
		sc.nextLong();		
		do{
			
			int command=(int)sc.nextLong();
			Long val=sc.nextLong();
			switch (command) {
			case 0:Node t=bst.search(val);
			System.out.println(t.data.equals(val));
			break;
			case 1:bst.insert(val);
			break;
			case 2:bst.delete(val);
			break;
			case 3:
				System.out.println(bst.getElementsRank(val));
				break;
			case 4:
				Node k=bst.SelectIthElement(val);
				if(k!=null)
				System.out.println(k.data);
				else
				System.out.println("Invalid Rank");
				break;
			}		

		}while(sc.hasNextLong());
		//System.out.println(bst.size());	
		//bst.inOrderTraversal();
	}

	private long size() {
		// TODO Auto-generated method stub
		return root.noOfNodes;
	}


}
