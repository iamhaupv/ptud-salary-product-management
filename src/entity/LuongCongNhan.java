package entity;

import java.sql.Date;
import java.util.Objects;

public class LuongCongNhan {
	private String maLCN;
	private double phuCap;
	private double thuong;
	private double phat;
	private double bhxh;
	private int soNgayCong;
	private CongDoan congDoan;
	private int soCongDoanHoanThanh;
	private CongNhan congNhan;
	private Date thangLuong;
	private double tongLuong;
	public LuongCongNhan() {
		// TODO Auto-generated constructor stub
	}
	public LuongCongNhan(String maLCN) {
		// TODO Auto-generated constructor stub
		this.maLCN = maLCN;
	}
	
	public LuongCongNhan(String maLCN, double phuCap, double thuong, double phat, double bhxh, int soNgayCong,
			CongDoan congDoan, int soCongDoanHoanThanh, CongNhan congNhan, Date thangLuong, double tongLuong) {
		super();
		this.maLCN = maLCN;
		this.phuCap = phuCap;
		this.thuong = thuong;
		this.phat = phat;
		this.bhxh = bhxh;
		this.soNgayCong = soNgayCong;
		this.congDoan = congDoan;
		this.soCongDoanHoanThanh = soCongDoanHoanThanh;
		this.congNhan = congNhan;
		this.thangLuong = thangLuong;
		this.tongLuong = tongLuong;
	}
	public double getTongLuong() {
		return tongLuong;
	}
	public void setTongLuong(double tongLuong) {
		this.tongLuong = tongLuong;
	}
	@Override
	public String toString() {
		return "LuongCongNhan [maLCN=" + maLCN + ", phuCap=" + phuCap + ", thuong=" + thuong + ", phat=" + phat
				+ ", bhxh=" + bhxh + ", soNgayCong=" + soNgayCong + ", congDoan=" + congDoan + ", soCongDoanHoanThanh="
				+ soCongDoanHoanThanh + ", congNhan=" + congNhan + ", thangLuong=" + thangLuong + "]";
	}
	public LuongCongNhan(String maLCN, double phuCap, double thuong, double phat, double bhxh, int soNgayCong,
			CongDoan congDoan, int soCongDoanHoanThanh, CongNhan congNhan, Date thangLuong) {
		super();
		this.maLCN = maLCN;
		this.phuCap = phuCap;
		this.thuong = thuong;
		this.phat = phat;
		this.bhxh = bhxh;
		this.soNgayCong = soNgayCong;
		this.congDoan = congDoan;
		this.soCongDoanHoanThanh = soCongDoanHoanThanh;
		this.congNhan = congNhan;
		this.thangLuong = thangLuong;
	}
	public int getSoNgayCong() {
		return soNgayCong;
	}
	public void setSoNgayCong(int soNgayCong) {
		this.soNgayCong = soNgayCong;
	}
	public int getSoCongDoanHoanThanh() {
		return soCongDoanHoanThanh;
	}
	public void setSoCongDoanHoanThanh(int soCongDoanHoanThanh) {
		this.soCongDoanHoanThanh = soCongDoanHoanThanh;
	}
	public CongNhan getCongNhan() {
		return congNhan;
	}
	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}
	public Date getThangLuong() {
		return thangLuong;
	}
	public void setThangLuong(Date thangLuong) {
		this.thangLuong = thangLuong;
	}
	public String getMaLCN() {
		return maLCN;
	}
	public void setMaLCN(String maLCN) {
		this.maLCN = maLCN;
	}
	public double getPhuCap() {
		return phuCap;
	}
	public void setPhuCap(double phuCap) {
		this.phuCap = phuCap;
	}
	public double getThuong() {
		return thuong;
	}
	public void setThuong(double thuong) {
		this.thuong = thuong;
	}
	public double getPhat() {
		return phat;
	}
	public void setPhat(double phat) {
		this.phat = phat;
	}
	public double getBhxh() {
		return bhxh;
	}
	public void setBhxh(double bhxh) {
		this.bhxh = bhxh;
	}

	public CongDoan getCongDoan() {
		return congDoan;
	}
	public void setCongDoan(CongDoan congDoan) {
		this.congDoan = congDoan;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maLCN);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LuongCongNhan other = (LuongCongNhan) obj;
		return Objects.equals(maLCN, other.maLCN);
	}
	
}
