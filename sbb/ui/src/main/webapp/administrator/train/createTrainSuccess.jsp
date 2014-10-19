<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.TrainBean" %>
<% TrainBean bean = (TrainBean)request.getAttribute("createResult");
%>
<html>
<head>
    <title>Train creating success</title>
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
    <label>Train has been created successfully</label>
    <table id = "resultData">
        <TR>
            <TD>id:</TD>
            <TD><%=bean.getId()%></TD>
        </TR>
        <TR>
            <TD>number:</TD>
            <TD><%=bean.getNumber()%></TD>
        </TR>
        <TR>
            <TD>seats:</TD>
            <TD><%=bean.getSeats()%></TD>
        </TR>
        <TR>
            <TD>total seats:</TD>
            <TD><%=bean.getTotalSeats()%></TD>
        </TR>
    </table>
    <table id="inputControls">
        <tr>
            <td>
                <input type=button onClick="location.href='<%=request.getContextPath()%>/administrator/administratorMain.jsp'" value='back'>
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
</div>

</body>
</html>