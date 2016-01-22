<%@page import="centurylink.cpms.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
    <%@include file="../css-files/style3.css" %>
 body{
  background-image:url(green.jpg)
    }
    </style>
<title>CPMS</title>
       

</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container">
<div class="header">
<h2>HOME</h2>
</div>

<div class="submainbody">
<H6>

&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<a href="search.action" >FIND A CAR</a>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<a href="updateRoute.action" >ADD MY CAR</a>
<!-- <input type="submit" id="updateRoute_0" name="action:updateRoute" value="   Update Route   "/> -->
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp

</H6>


</div>
 
<div class="mainbody">
<br/>
 <br/>
<h2>Notifications</h2>
 <s:set name="flag" value="dashBoardStatus"/>
 <%-- <s:set name="flag1" value="seatmessage"/> --%>
 
 <s:property value="seatMessage"/>

<s:if test="%{#flag!=0}">
<table border="5" style="border: 1px solid black;width:100%;" cellpadding="6" cellspacing="0" width="75%">
	 <tr valign="baseline" bgcolor="#c0c0c0">
	 <th align="center"> Requester</th>
	 <th align="center"> Owner</th>
	 <th align="center"> Status </th>
	 <th align="center"> Source </th>
	 <th align="center"> Destination </th>
	 <th align="center"> Time</th>
	<s:set name="usersId" value="userId"/>
	 <s:if test="%{#usersId==ocid}">
	 <th align="center">Accept/Reject</th>
	</s:if> 
	<s:else>
	   <th align="center">  </th>
	</s:else>
	</tr>
</s:if> 
<s:else>
<h5>No Notifications..!!!</h5>
</s:else>

<s:iterator value="dashBoard" status="rowstatus">
 
<tr  style="border:#ccc;" > 
<td align="center"><s:property value="rcuid" /></td> 
<td align="center"><s:property value="ocuid"/></td> 
<td align="center"><s:property value="status"/></td> 
<td align="center"><s:property value="source"/></td> 
<td align="center"><s:property value="destination"/></td> 
<td align="center"><s:property value="rtime"/></td> 

<form id="search" name="search" onsubmit="return true;" action="/cpms/search.action" method="post">

<s:hidden  value="%{routeid}" name="routeid" />
<s:hidden  value="%{rcuid}"  name="rcuid"/>
	
 <s:set name="rstatus" value="requeststatus"/>
 
<s:if test="%{#usersId==ocid}">
 <%-- <s:if test="%{(#rstatus!=1)||(#rstatus!=2)}">  --%>
<s:if test="%{#rstatus==0}">

<td align="center">
<input type="submit" name="action:accept" value="Accept" />
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<input type="submit" name="action:reject" value="Reject" />
</td> 
</s:if>
</s:if>



</form>


</tr> 
</s:iterator> 
</table> 
</div>
 
<div class="footer">	
Copyright &copy CTLI 2014<br/>
</div>


</div>

</body>
</html>