<%--
  Created by IntelliJ IDEA.
  User: herr
  Date: 03.10.14
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.TrainBean" %>
<% TrainBean bean = (TrainBean)request.getAttribute("createResult");
%>
<html>
<head>
    <title></title>
</head>
<body>
    <h1> Train has been created successfully</h1>
    <TABLE border="0"width="600px">
        <TR>
            <TD width="50px">id:</TD>
            <TD width="50px"><%=bean.getId()%></TD>
        </TR>
        <TR>
            <TD width="50px">number:</TD>
            <TD width="50px"><%=bean.getNumber()%></TD>
        </TR>
        <TR>
            <TD width="50px">seats:</TD>
            <TD width="50px"><%=bean.getSeats()%></TD>
        </TR>
        <TR>
            <TD width="50px">total seats:</TD>
            <TD width="50px"><%=bean.getTotalSeats()%></TD>
        </TR>
    </TABLE>
</body>
<a href="/ui/administrator/">return to main menu</a>
<div id = "userPanel">
    <div id = "userInfo">
        <label> user name: <%=request.getSession().getAttribute("user")%></label>
    </div>
    <a href="<%=request.getContextPath()%>/logout">logout</a>
</div>
</html>