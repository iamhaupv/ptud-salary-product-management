package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.connectDB;
import entity.CongDoan;
import entity.CongNhan;
import entity.PhanCong_CongDoan;
import entity.PhongBan;
import entity.SanPham;
import helper.DatabaseConnet;

public class CongDoan_DAO {
	public ArrayList<CongDoan> getAllCongDoan(){

		ArrayList<CongDoan> dscd = new ArrayList<CongDoan>();
		try {
			Connection con = connectDB.getInstance().getConnection();
			String sql ="select * from CongDoan";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				String maCongDoan = rs.getString(1);
				String tenCongDoan = rs.getString(2);
				double donGia = rs.getDouble(3);
				SanPham sp = new SanPham(rs.getString(4));
				int tt = rs.getInt(5);
				String t = rs.getString(6);
				CongDoan cd = new CongDoan(maCongDoan, tenCongDoan, donGia, sp, tt, t);
				dscd.add(cd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dscd;
	}
	// Thêm công đoạn
	public boolean addCongDoan(CongDoan congDoan)
	{
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement stmt = null ;
		int n = 0 ;
		try {
			stmt = con.prepareStatement("insert into "+" CongDoan values(?,?,?,?,?,?)");
			stmt.setString(1,congDoan.getMaCD());
			stmt.setString(2,congDoan.getTenCongDoan());
			stmt.setDouble(3,congDoan.getDonGia());
			stmt.setString(4,congDoan.getSanPham().getMaSP());
			stmt.setInt(5, congDoan.getThuTu());
			stmt.setString(6, congDoan.getTrangThai());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	// update CongDoan
	public boolean updateCongDoan(CongDoan congDoan)
	{
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update CongDoan set TenCongDoan =? , dongia = ?, masp = ?, thutu = ?, trangthai = ? where MaCD=? ");
			stmt.setString(1,congDoan.getTenCongDoan());
			stmt.setDouble(2,congDoan.getDonGia());
			stmt.setString(3, congDoan.getSanPham().getMaSP());
			stmt.setInt(4, congDoan.getThuTu());
			stmt.setString(5, congDoan.getTrangThai());
			stmt.setString(6,congDoan.getMaCD());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n >0;
	}

	// Xóa công đoạn
	public boolean deleteCongDoan(String maCongDoan)
	{

		Connection con = connectDB.getInstance().getConnection();
		PreparedStatement stmt = null ;
		int n = 0 ;
		try {
			stmt = con.prepareStatement("delete from CongDoan where MACD=?");
			stmt.setString(1, maCongDoan);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n >  0;
	}
	// Tìm kiếm theo lên công đoạn
	public List<CongDoan> timKiemTheoTenCongDoan (String name){
		ResultSet rs = null;
		Statement stmt = null;
		List<CongDoan> danhSachCongDoan = new ArrayList<CongDoan>();
		try {
			danhSachCongDoan.clear();
			Connection con = connectDB.getInstance().getConnection();
			String sql ="select * from CongDoan where tencongdoan like N'"+name+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				String maCongDoan = rs.getString(1);
				String tenCongDoan = rs.getString(2);
				double donGia = rs.getDouble(3);
				SanPham sp = new SanPham(rs.getString(4));
				int tt = rs.getInt(5);
				String t = rs.getString(6);
				CongDoan cd = new CongDoan(maCongDoan, tenCongDoan, donGia, sp, tt, t);
				danhSachCongDoan.add(cd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return danhSachCongDoan;
	}
	//
	public int getMaHd() {
		int ma = 1;
		connectDB.getInstance();
		Connection con = (Connection) connectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "select max( CONVERT(int , SUBSTRING(maCD,3 , 6))) from [dbo].[CongDoan]";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				return ma;
			} else {
				ma = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return ma + 1;
	}
	public CongDoan createCongDoan(final ResultSet rs){
		try {
			CongDoan nv = new CongDoan();
			nv.setMaCD(rs.getString("macd"));
			nv.setTenCongDoan(rs.getString("tencongdoan"));
			nv.setDonGia(rs.getDouble("dongia"));
			SanPham cn = new SanPham(rs.getString("masp"));
			nv.setThuTu(rs.getInt("thutu"));
			nv.setTrangThai(rs.getString("trangthai"));
			nv.setSanPham(cn);
			return nv;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	public List<CongDoan> findAll(){
		String sql = "select * from congdoan";
		List<CongDoan> list = new ArrayList<>();
		try {
			Connection con = DatabaseConnet.openConnect();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				CongDoan cn = createCongDoan(rs);
				list.add(cn);
			}	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
}
