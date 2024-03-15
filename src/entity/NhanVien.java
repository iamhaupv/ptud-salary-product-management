package entity;

import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;

public class NhanVien {
	private String maNV;
	private String hoTen;
	private int phai;
	private Date ngaySinh;
	private String diaChi;
	private String email;
	private String phone;
	private Date ngayThamGiaCongTac;
	private String trinhDoChuyenMon;
	private String kinhNghiem;
	private String chucVu;
	private PhongBan phong;
	private byte[] hinhAnh;
	private String trangThai ;
	public NhanVien() {
		// TODO Auto-generated constructor stub
	}
	public NhanVien(String maNV) {
		this.maNV = maNV;
	}
	public NhanVien(String maNV, String chucVu) {
		this.maNV = maNV;
		this.chucVu = chucVu;
	}
	public NhanVien(String maNV, String hoTen, PhongBan phong) {
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.phong = phong;
	}
	public NhanVien(String maNV, String hoTen, String phone) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.phone = phone;
	}
	public NhanVien(String maNV, String hoTen, int phai, Date ngaySinh, String diaChi, String email, String phone,
			Date ngayThamGiaCongTac, String trinhDoChuyenMon, String kinhNghiem, String chucVu, PhongBan phong,
			byte[] hinhAnh) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.phai = phai;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.email = email;
		this.phone = phone;
		this.ngayThamGiaCongTac = ngayThamGiaCongTac;
		this.trinhDoChuyenMon = trinhDoChuyenMon;
		this.kinhNghiem = kinhNghiem;
		this.chucVu = chucVu;
		this.phong = phong;
		this.hinhAnh = hinhAnh;
	}
	
	public NhanVien(String maNV, String hoTen, int phai, Date ngaySinh, String diaChi, String email, String phone,
			Date ngayThamGiaCongTac, String trinhDoChuyenMon, String kinhNghiem, String chucVu, PhongBan phong,
			byte[] hinhAnh, String trangThai) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.phai = phai;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.email = email;
		this.phone = phone;
		this.ngayThamGiaCongTac = ngayThamGiaCongTac;
		this.trinhDoChuyenMon = trinhDoChuyenMon;
		this.kinhNghiem = kinhNghiem;
		this.chucVu = chucVu;
		this.phong = phong;
		this.hinhAnh = hinhAnh;
		this.trangThai = trangThai;
	}
	
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getTrinhDoChuyenMon() {
		return trinhDoChuyenMon;
	}
	public void setTrinhDoChuyenMon(String trinhDoChuyenMon) {
		this.trinhDoChuyenMon = trinhDoChuyenMon;
	}
	public String getKinhNghiem() {
		return kinhNghiem;
	}
	public void setKinhNghiem(String kinhNghiem) {
		this.kinhNghiem = kinhNghiem;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
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
		return Objects.hash(maNV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoTen=" + hoTen + ", phai=" + phai + ", ngaySinh=" + ngaySinh + ", diaChi="
				+ diaChi + ", email=" + email + ", phone=" + phone + ", ngayThamGiaCongTac=" + ngayThamGiaCongTac
				+ ", trinhDoChuyenMon=" + trinhDoChuyenMon + ", kinhNghiem=" + kinhNghiem + ", chucVu=" + chucVu
				+ ", phong=" + phong + ", hinhAnh=" + Arrays.toString(hinhAnh) + "]";
	}
	
}
