package hacker.rank.project.euler;
import java.util.Scanner;


public class Multiple3and5 {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int tst=sc.nextInt();
		
		while(tst>0){
			
			long n=sc.nextLong();
			n--;
			
			long an_3=n-n%3;
			long an_5=n-n%5;
			long an_15=n-n%15;
			
			
			//System.out.println(an_15+" "+an_5+" "+an_3);
			
			long n3=((an_3-3)/3)+1;
			long n5=((an_5-5)/5)+1;
			long n15=((an_15-15)/15)+1;
			
			long sum3=(n3*(3+an_3))/2;
			long sum5=(n5*(5+an_5))/2;
			long sum15=(n15*(15+an_15))/2;
			
			long sum=sum3+sum5-sum15;
			
			System.out.println(sum);
			
			
			
			
			tst--;
		}
		
	}

}
