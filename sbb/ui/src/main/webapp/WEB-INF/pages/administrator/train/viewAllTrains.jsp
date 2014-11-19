<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="panel panel-primary inputBlockV3">
    <div class="panel-heading">
        <h3 class="panel-title">All trains</h3>
    </div>
    <div class="panel-body">
        <div class="col-sm-8">
            <div class="form-group">
                <table id = "resultDataV2" class = "table table-striped table-bordered table-hover">
                    <tbody>
                        <tr>
                            <th>ID</th>
                            <th>Number</th>
                            <th>Seats</th>
                            <th>Total seats</th>
                            <th>Timetable</th>
                            <th>Passengers</th>
                        </tr>
                        <c:forEach var="train" items="${allTrains}">
                            <tr>
                                <td>${train.id}</td>
                                <td>${train.number}</td>
                                <td>${train.seats}</td>
                                <td>${train.totalSeats}</td>
                                <td>
                                    <button type="button" class="btn btn-success btn-xs tableButton" onclick="location.href='${contextPath}/administrator/train/SearchTrain?trainSearchAction=watch timetable&Train_number=${train.number}'">Watch</button>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-success btn-xs tableButton" onclick="location.href='${contextPath}/administrator/train/SearchTrain?trainSearchAction=watch passengers&Train_number=${train.number}'">Watch</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <input type=button class="btn btn-primary" onClick="history.go(-1);" value='Back'>
                </div>
            </div>
            <table id="validationMessages">
            </table>
        </div>
    </div>
</div>
