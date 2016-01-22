package com.ctl.codgen.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLData
{	private Integer btlListLength;
	private String productName="";
	private StaticData staticData=new StaticData();
	private List<BTLData> btlDataList=new ArrayList<BTLData>();
	private List<ContactData> contactDataList=new ArrayList<ContactData>();
	private Map<String,Integer> BTLCountMap=new HashMap<String, Integer>();
	
	public StaticData getStaticData()
	{
		return staticData;
	}
	public void setStaticData(StaticData staticData)
	{
		this.staticData = staticData;
	}
	public List<BTLData> getBtlDataList()
	{
		return btlDataList;
	}
	public void setBtlDataList(List<BTLData> btlDataList)
	{
		this.btlDataList = btlDataList;
	}
	public List<ContactData> getContactDataList()
	{	
		return contactDataList;
	}
	public void setContactDataList(List<ContactData> contactDataList)
	{
		this.contactDataList = contactDataList;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getBtlListLength() {
		return btlListLength;
	}
	public void setBtlListLength(Integer btlListLength) {
		this.btlListLength = btlListLength;
	}
	public Map<String, Integer> getBTLCountMap() {
		return BTLCountMap;
	}
	public void setBTLCountMap(Map<String, Integer> countMap) {
		BTLCountMap = countMap;
	}
	
	

}
