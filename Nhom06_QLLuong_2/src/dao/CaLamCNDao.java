package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.CaLamCN;
import util.ConnectDB;

public class CaLamCNDao {
	private CaLamCN createCaLamCN(final ResultSet rs) throws SQLException {
		CaLamCN cl = new CaLamCN();
		cl.setMaCa(rs.getString("maCa"));
		cl.setTenCa(rs.getString("tenCa"));
		cl.setGioTheoCa(rs.getString("gioTheoCa"));
		cl.setLuongTheoCa(rs.getDouble("luongTheoCa"));
		return cl;
	}

	public List<CaLamCN> getAllCaLamCN() throws Exception {
		String sql = "select * from CaLamCN";
		Connection con = ConnectDB.getDataBase().getConnection();
		List<CaLamCN> list = new ArrayList<CaLamCN>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				CaLamCN cl = createCaLamCN(rs);
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

	public CaLamCN getCaTheoMa(String id) throws Exception {
		String sql = "select * from CaLamCN where maCa =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		CaLamCN ca = new CaLamCN();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				ca = createCaLamCN(rs);
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

	public CaLamCN getCaTheoTen(String ten) throws Exception {
		String sql = "select * from CaLamCN where tenCa =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		CaLamCN ca = new CaLamCN();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, ten);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				ca = createCaLamCN(rs);
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
