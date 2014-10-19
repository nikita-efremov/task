<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.TrainBean" %>
<html>
<head>
    <title>All trains</title>
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

<CENTER>
    <table width="150"border="1">
        <tr>
            <th>ID</th>
            <th>Number</th>
            <th>Seats</th>
            <th>Total seats</th>
            <th>Watch timetable</th>
            <th>Watch passengers</th>
        </tr>
        <%
            List list = (List)request.getAttribute("allTrains");
            if(list!=null)
            {
                for(int i=0 ; i< list.size();i++)
                {
                    TrainBean bean =(TrainBean) list.get(i);
        %>
        <tr>
            <td><%=bean.getId()%></td>
            <td><%=bean.getNumber()%></td>
            <td><%=bean.getSeats()%></td>
            <td><%=bean.getTotalSeats()%></td>
            <td>
                <a href="<%=request.getContextPath()%>/administrator/train/SearchTrain?trainSearchAction=watch timetable&Train number=<%=bean.getNumber()%>">watch</a>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/administrator/train/SearchTrain?trainSearchAction=watch passengers&Train number=<%=bean.getNumber()%>">watch</a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</CENTER>
</body>
</html>