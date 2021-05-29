package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static String driver;
	private static String url;
	private static String user;
	private static String password;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, user, password);

		return con;
	}

	public static Connection getConnectionMariaDB() throws ClassNotFoundException, SQLException {
		driver = "org.mariadb.jdbc.Driver";
		url = "jdbc:mariadb://localhost:3306/les_db?characterEncoding=UTF-8";
		user = "root";
		password = "";
		return getConnection();
	}
	
	public static Connection getConnectionMySQL() throws ClassNotFoundException, SQLException {
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost:3306/les_db?characterEncoding=UTF-8";
		user = "root";
		password = "";
		return getConnection();
	}
}
