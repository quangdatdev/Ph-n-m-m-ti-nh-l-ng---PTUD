package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.events.MouseEvent;

import dao.TaiKhoanDao;
import entity.TaiKhoan;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class FrmDoiMatKhau extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTextField txtTK;
	private JPasswordField txtMKC;
	private JPasswordField txtMKM;
	private JPasswordField txtNL;
	private JButton btnDoi;
	private JButton btnThoat;
	private List<TaiKhoan> listTK;
	private TaiKhoanDao tkDao;
//	private JLabel lbMKC;
//	private JLabel lbMKM;
//	private JLabel lbNL;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrmDoiMatKhau frame = new FrmDoiMatKhau();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public FrmDoiMatKhau() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("hinh\\icons8-shoe-67.png"));
		setTitle("Công Ty Giày CK-D");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 415);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDMK = new JLabel("Đổi Mật Khẩu");
		lblDMK.setForeground(new Color(255, 128, 128));
		lblDMK.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDMK.setBounds(251, 10, 164, 34);
		contentPane.add(lblDMK);

		JLabel lblTK = new JLabel("Tài Khoản: ");
		lblTK.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTK.setBounds(91, 80, 100, 24);
		contentPane.add(lblTK);

		JLabel lblMKC = new JLabel("Mật Khẩu Cũ:");
		lblMKC.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMKC.setBounds(91, 138, 130, 24);
		contentPane.add(lblMKC);

		JLabel lblMKM = new JLabel("Mật Khẩu Mới:");
		lblMKM.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMKM.setBounds(91, 186, 130, 24);
		contentPane.add(lblMKM);

		JLabel lblNLMK = new JLabel("Nhập Lại:");
		lblNLMK.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNLMK.setBounds(91, 239, 100, 24);
		contentPane.add(lblNLMK);

		txtTK = new JTextField();
		txtTK.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTK.setBounds(243, 83, 225, 24);
		contentPane.add(txtTK);
		txtTK.setColumns(10);
		txtTK.setText("ADMIN");

		btnDoi = new JButton("Đổi");
		btnDoi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDoi.setBounds(243, 317, 85, 34);
		btnDoi.setMnemonic(KeyEvent.VK_ENTER);
		contentPane.add(btnDoi);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThoat.setBounds(353, 317, 85, 34);
		contentPane.add(btnThoat);

		txtMKC = new JPasswordField();
		txtMKC.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMKC.setBounds(243, 138, 225, 24);
		contentPane.add(txtMKC);

		txtMKM = new JPasswordField();
		txtMKM.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMKM.setBounds(243, 186, 225, 24);
		contentPane.add(txtMKM);

		txtNL = new JPasswordField();
		txtNL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtNL.setBounds(243, 239, 225, 24);
		contentPane.add(txtNL);

//		lbMKC = new JLabel("");
//
//		lbMKC.setBounds(478, 146, 31, 16);
//		contentPane.add(lbMKC);
//
//		lbMKM = new JLabel("");
//
//		;
//		lbMKM.setBounds(478, 194, 31, 16);
//		contentPane.add(lbMKM);
//
//		lbNL = new JLabel("");
//
//		lbNL.setBounds(478, 247, 31, 16);
//		contentPane.add(lbNL);

		tkDao = new TaiKhoanDao();
		btnThoat.addActionListener(this);
		btnDoi.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnDoi)) {
			String name = txtTK.getText();
			String op = new String(txtMKC.getPassword());
			String np = new String(txtMKM.getPassword());
			String nl = new String(txtNL.getPassword());
			TaiKhoan tk = new TaiKhoan();
			try {
				tk = tkDao.getTKTheoTKMK(name, op);
			} catch (SQLException ex) {
				Logger.getLogger(FrmDoiMatKhau.class.getName()).log(Level.SEVERE, null, ex);
			}
			if (txtTK.getText().equals("")) {
				return;
			}

			if (op.equals("")) {
				return;
			}
			if (np.equals("")) {
				return;
			}
			if (nl.equals("")) {
				return;
			}

			if (tk != null) {
				if (np.equalsIgnoreCase(nl)) {
					try {
						TaiKhoan tkm = new TaiKhoan(name, np);
						boolean check = tkDao.suaMK(tkm);
						if (check)
							JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công", "thông báo",
									JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, "Đổi mật khẩu thất bại", "thông báo",
								JOptionPane.ERROR_MESSAGE);
						Logger.getLogger(FrmDoiMatKhau.class.getName()).log(Level.SEVERE, null, ex);
					}

					return;
				} else {
					JOptionPane.showMessageDialog(null, "nhập lại mật khẩu phải trùng với mật khẩu mới", "thông báo",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "tài khoản hoặc mật khẩu bị sai", "thông báo",
						JOptionPane.ERROR_MESSAGE);
			}

		}
		if (o.equals(btnThoat)) {
			DangNhapGUI gui;
			try {
				gui = new DangNhapGUI();
				gui.setVisible(true);
				this.setEnabled(true);
				this.setVisible(false);
			} catch (Exception e2) {
				// TODO: handle exception
				Logger.getLogger(DangNhapGUI.class.getName()).log(Level.SEVERE, null, e2);
			}
		}
	}

	private boolean isHide = true;

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {

	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
