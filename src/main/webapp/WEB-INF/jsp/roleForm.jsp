<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Role Form</title>
    <style type="text/css">
        .error(color:red;)
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/icons.css" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <link href="css/custom.css" rel="stylesheet" type="text/css">
<script>
    function sortTable(columnIndex) {
        var table = $("#tblRole");
        var rows = table.find('tr').get().slice(1); // Exclude the header row
        rows.sort(function(a, b) {
            var A = $(a).find('td').eq(columnIndex).text().toUpperCase();
            var B = $(b).find('td').eq(columnIndex).text().toUpperCase();
            return A.localeCompare(B);
        });
        $.each(rows, function(index, row) {
            table.append(row);
        });
    }

    $(document).ready(function() {
        let userName = $('#userName').text();
        console.log(userName);
        if(userName === null || userName==="" ){
            $("#logoutLI").hide();
            $("#loginLI").show();
        } else {
            $("#logoutLI").show();
            $("#loginLI").hide();
        }
    });
</script>
</head>
<body>
    <header>
        <nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <div class="navbar-brand" >
                        <div class="justify-me-left"><span>Aileen's</span>Travel</div>
                        <div style="position: absolute; right: 0;">
                            <ul>
                                <li><a href="${pageRequest.request.contextPath}/home">Home</a></li>

                                <li id="loginLI"><a href="${pageRequest.request.contextPath}/login">Login</a></li>

                                <li id="logoutLI">
                                    <security:authorize access="isAuthenticated()">
                                        <a class="dropdown-item" href="/login?logout" >Logout</a>
                                    </security:authorize>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div>
                        <li>
                            <sec:authorize access="isAuthenticated()">
                                <p>Hello : ${loggedInUserName}</p>
                                <c:forEach items="${roles}" var="r">
                                <p>Roles: ${r.getRoleName()}</p>
                                </c:forEach>
                            </sec:authorize>
                        </li>
                    </div>
                <div>
            </div>
        </nav>
    </header>




    <div align="center" class="bodyDiv">
        <sec:authorize access="isAuthenticated()">
            <p style="display:none" id="userName">${loggedInUserName}</p>
        </sec:authorize>

        <h1>Role Form</h1>

        <f:form action="saveRole" method="POST" modelAttribute="role">
            <table>
                <tr>
                    <td>Id</td>
                    <td><f:input path="roleId" /></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><f:input path="roleName" /></td>

                </tr>
                <tr>
                    <td></td>
                    <td style="color:red;"><f:errors path="roleName" /></td>
                </tr>
            </table>
            <input type="submit" value="submit"  class="btn btn-primary"  style="height:.7cm;width:2.5cm;"/>
        </f:form>

        <br>
        <hr>
        <h1>Role List</h1>
        <table border ="1" id="tblRole">
            <tr>
                <td><a href="javascript:sortTable(0)">Role Id</a></td>
                <td><a href="javascript:sortTable(1)">Role Name</a></td>
                <td>Update</td>
                <td>Delete</td>
            <tr>
            <c:forEach items="${roles}" var="role" >
                <tr>
                    <td>${role.getRoleId()}</td>
                    <td>${role.getRoleName()}</td>
                    <td><a href="${pageContext.request.contextPath}/updateRole?roleId=${role.getRoleId()}">Update</a></td>
                    <td><a href="${pageContext.request.contextPath}/deleteRole?roleId=${role.getRoleId()}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>