<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login Page</title>
	 <%@include file ="admin_navbar.jsp" %>
</head>
<body>	 
		 <div class = "container" style="width:50%">
			<h2 align ="center">Admin Login Page</h2>
			<hr>
		 
		<form  action ="logAdmin" method ="post">
		  <div class="mb-3">
		    <label for="tbEmail" class="form-label">Email :</label>
		    <input type="email"   name="tbEmail" class="form-control" id="tbEmail" aria-describedby="emailHelp" placeholder="Enter your Email" required = "required">
		    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
		  </div>
		  
		  <div class="mb-3">
		    <label for="tbPass" class="form-label">Password :</label>
		    <input type="password"   name="tbPass" class="form-control" id="tbPass" placeholder="Enter your Password" required = "required">
		  </div>
		   
		  <button type="submit" class="btn btn-primary" value="Login">Login</button>
		</form>
		</div>
</body>
</html>