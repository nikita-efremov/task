<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.StationBean" %>
<%@ page import="ru.tsystems.tsproject.sbb.ValidationBean" %>
<% StationBean bean = (StationBean)request.getAttribute("searchResult");
    if (bean == null) {
        bean = new StationBean();
    }
    ValidationBean validationBean = (ValidationBean)request.getAttribute("validationBean");
    if (validationBean == null) {
        validationBean = new ValidationBean();
    }
%>
<html>
<head>
    <title>Station search</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/js/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/js/bootstrap/css/bootstrap-theme.min.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/styles/main.css">

    <script src="<%=request.getContextPath()%>/resources/js/jquery/jquery-2.1.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrapValidator/bootstrapValidator.js"></script>
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

<div class = inputBlockV2>
    <label>To find trains by station name, please fill the following fields: </label>
    <div class="col-sm-8">
        <form class="form-horizontal" role="form" method="post" action="SearchStation" id = "stationSearchForm">
            <div class="form-group">
                <label for="Station_name" class="col-sm-4 control-label">Station name:</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="Station_name" placeholder="Station name" name = "Station_name" value = "<%=bean.getName()%>">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <button type="submit" name="stationSearchAction" value="search" class="btn btn-success">Search</button>
                    <input type=button class="btn btn-default" onClick="location.href='<%=request.getContextPath()%>/index.jsp'" value='back'>
                    <% if (bean.getId() > 0) { %>
                    <input type=button class="btn btn-success" onClick="location.href='<%=request.getContextPath()%>/common/SearchStation?stationSearchAction=watch timetable&Station_name=<%=bean.getName()%>'" value='watch timetable'>
                    <% } %>
                </div>
            </div>

            <table id="validationMessages">
                <tr>
                    <td><%=validationBean.getValidationMessage()%></td>
                </tr>
                <tr>
                    <td><%=bean.getProcessingErrorMessage()%></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<script src="<%=request.getContextPath()%>/resources/js/custom/stationSearchValidation.js"></script>
</body>
</html>