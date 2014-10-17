<%--
  Created by IntelliJ IDEA.
  User: herr
  Date: 05.10.14
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.TrainBean" %>
<% TrainBean bean = (TrainBean)request.getAttribute("createResult");
%>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1> Error </h1>
<h3> Train creation failed:  <%=bean.getProcessingErrorMessage()%> </h3>

<a href="/ui/administrator/train/createNewTrain.jsp">return to station creation page</a>
<div id = "userPanel">
    <div id = "userInfo">
        <label> user name: <%=request.getSession().getAttribute("user")%></label>
    </div>
    <a href="<%=request.getContextPath()%>/logout">logout</a>
</div>
</body>
</html>