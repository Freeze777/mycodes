package hacker.rank;
import java.util.Scanner;

class Vector{
	int x,y,z;

	public Vector(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public static double angle(Vector v1,Vector v2){
		double tmp=(double)(v1.x*v2.x+v1.y*v2.y+v1.z*v2.z)/((Math.sqrt(v1.x*v1.x+v1.y*v1.y+v1.z*v1.z))*Math.sqrt(v2.x*v2.x+v2.y*v2.y+v2.z*v2.z));
		return Math.acos(tmp);
	}
	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
}

public class AnglesSpaces {
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int tmp=n;
		Vector[] v=new Vector[n];		
		while(tmp>0){
			int x=sc.nextInt();
			int y=sc.nextInt();
			int z=sc.nextInt();
			v[tmp-1]=new Vector(x,y,z);
			System.out.println(v[tmp-1]);
			
			tmp--;
		}
		double sum=0.0;
		for (int i = 0; i < v.length; i++) {
			for (int j = i+1; j < v.length; j++) {
				for (int k =j+1; k < v.length; k++) {
					System.out.println(i+","+j+","+k);
					Vector v1=new Vector(v[j].x-v[i].x,v[j].y-v[i].y,v[j].z-v[i].z);
					Vector v2=new Vector(v[j].x-v[k].x,v[j].y-v[k].y,v[j].z-v[k].z);
					System.out.println(v1+","+v2);
					sum+=Vector.angle(v1,v2);
				}
				
			}
		}
		
		System.out.printf("%.11f",sum/n);		
	}

}
