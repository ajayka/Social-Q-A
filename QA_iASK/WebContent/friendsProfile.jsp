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
String uid1=request.getParameter("uid");// this parameter passed by friendprofile.jsp URL and Use
										//ful for the displaying the question related to that user
//String cid1=request.getParameter("cid");
int uid=(int)session.getAttribute("uid");
UserCommunityInfomation communityInfomation = DataAceesLayer.getUserCommunityInfomation(uid);

UserCommunityInfomation communityInfomation1 = DataAceesLayer.getUserCommunityInfomation(Integer.parseInt(uid1));
List<UserCommunityInfomation> allCommunityInfomation= DataAceesLayer.getAllUserCommunityInfomation(communityInfomation.getCid());

List<QuestionAndAnswerDetails> QAlist= DataAceesLayer.getQADetails(Integer.parseInt(uid1));


%>

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
								<h1><a href="Home.jsp">CQA</a></h1>
							</div>
						</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<nav class="link-effect-2" id="link-effect-2">
								<ul class="nav navbar-nav">
									<li><a href="Home.jsp?uid=<%=uid %>>"><span data-hover="Home">Home</span></a></li>
									<li><a href="myProfile.jsp?uid=<%=uid%>"><span data-hover="My Profile">My Profile</span></a></li>
									<li><a href="friendList.jsp"><span
											data-hover="Friend List">Friend List</span></a></li>
									<li><a href="#OurWork.jsp"><span data-hover="Search question">Search question</span></a></li>
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
				<h3><a href="#Home.jsp">Home</a> / <span>Question And Answers Summary</span></h3>
			</div>
		</div>
		
		<br>
		<br>
	<center>
		
		<b style="color: navy;font-size: 45px;"> Your friend expert in <i>"<%=communityInfomation1.getSubCategoryName()%>"</i> </b>
		<br><br>
		<form action="NotificationController" method="post">
		<textarea rows="2" cols="50" name="post" placeholder="Post on <%=communityInfomation1.getFname() %> 's wall..." title="What's in your mind"></textarea>
		<input type="hidden" name="cuid" value="<%=uid%>">
		<input type="hidden" name="fuid" value="<%=uid1%>"><br>
		<input type="submit" name="submit" value="post">
		</form>
		
		<%-- <%
		for(QuestionAndAnswerDetails QADetails:QAlist)
		{
			Question question=QADetails.getQuestion();
			Answers answers=QADetails.getAnswer();
		
		%>
		<b><i><%=question.getQuestion()%></i></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<br>
		<%if(answers.getAnswer()!=null) {%>
		<%=answers.getAnswer()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<%} %>
		<br>
		<%=answers.getRating() %>
		<form action="#" method="post">
		<textarea rows="1" cols="50" name="question" placeholder="Enter Your Answer" title="Enter Answer here"></textarea>		
		<input type="hidden" name="uid"	value="<%=communityInfomation.getUid()%>"> 
		<input type="hidden" name="cid" value="<%=communityInfomation.getCid()%>">
		<input type="hidden" name="subid" value="<%=communityInfomation.getSubid()%>"><br>
		<input type="submit" class="submitButton" name="submit" value="Submit">
		<input class="submitButton" type="reset" value="Clear" >
		</form>
		<br><br>
		<%
		}
		%> --%>
</center>

</body>
</html>






