<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Passenger register page</title>
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
        <h3 class="panel-title">Register</h3>
    </div>
    <div class="panel-body">
        <div class="col-sm-8">
            <label>To register in system, please fill the following fields: </label>
            <form:form commandName="passengerBean" class="form-horizontal" role="form" method="post" action="RegisterPassenger" id = "registerForm">
                <div class="form-group">
                    <label for="firstName" class="col-sm-4 control-label">First name:</label>
                    <div class="col-sm-7">
                        <form:input path="firstName" type="text" class="form-control" id="firstName" placeholder="First name" value = "${passengerBean.firstName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="lastName" class="col-sm-4 control-label">Last name:</label>
                    <div class="col-sm-7">
                        <form:input path="lastName" type="text" class="form-control" id="lastName" placeholder="Last name" value = "${passengerBean.lastName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="birthDate" class="col-sm-4 control-label">Birth date:</label>
                    <div class="col-sm-7">
                        <form:input path="birthDate" type="date" class="form-control" id="birthDate" placeholder="Birth date" value = "<fmt:formatDate value='${passengerBean.birthDate}' pattern='yyyy-MM-dd' />"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="docNumber" class="col-sm-4 control-label">Document number:</label>
                    <div class="col-sm-7">
                        <form:input path="docNumber" type="text" class="form-control" id="docNumber" placeholder="Document number" value = "${passengerBean.docNumber}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-4 control-label">Password:</label>
                    <div class="col-sm-7">
                        <form:input path="password" type="password" class="form-control" id="password" placeholder="Password" value = ""/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirmPassword" class="col-sm-4 control-label">Confirm password:</label>
                    <div class="col-sm-7">
                        <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm password" name = "confirmPassword" value = "">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-10">
                        <button type="submit" name="passengerRegisterAction" value="register" class="btn btn-success">Register</button>
                        <input type=button class="btn btn-default" onClick="location.href='${contextPath}/index.jsp'" value='Back'>
                    </div>
                </div>
                <table id="validationMessages">
                    <tr>
                        <td>${validationBean.validationMessage}</td>
                    </tr>
                    <tr>
                        <td>${passengerBean.processingErrorMessage}</td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</div>

<script src="${contextPath}/resources/js/custom/registerValidation.js"></script>
</body>
</html>