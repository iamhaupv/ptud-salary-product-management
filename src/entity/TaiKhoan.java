package entity;

import java.sql.Date;

public class TaiKhoan {
	private String taiKhoan;
	private String matKhau;
	private NhanVien nvhc;
	private Date ngayThem;
	private Date ngayCapNhat;
	private String vaiTro;
	public TaiKhoan() {
		// TODO Auto-generated constructor stub
	}
	public TaiKhoan(String taiKhoan, String matKhau) {
		// TODO Auto-generated constructor stub
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
	}
	public TaiKhoan(NhanVien nvhc) {
		// TODO Auto-generated constructor stub
		this.nvhc = nvhc;
	}

	public TaiKhoan(String taiKhoan, String matKhau, NhanVien nvhc, Date ngayThem, Date ngayCapNhat, String vaiTro) {
		super();
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.nvhc = nvhc;
		this.ngayThem = ngayThem;
		this.ngayCapNhat = ngayCapNhat;
		this.vaiTro = vaiTro;
	}
	public String getVaiTro() {
		return vaiTro;
	}
	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
	}
	public String getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public NhanVien getNvhc() {
		return nvhc;
	}
	public void setNvhc(NhanVien nvhc) {
		this.nvhc = nvhc;
	}

	
	@Override
	public String toString() {
		return "TaiKhoan [taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + ", nvhc=" + nvhc + ", ngayThem=" + ngayThem
				+ ", ngayCapNhat=" + ngayCapNhat + ", vaiTro=" + vaiTro + "]";
	}
	public Date getNgayThem() {
		return ngayThem;
	}
	public void setNgayThem(Date ngayThem) {
		this.ngayThem = ngayThem;
	}
	public Date getNgayCapNhat() {
		return ngayCapNhat;
	}
	public void setNgayCapNhat(Date ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}

}
