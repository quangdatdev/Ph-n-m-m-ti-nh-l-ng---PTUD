package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import dao.NVDao;
import dao.TrinhDoDao;
import entity.NhanVien;
import entity.TrinhDo;
import util.ConnectDB;

import javax.swing.border.LineBorder;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

//4/12
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//4/12

public class FrmNhanVien extends JPanel implements MouseListener, ActionListener {
	private JTextField txtMaNV;
	private JTextField txtHoTen;
	private JTextField txtSoDT;
	private JTextField txtDiaChi;
	private JTextField txtNamSinh;
	private JTextField txtLuongCB;
	private JTextField txtTroCap;
	private DefaultTableModel model;
	private List<NhanVien> listNV;
	private JTable tableNV;
	private NVDao nvDao;
	private JComboBox<String> cboGioiTinh;
	private JComboBox<String> cboTrinhDo;
	private JComboBox<String> cboHeSL;
	private JButton btnXoaRong;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnThem;
	private TrinhDoDao tdDao;
	private JButton btnExcel;
	private JTextField txtSoCMND;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */

	public FrmNhanVien() throws Exception {
		setLayout(null);
		JLabel lblNhanVien = new JLabel("Nhân Viên Hành Chính");
		lblNhanVien.setForeground(new Color(255, 128, 128));
		lblNhanVien.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNhanVien.setBounds(581, 10, 319, 56);
		add(lblNhanVien);

		JLabel lblMaNV = new JLabel("Mã NV:");
		lblMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaNV.setBounds(328, 76, 74, 29);
		add(lblMaNV);

		txtMaNV = new JTextField();
		txtMaNV.setEditable(false);
		txtMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(426, 77, 105, 26);
		add(txtMaNV);

		JLabel lblHoTen = new JLabel("Họ Tên:");
		lblHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblHoTen.setBounds(561, 76, 74, 29);
		add(lblHoTen);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(645, 77, 231, 26);
		add(txtHoTen);

		JLabel lblSoDT = new JLabel("Số ĐT:");
		lblSoDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSoDT.setBounds(918, 76, 74, 29);
		add(lblSoDT);

		txtSoDT = new JTextField();
		txtSoDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSoDT.setColumns(10);
		txtSoDT.setBounds(1030, 77, 140, 26);
		add(txtSoDT);

		JLabel lblDiaChi = new JLabel("Địa Chỉ:");
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDiaChi.setBounds(328, 132, 74, 29);
		add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(426, 133, 450, 26);
		add(txtDiaChi);

		JLabel lblGioiTinh = new JLabel("Giới Tính:");
		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblGioiTinh.setBounds(918, 188, 90, 29);

		add(lblGioiTinh);

		cboGioiTinh = new JComboBox();
		cboGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboGioiTinh.setBackground(Color.WHITE);
		cboGioiTinh.setBounds(1030, 189, 138, 27);
		cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
		add(cboGioiTinh);

		JLabel lblNamSinh = new JLabel("Năm Sinh:");
		lblNamSinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNamSinh.setBounds(326, 188, 90, 29);
		add(lblNamSinh);

		txtNamSinh = new JTextField();
		txtNamSinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtNamSinh.setColumns(10);
		txtNamSinh.setBounds(426, 189, 105, 26);
		add(txtNamSinh);

		JLabel lblTrinhDo = new JLabel("Trình Độ:");
		lblTrinhDo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTrinhDo.setBounds(561, 188, 90, 29);
		add(lblTrinhDo);

		cboTrinhDo = new JComboBox();
		cboTrinhDo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboTrinhDo.setBackground(Color.WHITE);
		cboTrinhDo.setBounds(661, 189, 140, 27);
//		cboTrinhDo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đại Học", "Cao Đẳng", "Trung Cấp" }));

		add(cboTrinhDo);

		JLabel lblLuongCB = new JLabel("Lương CB:");
		lblLuongCB.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblLuongCB.setBounds(918, 240, 90, 29);
		add(lblLuongCB);

		txtLuongCB = new JTextField();
		txtLuongCB.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtLuongCB.setColumns(10);
		txtLuongCB.setBounds(1030, 241, 140, 26);
		add(txtLuongCB);

		JLabel lblTroCap = new JLabel("Trợ Cấp: ");
		lblTroCap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTroCap.setBounds(328, 240, 90, 29);
		add(lblTroCap);

		txtTroCap = new JTextField();
		txtTroCap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTroCap.setColumns(10);
		txtTroCap.setBounds(426, 241, 105, 26);
		add(txtTroCap);

		JLabel lblHeSL = new JLabel("Hệ SL: ");
		lblHeSL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblHeSL.setBounds(561, 240, 66, 29);
		add(lblHeSL);

		cboHeSL = new JComboBox();
		cboHeSL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboHeSL.setBackground(Color.WHITE);
		cboHeSL.setBounds(661, 241, 138, 27);
//		
		cboHeSL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1.1", "1.2", "1.4", "1.86" }));
//		cboHeSL.addItem(1.1);
//		cboHeSL.addItem(1.2);
//		cboHeSL.addItem(1.4);
//		cboHeSL.addItem(1.1);
		add(cboHeSL);

		btnThem = new JButton("Thêm");

		btnThem.setIcon(new ImageIcon("hinh\\iconAdd.png"));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThem.setBounds(328, 305, 120, 29);
		add(btnThem);

		btnSua = new JButton("Sửa");

		btnSua.setIcon(new ImageIcon("hinh\\iconUpdate.png"));
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSua.setBounds(485, 305, 105, 29);
		add(btnSua);

		btnXoa = new JButton("Xóa");

		btnXoa.setIcon(new ImageIcon("hinh\\iconXoa.png"));
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXoa.setBounds(633, 305, 105, 29);
		add(btnXoa);

		btnXoaRong = new JButton("Xóa Rỗng");

		btnXoaRong.setIcon(new ImageIcon("hinh\\iconRefresh.png"));
		btnXoaRong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoaRong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXoaRong.setBounds(781, 305, 160, 29);
		add(btnXoaRong);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(3, 378, 1550, 399);

		tableNV = new JTable();
		tableNV.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers = "Mã NV;Họ tên;Số ĐT;Số CMND;Địa Chỉ;Giới Tính;Năm Sinh;Trình Độ;LươngCB;Trợ Cấp;Hệ SL"
				.split(";");
		model = new DefaultTableModel(headers, 0);
		tableNV.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(tableNV = new JTable(model));
		tableNV.setRowHeight(25);
		tableNV.setAutoCreateRowSorter(true);
		tableNV.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		add(scrollPane);

		// Đăng ký sự kiện

		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnSua.addActionListener(this);

		tdDao = new TrinhDoDao();
		ConnectDB.getDataBase().getConnection();
		updateCbo();
		updateTableData();
		tableNV.addMouseListener(this);

		btnExcel = new JButton("Xuất Excel");
		btnExcel.setIcon(new ImageIcon("hinh\\iconIn.png"));
		btnExcel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnExcel.setBounds(985, 305, 173, 29);
		add(btnExcel);

		btnExcel.addActionListener(this);

		JLabel lblSoCMND = new JLabel("Số CMND:");
		lblSoCMND.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSoCMND.setBounds(918, 132, 105, 29);
		add(lblSoCMND);

		txtSoCMND = new JTextField();
		txtSoCMND.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSoCMND.setColumns(10);
		txtSoCMND.setBounds(1030, 133, 140, 26);
		add(txtSoCMND);
	}

