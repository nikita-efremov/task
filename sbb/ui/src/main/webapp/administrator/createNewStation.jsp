<%--
  Created by IntelliJ IDEA.
  User: herr
  Date: 03.10.14
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form method="post" action="CreateNewStation">
    <CENTER>
        <TABLE border="0"width="600px">
            <TR>
                <TD width="150px">Name:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Station name" value="">
                </TD>
                <TD width="350px">
                    <font color="red"></font>
                </TD>
            </TR>
            <TR>
                <TD colspan="2" align="center">
                    <INPUT TYPE="submit" value="create" name="stationCreateAction">
                    <INPUT TYPE="submit" value="back" name="stationCreateAction">
                </TD>
                <TD>
                    &nbsp;
                </TD>
            </TR>
        </TABLE>
    </CENTER>
</form>

</body>
</html>