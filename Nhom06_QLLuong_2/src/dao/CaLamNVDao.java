package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import entity.CaLamNV;
import util.ConnectDB;

public class CaLamNVDao {

	private CaLamNV createCaLamNV(final ResultSet rs) throws SQLException {
		CaLamNV cl = new CaLamNV();
		cl.setMaCa(rs.getString("maCa"));
		cl.setTenCa(rs.getString("tenCa"));
		cl.setGioTheoCa(rs.getString("gioTheoCa"));
		cl.setLuongTheoCa(rs.getDouble("luongTheoCa"));
		return cl;
	}

	public List<CaLamNV> getAllCaLamNV() throws Exception {
		String sql = "select * from CaLamNV";
		Connection con = ConnectDB.getDataBase().getConnection();
		List<CaLamNV> list = new ArrayList<CaLamNV>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				CaLamNV cl = createCaLamNV(rs);
				list.add(cl);
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return list;

	}

	public CaLamNV getCaTheoMa(String id) throws Exception {
		String sql = "select * from CaLamNV where maCa =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		CaLamNV ca = new CaLamNV();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				ca = createCaLamNV(rs);
				return ca;

			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return null;

	}

	public CaLamNV getCaTheoTen(String id) throws Exception {
		String sql = "select * from CaLamNV where tenCa =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		CaLamNV ca = new CaLamNV();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				ca = createCaLamNV(rs);
				return ca;

			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return null;

	}
}
