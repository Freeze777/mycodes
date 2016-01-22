package com.ctl.codgen.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ctl.codgen.common.CommonConstants;

public class DataSourceConnection 
{
	protected Connection conn;
    	
	/**
     * Obtain a database connection
     * @return conn A Connection to the database
     */
     protected Connection getConnection() throws SQLException 
     {
        if (null != this.conn && !this.conn.isClosed())
            return this.conn;
        Context ic = null;
        DataSource ds = null;
     
        try 
        {
            ic = new InitialContext();
            ds =(DataSource)ic.lookup(CommonConstants.DATA_SOURCE);
            this.conn = ds.getConnection();
        } 
        catch(Exception ex) 
        {
           ex.printStackTrace();
        } 
        finally 
        {
            if (ic != null) 
            {
              try 
              {
                ic.close();
              } 
              catch(Exception i) 
              {
                 i.printStackTrace();
              }
            }
        }
        return this.conn;
    }
     
     /**
      * Doing the cleanup of the connection, statement and result set.
      */
     protected void closeResources(Connection pConn, Statement pStatement, ResultSet pResultSet) throws SQLException
     {
         if(null != pResultSet)
         {
              pResultSet.close();
         }
         if(null != pStatement)
         {
             pStatement.close();
         }
         if(null != pConn)
         {
              pConn.close();
         }
     }
}
