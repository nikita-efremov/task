<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="panel panel-danger inputBlockV3">
    <div class="panel-heading">
        <h3 class="panel-title">Server error</h3>
    </div>
    <div class="panel-body">
        <div class="col-sm-8">
            <label>Sorry, error occurred while request processing. Please, contact administrator</label>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <input type=button class="btn btn-primary" onClick="history.go(-1);" value='Back'>
                </div>
            </div>
        </div>
    </div>
</div>
