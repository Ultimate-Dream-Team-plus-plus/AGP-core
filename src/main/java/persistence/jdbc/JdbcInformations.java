package persistence.jdbc;

/**
 * A class used to configure the connection informations.
 * JdbcConnection will use this class in order to perform the connection.
 * 
 * @author Aldric Vitali Silvestre
 */
public class JdbcInformations {
	
	private String databaseName;
	private String port;
	private String host;
	private String username;
	private String password;

	public JdbcInformations() {
		super();
	}

	public JdbcInformations(String databaseName, String port, String host, String username, String password) {
		super();
		this.databaseName = databaseName;
		this.port = port;
		this.host = host;
		this.username = username;
		this.password = password;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
