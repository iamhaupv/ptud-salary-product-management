package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.connectDB;
import entity.ChamCong_NhanVien;
import entity.ChamCong_CongNhan;
import entity.CongNhan;
import entity.NhanVien;


public class ChamCong_CongNhan_DAO {
    ChamCong_CongNhan chamCongCongNhan ;
    ArrayList<ChamCong_CongNhan> dsChamCongCongNhan ;
    
    public ChamCong_CongNhan_DAO()
    {
    	chamCongCongNhan = new ChamCong_CongNhan();
    	dsChamCongCongNhan = new ArrayList<ChamCong_CongNhan>();
    }
    public ArrayList<ChamCong_CongNhan> getAllBangChamCongCongNhan()
	{
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql ="SELECT CHAMCONG_CONGNHAN.MACCCN,"
					+ "CHAMCONG_CONGNHAN.NGAYCHAMCONG,"
					+ "CHAMCONG_CONGNHAN.CALAMVIEC,"
					+ "CONGNHAN.MACN,CONGNHAN.HOTEN,"
					+ "CONGNHAN.PHONE,CHAMCONG_CONGNHAN.SOCONGDOANHOANTHANH,"
					+ "CHAMCONG_CONGNHAN.DIEMDANH,"
					+ "CHAMCONG_CONGNHAN.PHEP FROM CHAMCONG_CONGNHAN JOIN "
					+ "CONGNHAN ON CHAMCONG_CONGNHAN.MACN = CONGNHAN.MACN";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				String maCongCN = rs.getString(1);
				Date ngayChamCong = rs.getDate(2);
				String caLamViec = rs.getString(3);
				CongNhan congNhan = new CongNhan(rs.getString(4),rs.getString(5),rs.getString(6));
				int soCongDoanHoanThanh = rs.getInt(7);
				boolean diemDanh = rs.getBoolean(8);
				boolean phep = rs.getBoolean(9);
				
			chamCongCongNhan = new ChamCong_CongNhan(maCongCN, diemDanh, ngayChamCong, caLamViec, soCongDoanHoanThanh, congNhan, phep);
			dsChamCongCongNhan.add(chamCongCongNhan);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return dsChamCongCongNhan;
	}
	 
    public boolean chamCongCongNhan(ChamCong_CongNhan congCongNhan)
    {
 	   connectDB.getInstance();
 		Connection con = connectDB.getConnection();
 		PreparedStatement stmt = null ;
 		int n = 0 ;
 		try {
 			stmt =con.prepareStatement("insert into chamcong_congnhan values (?,?,?,?,?,?,?,?)");
 			stmt.setString(1,congCongNhan.getMaCCCN());
 			stmt.setBoolean(2, congCongNhan.isDiemDanh());
 			stmt.setDate(3, congCongNhan.getNgayChamCong());
 			stmt.setString(4, congCongNhan.getCaLamViec());
 			stmt.setInt(5, congCongNhan.getSoCongDoanHoanThanh());
 			stmt.setString(6,congCongNhan.getCongNhan().getMaCN());
 			stmt.setString(7, congCongNhan.getCongDoan().getMaCD());
 			stmt.setBoolean(8, congCongNhan.isPhep());
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
   			stmt = con.prepareStatement("delete from ChamCong_CongNhan where MaCCCN=?");
   			stmt.setString(1, maChamCong);
   			n = stmt.executeUpdate();
   		} catch (Exception e) {
   			// TODO: handle exception
   			e.printStackTrace();
   		}
   		   return n >  0;
    }
    public int getMaCCTD() {
		int ma = 1;
		connectDB.getInstance();
		Connection con = (Connection) connectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "\r\n"
					+ "select max( CONVERT(nchar,SUBSTRING(MACCCN,3, 6))) from [dbo].[CHAMCONG_CONGNHAN]";
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
