<%@ taglib prefix="s" uri="/struts-tags" %>
<table width="100%">
    <tr>
        <td width="300px"><img src="${pageContext.request.contextPath}/images/centurylink-logo.png" alt="Centurylink" height="50px">
        </td>
        <td><h2>CAR POOL MANAGEMENT SYSTEM</h2></td>
        <td align="right" width="200px">
            <div class="logout">
                <h6><%=session.getAttribute("username")%></h6>
                <a href="${pageContext.request.contextPath}/logout.action">Logout</a>
            </div>
        </td>
    </tr>
</table>
<br/><br/><br/>