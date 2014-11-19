<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="panel panel-primary inputBlockV3">
    <div class="panel-heading">
        <h3 class="panel-title">Station creation</h3>
    </div>
    <div class="panel-body">
        <div class="col-sm-8">
            <label>To create a new station, please fill the following fields: </label>
            <form:form commandName="stationBean" class="form-horizontal" role="form" method="post" action="CreateNewStation" id = "stationForm">
                <div class="form-group">
                    <label for="name" class="col-sm-4 control-label">Station name:</label>
                    <div class="col-sm-7">
                        <form:input path="name" type="text" class="form-control" id="name" placeholder="Station name" value = "${stationBean.name}"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-10">
                        <button type="submit" name="stationCreateAction" value="create" class="btn btn-success">Create</button>
                        <input type=button class="btn btn-default" onClick="location.href='${contextPath}/administrator/administratorMain'" value='Back'>
                    </div>
                </div>
                <table id="validationMessages">
                    <tr>
                        <td>${validationBean.validationMessage}</td>
                    </tr>
                    <tr>
                        <td>${stationBean.processingErrorMessage}</td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</div>
<script src="${contextPath}/resources/js/custom/stationValidation.js"></script>