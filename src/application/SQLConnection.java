package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
	private static final String DB_NAME = "DB_CLINIC";
	private static final String DB_USER = "sa";
	private static final String DB_PASSWORD = "1234";
	
	public static Connection getDBConnection() {
		Connection connection = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection("jdbc:sqlserver://KAITO\\MSSQLSERVER;databaseName="+DB_NAME, DB_USER,DB_PASSWORD);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return connection;
	}
}


