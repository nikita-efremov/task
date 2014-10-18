<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.PassengerBean" %>
<% PassengerBean bean = (PassengerBean)request.getAttribute("loginResult");
    if (bean == null) {
        bean = new PassengerBean();
    }
%>
<html>
<head>
    <title>Passenger login page</title>
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

<h2 align ="center"> Please, login in system</h2>
<a href="<%=request.getContextPath()%>/">back</a>
<form method="post" action="PassengerLogin">
    <CENTER>
        <TABLE border="0"width="60px">
            <TR align="center">
                <TD width="150px">Document number:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Document number" value="">
                </TD>
            </TR>
            <TR>
                <TD colspan="2" align="center">
                    <INPUT TYPE="submit" value="Login" name="loginAction">
                    <INPUT TYPE="submit" value="Back" name="loginAction">
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
                    <font color="red" width="300px"><%=bean.getProcessingErrorMessage()%></font>
                </TD>
            </TR>
        </TABLE>
    </CENTER>
</form>

</body>
</html>