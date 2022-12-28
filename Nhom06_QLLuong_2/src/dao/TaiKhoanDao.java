package dao;

import entity.CongNhan;
import entity.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConnectDB;

public class TaiKhoanDao {

	private TaiKhoan createTK(final ResultSet rs) throws SQLException {
		String tk, mk;
		tk = rs.getString("tenDangNhap");
		mk = rs.getString("matKhau");
		TaiKhoan acc = new TaiKhoan(tk, mk);
		return acc;
	}

	public TaiKhoan checkLogin(String tenDangNhap, String matKhau) throws Exception {
		String sql = "Select tenDangNhap, matKhau from TaiKhoan where tenDangNhap =? and matKhau = ?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tenDangNhap);
			pstmt.setString(2, matKhau);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				TaiKhoan tk = new TaiKhoan();
				tk.setTenDangNhap(tenDangNhap);
//				tk.setVaiTro(rs.getString("vaiTro"));
				return tk;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public TaiKhoan getTKTheoTKMK(String tk, String mk) throws SQLException {
		String sql = "select * from TaiKhoan where tenDangNhap =? and matKhau = ?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, tk);
			stmt.setString(2, mk);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {

				TaiKhoan acc = new TaiKhoan();
				acc = createTK(rs);
//				rs.getString("tenDangNhap");
//				mk = rs.getString("matKhau");
//				TaiKhoan acc = new TaiKhoan(tk, mk);
				return acc;
			}

			con.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		}
		return null;
	}

	public boolean suaMK(TaiKhoan tk) throws SQLException {
		int n = 0;
		String sql = "update TaiKhoan set matKhau =? where tenDangNhap =?";
		Connection con = ConnectDB.getDataBase().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, tk.getMatKhau());
			stmt.setString(2, tk.getTenDangNhap());

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
