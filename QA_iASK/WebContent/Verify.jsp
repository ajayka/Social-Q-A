<%@page import="com.model.UserDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Verify Code</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--css-->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!--css-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Studies Plus Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--js-->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/numscroller-1.0.js"></script>
<!--js-->
<!--webfonts-->
<link href='//fonts.googleapis.com/css?family=Cagliostro' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<!--webfonts-->


</head>
<body>

<%-- <%
usrDetails um=(usrDetails) session.getAttribute("usr");
%> --%>
	

<!--header-->
		<div class="header">
			
			<div class="container">
				<nav class="navbar navbar-default">
					<div class="container-fluid">
				<!---Brand and toggle get grouped for better mobile display--->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>				  
							<div class="navbar-brand">
								<h1><a href="index.jsp">CQA</a></h1>
							</div>
						</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<nav class="link-effect-2" id="link-effect-2">
								<ul class="nav navbar-nav">
									<li><a href="index.jsp"><span data-hover="Home">Home</span></a></li>
									<li><a href="Login.jsp"><span data-hover="Login">Login</span></a></li>
									<li><a href="SignUp.jsp"><span data-hover="Sign Up">Sign Up</span></a></li>
									<li><a href="OurWork.jsp"><span data-hover="Our System">Our System</span></a></li>

								</ul>
							</nav>
						</div>
					</div>
				</nav>
			</div>
		</div>
	<!--header-->
		<div class="banner-w3agile">
			<div class="container">
				<h3><a href="index.jsp">Home</a> / <span>Verify Code</span></h3>
			</div>
		</div>
	<br></br>
	<center><h4>Verification have been sent to your registered email-id.
						kindly check your mail and enter the code in below text box. <br></br>
						Enter Verification code</h4></center>
						<div class="col-md-8 contact-left cont">
						<form action="verification" method="post">
							<input type="password" style="width: 376px !important; margin-left: 439PX;" name="vrcode"><br></br>
							<%-- <input type="hidden" name="FirstName" value="<%= um.getFname()%>">
							<input type="hidden" name="LastName" value="<%= um.getLname()%>">
							<input type="hidden" name="Email" value="<%= um.getEmail()%>">
							<input type="hidden" name="Password" value="<%= um.getPass()%>">							
							<input type="hidden" name="PhoneNumber" value="<%= um.getPass()%>">
							<input type="hidden" name="otp" value="<%= um.getCode()%>"> --%>
							
							
							 <input type="submit" name="submit"
								value="Submit" style="margin-left: 67%;">
							<div class="btn btn-link-2 ">

								<a href="#index.jsp">Resend... </a>
							
							</div>
							</div>
							</form>
						




</body>
</html>