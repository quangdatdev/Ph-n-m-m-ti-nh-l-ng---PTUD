package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.TaiKhoanDao;
import entity.TaiKhoan;

import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.awt.Toolkit;

public class DangNhapGUI extends JFrame implements ActionListener, KeyListener {

	private JPanel contentPane;
	private JTextField txtTenDN;
	private JButton btnDN;
	private JPasswordField txtMK;
	private JButton btnDMK;

	// 7/12
	public static TaiKhoan tk;
	private TaiKhoanDao tkDao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhapGUI frame = new DangNhapGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DangNhapGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("hinh\\icons8-shoe-67.png"));
		setTitle("Công Ty Giày CK-D");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 618, 381);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDN = new JLabel("ĐĂNG NHẬP");
		lblDN.setBounds(235, 22, 183, 35);
		lblDN.setFont(new Font("Times New Roman", Font.BOLD, 26));
		contentPane.add(lblDN);

		JLabel lblTDN = new JLabel("Tên đăng nhập:");
		lblTDN.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTDN.setBounds(68, 94, 147, 48);
		contentPane.add(lblTDN);

		txtTenDN = new JTextField();
		txtTenDN.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtTenDN.setBounds(199, 99, 264, 35);
		contentPane.add(txtTenDN);
		txtTenDN.setText("ADMIN");
		txtTenDN.setColumns(10);

		JLabel lblMK = new JLabel("Mật khẩu:");
		lblMK.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMK.setBounds(68, 166, 116, 48);
		contentPane.add(lblMK);

		btnDN = new JButton("Login");

		btnDN.setIcon(new ImageIcon("hinh\\IconLogin.png"));
		btnDN.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDN.setBounds(199, 249, 127, 35);
		contentPane.add(btnDN);

		txtMK = new JPasswordField();
		txtMK.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtMK.setBounds(199, 171, 264, 35);
		txtMK.setText("123");
		contentPane.add(txtMK);

		btnDN.addActionListener(this);
		btnDN.setMnemonic(KeyEvent.VK_ENTER);

		btnDMK = new JButton("Đổi Mật Khẩu");

		btnDMK.setForeground(new Color(0, 0, 255));
		btnDMK.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnDMK.setBounds(336, 251, 127, 35);
		contentPane.add(btnDMK);
//		txtTenDN.requestFocus();
		this.addKeyListener(this);
		btnDMK.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnDN)) {
			dangNhap();

		}
//		if (o.equals(btnReset)) {
//			txtTenDN.setText("");
//			txtMK.setText("");
//			txtTenDN.requestFocus();
//		}
		if (o.equals(btnDMK)) {
			FrmDoiMatKhau gui = new FrmDoiMatKhau();
			gui.setVisible(true);

			this.setVisible(false);
		}

	}

	public void dangNhap() {
		StringBuilder sb = new StringBuilder();
		vaildateEmpty(txtTenDN, sb, "Tên đăng nhập không được để trống");
		vaildateEmpty(txtMK, sb, " Mật khẩu không được để trống");
		if (sb.length() > 0) {
			showErrorDialog(this, sb.toString(), "Lỗi");
			return;
		}
		tkDao = new TaiKhoanDao();
		try {
			tk = tkDao.checkLogin(txtTenDN.getText(), new String(txtMK.getPassword()));
			if (tk == null) {
				showErrorDialog(this, "Lỗi", "Tên đăng nhập hoặc mật khẩu không đúng");
			} else if (txtTenDN.getText().matches("NV[0-9]{2}")) {
//				new DangNhapGUI().setVisible(false);
				ManHinhChinh frmManHinhChinh = new ManHinhChinh();
				frmManHinhChinh.setVisible(true);
				frmManHinhChinh.mntmNhanVien.setEnabled(true);
				frmManHinhChinh.mntmPhanCongCN.setEnabled(false);
				frmManHinhChinh.mntmChamCong.setEnabled(true);
				frmManHinhChinh.mntmCongDoanSX.setEnabled(true);
//				frmManHinhChinh.mntmLuongCB.setEnabled(false);
				frmManHinhChinh.mntmTKNhanVien.setEnabled(true);
				frmManHinhChinh.mntmCongDoanSX.setEnabled(false);
				frmManHinhChinh.mnTimKiem.setEnabled(true);
				frmManHinhChinh.mnXuLy.setEnabled(false);
				this.setVisible(false);
			} else if (txtTenDN.getText().matches("CN[0-9]{2}")) {
//				new DangNhapGUI().setVisible(false);
//				new ManHinhChinh().setVisible(true);
				ManHinhChinh frmManHinhChinh = new ManHinhChinh();
				frmManHinhChinh.setVisible(true);
				frmManHinhChinh.mntmNhanVien.setEnabled(false);
				frmManHinhChinh.mntmPhanCongCN.setEnabled(false);
				frmManHinhChinh.mntmChamCong.setEnabled(false);
				frmManHinhChinh.mntmCongDoanSX.setEnabled(false);
//				frmManHinhChinh.mntmLuongCB.setEnabled(false);
				frmManHinhChinh.mntmTKNhanVien.setEnabled(false);
				frmManHinhChinh.mntmCongDoanSX.setEnabled(false);
				frmManHinhChinh.mnTimKiem.setEnabled(false);
				frmManHinhChinh.mntmTienLuong.setEnabled(false);
				frmManHinhChinh.mnXuLy.setEnabled(false);
				this.setVisible(false);
			} else if (txtTenDN.getText().matches("ADMIN")) {
				new DangNhapGUI().setVisible(false);
				new ManHinhChinh().setVisible(true);
				this.setVisible(false);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
			showErrorDialog(this, e1.getMessage(), "Lỗi");
		}
	}

	public static void vaildateEmpty(JTextField txtField, StringBuilder sb, String errorMessage) {
		if (txtField.getText().equals("")) {
			sb.append(errorMessage).append("\n");
			txtField.requestFocus();
		} else {
			txtField.setBackground(Color.white);
		}
	}

	public static void vaildateEmpty(JPasswordField txtField, StringBuilder sb, String errorMessage) {
		String password = new String(txtField.getPassword());
		if (password.equals("")) {
			sb.append(errorMessage).append("\n");
			txtField.requestFocus();
		} else {
			txtField.setBackground(Color.white);
		}
	}

	public static void showMessageDialog(Component parent, String content, String title) {
		JOptionPane.showMessageDialog(parent, title, content, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void showErrorDialog(Component parent, String content, String title) {
		JOptionPane.showMessageDialog(parent, title, content, JOptionPane.ERROR_MESSAGE);
	}

	public static int showConfirmDialog(Component parent, String content, String title) {

		int choose = JOptionPane.showConfirmDialog(parent, title, content, JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		return choose;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			dangNhap();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
