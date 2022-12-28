package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CongDoanSXDao;
import dao.SanPhamDao;
import entity.CongDoanSanXuat;
import entity.SanPham;
import util.ConnectDB;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class FrmCongDoanSanXuat extends JPanel implements MouseListener, ActionListener {
	private JTextField txtMaCD;
	private JTextField txtTenCD;
	private JTextField txtGiaCD;
	private JTextField txtMaSP;
	private JTable tableSanPham;
	private JTable tableCDSX;
	private JTextField txtSoLuong;
	private JCheckBox chkCDT;
	private JComboBox<Integer> cboGiaiDoan;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXoaRong;
	private DefaultTableModel modelSanPham;
	private DefaultTableModel modelCDSX;
	private SanPhamDao spDao;
	private CongDoanSXDao cdsxDao;
	private JComboBox<String> cboTenSP;
	private List<SanPham> listSP;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public FrmCongDoanSanXuat() throws Exception {
		setLayout(null);

		JLabel lblCongDoanSX = new JLabel("Công Đoạn Sản Xuất");
		lblCongDoanSX.setForeground(new Color(255, 128, 128));
		lblCongDoanSX.setBackground(new Color(255, 128, 128));
		lblCongDoanSX.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblCongDoanSX.setBounds(611, 22, 246, 46);
		add(lblCongDoanSX);

		JLabel lblMaCD = new JLabel("Mã CD:");
		lblMaCD.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaCD.setBounds(169, 106, 74, 29);
		add(lblMaCD);

		txtMaCD = new JTextField();
		txtMaCD.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaCD.setColumns(10);
		txtMaCD.setBounds(260, 107, 128, 26);

		txtMaCD.setEditable(false);
		add(txtMaCD);

		JLabel lblTenCD = new JLabel("Tên CD:");
		lblTenCD.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenCD.setBounds(169, 146, 74, 29);
		add(lblTenCD);

		JLabel lblGiaCD = new JLabel("Giá CD:");
		lblGiaCD.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblGiaCD.setBounds(169, 185, 74, 29);
		add(lblGiaCD);

		txtTenCD = new JTextField();
		txtTenCD.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenCD.setColumns(10);
		txtTenCD.setBounds(260, 146, 471, 26);
		add(txtTenCD);

		txtGiaCD = new JTextField();
		txtGiaCD.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtGiaCD.setColumns(10);
		txtGiaCD.setBounds(260, 186, 128, 26);
		add(txtGiaCD);

		JLabel lblMaSP = new JLabel("Mã SP:");
		lblMaSP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaSP.setBounds(434, 104, 74, 29);
		add(lblMaSP);

		txtMaSP = new JTextField();
		txtMaSP.setEditable(false);
		txtMaSP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaSP.setColumns(10);
		txtMaSP.setBounds(518, 102, 213, 26);
		add(txtMaSP);

		JLabel lblTenSP = new JLabel("Tên SP:");
		lblTenSP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenSP.setBounds(434, 185, 74, 29);
		add(lblTenSP);

		JLabel lblSoLuong = new JLabel("Số Lượng:");
		lblSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSoLuong.setBounds(169, 224, 95, 29);
		add(lblSoLuong);

		chkCDT = new JCheckBox("Công Đoạn Cần Làm Trước:");
		chkCDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		chkCDT.setBounds(169, 277, 255, 21);
		add(chkCDT);

		cboGiaiDoan = new JComboBox<Integer>();
		cboGiaiDoan.setBackground(Color.WHITE);
		cboGiaiDoan.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cboGiaiDoan.setBounds(434, 273, 297, 29);
		cboGiaiDoan.addItem(1);
		cboGiaiDoan.addItem(2);
		cboGiaiDoan.addItem(3);
		cboGiaiDoan.addItem(4);
		cboGiaiDoan.addItem(5);
		cboGiaiDoan.addItem(6);
		cboGiaiDoan.addItem(7);
		cboGiaiDoan.addItem(8);
		add(cboGiaiDoan);

		JLabel lblDanhSachSP = new JLabel("Danh Sách Sản Phẩm");
		lblDanhSachSP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDanhSachSP.setBounds(781, 78, 174, 29);
		add(lblDanhSachSP);

//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(589, 114, 580, 244);
//		add(scrollPane);
//
//		table = new JTable();
//		table.setModel(
//				new DefaultTableModel(new Object[][] { { null, null, null, null, null }, }, new String[] { "M\u00E3 SP",
//						"T\u00EAn SP", "Ki\u1EC3u D\u00E1ng ", "Ch\u1EA5t Li\u1EC7u", "S\u1ED1 L\u01B0\u1EE3ng" }) {
//					Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class,
//							Integer.class };
//
//					public Class getColumnClass(int columnIndex) {
//						return columnTypes[columnIndex];
//					}
//				});
//		table.setFont(new Font("Times New Roman", Font.PLAIN, 17));
//		scrollPane.setViewportView(table);

		JScrollPane scrollPane = new JScrollPane();// sua tu day xuong
		scrollPane.setBounds(781, 117, 680, 241);

		tableSanPham = new JTable();
		tableSanPham.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers = "Mã SP;Tên SP;Kiểu Dáng;Chất Liệu;Số Lượng".split(";");
		modelSanPham = new DefaultTableModel(headers, 0);
		tableSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(tableSanPham = new JTable(modelSanPham));
		tableSanPham.setRowHeight(25);
		tableSanPham.setAutoCreateRowSorter(true);
		tableSanPham.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		add(scrollPane);
		JScrollPane scrollPane1 = new JScrollPane();// sua tu day xuong
		scrollPane1.setBounds(0, 384, 1540, 346);

		tableCDSX = new JTable();
		tableCDSX.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane1.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers1 = "Mã CD;Tên CD;Giá CD;Mã SP;Tên SP;Số Lượng;Giai Đoạn".split(";");
		modelCDSX = new DefaultTableModel(headers1, 0);
		tableCDSX.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane1.setViewportView(tableCDSX = new JTable(modelCDSX));
		tableCDSX.setRowHeight(25);
		tableCDSX.setAutoCreateRowSorter(true);
		tableCDSX.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		add(scrollPane1);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(260, 225, 128, 26);
		add(txtSoLuong);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("hinh\\iconAdd.png"));
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnThem.setBounds(169, 329, 132, 29);
		add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon("hinh\\iconUpdate.png"));
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnSua.setBounds(328, 329, 107, 29);
		add(btnSua);

		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("hinh\\iconXoa.png"));
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnXoa.setBounds(462, 329, 107, 29);
		add(btnXoa);

		btnXoaRong = new JButton("Xóa rỗng");
		btnXoaRong.setIcon(new ImageIcon("hinh\\iconRefresh.png"));
		btnXoaRong.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnXoaRong.setBounds(593, 329, 138, 29);
		add(btnXoaRong);

		cboTenSP = new JComboBox();
		cboTenSP.setBackground(Color.WHITE);
		cboTenSP.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cboTenSP.setBounds(518, 185, 213, 29);
		add(cboTenSP);
		cboTenSP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SanPhamDao spDao = new SanPhamDao();
				try {
					listSP = spDao.getSPheoTenSP(cboTenSP.getSelectedItem().toString());
					for (SanPham sp : listSP) {
						txtMaSP.setText(sp.getMaSP());
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		ConnectDB.getDataBase().getConnection();

//		tableSanPham.addMouseListener(this);
//		tableCDSX.addMouseListener(this);
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);
		updateTableData();
		updateCBSP();
		refreshMaCD();

		tableSanPham.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = tableSanPham.getSelectedRow();

				txtMaSP.setText(modelSanPham.getValueAt(row, 0).toString());
				cboTenSP.setSelectedItem(modelSanPham.getValueAt(row, 1).toString());
				txtSoLuong.setText(modelSanPham.getValueAt(row, 4).toString());
			}
		});
		tableCDSX.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int row1 = tableCDSX.getSelectedRow();
				txtMaSP.setText(modelCDSX.getValueAt(row1, 3).toString());
				txtMaCD.setText(modelCDSX.getValueAt(row1, 0).toString());
				txtGiaCD.setText(modelCDSX.getValueAt(row1, 2).toString());
				cboTenSP.setSelectedItem(modelCDSX.getValueAt(row1, 4).toString());
				txtSoLuong.setText(modelCDSX.getValueAt(row1, 5).toString());
				cboGiaiDoan.setSelectedItem(modelCDSX.getValueAt(row1, 6));
				txtTenCD.setText(modelCDSX.getValueAt(row1, 1).toString());

			}
		});
	}

	private void updateTableData() throws Exception {// lay du lieu tu bang
		spDao = new SanPhamDao();
		cdsxDao = new CongDoanSXDao();
		List<CongDoanSanXuat> listCD = cdsxDao.getAllCongDoan();
		List<SanPham> list = spDao.getAllSanPham();
		for (SanPham sp : list) {
			modelSanPham.addRow(
					new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getKieuDang(), sp.getChatLieu(), sp.getSoLuong() });
		}
		for (CongDoanSanXuat cdsx : listCD) {
			modelCDSX.addRow(new Object[] { cdsx.getMaCD(), cdsx.getTenCD(), cdsx.getGia(), cdsx.getSanPham().getMaSP(),
					cdsx.getSanPham().getTenSP(), cdsx.getSoLuong(), cdsx.getGiaiDoan() });
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {

			SanPham sp;
			try {

				sp = spDao.getSanPhamTheoMa(txtMaSP.getText().toString());
				if (valiData()) {
					CongDoanSanXuat cdsx = new CongDoanSanXuat(txtMaCD.getText().toString(),
							txtTenCD.getText().toString(), Double.parseDouble(txtGiaCD.getText().toString()),
							Integer.parseInt(txtSoLuong.getText().toString()),
							Integer.parseInt(cboGiaiDoan.getSelectedItem().toString()), sp);

					cdsxDao.themCongDoan(cdsx);
					modelCDSX.addRow(
							new Object[] { cdsx.getMaCD(), cdsx.getTenCD(), cdsx.getGia(), cdsx.getSanPham().getMaSP(),
									cdsx.getSanPham().getTenSP(), cdsx.getSoLuong(), cdsx.getGiaiDoan() });

					clearFill();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (o.equals(btnXoa)) {
			int row = tableCDSX.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn công đoạn cần xóa!");
			} else {

				String maCD = tableCDSX.getValueAt(row, 0).toString();
				int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Cảnh báo!",
						JOptionPane.YES_NO_OPTION);
				if (yes == JOptionPane.YES_OPTION) {
					try {
						if (cdsxDao.xoaCongDoan(maCD)) {
							modelCDSX.removeRow(row);
							JOptionPane.showMessageDialog(null, "Xóa thành công!");
						}

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
			}
		}
		if (o.equals(btnSua)) {
			int row = tableCDSX.getSelectedRow();
			SanPham sp;
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn công đoạn cần sửa!");
			} else {
				String maCD = tableCDSX.getValueAt(row, 0).toString();
				int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa?", "Cảnh báo!",
						JOptionPane.YES_NO_OPTION);
				if (yes == JOptionPane.YES_OPTION) {
					if (valiData()) {
//					SanPham sp1 = new SanPham(txtMaSP.getText().toString(), txtTenSP.getText().toString(),
//							txtKieuDang.getText().toString(), txtChatLieu.getText().toString(),
//							Integer.parseInt(txtSoLuong.getText().toString()));
						try {
							sp = spDao.getSanPhamTheoMa(txtMaSP.getText().toString());

							// TODO Auto-generated catch block

							CongDoanSanXuat cdsx1 = new CongDoanSanXuat(txtMaCD.getText().toString(),
									txtTenCD.getText().toString(), Double.parseDouble(txtGiaCD.getText().toString()),
									Integer.parseInt(txtSoLuong.getText().toString()),
									Integer.parseInt(cboGiaiDoan.getSelectedItem().toString()), sp);

							if (cdsxDao.suaCongDoan(cdsx1)) {
								clearTable();
								clearFill();
								updateTable();

								JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
							}

						} catch (SQLException e1) {

							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		}
		if (o.equals(btnXoaRong)) {
			clearFill();
		}

	}

	private void updateTable() throws Exception {// lay du lieu tu bang
		CongDoanSXDao cdsxDao = new CongDoanSXDao();
		List<CongDoanSanXuat> list = cdsxDao.getAllCongDoan();
		for (CongDoanSanXuat cdsx : list) {
			modelCDSX.addRow(new Object[] { cdsx.getMaCD(), cdsx.getTenCD(), cdsx.getGia(), cdsx.getSanPham().getMaSP(),
					cdsx.getSanPham().getTenSP(), cdsx.getSoLuong(), cdsx.getGiaiDoan() });
		}

	}

	private void clearFill() {
		txtMaCD.setText("");
		txtTenCD.setText("");
		txtGiaCD.setText("");
		txtMaSP.setText("");
		txtSoLuong.setText("");
		cboGiaiDoan.setSelectedItem("");
		cboTenSP.setSelectedItem("");
		refreshMaCD();
	}

	// Sự kiện table
	@Override
	public void mouseClicked(MouseEvent e) {

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

	public void clearTable() {
		modelCDSX.getDataVector().removeAllElements();
		revalidate();
	}

	private void refreshMaCD() {
		String maCD = "CD";
		List<CongDoanSanXuat> list;
		try {
			list = cdsxDao.getAllCongDoan();
			int n = list.size();
			for (int i = 1; i < n; i++) {
				if (n > 0 || n <= 9) {
					int x = i + 2;
					txtMaCD.setText(maCD + "0" + x);
				} else
					txtMaCD.setText(maCD + i);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void updateCBSP() {
		SanPhamDao spDao = new SanPhamDao();
		try {
			List<SanPham> listSP = spDao.getAllSanPham();
			for (SanPham sp : listSP) {
				String tenSP = sp.getTenSP().toString();

				cboTenSP.addItem(tenSP);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private boolean valiData() {
		int r = tableSanPham.getSelectedRow();
		String tenCD = txtTenCD.getText().toString().trim();
		String giaCD = txtGiaCD.getText().toString().trim();
		String sl = txtSoLuong.getText().toString().trim();
		String maSP = txtMaSP.getText().toString().trim();
		
		int soSP = Integer.parseInt(modelSanPham.getValueAt(r, 4).toString());

		if (tenCD.equalsIgnoreCase("")) {
			thongbaoLoi(txtTenCD, "Vui lòng nhập tên công đoạn");
			return false;
		}

		if (giaCD.equalsIgnoreCase("")) {
			thongbaoLoi(txtGiaCD, "Vui lòng nhập giá công đoạn!");
			return false;
		}

		if (giaCD.length() > 0) {

			try {
				Double x = Double.parseDouble(txtGiaCD.getText());
				if (x <= 0) {
					JOptionPane.showMessageDialog(null, "Giá công đoạn lớn hơn 0!");
					txtGiaCD.requestFocus();
					return false;
				}
			} catch (NumberFormatException e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Giá công đoạn là số!");
				txtGiaCD.requestFocus();
				return false;
			}
		}
		if (sl.equalsIgnoreCase("")) {
			thongbaoLoi(txtSoLuong, "Vui lòng nhập số lượng!");
			return false;
		}


		if (sl.length() > 0) {

			try {
				int y = Integer.parseInt(txtSoLuong.getText());
				if (y > soSP) {
					JOptionPane.showMessageDialog(null, "Số lượng làm không được quá số lượng yêu cầu!");
					txtSoLuong.requestFocus();
					return false;
				}
				if (y <= 0) {
					JOptionPane.showMessageDialog(null, "Số lương lớn hơn 0!");
					txtSoLuong.requestFocus();
					return false;
				}
			} catch (NumberFormatException e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Số lượng là số!");
				txtSoLuong.requestFocus();
				return false;
			}
		}

		if (maSP.equalsIgnoreCase("")) {
			thongbaoLoi(txtMaSP, "Vui lòng chọn sản phẩm!");
			return false;
		}

		return true;
	}

	private void thongbaoLoi(JTextField txtHoTen2, String s) {
		txtHoTen2.requestFocus(true);
		JOptionPane.showMessageDialog(this, s);

	}
}
