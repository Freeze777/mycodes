package com.ctl.codgen.model;

public class ContactData
{
	private String contactType;
	private String actionCode;
	private String email;
	private String name;
	private String CONTACT_ID;
	private String ContactTN;
	private String CellPhoneTN;
	
	public String getContactType()
	{
		return contactType;
	}
	public void setContactType(String contactType)
	{
		this.contactType = contactType;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getCONTACT_ID()
	{
		return CONTACT_ID;
	}
	public void setCONTACT_ID(String contact_id)
	{
		CONTACT_ID = contact_id;
	}
	public String getContactTN()
	{
		return ContactTN;
	}
	public void setContactTN(String contactTN)
	{
		ContactTN = contactTN;
	}
	public String getCellPhoneTN()
	{
		return CellPhoneTN;
	}
	public void setCellPhoneTN(String cellPhoneTN)
	{
		CellPhoneTN = cellPhoneTN;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	
}
