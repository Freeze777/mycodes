
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
<style type="text/css">
    <%@include file="../css-files/style3.css" %>
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

<div class="container">

<div class="header">
<h2>REGISTER</h2>
</div>
 
 

  
<div class="mainbody">               			
<form id="registeruser" name="registeruser" onsubmit="return true;" action="/cpms/registeruser.action" method="post">
<br/>
<br/>
<br/>

    <label for="registeruser_cuid" class="label">CUID:  </label>
    <input type="text" name="cuid" value="" id="registeruser_cuid" required/>
<br/>
<br/>
    <label for="registeruser_name" class="label">User Name:</label>
    <input type="text" name="name" value="" id="registeruser_name" required/>
<br/>
<br/>
              
    <label for="registeruser_password" class="label">Password:</label>
    <input type="password" name="password" id="registeruser_password"/>
<br/>
 <br/>            	 
    <label for="registeruser_contact" class="label">Contact Num:
    <input type="text" name="contact" value="" id="registeruser_contact" required/>
<br/>
<br/>	
	<label for="registeruser_email" class="label">Email:    </label>
	<input type="text" name="email" value="" id="registeruser_email" required/>
<br/>
<br/>
	       
	<!-- <input type="submit" id="registeruser_0" value="   Find Car   "/> -->
<br/>
<br/>


	     <input type="submit" id="registeruser_register" name="action:register" value="Register "/>
</form>

<H3>     <s:property value="message"/></H3>   
        
</div>

<div class="footer">
Copyright &copy CTLI 2014<br/>
</div>
</div>
</body>
</html>
