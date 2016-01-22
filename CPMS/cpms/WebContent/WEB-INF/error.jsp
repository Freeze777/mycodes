<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
    <%@include file="../css-files/style3.css" %>
 body{
  background-image:url(green.jpg)
    }
    </style>
</head>
<body>
<div id="div11">
    <img src="${pageContext.request.contextPath}/images/centurylink-logo.png" height="110" >
    <h3>SESSION EXPIRED</h3>
<a href="/WEB-INF/login.jsp"/>
</div>
</body>
</html>