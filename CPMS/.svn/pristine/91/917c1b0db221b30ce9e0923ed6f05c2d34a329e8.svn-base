package centurylink.cpms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import centurylink.cpms.User;

public class DBOps {
private Connection con;




public DBOps(Connection con) {

	this.con = con;
}


public void insert(User user) throws SQLException
{
	String sqlcmd="insert into cpms_user(cuid,name,email,contact,password) values(?,?,?,?,?)";
	try {
		PreparedStatement stmt = con.prepareStatement(sqlcmd);
		stmt.setInt(1,Integer.parseInt(user.getCuid()));
		stmt.setString(2,user.getName());
		stmt.setString(3,user.getEmail());
		stmt.setInt(4,Integer.parseInt(user.getContact()));
		stmt.setInt(5,user.getPassword().hashCode());
		
		stmt.execute();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		throw new SQLException();
	}
}

/*
public String update(User user) throws SQLException
{
	
	
	String sqlcmd="update C3948user set email=?,password=? where userid=?";
	try {
		PreparedStatement y = con.prepareStatement(sqlcmd);
		y.setString(3,user.getUserid());
		y.setString(1,user.getEmail());
		y.setInt(2,user.getPassword().hashCode());
		y.execute();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		throw new SQLException();
	
	}
	return "yes";
	
	
}*/


public String select(User user) throws SQLException
{
	String sqlcmd="select name from cpms_user where (password=? and cuid=?)";
	String name=null;
	try {
		PreparedStatement stmt= con.prepareStatement(sqlcmd);
		stmt.setInt(2, Integer.parseInt(user.getCuid()));
		stmt.setInt(1, user.getPassword().hashCode());
		ResultSet resultSet = stmt.executeQuery();
		
	
		
		while(resultSet.next())
		{
			
			name = resultSet.getString("name");
		
					
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		throw new SQLException();
	}
	return name;
	
}
/*
public void block(User user) throws SQLException
{
	
	
	String sqlcmd="update C3948user set blocked=? where userid=?";
	try {
		PreparedStatement y = con.prepareStatement(sqlcmd);
	
		y.setInt(1,1);
		y.setString(2,user.getUserid());
		y.execute();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		throw new SQLException();
	
	}finally
	{
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}*/
	
	
	
//}


	

}
