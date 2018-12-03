package com.se.entities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import com.se.connection.ConnectionProvider;

public class Feature {
		public boolean createFeature(HttpServletRequest request) throws ParseException{
			 		 
			 String featureName=request.getParameter("featurename");
			 String featureDescription= request.getParameter("description");
			 String createdBy=request.getParameter("createdby");
			 String priority=request.getParameter("priority");
			 String efforts=request.getParameter("efforts");
			 String projectid=request.getParameter("projectid");
			 ConnectionProvider con =  new ConnectionProvider();

				String sql = "Insert into feature (name,comments,created_by,create_time,priority,effort,project_id) values (?,?,?,CURRENT_TIMESTAMP,?,?,?)";
				try {
					PreparedStatement pstmt = con.getCon().prepareStatement(sql);
					pstmt.setString(1,featureName);
					pstmt.setString(2, featureDescription);
					pstmt.setString(3,createdBy);
					pstmt.setString(4,priority);
					pstmt.setString(5,efforts);
					pstmt.setInt(6, Integer.parseInt(projectid));
					pstmt.executeUpdate();
					pstmt.close();

				}
				catch(SQLException r) {
					System.out.println("Feature creation failed during insert query");
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

		 public List<String> getFeatureDetails(String featureID) {
				List<String> featureDetails= new ArrayList<String>();

				ConnectionProvider con =  new ConnectionProvider();
				String sql = "select feature_id,name, comments, created_by,create_time,priority,effort from feature "
						+ "where feature_id=?";
				try {
					PreparedStatement pstmt = con.getCon().prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(featureID));
					ResultSet rst = pstmt.executeQuery();
					while (rst.next()) {
						featureDetails.add(rst.getString("feature_id"));
						featureDetails.add(rst.getString("name"));
						featureDetails.add(rst.getString("comments"));
						featureDetails.add(rst.getString("created_by"));
						featureDetails.add(rst.getDate("create_time").toString());
						featureDetails.add(rst.getString("priority"));
						featureDetails.add(rst.getString("effort"));
					}
					rst.close();
					pstmt.close();
					
				}
				catch(SQLException e) {
					System.out.println("SQL issue in getFeatureDetails():" + e.getMessage());
				}
				
				return featureDetails;
				
			}
		public boolean updateFeature(HttpServletRequest request) {
			// TODO Auto-generated method stub
			
			
			 String name=request.getParameter("featurename");
			 String comments= request.getParameter("description");
			 String priority=request.getParameter("priority");
			 String efforts=request.getParameter("efforts");
			 ConnectionProvider con =  new ConnectionProvider();				
			 String sql = "Update feature set name=?,comments=?,priority=?,effort=? where feature_id=\""+request.getParameter("feature")+"\"";
				try {
					PreparedStatement pstmt = con.getCon().prepareStatement(sql);
					pstmt.setString(1,name);
					pstmt.setString(2,comments);
					pstmt.setString(3,priority);
					pstmt.setString(4,efforts);


					pstmt.executeUpdate();
					pstmt.close();

				}
				catch(SQLException r) {
					System.out.println("Feature updation failed during update query");
					r.printStackTrace();
					return false;
				}
			return true;
		}


	}

