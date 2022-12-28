package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import dao.CaLamCNDao;
import dao.ChamCongCNDao;

import dao.LuongCNDao;
import dao.PhanCongCNDao;

import entity.CaLamCN;
import entity.ChamCongCN;

import entity.LuongCN;

import entity.PhanCongCN;

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class FrmChamCongCN extends JPanel implements ActionListener {
	private JTable tableCC;
	private JTable tableCC_1;
	private JTable table_1;
	private JTextField txtMaCa;
	private DefaultTableModel modelCC;
	private PhanCongCNDao pcCNDao;
	private ChamCongCNDao ccCNDao;
	private JTextField txtNgayCham;
	private JButton btnChamCong;
	private JButton btnLammoi;
	private JTextField txtMaCong;
	private List<PhanCongCN> listPC;
	private DefaultTableModel modelCCCN;
	private CaLamCNDao caLamCNDao;
	private LuongCNDao luongCNDao;
	private List<ChamCongCN> listCC;
	private JComboBox<String> cboCaLam;
	private JButton btnLayDS;
	private List<ChamCongCN> listx;
	private JButton btnXoa;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public FrmChamCongCN() throws Exception {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 997, 241);
		add(scrollPane);

		tableCC = new JTable();
		tableCC.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 17));

//		String[] headers2 = "Mã PC;Mã CN;Tên CN;Có Mặt;Có Phép;Số Lượng".split(";");
//		modelCC = new DefaultTableModel(headers2, 0);
		tableCC.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane
				.setViewportView(tableCC_1 = new JTable(modelCC = new DefaultTableModel(new Object[][] {},
						new String[] { "Ma\u0303 PC", "Ma\u0303 CN", "T\u00EAn CN", "T\u00EAn C\u0110", "T\u00EAn SP",
								"Co\u0301 M\u0103\u0323t", "Co\u0301 Phe\u0301p",
								"S\u00F4\u0301 L\u01B0\u01A1\u0323ng" }) {
					Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, String.class,
							String.class, Boolean.class, Boolean.class, Object.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				}));
		tableCC_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tableCC_1.setRowHeight(25);
		tableCC_1.setAutoCreateRowSorter(true);
		tableCC_1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane.setViewportView(tableCC_1);

		tableCC_1.addMouseListener(new MouseListener() {

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

			}
		});

		JLabel lblChamCongCN = new JLabel("Chấm Công Công Nhân");
		lblChamCongCN.setForeground(new Color(255, 128, 128));
		lblChamCongCN.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblChamCongCN.setBounds(674, 10, 309, 41);
		add(lblChamCongCN);

		JLabel lblCaLam = new JLabel("Ca Làm:");
		lblCaLam.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCaLam.setBounds(1270, 59, 74, 29);
		add(lblCaLam);

		cboCaLam = new JComboBox();
		cboCaLam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboCaLam.setBackground(Color.WHITE);
		cboCaLam.setBounds(1378, 60, 112, 27);
		cboCaLam.addItem("Sáng");
		cboCaLam.addItem("Chiều");
		
		add(cboCaLam);

		cboCaLam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				caLamCNDao = new CaLamCNDao();
				try {
					CaLamCN c = caLamCNDao.getCaTheoTen(cboCaLam.getSelectedItem().toString());
					txtMaCa.setText(c.getMaCa());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});


		btnLayDS = new JButton("Lấy Danh Sách Công Nhân Chấm Công");
		btnLayDS.setIcon(new ImageIcon("hinh\\iconUpdate.png"));
		btnLayDS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLayDS.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnLayDS.setBounds(1017, 159, 351, 29);
		add(btnLayDS);

		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("hinh\\iconXoa.png"));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnXoa.setBounds(1378, 159, 112, 29);
		add(btnXoa);

		btnChamCong = new JButton("Chấm Công");
		btnChamCong.setIcon(new ImageIcon("hinh\\iconAdd.png"));
		btnChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnChamCong.setBounds(1017, 209, 149, 29);
		add(btnChamCong);

		btnLammoi = new JButton("Đặt Lại Chấm Công ");
		btnLammoi.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnLammoi.setBounds(1176, 209, 192, 29);
		add(btnLammoi);

