<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.StationBean" %>
<% StationBean bean = (StationBean)request.getAttribute("searchResult");
    if (bean == null) {
        bean = new StationBean();
    }

%>
<html>
<head>
    <title>Station search</title>
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

<div id="commonOptions">
    <table id="commonOptionsTable">
        <tbody>
        <tr class="optionButtonsTR">
            <td class="common_link_td">
                <a class="top_home_link" href="<%=request.getContextPath()%>/common/searchStation.jsp">Trains by station</a>
            </td>
            <td class="common_link_td">
                <a class="top_home_link" href="<%=request.getContextPath()%>/common/searchStationDateTrain.jsp">Trains by stations and date</a>
            </td>
            <td class="common_link_td">
                <a class="top_home_link" href="<%=request.getContextPath()%>/passenger/purchase.jsp">Purchase ticket</a>
            </td>
            <td class="common_link_td">
                <a class="top_home_link" href="<%=request.getContextPath()%>/administrator/">I am admin</a>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<div class = inputBlock>
    <label>To find trains by station name, please fill the following fields: </label>
    <form method="post" action="SearchStation">
        <table id = "inputData">
            <tr>
                <td>Station name:</TD>
                <td>
                    <input type="text" name="Station name" value="<%=bean.getName()%>">
                </td>
            </tr>
        </table>
        <table id="inputControls">
            <tr>
                <td>
                <input type="submit" value="search" name="stationSearchAction">
                <input type="submit" value="back" name="stationSearchAction">
                <% if (bean.getId() > 0) { %>
                    <input type="submit" value="watch timetable" name="stationSearchAction">
                <% } %>
                </td>
            </tr>
        </table>
        <table id="validationMessages">
            <tr>
                <td><%=bean.getValidationMessage()%></td>
            </tr>
            <tr>
                <td><%=bean.getProcessingErrorMessage()%></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>