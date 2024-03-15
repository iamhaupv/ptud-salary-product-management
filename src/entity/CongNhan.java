package entity;

import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;

public class CongNhan {
	private String maCN;
	private String hoTen;
	private int phai;
	private Date ngaySinh;
	private String diaChi;
	private String phone;
	private Date ngayThamGiaCongTac;
	private PhongBan phong;
	private String chucVu;
	private byte[] hinhAnh;
	private String chuyenMon;
	private String trangThai; 
	private String tinhTrang;
	public CongNhan() {
		// TODO Auto-generated constructor stub
	}
	public CongNhan(String maCN) {
		// TODO Auto-generated constructor stub
		this.maCN = maCN;
	}
	public CongNhan(String maCN, String hoTen) {
		// TODO Auto-generated constructor stub
		this.maCN = maCN;
		this.hoTen = hoTen;
	}
	public CongNhan(String maCN, String hoTen, String phone) {
		super();
		this.maCN = maCN;
		this.hoTen = hoTen;
		this.phone = phone;
	}
	
	public CongNhan(String maCN, String hoTen, int phai, Date ngaySinh, String diaChi, String phone,
			Date ngayThamGiaCongTac, PhongBan phong, String chucVu, byte[] hinhAnh, String chuyenMon, String trangThai,
			String tinhTrang) {
		super();
		this.maCN = maCN;
		this.hoTen = hoTen;
		this.phai = phai;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.phone = phone;
		this.ngayThamGiaCongTac = ngayThamGiaCongTac;
		this.phong = phong;
		this.chucVu = chucVu;
		this.hinhAnh = hinhAnh;
		this.chuyenMon = chuyenMon;
		this.trangThai = trangThai;
		this.tinhTrang = tinhTrang;
	}
	public CongNhan(String maCN, String hoTen, int phai, Date ngaySinh, String diaChi, String phone,
			Date ngayThamGiaCongTac, PhongBan phong, String chucVu, byte[] hinhAnh, String chuyenMon, String trangThai) {
		super();
		this.maCN = maCN;
		this.hoTen = hoTen;
		this.phai = phai;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.phone = phone;
		this.ngayThamGiaCongTac = ngayThamGiaCongTac;
		this.phong = phong;
		this.chucVu = chucVu;
		this.hinhAnh = hinhAnh;
		this.chuyenMon = chuyenMon;
		this.trangThai = trangThai;
	}
	public CongNhan(String maCN, String hoTen, int phai, Date ngaySinh, String diaChi, String phone,
			Date ngayThamGiaCongTac, PhongBan phong, byte[] hinhAnh, String trangThai) {
		super();
		this.maCN = maCN;
		this.hoTen = hoTen;
		this.phai = phai;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.phone = phone;
		this.ngayThamGiaCongTac = ngayThamGiaCongTac;
		this.phong = phong;
		this.hinhAnh = hinhAnh;
		this.chuyenMon = chuyenMon;
		this.trangThai = trangThai;
	}
	
	public CongNhan(String maCN, String hoTen, int phai, Date ngaySinh, String diaChi, String phone,
			Date ngayThamGiaCongTac, PhongBan phong, String tinhTrang) {
		super();
		this.maCN = maCN;
		this.hoTen = hoTen;
		this.phai = phai;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.phone = phone;
		this.ngayThamGiaCongTac = ngayThamGiaCongTac;
		this.phong = phong;
		this.tinhTrang = tinhTrang;
	}
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public String getChuyenMon() {
		return chuyenMon;
	}
	public void setChuyenMon(String chuyenMon) {
		this.chuyenMon = chuyenMon;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	public int getPhai() {
		return phai;
	}
	public String getMaCN() {
		return maCN;
	}
	public void setMaCN(String maCN) {
		this.maCN = maCN;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public int isPhai() {
		return phai;
	}
	public void setPhai(int phai) {
		this.phai = phai;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getNgayThamGiaCongTac() {
		return ngayThamGiaCongTac;
	}
	public void setNgayThamGiaCongTac(Date ngayThamGiaCongTac) {
		this.ngayThamGiaCongTac = ngayThamGiaCongTac;
	}
	public PhongBan getPhong() {
		return phong;
	}
	public void setPhong(PhongBan phong) {
		this.phong = phong;
	}
	public byte[] getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maCN);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CongNhan other = (CongNhan) obj;
		return Objects.equals(maCN, other.maCN);
	}
	
	
	
}
