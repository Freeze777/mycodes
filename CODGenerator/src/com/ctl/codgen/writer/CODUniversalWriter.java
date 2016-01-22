package com.ctl.codgen.writer;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.XmlException;

import com.ctl.codgen.common.Utils;
import com.ctl.codgen.model.BTLData;
import com.ctl.codgen.model.ContactData;
import com.ctl.codgen.model.XMLData;
import com.qwest.voip.oes.xmlSchema.ContactT;
import com.qwest.voip.oes.xmlSchema.CustomerOrderResponseDocument;
import com.qwest.voip.oes.xmlSchema.CustomerProductT;
import com.qwest.voip.oes.xmlSchema.OrderItemT;
import com.qwest.voip.oes.xmlSchema.ProvisioningElementT;
import com.qwest.voip.oes.xmlSchema.AttributeNodeDocument.AttributeNode;
import com.qwest.voip.oes.xmlSchema.CustomerOrderDocument.CustomerOrder;
import com.qwest.voip.oes.xmlSchema.OrderItemT.ActionCode;

public class CODUniversalWriter {
	//contains path for template XMLs for each product
	Map<String,String> filemap=new HashMap<String,String>();
	String prodAccountId ;
	String salesOrderId;
	String customerAccountId ;
	Integer lastOrderItemId;
	List<String> editedBtlSEIDlist=new ArrayList<String>();
	Utils utils = new Utils();
	
	
	public CODUniversalWriter() {
		filemap.put("HVDSTenant","./templates/HVDS_TENANT.xml");
		filemap.put("HVDSService","./templates/HVDS_SERVICE.xml");
		filemap.put("SIPTrunk","./templates/SIPTrunkCOD.xml");
		filemap.put("SDPIA","./templates/SDP_IACOD_Analog.xml");
		filemap.put("Networx","./templates/NewNetworxCOD_AddTN2.xml");
	}
	
