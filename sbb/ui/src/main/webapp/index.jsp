<html>
<body>
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
