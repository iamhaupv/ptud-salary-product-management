package entity;

import java.sql.Date;
import java.util.Objects;

public class HopDong {
	private String maHD;
	private String tenHopDong;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private int soLuong;
	private double donGiaSanPham;
	private SanPham sanPham;
	private String khachHang;
	private String phone;
	private double tongTien;
	private String trangThai;
	public HopDong() {
		// TODO Auto-generated constructor stub
	}
	public HopDong(String maHD) {
		// TODO Auto-generated constructor stub
		this.maHD = maHD;
	}
	public HopDong(Date ngayBatDau, double tongTien) {
		// TODO Auto-generated constructor stub
		this.ngayBatDau = ngayBatDau;
		this.tongTien = tongTien;
	}
	
	public HopDong(String maHD, String tenHopDong, Date ngayBatDau, Date ngayKetThuc, int soLuong, double donGiaSanPham,
			SanPham sanPham, String khachHang, String phone, double tongTien) {
		super();
		this.maHD = maHD;
		this.tenHopDong = tenHopDong;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.soLuong = soLuong;
		this.donGiaSanPham = donGiaSanPham;
		this.sanPham = sanPham;
		this.khachHang = khachHang;
		this.phone = phone;
		this.tongTien = tongTien;
	}
	public HopDong(String maHD, String tenHopDong, Date ngayBatDau, Date ngayKetThuc, int soLuong, double donGiaSanPham,
			SanPham sanPham, String khachHang, String phone, double tongTien, String trangThai) {
		super();
		this.maHD = maHD;
		this.tenHopDong = tenHopDong;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.soLuong = soLuong;
		this.donGiaSanPham = donGiaSanPham;
		this.sanPham = sanPham;
		this.khachHang = khachHang;
		this.phone = phone;
		this.tongTien = tongTien;
		this.trangThai = trangThai;
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public String getTenHopDong() {
		return tenHopDong;
	}
	public void setTenHopDong(String tenHopDong) {
		this.tenHopDong = tenHopDong;
	}
	public Date getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getDonGiaSanPham() {
		return donGiaSanPham;
	}
	public void setDonGiaSanPham(double donGiaSanPham) {
		this.donGiaSanPham = donGiaSanPham;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public String getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(String khachHang) {
		this.khachHang = khachHang;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	
	
	@Override
	public String toString() {
		return "HopDong [maHD=" + maHD + ", tenHopDong=" + tenHopDong + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc="
				+ ngayKetThuc + ", soLuong=" + soLuong + ", donGiaSanPham=" + donGiaSanPham + ", sanPham=" + sanPham
				+ ", khachHang=" + khachHang + ", phone=" + phone + ", tongTien=" + tongTien + ", trangThai="
				+ trangThai + "]";
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maHD);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HopDong other = (HopDong) obj;
		return Objects.equals(maHD, other.maHD);
	}
	
}
