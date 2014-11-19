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
            <label>To search a train, please fill the following fields: </label>
            <form:form commandName="trainBean" class="form-horizontal" role="form" method="post" action="SearchTrain" id = "trainSearchForm">
                <div class="form-group">
                    <label for="number" class="col-sm-4 control-label">Train number:</label>
                    <div class="col-sm-7">
                        <form:input path="number" type="text" class="form-control" id="number" placeholder="Train number" value = "${trainBean.number}"/>
                    </div>
                </div>
                <c:if test="${!empty trainBean.seats}">
                    <div class="form-group">
                        <label for="seats" class="col-sm-4 control-label">Seats:</label>
                        <div class="col-sm-7">
                            <form:input path="seats" type="text" class="form-control" id="seats" placeholder="Seats" value = "${trainBean.seats}" disabled = "true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="totalSeats" class="col-sm-4 control-label">Total seats:</label>
                        <div class="col-sm-7">
                            <form:input path="totalSeats" type="text" class="form-control" id="totalSeats" placeholder="Total seats" value = "${trainBean.totalSeats}"  disabled = "true"/>
                        </div>
                    </div>
                </c:if>

                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-10">
                        <button type="submit" name="trainSearchAction" value="search" class="btn btn-success">Search</button>
                        <input type=button class="btn btn-default" onClick="location.href='${contextPath}/administrator/administratorMain'" value='Back'>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-10">
                        <c:if test="${!empty trainBean.seats}">
                            <input type=button class="btn btn-success" onClick="location.href='${contextPath}/administrator/train/SearchTrain?trainSearchAction=watch passengers&Train_number=${trainBean.number}'" value='Watch passengers'>
                            <input type=button class="btn btn-success" onClick="location.href='${contextPath}/administrator/train/SearchTrain?trainSearchAction=watch timetable&Train_number=${trainBean.number}'" value='Watch timetable'>
                        </c:if>
                    </div>
                </div>

                <table id="validationMessages">
                    <tr>
                        <td>${validationBean.validationMessage}</td>
                    </tr>
                    <tr>
                        <td>${trainBean.processingErrorMessage}</td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</div>
<script src="${contextPath}/resources/js/custom/trainSearchValidation.js"></script>
