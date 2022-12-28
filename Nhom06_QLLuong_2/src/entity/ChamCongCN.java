package entity;

import java.time.LocalDate;
import java.util.Objects;

public class ChamCongCN {
	private String maCong;
	private int soSP;
	private LocalDate ngayCham;
	private CaLamCN maCa;
	private boolean nghiPhep;
	private PhanCongCN maPC;
	private boolean trangThai;

	public ChamCongCN(String maCong, int soSP, LocalDate ngayCham, CaLamCN maCa, boolean nghiPhep, PhanCongCN maPC,
			boolean trangThai) {
		super();
		this.maCong = maCong;
		this.soSP = soSP;
		this.ngayCham = ngayCham;
		this.maCa = maCa;
		this.nghiPhep = nghiPhep;
		this.maPC = maPC;
		this.trangThai = trangThai;
	}

	public ChamCongCN() {
		super();
	}

	public String getMaCong() {
		return maCong;
	}

	public void setMaCong(String maCong) {
		this.maCong = maCong;
	}

	public int getSoSP() {
		return soSP;
	}

	public void setSoSP(int soSP) {
		this.soSP = soSP;
	}

	public LocalDate getNgayCham() {
		return ngayCham;
	}

	public void setNgayCham(LocalDate ngayCham) {
		this.ngayCham = ngayCham;
	}

	public CaLamCN getMaCa() {
		return maCa;
	}

	public void setMaCa(CaLamCN maCa) {
		this.maCa = maCa;
	}

	public boolean isNghiPhep() {
		return nghiPhep;
	}

	public void setNghiPhep(boolean nghiPhep) {
		this.nghiPhep = nghiPhep;
	}

	public PhanCongCN getMaPC() {
		return maPC;
	}

	public void setMaPC(PhanCongCN maPC) {
		this.maPC = maPC;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "ChamCongCN [maCong=" + maCong + ", soSP=" + soSP + ", ngayCham=" + ngayCham + ", maCa=" + maCa
				+ ", nghiPhep=" + nghiPhep + ", maPC=" + maPC + ", trangThai=" + trangThai + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maCa, maCong, maPC, ngayCham, nghiPhep, soSP, trangThai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChamCongCN other = (ChamCongCN) obj;
		return Objects.equals(maCa, other.maCa) && Objects.equals(maCong, other.maCong)
				&& Objects.equals(maPC, other.maPC) && Objects.equals(ngayCham, other.ngayCham)
				&& nghiPhep == other.nghiPhep && soSP == other.soSP && trangThai == other.trangThai;
	}

}
