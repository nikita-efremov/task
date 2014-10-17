<%--
  Created by IntelliJ IDEA.
  User: herr
  Date: 03.10.14
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.TrainBean" %>
<% TrainBean bean = (TrainBean)request.getAttribute("createResult");
    if (bean == null) {
        bean = new TrainBean();
    }
%>
<html>
<head>
    <title></title>
</head>
<body>
<form method="post" action="CreateNewTrain">
    <CENTER>
        <TABLE border="0"width="60px">
            <TR align="center">
                <TD width="150px">Train number:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Train number" value="">
                </TD>
            </TR>
            <TR align="center">
                <TD width="150px">Total seats:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Total seats" value="">
                </TD>
            </TR>
            <TR>
                <TD colspan="2" align="center">
                    <INPUT TYPE="submit" value="create" name="trainCreateAction">
                    <INPUT TYPE="submit" value="back" name="trainCreateAction">
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
        <label> user name: <%=request.getSession().getAttribute("user")%></label>
    </div>
    <a href="<%=request.getContextPath()%>/logout">logout</a>
</div>
</body>
</html>