package centurylink.cpms;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends ActionSupport implements SessionAware {
	private static ServiceInterface serviceObj =new ServiceLayer();
	private static int intTime;
	private static String to;
	private String ocuid;
	private int dashBoardStatus;
	private Long routeid;
	private String 	message;
	private String rcuid;
	private static Long userId;
	private static String from; //
	private static String time;
	private String seatMessage;

	public String getSeatMessage() {
		return seatMessage;
	}
	public void setSeatMessage(String seatMessage) {
		this.seatMessage = seatMessage;
	}
	private String 	searchMessage;
	private ArrayList<SearchArray> searchList;
	
	
	private static ArrayList<String> toList;
	private static ArrayList<String> fromList;
	private static ArrayList<String> timeList;
	private Map<String,Object> sessionMap;
	private ArrayList<CpmsRequestLogs> dashBoard;
	
	
	
	public ArrayList<SearchArray> getSearchList() {
		return searchList;
	}
	public void setSearchList(ArrayList<SearchArray> searchList) {
		this.searchList = searchList;
	}


	

	

	public ArrayList<CpmsRequestLogs> getDashBoard() {
		return dashBoard;
	}
	public void setDashBoard(ArrayList<CpmsRequestLogs> dashBoard) {
		this.dashBoard = dashBoard;
	}

	

	@Override
	public void setSession(Map sessionMap) {
		// TODO Auto-generated method stub
		//System.out.println("in setsession-search action");
		this.sessionMap=sessionMap; 
		setMessage((String) this.sessionMap.get("username") );
	}


	public String populate() {


		if(sessionMap.get("user")!=null) {

			User user=(User)sessionMap.get("user");
			userId=user.getCid();

			//System.out.println("in SearchAction-populate");

			
			toList = new ArrayList<String>();
			fromList=new ArrayList<String>();
			timeList=new ArrayList<>();

			timeList.add("07:00");
			timeList.add("07:30");	
			timeList.add("08:00");
			timeList.add("08:30");
			timeList.add("09:00");
			timeList.add("09:30");
			timeList.add("10:00");
			timeList.add("10:30");
			timeList.add("11:00");
			timeList.add("17:00");
			timeList.add("17:30");	
			timeList.add("18:00");
			timeList.add("18:30");
			timeList.add("19:00");
			timeList.add("19:30");
			timeList.add("20:00");
			timeList.add("20:30");
			timeList.add("21:00");	
			//servce----->dao calls;	
			
			
			


			toList=serviceObj.fillList();
			toList.add(0, "CTLI");


			fromList=serviceObj.fillList();
			fromList.add(0,"CTLI");
			if((toList !=null)&&(fromList!=null))
			{

				return SUCCESS;

			}else
				return "failed";

		}
		else
			return ERROR;


	}

	public String getMyDashboard()	{

		if(sessionMap.get("user")!=null)
		{	
			Long cid=  ((User)	sessionMap.get("user")).getCid();
			userId=cid;
			dashBoard=serviceObj.getUserDashboard(cid);
			setSeatMessage(serviceObj.getUserSeasts(cid));
			
			//System.out.println(dashBoard);
			if(dashBoard!=null)
			{dashBoardStatus=dashBoard.size();
			return SUCCESS;
			}
			else {
				return "failed";
			}


		}		
		else
			return ERROR;

	}


	public String getSearchResults()
	{ //System.out.println("getsearchresults::search action");
		if(sessionMap.get("user")!=null)
		{		
			String[] data = time.split("\\:");
			intTime=Integer.parseInt(data[0])*100+Integer.parseInt(data[1]);
			searchList=serviceObj.getSearchUserResult(to,from,intTime);
			if(searchList!=null)
			{
			sessionMap.put("searchList",searchList);
			return SUCCESS;
			}
			else
				setSearchMessage("No routes Found..!!!");
				return "failed";
			}
		else
			return ERROR;
	}



	public String makeRequest()
	{
		if(sessionMap.get("user")!=null)
		{rcuid=((User)sessionMap.get("user")).getCuid();
		String bls=serviceObj.makeUserRequest(rcuid,ocuid,from,to,intTime,routeid);

			if(bls.equals("ok"))
			{	
				return SUCCESS;
			}
			else {
				return "failed";
			}
		}
		else {
			return ERROR;
		}



	}

	public String searchRoutes()
	{	//System.out.println("in searchAction-searchRoutes");

		if(sessionMap.get("user")!=null)
		{
			
			return SUCCESS;
		}
		else
		{return ERROR;

		}

	}

	public Long getRouteid() {
		return routeid;
	}
	public void setRouteid(Long routeid) {
		this.routeid = routeid;
	}




	public int getDashBoardStatus() {
		return dashBoardStatus;
	}

	public void setDashBoardStatus(int dashBoardStatus) {
		this.dashBoardStatus = dashBoardStatus;
	}

	public static int getIntTime() {
		return intTime;
	}

	public static void setIntTime(int intTime) {
		SearchAction.intTime = intTime;
	}

	public String getRcuid() {
		return rcuid;
	}

	public void setRcuid(String rcuid) {
		this.rcuid = rcuid;
	}


	public static Long getUserId() {
		return userId;

	}

	public static void setUserId(Long userId) {
		SearchAction.userId = userId;

	}


	public String getSearchMessage() {
		return searchMessage;
	}
	public void setSearchMessage(String searchMessage) {
		this.searchMessage = searchMessage;
	}
	public SearchAction() {

	}

	public String getOcuid() {
		return ocuid;
	}

	public void setOcuid(String ocuid) {
		this.ocuid = ocuid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public ArrayList<String> getTimeList() {
		return timeList;
	}

	public void setTimeList(ArrayList<String> timeList) {
		this.timeList = timeList;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}


	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public ArrayList<String> getToList() {
		return toList;
	}


	public void setToList(ArrayList<String> toList) {
		this.toList = toList;
	}


	public ArrayList<String> getFromList() {
		return fromList;
	}


	public void setFromList(ArrayList<String> fromList) {
		this.fromList = fromList;
	}

	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}





}
