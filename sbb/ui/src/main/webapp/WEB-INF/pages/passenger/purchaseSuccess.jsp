<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.TicketBean" %>
<% TicketBean bean = (TicketBean)request.getAttribute("purchaseResult");
    if (bean == null) {
        bean = new TicketBean();
    }
%>
<html>
<head>
    <title>Purchase Success</title>
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

<div class = inputBlockV2>
    <label>You have been purchased ticket successfully. Ticket Data:</label>
    <div class="col-sm-8">
        <div class="form-group">
            <table id = "resultData">
                <TR>
                    <TD>Ticket number:</TD>
                    <TD> <%=bean.getTicketNumber()%> </TD>
                </TR>
                <TR>
                    <TD>Train number:</TD>
                    <TD> <%=bean.getTrainNumber()%> </TD>
                </TR>
                <TR>
                    <TD>Document number:</TD>
                    <TD> <%=bean.getPassengerDocNumber()%> </TD>
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