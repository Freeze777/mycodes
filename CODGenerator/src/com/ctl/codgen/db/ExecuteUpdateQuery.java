package com.ctl.codgen.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class ExecuteUpdateQuery extends DataSourceConnection 
{

	private String sql;
	private int count;
	
	public String getSql() 
	{
		return sql;
	}
	public void setSql(String sql) 
	{
		this.sql = sql;
	}
	public int getCount() 
	{
		return count;
	}
	public void setCount(int count) 
	{
		this.count = count;
	}
	
	//constructor
	public ExecuteUpdateQuery(String sql)
	{
		this.setSql(sql);
	}
	
	public void execute() throws SQLException
	{
		Connection conn = null;
		PreparedStatement ps = null;
		int count = 0;
		
		try 
		{
			conn = getConnection();
			ps = conn.prepareStatement(getSql());
			setParameters(ps);
			count = ps.executeUpdate();
			this.setCount(count);
		} 
		catch (SQLException ex) 
		{
			throw ex;
		} 
		finally 
		{
			super.closeResources(conn, ps, null);
		}
	}
	
	/**
	 * A setter method to set the SQL query parameters of execution.  This is
	 * defined by the extending class if needed.
	 *
	 * @param ps A PreparedStatement containing a SQL query.
	 * @throws SQLException When the provide SQL statement is invalid or has problems.
	 */
	public abstract void setParameters(PreparedStatement ps) throws SQLException;
}
