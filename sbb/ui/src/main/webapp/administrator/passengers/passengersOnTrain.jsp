<%@ page import="ru.tsystems.tsproject.sbb.bean.PassengerBean" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.TrainBean" %>
<% TrainBean bean = (TrainBean)request.getAttribute("trainBean");
    if (bean == null) {
        bean = new TrainBean();
    }
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<a href="<%=request.getContextPath()%>/administrator/">back</a>
<CENTER>
    <table width="150"border="1">
        <tr>Passengers ot train number <%=bean.getNumber()%> </tr>
        <tr>
            <th>Document number</th>
            <th>Full name</th>
            <th>Birth date</th>
        </tr>
        <%
            List list = (List)request.getAttribute("trainPassengers");
            if(list != null)
            {
                for (Object o: list) {
                    PassengerBean passengerBean = (PassengerBean)o;

        %>
        <tr>
            <td><%=passengerBean.getDocNumber()%></td>
            <td><%=passengerBean.getLastName()%> <%=passengerBean.getFirstName()%></td>
            <td><%=passengerBean.getBirthDate()%></td>
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