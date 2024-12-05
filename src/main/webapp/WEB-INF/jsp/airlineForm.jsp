<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Airline Form</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/icons.css" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <link href="css/custom.css" rel="stylesheet" type="text/css">
    <script>
        function sortTable(columnIndex) {
            var table = $("#tblAirline");
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
        $(document).ready(function () {
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
                                <p>Hello : ${loggedInUser}</p>
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

        <h1>Airline Form</h1>
        <f:form action="saveAirline" method="POST" modelAttribute="airline">
            <table>
                <c:if test="${hasErrors}">
                    <tr>
                        <td>Errors</td>
                        <td><f:errors path = "*" style="color:red;"/></td>
                    </tr>
                </c:if>

                <tr>
                    <td>Airline ID</td>
                    <td><f:input type="text" path="airlineId" name="airlineId"  value="${airline.airlineId}" /></td>
                </tr>
                <tr>
                    <td>Airline Name</td>
                    <td><f:input type="text" path="airlineName" name="airlineName" value="${airline.airlineName}" /></td>
                </tr>

                <tr>
                    <td>Airline Code</td>
                    <td><f:input type="text" path="airlineCode" value="${airline.airlineCode}" /></td>
                </tr>

            </table>
            <input type="submit" value="submit"  class="btn btn-primary"  style="height:.7cm;width:2.5cm;"/>
        </f:form>

        <br>
        <hr>
        <h1>Airline List</h1>
        <table border="1" id="tblAirline">
            <tr>
                <th><a href="javascript:sortTable(0)">Airline Id</a></th>
                <th><a href="javascript:sortTable(1)">Airline Name</a></th>
                <th><a href="javascript:sortTable(2)">Airline Code</a></th>
                <td>Update</td>
                <td>Delete</td>
            </tr>

            <c:forEach items="${airlines}" var="al">
                <tr>
                    <td>${al.getAirlineId()}</td>
                    <td>${al.getAirlineName()}</td>
                    <td>${al.getAirlineCode()}</td>
                    <td><a href="${pageContext.request.contextPath}/updateAirline?airlineId=${al.getAirlineId()}">Update</a></td>
                    <td><a href="${pageContext.request.contextPath}/deleteAirline?airlineId=${al.getAirlineId()}">Delete</a></td>
                </tr>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>

</html>
