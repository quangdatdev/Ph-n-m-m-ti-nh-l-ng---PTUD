package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;

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

import dao.CNDao;
import dao.ChamCongCNDao;

import dao.LuongCNDao;
import dao.PhanCongCNDao;
import dao.ThongKeCongNhanDao;
import entity.ChamCongCN;
import entity.CongNhan;
import entity.LuongCN;

//import dao.ThongKeCNDao;

//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.design.JRDesignQuery;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import net.sf.jasperreports.view.JasperViewer;

import javax.swing.ImageIcon;

public class FrmTKCongNhan extends JPanel implements ActionListener {
	private JTable tableTK;

	//
//	private final int NAM_THANHLAP = 1900;
	private int thang;
	private int nam;
	private CNDao cnDao;
	private LuongCNDao luongCNDao;
	private ChamCongCNDao ccCNDao;
	private PhanCongCNDao pcDao;
	private ThongKeCongNhanDao tkCNDao;
	private DefaultTableModel modelLuong;
	private List<Object[]> list;
	private double tongLuong;
	private int soCN;

	private List<ChamCongCN> listCC;
	private List<LuongCN> listLuong;
	private List<CongNhan> listCN;

	private JComboBox cboThang;

	private JComboBox cboNam;
	//

	private JLabel txtMaNV;

	private JLabel lblTongLuong;

	private JLabel lblSoCN;

	private JButton btnTK;

	private JButton btnIn;
	private JLabel lblTienLuongNN;
	private JLabel lbLTN;
	private JLabel lblTienLuongCaoNhat;
	private JLabel lbLCN;
	private JLabel lbLCN_1;
	private JLabel lbLCN_2;
	private JLabel lblTongCN;
	private JLabel lblTongTIenLuong;

	private Object listOB;
	private JButton btnXuatExcel;
	private JLabel lblCNXS;
	private JLabel lbLCNXSac;
	
	private List<Object[]> listX;

	private JButton btnTopCN;

	/**
	 * Create the panel.
	 * 
	 * @throws SQLException
	 */
	// 29/11
	public FrmTKCongNhan() {
		setLayout(null);

		//
//		tkDao = new ThongKeCNDao();
		cnDao = new CNDao();
		luongCNDao = new LuongCNDao();
		ccCNDao = new ChamCongCNDao();
		pcDao = new PhanCongCNDao();
//        initComponents();

		//

		JLabel lblThongKeCN = new JLabel("Thống Kê Công Nhân");
		lblThongKeCN.setBounds(605, 10, 302, 35);
		lblThongKeCN.setForeground(new Color(255, 128, 128));
		lblThongKeCN.setFont(new Font("Times New Roman", Font.BOLD, 30));
		add(lblThongKeCN);

		JLabel lblThang = new JLabel("Tháng:");// lblNewLabel_1_1
		lblThang.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblThang.setBounds(188, 84, 53, 29);
		add(lblThang);

		cboThang = new JComboBox();
		cboThang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboThang.setBackground(Color.WHITE);
		cboThang.setBounds(259, 85, 74, 27);
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
		lblNam.setBounds(389, 84, 45, 29);
		add(lblNam);

		cboNam = new JComboBox();
		cboNam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboNam.setBackground(Color.WHITE);
		cboNam.setBounds(444, 85, 74, 27);
		for (int i = 1; i < 20; i++) {
			cboNam.addItem(2009 + i);
		}
		cboNam.setSelectedItem(2022);
		add(cboNam);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 249, 1520, 475);
		add(scrollPane);

		//
//		defaultTableModel = (DefaultTableModel) table_1.getModel();
//        table_1.getTableHeader().setPreferredSize(new Dimension(table_1.getWidth(), 35));
//
//       table_1.getTableHeader().setFont(new Font("segoe ui", Font.PLAIN, 16));
//       
//       Calendar calendar = Calendar.getInstance();
//       for (int i = NAM_THANHLAP; i <= calendar.get(Calendar.YEAR); i++) {
//           cboNam.addItem(String.valueOf(i));
//       }
//       cboNam.setSelectedIndex(cboNam.getItemCount() - 1);

		//

		tableTK = new JTable();
		tableTK.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers1 = "Mã CN;Tên CN;Công Việc;Tổng Sản Phẩm;Tổng Tiền Lương".split(";");
		modelLuong = new DefaultTableModel(headers1, 0);
		tableTK.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(tableTK = new JTable(modelLuong));
		tableTK.setRowHeight(25);
		tableTK.setAutoCreateRowSorter(true);
		tableTK.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane.setViewportView(tableTK);

//		tableTK = new JTable();
//		tableTK.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null, null},
//			},
//			new String[] {
//				"M\u00E3 CN", "T\u00EAn CN", "C\u00F4ng Vi\u1EC7c", "T\u1ED5ng S\u1EA3n Ph\u1EA9m", "T\u1ED5ng Ti\u1EC1n L\u01B0\u01A1ng "
//			}
//		) {
//			Class[] columnTypes = new Class[] {
//				String.class, String.class, String.class, Integer.class, Double.class
//			};
//			public Class getColumnClass(int columnIndex) {
//				return columnTypes[columnIndex];
//			}
//		});
//		tableTK.setFont(new Font("Times New Roman", Font.PLAIN, 17));
//		scrollPane_1.setViewportView(tableTK);

		btnTK = new JButton("Thống Kê");
		btnTK.setIcon(new ImageIcon("hinh\\thongKe.png"));
		btnTK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTK.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnTK.setBounds(573, 81, 170, 35);
		add(btnTK);

		btnIn = new JButton("In");
		btnIn.setIcon(new ImageIcon("hinh\\iconIn.png"));
		btnIn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnIn.setBounds(799, 81, 105, 35);
		add(btnIn);

