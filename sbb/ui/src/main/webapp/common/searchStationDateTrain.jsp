<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.StationBean" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.TimetableBean" %>
<% TimetableBean startBean = (TimetableBean)request.getAttribute("startBean");
    if (startBean == null) {
        startBean = new TimetableBean();
    }
    TimetableBean endBean = (TimetableBean)request.getAttribute("endBean");
    if (endBean == null) {
        endBean = new TimetableBean();
    }

%>
<html>
<head>
    <title>Search trains by stations and date</title>
</head>
<body>
<form method="post" action="SearchStationDateTrain">
    <CENTER>
        <TABLE border="0"width="1000px">
            <tr>
                <th></th>
                <th>Station</th>
                <th>Date</th>
                <th></th>
            </tr>
            <TR align="left">
                <TD width="50px">Start:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Station start name" value="<%=startBean.getStationName()%>">
                </TD>
                <TD>
                    <INPUT TYPE="datetime-local" NAME="Start date" value="<%=startBean.getDate()%>">
                </TD>
                <TD width="500px">
                    <font color="red" width="300px"><%=startBean.getValidationMessage()%></font>
                    <font color="red" width="300px"><%=startBean.getProcessingErrorMessage()%></font>
                </TD>
            </TR>
            <TR align="left">
                <TD width="50px">End:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Station end name" value="<%=endBean.getStationName()%>">
                </TD>
                <TD>
                    <INPUT TYPE="datetime-local" NAME="End date" value="<%=endBean.getDate()%>">
                </TD>
                <TD width="500px">
                    <font color="red" width="300px"><%=endBean.getValidationMessage()%></font>
                    <font color="red" width="300px"><%=endBean.getProcessingErrorMessage()%></font>
                </TD>
            </TR>
            <TR>
                <TD colspan="2" align="center">
                    <INPUT TYPE="submit" value="search trains" name="stationDateTrainSearchAction">
                    <INPUT TYPE="submit" value="back" name="stationDateTrainSearchAction">
                </TD>
                <TD>
                    &nbsp;
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