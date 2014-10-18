<%--
  Created by IntelliJ IDEA.
  User: herr
  Date: 03.10.14
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.PassengerBean" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<% PassengerBean bean = (PassengerBean)request.getAttribute("createResult");
    if (bean == null) {
        bean = new PassengerBean();
    }
%>
<html>
<head>
    <title>Register Success</title>
</head>
<body>
<h2> You have been registered successfully</h2>
<a href="<%=request.getContextPath()%>/index.jsp">back to options page</a>
<div id = "passengerData">
    <CENTER>
        <TABLE border="0"width="60px">
            <TR align="center">
                <TD width="150px">First name:</TD>
                <TD> <%=bean.getFirstName()%> </TD>
            </TR>
            <TR align="center">
                <TD width="150px">Last name:</TD>
                <TD> <%=bean.getLastName()%> </TD>
            </TR>
            <TR align="center">
                <TD width="150px">Document number:</TD>
                <TD> <%=bean.getDocNumber()%> </TD>
            </TR>
            <TR align="center">
                <TD width="150px">Birth date:</TD>
                <TD> <%=new SimpleDateFormat("dd-MM-yyyy").format(bean.getBirthDate())%> </TD>
            </TR>
        </TABLE>
    </CENTER>
</div>
<div id = "userPanel">
    <div id = "userInfo">
        <label> user name: <%=request.getSession().getAttribute("user")%></label>
    </div>
    <a href="<%=request.getContextPath()%>/logout">logout</a>
</div>
</body>
</html>