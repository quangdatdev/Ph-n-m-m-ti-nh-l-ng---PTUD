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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.awt.Color;
import java.awt.Desktop;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import dao.SanPhamDao;
import entity.SanPham;
import util.ConnectDB;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class FrmSanPham extends JPanel implements MouseListener, ActionListener {
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JTextField txtKieuDang;
	private JTextField txtChatLieu;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnLamMoi;
	private JTextField txtSoLuong;
	private SanPhamDao spDao = new SanPhamDao();

	private JTable tableSP;
	private DefaultTableModel modelSP;
	private JButton btnXuatExcel;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public FrmSanPham() throws Exception {
		setLayout(null);

		JLabel lblSnPhm = new JLabel("Sản Phẩm");
		lblSnPhm.setForeground(new Color(255, 128, 128));
		lblSnPhm.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblSnPhm.setBounds(656, 10, 160, 56);
		add(lblSnPhm);

		JLabel lblMaSP = new JLabel("Mã SP:");
		lblMaSP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaSP.setBounds(433, 75, 74, 29);
		add(lblMaSP);

		txtMaSP = new JTextField();
		txtMaSP.setEditable(false);
		txtMaSP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaSP.setColumns(10);
		txtMaSP.setBounds(534, 76, 154, 26);

		add(txtMaSP);

		JLabel lblTenSP = new JLabel("Tên SP:");
		lblTenSP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenSP.setBounds(730, 76, 74, 29);
		add(lblTenSP);

		txtTenSP = new JTextField();
		txtTenSP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenSP.setColumns(10);
		txtTenSP.setBounds(825, 76, 323, 26);
		add(txtTenSP);

		JLabel lblKieuDang = new JLabel("Kiểu Dáng:");
		lblKieuDang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblKieuDang.setBounds(433, 131, 91, 29);
		add(lblKieuDang);

		txtKieuDang = new JTextField();
		txtKieuDang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtKieuDang.setColumns(10);
		txtKieuDang.setBounds(534, 132, 154, 26);
		add(txtKieuDang);

		JLabel lblChatLieu = new JLabel("Chất Liệu:");
		lblChatLieu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblChatLieu.setBounds(730, 131, 85, 29);
		add(lblChatLieu);

		txtChatLieu = new JTextField();
		txtChatLieu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtChatLieu.setColumns(10);
		txtChatLieu.setBounds(825, 132, 135, 26);
		add(txtChatLieu);

		JLabel lblSoLuong = new JLabel("Số Lượng:");
		lblSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSoLuong.setBounds(990, 131, 85, 29);
		add(lblSoLuong);

		//
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(1078, 132, 70, 25);
		add(txtSoLuong);
		//

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("hinh\\iconAdd.png"));
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnThem.setBounds(433, 173, 120, 32);
		add(btnThem);

		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("hinh\\iconXoa.png"));
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnXoa.setBounds(574, 173, 101, 32);
		add(btnXoa);

		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon("hinh\\iconUpdate.png"));
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnSua.setBounds(703, 174, 101, 31);
		add(btnSua);

		btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.setIcon(new ImageIcon("hinh\\iconRefresh.png"));
		btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnLamMoi.setBounds(825, 175, 135, 29);
		add(btnLamMoi);

		//

