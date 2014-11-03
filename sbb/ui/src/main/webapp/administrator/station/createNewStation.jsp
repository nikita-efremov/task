<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
    <link rel="stylesheet" href="${contextPath}/resources/js/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/js/bootstrap/css/bootstrap-theme.min.css">
    <link type="text/css" rel="stylesheet" href="${contextPath}/resources/styles/main.css">

    <script src="${contextPath}/resources/js/jquery/jquery-2.1.1.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrapValidator/bootstrapValidator.js"></script>
</head>
<body>
<div id="mainHeader">
    <span id = "title-pic">
        <a href="${contextPath}/"><img src="${contextPath}/resources/images/logo_sbb.png" width="75" height="75" alt="Git"></a>
    </span>
    <span id = "title">
        <label>SBB railways</label>
    </span>
</div>

<div id="userPanelV2">
    <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="collapse navbar-collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav nav-pills">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Trains<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <security:authorize access="hasRole('ROLE_ADMIN')">
                                <li><a href="${contextPath}/administrator/train/createNewTrain">Add new</a></li>
                                <li><a href="${contextPath}/administrator/train/searchTrain">Search by number</a></li>
                                <li><a href="${contextPath}/administrator/train/ViewAllTrains">Watch all</a></li>
                                <li class="divider"></li>
                            </security:authorize>
                            <li><a href="${contextPath}/common/searchStation">Search by station</a></li>
                            <li><a href="${contextPath}/common/searchStationDateTrain">Search by stations and date</a></li>
                        </ul>
                    </li>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Stations<span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="${contextPath}/administrator/station/createNewStation">Add new</a></li>
                                <li><a href="${contextPath}/administrator/station/ViewAllStations">Watch all</a></li>
                            </ul>
                        </li>
                        <li><a href="${contextPath}/administrator/passengers/ViewAllPassengers">Passengers</a></li>
                    </security:authorize>
                    <security:authorize access="hasAnyRole('ROLE_PASSENGER', 'ROLE_ANONYMOUS')">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Tickets<span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="${contextPath}/passenger/purchase">Purchase</a></li>
                                <li><a href="${contextPath}/passenger/WatchTickets">Search my tickets</a></li>
                            </ul>
                        </li>
                    </security:authorize>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <security:authorize access="isAuthenticated()">
                            You are logged as: <security:authentication property="principal.username" />
                            <button type="button" onclick="location.href='${contextPath}/logout'" class="btn btn-primary navbar-btn">Logout</button>
                        </security:authorize>
                        <security:authorize access="! isAuthenticated()">
                            You are not logged on system
                            <button type="button" onclick="location.href='${contextPath}/login.jsp'" class="btn btn-success navbar-btn">Login</button>
                            <button type="button" onclick="location.href='${contextPath}/register'" class="btn btn-primary navbar-btn">Register</button>
                        </security:authorize>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</div>

<div class="panel panel-primary inputBlockV3">
    <div class="panel-heading">
        <h3 class="panel-title">Station creation</h3>
    </div>
    <div class="panel-body">
        <div class="col-sm-8">
            <label>To create a new station, please fill the following fields: </label>
            <form:form class="form-horizontal" role="form" method="post" action="CreateNewStation" id = "stationForm">
                <div class="form-group">
                    <label for="Station_name" class="col-sm-4 control-label">Station name:</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="Station_name" placeholder="Station name" name = "Station_name" value = "<%=bean.getName()%>">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-10">
                        <button type="submit" name="stationCreateAction" value="create" class="btn btn-success">Create</button>
                        <input type=button class="btn btn-default" onClick="location.href='${contextPath}/administrator/administratorMain.jsp'" value='Back'>
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
            </form:form>
        </div>
    </div>
</div>
<script src="${contextPath}/resources/js/custom/stationValidation.js"></script>
</body>
</html>