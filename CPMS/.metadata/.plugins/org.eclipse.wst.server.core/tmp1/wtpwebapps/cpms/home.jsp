<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@taglib uri="/struts-tags" prefix="s"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
    <%@include file="css-files/style3.css" %>
 body{
  background-image:url(green.jpg)
    }
    </style>
   
   
<title>CPMS</title>
</head>
<body>
<table width="100%">
    <tr>
        <td width="300px"><img src="${pageContext.request.contextPath}/images/centurylink-logo.png" alt="Centurylink" height="50px">
        </td>
        <td><h2>CAR POOL MANAGEMENT SYSTEM</h2></td>
        <td align="right" width="200px">
           
        </td>
    </tr>
</table>
<br/><br/><br/>
<div class="container">


<div class="header">
<h2>LOGIN</h2>
</div>


<div class="mainbody">

<form id="login" name="login" onsubmit="return true;" action="/cpms/login.action" method="post">
		<br/>
		<br/>
		CUID:  &nbsp&nbsp&nbsp&nbsp<input type="text" name="cuid"  />
		<br/>
		<br/>
		Password:<input type="password" name="password"   />
		<br/>
		<br/>
		<s:property value="message"/>
		<br/>
		<input type="submit" id="login_0" value="  Login  "/>
		<br/>
		<br/>
		<h5>New to CPMS?</h5>
		<input type="submit" id="home_signup" name="action:signup" value="SignUp"/>
<br/>
<br/>

	</form>
</div>
	

	
<div class="footer">
Copyright &copy CTLI 2014<br/>
</div>


</div>
</body>
</html>