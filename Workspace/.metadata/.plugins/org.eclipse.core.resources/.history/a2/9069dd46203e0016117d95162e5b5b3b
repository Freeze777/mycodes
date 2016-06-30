package algo;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class SortArrayWith3Distincts {
	public static void main(String[] args) {
		int A[]={10,20,20,10,30,20,10,10,30,20};
		System.out.println(Arrays.toString(A));
		Map<Integer,Integer> map=new TreeMap<Integer, Integer>();
		for (int i = 0; i < A.length; i++) {
			int count=(map.get(A[i])==null)?0:map.get(A[i]);
			map.put(A[i], count+1);
		}
		int i=0;
		
		for (Entry<Integer,Integer> entry : map.entrySet()) {
			int val=entry.getKey();
			int count=entry.getValue();
			while(count>0)
			{	A[i++]=val;
				count--;
			}
		}
		System.out.println(Arrays.toString(A));
	}

}
