package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CNDao;
import dao.ChamCongCNDao;
import dao.ChamCongNVDao;
import dao.LuongCNDao;
import dao.LuongNVDao;
import dao.NVDao;
import entity.LuongCN;
import entity.LuongNV;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class FrmXuatTKCongNhan extends JFrame implements Printable, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	public JTable table;
	private JTable table_1;
	public static JLabel lblTKCN;
	public static JLabel lblDiaChi;
	public static JLabel lblDienThoai;
	public static JLabel lblCongTyGiay;
	public static JLabel lblNgayLap;
	public JLabel lblNgayLap1;
	public JLabel lblSL;
	public JLabel lblTongCN;
	public JLabel lblTongLuong;
	public JLabel lblNguoiBan;
	public JLabel lblNguoiMuaHang1;
	private static JPanel panel;
	public DefaultTableModel modelLuong;
	private LuongCNDao luongCNDao;

	private ChamCongCNDao ccCNDao;
	private CNDao cnDao;
	private int thang;
	private int nam;
//	private Object listOB;
	private JLabel lblThang_tieuDe;
	private JLabel lblNam_tieuDe;

	private List<Object[]> listOB;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrmXuatTKCongNhan frame = new FrmXuatTKCongNhan();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public FrmXuatTKCongNhan(LuongCNDao lCNDao, ChamCongCNDao ccCNDao, CNDao cnDao, int thang, int nam)
			throws Exception {
		setTitle("THỐNG KÊ CÔNG NHÂN");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setSize(1199, 775);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		panel.setBounds(432, 11, 616, 724);
		contentPane.add(panel);
		panel.setLayout(null);

		lblTKCN = new JLabel("THỐNG KÊ CÔNG NHÂN THÁNG:");
		lblTKCN.setBounds(128, 10, 230, 27);
		lblTKCN.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTKCN.setForeground(new Color(0, 0, 0));
		panel.add(lblTKCN);

		lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setBounds(33, 125, 58, 14);
		lblDiaChi.setForeground(new Color(0, 0, 0));
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panel.add(lblDiaChi);

		lblDienThoai = new JLabel("Điện thoại:");
		lblDienThoai.setBounds(33, 88, 75, 27);
		lblDienThoai.setForeground(new Color(0, 0, 0));
		lblDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panel.add(lblDienThoai);

		lblCongTyGiay = new JLabel("CÔNG TY GIÀY CK-D");
		lblCongTyGiay.setBounds(164, 38, 281, 53);
		lblCongTyGiay.setHorizontalAlignment(SwingConstants.CENTER);
		lblCongTyGiay.setForeground(new Color(0, 0, 0));
		lblCongTyGiay.setFont(new Font("Times New Roman", Font.BOLD, 24));
		panel.add(lblCongTyGiay);

		lblNgayLap = new JLabel("Ngày Lập: ");
		lblNgayLap.setBounds(413, 137, 66, 20);
		lblNgayLap.setForeground(new Color(0, 0, 0));
		lblNgayLap.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panel.add(lblNgayLap);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		scrollPane.setBounds(10, 171, 596, 418);
		panel.add(scrollPane);

		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers1 = "STT;Mã CN;Tên CN;Công Việc;Tổng Sản Phẩm;Tổng Tiền Lương".split(";");
		modelLuong = new DefaultTableModel(headers1, 0);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(table_1 = new JTable(modelLuong));
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		table_1.setRowHeight(25);
		table_1.setAutoCreateRowSorter(true);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane.setViewportView(table_1);

		JLabel lblNguoiBanHang = new JLabel("Người Lập Báo Cáo");
		lblNguoiBanHang.setBounds(446, 675, 134, 14);
		lblNguoiBanHang.setForeground(new Color(0, 0, 0));
		lblNguoiBanHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblNguoiBanHang);

		JLabel label_1 = new JLabel("(Ký, ghi rõ họ tên)");
		label_1.setBounds(460, 688, 95, 14);
		label_1.setForeground(new Color(0, 0, 0));
		label_1.setFont(new Font("Tahoma", Font.ITALIC, 10));
		panel.add(label_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(10, 599, 596, 35);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblTongSoCN = new JLabel("Tổng số Công Nhân:");
		lblTongSoCN.setForeground(new Color(0, 0, 0));
		lblTongSoCN.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTongSoCN.setBounds(220, 5, 120, 24);
		panel_2.add(lblTongSoCN);

		lblSL = new JLabel("");
		lblSL.setHorizontalAlignment(SwingConstants.LEFT);
		lblSL.setBounds(75, 12, 31, 14);
		panel_2.add(lblSL);

		lblTongCN = new JLabel("");
		lblTongCN.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblTongCN.setHorizontalAlignment(SwingConstants.LEFT);
		lblTongCN.setBounds(410, 7, 65, 19);
		panel_2.add(lblTongCN);

		JLabel lblDonViCN = new JLabel("(công nhân)");
		lblDonViCN.setForeground(new Color(0, 0, 0));
		lblDonViCN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDonViCN.setBounds(518, 10, 72, 19);
		panel_2.add(lblDonViCN);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 128)));
		panel_3.setBounds(10, 633, 596, 35);
		panel.add(panel_3);

		JLabel lblTongTienLuongCN = new JLabel("Tổng Tiền Lương Công Nhân:");
		lblTongTienLuongCN.setBounds(220, 8, 181, 23);
		panel_3.add(lblTongTienLuongCN);
		lblTongTienLuongCN.setForeground(new Color(0, 0, 0));
		lblTongTienLuongCN.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		lblTongLuong = new JLabel("");
		lblTongLuong.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblTongLuong.setHorizontalAlignment(SwingConstants.LEFT);
		lblTongLuong.setBounds(410, 7, 133, 23);
		panel_3.add(lblTongLuong);

		JLabel lblvnd = new JLabel("(VND)");
		lblvnd.setForeground(new Color(0, 0, 0));
		lblvnd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblvnd.setBounds(548, 12, 38, 14);
		panel_3.add(lblvnd);

		JLabel label_4 = new JLabel("");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(130, 12, 116, 14);
		panel_3.add(label_4);

		JLabel lblSDT1 = new JLabel("0802.090.301  - 0975.326.451");
		lblSDT1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSDT1.setBounds(100, 93, 199, 14);
		panel.add(lblSDT1);

		JLabel lblDiaChi1 = new JLabel("55 Nguyễn Thái Sơn, p4, Quận Gò Vấp, Tp.HCM");
		lblDiaChi1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDiaChi1.setBounds(101, 119, 295, 27);
		panel.add(lblDiaChi1);

		lblNgayLap1 = new JLabel("");
		lblNgayLap1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNgayLap1.setBounds(478, 140, 84, 14);
		panel.add(lblNgayLap1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		lblNgayLap1.setText(LocalDateTime.now().format(formatter));

		lblNguoiBan = new JLabel("");
		lblNguoiBan.setHorizontalAlignment(SwingConstants.CENTER);
		lblNguoiBan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNguoiBan.setBounds(410, 675, 168, 27);
		panel.add(lblNguoiBan);

		lblNguoiMuaHang1 = new JLabel("");
		lblNguoiMuaHang1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNguoiMuaHang1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNguoiMuaHang1.setBounds(18, 675, 157, 30);
		panel.add(lblNguoiMuaHang1);

		JLabel lblNam = new JLabel("NĂM:");
		lblNam.setForeground(new Color(0, 0, 0));
		lblNam.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNam.setBounds(386, 10, 45, 27);
		panel.add(lblNam);

		lblNam_tieuDe = new JLabel("???");
		lblNam_tieuDe.setForeground(new Color(0, 0, 0));
		lblNam_tieuDe.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNam_tieuDe.setBounds(441, 17, 45, 13);
		panel.add(lblNam_tieuDe);

		lblThang_tieuDe = new JLabel("???");
		lblThang_tieuDe.setForeground(new Color(0, 0, 0));
		lblThang_tieuDe.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblThang_tieuDe.setBounds(366, 17, 45, 13);
		panel.add(lblThang_tieuDe);

//		JButton btnIn = new JButton("IN");
//		btnIn.setBackground(new Color(192, 192, 192));
//		btnIn.setIcon(new ImageIcon("hinh\\iconIn.png"));
//		btnIn.setBounds(10, 11, 102, 33);
//		contentPane.add(btnIn);

		// 29/11
		ccCNDao = new ChamCongCNDao();
		luongCNDao = new LuongCNDao();//
		cnDao = new CNDao();

		this.ccCNDao = ccCNDao;
		this.cnDao = cnDao;
		this.thang = thang;
		this.nam = nam;
		listOB = luongCNDao.dsLCN(thang, nam);
		lblThang_tieuDe.setText(Integer.toString(thang));
		lblNam_tieuDe.setText(Integer.toString(nam));
		clearTable();
		updateTable();

	}

	public void clearTable() {
		modelLuong.getDataVector().removeAllElements();
		revalidate();
	}

	public void printingHoaDon() {
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(this);
		boolean ok = job.printDialog();
		if (ok) {
			try {
				job.print();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) graphics;
		if (pageIndex > 0) {
			return NO_SUCH_PAGE;
		}
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
		panel.printAll(graphics);
		return PAGE_EXISTS;
	}

	public void updateTable() throws SQLException {
		double s = luongCNDao.getTongLuong(thang, nam);
		int sl = luongCNDao.getTongSoCN();

		DecimalFormat df = new DecimalFormat("###,###,###");

		lblTongLuong.setText(df.format(s) + "");
		lblTongCN.setText(sl + "");

		if (listOB != null) {
			int l = 1;
			for (int i = 0; i < listOB.size(); i++) {
				double z = Double.parseDouble(listOB.get(i)[4].toString());
				modelLuong.addRow(new Object[] { l++, listOB.get(i)[0], listOB.get(i)[1], listOB.get(i)[2],
						listOB.get(i)[3], df.format(z) });

			}
		}
	}

}