	public void clearTable() {
		model.getDataVector().removeAllElements();
		revalidate();
	}

	private void updateTableData() throws Exception {
		nvDao = new NVDao();

		List<NhanVien> list = nvDao.getAllNhanVien();
		for (NhanVien nv : list) {
			model.addRow(new Object[] { nv.getMaNV(), nv.getHoTen(), nv.getSoDT(), nv.getSoCMND(), nv.getDiaChi(),
					nv.getGioiTinh(), nv.getNamSinh(), nv.getTrinhDo().getTenTrinhDo(), nv.getLuongCoBan(),
					nv.getTroCap(), nv.getHeSoLuong() });
		}
		refreshMaNV();

	}

	private void updateCbo() throws SQLException {
		tdDao = new TrinhDoDao();
		List<TrinhDo> list = tdDao.getAllTrinhDo();
		for (TrinhDo trinhDo : list) {
			cboTrinhDo.addItem(trinhDo.getTenTrinhDo());
		}
	}

//	private NhanVien nhapLieu() {
//		String maNv = txtMaNV.getText().toString();
//		String hoten = txtHoTen.getText().toString();
//		String soDt = txtSoDT.getText().toString();
//		String diaChi = txtDiaChi.getText().toString();
//		String gioiTinh = cboGioiTinh.getSelectedItem().toString();
//		int namSinh = Integer.parseInt(txtNamSinh.getText());
//		String trinhDo = cboTrinhDo.getSelectedItem().toString();
//		double luongCB = Double.parseDouble(txtLuongCB.getText());
//		double troCap = Double.parseDouble(txtTroCap.getText());
//		double heSoLuong = Double.parseDouble(cboHeSL.getSelectedItem().toString());
//
//		return new NhanVien(maNv, hoten, soDt, diaChi, gioiTinh, namSinh, trinhDo, luongCB, troCap, heSoLuong);
//	}

