import java.util.Scanner;
public class TestClass {
	static int max = 1000000;
	static boolean isNotPrime[] = new boolean[max + 1];
	static int pref[] = new int[max + 1];

	public static void sieve() {
		isNotPrime[0]=true;
		isNotPrime[1]=true;
		for (int i = 2; i * i <= max; i++) {
			for (int j = i; i * j <= max; j++) {
				isNotPrime[i * j] = true;
			}
		}
	}
	public static int getSum(int n) {
		if (pref[n] != 0)
			return pref[n];
		int i=n-1;
		for(;i>=0 && pref[i]==0;i--);
		i++;
		for (; i <= n; i++) {
			if(i>=1){
			if (!isNotPrime[i])
				pref[i] += (1 + pref[i - 1]);
			else
				pref[i] = pref[i - 1];
			}
		}
		return pref[n];

	}

	public static void main(String args[]) throws Exception {
		sieve();
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			int l=sc.nextInt();
			int r=sc.nextInt();
			int count=0;
			for (int j = l; j <=r; j++) {
				if(!isNotPrime[getSum(j)])
					count++;
			}	
			System.out.println(count);
		}
	}
}
