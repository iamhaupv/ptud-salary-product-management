package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import connect.connectDB;
import entity.ChamCong_NhanVien;
import entity.CongNhan;
import entity.LuongNhanVien;
import entity.NhanVien;
import entity.PhongBan;
import helper.DatabaseConnet;

public class LuongNhanVien_DAO {
	ArrayList<LuongNhanVien> dslnv;
	LuongNhanVien lnv;
	public LuongNhanVien_DAO() {
		// TODO Auto-generated constructor stub
		dslnv = new ArrayList<LuongNhanVien>();
		lnv = new LuongNhanVien();
	}
	public boolean insert(LuongNhanVien cn){
		String sql = "insert into LUONGNHANVIEN values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			Connection con = DatabaseConnet.openConnect();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cn.getMaLNV());
			stmt.setDouble(2, cn.getPhuCap());
			stmt.setDouble(3, cn.getThuong());
			stmt.setDouble(4, cn.getPhat());
			stmt.setDouble(5, cn.getBhxh());
			stmt.setInt(6, cn.getSoNgayCong());
			stmt.setDouble(7, cn.getTongLuong());
			stmt.setString(8, cn.getNhanVien().getMaNV());
			stmt.setDate(9, cn.getThangLuong());
			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public LuongNhanVien createNhanVien(final ResultSet rs) {
		LuongNhanVien nv = new LuongNhanVien();
		try {
			nv.setMaLNV(rs.getString("malnv"));
			nv.setPhuCap(rs.getDouble("phucap")); // 2
			nv.setThuong(rs.getDouble("thuong"));
			nv.setPhat(rs.getDouble("phat")); // 3
			nv.setBhxh(rs.getDouble("bhxh"));
			nv.setSoNgayCong(rs.getInt("songaycong"));
			nv.setTongLuong(rs.getDouble("tongluong"));
			NhanVien n = new NhanVien(rs.getString("manv"));
			nv.setNhanVien(n);
			nv.setThangLuong(rs.getDate("thangluong"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 1
		return nv;
	}
	public List<LuongNhanVien> findAll() throws SQLException, ClassNotFoundException{
		String sql = "select * from luongnhanvien";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
			){
			try(ResultSet rs = stmt.executeQuery();){
				List<LuongNhanVien> list = new ArrayList<>();
				while(rs.next()) {
					LuongNhanVien cn = createNhanVien(rs);
					list.add(cn);
				}
				return list;
			}	
		}
	}
	public int getMaHd() {
		int ma = 1;
		connectDB.getInstance();
		Connection con = (Connection) connectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "select max( CONVERT(int , SUBSTRING(malnv, 6, 8))) from [dbo].[luongnhanvien]";
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
	public boolean update(LuongNhanVien cn){
		String sql = "update luongnhanvien set phucap = ?, thuong = ?, phat = ?,"
				+ "bhxh = ? where malnv = ?";
		PreparedStatement stmt = null;
		try {
			Connection con = DatabaseConnet.openConnect();
			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, cn.getPhuCap());
			stmt.setDouble(2, cn.getThuong());
			stmt.setDouble(3, cn.getPhat());
			stmt.setDouble(4, cn.getBhxh());	
			stmt.setString(5, cn.getMaLNV());
			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	// xoa nhan vien bang ma
	public boolean delete(String ma){
		String sql = "delete from luongnhanvien where malnv = ?";
		try {
			Connection con = DatabaseConnet.openConnect();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			return stmt.executeUpdate() > 0;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public LuongNhanVien findByID(String id){
		connectDB.getInstance();
		String sql = "select * from luongnhanvien where malnv = ?";
		LuongNhanVien cn = new LuongNhanVien();
		try {
			Connection con = DatabaseConnet.openConnect();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1 , id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				cn = createNhanVien(rs);
			return cn;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<LuongNhanVien> getallLuongNhanVien(){
		ArrayList<LuongNhanVien> l = new ArrayList<LuongNhanVien>();
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql = "select n.MANV, hoten,  p.mapb,tenphong, malnv, phucap, thuong, phat, bhxh, songaycong, tongluong, THANGLUONG\r\n"
					+ "from nhanvien n join luongnhanvien l on n.MANV = l.manv join phongban p on p.MAPB = n.MAPB";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCN = rs.getString(1);
				String hoTen = rs.getString(2);
				String maPB = rs.getString(3);
				String tenPhong = rs.getString(4);
				String maLNV = rs.getString(5);
				double phuCap = rs.getDouble(6);
				double thuong = rs.getDouble(7);
				double phat = rs.getDouble(8);
				double bhxh = rs.getDouble(9);
				int snc = rs.getInt(10);
				double tongLuong = rs.getDouble(11);
				Date thangLuong = rs.getDate(12);
				PhongBan pb = new PhongBan(maPB, tenPhong);
				NhanVien nv = new NhanVien(maCN, hoTen, pb);
				LuongNhanVien cn = new LuongNhanVien(maLNV, phuCap, thuong, phat, bhxh, snc, tongLuong, nv, thangLuong);
				l.add(cn);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return l;
	}
	
}
