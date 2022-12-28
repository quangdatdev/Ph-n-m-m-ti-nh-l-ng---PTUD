package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import dao.NVDao;
import dao.SanPhamDao;
import entity.NhanVien;
import entity.SanPham;
import util.ConnectDB;

import javax.swing.border.LineBorder;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;

public class FrmTimKiemNV extends JPanel implements ActionListener, MouseListener {
	private JTextField txtHoTen;
	private JTextField txtSoDt;
	private JTextField txtDiaChi;
	private JTable tableNV;
	private DefaultTableModel modelNV;
	private NVDao nvDao = new NVDao();
	private List<NhanVien> list;
	private List<NhanVien> listTim;
	private JComboBox<String> cboMaNV;
	private JButton btnXoaRong;
	private JButton btnTim;
	private JTable tableNV_1;
	private JLabel lblGioiTInh;
	private JComboBox cboGioiTinh;
	private JTextField txtSoCMND;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */

	public FrmTimKiemNV() throws Exception {
		setLayout(null);
		JLabel lblNewLabel = new JLabel("Tìm Kiếm Nhân Viên");
		lblNewLabel.setForeground(new Color(255, 128, 128));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(598, 10, 280, 56);
		add(lblNewLabel);

		JLabel lblMaNV = new JLabel("Mã NV:");
		lblMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaNV.setBounds(342, 86, 74, 29);
		add(lblMaNV);

		JLabel lblHoTen = new JLabel("Họ Tên:");
		lblHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblHoTen.setBounds(574, 86, 74, 29);
		add(lblHoTen);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(658, 87, 231, 26);
		add(txtHoTen);

		JLabel lblSoDT = new JLabel("Số ĐT:");
		lblSoDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSoDT.setBounds(928, 86, 74, 29);
		add(lblSoDT);

		txtSoDt = new JTextField();
		txtSoDt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSoDt.setColumns(10);
		txtSoDt.setBounds(1012, 87, 140, 26);
		add(txtSoDt);

		JLabel lblDiaChi = new JLabel("Địa Chỉ:");
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDiaChi.setBounds(342, 142, 74, 29);
		add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(444, 143, 445, 26);
		add(txtDiaChi);

		btnTim = new JButton("Tìm Kiếm");
		btnTim.setIcon(new ImageIcon("hinh\\iconTimKiem.png"));

		btnTim.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnTim.setBounds(586, 239, 165, 29);
		add(btnTim);

		btnXoaRong = new JButton("Xóa Rỗng");
		btnXoaRong.setIcon(new ImageIcon("hinh\\iconRefresh.png"));

		btnXoaRong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXoaRong.setBounds(782, 239, 192, 29);
		add(btnXoaRong);

		JScrollPane scrollPane = new JScrollPane();// sua tu day xuong
		scrollPane.setBounds(10, 293, 1550, 582);

		tableNV = new JTable();
		tableNV.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers = "Mã NV;Họ tên;Số ĐT;Số CMND;Địa Chỉ;Giới Tính;Năm Sinh;Trình Độ;LươngCB;Trợ Cấp;Hệ SL"
				.split(";");
		modelNV = new DefaultTableModel(headers, 0);
		tableNV.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(tableNV_1 = new JTable(modelNV));
		tableNV.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		tableNV_1.setRowHeight(25);
		tableNV_1.setAutoCreateRowSorter(true);
		tableNV_1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		add(scrollPane);

		cboMaNV = new JComboBox();
		cboMaNV.setBackground(Color.WHITE);
		cboMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cboMaNV.setBounds(444, 87, 104, 27);
		add(cboMaNV);
		cboMaNV.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cboMaNV.getSelectedIndex() == 0) {
					resetForm();
					showInput(true);

				} else {

					showInput(false);
				}

			}

		});

		lblGioiTInh = new JLabel("Giới Tính:");
		lblGioiTInh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblGioiTInh.setBounds(928, 142, 81, 29);
		add(lblGioiTInh);

		cboGioiTinh = new JComboBox();
		cboGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cboGioiTinh.setBounds(1012, 143, 140, 27);
		cboGioiTinh.addItem("Nam");
		cboGioiTinh.addItem("Nữ");
		add(cboGioiTinh);

		ConnectDB.getDataBase().getConnection();
		tableNV_1.addMouseListener(this);
		btnTim.addActionListener(this);
		btnXoaRong.addActionListener(this);

		JLabel lblSoCMND = new JLabel("Số CMND: ");
		lblSoCMND.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSoCMND.setBounds(342, 192, 96, 29);
		add(lblSoCMND);

		txtSoCMND = new JTextField();
		txtSoCMND.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSoCMND.setColumns(10);
		txtSoCMND.setBounds(444, 193, 140, 26);
		add(txtSoCMND);
		DayDuLieu();
	}

	private void DayDuLieu() throws Exception {
		clearTable();
		list = new ArrayList<NhanVien>();
		listTim = new ArrayList<NhanVien>();
		if (nvDao != null) {
			list = nvDao.getAllNhanVien();
			updateTableData();

		}
		cboMaNV.removeAllItems();
		cboMaNV.addItem("Tất Cả");
		for (NhanVien nv : list) {
			cboMaNV.addItem(nv.getMaNV());

		}

	}

	private void updateComBoBox() throws Exception {
		NVDao nvDao = new NVDao();
		List<NhanVien> list = nvDao.getAllNhanVien();
		for (NhanVien sp : list) {
			cboMaNV.addItem(sp.getMaNV());
		}
	}

	public void clearTable() {
		modelNV.getDataVector().removeAllElements();
		revalidate();
	}

	public void addDLTim(List<NhanVien> list) {
		clearTable();
		if (list.size() > 0) {
			for (NhanVien nv : list) {
				modelNV.addRow(new Object[] { nv.getMaNV(), nv.getHoTen(), nv.getSoDT(), nv.getSoCMND(), nv.getDiaChi(),
						nv.getGioiTinh(), nv.getNamSinh(), nv.getTrinhDo().getTenTrinhDo(), nv.getLuongCoBan(),
						nv.getTroCap(), nv.getHeSoLuong() });
			}
		}
	}

	public boolean checkForm() {
		String tenNV = txtHoTen.getText().trim();
		String soDt = txtSoDt.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String gioiTinh = cboGioiTinh.getSelectedItem() == "Nam" ? "Nữ" : cboGioiTinh.getSelectedItem().toString();
		String soCMND = txtSoCMND.getText().trim();
		return true;
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		NVDao nvDao = new NVDao();
		if (o.equals(btnTim)) {
			clearTable();
			listTim.removeAll(listTim);
			if (!checkForm()) {
				return;
			}
			if (cboMaNV.getSelectedIndex() != 0) {

				String maNV = cboMaNV.getSelectedItem().toString();
				list.forEach((nv) -> {
					if (nv.getMaNV().equals(maNV)) {
						listTim.add(nv);
					}
				});
			} else {
				try {

					String tenNV = txtHoTen.getText();
					String soDt = txtSoDt.getText();
					String diaChi = txtDiaChi.getText();
					String gioiTinh = cboGioiTinh.getSelectedItem().toString();
					String soCMND = txtSoCMND.getText();
					listTim = nvDao.timNV("", tenNV, soDt, diaChi, gioiTinh, soCMND);

				} catch (SQLException ex) {
					Logger.getLogger(FrmTimKiemNV.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			addDLTim(listTim);
		}
		if (o.equals(btnXoaRong)) {
			resetForm();
		}
	}

	public void showInput(boolean show) {
		txtHoTen.setEditable(show);
		txtDiaChi.setEditable(show);
		txtSoDt.setEditable(show);
		cboGioiTinh.setEditable(show);
		txtSoCMND.setEditable(show);
	}

	public void resetForm() {
		cboMaNV.setSelectedItem("");
		txtHoTen.setText("");
		txtDiaChi.setText("");
		txtSoDt.setText("");
		cboGioiTinh.setSelectedItem("");
		txtSoCMND.setText("");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int n = tableNV_1.getSelectedRow();
		if (n > -1) {
			cboMaNV.setSelectedItem(modelNV.getValueAt(n, 0).toString());
			txtHoTen.setText(modelNV.getValueAt(n, 1).toString());
			txtDiaChi.setText(modelNV.getValueAt(n, 4).toString());
			txtSoDt.setText(modelNV.getValueAt(n, 2).toString());
			cboGioiTinh.setSelectedItem(modelNV.getValueAt(n, 5).toString());
			txtSoCMND.setText(modelNV.getValueAt(n, 3).toString());
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

	private void updateTableData() throws Exception {// lay du lieu tu bang

		NVDao nvDao = new NVDao();
		List<NhanVien> list = nvDao.getAllNhanVien();
		for (NhanVien nv : list) {
			modelNV.addRow(new Object[] { nv.getMaNV(), nv.getHoTen(), nv.getSoDT(), nv.getSoCMND(), nv.getDiaChi(),
					nv.getGioiTinh(), nv.getNamSinh(), nv.getTrinhDo().getTenTrinhDo(), nv.getLuongCoBan(),
					nv.getTroCap(), nv.getHeSoLuong() });
		}

	}
}
