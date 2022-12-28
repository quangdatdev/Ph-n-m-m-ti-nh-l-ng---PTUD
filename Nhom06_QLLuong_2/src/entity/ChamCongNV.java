package entity;


import java.time.LocalDate;
import java.util.Objects;

public class ChamCongNV {
	private String maCong;
	private LocalDate ngayCham;
	private boolean trangThai;
	private boolean nghiPhep;
	private NhanVien nv;
	private CaLamNV maCa;

	public String getMaCong() {
		return maCong;
	}

	public void setMaCong(String maCong) {
		this.maCong = maCong;
	}

	public LocalDate getNgayCham() {
		return ngayCham;
	}

	public void setNgayCham(LocalDate ngayCham) {
		this.ngayCham = ngayCham;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public boolean isNghiPhep() {
		return nghiPhep;
	}

	public void setNghiPhep(boolean nghiPhep) {
		this.nghiPhep = nghiPhep;
	}

	public NhanVien getNv() {
		return nv;
	}

	public void setNv(NhanVien nv) {
		this.nv = nv;
	}

	public CaLamNV getMaCa() {
		return maCa;
	}

	public void setMaCa(CaLamNV maCa) {
		this.maCa = maCa;
	}

	

	public ChamCongNV(String maCong, LocalDate ngayCham, boolean trangThai, boolean nghiPhep, NhanVien nv,
			CaLamNV maCa) {
		super();
		this.maCong = maCong;
		this.ngayCham = ngayCham;
		this.trangThai = trangThai;
		this.nghiPhep = nghiPhep;
		this.nv = nv;
		this.maCa = maCa;
	}

	public ChamCongNV() {
	}

	@Override
	public String toString() {
		return "ChamCongNV [maCong=" + maCong + ", ngayCham=" + ngayCham + ", trangThai=" + trangThai + ", nghiPhep="
				+ nghiPhep + ", nv=" + nv + ", maCa=" + maCa + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maCa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChamCongNV other = (ChamCongNV) obj;
		return Objects.equals(maCa, other.maCa);
	}

}