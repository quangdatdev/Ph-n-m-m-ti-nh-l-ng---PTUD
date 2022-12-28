package entity;

import java.util.Objects;

public class NhanVien {
	private String maNV;
	private String hoTen;
	private String soDT;
	private String soCMND;
	private String diaChi;
	private String gioiTinh;
	private int namSinh;
	private TrinhDo trinhDo;
	private double luongCoBan;
	private double troCap;
	private double heSoLuong;

	public NhanVien(String maNV, String hoTen) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
	}

	// lay ma nhan vien lam ten dang nhap luon vay co can ten dang nhap o day khong

	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhanVien(String maNV, String hoTen, String soDT, String soCMND, String diaChi, String gioiTinh, int namSinh,
			TrinhDo trinhDo, double luongCoBan, double troCap, double heSoLuong) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.soDT = soDT;
		this.soCMND = soCMND;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.namSinh = namSinh;
		this.trinhDo = trinhDo;
		this.luongCoBan = luongCoBan;
		this.troCap = troCap;
		this.heSoLuong = heSoLuong;
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

	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}

	public String getSoCMND() {
		return soCMND;
	}

	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public int getNamSinh() {
		return namSinh;
	}

	public void setNamSinh(int namSinh) {
		this.namSinh = namSinh;
	}

	public TrinhDo getTrinhDo() {
		return trinhDo;
	}

	public void setTrinhDo(TrinhDo trinhDo) {
		this.trinhDo = trinhDo;
	}

	public double getLuongCoBan() {
		return luongCoBan;
	}

	public void setLuongCoBan(double luongCoBan) {
		this.luongCoBan = luongCoBan;
	}

	public double getTroCap() {
		return troCap;
	}

	public void setTroCap(double troCap) {
		this.troCap = troCap;
	}

	public double getHeSoLuong() {
		return heSoLuong;
	}

	public void setHeSoLuong(double heSoLuong) {
		this.heSoLuong = heSoLuong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}

	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
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

	public void NhanVien1(String maNV, String hoTen) {

		this.maNV = maNV;
		this.hoTen = hoTen;
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoTen=" + hoTen + ", soDT=" + soDT + ", soCMND=" + soCMND + ", diaChi="
				+ diaChi + ", gioiTinh=" + gioiTinh + ", namSinh=" + namSinh + ", trinhDo=" + trinhDo + ", luongCoBan="
				+ luongCoBan + ", troCap=" + troCap + ", heSoLuong=" + heSoLuong + "]";
	}

}
