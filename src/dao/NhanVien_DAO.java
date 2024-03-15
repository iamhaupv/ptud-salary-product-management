package dao;

import java.io.IOException;
import java.lang.reflect.Array;
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

import org.codehaus.groovy.tools.shell.commands.ClearCommand;

import connect.connectDB;
import entity.CongNhan;
import entity.NhanVien;
import entity.PhongBan;
import helper.DatabaseConnet;

public class NhanVien_DAO {
	ArrayList<NhanVien> dsnv;
	NhanVien nv;
	public NhanVien_DAO() {
		// TODO Auto-generated constructor stub
		dsnv = new ArrayList<NhanVien>();
		nv = new NhanVien();
	}
	//
	public boolean addNhanVien(NhanVien nv) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into nhanvien(manv, hoten, phai, ngaysinh, diachi, email, phone, ngaythamgiacongtac, trinhdochuyenmon, kinhnghiem, chucvu, mapb, hinhanh) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
			stmt.setString(1, nv.getMaNV());
			stmt.setString(2, nv.getHoTen());
			//			stmt.setBoolean(3, nv.isPhai());
			stmt.setDate(4, nv.getNgaySinh());
			stmt.setString(5, nv.getDiaChi());
			stmt.setString(6, nv.getEmail());
			stmt.setString(7, nv.getPhone());
			stmt.setDate(8, nv.getNgayThamGiaCongTac());
			stmt.setString(9, nv.getTrinhDoChuyenMon());
			stmt.setString(10, nv.getKinhNghiem());
			stmt.setString(11, nv.getChucVu());
			stmt.setString(12, nv.getPhong().getMaPB());
			if(nv.getHinhAnh() != null) {
				Blob hinh = new SerialBlob(nv.getHinhAnh());
				stmt.setBlob(13, hinh);
			}
			else {
				Blob hinh = null;
				stmt.setBlob(13, hinh);
			}
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); 
		}
		return n > 0;
	}
	// them nhan vien
	public boolean insert(NhanVien cn) throws SQLException, ClassNotFoundException {
		String sql = "insert into nhanvien values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
				){

			stmt.setString(1, cn.getMaNV());
			stmt.setString(2, cn.getHoTen());
			stmt.setInt(3, cn.isPhai());
			stmt.setDate(4, cn.getNgaySinh());
			stmt.setString(5, cn.getDiaChi());
			stmt.setString(6, cn.getEmail());
			stmt.setString(7, cn.getPhone());
			stmt.setDate(8, cn.getNgayThamGiaCongTac());
			stmt.setString(9, cn.getTrinhDoChuyenMon());
			stmt.setString(10, cn.getKinhNghiem());
			stmt.setString(11, cn.getChucVu());
			stmt.setString(12, cn.getPhong().getMaPB());
			// set hinh anh 
			if(cn.getHinhAnh() != null) {
				Blob hinh = new SerialBlob(cn.getHinhAnh());
				stmt.setBlob(13, hinh);
			}
			else {
				Blob hinh = null;
				stmt.setBlob(13, hinh);
			}
			stmt.setString(14, cn.getTrangThai());
			return stmt.executeUpdate() > 0;
		}
	}
	//tao ra mot nhan vien moi
	public NhanVien createNhanVien(final ResultSet rs) throws SQLException {
		NhanVien nv = new NhanVien();
		nv.setMaNV(rs.getString("manv")); // 1
		nv.setHoTen(rs.getString("hoten")); // 2
		nv.setPhai(rs.getInt("phai"));
		nv.setNgaySinh(rs.getDate("ngaysinh")); // 3
		nv.setDiaChi(rs.getString("diachi"));
		nv.setEmail(rs.getString("email"));
		nv.setPhone(rs.getString("phone"));
		nv.setNgayThamGiaCongTac(rs.getDate("ngaythamgiacongtac"));
		nv.setTrinhDoChuyenMon(rs.getString("trinhdochuyenmon"));
		nv.setKinhNghiem(rs.getString("kinhnghiem"));
		nv.setChucVu(rs.getString("chucvu"));
		Blob blob = rs.getBlob("hinhanh");
		String m = "mapb";
		PhongBan p = new PhongBan(rs.getString(m));
		nv.setPhong(p);
		if(blob != null) 
			nv.setHinhAnh(blob.getBytes(1, (int)blob.length()));
		nv.setTrangThai(rs.getString("trangthai"));
		return nv;
	}
	// tim nhan vien bang ma nhan vien
	public NhanVien findByID(String id) throws SQLException, ClassNotFoundException {
		connectDB.getInstance();
		String sql = "select * from nhanvien where manv = ?";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
				){
			stmt.setString(1 , id);
			try(ResultSet rs = stmt.executeQuery();){
				if(rs.next()) {
					NhanVien nv = createNhanVien(rs);
					return nv;
				}
			}
			return null;
		}
	}
	// duyet toan bo danh sach nhan vien
	public List<NhanVien> findAll() throws SQLException, ClassNotFoundException{
		String sql = "select * from nhanvien";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
				){
			try(ResultSet rs = stmt.executeQuery();){
				List<NhanVien> list = new ArrayList<>();
				while(rs.next()) {
					NhanVien cn = createNhanVien(rs);
					list.add(cn);
				}
				return list;
			}
		}
	}
	public boolean update(NhanVien cn) throws SQLException, IOException, ClassNotFoundException {
		String sql = "update nhanvien set hoten = ?, phai = ?, ngaysinh = ?,"
				+ "diachi = ?, email = ?, phone = ?, ngaythamgiacongtac = ?, trinhdochuyenmon = ?,"
				+ " kinhnghiem = ?, chucvu = ?, mapb = ?, hinhanh = ?, trangthai = ? where manv = ?";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
				){
			stmt.setString(1, cn.getHoTen());
			stmt.setInt(2, cn.isPhai());
			stmt.setDate(3, cn.getNgaySinh());
			stmt.setString(4, cn.getDiaChi());
			stmt.setString(5, cn.getEmail());
			stmt.setString(6, cn.getPhone());
			stmt.setDate(7, cn.getNgayThamGiaCongTac());
			stmt.setString(8, cn.getTrinhDoChuyenMon());
			stmt.setString(9, cn.getKinhNghiem());
			stmt.setString(10, cn.getChucVu());
			stmt.setString(11, cn.getPhong().getMaPB());
			// hinh anh
			if(cn.getHinhAnh() != null) {
				Blob hinh = new SerialBlob(cn.getHinhAnh());
				stmt.setBlob(12, hinh);
			}
			else {
				Blob hinh = null;
				stmt.setBlob(12, hinh);
			}
			stmt.setString(13, cn.getTrangThai());
			stmt.setString(14, cn.getMaNV());
			return stmt.executeUpdate() > 0;
		}
	}
	// xoa nhan vien bang ma
	public boolean delete(String ma) throws SQLException, ClassNotFoundException {
		String sql = "delete from nhanvien where manv = ?";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
				){
			stmt.setString(1, ma);
			return stmt.executeUpdate() > 0;

		}
	}
	// tim kiem
	public List<NhanVien> findCongNhanByTagName(String id){
		ResultSet rs = null;
		Statement stmt = null;
		List<NhanVien> lcn = new ArrayList<NhanVien>();
		try {
			String sql = "select * from nhanvien where phone = '" + id + "'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maCN =  rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String email = rs.getString(6);
				String phone = rs.getString(7);
				Date ngayThamGiaCongTac = rs.getDate(8);
				String tdcm = rs.getString(9);
				String kn = rs.getString(10);
				String cv = rs.getString(11);
				String maPhong = rs.getString(12);
				PhongBan pb = new PhongBan(maPhong);
				NhanVien cn = new NhanVien(maCN, hoTen, phai, ngaySinh, diaChi, email, phone, ngayThamGiaCongTac, tdcm, kn, cv, pb, null);
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
			String sql = "select max( CONVERT(int , SUBSTRING(maNV,3 , 6))) from [dbo].[NhanVien]";
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
	public ArrayList<NhanVien> getallNhanVien_A(){
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql = "select * from nhanvien";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maVN = rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String email = rs.getString(6);
				String phone = rs.getString(7);
				Date ngayThamGiaCongTac = rs.getDate(8);
				String trinhDoChuyenMon = rs.getString(9);
				String kinhNghiem = rs.getString(10);
				String chucVu = rs.getString(11);
				PhongBan pb = new PhongBan(rs.getString(12));
				NhanVien nv = new NhanVien(maVN, hoTen, phai, ngaySinh, diaChi, email, phone, ngayThamGiaCongTac, trinhDoChuyenMon, kinhNghiem, chucVu, pb, null);
				dsnv.add(nv);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsnv;
	}
	public ArrayList<NhanVien> getallNhanVien(){
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql = "select * from nhanvien";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maVN = rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String email = rs.getString(6);
				String phone = rs.getString(7);
				Date ngayThamGiaCongTac = rs.getDate(8);
				String trinhDoChuyenMon = rs.getString(9);
				String kinhNghiem = rs.getString(10);
				String chucVu = rs.getString(11);
				PhongBan pb = new PhongBan(rs.getString(12));
				NhanVien nv = new NhanVien(maVN, hoTen, phai, ngaySinh, diaChi, email, phone, ngayThamGiaCongTac, trinhDoChuyenMon, kinhNghiem, chucVu, pb, null);
				dsnv.add(nv);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsnv;
	}

	public List<NhanVien> findNhanVienByTagName(String name){
		ResultSet rs = null;
		Statement stmt = null;
		List<NhanVien> lcn = new ArrayList<NhanVien>();
		try {
			String sql = "select * from nhanvien where hoten like  N'%"+name+"%'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maNV =  rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String email = rs.getString(6);
				String phone = rs.getString(7);
				Date ngayThamGiaCongTac = rs.getDate(8);
				String tdcm = rs.getString(9);
				String kn = rs.getString(10);
				String cv = rs.getString(11);
				String maPhong = rs.getString(12);
				PhongBan pb = new PhongBan(maPhong);
				NhanVien nhanVien = new NhanVien(maNV, hoTen, phai, ngaySinh, diaChi, email, phone, ngayThamGiaCongTac, kn , tdcm, cv, pb, null);
				lcn.add(nhanVien);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lcn;
	}
	public List<NhanVien>findNhanVienByGioiTinh(int gioitinh)
	{
		ResultSet rs = null;
		Statement stmt = null;
		List<NhanVien> lcn = new ArrayList<NhanVien>();
		try {
			String sql = "select * from nhanvien where gioitinh like "+gioitinh+"";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maNV =  rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String email = rs.getString(6);
				String phone = rs.getString(7);
				Date ngayThamGiaCongTac = rs.getDate(8);
				String tdcm = rs.getString(9);
				String kn = rs.getString(10);
				String cv = rs.getString(11);
				String maPhong = rs.getString(12);
				PhongBan pb = new PhongBan(maPhong);
				NhanVien nhanVien = new NhanVien(maNV, hoTen, phai, ngaySinh, diaChi, email, phone, ngayThamGiaCongTac, kn, tdcm, cv, pb, null);
				lcn.add(nhanVien);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lcn;
	}

	public List<NhanVien> findNhanVienByEmail(String email1){
		ResultSet rs = null;
		Statement stmt = null;
		List<NhanVien> lcn = new ArrayList<NhanVien>();
		try {
			String sql = "select * from nhanvien where email like  N'%"+email1+"%'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maNV =  rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String email = rs.getString(6);
				String phone = rs.getString(7);
				Date ngayThamGiaCongTac = rs.getDate(8);
				String tdcm = rs.getString(9);
				String kn = rs.getString(10);
				String cv = rs.getString(11);
				String maPhong = rs.getString(12);
				PhongBan pb = new PhongBan(maPhong);
				NhanVien nhanVien = new NhanVien(maNV, hoTen, phai, ngaySinh, diaChi, email, phone, ngayThamGiaCongTac, kn , tdcm, cv, pb, null);
				lcn.add(nhanVien);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lcn;
	}

	public List<NhanVien> findNhanVienByDiaChi(String diaChi1){
		ResultSet rs = null;
		Statement stmt = null;
		List<NhanVien> lcn = new ArrayList<NhanVien>();
		try {
			String sql = "select * from nhanvien where diachi like  N'%"+diaChi1+"%'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maNV =  rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String email = rs.getString(6);
				String phone = rs.getString(7);
				Date ngayThamGiaCongTac = rs.getDate(8);
				String tdcm = rs.getString(9);
				String kn = rs.getString(10);
				String cv = rs.getString(11);
				String maPhong = rs.getString(12);
				PhongBan pb = new PhongBan(maPhong);
				NhanVien nhanVien = new NhanVien(maNV, hoTen, phai, ngaySinh, diaChi, email, phone, ngayThamGiaCongTac, kn , tdcm, cv, pb, null);
				lcn.add(nhanVien);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lcn;
	}

	public List<NhanVien> findNhanVienByKinhNghiem(String kinhNghiem1){
		ResultSet rs = null;
		Statement stmt = null;
		List<NhanVien> lcn = new ArrayList<NhanVien>();
		try {
			String sql = "select * from nhanvien where kinhnghiem like  N'%"+kinhNghiem1+"%'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maNV =  rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String email = rs.getString(6);
				String phone = rs.getString(7);
				Date ngayThamGiaCongTac = rs.getDate(8);
				String tdcm = rs.getString(9);
				String kn = rs.getString(10);
				String cv = rs.getString(11);
				String maPhong = rs.getString(12);
				PhongBan pb = new PhongBan(maPhong);
				NhanVien nhanVien = new NhanVien(maNV, hoTen, phai, ngaySinh, diaChi, email, phone, ngayThamGiaCongTac, kn , tdcm, cv, pb, null);
				lcn.add(nhanVien);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lcn;
	}

	public List<NhanVien> findNhanVienBySoDienThoai(String soDienThoai1){
		ResultSet rs = null;
		Statement stmt = null;
		List<NhanVien> lcn = new ArrayList<NhanVien>();
		try {
			String sql = "select * from nhanvien where phone like  N'"+soDienThoai1+"'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maNV =  rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String email = rs.getString(6);
				String phone = rs.getString(7);
				Date ngayThamGiaCongTac = rs.getDate(8);
				String tdcm = rs.getString(9);
				String kn = rs.getString(10);
				String cv = rs.getString(11);
				String maPhong = rs.getString(12);
				PhongBan pb = new PhongBan(maPhong);
				NhanVien nhanVien = new NhanVien(maNV, hoTen, phai, ngaySinh, diaChi, email, phone, ngayThamGiaCongTac, kn , tdcm, cv, pb, null);
				lcn.add(nhanVien);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lcn;
	}

	//-----------Tìm theo mã-------------------------//
	public List<NhanVien> findNhanVienByMaNhanVien(String maNhanVien1){
		ResultSet rs = null;
		Statement stmt = null;
		List<NhanVien> lcn = new ArrayList<NhanVien>();
		try {
			String sql = "select * from nhanvien where manv like  N'"+maNhanVien1+"'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maNV =  rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String email = rs.getString(6);
				String phone = rs.getString(7);
				Date ngayThamGiaCongTac = rs.getDate(8);
				String tdcm = rs.getString(9);
				String kn = rs.getString(10);
				String cv = rs.getString(11);
				String maPhong = rs.getString(12);
				PhongBan pb = new PhongBan(maPhong);
				NhanVien nhanVien = new NhanVien(maNV, hoTen, phai, ngaySinh, diaChi, email, phone, ngayThamGiaCongTac, kn , tdcm, cv, pb, null);
				lcn.add(nhanVien);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lcn;
	}

	//-----------Tìm theo chức vụ-------------------------//
	public List<NhanVien> findNhanVienByChucVu(String chucVu1){
		ResultSet rs = null;
		Statement stmt = null;
		List<NhanVien> lcn = new ArrayList<NhanVien>();
		try {
			String sql = "select * from nhanvien where chucvu like  N'%"+chucVu1+"%'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maNV =  rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String email = rs.getString(6);
				String phone = rs.getString(7);
				Date ngayThamGiaCongTac = rs.getDate(8);
				String tdcm = rs.getString(9);
				String kn = rs.getString(10);
				String cv = rs.getString(11);
				String maPhong = rs.getString(12);
				PhongBan pb = new PhongBan(maPhong);
				NhanVien nhanVien = new NhanVien(maNV, hoTen, phai, ngaySinh, diaChi, email, phone, ngayThamGiaCongTac, kn , tdcm, cv, pb, null);
				lcn.add(nhanVien);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lcn;
	}

	public List<NhanVien> findNhanVienByTrinhDoChuyenMon(String trinhDoChuyenMon){
		ResultSet rs = null;
		Statement stmt = null;
		List<NhanVien> lcn = new ArrayList<NhanVien>();
		try {
			String sql = "select * from nhanvien where trinhdochuyenmon like  N'%"+trinhDoChuyenMon+"%'";
			Connection con = connectDB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maNV =  rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String email = rs.getString(6);
				String phone = rs.getString(7);
				Date ngayThamGiaCongTac = rs.getDate(8);
				String tdcm = rs.getString(9);
				String kn = rs.getString(10);
				String cv = rs.getString(11);
				String maPhong = rs.getString(12);
				PhongBan pb = new PhongBan(maPhong);
				NhanVien nhanVien = new NhanVien(maNV, hoTen, phai, ngaySinh, diaChi, email, phone, ngayThamGiaCongTac, kn , tdcm, cv, pb, null);
				lcn.add(nhanVien);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lcn;
	}
	
	
	public ArrayList<NhanVien> getallNhanVienDangLamViec(){
		try {
			dsnv.clear();
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql = "select * from  Nhanvien join PhongBan on nhanvien.mapb = phongban.mapb where TrangThai like N'Đang làm việc'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maVN = rs.getString(1);
				String hoTen = rs.getString(2);
				int phai = rs.getInt(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getString(5);
				String email = rs.getString(6);
				String phone = rs.getString(7);
				Date ngayThamGiaCongTac = rs.getDate(8);
				String trinhDoChuyenMon = rs.getString(9);
				String kinhNghiem = rs.getString(10);
				String chucVu = rs.getString(11);
				
				String tenPhongBan = rs.getString(16);
				PhongBan pb = new PhongBan(rs.getString(12),tenPhongBan);
				String trangThai = rs.getString(14);
				
				NhanVien nv = new NhanVien(maVN, hoTen, phai, ngaySinh, diaChi, email, phone, ngayThamGiaCongTac, trinhDoChuyenMon, kinhNghiem, chucVu, pb, null,trangThai);
				dsnv.add(nv);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsnv;
	}
}
