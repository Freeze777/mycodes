
package centurylink.cpms;

import java.util.ArrayList;
import java.util.List;

import centurylink.cpms.User;

public interface ServiceInterface {
    //void makeConnection();

	//functions used for user info
	String registerUser(User user);
	String loginUser(User user);
	
	//String updateUser(User user);

	//void blockuser(User user);
	//void killConnection();
	
	//functions used for adding route
	String addUserRoutes(Routes routes,long cid);
	//String addUserTransit(Routes routes, Long cid);
	ArrayList<String> fillList();
	
	ArrayList<SearchArray> getSearchUserResult(String to, String from,int intTime);

	String makeUserRequest(String rcuid, String ocuid, String from, String to,int intTime, Long routeid);
	ArrayList<CpmsRequestLogs> getUserDashboard(Long cid);

//	String addUserSeats(Routes routes, Long cid);
	String grantUserRequest(String rcuid, String ocuid, Long routeid);
	String declineUserRequest(String rcuid, String ocuid, Long routeid);
	String getUserSeasts(Long cid);

	
	
}
