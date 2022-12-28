package entity;

import java.util.Objects;

public class LuongNV {
	private String maLuongNV;
	private int thang;
	private int nam;
	private ChamCongNV cCNV;
	private double luong;

	public String getMaLuongNV() {
		return maLuongNV;
	}

	public void setMaLuongNV(String maLuongNV) {
		this.maLuongNV = maLuongNV;
	}

	public int getThang() {
		return thang;
	}

	public void setThang(int thang) {
		this.thang = thang;
	}

	public int getNam() {
		return nam;
	}

	public void setNam(int nam) {
		this.nam = nam;
	}

	public ChamCongNV getcCNV() {
		return cCNV;
	}

	public void setcCNV(ChamCongNV cCNV) {
		this.cCNV = cCNV;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	public LuongNV() {
		super();
	}

	public LuongNV(String maLuongNV, int thang, int nam, ChamCongNV cCNV, double luong) {
		super();
		this.maLuongNV = maLuongNV;
		this.thang = thang;
		this.nam = nam;
		this.cCNV = cCNV;
		this.luong = luong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLuongNV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LuongNV other = (LuongNV) obj;
		return Objects.equals(maLuongNV, other.maLuongNV);
	}

	@Override
	public String toString() {
		return "LuongNV [maLuongNV=" + maLuongNV + ", thang=" + thang + ", nam=" + nam + ", cCNV=" + cCNV + ", luong="
				+ luong + "]";
	}

}