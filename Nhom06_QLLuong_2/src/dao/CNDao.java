package dao;

import entity.CongNhan;

import entity.TayNghe;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import util.ConnectDB;

public class CNDao {

	ArrayList<CongNhan> dscn;
	private TayNgheDao tnDao;

	public CNDao() {
		dscn = new ArrayList<CongNhan>();
		tnDao = new TayNgheDao();
	}

	private CongNhan createCongNhan(final ResultSet rs) throws Exception {
		String maCN, tenCN, soCMND, diaChi, soDienThoai, gioiTinh;
		int namSinh;
		double troCap;

		maCN = rs.getString("maCN");
		tenCN = rs.getString("hoTen");
		soCMND = rs.getString("soCMND");
		namSinh = rs.getInt("namSinh");
		gioiTinh = rs.getString("gioiTinh");
		diaChi = rs.getString("diaChi");
		soDienThoai = rs.getString("sdt");
//		tayNghe = rs.getString("maTayNghe");

		TayNghe tn = tnDao.getTayNgheTheoMa(rs.getString("maTayNghe"));

		troCap = rs.getDouble("troCap");
		CongNhan cn = new CongNhan(maCN, tenCN, soDienThoai, diaChi, soCMND, namSinh, gioiTinh, tn, troCap);
		return cn;
	}

	public List<CongNhan> getAllCongNhan() throws Exception {
		String sql = "select * from CongNhan";
		Connection con = ConnectDB.getDataBase().getConnection();
		List<CongNhan> list = new ArrayList<CongNhan>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				CongNhan cn = createCongNhan(rs);

				list.add(cn);
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return list;
	}

	public CongNhan getCNTheoMa(String maCN) throws SQLException {
		String sql = "select * from CongNhan where maCN =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, maCN);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {

				CongNhan cn = new CongNhan();
				cn = createCongNhan(rs);
				return cn;
			}

