<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Search trains by stations and date</title>
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
                            <button type="button" onclick="location.href='${contextPath}/login'" class="btn btn-success navbar-btn">Login</button>
                            <button type="button" onclick="location.href='${contextPath}/register'" class="btn btn-info navbar-btn">Register</button>
                        </security:authorize>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</div>

<div class="panel panel-primary inputBlockV3">
    <div class="panel-heading">
        <h3 class="panel-title">Train search</h3>
    </div>
    <div class="panel-body">
        <div class="col-sm-8">
            <label>To find trains by stations and dates, please fill the following fields: </label>
            <form:form commandName="complexTrainSearchBean" id = "trainSearchForm" class="form-horizontal" role="form" method="post" action="SearchStationDateTrain" >
                <div class="form-group">
                    <label for="stationStartName" class="col-sm-4 control-label">Station start name:</label>
                    <div class="col-sm-7">
                        <form:input path="stationStartName" type="text" class="form-control" id="stationStartName" placeholder="Station start name"  value="${complexTrainSearchBean.stationStartName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="startDate" class="col-sm-4 control-label">Start date after:</label>
                    <div class="col-sm-7">
                        <form:input path="startDate" type="datetime-local" class="form-control" id="startDate" placeholder="Start date" value="<fmt:formatDate value='${complexTrainSearchBean.startDate}' pattern='yyyy-MM-dd'T'HH:mm' />"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="stationEndName" class="col-sm-4 control-label">Station end name:</label>
                    <div class="col-sm-7">
                        <form:input path="stationEndName" type="text" class="form-control" id="stationEndName" placeholder="Station end name" value="${complexTrainSearchBean.stationEndName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="endDate" class="col-sm-4 control-label">End date before:</label>
                    <div class="col-sm-7">
                        <form:input path="endDate" type="datetime-local" class="form-control" id="endDate" placeholder="End date" value="<fmt:formatDate value='${complexTrainSearchBean.endDate}' pattern='yyyy-MM-dd'T'HH:mm' />"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-10">
                        <button type="submit" name="stationDateTrainSearchAction" value="search trains" class="btn btn-success">Search trains</button>
                        <input type=button class="btn btn-default" onClick="location.href='${contextPath}/index'" value='Back'>
                    </div>
                </div>
                <table id="validationMessages">
                    <tr>
                        <td>${validationBean.validationMessage}</td>
                    </tr>
                    <tr>
                        <td>${complexTrainSearchBean.processingErrorMessage}</td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</div>

<script src="${contextPath}/resources/js/custom/complexTrainSearchValidation.js"></script>
</body>
</html>