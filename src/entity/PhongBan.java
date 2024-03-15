package entity;

import java.util.Objects;

public class PhongBan {
	private String maPB;
	private String tenPhong;
	public PhongBan() {
		// TODO Auto-generated constructor stub
	}
	public PhongBan(String maPB) {
		// TODO Auto-generated constructor stub
		this.maPB = maPB;
	}
	public PhongBan(String maPB, String tenPhong) {
		super();
		this.maPB = maPB;
		this.tenPhong = tenPhong;
	}
	public String getMaPB() {
		return maPB;
	}
	public void setMaPB(String maPB) {
		this.maPB = maPB;
	}
	public String getTenPhong() {
		return tenPhong;
	}
	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}
	@Override
	public String toString() {
		return "PhongBan [maPB=" + maPB + ", tenPhong=" + tenPhong + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPB);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhongBan other = (PhongBan) obj;
		return Objects.equals(maPB, other.maPB);
	}
	
}
