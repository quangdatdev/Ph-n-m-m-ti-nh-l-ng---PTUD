package entity;

import java.util.Objects;

public class LuongCN {
	private String maLuongCN;
	private int thang;
	private int nam;
//	private CongNhan cn;
	private ChamCongCN cc;
	private double luong;

	public LuongCN(String maLuongCN, int thang, int nam, ChamCongCN cc, double luong) {
		super();
		this.maLuongCN = maLuongCN;
		this.thang = thang;
		this.nam = nam;
		this.cc = cc;
		this.luong = luong;
	}

	public LuongCN() {
		super();
	}

	public String getMaLuongCN() {
		return maLuongCN;
	}

	public void setMaLuongCN(String maLuongCN) {
		this.maLuongCN = maLuongCN;
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

	public ChamCongCN getCc() {
		return cc;
	}

	public void setCc(ChamCongCN cc) {
		this.cc = cc;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	@Override
	public String toString() {
		return "LuongCN [maLuongCN=" + maLuongCN + ", thang=" + thang + ", nam=" + nam + ", cc=" + cc + ", luong="
				+ luong + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLuongCN);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LuongCN other = (LuongCN) obj;
		return Objects.equals(maLuongCN, other.maLuongCN);
	}

}