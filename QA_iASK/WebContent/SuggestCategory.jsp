<%@page import="com.model.Suggestion"%>
<%@page import="com.model.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.model.UserCommunityInfomation"%>
<%@page import="com.DAO.DataAceesLayer"%>
<%@page import="com.model.UserDetails"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Our System</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--css-->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!--css-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Studies Plus Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />
<script type="application/x-javascript">
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 


</script>
<!--js-->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/numscroller-1.0.js"></script>
<!--js-->
<!--webfonts-->
<link href='//fonts.googleapis.com/css?family=Cagliostro'
	rel='stylesheet' type='text/css'>
<link
	href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css'>
<!--webfonts-->
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setDateHeader("Expires", -1);

	if (session.getAttribute("uid") == null) {
		response.sendRedirect("Login.jsp");
		return;
	}
%>
</head>
<body>
	<%
		String uid1 = request.getParameter("uid");
		int uid = (int) session.getAttribute("uid");
		//usrDetails user=DataAceesLayer.getDetails(Integer.parseInt(cid));	
		UserCommunityInfomation communityInfomation = DataAceesLayer.getUserCommunityInfomation(uid);
		List<Category> catList = DataAceesLayer.getAllCategory();
	%>


	<!--header-->
	<div class="header">

		<div class="container">
			<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<div class="navbar-brand">
						<h1>
							<a href="index.jsp">CQA</a>
						</h1>
					</div>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<nav class="link-effect-2" id="link-effect-2">
					<ul class="nav navbar-nav">
						<li><a href="Home.jsp?uid=<%=uid%>">
						<span data-hover="Home">Home</span></a></li>
						<li><a href="myProfile.jsp?uid=<%=uid%>">
						<span data-hover="My Profile">My Profile</span></a></li>
						<li><a href="Notification.jsp">
						<span data-hover="Notification">Notification(<%=DataAceesLayer.getNotificationCount(uid)%>)
							</span></a></li>
						<li><a href="SuggestCategory.jsp"><span
								data-hover="Suggest Category">Suggest Category</span></a></li>
						<li><a href="Help.jsp"><span data-hover="Help">Help</span></a></li>
						<li><a href="Logout_controller"><span data-hover="Logout">Logout</span></a></li>
						<li><a href="Logout_controller"><span data-hover="Logout">Logout</span></a></li>
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
			<h3>
				<a href="#index.jsp">Home</a> / <span>Search Question</span>
			</h3>
		</div>
	</div>

	<%-- <h1><i><%=communityInfomation.getFname()+" "+communityInfomation.getLname() %></i></h1> --%>
	<br>
	<br>
	<br>
	<div
		style="width: 28%; margin-left: 155px; border-radius: 24px; height: 40%;">
		<center>
			<br>
			<form action="SuggestCategoryController" method="post">
				<input class="text-css" style="height: 30px;" type="text"
					name="category" placeholder="enter main category" required><br>
				<br> <input class="text-css" style="height: 30px;" type="text"
					name="subcategory" placeholder="enter sub category" required><br>
				<br> <input type="hidden" name="uid" value="<%=uid%>">
				<input class="btn-style" type="submit" style="height: 40px;"
					name="submit" value="Suggest To Admin">
			</form>
		</center>
		<br>

		<%
			List<Suggestion> list = DataAceesLayer.getSuggestCategoryMsg(uid);
			for (Suggestion suggestion : list) {
		%>
		<b><%=suggestion.getSugg1() + " (" + suggestion.getSugg() + ")"%></b><br>
		<%
			}
		%>
	</div>
	<div
		style="margin-left: 61%; margin-top: -15%; height: 300px; width: 500px; background-image: url(images/ss.png); background-repeat: no-repeat;">
	</div>
</body>
</html>
