package hacker.rank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Program for range minimum query using segment tree
class SegmentTreeRMQ {
	long st[]; // array to store segment tree

	// A utility function to get minimum of two numbers
	long minVal(long x, long y) {
		return (x < y) ? x : y;
	}

	// A utility function to get the middle index from corner
	// indexes.
	long getMid(long s, long e) {
		return s + (e - s) / 2;
	}

	/*
	 * A recursive function to get the minimum value in a given range of array
	 * indexes. The following are parameters for this function.
	 * 
	 * st --> Polonger to segment tree index --> Index of current node in the
	 * segment tree. Initially 0 is passed as root is always at index 0 ss & se
	 * --> Starting and ending indexes of the segment represented by current
	 * node, i.e., st[index] qs & qe --> Starting and ending indexes of query
	 * range
	 */
	long RMQUtil(long ss, long se, long qs, long qe, long index) {
		// If segment of this node is a part of given range, then
		// return the min of the segment
		if (qs <= ss && qe >= se)
			return st[(int) index];

		// If segment of this node is outside the given range
		if (se < qs || ss > qe)
			return Long.MAX_VALUE;

		// If a part of this segment overlaps with the given range
		long mid = getMid(ss, se);
		return minVal(RMQUtil(ss, mid, qs, qe, 2 * index + 1),
				RMQUtil(mid + 1, se, qs, qe, 2 * index + 2));
	}

	// Return minimum of elements in range from index qs (quey
	// start) to qe (query end). It mainly uses RMQUtil()
	long RMQ(long n, long qs, long qe) {
		// Check for erroneous input values
		if (qs < 0 || qe > n - 1 || qs > qe) {
			System.out.println("Invalid Input");
			return -1;
		}

		return RMQUtil(0, n - 1, qs, qe, 0);
	}

	// A recursive function that constructs Segment Tree for
	// array[ss..se]. si is index of current node in segment tree st
	long constructSTUtil(long arr[], long ss, long se, long si) {
		// If there is one element in array, store it in current
		// node of segment tree and return
		if (ss == se) {
			st[(int) si] = arr[(int) ss];
			return arr[(int) ss];
		}

		// If there are more than one elements, then recur for left and
		// right subtrees and store the minimum of two values in this node
		long mid = getMid(ss, se);
		st[(int) si] = minVal(constructSTUtil(arr, ss, mid, si * 2 + 1),
				constructSTUtil(arr, mid + 1, se, si * 2 + 2));
		return st[(int) si];
	}

	/*
	 * Function to construct segment tree from given array. This function
	 * allocates memory for segment tree and calls constructSTUtil() to fill the
	 * allocated memory
	 */
	void constructST(long arr[], long n) {
		// Allocate memory for segment tree

		// Height of segment tree
		long x = (long) (Math.ceil(Math.log(n) / Math.log(2)));

		// Maximum size of segment tree
		long max_size = 2 * (long) Math.pow(2, x) - 1;
		st = new long[(int) max_size]; // allocate memory

		// Fill the allocated memory st
		constructSTUtil(arr, 0, n - 1, 0);
	}

}

class Range {
	int l;
	int r;

	Range(int l, int r) {
		this.l = l;
		this.r = r;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + l;
		result = prime * result + r;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Range other = (Range) obj;
		if (l != other.l)
			return false;
		if (r != other.r)
			return false;
		return true;
	}

}

public class Coolguy {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		long arr[] = new long[(int) n];
		Map<Range, Long> map = new HashMap<Range, Long>();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextLong();
		}
		SegmentTreeRMQ tree = new SegmentTreeRMQ();

		// Build segment tree from given array
		tree.constructST(arr, n);
		long ans = 0L;
		long m = (long) Math.pow(10, 9) + 7;

		for (int a = 0; a < arr.length; a++) {
			for (int b = a; b < arr.length; b++) {
				for (int c = b + 1; c < arr.length; c++){
					for (int d = c; d < arr.length; d++){
						Range t1=new Range(a, b);
						Range t2=new Range(c, d);
						long q1,q2;
						if(map.containsKey(t1)){
							q1=map.get(t1);
						}
						else{
							q1=tree.RMQ(n, a, b);
							map.put(t1, q1);
						}
						if(map.containsKey(t2)){
							q2=map.get(t2);
						}
						else{
							q2=tree.RMQ(n, c,d);
							map.put(t2, q2);
						}
						
						ans = (ans + Math.min(q1,q2))% m;
					}
				}
			}
		}
		System.out.println(ans);

	}
}
