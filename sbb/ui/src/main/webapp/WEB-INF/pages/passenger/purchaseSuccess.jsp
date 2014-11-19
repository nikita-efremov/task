<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="panel panel-primary inputBlockV3">
    <div class="panel-heading">
        <h3 class="panel-title">Ticket purchase success</h3>
    </div>
    <div class="panel-body">
        <div class="col-sm-8">
            <label>You have been purchased ticket successfully. Ticket Data:</label>
            <div class="form-group">
                <table id = "resultDataV2" class="table table-bordered">
                    <tbody>
                        <TR>
                            <TD>Ticket number:</TD>
                            <TD>${ticketBean.ticketNumber}</TD>
                        </TR>
                        <TR>
                            <TD>Train number:</TD>
                            <TD>${ticketBean.trainNumber}</TD>
                        </TR>
                        <TR>
                            <TD>Document number:</TD>
                            <TD>${ticketBean.passengerDocNumber}</TD>
                        </TR>
                    </tbody>
                </table>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <input type=button class="btn btn-primary" onClick="location.href='${contextPath}/index'" value='Exit'>
                </div>
            </div>
            <table id="validationMessages">
            </table>
        </div>
    </div>
</div>
