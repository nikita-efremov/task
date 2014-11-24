<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="panel panel-primary inputBlockV3">
    <div class="panel-heading">
        <h3 class="panel-title">Register</h3>
    </div>
    <div class="panel-body">
        <div class="col-sm-8">
            <label>To register in system, please fill the following fields: </label>
            <form:form commandName="passengerBean" class="form-horizontal" role="form" method="post" action="RegisterPassenger" id = "registerForm">
                <div class="form-group">
                    <label for="firstName" class="col-sm-4 control-label">First name:</label>
                    <div class="col-sm-7">
                        <form:input path="firstName" type="text" class="form-control" id="firstName" placeholder="First name" value = "${passengerBean.firstName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="lastName" class="col-sm-4 control-label">Last name:</label>
                    <div class="col-sm-7">
                        <form:input path="lastName" type="text" class="form-control" id="lastName" placeholder="Last name" value = "${passengerBean.lastName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="birthDate" class="col-sm-4 control-label">Birth date:</label>
                    <div class="col-sm-7">
                        <form:input path="birthDate" type="date" class="form-control" id="birthDate" placeholder="Birth date" value = "<fmt:formatDate value='${passengerBean.birthDate}' pattern='yyyy-MM-dd' />"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="docNumber" class="col-sm-4 control-label">Document number:</label>
                    <div class="col-sm-7">
                        <form:input path="docNumber" type="text" class="form-control" id="docNumber" placeholder="Document number" value = "${passengerBean.docNumber}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-4 control-label">Password:</label>
                    <div class="col-sm-7">
                        <form:input path="password" type="password" class="form-control" id="password" placeholder="Password" value = ""/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirmPassword" class="col-sm-4 control-label">Confirm password:</label>
                    <div class="col-sm-7">
                        <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm password" name = "confirmPassword" value = "">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-10">
                        <button type="submit" name="passengerRegisterAction" value="register" class="btn btn-success">Register</button>
                        <input type=button class="btn btn-default" onClick="location.href='${contextPath}/index'" value='Back'>
                    </div>
                </div>
                <table id="validationMessages">
                    <tr>
                        <td><form:errors path="firstName"/></td>
                    </tr>
                    <tr>
                        <td><form:errors path="lastName"/></td>
                    </tr>
                    <tr>
                        <td><form:errors path="birthDate"/></td>
                    </tr>
                    <tr>
                        <td><form:errors path="docNumber"/></td>
                    </tr>
                    <tr>
                        <td><form:errors path="password"/></td>
                    </tr>
                    <tr>
                        <td>${passengerBean.processingErrorMessage}</td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</div>

<script src="${contextPath}/resources/js/custom/registerValidation.js"></script>