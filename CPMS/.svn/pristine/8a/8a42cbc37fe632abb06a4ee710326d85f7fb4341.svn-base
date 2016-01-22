package centurylink.cpms;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class HandleRequestAction extends ActionSupport implements SessionAware {

	private static ServiceInterface serviceObj =new ServiceLayer();
	private String ocuid;
	private String rcuid;
	private Long routeid;
	private Map<String,Object> sessionMap;


	public  String acceptRequest() {

		if(sessionMap.get("user")!=null)
		{		
			ocuid=((User)sessionMap.get("user")).getCuid();
			String bls = serviceObj.grantUserRequest(rcuid,ocuid,routeid);
			return SUCCESS;
		}
		else {
			return ERROR;
		}
	}

	public  String rejectRequest() {

		if(sessionMap.get("user")!=null)
		{
			ocuid=((User)sessionMap.get("user")).getCuid();
			String bls = serviceObj.declineUserRequest(rcuid,ocuid,routeid);
			return SUCCESS;
		}
		else {
			return ERROR;
		}
	}
	@Override
	public void setSession(Map sessionMap) {
		// TODO Auto-generated method stub
		this.sessionMap=sessionMap;

	}


	public String getOcuid() {
		return ocuid;
	}
	public void setOcuid(String ocuid) {
		this.ocuid = ocuid;
	}
	public String getRcuid() {
		return rcuid;
	}
	public void setRcuid(String rcuid) {
		this.rcuid = rcuid;
	}
	public Long getRouteid() {
		return routeid;
	}
	public void setRouteid(Long routeid) {
		this.routeid = routeid;
	}



}
