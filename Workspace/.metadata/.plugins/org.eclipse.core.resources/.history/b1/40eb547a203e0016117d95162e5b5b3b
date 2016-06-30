package hacker.rank;

class Node {
	int freq;
	char data;
	Node left;
	Node right;

}

public class HuffmanDecoding {

	void decode(String S, Node root) {
		Node temp = root;
		StringBuilder result=new StringBuilder();
		for (int i = 0; i < S.length(); i++) {
			
			if (S.charAt(i) == '1')
				temp = temp.right;
			else
				temp = temp.left;
			
			if(temp.left==null && temp.right==null)
			{
				result.append(temp.data);
			}

		}
	System.out.println(result);
	}
}
