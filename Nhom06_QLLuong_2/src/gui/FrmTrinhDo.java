package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CNDao;
import dao.NVDao;
import dao.SanPhamDao;
import dao.TayNgheDao;
import dao.TrinhDoDao;
import entity.CongNhan;
import entity.NhanVien;
import entity.SanPham;
import entity.TayNghe;
import entity.TrinhDo;
import util.ConnectDB;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class FrmTrinhDo extends JPanel implements ActionListener, MouseListener {
	private JTextField txtMaTD;
	private JTextField txtTenTD;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnLamMoi;
	// private JSpiner so_luong; ?
	private TrinhDoDao trDoDao = new TrinhDoDao();

	private JTable table;
	private DefaultTableModel defaultTableModel;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public FrmTrinhDo() throws Exception {
		setLayout(null);

		JLabel lblSnPhm = new JLabel("Trình Độ");
		lblSnPhm.setForeground(new Color(255, 128, 128));
		lblSnPhm.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblSnPhm.setBounds(656, 10, 160, 56);
		add(lblSnPhm);

		JLabel lblMaTrinhDo = new JLabel("Mã Trình Độ:");
		lblMaTrinhDo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaTrinhDo.setBounds(404, 75, 120, 29);
		add(lblMaTrinhDo);

		txtMaTD = new JTextField();
		txtMaTD.setEditable(false);
		txtMaTD.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaTD.setColumns(10);
		txtMaTD.setBounds(534, 76, 154, 26);

		add(txtMaTD);

		JLabel lblTenTD = new JLabel("Tên Trình Độ:");
		lblTenTD.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenTD.setBounds(730, 76, 126, 29);
		add(lblTenTD);

		txtTenTD = new JTextField();
		txtTenTD.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenTD.setColumns(10);
		txtTenTD.setBounds(866, 76, 244, 26);
		add(txtTenTD);
		//

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("hinh\\iconAdd.png"));
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnThem.setBounds(450, 173, 120, 32);
		add(btnThem);

		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("hinh\\iconXoa.png"));
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnXoa.setBounds(633, 173, 101, 32);
		add(btnXoa);

		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon("hinh\\iconUpdate.png"));
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnSua.setBounds(805, 174, 101, 31);
		add(btnSua);

		btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLamMoi.setIcon(new ImageIcon("hinh\\iconRefresh.png"));
		btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnLamMoi.setBounds(968, 175, 135, 29);
		add(btnLamMoi);

		//

