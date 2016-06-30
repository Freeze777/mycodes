	package hacker.rank;
	
	import java.util.HashSet;
	import java.util.Scanner;
	import java.util.Set;
	
	class Vec{
		long x;
		long y;
		Vec(){}
		Vec(long x,long y)
		{
			this.x=x;
			this.y=y;
		}
	}
	public class ScalarProduct {
		
		public static void main(String[] args) {
			Scanner sc=new Scanner(System.in);
			
			long c=sc.nextLong();
			long m=sc.nextLong();
			long n=sc.nextLong();
			Vec vec[]=new Vec[(int)n];
			
			vec[0]=new Vec(c%m,(2*c)%m);
			
		Set<Long> set=new HashSet<Long>();
		
		for (int i =1; i < vec.length; i++) {
			vec[i]=new Vec((vec[i-1].y+vec[i-1].x)%m,0);
			vec[i].y=(vec[i].x+vec[i-1].y)%m;
		}
		for (int i = 0; i < vec.length; i++) {
			System.out.println(vec[i].x+","+vec[i].y);
		}
		for (int i = 0; i < vec.length; i++) {
			for (int j =i+1; j < vec.length; j++) {
				
					set.add(((vec[i].x*vec[j].x)%m+(vec[i].y*vec[j].y)%m)%m);
				
				
			}
		}	
		System.out.println(set);
		}
	
	}
