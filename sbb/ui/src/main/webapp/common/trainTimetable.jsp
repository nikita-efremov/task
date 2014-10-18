<%@ page import="java.util.List" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.TrainBean" %>
<%@ page import="ru.tsystems.tsproject.sbb.entity.Timetable" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.TimetableBean" %>
<%@ page import="java.util.Set" %>
<% TrainBean bean = (TrainBean)request.getAttribute("trainBean");
    if (bean == null) {
        bean = new TrainBean();
    }
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<a href="<%=request.getContextPath()%>/">back</a>
<CENTER>
    <table width="150"border="1">
        <tr>Timetable of train number <%=bean.getNumber()%> </tr>
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
            <td><%=timetableBean.getDate()%></td>
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