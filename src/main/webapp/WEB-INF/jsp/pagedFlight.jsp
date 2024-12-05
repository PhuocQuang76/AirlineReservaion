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

                                <sec:authorize access="hasAuthority('Admin')">
                                    <li><a href="${pageContext.request.contextPath}/airlineForm" />Airlines</a></li>
                                    <li><a href="${pageRequest.request.contextPath}/airportForm" />Airport</a></li>
                                    <li><a href="${pageRequest.request.contextPath}/flightForm?pageNo=0&pageSize=5&sortedBy=flightId" />Flight</a></li>
                                    <li><a href="${pageRequest.request.contextPath}/passengerForm" />Passenger</a></li>
                                    <li><a href="${pageRequest.request.contextPath}/reservationForm" />Reservation</a></li>
                                    <li><a href="${pageRequest.request.contextPath}/checkIn" />checkIn</a></li>
                                    <li><a href="${pageRequest.request.contextPath}/roleForm" />Role</a></li>
                                </sec:authorize>

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

        <div>
            <h1>AIRLINE RESERVATION</h1>
            <img src="${pageContext.request.contextPath}/img/flight.jpeg" style="height:5cm;width:5cm;" />
        <div>
        <br>
        <div style="border="1"">
            <a class="button" href="${pageRequest.request.contextPath}/searchForm">FIND YOUR FLIGHT</a>

        </div>
        <hr>

        <h1>Flight List</h1>
        <table border="1" id="tblFlight">
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
            <c:forEach items="${flights}" var="f">
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

                </tr>
            </c:forEach>
            <div id="pagination">
                <p>Total Pages: ${totalPages} sortedBy=${sortedBy}</p>
                <c:set var="noOfPages" value="${totalPages}"></c:set>
                <c:set var="sortKey" value="${sortedBy}"></c:set>
                <c:set var="pageSize" value="${pageSize}"></c:set>

                <%
                //http://localhost:9090/pagedFlights?pageNo=0&pageSize=5&sortedBy=flightId
                String string= pageContext.getAttribute("noOfPages").toString();
                int TestTreatmentID = Integer.parseInt(string);
                for(int i=0; i<TestTreatmentID; i++){
                    out.println("<a href=\"pagedFlights?pageNo="+i + ' '
                    + "&pageSize="+request.getAttribute("pageSize")
                    +"&sortedBy="+request.getAttribute("sortedBy")
                    +"\">"
                    +i
                    +
                    "</a>");
                }
                %>
            </div>


    </div>
</body>

</html>

