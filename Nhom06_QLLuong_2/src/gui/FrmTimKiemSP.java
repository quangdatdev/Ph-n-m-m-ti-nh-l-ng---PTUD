package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CNDao;
import dao.SanPhamDao;
import entity.CongNhan;
import entity.SanPham;
import util.ConnectDB;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class FrmTimKiemSP extends JPanel implements MouseListener, ActionListener {
	private JComboBox<String> cboMaSP;
	private JTextField txtTenSP;
	private JTextField txtKieuDang;
	private JTextField txtChatLieu;
	// private JSpiner so_luong; ?
	private JButton btnTim;
	private JTable tableSP;
	private JTable tableSP_1;
	private DefaultTableModel modelSP;
	private SanPhamDao spDao = new SanPhamDao();
	private List<SanPham> list;
	private List<SanPham> listTim;
	private SanPhamDao sanPhamdao;
	private JButton btnXoaRong;
	private SanPham sp = new SanPham();
//	private String tenSP;
//	private String chatLieu;
//	private String kieuDang;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public FrmTimKiemSP() throws Exception {
		setLayout(null);

		JLabel lblSnPhm = new JLabel("Tìm Kiếm Sản Phẩm");
		lblSnPhm.setForeground(new Color(255, 128, 128));
		lblSnPhm.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblSnPhm.setBounds(694, 10, 274, 56);
		add(lblSnPhm);

		JLabel lblMaSP = new JLabel("Mã SP:");
		lblMaSP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaSP.setBounds(455, 73, 74, 29);
		add(lblMaSP);

		JLabel lblTenSP = new JLabel("Tên SP:");
		lblTenSP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenSP.setBounds(842, 76, 74, 29);
		add(lblTenSP);

		txtTenSP = new JTextField();
		txtTenSP.setForeground(new Color(0, 0, 0));
		txtTenSP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenSP.setColumns(10);
		txtTenSP.setBounds(947, 76, 232, 26);
		add(txtTenSP);

		JLabel lblKieuDang = new JLabel("Kiểu Dáng:");
		lblKieuDang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblKieuDang.setBounds(455, 132, 91, 29);
		add(lblKieuDang);

		txtKieuDang = new JTextField();
		txtKieuDang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtKieuDang.setColumns(10);
		txtKieuDang.setBounds(581, 133, 216, 26);
		add(txtKieuDang);

		JLabel lblChatLieu = new JLabel("Chất Liệu:");
		lblChatLieu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblChatLieu.setBounds(842, 132, 85, 29);
		add(lblChatLieu);

		txtChatLieu = new JTextField();
		txtChatLieu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtChatLieu.setColumns(10);
		txtChatLieu.setBounds(947, 133, 232, 26);
		add(txtChatLieu);

		btnXoaRong = new JButton("Xóa rỗng");
		btnXoaRong.setIcon(new ImageIcon("hinh\\iconRefresh.png"));
		btnXoaRong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoaRong.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnXoaRong.setBounds(767, 195, 149, 29);
		add(btnXoaRong);

		//

//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(0, 253, 875, 286);
//		add(scrollPane);
		//
		JScrollPane scrollPane = new JScrollPane();// sua tu day xuong
		scrollPane.setBounds(10, 252, 1550, 623);

		tableSP = new JTable();
		tableSP.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers = "Mã SP;Tên SP;Kiểu Dáng;Chất Liệu;Số Lượng".split(";");
		modelSP = new DefaultTableModel(headers, 0);
		tableSP.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(tableSP_1 = new JTable(modelSP));
		tableSP.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		tableSP_1.setRowHeight(25);
		tableSP_1.setAutoCreateRowSorter(true);
		tableSP_1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		add(scrollPane);

//		updateTableData();

		// lay du lieu tu bang len cac o o tren

		btnTim = new JButton("Tìm Kiếm");
		btnTim.setIcon(new ImageIcon("hinh\\iconTimKiem.png"));
		btnTim.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnTim.setBounds(581, 195, 154, 29);
		add(btnTim);

		cboMaSP = new JComboBox();
		cboMaSP.setBackground(Color.WHITE);
		cboMaSP.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cboMaSP.setBounds(581, 75, 216, 29);
		add(cboMaSP);

		cboMaSP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				resetForm();

//					 try {
//						sp = spDao.getSanPhamTheoMa(cboMaSP.getSelectedItem().toString());
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					txtTenSP.setText(sp.getTenSP());
//					txtKieuDang.setText(sp.getKieuDang());
//					txtChatLieu.setText(sp.getChatLieu());

				if (cboMaSP.getSelectedIndex() == 0) {

					showInput(true);
				} else {

					showInput(false);
				}

			}
		});

		ConnectDB.getDataBase().getConnection();
		btnTim.addActionListener(this);
		btnXoaRong.addActionListener(this);
		tableSP_1.addMouseListener(this);

		DayDuLieu();
		updateComBoBox();

	}

