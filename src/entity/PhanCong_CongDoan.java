package entity;

import java.sql.Date;
import java.util.Objects;

public class PhanCong_CongDoan {
	private Date ngayPhanCong;
	private CongNhan congNhan;
	private CongDoan congDoan;
	public PhanCong_CongDoan() {
		// TODO Auto-generated constructor stub
	}
	public PhanCong_CongDoan(Date ngayPhanCong, CongNhan congNhan, CongDoan congDoan) {
		super();
		this.ngayPhanCong = ngayPhanCong;
		this.congNhan = congNhan;
		this.congDoan = congDoan;
	}
	public Date getNgayPhanCong() {
		return ngayPhanCong;
	}
	public void setNgayPhanCong(Date ngayPhanCong) {
		this.ngayPhanCong = ngayPhanCong;
	}
	public CongNhan getCongNhan() {
		return congNhan;
	}
	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}
	public CongDoan getCongDoan() {
		return congDoan;
	}
	public void setCongDoan(CongDoan congDoan) {
		this.congDoan = congDoan;
	}
	@Override
	public String toString() {
		return "PhanCong_CongDoan [ngayPhanCong=" + ngayPhanCong + ", congNhan=" + congNhan + ", congDoan=" + congDoan
				+ "]";
	}
	
	
}
