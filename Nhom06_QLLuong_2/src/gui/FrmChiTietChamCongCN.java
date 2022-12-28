package gui;

import java.awt.Color;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.CNDao;
import dao.CaLamCNDao;

import dao.ChamCongCNDao;

import java.awt.Font;

import java.sql.SQLException;

import java.util.List;

import javax.swing.JLabel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Toolkit;

public class FrmChiTietChamCongCN extends JFrame {

	private JPanel contentPane;
	private ChamCongCNDao ccDao;
	private CaLamCNDao caDao;
	private CNDao cnDao;
	private String ma;
	private JLabel lblDSchamCongTrongThang;
	private JTable tableLuong;
	private JTable tableLuong_1;

	private DefaultTableModel modelLuong;
	private int thang;
	private int nam;
	private List<Object[]> listOB;
	private String trangThai;
	private String nghiPhep;

	/**
	 * Launch the application.
	 * 
	 * @throws SQLException
	 */
	public FrmChiTietChamCongCN(ChamCongCNDao ccDao, CaLamCNDao caDao, CNDao cnDao, String ma, int thang, int nam)
			throws SQLException {
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

		JLabel lblMaCN = new JLabel("Mã CN:");
		lblMaCN.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblMaCN.setBounds(68, 94, 86, 48);
		contentPane.add(lblMaCN);

		JLabel lblThongTinMaCN = new JLabel("???");
		lblThongTinMaCN.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblThongTinMaCN.setBounds(159, 103, 126, 35);
		contentPane.add(lblThongTinMaCN);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 152, 1385, 444);
		getContentPane().add(scrollPane);

		tableLuong = new JTable();
		tableLuong.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers1 = "Mã Công;Ngày Chấm;Trạng Thái;Nghỉ Phép;Số SP;Mã Ca;Tên Ca;Giờ Theo Ca;Mã PC;Mã SP;Tên SP;Mã CD;Tên CD;Giá CD"
				.split(";");
		modelLuong = new DefaultTableModel(headers1, 0);
		tableLuong.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(tableLuong_1 = new JTable(modelLuong));
		tableLuong_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		tableLuong_1.setRowHeight(25);
		tableLuong_1.setAutoCreateRowSorter(true);
		tableLuong_1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane.setViewportView(tableLuong_1);

		ccDao = new ChamCongCNDao();
		caDao = new CaLamCNDao();
		cnDao = new CNDao();

		this.ccDao = ccDao;
		this.caDao = caDao;
		this.cnDao = cnDao;
		this.ma = ma;
		this.thang = thang;
		this.nam = nam;

		listOB = ccDao.dsCCCN(ma, thang, nam);
		lblThongTinMaCN.setText(ma);

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
						listOB.get(i)[4], listOB.get(i)[5], listOB.get(i)[6], listOB.get(i)[7], listOB.get(i)[8],
						listOB.get(i)[9], listOB.get(i)[10], listOB.get(i)[11], listOB.get(i)[12], listOB.get(i)[13] });

			}
		}
	}
}
