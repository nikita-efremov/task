<%--
  Created by IntelliJ IDEA.
  User: herr
  Date: 05.10.14
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.StationBean" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>All stations</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/administrator/">back</a>
<CENTER>
    <table width="150"border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
        </tr>
        <%
            List list = (List)request.getAttribute("allStations");
            if(list!=null)
            {
                for(int i=0 ; i< list.size();i++)
                {
                    StationBean bean =(StationBean) list.get(i);
        %>
        <tr>
            <td><%=bean.getId()%></td>
            <td><%=bean.getName()%></td>
        </tr>
        <%
                }
            }
        %>
    </table>
</CENTER>
<div id = "userPanel">
    <div id = "userInfo">
        <label> user name: <%=request.getSession().getAttribute("user")%></label>
    </div>
    <a href="<%=request.getContextPath()%>/logout">logout</a>
</div>
</body>
</html>