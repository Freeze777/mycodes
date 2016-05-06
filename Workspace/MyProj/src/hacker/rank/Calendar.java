package hacker.rank;

import java.util.Scanner;

public class Calendar {
	static String day[]={"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};	
	static String month[]={"January","February","March","April","May","June",
		"July","August","September","October","November","December"};

	static int noOfDays[]={31,28,31,30,31,30,31,31,30,31,30,31};

	public static void leapYearCheck(int year) {
		if((year%400==0)||(year%100!=0 && year%4==0))
			noOfDays[1]=29;		
	}

	public static int  getStartDay(int dd,int mm,int yyyy)
	{
		int y = yyyy - (14 - mm) / 12;
		int x = y + y/4 - y/100 + y/400;
		int m = mm + 12 * ((14 - mm) / 12) - 2;
		int d = (dd + x + (31*m)/12) % 7;
		return d;
	}

	public static int printMonth(int monthIndex,int startDay,int year)
	{
		System.out.format("%3s%s,%s\n\n"," ",month[monthIndex],year+"");
		
		for (int i = 0; i < day.length; i++) 
			System.out.format("%6s", day[i]);

		System.out.println("");

		System.out.format("%"+((startDay>0)?(6*startDay):"")+"s"," ");
		int dayCount=1;
		for(;dayCount<=noOfDays[monthIndex];dayCount++)
		{
			if((dayCount+startDay)%7==1)
				System.out.println();
		
			System.out.format("%6s",dayCount+"");
		}
		System.out.println("\n");
		return((dayCount+startDay-1)%7);
	}

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the year:");
		int year=scanner.nextInt();
		leapYearCheck(year);
		int startDay=getStartDay(1,1,year);
		for(int i=0;i<12;i++)
			startDay= printMonth(i,startDay,year);
		scanner.close();

	}

}
