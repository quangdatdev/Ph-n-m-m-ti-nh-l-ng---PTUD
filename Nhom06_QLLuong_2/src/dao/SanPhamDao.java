package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.CaLamNV;
import entity.CongDoanSanXuat;
import entity.SanPham;
import util.ConnectDB;

public class SanPhamDao {
	// create sp
	ArrayList<SanPham> dssp;
	SanPham sp;

	public SanPhamDao() {
		dssp = new ArrayList<SanPham>();
	}

	private SanPham createSanPham(final ResultSet rs) throws SQLException {
		SanPham sp = new SanPham();
		sp.setMaSP(rs.getString("maSP"));
		sp.setTenSP(rs.getString("tenSP"));
		sp.setKieuDang(rs.getString("kieuDang"));
		sp.setChatLieu(rs.getString("chatLieu"));
		// sp.setGia(rs.getDouble("gia"));
		sp.setSoLuong(rs.getInt("soLuong"));

		return sp;
	}
	// get all sp

	public List<SanPham> getAllSanPham() throws Exception {
		String sql = "select * from SanPham";
		Connection con = ConnectDB.getDataBase().getConnection();
		List<SanPham> list = new ArrayList<SanPham>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				SanPham sp = createSanPham(rs);

				list.add(sp);
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return list;
	}
	// get SP theo MA

	public SanPham getSanPhamTheoMa(String maSP) throws SQLException {
		String sql = "select * from SanPham where maSP =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, maSP);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				SanPham sp = new SanPham();
				sp = createSanPham(rs);
				return sp;
			}

			con.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return null;
	}
	// tìm SP

	public List<SanPham> timSP(String maSP, String ten, String kieuDang, String chatLieu) throws SQLException {

		List<SanPham> list = new ArrayList<SanPham>();
		if (maSP != "") {
			String sql = "select * from SanPham where maSP =?";
			Connection con = ConnectDB.getDataBase().getConnection();
			try {
				PreparedStatement stmt = con.prepareStatement(sql);

				stmt.setString(1, maSP);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					SanPham sp = new SanPham();
					sp = createSanPham(rs);
					list.add(sp);

				}

				con.commit();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				con.rollback();
			}
			return list;
		} else {
			ten = ten.trim();
			kieuDang = kieuDang.trim();
			chatLieu = chatLieu.trim();
			String txtTen = ten == null ? "" : ("" + ten);
			String txtKieuDang = kieuDang == null ? "" : (" and kieuDang like N'%" + kieuDang + "%'");
			String txtChatLieu = chatLieu == null ? "" : (" and chatLieu like N'%" + chatLieu + "%'");
			String sql = "select * from SanPham where tenSP like N'%" + txtTen + "%'" + txtKieuDang + txtChatLieu;
			Connection con = ConnectDB.getDataBase().getConnection();
			// List<SanPham> list = new ArrayList<SanPham>();
			try {
				PreparedStatement stmt = con.prepareStatement(sql);

				// stmt.setString(1, tenSP);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					SanPham sp = new SanPham();
					sp = createSanPham(rs);
					list.add(sp);
				}

				con.commit();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				con.rollback();
			}
			return list;
		}

	}

	// them 1 sp
	public boolean themSanPham(SanPham sp) throws SQLException {
		String sql = " INSERT INTO [dbo].[SanPham] ([maSP],[tenSP],[kieuDang],[chatLieu], [soLuong])"
				+ "VALUES(?,?,?,?,?)";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, sp.getMaSP());
			stmt.setString(2, sp.getTenSP());
			stmt.setString(3, sp.getKieuDang());
			stmt.setString(4, sp.getChatLieu());

			stmt.setInt(5, sp.getSoLuong());

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
	// xoa sp

	public boolean xoaSanPham(String id) throws SQLException {
		String sql = "delete from SanPham where maSP =?";
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
	// sua sp

	public boolean suaSanPham(SanPham sp) throws SQLException {
		int n = 0;
		String sql = "update SanPham set tenSP =?, kieuDang=?, chatLieu=? , soLuong =? where maSP =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(5, sp.getMaSP());
			stmt.setString(1, sp.getTenSP());
			stmt.setString(2, sp.getKieuDang());
			stmt.setString(3, sp.getChatLieu());

			stmt.setInt(4, sp.getSoLuong());
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

//	public SanPham getSPTheoTen(String id) throws Exception {
//		String sql = "select * from CaLamNV where tenSP =?";
//		Connection con = ConnectDB.getDataBase().getConnection();
//		SanPham sp = new SanPham();
//		try {
//			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setString(1, id);
//			ResultSet rs = stmt.executeQuery();
//			if (rs.next()) {
//				sp = createSanPham(rs);
//				return sp;
//
//			}
//			con.commit();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			con.rollback();
//		}
//		return null;
//
//	}

	public List<SanPham> getSPheoTenSP(String ma) throws Exception {
		String sql = "select * from SanPham where tenSP =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		List<SanPham> list = new ArrayList<SanPham>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maSP, tenSP, chatLieu, kieuDang;
				int soLuong;
				maSP = rs.getString("maSP");
				tenSP = rs.getString("tenSP");
				kieuDang = rs.getString("kieuDang");
				chatLieu = rs.getString("chatLieu");
				soLuong = rs.getInt("soLuong");
				SanPham sp = new SanPham(maSP, tenSP, kieuDang, chatLieu, soLuong);
				list.add(sp);
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return list;
	}

//	public ArrayList<SanPham> getSanPhamByMa(String ma) {
//		String sql = "select * from SanPham where maSP = ?";
//		Connection con = ConnectDB.getDataBase().getConnection();
//		ArrayList<SanPham> list = new ArrayList<SanPham>();
//		try {
//			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setString(1, ma);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				String maSP, tenSP, chatLieu, kieuDang;
//				int soLuong;
//				maSP = rs.getString("maSP");
//				tenSP = rs.getString("tenSP");
//				kieuDang = rs.getString("kieuDang");
//				chatLieu = rs.getString("chatLieu");
//				soLuong = rs.getInt("soLuong");
//				SanPham sp = new SanPham(maSP, tenSP, kieuDang, chatLieu, soLuong);
//				list.add(sp);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

}