	// Xử lý xự kiện hành động
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		// Btn thêm
		if (o.equals(btnThem)) {
			TrinhDo td = tdDao.getTrinhDoTheoTen(cboTrinhDo.getSelectedItem().toString());

			if (valiData()) {
//				NhanVien nv1 = nhapLieu();

				NhanVien nv1 = new NhanVien(txtMaNV.getText().toString(), txtHoTen.getText().toString(),
						txtSoDT.getText().toString(), txtSoCMND.getText().toString(), txtDiaChi.getText().toString(),
						cboGioiTinh.getSelectedItem().toString(), Integer.parseInt(txtNamSinh.getText().toString()), td,
						Double.parseDouble(txtLuongCB.getText().toString()),
						Double.parseDouble(txtTroCap.getText().toString()),
						Double.parseDouble(cboHeSL.getSelectedItem().toString()));

				try {
					if (nvDao.themNhanVien(nv1)) {
						model.addRow(new Object[] { nv1.getMaNV(), nv1.getHoTen(), nv1.getSoDT(), nv1.getSoCMND(),
								nv1.getDiaChi(), nv1.getGioiTinh(), nv1.getNamSinh(), nv1.getTrinhDo().getTenTrinhDo(),
								nv1.getLuongCoBan(), nv1.getTroCap(), nv1.getHeSoLuong() });
						refreshMaNV();
						clearFill();
					} else {
						JOptionPane.showMessageDialog(this, "Trùng mã nhân viên!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
//				xoaAlldataTable();
//				updateTableData();

		// Btn xoa rong
		if (o.equals(btnXoaRong))

		{
			clearFill();
		}
		if (o.equals(btnXoa)) {
			int row = tableNV.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa!");
			} else {

				String maNVXoa = tableNV.getValueAt(row, 0).toString();
				int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Cảnh báo!",
						JOptionPane.YES_NO_OPTION);
				if (yes == JOptionPane.YES_OPTION) {
					try {
						if (nvDao.xoaNhanVien(maNVXoa)) {
							model.removeRow(row);
							JOptionPane.showMessageDialog(null, "Xóa thành công!");
						}

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}

			}
			clearFill();
			refreshMaNV();
		}

		if (o.equals(btnSua)) {
			int row = tableNV.getSelectedRow();
			TrinhDo td = tdDao.getTrinhDoTheoTen(cboTrinhDo.getSelectedItem().toString());

			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần sửa!");
			} else {

				String maNVChon = tableNV.getValueAt(row, 0).toString();
				int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa?", "Cảnh báo!",
						JOptionPane.YES_NO_OPTION);
				if (yes == JOptionPane.YES_OPTION) {
					if (valiData()) {
						NhanVien nv2 = new NhanVien(txtMaNV.getText().toString(), txtHoTen.getText().toString(),
								txtSoDT.getText().toString(), txtSoCMND.getText().toString(),
								txtDiaChi.getText().toString(), cboGioiTinh.getSelectedItem().toString(),
								Integer.parseInt(txtNamSinh.getText().toString()), td,
								Double.parseDouble(txtLuongCB.getText().toString()),
								Double.parseDouble(txtTroCap.getText().toString()),
								Double.parseDouble(cboHeSL.getSelectedItem().toString()));
						try {
							if (nvDao.suaNhanVien(nv2)) {
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
		// 4/12
		if (o.equals(btnExcel)) {
			try {
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.showSaveDialog(this);
				File saveFile = jFileChooser.getSelectedFile();

				if (saveFile != null) {
					saveFile = new File(saveFile.toString() + ".xlsx");
					Workbook wb = new XSSFWorkbook();
					Sheet sheet = wb.createSheet("NhanVien");

					Row rowCol = sheet.createRow(0);
					for (int i = 0; i < tableNV.getColumnCount(); i++) {
						Cell cell = rowCol.createCell(i);
						cell.setCellValue(tableNV.getColumnName(i));
					}

					for (int j = 0; j < tableNV.getRowCount(); j++) {
						Row row = sheet.createRow(j + 1);
						for (int k = 0; k < tableNV.getColumnCount(); k++) {
							Cell cell = row.createCell(k);
							if (tableNV.getValueAt(j, k) != null) {
								cell.setCellValue(tableNV.getValueAt(j, k).toString());
							}
						}
					}
					FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
					wb.write(out);
					wb.close();
					out.close();
					openFile(saveFile.toString());
				}
			} catch (FileNotFoundException ex) {
				System.out.println(ex);
			} catch (IOException io) {
				System.out.println(io);
			}

		}
		// 4/12
	}

	private void clearFill() {
		txtHoTen.setText("");
		txtSoDT.setText("");
		txtSoCMND.setText("");
		txtNamSinh.setText("");
		txtDiaChi.setText("");
		txtTroCap.setText("");
		txtLuongCB.setText("");
		cboGioiTinh.setSelectedIndex(0);
		cboHeSL.setSelectedIndex(0);
		cboTrinhDo.setSelectedIndex(0);
		refreshMaNV();

	}

	private void refreshMaNV() {
		String manv = "NV";
		List<NhanVien> list;
		try {
			list = nvDao.getAllNhanVien();
			int n = list.size();
			for (int i = 1; i < n; i++) {
				if (n > 0 || n <= 9) {
					int x = i + 2;
					txtMaNV.setText(manv + "0" + x);
				} else
					txtMaNV.setText(manv + i);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private boolean valiData() {
		String hoTen = txtHoTen.getText().toString().trim();
		String soDT = txtSoDT.getText().toString().trim();
		String diaChi = txtDiaChi.getText().toString().trim();
		String nam = txtNamSinh.getText().trim();
		String luongCB = txtLuongCB.getText().toString().trim();
		String troCap = txtTroCap.getText().toString().trim();
		String soCMND = txtSoCMND.getText().toString().trim();
		if (hoTen.equalsIgnoreCase("")) {
			thongbaoLoi(txtHoTen, "Vui lòng nhập họ tên!");
			return false;
		}
		if (soDT.equalsIgnoreCase("")) {
			thongbaoLoi(txtSoDT, "Vui lòng nhập số điện thoại!");
			return false;
		}

		if (diaChi.equalsIgnoreCase("")) {
			thongbaoLoi(txtDiaChi, "Vui lòng nhập địa chỉ!");
			return false;
		}
		if (soCMND.equalsIgnoreCase("")) {
			thongbaoLoi(txtSoCMND, "Vui lòng nhập số chứng minh nhân dân!");
			return false;
		}
		if (nam.equalsIgnoreCase("")) {
			thongbaoLoi(txtNamSinh, "Vui lòng nhập năm sinh!");
			return false;
		}
		if (luongCB.equalsIgnoreCase("")) {
			thongbaoLoi(txtLuongCB, "Vui lòng nhập lương cơ bản!");
			return false;
		}
		if (troCap.equalsIgnoreCase("")) {
			thongbaoLoi(txtTroCap, "Vui lòng nhập trợ cấp!");
			return false;
		}

		if (nam.length() > 0) {
			try {
				int x = Integer.parseInt(txtNamSinh.getText());
				if ((2022 - x) < 18) {
					JOptionPane.showMessageDialog(null, "Năm sinh phải nhỏ hơn năm hiện tại và phải đủ 18 tuổi!");
					txtNamSinh.requestFocus();
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Năm là số!");
				txtNamSinh.requestFocus();
				return false;
			}
		}

		if (luongCB.length() > 0) {
			try {
				Double y = Double.parseDouble(txtLuongCB.getText());
				if (y <= 0) {
					JOptionPane.showMessageDialog(null, "Lương cơ bản lớn hơn 0!");
					txtLuongCB.requestFocus();
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Lương cơ bản là số!");
				txtLuongCB.requestFocus();
				return false;
			}
		}
		if (troCap.length() > 0) {
			try {
				Double y = Double.parseDouble(txtTroCap.getText());
				if (y <= 0) {
					JOptionPane.showMessageDialog(null, "Trợ cấp lớn hơn 0!");
					txtTroCap.requestFocus();
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Trợ cấp là số là số!");
				txtTroCap.requestFocus();
				return false;
			}
		}
		return true;
	}

	private void thongbaoLoi(JTextField txtHoTen2, String s) {
		txtHoTen2.requestFocus(true);
		JOptionPane.showMessageDialog(this, s);

	}

	// Sự kiện table
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableNV.getSelectedRow();
//		String maNV = model.getValueAt(row, 1).toString
		txtMaNV.setText(model.getValueAt(row, 0).toString());
		txtHoTen.setText(model.getValueAt(row, 1).toString());
		txtSoDT.setText(model.getValueAt(row, 2).toString());
		txtSoCMND.setText(model.getValueAt(row, 3).toString());
		txtDiaChi.setText(model.getValueAt(row, 4).toString());
		cboGioiTinh.setSelectedItem(model.getValueAt(row, 5).toString());
		txtNamSinh.setText(model.getValueAt(row, 6).toString());
		cboTrinhDo.setSelectedItem(model.getValueAt(row, 7).toString());
		txtLuongCB.setText(model.getValueAt(row, 8).toString());
		txtTroCap.setText(model.getValueAt(row, 9).toString());
		cboHeSL.setSelectedItem(model.getValueAt(row, 10).toString());

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

	// 4/12
	public void openFile(String file) {
		try {
			File path = new File(file);
			Desktop.getDesktop().open(path);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
}
