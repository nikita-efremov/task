<%@ page import="ru.tsystems.tsproject.sbb.bean.PassengerBean" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.TrainBean" %>
<% TrainBean bean = (TrainBean)request.getAttribute("trainBean");
    if (bean == null) {
        bean = new TrainBean();
    }
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Passengers of train</title>
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

<a href="<%=request.getContextPath()%>/administrator/">back</a>
<CENTER>
    <table width="150"border="1">
        <tr>Passengers of train number <%=bean.getNumber()%> </tr>
        <tr>
            <th>Document number</th>
            <th>Full name</th>
            <th>Birth date</th>
        </tr>
        <%
            List list = (List)request.getAttribute("trainPassengers");
            if(list != null)
            {
                for (Object o: list) {
                    PassengerBean passengerBean = (PassengerBean)o;

        %>
        <tr>
            <td><%=passengerBean.getDocNumber()%></td>
            <td><%=passengerBean.getLastName()%> <%=passengerBean.getFirstName()%></td>
            <td><%=passengerBean.getBirthDate()%></td>
        </tr>
        <%
                }
            }
        %>
    </table>
</CENTER>
</body>
</html>