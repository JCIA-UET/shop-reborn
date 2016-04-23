package uet.jcia.shop.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * MySQL Connector for system, is singleton pattern
 * this class creates mysql connections for system
 * @author cuong
 *
 */
public class DBConnector {
	/**
	 * Information for database connection
	 * will be fetched from databaseConfig.properties
	 */
	private static String JDBC_DRIVER;
	private static String DB_URL;
	private static String USERNAME; 
	private static String PASSWORD;

	/**
	 * mysql connection session
	 */
	private static Connection con = null;
	
	private static DBConnector connector = new DBConnector();
	
	/**
	 * get properties from configuration file
	 */
	static {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream is = classLoader.getResourceAsStream("databaseConfig.properties");
			Properties p = new Properties();
			p.load(is);
			
			JDBC_DRIVER = p.getProperty("dbDriver");
			DB_URL = p.getProperty("dbUrl");
			USERNAME = p.getProperty("dbUsername");
			PASSWORD = p.getProperty("dbPassword");
			
			is.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	private DBConnector() {
		
	}
	
	public static DBConnector getInstance() { return connector; }
	
	/**
	 * create new session
	 * @return the new created session
	 */
	public Connection createConnection() {
		try {
			Class.forName(JDBC_DRIVER).newInstance();
			con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			return con;
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
}
