import java.io.*;
import java.util.*;

public class DoesItFit {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        	int x=sc.nextInt();
        	int y=sc.nextInt();
        	int W=Math.max(x,y);
        	int H=Math.min(x,y);
		    int n = sc.nextInt();
		    for (int i = 0; i < n; i++) {
				String s=sc.next();
				if(s.equals("R"))
				{
					x=sc.nextInt();
					y=sc.nextInt();
					int w=Math.max(x,y);
					int h=Math.min(x,y);
					System.out.println((w<=W)&&(h<=H)?"YES":"NO");
				}else{
					
					int r=sc.nextInt();
					System.out.println(((2*r)<=W)&&((2*r)<=H)?"YES":"NO");
				}
			}
    }
}