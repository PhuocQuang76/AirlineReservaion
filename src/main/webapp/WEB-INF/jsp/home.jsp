<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>AirLine Reservation</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

    <div align="center">
        <table>
            <tr>
               <td><a href="${pageRequest.request.contextPath}/home" />Home</td><td> | </td>
               <td><a href="${pageContext.request.contextPath}/airlineForm" />Airlines</a></td><td> | </td>
               <td><a href="${pageRequest.request.contextPath}/airportForm" />Airport</td><td> | </td>
               <td><a href="${pageRequest.request.contextPath}/flightForm?pageNo=0&pageSize=5&sortedBy=flightId" />Flight</td><td> | </td>
               <td><a href="${pageRequest.request.contextPath}/passengerForm" />Passenger</td><td> | </td>

               <td><a href="${pageRequest.request.contextPath}/roleForm" />Role Form</td><td> | </td>
               <td><a href="${pageRequest.request.contextPath}/userForm" />User</td><td> | </td>
               <td><a href="${pageRequest.request.contextPath}/searchForm" />Search</td><td> | </td>
               <td><a href="${pageRequest.request.contextPath}/checkIn" />CheckIn</td><td> | </td>
               <td><a href="${pageRequest.request.contextPath}/pagedFlights?pageNo=0&pageSize=5&sortedBy=flightId" />pagedFlights</td><td> | </td>

                <sec:authorize access="isAuthenticated()">
                    <td><a href="logout">Logout</a></td>
                </sec:authorize>
            </tr>
        </table>
        <div>
            <h1>AIRLINE RESERVATION</h1>
            <img src="${pageContext.request.contextPath}/img/flight.jpeg" style="height:5cm;width:5cm;" />
        <div>
        <br>
        <div>
            <button><a href="${pageRequest.request.contextPath}/searchForm" id="homeSearchButton">FIND YOUR FLIGHT</a></button>
        </div>
        <hr>

        <div align="center">
            <table>
                <tr>
                    <td><a href="${pageRequest.request.contextPath}/home" />Home</td><td> | </td>
                    <td><a href="${pageContext.request.contextPath}/airlineForm" />Airlines</a></td><td> | </td>
                    <td><a href="${pageRequest.request.contextPath}/airportForm" />Airport</td><td> | </td>
                    <td><a href="${pageRequest.request.contextPath}/flightForm" />Flight</td><td> | </td>
                    <td><a href="${pageRequest.request.contextPath}/passengerForm" />Passenger</td><td> | </td>

                    <td><a href="${pageRequest.request.contextPath}/roleForm" />Role Form</td><td> | </td>
                    <td><a href="${pageRequest.request.contextPath}/userForm" />User</td><td> | </td>
                    <td><a href="${pageRequest.request.contextPath}/searchForm" />Search</td><td> | </td>
                    <td><a href="${pageRequest.request.contextPath}/checkIn" />CheckIn</td><td> | </td>
                </tr>
            </table>

            <h1>Flight Form</h1>
            <sec:authorize access="isAuthenticated()">
                <p>User name: ${loggedInUser}</p>
                <c:forEach items="${roles}" var="r">
                <p>Roles: ${r.getRoleName()}</p>
                </c:forEach>
            </sec:authorize>

            <h1>Flight List</h1>
            <table border="1" id="tblFlight">
                <tr>
                    <th>Id</th>
                    <th>Number</th>
                    <th>Airline</th>
                    <th>DepartureCity</th>
                    <th>DepartureDate</th>
                    <th>DepartureTime</th>
                    <th>ArrivalCity</th>
                    <th>ArrivalDate</th>
                    <th>ArrivalTime</th>
                    <th>Capacity</th>
                    <th>Price</th>
                    <th>SeatsBooked</th>
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
            </table>
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