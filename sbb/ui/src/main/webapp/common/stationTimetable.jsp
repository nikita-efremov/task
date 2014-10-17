<%@ page import="ru.tsystems.tsproject.sbb.bean.TimetableBean" %>
<%@ page import="java.util.Set" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.StationBean" %>
<% StationBean bean = (StationBean)request.getAttribute("stationBean");
    if (bean == null) {
        bean = new StationBean();
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
        <tr>Timetable of station name <%=bean.getName()%> </tr>
        <tr>
            <th>Train number</th>
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
            <td><%=timetableBean.getTrainNumber()%></td>
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