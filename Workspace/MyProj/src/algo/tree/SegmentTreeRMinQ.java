package algo.tree;

public class SegmentTreeRMinQ {

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
