package context;
import java.sql.*;
public class DBConnection {
	public static String url = "jdbc:mysql://localhost:3306/shopkinhmat";
	public static String username = "root";
	public static String password = "";
	 
	public Connection getConnection() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, username, password);
		}catch(SQLException e) {
			System.out.println("Loi " + e.getMessage());
		}
		return null;
	}
}
