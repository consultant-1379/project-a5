<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
	<meta name="viewport" content="width=device-width, initial-scale=1"></meta>
	
	<title>Home Page</title>

    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="styles/index.css">
    
    <script src="jquery/jquery-1.12.4.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>	
</head>
<body>
    <div class="homeDiv">
        <h2>Welcome!</h2>
        <p>Enter a time range for the commit data you want!</p>
        <form action="postTimeRange">
            <input type="date" name="from" placeholder="From">
            <input type="date" name="to" placeholder="To">
            <br><br>
            <button>Get Commits!</button>
        </form>
    </div>
</body>
</html>