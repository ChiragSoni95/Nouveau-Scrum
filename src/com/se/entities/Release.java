package com.se.entities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.se.connection.ConnectionProvider;
public class Release {
		public boolean createRelease(HttpServletRequest request) throws ParseException {
			 int pID =Integer.parseInt(request.getParameter("projectid"));
			 String goals=request.getParameter("goals");
			 String comments=request.getParameter("comments");
			 String status="Inactive";
			 String startDate=request.getParameter("startdate");
			 String endDate=request.getParameter("enddate");		 
			 ConnectionProvider con =  new ConnectionProvider();
			 System.out.println("In createRelease()");

				String sql = "INSERT INTO releases (project_id, start_date, end_date, status,goals, comments,release_number) VALUES (?,?,?,?,?,?,?)";
				try {
					System.out.println("SQL entry in createRelease(): " );
					
					PreparedStatement pstmt = con.getCon().prepareStatement(sql);
					pstmt.setInt(1,pID);
					pstmt.setDate(2, Project.dateConverter(startDate));
					pstmt.setDate(3, Project.dateConverter(endDate));
					pstmt.setString(4, status);
					pstmt.setString(5,goals);
					pstmt.setString(6,comments);
					pstmt.setInt(7,getLatestReleaseNumber(pID)+1);
					pstmt.executeUpdate();
					pstmt.close();

				}
				catch(SQLException e) {
					System.out.println("SQL issue in createRelease(): " + e.getMessage());
					return false;
				}
				return true;
		}
		public boolean updateRelease(HttpServletRequest request) throws ParseException {
			// TODO Auto-generated method stub
			 int releaseid =Integer.parseInt(request.getParameter("releaseid"));
			 String goals=request.getParameter("goals");
			 String comments=request.getParameter("comments");
			 String startDate=request.getParameter("startdate");
			 String endDate=request.getParameter("enddate");
			ConnectionProvider con =  new ConnectionProvider();
			
			String sql = "UPDATE releases SET start_date=?,end_date=?,goals=?,comments=? WHERE release_id=?";
			try {
				PreparedStatement pstmt = con.getCon().prepareStatement(sql);
				pstmt.setDate(1, Project.dateConverter(startDate));
				pstmt.setDate(2, Project.dateConverter(endDate));
				pstmt.setString(3,goals);
				pstmt.setString(4,comments);
				pstmt.setInt(5,releaseid);
				
				
				pstmt.executeUpdate();
				pstmt.close();

			}
			catch(SQLException r) {
				System.out.println("Release updation failed during update query");
				r.printStackTrace();
				return false;
			}
			return true;
		}

		public static int getLatestReleaseNumber(int projectID) {
			// TODO Auto-generated method stub

			int latestReleaseNumber=0;

			ConnectionProvider con =  new ConnectionProvider();
			String sql = "select max(release_number) as latest_release_number from releases where project_id = ?";
			try {
				PreparedStatement pstmt = con.getCon().prepareStatement(sql);
				pstmt.setInt(1,projectID);

				ResultSet rst = pstmt.executeQuery();
				while (rst.next()) {
					latestReleaseNumber= rst.getInt("latest_release_number");
				}
				rst.close();
				pstmt.close();

			}
			catch(SQLException e) {
				System.out.println("SQL issue in getLatestReleaseNumber()");
			}return latestReleaseNumber;
		}
		
		 public List<String> getReleaseDetails(String releaseID) {
				List<String> releaseDetails= new ArrayList<String>();

				ConnectionProvider con =  new ConnectionProvider();
				String sql = "select goals,comments,start_date,end_date from releases where release_id=?";
				try {
					PreparedStatement pstmt = con.getCon().prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(releaseID));
					ResultSet rst = pstmt.executeQuery();
					while (rst.next()) {
						releaseDetails.add(rst.getString("goals"));
						releaseDetails.add(rst.getString("comments"));
						releaseDetails.add(rst.getDate("start_date").toString());
						releaseDetails.add(rst.getDate("end_date").toString());

					}
					rst.close();
					pstmt.close();
					
				}
				catch(SQLException e) {
					System.out.println("SQL issue in getReleaseDetails():" + e.getMessage());
				}
				
				return releaseDetails;
				
			}
			
		

	}


