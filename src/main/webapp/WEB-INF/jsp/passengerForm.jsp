<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Passenger Form</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/icons.css" rel="stylesheet" type="text/css">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link href="css/custom.css" rel="stylesheet" type="text/css">

    <script>

    function sortTable(columnIndex) {
        var table = $("#tblPassenger");
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


        <h1>Please add passenser information here:</h1>

        <f:form action="savePassenger" method="POST" modelAttribute="passenger">
            <table>
                <c:if test="${hasErrors}">
                    <tr>
                        <td>Errors</td>
                        <td><f:errors path = "*" style="color:red;"/></td>
                    </tr>
                </c:if>
                <tr>
                     <td>User Id</td>
                     <td><f:input type="hidden" readonly="true" path="userId" name="userId"  value="${userId}" /></td>
                 </tr>

                <tr>
                    <td>Flight Id</td>
                    <td><f:input type="text" readonly="true" path="flightId" name="flightId"  value="${passenger.flightId}" /></td>
                </tr>

                <tr>
                    <td></td>
                    <td><f:input type="hidden" path="passengerId" name="passengerId"  value="${passenger.passengerId}" /></td>
                </tr>

                <tr>
                    <td>First Name</td>
                    <td><f:input type="text" path="passengerFirstName" name="passengerFirstName" value="${passenger.passengerFirstName}" /></td>
                </tr>

                <tr>
                    <td>Last Name</td>
                    <td><f:input type="text" path="passengerLastName" value="${passenger.passengerLastName}" /></td>
                </tr>

                <tr>
                    <td>Email</td>
                    <td><f:input type="text" path="passengerEmail" value="${passenger.passengerEmail}" /></td>
                </tr>
                <tr>
                    <td>PhoneNo</td>
                    <td><f:input type="text" path="passengerPhoneNo" value="${passenger.passengerPhoneNo}" /></td>
                </tr>

                <tr>
                    <td>Gender</td>
                    <td>

                        <c:forEach items="${genders}" var="g">
                             <c:if test="${selectedGender==g.name()}">
                                  <f:radiobutton path="passengerGender" name="passengerGender"  value="${g}" label="${g}" checked="true"/>
                             </c:if>

                             <c:if test="${selectedGender!=g.name()}">
                                  <f:radiobutton path="passengerGender" name="passengerGender"  value="${g}" label="${g}"/>
                             </c:if>
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td>DOB</td>
                    <td><f:input type="date" path="passengerDOB" value="${passenger.passengerDOB}" /></td>
                </tr>

                <tr>
                    <td>Address</td>
                    <td></td>
                </tr>

                <tr>
                    <td>Address Line1</td>
                    <td><f:input type="text" path="passengerAddress.addressLine1" value="${passenger.passengerAddress.addressLine1}" /></td>
                    <td><f:errors path = "passengerAddress.addressLine1" style="color:red;"/></td>
                </tr>
                <tr>
                    <td>Address Line2</td>
                    <td><f:input type="text" path="passengerAddress.addressLine2" value="${passenger.passengerAddress.addressLine2}" /></td>
                    <td><f:errors path = "passengerAddress.addressLine2" style="color:red;"/></td>
                </tr>
                <tr>
                    <td>City</td>
                    <td><f:input type="text" path="passengerAddress.city" value="${passenger.passengerAddress.city}" /></td>
                    <td><f:errors path = "passengerAddress.city" style="color:red;"/></td>
                </tr>
                <tr>
                    <td>State</td>
                    <td><f:input type="text" path="passengerAddress.state" value="${passenger.passengerAddress.state}" /></td>
                    <td><f:errors path = "passengerAddress.state" style="color:red;"/></td>
                </tr>
                <tr>
                    <td>Country</td>
                    <td><f:input type="text" path="passengerAddress.country" value="${passenger.passengerAddress.country}" /></td>
                    <td><f:errors path = "passengerAddress.country" style="color:red;"/></td>
                </tr>
                <tr>
                    <td>ZipCode</td>
                    <td><f:input type="text" path="passengerAddress.zipcode" value="${passenger.passengerAddress.zipcode}" /></td>
                    <td><f:errors path = "passengerAddress.zipcode" style="color:red;"/></td>
                </tr>

            </table>
            <br>
            <br>
            <input type="submit" value="Add Passenger"  class="btn btn-primary"  style="height:.7cm;width:5cm;"/>
        </f:form>

        <br>
        <hr>
        <h1>Passenger List</h1>
        <table border="1" id="tblPassenger">
            <tr>
                <th><a href="javascript:sortTable(0)">PassengerID</a></th>
                <th><a href="javascript:sortTable(1)">UserID</a></th>
                <th><a href="javascript:sortTable(2)">FlightID</a></th>
                <th><a href="javascript:sortTable(3)">FirstName</a></th>
                <th><a href="javascript:sortTable(4)">LastName</a></th>
                <th><a href="javascript:sortTable(5)">Email</a></th>
                <th><a href="javascript:sortTable(6)">PhoneNo</a></th>
                <th><a href="javascript:sortTable(7)">Gender</a></th>
                <td><a href="javascript:sortTable(8)">DOB</a></td>
                <td><a href="javascript:sortTable(9)">Address</a></td>
                <td><a href="javascript:sortTable(10)">Reservation ID</a></td>
                <td>Update</td>
                <td>Delete</td>
            </tr>

            <c:forEach items="${passengers}" var="p">
                <tr>
                    <td>${p.getPassengerId()}</td>
                    <td>${p.getUserId()}</td>
                    <td>${p.getFlightId()}</td>
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
                    <td>${p.getReservation().getReservationNumber()}</td>

                    <td><a href="${pageContext.request.contextPath}/updatePassenger?passengerId=${p.getPassengerId()}">Update</a></td>
                    <td><a href="${pageContext.request.contextPath}/deletePassenger?passengerId=${p.getPassengerId()}">Delete</a></td>
                </tr>
                </tr>
            </c:forEach>
        </table>

        <br>
        <br>
        <input type="button" value="Confirm" id="confirmBooking" class="btn btn-primary"  style="height:.7cm;width:3cm;"/>
    </div>

</body>
</html>