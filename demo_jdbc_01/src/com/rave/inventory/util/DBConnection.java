package com.rave.inventory.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static final String URL="jdbc:sqlserver://localhost:1433;DatabaseName=inventorydb";
	private static final String USER_NAME="sa";
	private static final String PASSWORD="adm@123";
	private static final String DRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static Connection connection;
	public static Connection getConnection() throws Exception {
		Class driver=Class.forName(DRIVER);
		DriverManager.registerDriver((Driver) driver.newInstance());
		connection=DriverManager.getConnection(URL,USER_NAME,PASSWORD);
		return connection;
	}
}
