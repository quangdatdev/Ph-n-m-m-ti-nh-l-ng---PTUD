package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CNDao;
import dao.CongDoanSXDao;

import dao.PhanCongCNDao;
import dao.SanPhamDao;

import entity.CongDoanSanXuat;
import entity.CongNhan;

import entity.PhanCongCN;
import entity.SanPham;
import util.ConnectDB;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class FrmPhanCongCN extends JPanel implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2081706042344311737L;
	private JTable tableSanPham;
	private JTextField txtMaPC;
	private JTextField txtMaCD;
	private JTextField txtSoLuong;
	private DefaultTableModel modelSanPham;
	private JButton btnSua;
	private JButton btnThem;
	private JButton btnXoa;
	private JTable tablePC;
	private DefaultTableModel modelPC;
	private SanPhamDao spDao;
	private List<SanPham> list;
	private PhanCongCNDao pcDao = new PhanCongCNDao();
	private List<PhanCongCN> listPC;
	private CNDao cnDao;
	private CongDoanSXDao cdsxDao;
	private JTable tableCDSX;
	private DefaultTableModel modelCDSX;
	private List<CongDoanSanXuat> listCD;
	private JTextField txtNgay;
	private JComboBox<String> cboTenCN;
	private List<CongNhan> listCN;
	private JComboBox<String> cboTenCD;
	private JButton btnXoaRong;
	private JTextField txtMaCN;

	/**
	 * Create the panel.
	 */
	public FrmPhanCongCN() {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 452, 241);

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
		tableSanPham.addMouseListener(new MouseListener() {

			private List<CongDoanSanXuat> listA;

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
				int row = tableSanPham.getSelectedRow();
				String maX = modelSanPham.getValueAt(row, 0).toString();
				txtSoLuong.setText(modelSanPham.getValueAt(row, 4).toString());
				try {
					listA = cdsxDao.getCDTheoMASP(maX);
					clearTable1();
					for (CongDoanSanXuat cdsx : listA) {
						modelCDSX.addRow(new Object[] { cdsx.getMaCD(), cdsx.getTenCD(), cdsx.getGia(),
								cdsx.getSoLuong(), cdsx.getGiaiDoan() });

					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JLabel lblNewLabel = new JLabel("Phân Công Công Nhân");
		lblNewLabel.setForeground(new Color(255, 128, 128));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(626, 0, 309, 41);
		add(lblNewLabel);

		JLabel lblMaPC = new JLabel("Mã Phân Công:");
		lblMaPC.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMaPC.setBounds(482, 67, 123, 29);
		add(lblMaPC);

		txtMaPC = new JTextField();
		txtMaPC.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaPC.setColumns(10);
		txtMaPC.setBounds(603, 68, 105, 26);
		txtMaPC.setEditable(false);
		add(txtMaPC);

		JLabel lblSLCL = new JLabel("Số Lượng Cần Làm:");
		lblSLCL.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSLCL.setBounds(714, 67, 157, 29);
		add(lblSLCL);

		JLabel lblMaCN = new JLabel("Mã Công Nhân: ");
		lblMaCN.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMaCN.setBounds(482, 144, 123, 29);
		add(lblMaCN);

		JLabel lblMaCD = new JLabel("Mã Công Đoạn:");
		lblMaCD.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMaCD.setBounds(482, 216, 138, 29);
		add(lblMaCD);

		txtMaCD = new JTextField();
		txtMaCD.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaCD.setColumns(10);
		txtMaCD.setBounds(603, 217, 105, 26);
		txtMaCD.setEditable(false);
		add(txtMaCD);

		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("hinh\\iconXoa.png"));
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnXoa.setBounds(775, 336, 105, 29);
		add(btnXoa);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("hinh\\iconAdd.png"));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnThem.setBounds(490, 336, 115, 29);
		add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon("hinh\\iconUpdate.png"));
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnSua.setBounds(644, 336, 93, 29);
		add(btnSua);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 408, 1520, 322);

		// Mã PC;Mã CN;Tên CN;Mã CĐ;Tên CĐ;Số Lượng;
		tablePC = new JTable();
		tablePC.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers1 = "Mã PC;Mã CN;Tên CN;Mã CĐ;Tên CĐ;Số Lượng;Ngày bắt đầu".split(";");
		modelPC = new DefaultTableModel(headers1, 0);
		tablePC.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane_1.setViewportView(tablePC = new JTable(modelPC));
		tablePC.setRowHeight(25);
		tablePC.setAutoCreateRowSorter(true);
		tablePC.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane_1.setViewportView(tablePC);

		add(scrollPane_1);

		JLabel lblTenCD = new JLabel("Tên Công Đoạn:");
		lblTenCD.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTenCD.setBounds(714, 216, 130, 29);
		add(lblTenCD);

		JLabel lblTenCN = new JLabel("Tên Công Nhân:");
		lblTenCN.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTenCN.setBounds(714, 144, 123, 29);
		add(lblTenCN);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(869, 68, 204, 26);
		add(txtSoLuong);

		btnXoaRong = new JButton("Làm Mới");
		btnXoaRong.setIcon(new ImageIcon("hinh\\iconRefresh.png"));
		btnXoaRong.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnXoaRong.setBounds(915, 336, 138, 29);
		add(btnXoaRong);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(1094, 61, 436, 241);

		tableCDSX = new JTable();
		tableCDSX.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_2.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers2 = "Mã CD;Tên CD;Giá CD;Số Lượng;Giai Đoạn".split(";");
		modelCDSX = new DefaultTableModel(headers2, 0);
		tableCDSX.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane_2.setViewportView(tableCDSX = new JTable(modelCDSX));
		tableCDSX.setRowHeight(25);
		tableCDSX.setAutoCreateRowSorter(true);
		tableCDSX.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		add(scrollPane_2);

		txtNgay = new JTextField();
		txtNgay.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtNgay.setBounds(603, 268, 105, 34);
		add(txtNgay);
		txtNgay.setColumns(10);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		txtNgay.setText(LocalDate.now().format(formatter));
		txtNgay.setEditable(false);

		JLabel lblNgayPC = new JLabel("Ngày PC:");
		lblNgayPC.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNgayPC.setBounds(482, 270, 115, 29);
		add(lblNgayPC);

		cboTenCN = new JComboBox();
		cboTenCN.setBackground(Color.WHITE);
		cboTenCN.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cboTenCN.setBounds(869, 144, 204, 29);
		add(cboTenCN);
		txtMaCN = new JTextField();
		txtMaCN.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaCN.setEditable(false);
		txtMaCN.setColumns(10);
		txtMaCN.setBounds(603, 144, 105, 26);
		add(txtMaCN);
		cboTenCN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CNDao cnDao = new CNDao();
				try {
					listCN = cnDao.getCongNhanByHoTen(cboTenCN.getSelectedItem().toString());
					for (CongNhan cn : listCN) {
						txtMaCN.setText(cn.getMaCN());
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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

				int r = tableCDSX.getSelectedRow();
				txtMaCD.setText(modelCDSX.getValueAt(r, 0).toString());
//				txtTenCD.setText(modelCDSX.getValueAt(r, 1).toString());
				cboTenCD.setSelectedItem(modelCDSX.getValueAt(r, 1).toString());

			}
		});
		;

		tablePC.addMouseListener(new MouseListener() {

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
				int r = tablePC.getSelectedRow();

				txtMaPC.setText(modelPC.getValueAt(r, 0).toString());
				txtSoLuong.setText(modelPC.getValueAt(r, 5).toString());
				txtMaCN.setText(modelPC.getValueAt(r, 1).toString());
				cboTenCN.setSelectedItem(modelPC.getValueAt(r, 2).toString());
//				txtTenCD.setText(modelPC.getValueAt(r, 4).toString());
				cboTenCD.setSelectedItem(modelPC.getValueAt(r, 4));
				txtMaCD.setText(modelPC.getValueAt(r, 3).toString());
				txtNgay.setText(modelPC.getValueAt(r, 6).toString());

			}
		});

		cboTenCD = new JComboBox();
		cboTenCD.setBackground(Color.WHITE);
		cboTenCD.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cboTenCD.setBounds(869, 216, 204, 29);
		add(cboTenCD);
		cboTenCD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				CongDoanSXDao cdsxDao = new CongDoanSXDao();
				try {

					listCD = cdsxDao.getCDTheoTenCD(cboTenCD.getSelectedItem().toString());
					for (CongDoanSanXuat cdsx : listCD) {
						txtMaCD.setText(cdsx.getMaCD());
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		cdsxDao = new CongDoanSXDao();
		pcDao = new PhanCongCNDao();
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoaRong.addActionListener(this);
		ConnectDB.getDataBase().getConnection();
		try {
			updateTableData();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		refreshMaPC();
		updateCBTenCD();
		updateCBTenCN();

	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			cnDao = new CNDao();
			cdsxDao = new CongDoanSXDao();
			pcDao = new PhanCongCNDao();

			try {
				CongNhan cn = cnDao.getCNTheoMa(txtMaCN.getText().toString());
				if (valiData()) {
					CongDoanSanXuat cdsx = cdsxDao.getCDTheoMaCD(txtMaCD.getText().toString());
//				System.out.println(cdsx.getMaCD().toString());
					LocalDate loc = LocalDate.parse(txtNgay.getText().toString(),
							DateTimeFormatter.ofPattern("yyyy-MM-dd"));

					PhanCongCN pc = new PhanCongCN(txtMaPC.getText().toString(), cn, cdsx,
							Integer.parseInt(txtSoLuong.getText().toString()), loc);
					pcDao.themPhanCong(pc);
					modelPC.addRow(new Object[] { pc.getMaPC(), pc.getMaCN().getMaCN(), pc.getMaCN().getHoTen(),
							pc.getCDSX().getMaCD(), pc.getCDSX().getTenCD(), pc.getSoLuong(), pc.getNgay() });
					clearFill();
					refreshMaPC();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}
		}
		if (o.equals(btnXoa)) {
			int row = tablePC.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn phân công cần xóa!");
			} else {

				String maPC = tablePC.getValueAt(row, 0).toString();
				int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Cảnh báo!",
						JOptionPane.YES_NO_OPTION);
				if (yes == JOptionPane.YES_OPTION) {
					try {
						if (pcDao.xoaPhanCong(maPC)) {
							modelPC.removeRow(row);
							JOptionPane.showMessageDialog(null, "Xóa thành công!");
						}

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}

			}

		}
		if (o.equals(btnSua)) {
			int row = tablePC.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn phân công cần sửa!");
			} else {
				String maPC = tablePC.getValueAt(row, 0).toString();
				int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa?", "Cảnh báo!",
						JOptionPane.YES_NO_OPTION);
				if (yes == JOptionPane.YES_OPTION) {
					if (valiData()) {
						try {
							CongNhan cn = cnDao.getCNTheoMa(txtMaCN.getText().toString());
							CongDoanSanXuat cdsx = cdsxDao.getCDTheoMaCD(txtMaCD.getText().toString());
//					System.out.println(cdsx.getMaCD().toString());
							LocalDate loc = LocalDate.parse(txtNgay.getText().toString(),
									DateTimeFormatter.ofPattern("yyyy-MM-dd"));

							PhanCongCN pc = new PhanCongCN(txtMaPC.getText().toString(), cn, cdsx,
									Integer.parseInt(txtSoLuong.getText().toString()), loc);

							if (pcDao.suaPhanCong(pc)) {
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

	private void clearFill() {
		txtMaCD.setText("");
		txtMaCN.setText("");
		txtMaPC.setText("");
		txtSoLuong.setText("");
//		txtTenCD.setText("");
		cboTenCD.setSelectedItem("");
		cboTenCN.setSelectedItem("");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		txtNgay.setText(LocalDate.now().format(formatter));
		refreshMaPC();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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

	private void clearTable1() {
		modelCDSX.getDataVector().removeAllElements();
		revalidate();
	}

	public void clearTable() {
		modelSanPham.getDataVector().removeAllElements();
		revalidate();
		modelPC.getDataVector().removeAllElements();
		revalidate();

	}

	private void updateTableData() throws Exception {
		spDao = new SanPhamDao();
		list = spDao.getAllSanPham();
		cdsxDao = new CongDoanSXDao();
		listCD = cdsxDao.getAllCongDoan();
		for (SanPham sp : list) {
			modelSanPham.addRow(
					new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getKieuDang(), sp.getChatLieu(), sp.getSoLuong() });
		}
		refreshMaPC();
//		 "Mã PC;Mã CN;Tên CN;Mã CĐ;Tên CĐ;Số Lượng"
		pcDao = new PhanCongCNDao();
		listPC = pcDao.getAllPhanCong();
		for (PhanCongCN pc : listPC) {
			modelPC.addRow(new Object[] { pc.getMaPC(), pc.getMaCN().getMaCN(), pc.getMaCN().getHoTen(),
					pc.getCDSX().getMaCD(), pc.getCDSX().getTenCD(), pc.getSoLuong(), pc.getNgay() });
		}

//		for (CongDoanSanXuat cdsx : listCD) {
//			modelCDSX.addRow(new Object[] { cdsx.getMaCD(), cdsx.getTenCD(), cdsx.getGia(), cdsx.getSanPham().getMaSP(),
//					cdsx.getSanPham().getTenSP(), cdsx.getSoLuong(), cdsx.getGiaiDoan() });
//		}

	}

	private void updateTable() throws Exception {
		pcDao = new PhanCongCNDao();
		listPC = pcDao.getAllPhanCong();
		for (PhanCongCN pc : listPC) {
			modelPC.addRow(new Object[] { pc.getMaPC(), pc.getMaCN().getMaCN(), pc.getMaCN().getHoTen(),
					pc.getCDSX().getMaCD(), pc.getCDSX().getTenCD(), pc.getSoLuong(), pc.getNgay() });
		}
	}

	private void refreshMaPC() {
		String maPC = "PC";
		List<PhanCongCN> list;
		try {
			list = pcDao.getAllPhanCong();
			int n = list.size();
			for (int i = 1; i < n; i++) {
				if (n > 0 || n <= 9) {
					int x = i + 2;
					txtMaPC.setText(maPC + "0" + x);
				} else
					txtMaPC.setText(maPC + i);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateCBTenCN() {

		CNDao cnDao = new CNDao();
		try {
			List<CongNhan> listCN = cnDao.getAllCongNhan();
			for (CongNhan sp : listCN) {
				String tenCN = sp.getHoTen().toString();
				cboTenCN.addItem(tenCN);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void updateCBTenCD() {
		CongDoanSXDao cdsxDao = new CongDoanSXDao();

		try {
			List<CongDoanSanXuat> listCD = cdsxDao.getAllCongDoan();
			for (CongDoanSanXuat cdsx : listCD) {
				String tenCD = cdsx.getTenCD().toString();
				cboTenCD.addItem(tenCD);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private boolean valiData() {
		int r = tableCDSX.getSelectedRow();
		String sl = txtSoLuong.getText().toString().trim();
		String maCN = txtMaCN.getText().toString().trim();
		String maCD = txtMaCD.getText().toString().trim();

		int slCL = Integer.parseInt(modelCDSX.getValueAt(r, 3).toString());
		if (sl.equalsIgnoreCase("")) {
			thongbaoLoi(txtSoLuong, "Vui lòng nhập số lượng!");
			return false;
		}

		if (sl.length() > 0) {

			try {
				int y = Integer.parseInt(txtSoLuong.getText());
				if (y > slCL) {
					JOptionPane.showMessageDialog(null, "Số lương cần làm không được lớn hơn số lượng yêu cầu");
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
		if (maCD.equalsIgnoreCase("")) {
			thongbaoLoi(txtMaCD, "Vui lòng chọn công đoạn!");
			return false;
		}
		if (maCN.equalsIgnoreCase("")) {
			thongbaoLoi(txtMaCN, "Vui lòng chọn công nhân!");
			return false;
		}

		return true;
	}

	private void thongbaoLoi(JTextField txtHoTen2, String s) {
		txtHoTen2.requestFocus(true);
		JOptionPane.showMessageDialog(this, s);

	}
}
