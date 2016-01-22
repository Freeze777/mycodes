package centurylink.cpms;

import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.HibernateException;

import com.sun.net.httpserver.Authenticator.Success;

import centurylink.cpms.User;



public class ServiceLayer implements ServiceInterface {

	private static DAOInterface daoObj=new DAOLayer();

	//register user will return 
	/*
	 * fp  -- freak problem  -- datbase failed
	 * iat  -- id taken  -- primary key violated
	 * ok  -- everything ok  -- everything ok
	 * nok-- sumthng went wrong-- not ok
	 * 
	 */
	public String registerUser(User user) {
		// TODO Auto-generated method stub
		//System.out.println("registeruser-in servicelayer");
		String bls ="fp";

		try {
			daoObj.storeUserDetailsInDB(user);
			bls  ="ok";
		}catch (CpmsConstraintViolation e) {
			// TODO Auto-generated catch block
			bls="iat";
		}catch (HibernateException e) {
			// TODO: handle exception
			bls="fp";
		} 
		catch (CpmsException e) {
			// TODO Auto-generated catch block
		bls="nok";
		}
		return bls;
	}

	public String loginUser(User user) {
		// TODO Auto-generated method stub
		//System.out.println("in loginuser-in servicelayer");
		String userName="nok";
		
		try {
			userName=daoObj.retrieveDetailsInDB(user);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			userName="nok";
		} catch (CpmsException e) {
			// TODO Auto-generated catch block
			userName="nok";
		}catch (Exception e) {
			// TODO: handle exception
		userName="nok";
		}
		return userName;
	}

	@Override
	public String addUserRoutes(Routes routes,long cid) {
		// TODO Auto-generated method stub
		//System.out.println("in addUserRoutes");
		String bls = "fp";

		try {
			daoObj.storeRoutesDetailsInDB(routes,cid);
			bls  ="ok";
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		bls="fp";
		} catch (CpmsException e) {
			// TODO Auto-generated catch block
			bls="nok";
		}

		return bls;
	}

	
	@Override
	public ArrayList<String> fillList() {
		// TODO Auto-generated method stub
		ArrayList<String> list =null;

		try {
			list = daoObj.getTransitListFromDB();
		}  catch (CpmsException e) {
			// TODO Auto-generated catch block
		return null;
		}

		return list;
	}

	@Override
	public ArrayList<SearchArray> getSearchUserResult(String to, String from,int time) {
		// TODO Auto-generated method stub
		ArrayList<SearchArray> list =null;
		try {
			list=daoObj.getUserSearchResultsFromDB(to,from,time);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			list=null;
		}  catch (NoRoutesFoundException e) {
			// TODO Auto-generated catch block
			list=null;
		} catch (CpmsException e) {
			// TODO Auto-generated catch block
			list=null;
		}
		return list;
	}







	@Override

	public String makeUserRequest(String rcuid, String ocuid, String from,String to,int time,Long routeid) {


		String bls="ok";
		try {
			bls = daoObj.makeUserRequestinDB( rcuid, ocuid,  from, to,time,routeid);
			bls="ok";
		} catch (CpmsException e) {
			// TODO Auto-generated catch block
			bls="nok";
		}
		return bls;

	}


	@Override
	public ArrayList<CpmsRequestLogs> getUserDashboard(Long cid) {
		// TODO Auto-generated method stub
		ArrayList<CpmsRequestLogs> list=null;

		try {
			list=daoObj.getDashboardinDB(cid);
		} catch (CpmsException e) {
			// TODO Auto-generated catch block
			list=null;
		}

		return list;
	}

	@Override
	public String grantUserRequest(String rcuid, String ocuid,Long routeid) {
		// TODO Auto-generated method stub
		String bls="ok";
		try {
			
				daoObj.acceptRequestIntoDB(rcuid,ocuid,routeid);
				bls= "ok";
			} catch (CpmsException e) {
				// TODO Auto-generated catch block
				bls= "nok";
				
			}
		return bls;
			
		} 

	

	@Override
	public String declineUserRequest(String rcuid, String ocuid,Long routeid) {
		// TODO Auto-generated method stub
		String bls="ok";
		try {
			daoObj.declineRequestIntoDB(rcuid,ocuid,routeid);
		bls="ok";
		}catch (CpmsException e) {
			// TODO Auto-generated catch block
		bls="nok";
		}
		return bls;

	}

	@Override
	public String getUserSeasts(Long cid) {
		// TODO Auto-generated method stub
		String bls="ok";
		try {
			ArrayList<String> seatString=daoObj.getSeatsFromDB(cid);
			bls = "From "+seatString.get(0)+" Towards CTLI Seats Left: "+seatString.get(1)+"  ||  "+" From CTLI Towards "+seatString.get(2)+" Seats Left: "+seatString.get(3);
		
		}catch (CpmsException e) {
			// TODO Auto-generated catch block
		bls=" ";
		}
		return bls;

	}








}
