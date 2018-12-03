package com.se.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.se.connection.ConnectionProvider;

public class Project {
	public boolean createProject(HttpServletRequest request) throws ParseException{

		String projectName=request.getParameter("projname");
		System.out.print(projectName);
		String projectDescription= request.getParameter("description");
		String createdBy=request.getParameter("createdby");
		String owner=request.getParameter("owner");
		String members[]=request.getParameterValues("members");
		String projectType=request.getParameter("projecttype");
		String startDate=request.getParameter("startdate");
		String endDate=request.getParameter("enddate");
		Double sprintDuration=Double.parseDouble(request.getParameter("sprintduration"));
		String memberList="";
		for(int i=0;i<members.length;i++) {
			memberList=memberList+members[i]+",";
		}
		memberList=memberList.substring(0, memberList.length()-1);

		ConnectionProvider con =  new ConnectionProvider();

		String sql = "Insert into project (name,created_by,owner,user_list,project_type,sprint_duration,start_date,end_date,description) values (?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.getCon().prepareStatement(sql);
			pstmt.setString(1,projectName);
			pstmt.setString(2,createdBy);
			pstmt.setString(3,owner);
			pstmt.setString(4,memberList);
			pstmt.setString(5,projectType);
			pstmt.setDouble(6, sprintDuration);
			pstmt.setDate(7, dateConverter(startDate));
			pstmt.setDate(8, dateConverter(endDate));
			pstmt.setString(9,projectDescription);

			pstmt.executeUpdate();
			pstmt.close();

		}
		catch(SQLException r) {
			System.out.println("Project creation failed during insert query");
			r.printStackTrace();
			return false;
		}
		return true;
	}


	/*Method to convert String to SQL Date format*/
	public static java.sql.Date dateConverter(String str) throws ParseException {
		java.sql.Date sqlDate = java.sql.Date.valueOf(str);
		return sqlDate;		
	}


	public List<String> getExistingProjects(String username) {
		// TODO Auto-generated method stub
		List<String> existingproject= new ArrayList<String>();



		ConnectionProvider con =  new ConnectionProvider();
		String sql = "select name as name from project where user_list like \""+username+"%\"";
		try {
			PreparedStatement pstmt = con.getCon().prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				existingproject.add( rst.getString("name"));
			}
			rst.close();
			pstmt.close();

		}
		catch(SQLException e) {
			System.out.println("SQL issue in getExistingProjects()");
		}



		return existingproject;
	}


	public List<String> getProjectDetails(String projectName) {
		List<String> projectDetails= new ArrayList<String>();

		ConnectionProvider con =  new ConnectionProvider();
		String sql = "select name, created_by, owner, user_list,  start_date, end_date, sprint_duration,id,project_type,description from project "
				+ "where name=?";
		try {
			PreparedStatement pstmt = con.getCon().prepareStatement(sql);
			pstmt.setString(1, projectName);
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				projectDetails.add(rst.getString("name"));
				projectDetails.add(rst.getString("created_by"));
				projectDetails.add(rst.getString("owner"));
				projectDetails.add(rst.getString("user_list"));
				projectDetails.add(rst.getDate("start_date").toString());
				projectDetails.add(rst.getDate("end_date").toString());
				projectDetails.add(rst.getDouble("sprint_duration")+"");
				projectDetails.add(rst.getString("id"));
				projectDetails.add(rst.getString("description"));
				projectDetails.add(rst.getString("project_type"));
			}
			rst.close();
			pstmt.close();

		}
		catch(SQLException e) {
			System.out.println("SQL issue in getProjectDetails()");
		}

		return projectDetails;

	}
	public List<String> getProjectDetails(int projectid) {
		List<String> projectDetails= new ArrayList<String>();

		ConnectionProvider con =  new ConnectionProvider();
		String sql = "select name, created_by, owner, user_list,  start_date, end_date, sprint_duration,id,project_type,description from project "
				+ "where id=?";
		try {
			PreparedStatement pstmt = con.getCon().prepareStatement(sql);
			pstmt.setInt(1, projectid);
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				projectDetails.add(rst.getString("name"));
				projectDetails.add(rst.getString("created_by"));
				projectDetails.add(rst.getString("owner"));
				projectDetails.add(rst.getString("user_list"));
				projectDetails.add(rst.getDate("start_date").toString());
				projectDetails.add(rst.getDate("end_date").toString());
				projectDetails.add(rst.getDouble("sprint_duration")+"");
				projectDetails.add(rst.getString("id"));
				projectDetails.add(rst.getString("description"));
				projectDetails.add(rst.getString("project_type"));
			}
			rst.close();
			pstmt.close();

		}
		catch(SQLException e) {
			System.out.println("SQL issue in getProjectDetails()");
		}

		return projectDetails;

	}


	public boolean updateProject(HttpServletRequest request) throws ParseException {
		// TODO Auto-generated method stub
		String projectName=request.getParameter("projname");
		String projectDescription= request.getParameter("description");
		String members[]=request.getParameterValues("members");
		String endDate=request.getParameter("enddate");
		Double sprintDuration=Double.parseDouble(request.getParameter("sprintduration"));
		String memberList="";
		ConnectionProvider con =  new ConnectionProvider();
		if(members!=null) {
			for(int i=0;i<members.length;i++) {
				memberList=memberList+members[i]+",";
			}
			memberList=memberList.substring(0, memberList.length()-1);
		}
		String sql = "Update project set name=?,sprint_duration=?,end_date=?,description=?";
		if(members!=null) {
			sql = sql + ", user_list = ? ";
		}	
		sql = sql + "where id=\""+request.getParameter("id")+"\"";
		try {
			PreparedStatement pstmt = con.getCon().prepareStatement(sql);
			pstmt.setString(1,projectName);
			pstmt.setDouble(2, sprintDuration);
			pstmt.setDate(3, dateConverter(endDate));
			pstmt.setString(4,projectDescription);
			if(members!=null) {
				pstmt.setString(5,memberList);
			}
			pstmt.executeUpdate();
			pstmt.close();

		}
		catch(SQLException r) {
			System.out.println("Project updation failed during update query");
			r.printStackTrace();
			return false;
		}
		return true;

	}
}
