<%--
  Created by IntelliJ IDEA.
  User: herr
  Date: 17.10.14
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.TrainBean" %>
<% TrainBean bean = (TrainBean)request.getAttribute("searchResult");
    if (bean == null) {
        bean = new TrainBean();
    }

%>
<html>
<head>
    <title>Train search</title>
</head>
<body>
<form method="post" action="SearchTrain">
    <CENTER>
        <TABLE border="0"width="300px">
            <TR align="left">
                <TD width="150px">Train number:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Train number" value="<%=bean.getNumber()%>">
                </TD>
            </TR>
            <% if (!bean.getSeats().equals("")) { %>
            <TR align="left">
                <TD width="150px">Seats:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Seats" value="<%=bean.getSeats()%>">
                </TD>
            </TR>
            <TR align="left">
                <TD width="150px">Total seats:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Total seats" value="<%=bean.getTotalSeats()%>">
                </TD>
            </TR>
            <% } %>
            <TR>
                <TD colspan="2" align="center">
                    <INPUT TYPE="submit" value="search" name="trainSearchAction">
                    <INPUT TYPE="submit" value="back" name="trainSearchAction">
                </TD>
                <TD>
                    &nbsp;
                </TD>
            </TR>
            <TR>
                <TD colspan="2" align="center">
                    <% if (!bean.getSeats().equals("")) { %>
                    <INPUT TYPE="submit" value="watch passengers" name="trainSearchAction">
                    <INPUT TYPE="submit" value="watch timetable" name="trainSearchAction">
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