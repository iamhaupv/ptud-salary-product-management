package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.connectDB;
import entity.CongDoan;
import entity.CongNhan;
import entity.LuongCongNhan;
import entity.LuongNhanVien;
import entity.NhanVien;
import entity.PhongBan;
import helper.DatabaseConnet;

public class LuongCongNhan_DAO {
	List<LuongCongNhan> dslcn;
	LuongCongNhan lcn;
	public LuongCongNhan_DAO() {
		// TODO Auto-generated constructor stub
		dslcn = new ArrayList<LuongCongNhan>();
		lcn = new LuongCongNhan();
	}
	// ham auto ma luong nhan vien
	public int getMaHd() {
		int ma = 1;
		connectDB.getInstance();
		Connection con = (Connection) connectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "select max( CONVERT(int , SUBSTRING(malcn, 6, 8))) from [dbo].[luongcongnhan]";
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
	// ham insert 
	public boolean insert(LuongCongNhan cn){
		String sql = "insert into luongcongnhan values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			Connection con = DatabaseConnet.openConnect();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cn.getMaLCN());
			stmt.setDouble(2, cn.getPhuCap());
			stmt.setDouble(3, cn.getThuong());
			stmt.setDouble(4, cn.getPhat());
			stmt.setDouble(5, cn.getBhxh());
			stmt.setInt(6, cn.getSoNgayCong());
			stmt.setString(7, cn.getCongDoan().getMaCD());
			stmt.setInt(8, cn.getSoCongDoanHoanThanh());
			stmt.setString(9, cn.getCongNhan().getMaCN());
			stmt.setDate(10, cn.getThangLuong());
			stmt.setDouble(11, cn.getTongLuong());
			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	// tao ra mot luong cong nhan
	public LuongCongNhan createCongNhan(final ResultSet rs) {
		LuongCongNhan nv = new LuongCongNhan();
		try {
			nv.setMaLCN(rs.getString("malcn"));
			nv.setPhuCap(rs.getDouble("phucap")); // 2
			nv.setThuong(rs.getDouble("thuong"));
			nv.setPhat(rs.getDouble("phat")); // 3
			nv.setBhxh(rs.getDouble("bhxh"));
			nv.setSoNgayCong(rs.getInt("songaycong"));
			CongDoan cd = new CongDoan(rs.getString("macd"));
			nv.setCongDoan(cd);
			nv.setSoCongDoanHoanThanh(rs.getInt("socongdoanhoanthanh"));
			CongNhan n = new CongNhan(rs.getString("macn"));
			nv.setCongNhan(n);
			nv.setThangLuong(rs.getDate("thangluong"));
			nv.setTongLuong(rs.getDouble("tongLuong"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 1
		return nv;
	}
	// liet ke toan bo danh sach luong cong nhan
	public List<LuongCongNhan> findAll() throws SQLException, ClassNotFoundException{
		String sql = "select * from luongcongnhan";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
			){
			try(ResultSet rs = stmt.executeQuery();){
				List<LuongCongNhan> list = new ArrayList<>();
				while(rs.next()) {
					LuongCongNhan cn = createCongNhan(rs);
					list.add(cn);
				}
				return list;
			}	
		}
	}
	// update luong cong nhan
	public boolean update(LuongCongNhan cn){
		String sql = "update luongcongnhan set phucap = ?, thuong = ?, phat = ?,"
				+ "bhxh = ? where malcn = ?";
		PreparedStatement stmt = null;
		try {
			Connection con = DatabaseConnet.openConnect();
			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, cn.getPhuCap());
			stmt.setDouble(2, cn.getThuong());
			stmt.setDouble(3, cn.getPhat());
			stmt.setDouble(4, cn.getBhxh());	
			stmt.setString(5, cn.getMaLCN());
			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	// xoa luong cong nhan bang ma
	public boolean delete(String ma){
		String sql = "delete from luongcongnhan where malcn = ?";
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
	// tim kiem bang ID
	public LuongCongNhan findByID(String id){
		connectDB.getInstance();
		String sql = "select * from luongcongnhan where malcn = ?";
		LuongCongNhan cn = new LuongCongNhan();
		try {
			Connection con = DatabaseConnet.openConnect();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1 , id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				cn = createCongNhan(rs);
			return cn;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	// ham lay tat ca luong cong nhan
	public ArrayList<LuongCongNhan> getallLuongCongNhan(){
		ArrayList<LuongCongNhan> l = new ArrayList<LuongCongNhan>();
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql = "select * from luongcongnhan";
			Statement stmt = con.createStatement();;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maLNV = rs.getString(1);
				double phuCap = rs.getDouble(2);
				double thuong = rs.getDouble(3);
				double phat = rs.getDouble(4);
				double bhxh = rs.getDouble(5);
				int snc = rs.getInt(6);
				String macd = rs.getString(7);
				CongDoan cd = new CongDoan(macd);
				int cdht = rs.getInt(8);
				String cn = rs.getString(9);
				CongNhan cnn = new CongNhan(cn);
				Date thangLuong = rs.getDate(10);
				double tongLuong = rs.getDouble(11);
				LuongCongNhan cnnn = new LuongCongNhan(maLNV,phuCap, thuong, phat, bhxh, snc, cd, cdht, cnn, thangLuong, tongLuong);
				l.add(cnnn);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return l;
	}
}
