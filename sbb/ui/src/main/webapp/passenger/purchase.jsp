<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.tsystems.tsproject.sbb.bean.TicketBean" %>
<% TicketBean bean = (TicketBean)request.getAttribute("purchaseResult");
    if (bean == null) {
        bean = new TicketBean();
    }
%>
<html>
<head>
    <title>Ticket purchasing</title>
</head>
<body>
<form method="post" action="TicketPurchase">
    <CENTER>
        <TABLE border="0"width="300px">
            <TR align="left">
                <TD width="150px">Train number:</TD>
                <TD>
                    <INPUT TYPE="text" NAME="Train number" value="<%=bean.getTrainNumber()%>">
                </TD>
            </TR>
            <TR>
                <TD colspan="2" align="center">
                    <INPUT TYPE="submit" value="Purchase" name="purchaseAction">
                    <INPUT TYPE="submit" value="Back" name="purchaseAction">
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
            <TR>
                <TD>
                    <font color="red" width="300px"><%=bean.getProcessingErrorMessage()%></font>
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
    <a href="<%=request.getContextPath()%>/passengerLogin.jsp">login</a>
    <% } %>
</div>
</div>
</body>
</html>