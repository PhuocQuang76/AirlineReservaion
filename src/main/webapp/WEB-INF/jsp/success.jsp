<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Form</title>

    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/icons.css" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <link href="css/custom.css" rel="stylesheet" type="text/css">

    <script>
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
        <h1>Your flight is successfully booked.</h1>
        <br>

        <h4>Please check your email and confirm your flight</h4>
        <h4>Thank you for your purchase</h4>


    </div>
</body>
</html>