		btnTK.addActionListener(this);
		btnIn.addActionListener(this);

		lblTienLuongNN = new JLabel("Tiền lương thấp nhất: ");
		lblTienLuongNN.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTienLuongNN.setBounds(188, 155, 166, 21);
		add(lblTienLuongNN);

		lbLTN = new JLabel("???");
		lbLTN.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lbLTN.setBounds(364, 155, 219, 21);
		add(lbLTN);

		lblTienLuongCaoNhat = new JLabel("Tiền lương cao nhất: ");
		lblTienLuongCaoNhat.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTienLuongCaoNhat.setBounds(188, 198, 166, 21);
		add(lblTienLuongCaoNhat);

		lbLCN = new JLabel("???");
		lbLCN.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lbLCN.setBounds(364, 198, 219, 21);
		add(lbLCN);

		lbLCN_1 = new JLabel("???");
		lbLCN_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lbLCN_1.setBounds(743, 155, 194, 21);
		add(lbLCN_1);

		lbLCN_2 = new JLabel("???");
		lbLCN_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lbLCN_2.setBounds(743, 198, 194, 21);
		add(lbLCN_2);

		lblTongCN = new JLabel("Tổng công nhân:");
		lblTongCN.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTongCN.setBounds(605, 155, 128, 21);
		add(lblTongCN);

		lblTongTIenLuong = new JLabel("Tổng tiền lương: ");
		lblTongTIenLuong.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTongTIenLuong.setBounds(605, 198, 128, 21);
		add(lblTongTIenLuong);

		btnXuatExcel = new JButton("Xuất Excel");
		btnXuatExcel.setIcon(new ImageIcon("hinh\\iconIn.png"));
		btnXuatExcel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnXuatExcel.setBounds(961, 81, 170, 35);
		add(btnXuatExcel);

		btnXuatExcel.addActionListener(this);

		lblCNXS = new JLabel("Công nhân xuất sắc:");
		lblCNXS.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCNXS.setBounds(960, 155, 150, 21);
		add(lblCNXS);

		lbLCNXSac = new JLabel("???");
		lbLCNXSac.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lbLCNXSac.setBounds(1120, 155, 180, 21);
		add(lbLCNXSac);
		
		btnTopCN = new JButton("DS");
		btnTopCN.setBounds(47, 157, 85, 21);
		add(btnTopCN);
		btnTopCN.addActionListener(this);

		tkCNDao = new ThongKeCongNhanDao();

		clearTable();
//		updatetable();
	}

	//
	public void clearTable() {
		modelLuong.getDataVector().removeAllElements();
		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTopCN)) {
			int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
			int thang = Integer.parseInt(cboThang.getSelectedItem().toString());

			DecimalFormat df = new DecimalFormat("###,###,### VND");
			listX = null;
			try {
				String sospmax = tkCNDao.soSPMax(thang, nam);
				System.out.print(sospmax);
				listX = tkCNDao.dsCNXuatSac(thang, nam, sospmax);
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			System.out.print("ds:" + listX.get(0).toString());
			clearTable();
			if (listX != null) {
				for (int i = 0; i < listX.size(); i++) {
					double x = (double) listX.get(i)[4];
					modelLuong.addRow(new Object[] { listX.get(i)[0], listX.get(i)[1], listX.get(i)[2],
							listX.get(i)[3], df.format(x) });
				}
			}
			
			listCC = null;
			listCN = null;
			listLuong = null;
		}
		
		if (o.equals(btnTK)) {
			int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
			int thang = Integer.parseInt(cboThang.getSelectedItem().toString());

			DecimalFormat df = new DecimalFormat("###,###,### VND");
			
			

			try {
				tongLuong = tkCNDao.tongLuongCN(thang, nam);//
				lbLCN_2.setText(df.format(tongLuong).toString());
				int soCN;
				soCN = tkCNDao.tongCN();
				lbLCN_1.setText(Integer.toString(soCN));
				double luongLN, luongNN;
				luongLN = tkCNDao.luongCaoNhat(thang, nam);
				lbLCN.setText(df.format(luongLN));
				luongNN = tkCNDao.luongThapNhat(thang, nam);
				lbLTN.setText(df.format(luongNN));
//				String cn = tkCNDao.congNhanXuatSac(thang, nam);//tra 1 con int
				
				
//				lbLCNXSac.setText(cn);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				listCN = cnDao.getAllCongNhan();
				listLuong = luongCNDao.getAllLuongCN();
			} catch (Exception ex) {
				Logger.getLogger(FrmTKCongNhan.class.getName()).log(Level.SEVERE, null, ex);
			}
			clearTable();
			try {

//				listCC = tkCNDao.soNgayLam(thang, nam);
				List<Object[]> listOB = null;
				listOB = tkCNDao.thongKe(thang, nam);
				if (listOB != null) {
					for (int i = 0; i < listOB.size(); i++) {
						double x = (double) listOB.get(i)[4];
						modelLuong.addRow(new Object[] { listOB.get(i)[0], listOB.get(i)[1], listOB.get(i)[2],
								listOB.get(i)[3], df.format(x) });
					}
				}

			} catch (Exception e2) {
				// TODO: handle exception
				Logger.getLogger(FrmTKCongNhan.class.getName()).log(Level.SEVERE, null, e2);
			}
		}
		if (o.equals(btnIn)) {
			int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
			int thang = Integer.parseInt(cboThang.getSelectedItem().toString());

			FrmXuatTKCongNhan frmXuatTK;
			try {
				frmXuatTK = new FrmXuatTKCongNhan(luongCNDao, ccCNDao, cnDao, thang, nam);
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
}
