<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="panel panel-primary inputBlockV3">
    <div class="panel-heading">
        <h3 class="panel-title">Train search</h3>
    </div>
    <div class="panel-body">
        <div class="col-sm-8">
            <label>To find trains by stations and dates, please fill the following fields: </label>
            <form:form commandName="complexTrainSearchBean" id = "trainSearchForm" class="form-horizontal" role="form" method="post" action="SearchStationDateTrain" >
                <div class="form-group">
                    <label for="stationStartName" class="col-sm-4 control-label">Station start name:</label>
                    <div class="col-sm-7">
                        <form:input path="stationStartName" type="text" class="form-control" id="stationStartName" placeholder="Station start name"  value="${complexTrainSearchBean.stationStartName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="startDate" class="col-sm-4 control-label">Start date after:</label>
                    <div class="col-sm-7">
                        <form:input path="startDate" type="datetime-local" class="form-control" id="startDate" placeholder="Start date" value="<fmt:formatDate value='${complexTrainSearchBean.startDate}' pattern='yyyy-MM-dd'T'HH:mm' />"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="stationEndName" class="col-sm-4 control-label">Station end name:</label>
                    <div class="col-sm-7">
                        <form:input path="stationEndName" type="text" class="form-control" id="stationEndName" placeholder="Station end name" value="${complexTrainSearchBean.stationEndName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="endDate" class="col-sm-4 control-label">End date before:</label>
                    <div class="col-sm-7">
                        <form:input path="endDate" type="datetime-local" class="form-control" id="endDate" placeholder="End date" value="<fmt:formatDate value='${complexTrainSearchBean.endDate}' pattern='yyyy-MM-dd'T'HH:mm' />"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-10">
                        <button type="submit" name="stationDateTrainSearchAction" value="search trains" class="btn btn-success">Search trains</button>
                        <input type=button class="btn btn-default" onClick="location.href='${contextPath}/index'" value='Back'>
                    </div>
                </div>
                <table id="validationMessages">
                    <tr>
                        <td><form:errors path="stationStartName"/></td>
                    </tr>
                    <tr>
                        <td><form:errors path="startDate"/></td>
                    </tr>
                    <tr>
                        <td><form:errors path="stationEndName"/></td>
                    </tr>
                    <tr>
                        <td><form:errors path="endDate"/></td>
                    </tr>
                    <tr>
                        <td>${complexTrainSearchBean.processingErrorMessage}</td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</div>

<script src="${contextPath}/resources/js/custom/complexTrainSearchValidation.js"></script>