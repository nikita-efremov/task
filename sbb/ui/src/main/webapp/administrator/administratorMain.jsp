<%--
  Created by IntelliJ IDEA.
  User: herr
  Date: 03.10.14
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose administrator action:</title>
</head>
<body>
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
<div id = "userPanel">
    <div id = "userInfo">
        <label> user name: <%=request.getSession().getAttribute("user")%></label>
    </div>
    <a href="<%=request.getContextPath()%>/logout">logout</a>
</div>
</body>
</html>


