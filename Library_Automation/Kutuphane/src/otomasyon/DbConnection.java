package otomasyon;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

	private final static String dbHost = "jdbc:sqlserver://localhost:1433;databaseName = kutuphane";
	private final static String userName = "mero37";
	private final static String password = "3737";
	private final static String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	private static Connection connection = null;
	
	public static Connection getDbConnection() {
		
		try {
			if(connection == null) {
				
				Class.forName(jdbcDriver);
				connection = DriverManager.getConnection(dbHost, userName, password);
			}
			return connection;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
