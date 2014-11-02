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
                                <li><a href="<%=request.getContextPath()%>/administrator/train/createNewTrain.jsp">Add new</a></li>
                                <li><a href="<%=request.getContextPath()%>/administrator/train/searchTrain.jsp">Search by number</a></li>
                                <li><a href="<%=request.getContextPath()%>/administrator/train/ViewAllTrains">Watch all</a></li>
                                <li class="divider"></li>
                            </security:authorize>
                            <li><a href="<%=request.getContextPath()%>/common/searchStation.jsp">Search by station</a></li>
                            <li><a href="<%=request.getContextPath()%>/common/searchStationDateTrain.jsp">Search by stations and date</a></li>
                        </ul>
                    </li>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Stations<span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="<%=request.getContextPath()%>/administrator/station/createNewStation.jsp">Add new</a></li>
                                <li><a href="<%=request.getContextPath()%>/administrator/station/ViewAllStations">Watch all</a></li>
                            </ul>
                        </li>
                        <li><a href="<%=request.getContextPath()%>/administrator/passengers/ViewAllPassengers">Passengers</a></li>
                    </security:authorize>
                    <security:authorize access="hasAnyRole('ROLE_PASSENGER', 'ROLE_ANONYMOUS')">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Tickets<span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="<%=request.getContextPath()%>/passenger/purchase.jsp">Purchase</a></li>
                                <li><a href="<%=request.getContextPath()%>/passenger/WatchTickets">Search my tickets</a></li>
                            </ul>
                        </li>
                    </security:authorize>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <security:authorize access="isAuthenticated()">
                            You are logged as: <security:authentication property="principal.username" />
                            <button type="button" onclick="location.href='<%=request.getContextPath()%>/logout'" class="btn btn-primary navbar-btn">Logout</button>
                        </security:authorize>
                        <security:authorize access="! isAuthenticated()">
                            You are not logged on system
                            <button type="button" onclick="location.href='<%=request.getContextPath()%>/login.jsp'" class="btn btn-success navbar-btn">Login</button>
                            <button type="button" onclick="location.href='<%=request.getContextPath()%>/register.jsp'" class="btn btn-primary navbar-btn">Register</button>
                        </security:authorize>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
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