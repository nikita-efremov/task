<%--
  Created by IntelliJ IDEA.
  User: herr
  Date: 03.10.14
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.PassengerBean" %>
<% PassengerBean bean = (PassengerBean)request.getAttribute("createResult");
    if (bean == null) {
        bean = new PassengerBean();
    }
%>
<html>
<head>
    <title></title>
</head>
<body>
<a href="<%=request.getContextPath()%>/">back</a>
<form method="post" action="RegisterPassenger">
    <CENTER>
        <TABLE border="0"width="60px">
            <TR align="center">
                <TD width="150px">First name:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="First name" value="<%=bean.getFirstName()%>">
                </TD>
            </TR>
            <TR align="center">
                <TD width="150px">Last name:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Last name" value="<%=bean.getLastName()%>">
                </TD>
            </TR>
            <TR align="center">
                <TD width="150px">Birth date:</TD>
                <TD>
                    <INPUT TYPE="date" NAME="Birth date" value="<%=bean.getBirthDate()%>">
                </TD>
            </TR>
            <TR align="center">
                <TD width="150px">Document number:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Document number" value="<%=bean.getDocNumber()%>">
                </TD>
            </TR>
            <TR>
                <TD colspan="2" align="center">
                    <INPUT TYPE="submit" value="register" name="passengerRegisterAction">
                    <INPUT TYPE="submit" value="back" name="passengerRegisterAction">
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
        <%
            String userName = (String)request.getSession().getAttribute("user");
            if (userName == null) {
                userName = "unauthorized user";
            }
        %>
        <label> user name: <%=userName%></label>
    </div>
    <% if (request.getSession().getAttribute("user") != null) { %>
    <a href="<%=request.getContextPath()%>/logout">logout</a>
    <% } else { %>
    <a href="<%=request.getContextPath()%>/login">login</a>
    <% } %>
</div>
</div>
</body>
</html>