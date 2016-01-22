package algo;

public class Permutations {
public static void main(String[] args) {
	String s="1234";
	char[] c=s.toCharArray();
	printPermutations(c,0,s.length()-1);
	
	
}

public static void printPermutations(char[] s, int depth, int n) {
	
	if(depth==n)
	{	System.out.println(String.valueOf(s));
		return;
	}
for (int i = depth; i <=n; i++) {
	char temp=s[depth];
	s[depth]=s[i];
	s[i]=temp;
	printPermutations(s, depth+1, n);
	s[i]=s[depth];
	s[depth]=temp;
}
	
}	

}
