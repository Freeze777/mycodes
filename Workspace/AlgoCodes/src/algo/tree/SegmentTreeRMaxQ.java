package algo.tree;

public class SegmentTreeRMaxQ {
	long st[]; // array to store segment tree

	// A utility function to get minimum of two numbers
	long maxVal(long l, long m) {
		return (l > m) ? l : m;
	}

	// A utility function to get the middle index from corner
	// indexes.
	int getMid(int s, int e) {
		return s + (e - s) / 2;
	}

	
	long RMQUtil(int ss, int se, int qs, int qe, int index) {
		// If segment of this node is a part of given range, then
		// return the min of the segment
		if (qs <= ss && qe >= se)
			return st[(int) index];

		// If segment of this node is outside the given range
		if (se < qs || ss > qe)
			return Integer.MIN_VALUE;

		// If a part of this segment overlaps with the given range
		int mid = getMid(ss, se);
		return maxVal(RMQUtil(ss, mid, qs, qe, 2 * index + 1),
				RMQUtil(mid + 1, se, qs, qe, 2 * index + 2));
	}

	// Return minimum of elements in range from index qs (quey
	// start) to qe (query end). It mainly uses RMQUtil()
	long RMQ(int n, int qs, int qe) {
		// Check for erroneous input values
		if (qs < 0 || qe > n - 1 || qs > qe) {
			System.out.println("Invalid Input");
			return -1;
		}

		return RMQUtil(0, n - 1, qs, qe, 0);
	}

	// A recursive function that constructs Segment Tree for
	// array[ss..se]. si is index of current node in segment tree st
	long constructSTUtil(long[] a, int ss, int se, int si) {
		// If there is one element in array, store it in current
		// node of segment tree and return
		if (ss == se) {
			st[(int) si] =  a[(int) ss];
			return a[(int) ss];
		}

		// If there are more than one elements, then recur for left and
		// right subtrees and store the minimum of two values in this node
		int mid = getMid(ss, se);
		st[(int) si] = maxVal(constructSTUtil(a, ss, mid, si * 2 + 1),
				constructSTUtil(a, mid + 1, se, si * 2 + 2));
		return st[(int) si];
	}

	/*
	 * Function to construct segment tree from given array. This function
	 * allocates memory for segment tree and calls constructSTUtil() to fill the
	 * allocated memory
	 */
	void constructST(long[] a, int n) {
		// Allocate memory for segment tree

		// Height of segment tree
		int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));

		// Maximum size of segment tree
		int max_size = 2 * (int) Math.pow(2, x) - 1;
		st = new long[(int) max_size]; // allocate memory

		// Fill the allocated memory st
		constructSTUtil(a, 0, n - 1, 0);
	}

}
