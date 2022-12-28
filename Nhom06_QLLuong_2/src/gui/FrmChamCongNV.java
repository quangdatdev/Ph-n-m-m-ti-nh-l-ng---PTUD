package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.CaLamNVDao;
import dao.ChamCongNVDao;
import dao.LuongNVDao;
import dao.NVDao;
import entity.CaLamNV;
import entity.ChamCongNV;
import entity.LuongNV;
import entity.NhanVien;
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
import java.awt.Scrollbar;

public class FrmChamCongNV extends JPanel implements ActionListener, MouseListener {
	private JTextField txtMaCa;
	private JTextField txtGioLam;
	private JTextField txtMaCong;
	private JTable tableCCNV;

	private JComboBox<String> cboTenCa;
	private JButton btnLayDS;
	private JButton btnChamCong;
	private JButton btnDatLaiChamCong;
	private JButton btnXoa;
	private DefaultTableModel modelCC;
	private NVDao nvDao = new NVDao();
	private JTextField txtNgayCham;
	private CaLamNVDao caLamNVdao = new CaLamNVDao();
	private ChamCongNVDao cCNVDao = new ChamCongNVDao();
	private JTable tableCC;

	private DefaultTableModel modelCCNV;
	private LuongNVDao luongNVDao = new LuongNVDao();
	private List<ChamCongNV> listCC;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public FrmChamCongNV() throws Exception {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 689, 260);
		add(scrollPane);

		tableCC = new JTable();
		tableCC.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 17));

//		String[] headers2 = "Mã PC;Mã CN;Tên CN;Có Mặt;Có Phép;Số Lượng".split(";");
//		modelCC = new DefaultTableModel(headers2, 0);
		tableCC.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(tableCC = new JTable(modelCC = new DefaultTableModel(new Object[][] {},
				new String[] { "Ma\u0303 NV", "T\u00EAn NV", "Co\u0301 M\u0103\u0323t", "Co\u0301 Phe\u0301p" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, Boolean.class, Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		}));
		tableCC.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		tableCC.setRowHeight(25);
		tableCC.setAutoCreateRowSorter(true);
		tableCC.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane.setViewportView(tableCC);

		JLabel lblChamCongNV = new JLabel("Chấm Công Nhân Viên");
		lblChamCongNV.setForeground(new Color(255, 128, 128));
		lblChamCongNV.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblChamCongNV.setBounds(607, 9, 302, 41);
		add(lblChamCongNV);

		JLabel lblMaCa = new JLabel("Mã Ca:");
		lblMaCa.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMaCa.setBounds(835, 60, 74, 29);
		add(lblMaCa);

		txtMaCa = new JTextField();
		txtMaCa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaCa.setColumns(10);
		txtMaCa.setBounds(919, 61, 126, 26);
		txtMaCa.setEditable(false);
		add(txtMaCa);

		JLabel lblCaLam = new JLabel("Ca Làm:");
		lblCaLam.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCaLam.setBounds(1128, 60, 74, 29);
		add(lblCaLam);

		cboTenCa = new JComboBox();
		cboTenCa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboTenCa.setBackground(Color.WHITE);
		cboTenCa.setBounds(1240, 61, 111, 27);
		cboTenCa.addItem("Sáng");
		cboTenCa.addItem("Chiều");

		add(cboTenCa);

		cboTenCa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				caLamNVdao = new CaLamNVDao();

				try {
					CaLamNV caLamNV = caLamNVdao.getCaTheoTen(cboTenCa.getSelectedItem().toString());
					txtMaCa.setText(caLamNV.getMaCa());
					txtGioLam.setText(caLamNV.getGioTheoCa());
//					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		JLabel lblGioLam = new JLabel("Giờ Làm: ");
		lblGioLam.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblGioLam.setBounds(835, 113, 88, 29);
		add(lblGioLam);

		txtGioLam = new JTextField();
		txtGioLam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtGioLam.setColumns(10);
		txtGioLam.setBounds(919, 114, 126, 26);
		txtGioLam.setEditable(false);
		add(txtGioLam);

		JLabel lblNgayCham = new JLabel("Ngày Chấm:");
		lblNgayCham.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNgayCham.setBounds(1128, 113, 105, 29);
		add(lblNgayCham);

		JLabel lblMaCong = new JLabel("Mã công:");
		lblMaCong.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMaCong.setBounds(835, 174, 88, 29);
		add(lblMaCong);

		txtMaCong = new JTextField();
		txtMaCong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaCong.setColumns(10);
		txtMaCong.setBounds(916, 174, 129, 26);
		txtMaCong.setEditable(false);
		add(txtMaCong);

		btnLayDS = new JButton("Lấy Danh Sách Nhân Viên Chấm Công");
		btnLayDS.setIcon(new ImageIcon("hinh\\iconUpdate.png"));
		btnLayDS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLayDS.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnLayDS.setBounds(835, 234, 349, 29);
		add(btnLayDS);

		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("hinh\\iconXoa.png"));
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnXoa.setBounds(1240, 234, 111, 29);
		add(btnXoa);

		btnChamCong = new JButton("Chấm Công");
		btnChamCong.setIcon(new ImageIcon("hinh\\iconAdd.png"));
		btnChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnChamCong.setBounds(835, 292, 150, 29);
		add(btnChamCong);

		btnDatLaiChamCong = new JButton("Đặt Lại Chấm Công ");
		btnDatLaiChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnDatLaiChamCong.setBounds(1013, 292, 171, 29);
		add(btnDatLaiChamCong);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 344, 1550, 360);
		final Scrollbar s = new Scrollbar();
		tableCCNV = new JTable();
		tableCCNV.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers = "Mã Công;Ngày Chấm;Trạng Thái;Nghỉ Phép;Mã NV;Tên NV;Mã Ca;Ca;Giờ Làm".split(";");
		modelCCNV = new DefaultTableModel(headers, 0);
		tableCCNV.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane_1.setViewportView(tableCCNV = new JTable(modelCCNV));
		scrollPane_1.add(s);
		tableCCNV.setRowHeight(25);
		tableCCNV.setAutoCreateRowSorter(true);
		tableCCNV.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		add(scrollPane_1);
