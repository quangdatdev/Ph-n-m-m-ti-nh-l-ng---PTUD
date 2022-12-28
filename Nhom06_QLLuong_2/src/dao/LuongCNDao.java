package dao;

import entity.ChamCongCN;
import entity.CongNhan;
import entity.LuongCN;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConnectDB;

public class LuongCNDao {
	private CNDao cnDao;
	private ChamCongCNDao ccCNDao;
	private PreparedStatement pre;

	public LuongCNDao() {
		cnDao = new CNDao();
	}

	private LuongCN createLuongCN(final ResultSet rs) throws SQLException {
		String maLuong, maCN;
		int thang, nam;
		double luong;
//		Date input = ;

		maLuong = rs.getString("maLuongCN");
		maCN = rs.getString("maCong");
		thang = rs.getInt("thang");
		nam = rs.getInt("nam");
		luong = rs.getDouble("luong");

		CongNhan cn;

		ccCNDao = new ChamCongCNDao();
		ChamCongCN cc = ccCNDao.getCongTheoMaCong(maCN);
		LuongCN lcn = new LuongCN(maLuong, thang, nam, cc, luong);
		return lcn;
	}

	public List<LuongCN> getAllLuongCN() throws Exception {
		String sql = "select * from LuongCN";
		Connection con = ConnectDB.getDataBase().getConnection();
		List<LuongCN> list = new ArrayList<LuongCN>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				LuongCN lcn = createLuongCN(rs);

				list.add(lcn);
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return list;
	}

	public boolean themLuong(LuongCN lcn) throws SQLException {
		String sql = "insert into LuongCN ([maLuongCN],[maCong],[thang],[nam],[luong]) VALUES (?,?,?,?,?)";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, lcn.getMaLuongCN());
			stmt.setString(2, lcn.getCc().getMaCong());
			stmt.setInt(3, lcn.getThang());
			stmt.setInt(4, lcn.getNam());
			stmt.setDouble(5, lcn.getLuong());
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

	public List<Object[]> getLuongTheoThangNam(int thang, int nam) throws SQLException {
		List<Object[]> list = new ArrayList<>();
		String sql = "SELECT CongNhan.maCN, CongNhan.hoTen, sum(LuongCN.luong) as 'tongLuong'\r\n"
				+ "FROM     ChamCongCN INNER JOIN\r\n"
				+ "                  LuongCN ON ChamCongCN.maCong = LuongCN.maCong INNER JOIN\r\n"
				+ "                  PhanCongCN ON ChamCongCN.maPC = PhanCongCN.maPC INNER JOIN\r\n"
				+ "                  CongNhan ON PhanCongCN.maCN = CongNhan.maCN\r\n"
				+ "where thang = ? and nam = ?\r\n" + "group by CongNhan.maCN, hoTen";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maCN = rs.getString("maCN");
				String hoTen = rs.getString("hoTen");
				double luong = rs.getDouble("tongLuong");
				Object[] o = { maCN, hoTen, luong };
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


	public double getTongLuong(int thang, int nam) {
		double sum = 0;
		String sql = "\r\n" + "select  SUM(luong)\r\n" + "from [dbo].[LuongCN]\r\n" + "where thang = ? \r\n"
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

	public int getTongSoCN() throws SQLException {
		int sl = 0;
		;
		String sql = "select COUNT(maCN)from CongNhan";
		Connection con = ConnectDB.getDataBase().getConnection();
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			sl = rs.getInt(1);
		}
		return sl;
	}

	public List<Object[]> dsLCN(int thang, int nam) throws SQLException {
		List<Object[]> listOB = new ArrayList<Object[]>();
		String sql = "SELECT CongNhan.maCN, CongNhan.hoTen, CongDoanSanXuat.tenCD,  sum(ChamCongCN.soSP) as 'tongSP', sum(LuongCN.luong) as 'tongLuong'\r\n"
				+ "FROM     ChamCongCN INNER JOIN\r\n"
				+ "                  LuongCN ON ChamCongCN.maCong = LuongCN.maCong INNER JOIN\r\n"
				+ "                  PhanCongCN ON ChamCongCN.maPC = PhanCongCN.maPC INNER JOIN\r\n"
				+ "                  CongNhan ON PhanCongCN.maCN = CongNhan.maCN INNER JOIN\r\n"
				+ "                  CongDoanSanXuat ON PhanCongCN.maCD = CongDoanSanXuat.maCD\r\n"
				+ "where MONTH(ngayCham) = ? and YEAR(ngayCham) = ?\r\n"
				+ "group by CongNhan.maCN, CongNhan.hoTen, CongDoanSanXuat.tenCD";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maCN = rs.getString("maCN");
				String tenCN = rs.getString("hoTen");
				String congViec = rs.getString("tenCD");
				int tongSP = rs.getInt("tongSP");
				double luong = rs.getDouble("tongLuong");
				Object[] o = { maCN, tenCN, congViec, tongSP, luong };
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
