package centurylink.cpms;

import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

public class HibernateOps {

	/* Method to CREATE an cpms_user in the database */
	public void addCpmsUser(User user, SessionFactory factory) throws CpmsConstraintViolation,CpmsException  {
		//////System.out.println("HibernateOps-addCmpsUser");
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			CpmsUser cpms_user = new CpmsUser(user);

			user.setCid((Long)session.save(cpms_user));
			tx.commit();
		}catch (ConstraintViolationException e) {
			if (tx != null)
				tx.rollback();
			throw new CpmsConstraintViolation("Registration failed");
			// TODO: handle exception
		}
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw new HibernateException("Registration failed");
		}catch (Exception e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			throw new CpmsException("Registration failed");
		}   
		finally {
			session.close();
		}

		//////System.out.println("inserted successfully");

	}

	public String selectCpmsUser(User user, SessionFactory factory)throws HibernateException, CpmsException {
		// TODO Auto-generated method stub
		//////System.out.println("HibernateOPs-selectCpmsuser");
		Session session = factory.openSession();
		Transaction tx = null;
		String cuid =user.getCuid().trim();
		int password = user.getPassword().trim().hashCode();
		CpmsUser cpmsuser = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM CpmsUser WHERE cuid=:cuid");
			//////System.out.println(query);
			query.setParameter("cuid", cuid);
			cpmsuser = (CpmsUser) query.uniqueResult();
			//////System.out.println(cpmsuser);

			if (cpmsuser != null) {
				if(cpmsuser.getPassword()==password)
				{	user.setCid(cpmsuser.getCid());
				return cpmsuser.getName();
				}
			}
			tx.commit();
		} 
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw new HibernateException("Login failed");

		}catch (Exception e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			throw new CpmsException("Login failed");
		}    
		finally {
			session.close();
		}

		return null;

	}


	//UPDATE or ADD Routes

	public void addCpmsRoutes(Routes routes, SessionFactory factory,long cid)throws HibernateException, CpmsException  {

		// TODO Auto-generated method stub
		//////System.out.println("HibernateOps-addCmpsRoutes");
		Session session = factory.openSession();
		Transaction tx = null;
		//////System.out.println(routes);
		try {
			tx = session.beginTransaction();
			Criteria cr = (Criteria)session.createCriteria(CpmsRoutes.class);
			cr.add(Restrictions.eq("cid",cid));

			//if block for updating the database
			if(cr.list() != null)
			{

				//////System.out.println("IN UPDATE ROUTE");

				ArrayList<CpmsRoutes> routeRows = new ArrayList<CpmsRoutes>();
				routeRows = (ArrayList<CpmsRoutes>)cr.list();

				ArrayList<Long> routeIdList = new ArrayList<Long>();

				for (int i = 0; i < routeRows.size(); i++)
				{
					CpmsRoutes routeToDelete = routeRows.get(i);
					session.delete(routeToDelete);
					routeIdList.add(routeToDelete.getRouteid());
				}


				//update the route status in request logs table
				//////System.out.println("HibernateOps-addCmpsRoutes-Delete Routes");
				Criteria crRequest = (Criteria)session.createCriteria(CpmsRequestLogs.class);
				crRequest.add(Restrictions.eq("cid", cid)).add(Restrictions.in("routeid", routeIdList));
				ArrayList<CpmsRequestLogs> requestLogs = new ArrayList<CpmsRequestLogs>();
				
				for (int i=0;i<requestLogs.size();i++)
				{	CpmsRequestLogs request = new CpmsRequestLogs();
					request = (CpmsRequestLogs)crRequest.list().get(i);
					request.setRequeststatus(3);
					session.save(request);
				}




				//add routes
				//////System.out.println("HibernateOps-addCmpsRoutes-Add Routes");
				CpmsRoutes cpmsRouteTo = new CpmsRoutes(cid, routes.getToSource().toLowerCase(), routes.getToDestination().toLowerCase(),
						1, CpmsUtility.stringTimeToInt(routes.getToOfficeStartTime()), CpmsUtility.stringTimeToInt(routes.getToOfficeEndTime()), 0);

				CpmsRoutes cpmsRouteAway = new CpmsRoutes(cid, routes.getFromSource().toLowerCase(), routes.getFromDestination().toLowerCase(), 1,
						CpmsUtility.stringTimeToInt(routes.getFromOfficeStartTime()), CpmsUtility.stringTimeToInt(routes.getFromOfficeEndTime()), 1);

				routes.setToRouteId((Long)session.save(cpmsRouteTo));
				routes.setFromRouteId((Long)session.save(cpmsRouteAway));


				//add transits
				//////System.out.println("HibernateOps-addCmpsRoutes-Add Routes");
				String[] toTrasitsArray = routes.getTowardsTransits().split("\\,");
				String[] fromTrasitsArray = routes.getFromTransits().split("\\,");
				int toLength = toTrasitsArray.length;
				int fromLength = fromTrasitsArray.length;

				CpmsTransits cpmsTransit = new CpmsTransits(cid,routes.getToRouteId(),routes.getToSource(),0);
				session.save(cpmsTransit);

				for(int i =0; i<toLength; i++)
				{
					CpmsTransits cpmsTransitTo = new CpmsTransits(cid, routes.getToRouteId(), toTrasitsArray[i],0);

					session.save(cpmsTransitTo);
					//////System.out.println(cpmsTransitTo);
				}
				CpmsTransits cpmsTransitFromSource = new CpmsTransits(cid,routes.getFromRouteId(),routes.getFromDestination(),1);
				session.save(cpmsTransitFromSource);
				//////System.out.println(cpmsTransitFromSource);

				for(int i =0; i<fromLength; i++)
				{
					CpmsTransits cpmsTransitFrom = new CpmsTransits(cid, routes.getFromRouteId(), fromTrasitsArray[i],1);
					session.save(cpmsTransitFrom);
					//////System.out.println(cpmsTransitFrom);
				}



				//add seats
				//////System.out.println("HibernateOps-addCmpsRoutes-Add Seats");
				CpmsSeats cpmsToSeats = new CpmsSeats(cid, routes.getToRouteId(), Integer.parseInt(routes.getToOfficeSeats()), 0);
				session.save(cpmsToSeats);

				CpmsSeats cpmsFromSeats = new CpmsSeats(cid, routes.getFromRouteId(), Integer.parseInt(routes.getFromOfficeSeats()), 1);
				session.save(cpmsFromSeats);


			}

			//else block for adding a new route
			else
			{
				//add routes
				CpmsRoutes cpmsRouteTo = new CpmsRoutes(cid, routes.getToSource(), routes.getToDestination(), 1, CpmsUtility.stringTimeToInt(routes.getToOfficeStartTime()), CpmsUtility.stringTimeToInt(routes.getToOfficeEndTime()), 0);
				CpmsRoutes cpmsRouteAway = new CpmsRoutes(cid, routes.getFromSource(), routes.getFromDestination(), 1, CpmsUtility.stringTimeToInt(routes.getFromOfficeStartTime()), CpmsUtility.stringTimeToInt(routes.getFromOfficeEndTime()), 1);

				routes.setToRouteId((Long)session.save(cpmsRouteTo));
				routes.setFromRouteId((Long)session.save(cpmsRouteAway));

				//////System.out.println("HibernateOps-addCmpsRoutes-Add Routes");
				String[] toTrasitsArray = routes.getTowardsTransits().split("\\,");
				String[] fromTrasitsArray = routes.getFromTransits().split("\\,");
				int toLength = toTrasitsArray.length;
				int fromLength = fromTrasitsArray.length;

				CpmsTransits cpmsTransit = new CpmsTransits(cid,routes.getToRouteId(),routes.getToSource(),0);
				session.save(cpmsTransit);

				for(int i =0; i<toLength; i++)
				{
					CpmsTransits cpmsTransitTo = new CpmsTransits(cid, routes.getToRouteId(), toTrasitsArray[i],0);

					session.save(cpmsTransitTo);
					//////System.out.println(cpmsTransitTo);
				}
				CpmsTransits cpmsTransitFromSource = new CpmsTransits(cid,routes.getFromRouteId(),routes.getFromDestination(),1);

				session.save(cpmsTransitFromSource);
				//////System.out.println(cpmsTransitFromSource);

				for(int i =0; i<fromLength; i++)
				{
					CpmsTransits cpmsTransitFrom = new CpmsTransits(cid, routes.getFromRouteId(), fromTrasitsArray[i],1);
					session.save(cpmsTransitFrom);
					//////System.out.println(cpmsTransitFrom);
				}

				//add seats
				CpmsSeats cpmsToSeats = new CpmsSeats(cid, routes.getToRouteId(), Integer.parseInt(routes.getToOfficeSeats()), 0);
				session.save(cpmsToSeats);

				CpmsSeats cpmsFromSeats = new CpmsSeats(cid, routes.getFromRouteId(), Integer.parseInt(routes.getFromOfficeSeats()), 1);
				session.save(cpmsFromSeats);

			}


			tx.commit();
		}catch (HibernateException e) {
			if (tx != null)
				tx.rollback();

			throw new HibernateException("Add route failed:DB issues");
			//e.printStackTrace();
		}
		catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new CpmsException("Add route failed:Error ");
			//e.printStackTrace();
		} 
		finally {
			session.close();
		}

		//////System.out.println("route inserted successfully");

	}






	public ArrayList<String> fillCpmsTransitList( SessionFactory factory) throws CpmsException {
		// TODO Auto-generated method stub
		//////System.out.println("HibernateOPs-fillCpmsTransits");
		Session session = factory.openSession();
		Transaction tx = null;
		ArrayList<String> list = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = (Criteria)session.createCriteria(CpmsTransits.class);
			cr.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("transit"))));
			list = (ArrayList<String>) cr.list();
			tx.commit();
		}		
		catch (HibernateException e) {
			// TODO Auto-generated catch block
			if (tx != null)
				tx.rollback();
			throw new HibernateException("Dropdown fetch failed");
		}catch (Exception e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			throw new CpmsException("Dropdown fetch failed");
		}
		finally {
			session.close();
		}
		//////System.out.println(list);
		return list;
	}

	public ArrayList<SearchArray> searchCpmsRoutes(String to,String from,int time,SessionFactory factory) throws HibernateException, NoRoutesFoundException, CpmsException
	{
		//////System.out.println("HibernateOPs-searchCpmsTransits");
		Session session = factory.openSession();
		Transaction tx = null;
		ArrayList<Integer> routeList = new ArrayList<Integer>();
		ArrayList<SearchArray> searchArrayList = new ArrayList<SearchArray>();
		SearchArray searchArrayObj = new SearchArray();
	

		try {
			tx = session.beginTransaction();
			Criteria cr = (Criteria)session.createCriteria(CpmsTransits.class);
			if((to.equals("CTLI"))&&(from.equals("CTLI")))
			{
				throw new NoRoutesFoundException("Search Failed");
				
			}
			else if(to.equals("CTLI"))
			{
				cr.add(Restrictions.eq("type", 0)).add(Restrictions.like("transit",from));
				cr.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("routeid"))));
				routeList = (ArrayList<Integer>) cr.list();
			}

			else if(from.equals("CTLI"))
			{
				cr.add(Restrictions.eq("type", 1)).add(Restrictions.like("transit",to));
				cr.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("routeid"))));
				routeList = (ArrayList<Integer>) cr.list();
			}
			
			else
			{
				
				throw new NoRoutesFoundException("Search Failed");
			}

			Criteria crRoutes = (Criteria)session.createCriteria(CpmsRoutes.class);

			crRoutes.add(Restrictions.in("routeid", routeList)).add(Restrictions.between("sourcetime",(time-2000),time));

			ArrayList<CpmsRoutes> routesArray = (ArrayList<CpmsRoutes>)crRoutes.list();
			
			int numberOfRoutes = routesArray.size();

			for (int i=0;i<numberOfRoutes;i++) 
			{
				CpmsRoutes cpmsRoutes =  routesArray.get(i);
				searchArrayObj.setSource(cpmsRoutes.getSource());
				searchArrayObj.setDestination(cpmsRoutes.getDestination());
				
				
				
				searchArrayObj.setStartTime(CpmsUtility.intTimeToString(((Integer)cpmsRoutes.getSourcetime())));
				searchArrayObj.setRouteid(cpmsRoutes.getRouteid());
				CpmsUser cpmsUser = (CpmsUser) session.get(CpmsUser.class, cpmsRoutes.getCid());
				Criteria crSeats = (Criteria)session.createCriteria(CpmsSeats.class);
				crSeats.add(Restrictions.eq("routeid",cpmsRoutes.getRouteid()));
				crSeats.setProjection((Projections.property("seats")));
			
				//////System.out.println(((Integer) crSeats.list().get(0)));
				searchArrayObj.setSeats(((Integer) crSeats.list().get(0)));
				searchArrayObj.setCuid(cpmsUser.getCuid());
				searchArrayObj.setName(cpmsUser.getName());

				searchArrayList.add(searchArrayObj);


			}
			tx.commit();
		}	
		catch (HibernateException e) {
			// TODO Auto-generated catch block
			if (tx != null)
				tx.rollback();
		throw new HibernateException("search failed");
		}catch (Exception e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			throw new CpmsException("search failed");
		}
		finally {
			session.close();
		}
		return searchArrayList;

	}



	public void addRequestToLog(String rcuid, String ocuid, String from,
			String to,int time,Long routeid,SessionFactory factory) throws CpmsException {
		// TODO Auto-generated method stub
		int status=0;
		//////System.out.println("HibernateOps-addRequesttoLog");
		Session session = factory.openSession();
		Transaction tx = null;
		Long rcid=null;
		Long ocid=null;
		try {
			tx = session.beginTransaction();
			Criteria crUser = (Criteria)session.createCriteria(CpmsUser.class);
			crUser.add(Restrictions.like("cuid", rcuid));
			crUser.setProjection((Projections.property("cid")));
			rcid=(Long)crUser.list().get(0);
			Criteria crUser1 = (Criteria)session.createCriteria(CpmsUser.class);
			crUser1.add(Restrictions.like("cuid", ocuid));
			crUser1.setProjection((Projections.property("cid")));
			ocid=(Long)crUser1.list().get(0);
			/*public CpmsRequestLogs(Long rcid, Long ocid, int requeststatus,
					String source, String destination, int time) {*/
			CpmsRequestLogs	cpms_request_logs=new CpmsRequestLogs(rcid,ocid,status,from,to,time,routeid);
			session.save(cpms_request_logs);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw new HibernateException("Logging db failed");
		}
		catch (Exception e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			throw new CpmsException("Logging db failed");
		}
		finally {
			session.close();
		}

		//////System.out.println("inserted successfully");



	}

	public ArrayList<CpmsRequestLogs> getDashBoardfromDB(Long cid,SessionFactory factory) throws CpmsException {
		// TODO Auto-generated method stub


		//////System.out.println("HibernateOps-getDashboard");
		Session session = factory.openSession();
		Transaction tx = null;
		ArrayList<CpmsRequestLogs> crList=null;

		try {
			tx = session.beginTransaction();

			
			Criteria crReq = (Criteria)session.createCriteria(CpmsRequestLogs.class);
			Criteria crReq1 = (Criteria)session.createCriteria(CpmsRequestLogs.class);
			//Criteria crReq2 = (Criteria)session.createCriteria(CpmsRequestLogs.class);
	

			crReq.add(Restrictions.eq("ocid",cid));
			crList = (ArrayList<CpmsRequestLogs>)crReq.list();
			crReq1.add(Restrictions.eq("rcid",cid));
			ArrayList<CpmsRequestLogs>	crList1=(ArrayList<CpmsRequestLogs>)crReq1.list();
			crList.addAll(crList1);
		

			int reqstatus=0;

			for (Iterator iterator = crList.iterator(); iterator.hasNext();) {


				CpmsRequestLogs cpmsRequestLogs = (CpmsRequestLogs) iterator.next();
				cpmsRequestLogs.setRcuid(getCpmsCuid(cpmsRequestLogs.getRcid(), factory));
				cpmsRequestLogs.setOcuid(getCpmsCuid(cpmsRequestLogs.getOcid(), factory));
				cpmsRequestLogs.setRtime(CpmsUtility.intTimeToString(cpmsRequestLogs.getTime()));
				reqstatus=cpmsRequestLogs.getRequeststatus();
				if(reqstatus==0)
				{cpmsRequestLogs.setStatus("Pending");
				}	
				else if(reqstatus==1)
				{
					cpmsRequestLogs.setStatus("Accepted");
				}
				else if(reqstatus==2)
				{
					cpmsRequestLogs.setStatus("Rejected");
				}else if(reqstatus==3)
				{
					cpmsRequestLogs.setStatus("Route no more exists");
				}



			}

			tx.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			if (tx != null)
				tx.rollback();
			throw new HibernateException("dashboard fetch failed");
		}catch (Exception e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			throw new CpmsException("dashborad fetch failed");
		}
		finally {
			session.close();
		}
		return crList;

	}




	public void grantCmpsRequest(String rcuid, String ocuid,
			Long routeid, SessionFactory factory) throws CpmsException {
		// TODO Auto-generated method stub
		//////System.out.println("HibernateOps-grantCmpsRequest");
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			//Change the request status
			Long rcid = getCpmsCid(rcuid, factory);
			Long ocid = getCpmsCid(ocuid, factory);
			Criteria cr = (Criteria)session.createCriteria(CpmsRequestLogs.class);
			cr.add(Restrictions.eq("ocid", ocid)).add(Restrictions.eq("rcid", rcid)).add(Restrictions.eq("routeid", routeid));

			CpmsRequestLogs request = (CpmsRequestLogs)cr.list().get(0);
			request.setRequeststatus(1);
			session.save(request);
			

			//Change,dec the number of seats 
			Criteria crSeats = (Criteria)session.createCriteria(CpmsSeats.class);
			crSeats.add(Restrictions.eq("routeid", routeid));
			CpmsSeats seats = (CpmsSeats)crSeats.list().get(0);
			int nofseats = seats.getSeats();
			nofseats--;
			seats.setSeats(nofseats);
			session.save(seats);

			tx.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			if (tx != null)
				tx.rollback();
			throw new HibernateException("grant request failed");
		}catch (Exception e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			throw new CpmsException("grant request failed");
		}
		finally {
			session.close();
		}

	}

	public void declineCpmsRequest(String rcuid, String ocuid,
			Long routeid,SessionFactory factory) throws CpmsException {
		// TODO Auto-generated method stub
		//////System.out.println("HibernateOps-decineCmpsRequest");
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			//Change the request status
			Long rcid = getCpmsCid(rcuid, factory);
			Long ocid = getCpmsCid(ocuid, factory);
			Criteria cr = (Criteria)session.createCriteria(CpmsRequestLogs.class);
			cr.add(Restrictions.eq("ocid", ocid)).add(Restrictions.eq("rcid", rcid)).add(Restrictions.eq("routeid", routeid));

			CpmsRequestLogs request = (CpmsRequestLogs)cr.list().get(0);
			request.setRequeststatus(2);
			session.save(request);

			


			tx.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			if (tx != null)
				tx.rollback();
			throw new HibernateException("decline request failed");
		}catch (Exception e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			throw new CpmsException("decline request failed");
		}
		finally {
			session.close();
		}

	}



	public Long getCpmsCid(String cuid,SessionFactory factory) throws CpmsException
	{
	Session session = factory.openSession();
	Transaction tx = null;
	Long cid = new Long(0);
	try {
		tx = session.beginTransaction();
		Criteria cr = (Criteria)session.createCriteria(CpmsUser.class);
		cr.add(Restrictions.like("cuid", cuid));
		//////System.out.println(cuid);
		cid = (Long)(((CpmsUser)cr.list().get(0)).getCid());
		tx.commit();
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		if (tx != null)
			tx.rollback();
		throw new HibernateException("getCID failed");
	}catch (Exception e) {
		// TODO: handle exception
		if (tx != null)
			tx.rollback();
		throw new CpmsException("getCID failed");
	}
	finally {
		session.close();
	}

	return cid;
	}



	public String getCpmsCuid(Long cid,SessionFactory factory) throws CpmsException

	{
		Session session = factory.openSession();
		Transaction tx = null;
		String cuid = null;
		try {
			tx = session.beginTransaction();
			CpmsUser user =  (CpmsUser)session.get(CpmsUser.class,cid);

			cuid = user.getCuid();
			tx.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			if (tx != null)
				tx.rollback();
			throw new HibernateException("getCuid failed");
		}
		catch (Exception e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			throw new CpmsException("getCuid failed");
		}finally {
			session.close();
		}
		return cuid;
	}

	public ArrayList<String> getCpmsSeats(Long cid, SessionFactory factory) throws CpmsException {
		// TODO Auto-generated method stub
		ArrayList<String> seatString =new ArrayList<String>();
		Session session = factory.openSession();
		
		Transaction tx = null;
		String cuid = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = (Criteria)session.createCriteria(CpmsRoutes.class);
			cr.add(Restrictions.eq("cid",cid));
			
			Criteria crSeats = (Criteria)session.createCriteria(CpmsSeats.class);
			crSeats.add(Restrictions.eq("cid",cid));
			
			ArrayList<CpmsRoutes> routes = new ArrayList<CpmsRoutes>();
			routes=(ArrayList<CpmsRoutes>)cr.list();
			
			ArrayList<CpmsSeats> seatArray = new ArrayList<CpmsSeats>();
			seatArray=(ArrayList<CpmsSeats>)crSeats.list();
			
			//TODO improve the logic to get seats
			
			seatString.add(routes.get(0).getSource());
			
			Integer towardsSeat = seatArray.get(0).getSeats();
			seatString.add(towardsSeat.toString());
			
			seatString.add(routes.get(1).getDestination());
			
			Integer fromSeat = seatArray.get(1).getSeats();
			seatString.add(fromSeat.toString());
			
			tx.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			if (tx != null)
				tx.rollback();
			throw new HibernateException("getSeats failed");
		}
		catch (Exception e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
		//	e.printStackTrace();
			throw new CpmsException("getseats failed");
		}finally {
			session.close();
		}
		return seatString;
	}

}


//	public static void main(String[] args) {
//		HibernateConnection.createConnection();
//		HibernateOps hb = new HibernateOps();
//		hb.fillCpmsTransitList(0, HibernateConnection.getFactory());
//	}


