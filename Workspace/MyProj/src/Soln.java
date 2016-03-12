import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Student implements Comparable<Student> {
	private int token;
	private String fname;
	private double cgpa;

	public Student(int id, String fname, double cgpa) {
		super();
		this.token = id;
		this.fname = fname;
		this.cgpa = cgpa;
	}

	public int getToken() {
		return token;
	}

	public String getFname() {
		return fname;
	}

	public double getCgpa() {
		return cgpa;
	}

	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		if(this.getCgpa()>o.getCgpa())
			return -1;
		else if(this.getCgpa()<o.getCgpa())
			return 1;
		
		if(this.getFname().equals(o.getFname()))
			return this.getToken()-o.getToken();
		
			return this.getFname().compareTo(o.getFname());
		
		
		
		
	}
}

public class Soln {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int totalEvents = Integer.parseInt(in.nextLine());
		PriorityQueue<Student> pq = new PriorityQueue<Student>();
		while (totalEvents > 0) {
			String event = in.next();
			if (event.equals("ENTER"))
				{
				
				String fname= in.next();
				double gpa=Double.parseDouble(in.next());
				int token=Integer.parseInt(in.next());
				pq.add(new Student(token,fname.trim(),gpa));
				}
			else
			{	if(!pq.isEmpty())
				pq.remove();
			}
			totalEvents--;
		}
		if(pq.isEmpty())
		System.out.println("EMPTY");
		else
		{
			while(!pq.isEmpty())
				System.out.println(pq.remove().getFname());
			
		}
	}
}
