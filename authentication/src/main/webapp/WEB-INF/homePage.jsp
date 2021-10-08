<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>

	<meta charset="UTF-8">
	<title>Welcome</title>
	
</head>

<body>

	<h1>Welcome, <c:out value="${user.firstName} ${user.lastName}" /></h1><br>
	<br>
	<a href="/logout">Logout</a>
	
</body>

</html>