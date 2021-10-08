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
	<table>
	    <thead>
	        <tr>
	            <th>Idea</th>
	            <th>Created By</th>
	            <th>Like</th>
	            <th>Action</th>
	        </tr>
	    </thead>
	    
	    <tbody>
	        <c:forEach items="${allIdeas}" var="idea">
	        <tr>
	            <td><a href="/ideas/${idea.id}">${idea.content}</a></td>
	            <td>${idea.creator.firstName} ${idea.creator.lastName}</td>
	           <td>${idea.likes.size()}</td>
	            <td>
		            <c:choose>
						<c:when test="${idea.likes.contains(user)}">
							<a href="/unlike/${idea.id}">Unlike</a>
						</c:when>
			
						<c:otherwise>
							<a href="/like/${idea.id}">Like</a>
						</c:otherwise>
					</c:choose>
				</td>
	        </tr>
	        </c:forEach>
	    </tbody>
	</table>
	<br>
	<a href="/ideas/new">Create An Idea</a><br>
	<br>
	<a href="/logout">Logout</a>
	
</body>

</html>