package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import persistence.jdbc.JdbcConnection;

/**
 * A simple test for checking the connection with Jdbc
 * 
 * @author Aldric Vitali Silvestre
 */
public class JdbcConnectionTest {

	public static void main(String[] args) throws Exception {
		
		Connection connection = JdbcConnection.getConnection();
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT 1");
		result.close();
		statement.close();
	}
}
