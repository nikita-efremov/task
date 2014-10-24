<%@ page import="ru.tsystems.tsproject.sbb.bean.PassengerBean" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.TrainBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="ru.tsystems.tsproject.sbb.ValidationBean" %>
<% TrainBean bean = (TrainBean)request.getAttribute("trainBean");
    if (bean == null) {
        bean = new TrainBean();
    }
    ValidationBean validationBean = (ValidationBean)request.getAttribute("validationBean");
    if (validationBean == null) {
        validationBean = new ValidationBean();
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
    <label>Passengers of train number <%=bean.getNumber()%></label>
    <table id = "resultData">
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
            <td><%=new SimpleDateFormat("dd-MM-yyyy").format(passengerBean.getBirthDate())%></td>
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
            <td><%=validationBean.getValidationMessage()%></td>
        </tr>
        <tr>
            <td><%=bean.getProcessingErrorMessage()%></td>
        </tr>
    </table>
</div>

</body>
</html>