//	public void addVaoBang() {
//		for (SanPham sp : list) {
//			defaultTableModel.addRow(
//					new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getKieuDang(), sp.getChatLieu(), sp.getSoLuong() });
//		}
//	}

	private void DayDuLieu() throws Exception {
		clearTable();
		list = new ArrayList<SanPham>();
		listTim = new ArrayList<SanPham>();
		if (sanPhamdao != null) {
			list = sanPhamdao.getAllSanPham();
			updateTableData();

		}
		cboMaSP.removeAllItems();
		cboMaSP.addItem("Tất Cả");
		for (SanPham sp : list) {
			cboMaSP.addItem(sp.getMaSP());

		}

	}

//
	private void updateComBoBox() throws Exception {
		SanPhamDao sanPhamDao = new SanPhamDao();
		List<SanPham> list = sanPhamDao.getAllSanPham();
		for (SanPham sp : list) {
			cboMaSP.addItem(sp.getMaSP());
		}
	}
//		for (SanPham sp : list) {
//			
//		}
	// cbMaSP.setSelectedItem(list.size() < 10 ? ("SP0" + (list.size() + 1)) : ("SP"
	// + (list.size() + 1)));

	public void clearTable() {
		modelSP.getDataVector().removeAllElements();
		revalidate();
	}

	public void addDLTim(List<SanPham> list) {
		clearTable();
		if (list.size() > 0) {
			for (SanPham sp : list) {
				modelSP.addRow(new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getKieuDang(), sp.getChatLieu(),
						sp.getSoLuong() });
			}
		}
	}

	public boolean checkForm() {
//		 tenSP = txtTenSP.getText().trim();
//		 kieuDang = txtKieuDang.getText().trim();
//		 chatLieu = txtChatLieu.getText().trim();

		String tenSP = txtTenSP.getText().trim();
		String kieuDang = txtKieuDang.getText().trim();
		String chatLieu = txtChatLieu.getText().trim();
		return true;
	}

//	private void loadData() {
//		defaultTableModel = (DefaultTableModel) table.getModel();
//		defaultTableModel.setRowCount(0);
//		for (SanPham sp : dssp) {
//			defaultTableModel.addRow(
//					new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getKieuDang(), sp.getChatLieu(), sp.getSoLuong() });
//		}
//	}
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		SanPhamDao sanPhamdao = new SanPhamDao();
		if (o.equals(btnTim)) {
			listTim.removeAll(listTim);
			if (!checkForm()) {
				return;
			}
			if (cboMaSP.getSelectedIndex() != 0) {

				String maSP = cboMaSP.getSelectedItem().toString();
				list.forEach((sp) -> {
					if (sp.getMaSP().equals(maSP)) {
						listTim.add(sp);
					}
				});
//			} else if (cboMaSP.getSelectedIndex()!=0) {
//				SanPhamDao spDao = new SanPhamDao();
//				try {
//					List<SanPham> list = spDao.getSanPhamByMa(cboMaSP.getSelectedItem().toString());
//					if (list.size() != 0) {
//						updateTableData();
//					}
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}

			} else {
				try {

					String tenSP = txtTenSP.getText();
					String kieuDang = txtKieuDang.getText();
					String chatLieu = txtChatLieu.getText();
					listTim = sanPhamdao.timSP("", tenSP, kieuDang, chatLieu);
				} catch (SQLException ex) {
					Logger.getLogger(FrmTimKiemSP.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			addDLTim(listTim);
		}
		if (o.equals(btnXoaRong)) {
			resetForm();
		}
	}

	public void showInput(boolean show) {
		txtTenSP.setEnabled(show);
		txtKieuDang.setEnabled(show);
		txtChatLieu.setEnabled(show);

	}

	public void resetForm() {
		cboMaSP.setSelectedItem("");
		txtTenSP.setText("");
		txtKieuDang.setText("");
		txtChatLieu.setText("");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int n = tableSP_1.getSelectedRow();
		if (n > -1) {
			cboMaSP.setSelectedItem(modelSP.getValueAt(n, 0).toString());
			txtTenSP.setText(modelSP.getValueAt(n, 1).toString());
			txtKieuDang.setText(modelSP.getValueAt(n, 2).toString());
			txtChatLieu.setText(modelSP.getValueAt(n, 3).toString());
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private void updateTableData() throws Exception {// lay du lieu tu bang
		SanPhamDao spDao = new SanPhamDao();
		List<SanPham> list = spDao.getAllSanPham();
		for (SanPham sp : list) {
			modelSP.addRow(
					new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getKieuDang(), sp.getChatLieu(), sp.getSoLuong() });
		}

	}
}
