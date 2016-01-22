<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<SCRIPT type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></SCRIPT>

<script type="text/javascript">


function addBTLcallToServer()
{	codXMLform=document.getElementById("CODXML");
	codXMLform.action="/CODGenerator/addNewBTL.action";
	document.getElementsByName("model.btlDataList[0].btlCount").value="0";
	codXMLform.submit();
	
}

function submitCOD(){
	codXMLform=document.getElementById("CODXML");
	codXMLform.action="/CODGenerator/generateNewXML.action";
	codXMLform.submit();

}

function createSelectBox(){
	
	btlArray=document.getElementsByClassName("BTL");
	BTLselectbox=document.getElementById("newBTL");
	
	
	for ( var i=0; i < btlArray.length; i++) {
			var option = document.createElement("option"); 	
			option.value =btlArray[i].value;
		 	option.text = btlArray[i].value;
		 	BTLselectbox.appendChild(option);
	}
	
	
}
</script>

<style type="text/css">
   <%@include file="../css-files/sample.css" %>

    </style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>COD Generator</title>
</head>
<body onload="createSelectBox()">
<table align="center">
<td align="center">
<jsp:include page="/jsp/header.jsp"/>
</td>
</table>
<form id="CODXML"  name="generateXML" onsubmit="return true;" action="/CODGenerator/generateNewXML.action" method="post">
<table align="right">
<td align="right">
<input type="button" value="Save & Generate Xml" onclick="submitCOD()"/>
<!--<s:submit align="right" theme="simple" value="Save & Generate Xml" id="submit"  />
--></td>
</table>

<table align="right">
<td align="right">
<select id="newBTL" name="newBTL">

</select>
<input type="button" value="Add BTL" onclick="addBTLcallToServer()"/>
</td>
</table>


<s:hidden name="model.productName" />

<s:iterator value="model.BTLCountMap" status="cnt2">
<s:hidden name="model.BTLCountMap['%{key}']"  theme="simple" />
</s:iterator>





<table>
<h1 align="left">Static info</h1>
<td align="right">ORDER_ID=</td>
<td><s:textfield name="model.staticData.ORDER_ID"  theme="simple" readonly="true"/></td>
<td align="right">CUSTOMER_ACCT_ID=</td>
<td><s:textfield name="model.staticData.CUSTOMER_ACCT_ID"  theme="simple" readonly="true"/></td>
<td align="right">PRODUCT_ACCOUNT_ID=</td>
<td><s:textfield name="model.staticData.PRODUCT_ACCOUNT_ID"  theme="simple" readonly="true"/></td>
<td align="right">SALES_ORDER_ID=</td>
<td><s:textfield name="model.staticData.SALES_ORDER_ID"  theme="simple" readonly="true"/></td>
</table>

 <s:hidden name="model.btlListLength" />
<s:iterator value="model.btlDataList" status="cnt">

 <th><h1><s:property value="model.btlDataList[#cnt.count-1].btlName"/></h1></th><br>
 <s:hidden name="model.btlDataList[%{#cnt.count-1}].btlName" cssClass="BTL"/>
 <s:hidden name="model.btlDataList[%{#cnt.count-1}].btlIndex" />
  <s:hidden name="model.btlDataList[%{#cnt.count-1}].btlCount" />
<table>
<tr>
<td align="right">SEID=</td>
<td><s:textfield name="model.btlDataList[%{#cnt.count-1}].btlSEID"  theme="simple" readonly="true"/></td>
   
 <td align="right">ACTION CODE=</td>
 <td><s:select list="#{'A':'A','C':'C','D':'D','R':'R','S':'S','N':'N'}" name="model.btlDataList[%{#cnt.count-1}].actionCode"  id="ACTION_CODE" theme="simple" ></s:select>
 </td>
    </tr>  
    
     <s:iterator value="model.btlDataList[#cnt.count-1].attrMap" status="cnt1">
   
   <s:if  test="#cnt1.even == true"> 
   <tr>
   <td align="right"> <s:property value="key"/>=</td>
   <td> <s:textfield name="model.btlDataList[%{#cnt.count-1}].attrMap['%{key}']"  theme="simple" /></td>
 </s:if>
 <s:elseif test="#cnt1.even == false">
 <td align="right"> <s:property value="key"/>=</td>
   <td> <s:textfield name="model.btlDataList[%{#cnt.count-1}].attrMap['%{key}']"  theme="simple" /></td>
 </tr>
 </s:elseif>
    
 
    </s:iterator>
     
     </table>
</s:iterator>
<h1>Contact</h1>

<s:iterator value="model.contactDataList" status="cnt1">
<table>
<tr>
<td align="right">CONTACT TYPE=</td>
<td><s:textfield name="model.contactDataList[%{#cnt1.count-1}].contactType"  theme="simple" /></td>
<td align="right">EMAIL=</td>
<td><s:textfield name="model.contactDataList[%{#cnt1.count-1}].email"  theme="simple" /></td>
<td align="right">NAME=</td>
<td><s:textfield name="model.contactDataList[%{#cnt1.count-1}].name"  theme="simple" /></td>
<td align="right">CONTACT ID=</td>
<td><s:textfield name="model.contactDataList[%{#cnt1.count-1}].CONTACT_ID"  theme="simple" /></td>
</tr>
<tr>
<td align="right">ACTION CODE=</td>
 <td><s:select list="#{'A':'A','C':'C','D':'D','R':'R','S':'S','N':'N'}" name="model.contactDataList[%{#cnt1.count-1}].actionCode" theme="simple" ></s:select>
 </td>
<td align="right">CONTACT TN=</td>
<td><s:textfield name="model.contactDataList[%{#cnt1.count-1}].ContactTN"  theme="simple" /> </td>
<td align="right">CELL=</td>
<td><s:textfield name="model.contactDataList[%{#cnt1.count-1}].CellPhoneTN"  theme="simple" /></td>
   </tr>
   <br>
   <br>
  </table> 
</s:iterator>
<br>
   <br>


</form>
</body>
</html>