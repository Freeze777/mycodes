package centurylink.cpms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RouteAction extends ActionSupport implements ModelDriven,SessionAware {
	
	private Routes routes=new Routes();
	private static ServiceInterface serviceObj =new ServiceLayer();
	private Map<String, Object> sessionMap;
	private static ArrayList<String> toTimeList=new ArrayList<String>();
	private static ArrayList<String> fromTimeList=new ArrayList<String>();
	private String routeMessage;
	private static ArrayList<String> seatList;


	public String getRouteMessage() {
		return routeMessage;
	}


	public void setRouteMessage(String routeMessage) {
		this.routeMessage = routeMessage;
	}


	static{
		seatList = new ArrayList<String>();
		seatList.add("1");
		seatList.add("2");
		seatList.add("3");
		seatList.add("4");
		seatList.add("5");
		
	}

	static
	{
		
		toTimeList.add("06:30");
		toTimeList.add("07:00");
		toTimeList.add("07:30");	
		toTimeList.add("08:00");
		toTimeList.add("08:30");
		toTimeList.add("09:00");
		toTimeList.add("09:30");
		toTimeList.add("10:00");
		toTimeList.add("10:30");
		toTimeList.add("11:00");
		fromTimeList.add("17:00");
		fromTimeList.add("17:30");	
		fromTimeList.add("18:00");
		fromTimeList.add("18:30");
		fromTimeList.add("19:00");
		fromTimeList.add("19:30");
		fromTimeList.add("20:00");
		fromTimeList.add("20:30");
		fromTimeList.add("21:00");
		fromTimeList.add("21:30");
		fromTimeList.add("22:00");
		fromTimeList.add("22:30");
		
	}
	
	


	public String fillSearchList()
	{
		//System.out.println("RouteAction-Search Routes");

		//System.out.println((User)sessionMap.get("user"));
		User user = (User)sessionMap.get("user");
		
		if(sessionMap.get("user")!=null)
		{
		List toList = serviceObj.fillList();
		List fromList = serviceObj.fillList();
		return SUCCESS;
		}
		else
		return ERROR;
	
	}
	

	public String searchRoutes() {
	//System.out.println("RouteAction-Search Routes");

	////System.out.println((User)sessionMap.get("user"));
	//User user = (User)sessionMap.get("user");
	
	if(sessionMap.get("user")!=null)
	{
//	List list = serviceObj.searchUserRoutes();
		return SUCCESS;
	}
	else
		return ERROR;
}
	
	

public String updateRoutes()
{//System.out.println("RouteAction-Update Routes");



if(sessionMap.get("user")!=null)
{

	return SUCCESS;
}
else
	return ERROR;
	
}


	public String addRoute() {
		//System.out.println("RouteAction-add route");
		//System.out.println((User)sessionMap.get("user"));
		User user = (User)sessionMap.get("user");
		
		///to be refined
		
		if(sessionMap.get("user")!=null)
		{
				String bls=serviceObj.addUserRoutes(routes,user.getCid());
//				String blsTransit = serviceObj.addUserTransit(routes,user.getCid());
//				String blsSeats = serviceObj.addUserSeats(routes,user.getCid());
				
			if (bls.equals("ok")) {
				//sessionMap.put("routes", routes);
				
				setRouteMessage("Route was Added Successfully");
				return SUCCESS;
			}
			else if (bls.equals("iat")) {
				//ssetMessage("The CUID is already registered");
				return "iat";
	
			}	else {
				//setMessage("Registeration Failed. PLease Retry");
				return ERROR;
			}
		}		
		else
			return ERROR;
	
	}

	@Override
	public void setSession(Map sessionMap) {
		// TODO Auto-generated method stub
		this.sessionMap=sessionMap;
	}
	

	
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return routes;
	}


	public static ArrayList<String> getSeatList() {
		return seatList;
	}
	public static void setSeatList(ArrayList<String> seatList) {
		RouteAction.seatList = seatList;
	}
	
	public RouteAction() {
		//super();
		// TODO Auto-generated constructor stub
	}

	public Routes getRoutes() {
		return routes;
	}

	public void setRoutes(Routes routes) {
		this.routes = routes;
	}

	public static ArrayList<String> getToTimeList() {
		return toTimeList;
	}



	public static void setToTimeList(ArrayList<String> toTimeList) {
		RouteAction.toTimeList = toTimeList;
	}



	public static ArrayList<String> getFromTimeList() {
		return fromTimeList;
	}



	public static void setFromTimeList(ArrayList<String> fromTimeList) {
		RouteAction.fromTimeList = fromTimeList;
	}

	

}
