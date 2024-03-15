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

import entity.ChamCong_NhanVien;
import entity.CongDoan;
import entity.CongNhan;
import entity.HopDong;
import entity.NhanVien;
import entity.PhanCong_CongDoan;
import entity.PhongBan;
import entity.SanPham;
import helper.DatabaseConnet;

public class ChamCong_NhanVien_DAO {
  
	ChamCong_NhanVien congNhanVien;
	ArrayList<ChamCong_NhanVien> dsChamCongNhanVien ;
	
	public ChamCong_NhanVien_DAO()
	{
		congNhanVien = new ChamCong_NhanVien();
		dsChamCongNhanVien = new ArrayList<ChamCong_NhanVien>();
	}
	
	public ArrayList<ChamCong_NhanVien> getAllBangChamCong()
	{
		ArrayList<ChamCong_NhanVien> l = new ArrayList<>();
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql ="SELECT CHAMCONG_NHANVIEN.MACCNV ,"
					+ " CHAMCONG_NHANVIEN.ngaychamcong,"
					+ "NHANVIEN.MANV,NHANVIEN.HOTEN,"
					+ "NHANVIEN.PHONE,CHAMCONG_NHANVIEN.DIEMDANH,"
					+ "CHAMCONG_NHANVIEN.PHEP\r\n"
					+ "\r\n"
					+ "	FROM CHAMCONG_NHANVIEN JOIN NHANVIEN ON CHAMCONG_NHANVIEN.MANV = NHANVIEN.MANV";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				String maChamCong = rs.getString(1);
				Date ngayChamCong = rs.getDate(2);
				
				NhanVien nhanVien = new NhanVien(rs.getString(3),rs.getString(4),rs.getString(5));
				boolean diemDanh = rs.getBoolean(6);
				boolean phep = rs.getBoolean(7);
				ChamCong_NhanVien congNhanVien = new ChamCong_NhanVien(maChamCong, diemDanh, phep, ngayChamCong, nhanVien);
				l.add(congNhanVien);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return l;
	}
	
   public boolean chamCongNhanVien(ChamCong_NhanVien congNhanVien)
   {
	   connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement stmt = null ;
		int n = 0 ;
		try {
			stmt =con.prepareStatement("insert into chamcong_nhanvien values (?,?,?,?,?)");
			stmt.setString(1,congNhanVien.getMaCCNV());
			stmt.setBoolean(2, congNhanVien.isDiemDanh());
			stmt.setDate(4, congNhanVien.getNgayChamCong());
			
			stmt.setString(5,congNhanVien.getNhanVien().getMaNV());
			
			stmt.setBoolean(3, congNhanVien.isPhep());
			n = stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
   }
   public boolean xoaBangChamCong(String maChamCong)
   {
  	 Connection con = connectDB.getInstance().getConnection();
  	   PreparedStatement stmt = null ;
  	   int n = 0 ;
  	   try {
  			stmt = con.prepareStatement("delete from chamcong_nhanvien where MaCCNV=?");
  			stmt.setString(1, maChamCong);
  			n = stmt.executeUpdate();
  		} catch (Exception e) {
  			// TODO: handle exception
  			e.printStackTrace();
  		}
  		   return n >  0;
   }
   public List<ChamCong_NhanVien> tinhSoNgayCong(){
	   List<ChamCong_NhanVien> l = new ArrayList<ChamCong_NhanVien>();
	   try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql ="select count(maccnv) as songaycong, manv from CHAMCONG_NHANVIEN where month(ngayhientai) = 11 and year(ngayhientai) = 2022 group by manv \r\n"
					+ "";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				String maChamCong = rs.getString(1);
				String mavn = rs.getString(2);
				Date ngayChamCong = rs.getDate(3);
				Date d = rs.getDate(4);
				String m = rs.getString(5);
				
				NhanVien nhanVien = new NhanVien(rs.getString(mavn));
				ChamCong_NhanVien ccnv = new ChamCong_NhanVien(maChamCong, nhanVien, ngayChamCong);
				l.add(ccnv);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return l;
   }
   public int getMaHd() {
		int ma = 1;
		connectDB.getInstance();
		Connection con = (Connection) connectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "select max( CONVERT(int , SUBSTRING(maCCNV,3 , 6))) from [dbo].[ChamCong_NhanVien]";
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
}
