package persistence.apiBDe.request;

import java.sql.Connection;
import java.sql.DriverManager;

class JdbcConnection {
	private static String host = "localhost";
	private static String base = "agp_db";
	private static String user = "nico";
	private static String password = "password";
	private static String url = "jdbc:mysql://" + host + "/" + base;

	/**
	 * Lazy singleton instance.
	 */
	private static Connection connection;


	public static Connection getConnection() {
		if (connection == null) {
			try {
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
				connection = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				System.err.println("Connection failed : " + e.getMessage());
			}
		}
		return connection;
	}
}
