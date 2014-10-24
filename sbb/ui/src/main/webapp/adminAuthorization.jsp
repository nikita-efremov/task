<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.tsystems.tsproject.sbb.bean.AdminLoginBean" %>
<%@ page import="ru.tsystems.tsproject.sbb.ValidationBean" %>
<% AdminLoginBean bean = (AdminLoginBean)request.getAttribute("loginResult");
    if (bean == null) {
        bean = new AdminLoginBean();
    }
    ValidationBean validationBean = (ValidationBean)request.getAttribute("validationBean");
    if (validationBean == null) {
        validationBean = new ValidationBean();
    }
%>
<html>
<head>
    <title>Administrator authorization</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/styles/main.css"/>
</head>
<body>
<div id="mainHeader">
    <span id = "title-pic">
        <a href="<%=request.getContextPath()%>/"><img src="<%=request.getContextPath()%>/resources/images/logo_sbb.png" width="75" height="75" alt="Git"></a>
    </span>
    <span id = "title">
        <label>SBB railways</label>
    </span>
</div>

<div id="commonOptions">
    <table id="commonOptionsTable">
        <tbody>
        <tr class="optionButtonsTR">
            <td class="common_link_td">
                <a class="top_home_link" href="<%=request.getContextPath()%>/common/searchStation.jsp">Trains by station</a>
            </td>
            <td class="common_link_td">
                <a class="top_home_link" href="<%=request.getContextPath()%>/common/searchStationDateTrain.jsp">Trains by stations and date</a>
            </td>
            <td class="common_link_td">
                <a class="top_home_link" href="<%=request.getContextPath()%>/passenger/purchase.jsp">Purchase ticket</a>
            </td>
            <td class="common_link_td">
                <a class="top_home_link" href="<%=request.getContextPath()%>/administrator/">I am admin</a>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<div class = inputBlock>
    <label>To continue work as administrator, please fill the following fields:  </label>
    <form method="post" action="authorization">
        <table id = "inputData">
            <TR>
                <TD>Login</TD>
                <TD>
                    <INPUT TYPE="text" NAME="login" value="">
                </TD>
            </TR>
            <TR>
                <TD>Password</TD>
                <TD>
                    <INPUT TYPE="password" NAME="password" value="">
                </TD>
            </TR>
        </table>
        <table id="inputControls">
            <tr>
                <td>
                    <INPUT TYPE="submit" value="Login" name="loginAction">
                    <INPUT TYPE="submit" value="Back" name="loginAction">
                </td>
            </tr>
        </table>
        <table id="validationMessages">
            <tr>
                <td><%=validationBean.getValidationMessage()%></td>
            </tr>
            <tr>
                <td><%=bean.getProcessingErrorMessage()%></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>