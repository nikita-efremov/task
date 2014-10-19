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

<div id="adminOptions">
    <table id="adminOptionsTable">
        <tbody>
        <tr class="optionButtonsTR">
            <td class="common_link_td">
                <a class="top_home_link" href="<%=request.getContextPath()%>/administrator/train/trainsIndex.jsp">Trains</a>
            </td>
            <td class="common_link_td">
                <a class="top_home_link" href="<%=request.getContextPath()%>/administrator/station/stationsIndex.jsp">Stations</a>
            </td>
            <td class="common_link_td">
                <a class="top_home_link" href="<%=request.getContextPath()%>/index.jsp">Back to passenger options</a>
            </td>
        </tr>
        </tbody>
    </table>
</div>

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
</body>
</html>