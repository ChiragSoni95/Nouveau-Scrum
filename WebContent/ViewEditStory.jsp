<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Map,java.util.HashMap"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Story</title>
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
<%
	Map<String, String> complexity = new HashMap<String, String>();
	complexity.put((String) request.getAttribute("complexity"), "selected");
	request.setAttribute("complexity_display", complexity);
%>

<%
	Map<String, String> features = new HashMap<String, String>();
	features.put("F" + (String) request.getAttribute("features"), "selected");
	request.setAttribute("feature_display", features);
%>

<%
	Map<String, String> assignedto = new HashMap<String, String>();
	assignedto.put((String) request.getAttribute("assigned_to"), "selected");
	request.setAttribute("assignedto_display", assignedto);
%>


<%
	Map<String, String> status = new HashMap<String, String>();
	status.put((String) request.getAttribute("status"), "selected");
	request.setAttribute("status_display", status);
%>
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

	<a href="<%=request.getContextPath()%>/Controller?action=home"
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
					<li><a href="<%=request.getContextPath()%>/login.jsp"><i
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
				&username=<%= session.getAttribute("usernamedisplay")%>">
					<i class="icon_house_alt"></i> <span>Dashboard</span>
			</a></li>
			<li class="sub-menu"><a href="<%=request.getContextPath()%>/Controller?action=projecthome" class=""> <i
					class="icon_desktop"></i> <span><%=session.getAttribute("projectname")%></span>
			</a></li>
			<li class="sub-menu"><a href="javascript:;" class=""> <i
					class="icon_documents_alt"></i> <span>Features</span>
			</a>
				<ul class="sub">
					<li name="createFeatureLink"
						class="<%=session.getAttribute("privileges")%>"><a class=""
						href="<%=request.getContextPath()%>/CreateFeatures.jsp">Create
							Features</a></li>
					<li class="sub-menu"><a>View Features</a></li>
					<!-- Retrieving details of features of a project -->
					<sql:setDataSource var="myDS" driver="com.mysql.jdbc.Driver"
						url="jdbc:mysql://127.0.0.1:3306/scrum" user="root"
						password="sonichirag1995" />
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
				<i class="fa fa-files-o"></i>Update Story
			</h3>
			<ol class="breadcrumb">
				<li><i class="fa fa-home"></i>Home</li>
				<li><i class="icon_document_alt"></i>Stories</li>
				<li><i class="fa fa-files-o"></i>View/Update Stories</li>
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
									type="text" required
									value="<%=(String) request.getAttribute("user_story_name")%>" />
							</div>
						</div>

						<div class="form-group ">
							<label for="cname" class="control-label col-lg-2">Story
								Description<span class="required">*</span>
							</label>
							<div class="col-lg-10">
								<textarea name="description" rows=10 cols=30
									/ class="form-control"><%=(String) request.getAttribute("comments")%></textarea>
							</div>

						</div>

						<div class="form-group ">
							<label for="curl" class="control-label col-lg-2">Created
								By</label>
							<div class="col-lg-10">
								<input class="form-control " id="createdby" type="text"
									name="createdby" disabled="disabled"
									value="<%=(String) request.getAttribute("created_by")%>" />
							</div>
						</div>

						<div class="form-group ">
							<label for="ccomment" class="control-label col-lg-2">Updated
								On</label>
							<div class="col-lg-10">
								<input type="date" id="createdon" name="createdon"
									min="2015-01-01" max="2020-12-31" disabled="disabled"
									value="<%=(String) request.getAttribute("updated_timestamp")%>" />
							</div>
						</div>
						<div class="form-group ">
							<label for="curl" class="control-label col-lg-2">Feature
							</label>
							<div class="col-lg-10">
								<select name="features" disabled="disabled"
									selected="<%=(String) request.getAttribute("features")%>">
									<option value="1" ${feature_display.F1}>f1</option>
									<option value="2" ${feature_display.F2}>f2</option>
									<option value="2" ${feature_display.F3}>f3</option>
									<option value="4" ${feature_display.F4}>f4</option>
								</select>
							</div>
						</div>
						<div class="form-group ">
							<label for="curl" class="control-label col-lg-2">Estimation</label>
							<div class="col-lg-10">
								<input class="form-control " id="estimation" type="number"
									name="estimation"
									value="<%=(String) request.getAttribute("estimation")%>" />
							</div>
						</div>
						<div class="form-group ">
							<label for="cname" class="control-label col-lg-2">Assigned
								to <span class="required">*</span>
							</label>
							<div class="col-lg-10">
								<select name="assignedon"
									selected="<%=(String) request.getAttribute("assigned_to")%>">
									<option value="Chirag" ${assignedto_display.Chirag}>Chirag</option>
									<option value="Srinath" ${assignedto_display.Srinath}>Srinath</option>
									<option value="Deepika" ${assignedto_display.Deepika}>Deepika</option>
									<option value="Harsh" ${assignedto_display.Harsh}>Harsh</option>
								</select>
							</div>
						</div>
						<div class="form-group ">
							<label for="cname" class="control-label col-lg-2">Location<span
								class="required"></span>
							</label>
							<div class="col-lg-10">
								<label name="location"><%=(String) request.getAttribute("location")%></label>
							</div>
						</div>

						<div class="form-group ">
							<label for="cname" class="control-label col-lg-2">Complexity<span
								class="required">*</span>
							</label>
							<div class="col-lg-10">
								<select name="complexity"
									selected="<%=(String) request.getAttribute("complexity")%>">
									<option value="Low" ${complexity_display.Low}>Low</option>
									<option value="Medium" ${complexity_display.Medium}>Medium</option>
									<option value="High" ${complexity_display.High}>High</option>
								</select>
							</div>
						</div>

						<div class="form-group ">
							<label for="curl" class="control-label col-lg-2">Priority</label>
							<div class="col-lg-10">
								<input class="form-control " id="priority" type="number"
									name="priority"
									value="<%=(String) request.getAttribute("priority")%>" />
							</div>
						</div>


						<div class="form-group ">
							<label for="cname" class="control-label col-lg-2">Status<span
								class="required">*</span>
							</label>
							<div class="col-lg-10">
								<select name="status"
									selected="<%=(String) request.getAttribute("status")%>">
									<option value="status" ${status_display.Todo}>Todo</option>
									<option value="In_progress" ${status_display.In_progress}>In
										progress</option>
									<option value="Done" ${status_display.Done}>Done</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-offset-2 col-lg-10">
								<button class="btn btn-primary" type="submit"
									formaction="<%=request.getContextPath()%>/Controller?action=updateUserStory&sprintid=<%=(String) request.getParameter("sprintid")%>&home=<%=(String) request.getParameter("home")%>&storyid=<%=(String) request.getAttribute("user_story_id")%>
									&username=<%= session.getAttribute("usernamedisplay")%>">Save</button>
								<button class="btn btn-default" type="button">Cancel</button>
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
