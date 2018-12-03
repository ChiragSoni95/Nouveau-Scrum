package com.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Admin;
import com.se.connection.ConnectionProvider;
import com.se.entities.Feature;
import com.se.entities.Project;
import com.se.entities.Release;
import com.se.entities.Sprint;
import com.se.entities.Task;
import com.se.entities.UserStory;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	int product_id,threshold,buffer;
	String category,brand,sub_category;
	double cost,holding_cost;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			System.out.println("doGet(): Request is Null.");
		} else {
			System.out.println("doGet(): Request is Not Null.");
			doPost(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"); // user-login
		String usernamedisplay=request.getParameter("usernamedisplay");
		request.setAttribute("usernamedisplay", usernamedisplay);
		System.out.println("Action is: " + action);
		if ("doLogin".equalsIgnoreCase(action)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Admin account = new Admin();

			boolean status = false;
			try {
				status = account.process(username, password);
				if(status) {
					System.out.println("user confirmed.");
					sendToDashboard(request,response);
				}else {
					System.out.println("Invalid username/password.");
					request.setAttribute("error", "Invalid login details.");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if ("doRegistration".equalsIgnoreCase(action)) {
			String register_username = request.getParameter("username");
			String register_password = request.getParameter("password");
			String email=request.getParameter("email");
			String firstname=request.getParameter("firstname");
			String lastname=request.getParameter("lastname");
			String role=request.getParameter("role");
			Admin admin = new Admin();
			boolean username_duplicate = admin.checkUsername(register_username);  // Method to check if username is taken or already registered.
			boolean email_duplicate = admin.checkEmail(email);// Method to check if email is already registered

			if(username_duplicate) {
				request.setAttribute("error", "Username already present.");
				request.getRequestDispatcher("/Registration.jsp").forward(request, response);
			}else if(email_duplicate) {
				request.setAttribute("error", "Email already present.");
				request.getRequestDispatcher("/Registration.jsp").forward(request, response);
			}else {
				System.out.println("Register");
				boolean flag = admin.registerUser(register_username,register_password,email,firstname,lastname,role);
				if(flag) {
					request.setAttribute("error", "Registration Successful.");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}else {
					request.setAttribute("error", "Registration Failed.");
					request.getRequestDispatcher("/Registration.jsp").forward(request, response);
				}
			}
		}else if("createProject".equalsIgnoreCase(action)) {
			Project project=new Project();
			try {
				boolean flag=project.createProject(request);
				if(flag) {
					System.out.println("Project Creation successful");
					sendToDashboard(request, response);
				}else {
					System.out.println("Project Creation failed");
					sendToDashboard(request, response);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}else if("createUserStory".equalsIgnoreCase(action)) {
			System.out.println("Create user story action found.");
			UserStory us = new UserStory();
			try {
				boolean flag=us.createUserStory(request);
				if(flag) {
					System.out.println("User Story Creation successful");
					request.getRequestDispatcher("/Sandbox.jsp").forward(request, response);
				}else {
					System.out.println("User Story Creation failed");
					sendToDashboard(request, response);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if("createTask".equalsIgnoreCase(action)) {
			Task task = new Task();
			try {
				boolean flag=task.createTask(request);
				if(flag) {
					System.out.println("Task Creation successful");
					sendToDashboard(request, response);
				}else {
					System.out.println("Task Creation failed");
					sendToDashboard(request, response);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if("createFeature".equalsIgnoreCase(action)) {
			System.out.println("Creating Features");
			Feature feature=new Feature();
			try {
				boolean flag=feature.createFeature(request);
				if(flag) {
					System.out.println("Feature Creation successful");
					sendToProject(request, response);

				}
				else {
					System.out.println("Feature Creation failed");
					sendToDashboard(request, response);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if("selectProject".equalsIgnoreCase(action)){
			String projectName= request.getParameter("project");
			Project project=new Project();
			List<String> projectDetails= project.getProjectDetails(projectName);
			request.setAttribute("projectname",projectDetails.get(0));
			request.setAttribute("created_by",projectDetails.get(1));
			request.setAttribute("owner",projectDetails.get(2));
			request.setAttribute("user_list",projectDetails.get(3));
			request.setAttribute("start_date",projectDetails.get(4));
			request.setAttribute("end_date",projectDetails.get(5));
			request.setAttribute("sprint_duration",projectDetails.get(6));
			request.setAttribute("id",projectDetails.get(7));
			request.setAttribute("description",projectDetails.get(8));
			request.setAttribute("projecttype",projectDetails.get(9));
			request.getRequestDispatcher("/ViewUpdateProject.jsp").forward(request, response);
		}else if("selectFeature".equalsIgnoreCase(action)){
			String featureID= request.getParameter("feature");
			Feature feature=new Feature();
			List<String> featureDetails= feature.getFeatureDetails(featureID);
			request.setAttribute("feature_id",featureDetails.get(0));
			request.setAttribute("name",featureDetails.get(1));
			request.setAttribute("comments",featureDetails.get(2));
			request.setAttribute("created_by",featureDetails.get(3));
			request.setAttribute("create_time",featureDetails.get(4));
			request.setAttribute("priority",featureDetails.get(5));
			request.setAttribute("effort",featureDetails.get(6));
			request.getRequestDispatcher("/ViewEditFeature.jsp").forward(request, response);
		}else if("selectStory".equalsIgnoreCase(action)){
			String storyID= request.getParameter("storyid");
			UserStory story=new UserStory();
			List<String> storyDetails= story.getStoryDetails(storyID);
			request.setAttribute("user_story_id",storyDetails.get(0));
			request.setAttribute("user_story_name",storyDetails.get(1));
			request.setAttribute("status",storyDetails.get(2));
			request.setAttribute("user_story_type",storyDetails.get(3));
			request.setAttribute("dependency_description",storyDetails.get(4));
			request.setAttribute("created_by",storyDetails.get(5));
			request.setAttribute("assigned_to",storyDetails.get(6));
			request.setAttribute("estimation",storyDetails.get(7));
			request.setAttribute("updated_timestamp",storyDetails.get(8));
			request.setAttribute("location",storyDetails.get(9));
			request.setAttribute("comments",storyDetails.get(10));
			request.setAttribute("priority",storyDetails.get(11));
			request.setAttribute("complexity",storyDetails.get(12));
			request.getRequestDispatcher("/ViewEditStory.jsp").forward(request, response);
		}else if("selectTask".equalsIgnoreCase(action)){
			String taskID= request.getParameter("taskid");
			Task task=new Task();
			request.setAttribute("storyid", request.getAttribute("storyid"));
			List<String> taskDetails= task.getTaskDetails(taskID);
			request.setAttribute("task_id",taskDetails.get(0));
			request.setAttribute("task_name",taskDetails.get(1));
			request.setAttribute("description",taskDetails.get(2));
			request.setAttribute("status",taskDetails.get(3));
			request.setAttribute("estimate",taskDetails.get(4));
			request.setAttribute("created_time",taskDetails.get(5));
			request.setAttribute("update_time",taskDetails.get(6));
			request.setAttribute("assigned_to",taskDetails.get(7));
			request.setAttribute("priority",taskDetails.get(8));
			request.getRequestDispatcher("/ViewEditTask.jsp").forward(request, response);
		}else if("updateProject".equalsIgnoreCase(action)) {
			Project project=new Project();
			try {
				boolean flag=project.updateProject(request);
				if(flag) {
					System.out.println("Project updation successful");
					sendToDashboard(request, response);
				}else {
					System.out.println("Project updation failed");
					sendToDashboard(request, response);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if("updateUserStory".equalsIgnoreCase(action)) {
			UserStory us=new UserStory();
			boolean flag = us.updateUserStory(request);
			try {
				if(flag) {
					System.out.println("User Story updation successful");
					if("sandbox".equalsIgnoreCase(request.getParameter("home"))) {
						request.getRequestDispatcher("/Sandbox.jsp").forward(request, response);}
					else if("productbacklog".equalsIgnoreCase(request.getParameter("home"))){
						request.getRequestDispatcher("/ProductBacklog.jsp").forward(request, response);
					}
					else if("sprintplan".equalsIgnoreCase(request.getParameter("home"))){
						request.getRequestDispatcher("/ViewSprintPlan.jsp").forward(request, response);
					}
				}else {
					System.out.println("User Story updation failed");
					request.getRequestDispatcher("/ViewSprintPlan.jsp").forward(request, response);
				} 
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if("updateFeature".equalsIgnoreCase(action)) {
			Feature feature=new Feature();
			try {
				boolean flag=feature.updateFeature(request);
				if(flag) {
					System.out.println("Feature updation successful");
					sendToProject(request, response);
				}else {
					System.out.println("Feature updation failed");
					sendToDashboard(request, response);
				}
			}
			catch(Exception e) {
				System.out.println("Exception in update feature: "+ e.getMessage());
			}
		}else if("getTask".equalsIgnoreCase(action)) {
			try {
				request.setAttribute("storyid", request.getParameter("storyid"));
				request.setAttribute("projectid", request.getParameter("projectid"));
				request.getRequestDispatcher("/CreateTask.jsp").forward(request, response);
			}
			catch(Exception e) {
				System.out.println("Exception while calling create sprint page." + e.getMessage());

			}
		}else if("updateTask".equalsIgnoreCase(action)) {
			Task task=new Task();
			try {
				boolean flag=task.updateTask(request);
				if(flag) {
					System.out.println("Task updation successful");
					request.getRequestDispatcher("/ViewStoryPlan.jsp").forward(request, response);
				}else {
					System.out.println("Task updation failed");
					sendToDashboard(request, response);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if("createRelease".equalsIgnoreCase(action)) {
			System.out.println("Creating Releases");
			Release release=new Release();
			try {
				boolean flag=release.createRelease(request);
				if(flag) {
					System.out.println("Release Creation successful");
					sendToProject(request, response);
				}
				else {
					System.out.println("Release Creation failed");
					sendToDashboard(request, response);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if("selectRelease".equalsIgnoreCase(action)){
			String releaseID= request.getParameter("releaseid");
			Release release=new Release();
			List<String> releaseDetails= release.getReleaseDetails(releaseID);
			request.setAttribute("goals",releaseDetails.get(0));
			request.setAttribute("comments",releaseDetails.get(1));
			request.setAttribute("startdate",releaseDetails.get(2));
			request.setAttribute("enddate",releaseDetails.get(3));
			request.setAttribute("releaseid",releaseID);
			request.getRequestDispatcher("/ViewEditRelease.jsp").forward(request, response);

		}else if("updateRelease".equalsIgnoreCase(action)) {
			Release release=new Release();
			try {
				boolean flag=release.updateRelease(request);
				if(flag) {
					System.out.println("Release updation successful");
					sendToDashboard(request, response);
				}else {
					System.out.println("Release updation failed");
					sendToDashboard(request, response);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if("getSprint".equalsIgnoreCase(action)) {
			try {
				request.setAttribute("releaseid", request.getParameter("releaseid"));			
				request.getRequestDispatcher("/CreateSprint.jsp").forward(request, response);
			}
			catch(Exception e) {
				System.out.println("Exception while calling create sprint page." + e.getMessage());

			}
		}else if("viewstoryplan".equalsIgnoreCase(action)) {
			try {
				request.setAttribute("storyid", request.getParameter("storyid"));			
				request.getRequestDispatcher("/ViewStoryPlan.jsp").forward(request, response);
			}
			catch(Exception e) {
				System.out.println("Exception while calling retrieving tasks page." + e.getMessage());

			}
		}else if("createSprint".equalsIgnoreCase(action)) {
			System.out.println("Creating Sprint for release:" + request.getParameter("releaseid"));
			Sprint sprint=new Sprint();
			try {
				boolean flag=sprint.createSprint(request);
				if(flag) {
					System.out.println("Sprint Creation successful");
					sendToRelease(request, response);
				}else {
					System.out.println("Sprint Creation failed");
					sendToRelease(request, response);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if("selectSprint".equalsIgnoreCase(action)){
			String sprintID= request.getParameter("sprintid");
			Sprint sprint=new Sprint();
			List<String> sprintDetails= sprint.getSprintDetails(sprintID);
			request.setAttribute("description",sprintDetails.get(0));
			request.setAttribute("startdate",sprintDetails.get(1));
			request.setAttribute("enddate",sprintDetails.get(2));
			request.setAttribute("sprintid",sprintID);
			request.getRequestDispatcher("/ViewEditSprint.jsp").forward(request, response);

		}else if("updateSprint".equalsIgnoreCase(action)) {
			Sprint sprint=new Sprint();
			try {
				boolean flag=sprint.updateSprint(request);
				if(flag) {
					System.out.println("Sprint updation successful");
					sendToProject(request, response);
				}else {
					System.out.println("Sprint updation failed");
					sendToProject(request, response);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} 
		}else if("sandbox".equalsIgnoreCase(action)){
			String projectid= request.getParameter("projectid");

			request.setAttribute("id",projectid);

			request.getRequestDispatcher("/Sandbox.jsp").forward(request, response);
		}else if("productBacklog".equalsIgnoreCase(action)){
			String projectid= request.getParameter("projectid");
			String projectName= request.getParameter("projectname");
			request.setAttribute("projectname",projectName);
			request.setAttribute("id",projectid);

			request.getRequestDispatcher("/ProductBacklog.jsp").forward(request, response);
		}else if("storyToBacklog".equalsIgnoreCase(action)){
			UserStory us = new UserStory();
			us.moveToBacklog(request);
			String projectid= request.getParameter("projectid");
			String projectName= request.getParameter("projectname");
			request.setAttribute("projectname",projectName);
			request.setAttribute("id",projectid);

			request.getRequestDispatcher("/Sandbox.jsp").forward(request, response);
		}else if("moveToLocation".equalsIgnoreCase(action)){
			UserStory us = new UserStory();
			us.moveToLocation(request);
			String projectid= request.getParameter("projectid");
			String projectName= request.getParameter("projectname");
			request.setAttribute("projectname",projectName);
			request.setAttribute("id",projectid);
			if("productBacklog".equalsIgnoreCase(request.getParameter("original"))) {
			request.getRequestDispatcher("/ProductBacklog.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/ViewSprintPlan.jsp").forward(request, response);
			}
		}else if("home".equalsIgnoreCase(action)) {
			try {
				System.out.println("Routing to dashboard.");
				sendToDashboard(request, response);
			} catch (SQLException e) {
				System.out.println("Exception while routing to dashboard." + e.getMessage());
			}
		}else if("projecthome".equalsIgnoreCase(action)) {
			System.out.println("Routing to project.");
			sendToProject(request, response);
		}else if("selectSprintPlan".equalsIgnoreCase(action)) {
			System.out.println("Routing to sprintplan.");
			request.getRequestDispatcher("/ViewSprintPlan.jsp").forward(request, response);
		}
	}
	private void sendToProject(HttpServletRequest request, HttpServletResponse response) {
		int projectName= Integer.parseInt(request.getSession(false).getAttribute("projectid").toString());
		Project project=new Project();
		List<String> projectDetails= project.getProjectDetails(projectName);
		request.setAttribute("projectname",projectDetails.get(0));
		request.setAttribute("created_by",projectDetails.get(1));
		request.setAttribute("owner",projectDetails.get(2));
		request.setAttribute("user_list",projectDetails.get(3));
		request.setAttribute("start_date",projectDetails.get(4));
		request.setAttribute("end_date",projectDetails.get(5));
		request.setAttribute("sprint_duration",projectDetails.get(6));
		request.setAttribute("id",projectDetails.get(7));
		request.setAttribute("description",projectDetails.get(8));
		request.setAttribute("projecttype",projectDetails.get(9));
		try {
			request.getRequestDispatcher("/ViewUpdateProject.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}



	}

	private void sendToDashboard(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String username = request.getParameter("username");
		System.out.println("Username Debug: " + username);
		request.setAttribute("usernamedisplay", username);
		String role = getUserRole(username);
		request.setAttribute("role", role);
		request.setAttribute("privileges", checkPrivileges(role));
		Project project=new Project();
		request.setAttribute("existingprojects", project.getExistingProjects(username));
		request.getRequestDispatcher("/Dashboard.jsp").forward(request, response);
	}
	private void sendToRelease(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String username = request.getParameter("username");
		System.out.println("Username Debug: " + username);
		request.setAttribute("usernamedisplay", username);
		String role = getUserRole(username);
		request.setAttribute("role", role);
		request.setAttribute("privileges", checkPrivileges(role));
		String releaseID= request.getParameter("releaseid");
		Release release=new Release();
		List<String> releaseDetails= release.getReleaseDetails(releaseID);
		request.setAttribute("goals",releaseDetails.get(0));
		request.setAttribute("comments",releaseDetails.get(1));
		request.setAttribute("startdate",releaseDetails.get(2));
		request.setAttribute("enddate",releaseDetails.get(3));
		request.setAttribute("releaseid",releaseID);
		request.getRequestDispatcher("/ViewEditRelease.jsp").forward(request, response);
	}
	private String checkPrivileges(String role) {
		String status="hidden";
		if("Project Manager".equalsIgnoreCase(role)){
			status="";
		}
		return status;
	}

	private static String getUserRole(String username) throws SQLException {
		String role = null;
		ConnectionProvider con =  new ConnectionProvider();
		String sql = "select role from users where user_name=?";
		PreparedStatement pstmt = con.getCon().prepareStatement(sql);
		pstmt.setString(1, username);
		ResultSet rst = pstmt.executeQuery();
		while (rst.next()) {
			role=rst.getString("role");
		}
		rst.close();
		pstmt.close();
		return role;
	}
}
