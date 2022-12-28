package gui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CNDao;
import dao.NVDao;
import entity.CongNhan;
import entity.NhanVien;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

public class FrmThongTinTaiKhoan extends JPanel {
	private JTextField txtHoTen;
	private JTextField txtGioiTinh;
	private JTextField txtMaNV;
	private NVDao nvDao;
	private NhanVien nv;
	private JTextField txtSoDT;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public FrmThongTinTaiKhoan() throws Exception {
		setLayout(null);

		JLabel lblHoTen = new JLabel("Họ Tên:");
		lblHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblHoTen.setBounds(632, 132, 102, 42);
		add(lblHoTen);

		JLabel lblThongTinTaiKhoan = new JLabel("Thông Tin Tài Khoản");
		lblThongTinTaiKhoan.setBounds(653, 5, 284, 35);
		lblThongTinTaiKhoan.setForeground(new Color(255, 128, 128));
		lblThongTinTaiKhoan.setFont(new Font("Times New Roman", Font.BOLD, 30));
		add(lblThongTinTaiKhoan);

		JLabel lblGioiTinh = new JLabel("Giới Tính:");
		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblGioiTinh.setBounds(632, 226, 102, 42);
		add(lblGioiTinh);

		JLabel lblgioiTinh = new JLabel("Số ĐT:");
		lblgioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblgioiTinh.setBounds(632, 333, 120, 42);
		add(lblgioiTinh);

		JLabel lblAvatar = new JLabel("");
		lblAvatar.setBounds(385, 100, 129, 129);
		lblAvatar.setIcon(new ImageIcon("hinh\\user.png"));
		add(lblAvatar);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtHoTen.setEditable(false);
		txtHoTen.setBounds(744, 136, 284, 35);
		add(txtHoTen);
		txtHoTen.setColumns(10);

		txtGioiTinh = new JTextField();
		txtGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtGioiTinh.setEditable(false);
		txtGioiTinh.setColumns(10);
		txtGioiTinh.setBounds(744, 230, 284, 35);
		add(txtGioiTinh);

		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtMaNV.setEditable(false);
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(360, 244, 178, 35);
		add(txtMaNV);

		txtSoDT = new JTextField();
		txtSoDT.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtSoDT.setEditable(false);
		txtSoDT.setColumns(10);
		txtSoDT.setBounds(744, 333, 284, 35);
		add(txtSoDT);

		updateTableData();
	}

	private void updateTableData() throws Exception {
		NVDao nvDao = new NVDao();
		List<NhanVien> list = nvDao.getAllNhanVien();
		for (NhanVien nv : list) {
			txtMaNV.setText(nv.getMaNV());
			txtHoTen.setText(nv.getHoTen());
			txtGioiTinh.setText(nv.getGioiTinh());
			txtSoDT.setText(nv.getSoDT());
			;
		}

	}
}
