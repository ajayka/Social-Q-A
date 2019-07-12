<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.awt.image.BufferedImage"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>friend list</title>
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
//String uid1=request.getParameter("uid");
//String cid1=request.getParameter("cid");
int uid=(int)session.getAttribute("uid");
UserCommunityInfomation communityInfomation = DataAceesLayer.getUserCommunityInfomation(uid);

List<UserCommunityInfomation> allCommunityInfomation= DataAceesLayer.getAllUserCommunityInfomation(communityInfomation.getCid());

%>

<!--header-->
		<div class="header">
			<div class="header-top">
			<div class="container">
				<div class="detail">
					<ul>
						<li><i class="glyphicon glyphicon-earphone1"
							aria-hidden="true"></i>Name:  <%=communityInfomation.getFname()+" "+communityInfomation.getLname() %></li>
						<li><i class="glyphicon glyphicon-time1" aria-hidden="true"></i>
							(<%=communityInfomation.getCategoryName() %>)</li>
					</ul>
				</div>
				<div class="indicate">
		<center style="margin-top: -58px;">

			<%
			BufferedImage buff= DataAceesLayer.getImg(uid);
			if(buff!=null){
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( buff, "png", baos );
			baos.flush();
			byte[] imageInByteArray = baos.toByteArray();
			baos.close();
			String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
			%>
	    <br><br>
	    	<a href="myProfile.jsp?uid=<%=request.getParameter("uid")%>"><img src="data:image/png;base64, <%=b64%>" alt="Visruth.jpg not found" style="width: 39px!important;border-radius: 29px; margin-top: 2px; margin-bottom: -45px;"/></a>
			<br>
			<br>
			<%}else
				{%>
				<a href="myProfile.jsp?uid=<%=request.getParameter("uid")%>"><img src="images/p.png" alt=""  WIDTH="40" HEIGHT="55" BORDER="0" style="border-radius: 29px;margin-top: 60px;margin-left: -169px;" ></a>
				<%}%>
				<br>
		</center>

	</div>
					<!-- <p>
						<i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>General
						Pvt 66, Dong Da Hanoi, Vietnam.
					</p> -->
				</div>
				<div class="clearfix"></div>
		</div>
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
								<h1><a href="Home.jsp">CQA</a></h1>
							</div>
						</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<nav class="link-effect-2" id="link-effect-2">
								<ul class="nav navbar-nav">
									<li><a href="Home.jsp?uid=<%=uid%>"><span data-hover="Home">Home</span></a></li>
									<li><a href="myProfile.jsp?uid=<%=uid%>"><span data-hover="My Profile">My Profile</span></a></li>
									<li><a href="friendList.jsp"><span
											data-hover="Friend List">Friend List</span></a></li>
									<li><a href="#OurWork.jsp"><span data-hover="Search question">Search question</span></a></li>
									<li><a href="SuggestCategory.jsp"><span data-hover="Suggest Category">Suggest Category</span></a></li>
									<li><a href="Help.jsp"><span data-hover="Help">Help</span></a></li>
									<li><a href="Logout_controller"><span	data-hover="Logout">Logout</span></a></li>
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
				<h3><a href="Home.jsp">Home</a> / <span>Post Answer</span></h3>
			</div>
		</div>
		
		<br>
		<br>
		<center>
		<b style="font-size: 30px;color: navy;">Post Your Answer: </b><br><br>
		<form action="PostAnswerController" method="post">
			<textarea class="text-css" rows="2" cols="50" name="answer" placeholder="Enter Your Answer" title="Enter Question here"></textarea>
			<input type="hidden" name="uid"	value="<%=communityInfomation.getUid()%>"> 
			<input type="hidden" name="qid" value="<%=request.getParameter("qid")%>"><br><br>
			<input type="submit" class="btn-style" style="height: 40px;" name="submit" value="Submit">
			<input class="btn-style" type="reset" style="height: 40px;" value="Clear" >
		</form>
		
		</center>
		


</body>
</html>






