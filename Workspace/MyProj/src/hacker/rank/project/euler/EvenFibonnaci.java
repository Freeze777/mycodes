package hacker.rank.project.euler;
import java.util.Scanner;

public class EvenFibonnaci {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    	
    	Scanner sc=new Scanner(System.in);
    	
    	int tst=sc.nextInt();
    	
    	while(tst>0){
    		long n=sc.nextLong();
    		long sum=2;
    		long term1=0;
    		long term2=2;
    		long curr=0;
    		while((4*term2+term1)<=n){
    			
    			curr=4*term2+term1;
    			sum+=curr;
    			term1=term2;
    			term2=curr;
    					
    			
    		}
    		System.out.println(sum);
    		
    		
    		tst--;
    	}
    }
}