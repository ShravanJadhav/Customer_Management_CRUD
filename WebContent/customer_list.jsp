<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="com.techpalle.model.Customer" %>
    <%@ page import="java.util.ArrayList" %>
    
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Customer List</title>
		 <%@include file ="navbar.jsp" %>
	</head>
	<body>
		 
		<div class = "container" >
			<h2 align ="center">Customers List</h2>
			<hr>
			
			<form action="insertForm">
				<button class="btn btn-success">Add Customer</button>
			</form>
			
			
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Id</th>
						<th scope="col">Name</th>
						<th scope="col">Email</th>
						<th scope="col">Mobile</th>
						<th scope="col">Actions</th>
					</tr>    
				  </thead>
				  <tbody>
				  	<c:forEach var="i" items ="${al}">
				  	<tr>
				  		<td><c:out value ="${i.id}"></c:out></td>
				  		<td><c:out value ="${i.name}"></c:out></td>
				  		<td><c:out value ="${i.email}"></c:out></td>
				  		<td><c:out value ="${i.mobile}"></c:out></td>
				  		
				  		
				  		<td><a href = "editForm?id=<c:out value ="${i.id}"></c:out>">Edit</a> &nbsp;&nbsp; <a href = "delete?id=<c:out value ="${i.id}"></c:out>">Delete</a></td>
				  	</tr></c:forEach>
				  	
				  
				  </tbody>
			</table>	
		</div>
	</body>
</html>