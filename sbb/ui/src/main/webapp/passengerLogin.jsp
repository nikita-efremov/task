<%--
  Created by IntelliJ IDEA.
  User: herr
  Date: 15.10.14
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.PassengerBean" %>
<% PassengerBean bean = (PassengerBean)request.getAttribute("loginResult");
    if (bean == null) {
        bean = new PassengerBean();
    }
%>
<html>
<head>
    <title>Passenger login page</title>
</head>
<body>
<h2 align ="center"> Please, login in system</h2>
<a href="<%=request.getContextPath()%>/">back</a>
<form method="post" action="PassengerLogin">
    <CENTER>
        <TABLE border="0"width="60px">
            <TR align="center">
                <TD width="150px">Document number:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Document number" value="">
                </TD>
            </TR>
            <TR>
                <TD colspan="2" align="center">
                    <INPUT TYPE="submit" value="Login" name="loginAction">
                    <INPUT TYPE="submit" value="Back" name="loginAction">
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
                    <font color="red" width="300px"><%=bean.getProcessingErrorMessage()%></font>
                </TD>
            </TR>
        </TABLE>
    </CENTER>
</form>

</body>
</html>