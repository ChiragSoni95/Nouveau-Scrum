<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View or Update Releases</title>
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

	<a href="<%=request.getContextPath()%>/Controller?action=createProject"
		class="logo"><img src="uploads/logo.jpg" width="90" height="41"><span
		class="lite"></span></a> <!--logo end-->


	<div class="top-nav notification-row">
		<!-- notificatoin dropdown start-->
		<ul class="nav pull-right top-menu">
			<!-- user login dropdown start-->
			<li class="dropdown"><a data-toggle="dropdown"
				class="dropdown-toggle" href="#"> <span class="profile-ava">
						<img alt="" src="uploads/logo.jpg" width="30" height="40">
				</span> <span class="username"> <%
 	String name = (String) session.getAttribute("usernamedisplay");
 	session.setAttribute("usernamedisplay", name);

 	if (name != null || "".equalsIgnoreCase(name)) {
 		out.println(name);
 	}
 %></a>
				<ul class="dropdown-menu extended logout">
					<div class="log-arrow-up"></div>
					<li><a href="<%=request.getContextPath()%>/login.jsp"><i
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
			<li class="active"><a class=""
				href="<%=request.getContextPath()%>/Controller?action=home
				&username=<%=session.getAttribute("usernamedisplay")%>">
					<i class="icon_house_alt"></i> <span>Dashboard</span>
			</a></li>
	

			<!--Sprint on side menu -->
			<li class="sub-menu"><a href="<%=request.getContextPath()%>/Controller?action=projecthome" class=""> <i
					class="icon_documents_alt"></i> <span>Sprints</span>
			</a>
				<ul class="sub">
					
					<li class="sub-menu"><a class="">Existing Sprints</a></li>
					<!-- Retrieving details of tasks of a project -->
					<sql:setDataSource var="myDS" driver="com.mysql.jdbc.Driver"
						url="jdbc:mysql://127.0.0.1:3306/scrum" user="root" password="sonichirag1995" />
					<sql:query var="list_of_sprints" dataSource="${myDS}">
					   select description as description, sprint_id as sprint_id from sprint where project_id = "<%=session.getAttribute("projectid")%>";
					 </sql:query>
					<c:forEach var="sprint" items="${list_of_sprints.rows}">
						<tr>
							<td align="right"><a
								href="<%=request.getContextPath()%>/Controller?action=selectSprint&sprintid=<c:out  value="${sprint.sprint_id}"/>">
									<c:out value="${sprint.description}" />
							</a>
							<td>
						<tr>
					</c:forEach>


				</ul></li>
			<!--Sprint on side menu end -->


		</ul>
		<!-- sidebar menu end-->
	</div>
	<!--main content start--> <section id="main-content"> <section
		class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h3 class="page-header">
				<i class="fa fa-files-o"></i> View/Edit Release
			</h3>

		</div>
	</div>
		<div class="row">
		<div class="col-lg-12">
			<form name="createNewSprint" action="<%=request.getContextPath()%>/Controller?">
									<input type="hidden" name="releaseid"
										value="<%=request.getAttribute("releaseid")%>"/>
									<input type="hidden" name="projectid"
										value="<%=session.getAttribute("projectid")%>"/>
									<input type="hidden" name="action"
										value="getSprint" />
				<input type="submit" class="btn btn-primary" value="Create Sprint" /></br></br>
			</form>
		</div>
	</div>

	<!-- Form validations -->
	<div class="row">
		<div class="col-lg-12">
			<section class="panel"> <header class="panel-heading">
			Release Details </header>
			<div class="panel-body">
				<div class="form">
					<form class="form-validate form-horizontal" id="feedback_form"
						method="post">
							<div class="form-group ">
								<label for="cname" class="control-label col-lg-2">Release
									Goals<span class="required">*</span>
								</label>
								<div class="col-lg-10">
									<textarea name="goals" rows=10 cols=30
										 class="form-control" ><%=(String) request.getAttribute("goals")%></textarea>
								</div>
							</div>
						<div class="form-group ">
								<label for="cname" class="control-label col-lg-2">Release
									Comments<span class="required">*</span>
								</label>
								<div class="col-lg-10">
									<textarea name="comments" rows=10 cols=30
										 class="form-control" ><%=(String) request.getAttribute("comments")%></textarea>
								</div>
							</div>
						<div class="form-group ">
							<label for="ccomment" class="control-label col-lg-2">Start
								Date</label>
							<div class="col-lg-10">

									<input type="date" id="startdate" name="startdate"
										class="form-control"
										value="<%=(String) request.getAttribute("startdate")%>"/>
							</div>
						</div>
						<div class="form-group ">
							<label for="ccomment" class="control-label col-lg-2">End
								Date</label>
							<div class="col-lg-10">
								<input type="date" id="enddate" name="enddate"
									class="form-control"
									value="<%=(String) request.getAttribute("enddate")%>"
									min="<c:out value="${proj.start_date}" />"
									max="<c:out value="${proj.end_date}" />" />
							</div>
						</div>



						<div class="form-group">
							<div class="col-lg-offset-2 col-lg-10">
								<button class="btn btn-primary" type="submit"
									formaction="<%=request.getContextPath()%>/Controller?action=updateRelease
									&releaseid=<%=request.getAttribute("releaseid")%>">Save</button>
								<button class="btn btn-default" type="button">Cancel</button>
							</div>
						</div>
					</form>
				</div>

			</div>
			</section>
		</div>
	</div>

	<!-- page end--> </section> </section></section>
	</aside>
	<!--sidebar end-->
	<!--main content start-->
	<!-- javascripts -->
	<script src="js/jquery.js"></script>
	<script src="js/jquery-ui-1.10.4.min.js"></script>
	<script src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.9.2.custom.min.js"></script>
	<!-- bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- nice scroll -->
	<script src="js/jquery.scrollTo.min.js"></script>
	<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
	<!-- charts scripts -->
	<script src="assets/jquery-knob/js/jquery.knob.js"></script>
	<script src="js/jquery.sparkline.js" type="text/javascript"></script>
	<script src="assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
	<script src="js/owl.carousel.js"></script>
	<!-- jQuery full calendar -->
	<
	<script src="js/fullcalendar.min.js"></script>
	<!-- Full Google Calendar - Calendar -->
	<script src="assets/fullcalendar/fullcalendar/fullcalendar.js"></script>
	<!--script for this page only-->
	<script src="js/calendar-custom.js"></script>
	<script src="js/jquery.rateit.min.js"></script>
	<!-- custom select -->
	<script src="js/jquery.customSelect.min.js"></script>
	<script src="assets/chart-master/Chart.js"></script>
	<!--customer script for all page-->
	<script src="js/scripts.js"></script>
	<!-- custom script for this page-->
	<script src="js/sparkline-chart.js"></script>
	<script src="js/easy-pie-chart.js"></script>
	<script src="js/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="js/jquery-jvectormap-world-mill-en.js"></script>
	<script src="js/xcharts.min.js"></script>
	<script src="js/jquery.autosize.min.js"></script>
	<script src="js/jquery.placeholder.min.js"></script>
	<script src="js/gdp-data.js"></script>
	<script src="js/morris.min.js"></script>
	<script src="js/sparklines.js"></script>
	<script src="js/charts.js"></script>
	<script src="js/jquery.slimscroll.min.js"></script>
	<script>
		
	</script>
</body>
</html>
