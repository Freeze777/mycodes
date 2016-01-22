package centurylink.cpms;

public class CpmsUtility {

	
	public static int stringTimeToInt(String time){
		
		String[] data = time.split("\\:");


		return(Integer.parseInt(data[0])*100+Integer.parseInt(data[1]));
		
		
		
	}	
	

	public static String intTimeToString(int time){
		String sminute=null;
		
	Integer	minute=time%100;
	Integer	hour=time/100;
		if(minute==0){
		sminute="00";

		}
		else{
			sminute=minute.toString();
		}
	return(hour.toString()+":"+sminute);

		
		
	}	
	



}
