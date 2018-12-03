<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dashboard</title>
<!-- Bootstrap CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap theme -->
<link href="css/bootstrap-theme.css" rel="stylesheet">
<!--external css-->
<!-- font icon -->
<link href="css/elegant-icons-style.css" rel="stylesheet" />
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!-- full calendar css-->
<link href="assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css"
	rel="stylesheet" />
<link href="assets/fullcalendar/fullcalendar/fullcalendar.css"
	rel="stylesheet" />
<!-- easy pie chart-->
<link href="assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css"
	rel="stylesheet" type="text/css" media="screen" />
<!-- owl carousel -->
<link rel="stylesheet" href="css/owl.carousel.css" type="text/css">
<link href="css/jquery-jvectormap-1.2.2.css" rel="stylesheet">
<!-- Custom styles -->
<link rel="stylesheet" href="css/fullcalendar.css">
<link href="css/widgets.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />
<link href="css/xcharts.min.css" rel=" stylesheet">
<link href="css/jquery-ui-1.10.4.min.css" rel="stylesheet">
</head>

<body>

	<!-- container section start -->
	<section id="container" class=""> <header
		class="header dark-bg">
	<div class="toggle-nav">
		<div class="icon-reorder tooltips"
			data-original-title="Toggle Navigation" data-placement="bottom">
			<i class="icon_menu"></i>
		</div>
	</div>

	<!--logo start--> <!--<a href="" class="logo">DNA <span class="lite">Admin</span></a>-->
	<a href="<%=request.getContextPath()%>/Dashboard.jsp"
		class="logo"><img src="uploads/logo.jpg" width="90" height="41"><span
		class="lite"></span></a> <!--logo end-->


	<div class="top-nav notification-row">
		<!-- notificatoin dropdown start-->
		<ul class="nav pull-right top-menu">
			<!-- user login dropdown start-->
			<li class="dropdown"><a data-toggle="dropdown"
				class="dropdown-toggle" href="#"> <span class="profile-ava">
						<img alt="" src="uploads/logo.jpg"
						width="30" height="40">
				</span> <span class="username"><%
						String name = (String) request.getAttribute("usernamedisplay");
						session.setAttribute("usernamedisplay", name);
						//request.setAttribute("usernamedisplay",name);
						if(name != null || "".equalsIgnoreCase(name)){
							out.println(name);	
						}
					%></span>
					<b class="caret"></b>
			</a>
				<ul class="dropdown-menu extended logout">
					<div class="log-arrow-up">		</div>
					<li><a
						href="<%=request.getContextPath()%>/login.jsp"><i
							class="icon_key_alt"></i> Log Out</a></li>

				</ul></li>
			<!-- user login dropdown end -->
		</ul>
		<!-- notification dropdown end-->
	</div>
	</header> <!--header end--> <!--sidebar start--> <aside>
	<div id="sidebar" class="nav-collapse ">
		<!-- sidebar menu start-->
		<ul class="sidebar-menu">

			<li class="sub-menu"><a href="javascript:;" class=""> <i
					class="icon_document_alt"></i> <span>Projects</span>
			</a>
				

					<li name="createProjectLink" class="<%=request.getAttribute("privileges")%>"><a class=""
						href="<%=request.getContextPath()%>/CreateProject.jsp">+ Create New Project</a>
					</li>
					
					<li class=""> <a>Existing Project</a></li>    
					<li class="active">	
					
					 <sql:setDataSource
      				  var="myDS"
      				  driver="com.mysql.jdbc.Driver"
     				  url="jdbc:mysql://127.0.0.1:3306/scrum"
        			  user="root" password="sonichirag1995" />
        			  <sql:query var="list_of_projects" dataSource="${myDS}">
					   select name as name from project where user_list like "%<%=session.getAttribute("usernamedisplay")%>%";
					  </sql:query>
					<c:forEach var="proj" items="${list_of_projects.rows}">
					<tr><td><a href="<%=request.getContextPath()%>/Controller?action=selectProject&project=<c:out  value="${proj.name}"/>">
					  <c:out  value="${proj.name}" /></a><td>
					  <tr>
					  </c:forEach>
					 </li>
				</li>

		</ul>
		<!-- sidebar menu end-->
		  <input type="hidden" id="usernamedisplay" name="usernamedisplay" value="<%=request.getAttribute("usernamedisplay")%>"></input>
		
							<% session.setAttribute("privileges",request.getAttribute("privileges"));%>
		
	</div>
	</aside> <!--sidebar end-->
	
	
	<!-- javascripts --> <script
		src="js/jquery.js"></script> <script src="js/jquery-ui-1.10.4.min.js"></script>
	<script src="js/jquery-1.8.3.min.js"></script> <script
		type="text/javascript" src="js/jquery-ui-1.9.2.custom.min.js"></script>
	<!-- bootstrap --> <script src="js/bootstrap.min.js"></script> <!-- nice scroll -->
	<script src="js/jquery.scrollTo.min.js"></script> <script
		src="js/jquery.nicescroll.js" type="text/javascript"></script> <!-- charts scripts -->
	<script src="assets/jquery-knob/js/jquery.knob.js"></script> <script
		src="js/jquery.sparkline.js" type="text/javascript"></script> <script
		src="assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
	<script src="js/owl.carousel.js"></script> <!-- jQuery full calendar -->
	< <script src="js/fullcalendar.min.js"></script> <!-- Full Google Calendar - Calendar -->
	<script src="assets/fullcalendar/fullcalendar/fullcalendar.js"></script>
	<!--script for this page only--> <script src="js/calendar-custom.js"></script>
	<script src="js/jquery.rateit.min.js"></script> <!-- custom select -->
	<script src="js/jquery.customSelect.min.js"></script> <script
		src="assets/chart-master/Chart.js"></script> <!--customer script for all page-->
	<script src="js/scripts.js"></script> <!-- custom script for this page-->
	<script src="js/sparkline-chart.js"></script> <script
		src="js/easy-pie-chart.js"></script> <script
		src="js/jquery-jvectormap-1.2.2.min.js"></script> <script
		src="js/jquery-jvectormap-world-mill-en.js"></script> <script
		src="js/xcharts.min.js"></script> <script
		src="js/jquery.autosize.min.js"></script> <script
		src="js/jquery.placeholder.min.js"></script> <script
		src="js/gdp-data.js"></script> <script src="js/morris.min.js"></script>
	<script src="js/sparklines.js"></script> <script src="js/charts.js"></script>
	<script src="js/jquery.slimscroll.min.js"></script> <script>




  </script>
</body>
</html>
