package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.CaLamCN;
import entity.CongNhan;
import entity.TayNghe;
import util.ConnectDB;

public class TayNgheDao {

	ArrayList<TayNghe> dstn;

	public TayNgheDao() {
		dstn = new ArrayList<TayNghe>();

	}

	private TayNghe createTayNghe(final ResultSet rs) throws SQLException {
		String maTN, tenTN;

		maTN = rs.getString("maTayNghe");
		tenTN = rs.getString("tenTayNghe");

		TayNghe tn = new TayNghe(maTN, tenTN);
		return tn;
	}

	public TayNghe getTayNgheTheoMa(String id) {
		String sql = "select * from TayNghe where maTayNghe =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		TayNghe tn = new TayNghe();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				tn = createTayNghe(rs);
				return tn;

			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		return null;

	}

	public List<TayNghe> getAllTayNghe() throws SQLException {
		String sql = "select * from tayNghe";
		Connection con = ConnectDB.getDataBase().getConnection();
		List<TayNghe> list = new ArrayList<TayNghe>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				TayNghe tn = createTayNghe(rs);
				list.add(tn);
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return list;
	}

	public boolean themTayNghe(TayNghe tn) throws SQLException {
		String sql = "INSERT [dbo].[TayNghe] ([maTayNghe], [tenTayNghe]) VALUES (?,?)";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, tn.getMaTN());
			stmt.setString(2, tn.getTenTN());
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

	public boolean xoaTN(String id) throws SQLException {
		String sql = "delete from TayNghe where maTayNghe =?";
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

	public boolean suaTN(TayNghe tn) throws SQLException {
		int n = 0;
		String sql = "update TayNghe set tenTayNghe =? where maTayNghe =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(2, tn.getMaTN());
//			stmt.setString(1, cn.getMaCN());
			stmt.setString(1, tn.getTenTN());

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

	public TayNghe getTayNgheTheoTen(String id) {
		String sql = "select * from TayNghe where tenTayNghe =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		TayNghe tn = new TayNghe();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				tn = createTayNghe(rs);
				return tn;

			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		return null;

	}
}
