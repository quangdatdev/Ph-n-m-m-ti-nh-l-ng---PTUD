package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ChamCongNVDao;
import dao.LuongNVDao;
import dao.NVDao;
import dao.ThongKeNhanVienDao;
import entity.LuongNV;
import entity.NhanVien;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class FrmXuatTKNhanVien extends JFrame implements Printable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	public JTable table;
	private JTable table_1;
	public static JLabel lblTKNV;
	public static JLabel lblDiaChi;
	public static JLabel lblDienThoai;
	public static JLabel lblCongTyGiay;
	public JLabel lblSL;
	public JLabel lblTongNV;
	public JLabel lblTongLuong;
	public JLabel lblNguoiBan;
	public JLabel lblNguoiMuaHang1;
	private static JPanel panel;
	public DefaultTableModel modelLuong;
	private JButton btnIn;
	private LuongNVDao luongNVDao;

	private List<Float> listCC;
	private ThongKeNhanVienDao tkNVDao;
	private JLabel lblNgayLap;
	private JLabel lblNgayLap1_1;
	private JLabel lblNam;
	private JLabel lblThang_tieuDe;
	private JLabel lblNam_tieuDe;
	private int thang;
	private int nam;
	private NVDao nvDao;
	private ChamCongNVDao ccNVDao;
	private List<Object[]> listOB;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	// 29/11
	public FrmXuatTKNhanVien(LuongNVDao lNVDao, ChamCongNVDao ccNVDao, NVDao nvDao, int thang, int nam)
			throws Exception {
		setTitle("THỐNG KÊ NHÂN VIÊN");
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

		lblTKNV = new JLabel("THỐNG KÊ NHÂN VIÊN THÁNG:");
		lblTKNV.setBounds(147, 10, 222, 27);
		lblTKNV.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTKNV.setForeground(new Color(0, 0, 0));
		panel.add(lblTKNV);

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
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		scrollPane.setBounds(10, 174, 596, 393);
		panel.add(scrollPane);

		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		String[] headers1 = "STT;Mã NV;Tên NV;Số Ngày Chấm Công;Lương".split(";");
		modelLuong = new DefaultTableModel(headers1, 0);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(table_1 = new JTable(modelLuong));
		table_1.setFont(new Font("Tohama", Font.PLAIN, 10));
		table_1.setRowHeight(25);
		table_1.setAutoCreateRowSorter(true);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		scrollPane.setViewportView(table_1);

		JLabel lblNguoiLapBaoCao = new JLabel("Người Lập Báo Cáo");
		lblNguoiLapBaoCao.setBounds(446, 655, 134, 14);
		lblNguoiLapBaoCao.setForeground(new Color(0, 0, 0));
		lblNguoiLapBaoCao.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblNguoiLapBaoCao);

		JLabel lblKyTen = new JLabel("(Ký, ghi rõ họ tên)");
		lblKyTen.setBounds(460, 668, 95, 14);
		lblKyTen.setForeground(new Color(0, 0, 0));
		lblKyTen.setFont(new Font("Tahoma", Font.ITALIC, 10));
		panel.add(lblKyTen);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(10, 577, 596, 35);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblTongSoNV = new JLabel("Tổng Số Nhân Viên:");
		lblTongSoNV.setForeground(new Color(0, 0, 0));
		lblTongSoNV.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTongSoNV.setBounds(220, 5, 120, 24);
		panel_2.add(lblTongSoNV);

		lblSL = new JLabel("");
		lblSL.setHorizontalAlignment(SwingConstants.LEFT);
		lblSL.setBounds(75, 12, 31, 14);
		panel_2.add(lblSL);

		lblTongNV = new JLabel("");
		lblTongNV.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblTongNV.setHorizontalAlignment(SwingConstants.LEFT);
		lblTongNV.setBounds(410, 7, 65, 19);
		panel_2.add(lblTongNV);

		JLabel lblDonViNV = new JLabel("(nhân viên)");
		lblDonViNV.setForeground(new Color(0, 0, 0));
		lblDonViNV.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDonViNV.setBounds(518, 10, 72, 19);
		panel_2.add(lblDonViNV);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 128)));
		panel_3.setBounds(10, 610, 596, 35);
		panel.add(panel_3);

		JLabel lblTongLuongNV = new JLabel("Tổng Tiền Lương Nhân Viên:");
		lblTongLuongNV.setBounds(220, 8, 181, 23);
		panel_3.add(lblTongLuongNV);
		lblTongLuongNV.setForeground(new Color(0, 0, 0));
		lblTongLuongNV.setFont(new Font("Times New Roman", Font.PLAIN, 14));

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
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");//

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

		lblNgayLap = new JLabel("Ngày Lập: ");
		lblNgayLap.setForeground(new Color(0, 0, 0));
		lblNgayLap.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNgayLap.setBounds(410, 91, 66, 20);
		panel.add(lblNgayLap);

		lblNgayLap1_1 = new JLabel("");
		lblNgayLap1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNgayLap1_1.setBounds(473, 94, 84, 14);
		panel.add(lblNgayLap1_1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");//
		lblNgayLap1_1.setText(LocalDateTime.now().format(formatter));

		lblNam = new JLabel("NĂM:");
		lblNam.setForeground(new Color(0, 0, 0));
		lblNam.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNam.setBounds(400, 17, 45, 13);
		panel.add(lblNam);

		lblThang_tieuDe = new JLabel("???");
		lblThang_tieuDe.setForeground(new Color(0, 0, 0));
		lblThang_tieuDe.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblThang_tieuDe.setBounds(372, 17, 45, 13);
		panel.add(lblThang_tieuDe);

		lblNam_tieuDe = new JLabel("???");
		lblNam_tieuDe.setForeground(new Color(0, 0, 0));
		lblNam_tieuDe.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNam_tieuDe.setBounds(442, 17, 45, 13);
		panel.add(lblNam_tieuDe);

//		btnIn = new JButton("IN");
//		btnIn.setBackground(new Color(192, 192, 192));
//		btnIn.setIcon(new ImageIcon("hinh\\iconIn.png"));
//		btnIn.setBounds(10, 10, 97, 32);
//		contentPane.add(btnIn);

		// 29/11
		ccNVDao = new ChamCongNVDao();// lNVDao = new LuongNVDao();
//		caDao = new CaLamNVDao();//
		nvDao = new NVDao();
		luongNVDao = new LuongNVDao();
		this.ccNVDao = ccNVDao;
//		this.caDao = caDao;
		this.nvDao = nvDao;
//		this.ma = ma;
		this.thang = thang;
		this.nam = nam;
		listOB = luongNVDao.dsLNV(thang, nam);// khai báo ở dòng 57

		lblThang_tieuDe.setText(Integer.toString(thang));
		lblNam_tieuDe.setText(Integer.toString(nam));
		clearTable();
		updateTableData();
	}

	public void printingBaoCao() {
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

	public void clearTable() {
		modelLuong.getDataVector().removeAllElements();
		revalidate();
	}

	public void updateTableData() throws SQLException {
		int o = 1;
		DecimalFormat df = new DecimalFormat("###,###,###");
		double s = luongNVDao.getTongLuong(thang, nam);
		int sl = luongNVDao.getTongSoNV();
		if (listOB != null) {
			for (int i = 0; i < listOB.size(); i++) {
				double x = Double.parseDouble(listOB.get(i)[3].toString());
				modelLuong.addRow(
						new Object[] { o++, listOB.get(i)[0], listOB.get(i)[1], listOB.get(i)[2], df.format(x), });
			}
		}
		lblTongNV.setText(sl + "");
		lblTongLuong.setText(df.format(s) + "");
	}
}
