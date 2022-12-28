package entity;

import java.util.Objects;

public class CaLamCN {
	private String maCa;
	private String tenCa;
	private String gioTheoCa;
	private double luongTheoCa;

	public String getMaCa() {
		return maCa;
	}

	public void setMaCa(String maCa) {
		this.maCa = maCa;
	}

	public String getTenCa() {
		return tenCa;
	}

	public void setTenCa(String tenCa) {
		this.tenCa = tenCa;
	}

	public String getGioTheoCa() {
		return gioTheoCa;
	}

	public void setGioTheoCa(String gioTheoCa) {
		this.gioTheoCa = gioTheoCa;
	}

	public double getLuongTheoCa() {
		return luongTheoCa;
	}

	public void setLuongTheoCa(double luongTheoCa) {
		this.luongTheoCa = luongTheoCa;
	}

	public CaLamCN(String maCa, String tenCa, String gioTheoCa, double luongTheoCa) {
		super();
		this.maCa = maCa;
		this.tenCa = tenCa;
		this.gioTheoCa = gioTheoCa;
		this.luongTheoCa = luongTheoCa;
	}

	public CaLamCN() {
		super();
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
		CaLamCN other = (CaLamCN) obj;
		return Objects.equals(maCa, other.maCa);
	}

	@Override
	public String toString() {
		return "CaLamCN [maCa=" + maCa + ", tenCa=" + tenCa + ", gioTheoCa=" + gioTheoCa + ", luongTheoCa="
				+ luongTheoCa + "]";
	}

}