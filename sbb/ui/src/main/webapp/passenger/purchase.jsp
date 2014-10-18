<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.TicketBean" %>
<% TicketBean bean = (TicketBean)request.getAttribute("purchaseResult");
    if (bean == null) {
        bean = new TicketBean();
    }
%>
<html>
<head>
    <title>Ticket purchasing</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/styles/main.css"/>
</head>
<body>
<div id="mainHeader">
    <span id = "title-pic">
        <a href="<%=request.getContextPath()%>/"><img src="<%=request.getContextPath()%>/resources/images/logo_sbb.png" width="75" height="75" alt="Git"></a>
    </span>
    <span id = "title">
        <label>SBB railways</label>
    </span>
</div>

<div id = "userPanel">
    <%
        String userName = (String)request.getSession().getAttribute("user");
        if (userName == null) {
            userName = "unauthorized user";
        }
    %>
    <div id = "userInfo">
        <% if (request.getSession().getAttribute("user") != null) { %>
        <label>You are logged as: <%=userName%></label>
        <% } else { %>
        <label>You are not logged in system</label>
        <% } %>
    </div>
    <div id = "userControls">
        <% if (request.getSession().getAttribute("user") != null) { %>
        <a href="<%=request.getContextPath()%>/logout">Logout</a>
        <% } else { %>
        <a href="<%=request.getContextPath()%>/passengerLogin.jsp">Login</a>
        <a href="<%=request.getContextPath()%>/register.jsp">Register</a>
        <% } %>
    </div>
</div>

<form method="post" action="TicketPurchase">
    <CENTER>
        <TABLE border="0"width="300px">
            <TR align="left">
                <TD width="150px">Train number:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Train number" value="<%=bean.getTrainNumber()%>">
                </TD>
            </TR>
            <TR>
                <TD colspan="2" align="center">
                    <INPUT TYPE="submit" value="Purchase" name="purchaseAction">
                    <INPUT TYPE="submit" value="Back" name="purchaseAction">
                </TD>
                <TD>
                    &nbsp;
                </TD>
            </TR>
        </TABLE>
        <TABLE border="0"width="300px">
            <TR>
                <TD>
                    <font color="red" width="300px"><%=bean.getValidationMessage()%></font>
                </TD>
            </TR>
            <TR>
                <TD>
                    <font color="red" width="300px"><%=bean.getProcessingErrorMessage()%></font>
                </TD>
            </TR>
        </TABLE>
    </CENTER>
</form>

</body>
</html>