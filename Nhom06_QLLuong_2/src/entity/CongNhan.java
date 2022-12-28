package entity;

import java.util.Objects;

public class CongNhan {
	private String maCN;
	private String hoTen;
	private String soDT;
	private String diaChi;
	private String soCMND;
	private int namSinh;
	private String gioiTinh;
	private TayNghe tayNghe;
	private double troCap;

	public CongNhan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CongNhan(String maCN, String hoTen, String soDT, String diaChi, String soCMND, int namSinh, String gioiTinh,
			TayNghe tayNghe, double troCap) {
		super();
		this.maCN = maCN;
		this.hoTen = hoTen;
		this.soDT = soDT;
		this.diaChi = diaChi;
		this.soCMND = soCMND;
		this.namSinh = namSinh;
		this.gioiTinh = gioiTinh;
		this.tayNghe = tayNghe;
		this.troCap = troCap;
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

	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoCMND() {
		return soCMND;
	}

	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}

	public int getNamSinh() {
		return namSinh;
	}

	public void setNamSinh(int namSinh) {
		this.namSinh = namSinh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public TayNghe getTayNghe() {
		return tayNghe;
	}

	public void setTayNghe(TayNghe tayNghe) {
		this.tayNghe = tayNghe;
	}

	public double getTroCap() {
		return troCap;
	}

	public void setTroCap(double troCap) {
		this.troCap = troCap;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maCN);
	}

	public CongNhan(String maCN) {
		super();
		this.maCN = maCN;
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

	@Override
	public String toString() {
		return "CongNhan [maCN=" + maCN + ", hoTen=" + hoTen + ", soDT=" + soDT + ", diaChi=" + diaChi + ", soCMND="
				+ soCMND + ", namSinh=" + namSinh + ", gioiTinh=" + gioiTinh + ", tayNghe=" + tayNghe + ", troCap="
				+ troCap + "]";
	}

}