//		updateComBoBoxTenCa();

		txtNgayCham = new JTextField();
		txtNgayCham.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtNgayCham.setColumns(10);
		txtNgayCham.setBounds(1240, 114, 111, 26);
		txtNgayCham.setColumns(10);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		txtNgayCham.setText(LocalDateTime.now().format(formatter));
		add(txtNgayCham);
//		updateTableData();

		cCNVDao = new ChamCongNVDao();
		ConnectDB.getDataBase().getConnection();
		updateTableDataNV();
//		updateTableData();
		refreshMaCC();
		btnChamCong.addActionListener(this);
		btnDatLaiChamCong.addActionListener(this);
		btnLayDS.addActionListener(this);
		btnXoa.addActionListener(this);

	}

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnChamCong)) {
//			int u = 1;
			try {
				int row = tableCC.getSelectedRow();
				boolean nghi;
				if (modelCC.getValueAt(row, 2) == null) {
					nghi = false;
				} else {
					nghi = true;
				}

				boolean phep;
				if (modelCC.getValueAt(row, 3) == null) {
					phep = false;
				} else {
					phep = true;
				}

				if (valiData()) {
					NhanVien nv = nvDao.getNVTheoMa(modelCC.getValueAt(row, 0).toString());
					CaLamNV ca = caLamNVdao.getCaTheoMa(txtMaCa.getText().toString());
					LocalDate ngayCham = LocalDate.parse(txtNgayCham.getText(),
							DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//				

					ChamCongNV cCNV = new ChamCongNV(txtMaCong.getText(), ngayCham, nghi, phep, nv, ca);

					cCNVDao.themCongNV(cCNV);
					if (modelCC.getValueAt(row, 2) != null && modelCC.getValueAt(row, 3) == null) {
						capNhatLuong();
					} else {
						capNhatLuong1();
					}

					modelCCNV.addRow(new Object[] { cCNV.getMaCong(), cCNV.getNgayCham(),
							cCNV.isTrangThai() ? "Có mặt" : "Vắng", cCNV.isNghiPhep() ? "Có" : "Không",
							cCNV.getNv().getMaNV(), cCNV.getNv().getHoTen(), cCNV.getMaCa().getMaCa(),
							cCNV.getMaCa().getTenCa(), cCNV.getMaCa().getGioTheoCa() });

					modelCC.removeRow(row);
					refreshMaCC();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		if (o.equals(btnXoa))

		{
			int row = tableCCNV.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn chấm công cần xóa!");
			} else {

				String maCCNV = tableCCNV.getValueAt(row, 0).toString();
				int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Cảnh báo!",
						JOptionPane.YES_NO_OPTION);
				if (yes == JOptionPane.YES_OPTION) {
					try {
						if (cCNVDao.xoaCongNV(maCCNV)) {
							modelCCNV.removeRow(row);
							JOptionPane.showMessageDialog(null, "Xóa thành công!");
						}
						refreshMaCC();
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
				layDSChamCongTheoNgay();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				txtNgayCham.setText(LocalDateTime.now().format(formatter));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (o.equals(btnDatLaiChamCong)) {

			try {
				clearTable();
				updateTableData();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				txtNgayCham.setText(LocalDateTime.now().format(formatter));
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private void capNhatLuong() {

		cCNVDao = new ChamCongNVDao();
		luongNVDao = new LuongNVDao();

		try {
			listCC = cCNVDao.getAllChamCongNV();

			for (ChamCongNV cc : listCC) {
				String maLCN = "LNV" + cc.getMaCong().toString();
				double luong = (((cc.getNv().getLuongCoBan() * cc.getNv().getHeSoLuong())
						+ cc.getMaCa().getLuongTheoCa())) / 26 + cc.getNv().getTroCap();
				LuongNV luongNV = new LuongNV(maLCN, cc.getNgayCham().getMonthValue(), cc.getNgayCham().getYear(), cc,
						luong);
				luongNVDao.themLuongNV(luongNV);
			}
		} catch (Exception e1) {

			e1.printStackTrace();
		}

	}

	private void capNhatLuong1() {

		cCNVDao = new ChamCongNVDao();
		luongNVDao = new LuongNVDao();

		try {
			listCC = cCNVDao.getAllChamCongNV();

			for (ChamCongNV cc : listCC) {
				String maLCN = "LNV" + cc.getMaCong().toString();
				LuongNV luongNV = new LuongNV(maLCN, cc.getNgayCham().getMonthValue(), cc.getNgayCham().getYear(), cc,
						0);
				luongNVDao.themLuongNV(luongNV);
			}
		} catch (Exception e1) {

			e1.printStackTrace();
		}

	}

	private void updateTableDataNV() throws Exception {

		List<NhanVien> list = nvDao.getNhanVien();
		for (NhanVien nhanVien : list) {
			modelCC.addRow(new Object[] { nhanVien.getMaNV(), nhanVien.getHoTen() });
		}
	}

	private void updateTableData() throws Exception {
		cCNVDao = new ChamCongNVDao();
		List<NhanVien> list = nvDao.getNhanVien();
		for (NhanVien nhanVien : list) {
			modelCC.addRow(new Object[] { nhanVien.getMaNV(), nhanVien.getHoTen() });
		}

		List<ChamCongNV> listCC = cCNVDao.getAllChamCongNV();
		for (ChamCongNV cCNV : listCC) {

			modelCCNV
					.addRow(new Object[] { cCNV.getMaCong(), cCNV.getNgayCham(), cCNV.isTrangThai() ? "Có mặt" : "Vắng",
							cCNV.isNghiPhep() ? "Có" : "Không", cCNV.getNv().getMaNV(), cCNV.getNv().getHoTen(),
							cCNV.getMaCa().getMaCa(), cCNV.getMaCa().getTenCa(), cCNV.getMaCa().getGioTheoCa() });
		}
	}

	private void refreshMaCC() {
		String maCCNV = "CCNV";
		List<ChamCongNV> list;

		try {
			list = cCNVDao.getAllChamCongNV();
			int n = list.size();
			for (int i = 1; i < n; i++) {
				if (n > 0 || n <= 9) {
					int x = i + 2;
					txtMaCong.setText(maCCNV + "0" + x);

				} else
					txtMaCong.setText(maCCNV + i);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void clearTable() {
		modelCCNV.getDataVector().removeAllElements();
		modelCC.getDataVector().removeAllElements();
		revalidate();
	}

	private void layDSChamCongTheoNgay() throws SQLException {
		clearTable();
		String[] date = txtNgayCham.getText().split("-");
		int year = Integer.parseInt(date[0]);
		int month = Integer.parseInt(date[1]);
		int day = Integer.parseInt(date[2]);

		List<ChamCongNV> listCCNV = cCNVDao.getCCByNgayCham(day, month, year);
		for (ChamCongNV cCNV : listCCNV) {
			modelCCNV
					.addRow(new Object[] { cCNV.getMaCong(), cCNV.getNgayCham(), cCNV.isTrangThai() ? "Có mặt" : "Vắng",
							cCNV.isNghiPhep() ? "Có" : "Không", cCNV.getNv().getMaNV(), cCNV.getNv().getHoTen(),
							cCNV.getMaCa().getMaCa(), cCNV.getMaCa().getTenCa(), cCNV.getMaCa().getGioTheoCa() });
		}

	}

	private boolean valiData() {
		String maCa = txtMaCa.getText().trim();
		String gioLam = txtGioLam.getText().trim();
		int row = tableCC.getSelectedRow();
		LocalDate ngayCham = LocalDate.parse(txtNgayCham.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		if (modelCC.getValueAt(row, 2) != null && modelCC.getValueAt(row, 3) != null) {
			JOptionPane.showMessageDialog(null, "Không thể chấm công vừa vắng vừa có mặt!");
			modelCC.setValueAt(null, row, 2);
			modelCC.setValueAt(null, row, 3);
			return false;
		}
		if (maCa.equalsIgnoreCase("")) {
			thongbaoLoi(txtMaCa, "Mã ca không được để trống!");
			return false;
		}
		if (gioLam.equalsIgnoreCase("")) {
			thongbaoLoi(txtGioLam, "Giờ làm không được để trống!");
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