			con.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return null;
	}

	public List<CongNhan> timCN(String maCN, String tenCN, String sDT, String diaChi, String soCMND, String gioiTinh)
			throws SQLException {

		List<CongNhan> list = new ArrayList<CongNhan>();
		if (maCN != "") {
			String sql = "select * from CongNhan where maCN =? ";

			Connection con = ConnectDB.getDataBase().getConnection();
			try {
				PreparedStatement stmt = con.prepareStatement(sql);

				stmt.setString(1, maCN);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					CongNhan cn = new CongNhan();
					cn = createCongNhan(rs);
					list.add(cn);

				}

				con.commit();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				con.rollback();
			}
			return list;
		} else {
			tenCN = tenCN.trim();
			sDT = sDT.trim();
			diaChi = diaChi.trim();
			soCMND = soCMND.trim();
			gioiTinh = gioiTinh.trim();

			String txtTenCN = tenCN == null ? "" : ("" + tenCN);
			String txtSoDT = sDT == null ? "" : ("and sdt like N'%" + sDT + "%'");
			String txtDiaChi = diaChi == null ? "" : ("and diaChi like N'%" + diaChi + "%'");
			String txtCMND = soCMND == null ? "" : ("and soCMND like N'%" + soCMND + "%'");
			String cboGioiTinh = gioiTinh == null ? "" : ("and gioiTinh like N'%" + gioiTinh + "%'");

			String sql = "select * from CongNhan where hoTen like N'%" + txtTenCN + "%'" + txtSoDT + txtDiaChi + txtCMND
					+ cboGioiTinh;

			Connection con = ConnectDB.getDataBase().getConnection();
			try {
				PreparedStatement stmt = con.prepareStatement(sql);

				// stmt.setString(1, tenSP);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					CongNhan cn = new CongNhan();
					cn = createCongNhan(rs);
					list.add(cn);
				}

				con.commit();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				con.rollback();

			}
		}
		return list;
	}

	public boolean themCongNhan(CongNhan cn) throws SQLException {
		String sql = "insert into [dbo].[CongNhan] (maCN,hoten,sdt, diaChi, soCMND, namSinh, gioiTinh, maTayNghe, troCap)"
				+ "VALUES(?,?,?,?,?,?,?,?,?)";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, cn.getMaCN());
			stmt.setString(2, cn.getHoTen());
			stmt.setString(3, cn.getSoDT());
			stmt.setString(4, cn.getDiaChi());
			stmt.setString(5, cn.getSoCMND());
			stmt.setInt(6, cn.getNamSinh());
			stmt.setString(7, cn.getGioiTinh());
			stmt.setString(8, cn.getTayNghe().getMaTN());
			stmt.setDouble(9, cn.getTroCap());

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

	public boolean xoaCN(String id) throws SQLException {
		String sql = "delete from CongNhan where maCN =?";
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

	public boolean suaCN(CongNhan cn) throws SQLException {
		int n = 0;
		String sql = "update CongNhan set hoTen =?, sdt = ?, diaChi = ?, soCMND=?, namSinh=?, gioiTinh=?, maTayNghe =?, troCap =? where maCN =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(9, cn.getMaCN());
//			stmt.setString(1, cn.getMaCN());
			stmt.setString(1, cn.getHoTen());
			stmt.setString(2, cn.getSoDT());
			stmt.setString(3, cn.getDiaChi());
			stmt.setString(4, cn.getSoCMND());
			stmt.setInt(5, cn.getNamSinh());
			stmt.setString(6, cn.getGioiTinh());
			stmt.setString(7, cn.getTayNghe().getMaTN());
			stmt.setDouble(8, cn.getTroCap());

			n = stmt.executeUpdate();
			con.commit();
			// return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return n > 0;
	}

	public ArrayList<CongNhan> getCongNhanByMa(String maCN) {
		PreparedStatement stmt = null;
		try {
			Connection con = ConnectDB.getDataBase().getConnection();
			stmt = con.prepareStatement("Select * from CongNhan where maCN = ?");
			stmt.setString(1, maCN);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				maCN = rs.getString("maCN");
				String tenCN = rs.getString("hoTen");
				String soCMND = rs.getString("soCMND");
				int namSinh = rs.getInt("namSinh");
				String gioiTinh = rs.getString("gioiTinh");
				String diaChi = rs.getString("diaChi");
				String soDienThoai = rs.getString("sdt");
				String tayNghe = rs.getString("maTayNghe");

				TayNghe tn = new TayNghe();
				tn = tnDao.getTayNgheTheoMa(tayNghe);
				double troCap = rs.getDouble("troCap");
				CongNhan cn = new CongNhan(maCN, tenCN, soDienThoai, diaChi, soCMND, namSinh, gioiTinh, tn, troCap);
				dscn.add(cn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dscn;
	}

	public ArrayList<CongNhan> getCongNhanByHoTen(String hoTen) {
		PreparedStatement stmt = null;
		try {
			Connection con = ConnectDB.getDataBase().getConnection();
			stmt = con.prepareStatement("Select * from CongNhan where hoTen = ?");
			stmt.setString(1, hoTen);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				String maCN = rs.getString("maCN");
				hoTen = rs.getString("hoTen");
				String soCMND = rs.getString("soCMND");
				int namSinh = rs.getInt("namSinh");
				String gioiTinh = rs.getString("gioiTinh");
				String diaChi = rs.getString("diaChi");
				String soDienThoai = rs.getString("sdt");
				String tayNghe = rs.getString("maTayNghe");

				TayNghe tn = new TayNghe();
				tn = tnDao.getTayNgheTheoMa(tayNghe);
				double troCap = rs.getDouble("troCap");
				CongNhan cn = new CongNhan(maCN, hoTen, soDienThoai, diaChi, soCMND, namSinh, gioiTinh, tn, troCap);
				dscn.add(cn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dscn;
	}


}
