<%--
  Created by IntelliJ IDEA.
  User: herr
  Date: 03.10.14
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.PassengerBean" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<% PassengerBean bean = (PassengerBean)request.getAttribute("createResult");
    if (bean == null) {
        bean = new PassengerBean();
    }
%>
<html>
<head>
    <title>Register Success</title>
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

<h2> You have been registered successfully</h2>
<a href="<%=request.getContextPath()%>/index.jsp">back to options page</a>
<div id = "passengerData">
    <CENTER>
        <TABLE border="0"width="60px">
            <TR align="center">
                <TD width="150px">First name:</TD>
                <TD> <%=bean.getFirstName()%> </TD>
            </TR>
            <TR align="center">
                <TD width="150px">Last name:</TD>
                <TD> <%=bean.getLastName()%> </TD>
            </TR>
            <TR align="center">
                <TD width="150px">Document number:</TD>
                <TD> <%=bean.getDocNumber()%> </TD>
            </TR>
            <TR align="center">
                <TD width="150px">Birth date:</TD>
                <TD> <%=new SimpleDateFormat("dd-MM-yyyy").format(bean.getBirthDate())%> </TD>
            </TR>
        </TABLE>
    </CENTER>
</div>

</body>
</html>