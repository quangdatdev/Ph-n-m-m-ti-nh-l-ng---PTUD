package entity;

import java.util.Objects;

public class CongDoanSanXuat {
	private String maCD;
	private String tenCD;
	private double gia;
	private int soLuong;
	private int giaiDoan;
	private SanPham sanPham;//
	public CongDoanSanXuat() {
		
	}
	public CongDoanSanXuat(String maCD, String tenCD, double gia, int soLuong, int giaiDoan, SanPham sanPham) {
		super();
		this.maCD = maCD;
		this.tenCD = tenCD;
		this.gia = gia;
		this.soLuong = soLuong;
		this.giaiDoan = giaiDoan;
		this.sanPham = sanPham;
	}
	public String getMaCD() {
		return maCD;
	}
	public void setMaCD(String maCD) {
		this.maCD = maCD;
	}
	public String getTenCD() {
		return tenCD;
	}
	public void setTenCD(String tenCD) {
		this.tenCD = tenCD;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public int getGiaiDoan() {
		return giaiDoan;
	}
	public void setGiaiDoan(int giaiDoan) {
		this.giaiDoan = giaiDoan;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	@Override
	public int hashCode() {
		return Objects.hash(gia);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CongDoanSanXuat other = (CongDoanSanXuat) obj;
		return Double.doubleToLongBits(gia) == Double.doubleToLongBits(other.gia);
	}
	
}
