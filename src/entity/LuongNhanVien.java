package entity;

import java.sql.Date;
import java.util.Objects;

public class LuongNhanVien {
	private String maLNV;
	private double phuCap;
	private double thuong;
	private double phat;
	private double bhxh;
	private int soNgayCong;
	private double tongLuong;
	private NhanVien nhanVien;
	private Date thangLuong;
	
	public Date getThangLuong() {
		return thangLuong;
	}

	public void setThangLuong(Date thangLuong) {
		this.thangLuong = thangLuong;
	}
	
	public LuongNhanVien(String maLNV, double phuCap, double thuong, double phat, double bhxh, int soNgayCong,
			double tongLuong, NhanVien nhanVien, Date thangLuong) {
		super();
		this.maLNV = maLNV;
		this.phuCap = phuCap;
		this.thuong = thuong;
		this.phat = phat;
		this.bhxh = bhxh;
		this.soNgayCong = soNgayCong;
		this.tongLuong = tongLuong;
		this.nhanVien = nhanVien;
		this.thangLuong = thangLuong;
	}
	
	public LuongNhanVien(Date thangLuong, double phuCap, double thuong, double phat, double bhxh, double tongLuong) {
		super();
		this.thangLuong = thangLuong;
		this.phuCap = phuCap;
		this.thuong = thuong;
		this.phat = phat;
		this.bhxh = bhxh;
		this.tongLuong = tongLuong;
	}
	public LuongNhanVien(String maLNV, Date thangLuong, double phuCap, double thuong, double phat, double bhxh, double tongLuong) {
		super();
		this.maLNV = maLNV;
		this.thangLuong = thangLuong;
		this.phuCap = phuCap;
		this.thuong = thuong;
		this.phat = phat;
		this.bhxh = bhxh;
		this.tongLuong = tongLuong;
	}
	public LuongNhanVien(double phuCap, double thuong, double phat, double bhxh, double tongLuong) {
		super();
		this.phuCap = phuCap;
		this.thuong = thuong;
		this.phat = phat;
		this.bhxh = bhxh;
		this.tongLuong = tongLuong;
	}

	public double getTongLuong() {
		return tongLuong;
	}

	public void setTongLuong(double tongLuong) {
		this.tongLuong = tongLuong;
	}

	public LuongNhanVien() {
		// TODO Auto-generated constructor stub
	}
	public LuongNhanVien(Date thangLuong) {
		// TODO Auto-generated constructor stub
		this.thangLuong = thangLuong;
	}
	public LuongNhanVien(String maLNV) {
		// TODO Auto-generated constructor stub
		this.maLNV = maLNV;
		
	}
	
	public int getSoNgayCong() {
		return soNgayCong;
	}

	public void setSoNgayCong(int soNgayCong) {
		this.soNgayCong = soNgayCong;
	}


	public String getMaLNV() {
		return maLNV;
	}

	public void setMaLNV(String maLNV) {
		this.maLNV = maLNV;
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

	@Override
	public int hashCode() {
		return Objects.hash(maLNV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LuongNhanVien other = (LuongNhanVien) obj;
		return Objects.equals(maLNV, other.maLNV);
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	
}