package hacker.rank;
import java.io.*;
import java.util.*;

public class FixTheCycles {

    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
       int[] A=new int[6];
       int[] cycle=new int[3];
       
       for(int i=0;i<6;i++)
          A[i]=sc.nextInt();
       
       cycle[0]=A[0]+A[4]+A[3];
       cycle[1]=A[0]+A[1]+A[5];
       cycle[2]=A[0]+A[1]+A[2]+A[3];
       int min=Math.min(cycle[0],Math.min(cycle[1],cycle[2]));
       
       System.out.println(Math.abs(min));
          
       
    }
}