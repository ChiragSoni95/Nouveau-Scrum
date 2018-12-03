package com.se.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.se.connection.ConnectionProvider;

public class UserStory {
	public boolean createUserStory(HttpServletRequest request) throws ParseException{

		int pID=Integer.parseInt(request.getParameter("projectid"));
		String name= request.getParameter("storyname");
		String status=request.getParameter("status");
		String type=request.getParameter("type");
		int fID=Integer.parseInt(request.getParameter("features"));
		String dependencyDesc=request.getParameter("dependency");
		int createdBy=Integer.parseInt(request.getParameter("createdby"));
		String assignedTo= request.getParameter("assignedon");
		int watchList=0;	//Integer.parseInt(request.getParameter("watchList"));
		double estimation=Double.parseDouble(request.getParameter("estimation"));
		String location="Sandbox";
		String comments=request.getParameter("description");
		int priority=Integer.parseInt(request.getParameter("priority"));
		String complexity = request.getParameter("complexity");
		ConnectionProvider con =  new ConnectionProvider();

		String sql = "Insert into user_stories (project_id,user_story_name,status,user_story_type,feature_id"
				+ ",dependency_description,created_by,assigned_to,watch_list,estimation,updated_timestamp,"
				+ "location,comments,priority,complexity) values (?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.getCon().prepareStatement(sql);
			pstmt.setInt(1,pID);
			pstmt.setString(2,name);
			pstmt.setString(3,status);
			pstmt.setString(4,type);
			pstmt.setInt(5,fID);
			pstmt.setString(6, dependencyDesc);
			pstmt.setInt(7, createdBy);
			pstmt.setString(8, assignedTo);
			pstmt.setInt(9, watchList);
			pstmt.setDouble(10, estimation);
			pstmt.setString(11, location);
			pstmt.setString(12, comments);
			pstmt.setDouble(13, priority);
			pstmt.setString(14, complexity);

			pstmt.executeUpdate();
			pstmt.close();

		}
		catch(SQLException e) {
			System.out.println("SQL issue in createUserStory(): " + e.getMessage());
			return false;
		}
		return true;
	}

	public String getExistingUserStories(int pID) {
		// TODO Auto-generated method stub
		String existingUserStories="";
		ConnectionProvider con =  new ConnectionProvider();
		String sql = "select user_story_name as name from user_stories where project_id = ?";
		try {
			PreparedStatement pstmt = con.getCon().prepareStatement(sql);
			pstmt.setInt(1, pID);
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				existingUserStories =existingUserStories+ rst.getString("name")+ ",";
			}
			rst.close();
			pstmt.close();
		}catch(SQLException e) {
			System.out.println("SQL issue in getExistingUserStories(): " + e.getMessage());
		}
		return existingUserStories;
	}
	public List<String> getStoryDetails(String storyID) {
		List<String> storyDetails= new ArrayList<String>();

		ConnectionProvider con =  new ConnectionProvider();
		String sql = "select user_story_id,user_story_name, status,user_story_type,dependency_description,created_by,assigned_to,"
				+ "estimation,updated_timestamp,location,comments,priority,complexity,feature_id from user_stories "
				+ "where user_story_id=?";
		try {
			PreparedStatement pstmt = con.getCon().prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(storyID));
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				storyDetails.add(rst.getString("user_story_id"));
				storyDetails.add(rst.getString("user_story_name"));
				storyDetails.add(rst.getString("status"));
				storyDetails.add(rst.getString("user_story_type"));
				storyDetails.add(rst.getString("dependency_description"));
				storyDetails.add(rst.getString("created_by"));
				storyDetails.add(rst.getString("assigned_to"));
				storyDetails.add(rst.getString("estimation"));
				storyDetails.add(rst.getDate("updated_timestamp").toString());
				storyDetails.add(rst.getString("location"));
				storyDetails.add(rst.getString("comments"));
				storyDetails.add(rst.getString("priority"));
				storyDetails.add(rst.getString("complexity"));
				storyDetails.add(rst.getString("feature_id"));
			}
			rst.close();
			pstmt.close();

		}
		catch(SQLException e) {
			System.out.println("SQL issue in getStoryDetails():" + e.getMessage());
		}

		return storyDetails;

	}

	public boolean updateUserStory(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String name = request.getParameter("storyname");
		String description = request.getParameter("description");
		double estimation = Double.parseDouble(request.getParameter("estimation"));
		String assignedTo = request.getParameter("assignedon");
		String complexity = request.getParameter("complexity");
		int priority = Integer.parseInt(request.getParameter("priority"));
		String status = request.getParameter("status");
		ConnectionProvider con =  new ConnectionProvider();
		String sql = "Update user_stories set user_story_name = ?,comments = ?,estimation = ?,assigned_to = ?,"
				+ "complexity = ?,priority = ?,status = ? "
				+ "where user_story_id = ?";
		try {
			PreparedStatement pstmt = con.getCon().prepareStatement(sql);
			pstmt.setString(1,name);
			pstmt.setString(2,description);
			pstmt.setDouble(3, estimation);
			pstmt.setString(4, assignedTo);
			pstmt.setString(5,complexity);
			pstmt.setInt(6,priority);
			pstmt.setString(7,status);
			pstmt.setInt(8,Integer.parseInt(request.getParameter("storyid")));

			pstmt.executeUpdate();
			pstmt.close();

		}
		catch(SQLException r) {
			System.out.println("Project updation failed during update query" + r.getMessage());
			return false;
		}
		return true;

	}

	public boolean moveToBacklog(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String location = "Product Backlog";
		ConnectionProvider con =  new ConnectionProvider();
		String sql = "Update user_stories set location = ? where user_story_id = ?";
		try {
			PreparedStatement pstmt = con.getCon().prepareStatement(sql);
			pstmt.setString(1,location);
			pstmt.setInt(2,Integer.parseInt(request.getParameter("storyId")));

			pstmt.executeUpdate();
			pstmt.close();

		}
		catch(SQLException r) {
			System.out.println("Project updation failed while moving to product backlog" + r.getMessage());
			return false;
		}
		return true;
	}
	public boolean moveToLocation(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String location = request.getParameter("location");
		if(!"Sandbox".equalsIgnoreCase(location) && !"Product Backlog".equalsIgnoreCase(location)) {
			location = "Sprint " + location;
		}
		ConnectionProvider con =  new ConnectionProvider();
		String sql = "Update user_stories set location = ? where user_story_id = ?";
		try {
			PreparedStatement pstmt = con.getCon().prepareStatement(sql);
			pstmt.setString(1,location);
			pstmt.setInt(2,Integer.parseInt(request.getParameter("storyId")));

			pstmt.executeUpdate();
			pstmt.close();

		}
		catch(SQLException r) {
			System.out.println("Project updation failed while moving to product backlog" + r.getMessage());
			return false;
		}
		return true;
	}
}
