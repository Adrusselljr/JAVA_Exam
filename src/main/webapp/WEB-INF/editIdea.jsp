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
	<title>Create Idea</title>

</head>

<body>

	<a href="/ideas">Home</a><br>
	<br>

	<h1>Create A New Idea!</h1>
	
	<form:form action="/idea/update/${idea.id}" method="post" modelAttribute="idea">
	
    <p>
        <form:label path="content">Content : </form:label>
        <form:errors path="content"/>
        <form:input path="content"/>
    </p>
    <br>

    <input type="submit" value="Update"/>
    
</form:form>    

</body>