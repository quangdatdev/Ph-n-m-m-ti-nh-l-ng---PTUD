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
import entity.CongNhan;
import entity.NhanVien;
import entity.SanPham;
import entity.TayNghe;
import util.ConnectDB;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class FrmTayNghe extends JPanel implements ActionListener, MouseListener {
	private JTextField txtMaTN;
	private JTextField txtTenTN;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnLamMoi;
	// private JSpiner so_luong; ?
	private TayNgheDao tnDao = new TayNgheDao();

	private JTable tableTN;
	private DefaultTableModel modelTN;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public FrmTayNghe() throws Exception {
		setLayout(null);

		JLabel lblSnPhm = new JLabel("Tay Nghề");
		lblSnPhm.setForeground(new Color(255, 128, 128));
		lblSnPhm.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblSnPhm.setBounds(656, 10, 160, 56);
		add(lblSnPhm);

		JLabel lblMaTayNghe = new JLabel("Mã Tay Nghề: ");
		lblMaTayNghe.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaTayNghe.setBounds(404, 75, 120, 29);
		add(lblMaTayNghe);

		txtMaTN = new JTextField();
		txtMaTN.setEditable(false);
		txtMaTN.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaTN.setColumns(10);
		txtMaTN.setBounds(534, 76, 154, 26);

		add(txtMaTN);

		JLabel lblTenTayNghe = new JLabel("Tay Nghề: ");
		lblTenTayNghe.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenTayNghe.setBounds(730, 76, 126, 29);
		add(lblTenTayNghe);

		txtTenTN = new JTextField();
		txtTenTN.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenTN.setColumns(10);
		txtTenTN.setBounds(866, 76, 244, 26);
		add(txtTenTN);
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

		tableTN = new JTable();
		tableTN.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers = "Mã Tay Nghề;Tay Nghề".split(";");
		modelTN = new DefaultTableModel(headers, 0);
		tableTN.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(tableTN = new JTable(modelTN));
		tableTN.setRowHeight(25);
		tableTN.setAutoCreateRowSorter(true);
		tableTN.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		add(scrollPane);

		ConnectDB.getDataBase().getConnection();
		updateTableData();
		btnLamMoi.addActionListener(this);
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		// lay du lieu tu bang len cac o o tren
		tableTN.addMouseListener(this);

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
		TayNgheDao tnDao = new TayNgheDao();
		List<TayNghe> list = tnDao.getAllTayNghe();
		for (TayNghe tn : list) {
			modelTN.addRow(new Object[] { tn.getMaTN(), tn.getTenTN() });
		}
		refreshMaTN();

	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		// Btn thêm
		if (o.equals(btnThem)) {
			if (valiData()) {
				TayNghe tn = new TayNghe(txtMaTN.getText().toString(), txtTenTN.getText().toString());
				try {
					if (tnDao.themTayNghe(tn)) {
						modelTN.addRow(new Object[] { tn.getMaTN(), tn.getTenTN() });
						clearFill();
						refreshMaTN();

					} else {
						JOptionPane.showMessageDialog(this, "Trùng mã tay nghề!");
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
			int row = tableTN.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn tay nghề cần xóa!");
			} else {

				String maTN = tableTN.getValueAt(row, 0).toString();
				int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Cảnh báo!",
						JOptionPane.YES_NO_OPTION);
				if (yes == JOptionPane.YES_OPTION) {
					try {
						if (tnDao.xoaTN(maTN)) {
							modelTN.removeRow(row);
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
			int row = tableTN.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn tay nghề cần sửa!");
			} else {

				String maTN = tableTN.getValueAt(row, 0).toString();
				int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa?", "Cảnh báo!",
						JOptionPane.YES_NO_OPTION);
				if (yes == JOptionPane.YES_OPTION) {
					if (valiData()) {
						TayNghe tn = new TayNghe(txtMaTN.getText().toString(), txtTenTN.getText().toString());
						try {
							if (tnDao.suaTN(tn)) {
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
		txtMaTN.setText("");
		txtTenTN.setText("");
		refreshMaTN();
	}

	private void refreshMaTN() {
		String maTN = "TN";
		List<TayNghe> list;
		try {
			list = tnDao.getAllTayNghe();
			int n = list.size();
			for (int i = 1; i < n; i++) {
				if (n > 0 || n <= 9) {
					int x = i + 2;
					txtMaTN.setText(maTN + "0" + x);
				} else
					txtMaTN.setText(maTN + i);
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
		String tenTN = txtTenTN.getText().toString().trim();

		if (tenTN.equalsIgnoreCase("")) {
			thonbaoLoi(txtTenTN, "Vui lòng nhập tên tay nghề!");
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
		int row = tableTN.getSelectedRow();
//		String maNV = model.getValueAt(row, 1).toString
		txtMaTN.setText(modelTN.getValueAt(row, 0).toString());
		txtTenTN.setText(modelTN.getValueAt(row, 1).toString());

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
		modelTN.getDataVector().removeAllElements();
		revalidate();
	}

}