<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Flight Form</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/icons.css" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <link href="css/custom.css" rel="stylesheet" type="text/css">

    <script>
        function sortTable(columnIndex) {
            var table = $("#tblFlight");
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

        <h1>Flight Form</h1>

        <f:form action="saveFlight" method="POST" modelAttribute="flight">
            <table>
                <c:if test="${hasErrors}">
                    <tr>
                        <td>Errors</td>
                        <td><f:errors path = "*" style="color:red;"/></td>
                    </tr>
                </c:if>

                <tr>
                    <td>ID</td>
                    <td><f:input type="text" path="flightId" value="${flight.flightId}" /></td>
                </tr>
                <tr>
                    <td>Number</td>
                    <td><f:input type="text" path="flightNumber" value="${flight.flightNumber}" /></td>
                </tr>


                <tr>
                    <td>Airline</td>
                    <td><f:input type="text" path="flightAirline.airlineId" value="${flight.flightAirline.airlineId}" /></td>

                </tr>


                <tr>
                    <td>Departure City</td>
                    <td><f:input type="text" path="flightDepartureCity" value="${flight.flightDepartureCity}" /></td>
                </tr>

                <tr>
                    <td>Departure Date</td>
                    <td><f:input type="date" path="flightDepartureDate" value="${flight.flightDepartureDate}" /></td>
                </tr>
                <tr>
                    <td>Departure Time</td>
                    <td><f:input type="time" path="flightDepartureTime" value="${flight.flightDepartureTime}" /></td>
                </tr>
                <tr>
                    <td>Arrival City</td>
                    <td><f:input type="text" path="flightArrivalCity" value="${flight.flightArrivalCity}" /></td>
                </tr>
                <tr>
                    <td>Arrival Date</td>
                    <td><f:input type="date" path="flightArrivalDate" value="${flight.flightArrivalDate}" /></td>
                </tr>
                <tr>
                    <td>Arrival Time</td>
                    <td><f:input type="time" path="flightArrivalTime" value="${flight.flightArrivalTime}" /></td>
                </tr>
                <tr>
                    <td>Capacity</td>
                    <td><f:input type="number" path="flightCapacity" value="${flight.flightCapacity}" /></td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><f:input type="number" path="flightPrice" value="${flight.flightPrice}" /></td>
                </tr>
                <tr>
                    <td>Seats Booked</td>
                    <td><f:input type="number" path="flightSeatsBooked" value="${flight.flightSeatsBooked}" /></td>
                </tr>


            </table>
            <input type="submit" value="submit"  class="btn btn-primary"  style="height:.7cm;width:2.5cm;"/>
        </f:form>
    </div>




    <div align="center">
        <br>
        <hr>
        <h1>Flight List</h1>
        <table border="1" id="tblFlight" class="display">
            <thead>
            <tr>
                <th><a href="javascript:sortTable(0)">Id</a></th>
                <th><a href="javascript:sortTable(1)">Number</a></th>
                <th><a href="javascript:sortTable(2)">Airline</a></th>
                <th><a href="javascript:sortTable(3)">DepartureCity</a></th>
                <th><a href="javascript:sortTable(4)">DepartureDate</a></th>
                <th><a href="javascript:sortTable(5)">DepartureTime</a></th>
                <th><a href="javascript:sortTable(6)">ArrivalCity</a></th>
                <th><a href="javascript:sortTable(7)">ArrivalDate</a></th>
                <th><a href="javascript:sortTable(8)">ArrivalTime</a></th>
                <th><a href="javascript:sortTable(9)">Capacity</a></th>
                <th><a href="javascript:sortTable(10)">Price</a></th>
                <th><a href="javascript:sortTable(11)">SeatsBooked</a></th>
                <td>Update</td>
                <td>Delete</td>
            </tr>
            </thead>
            <c:forEach items="${pagedFlights}" var="f">
            <tbody>
                <tr>
                    <td>${f.getFlightId()}</td>
                    <td>${f.getFlightNumber()}</td>
                    <td>${f.getFlightAirline().getAirlineId()}</td>
                    <td>${f.getFlightDepartureCity()}</td>
                    <td>${f.getFlightDepartureDate()}</td>
                    <td>${f.getFlightDepartureTime()}</td>
                    <td>${f.getFlightArrivalCity()}</td>
                    <td>${f.getFlightArrivalDate()}</td>
                    <td>${f.getFlightArrivalTime()}</td>
                    <td>${f.getFlightCapacity()}</td>
                    <td>${f.getFlightPrice()}</td>
                    <td>${f.getFlightSeatsBooked()}</td>
                    <td><a href="${pageContext.request.contextPath}/updateFlight?flightId=${f.getFlightId()}">Update</a></td>
                    <td><a href="${pageContext.request.contextPath}/deleteFlight?flightId=${f.getFlightId()}">Delete</a></td>
                </tr>
            </tbody>
            </c:forEach>

        </table>
        <p>Total Pages: ${totalPages} sortedBy=${sortedBy}</p>
        <c:set var="noOfPages" value="${totalPages}"></c:set>
        <c:set var="sortKey" value="${sortedBy}"></c:set>
        <c:set var="pageSize" value="${pageSize}"></c:set>

<%
//http://localhost:9090/flightForm?pageNo=0&pageSize=5&sortedBy=flightId
String string= pageContext.getAttribute("noOfPages").toString();
int TestTreatmentID = Integer.parseInt(string);
for(int i=0; i<TestTreatmentID; i++){
    out.println("<a href=\"flightForm?pageNo="+i + ' '
    + "&pageSize="+request.getAttribute("pageSize")
    +"&sortedBy="+request.getAttribute("sortedBy")
    +"\">"
    +i
    +
    "</a>");
}
%>

</div>

</body>

</html>

