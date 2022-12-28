package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.CNDao;
import dao.NVDao;
import dao.TayNgheDao;
import entity.CongNhan;
import entity.NhanVien;
import entity.TayNghe;
import util.ConnectDB;

import javax.swing.border.LineBorder;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;

public class FrmCongNhan extends JPanel implements MouseListener, ActionListener {
	private JTextField txtMaCN;
	private JTextField txtHoTen;
	private JTextField txtSoDT;
	private JTextField txtDiaChi;
	private JTextField txtNamSinh;
	private JTextField txtTroCap;
	private JTextField txtCMND;
	private JTable tableCN;
	private JComboBox<String> cboGioiTinh;
	private JComboBox<String> cboTayNghe;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnXoaRong;
	private DefaultTableModel model;
	private CNDao cnDao;
	private TayNgheDao tnDao;
	private List<CongNhan> listCN;
	
	private JButton btnXuatExcel;

	/**
	 * Create the panel.
	 */

	public FrmCongNhan() {
		setLayout(null);
		JLabel lblNewLabel = new JLabel("Công Nhân");
		lblNewLabel.setForeground(new Color(255, 128, 128));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(661, 10, 160, 56);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã CN:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(332, 86, 74, 29);
		add(lblNewLabel_1);

		txtMaCN = new JTextField();
		txtMaCN.setEditable(false);
		txtMaCN.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaCN.setColumns(10);
		txtMaCN.setBounds(421, 87, 127, 26);
		add(txtMaCN);

		JLabel lblNewLabel_1_1 = new JLabel("Họ Tên:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(558, 86, 74, 29);
		add(lblNewLabel_1_1);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(652, 87, 220, 26);
		add(txtHoTen);

		JLabel lblNewLabel_1_1_1 = new JLabel("Số ĐT:");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(905, 86, 74, 29);
		add(lblNewLabel_1_1_1);

		txtSoDT = new JTextField();
		txtSoDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSoDT.setColumns(10);
		txtSoDT.setBounds(999, 87, 140, 26);
		add(txtSoDT);

		JLabel lblNewLabel_1_2 = new JLabel("Địa Chỉ:");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(332, 142, 74, 29);
		add(lblNewLabel_1_2);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(422, 143, 450, 26);
		add(txtDiaChi);

		JLabel lblNewLabel_1_2_1 = new JLabel("Giới Tính:");
		lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2_1.setBounds(905, 142, 90, 29);
		add(lblNewLabel_1_2_1);

		cboGioiTinh = new JComboBox();
		cboGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboGioiTinh.setBackground(Color.WHITE);
		cboGioiTinh.setBounds(999, 143, 138, 27);
		cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
		add(cboGioiTinh);

		JLabel lblNewLabel_1_2_2 = new JLabel("Năm Sinh:");
		lblNewLabel_1_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2_2.setBounds(332, 198, 90, 29);
		add(lblNewLabel_1_2_2);

		txtNamSinh = new JTextField();
		txtNamSinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtNamSinh.setColumns(10);
		txtNamSinh.setBounds(421, 199, 127, 26);
		add(txtNamSinh);

		JLabel lblNewLabel_1_2_3 = new JLabel("Tay nghề:");
		lblNewLabel_1_2_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2_3.setBounds(332, 250, 90, 29);
		add(lblNewLabel_1_2_3);

		cboTayNghe = new JComboBox();
		cboTayNghe.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboTayNghe.setBackground(Color.WHITE);
		cboTayNghe.setBounds(421, 251, 127, 27);
//		cboTayNghe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cao", "Trung Bình", "Thấp" }));
		add(cboTayNghe);

		JLabel lblNewLabel_1_2_3_1 = new JLabel("Số CMND:");
		lblNewLabel_1_2_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2_3_1.setBounds(558, 198, 96, 29);
		add(lblNewLabel_1_2_3_1);

		txtTroCap = new JTextField();
		txtTroCap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTroCap.setColumns(10);
		txtTroCap.setBounds(999, 199, 140, 26);
		add(txtTroCap);

		JLabel lblNewLabel_1_2_2_1 = new JLabel("Trợ Cấp: ");
		lblNewLabel_1_2_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2_2_1.setBounds(905, 198, 90, 29);
		add(lblNewLabel_1_2_2_1);

		txtCMND = new JTextField();
		txtCMND.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtCMND.setColumns(10);
		txtCMND.setBounds(652, 199, 220, 26);
		add(txtCMND);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("hinh\\iconAdd.png"));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThem.setBounds(332, 315, 122, 29);
		add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon("hinh\\iconUpdate.png"));
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSua.setBounds(475, 315, 114, 29);
		add(btnSua);

		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("hinh\\iconXoa.png"));
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXoa.setBounds(616, 315, 108, 29);
		add(btnXoa);

		btnXoaRong = new JButton("Xóa Rỗng");
		btnXoaRong.setIcon(new ImageIcon("hinh\\iconRefresh.png"));
		btnXoaRong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoaRong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXoaRong.setBounds(752, 315, 181, 29);
		add(btnXoaRong);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(0, 377, 1530, 394);
		add(scrollPane);

		tableCN = new JTable();
		tableCN.setBorder(new LineBorder(new Color(0, 0, 0)));

		tableCN.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		String[] headers = "Mã CN;Họ tên;Số ĐT;Địa Chỉ;Số CMND;Năm Sinh;Giới Tính;Tay Nghề;Trợ Cấp".split(";");
		model = new DefaultTableModel(headers, 0);
		tableCN.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(tableCN = new JTable(model));
		tableCN.setRowHeight(25);
		tableCN.setAutoCreateRowSorter(true);
		tableCN.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane.setViewportView(tableCN);

		// Đăng ký sự kiện
		tableCN.addMouseListener(this);
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);
		
		btnXuatExcel = new JButton("Xuất Excel");
		btnXuatExcel.setIcon(new ImageIcon("hinh\\iconIn.png"));
		btnXuatExcel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXuatExcel.setBounds(958, 315, 181, 29);
		add(btnXuatExcel);
		
		btnXuatExcel.addActionListener(this);

		ConnectDB.getDataBase().getConnection();
		tnDao = new TayNgheDao();
		try {
			updateComBoBoxTayNghe();
			updateTableData();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu!");
		}

	}

	// xử lý sự kiện hành động
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		// Btn thêm

		if (o.equals(btnThem)) {
			TayNghe tn;

			tnDao = new TayNgheDao();
			tn = tnDao.getTayNgheTheoTen(cboTayNghe.getSelectedItem().toString());
			if (valiData()) {
//				CongNhan cn = nhapLieu();
				CongNhan cn = new CongNhan(txtMaCN.getText().toString(), txtHoTen.getText().toString(),
						txtSoDT.getText().toString(), txtDiaChi.getText().toString(), txtCMND.getText().toString(),
						Integer.parseInt(txtNamSinh.getText().toString()), cboGioiTinh.getSelectedItem().toString(), tn,
						Double.parseDouble(txtTroCap.getText().toString()));

				try {
					if (cnDao.themCongNhan(cn)) {
						model.addRow(new Object[] { cn.getMaCN(), cn.getHoTen(), cn.getSoDT(), cn.getDiaChi(),
								cn.getSoCMND(), cn.getNamSinh(), cn.getGioiTinh(), cn.getTayNghe().getTenTN(),
								cn.getTroCap() });
						refreshMaCN();
//						;
						clearFill();
					} else {
						JOptionPane.showMessageDialog(this, "Trùng mã công nhân!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				xoaAlldataTable();
//				updateTableData();
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		// Btn xoa rong
		if (o.equals(btnXoaRong)) {
			try {
				clearFill();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (o.equals(btnXoa)) {
			int row = tableCN.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn công nhân cần xóa!");
			} else {

				String maNVXoa = tableCN.getValueAt(row, 0).toString();
				int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Cảnh báo!",
						JOptionPane.YES_NO_OPTION);
				if (yes == JOptionPane.YES_OPTION) {
					try {
						if (cnDao.xoaCN(maNVXoa)) {
							model.removeRow(row);
							JOptionPane.showMessageDialog(null, "Xóa thành công!");
						}

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}

			}
			try {
				clearFill();
				refreshMaCN();
//				;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (o.equals(btnSua)) {
			int row = tableCN.getSelectedRow();
			TayNghe tn;
			tnDao = new TayNgheDao();
			tn = tnDao.getTayNgheTheoTen(cboTayNghe.getSelectedItem().toString());
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn công nhân cần sửa!");
			} else {

				String maNVChon = tableCN.getValueAt(row, 0).toString();
				int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa?", "Cảnh báo!",
						JOptionPane.YES_NO_OPTION);
				if (yes == JOptionPane.YES_OPTION) {
					if (valiData()) {
//						CongNhan cn2 = nhapLieu();
						CongNhan cn2 = new CongNhan(txtMaCN.getText().toString(), txtHoTen.getText().toString(),
								txtSoDT.getText().toString(), txtDiaChi.getText().toString(),
								txtCMND.getText().toString(), Integer.parseInt(txtNamSinh.getText().toString()),
								cboGioiTinh.getSelectedItem().toString(), tn,
								Double.parseDouble(txtTroCap.getText().toString()));
						try {
							if (cnDao.suaCN(cn2)) {
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
		// 5/12
		if (o.equals(btnXuatExcel)) {
			try {
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.showSaveDialog(this);
				File saveFile = jFileChooser.getSelectedFile();

				if (saveFile != null) {
					saveFile = new File(saveFile.toString() + ".xlsx");
					Workbook wb = new XSSFWorkbook();
					Sheet sheet = wb.createSheet("NhanVien");

					Row rowCol = sheet.createRow(0);
					for (int i = 0; i < tableCN.getColumnCount(); i++) {
						Cell cell = rowCol.createCell(i);
						cell.setCellValue(tableCN.getColumnName(i));
					}

					for (int j = 0; j < tableCN.getRowCount(); j++) {
						Row row = sheet.createRow(j + 1);
						for (int k = 0; k < tableCN.getColumnCount(); k++) {
							Cell cell = row.createCell(k);
							if (tableCN.getValueAt(j, k) != null) {
								cell.setCellValue(tableCN.getValueAt(j, k).toString());
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
		// 5/12


	}

	// 5/12
			public void openFile(String file) {
				try {
					File path = new File(file);
					Desktop.getDesktop().open(path);
				} catch (IOException ioe) {
					System.out.println(ioe);
				}
			}
			// 5/12

	
	private void clearFill() throws Exception {

		txtHoTen.setText("");
		txtDiaChi.setText("");
		txtSoDT.setText("");
		txtNamSinh.setText("");
		txtTroCap.setText("");
		txtCMND.setText("");
		cboGioiTinh.setSelectedItem("");
		cboTayNghe.setSelectedItem("");
		refreshMaCN();
//		;
	}

	private boolean valiData() {
		// TODO Auto-generated method stub
		String hoTen = txtHoTen.getText().toString().trim();
		String soDT = txtSoDT.getText().toString().trim();
		String diaChi = txtDiaChi.getText().toString().trim();
		String nam = txtNamSinh.getText().trim();
		String soCMND = txtCMND.getText().toString().trim();
		String troCap = txtTroCap.getText().toString().trim();

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
			thongbaoLoi(txtCMND, "Vui lòng nhập số CMND!");
			return false;
		}
		if (nam.equalsIgnoreCase("")) {
			thongbaoLoi(txtNamSinh, "Vui lòng nhập năm sinh!");
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
		if (troCap.equalsIgnoreCase("")) {
			thongbaoLoi(txtTroCap, "Vui lòng nhập trợ cấp!");
			return false;
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
				JOptionPane.showMessageDialog(null, "Trợ cấp là số!");
				txtTroCap.requestFocus();
				return false;
			}
		}

		return true;
	}

	// Xử lý sự kiện menu
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableCN.getSelectedRow();
		txtMaCN.setText(model.getValueAt(row, 0).toString());
		txtHoTen.setText(model.getValueAt(row, 1).toString());
		txtSoDT.setText(model.getValueAt(row, 2).toString());
		txtDiaChi.setText(model.getValueAt(row, 3).toString());
		txtCMND.setText(model.getValueAt(row, 4).toString());
		txtNamSinh.setText(model.getValueAt(row, 5).toString());
		cboGioiTinh.setSelectedItem(model.getValueAt(row, 6).toString());
		cboTayNghe.setSelectedItem(model.getValueAt(row, 7).toString());
		txtTroCap.setText(model.getValueAt(row, 8).toString());

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
		model.getDataVector().removeAllElements();
		revalidate();
	}

	private void updateTableData() throws Exception {
		cnDao = new CNDao();

		List<CongNhan> list = cnDao.getAllCongNhan();
		for (CongNhan cn : list) {
			model.addRow(new Object[] { cn.getMaCN(), cn.getHoTen(), cn.getSoDT(), cn.getDiaChi(), cn.getSoCMND(),
					cn.getNamSinh(), cn.getGioiTinh(), cn.getTayNghe().getTenTN(), cn.getTroCap() });
		}
//		maTang();
		refreshMaCN();

	}

	private void refreshMaCN() {
		String macn = "CN";
		List<CongNhan> list;
		try {
			list = cnDao.getAllCongNhan();
			int n = list.size();
			for (int i = 1; i < n; i++) {
				if (n > 0 || n <= 9) {
					int x = i + 2;
					txtMaCN.setText(macn + "0" + x);
				} else
					txtMaCN.setText(macn + i);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	public void  maTang() throws Exception {
//	       listCN = null;
//	        listCN = cnDao.getAllCongNhan();
//
//	        String maHD = listCN.get(listCN.size() - 1).getMaCN();
//	        String[] part = maHD.split("NV");
//	        int n = Integer.parseInt(part[1]) + 1;
//	        txtMaCN.setText(n < 10 ? ("CN0" + n) : ("CN" + n));
//
//	    }
	private void thongbaoLoi(JTextField txtHoTen2, String s) {
		txtHoTen2.requestFocus(true);
		JOptionPane.showMessageDialog(this, s);

	}

	private CongNhan nhapLieu() {
		String ma = txtMaCN.getText().trim();
		String hoTen = txtHoTen.getText().trim();
		String sdt = txtSoDT.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String soCMND = txtCMND.getText().trim();
		int namSinh = Integer.parseInt(txtNamSinh.getText());
		String gioiTinh = cboGioiTinh.getSelectedItem().toString();
		String maTayNghe = cboTayNghe.getSelectedItem().toString();
		double troCap = Double.parseDouble(txtTroCap.getText());
		return new CongNhan(ma, hoTen, sdt, diaChi, soCMND, namSinh, gioiTinh, new TayNghe(maTayNghe), troCap);
	}

	private void updateComBoBoxTayNghe() throws SQLException {
		TayNgheDao dstn = new TayNgheDao();
		List<TayNghe> list = dstn.getAllTayNghe();
		for (TayNghe tayNghe : list) {
			cboTayNghe.addItem(tayNghe.getTenTN());
		}
	}

}
