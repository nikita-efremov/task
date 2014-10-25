<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.TimetableBean" %>
<%@ page import="ru.tsystems.tsproject.sbb.ValidationBean" %>
<% TimetableBean startBean = (TimetableBean)request.getAttribute("startBean");
    if (startBean == null) {
        startBean = new TimetableBean();
    }
    TimetableBean endBean = (TimetableBean)request.getAttribute("endBean");
    if (endBean == null) {
        endBean = new TimetableBean();
    }
    ValidationBean validationBeanStart = (ValidationBean)request.getAttribute("validationBeanStart");
    if (validationBeanStart == null) {
        validationBeanStart = new ValidationBean();
    }
    ValidationBean validationBeanEnd = (ValidationBean)request.getAttribute("validationBeanEnd");
    if (validationBeanEnd == null) {
        validationBeanEnd = new ValidationBean();
    }

%>
<html>
<head>
    <title>Search trains by stations and date</title>
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
    <label>To find trains by stations and dates, please fill the following fields: </label>
    <div class="col-sm-8">
        <form id = "trainSearchForm" class="form-horizontal" role="form" method="post" action="SearchStationDateTrain" >
            <div class="form-group">
                <label for="Station_start_name" class="col-sm-4 control-label">Station start name:</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="Station_start_name" placeholder="Station start name" name = "Station_start_name" value="<%=startBean.getStationName()%>">
                </div>
            </div>
            <div class="form-group">
                <label for="Start_date" class="col-sm-4 control-label">Start date after:</label>
                <div class="col-sm-7">
                    <input type="datetime-local" class="form-control" id="Start_date" placeholder="Start date" name = "Start_date" value="<%=startBean.getDate()%>">
                </div>
            </div>
            <div class="form-group">
                <label for="Station_end_name" class="col-sm-4 control-label">Station end name:</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="Station_end_name" placeholder="Station end name" name = "Station_end_name" value="<%=endBean.getStationName()%>">
                </div>
            </div>
            <div class="form-group">
                <label for="End_date" class="col-sm-4 control-label">End date before:</label>
                <div class="col-sm-7">
                    <input type="datetime-local" class="form-control" id="End_date" placeholder="End date" name = "End_date" value="<%=endBean.getDate()%>">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <button type="submit" name="stationDateTrainSearchAction" value="search trains" class="btn btn-success">search trains</button>
                    <input type=button class="btn btn-default" onClick="location.href='<%=request.getContextPath()%>/index.jsp'" value='Back'>
                </div>
            </div>
            <table id="validationMessages">
                <tr>
                    <td><%=validationBeanStart.getValidationMessage()%></td>
                </tr>
                <tr>
                    <td><%=validationBeanEnd.getValidationMessage()%></td>
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
</div>

<script src="<%=request.getContextPath()%>/resources/js/custom/complexTrainSearchValidation.js"></script>
</body>
</html>