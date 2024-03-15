package dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.connectDB;
import entity.CongDoan;
import entity.CongNhan;
import entity.PhanCong_CongDoan;
import entity.SanPham;
import helper.DatabaseConnet;

public class PhanCongCongDoan_DAO {
	ArrayList<PhanCong_CongDoan> dsPhanCong ;
	PhanCong_CongDoan phanCong ;

	public PhanCongCongDoan_DAO()
	{
		dsPhanCong = new ArrayList<PhanCong_CongDoan>();
		phanCong = new PhanCong_CongDoan();
	}

	public ArrayList<PhanCong_CongDoan> getallBangPhanCong(){
		ArrayList<PhanCong_CongDoan> d = new ArrayList<PhanCong_CongDoan>();
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql ="select CONGNHAN.MACN ,CONGNHAN.HOTEN,\r\n"
					+ "					CONGDOAN.MACD,\r\n"
					+ "					CONGDOAN.TENCONGDOAN,\r\n"
					+ "					CONGDOAN.DONGIA,\r\n"
					+ "					PHANCONG_CONGDOAN.NGAYPHANCONG\r\n"
					+ "					from PHANCONG_CONGDOAN \r\n"
					+ "					inner join CONGDOAN\r\n"
					+ "					on PHANCONG_CONGDOAN.MACD = CONGDOAN.MACD \r\n"
					+ "					join CONGNHAN on PHANCONG_CONGDOAN.MACN = CONGNHAN.MACN";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
//				String maPhanCong = rs.getString(1);
				CongNhan congNhan = new CongNhan(rs.getString(1),rs.getString(2));
				CongDoan congDoan = new CongDoan(rs.getString(3),rs.getString(4),rs.getDouble(5));
				Date ngayPhanCong = rs.getDate(6);
				phanCong = new PhanCong_CongDoan(ngayPhanCong, congNhan, congDoan);
				d.add(phanCong);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return d;
	}
	//Tạp bảng phân công
	public boolean taoBangPhanCong(PhanCong_CongDoan phanCong_CD)
	{
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement stmt = null ;
		int n = 0 ;
		try {
			stmt =con.prepareStatement("insert into PHANCONG_CONGDOAN values (?,?,?)");
			stmt.setDate(1, phanCong_CD.getNgayPhanCong());
			stmt.setString(2,phanCong_CD.getCongNhan().getMaCN());
			stmt.setString(3,phanCong_CD.getCongDoan().getMaCD());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;

	}
	// Xóa bảng phân công
	public boolean xoaBangPhanCong(String maPhanCong)
	{
		Connection con = connectDB.getInstance().getConnection();
		PreparedStatement stmt = null ;
		int n = 0 ;
		try {
			stmt = con.prepareStatement("delete from PhanCong_CongDoan where Macn=?");
			stmt.setString(1, maPhanCong);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n >  0;
	}



	// public boolean CapNhatBangPhanCong()
	// {
	//	 connectDB.getInstance();
	//	   Connection con = connectDB.getConnection();
	//	   PreparedStatement stmt = null;
	//	   int n = 0;
	//	   try {
	//		    stmt = con.prepareStatement("update PhanCong_CongDoan set MaCN=? ,  MaCD=? where MaPC=? ");
	//		    stmt.setString(1,phanCong.getCongNhan().getMaCN());
	//			stmt.setString(2,phanCong.getCongDoan().getMaCD());
	//			stmt.setString(3,phanCong.getMaPC());
	//			n = stmt.executeUpdate();
	//	} catch (Exception e) {
	//		// TODO: handle exception
	//		e.printStackTrace();
	//	}
	//	   return n >0;
	// }

	public PhanCong_CongDoan createPhanCong(final ResultSet rs){
		try {
			PhanCong_CongDoan nv = new PhanCong_CongDoan();
			nv.setNgayPhanCong(rs.getDate("ngayphancong"));
			CongNhan cn = new CongNhan(rs.getString("macn"));
			nv.setCongNhan(cn);
			CongDoan cd = new CongDoan(rs.getString("macd"));
			nv.setCongDoan(cd);
			return nv;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	public List<PhanCong_CongDoan> findAll(){
		String sql = "select * from phancong_congdoan";
		List<PhanCong_CongDoan> list = new ArrayList<>();
		try {
			Connection con = DatabaseConnet.openConnect();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				PhanCong_CongDoan cn = createPhanCong(rs);
				list.add(cn);
			}	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	public List<PhanCong_CongDoan> loadBangPhanCong() {
		List<PhanCong_CongDoan> l = new ArrayList<PhanCong_CongDoan>();
		String sql = "select * from phancong_congdoan";
		try {
			Connection con = DatabaseConnet.openConnect();
			PreparedStatement stmt = con.prepareStatement(sql);
			PhanCong_CongDoan pc = new PhanCong_CongDoan();
			stmt.setDate(1, pc.getNgayPhanCong());
			stmt.setString(2, pc.getCongNhan().getMaCN());
			stmt.setString(3, pc.getCongDoan().getMaCD());
			l.add(pc);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return l;
	}
	public List<PhanCong_CongDoan> phanCong(){
		List<PhanCong_CongDoan> l = new ArrayList<PhanCong_CongDoan>();
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql ="select p.MACN, hoten, n.trangThai, c.MACD, tencongdoan, c.DONGIA, NGAYPHANCONG, c.trangthai,  s.MASP, tensanpham\r\n"
					+ "from sanpham s join congdoan c on s.MASP = c.MASP join PHANCONG_CONGDOAN p on c.MACD = p.MACD join congnhan n on n.MACN = p.MACN";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				SanPham sp = new SanPham(rs.getString(9), rs.getString(10));
				CongNhan congNhan = new CongNhan(rs.getString(1),rs.getString(2), rs.getString(3));
				CongDoan congDoan = new CongDoan(rs.getString(4),rs.getString(5),rs.getDouble(6), sp, rs.getString(8));
				Date ngayPhanCong = rs.getDate(7);
				phanCong = new PhanCong_CongDoan(ngayPhanCong, congNhan, congDoan);
				l.add(phanCong);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return l;
	}
}

