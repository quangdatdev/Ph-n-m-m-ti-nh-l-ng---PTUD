package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.CaLamNVDao;
import dao.ChamCongCNDao;
import dao.ChamCongNVDao;
import dao.LuongCNDao;
import dao.LuongNVDao;
import dao.NVDao;
import entity.ChamCongCN;
import entity.ChamCongNV;
import entity.LuongCN;
import entity.LuongNV;
import javax.swing.JTextField;

public class FrmTienLuongNV extends JPanel implements ActionListener, MouseListener {

	private static final String DO_NOTHING_ON_CLOSE = null;
	private JTable tableLuong;
	private DefaultTableModel modelLuong;
	private LuongNVDao luongNVDao;
	private ChamCongNVDao cCNVDao;
	private JComboBox cboThang;
	private JComboBox cboNam;
	private JButton btnXem;
	private List<ChamCongNV> listCC;
	private List<LuongNV> listluong;
	private LuongNV luongNV = new LuongNV();
	private JTextField txtTong;
	private List<Object[]> listOB;
	private CaLamNVDao caLamNVDao;
	private NVDao nvDao;
	private FrmChiTietChamCongNV gui;

	/**
	 * Create the panel.
	 */
	public FrmTienLuongNV() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("TIỀN LƯƠNG NHÂN VIÊN");
		lblNewLabel.setForeground(new Color(255, 128, 128));
		lblNewLabel.setBounds(614, 28, 366, 63);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		add(lblNewLabel);

		cboThang = new JComboBox();
		cboThang.setBackground(Color.WHITE);
		cboThang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboThang.setEditable(true);
		cboThang.setBounds(423, 132, 120, 30);
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

		JLabel lblThang = new JLabel("Tháng:");
		lblThang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblThang.setBounds(357, 135, 55, 24);
		add(lblThang);

		cboNam = new JComboBox();
		cboNam.setBackground(Color.WHITE);
		cboNam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboNam.setEditable(true);
		cboNam.setBounds(738, 132, 120, 30);
		for (int i = 1; i < 20; i++) {
			cboNam.addItem(2009 + i);
		}
		cboNam.setSelectedItem(2022);
		add(cboNam);

		JLabel lblNam = new JLabel("Năm:");
		lblNam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNam.setBounds(683, 134, 55, 24);
		add(lblNam);

		btnXem = new JButton("Xem");
		btnXem.setBackground(new Color(255, 255, 255));
		btnXem.setIcon(new ImageIcon("hinh\\iconTimKiem.png"));
		btnXem.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnXem.setBounds(1010, 128, 113, 39);
		add(btnXem);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(3, 198, 1550, 414);
		add(scrollPane);

		tableLuong = new JTable();
		tableLuong.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers1 = "Mã NV;Tên NV;Tháng;Năm;Tiền lương".split(";");
		modelLuong = new DefaultTableModel(headers1, 0);
		tableLuong.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(tableLuong = new JTable(modelLuong));

		tableLuong.setToolTipText("Nhấp chuột phải 2 lần để hiển thị lịch sử chấm công");//
		tableLuong.setRowHeight(25);
		tableLuong.setAutoCreateRowSorter(true);
		tableLuong.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		tableLuong.addMouseListener(this);//
		scrollPane.setViewportView(tableLuong);

//		cCNVDao = new ChamCongNVDao();
//		luongNVDao = new LuongNVDao();
		btnXem.addActionListener(this);

		JLabel lblTongLuong = new JLabel("Tổng tiền lương: ");
		lblTongLuong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTongLuong.setBounds(31, 622, 137, 24);
		add(lblTongLuong);

		txtTong = new JTextField();
		txtTong.setEditable(false);
		txtTong.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtTong.setBounds(193, 622, 171, 24);
		add(txtTong);
		txtTong.setColumns(10);
//		updatetable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXem)) {
			clearTable();
			luongNVDao = new LuongNVDao();
			int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
			int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
			double sum = luongNVDao.getTongLuong(thang, nam);
			DecimalFormat df = new DecimalFormat("###,###,###");
			txtTong.setText(df.format(sum) + " VNĐ");
//			try {
//				listluong = luongNVDao.getAllLuongThangNam(thang, nam);
//				for (LuongNV luongNV : listluong) {
////					System.out.println(luongNV.getcCNV().getNv().getHoTen().toString());
//					modelLuong.addRow(new Object[] { luongNV.getcCNV().getNv().getMaNV(),
//							luongNV.getcCNV().getNv().getHoTen(), thang, nam, df.format(luongNV.getLuong()) });
//				}
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}

			try {
				List<Object[]> listOB = null;
				listOB = luongNVDao.getLuongTheoThangNam(thang, nam);
				if (listOB != null) {
					for (int i = 0; i < listOB.size(); i++) {
						double x = (double) listOB.get(i)[2];
						modelLuong
								.addRow(new Object[] { listOB.get(i)[0], listOB.get(i)[1], thang, nam, df.format(x) });
					}
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		}

	}

	public void clearTable() {
		modelLuong.getDataVector().removeAllElements();
		revalidate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int index = tableLuong.getSelectedRow();

		System.out.println(e.getClickCount());// in trong console đếm số lần click
		System.out.println("click chuột");//
		int count = e.getClickCount();

		if (index > -1) {
//            txtMaLuong.setText(modelLuong.getValueAt(index, 0).toString());
//            cbMaNV.setSelectedItem(modelLuong.getValueAt(index, 1).toString());
//            cbTenNV.setSelectedItem(modelLuong.getValueAt(index, 2).toString());
//            cbThang.setSelectedItem(modelLuong.getValueAt(index, 7).toString());
//            cbNam.setSelectedItem(modelLuong.getValueAt(index, 8).toString());
//            txtThucLanh.setText(modelLuong.getValueAt(index, 9).toString());
		}
		if (count == 1) {
			System.out.println("Click 1 lần");//

//        	JOptionPane.showMessageDialog(null, "có click rồi");
		}
		if (count == 2) {
//			System.out.println("click chuột 2 lần");//
//        	JOptionPane.showMessageDialog(null, "có 2 lần click rồi mà hong ra cái bảng");

			try {
				String ma = modelLuong.getValueAt(index, 0).toString();//
				int thang = Integer.parseInt(modelLuong.getValueAt(index, 2).toString());
				int nam = Integer.parseInt(modelLuong.getValueAt(index, 3).toString());
				FrmChiTietChamCongNV gui;
				gui = new FrmChiTietChamCongNV(cCNVDao, caLamNVDao, nvDao, ma, thang, nam);
				gui.setVisible(true);

			} catch (Exception ex) {
				Logger.getLogger(FrmTienLuongNV.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

	private void setDefaultCloseOperation(String doNothingOnClose) {
		// TODO Auto-generated method stub

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

}
