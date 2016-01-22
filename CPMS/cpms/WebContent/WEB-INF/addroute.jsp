<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>

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
<h2>ADD ROUTE</h2>
</div>

<div class="submainbody">



<H6>
<a href="getMyDashboardLog.action" >HOME</a>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<a href="search.action" >FIND A CAR</a>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<a href="updateRoute.action" >ADD MY CAR</a>
<!-- <input type="submit" id="updateRoute_0" name="action:updateRoute" value="   Update Route   "/> -->
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp

</H6>




</div>


<div class="mainbody">



<form id="addRoute" name="addRoute" onsubmit="return true;" action="/cpms/addRoute.action" method="post">
<h2>Towards CenturyLink</h2>
<label for="addRoute_toSource" class="label">&nbsp&nbsp&nbsp&nbspSource:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
<input type="text" name="toSource" value="" id="addRoute_toSource"/>

<label for="addRoute_toDestination" class="label">&nbsp&nbsp&nbspDestination:&nbsp&nbsp&nbsp&nbsp&nbsp</label>
<input type="text" name="toDestination" value="CTLI" id="addRoute_toDestination" readonly/>
<br/>
<!-- <label for="addRoute_toOfficeStartTime" class="label">Time of Departure:</label>
<input type="text" name="toOfficeStartTime" value="" id="addRoute_toOfficeStartTime"/>

<label for="addRoute_toOfficeEndTime" class="label">&nbsp&nbsp&nbspTime of Arrival:&nbsp&nbsp</label>
<input type="text" name="toOfficeEndTime" value="" id="addRoute_toOfficeEndTime"/> -->

<br/><s:select name="toOfficeStartTime" list="toTimeList" 
		label="Time of Departure" />
<s:select name="toOfficeEndTime" list="toTimeList" 
		label="Time of Arrival" />
</br>

<br/>
<!-- <label for="addRoute_toOfficeSeats" class="label" >Seats:</label>
<input type="text" name="toOfficeSeats" value="0" id="addRoute_toOfficeSeats"/> -->
<s:select name="toOfficeSeats" list="seatList" 
		label="Seats" />
<label for="addRoute_towardsTransits" class="label">Enter your transit points:</label>
<textarea name="towardsTransits" cols="20" rows="2" id="addRoute_towardsTransits"></textarea>
<br/>
<br/><br/>

<h2>From CenturyLink</h2>	
<label for="addRoute_fromSource" class="label">&nbsp&nbsp&nbsp&nbspSource:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
 <input type="text" name="fromSource" value="CTLI" id="addRoute_fromSource" readonly/>


		
 <label for="addRoute_fromDestination" class="label">&nbsp&nbsp&nbspDestination:&nbsp&nbsp&nbsp&nbsp&nbsp</label>
 <input type="text" name="fromDestination" value="" id="addRoute_fromDestination"/>
<br/>
 <!-- <label for="addRoute_fromOfficeStartTime" class="label">Time of Departure:</label>
 <input type="text" name="fromOfficeStartTime" value="" id="addRoute_fromOfficeStartTime"/>


		
 <label for="addRoute_fromOfficeEndTime" class="label">&nbsp&nbsp&nbspTime of Arrival:&nbsp&nbsp</label>
 <input type="text" name="fromOfficeEndTime" value="" id="addRoute_fromOfficeEndTime"/> -->
 </br>
 <s:select name="fromOfficeStartTime" list="fromTimeList" 
		label="Time of Departure" />

<s:select name="fromOfficeEndTime" list="fromTimeList" 
		label="Time of Arrival" />
		</br>
<br/>
<!-- <label for="addRoute_fromOfficeSeats" class="label" >Seats:</label>
<input type="text" name="fromOfficeSeats" value="0" id="addRoute_fromOfficeSeats"/> -->
<s:select name="fromOfficeSeats" list="seatList" 
		label="Seats" />
<label for="addRoute_fromTransits" class="label">Enter your transit points:</label>
<textarea name="fromTransits" cols="20" rows="2" id="addRoute_fromTransits"></textarea>

<br/>
<br/>
  <input type="submit" id="addRoute_0" value="Add Route"/>
</form>
</div>



<div class="footer">

Copyright &copy CTLI 2014


</div>
</div>

</body>
</html>