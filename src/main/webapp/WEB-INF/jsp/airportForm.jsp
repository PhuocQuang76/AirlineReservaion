<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Airport Form</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/icons.css" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <link href="css/custom.css" rel="stylesheet" type="text/css">
    <script>
        function sortTable(columnIndex) {
            var table = $("#tblAirport");
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

        <h1>Airport Form</h1>

        <f:form action="saveAirport" method="POST" modelAttribute="airport">
            <table>
                <c:if test="${hasErrors}">
                    <tr>
                        <td>Errors</td>
                        <td><f:errors path = "*" style="color:red;"/></td>
                    </tr>
                </c:if>

                <tr>
                    <td>ID</td>
                    <td><f:input type="text" path="airportId" name="airportId"  value="${airport.airportId}" /></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><f:input type="text" path="airportName" name="airportName" value="${airport.airportName}" /></td>
                </tr>

                <tr>
                    <td>Code</td>
                    <td><f:input type="text" path="airportCode" value="${airport.airportCode}" /></td>
                </tr>

                <tr>
                    <td>City</td>
                    <td><f:input type="text" path="airportCity" value="${airport.airportCity}" /></td>
                </tr>

            </table>
            <input type="submit" value="submit"  class="btn btn-primary"  style="height:.7cm;width:2.5cm;"/>
        </f:form>

        <br>
        <hr>
        <h1>Airport List</h1>
        <table border="1" id="tblAirport">
            <tr>
                <th><a href="javascript:sortTable(0)">Id</a></th>
                <th><a href="javascript:sortTable(1)">Name</a></th>
                <th><a href="javascript:sortTable(2)">Code</a></th>
                <th><a href="javascript:sortTable(3)">City</a></th>
                <th><a href="javascript:sortTable(4)">ArrivalFlight</a></th>
                <th><a href="javascript:sortTable(5)">DepartureFlight</a></th>
                <td>Update</td>
                <td>Delete</td>
            </tr>

            <c:forEach items="${airports}" var="ap">
                <tr>
                    <td>${ap.getAirportId()}</td>
                    <td>${ap.getAirportName()}</td>
                    <td>${ap.getAirportCode()}</td>
                    <td>${ap.getAirportCity()}</td>
                    <td>
                        <c:forEach items="${ap.getAirportArrivalFlights()}" var="av">
                            ${av.getAirlineId()}
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${ap.getAirportDepartureFlights()}" var="dp">
                            ${dp.getAirlineId()}
                        </c:forEach>
                    </td>
                    <td><a href="${pageContext.request.contextPath}/updateAirport?airportId=${ap.getAirportId()}">Update</a></td>
                    <td><a href="${pageContext.request.contextPath}/deleteAirport?airportId=${ap.getAirportId()}">Delete</a></td>
                </tr>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>