<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<div class="panel panel-primary inputBlockV3">
    <div class="panel-heading">
        <h3 class="panel-title">Login</h3>
    </div>
    <div class="panel-body">
        <div class="col-sm-8">
            <label>To login in system, please fill the following fields: </label>
            <form class="form-horizontal" role="form" method="post" action="${contextPath}/j_spring_security_check" id = "loginForm">
                <div class="form-group">
                    <label for="j_username" class="col-sm-4 control-label">Username:</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="j_username" placeholder="Username" name = "j_username" value = "">
                    </div>
                </div>
                <div class="form-group">
                    <label for="j_password" class="col-sm-4 control-label">Password:</label>
                    <div class="col-sm-7">
                        <input type="password" class="form-control" id="j_password" placeholder="Password" name = "j_password" value = "">
                    </div>
                </div>
                <div class="checkbox" id="custom-checkbox">
                    <label>
                        <input type="checkbox" id = "_spring_security_remember_me" name = "_spring_security_remember_me"> Remember me
                    </label>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-10">
                        <button type="submit" name="loginAction" value="Login" class="btn btn-success">Login</button>
                        <input type=button class="btn btn-default" onClick="location.href='${contextPath}/index'" value='Back'>
                    </div>
                </div>

                <a class="register-link" href="${contextPath}/register">I am not registered</a>

                <table id="validationMessages">
                    <tr>
                        <c:if test="${param.error == 'true'}">
                            <td>Invalid credentials!</td>
                        </c:if>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>

<script src="${contextPath}/resources/js/custom/loginValidation.js"></script>