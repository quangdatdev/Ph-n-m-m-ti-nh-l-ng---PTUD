package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.CaLamNV;
import entity.NhanVien;
import entity.TrinhDo;
import util.ConnectDB;

public class TrinhDoDao {

	ArrayList<TrinhDo> dsTrDo;

	public TrinhDoDao() {
		dsTrDo = new ArrayList<TrinhDo>();

	}

	private TrinhDo createTrinhDo(final ResultSet rs) throws SQLException {
		String maTD, tenTD;

		maTD = rs.getString("maTrinhDo");
		tenTD = rs.getString("tenTrinhDo");

		TrinhDo td = new TrinhDo(maTD, tenTD);
		return td;
	}

	public TrinhDo getTrinhDoTheoMa(String id) {
		String sql = "select * from TrinhDo where maTrinhDo =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		TrinhDo td = new TrinhDo();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				td = createTrinhDo(rs);
				return td;

			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		return null;

	}

	public List<TrinhDo> getAllTrinhDo() throws SQLException {
		String sql = "select * from trinhDo";
		Connection con = ConnectDB.getDataBase().getConnection();
		List<TrinhDo> list = new ArrayList<TrinhDo>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				TrinhDo td = createTrinhDo(rs);
				list.add(td);
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return list;
	}

	public boolean themTrinhDo(TrinhDo td) throws SQLException {
		String sql = "INSERT [dbo].[TrinhDo] ([maTrinhDo], [tenTrinhDo]) VALUES (?,?)";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, td.getMaTrinhDo());
			stmt.setString(2, td.getTenTrinhDo());
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

	public boolean xoaTrinhDo(String id) throws SQLException {
		String sql = "delete from TrinhDo where maTrinhDo = ?";
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

	public boolean suaTrinhDo(TrinhDo td) throws SQLException {
		int n = 0;
		String sql = "update TrinhDo set tenTrinhDo =? where maTrinhDo =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(2, td.getMaTrinhDo());
//			stmt.setString(1, cn.getMaCN());
			stmt.setString(1, td.getTenTrinhDo());

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

	public TrinhDo getTrinhDoTheoTen(String id) {
		String sql = "select * from TrinhDo where tenTrinhDo =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		TrinhDo tn = new TrinhDo();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				tn = createTrinhDo(rs);
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
