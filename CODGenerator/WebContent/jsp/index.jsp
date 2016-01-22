<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<SCRIPT type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></SCRIPT>

<SCRIPT type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></SCRIPT>

<SCRIPT type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-collapse.js"></SCRIPT>


<style type="text/css">
   <%@include file="../css-files/sample.css" %>

    </style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product</title>
</head>
<body>
<table align="center">
<td align="center">
<jsp:include page="/jsp/header.jsp"/>
</td>
</table>
<div id="container">
   
<form id="redirect" name="redirect" onsubmit="return true;" action="/CODGenerator/jsp/hvdsTenant.action" method="post">

              
       
 <p align="center">
<label>Product:</label>
<select name="product" id="product" >
  <option value="HVDSTenant">HVDS Tenant</option>
  <option value="HVDSService">HVDS Service</option>
  <!--
  <option value="SIPTrunk">SIP Trunk</option>
  <option value="SDPIA">SDP IA</option>
   <option value="Networx">Networx</option>
 -->
 </select>
<br/>
<br/>




<input type="checkbox" name="tenantCheckbox" value="yes" id="tenantCheckbox" onchange="enableTenantID()"/>

<label>TenantID</label>
<input type="text" name="TENANT_ID" id="TENANT_ID"  disabled="disabled"   />
<br>
<br>
<s:submit theme="simple"></s:submit>

<!--
<input type="button" id="redirect_0" value="Submit" onclick="getNextPageURL()"/>
-->
</p>

</form>
	
</div>
<!--<h1>${pageContext.request.contextPath}</h1>--></body>
</html>