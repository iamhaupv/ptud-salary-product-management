package entity;

import java.sql.Date;
import java.util.Objects;

public class ChamCong_CongNhan {
	private String maCCCN;
	private boolean diemDanh;
	private Date ngayChamCong;
	private String caLamViec;
	private int soCongDoanHoanThanh;
	private CongNhan congNhan;
	private CongDoan congDoan;
	private boolean phep ;
	public ChamCong_CongNhan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChamCong_CongNhan(String maCCCN, boolean diemDanh, Date ngayChamCong, String caLamViec,
			int soCongDoanHoanThanh, CongNhan congNhan, boolean phep) {
		super();
		this.maCCCN = maCCCN;
		this.diemDanh = diemDanh;
		this.ngayChamCong = ngayChamCong;
		this.caLamViec = caLamViec;
		this.soCongDoanHoanThanh = soCongDoanHoanThanh;
		this.congNhan = congNhan;
		this.phep = phep;
	}
	
	public ChamCong_CongNhan(String maCCCN) {
		super();
		this.maCCCN = maCCCN;
	}
	
	public ChamCong_CongNhan(String maCCCN, boolean diemDanh, Date ngayChamCong, String caLamViec,
			int soCongDoanHoanThanh, CongNhan congNhan, CongDoan congDoan, boolean phep) {
		super();
		this.maCCCN = maCCCN;
		this.diemDanh = diemDanh;
		this.ngayChamCong = ngayChamCong;
		this.caLamViec = caLamViec;
		this.soCongDoanHoanThanh = soCongDoanHoanThanh;
		this.congNhan = congNhan;
		this.congDoan = congDoan;
		this.phep = phep;
	}
	public CongDoan getCongDoan() {
		return congDoan;
	}
	public void setCongDoan(CongDoan congDoan) {
		this.congDoan = congDoan;
	}
	@Override
	public String toString() {
		return "ChamCong_CongNhan [maCCCN=" + maCCCN + ", diemDanh=" + diemDanh + ", ngayChamCong=" + ngayChamCong
				+ ", caLamViec=" + caLamViec + ", soCongDoanHoanThanh=" + soCongDoanHoanThanh + ", congNhan=" + congNhan
				+ ", phep=" + phep + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(maCCCN);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ChamCong_CongNhan)) {
			return false;
		}
		ChamCong_CongNhan other = (ChamCong_CongNhan) obj;
		return Objects.equals(maCCCN, other.maCCCN);
	}
	/**
	 * @return the maCCCN
	 */
	public String getMaCCCN() {
		return maCCCN;
	}
	/**
	 * @param maCCCN the maCCCN to set
	 */
	public void setMaCCCN(String maCCCN) {
		this.maCCCN = maCCCN;
	}
	/**
	 * @return the diemDanh
	 */
	public boolean isDiemDanh() {
		return diemDanh;
	}
	/**
	 * @param diemDanh the diemDanh to set
	 */
	public void setDiemDanh(boolean diemDanh) {
		this.diemDanh = diemDanh;
	}
	/**
	 * @return the ngayChamCong
	 */
	public Date getNgayChamCong() {
		return ngayChamCong;
	}
	/**
	 * @param ngayChamCong the ngayChamCong to set
	 */
	public void setNgayChamCong(Date ngayChamCong) {
		this.ngayChamCong = ngayChamCong;
	}
	/**
	 * @return the caLamViec
	 */
	public String getCaLamViec() {
		return caLamViec;
	}
	/**
	 * @param caLamViec the caLamViec to set
	 */
	public void setCaLamViec(String caLamViec) {
		this.caLamViec = caLamViec;
	}
	/**
	 * @return the soCongDoanHoanThanh
	 */
	public int getSoCongDoanHoanThanh() {
		return soCongDoanHoanThanh;
	}
	/**
	 * @param soCongDoanHoanThanh the soCongDoanHoanThanh to set
	 */
	public void setSoCongDoanHoanThanh(int soCongDoanHoanThanh) {
		this.soCongDoanHoanThanh = soCongDoanHoanThanh;
	}
	/**
	 * @return the congNhan
	 */
	public CongNhan getCongNhan() {
		return congNhan;
	}
	/**
	 * @param congNhan the congNhan to set
	 */
	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}
	/**
	 * @return the phep
	 */
	public boolean isPhep() {
		return phep;
	}
	/**
	 * @param phep the phep to set
	 */
	public void setPhep(boolean phep) {
		this.phep = phep;
	}
	
	
	
	
    
}
