package com.ctl.codgen.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ExecuteSelectQuery extends DataSourceConnection 
{
	
	private String sql;						
	private int rowCount;
	
	public String getSql() 							
	{													
		return sql;											
	}
	
	public void setSql(String sql) 
	{
		this.sql = sql;						
	}

	//constructor
	public ExecuteSelectQuery(String sql)
	{
		this.setSql(sql);
	}
	
	public void execute() throws SQLException
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try 
		{
			conn = getConnection();
			ps = conn.prepareStatement(getSql());
			setParameters(ps);
			rs = ps.executeQuery();
			
			rowCount = 0;
			while (rs.next()) 
			{
				rowCount++;
				rowContents(rs);
			}
		} 
		catch (SQLException ex)
		{
			throw ex;
		} 
		finally 
		{
			super.closeResources(conn, ps, rs);
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
	
	/**
	 * A process method that operates on a result row of the SQL query results.
	 * This is defined by the extending class if needed.
	 *
	 * @param ps A PreparedStatement containing a SQL query.
	 * @throws SQLException When the provide SQL statement is invalid or has problems.
	 */
	public abstract void rowContents(ResultSet rs) throws SQLException ;
}
