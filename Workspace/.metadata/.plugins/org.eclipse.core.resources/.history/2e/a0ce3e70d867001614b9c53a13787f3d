package hacker.rank;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class MaxValues {

    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);
    	//long m=(long)Math.pow(10,9)+7;
    	BigInteger m=new BigInteger("1000000007");
    	long q=sc.nextLong();
    	while(q>0){
    		BigInteger l=sc.nextBigInteger();
    		BigInteger r=sc.nextBigInteger();
    		BigInteger sum=BigInteger.ZERO;
    		
    		BigInteger res=r.multiply(r.add(BigInteger.ONE)).multiply(r.multiply(BigInteger.TEN).add(new BigInteger("9")));
    		res=res.divide(new BigInteger("2"));
    		res=res.mod(m);
    		BigInteger res2=l.multiply(l.subtract(BigInteger.ONE)).multiply(l.multiply(BigInteger.TEN).subtract(BigInteger.ONE));
    		res2=res2.divide(new BigInteger("2"));
    		res2=res2.mod(m);
    		
    		BigInteger res3=r.add(BigInteger.ONE).subtract(l);
    		res3=res3.multiply(new BigInteger("4"));
    		res3=res3.mod(m);
    		
    		res=res.subtract(res2).subtract(res3);
    		res=res.mod(m);
    		System.out.println(res);
    		q--;
    	}
       
    }

	
}