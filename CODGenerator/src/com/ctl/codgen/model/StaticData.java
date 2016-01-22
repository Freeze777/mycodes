package com.ctl.codgen.model;

public class StaticData
{
	private String ORDER_ID;
	private String CUSTOMER_ACCT_ID;
	private String PRODUCT_ACCOUNT_ID;
	private String SALES_ORDER_ID;
	
	public String getORDER_ID()
	{
		return ORDER_ID;
	}
	public void setORDER_ID(String order_id)
	{
		ORDER_ID = order_id;
	}
	public String getCUSTOMER_ACCT_ID()
	{
		return CUSTOMER_ACCT_ID;
	}
	public void setCUSTOMER_ACCT_ID(String customer_acct_id)
	{
		CUSTOMER_ACCT_ID = customer_acct_id;
	}
	public String getPRODUCT_ACCOUNT_ID()
	{
		return PRODUCT_ACCOUNT_ID;
	}
	public void setPRODUCT_ACCOUNT_ID(String product_account_id)
	{
		PRODUCT_ACCOUNT_ID = product_account_id;
	}
	public String getSALES_ORDER_ID()
	{
		return SALES_ORDER_ID;
	}
	public void setSALES_ORDER_ID(String sales_order_id)
	{
		SALES_ORDER_ID = sales_order_id;
	}
}
