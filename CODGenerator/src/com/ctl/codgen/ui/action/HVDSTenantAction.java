package com.ctl.codgen.ui.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.ctl.codgen.common.Utils;
import com.ctl.codgen.model.BTLData;
import com.ctl.codgen.model.XMLData;
import com.ctl.codgen.reader.CODReader;
import com.ctl.codgen.writer.CODUniversalWriter;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class HVDSTenantAction implements Action,ServletRequestAware,ModelDriven<XMLData>  {
	XMLData xmldata=new XMLData();
	String product="";
	String newBTL="";
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		CODReader reader=new CODReader();
		xmldata=reader.readHVDSContents(null,product);
		xmldata.setProductName(product);
		xmldata.setBtlListLength(xmldata.getBtlDataList().size());
		BTLData.setBtlCount(xmldata.getBtlListLength());
		return SUCCESS;
	}
	
	public String generateTenantXML() throws Exception {
		// TODO Auto-generated method stub
		CODUniversalWriter writer=new CODUniversalWriter();
		Utils utils=new Utils();
		Map<String,String> returnMap=writer.createCODXML(xmldata);
		
	
	File file = new File("C:\\Users\\ab09505\\Desktop\\output.xml");
	FileWriter fw = new FileWriter(file);
	BufferedWriter bw = new BufferedWriter(fw);
	bw.write(returnMap.get("COD_XML"));
	bw.close();
	
	//PERSIST COD XML IN DB

	utils.persistCODInDB(returnMap.get("SALES_ORDER_ID"), returnMap.get("TENANT_ID"),
			returnMap.get("VERSION_NUMBER"), returnMap.get("COD_XML"));


	xmldata.getStaticData().setSALES_ORDER_ID(returnMap.get("SALES_ORDER_ID"));
		return SUCCESS;
	}
	
	
	
	public String addNewBTL() throws Exception{
		List<BTLData> btlList = xmldata.getBtlDataList();
		BTLData newBtl=null;
		BTLData.incrementBTLCount();
		for (BTLData btl :btlList)
		{
			if(btl.getBtlName().equalsIgnoreCase(newBTL))
			newBtl=new BTLData(btl);
				
		
			
		}
		newBtl.setBtlSEID("###");
	
		btlList.add(newBtl);
		
		xmldata.setBtlListLength(xmldata.getBtlDataList().size());
		BTLData.setBtlCount(xmldata.getBtlListLength());
		return SUCCESS;
	}
	public XMLData getModel() {
		// TODO Auto-generated method stub
		
		return xmldata;
	}
	public void setServletRequest(HttpServletRequest request)
	{
		
		this.product=request.getParameter("product");
		
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getNewBTL() {
		return newBTL;
	}

	public void setNewBTL(String newBTL) {
		this.newBTL = newBTL;
	}

	
}
