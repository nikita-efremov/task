<%--
  Created by IntelliJ IDEA.
  User: herr
  Date: 03.10.14
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose administrator action:</title>
</head>
<body>
<form method="get" action="AdminActionResolver">
    Select action
    <select name="adminAction" size="2">
        <option>Add new train</option>
        <option>Add new Station</option>
        <option>Watch all trains</option>
        <option>Watch passengers of chosen train</option>
    </select>
    <INPUT TYPE="submit" value="Send">
</form>
</body>
</html>


