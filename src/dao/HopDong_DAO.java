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
import entity.HopDong;
import entity.PhongBan;
import entity.SanPham;
import helper.DatabaseConnet;

public class HopDong_DAO {
	// Pham Van Hau
	ArrayList<HopDong> dshd;
	HopDong hd;
	public HopDong_DAO() {
		// TODO Auto-generated constructor stub
		dshd = new ArrayList<HopDong>();
		hd = new HopDong();
	}
	public ArrayList<HopDong> getallHopDong(){
		ArrayList<HopDong> ds = new ArrayList<HopDong>();
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql = "select * from hopdong";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maHD = rs.getString(1);
				String tenHopDong = rs.getString(2);
				Date ngayBatDau = rs.getDate(3);
				Date ngayKetThuc = rs.getDate(4);
				int soLuong = rs.getInt(5);
				float donGiaSanPham = rs.getFloat(6);
				SanPham sp = new SanPham(rs.getString(7)) ;
				String kh = rs.getString(8);
				double tt = rs.getDouble(9);
				String phone = rs.getString(10);
				String trangThai = rs.getString(11);
				HopDong h = new HopDong(maHD, tenHopDong, ngayBatDau, ngayKetThuc, soLuong, donGiaSanPham, sp, kh, phone, tt, trangThai);
				ds.add(h);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}
	// them hop dong
	public boolean insert(HopDong cn) throws SQLException, ClassNotFoundException {
		String sql = "insert into hopdong values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
				){

			stmt.setString(1, cn.getMaHD());
			stmt.setString(2, cn.getTenHopDong());
			stmt.setDate(3, cn.getNgayBatDau());
			stmt.setDate(4, cn.getNgayKetThuc());
			stmt.setInt(5, cn.getSoLuong());
			stmt.setDouble(6, cn.getDonGiaSanPham());
			stmt.setString(7, cn.getSanPham().getMaSP());
			stmt.setString(8, cn.getKhachHang());
			stmt.setString(9, cn.getPhone());
			stmt.setDouble(10, cn.getTongTien());
			stmt.setString(11, cn.getTrangThai());
			return stmt.executeUpdate() > 0;
		}
	}
	// tao ra mot hop dong moi
	public HopDong createHopDong(final ResultSet rs) throws SQLException {
		HopDong nv = new HopDong();
		nv.setMaHD(rs.getString("mahd"));
		nv.setTenHopDong(rs.getString("tenhopdong"));
		nv.setNgayBatDau(rs.getDate("ngaybatdau"));
		nv.setNgayKetThuc(rs.getDate("ngayketthuc"));
		nv.setSoLuong(rs.getInt("soluong"));
		nv.setDonGiaSanPham(rs.getDouble("dogiasanpham"));
		SanPham sp = new SanPham(rs.getString("masp"));
		nv.setSanPham(sp);
		nv.setKhachHang(rs.getString("khachhang"));
		nv.setPhone(rs.getString("phone"));
		nv.setTongTien(rs.getDouble("TongTien"));
		nv.setTrangThai(rs.getString("trangThai"));
		return nv;
	}
	// tim kiem nhan vien bang ma cong nhan
	public HopDong findByID(String id) throws SQLException, ClassNotFoundException {
		connectDB.getInstance();
		String sql = "select * from hopdong where mahd = ?";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
				){
			stmt.setString(1 , id);
			try(ResultSet rs = stmt.executeQuery();){
				if(rs.next()) {
					HopDong cn = createHopDong(rs);
					return cn;
				}
			}
			return null;
		}
	}
	// duyet toan bo danh sach cong nhan
	public List<HopDong> findAll() throws SQLException, ClassNotFoundException{
		String sql = "select * from hopdong";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
				){
			try(ResultSet rs = stmt.executeQuery();){
				List<HopDong> list = new ArrayList<>();
				while(rs.next()) {
					HopDong cn = createHopDong(rs);
					list.add(cn);
				}
				return list;
			}
		}
	}
	// cap nhat cong nhan bang ma
	public boolean update(HopDong cn) throws SQLException, IOException, ClassNotFoundException {
		String sql = "update hopdong set tenhopdong = ?, ngaybatdau = ?, ngayketthuc = ?,"
				+ "soluong = ?, dogiasanpham = ?, masp = ?, khachhang = ?, phone = ?, tongTien = ?, trangthai = ? where mahd = ?";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
				){
			stmt.setString(1, cn.getTenHopDong());
			stmt.setDate(2, cn.getNgayBatDau());
			stmt.setDate(3, cn.getNgayKetThuc());
			stmt.setInt(4, cn.getSoLuong());
			stmt.setDouble(5, cn.getDonGiaSanPham());
			stmt.setString(6, cn.getSanPham().getMaSP());
			stmt.setString(7, cn.getKhachHang());
			stmt.setString(8, cn.getPhone());
			stmt.setDouble(9, cn.getTongTien());
			stmt.setString(10, cn.getTrangThai());
			stmt.setString(11, cn.getMaHD());
			return stmt.executeUpdate() > 0;
		}
	}
	// xoa nhan vien bang ma
	public boolean delete(String ma) throws SQLException, ClassNotFoundException {
		String sql = "delete from hopdong where mahd = ?";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
				){
			stmt.setString(1, ma);
			return stmt.executeUpdate() > 0;

		}
	}
	// tim kiem
	public List<HopDong> findHopDongByTagName(String id){
		ResultSet rs = null;
		Statement stmt = null;
		List<HopDong> lcn = new ArrayList<HopDong>();
		try {
			String sql = "select * from hopdong where mahd = '" + id + "'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCN =  rs.getString(1);
				String hoTen = rs.getString(2);
				Date ngaySinh = rs.getDate(3);
				Date nkt = rs.getDate(4);
				int soLuong = rs.getInt(5);
				double phone = rs.getDouble(6);
				String sp = rs.getString(7);
				SanPham maPhong = new SanPham(sp);
				String kh = rs.getString(8);
				String phoen = rs.getString(9);
				double tt = rs.getDouble(10);
				String trangThai = rs.getString(11);
				HopDong cn = new HopDong(maCN, hoTen, ngaySinh, nkt, soLuong, phone, maPhong, kh, phoen, tt, trangThai);
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
			String sql = "select max(CONVERT(int , SUBSTRING(maHD,3 , 6))) from [dbo].[HopDong]";
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
	//tim bang tong tien
	public List<HopDong> findHopDongByTongTien(String tT){
		ResultSet rs = null;
		Statement stmt = null;
		List<HopDong> lcn = new ArrayList<HopDong>();
		try {
			String sql = "select * from hopdong  join sanpham on hopdong.masp = sanpham.masp  where tongtien like N'%"+tT+"%'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCN =  rs.getString(1);
				String hoTen = rs.getString(2);
				Date ngaySinh = rs.getDate(3);
				Date nkt = rs.getDate(4);
				int soLuong = rs.getInt(5);
				double phone = rs.getDouble(6);
				String sp = rs.getString(7);
				SanPham maPhong = new SanPham(sp,rs.getString(12));
				String kh = rs.getString(8);
				String phoen = rs.getString(9);
				double tt = rs.getDouble(10);
				HopDong hd = new HopDong(maCN, hoTen, ngaySinh, nkt, soLuong, phone, maPhong, kh, phoen, tt, null);
				lcn.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lcn;
	}
	//tim bang so dien thoai
	public List<HopDong> findHopDongBySoDienThoai(String sdt){
		ResultSet rs = null;
		Statement stmt = null;
		List<HopDong> lcn = new ArrayList<HopDong>();
		try {
			String sql = "select * from hopdong join sanpham on hopdong.masp = sanpham.masp where phone like N'%"+sdt+"%'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCN =  rs.getString(1);
				String hoTen = rs.getString(2);
				Date ngaySinh = rs.getDate(3);
				Date nkt = rs.getDate(4);
				int soLuong = rs.getInt(5);
				double phone = rs.getDouble(6);
				String sp = rs.getString(7);
				SanPham maPhong = new SanPham(sp,rs.getString(12));
				String kh = rs.getString(8);
				String phoen = rs.getString(9);
				double tt = rs.getDouble(10);
				HopDong hd = new HopDong(maCN, hoTen, ngaySinh, nkt, soLuong, phone, maPhong, kh, phoen, tt, null);
				lcn.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lcn;
	}
	//tim bang khach hang
	public List<HopDong> findHopDongByKhachHang(String kh1){
		ResultSet rs = null;
		Statement stmt = null;
		List<HopDong> lcn = new ArrayList<HopDong>();
		try {
			String sql = "select * from hopdong join sanpham on hopdong.masp = sanpham.masp where khachhang like N'%"+kh1+"%'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCN =  rs.getString(1);
				String hoTen = rs.getString(2);
				Date ngaySinh = rs.getDate(3);
				Date nkt = rs.getDate(4);
				int soLuong = rs.getInt(5);
				double phone = rs.getDouble(6);
				String sp = rs.getString(7);
				SanPham maPhong = new SanPham(sp,rs.getString(12));
				String kh = rs.getString(8);
				String phoen = rs.getString(9);
				double tt = rs.getDouble(10);
				HopDong hd = new HopDong(maCN, hoTen, ngaySinh, nkt, soLuong, phone, maPhong, kh, phoen, tt, null);
				lcn.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lcn;
	}
	public List<HopDong> findHopDongByMaSanPham(String maSP){
		ResultSet rs = null;
		Statement stmt = null;
		List<HopDong> lcn = new ArrayList<HopDong>();
		try {
			String sql = "select * from hopdong join sanpham on hopdong.masp = sanpham.masp where masp like N'%"+maSP+"%'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCN =  rs.getString(1);
				String hoTen = rs.getString(2);
				Date ngaySinh = rs.getDate(3);
				Date nkt = rs.getDate(4);
				int soLuong = rs.getInt(5);
				double phone = rs.getDouble(6);
				String sp = rs.getString(7);
				SanPham maPhong = new SanPham(sp,rs.getString(12));
				String kh = rs.getString(8);
				String phoen = rs.getString(9);
				double tt = rs.getDouble(10);
				HopDong hd = new HopDong(maCN, hoTen, ngaySinh, nkt, soLuong, phone, maPhong, kh, phoen, tt, null);
				lcn.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lcn;
	}
	//tim bang don gia san pham
	public List<HopDong> findHopDongByDonGiaSanPham(String dgsp){
		ResultSet rs = null;
		Statement stmt = null;
		List<HopDong> lcn = new ArrayList<HopDong>();
		try {
			String sql = "select * from hopdong join sanpham on hopdong.masp = sanpham.masp where dogiasanpham like N'%"+dgsp+"%'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCN =  rs.getString(1);
				String hoTen = rs.getString(2);
				Date ngaySinh = rs.getDate(3);
				Date nkt = rs.getDate(4);
				int soLuong = rs.getInt(5);
				double phone = rs.getDouble(6);
				String sp = rs.getString(7);
				SanPham maPhong = new SanPham(sp,rs.getString(12));
				String kh = rs.getString(8);
				String phoen = rs.getString(9);
				double tt = rs.getDouble(10);
				HopDong hd = new HopDong(maCN, hoTen, ngaySinh, nkt, soLuong, phone, maPhong, kh, phoen, tt, null);
				lcn.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lcn;
	}
	public List<HopDong> findHopDongBySoLuong(String sl){
		ResultSet rs = null;
		Statement stmt = null;
		List<HopDong> lcn = new ArrayList<HopDong>();
		try {
			String sql = "select * from hopdong join sanpham on hopdong.masp = sanpham.masp where soluong like N'%"+sl+"%'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCN =  rs.getString(1);
				String hoTen = rs.getString(2);
				Date ngaySinh = rs.getDate(3);
				Date nkt = rs.getDate(4);
				int soLuong = rs.getInt(5);
				double phone = rs.getDouble(6);
				String sp = rs.getString(7);
				SanPham maPhong = new SanPham(sp,rs.getString(12));
				String kh = rs.getString(8);
				String phoen = rs.getString(9);
				double tt = rs.getDouble(10);
				HopDong hd = new HopDong(maCN, hoTen, ngaySinh, nkt, soLuong, phone, maPhong, kh, phoen, tt, null);
				lcn.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lcn;
	}
	//tim bang ma hop dong
	public List<HopDong> findHopDongByMaHopDong(String maHopDong1){
		ResultSet rs = null;
		Statement stmt = null;
		List<HopDong> lcn = new ArrayList<HopDong>();
		try {
			String sql = "select * from hopdong join sanpham on hopdong.masp = sanpham.masp where mahd like N'"+maHopDong1+"'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCN =  rs.getString(1);
				String hoTen = rs.getString(2);
				Date ngaySinh = rs.getDate(3);
				Date nkt = rs.getDate(4);
				int soLuong = rs.getInt(5);
				double phone = rs.getDouble(6);
				String sp = rs.getString(7);
				SanPham maPhong = new SanPham(sp,rs.getString(12));
				String kh = rs.getString(8);
				String phoen = rs.getString(9);
				double tt = rs.getDouble(10);
				HopDong hd = new HopDong(maCN, hoTen, ngaySinh, nkt, soLuong, phone, maPhong, kh, phoen, tt, null);
				lcn.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lcn;
	}
	// tim kiem bang ten hop dong
	public List<HopDong> findHopDongByTenHopDong(String id){
		ResultSet rs = null;
		Statement stmt = null;
		List<HopDong> lcn = new ArrayList<HopDong>();
		try {
			String sql = "select * from hopdong join sanpham on hopdong.masp = sanpham.masp where tenhopdong like N'%"+id+"%'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCN =  rs.getString(1);
				String hoTen = rs.getString(2);
				Date ngaySinh = rs.getDate(3);
				Date nkt = rs.getDate(4);
				int soLuong = rs.getInt(5);
				double phone = rs.getDouble(6);
				String sp = rs.getString(7);
				SanPham maPhong = new SanPham(sp,rs.getString(12));
				String kh = rs.getString(8);
				String phoen = rs.getString(9);
				double tt = rs.getDouble(10);
				HopDong hd = new HopDong(maCN, hoTen, ngaySinh, nkt, soLuong, phone, maPhong, kh, phoen, tt, null);
				lcn.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lcn;
	}
	
	
	public ArrayList<HopDong> getAllHopDong(){
		ArrayList<HopDong> dshd = new ArrayList<HopDong>();
		try {
			dshd.clear();
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql = "select * from hopdong join sanpham on hopdong.masp = sanpham.masp ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maHD = rs.getString(1);
				String tenHopDong = rs.getString(2);
				Date ngayBatDau = rs.getDate(3);
				Date ngayKetThuc = rs.getDate(4);
				int soLuong = rs.getInt(5);
				float donGiaSanPham = rs.getFloat(6);
				SanPham sp = new SanPham(rs.getString(11),rs.getString(12)) ;
				String kh = rs.getString(8);
				double tt = rs.getDouble(10);
				String phone = rs.getString(9);
				HopDong hd = new HopDong(maHD,tenHopDong,ngayBatDau,ngayKetThuc,soLuong,donGiaSanPham,sp,kh,phone,tt);
				dshd.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dshd;
	}
}
