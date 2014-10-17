<%@ page import="ru.tsystems.tsproject.sbb.bean.TimetableBean" %>
<%  String trainNumber = (String) request.getAttribute("trainNumber");
    TimetableBean bean = (TimetableBean)request.getAttribute("timetableBean");
    if (bean == null) {
        bean = new TimetableBean();
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form method="post" action="AddNewTrainStop">
    <CENTER>
        <TABLE border="0"width="60px">
            <tr align="center">
                <label> Add stop for train number: <%=trainNumber%> </label>
                <INPUT TYPE="text" NAME="Train number" value="<%=trainNumber%>" hidden="hidden">
            </tr>
            <TR align="center">
                <TD width="150px">Station name:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Station name" value="">
                </TD>
            </TR>
            <TR align="center">
                <TD width="150px">Departure date:</TD>
                <TD>
                    <INPUT TYPE="datetime-local" NAME="Departure date" value="">
                </TD>
            </TR>
            <TR>
                <TD colspan="2" align="center">
                    <INPUT TYPE="submit" value="add" name="stopAddAction">
                    <INPUT TYPE="submit" value="back" name="stopAddAction">
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
            <TR>
                <TD>
                    <font color="red" width="300px"><%=bean.getProcessingErrorMessage()%></font>
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