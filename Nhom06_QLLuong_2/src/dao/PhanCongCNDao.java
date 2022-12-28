package dao;

import entity.CongDoanSanXuat;
import entity.CongNhan;

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

public class PhanCongCNDao {

	private CNDao cnDao;
	private SanPhamDao spDao;
	private CongDoanSXDao cdDao;

	public PhanCongCNDao() {
		cnDao = new CNDao();
		spDao = new SanPhamDao();
		cdDao = new CongDoanSXDao();
	}

	private PhanCongCN createPhanCong(final ResultSet rs) throws SQLException, Exception {
		String maPC;
		CongNhan cn;
		CongDoanSanXuat cd;
		LocalDate ngay;
		int soLuong;
		maPC = rs.getString("maPC");
		cn = cnDao.getCNTheoMa(rs.getString("maCN"));
		cd = cdDao.getCDTheoMaCD(rs.getString("maCD"));
		soLuong = rs.getInt("soLuong");
		ngay = rs.getDate("ngay").toLocalDate();
		PhanCongCN pc = new PhanCongCN(maPC, cn, cd, soLuong, ngay);
		return pc;
	}

	public List<PhanCongCN> getAllPhanCong() throws Exception {
		String sql = "select * from PhanCongCN pc join CongNhan cn on pc.maCN = cn.maCN join CongDoanSanXuat cd on pc.maCD = cd.maCD join SanPham sp on cd.maSP = sp.maSP ";
//		String sql = "select * from PhanCongCN"
		Connection con = ConnectDB.getDataBase().getConnection();
		List<PhanCongCN> list = new ArrayList<PhanCongCN>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				PhanCongCN pc = createPhanCong(rs);

				list.add(pc);
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return list;
	}

	public PhanCongCN getPCTheoMaPC(String maPC) throws SQLException {
		String sql = "select * from PhanCongCN where maPC=?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, maPC);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {

				PhanCongCN pc = new PhanCongCN();
				pc = createPhanCong(rs);
				return pc;
			}

			con.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return null;
	}


	public boolean themPhanCong(PhanCongCN pc1) throws SQLException {
		String sql = "insert into [dbo].[PhanCongCN] (maPC,maCN,maCD,soLuong,ngay)" + "VALUES(?,?,?,?,?)";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			LocalDate input = pc1.getNgay();
			Date date = Date.valueOf(input);
			stmt.setString(1, pc1.getMaPC());
			stmt.setString(2, pc1.getMaCN().getMaCN());

			stmt.setString(3, pc1.getCDSX().getMaCD());
			stmt.setInt(4, pc1.getSoLuong());
			stmt.setDate(5, date);
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

	public boolean xoaPhanCong(String id) throws SQLException {
		String sql = "delete from PhanCongCN where maPC =?";
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



	public boolean suaPhanCong(PhanCongCN pc) throws SQLException {
		int n = 0;
		String sql = "update PhanCong set maCN =?, maCD=?, soLuong =?, ngay =? where maPC =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			LocalDate input = pc.getNgay();
			Date date = Date.valueOf(input);
			stmt.setString(5, pc.getMaPC());
			stmt.setString(1, pc.getMaCN().getMaCN());
			stmt.setString(2, pc.getCDSX().getMaCD());
			stmt.setInt(3, pc.getSoLuong());
			stmt.setDate(4, date);
			// stmt.setInt(3, pc.getSoLuong());
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

	public List<PhanCongCN> getPCTheoNgay(int ngay, int thang, int nam) {
		String sql = "SELECT [maPC]\r\n" + "      ,[maCN]\r\n" + "      ,[maCD]\r\n" + "      ,[soLuong]\r\n"
				+ "      ,[ngay]\r\n" + "  FROM [dbo].[PhanCongCN]\r\n" + "  where DAY(ngay)=" + ngay + " \r\n"
				+ "  and MONTH(ngay)=" + thang + "\r\n" + "  and YEAR(ngay)=" + nam;
//		String sql = "select * from PhanCongCN"
		Connection con = ConnectDB.getDataBase().getConnection();
		List<PhanCongCN> list = new ArrayList<PhanCongCN>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				PhanCongCN pc = createPhanCong(rs);

				list.add(pc);
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
