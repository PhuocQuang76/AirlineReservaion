<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

            var flightId = "${flightId}";
            alert("FlightId:" + flightId);
            var passengerArrayList = [];
            var num = 0;



            $("#addPassenger").click(function(){
                var address = new Object();
                address.addressLine1 = $("#addressLine1").val();
                address.addressLine2 = $("#addressLine2").val();
                address.city = $("#city").val();
                address.state = $("#state").val();
                address.country = $("#country").val();
                address.zipcode = $("#zipcode").val();

                num++;
                var passenger = new Object;
                passenger.flightId = flightId;
                passenger.passengerFirstName = $("#passengerFirstName").val();
                passenger.passengerLastName = $("#passengerLastName").val();
                passenger.passengerEmail = $("#passengerEmail").val();
                passenger.passengerPhoneNo = $("#passengerPhoneNo").val();
                passenger.passengerGender = $("#passengerForm input[type='radio']:checked").val();
                passenger.passengerDOB = $("#passengerDOB").val();
                passenger.passengerAddress = address;
                passengerArrayList.push(passenger);
                $("#passengerList").append(

                    "<tr>" +
                        "<td>" + num + "</td>"+
                        "<td>" + passenger.flightId + "</td>"+
                        "<td>" + passenger.passengerFirstName + "</td>"+
                        "<td>" + passenger.passengerLastName + "</td>"+
                        "<td>" + passenger.passengerEmail + "</td>"+
                        "<td>" + passenger.passengerPhoneNo + "</td>"+
                        "<td>" + passenger.passengerGender + "</td>"+
                        "<td>" + passenger.passengerDOB + "</td>"+
                        "<td>" + passenger.passengerAddress.addressLine1 + "</td>"+
                        "<td>" + passenger.passengerAddress.addressLine2 + "</td>"+
                        "<td>" + passenger.passengerAddress.city + "</td>"+
                        "<td>" + passenger.passengerAddress.state + "</td>"+
                        "<td>" + passenger.passengerAddress.country + "</td>"+
                        "<td>" + passenger.passengerAddress.zipcode + "</td>"+
                        "<td>" + "update" + "</td>"+
                        "<td>" + "delete" + "</td>"+

                    "</tr>"
                )

            });

            $("#submitReservation").click(function(){
                let reservation = new Object();
                reservation.passengers = passengerArrayList;
                reservation.flightId = flightId;
                reservation.checkedBags = 0;
                reservation.status ="BOOKED";


                $.ajax({
                    url: "/saveReservation",
                    type: "POST",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(reservation),
                    success: function (result) {
                        console.log("hi");
                        alert("Your flight was successfully booked.");

                    },
                    error: function (err) {

                    }
                }); // ajax call closing
                window.location.href = "success";
            });


            <!-- ***** Close Modal ***** -->
            $(function () {
                $('.closeCheckInModal').on('click', function () {
                    $('#checkInModal').hide();
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

        <h1>Reservation Form</h1>
        <br>
        <h4>Flight Information</h4>
        <div>
            <table border="1" id="tblFlight">
                <tr>
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
                </tr>

                <tr>
                    <td>${bookedFlight.flightNumber}</td>
                    <td>${bookedFlight.flightAirline}</td>
                    <td>${bookedFlight.flightDepartureCity}</td>
                    <td>${bookedFlight.flightArrivalCity}</td>
                    <td>${bookedFlight.flightDepartureDate}</td>
                    <td>${bookedFlight.flightDepartureTime}</td>
                    <td>${bookedFlight.flightArrivalDate}</td>
                    <td>${bookedFlight.flightArrivalTime}</td>
                    <td>${bookedFlight.flightCapacity}</td>
                    <td>${bookedFlight.flightPrice}</td>
                </tr>
            </table>
        </div>
        <br>

        <h4>Passengers Information:</h4>
        <form id="passengerForm">
        <table>
            <tr>
                <td>First Name</td>
                <td><input type="text" id="passengerFirstName" name="passengerFirstName" /></td>
            </tr>

            <tr>
                <td>Last Name</td>
                <td><input type="text" id="passengerLastName"  /></td>
            </tr>

            <tr>
                <td>Email</td>
                <td><input type="text" id="passengerEmail" /></td>
            </tr>
            <tr>
                <td>PhoneNo</td>
                <td><input type="text" id="passengerPhoneNo" /></td>
            </tr>

            <tr>
                <td>Gender</td>
                <td id="gender">
                    <label><input type="radio" name="gender" value = "MALE" />Male</label>
                    <label><input type="radio" name="gender" value="FEMALE" />Female</label>
                </td>
            </tr>

            <tr>
                <td>DOB</td>
                <td><input type="date" id="passengerDOB" /></td>
            </tr>

            <tr>
                <td>Address</td>
                <td></td>
            </tr>

            <tr>
                <td>Address Line1</td>
                <td><input type="text" id="addressLine1"  /></td>

            </tr>
            <tr>
                <td>Address Line2</td>
                <td><input type="text" id="addressLine2" /></td>

            </tr>
            <tr>
                <td>City</td>
                <td><input type="text" id="city"  /></td>

            </tr>
            <tr>
                <td>State</td>
                <td><input type="text" id="state" /></td>

            </tr>
            <tr>
                <td>Country</td>
                <td><input type="text" id="country" /></td>

            </tr>
            <tr>
                <td>ZipCode</td>
                <td><input type="text" id="zipcode"  /></td>

            </tr>

        </table>
    </form>
        <input type="button" id="addPassenger" value="addPassenger">


        <h1>Passenger List</h1>
        <table id="passengerList" border="1">
            <tr>
                <th>No</th>
                <th>Flight ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone No</th>
                <th>Gender</th>
                <td>DOB</td>
                <td>Address1</td>
                <td>Address2</td>
                <td>City</td>
                <td>State</td>
                <td>Country</td>
                <td>Zipcode</td>
                <td>Update</td>
                <td>Delete</td>
            </tr>
        </table>


        <input type="button" id="submitReservation" value="submit">


    </div>


<div class="modal" id="checkInModal">
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
                <div><input  readonly="true" class="form-control" type="text" id="modal_reservationId"/></div>
                <div>Checked Bags: <input  class="form-control" type="text" id="modal_checkedBag"/></div>
                <div>Confirm Status: <input class="form-control" type="text" id="modal_confirmStatus"/></div>

                <button class='btn-confirm-booking btn btn-primary'>Confirm Booking</button>
                <button class='btn btn-primary'>Edit</button>

            </div>
        </div>


      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger closeCheckInModal" data-bs-dismiss="modal">Close</button>

      </div>

    </div>
  </div>
</div>




    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>