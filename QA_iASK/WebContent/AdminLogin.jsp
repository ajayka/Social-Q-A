<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html >
	<head>
		<meta charset="UTF-8">
		<title>Sign Up Form</title>
  
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

		<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

      <link rel="stylesheet" href="css1/style.css">

  
	</head>

<body>
  
<div class="container">
  <form action="AdminController" method="post">
    <div class="row">
      <h4>Login</h4>
      <div class="input-group input-group-icon">
        <input type="email" name="Email" placeholder="Email Adress"/>
        <div class="input-icon"><i class="fa fa-envelope"></i></div>
      </div>
      <div class="input-group input-group-icon">
        <input type="password" name="Password" placeholder="Password"/>
        <div class="input-icon"><i class="fa fa-key"></i></div>
      </div>
	  <div>
        <input type="submit" name="submit" value="Login"/>
        <div class="input-icon"></div>
      </div>
      <div>
      <center>
       <a href="index.jsp"><h3><i><b>Back</b></i></h3></a>
        <div class="input-icon"></div>
        </center>
      </div>
    </div>
  </form>
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script  src="js1/index.js"></script>

</body>
</html>
