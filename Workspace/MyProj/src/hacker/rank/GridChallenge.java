package hacker.rank;
import java.io.*;
import java.util.*;

public class GridChallenge {

    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);
	int tst=sc.nextInt();

	while(tst>0){
	int n=sc.nextInt();
	char[][] arr=new char[n][n];
		for(int i=0;i<n;i++)
		{	arr[i]=sc.next().toCharArray();
			Arrays.sort(arr[i]);
		}
		boolean flag=true;
		for(int i=0;i<n && flag;i++)
		{	for(int j=0;j<n && flag;j++)
			{	if(arr[j][i]>arr[j+1][i])
				{
					flag=false;
				}
			}
		}
	if(flag)
		System.out.println("YES");
	else
		System.out.println("NO");
	tst--;
	}
    
    
    
    }
}