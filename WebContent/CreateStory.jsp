<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CreateStory</title>
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
	<input type="hidden" name="projectid"
		value="<%=session.getAttribute("projectid")%>"></input> <a
		href="<%=request.getContextPath()%>/Controller?action=home"
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
 	//request.setAttribute("usernamedisplay",name);
 	if (name != null || "".equalsIgnoreCase(name)) {
 		out.println(name);
 	}
 %></a>
				<ul class="dropdown-menu extended logout">
					<div class="log-arrow-up"></div>
					<li><a
						href="<%=request.getContextPath()%>/login.jsp"><i
							class="icon_key_alt"></i> Log Out</a></li>

				</ul></li>
			<!-- user login dropdown end -->
		</ul>
		<!-- notification dropdown end-->
	</div>
	</header> <!--header end--><!--sidebar start--> <aside>
	<div id="sidebar" class="nav-collapse ">
		<!-- sidebar menu start-->
		<ul class="sidebar-menu">
			<li class="active"><a class=""
				href="<%=request.getContextPath()%>/Controller?action=home
				&username=<%= session.getAttribute("usernamedisplay")%>"> <i
					class="icon_house_alt"></i> <span>Dashboard</span>
			</a></li>
			<li class="sub-menu"><a href="<%=request.getContextPath()%>/Controller?action=projecthome" class=""> <i
					class="icon_desktop"></i> <span><%=session.getAttribute("projectname")%></span>
			</a></li>
			<li class="sub-menu"><a href="javascript:;" class=""> <i
					class="icon_documents_alt"></i> <span>Features</span>
			</a>
				<ul class="sub">
					<li name="createFeatureLink" class="<%=session.getAttribute("privileges")%>">
					<a class="" href="<%=request.getContextPath()%>/CreateFeatures.jsp">Create Features</a></li>
					<li class="sub-menu"><a>View Features</a></li>
					<!-- Retrieving details of features of a project -->
					<sql:setDataSource var="myDS" driver="com.mysql.jdbc.Driver"
						url="jdbc:mysql://127.0.0.1:3306/scrum" user="root" password="sonichirag1995" />
					<sql:query var="list_of_features" dataSource="${myDS}">
					   select name as name, feature_id as feature_id from feature where project_id like "<%=session.getAttribute("projectid")%>";
					  </sql:query>
					<c:forEach var="feature" items="${list_of_features.rows}">
						<tr>
							<td><a
								href="<%=request.getContextPath()%>/Controller?action=selectFeature&feature=<c:out  value="${feature.feature_id}"/>">
									<c:out value="${feature.name}" />
							</a>
							<td>
						<tr>
					</c:forEach>


				</ul></li>

					
		<!-- sidebar menu end-->
	</div></section>
	</aside>
	<!--sidebar end-->
	</div>
	<!--main content start-->
	<section id="main-content"> <section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h3 class="page-header">
				<i class="fa fa-files-o"></i>Create Story
			</h3>
			<ol class="breadcrumb">
				<li><i class="fa fa-home"></i>Home</li>
				<li><i class="icon_document_alt"></i>Story</li>
				<li><i class="fa fa-files-o"></i>Create Story</li>
			</ol>
		</div>
	</div>
	<!-- Form validations -->
	<div class="row">
		<div class="col-lg-12">
			<section class="panel"> <header class="panel-heading">
			Project Details </header>
			<div class="panel-body">
				<div class="form">
					<form class="form-validate form-horizontal" id="feedback_form"
						method="post">
						<input type="hidden" name="projectid"
							value="<%=session.getAttribute("projectid")%>"></input>

						<div class="form-group ">
							<label for="cname" class="control-label col-lg-2">Story
								Name <span class="required">*</span>
							</label>
							<div class="col-lg-10">
								<input class="form-control" id="sname" name="storyname"
									type="text" required />
							</div>
						</div>

						<div class="form-group ">
							<label for="cname" class="control-label col-lg-2">Story
								Description<span class="required">*</span>
							</label>
							<div class="col-lg-10">
								<textarea name="description" rows=10 cols=30
									/ class="form-control"></textarea>
							</div>

						</div>

						<div class="form-group ">
							<label for="curl" class="control-label col-lg-2">Created
								By</label>
							<div class="col-lg-10">
								<input class="form-control " id="createdby" type="text"
									name="createdby" />
							</div>
						</div>

						<div class="form-group ">
							<label for="ccomment" class="control-label col-lg-2">Created
								On</label>
							<div class="col-lg-10">
								<input type="date" id="createdon" name="createdon"
									value="2018-07-22" min="2015-01-01" max="2020-12-31" />
							</div>
						</div>
						<div class="form-group ">
							<label for="curl" class="control-label col-lg-2">Feature
							</label>
							<div class="col-lg-10">
								<select name="features">
									<option value="1">f1</option>
									<option value="2">f2</option>
									<option value="3">f3</option>
									<option value="4">f4</option>
								</select>
							</div>
						</div>
						<div class="form-group ">
							<label for="curl" class="control-label col-lg-2">Estimation</label>
							<div class="col-lg-10">
								<input class="form-control " id="estimation" type="number"
									name="estimation" />
							</div>
						</div>
						<div class="form-group ">
							<label for="cname" class="control-label col-lg-2">Assigned
								to <span class="required">*</span>
							</label>
							<div class="col-lg-10">
								<select name="assignedon">
									<option value="Chirag">Chirag</option>
									<option value="Srinath">Srinath</option>
									<option value="Deepika">Deepika</option>
									<option value="Harsh">Harsh</option>
								</select>
							</div>
						</div>

						<div class="form-group ">
							<label for="cname" class="control-label col-lg-2">Complexity<span
								class="required">*</span>
							</label>
							<div class="col-lg-10">
								<select name="complexity">
									<option value="Low">Low</option>
									<option value="Medium">Medium</option>
									<option value="High">High</option>
								</select>
							</div>
						</div>

						<div class="form-group ">
							<label for="curl" class="control-label col-lg-2">Priority</label>
							<div class="col-lg-10">
								<input class="form-control " id="priority" type="number"
									name="priority" />
							</div>
						</div>


						<div class="form-group ">
							<label for="cname" class="control-label col-lg-2">Status<span
								class="required">*</span>
							</label>
							<div class="col-lg-10">
								<select name="status">
									<option value="Todo">Todo</option>
									<option value="In_progress">In progress</option>
									<option value="Done">Done</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-offset-2 col-lg-10">
								<button class="btn btn-primary" type="submit"
									formaction="<%=request.getContextPath()%>/Controller?action=createUserStory
									&username=<%=session.getAttribute("usernamedisplay")%>"
									&projectid=<%=session.getAttribute("projectid")%>">Save</button>
								<button class="btn btn-default" type="button" formaction="<%=request.getContextPath()%>/Controller?action=projecthome
									&projectid=<%=session.getAttribute("projectid")%>">Cancel</button>
							</div>
						</div>
					</form>
				</div>

			</div>
			</section>
		</div>
	</div>
	<!-- page end--> </section> </section>
	</section>
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
	<!--custome script for all page-->
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
