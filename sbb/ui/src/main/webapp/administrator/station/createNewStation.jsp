<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.StationBean" %>
<%@ page import="ru.tsystems.tsproject.sbb.ValidationBean" %>
<% StationBean bean = (StationBean)request.getAttribute("createResult");
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
    <title>Creating new staion</title>
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
    <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    <div id = "userInfo">
        <security:authorize access="isAuthenticated()">
            You are logged as: <security:authentication property="principal.username" />
        </security:authorize>
        <security:authorize access="! isAuthenticated()">
            You are not logged on system
        </security:authorize>
    </div>
    <div id = "userControls">
        <security:authorize access="isAuthenticated()">
            <a href="<%=request.getContextPath()%>/logout">Logout</a>
        </security:authorize>
        <security:authorize access="! isAuthenticated()">
            <a href="<%=request.getContextPath()%>/login.jsp">Login</a>
            <a href="<%=request.getContextPath()%>/register.jsp">Register</a>
        </security:authorize>
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

<div class = inputBlockV2>
    <label>To create a new station, please fill the following fields: </label>
    <div class="col-sm-8">
        <form class="form-horizontal" role="form" method="post" action="CreateNewStation" id = "stationForm">
            <div class="form-group">
                <label for="Station_name" class="col-sm-4 control-label">Station name:</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="Station_name" placeholder="Station name" name = "Station_name" value = "<%=bean.getName()%>">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <button type="submit" name="stationCreateAction" value="create" class="btn btn-success">Create</button>
                    <input type=button class="btn btn-default" onClick="location.href='<%=request.getContextPath()%>/administrator/administratorMain.jsp'" value='Back'>
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
<script src="<%=request.getContextPath()%>/resources/js/custom/stationValidation.js"></script>
</body>
</html>