//		JScrollPane scrollPane_1 = new JScrollPane();
//		scrollPane_1.setBounds(0, 327, 1520, 388);
//		add(scrollPane_1);
//
//		table_1 = new JTable();
//		table_1.setModel(modelCCCN = new DefaultTableModel(
//				new Object[][] { { null, null, null, null, null, null, null, null, null, null, null, null }, },
//				new String[] { "M\u00E3 C\u00F4ng ", "M\u00E3 CN", "T\u00EAn CN", "M\u00E3 PC", "M\u00E3 C\u0110",
//						"T\u00EAn C\u0110", "M\u00E3 Ca", "Ca", "S\u1ED1 SP", "Ng\u00E0y Ch\u1EA5m ",
//						"Tr\u1EA1ng Th\u00E1i", "Ngh\u1EC9 Ph\u00E9p" }) {
//			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
//					String.class, String.class, String.class, Integer.class, String.class, String.class, String.class };
//
//			public Class getColumnClass(int columnIndex) {
//				return columnTypes[columnIndex];
//			}
//		});
//		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//		scrollPane_1.setViewportView(table_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 327, 1542, 402);
		final Scrollbar s = new Scrollbar();
		table_1 = new JTable();
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers = "Mã Công;Mã CN;Tên CN;Mã PC;Mã CD;Tên CĐ;Mã Ca;Ca;Số SP;Ngày Chấm;Trạng Thái;Nghỉ Phép"
				.split(";");
		modelCCCN = new DefaultTableModel(headers, 0);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane_1.setViewportView(table_1 = new JTable(modelCCCN));
		scrollPane_1.add(s);
		table_1.setRowHeight(25);
		table_1.setAutoCreateRowSorter(true);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		add(scrollPane_1);

		JLabel lblMaCa = new JLabel("Mã Ca:");
		lblMaCa.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMaCa.setBounds(1017, 59, 74, 29);
		add(lblMaCa);

		txtMaCa = new JTextField();
		txtMaCa.setEditable(false);
		txtMaCa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaCa.setColumns(10);
		txtMaCa.setBounds(1087, 60, 121, 26);
		add(txtMaCa);

		txtNgayCham = new JTextField();
		txtNgayCham.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtNgayCham.setBounds(1378, 106, 112, 32);
		add(txtNgayCham);
		txtNgayCham.setColumns(10);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		txtNgayCham.setText(LocalDateTime.now().format(formatter));

		txtMaCong = new JTextField();
		txtMaCong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaCong.setEditable(false);
		txtMaCong.setBounds(1087, 109, 121, 26);
		add(txtMaCong);
		txtMaCong.setColumns(10);

		JLabel lblMaCong = new JLabel("Mã công:");
		lblMaCong.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMaCong.setBounds(1017, 112, 74, 21);
		add(lblMaCong);

		btnChamCong.addActionListener(this);
		btnLayDS.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLammoi.addActionListener(this);

		ConnectDB.getDataBase().getConnection();
//		try {
//			updateTableData();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		updateTableDataCN();
//		updateCBSP();
//		updateCBCD();
		refreshMaCC();

	}

