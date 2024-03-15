package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDB {
	public static Connection con = null;
	public static connectDB instance = new connectDB();
	public static connectDB getInstance() {
		return instance;
	}
	public static void connect() {
		String url = "jdbc:sqlserver://localhost:1433;databasename=QUANLYLUONGSANPHAM";
		String user = "sa";
		String pwd = "123456789";
		try {
			con = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void disconnect() {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static Connection getConnection() {
		return con;
	}
}
