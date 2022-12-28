package entity;

import java.time.LocalDate;
import java.util.Objects;

public class PhanCongCN {
	private String maPC;
	private CongNhan maCN;//
	private CongDoanSanXuat cDSX;//
	private int soLuong;
	private LocalDate ngay;

	public PhanCongCN(String maPC, CongNhan maCN, CongDoanSanXuat maCD, int soLuong, LocalDate ngay) {
		super();
		this.maPC = maPC;
		this.maCN = maCN;
		this.cDSX = maCD;
		this.soLuong = soLuong;
		this.ngay = ngay;
	}

	public PhanCongCN() {
		super();
	}

	public String getMaPC() {
		return maPC;
	}

	public CongDoanSanXuat getcDSX() {
		return cDSX;
	}

	public void setcDSX(CongDoanSanXuat cDSX) {
		this.cDSX = cDSX;
	}

	public void setMaPC(String maPC) {
		this.maPC = maPC;
	}

	public CongNhan getMaCN() {
		return maCN;
	}

	public void setMaCN(CongNhan maCN) {
		this.maCN = maCN;
	}

	public CongDoanSanXuat getCDSX() {
		return cDSX;
	}

	public void setCDSX(CongDoanSanXuat cDSX) {
		this.cDSX = cDSX;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public LocalDate getNgay() {
		return ngay;
	}

	public void setNgay(LocalDate ngay) {
		this.ngay = ngay;
	}

	@Override
	public String toString() {
		return "PhanCongCN [maPC=" + maPC + ", maCN=" + maCN + ", maCD=" + cDSX + ", soLuong=" + soLuong + ", ngay="
				+ ngay + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPC);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhanCongCN other = (PhanCongCN) obj;
		return Objects.equals(maPC, other.maPC);
	}

}
