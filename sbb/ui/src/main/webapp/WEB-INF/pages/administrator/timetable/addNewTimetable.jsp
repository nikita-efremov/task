<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="panel panel-primary inputBlockV3">
    <div class="panel-heading">
        <h3 class="panel-title">Timetable creation</h3>
    </div>
    <div class="panel-body">
        <div class="col-sm-8">
            <label>To create a new stop for train number ${trainBean.number}, please fill the following fields: </label>
            <form:form commandName="timetableBean" class="form-horizontal" role="form" method="post" action="AddNewTrainStop" id = "createStopForm">
                <div class="form-group" hidden="hidden">
                    <label for="trainNumber" class="col-sm-4 control-label">Train number:</label>
                    <div class="col-sm-7">
                        <form:input path="trainNumber" type="text" class="form-control" id="trainNumber" placeholder="Train number" value = "${timetableBean.trainNumber}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="stationName" class="col-sm-4 control-label">Station name:</label>
                    <div class="col-sm-7">
                        <form:input path="stationName" type="text" class="form-control" id="stationName" placeholder="Station name" value = "${timetableBean.stationName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="date" class="col-sm-4 control-label">Departure date:</label>
                    <div class="col-sm-7">
                        <form:input path="date" type="datetime-local" class="form-control" id="date" placeholder="Departure date" value = "<fmt:formatDate value='${timetableBean.date}' pattern='yyyy-MM-dd'T'HH:mm' />"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-10">
                        <button type="submit" name="stopAddAction" value="add" class="btn btn-success">Add</button>
                        <input type=button class="btn btn-default" onClick="location.href='${contextPath}/administrator/administratorMain'" value='Back'>
                    </div>
                </div>

                <table id="validationMessages">
                    <tr>
                        <td><form:errors path="stationName"/></td>
                    </tr>
                    <tr>
                        <td><form:errors path="date"/></td>
                    </tr>
                    <tr>
                        <td>${timetableBean.processingErrorMessage}</td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</div>
<script src="${contextPath}/resources/js/custom/trainStopValidation.js"></script>