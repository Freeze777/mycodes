package hacker.rank.project.euler;
import java.io.*;
import java.util.*;

public class LCMOfGroupOfNumbers {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int tst=sc.nextInt();
        while(tst>0){
        	int n=sc.nextInt();
        	int ans=1;
        	for(int i=2;i<=n;i++)
        		ans=lcm(ans,i);
        
        	System.out.println(ans);
        	tst--;
        }
        
    }

	private static int lcm(int a, int b) {
		
		return (a*b)/gcd(a,b);
	}

	private static int gcd(int a, int b) {
		if(a==0)return b;
		if(b==0)return a;
				
		return gcd(b,a%b);
	}
}