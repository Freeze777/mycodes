package com.ctl.codgen.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ctl.codgen.db.ExecuteSelectQuery;
import com.ctl.codgen.db.ExecuteUpdateQuery;
import com.qwest.voip.oes.xmlSchema.AttributeNodeDocument.AttributeNode;

public class Utils
{
	public String getNxtSeqFrmDB(String value) throws SQLException 
	{
		final List<String> retList = new ArrayList<String>();
		if (value.equals("###")) 
		{
			String sql="select COD_GEN_SEQ.nextval from dual";
			ExecuteSelectQuery executeSelectQuery = new ExecuteSelectQuery(sql)
			{ 
				@Override
				public void setParameters(PreparedStatement ps)
				throws SQLException 
				{
					//No Parameters
				}

				@Override
				public void rowContents(ResultSet rs) throws SQLException 
				{
					retList.add(rs.getString(1));
				}
			};

			executeSelectQuery.execute();
			
			return retList.get(0);
		}
		return value;
	}
	
	public String getCodXmlFrmDB(final String tenantId) throws SQLException 
	{
		final List<String> retList = new ArrayList<String>();
		String sql="select AC.COD_XML from ALTERNATE_COD_XML AC, TENANT_SALES_ORDER_MAPPING TS" +
			"where AC.SALES_ORDER_ID = TS.SALES_ORDER_ID and TS.TENANT_ID = ?";

		ExecuteSelectQuery executeSelectQuery = new ExecuteSelectQuery(sql)
		{ 
			@Override
			public void setParameters(PreparedStatement ps)
			throws SQLException 
			{
				ps.setString(1, tenantId);
			}

			@Override
			public void rowContents(ResultSet rs) throws SQLException 
			{
				retList.add(rs.getString(1));
			}
		};

		executeSelectQuery.execute();

		return retList.get(0).toString();
	}
	
	public File readTemplateFiles(String fileName)
	{
		ClassLoader classLoader = this.getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		
		return file;
	}
	
	public String readHVDSPropertiesValues(String key) throws IOException
	{
		String propFileName = "./config/hvds.properties";
		return readPropertiesValues(propFileName, key);
	}
	
	public String readDBPropertiesValues(String key) throws IOException
	{
		String propFileName = "./config/db.properties";
		return readPropertiesValues(propFileName, key);
	}
	
	private String readPropertiesValues(String propFileName, String key) throws IOException
	{
		Properties properties = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		properties.load(inputStream);
		for(Object property : properties.keySet())
		{
			if(key.equalsIgnoreCase(property.toString()))
			{
				return properties.getProperty(property.toString());
			}
		}
		return null;
	}
	
	public void persistCODInDB(final String salesOrderID, final String tenantID, 
			final String versionNumber, final String CODXml) throws SQLException 
	{
		String sql = " INSERT INTO ALTERNATE_COD_XML " +
        " (SALES_ORDER_ID,VERSION_NUMBER,COD_XML) " +
        " VALUES(?,?,?) ";
		ExecuteUpdateQuery execUpdateQuery = new ExecuteUpdateQuery(sql)
		{
			@Override
			public void setParameters(PreparedStatement ps) throws SQLException
			{ 
				ps.setString(1, salesOrderID);
				ps.setString(2, versionNumber);
				ps.setString(3, CODXml);
			}
		};
	    execUpdateQuery.execute();
	    
	    
	    sql=" INSERT INTO TENANT_SALES_ORDER_MAPPING " +
	    " (SALES_ORDER_ID,TENANT_ID) " +
	    " VALUES(?,?) ";
	    ExecuteUpdateQuery executeUpdateQuery=new ExecuteUpdateQuery(sql)
	    {
	    	@Override
	    	public void setParameters(PreparedStatement ps)throws SQLException 
	    	{
	    		ps.setString(1, salesOrderID);
	    		ps.setString(2, tenantID);
	    	}
	    };
	    executeUpdateQuery.execute();
		
	}
	
	public void constructAttributeNode(AttributeNode attributeNode, boolean isEncrypted, String attributeName, 
			String attributeValue, String attributeDesc)
    {
        if(null != attributeValue && attributeValue.length() > 0)
        {
            attributeNode.setIsEncrypted(isEncrypted);
            attributeNode.setAttributeName(attributeName);
            attributeNode.setAttributeValue(attributeValue);
            if(attributeDesc!=null)
                attributeNode.setAttributeValueDesc(attributeDesc);
        }
    }
}
