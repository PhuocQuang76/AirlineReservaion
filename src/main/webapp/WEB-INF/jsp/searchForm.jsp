<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/icons.css" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <link href="css/custom.css" rel="stylesheet" type="text/css">

<script>
    function sortTable(columnIndex) {
        var table = $("#tblSearchFlightList");
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

    function sortTable2(columnIndex) {
        var table = $("#tblFlightList");
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
        let SearchFlights = null;
        let userName = $('#userName').text();
        console.log(userName);
        if(userName === null || userName==="" ){
            $("#logoutLI").hide();
            $("#loginLI").show();
        } else {
            $("#logoutLI").show();
            $("#loginLI").hide();
        }



        $("#clearSearch").click(function(){
            $(".searchBlock").hide();
            $("#flightDepartureCity").val("");
        });
        $("#searchFlights").click(function(){
            $(".searchBlock").show();

        });

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

        <h1>SEARCH FLIGHT</h1>
        <div id ="loader" >
        <f:form action="saveSearch"  modelAttribute="search">
            <table>
                <tr>
                    <td>Flight Departure City</td>
                    <td><f:input type="text" id="flightDepartureCity"  path="flightDepartureCity" /></td>
                </tr>
                <tr>
                    <td>Flight Arrival City</td>
                    <td><f:input type="text" id="flightArrivalCity"  path="flightArrivalCity" /></td>
                </tr>

                <tr>
                    <td>Start Date</td>
                    <td><f:input type="text" id="flightDepartureDateStart"  path="flightDepartureDateStart" /></td>

                </tr>

                <tr>
                    <td>End Date</td>
                    <td><f:input type="text" id="flightDepartureDateEnd"  path="flightDepartureDateEnd" /></td>
                </tr>
            </table>
            <input type="submit" id="searchFlights" value="Search"  class="btn btn-primary"  style="height:.7cm;width:2.5cm;"/>
        </f:form>


        <div class="searchBlock">
            <h2>Search Result</h2>
            <table border="1" id="tblSearchFlightList">
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
                    <th>Book Flight</th>
                </tr>
                <c:forEach items="${SearchFlights}" var="sf">
                    <tr>
                        <td>${sf.getFlightId()}</td>
                        <td>${sf.getFlightNumber()}</td>
                        <td>${sf.getFlightAirline().getAirlineId()}</td>
                        <td>${sf.getFlightDepartureCity()}</td>
                        <td>${sf.getFlightDepartureDate()}</td>
                        <td>${sf.getFlightDepartureTime()}</td>
                        <td>${sf.getFlightArrivalCity()}</td>
                        <td>${sf.getFlightArrivalDate()}</td>
                        <td>${sf.getFlightArrivalTime()}</td>
                        <td>${sf.getFlightCapacity()}</td>
                        <td>${sf.getFlightPrice()}</td>
                        <td>${sf.getFlightSeatsBooked()}</td>

                        <td>

                        <form action="reservationForm" method="GET">
                            <!-- Other form fields here -->
                            <input type="hidden" name="flightId" value="${sf.getFlightId()}" />
                            <input type="submit" value="Book" />
                        </form>

                        </td>

                    </tr>
                </c:forEach>

            </table>
            <br>
            <input type="button" id="clearSearch" value="clearSearch"  class="btn btn-primary"  style="height:.7cm;width:2.5cm;"/>

        </div>
    </div>
</body>
</html>