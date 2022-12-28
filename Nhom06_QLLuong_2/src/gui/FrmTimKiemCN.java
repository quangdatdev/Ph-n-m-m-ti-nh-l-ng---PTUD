package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CNDao;
import dao.NVDao;

import entity.CongNhan;
import entity.NhanVien;
import util.ConnectDB;

import javax.swing.border.LineBorder;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;

public class FrmTimKiemCN extends JPanel implements MouseListener, ActionListener {
	private JTextField txtHoTen;
	private JTextField txtSoDT;
	private JTextField txtDiaChi;
	private JTextField txtCMND;
	private JButton btnTim;
	private JButton btnXoaRong;
	private JTable tableCN;
	private DefaultTableModel modelCN;
	private JComboBox<String> cboMaCN;
	private JComboBox<String> cboGioiTinh;
	private List<CongNhan> list;
	private List<CongNhan> listTim;
	private CNDao cnDao = new CNDao();

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */

	public FrmTimKiemCN() throws Exception {
		setLayout(null);
		JLabel lblTimKiemCN = new JLabel("Tìm Kiếm Công Nhân");
		lblTimKiemCN.setForeground(new Color(255, 128, 128));
		lblTimKiemCN.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblTimKiemCN.setBounds(607, 10, 287, 56);
		add(lblTimKiemCN);

		JLabel lblMaCN = new JLabel("Mã CN:");
		lblMaCN.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaCN.setBounds(265, 86, 74, 29);
		add(lblMaCN);

		JLabel lblHoTen = new JLabel("Họ Tên:");
		lblHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblHoTen.setBounds(472, 86, 74, 29);
		add(lblHoTen);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(549, 87, 220, 26);
		add(txtHoTen);

		JLabel lblSoDT = new JLabel("Số ĐT:");
		lblSoDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSoDT.setBounds(798, 86, 74, 29);
		add(lblSoDT);

		txtSoDT = new JTextField();
		txtSoDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSoDT.setColumns(10);
		txtSoDT.setBounds(904, 87, 157, 26);
		add(txtSoDT);

		JLabel lblDiaChi = new JLabel("Địa Chỉ:");
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDiaChi.setBounds(265, 142, 74, 29);
		add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(360, 143, 409, 26);
		add(txtDiaChi);

		JLabel lblNamSinh = new JLabel("Giới Tính:");
		lblNamSinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNamSinh.setBounds(265, 196, 90, 29);
		add(lblNamSinh);

		JLabel lblSoCMND = new JLabel("Số CMND:");
		lblSoCMND.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSoCMND.setBounds(798, 142, 96, 29);
		add(lblSoCMND);

		txtCMND = new JTextField();
		txtCMND.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtCMND.setColumns(10);
		txtCMND.setBounds(904, 143, 157, 26);

		add(txtCMND);

		btnTim = new JButton("Tìm Kiếm");
		btnTim.setIcon(new ImageIcon("hinh\\iconTimKiem.png"));
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTim.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnTim.setBounds(505, 230, 180, 29);
		add(btnTim);

		btnXoaRong = new JButton("Xóa Rỗng");
		btnXoaRong.setIcon(new ImageIcon("hinh\\iconRefresh.png"));
		btnXoaRong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoaRong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXoaRong.setBounds(733, 230, 161, 29);
		add(btnXoaRong);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(0, 281, 1520, 434);
		add(scrollPane);

		tableCN = new JTable();
		tableCN.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableCN.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers = "Mã CN;Họ tên;Số ĐT;Địa Chỉ;Số CMND;Năm Sinh;Giới Tính;Tay Nghề;Trợ Cấp".split(";");
		modelCN = new DefaultTableModel(headers, 0);
		tableCN.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(tableCN = new JTable(modelCN));
		tableCN.setRowHeight(25);
		tableCN.setAutoCreateRowSorter(true);
		tableCN.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane.setViewportView(tableCN);

		cboMaCN = new JComboBox();
		cboMaCN.setBackground(Color.WHITE);
		cboMaCN.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cboMaCN.setBounds(360, 86, 102, 27);
		add(cboMaCN);

		cboGioiTinh = new JComboBox();
		cboGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cboGioiTinh.setBounds(360, 197, 102, 27);

		cboGioiTinh.addItem("Nam");
		cboGioiTinh.addItem("Nữ");
		add(cboGioiTinh);

		tableCN.addMouseListener(this);
		btnTim.addActionListener(this);
		btnXoaRong.addActionListener(this);

		DayDuLieu();
	}

