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
<a href="<%=request.getContextPath()%>/administrator/">back</a>
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

<form method="post" action="AddNewTrainStop">
    <CENTER>
        <TABLE border="0"width="60px">
            <tr align="center">
                <TD>
                    <INPUT NAME="Train number" value = "<%=bean.getNumber()%>" hidden="hidden">
                </TD>
            </tr>
            <TR>
                <TD colspan="2" align="center">
                    <INPUT TYPE="submit" value="add new stop" name="trainTimetableAction">
                </TD>
                <TD>
                    &nbsp;
                </TD>
            </TR>
        </TABLE>
        <TABLE border="0"width="300px">
            <TR>
                <TD>
                    <font color="red" width="300px"><%=bean.getValidationMessage()%></font>
                </TD>
            </TR>
        </TABLE>
    </CENTER>
</form>
<div id = "userPanel">
    <div id = "userInfo">
        <label> user name: <%=request.getSession().getAttribute("user")%></label>
    </div>
    <a href="<%=request.getContextPath()%>/logout">logout</a>
</div>
</body>
</html>