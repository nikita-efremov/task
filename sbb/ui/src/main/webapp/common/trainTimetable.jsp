<%@ page import="ru.tsystems.tsproject.sbb.bean.TrainBean" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.TimetableBean" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.text.SimpleDateFormat" %>
<% TrainBean bean = (TrainBean)request.getAttribute("trainBean");
    if (bean == null) {
        bean = new TrainBean();
    }
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Train timetable</title>
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
    <label>Timetable of train number <%=bean.getNumber()%></label>
    <table id = "resultData">
        <tr>
            <th>Station name</th>
            <th>Departure date</th>
        </tr>
        <%
            Set set = (Set)bean.getTimetables();
            if(set != null)
            {
                for (Object o: set) {
                    TimetableBean timetableBean = (TimetableBean)o;

        %>
        <tr>
            <td><%=timetableBean.getStationName()%></td>
            <td><%=new SimpleDateFormat("dd-MM-yyyy HH:mm").format(timetableBean.getDate())%></td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <table id="inputControls">
        <tr>
            <td>
                <INPUT TYPE="button" VALUE="back" onClick="history.go(-1);">
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