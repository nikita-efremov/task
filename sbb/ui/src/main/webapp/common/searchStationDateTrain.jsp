<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.TimetableBean" %>
<% TimetableBean startBean = (TimetableBean)request.getAttribute("startBean");
    if (startBean == null) {
        startBean = new TimetableBean();
    }
    TimetableBean endBean = (TimetableBean)request.getAttribute("endBean");
    if (endBean == null) {
        endBean = new TimetableBean();
    }

%>
<html>
<head>
    <title>Search trains by stations and date</title>
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
    <label>To find trains by stations and dates, please fill the following fields: </label>
    <form method="post" action="SearchStationDateTrain">
        <table id = "inputData">
            <tr>
                <td>Station start name:</TD>
                <td>
                    <input type="text" name="Station start name" value="<%=startBean.getStationName()%>">
                </td>
            </tr>
            <tr>
                <td>Start date after:</TD>
                <td>
                    <input type="datetime-local" name="Start date" value="<%=startBean.getDate()%>">
                </td>
            </tr>
            <tr>
                <td>Station end name:</TD>
                <td>
                    <input type="text" name="Station end name" value="<%=endBean.getStationName()%>">
                </td>
            </tr>
            <tr>
                <td>End date before:</TD>
                <td>
                    <input type="datetime-local" name="End date" value="<%=endBean.getDate()%>">
                </td>
            </tr>
        </table>
        <table id="inputControls">
            <tr>
                <td>
                    <INPUT TYPE="submit" value="search trains" name="stationDateTrainSearchAction">
                    <INPUT TYPE="submit" value="back" name="stationDateTrainSearchAction">
                </td>
            </tr>
        </table>
        <table id="validationMessages">
            <tr>
                <td><%=startBean.getValidationMessage()%></td>
            </tr>
            <tr>
                <td><%=endBean.getValidationMessage()%></td>
            </tr>
            <tr>
                <td><%=startBean.getProcessingErrorMessage()%></td>
            </tr>
            <tr>
                <td><%=endBean.getProcessingErrorMessage()%></td>
            </tr>
        </table>
    </form>
</div>


</body>
</html>