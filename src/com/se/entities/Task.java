package com.se.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.se.connection.ConnectionProvider;

public class Task {
	 public boolean createTask(HttpServletRequest request) throws ParseException{
		 		 
		 int pID =Integer.parseInt(request.getParameter("projectid"));
		 String taskName= request.getParameter("taskname");
		 String description=request.getParameter("description");
		 int userStoryID = Integer.parseInt(request.getParameter("storyid"));
		 String status=request.getParameter("status");
		 double estimation=Double.parseDouble(request.getParameter("estimation"));
		 String assignedTo= request.getParameter("assignedto");
		 
		 int priority=Integer.parseInt(request.getParameter("priority"));
		 
		 ConnectionProvider con =  new ConnectionProvider();

			String sql = "INSERT INTO task (project_id, task_name, description, user_story_id, status, estimate, created_time, update_time, "
					+ "assigned_to,priority) VALUES (?,?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?)";
			try {
				PreparedStatement pstmt = con.getCon().prepareStatement(sql);
				pstmt.setInt(1,pID);
				pstmt.setString(2,taskName);
				pstmt.setString(3,description);
				pstmt.setInt(4, userStoryID);
				pstmt.setString(5,status);
				pstmt.setDouble(6, estimation);
				pstmt.setString(7, assignedTo);
				pstmt.setInt(8, priority);
				
				pstmt.executeUpdate();
				pstmt.close();

			}
			catch(SQLException e) {
				System.out.println("SQL issue in createTask(): " + e.getMessage());
				return false;
			}
		 return true;
	 }

	public String getExistingTask(int userStoryID) {
		// TODO Auto-generated method stub
		String existingTask="";
		ConnectionProvider con =  new ConnectionProvider();
		String sql = "select task_name as name from task where user_story_id = ?";
		try {
			PreparedStatement pstmt = con.getCon().prepareStatement(sql);
			pstmt.setInt(1,userStoryID);
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				existingTask = existingTask+ rst.getString("name")+ ",";
			}
			rst.close();
			pstmt.close();
		}catch(SQLException e) {
			System.out.println("SQL issue in getExistingTask(): " + e.getMessage());
		}
		return existingTask;
	}
	public List<String> getTaskDetails(String taskID) {
		List<String> taskDetails= new ArrayList<String>();

		ConnectionProvider con =  new ConnectionProvider();
		String sql = "select task_id,task_name, description,status,estimate,created_time,update_time,assigned_to,priority from task "
				+ "where task_id=?";
		try {
			PreparedStatement pstmt = con.getCon().prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(taskID));
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				taskDetails.add(rst.getString("task_id"));
				taskDetails.add(rst.getString("task_name"));
				taskDetails.add(rst.getString("description"));
				taskDetails.add(rst.getString("status"));
				taskDetails.add(rst.getString("estimate"));
				taskDetails.add(rst.getDate("created_time").toString());
				taskDetails.add(rst.getDate("update_time").toString());
				taskDetails.add(rst.getString("assigned_to"));
				taskDetails.add(rst.getString("priority"));
			}
			rst.close();
			pstmt.close();
			
		}
		catch(SQLException e) {
			System.out.println("SQL issue in gettaskDetails():" + e.getMessage());
		}
		
		return taskDetails;
		
	}
	public boolean updateTask(HttpServletRequest request) throws ParseException {
		// TODO Auto-generated method stub
		 String taskName=request.getParameter("taskname");
		 String taskDescription= request.getParameter("description");
		 String estimation=request.getParameter("estimation");
		 String assignedto=request.getParameter("assignedto");
		 String priority=request.getParameter("priority");
		 String status=request.getParameter("status");
		  ConnectionProvider con =  new ConnectionProvider();
			String sql = "Update task set task_name=?,description=?,update_time=CURRENT_TIMESTAMP,estimate=?,assigned_to=?,priority=?,status=? where task_id=\""+request.getParameter("task_id")+"\"";
			try {
				PreparedStatement pstmt = con.getCon().prepareStatement(sql);
				pstmt.setString(1,taskName);
				pstmt.setString(2,taskDescription);
				pstmt.setString(3, estimation);
				pstmt.setString(4, assignedto);
				pstmt.setString(5, priority);
				pstmt.setString(6, status);
				pstmt.executeUpdate();
				pstmt.close();

			}
			catch(SQLException r) {
				System.out.println("Task updation failed during update query");
				r.printStackTrace();
				return false;
			}
		 return true;
		
	}


}
