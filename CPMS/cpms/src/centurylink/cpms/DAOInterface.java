package centurylink.cpms;

import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.HibernateException;

import centurylink.cpms.User;

public interface DAOInterface {

 public void storeUserDetailsInDB(User user)throws CpmsConstraintViolation, CpmsException ;
public String retrieveDetailsInDB(User user) throws HibernateException, CpmsException ;
public void makeDBconnection()throws SQLException,
IllegalArgumentException ;
public void storeRoutesDetailsInDB(Routes routes,long cid) throws  HibernateException, CpmsException;




public ArrayList<String> getTransitListFromDB() throws  CpmsException  ;

public ArrayList<SearchArray> getUserSearchResultsFromDB(String to,
		String from, int time) throws HibernateException, CpmsException, NoRoutesFoundException;

public String makeUserRequestinDB(String rcuid, String ocuid, String from,
		String to,int time, Long routeid)throws CpmsException ;
public ArrayList<CpmsRequestLogs> getDashboardinDB(Long cid)throws CpmsException;


public void acceptRequestIntoDB(String rcuid, String ocuid, Long routeid)throws CpmsException;
public void declineRequestIntoDB(String rcuid, String ocuid, Long routeid)throws CpmsException;
public ArrayList<String> getSeatsFromDB(Long cid)throws CpmsException;





}
