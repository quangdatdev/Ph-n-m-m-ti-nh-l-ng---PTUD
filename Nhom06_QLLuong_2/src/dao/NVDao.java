package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.NhanVien;

import entity.TrinhDo;
import util.ConnectDB;

public class NVDao {

	ArrayList<NhanVien> dsnv;
	private TrinhDoDao tdDao;

	public NVDao() {
		dsnv = new ArrayList<NhanVien>();
		tdDao = new TrinhDoDao();
	}

	private NhanVien createNhanVien(final ResultSet rs) throws SQLException, Exception {
		String maNV, tenNV, diaChi, soDienThoai, gioiTinh, trinhDo, soCMND;
		int namSinh;
		double heSoLuong;
		double luongCoBan, troCap;

//		Date input = ;

		maNV = rs.getString("maNV");
		tenNV = rs.getString("hoTen");
		soDienThoai = rs.getString("soDT");
		soCMND = rs.getString("soCMND");
		diaChi = rs.getString("diaChi");
		gioiTinh = rs.getString("gioiTinh");
		namSinh = rs.getInt("namSinh");
		TrinhDo td = tdDao.getTrinhDoTheoMa(rs.getString("maTrinhDo"));
		luongCoBan = rs.getDouble("luongCB");
		troCap = rs.getDouble("troCap");
		heSoLuong = rs.getDouble("heSoLuong");

		NhanVien nv = new NhanVien(maNV, tenNV, soDienThoai, soCMND,diaChi, gioiTinh, namSinh, td, luongCoBan, troCap,
				heSoLuong);

		return nv;
	}

	// get all nv
	public List<NhanVien> getAllNhanVien() throws Exception {
		String sql = "select * from NhanVien";
		Connection con = ConnectDB.getDataBase().getConnection();
		List<NhanVien> list = new ArrayList<NhanVien>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				NhanVien nv = new NhanVien();
				nv = createNhanVien(rs);
				list.add(nv);
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return list;
	}

	// get NV theo MA
	public NhanVien getNVTheoMa(String maNV) throws SQLException {
		String sql = "select * from NhanVien where  maNV =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, maNV);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {

				NhanVien nv = new NhanVien();
				nv = createNhanVien(rs);
				return nv;
			}

			con.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return null;
	}

	public List<NhanVien> getNhanVien() throws Exception {
		String sql = "select maNV, hoTen from NhanVien";
		Connection con = ConnectDB.getDataBase().getConnection();
		List<NhanVien> list = new ArrayList<NhanVien>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				String maNV = rs.getString("maNV");
				String hoTen = rs.getString("hoTen");
				NhanVien nv = new NhanVien(maNV, hoTen);
				list.add(nv);
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return list;
	}

	// tìm nv
	public List<NhanVien> timNV(String maNV, String tenNV, String soDT, String diaChi, String gioiTinh, String soCMND)
			throws SQLException {

		List<NhanVien> list = new ArrayList<NhanVien>();
		if (maNV != "") {
			String sql = "select * from NhanVien where maNV =? ";

			Connection con = ConnectDB.getDataBase().getConnection();
			try {
				PreparedStatement stmt = con.prepareStatement(sql);

				stmt.setString(1, maNV);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					NhanVien nv = new NhanVien();
					nv = createNhanVien(rs);
					list.add(nv);

				}

				con.commit();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				con.rollback();
			}
			return list;
		} else {
			tenNV = tenNV.trim();
			soDT = soDT.trim();
			diaChi = diaChi.trim();
			gioiTinh = gioiTinh.trim();
			soCMND = soCMND.trim();
			
			String txtTen = tenNV == null ? "" : ("" + tenNV);
			String txtSoDT = soDT == null ? "" : (" and soDT like N'%" + soDT + "%'");
			String txtDiaChi = diaChi == null ? "" : (" and diaChi like N'%" + diaChi + "%'");
			String cbGioiTinh = gioiTinh == null ? "" : (" and gioiTinh like N'%" + gioiTinh + "%'");
			String txtSoCMND = soCMND == null ? "" :("and soCMND like N'%" + soCMND + "%'");
			String sql = "select * from NhanVien where hoTen like N'%" + txtTen + "%'" + txtSoDT + txtDiaChi
					+ cbGioiTinh + txtSoCMND;
			Connection con = ConnectDB.getDataBase().getConnection();

			try {
				PreparedStatement stmt = con.prepareStatement(sql);

				// stmt.setString(1, tenSP);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					NhanVien nv = new NhanVien();
					nv = createNhanVien(rs);
					list.add(nv);
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

	// them 1 nv
	public boolean themNhanVien(NhanVien nv) throws SQLException {
		String sql = " insert into [dbo].[NhanVien] (manv, hoTen, soDT,soCMND, diaChi, gioiTinh, namSinh, maTrinhDo, luongCB, troCap, heSoluong)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, nv.getMaNV());
			stmt.setString(2, nv.getHoTen());
			stmt.setString(3, nv.getSoDT());
			stmt.setString(4, nv.getSoCMND());
			stmt.setString(5, nv.getDiaChi());
			stmt.setString(6, nv.getGioiTinh());
			stmt.setInt(7, nv.getNamSinh());
			stmt.setString(8, nv.getTrinhDo().getMaTrinhDo());
			stmt.setDouble(9, nv.getLuongCoBan());
			stmt.setDouble(10, nv.getTroCap());
			stmt.setDouble(11, nv.getHeSoLuong());

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

	// xoa nv
	public boolean xoaNhanVien(String id) throws SQLException {
		String sql = "delete from NhanVien where maNV =?";
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

	public boolean suaNhanVien(NhanVien nv) throws SQLException {
		int n = 0;
		String sql = "update NhanVien set hoTen = ?,soDT= ?,soCMND = ?, diaChi = ?, gioiTinh=?, namSinh = ?, maTrinhDo= ?, luongCB=?, troCap=?, heSoLuong = ?  where maNV =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(11, nv.getMaNV());
			stmt.setString(1, nv.getHoTen());
			stmt.setString(2, nv.getSoDT());
			stmt.setString(3, nv.getSoCMND());
			stmt.setString(4, nv.getDiaChi());
			stmt.setString(5, nv.getGioiTinh());
			stmt.setInt(6, nv.getNamSinh());
			stmt.setString(7, nv.getTrinhDo().getMaTrinhDo());
			stmt.setDouble(8, nv.getLuongCoBan());
			stmt.setDouble(9, nv.getTroCap());
			stmt.setDouble(10, nv.getHeSoLuong());
//			stmt.setString(10, nv.getTenDangNhap());
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

}
