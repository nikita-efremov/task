<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.PassengerBean" %>
<% PassengerBean bean = (PassengerBean)request.getAttribute("loginResult");
    if (bean == null) {
        bean = new PassengerBean();
    }
%>
<html>
<head>
    <title>Passenger login page</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/styles/main.css"/>
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

<div class = inputBlock>
    <label>To login in system, please fill the following fields: </label>
    <form method="post" action="PassengerLogin">
        <table id = "inputData">
            <tr>
                <td>Document number:</TD>
                <td>
                    <input type="text" name="Document number" value="">
                </td>
            </tr>
        </table>
        <table id="inputControls">
            <tr>
                <td>
                    <INPUT TYPE="submit" value="Login" name="loginAction">
                    <INPUT TYPE="submit" value="Back" name="loginAction">
                </td>
            </tr>
        </table>
        <table id="validationMessages">
            <tr>
                <td><%=bean.getValidationMessage()%></td>
            </tr>
            <tr>
                <td><%=bean.getProcessingErrorMessage()%></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>