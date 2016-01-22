package centurylink.cpms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MakeCon {
	
	public static Connection r=null;
	
	public static Connection get() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
		System.out.println("before connecting");
		
		String url ="jdbc:oracle:thin:@10.140.4.104:1578:training";
		String userid ="training";
		String password= "training2013";
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			 r = DriverManager.getConnection(url,userid,password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			throw new IllegalArgumentException("cng");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
			throw new IllegalArgumentException("cng cnf");
		}
	System.out.println("ok");
		return r;
	
	}
	
	
	}
