<%@ page import="ru.tsystems.tsproject.sbb.bean.TimetableBean" %>
<%  String trainNumber = (String) request.getAttribute("trainNumber");
    TimetableBean bean = (TimetableBean)request.getAttribute("timetableBean");
    if (bean == null) {
        bean = new TimetableBean();
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding new timetable</title>
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

<form method="post" action="AddNewTrainStop">
    <CENTER>
        <TABLE border="0"width="60px">
            <tr align="center">
                <label> Add stop for train number: <%=trainNumber%> </label>
                <INPUT TYPE="text" NAME="Train number" value="<%=trainNumber%>" hidden="hidden">
            </tr>
            <TR align="center">
                <TD width="150px">Station name:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Station name" value="">
                </TD>
            </TR>
            <TR align="center">
                <TD width="150px">Departure date:</TD>
                <TD>
                    <INPUT TYPE="datetime-local" NAME="Departure date" value="">
                </TD>
            </TR>
            <TR>
                <TD colspan="2" align="center">
                    <INPUT TYPE="submit" value="add" name="stopAddAction">
                    <INPUT TYPE="submit" value="back" name="stopAddAction">
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