//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(0, 253, 875, 286);
//		add(scrollPane);
		//
		JScrollPane scrollPane = new JScrollPane();// sua tu day xuong
		scrollPane.setBounds(0, 243, 1560, 504);

		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers = "Mã Trình Đô;Trình Độ".split(";");
		defaultTableModel = new DefaultTableModel(headers, 0);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(table = new JTable(defaultTableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		add(scrollPane);

		ConnectDB.getDataBase().getConnection();
		updateTableData();
		btnLamMoi.addActionListener(this);
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		// lay du lieu tu bang len cac o o tren
		table.addMouseListener(this);

		//
//		table = new JTable();
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null, null},
//			},
//			new String[] {
//				"M\u00E3 SP", "T\u00EAn SP", "Ki\u1EC3u D\u00E1ng", "Ch\u1EA5t Li\u1EC7u", "S\u1ED1 L\u01B0\u1EE3ng"
//			}
//		) {
//			Class[] columnTypes = new Class[] {
//				String.class, String.class, String.class, String.class, Integer.class
//			};
//			public Class getColumnClass(int columnIndex) {
//				return columnTypes[columnIndex];
//			}
//		});
//		table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//		scrollPane.setViewportView(table);
//		
//		JSpinner spinner = new JSpinner();
//		spinner.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(0), null, null, Integer.valueOf(1)));
//		spinner.setBounds(676, 123, 63, 25);
//		add(spinner);
		//
	}
	
	private void updateTableData() throws Exception {// lay du lieu tu bang
		TrinhDoDao tdDao = new TrinhDoDao();
		List<TrinhDo> list = tdDao.getAllTrinhDo();
		for (TrinhDo td : list) {
			defaultTableModel.addRow(new Object[] { td.getMaTrinhDo(), td.getTenTrinhDo() });
		}
		refreshMaTN();

	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		// Btn thêm
		if (o.equals(btnThem)) {
			if (valiData()) {
				TrinhDo td = new TrinhDo(txtMaTD.getText().toString(), txtTenTD.getText().toString());
				try {
					if (trDoDao.themTrinhDo(td)) {
						defaultTableModel.addRow(new Object[] { td.getMaTrinhDo(), td.getTenTrinhDo() });
						clearFill();
						refreshMaTN();

					} else {
						JOptionPane.showMessageDialog(this, "Trùng mã trình độ!");
					}
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
//				xoaAlldataTable();
//				updateTableData();

		}

		// Btn xoa rong
		if (o.equals(btnLamMoi)) {
			clearFill();
		}
		if (o.equals(btnXoa)) {
			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn trình độ cần xóa!");
			} else {

				String maTN = table.getValueAt(row, 0).toString();
				int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Cảnh báo!",
						JOptionPane.YES_NO_OPTION);
				if (yes == JOptionPane.YES_OPTION) {
					try {
						if (trDoDao.xoaTrinhDo(maTN)) {
							defaultTableModel.removeRow(row);
							JOptionPane.showMessageDialog(null, "Xóa thành công!");
						}

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}

			}
			clearFill();
			refreshMaTN();
		}
		if (o.equals(btnSua)) {
			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn trình độ cần sửa!");
			} else {

				String maTD = table.getValueAt(row, 0).toString();
				int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa?", "Cảnh báo!",
						JOptionPane.YES_NO_OPTION);
				if (yes == JOptionPane.YES_OPTION) {
					if (valiData()) {
						TrinhDo td = new TrinhDo(txtMaTD.getText().toString(), txtTenTD.getText().toString());
						try {
							if (trDoDao.suaTrinhDo(td)) {
								clearTable();
								clearFill();
								updateTableData();
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

	}

	private void clearFill() {
		txtMaTD.setText("");
		txtTenTD.setText("");
		refreshMaTN();
	}

	private void refreshMaTN() {
		String maTrDo = "TD";
		List<TrinhDo> list;
		try {
			list = trDoDao.getAllTrinhDo();
			int n = list.size();
			for (int i = 1; i < n; i++) {
				if (n > 0 || n <= 9) {
					int x = i + 2;
					txtMaTD.setText(maTrDo + "0" + x);
				} else
					txtMaTD.setText(maTrDo + i);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	private void xoaAlldataTable() {
//		model = (DefaultTableModel) table.getModel();
//		model.getDataVector().removeAllElements();
//		
//	}

	private boolean valiData() {
		String tenTD = txtTenTD.getText().toString().trim();

		if (tenTD.equalsIgnoreCase("")) {
			thonbaoLoi(txtTenTD, "Vui lòng nhập tên trình độ!");
			return false;
		}

		return true;
	}

	private void thonbaoLoi(JTextField txtHoTen2, String s) {
		txtHoTen2.requestFocus(true);
		JOptionPane.showMessageDialog(this, s);

	}

	// Sự kiện table
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
//		String maNV = model.getValueAt(row, 1).toString
		txtMaTD.setText(defaultTableModel.getValueAt(row, 0).toString());
		txtTenTD.setText(defaultTableModel.getValueAt(row, 1).toString());

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
		defaultTableModel.getDataVector().removeAllElements();
		revalidate();
	}


	//
//	private void updateTableData() throws Exception {// lay du lieu tu bang
//		SanPhamDao spDao = new SanPhamDao();
//		List<SanPham> list = spDao.getAllSanPham();
//		for (SanPham sp : list) {
//			defaultTableModel.addRow(
//					new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getKieuDang(), sp.getChatLieu(), sp.getSoLuong() });
//		}
//		refreshMaSP();
//
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		Object o = e.getSource();
//		// Btn thêm
//		if (o.equals(btnThem)) {
//			if (valiData()) {
//				SanPham sp = new SanPham(txtMaSP.getText().toString(), txtTenSP.getText().toString(),
//						txtKieuDang.getText().toString(), txtChatLieu.getText().toString(),
//						Integer.parseInt(txtSoLuong.getText().toString()));
//
//				try {
//					if (spDao.themSanPham(sp)) {
//						defaultTableModel.addRow(new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getKieuDang(),
//								sp.getChatLieu(), sp.getSoLuong() });
//						clearFill();
//						refreshMaSP();
//
//					} else {
//						JOptionPane.showMessageDialog(this, "Trùng mã sản phẩm!");
//					}
//				} catch (HeadlessException | SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
////				xoaAlldataTable();
////				updateTableData();
//
//		}
//
//		// Btn xoa rong
//		if (o.equals(btnLamMoi)) {
//			clearFill();
//		}
//		if (o.equals(btnXoa)) {
//			int row = table.getSelectedRow();
//			if (row == -1) {
//				JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa!");
//			} else {
//
//				String maSP = table.getValueAt(row, 0).toString();
//				int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Cảnh báo!",
//						JOptionPane.YES_NO_OPTION);
//				if (yes == JOptionPane.YES_OPTION) {
//					try {
//						if (spDao.xoaSanPham(maSP)) {
//							defaultTableModel.removeRow(row);
//							JOptionPane.showMessageDialog(null, "Xóa thành công!");
//						}
//
//					} catch (SQLException e1) {
//
//						e1.printStackTrace();
//					}
//				}
//
//			}
//			clearFill();
//			refreshMaSP();
//		}
//		if (o.equals(btnSua)) {
//			int row = table.getSelectedRow();
//			if (row == -1) {
//				JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần sửa!");
//			} else {
//
//				String maSP = table.getValueAt(row, 0).toString();
//				int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa?", "Cảnh báo!",
//						JOptionPane.YES_NO_OPTION);
//				if (yes == JOptionPane.YES_OPTION) {
//					if (valiData()) {
//						SanPham sp1 = new SanPham(txtMaSP.getText().toString(), txtTenSP.getText().toString(),
//								txtKieuDang.getText().toString(), txtChatLieu.getText().toString(),
//								Integer.parseInt(txtSoLuong.getText().toString()));
//						try {
//							if (spDao.suaSanPham(sp1)) {
//								clearTable();
//								clearFill();
//								updateTableData();
//								JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
//							}
//
//						} catch (SQLException e1) {
//
//							e1.printStackTrace();
//						} catch (Exception e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//					}
//				}
//			}
//		}
//
//	}
//
//	private void clearFill() {
//		txtMaSP.setText("");
//		txtTenSP.setText("");
//		txtKieuDang.setText("");
//		txtChatLieu.setText("");
//		txtSoLuong.setText("");
//		refreshMaSP();
//	}
//
//	private void refreshMaSP() {
//		String maSP = "SP";
//		List<SanPham> list;
//		try {
//			list = spDao.getAllSanPham();
//			int n = list.size();
//			for (int i = 1; i < n; i++) {
//				if (n > 0 || n <= 9) {
//					int x = i + 2;
//					txtMaSP.setText(maSP + "0" + x);
//				} else
//					txtMaSP.setText(maSP + i);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
////	private void xoaAlldataTable() {
////		model = (DefaultTableModel) table.getModel();
////		model.getDataVector().removeAllElements();
////		
////	}
//
//	private boolean valiData() {
//		String tenSP = txtTenSP.getText().toString().trim();
//		String chatLieu = txtChatLieu.getText().toString().trim();
//		String kieuDang = txtKieuDang.getText().toString().trim();
//		String sl = txtSoLuong.getText().toString().trim();
////		int soLuong = Integer.parseInt(txtSoLuong.getText().toString().trim());
//
//		if (tenSP.equalsIgnoreCase("")) {
//			thonbaoLoi(txtTenSP, "Vui lòng nhập tên sản phẩm!");
//			return false;
//		}
//
//		if (kieuDang.equalsIgnoreCase("")) {
//			thonbaoLoi(txtKieuDang, "Vui lòng nhập kiểu dáng");
//			return false;
//		}
//		if (chatLieu.equalsIgnoreCase("")) {
//			thonbaoLoi(txtChatLieu, "Vui lòng nhập chất liệu!");
//			return false;
//		}
//		if (sl.equalsIgnoreCase("")) {
//			thonbaoLoi(txtSoLuong, "Vui lòng nhập số lượng!");
//		}
//		if (sl.length() > 0) {
//			try {
//				int x = Integer.parseInt(txtSoLuong.getText());
//				if (x <= 0) {
//					JOptionPane.showMessageDialog(null, "Số lượng sản phẩm lớn hơn 0!");
//					txtSoLuong.requestFocus();
//					return false;
//				}
//			} catch (NumberFormatException e) {
//				JOptionPane.showMessageDialog(null, "Số lượng là số!");
//				txtSoLuong.requestFocus();
//				return false;
//			}
//		}
//		return true;
//	}
//
//	private void thonbaoLoi(JTextField txtHoTen2, String s) {
//		txtHoTen2.requestFocus(true);
//		JOptionPane.showMessageDialog(this, s);
//
//	}
//
//	// Sự kiện table
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		int row = table.getSelectedRow();
////		String maNV = model.getValueAt(row, 1).toString
//		txtMaSP.setText(defaultTableModel.getValueAt(row, 0).toString());
//		txtTenSP.setText(defaultTableModel.getValueAt(row, 1).toString());
//		txtKieuDang.setText(defaultTableModel.getValueAt(row, 2).toString());
//		txtChatLieu.setText(defaultTableModel.getValueAt(row, 3).toString());
//		txtSoLuong.setText(defaultTableModel.getValueAt(row, 4).toString());
//
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//	public void clearTable() {
//		defaultTableModel.getDataVector().removeAllElements();
//		revalidate();
//	}

}