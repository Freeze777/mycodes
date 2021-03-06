package hacker.rank.project.euler;

import java.util.Scanner;

public class CountSundays {

	public static long getStartDay(int dd, int mm, long yyyy) {
		long y = (yyyy - (14 - mm) / 12);
		long x = y + y / 4 - y / 100 + y / 400;
		long m = mm + 12 * ((14 - mm) / 12) - 2;
		long d = (dd + x + (31 * m) / 12) % 7;
		return d;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();
		while (tst > 0) {
			long yy1 = sc.nextLong();
			int mm1 = sc.nextInt();
			int dd1 = sc.nextInt();
			long yy2 = sc.nextLong();
			int mm2 = sc.nextInt();
			int dd2 = sc.nextInt();
			int count=0;
			if (dd1 > 1) {
				dd1 = 1;
				yy1=(mm1==12)?yy1+1:yy1;
				mm1 = (mm1 == 12) ?  1 : mm1+1;
				
			}
			
			if (dd2 <= 31) {
				dd2 = 1;
			}
			
			
			for (long year = yy1; year <= yy2; year++) {
				int start=1;
				int end=12;
				if(yy1==yy2){
					 start=mm1;
					 end=mm2;
					
				}else if(year==yy1){
					start=mm1;
				}else if(year==yy2){
					end=mm2;
				}
				for (int month = start; month <=end ; month++) {
					long day = getStartDay(1, month, year);
					if(day==0)
						count++;
				}
				
			}
		System.out.println(count);
			tst--;
		}
		sc.close();

	}

}
