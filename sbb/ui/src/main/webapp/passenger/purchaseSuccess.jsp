<%--
  Created by IntelliJ IDEA.
  User: herr
  Date: 03.10.14
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.TicketBean" %>
<% TicketBean bean = (TicketBean)request.getAttribute("purchaseResult");
    if (bean == null) {
        bean = new TicketBean();
    }
%>
<html>
<head>
    <title>Purchase Success</title>
</head>
<body>
<h2> You have been purchased ticket successfully. Ticket Data: </h2>
<a href="<%=request.getContextPath()%>/index.jsp">back to options page</a>
<div id = "passengerData">
    <CENTER>
        <TABLE border="0"width="60px">
            <TR align="center">
                <TD width="150px">Ticket number:</TD>
                <TD> <%=bean.getTicketNumber()%> </TD>
            </TR>
            <TR align="center">
                <TD width="150px">Train number:</TD>
                <TD> <%=bean.getTrainNumber()%> </TD>
            </TR>
            <TR align="center">
                <TD width="150px">Document number:</TD>
                <TD> <%=bean.getPassengerDocNumber()%> </TD>
            </TR>
        </TABLE>
    </CENTER>
</div>
<div id = "userPanel">
    <div id = "userInfo">
        <%
            String userName = (String)request.getSession().getAttribute("user");
            if (userName == null) {
                userName = "unauthorized user";
            }
        %>
        <label> user name: <%=userName%></label>
    </div>
    <% if (request.getSession().getAttribute("user") != null) { %>
    <a href="<%=request.getContextPath()%>/logout">logout</a>
    <% } else { %>
    <a href="<%=request.getContextPath()%>/passengerLogin.jsp">login</a>
    <% } %>
</div>
</div>
</body>
</html>