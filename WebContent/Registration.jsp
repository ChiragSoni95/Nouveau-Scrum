<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Creative - Bootstrap 3 Responsive Admin Template">
<meta name="author" content="GeeksLabs">
<meta name="keyword"
	content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
<link rel="shortcut icon" href="img/favicon.png">

<title>Registration Page</title>

<!-- Bootstrap CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap theme -->
<link href="css/bootstrap-theme.css" rel="stylesheet">
<!--external css-->
<!-- font icon -->
<link href="css/elegant-icons-style.css" rel="stylesheet" />
<link href="css/font-awesome.css" rel="stylesheet" />
<!-- Custom styles -->
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />

</head>

<body class="login-img3-body">

	<div class="container">

		<form class="login-form" name="registerform"
			action="<%=request.getContextPath()%>/Controller?action=doRegistration"
			onsubmit="return password_validation()" method="post">

			<div class="login-wrap">
				<p class="login-img">
					<i class="icon_lock_alt"></i>
				</p>
				<font color="red">
					<%
						String name = (String) request.getAttribute("error");
						if(name != null || "".equalsIgnoreCase(name)){
							out.println(name);	
						}
					%>
				</font>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_profile"></i></span>
					<input type="text" name="firstname" class="form-control"
						placeholder="Firstname" autofocus required>
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_profile"></i></span>
					<input type="text" name="lastname" class="form-control"
						placeholder="Lastname" autofocus required>
				</div>
				
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_profile"></i></span>
					<input type="text" name="username" class="form-control"
						placeholder="Username" autofocus required>
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_profile"></i></span>
					<input type="email" name="email" class="form-control"
						placeholder="Email ID" autofocus required>						
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_profile"></i></span>
                                              <select name="role" class="form-control">
                                              <option selected="selected">Select your role</option>
                                                  <option value="Project Manager">Project Manager</option>
    												<option value="Developer">Developer</option>
                                              </select>					
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_key_alt"></i></span>
					<input type="password" name="password" class="form-control"
						placeholder="Password" required>
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_key_alt"></i></span>
					<input type="password" name="confirmpassword" class="form-control"
						placeholder="Confirm Password" required> <span
						id='message'></span>
				</div>
				<div class="registrationFormAlert" id="divCheckPasswordMatch">
				</div>
				<button class="btn btn-primary btn-lg btn-block" type="submit">Submit</button>
			</div>
		</form>

	</div>


</body>
<script>
function password_validation()
{
var pass=document.forms["registerform"]["password"].value;
var confpass=document.forms["registerform"]["confirmpassword"].value;
if(pass!=confpass)
{
	alert("Passwords donot match");
return false;
}

}

</script>

</html>


