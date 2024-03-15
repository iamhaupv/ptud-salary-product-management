package entity;

import java.util.Arrays;
import java.util.Objects;

public class SanPham {
	private String maSP;
	private String tenSanPham;
	private String donViTinh;
	private int soCongDoan;
	private String chatLieu;
	private int kichThuoc;
	private String mauSac;
	private double donGia;
	private byte[] hinhAnh;
	private String trangThai;
	public SanPham() {
		// TODO Auto-generated constructor stub
	}
	public SanPham(String maSP) {
		// TODO Auto-generated constructor stub
		this.maSP = maSP;
	}
	
	public SanPham(String maSP, String tenSanPham) {
		// TODO Auto-generated constructor stub
		this.maSP = maSP;
		this.tenSanPham = tenSanPham;
	}
	
	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSanPham=" + tenSanPham + ", donViTinh=" + donViTinh + ", soCongDoan="
				+ soCongDoan + ", chatLieu=" + chatLieu + ", kichThuoc=" + kichThuoc + ", mauSac=" + mauSac
				+ ", donGia=" + donGia + ", hinhAnh=" + Arrays.toString(hinhAnh) + ", trangThai=" + trangThai + "]";
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public int getSoCongDoan() {
		return soCongDoan;
	}
	public void setSoCongDoan(int soCongDoan) {
		this.soCongDoan = soCongDoan;
	}
	public String getChatLieu() {
		return chatLieu;
	}
	public void setChatLieu(String chatLieu) {
		this.chatLieu = chatLieu;
	}
	public int getKichThuoc() {
		return kichThuoc;
	}
	public void setKichThuoc(int kichThuoc) {
		this.kichThuoc = kichThuoc;
	}
	public String getMauSac() {
		return mauSac;
	}
	public void setMauSac(String mauSac) {
		this.mauSac = mauSac;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public byte[] getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public SanPham(String maSP, String tenSanPham, String donViTinh, int soCongDoan, String chatLieu, int kichThuoc,
			String mauSac, double donGia, byte[] hinhAnh, String trangThai) {
		super();
		this.maSP = maSP;
		this.tenSanPham = tenSanPham;
		this.donViTinh = donViTinh;
		this.soCongDoan = soCongDoan;
		this.chatLieu = chatLieu;
		this.kichThuoc = kichThuoc;
		this.mauSac = mauSac;
		this.donGia = donGia;
		this.hinhAnh = hinhAnh;
		this.trangThai = trangThai;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		return Objects.equals(maSP, other.maSP);
	}
}
