<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="panel panel-primary inputBlockV3">
    <div class="panel-heading">
        <h3 class="panel-title">Ticket purchasing</h3>
    </div>
    <div class="panel-body">
        <div class="col-sm-8">
            <label>To purchase ticket, please fill the following fields: </label>
            <form:form commandName="ticketBean" class="form-horizontal" role="form" method="post" action="TicketPurchase" id = "trainSearchForm">
                <div class="form-group">
                    <label for="trainNumber" class="col-sm-4 control-label">Train number:</label>
                    <div class="col-sm-7">
                        <form:input path="trainNumber" type="text" class="form-control" id="trainNumber" placeholder="Train number" value = "${ticketBean.trainNumber}"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-10">
                        <button type="submit" name="purchaseAction" value="Purchase" class="btn btn-success">Purchase</button>
                        <input type=button class="btn btn-default" onClick="history.go(-1);" value='Back'>
                    </div>
                </div>

                <table id="validationMessages">
                    <tr>
                        <td>${validationBean.validationMessage}</td>
                    </tr>
                    <tr>
                        <td>${ticketBean.processingErrorMessage}</td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</div>

<script src="${contextPath}/resources/js/custom/purchaseValidation.js"></script>