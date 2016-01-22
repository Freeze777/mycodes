package centurylink.cpms;

import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.HibernateException;

public class DAOLayer implements DAOInterface {


	public void storeUserDetailsInDB(User user) throws CpmsConstraintViolation, CpmsException {
	
		//System.out.println("storeUserDetails-in DAO");
		HibernateOps hb=new HibernateOps();
		hb.addCpmsUser(user,HibernateConnection.getFactory());

	}


	public DAOLayer() {
		try {
			makeDBconnection();
		} catch (IllegalArgumentException e) {

//			e.printStackTrace();
		} catch (SQLException e) {
	
	//		e.printStackTrace();
		}

	}


	public String retrieveDetailsInDB(User user) throws HibernateException, CpmsException {
//		System.out.println("retrieveUserDetails-in DAO");
		HibernateOps hb=new HibernateOps();
		return hb.selectCpmsUser(user, HibernateConnection.getFactory());

	}


	@Override
	public void makeDBconnection() throws SQLException,
	IllegalArgumentException {

		HibernateConnection.createConnection();


	}



	@Override
	public void storeRoutesDetailsInDB(Routes routes,long cid) throws  HibernateException, CpmsException {

	
	//	System.out.println("storeRouteDetails-in DAO");
	
		HibernateOps hb=new HibernateOps();
		try {
			hb.addCpmsRoutes(routes,HibernateConnection.getFactory(),cid);
		} catch (Exception e) {
	
//			e.printStackTrace();
		}





	}



	@Override
	public ArrayList<String> getTransitListFromDB() throws  CpmsException {
	
		//System.out.println("getTransitListFromDB-in DAO");
		HibernateOps hb=new HibernateOps();
		ArrayList<String> list =null;
		list = hb.fillCpmsTransitList(HibernateConnection.getFactory());
		
		return list;
	}


	@Override
	public ArrayList<SearchArray> getUserSearchResultsFromDB(String to,
			String from, int time) throws HibernateException, NoRoutesFoundException, CpmsException {
	
	//	System.out.println("getTransitListFromDB-in DAO");
		HibernateOps hb=new HibernateOps();
		ArrayList<SearchArray> list =null;
		
		
			list = hb.searchCpmsRoutes(to, from, time, HibernateConnection.getFactory());
		
			return list;
	}



	@Override
	public String makeUserRequestinDB(String rcuid, String ocuid, String from,
			String to,int time,Long routeid) throws CpmsException {
	
//		System.out.println("makeRequestinDB-in DAO");
		
		HibernateOps hb=new HibernateOps();
		hb.addRequestToLog(rcuid,ocuid,from,to,time,routeid,HibernateConnection.getFactory());
		return "ok";
	}
	
	

	@Override
	public ArrayList<CpmsRequestLogs> getDashboardinDB(Long cid) throws CpmsException {

		
		//System.out.println("getDashboardinDB-in DAO");
		
		HibernateOps hb=new HibernateOps();
		return hb.getDashBoardfromDB(cid,HibernateConnection.getFactory());
	}




	@Override
	public void acceptRequestIntoDB(String rcuid, String ocuid, Long routeid)
			throws  CpmsException {
		
		HibernateOps hb=new HibernateOps();
		hb.grantCmpsRequest(rcuid,ocuid,routeid,HibernateConnection.getFactory());
		
	}


	@Override
	public void declineRequestIntoDB(String rcuid, String ocuid, Long routeid)
			throws  CpmsException {
		
		HibernateOps hb=new HibernateOps();
		hb.declineCpmsRequest(rcuid,ocuid,routeid,HibernateConnection.getFactory());
		
	}


	@Override
	public ArrayList<String> getSeatsFromDB(Long cid) throws  CpmsException{
		// TODO Auto-generated method stub
		HibernateOps hb=new HibernateOps();
		ArrayList<String> seats=hb.getCpmsSeats(cid,HibernateConnection.getFactory());
		return seats;
	}



}
