package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.ChamCongNVDao;
import dao.LuongNVDao;
import dao.NVDao;
import dao.ThongKeNhanVienDao;
import entity.ChamCongNV;
import entity.LuongNV;
import entity.NhanVien;

import javax.net.ssl.SNIHostName;
import javax.swing.ImageIcon;

public class FrmTKNhanVien extends JPanel implements ActionListener {
	private JTable tableTK;
	private JLabel lbTongLuong;
	private JLabel lbTongNV;
	private JLabel lbLTN;
	private JLabel lbLCN;
	private JLabel lblNVXSac;
	private int thang;
	private int nam;
	private NVDao nvDao;
	private LuongNVDao luongNVDao;
	private ChamCongNVDao ccNVDao;
	private ThongKeNhanVienDao tkNVDao;
	private DefaultTableModel modelLuong;
	private List<Object[]> list;
	private double tongLuong;
	private int soCN;

	private List<Float> listCC;
	private List<LuongNV> listLuong;
	private List<NhanVien> listNV;

	private JComboBox cboThang;

	private JComboBox cboNam;
	//

	private JLabel txtMaNV;

	private JLabel lblTongLuong;

	private JLabel lblSoCN;

	private JButton btnTK;

	private JButton btnIn;
	private JButton btnXuatExcel;
	private JButton btnTopNV;
	
	private 	List<Object[]> listX;

	/**
	 * Create the panel.
	 */
	public FrmTKNhanVien() {
		setLayout(null);

		JLabel lblThongKeNhanVien = new JLabel("Thống Kê Nhân Viên");
		lblThongKeNhanVien.setBounds(605, 10, 302, 35);
		lblThongKeNhanVien.setForeground(new Color(255, 128, 128));
		lblThongKeNhanVien.setFont(new Font("Times New Roman", Font.BOLD, 30));
		add(lblThongKeNhanVien);

		JLabel lblThang = new JLabel("Tháng:");// lblNewLabel_1_1
		lblThang.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblThang.setBounds(225, 85, 53, 29);
		add(lblThang);

		cboThang = new JComboBox();
		cboThang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboThang.setBackground(Color.WHITE);
		cboThang.setBounds(292, 86, 74, 27);
		cboThang.addItem(1);
		cboThang.addItem(2);
		cboThang.addItem(3);
		cboThang.addItem(4);
		cboThang.addItem(5);
		cboThang.addItem(6);
		cboThang.addItem(7);
		cboThang.addItem(8);
		cboThang.addItem(9);
		cboThang.addItem(10);
		cboThang.addItem(11);
		cboThang.addItem(12);

		add(cboThang);

		JLabel lblNam = new JLabel("Năm:");
		lblNam.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNam.setBounds(409, 85, 45, 29);
		add(lblNam);

		cboNam = new JComboBox();
		cboNam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboNam.setBackground(Color.WHITE);
		cboNam.setBounds(459, 86, 74, 27);
		for (int i = 1; i < 20; i++) {
			cboNam.addItem(2009 + i);
		}

		add(cboNam);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 273, 1554, 439);
		add(scrollPane);

		tableTK = new JTable();
		tableTK.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers1 = "Mã NV;Tên NV;Số DT;Địa Chỉ;Số ngày làm;Lương".split(";");
		modelLuong = new DefaultTableModel(headers1, 0);
		tableTK.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(tableTK = new JTable(modelLuong));
		tableTK.setRowHeight(25);
		tableTK.setAutoCreateRowSorter(true);
		tableTK.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane.setViewportView(tableTK);

		btnTK = new JButton("Thống Kê");
		btnTK.setIcon(new ImageIcon("hinh\\thongKe.png"));
		btnTK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTK.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnTK.setBounds(585, 82, 143, 35);
		add(btnTK);

		btnIn = new JButton("In");
		btnIn.setIcon(new ImageIcon("hinh\\iconIn.png"));
		btnIn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnIn.setBounds(779, 82, 105, 35);
		add(btnIn);

		btnTK.addActionListener(this);
		btnIn.addActionListener(this);

		JLabel lblTienLuongNN = new JLabel("Tiền lương thấp nhất/ngày:  ");
		lblTienLuongNN.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTienLuongNN.setBounds(225, 167, 230, 21);
		add(lblTienLuongNN);

		lbLTN = new JLabel("???");
		lbLTN.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lbLTN.setBounds(443, 167, 206, 21);
		add(lbLTN);

		JLabel lblTienLuongCaoNhat = new JLabel("Tiền lương cao nhất/ngày: ");
		lblTienLuongCaoNhat.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTienLuongCaoNhat.setBounds(225, 198, 203, 21);
		add(lblTienLuongCaoNhat);

		lbLCN = new JLabel("???");
		lbLCN.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lbLCN.setBounds(443, 198, 206, 21);
		add(lbLCN);

		JLabel lblTongTIenLuong = new JLabel("Tổng tiền lương: ");
		lblTongTIenLuong.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTongTIenLuong.setBounds(673, 198, 128, 21);
		add(lblTongTIenLuong);

