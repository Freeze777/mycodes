package hacker.rank.project.euler;
import java.io.*;
import java.util.*;

public class LargestPrimeFactor {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int tst=sc.nextInt();
        
        while(tst>0){
        	long n=sc.nextLong();
        	long i=2;
        	List<Long> primes=new ArrayList<Long>();
        	
        	while(n>1)
        	{
        		if(n%i==0){
        			primes.add(i);
        			while(n%i==0){
        				
        				n=n/i;
        			}
        			
        			
        		}
        		i+=1;
        		if(i*i > n){	//---> i>sqrt(n) meaning there was no factor pair for this number therfore prime 
        			if(n>2)// its a prime
        				primes.add(n);
        			break;
        		}
        		
        	}
        	
        	System.out.println(primes.get(primes.size()-1));
        	tst--;
        }
        
        
    }
}