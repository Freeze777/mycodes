package algo.string;

import java.util.PriorityQueue;
import java.util.Queue;

class QNode implements Comparable<QNode> {
	char c;
	int idx;

	public QNode(char c, int idx) {
		this.c = c;
		this.idx = idx;
	}
	@Override
	public int compareTo(QNode obj) {
		if (this.c < obj.c)
			return 1;
		else if (this.c > obj.c)
			return -1;
		else
			return obj.idx - this.idx;
	}
	@Override
	public String toString() {
		return "QNode [c=" + c + ", idx=" + idx + "]";
	}
	
}

public class CreateBigNumberAtmostKSwaps {
	public static void main(String[] args) {
		int n=8799;
		int k=2;
		char[] num=Integer.toString(n).toCharArray();
		
		Queue<QNode> pq=new PriorityQueue<QNode>();
		for (int i = 0; i < num.length; i++) {
			pq.add(new QNode(num[i],i));
		}
		int i=0;
		while(k>0){
			QNode node=pq.remove();
			if(node.idx!=i){
				char t=num[i];
				num[i]=node.c;
				num[node.idx]=t;
				k--;
				i++;
			}
		}
		
		System.out.println(String.valueOf(num));
	}
}
