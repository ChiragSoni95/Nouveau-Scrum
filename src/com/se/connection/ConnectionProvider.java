package com.se.connection;
import java.sql.Connection;
import java.sql.DriverManager;  

public class ConnectionProvider {
	String DRIVER="com.mysql.jdbc.Driver";  
	String CONNECTION_URL="jdbc:mysql://127.0.0.1:3306/scrum";  
	String USERNAME="root";  
	String PASSWORD="sonichirag1995";
	private Connection con=null;  
	  
	public Connection getCon(){ 
		try{  
			System.out.println("Connection getting initialized.");
			Class.forName(DRIVER);  
			this.con=DriverManager.getConnection(CONNECTION_URL,USERNAME,PASSWORD);  
			System.out.println("Connection successfully initialized.");
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
	    return this.con;  
	}  

}
