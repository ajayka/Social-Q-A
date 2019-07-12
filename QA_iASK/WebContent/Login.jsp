<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
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
									<li><a href="SignUp.jsp"><span data-hover="Sign Up">Registration</span></a></li>
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
				<h3><a href="index.jsp">Home</a> / <span>Login</span></h3>
			</div>
		</div>
		
		<div class="content" >
		
			<div class="contact-w3l">
				<div class="container">
					
					
					<div class="col-md-8 contact-left cont">
						<div class="contct-info" style="border: 1px solid black;border-radius: 51px;  width: 67%!important;">
							<center><h4 style="color: navy;">Login</h4></center>
							<form action="LoginController" method="post">
								
								<div class="row">
									<div class="col-md-6 row-grid">
									<input type="email" style="margin-left: 50%;" name="Email" class="text-css" placeholder="Email-Id" required>
									</div>
									
									<div class="clearfix"></div>
								</div>
								<div class="row">
									<div class="col-md-6 row-grid">
									<input type="password" style="margin-left: 50%;" class="text-css" name="Password" placeholder="Password" id="password" pattern=".{6,}" required>
									</div>
									
									<div class="clearfix"></div>
								</div>
								<span id="mesage" ></span>
								<input type="submit" style="margin-left: 30%; height: 50px;" name="submit" value="Login" id="submit" class="btn-style">
								<input type="reset" value="Clear" class="btn-style" >
							</form>
						</div>
						<div style="margin-left: 79%; margin-top: -39%; height: 300px;width: 500px;background-image: url(images/login.png); background-repeat: no-repeat;">
						
						</div>
					</div>
				</div>
			</div>
			
		</div>
		
</body>
</html>