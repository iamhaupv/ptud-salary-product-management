package dao;

import java.beans.Statement;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import connect.connectDB;
import entity.NhanVien;
import entity.PhongBan;
import entity.TaiKhoan;
import helper.DatabaseConnet;

public class TaiKhoan_DAO {
	ArrayList<TaiKhoan> dsnd;
	TaiKhoan nd;
	public TaiKhoan_DAO() {
		// TODO Auto-generated constructor stub
		dsnd = new ArrayList<TaiKhoan>();
		nd = new TaiKhoan();
	}
	public ArrayList<TaiKhoan> getAllNguoiDung(){
		try {
			Connection con = connectDB.getInstance().getConnection();
			String sql = "Select * from taikhoan";
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String tk = rs.getString(1);
				String mk = rs.getString(2);
				NhanVien nvhc = new NhanVien(rs.getString(3));
				java.sql.Date ngayThem = rs.getDate(4);
				java.sql.Date ngayCapNhat = rs.getDate(5);
				if(ngayCapNhat == null) {
					ngayCapNhat = java.sql.Date.valueOf("2002-06-09");
				}
				String vaiTro = rs.getString(6);
				TaiKhoan h = new TaiKhoan(tk, mk, nvhc, ngayThem, ngayCapNhat, vaiTro);
				dsnd.add(h);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsnd;
	}
		public boolean createTaiKhoan(TaiKhoan nd) {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			PreparedStatement stmt = null;
			int n = 0;
			try {
				stmt = con.prepareStatement("insert into taikhoan values(?, ?, ?, ?, ?, ?)");
				stmt.setString(1, nd.getTaiKhoan());
				stmt.setString(2, nd.getMatKhau());
				stmt.setString(3, nd.getNvhc().getMaNV());
				stmt.setDate(4, (java.sql.Date) nd.getNgayThem());
				stmt.setDate(5, (java.sql.Date) nd.getNgayCapNhat());
				stmt.setString(6, nd.getVaiTro());
				n = stmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
				return n < 0;
			}
			return n > 0;
		}
	public boolean update(TaiKhoan nd) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update taikhoan set matkhau = ?, ngayThem = ?, ngaycapnhat = ? where taikhoan = ?");
			stmt.setString(1, nd.getMatKhau());
			stmt.setDate(2, nd.getNgayThem());
			stmt.setDate(3, nd.getNgayCapNhat());
			stmt.setString(4, nd.getTaiKhoan());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
//	public boolean deleteNguoiDung(String user) {
//		connectDB.getInstance();
//		Connection con = connectDB.getConnection();
//		PreparedStatement stmt = null;
//		int n = 0;
//		try {
//			stmt = con.prepareStatement("delete taikhoan where taikhoan = ?");
//			stmt.setString(1, user);
//			n = stmt.executeUpdate();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return n > 0;
//	}
	public boolean delete(String ma){
		String sql = "delete from taikhoan where taikhoan = ?";
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
	//tao ra mot tai khoan moi
	public TaiKhoan createTaiKhoan(final ResultSet rs) throws SQLException {
		TaiKhoan nv = new TaiKhoan();
		nv.setTaiKhoan(rs.getString("taikhoan")); // 1
		nv.setMatKhau(rs.getString("matkhau")); // 2
		NhanVien n = new NhanVien(rs.getString("manv"));
		nv.setNgayThem(rs.getDate("ngaythem"));
		nv.setNgayCapNhat(rs.getDate("ngaycapnhat"));
		nv.setNvhc(n);
		nv.setVaiTro(rs.getString("vaitro"));
		return nv;
	}
	// tim nhan vien bang ma nhan vien
	public TaiKhoan findByID(String id) throws SQLException, ClassNotFoundException {
		connectDB.getInstance();
		String sql = "select * from taikhoan where taikhoan = ?";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
				){
			stmt.setString(1 , id);
			try(ResultSet rs = stmt.executeQuery();){
				if(rs.next()) {
					TaiKhoan nv = createTaiKhoan(rs);
					return nv;
				}
			}
			return null;
		}
	}
	public List<TaiKhoan> findAll() throws SQLException, ClassNotFoundException{
		String sql = "select * from taikhoan";
		try(
				Connection con = DatabaseConnet.openConnect();
				PreparedStatement stmt = con.prepareStatement(sql);
			){
			try(ResultSet rs = stmt.executeQuery();){
				List<TaiKhoan> list = new ArrayList<>();
				while(rs.next()) {
					TaiKhoan cn = createTaiKhoan(rs);
					list.add(cn);
				}
				return list;
			}
		}
	}
}
