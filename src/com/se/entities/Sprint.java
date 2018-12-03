package com.se.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.se.connection.ConnectionProvider;

public class Sprint {

	public boolean createSprint(HttpServletRequest request) throws ParseException {
		 
		 int pID =Integer.parseInt(request.getParameter("projectid"));
		 String description=request.getParameter("description");
		 String status="Inactive";
		 int rID =Integer.parseInt(request.getParameter("releaseid"));
		 String startDate=request.getParameter("startdate");
		 String endDate=request.getParameter("enddate");		 
		 ConnectionProvider con =  new ConnectionProvider();
		 System.out.println("In createSprint()");
	   	String sql = "INSERT INTO sprint (project_id, start_date, end_date, status, description, sprint_number,release_id) VALUES (?,?,?,?,?,?,?)";
			try {
				PreparedStatement pstmt = con.getCon().prepareStatement(sql);
				pstmt.setInt(1,pID);
				pstmt.setDate(2, Project.dateConverter(startDate));
				pstmt.setDate(3, Project.dateConverter(endDate));
				pstmt.setString(4, status);
				pstmt.setString(5,description);
				pstmt.setInt(6,getLatestSprintNumber(pID)+1);
				pstmt.setInt(7,rID);
				pstmt.executeUpdate();
				pstmt.close();

			}
			catch(SQLException e) {
				System.out.println("SQL issue in createSprint(): " + e.getMessage());
				return false;
			}
			return true;
	}
	

	public boolean updateSprint(HttpServletRequest request) throws ParseException {
		// TODO Auto-generated method stub
		 int sprintid =Integer.parseInt(request.getParameter("sprintid"));
		 String description=request.getParameter("description");
		 String startDate=request.getParameter("startdate");
		 String endDate=request.getParameter("enddate");
		ConnectionProvider con =  new ConnectionProvider();
		
		String sql = "UPDATE sprint SET start_date=?,end_date=?,description=? WHERE sprint_id=?";
		try {
			PreparedStatement pstmt = con.getCon().prepareStatement(sql);
			pstmt.setDate(1, Project.dateConverter(startDate));
			pstmt.setDate(2, Project.dateConverter(endDate));
			pstmt.setString(3,description);
			pstmt.setInt(4,sprintid);
			
			
			pstmt.executeUpdate();
			pstmt.close();

		}
		catch(SQLException r) {
			System.out.println("Sprint updation failed during update query");
			r.printStackTrace();
			return false;
		}
		return true;
	}

	public static int getLatestSprintNumber(int projectID) {
		// TODO Auto-generated method stub

		int latestSprintNumber=0;

		ConnectionProvider con =  new ConnectionProvider();
		String sql = "select max(sprint_number) as latest_sprint_number from sprint where project_id = ?";
		try {
			PreparedStatement pstmt = con.getCon().prepareStatement(sql);
			pstmt.setInt(1,projectID);

			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				latestSprintNumber= rst.getInt("latest_sprint_number");
			}
			rst.close();
			pstmt.close();

		}
		catch(SQLException e) {
			System.out.println("SQL issue in getLatestSprintNumber()");
		}return latestSprintNumber;
	}
	
	 public List<String> getSprintDetails(String sprintID) {
			List<String> sprintDetails= new ArrayList<String>();

			ConnectionProvider con =  new ConnectionProvider();
			String sql = "select description,start_date,end_date from sprint where sprint_id=?";
			try {
				PreparedStatement pstmt = con.getCon().prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(sprintID));
				ResultSet rst = pstmt.executeQuery();
				while (rst.next()) {

					sprintDetails.add(rst.getString("description"));
					sprintDetails.add(rst.getDate("start_date").toString());
					sprintDetails.add(rst.getDate("end_date").toString());

				}
				rst.close();
				pstmt.close();
				
			}
			catch(SQLException e) {
				System.out.println("SQL issue in getSprintDetails():" + e.getMessage());
			}
			
			return sprintDetails;
			
		}
		

}