//	private void updateCBCD() {
//		cdsxDao = new CongDoanSXDao();
//		try {
//			listCDsx = cdsxDao.getAllCongDoan();
//			for (CongDoanSanXuat cd : listCDsx) {
//				String tencd = cd.getTenCD().toString();
//
//				cboTenCD.addItem(tencd);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		caLamCNDao = new CaLamCNDao();

		if (o.equals(btnChamCong)) {

			try {
				int row = tableCC_1.getSelectedRow();
				CaLamCN ca = caLamCNDao.getCaTheoMa(txtMaCa.getText().toString());
				PhanCongCN pc = pcCNDao.getPCTheoMaPC(modelCC.getValueAt(row, 0).toString());
				boolean nghi;
				String trangthai = "";
				if (modelCC.getValueAt(row, 5) == null) {
					nghi = false;

				} else {
					nghi = true;
				}

				boolean phep;
				if (modelCC.getValueAt(row, 6) == null) {
					phep = false;
				} else {
					phep = true;
				}
				if (valiData()) {
					int soluong = Integer.parseInt(modelCC.getValueAt(row, 7).toString());

					LocalDate loc = LocalDate.parse(txtNgayCham.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					// String , int , localdate , calam , boolean, phanc , boolean
					ChamCongCN cccn = new ChamCongCN(txtMaCong.getText().toString(), soluong, loc, ca, phep, pc, nghi);

					ccCNDao.themCongCN(cccn);

					if (modelCC.getValueAt(row, 5) != null && modelCC.getValueAt(row, 6) == null) {
						capNhatLuong();
					} else {
						capNhatLuong1();
					}
					modelCCCN.addRow(new Object[] { cccn.getMaCong(), cccn.getMaPC().getMaCN().getMaCN(),
							cccn.getMaPC().getMaCN().getHoTen(), cccn.getMaPC().getMaPC(),
							cccn.getMaPC().getCDSX().getMaCD(), cccn.getMaPC().getCDSX().getTenCD(),
							cccn.getMaCa().getMaCa(), cccn.getMaCa().getTenCa(), cccn.getSoSP(), cccn.getNgayCham(),
							cccn.isTrangThai() ? "Có mặt" : "Vắng", cccn.isNghiPhep() ? "Có" : "Không" });
					modelCC.removeRow(row);
					refreshMaCC();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (o.equals(btnXoa))

		{
			int row = table_1.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn chấm công cần xóa!");
			} else {

				String maCCNV = table_1.getValueAt(row, 0).toString();
				int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Cảnh báo!",
						JOptionPane.YES_NO_OPTION);
				if (yes == JOptionPane.YES_OPTION) {
					try {
						if (ccCNDao.xoaCongCN(maCCNV)) {
							modelCCCN.removeRow(row);
							JOptionPane.showMessageDialog(null, "Xóa thành công!");
						}

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}

			}
//			refreshMaCC();
		}

		if (o.equals(btnLayDS)) {
			try {
				clearTable();
				layDSTheoNgay();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				txtNgayCham.setText(LocalDateTime.now().format(formatter));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (o.equals(btnLammoi)) {

			try {
				clearTable();
				updateTableData();
				int r = tableCC_1.getSelectedRow();
//				modelCC.setValueAt(null, r, 3);
//				modelCC.setValueAt(null, r, 4);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				txtNgayCham.setText(LocalDateTime.now().format(formatter));
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	private void refreshMaCC() {
		String maSP = "CCCN";
		List<ChamCongCN> list;
		try {
			listx = ccCNDao.getAllChamCongCN();
			int n = listx.size();
			for (int i = 1; i < n; i++) {
				if (n > 0 || n <= 9) {
					int x = i + 2;
					txtMaCong.setText(maSP + "0" + x);
				} else
					txtMaCong.setText(maSP + i);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void capNhatLuong() {

		ccCNDao = new ChamCongCNDao();
		luongCNDao = new LuongCNDao();
		try {
			listCC = ccCNDao.getAllChamCongCN();
			for (ChamCongCN cc : listCC) {
				String maLCN = "LCN" + cc.getMaCong().toString();
				double luong = cc.getSoSP() * cc.getMaPC().getCDSX().getGia();

				LuongCN lz = new LuongCN(maLCN, cc.getNgayCham().getMonthValue(), cc.getNgayCham().getYear(), cc,
						luong);
				luongCNDao.themLuong(lz);
//				modelLuong.addRow(new Object[] { l.getCn().getMaCN(), l.getCn().getHoTen(), l.getLuong()});
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private void capNhatLuong1() {

		ccCNDao = new ChamCongCNDao();
		luongCNDao = new LuongCNDao();
		try {
			listCC = ccCNDao.getAllChamCongCN();
			for (ChamCongCN cc : listCC) {
				String maLCN = "LCN" + cc.getMaCong().toString();
//				double luong = cc.getSoSP() * cc.getMaPC().getCDSX().getGia();

				LuongCN lz = new LuongCN(maLCN, cc.getNgayCham().getMonthValue(), cc.getNgayCham().getYear(), cc, 0);
				luongCNDao.themLuong(lz);
//				modelLuong.addRow(new Object[] { l.getCn().getMaCN(), l.getCn().getHoTen(), l.getLuong()});
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private void layDSTheoNgay() {
		clearTable();
		String[] date = txtNgayCham.getText().split("-");
		int year = Integer.parseInt(date[0]);
		int mon = Integer.parseInt(date[1]);
		int day = Integer.parseInt(date[2]);
		System.out.println("day:" + day + " mon: " + mon);
		List<ChamCongCN> listTest = ccCNDao.getCCCNByNgayLap2xx(day, mon, year);
		for (ChamCongCN cccn : listTest) {
			modelCCCN.addRow(new Object[] { cccn.getMaCong(), cccn.getMaPC().getMaCN().getMaCN(),
					cccn.getMaPC().getMaCN().getHoTen(), cccn.getMaPC().getMaPC(), cccn.getMaPC().getCDSX().getMaCD(),
					cccn.getMaPC().getCDSX().getTenCD(), cccn.getMaCa().getMaCa(), cccn.getMaCa().getTenCa(),
					cccn.getSoSP(), cccn.getNgayCham(), cccn.isTrangThai() ? "Có mặt" : "Vắng",
					cccn.isNghiPhep() ? "Có" : "Không" });
		}

		List<PhanCongCN> listPC = pcCNDao.getPCTheoNgay(day, mon, year);
		System.out.println("day:" + day + " mon: " + mon);
		for (PhanCongCN pc : listPC) {
			modelCC.addRow(new Object[] { pc.getMaPC(), pc.getMaCN().getMaCN(), pc.getMaCN().getHoTen() });
		}
	}

	private void updateTableData() throws Exception {// lay du lieu tu bang
		ccCNDao = new ChamCongCNDao();
		List<ChamCongCN> listCC = ccCNDao.getAllChamCongCN();
		pcCNDao = new PhanCongCNDao();

		listPC = pcCNDao.getAllPhanCong();

		for (PhanCongCN pc : listPC) {
			modelCC.addRow(new Object[] { pc.getMaPC(), pc.getMaCN().getMaCN(), pc.getMaCN().getHoTen(),
					pc.getCDSX().getTenCD(), pc.getCDSX().getSanPham().getTenSP() });
		}
		for (ChamCongCN cccn : listCC) {
			modelCCCN.addRow(new Object[] { cccn.getMaCong(), cccn.getMaPC().getMaCN().getMaCN(),
					cccn.getMaPC().getMaCN().getHoTen(), cccn.getMaPC().getMaPC(), cccn.getMaPC().getCDSX().getMaCD(),
					cccn.getMaPC().getCDSX().getTenCD(), cccn.getMaCa().getMaCa(), cccn.getMaCa().getTenCa(),
					cccn.getSoSP(), cccn.getNgayCham(), cccn.isTrangThai() ? "Có mặt" : "Vắng",
					cccn.isNghiPhep() ? "Có" : "Không" });

		}

//		
	}

	private void updateTableDataCN() throws Exception {

		ccCNDao = new ChamCongCNDao();
		List<ChamCongCN> listCC = ccCNDao.getAllChamCongCN();
		pcCNDao = new PhanCongCNDao();
		listPC = pcCNDao.getAllPhanCong();
		for (PhanCongCN pc : listPC) {
			modelCC.addRow(new Object[] { pc.getMaPC(), pc.getMaCN().getMaCN(), pc.getMaCN().getHoTen(),
					pc.getCDSX().getTenCD(), pc.getCDSX().getSanPham().getTenSP() });
		}
	}

//	private void updateCBSP() {
//		SanPhamDao spDao = new SanPhamDao();
//		try {
//			List<SanPham> listSP = spDao.getAllSanPham();
//			for (SanPham sp : listSP) {
//				String tenSP = sp.getTenSP().toString();
//
//				cboTenSP.addItem(tenSP);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	public void clearTable() {
		modelCC.getDataVector().removeAllElements();
		modelCCCN.getDataVector().removeAllElements();
		revalidate();
	}

	private boolean valiData() {
		int row = tableCC_1.getSelectedRow();

		LocalDate ngayCham = LocalDate.parse(txtNgayCham.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String maCa = txtMaCa.getText().trim();
		if (maCa.equalsIgnoreCase("")) {
			thongbaoLoi(txtMaCa, "Mã ca không được để trống");
			return false;
		}
		if (modelCC.getValueAt(row, 5) != null && modelCC.getValueAt(row, 6) != null) {
			JOptionPane.showMessageDialog(null, "Không thể chấm công vừa vắng vừa có mặt");
			modelCC.setValueAt(null, row, 5);
			modelCC.setValueAt(null, row, 6);
			return false;
		}
		if (ngayCham.isBefore(LocalDate.now())) {
			thongbaoLoi(txtNgayCham, "Ngày chấm không hợp lệ!");
			return false;
		}
		if (ngayCham.isAfter(LocalDate.now())) {
			thongbaoLoi(txtNgayCham, "Ngày chấm không hợp lệ!");
			return false;
		}
		return true;
	}

	private void thongbaoLoi(JTextField txtHoTen2, String s) {
		txtHoTen2.requestFocus(true);
		JOptionPane.showMessageDialog(this, s);

	}
}
