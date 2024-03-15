package dao;

import java.awt.image.BufferedImage;
import java.io.File;
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

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;

import connect.connectDB;
import entity.CongNhan;
import entity.NhanVien;
import entity.PhongBan;
import entity.SanPham;
import helper.DatabaseConnet;

public class CongNhan_DAO {
	ArrayList<CongNhan> dscn;
	CongNhan cn;
	public CongNhan_DAO() {
		// TODO Auto-generated constructor stub
		dscn = new ArrayList<CongNhan>();
		cn = new CongNhan();
	}
	public ArrayList<CongNhan> getallCongNhan(){
		ArrayList<CongNhan> l = new ArrayList<CongNhan>();
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql = "select macn = ?, hoten = ?, phai = ?, ngaysinh = ?, diachi = ?, phone = ?,"
					+ " ngaythamgiacongtac = ?, chucvu = ?, c.MAPB = ?, tenphong = ?, chuyenmon = ?, trangthai = ?\r\n"
					+ "	 from congnhan c join phongban p on c.MAPB = p.MAPB";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCN = rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String phone = rs.getString(6);
				Date ngayThamGiaCongTac = rs.getDate(7);
				PhongBan pb = new PhongBan(rs.getString(8));
				String chucVu = rs.getString(9);
				String chuyenMon = rs.getString(10);
				String trangThai = rs.getString(11);
				CongNhan cn = new CongNhan(maCN, hoTen, phai, ngaySinh, diaChi, phone, ngayThamGiaCongTac, pb, chucVu, null, chuyenMon, trangThai);
				l.add(cn);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return l;
	}
	public ArrayList<CongNhan> getAllCongNhan1(){
		ArrayList<CongNhan> danhSachCongNhan = new ArrayList<CongNhan>();
		try {
			Connection con = connectDB.getInstance().getConnection();
			String sql = "Select * from CongNhan";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				String maCN = rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String phone = rs.getString(6);
				Date ngayThamGiaCongTac = rs.getDate(7);
				PhongBan pb = new PhongBan(rs.getString(8));
				String chuyenMon = rs.getString(9);
				String trangThai = rs.getString(11);
				CongNhan cn = new CongNhan(maCN, hoTen, phai, ngaySinh, diaChi, phone, ngayThamGiaCongTac, pb, phone, null, chuyenMon, trangThai);
				danhSachCongNhan.add(cn);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return danhSachCongNhan;
	}
	//----Load ds công nhân đang làm việc----//

	public ArrayList<CongNhan> getAllCongNhanDangLamViec(){
		ArrayList<CongNhan> danhSachCongNhan = new ArrayList<CongNhan>();
		try {
			Connection con = connectDB.getInstance().getConnection();
			String sql = "Select * from CongNhan join PhongBan on congnhan.mapb = phongban.mapb  where tinhtrang like N'Đang làm việc'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				String maCN = rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String phone = rs.getString(6);
				Date ngayThamGiaCongTac = rs.getDate(7);

				PhongBan pb = new PhongBan(rs.getString(8),rs.getString(15));
				String chuyenMon = rs.getString(9);
				String trangThai = rs.getString(11);
				String tinhTrang = rs.getString(13);

				CongNhan cn = new CongNhan(maCN, hoTen, phai, ngaySinh, diaChi, phone, ngayThamGiaCongTac, pb, phone, null, chuyenMon, trangThai,tinhTrang);
				danhSachCongNhan.add(cn);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return danhSachCongNhan;
	}

	// them cong nhan
	public boolean insert(CongNhan cn){
		String sql = "insert into congnhan values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		try {
			Connection con = DatabaseConnet.openConnect();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cn.getMaCN());
			stmt.setString(2, cn.getHoTen());
			stmt.setInt(3, cn.isPhai());
			stmt.setDate(4, cn.getNgaySinh());
			stmt.setString(5, cn.getDiaChi());
			stmt.setString(6, cn.getPhone());
			stmt.setDate(7, cn.getNgayThamGiaCongTac());
			stmt.setString(8, cn.getPhong().getMaPB());
			stmt.setString(9, cn.getChucVu());
			// set hinh anh 
			if(cn.getHinhAnh() != null) {
				Blob hinh = new SerialBlob(cn.getHinhAnh());
				stmt.setBlob(10, hinh);
			}
			else {
				Blob hinh = null;
				stmt.setBlob(10, hinh);
			}
			stmt.setString(11, cn.getChuyenMon());
			stmt.setString(12, cn.getTrangThai());
			stmt.setString(13, cn.getTinhTrang());
			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	// tao ra mot cong nhan moi
	public CongNhan createCongNhan(final ResultSet rs){
		try {
			CongNhan nv = new CongNhan();
			nv.setMaCN(rs.getString("macn"));
			nv.setHoTen(rs.getString("hoten"));
			nv.setPhai(rs.getInt("phai"));
			nv.setNgaySinh(rs.getDate("ngaysinh"));
			nv.setDiaChi(rs.getString("diachi"));
			nv.setPhone(rs.getString("phone"));
			nv.setNgayThamGiaCongTac(rs.getDate("ngaythamgiacongtac"));
			Blob blob = rs.getBlob("hinhanh");
			String m = "mapb";
			PhongBan p = new PhongBan(rs.getString(m));
			nv.setPhong(p);
			nv.setChucVu(rs.getString("chucvu"));
			if(blob != null) 
				nv.setHinhAnh(blob.getBytes(1, (int)blob.length()));
			nv.setChuyenMon(rs.getString("chuyenmon"));
			nv.setTrangThai(rs.getString("trangthai"));
			return nv;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	// tim kiem nhan vien bang ma cong nhan
	public CongNhan findByID(String id){
		connectDB.getInstance();
		String sql = "select * from congnhan where maCN = ?";
		CongNhan cn = new CongNhan();
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
	// duyet toan bo danh sach cong nhan
	public List<CongNhan> findAll(){
		String sql = "select * from congnhan";
		List<CongNhan> list = new ArrayList<>();
		try {
			Connection con = DatabaseConnet.openConnect();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				CongNhan cn = createCongNhan(rs);
				list.add(cn);
			}	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	// cap nhat cong nhan bang ma
	public boolean update(CongNhan cn){
		String sql = "update congnhan set hoten = ?, phai = ?, ngaysinh = ?,"
				+ "diachi = ?, phone = ?, ngaythamgiacongtac = ?, mapb = ?, chucvu =?, hinhanh = ?, chuyenmon=?, trangthai = ? where macn = ?";
		PreparedStatement stmt = null;
		try {
			Connection con = DatabaseConnet.openConnect();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, cn.getHoTen());
			stmt.setInt(2, cn.isPhai());
			stmt.setDate(3, cn.getNgaySinh());
			stmt.setString(4, cn.getDiaChi());
			stmt.setString(5, cn.getPhone());
			stmt.setDate(6, cn.getNgayThamGiaCongTac());
			stmt.setString(7, cn.getPhong().getMaPB());
			stmt.setString(8, cn.getChucVu());
			if(cn.getHinhAnh() != null) {
				Blob hinh = new SerialBlob(cn.getHinhAnh());
				stmt.setBlob(9, hinh);
			}
			else {
				Blob hinh = null;
				stmt.setBlob(9, hinh);
			}
			stmt.setString(10, cn.getChuyenMon());
			stmt.setString(11, cn.getTrangThai());
			stmt.setString(12, cn.getMaCN());
			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	// xoa nhan vien bang ma
	public boolean delete(String ma){
		String sql = "delete from congnhan where macn = ?";
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
	// tim kiem
	public List<CongNhan> findCongNhanByTagName(String id){
		ResultSet rs = null;
		Statement stmt = null;
		List<CongNhan> lcn = new ArrayList<CongNhan>();
		try {
			String sql = "select * from congnhan where phone = '" + id + "'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCN =  rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String phone = rs.getString(6);
				Date ngayThamGiaCongTac = rs.getDate(7);
				String maPhong = rs.getString(8);
				PhongBan pb = new PhongBan(maPhong);
				String chucVu = rs.getString(9);
				String cm = rs.getString(10);
				String tt = rs.getString(11);
				CongNhan cn = new CongNhan(maCN, hoTen, phai, ngaySinh, diaChi, phone, ngayThamGiaCongTac, pb, chucVu, null, cm, tt);
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
			String sql = "select max( CONVERT(int , SUBSTRING(maCN,3 , 6))) from [dbo].[CongNhan]";
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
	public boolean updateTrangThai(CongNhan cn) throws ClassNotFoundException, SQLException {
		String sql = "update congnhan set trangthai = ? where macn = ?";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
				){

			stmt.setString(1, cn.getTrangThai());
			stmt.setString(2, cn.getMaCN());
			return stmt.executeUpdate() > 0;
		}
	}
	
	public ArrayList<CongNhan> getAllCongNhan(){
		ArrayList<CongNhan> danhSachCongNhan = new ArrayList<CongNhan>();
		try {
			Connection con = connectDB.getInstance().getConnection();
			String sql = "Select * from CongNhan join phongban on  congnhan.MAPB = PHONGBAN.MAPB  ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				String maCN = rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String phone = rs.getString(6);
				Date ngayThamGiaCongTac = rs.getDate(7);
				PhongBan pb = new PhongBan(rs.getString(8),rs.getString(15));
				String tinhtrang = rs.getString(13);
				
				CongNhan cn = new CongNhan(maCN, hoTen, phai, ngaySinh, diaChi, phone, ngayThamGiaCongTac, pb, tinhtrang);
				danhSachCongNhan.add(cn);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return danhSachCongNhan;
	}
	public List<CongNhan> findCongNhanBySoDienThoai(String sdt){
		ResultSet rs = null;
		Statement stmt = null;
		List<CongNhan> danhSachCongNhan = new ArrayList<CongNhan>();

		try {
			String sql = " select * from congnhan join phongban on  congnhan.MAPB = PHONGBAN.MAPB where phone like N'"+sdt+"'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCN =  rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String phone = rs.getString(6);
				Date ngayThamGiaCongTac = rs.getDate(7);
				PhongBan pb = new PhongBan(rs.getString(14),rs.getString(15));
				String tinhtrang = rs.getString(13);
				CongNhan cn = new CongNhan(maCN, hoTen, phai, ngaySinh, diaChi, phone, ngayThamGiaCongTac, pb, tinhtrang);
				danhSachCongNhan.add(cn);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return danhSachCongNhan;
	}
	public List<CongNhan> findCongNhanByDiaChi(String diachi){
		ResultSet rs = null;
		Statement stmt = null;
		List<CongNhan> danhSachCongNhan = new ArrayList<CongNhan>();

		try {
			String sql = " select * from congnhan join phongban on  congnhan.MAPB = PHONGBAN.MAPB where diachi like N'%"+diachi+"%'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCN =  rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String phone = rs.getString(6);
				Date ngayThamGiaCongTac = rs.getDate(7);
				PhongBan pb = new PhongBan(rs.getString(14),rs.getString(15));
				String tinhtrang = rs.getString(13);
				CongNhan cn = new CongNhan(maCN, hoTen, phai, ngaySinh, diaChi, phone, ngayThamGiaCongTac, pb, tinhtrang);
				danhSachCongNhan.add(cn);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return danhSachCongNhan;
	}
	public List<CongNhan> findCongNhanByName(String name){
		ResultSet rs = null;
		Statement stmt = null;
		List<CongNhan> danhSachCongNhan = new ArrayList<CongNhan>();

		try {
			String sql = " select * from congnhan join phongban on  congnhan.MAPB = PHONGBAN.MAPB where hoten like N'%"+name+"%'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCN =  rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String phone = rs.getString(6);
				Date ngayThamGiaCongTac = rs.getDate(7);
				PhongBan pb = new PhongBan(rs.getString(14),rs.getString(15));
				String tinhtrang = rs.getString(13);
				CongNhan cn = new CongNhan(maCN, hoTen, phai, ngaySinh, diaChi, phone, ngayThamGiaCongTac, pb, tinhtrang);
				danhSachCongNhan.add(cn);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return danhSachCongNhan;
	}
	public List<CongNhan> findCongNhanByMa(String maCongNhan){
		ResultSet rs = null;
		Statement stmt = null;
		List<CongNhan> danhSachCongNhan = new ArrayList<CongNhan>();

		try {
			String sql = " select * from congnhan join phongban on  congnhan.MAPB = PHONGBAN.MAPB where maCN like N'"+maCongNhan+"'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCN =  rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String phone = rs.getString(6);
				Date ngayThamGiaCongTac = rs.getDate(7);
				PhongBan pb = new PhongBan(rs.getString(14),rs.getString(15));
				String tinhtrang = rs.getString(13);
				CongNhan cn = new CongNhan(maCN, hoTen, phai, ngaySinh, diaChi, phone, ngayThamGiaCongTac, pb, tinhtrang);
				danhSachCongNhan.add(cn);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return danhSachCongNhan;
	}
}
