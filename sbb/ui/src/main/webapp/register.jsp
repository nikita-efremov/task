<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.PassengerBean" %>
<%@ page import="ru.tsystems.tsproject.sbb.ValidationBean" %>
<% PassengerBean bean = (PassengerBean)request.getAttribute("createResult");
    if (bean == null) {
        bean = new PassengerBean();
    }
    ValidationBean validationBean = (ValidationBean)request.getAttribute("validationBean");
    if (validationBean == null) {
        validationBean = new ValidationBean();
    }
%>
<html>
<head>
    <title>Passenger register page</title>
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
    <label>To register in system, please fill the following fields: </label>
    <div class="col-sm-8">
        <form class="form-horizontal" role="form" method="post" action="RegisterPassenger" id = "registerForm">
            <div class="form-group">
                <label for="First_name" class="col-sm-4 control-label">First name:</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="First_name" placeholder="First name" name = "First_name" value = "<%=bean.getFirstName()%>">
                </div>
            </div>
            <div class="form-group">
                <label for="Last_name" class="col-sm-4 control-label">Last name:</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="Last_name" placeholder="Last name" name = "Last_name" value = "<%=bean.getLastName()%>">
                </div>
            </div>
            <div class="form-group">
                <label for="Birth_date" class="col-sm-4 control-label">Birth date:</label>
                <div class="col-sm-7">
                    <input type="date" class="form-control" id="Birth_date" placeholder="Birth date" name = "Birth_date" value = "<%=bean.getBirthDate()%>">
                </div>
            </div>
            <div class="form-group">
                <label for="Document_number" class="col-sm-4 control-label">Document number:</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="Document_number" placeholder="Document number" name = "Document_number" value = "<%=bean.getDocNumber()%>">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <button type="submit" name="passengerRegisterAction" value="register" class="btn btn-success">Register</button>
                    <input type=button class="btn btn-default" onClick="location.href='<%=request.getContextPath()%>/index.jsp'" value='Back'>
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

<script src="<%=request.getContextPath()%>/resources/js/custom/registerValidation.js"></script>
</body>
</html>