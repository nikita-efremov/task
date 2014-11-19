<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="panel panel-primary inputBlockV3">
    <div class="panel-heading">
        <h3 class="panel-title">All passengers</h3>
    </div>
    <div class="panel-body">
        <div class="col-sm-8">
            <div class="form-group">
                <table id = "resultDataV2" class = "table table-striped table-bordered table-hover">
                    <tbody>
                        <tr>
                            <th>ID</th>
                            <th>Document number</th>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Birth date</th>
                        </tr>
                        <c:forEach var="passenger" items="${passengers}">
                            <tr>
                                <td>${passenger.id}</td>
                                <td>${passenger.docNumber}</td>
                                <td>${passenger.firstName}</td>
                                <td>${passenger.lastName}</td>
                                <td><fmt:formatDate type="date" value = "${passenger.birthDate}"/></td>
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
