package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ConnectDB;

public class ThongKeNhanVienDao {

	private NVDao nvDao;

	public ThongKeNhanVienDao() {
		nvDao = new NVDao();
	}

	public List<Float> soNgayLam(int thang, int nam) throws SQLException {
		float n = 0;
		List<Float> list = new ArrayList<Float>();
		String sql = "select count(trangThai) as 'soNgayLam' from ChamCongNV where trangThai = 1 and MONTH(ngayCham) = ? and year(ngayCham) =? group by maNV\r\n"
				+ "";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, thang);
			stmt.setInt(2, nam);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				n = rs.getFloat("soNgayLam");
				list.add(n);
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return list;
	}

	public double tongLuongNV(int thang, int nam) throws SQLException {
		double s = 0;
		String sql = "	select SUM(luong) as 'tongLuong' from LuongNV where thang = ? and nam = ?";
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

	public int tongNV() throws SQLException {
		int s = 0;
		String sql = "select count(maNV) as 'tongNV' from NhanVien";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				s = rs.getInt("tongNV");
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
		String sql = "select MAX(luong) as 'luongLN' from LuongNV where thang =? and nam = ?";
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
		String sql = "select MIN(luong) as 'luongNN' from LuongNV where thang =? and nam = ?";
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

	public String soNgayLamMax(int thang, int nam) throws SQLException {
		String s = "";
		String sql = "SELECT top 1 count(trangThai) as 'soNgayLam'\r\n"
				+ "	FROM     ChamCongNV INNER JOIN\r\n"
				+ "				               LuongNV ON ChamCongNV.maCong = LuongNV.maCong INNER JOIN\r\n"
				+ "				NhanVien ON ChamCongNV.maNV = NhanVien.maNV\r\n"
				+ "				where  trangThai = 1 and MONTH(ngayCham) = ? and YEAR(ngayCham) = ? \r\n"
				+ "		group by NhanVien.hoTen\r\n"
				+ "		order by count(trangthai) desc";
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
	
	public List<Object[]> dsTopNV(int thang, int nam,String soNgay) throws SQLException {
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = "\r\n"
				+ "SELECT NhanVien.maNV, hoTen , soDT, diaChi,count(trangThai) as 'soNgayLam',sum(LuongNV.luong) as 'tongLuong' \r\n"
				+ "				FROM     ChamCongNV INNER JOIN\r\n"
				+ "			                 LuongNV ON ChamCongNV.maCong = LuongNV.maCong INNER JOIN\r\n"
				+ "			               NhanVien ON ChamCongNV.maNV = NhanVien.maNV\r\n"
				+ "				where trangThai = 1 and MONTH(ngayCham) = ? and year(ngayCham) = ? \r\n"
				+ "				group by NhanVien.maNV, NhanVien.hoTen, NhanVien.soDT, NhanVien.diaChi\r\n"
				+ "				having count(trangThai) = ?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			stmt.setString(3, soNgay);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString("maNV");
				String hoTen = rs.getString("hoTen");
				String soDT = rs.getString("soDT");
				String diaChi = rs.getString("diaChi");
				float ngayLam = rs.getFloat("soNgayLam");
				double luong = rs.getDouble("tongLuong");
				Object[] o = { maNV, hoTen, soDT, diaChi, ngayLam, luong };
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
		String sql = "SELECT NhanVien.maNV,hoTen,soDT,diaChi,count(trangThai) as 'soNgayLam',sum(LuongNV.luong) as 'tongLuong' \r\n"
				+ "FROM     ChamCongNV INNER JOIN\r\n"
				+ "                  LuongNV ON ChamCongNV.maCong = LuongNV.maCong INNER JOIN\r\n"
				+ "                  NhanVien ON ChamCongNV.maNV = NhanVien.maNV\r\n"
				+ "where trangThai = 1 and MONTH(ngayCham) = ? and year(ngayCham) =?\r\n"
				+ "group by NhanVien.maNV, NhanVien.hoTen, NhanVien.soDT, NhanVien.diaChi";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString("maNV");
				String hoTen = rs.getString("hoTen");
				String soDT = rs.getString("soDT");
				String diaChi = rs.getString("diaChi");
				float ngayLam = rs.getFloat("soNgayLam");
				double luong = rs.getDouble("tongLuong");
				Object[] o = { maNV, hoTen, soDT, diaChi, ngayLam, luong };
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
