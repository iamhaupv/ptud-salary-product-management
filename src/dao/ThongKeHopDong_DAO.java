package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connect.connectDB;
import entity.HopDong;
import entity.SanPham;
import helper.DatabaseConnet;

public class ThongKeHopDong_DAO {
	ArrayList<HopDong> dshd;
	HopDong hd;
	public ThongKeHopDong_DAO() {
		// TODO Auto-generated constructor stub
		dshd = new ArrayList<>();
		hd = new HopDong();
	}
	// thong ke so luong hop dong/ngay
	public List<HopDong> thongKeHopDongTheoSoLuong(){
		List<HopDong> ds = new ArrayList<HopDong>();
		PreparedStatement stmt = null;
		try {
			Connection con = DatabaseConnet.openConnect();
			String sql = "select ngaybatdau, count(soluong) as tongsoluong from hopdong group by ngaybatdau";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				HopDong hd = new HopDong();
				hd.setNgayBatDau(rs.getDate("ngaybatdau"));
				hd.setSoLuong(rs.getInt("tongSoluong"));
				ds.add(hd);
			}
			return ds;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	// thong ke theo tong tien cua hop dong/ngay
	public List<HopDong> thongKeHopDongTheoTongTien(){
		List<HopDong> ds = new ArrayList<HopDong>();
		PreparedStatement stmt = null;
		try {
			Connection con = DatabaseConnet.openConnect();
			String sql = "select ngaybatdau, sum(tongtien) as tongtien from hopdong group by ngaybatdau";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				HopDong hd = new HopDong();
				hd.setNgayBatDau(rs.getDate("ngaybatdau"));
				hd.setTongTien(rs.getDouble("tongtien"));
				ds.add(hd);
			}
			return ds;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	// thong ke theo so luong san pham duoc dat hang
	public List<HopDong> thongKeHopDongTheoSoLuongSanPham(){
		List<HopDong> ds = new ArrayList<HopDong>();
		PreparedStatement stmt = null;
		try {
			Connection con = DatabaseConnet.openConnect();
			String sql = " select masp, sum(SOLUONG) as tongsoluong from hopdong group by masp";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				HopDong hd = new HopDong();
				SanPham s = new SanPham(rs.getString("masp"));
				hd.setSanPham(s);
				hd.setSoLuong(rs.getInt("tongsoluong"));
				ds.add(hd);
			}
			return ds;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
