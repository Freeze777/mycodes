package hacker.rank;
import java.util.Scanner;
/*HackerRank math contest*/
public class BirthdayProb {

    public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      int t=sc.nextInt();
      double d=365.0;
      while(t>0)
      {
    	  double prob=sc.nextDouble();
    	  double tmp=(2.0*Math.log(1-prob))/Math.log((d-1)/d);
    	  double disc=Math.sqrt(1+(4*tmp));
    	  double x=(1+disc)/2.0;
    	  System.out.println((int)Math.ceil(x));
    	  
    	  t--;
      }
      
    }
}