//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(0, 253, 875, 286);
//		add(scrollPane);
		//
		JScrollPane scrollPane = new JScrollPane();// sua tu day xuong
		scrollPane.setBounds(10, 234, 1550, 641);

		tableSP = new JTable();
		tableSP.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers = "Mã SP;Tên SP;Kiểu Dáng;Chất Liệu;Số Lượng".split(";");
		modelSP = new DefaultTableModel(headers, 0);
		tableSP.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(tableSP = new JTable(modelSP));
		tableSP.setRowHeight(25);
		tableSP.setAutoCreateRowSorter(true);
		tableSP.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		add(scrollPane);

		ConnectDB.getDataBase().getConnection();
		updateTableData();
		btnLamMoi.addActionListener(this);
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		// lay du lieu tu bang len cac o o tren
		tableSP.addMouseListener(this);

		btnXuatExcel = new JButton("Xuất Excel");
		btnXuatExcel.setIcon(new ImageIcon("hinh\\iconIn.png"));
		btnXuatExcel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnXuatExcel.setBounds(976, 175, 172, 29);
		add(btnXuatExcel);

		btnXuatExcel.addActionListener(this);
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

	//
	private void updateTableData() throws Exception {// lay du lieu tu bang
		SanPhamDao spDao = new SanPhamDao();
		List<SanPham> list = spDao.getAllSanPham();
		for (SanPham sp : list) {
			modelSP.addRow(
					new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getKieuDang(), sp.getChatLieu(), sp.getSoLuong() });
		}
		refreshMaSP();

	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		// Btn thêm
		if (o.equals(btnThem)) {
			if (valiData()) {
				SanPham sp = new SanPham(txtMaSP.getText().toString(), txtTenSP.getText().toString(),
						txtKieuDang.getText().toString(), txtChatLieu.getText().toString(),
						Integer.parseInt(txtSoLuong.getText().toString()));

				try {
					if (spDao.themSanPham(sp)) {
						modelSP.addRow(new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getKieuDang(),
								sp.getChatLieu(), sp.getSoLuong() });
						clearFill();
						refreshMaSP();

					} else {
						JOptionPane.showMessageDialog(this, "Trùng mã sản phẩm!");
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
			int row = tableSP.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa!");
			} else {

				String maSP = tableSP.getValueAt(row, 0).toString();
				int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Cảnh báo!",
						JOptionPane.YES_NO_OPTION);
				if (yes == JOptionPane.YES_OPTION) {
					try {
						if (spDao.xoaSanPham(maSP)) {
							modelSP.removeRow(row);
							JOptionPane.showMessageDialog(null, "Xóa thành công!");
						}

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}

			}
			clearFill();
			refreshMaSP();
		}
		if (o.equals(btnSua)) {
			int row = tableSP.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần sửa!");
			} else {

				String maSP = tableSP.getValueAt(row, 0).toString();
				int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa?", "Cảnh báo!",
						JOptionPane.YES_NO_OPTION);
				if (yes == JOptionPane.YES_OPTION) {
					if (valiData()) {
						SanPham sp1 = new SanPham(txtMaSP.getText().toString(), txtTenSP.getText().toString(),
								txtKieuDang.getText().toString(), txtChatLieu.getText().toString(),
								Integer.parseInt(txtSoLuong.getText().toString()));
						try {
							if (spDao.suaSanPham(sp1)) {
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
					for (int i = 0; i < tableSP.getColumnCount(); i++) {
						Cell cell = rowCol.createCell(i);
						cell.setCellValue(tableSP.getColumnName(i));
					}

					for (int j = 0; j < tableSP.getRowCount(); j++) {
						Row row = sheet.createRow(j + 1);
						for (int k = 0; k < tableSP.getColumnCount(); k++) {
							Cell cell = row.createCell(k);
							if (tableSP.getValueAt(j, k) != null) {
								cell.setCellValue(tableSP.getValueAt(j, k).toString());
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

	private void clearFill() {
		txtMaSP.setText("");
		txtTenSP.setText("");
		txtKieuDang.setText("");
		txtChatLieu.setText("");
		txtSoLuong.setText("");
		refreshMaSP();
	}

	private void refreshMaSP() {
		String maSP = "SP";
		List<SanPham> list;
		try {
			list = spDao.getAllSanPham();
			int n = list.size();
			for (int i = 1; i < n; i++) {
				if (n > 0 || n <= 9) {
					int x = i + 2;
					txtMaSP.setText(maSP + "0" + x);
				} else
					txtMaSP.setText(maSP + i);
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
		String tenSP = txtTenSP.getText().toString().trim();
		String chatLieu = txtChatLieu.getText().toString().trim();
		String kieuDang = txtKieuDang.getText().toString().trim();
		String sl = txtSoLuong.getText().toString().trim();
//		int soLuong = Integer.parseInt(txtSoLuong.getText().toString().trim());

		if (tenSP.equalsIgnoreCase("")) {
			thonbaoLoi(txtTenSP, "Vui lòng nhập tên sản phẩm!");
			return false;
		}

		if (kieuDang.equalsIgnoreCase("")) {
			thonbaoLoi(txtKieuDang, "Vui lòng nhập kiểu dáng");
			return false;
		}
		if (chatLieu.equalsIgnoreCase("")) {
			thonbaoLoi(txtChatLieu, "Vui lòng nhập chất liệu!");
			return false;
		}
		if (sl.equalsIgnoreCase("")) {
			thonbaoLoi(txtSoLuong, "Vui lòng nhập số lượng!");
		}
		if (sl.length() > 0) {
			try {
				int x = Integer.parseInt(txtSoLuong.getText());
				if (x <= 0) {
					JOptionPane.showMessageDialog(null, "Số lượng sản phẩm lớn hơn 0!");
					txtSoLuong.requestFocus();
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Số lượng là số!");
				txtSoLuong.requestFocus();
				return false;
			}
		}
		return true;
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

	private void thonbaoLoi(JTextField txtHoTen2, String s) {
		txtHoTen2.requestFocus(true);
		JOptionPane.showMessageDialog(this, s);

	}

	// Sự kiện table
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableSP.getSelectedRow();
//		String maNV = model.getValueAt(row, 1).toString
		txtMaSP.setText(modelSP.getValueAt(row, 0).toString());
		txtTenSP.setText(modelSP.getValueAt(row, 1).toString());
		txtKieuDang.setText(modelSP.getValueAt(row, 2).toString());
		txtChatLieu.setText(modelSP.getValueAt(row, 3).toString());
		txtSoLuong.setText(modelSP.getValueAt(row, 4).toString());

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
		modelSP.getDataVector().removeAllElements();
		revalidate();
	}
}