package com.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.se.connection.ConnectionProvider;


public class Admin {
	
	private String email;
	private String password;
	Connection con;
	public boolean process(String email, String password) throws Exception {

		this.email = email;
		this.password = password;
		int count = 0;
		ConnectionProvider con =  new ConnectionProvider();
		
		String sql = "select count(*) as count from users where user_name=? AND password=?";
		PreparedStatement pstmt = con.getCon().prepareStatement(sql);
		pstmt.setString(1, this.email);
		pstmt.setString(2, this.password);

		ResultSet rst = pstmt.executeQuery();
		if (rst.next()) {
			count = rst.getInt("count");
		}
		rst.close();
		pstmt.close();
		if (count == 1) {
			System.out.println("User Found.");
			return true;
		}
		return false;
	}
	
	public boolean checkUsername(String username)  {
		ConnectionProvider con =  new ConnectionProvider();
		int count=0;		
		String sql = "select count(*) as count from users where user_name=?";
		try {
			PreparedStatement pstmt = con.getCon().prepareStatement(sql);
			pstmt.setString(1,username);
	
			ResultSet rst = pstmt.executeQuery();
			if (rst.next()) {
				count = rst.getInt("count");
			}
			rst.close();
			pstmt.close();
			if (count == 1) {
				System.out.println("Duplicate User");
				return true;
			}
		}
		catch(SQLException e) {
			System.out.println("SQL issue in checkUsername()");
		}
		
		return false;		
	}
	
	

	public boolean checkEmail(String email)  {
		ConnectionProvider con =  new ConnectionProvider();
		int count=0;		
		String sql = "select count(*) as count from users where email=?";
		try {
			PreparedStatement pstmt = con.getCon().prepareStatement(sql);
			pstmt.setString(1,email);
	
			ResultSet rst = pstmt.executeQuery();
			if (rst.next()) {
				count = rst.getInt("count");
			}
			rst.close();
			pstmt.close();
			if (count == 1) {
				System.out.println("Email already registered");
				return true;
			}
		}
		catch(SQLException e) {
			System.out.println("SQL issue in checkUsername()");
		}
		
		return false;		
	}

	public boolean registerUser(String register_username, String register_password, String email, String firstname,
			String lastname, String role) {
		// TODO Auto-generated method stub
		ConnectionProvider con= new ConnectionProvider();
		String query="INSERT into users (user_name,password,firstname,lastname,email,role) values(?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.getCon().prepareStatement(query);
			pstmt.setString(1, register_username);
			pstmt.setString(2, register_password);
			pstmt.setString(3, firstname);
			pstmt.setString(4, lastname);
			pstmt.setString(5,email );
			pstmt.setString(6, role);
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("SQL issue: user not inserted");
			return false;
			
		}
		return true;		
	}
	
	
	
}
