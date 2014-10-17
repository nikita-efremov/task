<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.StationBean" %>
<% StationBean bean = (StationBean)request.getAttribute("searchResult");
    if (bean == null) {
        bean = new StationBean();
    }

%>
<html>
<head>
    <title>Train search</title>
</head>
<body>
<form method="post" action="SearchStation">
    <CENTER>
        <TABLE border="0"width="300px">
            <TR align="left">
                <TD width="150px">Station name:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Station name" value="<%=bean.getName()%>">
                </TD>
            </TR>
            <% if (bean.getId() > 0) { %>
            <TR align="left">
                <TD width="150px">Seats:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Seats" value="<%=bean.getId()%>">
                </TD>
            </TR>
            <% } %>
            <TR>
                <TD colspan="2" align="center">
                    <INPUT TYPE="submit" value="search" name="stationSearchAction">
                    <INPUT TYPE="submit" value="back" name="stationSearchAction">
                </TD>
                <TD>
                    &nbsp;
                </TD>
            </TR>
            <TR>
                <TD colspan="2" align="center">
                    <% if (bean.getId() > 0) { %>
                    <INPUT TYPE="submit" value="watch timetable" name="stationSearchAction">
                    <% } %>
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
<div id = "userPanel">
    <div id = "userInfo">
        <label> user name: <%=request.getSession().getAttribute("user")%></label>
    </div>
    <a href="<%=request.getContextPath()%>/logout">logout</a>
</div>
</body>
</html>