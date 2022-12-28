package dao;

import entity.ChamCongNV;

import entity.LuongNV;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import util.ConnectDB;

public class LuongNVDao {

	private ChamCongNVDao cCNVDao;
	private PreparedStatement pre;
	private NVDao nvDao;

	public LuongNVDao() {
		nvDao = new NVDao();
	}

	private LuongNV createLuongNV(final ResultSet rs) throws SQLException {
		String maLuong, maCong;
		int thang, nam;
		double luong;
//		Date input = ;

		maLuong = rs.getString("maLuongNV");
		thang = rs.getInt("thang");
		nam = rs.getInt("nam");
		maCong = rs.getString("maCong");
		luong = rs.getDouble("luong");
		cCNVDao = new ChamCongNVDao();
		ChamCongNV cc = cCNVDao.getCongTheoMaCong(maCong);
		LuongNV lNV = new LuongNV(maLuong, thang, nam, cc, luong);
		return lNV;
	}

	public List<LuongNV> getAllLuongNV() throws Exception {
		String sql = "select * from LuongNV";
		Connection con = ConnectDB.getDataBase().getConnection();
		List<LuongNV> list = new ArrayList<LuongNV>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				LuongNV luongNV = new LuongNV();
				luongNV = createLuongNV(rs);

				list.add(luongNV);
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return list;
	}

//	public List<LuongNV> getAllLuongThangNam(int thang, int nam) throws Exception {
//		String sql = "select * from LuongNV where thang = ? and nam = ?";
//		Connection con = ConnectDB.getDataBase().getConnection();
//		List<LuongNV> list = new ArrayList<LuongNV>();
//
//		try {
//			PreparedStatement stmt = con.prepareStatement(sql);
//
//			stmt.setInt(1, thang);
//			stmt.setInt(2, nam);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				LuongNV luong = createLuongNV(rs);
//				list.add(luong);
//
//			}
//
//			con.commit();
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			con.rollback();
//		}
//		return list;
//	}

	// get all nam
	public List<Object[]> getLuongTheoThangNam(int thang, int nam) throws SQLException {
		List<Object[]> list = new ArrayList<>();
		String sql = "SELECT NhanVien.maNV, NhanVien.hoTen, sum(LuongNV.luong) as 'tongLuong'\r\n"
				+ "FROM     NhanVien INNER JOIN\r\n"
				+ "                  ChamCongNV ON NhanVien.maNV = ChamCongNV.maNV INNER JOIN\r\n"
				+ "                  LuongNV ON ChamCongNV.maCong = LuongNV.maCong\r\n"
				+ "where thang =? and nam =?\r\n" + "group by nhanvien.maNV, hoTen";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString("maNV");
				String hoTen = rs.getString("hoTen");
				double luong = rs.getDouble("tongLuong");
				Object[] o = { maNV, hoTen, luong };
				list.add(o);
			}
			con.commit();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return list;
	}

	public boolean themLuongNV(LuongNV lnv) throws SQLException {
		String sql = "insert into LuongNV values(?, ?, ?, ?, ?)";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, lnv.getMaLuongNV());
			stmt.setInt(2, lnv.getThang());
			stmt.setInt(3, lnv.getNam());
			stmt.setString(4, lnv.getcCNV().getMaCong());
			stmt.setDouble(5, lnv.getLuong());
			int n = stmt.executeUpdate();

			con.commit();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
			return false;
		}

		return false;
	}

	public double getTongLuong(int thang, int nam) {
		double sum = 0;
		String sql = "\r\n" + "select  SUM(luong)\r\n" + "from [dbo].[LuongNV]\r\n" + "where thang = ? \r\n"
				+ "and  nam= ?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			pre = con.prepareStatement(sql);
			pre.setInt(1, thang);
			pre.setInt(2, nam);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				sum = rs.getDouble(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}

	public int getTongSoNV() throws SQLException {
		int sl = 0;
		;
		String sql = "select COUNT(maNV)from NhanVien";
		Connection con = ConnectDB.getDataBase().getConnection();
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			sl = rs.getInt(1);
		}
		return sl;
	}

	public List<Object[]> dsLNV(int thang, int nam) throws SQLException {
		List<Object[]> listOB = new ArrayList<Object[]>();
		String sql = "SELECT  NhanVien.maNV, NhanVien.hoTen, count(ChamCongNV.trangThai) as 'tongSoNgay' ,sum(LuongNV.luong) as 'tongLuong'\r\n"
				+ "FROM     ChamCongNV INNER JOIN\r\n"
				+ "                  LuongNV ON ChamCongNV.maCong = LuongNV.maCong INNER JOIN\r\n"
				+ "                  NhanVien ON ChamCongNV.maNV = NhanVien.maNV\r\n"
				+ "where trangThai = 1 and MONTH(ngayCham)  = ? and YEAR(ngayCham) = ?\r\n"
				+ "group by NhanVien.maNV, NhanVien.hoTen";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString("maNV");
				String tenNV = rs.getString("hoTen");
				float ngayLam = rs.getFloat("tongSoNgay");
				double luong = rs.getDouble("tongLuong");
				Object[] o = { maNV, tenNV, ngayLam, luong };
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
