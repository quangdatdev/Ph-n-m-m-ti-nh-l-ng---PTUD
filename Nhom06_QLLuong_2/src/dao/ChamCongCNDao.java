package dao;

import entity.CaLamCN;

import entity.ChamCongCN;

import entity.NhanVien;
import entity.PhanCongCN;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import util.ConnectDB;

public class ChamCongCNDao {

	private CNDao cnDao;
	private PhanCongCNDao pcDao;
	private CaLamCNDao caDao;
	private CongDoanSXDao cdDao;
	private SanPhamDao spDao;

	public ChamCongCNDao() {
		cnDao = new CNDao();
		pcDao = new PhanCongCNDao();
		caDao = new CaLamCNDao();
		spDao = new SanPhamDao();
	}

	private ChamCongCN createCCN(final ResultSet rs) throws SQLException, Exception {
		String maCong, maCN, tenCN, diaChi, soDienThoai, tayNghe, gioiTinh, maCa, gioTheoCa, tenCa, maPhanCong, maCD,
				tenCD, maSP;
		int soLuong, namSinh;
		boolean trangThai, nghiPhep;
		LocalDate ngayCham;
		double troCap, tienTheoCD, heSoLuong;
		double luongTheoCa;

		maCong = rs.getString("maCong");
		soLuong = rs.getInt("soSP");
		ngayCham = rs.getDate("ngayCham").toLocalDate();
		maCa = rs.getString("maCa");
		nghiPhep = rs.getBoolean("nghiPhep") ? true : false;

		trangThai = rs.getBoolean("trangThai") ? true : false;

		maPhanCong = rs.getString("maPC");

		CaLamCN ca = caDao.getCaTheoMa(maCa);

		PhanCongCN pc = pcDao.getPCTheoMaPC(maPhanCong);
		ChamCongCN cc = new ChamCongCN(maCong, soLuong, ngayCham, ca, nghiPhep, pc, trangThai);

		return cc;
	}

	public List<ChamCongCN> getAllChamCongCN() throws Exception {
		String sql = "select * from ChamCongCN";
		Connection con = ConnectDB.getDataBase().getConnection();
		List<ChamCongCN> list = new ArrayList<ChamCongCN>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				ChamCongCN cc = new ChamCongCN();
				cc = createCCN(rs);

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


	public ChamCongCN getCongTheoMaCong(String maCong) throws SQLException {

		List<ChamCongCN> list = new ArrayList<ChamCongCN>();
		if (maCong != "") {
			String sql = "select * from ChamCongCN where maCong =? ";
			Connection con = ConnectDB.getDataBase().getConnection();
			try {
				PreparedStatement stmt = con.prepareStatement(sql);

				stmt.setString(1, maCong);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					ChamCongCN cc = new ChamCongCN();
					cc = createCCN(rs);
					// list.add(cc);
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

	public boolean themCongCN(ChamCongCN cc) throws SQLException {
		String sql = "insert into ChamCongCN ([maCong],[soSP],[ngayCham],[maCa],[nghiPhep],[maPC],[trangThai])"
				+ "values(?,?,?,?,?,?,?)";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			LocalDate input = cc.getNgayCham();
			Date date = Date.valueOf(input);
			stmt.setString(1, cc.getMaCong());
			stmt.setInt(2, cc.getSoSP());
			stmt.setDate(3, date);
			stmt.setString(4, cc.getMaCa().getMaCa());
			stmt.setBoolean(5, cc.isNghiPhep() ? true : false);
			stmt.setString(6, cc.getMaPC().getMaPC());
			stmt.setBoolean(7, cc.isTrangThai() ? true : false);
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

	public boolean xoaCongCN(String id) throws SQLException {
		String sql = "delete from ChamCongCN where maCong =?";
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

	public List<Object[]> dsCCCN(String maCN, int thang, int nam) throws SQLException {
		List<Object[]> listOB = new ArrayList<Object[]>();
		String sql = "SELECT ChamCongCN.maCong, ChamCongCN.ngayCham,ChamCongCN.trangThai, ChamCongCN.nghiPhep,\r\n"
				+ "ChamCongCN.soSP, CaLamCN.maCa, CaLamCN.tenCa, CaLamCN.gioTheoCa, PhanCongCN.maPC, SanPham.maSP, SanPham.tenSP, CongDoanSanXuat.maCD, CongDoanSanXuat.tenCD, CongDoanSanXuat.giaCD\r\n"
				+ "FROM     CongNhan INNER JOIN\r\n"
				+ "                  PhanCongCN ON CongNhan.maCN = PhanCongCN.maCN INNER JOIN\r\n"
				+ "                  CongDoanSanXuat ON PhanCongCN.maCD = CongDoanSanXuat.maCD INNER JOIN\r\n"
				+ "                  ChamCongCN ON PhanCongCN.maPC = ChamCongCN.maPC INNER JOIN\r\n"
				+ "                  CaLamCN ON ChamCongCN.maCa = CaLamCN.maCa INNER JOIN\r\n"
				+ "                  SanPham ON CongDoanSanXuat.maSP = SanPham.maSP\r\n"
				+ "where CongNhan.maCN = ? and MONTH(ngayCham)=? and YEAR(ngayCham)=?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maCN);
			stmt.setInt(2, thang);
			stmt.setInt(3, nam);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maCong = rs.getString("maCong");
				LocalDate ngayCham = rs.getDate("ngayCham").toLocalDate();
				boolean trangThai = rs.getBoolean("trangThai") ? true : false;
				boolean nghiPhep = rs.getBoolean("nghiPhep") ? true : false;
				int soSP = rs.getInt("soSP");
				String maCa = rs.getString("maCa");
				String tenCa = rs.getString("tenCa");
				String gioTheoCa = rs.getString("gioTheoCa");
				String maPC = rs.getString("maPC");
				String maSP = rs.getString("maSP");
				String tenSP = rs.getString("tenSP");
				String maCD = rs.getString("maCD");
				String tenCD = rs.getString("tenCD");
				double giaCD = rs.getDouble("giaCD");
				Object[] o = { maCong, ngayCham, trangThai, nghiPhep, soSP, maCa, tenCa, gioTheoCa, maPC, maSP, tenSP,
						maCD, tenCD, giaCD };
				listOB.add(o);

			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
		}
		return listOB;
	}

	public List<ChamCongCN> getCCCNByNgayLap2xx(int ngay, int thang, int nam) {
		List<ChamCongCN> list = new ArrayList<ChamCongCN>();
		Connection con = ConnectDB.getDataBase().getConnection();
		try {

			String sql = "\r\n" + "SELECT [maCong]\r\n" + "      ,[soSP]\r\n" + "      ,[ngayCham]\r\n"
					+ "      ,[maCa]\r\n" + "      ,[nghiPhep]\r\n" + "      ,[maPC]\r\n" + "      ,[trangThai]\r\n"
					+ "  FROM [dbo].[ChamCongCN]\r\n" + "\r\n" + "  where DAY(ngayCham)= " + ngay + "\r\n"
					+ "	and MONTH(ngayCham) = " + thang + "\r\n" + "	and YEAR(ngayCham) = " + nam;

			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				ChamCongCN cc = new ChamCongCN();
				cc = createCCN(rs);

				list.add(cc);
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return list;
	}

}