		lbTongLuong = new JLabel("???");
		lbTongLuong.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lbTongLuong.setBounds(811, 198, 170, 21);
		add(lbTongLuong);

		JLabel lblTongNhanVien = new JLabel("Tổng nhân viên:");
		lblTongNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTongNhanVien.setBounds(673, 167, 128, 21);
		add(lblTongNhanVien);

		lbTongNV = new JLabel("???");
		lbTongNV.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lbTongNV.setBounds(811, 167, 170, 21);
		add(lbTongNV);

		btnXuatExcel = new JButton("Xuất Excel");
		btnXuatExcel.setIcon(new ImageIcon("hinh\\iconIn.png"));
		btnXuatExcel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnXuatExcel.setBounds(933, 82, 160, 35);
		add(btnXuatExcel);

		btnXuatExcel.addActionListener(this);
		
		JLabel lblNVXS = new JLabel("Nhân viên xuất sắc:");
		lblNVXS.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNVXS.setBounds(991, 167, 153, 21);
		add(lblNVXS);
		
		lblNVXSac = new JLabel("???");
		lblNVXSac.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNVXSac.setBounds(1143, 167, 170, 21);
		add(lblNVXSac);
		
		btnTopNV = new JButton("DS");
		btnTopNV.setBounds(69, 169, 85, 21);
		add(btnTopNV);
		btnTopNV.addActionListener(this);

		nvDao = new NVDao();
		luongNVDao = new LuongNVDao();
		tkNVDao = new ThongKeNhanVienDao();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		
		if (o.equals(btnTopNV)) {
			int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
			int thang = Integer.parseInt(cboThang.getSelectedItem().toString());

			DecimalFormat df = new DecimalFormat("###,###,### VND");
			clearTable();
			try {
				String top = tkNVDao.soNgayLamMax(thang, nam);
				System.out.print(top);
				listX = null;
				listX = tkNVDao.dsTopNV(thang, nam,top);
				if (listX != null) {
					for (int i = 0; i < listX.size(); i++) {
						double x = (double) listX.get(i)[5];
						modelLuong.addRow(new Object[] { listX.get(i)[0], listX.get(i)[1], listX.get(i)[2],
								listX.get(i)[3], listX.get(i)[4], df.format(x) });
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (o.equals(btnTK)) {
			int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
			int thang = Integer.parseInt(cboThang.getSelectedItem().toString());

			DecimalFormat df = new DecimalFormat("###,###,### VND");
			listCC = null;
			listNV = null;
			listLuong = null;

			try {
				tongLuong = tkNVDao.tongLuongNV(thang, nam);
				lbTongLuong.setText(df.format(tongLuong).toString());
				int soNV;
				soNV = tkNVDao.tongNV();
				lbTongNV.setText(Integer.toString(soNV));
				double luongLN, luongNN;
				luongLN = tkNVDao.luongCaoNhat(thang, nam);
				lbLCN.setText(df.format(luongLN));
				luongNN = tkNVDao.luongThapNhat(thang, nam);
				lbLTN.setText(df.format(luongNN));
//				String nv = tkNVDao.nhanVienXuatSac(thang, nam);
//				lblNVXSac.setText(nv);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				listNV = nvDao.getAllNhanVien();
				listLuong = luongNVDao.getAllLuongNV();
			} catch (Exception ex) {
				Logger.getLogger(FrmTKNhanVien.class.getName()).log(Level.SEVERE, null, ex);
			}
			clearTable();
			try {

				listCC = tkNVDao.soNgayLam(thang, nam);
				List<Object[]> listOB = null;
				listOB = tkNVDao.thongKe(thang, nam);
				if (listOB != null) {
					for (int i = 0; i < listOB.size(); i++) {
						double x = (double) listOB.get(i)[5];
						modelLuong.addRow(new Object[] { listOB.get(i)[0], listOB.get(i)[1], listOB.get(i)[2],
								listOB.get(i)[3], listOB.get(i)[4], df.format(x) });
					}
				}

			} catch (Exception e2) {
				// TODO: handle exception
				Logger.getLogger(FrmTKNhanVien.class.getName()).log(Level.SEVERE, null, e2);
			}
		}
		if (o.equals(btnIn)) {
			FrmXuatTKNhanVien frmXuatTK;
			int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
			int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
			try {
				frmXuatTK = new FrmXuatTKNhanVien(luongNVDao, ccNVDao, nvDao, thang, nam);// 29/11
				frmXuatTK.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
					for (int i = 0; i < tableTK.getColumnCount(); i++) {
						Cell cell = rowCol.createCell(i);
						cell.setCellValue(tableTK.getColumnName(i));
					}

					for (int j = 0; j < tableTK.getRowCount(); j++) {
						Row row = sheet.createRow(j + 1);
						for (int k = 0; k < tableTK.getColumnCount(); k++) {
							Cell cell = row.createCell(k);
							if (tableTK.getValueAt(j, k) != null) {
								cell.setCellValue(tableTK.getValueAt(j, k).toString());
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

	public void clearTable() {
		modelLuong.getDataVector().removeAllElements();
		revalidate();
	}
}
