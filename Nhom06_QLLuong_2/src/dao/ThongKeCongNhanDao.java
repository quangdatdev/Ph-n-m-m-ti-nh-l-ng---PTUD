package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ConnectDB;

public class ThongKeCongNhanDao {

	private CNDao cnDao;

	public ThongKeCongNhanDao() {
		cnDao = new CNDao();
	}

	public double tongLuongCN(int thang, int nam) throws SQLException {
		double s = 0;
		String sql = "	select SUM(luong) as 'tongLuong' from LuongCN where thang = ? and nam = ?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				s = rs.getDouble("tongLuong");
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return s;

	}

	public int tongCN() throws SQLException {
		int s = 0;
		String sql = "select count(maCN) as 'tongCN' from CongNhan";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				s = rs.getInt("tongCN");
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return s;

	}

	public double luongCaoNhat(int thang, int nam) throws SQLException {
		double s = 0;
		String sql = "select MAX(luong) as 'luongLN' from LuongCN where thang =? and nam = ?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, thang);
			stmt.setInt(2, nam);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				s = rs.getDouble("luongLN");
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return s;

	}

	public double luongThapNhat(int thang, int nam) throws SQLException {
		double s = 0;
		String sql = "select MIN(luong) as 'luongNN' from LuongCN where thang =? and nam = ?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, thang);
			stmt.setInt(2, nam);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				s = rs.getDouble("luongNN");
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return s;

	}

	public String congNhanXuatSac(int thang, int nam) throws SQLException {
		String s = "";
		String sql = "SELECT top 1 CongNhan.hoTen as 'hoTenCN'\r\n"
				+ "FROM     ChamCongCN INNER JOIN\r\n"
				+ "                  LuongCN ON ChamCongCN.maCong = LuongCN.maCong INNER JOIN\r\n"
				+ "                  PhanCongCN ON ChamCongCN.maPC = PhanCongCN.maPC INNER JOIN\r\n"
				+ "                  CongNhan ON PhanCongCN.maCN = CongNhan.maCN\r\n"
				+ "where trangThai = 1 and MONTH(ngayCham)=? and YEAR(ngayCham) = ?\r\n"
				+ "group by hoTen\r\n"
				+ "order by SUM(ChamCongCN.soSP) desc";
		
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				s = rs.getString("hoTenCN");
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return s;
	}
	public String soSPMax(int thang, int nam) throws SQLException {
		String s = "";
		String sql = "SELECT top 1 SUM(ChamCongCN.soSP) as soSP\r\n"
				+ "			FROM     ChamCongCN INNER JOIN\r\n"
				+ "			LuongCN ON ChamCongCN.maCong = LuongCN.maCong INNER JOIN\r\n"
				+ "			PhanCongCN ON ChamCongCN.maPC = PhanCongCN.maPC INNER JOIN\r\n"
				+ "			CongNhan ON PhanCongCN.maCN = CongNhan.maCN\r\n"
				+ "		where trangThai = 1 and MONTH(ngayCham)= ? and YEAR(ngayCham) = ?\r\n"
				+ "		group by hoTen\r\n"
				+ "			order by SUM(ChamCongCN.soSP) desc";
		
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				s = rs.getString(1);
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return s;
	}
	public List<Object[]> dsCNXuatSac(int thang, int nam, String soSP) throws SQLException {
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = "SELECT CongNhan.maCN, CongNhan.hoTen, CongDoanSanXuat.tenCD, sum(ChamCongCN.soSP) as 'tongSoSP', sum(LuongCN.luong) as 'tongLuong'\r\n"
				+ "				 FROM     ChamCongCN INNER JOIN\r\n"
				+ "			              LuongCN ON ChamCongCN.maCong = LuongCN.maCong INNER JOIN\r\n"
				+ "				               PhanCongCN ON ChamCongCN.maPC = PhanCongCN.maPC INNER JOIN\r\n"
				+ "				              CongNhan ON PhanCongCN.maCN = CongNhan.maCN INNER JOIN\r\n"
				+ "				                CongDoanSanXuat ON PhanCongCN.maCD = CongDoanSanXuat.maCD\r\n"
				+ "				where MONTH(ngayCham) = ? and YEAR(ngayCham) = ?\r\n"
				+ "				group by CongNhan.maCN, CongNhan.hoTen, CongDoanSanXuat.tenCD\r\n"
				+ "				having sum(ChamCongCN.soSP) = ? ";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			stmt.setString(3, soSP);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString("maCN");
				String hoTen = rs.getString("hoTen");
				String congViec = rs.getString("tenCD");
				String soSanPham = rs.getString("tongSoSP");
				double luong = rs.getDouble("tongLuong");
				Object[] o = { maNV, hoTen, congViec, soSanPham, luong };
				list.add(o);
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return list;
	}
	
	public List<Object[]> thongKe(int thang, int nam) throws SQLException {
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = "SELECT CongNhan.maCN, CongNhan.hoTen, CongDoanSanXuat.tenCD, sum(ChamCongCN.soSP) as 'tongSoSP', sum(LuongCN.luong) as 'tongLuong'\r\n"
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
				String maNV = rs.getString("maCN");
				String hoTen = rs.getString("hoTen");
				String congViec = rs.getString("tenCD");
				String soSanPham = rs.getString("tongSoSP");
				double luong = rs.getDouble("tongLuong");
				Object[] o = { maNV, hoTen, congViec, soSanPham, luong };
				list.add(o);
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
