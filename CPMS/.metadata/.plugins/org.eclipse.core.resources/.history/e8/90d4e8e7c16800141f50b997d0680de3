package centurylink.cpms;






import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.net.httpserver.Authenticator.Failure;

public class CpmsAction extends ActionSupport
implements SessionAware,ModelDriven {
	private User user = new User();	
	private String message;
	private static ServiceInterface serviceObj =new ServiceLayer();
	private Map<String,Object> sessionMap;
	
	
	
	
	@Override
	public void setSession(Map sessionMap) {	
		this.sessionMap=sessionMap; 
	}

	public String homepage(){
		
		return "login";
		
		
	}
	
	
	
	public String registerUser() {
		
		
		//System.out.println("registerUser-Cpms action");
		
		String bls=serviceObj.registerUser(user);
		if (bls.equals("ok")) {
			sessionMap.put("user",user);
			sessionMap.put("username","Welcome  "+user.getName());
			return SUCCESS;
		}
		else if (bls.equals("iat")) {
			setMessage("The CUID is already registered");
			return "iat";

		}	else {
			setMessage("Registeration Failed. Please Retry");
			return ERROR;
		}
		}

	public String validateLogin()  {

		//System.out.println(" validatelogin function");
		String  userName= serviceObj.loginUser(user);
		if (userName==null){ 
		setMessage("Invalid CUID/Password");
		return ERROR;
		}
		else if(userName.equals("nok")){
		
			setMessage("Invalid CUID/Password");
			return ERROR;
			
		}			
		else {
			
			user.setPassword(((Integer)user.getPassword().hashCode()).toString());
			sessionMap.put("user",user);
			sessionMap.put("username","Welcome  "+userName );
			return SUCCESS;
		}
		



	}
	public String signup()  {
			
		return SUCCESS;

	}
	
	
	public String logoutUser(){	
		
		//System.out.println("logged out");
		sessionMap.remove("user");	
		sessionMap.remove("username");
		sessionMap.clear();
		//System.out.println(sessionMap.get("user"));
		setMessage("Logged out successfully");
		return SUCCESS;
		
	}

	@Override

	public Object getModel() {
		
		return user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	




}
