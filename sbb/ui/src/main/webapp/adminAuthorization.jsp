<%--
  Created by IntelliJ IDEA.
  User: herr
  Date: 15.10.14
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.AdminLoginBean" %>
<% AdminLoginBean bean = (AdminLoginBean)request.getAttribute("loginResult");
    if (bean == null) {
        bean = new AdminLoginBean();
    }
%>
<html>
<head>
    <title>Administrator authorization page</title>
</head>
<body>
<h2 align ="center"> Please, enter you credentials</h2>
<form method="post" action="authorization">
    <CENTER>
        <TABLE border="0"width="60px">
            <TR align="center">
                <TD width="150px">Login</TD>
                <TD>
                    <INPUT TYPE="text" NAME="login" value="">
                </TD>
            </TR>
            <TR>
                <TD width="150px">Password</TD>
                <TD>
                    <INPUT TYPE="password" NAME="password" value="">
                </TD>
            </TR>
            <TR>
                <TD colspan="2" align="center">
                    <INPUT TYPE="submit" value="Login" name="loginAction">
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

</body>
</html>