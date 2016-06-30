package hacker.rank;


public class SmallestSubarrayContaining1toK {
	public static void getMinAndMax(int[] index,int[] minmax) {
		int max=index[1],min=index[1];
		for (int i = 1; i < index.length; i++) {
			if(index[i]<min)
				min=index[i];
			else
				max=index[i];
		}
	minmax[0]=min;minmax[1]=max;
	}

	public static void main(String[] args) {
		int k = 4;
		int[] A = { 1, 2, 3, 9, 12, 3, 1, 6, 3, 13, 100, 123, 4, 2, 14, 3, 1 };
		int[] index = new int[k+1];
		int minmax[]={Integer.MAX_VALUE,Integer.MIN_VALUE};
		for (int j = 0; j < index.length; j++) {
			index[j]=-1;
		}
		int sumOfk = (k * (k + 1)) / 2, tempSum = 0;
		int ub=Integer.MIN_VALUE, lb=Integer.MAX_VALUE;
		int i = 0,result=Integer.MAX_VALUE;
		while (A[i++] > k)
			;
		ub = --i;
		index[A[ub]]=ub;
		tempSum += A[ub];
		i++;
		while (i<A.length) {
			System.out.println("At element "+A[i]);
			if (A[i] <= k) {
				System.out.println("Got element in range ");
				if (index[A[i]] == -1) {
					System.out.println("First occurence of "+A[i]);
					index[A[i]]=i;
					tempSum += A[i];
					System.out.println("tempSum="+tempSum);

				} else {
					if (A[ub] == A[i]) {
						ub++;
						while (A[ub++]>k);
						--ub;
						System.out.println("Moving ub to position "+ub);
						index[A[i]]=i;
					} else {

						index[A[i]]=i;
						System.out.println("Updating index array for "+A[i]+" to "+i);
					}
				}
			}
			if(tempSum==sumOfk)
			{	lb=i;
			System.out.println("got every element in range from indexes ["+ub+","+lb+"]");

			result=lb-ub+1;
			break;



			}
			i++;
		}
		System.out.println(result);
		i++;

		while(i<A.length)
		{	

			while(i<A.length&&A[ub]!=A[i])
			{	if(A[i]<=k)
				index[A[i]]=i;
			
				i++;
			}
			if(A[ub]==A[i])
			{index[A[i]]=i;
			lb=i;
			}
			if(A[i]<=k)
				index[A[i]]=i;
			
			getMinAndMax(index, minmax);
			ub=minmax[0];
			//index[A[ub]]=ub;
			System.out.println("got every element in range from indexes ["+ub+","+lb+"]");
			result=(result<(lb-ub+1))?(result):(lb-ub+1);
			i++;
		}	 
		
		getMinAndMax(index, minmax);
		for (int j = 0; j <index.length; j++) {
			System.out.print(index[j]+" ");
		}
		System.out.println("\n"+result);
	}
}