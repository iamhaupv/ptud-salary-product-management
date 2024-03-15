package dao;

import java.io.IOException;
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
import entity.CongNhan;
import entity.PhongBan;
import entity.SanPham;
import helper.DatabaseConnet;

public class SanPham_DAO {
	ArrayList<SanPham> dssp;
	SanPham sp;
	public SanPham_DAO() {
		// TODO Auto-generated constructor stub
		dssp = new ArrayList<SanPham>();
		sp = new SanPham();
	}
	// them 
	public boolean insert(SanPham cn) throws SQLException, ClassNotFoundException {
		String sql = "insert into sanpham values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
				){

			stmt.setString(1, cn.getMaSP());
			stmt.setString(2, cn.getTenSanPham());
			stmt.setString(3, cn.getDonViTinh());
			stmt.setInt(4, cn.getSoCongDoan());
			stmt.setString(5, cn.getChatLieu());
			stmt.setInt(6, cn.getKichThuoc());
			stmt.setString(7, cn.getMauSac());
			stmt.setDouble(8, cn.getDonGia());
			// set hinh anh 
			if(cn.getHinhAnh() != null) {
				Blob hinh = new SerialBlob(cn.getHinhAnh());
				stmt.setBlob(9, hinh);
			}
			else {
				Blob hinh = null;
				stmt.setBlob(9, hinh);
			}
			stmt.setString(10, cn.getTrangThai());
			return stmt.executeUpdate() > 0;
		}
	}
	// tao ra san pham moi
	public SanPham createSanPham(final ResultSet rs) throws SQLException {
		SanPham nv = new SanPham();
		nv.setMaSP(rs.getString("masp"));
		nv.setTenSanPham(rs.getString("tensanpham"));
		nv.setDonViTinh(rs.getString("donvitinh"));
		nv.setSoCongDoan(Integer.parseInt(rs.getString("socongdoan")));
		nv.setChatLieu(rs.getString("chatlieu"));
		nv.setKichThuoc(Integer.parseInt(rs.getString("kichthuoc")));
		nv.setMauSac(rs.getString("mausac"));
		nv.setDonGia(rs.getDouble("dongia"));
		Blob blob = rs.getBlob("hinhanh");
		if(blob != null) 
			nv.setHinhAnh(blob.getBytes(1, (int)blob.length()));
		nv.setTrangThai(rs.getString("trangthai"));
		return nv;
	}
	// tim kiem nhan vien bang ma cong nhan
	public SanPham findByID(String id) throws SQLException, ClassNotFoundException {
		connectDB.getInstance();
		String sql = "select * from sanpham where masp = ?";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
				){
			stmt.setString(1 , id);
			try(ResultSet rs = stmt.executeQuery();){
				if(rs.next()) {
					SanPham cn = createSanPham(rs);
					return cn;
				}
			}
			return null;
		}
	}
	// duyet toan bo danh sach cong nhan
	public List<SanPham> findAll() throws SQLException, ClassNotFoundException{
		String sql = "select * from sanpham";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
				){
			try(ResultSet rs = stmt.executeQuery();){
				List<SanPham> list = new ArrayList<SanPham>();
				while(rs.next()) {
					SanPham cn = createSanPham(rs);
					list.add(cn);
				}
				return list;
			}
		}
	}
	public boolean updateTrangThai(SanPham cn) throws ClassNotFoundException, SQLException {
		String sql = "update sanpham set trangthai = ? where masp = ?";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
				){

			stmt.setString(1, cn.getTrangThai());
			stmt.setString(2, cn.getMaSP());
			return stmt.executeUpdate() > 0;
		}
	}
	// cap nhat cong nhan bang ma
	public boolean update(SanPham cn) throws SQLException, IOException, ClassNotFoundException {
		String sql = "update sanpham set tensanpham = ?, donvitinh = ?, socongdoan = ?,"
				+ " chatlieu = ?, kichthuoc = ?, mausac = ?, dongia = ?, hinhanh = ?, trangthai = ? where masp = ?";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
				){
			stmt.setString(1, cn.getTenSanPham());
			stmt.setString(2, cn.getDonViTinh());
			stmt.setInt(3, cn.getSoCongDoan());
			stmt.setString(4, cn.getChatLieu());
			stmt.setInt(5, cn.getKichThuoc());
			stmt.setString(6, cn.getMauSac());
			stmt.setDouble(7, cn.getDonGia());
			// hinh anh
			if(cn.getHinhAnh() != null) {
				Blob hinh = new SerialBlob(cn.getHinhAnh());
				stmt.setBlob(8, hinh);
			}
			else {
				Blob hinh = null;
				stmt.setBlob(8, hinh);
			}
			stmt.setString(9, cn.getTrangThai());
			stmt.setString(10, cn.getMaSP());
			return stmt.executeUpdate() > 0;
		}
	}
	// xoa nhan vien bang ma
	public boolean delete(String ma) throws SQLException, ClassNotFoundException {
		String sql = "delete from sanpham where masp = ?";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
				){
			stmt.setString(1, ma);
			return stmt.executeUpdate() > 0;

		}
	}
	// tim kiem
	public List<SanPham> findSanPhamByTagName(String id){
		ResultSet rs = null;
		Statement stmt = null;
		List<SanPham> lcn = new ArrayList<SanPham>();
		try {
			String sql = "select * from sanpham where masp = '" + id + "'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCN =  rs.getString(1);
				String hoTen = rs.getString(2);
				String dvt = rs.getString(3);
				int scd = rs.getInt(4);
				String cl = rs.getString(5);
				int kt = rs.getInt(6);
				String ms = rs.getString(7);
				double dg = rs.getDouble(8);
				String tt = rs.getString(10);
				SanPham cn = new SanPham(maCN, hoTen, dvt, scd, cl, kt, ms, dg, null, tt);
				lcn.add(cn);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lcn;
	}
	public int getMaHd() {
		int ma = 1;
		connectDB.getInstance();
		Connection con = (Connection) connectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "select max( CONVERT(int , SUBSTRING(maSP,3 , 6))) from [dbo].[SanPham]";
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
