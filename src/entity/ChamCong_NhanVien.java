package entity;

import java.sql.Date;
import java.util.Objects;

public class ChamCong_NhanVien {
	private String maCCNV ;
	private boolean diemDanh ;
	private boolean phep;
	private Date ngayChamCong ;
	private NhanVien nhanVien ;
	public ChamCong_NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChamCong_NhanVien(String maCCNV) {
		this.maCCNV = maCCNV;
	}
	public ChamCong_NhanVien(String maCCNV, NhanVien nhanVien, Date ngayChamCong) {
		this.maCCNV = maCCNV;
		this.nhanVien = nhanVien;
		this.ngayChamCong = ngayChamCong;
	}
	public ChamCong_NhanVien(String maCCNV, boolean diemDanh, boolean phep, Date ngayChamCong, NhanVien nhanVien) {
		super();
		this.maCCNV = maCCNV;
		this.diemDanh = diemDanh;
		this.phep = phep;
		this.ngayChamCong = ngayChamCong;
		this.nhanVien = nhanVien;
	}
	public String getMaCCNV() {
		return maCCNV;
	}
	public void setMaCCNV(String maCCNV) {
		this.maCCNV = maCCNV;
	}
	public boolean isDiemDanh() {
		return diemDanh;
	}
	public void setDiemDanh(boolean diemDanh) {
		this.diemDanh = diemDanh;
	}
	public boolean isPhep() {
		return phep;
	}
	public void setPhep(boolean phep) {
		this.phep = phep;
	}
	public Date getNgayChamCong() {
		return ngayChamCong;
	}
	public void setNgayChamCong(Date ngayChamCong) {
		this.ngayChamCong = ngayChamCong;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maCCNV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChamCong_NhanVien other = (ChamCong_NhanVien) obj;
		return Objects.equals(maCCNV, other.maCCNV);
	}
	
}
