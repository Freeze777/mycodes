package hacker.rank.project.euler;
import java.util.Scanner;
import java.util.TreeSet;

public class Largest6DigitPalidrome {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int tst=sc.nextInt();
        TreeSet<Integer> palindromes=new TreeSet<Integer>();
        for (int i = 100; i <1000 ; i++) {
			for (int j =100; j <1000; j++) {
				int prod=i*j;
				int temp=prod;
				int rev=0;
				while(temp!=0)
				{
					rev=rev*10+(temp%10);
					temp/=10;
				}
				if(rev==prod)
					palindromes.add(prod);
			}
		}
        
        
        while(tst>0){
        	int n=sc.nextInt();
        	System.out.println(palindromes.floor(n));        	  	
        	tst--;
        }
        
    }
}