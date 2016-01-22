package com.ctl.codgen.model;

import java.util.HashMap;
import java.util.Map;

public class BTLData
{	private Integer btlIndex;
	private static Integer btlCount=1;
	private String btlName;
	private String btlSEID;
	private String actionCode;
	private Map<String, String> attrMap;
	
	
	
	public BTLData() {
		
		this.btlIndex=btlCount;
	}
	public BTLData(BTLData btldata ) {
		
		this.btlName =btldata.getBtlName();
		this.btlSEID = btldata.getBtlSEID();
		this.actionCode = btldata.getActionCode();
		this.attrMap = new HashMap<String, String>(btldata.getAttrMap());
		this.btlIndex=btlCount;
	}
	public String getBtlName()
	{
		return btlName;
	}
	public void setBtlName(String btlName)
	{
		this.btlName = btlName;
	}
	public String getBtlSEID()
	{
		return btlSEID;
	}
	public void setBtlSEID(String btlSEID)
	{
		this.btlSEID = btlSEID;
	}
	public Map<String, String> getAttrMap()
	{
		return attrMap;
	}
	public void setAttrMap(Map<String, String> attrMap)
	{
		this.attrMap = attrMap;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public Integer getBtlIndex() {
		return btlIndex;
	}
	public void setBtlIndex(Integer btlIndex) {
		this.btlIndex = btlIndex;
	}
	public static Integer getBtlCount() {
		return btlCount;
	}
	public static void setBtlCount(Integer btlCount) {
		BTLData.btlCount = btlCount;
	}
	public static void incrementBTLCount() {
		btlCount++;
	}
}
