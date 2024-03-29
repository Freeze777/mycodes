package hacker.earth;

import java.util.*;
/*https://www.hackerearth.com/problem/algorithm/emma-and-the-prime-sum/*/
public class EmmaAndPrimes {
    public static void main(String args[] ) throws Exception {
       Scanner sc=new Scanner(System.in);
       int t=sc.nextInt();
       int max=1000001;
       long[] isPrime=new long[max];
       Arrays.fill(isPrime,1);
       isPrime[0]=0;
       isPrime[1]=0;
       for(int i=2;i*i<max;i++){
       		if(isPrime[i]==1){
	       		for(int j=i;i*j<max;j++){
	       			isPrime[i*j]=0;
	       		}
       		}
       }
       isPrime[2]=2;
       for(int i=3;i<max;i++){
       	if(isPrime[i]==1)
       		isPrime[i]=i+isPrime[i-1];
       	else
       		isPrime[i]=isPrime[i-1];
       }
       while(t-->0){
       	int l=sc.nextInt();
       	int r=sc.nextInt();
       	System.out.println(isPrime[r]-isPrime[l-1]);
       }
 
        
    }
}