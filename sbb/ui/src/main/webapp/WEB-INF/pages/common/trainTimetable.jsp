<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="panel panel-primary inputBlockV3">
    <div class="panel-heading">
        <h3 class="panel-title">Timetable of train number ${trainBean.number}</h3>
    </div>
    <div class="panel-body">
        <div class="col-sm-8">
            <div class="form-group">
                <table id = "resultDataV2" class = "table table-striped table-bordered table-hover">
                    <tbody>
                        <tr>
                            <th>Station name</th>
                            <th>Departure date</th>
                        </tr>
                        <c:forEach var="timetable" items="${trainBean.timetables}">
                        <tr>
                            <td>${timetable.stationName}</td>
                            <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value = "${timetable.date}"/></td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <input type=button class = "btn btn-success" onClick="location.href='${contextPath}/administrator/train/addNewTrainStop?Train_number=${trainBean.number}'" value='Add new stop'>
                    </security:authorize>
                    <input type=button class="btn btn-primary" onClick="history.go(-1);" value='Back'>
                </div>
            </div>

            <table id="validationMessages">
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <td>${trainBean.processingErrorMessage}</td>
                </tr>
            </table>
        </div>
    </div>
</div>