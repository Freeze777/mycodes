package hacker.rank.project.euler;

import java.util.Scanner;

public class PrimePattern {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int max = 10000000;
		boolean isNotPrime[] = new boolean[max + 1];
		isNotPrime[0]=true;
		isNotPrime[1]=true;		
		for (int i = 2; i * i <= max; i++) {
			for (int j = i; i * j <= max; j++) {
				isNotPrime[i * j] = true;
			}
		}
		int t=sc.nextInt();
		while(t-->0){
			
		}
	}
}
