<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="panel panel-primary inputBlockV3">
    <div class="panel-heading">
        <h3 class="panel-title">Register success</h3>
    </div>
    <div class="panel-body">
        <div class="col-sm-8">
            <label>You have been registered successfully</label>
            <div class="form-group">
                <table id = "resultDataV2" class="table table-bordered">
                    <tbody>
                        <TR>
                            <TD>Username:</TD>
                            <TD>${passengerBean.docNumber}</TD>
                        </TR>
                        <TR>
                            <TD>First name:</TD>
                            <TD>${passengerBean.firstName}</TD>
                        </TR>
                        <TR>
                            <TD>Last name:</TD>
                            <TD>${passengerBean.lastName}</TD>
                        </TR>
                        <TR>
                            <TD>Document number:</TD>
                            <TD>${passengerBean.docNumber}</TD>
                        </TR>
                        <TR>
                            <TD>Birth date:</TD>
                            <td><fmt:formatDate type="date" value = "${passengerBean.birthDate}"/></td>
                        </TR>
                    </tbody>
                </table>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <input type=button class="btn btn-primary" onClick="location.href='${contextPath}/index'" value='Exit'>
                </div>
            </div>
            <table id="validationMessages">
            </table>
        </div>
    </div>
</div>
