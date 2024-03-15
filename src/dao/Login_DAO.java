package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import entity.TaiKhoan;

public class Login_DAO {
	public TaiKhoan login(String tk, String mk) {
		String url = "jdbc:sqlserver://localhost:1433;databasename=QUANLYLUONGSANPHAM; user=sa; password=123456789";

		try {
			Connection con = DriverManager.getConnection(url);
			String sql = "select taikhoan, matkhau, vaitro from taikhoan where taikhoan = ? and matkhau = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, tk);
			stmt.setString(2, mk);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				TaiKhoan t = new TaiKhoan();
				t.setTaiKhoan(tk);
				t.setVaiTro(rs.getString("vaitro"));
				return t;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
