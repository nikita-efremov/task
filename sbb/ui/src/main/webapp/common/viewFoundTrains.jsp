<%--
  Created by IntelliJ IDEA.
  User: herr
  Date: 05.10.14
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.TrainBean" %>
<html>
<head>
    <title>All stations</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/">back</a>
<CENTER>
    <table width="150"border="1">
        <tr>
            <th>Number</th>
            <th>Seats</th>
            <th>Total seats</th>
            <th>Watch timetable</th>
        </tr>
        <%
            List list = (List)request.getAttribute("foundTrains");
            if(list!=null)
            {
                for(int i=0 ; i< list.size();i++)
                {
                    TrainBean bean =(TrainBean) list.get(i);
        %>
        <tr>
            <td><%=bean.getNumber()%></td>
            <td><%=bean.getSeats()%></td>
            <td><%=bean.getTotalSeats()%></td>
            <td>
                <a href="<%=request.getContextPath()%>/common/TrainTimetable?trainSearchAction=watch timetable&Train number=<%=bean.getNumber()%>">watch</a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</CENTER>
<div id = "userPanel">
    <div id = "userInfo">
        <label> user name: <%=request.getSession().getAttribute("user")%></label>
    </div>
    <a href="<%=request.getContextPath()%>/logout">logout</a>
</div>
</body>
</html>