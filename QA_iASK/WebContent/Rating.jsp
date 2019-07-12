<%@page import="com.model.Answers"%>
<%@page import="com.model.Question"%>
<%@page import="com.model.QuestionAndAnswerDetails"%>
<%@page import="com.model.UserDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.DAO.DataAceesLayer"%>
<%@page import="com.model.UserCommunityInfomation"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>friend list</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--css-->
<link href="css/star.css" rel="stylesheet" type="text/css" media="all" />
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

      if(session.getAttribute("uid")==null)
      {
    	  response.sendRedirect("Login.jsp");
    	  return;
      }
%>
</head>
<body>
<% 

String message = (String)request.getAttribute("alertMsg");
if(message!=null){
%>
<script type="text/javascript">
    var msg = "<%=message%>";
    alert(msg);
</script>
<%} %>

	<%
		//String uid1=request.getParameter("uid");
		//String cid1=request.getParameter("cid");
		int uid = (int) session.getAttribute("uid");
		UserCommunityInfomation communityInfomation = DataAceesLayer.getUserCommunityInfomation(uid);

		List<UserCommunityInfomation> allCommunityInfomation = DataAceesLayer
				.getAllUserCommunityInfomation(communityInfomation.getCid());
	%>

	<!--header-->
	<div class="header">

		<div class="container">
			<nav class="navbar navbar-default">
			<div class="container-fluid">
				<!---Brand and toggle get grouped for better mobile display--->
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
							<a href="Home.jsp">CQA</a>
						</h1>
					</div>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<nav class="link-effect-2" id="link-effect-2">
					<ul class="nav navbar-nav">
						<li><a href="Home.jsp?uid=<%=uid%>"><span data-hover="Home">Home</span></a></li>
						<li><a href="myProfile.jsp?uid=<%=uid%>"><span data-hover="My Profile">My Profile</span></a></li>
						<li><a href="friendList.jsp"><span data-hover="Friend List">Friend List</span></a></li>
						<li><a href="#OurWork.jsp"><span data-hover="Search question">Search question</span></a></li>
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
				<a href="Home.jsp">Home</a> / <span>My Friend</span>
			</h3>
		</div>
	</div>

	<br>
	<br>
	
	<center>
	<b style="font-size: 25px;color: purple;"><%=request.getParameter("answer")%>

	</b>
	<%
	boolean flag= DataAceesLayer.getRating(Integer.parseInt(request.getParameter("uid")), Integer.parseInt(request.getParameter("aid")));
	
	if(flag==false){
	%>
	<br>
	<div class="stars">

  <form action="RatingController" method="post">

    <input class="star star-5" id="star-5" type="radio" name="star" value="5"/>

    <label class="star star-5" for="star-5"></label>

    <input class="star star-4" id="star-4" type="radio" name="star" value="4"/>

    <label class="star star-4" for="star-4"></label>

    <input class="star star-3" id="star-3" type="radio" name="star" value="3"/>

    <label class="star star-3" for="star-3"></label>

    <input class="star star-2" id="star-2" type="radio" name="star" value="2"/>

    <label class="star star-2" for="star-2"></label>

    <input class="star star-1" id="star-1" type="radio" name="star" checked="checked" value="1"/>
    <label class="star star-1" for="star-1"></label>
    <input type="hidden" name="uid" value="<%=request.getParameter("uid") %>">
    <input type="hidden" name="aid" value="<%=request.getParameter("aid") %>">
	<input type="hidden" name="qid" value="<%=request.getParameter("qid") %>">
    <input type="submit" />
  </form>
</div>
<%}else{%>
<br><br>
<b style="font-size: 30px;color: navy;">You have already given feedback!!!!!!!!!!</b>

<%} %>
<br>
<br>
<a href="searchQuestion.jsp"><b style="font-size: 30px;color: navy;">Back</b></a>

</center>
</body>
</html>