<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
<script type="text/javascript">
	function check_pass() {
		if (document.getElementById('password').value == document
				.getElementById('confirm_password').value) {
			document.getElementById('submit').disabled = false;
			document.getElementById('mesage').innerHTML="Password Matched";
		} else {
			document.getElementById('submit').disabled = true;
			document.getElementById('mesage').innerHTML="Password do not matched";
		};
	}
	
</script>
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
									<li><a href="OurWork.jsp"><span data-hover="Services">Services</span></a></li>
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
				<h3><a href="index.jsp">Home</a> / <span>Sign Up</span></h3>
			</div>
		</div>
		

		<div class="content">
		
			<div class="contact-w3l">
				<div class="container">
					
					
					<div class="col-md-8 contact-left cont">
						<div class="contct-info" style="border: solid 1px;margin-left: -82px;">
							<center><h4>Registration</h4></center>
							<form action="RegistrationController" method="post">
								<div class="row">
									<div class="col-md-6 row-grid">
									<input type="text" name="FirstName" style="width: 91%;" class="text-css" placeholder="First Name" title="Enter First Name" required>
									</div>
									<div class="col-md-6 row-grid">
									<input class="text-css" style="width: 91%;" class="text-css" type="text" name="LastName" placeholder="Last Name" title="Enter Last Name" required>
									</div>
									<div class="clearfix"></div>
								</div>
								<div class="row">
									<div class="col-md-6 row-grid">
									<input type="email" class="text-css" style="width: 91%;" name="Email" placeholder="Email-Id" required>
									</div>
									<div class="col-md-6 row-grid">
									<input type="text" class="text-css" style="width: 91%;" name="PhoneNumber" placeholder="Phone number" maxlength="10" pattern="[789][0-9]{9}" required>
									</div>
									<div class="clearfix"></div>
								</div>
								
								<div class="row">
									<div class="col-md-6 row-grid">
									<input class="text-css" type="text" style="width: 91%;" name="address" placeholder="Address"  required>
									</div>
									<div class="col-md-6 row-grid">
									<lable>Gender : </lable>
										<select name="Gender" class="text-css" required>
										<option value="">---Select---</option>
										<option value="male">Male</option>
										<option value="female">Female</option>
										</select>
									<!-- <input class="text-css" type="text" style="width: 91%;" name="Gender" placeholder="Gender" required> -->
									</div>
									<div class="clearfix"></div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6 row-grid">
									<input class="text-css" type="password" style="width: 91%;" name="Password" placeholder="Password" id="password" pattern=".{6,}" onkeyup='check_pass();' / required>
									</div>
										<div class="col-md-6 row-grid">
									<input class="text-css" type="password" style="width: 91%;" name="ConfirmPassword" placeholder="ConfirmPassword" id="confirm_password" pattern=".{6,}" onkeyup='check_pass();' / required>
									</div>
									<div class="clearfix"></div>
								</div>
								<span id="mesage" ></span>
								<input type="submit" style="margin-left: 30%;height: 50px;" name="submit" value="Submit" id="submit" class="btn-style" disabled>
								<input type="reset" value="Clear" style="height: 50px;" class="btn-style">
							</form>
						</div>
						<div style="margin-left: 100%; margin-top: -56%; height: 435px;width: 500px;background-image: url(images/reg.png); background-repeat: no-repeat;">
						
						</div>
					</div>
				</div>
			</div>
			
		</div>

</body>
</html>