	public Map<String, String> createCODXML(XMLData xmldata)
	{
	

	File file = null;
	CustomerOrderResponseDocument customerOrderResponseDocument = null;
	CustomerOrder customerOrder = null;
	Map<String, String> returnMap = new HashMap<String, String>();
	
	try
	{
		//Read XML from Template
		
		List<BTLData> btlList=xmldata.getBtlDataList();
		String versionNum="0";
		String tenantID ="";
		
		file = utils.readTemplateFiles(filemap.get(xmldata.getProductName()));
		customerOrderResponseDocument = CustomerOrderResponseDocument.Factory.parse(file);
		customerOrderResponseDocument.toString().replaceAll("xsi:type", "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type");
		customerOrder = customerOrderResponseDocument.getCustomerOrderResponse().getCustomerOrder();
		
		customerOrder = customerOrderResponseDocument.getCustomerOrderResponse().getCustomerOrder();
		customerAccountId = xmldata.getStaticData().getCUSTOMER_ACCT_ID();
		salesOrderId = xmldata.getStaticData().getSALES_ORDER_ID();
		prodAccountId = xmldata.getStaticData().getPRODUCT_ACCOUNT_ID();
	
		//Set Dates
		customerOrder.setOrderCreateDate(Calendar.getInstance());
		customerOrder.setOrderReceivedDate(Calendar.getInstance());
		customerOrder.setOrderTakenDate(Calendar.getInstance());
		
		//setting static data
		customerOrder.getServiceOrderId().setStringValue(xmldata.getStaticData().getORDER_ID());
		customerOrder.getOrderCustomer().getOrderCustomerAccountArray(0).getCustomerAccountId().
		setStringValue(customerAccountId);
		
				
		//Get OrderItems
		List<OrderItemT> orderItemList = customerOrder.getOrderCustomer().
		getOrderCustomerAccountArray(0).getOrderItemList();
		lastOrderItemId=orderItemList.size();
		
		for (BTLData btl : btlList)
		{
			if(btl.getBtlSEID().equals("###"))
			{
				for (OrderItemT orderItem : orderItemList)
				{	
				
				if(orderItem.getBusinessObjectArray(0) instanceof ProvisioningElementT)
					{	ProvisioningElementT provisionElement= (ProvisioningElementT) orderItem.getBusinessObjectArray(0);
						if(provisionElement.getSubClass().equalsIgnoreCase(btl.getBtlName()))
						{	addNewBTL(customerOrder, orderItem, btl);
							break;
						}
					}
				
				}
				
			
			}
		}
	
		
		for(OrderItemT orderItem : orderItemList)
		{	
			if(null!=orderItem.getCustomerAccountId())
			orderItem.getCustomerAccountId().setStringValue(customerAccountId);
			if(orderItem.getBusinessObjectArray(0) instanceof CustomerProductT)
		
			{
				orderItem.setDesiredDueDate(Calendar.getInstance());
				orderItem.setCommitmentDate(Calendar.getInstance());	
				CustomerProductT customerProduct = (CustomerProductT) orderItem.getBusinessObjectArray(0);
				customerProduct.getSalesOrderId().setStringValue(salesOrderId);
				customerProduct.getCustomerBrandId().setStringValue(prodAccountId);
				customerProduct.getSalesOrderId().setStringValue(salesOrderId);
				customerProduct.getCustomerAccountId().setStringValue(customerAccountId);
			}else if(orderItem.getBusinessObjectArray(0) instanceof ContactT)
			{	
				List<ContactData> contactDataList=xmldata.getContactDataList();
				ContactT contactT = (ContactT) orderItem.getBusinessObjectArray(0);
				for (ContactData contact : contactDataList)
				{
					if(contactT.getContactType().equalsIgnoreCase(contact.getContactType()))
					{
	
						contactT.setContactName(contact.getName());
						contactT.setEmail(contact.getEmail());
						orderItem.setActionCode(ActionCode.Enum.forString(contact.getActionCode()));
						contactT.getContactTN().setNPA(contact.getContactTN().substring(0, 3));
						contactT.getContactTN().setNXX(contact.getContactTN().substring(3, 6));
						contactT.getContactTN().setLineNumber(contact.getContactTN().substring(6));
						if(contact.getCellPhoneTN().length()>0 && contact.getCellPhoneTN()!=null)
						{
						contactT.getCellPhoneTN().setNPA(contact.getCellPhoneTN().substring(0, 3));
						contactT.getCellPhoneTN().setNXX(contact.getCellPhoneTN().substring(3, 6));
						contactT.getCellPhoneTN().setLineNumber(contact.getCellPhoneTN().substring(6));
						contactT.getContactId().setStringValue(contact.getCONTACT_ID());
						}
					}
				
				
					}
			
				}
		}
		
		
		for (BTLData btl : btlList)
		{	
			
			
			for (OrderItemT orderItem : orderItemList)
			{	
				if(null!=orderItem.getCustomerAccountId())
				orderItem.getCustomerAccountId().setStringValue(customerAccountId);
				
				
				if(orderItem.getBusinessObjectArray(0) instanceof ProvisioningElementT)
				{	
					
					ProvisioningElementT provisionElement= (ProvisioningElementT) orderItem.getBusinessObjectArray(0);
				
					if((provisionElement.getSubClass().equalsIgnoreCase(btl.getBtlName()))
							&&((editedBtlSEIDlist.size()==0)||!(editedBtlSEIDlist.contains(provisionElement.getProvisioningSvcElementId().getStringValue().trim()))))
					{	
						
						editedBtlSEIDlist.add(btl.getBtlSEID());
					
						
					
						
						if(provisionElement.getSubClass().equalsIgnoreCase("Tenant"))
							tenantID=btl.getAttrMap().get("TENANT_ID");
						
						provisionElement.getCustomerProductComponentId().setStringValue(prodAccountId);
						orderItem.setActionCode(ActionCode.Enum.forString(btl.getActionCode()));
						provisionElement.getProvisioningSvcElementId().setStringValue(btl.getBtlSEID());
						for(AttributeNode attributeNode : provisionElement.getAttributeGroupArray(0).getAttributeNodeList())
						attributeNode.setAttributeValue(btl.getAttrMap().get(attributeNode.getAttributeName()));
						break;
						}
					
					
					}
				
					
				}
		
		}	
			
		String outputXml = customerOrderResponseDocument.toString();
		outputXml = outputXml.replaceAll("xsi:type", "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type");
		
		returnMap.put("TENANT_ID", tenantID);
		returnMap.put("SALES_ORDER_ID", salesOrderId);
		returnMap.put("VERSION_NUMBER", versionNum);
		returnMap.put("COD_XML", outputXml);
		
		
		
		
		
		
	}catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		} 
		catch (XmlException xEx) 
		{
			xEx.printStackTrace();
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	return returnMap;
	

	
}

	private void addNewBTL(CustomerOrder customerOrder, OrderItemT orderItem, BTLData btl) throws SQLException {
			
			OrderItemT newOrderItem = customerOrder.getOrderCustomer().getOrderCustomerAccountArray(0).addNewOrderItem();
			newOrderItem.set((OrderItemT)orderItem.copy());
			
			int i=++lastOrderItemId;
			newOrderItem.setOrderItemId(new BigInteger(Integer.toString(i)));
			
			if(null!=newOrderItem.getCustomerAccountId())
				newOrderItem.getCustomerAccountId().setStringValue(customerAccountId);
		
			newOrderItem.setActionCode(ActionCode.Enum.forString(btl.getActionCode()));
			ProvisioningElementT provisionElement=(ProvisioningElementT) newOrderItem.getBusinessObjectArray(0);
	     	provisionElement.getCustomerProductComponentId().setStringValue(prodAccountId);
			provisionElement.getProvisioningSvcElementId().setStringValue(utils.getNxtSeqFrmDB(btl.getBtlSEID()));
			btl.setBtlSEID(provisionElement.getProvisioningSvcElementId().getStringValue());
		
			for(AttributeNode attributeNode : provisionElement.getAttributeGroupArray(0).getAttributeNodeList())
			attributeNode.setAttributeValue(btl.getAttrMap().get(attributeNode.getAttributeName()));
		
			editedBtlSEIDlist.add(provisionElement.getProvisioningSvcElementId().getStringValue());
			
	}


}
