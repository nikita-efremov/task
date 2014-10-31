<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.TrainBean" %>
<html>
<head>
    <title>All trains</title>
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

<div class = inputBlockV2>
    <label>All trains</label>
    <div class="col-sm-8">
        <div class="form-group">
            <table id = "resultData">
                <tr>
                    <th>ID</th>
                    <th>Number</th>
                    <th>Seats</th>
                    <th>Total seats</th>
                    <th>Timetable</th>
                    <th>Passengers</th>
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
                        <a href="<%=request.getContextPath()%>/administrator/train/SearchTrain?trainSearchAction=watch timetable&Train_number=<%=bean.getNumber()%>">watch</a>
                    </td>
                    <td>
                        <a href="<%=request.getContextPath()%>/administrator/train/SearchTrain?trainSearchAction=watch passengers&Train_number=<%=bean.getNumber()%>">watch</a>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </table>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <input type=button class="btn btn-primary" onClick="history.go(-1);" value='Back'>
            </div>
        </div>
        <table id="validationMessages">
        </table>
    </div>
</div>

</body>
</html>