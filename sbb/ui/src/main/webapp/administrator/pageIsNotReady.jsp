<%--
  Created by IntelliJ IDEA.
  User: herr
  Date: 05.10.14
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page is not ready</title>
</head>
<body>
<h1> Oops, this page is not ready</h1>
<a href="administratorMain.jsp">go back to administrator actions</a>
<div id = "userPanel">
    <div id = "userInfo">
        <label> user name: <%=request.getSession().getAttribute("user")%></label>
    </div>
    <a href="<%=request.getContextPath()%>/logout">logout</a>
</div>
</body>
</html>