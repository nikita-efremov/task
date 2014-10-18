<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose administrator action</title>
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

<a href="<%=request.getContextPath()%>/">back to passenger menu</a>
<form method="get" action="AdminActionResolver">
    <CENTER>
        <TABLE border="0"width="600px">
            <TR align = "center">
                <TD>
                    Select action
                </TD>
            </TR>
            <TR align = "center">
                <TD>
                    <select name="adminAction" size="2">
                        <option>Add new train</option>
                        <option>Search train</option>
                        <option>Add new station</option>
                        <option>Watch all trains</option>
                        <option>Watch all stations</option>
                    </select>
                </TD>
            </TR>
            <TR align = "center">
                <TD>
                    <INPUT TYPE="submit" value="Submit">
                </TD>
            </TR>
        </TABLE>
    </CENTER>
</form>
</body>
</html>


