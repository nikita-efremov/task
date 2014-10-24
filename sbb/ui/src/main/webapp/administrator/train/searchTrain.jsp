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

<div class = inputBlock>
    <label>To create a new train, please fill the following fields: </label>
    <form method="post" action="SearchTrain">
        <table id = "inputData">
            <TR>
                <TD>Train number:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Train number" value="<%=bean.getNumber()%>">
                </TD>
            </TR>
            <% if (!bean.getSeats().equals("")) { %>
            <TR>
                <TD>Seats:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Seats" value="<%=bean.getSeats()%>">
                </TD>
            </TR>
            <TR>
                <TD>Total seats:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Total seats" value="<%=bean.getTotalSeats()%>">
                </TD>
            </TR>
            <% } %>
        </table>
        <table id="inputControls">
            <tr>
                <td>
                    <INPUT TYPE="submit" value="search" name="trainSearchAction">
                    <INPUT TYPE="submit" value="back" name="trainSearchAction">
                    <% if (!bean.getSeats().equals("")) { %>
                    <INPUT TYPE="submit" value="watch passengers" name="trainSearchAction">
                    <INPUT TYPE="submit" value="watch timetable" name="trainSearchAction">
                    <% } %>
                </td>
            </tr>
        </table>
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

</body>
</html>