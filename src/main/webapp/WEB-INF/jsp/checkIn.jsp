<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<!DOCTYPE html>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="css/checkin.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/icons.css" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <link href="css/custom.css" rel="stylesheet" type="text/css">
<script>

    function sortTable(columnIndex) {
        var table = $("#reservationList");
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

        $("#reservationList").on("click", ".tbl_detail",function(){

             let reservationId =  $(this).parent().parent().children("td").eq(0).text();
             let flightId = $(this).parent().parent().children("td").eq(1).text();
            $("#bar2").toggleClass("hidden");

            $.ajax({
                type:'GET',
                dataType:'json',
                url: "/findPassengers/" + reservationId ,
                success: function(data) {
                },
                error: function(e) {
                   console.log(e.message);
                }
            }); <!-- Close Ajax -->

        });

        $("#reservationList").on("click", "#confirmButton",function(){

            let reservationId =  $(this).parent().parent().children("td").eq(0).text();

            $("#modal_reservationNo").val(reservationId);
            $("#modalCheckin").toggle();
        });

        $(".btn-modal-checkIn").click(function(){
            let reservationDTO = new Object();
            reservationDTO.reservationNumber = $("#modal_reservationNo").val();

            reservationDTO.checkedBags = $("#modal_checkBagNo").val();

            reservationDTO.status = "CHECKEDIN";


            $.ajax({
                 url: "/confirmCheckin",
                 type: "POST",
                 dataType: "json",
                 contentType: "application/json; charset=utf-8",
                 data: JSON.stringify(reservationDTO),
                 success: function (result) {
                     window.location.reload();
                 },
                 error: function(xhr, status, error) {
                     console.log('Failed to upload file');
                 }
             });
            window.location.reload();

            $('#modalCheckin').hide();

        });

        <!-- ****** Close momdal ******* -->
        $(function () {
            $('.closeCheckInModal').on('click', function () {
                $('#modalCheckin').hide();
            })
        })


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
                            <li>
                                <sec:authorize access="isAuthenticated()">
                                    <p>Hello : ${loggedInUserName}</p>
                                    <c:forEach items="${roles}" var="r">
                                    <p>Roles: ${r.getRoleName()}</p>
                                    </c:forEach>
                                </sec:authorize>
                            </li>
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

        <h1>CONFIRM AND CHECK IN</h1>

    </div>


    <div id="foo">
      <div style="border:thin" id="bar1">
        <table border="1" id="reservationList">
                <tr>
                    <th><a href="javascript:sortTable(0)">ReserveNo</a></th>
                    <th><a href="javascript:sortTable(1)">FlightID</a></th>
                    <th><a href="javascript:sortTable(2)">CheckBags</a></th>
                    <th><a href="javascript:sortTable(3)">Status</a></th>
                    <th>Detail</th>
                    <th>Check In</th>
                </tr>


                <c:forEach items="${reservations}" var="r">
                <tr>
                    <td>${r.reservationNumber}</td>
                    <td>${r.flightId}</td>
                    <td>${r.checkedBags}</td>
                    <td>${r.status}</td>

                    <td>
                        <form id="findPassengers" action="findPassengers" method="GET">
                            <!-- Other form fields here -->
                            <input type="hidden" name="reservationId" value="${r.reservationNumber}" />
                            <input type="hidden" name="flightId" value="${r.flightId}" />

                            <input type="submit" class="tbl_detail btn btn-primary" value="View" />
                        </form>
                    </td>
                    <td><input type="button" class="btn btn-primary"id="confirmButton" value="CheckIn"</td>
                </tr>
                </c:forEach>

            </table>

      </div>


      <div id="bar2" >
        <h3 style="padding-left:300px">Reservation Detail</h3>
         <table>
            <tr>
                <td><h5>Airport</h5></td>
                <td><input type="text" value="${flight.flightDepartureCity} Airport"></input></td>
            </tr>

            <br>
            <tr>
                <td><h5>Airline</h5></td>
                <td><input type="text" value="${flight.flightAirline.airlineName}  Airline"></input></td>
            </tr>

            <br>
            <br>
            <tr>
                <td><h5>Flight</h5></td>
            </tr>
            <tr>
                <table border="1" id="tblFlight">
                    <tr>
                        <th>Number</th>
                        <th>DepartureCity</th>
                        <th>DepartureDate</th>
                        <th>DepartureTime</th>
                        <th>ArrivalCity</th>
                        <th>ArrivalDate</th>
                        <th>ArrivalTime</th>
                        <th>Capacity</th>
                        <th>Price</th>
                    </tr>

                    <tr>
                        <td>${flight.getFlightNumber()}</td>
                        <td>${flight.getFlightDepartureCity()}</td>
                        <td>${flight.getFlightDepartureDate()}</td>
                        <td>${flight.getFlightDepartureTime()}</td>
                        <td>${flight.getFlightArrivalCity()}</td>
                        <td>${flight.getFlightArrivalDate()}</td>
                        <td>${flight.getFlightArrivalTime()}</td>
                        <td>${flight.getFlightCapacity()}</td>
                        <td><span>$</span>${flight.getFlightPrice()}</td>
                    </tr>
                  </table>
              </tr>
              <br>
              <tr>
                 <td><h5>Passenger List</h5</td>
              </tr>
              <br>
              <tr>
                  <table border="1" id="passengerList">
                    <thead>
                      <tr>
                         <th>ID</th>
                         <th>First</th>
                         <th>Last</th>
                         <th>Email</th>
                         <th>PhoneNo</th>
                         <th>Gender</th>
                         <td>DOB</td>
                         <td>Address</td>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${passengerList}" var="p">
                          <tr>
                             <td>${p.passengerId}</td>
                             <td>${p.getPassengerFirstName()}</td>
                             <td>${p.getPassengerLastName()}</td>
                             <td>${p.getPassengerEmail()}</td>
                             <td>${p.getPassengerPhoneNo()}</td>
                             <td>${p.getPassengerGender()}</td>
                             <td>${p.getPassengerDOB()}</td>
                             <td>
                                 ${p.getPassengerAddress().getAddressLine1()}
                                 ${p.getPassengerAddress().getAddressLine2()}
                                 ${p.getPassengerAddress().getCity()}
                                 ${p.getPassengerAddress().getState()}
                                 ${p.getPassengerAddress().getCountry()}
                                 ${p.getPassengerAddress().getZipcode()}
                             </td>
                         </tr>
                      </c:forEach>
                    </tbody>
                  </table>
            </tr>

         </table>

      </div>










    <div class="modal" id="modalCheckin">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">

          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title">CHECK IN </h4>
            <button type="button" class="closeCheckInModal" data-dismiss="modal">&times;</button>
          </div>


            <!-- Modal body -->
            <div class="modal-body" id="bookingRoom_modalBody">
                <div class="col">
                    <div>
                        <input type="text" id="modal_reservationNo"></input>

                        <h5>Check Bags </h5>
                        <input type="text" id="modal_checkBagNo"></input>
                    </div>

                    <button class='btn-modal-checkIn btn btn-primary'>Check-In</button>

                </div>
            </div>


          <!-- Modal footer -->
          <div class="modal-footer">
            <button type="button" class="btn btn-danger closeCheckInModal" data-bs-dismiss="modal">Close</button>

          </div>

        </div>
      </div>
    </div>

</body
</html>