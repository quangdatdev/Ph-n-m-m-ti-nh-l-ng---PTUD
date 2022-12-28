package dao;

import entity.CongDoanSanXuat;

import entity.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import java.util.List;

import util.ConnectDB;

public class CongDoanSXDao {

	private SanPhamDao spDao;

	public CongDoanSXDao() {
		spDao = new SanPhamDao();
	}

	private CongDoanSanXuat createCongDoan(final ResultSet rs) throws SQLException {
		String maCD, tenCD;
		double giaCD, gia;
		int soLuong;
		int giaiDoan = 0;
		SanPham sp;
		maCD = rs.getString("maCD");
		tenCD = rs.getString("tenCD");
		giaCD = rs.getDouble("giaCD");
		giaiDoan = rs.getInt("giaiDoan");
		soLuong = rs.getInt("soLuong");
		sp = spDao.getSanPhamTheoMa(rs.getString("maSP"));

		CongDoanSanXuat cd = new CongDoanSanXuat(maCD, tenCD, giaCD, soLuong, giaiDoan, sp);

		return cd;
	}

	public List<CongDoanSanXuat> getAllCongDoan() throws Exception {
		String sql = "select * from CongDoanSanXuat";
		Connection con = ConnectDB.getDataBase().getConnection();
		List<CongDoanSanXuat> list = new ArrayList<CongDoanSanXuat>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String maCD, tenCD;
				double giaCD;
				int soLuong;
				int giaiDoan = 0;
				SanPham sp;
				maCD = rs.getString("maCD");
				tenCD = rs.getNString("tenCD");
				giaCD = rs.getDouble("giaCD");
				giaiDoan = rs.getInt("giaiDoan");
				soLuong = rs.getInt("soLuong");
				sp = spDao.getSanPhamTheoMa(rs.getString("maSP"));
				CongDoanSanXuat cd = new CongDoanSanXuat(maCD, tenCD, giaCD, soLuong, giaiDoan, sp);
				list.add(cd);

			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
		}
		return list;
	}

	public List<CongDoanSanXuat> getCDTheoTenCD(String ma) throws Exception {
		String sql = "select * from CongDoanSanXuat where tenCD =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		List<CongDoanSanXuat> list = new ArrayList<CongDoanSanXuat>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String maCD, tenCD;
				double giaCD;
				int soLuong;
				int giaiDoan = 0;
				SanPham sp;
				maCD = rs.getString("maCD");
				tenCD = rs.getNString("tenCD");
				giaCD = rs.getDouble("giaCD");
				giaiDoan = rs.getInt("giaiDoan");
				soLuong = rs.getInt("soLuong");
				sp = spDao.getSanPhamTheoMa(rs.getString("tenCD"));
				CongDoanSanXuat cd = new CongDoanSanXuat(maCD, tenCD, giaCD, soLuong, giaiDoan, sp);
				list.add(cd);

			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return list;
	}

	public List<CongDoanSanXuat> getCDTheoMASP(String ma) throws Exception {
		String sql = "select * from CongDoanSanXuat where maSP =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		List<CongDoanSanXuat> list = new ArrayList<CongDoanSanXuat>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery(); // khac

			while (rs.next()) {
				String maCD, tenCD;
				double giaCD;
				int soLuong;
				int giaiDoan = 0;
				SanPham sp;
				maCD = rs.getString("maCD");
				tenCD = rs.getNString("tenCD");
				giaCD = rs.getDouble("giaCD");
				giaiDoan = rs.getInt("giaiDoan");
				soLuong = rs.getInt("soLuong");
				sp = spDao.getSanPhamTheoMa(rs.getString("tenCD"));
				CongDoanSanXuat cd = new CongDoanSanXuat(maCD, tenCD, giaCD, soLuong, giaiDoan, sp);
				list.add(cd);

			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return list;
	}

	public CongDoanSanXuat getCDTheoMaCD(String ma) throws SQLException {
		String sql = "select * from CongDoanSanXuat where maCD like '" + ma + "' ";
		Connection con = ConnectDB.getDataBase().getConnection();
		CongDoanSanXuat cd = new CongDoanSanXuat();
		try {
//			PreparedStatement stmt = con.prepareStatement(sql);
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String maCD = rs.getString("maCD");
				String tenCD = rs.getString("tenCD");
				double giaCD = rs.getDouble("giaCD");
				int soLuong = rs.getInt("soLuong");
				int giaiDoan = rs.getInt("giaiDoan");
				SanPham sp = spDao.getSanPhamTheoMa(rs.getString("maSP"));
				cd = new CongDoanSanXuat(maCD, tenCD, giaCD, soLuong, giaiDoan, sp);

			}

			return cd;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return null;
	}

	public boolean themCongDoan(CongDoanSanXuat cd) throws SQLException {
		int n = 0;
		String sql = "insert into [dbo].[CongDoanSanXuat] (maCD,tenCD,giaCD,soLuong,giaiDoan,maSP)"
				+ "VALUES(?,?,?,?,?,?)	";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cd.getMaCD());
			stmt.setString(2, cd.getTenCD());
			stmt.setDouble(3, cd.getGia());
			stmt.setInt(4, cd.getSoLuong());
			stmt.setInt(5, cd.getGiaiDoan());
			stmt.setString(6, cd.getSanPham().getMaSP());
			n = stmt.executeUpdate();
			con.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return n > 0;
	}

	public boolean suaCongDoan(CongDoanSanXuat cd) throws SQLException {
		int n = 0;
		String sql = "update CongDoanSanXuat set tenCD =?, giaCD=?, soLuong =?, giaiDoan=?, maSP=? where maCD =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(6, cd.getMaCD());
			stmt.setString(1, cd.getTenCD());
			stmt.setDouble(2, cd.getGia());
			stmt.setInt(3, cd.getSoLuong());
			stmt.setInt(4, cd.getGiaiDoan());
			stmt.setString(5, cd.getSanPham().getMaSP());
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

	public boolean xoaCongDoan(String id) throws SQLException {
		String sql = "delete from CongDoanSanXuat where maCD =?";
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

//	public int soCongDoan(String maSP) throws SQLException {
//		String sql = "select 'soCD' = COUNT(*)  from CongDoanSanXuat where maSP = ?";
//
//		Connection con = ConnectDB.getDataBase().getConnection();
//		int n = 0;
//
//		try {
//			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setString(1, maSP);
//			ResultSet rs = stmt.executeQuery();
//			rs.next();
//			n = rs.getInt("soCD");
//			con.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			con.rollback();
//		}
//		return n;
//	}

//	public int soLuongDaLam(String maCD) throws SQLException {
//		String sql = "select soSP = sum(soSP) from ChamCongCN cc join PhanCongCN pc on cc.maPC = pc.maPC where maCD = ?";
//		Connection con = ConnectDB.getDataBase().getConnection();
//		int n = 0;
//
//		try {
//			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setString(1, maCD);
//			ResultSet rs = stmt.executeQuery();
//			rs.next();
//			n = rs.getInt("soSP");
//			con.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			con.rollback();
//		}
//		return n;
//	}

}
