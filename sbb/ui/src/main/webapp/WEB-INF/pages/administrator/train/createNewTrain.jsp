<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="panel panel-primary inputBlockV3">
    <div class="panel-heading">
        <h3 class="panel-title">Train creation</h3>
    </div>
    <div class="panel-body">
        <div class="col-sm-8">
            <label>To create a new train, please fill the following fields: </label>
            <form:form commandName="trainBean" class="form-horizontal" role="form" method="post" action="CreateNewTrain" id = "createTrainForm">
                <div class="form-group">
                    <label for="number" class="col-sm-4 control-label">Train number:</label>
                    <div class="col-sm-7">
                        <form:input path="number" type="text" class="form-control" id="number" placeholder="Train number" value = "${trainBean.number}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="totalSeats" class="col-sm-4 control-label">Total seats:</label>
                    <div class="col-sm-7">
                        <form:input path="totalSeats" type="text" class="form-control" id="totalSeats" placeholder="Total seats" value = "${trainBean.seats}"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-10">
                        <button type="submit" name="trainCreateAction" value="create" class="btn btn-success">Create</button>
                        <input type=button class="btn btn-default" onClick="location.href='${contextPath}/administrator/administratorMain'" value='Back'>
                    </div>
                </div>
                <table id="validationMessages">
                    <tr>
                        <td><form:errors path="number"/></td>
                    </tr>
                    <tr>
                        <td><form:errors path="totalSeats"/></td>
                    </tr>
                    <tr>
                        <td>${trainBean.processingErrorMessage}</td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</div>
<script src="${contextPath}/resources/js/custom/trainCreateValidation.js"></script>