	private void DayDuLieu() throws Exception {
		clearTable();
		list = new ArrayList<CongNhan>();
		listTim = new ArrayList<CongNhan>();
		if (cnDao != null) {
			list = cnDao.getAllCongNhan();
			updateTableData();
		}
		cboMaCN.removeAllItems();
		cboMaCN.addItem("Tất Cả");
		for (CongNhan cn : list) {
			cboMaCN.addItem(cn.getMaCN());
		}

	}

//	private void updateComBoBox() throws Exception {
//		NVDao nvDao = new NVDao();
//		List<NhanVien> list = nvDao.getAllNhanVien();
//		for (NhanVien sp : list) {
//			cboMaNV.addItem(sp.getMaNV());
//		}
//	}
	public void clearTable() {
		modelCN.getDataVector().removeAllElements();
		revalidate();
	}

	public void addDLTim(List<CongNhan> list) {
		clearTable();
		if (list.size() > 0) {
			for (CongNhan cn : list) {
				modelCN.addRow(new Object[] { cn.getMaCN(), cn.getHoTen(), cn.getSoDT(), cn.getDiaChi(), cn.getSoCMND(),
						cn.getNamSinh(), cn.getGioiTinh(), cn.getTayNghe().getTenTN(), cn.getTroCap() });
			}
		}
	}

	public boolean checkForm() {
		String tenNV = txtHoTen.getText().trim();
		String soDt = txtSoDT.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String soCMND = txtCMND.getText().trim();
		String gioiTinh = cboGioiTinh.getSelectedItem() == "Nam" ? "Nữ" : cboGioiTinh.getSelectedItem().toString();
		return true;
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		CNDao cnDao = new CNDao();
		if (o.equals(btnTim)) {
			listTim.removeAll(listTim);
			clearTable();
			if (!checkForm()) {
				return;
			}
			if (cboMaCN.getSelectedIndex() != 0) {

				String maCN = cboMaCN.getSelectedItem().toString();
				list.forEach((cn) -> {
					if (cn.getMaCN().equals(maCN)) {
						listTim.add(cn);
					}
				});
			} else {
				try {

					String tenNV = txtHoTen.getText();
					String soDt = txtSoDT.getText();
					String diaChi = txtDiaChi.getText();
					String soCMND = txtCMND.getText();
					String gioiTinh = cboGioiTinh.getSelectedItem().toString();

					listTim = cnDao.timCN("", tenNV, soDt, diaChi, soCMND, gioiTinh);

				} catch (SQLException ex) {
					Logger.getLogger(FrmTimKiemCN.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			addDLTim(listTim);
		}
		if (o.equals(btnXoaRong)) {
			resetForm();
//			try {
//				DayDuLieu();
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			
		}
	}

	public void showInput(boolean show) {
		txtHoTen.setEditable(show);
		txtDiaChi.setEditable(show);
		txtSoDT.setEditable(show);
		txtCMND.setEditable(show);
		cboGioiTinh.setEditable(show);
	}

	public void resetForm() {
		cboMaCN.setSelectedItem("");
		txtHoTen.setText("");
		txtDiaChi.setText("");
		txtSoDT.setText("");
		txtCMND.setText("");
		cboGioiTinh.setSelectedItem("");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int n = tableCN.getSelectedRow();
		if (n > -1) {
			cboMaCN.setSelectedItem(modelCN.getValueAt(n, 0).toString());
			txtHoTen.setText(modelCN.getValueAt(n, 1).toString());
			txtDiaChi.setText(modelCN.getValueAt(n, 3).toString());
			txtSoDT.setText(modelCN.getValueAt(n, 2).toString());
			txtCMND.setText(modelCN.getValueAt(n, 4).toString());
			cboGioiTinh.setSelectedItem(modelCN.getValueAt(n, 6).toString());

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

	private void updateTableData() throws Exception {

		List<CongNhan> list = cnDao.getAllCongNhan();
		for (CongNhan cn : list) {
			modelCN.addRow(new Object[] { cn.getMaCN(), cn.getHoTen(), cn.getSoDT(), cn.getDiaChi(), cn.getSoCMND(),
					cn.getNamSinh(), cn.getGioiTinh(), cn.getTayNghe().getTenTN(), cn.getTroCap() });
		}
	}
}
