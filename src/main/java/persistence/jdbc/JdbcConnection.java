package persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import business.spring.SpringIoC;

public class JdbcConnection {
	private static Connection connection;

	public static Connection getConnection() {
		if (connection == null) {
			try {
				JdbcInformations informations = SpringIoC.getBean("jdbcInformations", JdbcInformations.class);
				String user = informations.getUsername();
				String password = informations.getPassword();
				String url = createUrl(informations);

				DriverManager.registerDriver(new com.mysql.jdbc.Driver());

				connection = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				System.err.println("Connection failed : " + e.getMessage());
				// Cannot continue the program
				throw new RuntimeException(e);
			}
		}
		return connection;
	}

	private static String createUrl(JdbcInformations informations) {
		String databaseName = informations.getDatabaseName();
		String host = informations.getHost();
		String driver = "mysql";
		return "jdbc:" + driver + "://" + host + "/" + databaseName;
	}
}
