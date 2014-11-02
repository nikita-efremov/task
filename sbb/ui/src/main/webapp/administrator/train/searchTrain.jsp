<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.TrainBean" %>
<%@ page import="ru.tsystems.tsproject.sbb.ValidationBean" %>
<% TrainBean bean = (TrainBean)request.getAttribute("searchResult");
    if (bean == null) {
        bean = new TrainBean();
    }
    ValidationBean validationBean = (ValidationBean)request.getAttribute("validationBean");
    if (validationBean == null) {
        validationBean = new ValidationBean();
    }
%>
<html>
<head>
    <title>Train search</title>
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
                <a class="top_home_link" href="<%=request.getContextPath()%>/administrator/passengers/ViewAllPassengers">Passengers</a>
            </td>
            <td class="common_link_td">
                <a class="top_home_link" href="<%=request.getContextPath()%>/index.jsp">Back to passenger options</a>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<div class = inputBlockV2>
    <label>To search a train, please fill the following fields: </label>
    <div class="col-sm-8">
        <form class="form-horizontal" role="form" method="post" action="SearchTrain" id = "trainSearchForm">
            <div class="form-group">
                <label for="Train_number" class="col-sm-4 control-label">Train number:</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="Train_number" placeholder="Train number" name = "Train_number" value = "<%=bean.getNumber()%>">
                </div>
            </div>
            <% if (!bean.getSeats().equals("")) { %>
            <div class="form-group">
                <label for="Seats" class="col-sm-4 control-label">Seats:</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="Seats" placeholder="Seats" name = "Seats" value = "<%=bean.getSeats()%>" disabled = "disabled">
                </div>
            </div>
            <div class="form-group">
                <label for="Total_seats" class="col-sm-4 control-label">Total seats:</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="Total_seats" placeholder="Total seats" name = "Total_seats" value = "<%=bean.getTotalSeats()%>"  disabled = "disabled">
                </div>
            </div>
            <% } %>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <button type="submit" name="trainSearchAction" value="search" class="btn btn-success">Search</button>
                    <input type=button class="btn btn-default" onClick="location.href='<%=request.getContextPath()%>/administrator/train/SearchTrain?trainSearchAction=back&Train_number=<%=bean.getNumber()%>'" value='Back'>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <% if (!bean.getSeats().equals("")) { %>
                    <input type=button class="btn btn-success" onClick="location.href='<%=request.getContextPath()%>/administrator/train/SearchTrain?trainSearchAction=watch passengers&Train_number=<%=bean.getNumber()%>'" value='Watch passengers'>
                    <input type=button class="btn btn-success" onClick="location.href='<%=request.getContextPath()%>/administrator/train/SearchTrain?trainSearchAction=watch timetable&Train_number=<%=bean.getNumber()%>'" value='Watch timetable'>
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
<script src="<%=request.getContextPath()%>/resources/js/custom/trainSearchValidation.js"></script>
</body>
</html>