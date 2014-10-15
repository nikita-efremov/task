<%--
  Created by IntelliJ IDEA.
  User: herr
  Date: 03.10.14
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.StationBean" %>
<% StationBean bean = (StationBean)request.getAttribute("createResult");
%>
<html>
<head>
    <title></title>
</head>
<body>
    <h1> Station has been created successfully</h1>
    <TABLE border="0"width="600px">
        <TR>
            <TD width="50px">id:</TD>
            <TD width="50px"><%=bean.getId()%></TD>
        </TR>
        <TR>
            <TD width="50px">name:</TD>
            <TD width="50px"><%=bean.getName()%></TD>
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