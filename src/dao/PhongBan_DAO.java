package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.connectDB;
import entity.CongNhan;
import entity.PhongBan;
import helper.DatabaseConnet;

public class PhongBan_DAO {
	ArrayList<PhongBan> dspb;
	PhongBan pb;
	public PhongBan_DAO() {
		// TODO Auto-generated constructor stub
		dspb = new ArrayList<PhongBan>();
		pb = new PhongBan();
	}
	public ArrayList<PhongBan> getallPhongBan(){
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql = "select * from phongban";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maPhong = rs.getString(1);
				String tenPhong = rs.getString(2);
				PhongBan p = new PhongBan(maPhong, tenPhong);
				dspb.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dspb;
	}
	// tao ra mot cong nhan moi
	public PhongBan createPhong(final ResultSet rs){
		try {
			PhongBan nv = new PhongBan();
			nv.setMaPB(rs.getString("mapb"));
			nv.setTenPhong(rs.getString("tenphong"));
			return nv;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	// tim kiem nhan vien bang ma cong nhan
	public PhongBan findByID(String id){
		connectDB.getInstance();
		String sql = "select * from congnhan where maCN = ?";
		PhongBan cn = new PhongBan();
		try {
			Connection con = DatabaseConnet.openConnect();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1 , id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				cn = createPhong(rs);
			return cn;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	// duyet toan bo danh sach cong nhan
	public List<PhongBan> findAll(){
		String sql = "select * from phongban";
		List<PhongBan> list = new ArrayList<>();
		try {
			Connection con = DatabaseConnet.openConnect();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				PhongBan cn = createPhong(rs);
				list.add(cn);
			}	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
}
