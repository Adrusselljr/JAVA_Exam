<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@ page isErrorPage="true" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  

<%-- <c:out value="${2+2}"/> --%>
    
    
<!DOCTYPE html>

<html>

<head>

	<meta charset="ISO-8859-1">
	<title>Please Register</title>

</head>

<body>

	<a href="/registration"><h1>Please click here to register!</h1></a><br>
	<br>
	<h2>Already Have An Account? <a href="/login"><h3>Click Here!</h3></a></h2>

</body>

</html>