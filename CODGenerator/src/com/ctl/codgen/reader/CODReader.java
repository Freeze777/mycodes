package com.ctl.codgen.reader;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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

	public class CODReader
	{	Map<String,String> filemap=new HashMap<String,String>();
		
	
		public CODReader()
		{	
		filemap.put("HVDSTenant","./templates/HVDS_TENANT.xml");
		filemap.put("HVDSService","./templates/HVDS_SERVICE.xml");
		filemap.put("SIPTrunk","./templates/SIPTrunkCOD.xml");
		filemap.put("SDPIA","./templates/SDP_IACOD_Analog.xml");
		filemap.put("Networx","./templates/NewNetworxCOD_AddTN2.xml");	
		}
		
		
		
		public XMLData readHVDSContents(String tenantId,String pruduct) 
		{
			Utils utils = null;
			File file = null;
			CustomerOrderResponseDocument customerOrderResponseDocument = null;
			CustomerOrder customerOrder = null;
			XMLData xmldata=new XMLData();
			try
			{
				utils = new Utils();
				if(null != tenantId && tenantId.length() > 0)
				{
					//Read the XML from DB
					String codXml = utils.getCodXmlFrmDB(tenantId);
					customerOrderResponseDocument = CustomerOrderResponseDocument.Factory.parse(codXml);
				}
				else
				{
					//Read XML from Template based on filetype
				//	String filename = utils.readHVDSPropertiesValues(filetype);
					file = utils.readTemplateFiles(filemap.get(pruduct));
					customerOrderResponseDocument = CustomerOrderResponseDocument.Factory.parse(file);
				}

				if(null == customerOrderResponseDocument)
				{
					throw new XmlException("Input XML is Null");
				}
				
				customerOrder = customerOrderResponseDocument.getCustomerOrderResponse().getCustomerOrder();
				xmldata.getStaticData().setORDER_ID(utils.getNxtSeqFrmDB(customerOrder.getServiceOrderId().getStringValue().trim()));
				xmldata.getStaticData().setCUSTOMER_ACCT_ID(utils.getNxtSeqFrmDB(customerOrder.getOrderCustomer().
						getOrderCustomerAccountArray(0).getCustomerAccountId().getStringValue().trim()));
				
				//Get OrderItems
				List<OrderItemT> orderItemList = customerOrder.getOrderCustomer().getOrderCustomerAccountArray(0).getOrderItemList();

				for(OrderItemT orderItem : orderItemList)
				{	//actions codes are not read
					if(orderItem.getBusinessObjectArray(0) instanceof ProvisioningElementT)
					{
						ProvisioningElementT provisionElement = (ProvisioningElementT) orderItem.getBusinessObjectArray(0);
							
							BTLData btldata=new BTLData();
							BTLData.incrementBTLCount();
							//Read TENANT_SEID
							btldata.setBtlSEID(utils.getNxtSeqFrmDB(provisionElement.getProvisioningSvcElementId().getStringValue().trim()));
							
							//setting BTL name
							btldata.setBtlName(provisionElement.getSubClass());
							
							//setting BTL action code
							//btldata.setActionCode(orderItem.getCoreActionCode().toString());
						//	btldata.setActionCode("R");
							//Read Attribute Values
							Map<String, String> attribMap = new HashMap<String, String>();
							for(AttributeNode attributeNode : provisionElement.getAttributeGroupArray(0).getAttributeNodeList())
							{
								attribMap.put(attributeNode.getAttributeName(), utils.getNxtSeqFrmDB(attributeNode.getAttributeValue()));
							}

							btldata.setAttrMap(attribMap);
							
							xmldata.getBtlDataList().add(btldata);
					}
					else if(orderItem.getBusinessObjectArray(0) instanceof ContactT)
					{
						ContactT contact = (ContactT) orderItem.getBusinessObjectArray(0);
						ContactData contactdata=new ContactData();
							
							contactdata.setContactType(contact.getContactType());
							contactdata.setCONTACT_ID(utils.getNxtSeqFrmDB(contact.getContactId().getStringValue().trim()));
							contactdata.setName(contact.getContactName());
							contactdata.setEmail(contact.getEmail());
							String tn = contact.getContactTN().getNPA()+
							contact.getContactTN().getNXX()+contact.getContactTN().getLineNumber();
							contactdata.setContactTN(tn);
							if(null!=contact.getCellPhoneTN())
							{
							String cell = contact.getCellPhoneTN().getNPA()+
							contact.getCellPhoneTN().getNXX()+contact.getCellPhoneTN().getLineNumber();
							contactdata.setCellPhoneTN(cell);
							}
						
							xmldata.getContactDataList().add(contactdata);
					}
					else if (orderItem.getBusinessObjectArray(0) instanceof CustomerProductT)
					{
						CustomerProductT productT=(CustomerProductT)orderItem.getBusinessObjectArray(0);
						xmldata.getStaticData().setPRODUCT_ACCOUNT_ID(utils.getNxtSeqFrmDB(productT.getCustomerBrandId().getStringValue().trim()));
						xmldata.getStaticData().setSALES_ORDER_ID(utils.getNxtSeqFrmDB(productT.getSalesOrderId().getStringValue().trim()));
					}
				}
				List<BTLData> btlList=xmldata.getBtlDataList();
				int count;
				for (BTLData btl : btlList)
				{	
					if(xmldata.getBTLCountMap().containsKey(btl.getBtlName()))
					{	count=xmldata.getBTLCountMap().get(btl.getBtlName());
					xmldata.getBTLCountMap().put(btl.getBtlName(),count+1);
					}
					else
						xmldata.getBTLCountMap().put(btl.getBtlName(),1);
				}
			}
			catch(IOException ioEx)
			{
				ioEx.printStackTrace();
			} 
			catch (XmlException xEx) 
			{
				xEx.printStackTrace();
			} 
			catch (SQLException sqlEx) 
			{
				sqlEx.printStackTrace();
			}

			return xmldata;
		}
		
		public static void main(String[] args) {
		CODReader reader=new CODReader();
		XMLData xml=reader.readHVDSContents(null, "./templates/HVDS_TENANT.xml/");
		System.out.println(xml.getBtlDataList().get(1).getActionCode());
		System.out.println(xml.getContactDataList());
		System.out.println(xml.getStaticData());
		
		}
	
	
	}


