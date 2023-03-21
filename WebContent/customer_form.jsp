<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Customer Form</title>
		 <%@include file ="navbar.jsp" %>
	</head>
	 
	<body>
	
		<div class = "container"  style="width:50%">
		
			<c:if test="${customer == null}">
			
				<form action ="add" method="post">
				<h2>Add Customer</h2>
			
			</c:if>
			<c:if test="${customer != null}">
			
				<form action="edit" method = "post">
				<h2>Edit Customer</h2>
				 
			</c:if>
			
				<div class="mb-3" hidden>
				  <input type="text" value="<c:out value="${customer.id}"></c:out>" class="form-control" id="exampleFormControlInput" name="tbId">
				</div>
			
			
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">Name</label>
				  <input type="text" value="<c:out value="${customer.name}"></c:out>" class="form-control" id="exampleFormControlInput1" name="tbName" placeholder="Enter your Name" required = "required">
				</div>
				
				<div class="mb-3">
				  <label for="exampleFormControlInput2" class="form-label">Email</label>
				  <input type="email" value="<c:out value="${customer.email}"></c:out>" class="form-control" id="exampleFormControlInput2" name="tbEmail" placeholder="Enter your Email" required = "required">
				</div>
				
				<div class="mb-3">
				  <label for="exampleFormControlInput3" class="form-label">Mobile</label>
				  <input type="tel" value="<c:out value="${customer.mobile}"></c:out>" class="form-control" id="exampleFormControlInput3" name="tbMobile" placeholder="Enter your Number" required = "required">
				</div>
				
				<div>
					<button class="btn btn-success">Save</button>
				</div>
				
			</form>
				
		</div>
			
	</body>
</html>