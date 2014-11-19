<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="panel panel-primary inputBlockV3">
    <div class="panel-heading">
        <h3 class="panel-title">Station creation</h3>
    </div>
    <div class="panel-body">
        <div class="col-sm-8">
            <label>Station has been created successfully:</label>
            <div class="form-group">
                <table id = "resultDataV2" class="table table-bordered">
                    <tbody>
                        <TR>
                            <TD>id:</TD>
                            <TD>${stationBean.id}</TD>
                        </TR>
                        <TR>
                            <TD>name:</TD>
                            <TD>${stationBean.name}</TD>
                        </TR>
                    </tbody>
                </table>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <input type=button class="btn btn-primary" onClick="location.href='${contextPath}/administrator/administratorMain'" value='Exit'>
                </div>
            </div>
            <table id="validationMessages">
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <td>${stationBean.processingErrorMessage}</td>
                </tr>
            </table>
        </div>
    </div>
</div>
