package hacker.earth;

import java.util.Arrays;
import java.util.Scanner;

/*https://www.hackerearth.com/problem/algorithm/primestring/*/
public class PrimeString {
        public static void main(String args[] ) throws Exception {
        	int max=100001;
        	boolean[] p=new boolean[max];
        	Arrays.fill(p,true);
        	p[0]=false;p[1]=false;
        	for(int i=2;i*i<max;i++){
        		if(p[i]){
        			for(int j=i;j*i<max;j++)
        				p[i*j]=false;
        		}
        	}
          	Scanner sc=new Scanner(System.in);
          	int t=sc.nextInt();
          	while(t-->0){
          		int[] count=new int[26];
          		String s=sc.next();
          		int cnt=0;
          		boolean ans=true;
          		for(int i=0;i<s.length();i++){
          			if(count[s.charAt(i)-'a']==0)
          				cnt++;
          			count[s.charAt(i)-'a']++;
          		}
          		ans=ans&&p[cnt];
          		if(ans){
          			for(int i=0;i<26;i++){
          				if(count[i]!=0){
          					ans=ans&&p[count[i]];
          				}
          				if(!ans)
          					break;
          			}
          		}
          		System.out.println(ans?"YES":"NO");	
          	}
     
            
        }
}
