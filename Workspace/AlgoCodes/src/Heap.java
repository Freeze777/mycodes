
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

interface ItemChanger<T> {
	public void decreaseKey(T item);
}

public class Heap<T> extends AbstractQueue<T> {
	private ArrayList<T> heap;
	private Map<T, Set<Integer>> map;
	private Comparator<? super T> c;
	private static final int DEFAULT_INITIAL_CAPACITY = 11;
	
	public Heap() {
		this(DEFAULT_INITIAL_CAPACITY);
	}
	
	public Heap(int initialCapacity) {
		this(initialCapacity, new Comparator<T>() {
			@SuppressWarnings("unchecked")
			@Override
			public int compare(T a, T b) {
				Comparable<? super T> key = (Comparable<? super T>) a;
				return key.compareTo(b);
			}
		});
	}
	
	public Heap(Comparator<? super T> comparator) {
		this.heap = new ArrayList<T>(DEFAULT_INITIAL_CAPACITY);
		c = comparator;
		initMap(DEFAULT_INITIAL_CAPACITY);
	}
	
	public Heap(int initialCapacity, Comparator<? super T> comparator) {
		this.heap = new ArrayList<T>(initialCapacity);
		c = comparator;
		initMap(initialCapacity);
	}
	
	
	public Heap(Collection<? extends T> items) {
		this(items, new Comparator<T>() {
			@SuppressWarnings("unchecked")
			@Override
			public int compare(T a, T b) {
				Comparable<? super T> key = (Comparable<? super T>) a;
				return key.compareTo(b);
			}
		});
	}
	
	public Heap(Collection<? extends T> items, Comparator<? super T> comparator) {
		this.heap = new ArrayList<T>(items);
		c = comparator;
		initMap();
		buildMinHeap();
	}
	
	@Override
	public boolean add(T item) {
		return offer(item);
	}
	
	
	public Comparator<? super T> comparator() {
		return c;
	}
	
	
	@Override
	public void clear() {
		heap = new ArrayList<T>(DEFAULT_INITIAL_CAPACITY);
		initMap(DEFAULT_INITIAL_CAPACITY);
	}
	
	@Override
	public boolean contains(Object o) {
		return map.containsKey(o);
	}

	@Override
	public boolean offer(T item) {
		heap.add(item);
		addMapping(item, heap.size() - 1);
		decreaseKeyByIndex(heap.size() - 1);
		return true;
	}

	@Override
	public T peek() {
		if (heap.size() == 0) { return null; }
		return heap.get(0);
	}

	
	@Override
	public T poll() {
		if (heap.size() < 1) {
			return null;
		}
		int lastIndex = heap.size() - 1;
		T min = heap.get(0);
		removeMapping(min, 0);
		heap.set(0, heap.get(lastIndex));
		addMapping(heap.get(0), 0);
		removeMapping(heap.get(lastIndex), lastIndex);
		heap.remove(lastIndex);
		minHeapify(0);
		return min;
	}

	@Override
	public Iterator<T> iterator() {
		return heap.listIterator();
	}

	
	@Override
	public int size() {
		return heap.size();
	}
	
	public int find(T item) {
		if (!map.containsKey(item)) {
			return -1;
		}
		return map.get(item).iterator().next();
	}
	
	public T get(int index) {
		return heap.get(index);
	}
	public void reset() {
		initMap();
		buildMinHeap();
	}
	
	public boolean check() {
		int len = heap.size();
		for (int i = (len / 2) - 1; i >= 0; i--) {
			if (c.compare(heap.get(left(i)), heap.get(i)) < 0)
				return false;
			if (right(i) < len && c.compare(heap.get(right(i)), heap.get(i)) < 0)
				return false;
		}
		for (int i = 0; i < len; i++) {
			if (!map.containsKey(heap.get(i)) || !map.get(heap.get(i)).contains(i)) 
				return false;
		}
		for (T item : map.keySet()) {
			for (int i : map.get(item)) {
				if (!item.equals(heap.get(i)))
					return false;
			}
		}
		return true;
	}
	
	public void decreaseKey(int i, T item) {
		if (c.compare(item, heap.get(i)) > 0) {
			throw new IllegalArgumentException("new key is larger than current key");
		}
		removeMapping(heap.get(i), i);
		heap.set(i, item);
		addMapping(item, i);
		decreaseKeyByIndex(i);
	}
	
	public void decreaseKey(T item, ItemChanger<T> itemChanger) {
		int index = find(item),
			len = heap.size();
		removeMapping(item, index);
		itemChanger.decreaseKey(item);
		addMapping(item, index);
		if (left(index) < len) {
			if (c.compare(heap.get(left(index)), item) < 0 || (right(index) < len 
					&& c.compare(heap.get(right(index)), item) < 0)) {
				throw new IllegalArgumentException("heap was corrupted due to illegal key change");
			}
		}
		decreaseKeyByIndex(index);
	}
	
	private void buildMinHeap() {
		for (int i = (heap.size() / 2) - 1; i >= 0; i--) {
			minHeapify(i);
		}
	}
	
	private void minHeapify(int i) {
		int l = left(i),
			r = right(i),
			smallest = i;
		
		if (l < heap.size() && c.compare(heap.get(l), heap.get(i)) < 0) {
			smallest = l;
		}
		if (r < heap.size() && c.compare(heap.get(r), heap.get(smallest)) < 0) {
			smallest = r;
		}
		if (smallest != i) {
			swap(i, smallest);
			minHeapify(smallest);
		}
	}
	
	private void decreaseKeyByIndex(int i) {
		while (i > 0 && c.compare(heap.get(i), heap.get(parent(i))) < 0) {
			swap(i, parent(i));
			i = parent(i);
		}
	}
	
	private void removeMapping(T item, Integer i) {
		map.get(item).remove(i);
		if (map.get(item).isEmpty()) {
			map.remove(item);
		}
	}
	
	private void addMapping(T item, Integer i) {
		if (!map.containsKey(item)) {
			map.put(item, new HashSet<Integer>());
		}
		map.get(item).add(i);
	}
	
	private void swap(int i, int j) {
		map.get(heap.get(i)).remove(new Integer(i));
		map.get(heap.get(j)).remove(new Integer(j));
		T tmp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, tmp);
		map.get(heap.get(i)).add(i);
		map.get(heap.get(j)).add(j);
		
	}
	
	private static int parent(int i) {
		return (i - 1) / 2;
	}
	
	private static int left(int i) {
		return 2 * i + 1;
	}
	
	private static int right(int i) {
		return 2 * i + 2;
	}
	
	private void initMap(int initialCapacity) {
		map = new HashMap<T, Set<Integer>>(initialCapacity);
	}
	
	private void initMap() {
		map = new HashMap<T, Set<Integer>>(heap.size());
		for (int i = 0; i < heap.size(); i++) {
			if (map.containsKey(heap.get(i))) {
				map.get(heap.get(i)).add(i);
			}
			else {
				map.put(heap.get(i), new HashSet<Integer>());
				map.get(heap.get(i)).add(i);
			}
		}
	}
   public static void main(String[] args){
      Heap<Integer> hp=new Heap<Integer>(Colle);
      hp.add(10);hp.add(10);hp.add(-100);hp.add(-10);hp.add(100);
      System.out.println(hp.remove());
   }
}