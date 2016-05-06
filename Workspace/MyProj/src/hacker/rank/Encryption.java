package hacker.rank;

import java.util.Scanner;


public class Encryption {
	public static void main(String[] args) {
		Scanner sc =new  Scanner(System.in);
		String s=sc.next();
		int i;
		int l=s.length();
		int rows=(int)Math.floor(Math.sqrt(l));
		int cols=(int)Math.ceil(Math.sqrt(l));
		if(rows*cols<l)
		{
			rows=(int)Math.ceil(((l*1.0)/cols));
		}
		String[] encode=new String[rows];
		
		for (i = 0; i < rows-1; i++) {
			encode[i]=s.substring(i*cols,((i*cols)+cols));
		}

		encode[rows-1]=s.substring(i*cols);
		StringBuilder result=new StringBuilder();
		for(int j=0;j<cols;j++)
		{
			for(i=0;i<rows;i++)
			{	if(j<encode[i].length())	
				result.append(encode[i].charAt(j));	
			}	
			result.append(" ");
		}
		System.out.println(result);
	sc.close();
	}
}
