package entity;

import java.util.Objects;

public class TayNghe {
	private String maTN;
	private String tenTN;

	public String getMaTN() {
		return maTN;
	}

	public void setMaTN(String maTN) {
		this.maTN = maTN;
	}

	public String getTenTN() {
		return tenTN;
	}

	public void setTenTN(String tenTN) {
		this.tenTN = tenTN;
	}

	public TayNghe(String maTN, String tenTN) {
		super();
		this.maTN = maTN;
		this.tenTN = tenTN;
	}

	public TayNghe() {
		super();
	}

	public TayNghe(String maTN) {
		super();
		this.maTN = maTN;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maTN);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TayNghe other = (TayNghe) obj;
		return Objects.equals(maTN, other.maTN);
	}

	@Override
	public String toString() {
		return "TayNghe [maTN=" + maTN + ", tenTN=" + tenTN + "]";
	}

}
