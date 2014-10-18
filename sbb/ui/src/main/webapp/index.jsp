<html>
<head>
    <title>Start page</title>
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

<a href="<%=request.getContextPath()%>/administrator/">Login as admin</a>
<h2 align = "center">SBB system</h2>
<form method="get" action="CommonAction">
    <CENTER>
        <TABLE border="0"width="600px">
            <TR align = "center">
                <TD>
                    Select action
                </TD>
            </TR>
            <TR align = "center">
                <TD>
                    <select name="commonAction" size="2">
                        <option>Watch station timetable</option>
                        <option>Search train by stations and date</option>
                        <option>Purchase ticket</option>
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
