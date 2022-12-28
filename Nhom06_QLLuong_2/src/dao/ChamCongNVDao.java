package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.CaLamNV;

import entity.ChamCongNV;

import entity.NhanVien;

import util.ConnectDB;

public class ChamCongNVDao {

	private CaLamNVDao caNVDao;
	private NVDao nvDao;

	public ChamCongNVDao() {
		caNVDao = new CaLamNVDao();
		nvDao = new NVDao();
	}

	private ChamCongNV createChamCongNV(final ResultSet rs) throws Exception {
		String maCong, maNV, maCa;
		LocalDate ngayCham;

		boolean trangThai, nghiPhep;

		maCong = rs.getString("maCong");
		LocalDate date2 = rs.getDate("ngayCham").toLocalDate();
		ngayCham = date2;
		trangThai = rs.getBoolean("trangThai") ? true : false;
		nghiPhep = rs.getBoolean("nghiPhep") ? true : false;
		maNV = rs.getString("maNV");
		maCa = rs.getString("maCa");

		NhanVien nv = nvDao.getNVTheoMa(maNV);
		CaLamNV ca = caNVDao.getCaTheoMa(maCa);

		ChamCongNV cc = new ChamCongNV(maCong, ngayCham, trangThai, nghiPhep, nv, ca);

		return cc;
	}

	public List<ChamCongNV> getAllChamCongNV() throws Exception {
		String sql = "Select * from ChamCongNV";
		Connection con = ConnectDB.getDataBase().getConnection();
		List<ChamCongNV> list = new ArrayList<ChamCongNV>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ChamCongNV cc = new ChamCongNV();
				cc = createChamCongNV(rs);

				list.add(cc);
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return list;
	}

	public boolean themCongNV(ChamCongNV cc) throws SQLException {
		String sql = "insert into [dbo].[ChamCongNV] ([maCong],[ngayCham],[trangThai],[nghiPhep],[maNV],[maCa])"
				+ "VALUES(?,?,?,?,?,?)";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			LocalDate input = cc.getNgayCham();
			Date date = Date.valueOf(input);
			stmt.setString(1, cc.getMaCong());
			stmt.setDate(2, date);
			stmt.setBoolean(3, cc.isTrangThai() ? true : false);
			stmt.setBoolean(4, cc.isNghiPhep() ? true : false);
			stmt.setString(5, cc.getNv().getMaNV());
			stmt.setString(6, cc.getMaCa().getMaCa());

			stmt.executeUpdate();
			con.commit();
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return false;
	}

	public boolean xoaCongNV(String id) throws SQLException {
		String sql = "delete from ChamCongNV where maCong =?";
		int n = 0;
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			n = stmt.executeUpdate();
			con.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
		}
		return false;
	}

	public List<ChamCongNV> getCCByNgayCham(int ngay, int thang, int nam) throws SQLException {
		Connection con = ConnectDB.getDataBase().getConnection();
		List<ChamCongNV> list = new ArrayList<ChamCongNV>();
		try {
			String sql = "select maCong, ngayCham, trangThai, nghiPhep, maNV, maCa from ChamCongNV where DAY(ngayCham)= "
					+ ngay + "\r\n" + "	and MONTH(ngayCham) = " + thang + "\r\n" + "	and YEAR(ngayCham) = " + nam;
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ChamCongNV ccNV = new ChamCongNV();
				ccNV = createChamCongNV(rs);
				list.add(ccNV);
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
		}
		return list;
	}

	public ChamCongNV getCongTheoMaCong(String maCong) throws SQLException {
		List<ChamCongNV> list = new ArrayList<ChamCongNV>();
		if (maCong != "") {
			String sql = "select * from ChamCongNV where maCong = ?";
			Connection con = ConnectDB.getDataBase().getConnection();
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, maCong);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					ChamCongNV cc = new ChamCongNV();
					cc = createChamCongNV(rs);
					return cc;
				}
				con.commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				con.rollback();
			}
		}
		return null;
	}

	public List<Object[]> dsCCNV(String maNV, int thang, int nam) throws SQLException {
		List<Object[]> listOB = new ArrayList<Object[]>();
		String sql = "SELECT ChamCongNV.maCong, ChamCongNV.ngayCham, ChamCongNV.trangThai, ChamCongNV.nghiPhep, CaLamNV.maCa, CaLamNV.tenCa, CaLamNV.gioTheoCa, CaLamNV.luongTheoCa\r\n"
				+ "FROM     ChamCongNV INNER JOIN\r\n"
				+ "                  CaLamNV ON ChamCongNV.maCa = CaLamNV.maCa INNER JOIN\r\n"
				+ "                  NhanVien ON ChamCongNV.maNV = NhanVien.maNV\r\n"
				+ "where NhanVien.maNV = ? and MONTH(ngayCham) = ? and YEAR(ngayCham) = ?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maNV);
			stmt.setInt(2, thang);
			stmt.setInt(3, nam);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maCong = rs.getString("maCong");
				LocalDate ngayCham = rs.getDate("ngayCham").toLocalDate();
				boolean trangThai = rs.getBoolean("trangThai");
				boolean nghiPhep = rs.getBoolean("nghiPhep");
				String maCa = rs.getString("maCa");
				String tenCa = rs.getString("tenCa");
				String gioTheoCa = rs.getString("gioTheoCa");
				double luongTheoCa= rs.getDouble("luongTheoCa");
//				double luong = rs.getDouble("luongTheoCa");
				Object[] o = { maCong, ngayCham, trangThai, nghiPhep, maCa, tenCa, gioTheoCa, luongTheoCa };
				listOB.add(o);
			}
			con.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return listOB;
	}
}
