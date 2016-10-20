import java.util.Scanner;

public class SegmentTreeIntervalAddMax {

	int n;
	long[] tmax;
	long[] tadd; // tadd[i] affects tmax[i], tadd[2*i+1] and tadd[2*i+2]

	void push(int root) {
		tmax[root] += tadd[root];
		tadd[2 * root + 1] += tadd[root];
		tadd[2 * root + 2] += tadd[root];
		tadd[root] = 0;
	}

	public SegmentTreeIntervalAddMax(int n) {
		this.n = n;
		tmax = new long[4 * n];
		tadd = new long[4 * n];
	}

	public long max(int from, int to) {
		return max(from, to, 0, 0, n - 1);
	}

	long max(int from, int to, int root, int left, int right) {
		if (from == left && to == right) {
			return tmax[root] + tadd[root];
		}
		push(root);
		int mid = (left + right) >> 1;
		if (from <= mid && to > mid)
			return Math.max(
					max(from, Math.min(to, mid), 2 * root + 1, left, mid),
					max(Math.max(from, mid + 1), to, 2 * root + 2, mid + 1,
							right));
		else if (from <= mid)
			return max(from, Math.min(to, mid), 2 * root + 1, left, mid);
		else if (to > mid)
			return max(Math.max(from, mid + 1), to, 2 * root + 2, mid + 1,
					right);
		else
			throw new RuntimeException();
	}

	public void add(int from, int to, long delta) {
		add(from, to, delta, 0, 0, n - 1);
	}

	void add(int from, int to, long delta, int root, int left, int right) {
		if (from == left && to == right) {
			tadd[root] += delta;
			return;
		}
		// push can be skipped for add, but is necessary for other operations
		// such as set
		push(root);
		int mid = (left + right) >> 1;
		if (from <= mid)
			add(from, Math.min(to, mid), delta, 2 * root + 1, left, mid);
		if (to > mid)
			add(Math.max(from, mid + 1), to, delta, 2 * root + 2, mid + 1,
					right);
		tmax[root] = Math.max(tmax[2 * root + 1] + tadd[2 * root + 1],
				tmax[2 * root + 2] + tadd[2 * root + 2]);
	}

	// tests
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		SegmentTreeIntervalAddMax t = new SegmentTreeIntervalAddMax(n);
		for (int i = 0; i < n; i++) {
			t.add(i, i, sc.nextLong());
		}
		for (int i = 0; i < q; i++) {
			String c=sc.next();
			if(c.equals("q")){
				int l=sc.nextInt();
				int r=sc.nextInt();
			}else{
				
			}
		}
		t.add(3, 3, Long.MAX_VALUE / 2);
		System.out.println(t.max(0, 9));
		System.out.println(t.tmax[0] + t.tadd[0]);
		System.out.println(t.max(0, 0));
	}
}