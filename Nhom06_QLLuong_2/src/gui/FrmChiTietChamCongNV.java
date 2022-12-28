package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.CaLamNVDao;
import dao.ChamCongNVDao;
import dao.NVDao;
import dao.TaiKhoanDao;
import entity.TaiKhoan;

import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.management.ListenerNotFoundException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.SystemColor;
import java.awt.Toolkit;

public class FrmChiTietChamCongNV extends JFrame {

	private JPanel contentPane;
	private ChamCongNVDao ccNVDao = new ChamCongNVDao();
	private CaLamNVDao caDao;
	private NVDao nvDao;
	private String ma;
	private JLabel lblDSchamCongTrongThang;
	private JTable tableLuong;
	private JTable tableLuong_1;
	private Component scrollPane;
	private DefaultTableModel modelLuong;
	private int thang;
	private int nam;
	private List<Object[]> listOB;
	private JLabel lblThongTinMaNV;
	private String trangThai;
	private String nghiPhep;
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public FrmChiTietChamCongNV(ChamCongNVDao ccNVDao, CaLamNVDao caDao, NVDao nvDao, String ma, int thang, int nam) throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("hinh\\mhc.png"));
		setTitle("Công Ty Giày CK-D");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1399, 633);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDSchamCongTrongThang = new JLabel("DANH SÁCH CHẤM CÔNG TRONG THÁNG");
		lblDSchamCongTrongThang.setBounds(391, 26, 538, 35);
		lblDSchamCongTrongThang.setFont(new Font("Times New Roman", Font.BOLD, 26));
		contentPane.add(lblDSchamCongTrongThang);

		JLabel lblMaNV = new JLabel("Mã NV:");
		lblMaNV.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblMaNV.setBounds(68, 94, 86, 48);
		contentPane.add(lblMaNV);

		lblThongTinMaNV = new JLabel("???");
		lblThongTinMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblThongTinMaNV.setBounds(159, 103, 126, 35);
		contentPane.add(lblThongTinMaNV);
//		txtTenDN.requestFocus();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 152, 1385, 444);
		getContentPane().add(scrollPane);

		tableLuong = new JTable();
		tableLuong.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers1 = "Mã Công;Ngày Chấm;Trạng Thái;Nghỉ Phép;Mã Ca;Tên Ca;Giờ Theo Ca;Lương Theo Ca".split(";");
		modelLuong = new DefaultTableModel(headers1, 0);
		tableLuong.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(tableLuong_1 = new JTable(modelLuong));
		tableLuong_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		tableLuong_1.setRowHeight(25);
		tableLuong_1.setAutoCreateRowSorter(true);
		tableLuong_1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane.setViewportView(tableLuong_1);
		
		ccNVDao  =new ChamCongNVDao();
		caDao = new CaLamNVDao();
		nvDao = new NVDao();
		
		this.ccNVDao = ccNVDao;
		this.caDao = caDao;
		this.nvDao = nvDao;
		this.ma = ma;
		this.thang = thang;
		this.nam = nam;
		listOB = ccNVDao.dsCCNV(ma, thang, nam);
		lblThongTinMaNV.setText(ma);
		clearTable();
		updateTable();
	}
	public void clearTable() {
		modelLuong.getDataVector().removeAllElements();
		revalidate();
	}

	public void updateTable() {
		if (listOB != null) {
			for (int i = 0; i < listOB.size(); i++) {
				trangThai = Boolean.parseBoolean(listOB.get(i)[2].toString()) ? "Có mặt" : "Vắng";
				nghiPhep = Boolean.parseBoolean(listOB.get(i)[3].toString()) ? "Có" : "Không";
				modelLuong.addRow(new Object[] { listOB.get(i)[0], listOB.get(i)[1], trangThai, nghiPhep,
						listOB.get(i)[4], listOB.get(i)[5], listOB.get(i)[6], listOB.get(i)[7]});
			}
		}
	}

	
}
