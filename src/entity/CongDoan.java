package entity;

import java.util.Objects;

public class CongDoan {
	private String maCD;
	private String tenCongDoan;
	private double donGia;
	private SanPham sanPham;
	private int thuTu;
	private String trangThai;
	public CongDoan() {
		// TODO Auto-generated constructor stub
	}
	public CongDoan(SanPham sanPham) {
		// TODO Auto-generated constructor stub
		this.sanPham = sanPham;
	}
	public CongDoan(String maCD, String tenCongDoan, double donGia, String trangThai, int thuTu) {
		// TODO Auto-generated constructor stub
		this.maCD = maCD;
		this.tenCongDoan = tenCongDoan;
		this.donGia = donGia;
		this.trangThai = trangThai;
		this.thuTu = thuTu;
	}
	public CongDoan(String maCD) {
		// TODO Auto-generated constructor stub
		this.maCD = maCD;
	}
	public CongDoan(String maCD, String tenCongDoan, double donGia) {
		// TODO Auto-generated constructor stub
		this.maCD = maCD;
		this.tenCongDoan = tenCongDoan;
		this.donGia = donGia;
	}
	public CongDoan(String maCD, String tenCongDoan, double donGia, SanPham sanPham) {
		// TODO Auto-generated constructor stub
		this.maCD = maCD;
		this.tenCongDoan = tenCongDoan;
		this.donGia = donGia;
		this.sanPham = sanPham;
	}
	public CongDoan(String maCD, String tenCongDoan, double donGia, SanPham sanPham, String trangThai) {
		// TODO Auto-generated constructor stub
		this.maCD = maCD;
		this.tenCongDoan = tenCongDoan;
		this.donGia = donGia;
		this.sanPham = sanPham;
		this.trangThai = trangThai;
	}
	public CongDoan(String maCD, String tenCongDoan, double donGia, SanPham sanPham, int thuTu, String trangThai) {
		super();
		this.maCD = maCD;
		this.tenCongDoan = tenCongDoan;
		this.donGia = donGia;
		this.sanPham = sanPham;
		this.thuTu = thuTu;
		this.trangThai = trangThai;
	}
	public String getMaCD() {
		return maCD;
	}
	public void setMaCD(String maCD) {
		this.maCD = maCD;
	}
	public String getTenCongDoan() {
		return tenCongDoan;
	}
	public void setTenCongDoan(String tenCongDoan) {
		this.tenCongDoan = tenCongDoan;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maCD);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CongDoan other = (CongDoan) obj;
		return Objects.equals(maCD, other.maCD);
	}
	@Override
	public String toString() {
		return "CongDoan [maCD=" + maCD + ", tenCongDoan=" + tenCongDoan + ", donGia=" + donGia + ", sanPham=" + sanPham
				+ ", thuTu=" + thuTu + ", trangThai=" + trangThai + "]";
	}
	public int getThuTu() {
		return thuTu;
	}
	public void setThuTu(int thuTu) {
		this.thuTu = thuTu;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
}
