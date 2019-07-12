
<!--A Design by W3layouts 
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="com.DAO.DataAceesLayer"%>
<%@page import="com.model.UserCommunityInfomation"%>
<html>
<head>
<title>CQA Help</title>
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

<%
		String uid1 = request.getParameter("uid");
		int uid=(int)session.getAttribute("uid");
		//usrDetails user=DataAceesLayer.getDetails(Integer.parseInt(cid));	
		UserCommunityInfomation communityInfomation = DataAceesLayer
				.getUserCommunityInfomation(uid);
		String communityDesc = DataAceesLayer.getCommunityDesc(communityInfomation.getCid());
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
								<h1><a href="Home.jsp">CQA <span></span></a></h1>
							</div>
						</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<nav class="link-effect-2" id="link-effect-2">
								<ul class="nav navbar-nav">
						<li><a
							href="myProfile.jsp?uid=<%=request.getParameter("uid")%>"><span
								data-hover="My Profile">My Profile</span></a></li>
						<li><a
							href="friendList.jsp"><span
								data-hover="Friend List">Friend List</span></a></li>
						<li><a href="searchQuestion.jsp"><span
								data-hover="Search question">Search question</span></a></li>
						<li><a href="PostQuestion.jsp?uid=<%=uid %>"><span data-hover="Search question">Post question</span></a></li>
						<li><a href="RecenltyQuestion.jsp"><span data-hover="New Question">New Posted question</span></a></li>
						<li><a href="BestAnswerer.jsp"><span data-hover="Best Answerer">Best Answerer</span></a></li>
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
				<h3><a href="Home.jsp">Home</a> / <span>Help</span></h3>
			</div>
		</div>
		<!--content-->
		<div class="content">
			
			<!--statistics--->
						<div class="statistics-w3">
							<div class="container">
								<h3 class="tittle">Some Primary Points</h3>
								<div class="statistics-grids">
									<div class="col-md-3 statistics-grid">
										<div class="statistic">
											<h4></h4>
											<h5>Registration</h5>
											<p>Register with details</p>
										</div>
									</div>
									<div class="col-md-3 statistics-grid">
										<div class="statistic">
											<h4></h4>
											<h5>My Profile</h5>
											<p>User can upload his profile picture</p>
										</div>
									</div>
									<div class="col-md-3 statistics-grid">
										<div class="statistic">
											<h4></h4>
											<h5>Post Question</h5>
											<p>User can post new question on CQA system</p>
										</div>
									</div>
									<div class="col-md-3 statistics-grid">
										<div class="statistic">
											<h4></h4>
											<h5>Post Answer</h5>
											<p>User can post answer of new posted Question</p>
										</div>
									</div>
									<div class="col-md-3 statistics-grid">
										<div class="statistic">
											<h4></h4>
											<h5>Community Members</h5>
											<p>User can see his friend available in his community </p>
										</div>
									</div>
									<div class="col-md-3 statistics-grid">
										<div class="statistic">
											<h4> </h4>
											<h5>Notification</h5>
											<p>User can write message on his friends wall</p>
										</div>
									</div>
									<div class="col-md-3 statistics-grid">
										<div class="statistic">
											<h4></h4>
											<h5>Forum</h5>
											<p>Enter name for login and start discussion</p>
										</div>
									</div>
									<div class="col-md-3 statistics-grid" style="margin-top: -12%!important;">
										<div class="statistic" style="margin-top: 131px!important;">
											<h4></h4>
											<h5>Search</h5>
											<p>Search question in CQA system </p>
										</div>
									</div> 
									
									
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
					<!--statistics--->
					
					<!--What we do-->
				<div class="what-w3">
					<div class="container">
						<h3 class="tittle">Help</h3>
						<div class="what-grids">
							<div class="col-md-6 what-grid">
								<div class="mask">
									<img src="images/9.jpg" class="img-responsive zoom-img" />
								</div>
								<div class="what-bottom">
									<h4>Community Structure</h4>
									<p>Here we are follows the community structure like Main and sub category (interest and his main subject so first before registration user can select category and sub category)</p>
								</div>
							</div>
							<div class="col-md-6 what-grid">
								<div class="what-bottom1">
									<h4>Fuzzy Spell</h4>
									<p>In computing, a spell checker (or spell check) is an application program that flags words in a document that may not be spelled correctly. Spell checkers may be stand-alone, capable of operating on a block of text, or as part of a larger application, such as a word processor, email client, electronic dictionary, or search engine. </p>
								</div>
								<div class="mask">
									<img src="images/8.jpg" class="img-responsive zoom-img" />
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			<!--What we do-->
		</div>	
		<!--content-->
		<!--footer-->
			<div class="footer-w3">
				<div class="container">
					<div class="footer-grids">
						<div class="col-md-4 footer-grid">
							<h4>About Us</h4>
							<p>In today’s life there are many QA based application in community system which are very helpful to the users. Currently users are mostly depended on the internet
							 for  searching the question online in which  CQA web based application  provide the related answers to them by using the more and more  corresponding  keywords and the concept which are matching. 
							  </p>
						</div>
						<!-- <div class="col-md-4 footer-grid">
						<h4>Instagram Posts</h4>
							<div class="footer-grid1">
								<img src="images/w1.jpg" alt=" " class="img-responsive">
							</div>
							<div class="footer-grid1">
								<img src="images/w2.jpg" alt=" " class="img-responsive">
							</div>
							<div class="footer-grid1">
								<img src="images/w4.jpg" alt=" " class="img-responsive">
							</div>
							<div class="footer-grid1">
								<img src="images/w5.jpg" alt=" " class="img-responsive">
							</div>
							<div class="footer-grid1">
								<img src="images/w6.jpg" alt=" " class="img-responsive">
							</div>
							<div class="footer-grid1">
								<img src="images/w2.jpg" alt=" " class="img-responsive">
							</div>
							<div class="clearfix"> </div>
						</div> -->
						<div class="col-md-4 footer-grid">
						<h4>Information</h4>
							<ul>
								<li><i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>139 Pune</li>
								<li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>1 599-033-5036</li>
								<li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i><a href="mailto:example@mail.com"> CQA@mail.com</a></li>
								<li><i class="glyphicon glyphicon-time" aria-hidden="true"></i></li>
							</ul>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		<!--footer-->
		<!---copy--->
			<div class="copy-section">
				<div class="container">
					<div class="social-icons">
						<a href="#"><i class="icon1"></i></a>
						<a href="#"><i class="icon2"></i></a>
						<a href="#"><i class="icon3"></i></a>
						<a href="#"><i class="icon4"></i></a>
					</div>
					<div class="copy">
						<p>&copy; CQA <a href="#http://w3layouts.com">System</a></p>
					</div>
				</div>
			</div>
			<!---copy--->


				
</body>
</html>
