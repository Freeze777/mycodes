package algo.bits;
import java.math.BigInteger;
import java.util.Scanner;

public class CounterGame {
	public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	int tst=sc.nextInt();
	while(tst>0)
	{	boolean winner=true;
		BigInteger num=sc.nextBigInteger();
		while(!num.equals(BigInteger.ONE))
		{
			
			if(num.and(num.subtract(BigInteger.ONE)).equals(BigInteger.ZERO))
				num=num.shiftRight(1);
			else
				num=num.subtract(BigInteger.ONE.shiftLeft(num.bitLength()-1));
				
			winner=!winner;
			
		}
		
		if(!winner)
			System.out.println("Louise");
		else
			System.out.println("Richard");
		tst--;
	}
}
}
