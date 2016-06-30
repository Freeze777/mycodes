package hacker.rank;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;


public class OnlineMedianUsingTwoHeaps {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		PriorityQueue<Long> minHeap=new PriorityQueue<Long>();

		Comparator<Long> maxHeapComparator=new Comparator<Long>() {

			public int compare(Long x, Long y) {
				// TODO Auto-generated method stub
				return (int)(y-x);
			}


		};
		PriorityQueue<Long> maxHeap=new PriorityQueue<Long>(20,maxHeapComparator);
		double median=0.0;
		long n=sc.nextLong();
		while(n>0)
		{
			long a=sc.nextLong();
			if(a>median)
				minHeap.add(a);
			else
				maxHeap.add(a);

			int minSize=minHeap.size();
			int maxSize=maxHeap.size();
			//System.out.println(minSize);
			//System.out.println(maxSize);
			if(Math.abs(minSize-maxSize)>1){
				boolean x=(minSize>maxSize)?maxHeap.add(minHeap.remove()):minHeap.add(maxHeap.remove());
				minSize=minHeap.size();
				maxSize=maxHeap.size();

			}			

			if (minSize==maxSize) {
				median=(minHeap.peek()+maxHeap.peek())/2.0;
			}else if(Math.abs(minSize-maxSize)==1)
			{
				median=(minSize>maxSize)?minHeap.peek():maxHeap.peek();
			}

			System.out.println(median);
			n--;
		}


sc.close();
	}
}
