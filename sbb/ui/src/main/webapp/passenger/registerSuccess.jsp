<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.PassengerBean" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<% PassengerBean bean = (PassengerBean)request.getAttribute("createResult");
    if (bean == null) {
        bean = new PassengerBean();
    }
%>
<html>
<head>
    <title>Register Success</title>
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
    <label>You have been registered successfully</label>
    <div class="col-sm-8">
        <div class="form-group">
            <table id = "resultData">
                <TR>
                    <TD>Username:</TD>
                    <TD> <%=bean.getDocNumber()%> </TD>
                </TR>
                <TR>
                    <TD>First name:</TD>
                    <TD> <%=bean.getFirstName()%> </TD>
                </TR>
                <TR>
                    <TD>Last name:</TD>
                    <TD> <%=bean.getLastName()%> </TD>
                </TR>
                <TR>
                    <TD>Document number:</TD>
                    <TD> <%=bean.getDocNumber()%> </TD>
                </TR>
                <TR>
                    <TD>Birth date:</TD>
                    <TD> <%=new SimpleDateFormat("dd-MM-yyyy").format(bean.getBirthDate())%> </TD>
                </TR>
            </table>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <input type=button class="btn btn-primary" onClick="location.href='<%=request.getContextPath()%>/index.jsp'" value='Exit'>
            </div>
        </div>
        <table id="validationMessages">
            <tr>
                <td></td>
            </tr>
            <tr>
                <td><%=bean.getProcessingErrorMessage()%></td>
            </tr>
        </table>
    </div>
</div>

</body>
</html>