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
	<title>Idea Info</title>

</head>

<body>

	<a href="/ideas">Home</a><br>
	<br>

	<h1>${ideaToShow.content}</h1>
	
	<p>Created By : ${ideaToShow.creator.firstName} ${ideaToShow.creator.lastName}</p>
	<br>
	<h2>Users Who Liked Your Idea :</h2>
	
	<table>
	    <thead>
	        <tr>
	            <th>Name</th>
	        </tr>
	    </thead>
	    
	    <tbody>
	        <c:forEach items="${ideaToShow.likes}" var="user">
	        <tr>
	            <td>${user.firstName} ${user.lastName}</td>
	        </tr>
	        </c:forEach>
	    </tbody>
	</table>
	<br>
	
	<c:choose>
		<c:when test="${loggedInUser == ideaToShow.creator}">
			<a href="/edit/${ideaToShow.id}">Edit</a><br>
			<br>
			<a href="/delete/${ideaToShow.id}">Delete</a>
		</c:when>
		
		<c:otherwise>
		
		</c:otherwise>
	</c:choose>

</body>

</html>