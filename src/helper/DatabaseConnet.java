package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnet {
	// lop ket noi
	public static Connection openConnect() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String connectURL = "jdbc:sqlserver://localhost;database=quanlyluongsanpham";
		String u = "sa";
		String p = "123456789";
		Connection con = DriverManager.getConnection(connectURL, u, p);
		return con;
	}
}
