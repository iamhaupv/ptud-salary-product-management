package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ctc.wstx.shaded.msv_core.grammar.ConcurExp;
import com.itextpdf.awt.geom.Point;

import connect.connectDB;
import dao.ChamCong_NhanVien_DAO;
import dao.CongNhan_DAO;
import dao.NhanVien_DAO;
import dao.PhongBan_DAO;
import dao.LuongNhanVien_DAO;
import entity.ChamCong_NhanVien;
import entity.CongDoan;
import entity.CongNhan;
import entity.LuongNhanVien;
import entity.NhanVien;
import entity.PhanCong_CongDoan;
import entity.PhongBan;
import entity.SanPham;
import helper.ImageHepler;
import helper.KNBCC;
import helper.LNV;
import oracle.sql.DATE;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultComboBoxModel;

public class LuongNhanVien_GUI extends JPanel implements ActionListener, MouseListener{
	private JTable table_cc;
	private JTable table_lnv;
	private JTextField txtMa;
	private JTextField txtPhat;
	private JTextField txtTimKiem;
	private JButton btnTinhLuong;
	private JButton btnCapNhat;
	private JButton btnXoa;
	private JButton btnTaoMoi;
	private JButton btnTimKiem;
	private JButton btnLamMoiBang;
	private JLabel lblTongLuong;
	private ChamCong_NhanVien_DAO cclnv_dao;
	private DefaultTableModel tableModel_cc;
	private DefaultTableModel tableModel_lnv;
	private LuongNhanVien_DAO lnv_dao;
	private JComboBox cboPhong;
	private NhanVien_DAO nv_dao;
	private PhongBan_DAO pb_dao;
	private JLabel lblMaPhong;
	private JComboBox cboMaPhong;
	private JLabel lblNgay;
	private JDateChooser cboNgay;
	private JComboBox cboThang;
	private JComboBox cboNam;
	private LNV ln;
	private JComboBox cboChucVu;
	public static KNBCC knbcc;
	private JComboBox cboLuong;
	private JLabel lblLuongg;
	/**
	 * Create the panel.
	 */
	public LuongNhanVien_GUI() {
		connectDB.getInstance().connect();
		cclnv_dao = new ChamCong_NhanVien_DAO();
		lnv_dao = new LuongNhanVien_DAO();
		nv_dao = new NhanVien_DAO();
		pb_dao = new PhongBan_DAO();
		setLayout(null);

		JScrollPane scroll_cc = new JScrollPane();
		scroll_cc.setBounds(20, 187, 566, 563);
		add(scroll_cc);
		// bang cham cong nhan vien
		table_cc = new JTable();
		table_cc.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		String header_cc [] = "Mã nhân viên;Tên nhân viên;Phái;Ngày sinh;Địa chỉ;Email;Số điện thoại;Ngày tham gia công tác;TDCM;Kinh nghiệm;Chức vụ".split(";");
		tableModel_cc = new DefaultTableModel(header_cc, 0);
		table_cc.setModel(tableModel_cc);
		scroll_cc.setViewportView(table_cc);
		table_cc.addMouseListener(new NhanVienMouse());
		JLabel lbl_tt = new JLabel("BẢNG NHÂN VIÊN");
		lbl_tt.setBounds(174, 142, 229, 35);
		lbl_tt.setFont(new Font("Times New Roman", Font.BOLD, 25));
		add(lbl_tt);

		JScrollPane scroll_lnv = new JScrollPane();
		scroll_lnv.setBounds(596, 392, 922, 310);
		add(scroll_lnv);

		table_lnv = new JTable();
		table_lnv.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		//
		String header_lnv [] = "Mã nhân viên;Họ tên;Mã phòng; Tên phòng;Mã LNV;Phụ cấp;Thưởng;Phạt;BHXH;Số ngày công;Tổng lương;Tháng lương".split(";");
		tableModel_lnv = new DefaultTableModel(header_lnv, 0);
		table_lnv.setModel(tableModel_lnv);
		scroll_lnv.setViewportView(table_lnv);

		JLabel lbl_tt_lnv = new JLabel("THÔNG TIN LƯƠNG NHÂN VIÊN");
		lbl_tt_lnv.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_tt_lnv.setBounds(876, 334, 394, 35);
		add(lbl_tt_lnv);

		JLabel lblPhat = new JLabel("Phạt:");
		lblPhat.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblPhat.setBounds(1017, 46, 45, 21);
		add(lblPhat);

		JLabel lblMa = new JLabel("Mã lương nhân viên:");
		lblMa.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMa.setBounds(642, 46, 154, 21);
		add(lblMa);

		txtMa = new JTextField();
		txtMa.setFont(new Font("Times New Roman", Font.BOLD, 17));
		txtMa.setBounds(821, 47, 174, 19);
		add(txtMa);
		txtMa.setText(autoMaHD());
		txtMa.setEnabled(false);
		txtMa.setColumns(10);

		txtPhat = new JTextField();
		txtPhat.setFont(new Font("Times New Roman", Font.BOLD, 17));
		txtPhat.setBounds(1072, 47, 144, 19);
		add(txtPhat);
		txtPhat.setText("0");
		txtPhat.setColumns(10);

		btnTinhLuong = new JButton("Tính lương");
		btnTinhLuong.setIcon(new ImageIcon("image/salaryh_.png"));
		btnTinhLuong.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnTinhLuong.setBounds(743, 258, 159, 46);
		add(btnTinhLuong);
		btnTinhLuong.addActionListener(this);

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setIcon(new ImageIcon("image/exchange.png"));
		btnCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCapNhat.setBounds(948, 258, 154, 46);
		add(btnCapNhat);

		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("image/delete.png"));
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnXoa.setBounds(1146, 258, 124, 46);
		add(btnXoa);

		btnTaoMoi = new JButton("Tạo mới");
		btnTaoMoi.setIcon(new ImageIcon("image/create.png"));
		btnTaoMoi.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnTaoMoi.setBounds(1313, 258, 144, 46);
		add(btnTaoMoi);

		JLabel lblTimKiem = new JLabel("Nhập để tìm kiếm:");
		lblTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTimKiem.setBounds(428, 712, 144, 30);
		add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(619, 715, 695, 30);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);

		btnTimKiem = new JButton("");
		btnTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnTimKiem.setBounds(1336, 715, 85, 35);
		add(btnTimKiem);
		btnTimKiem.setIcon(new ImageIcon("image/search.png"));

		btnLamMoiBang = new JButton("");
		btnLamMoiBang.setBounds(1431, 715, 85, 35);
		add(btnLamMoiBang);
		btnLamMoiBang.setIcon(new ImageIcon("image/rs.png"));
		String hsl [] = "6.2;6.56;6.92;7.28;7.64;8.0".split(";");

		JLabel lblLuong = new JLabel("Tổng lương được nhận:");
		lblLuong.setForeground(new Color(255, 0, 0));
		lblLuong.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblLuong.setBounds(1017, 95, 187, 30);
		add(lblLuong);

		lblTongLuong = new JLabel("");
		lblTongLuong.setForeground(new Color(255, 0, 0));
		lblTongLuong.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTongLuong.setBounds(1200, 106, 187, 19);
		add(lblTongLuong);
		//
		//		loadBangChamCong();

		btnXoa.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnTaoMoi.addActionListener(this);
		table_lnv.addMouseListener(this);

		JLabel lbl_Phong = new JLabel("Phòng:");
		lbl_Phong.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lbl_Phong.setBounds(73, 46, 70, 21);
		add(lbl_Phong);

		cboPhong = new JComboBox();
		cboPhong.addItem("Chọn");
		cboPhong.setBounds(174, 48, 273, 21);
		add(cboPhong);

		lblMaPhong = new JLabel("Mã phòng:");
		lblMaPhong.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMaPhong.setBounds(73, 102, 104, 17);
		add(lblMaPhong);

		cboMaPhong = new JComboBox();
		cboMaPhong.addItem("");
		cboMaPhong.setEnabled(false);
		cboMaPhong.setBounds(174, 102, 273, 21);
		add(cboMaPhong);

		lblNgay = new JLabel("Ngày:");
		lblNgay.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNgay.setBounds(642, 95, 55, 30);
		add(lblNgay);

		cboNgay = new JDateChooser();
		cboNgay.getSpinner().setEnabled(false);
		cboNgay.setBounds(821, 103, 174, 20);
		add(cboNgay);

		JLabel lblThang = new JLabel("Tháng:");
		lblThang.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblThang.setBounds(642, 146, 63, 30);
		add(lblThang);

		JLabel lblNam = new JLabel("Năm:");
		lblNam.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNam.setBounds(1017, 149, 55, 24);
		add(lblNam);

		cboThang = new JComboBox();
		cboThang.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cboThang.setBounds(821, 153, 51, 21);
		add(cboThang);

		cboThang.setSelectedItem((LocalDate.now().getMonthValue())+"");
		cboThang.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				boolean flag = true;
				tableModel_lnv.setRowCount(0);
				List<LuongNhanVien> l = lnv_dao.getallLuongNhanVien();
				for (LuongNhanVien c : l) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
						if(!cboThang.getSelectedItem().toString().equals("Chọn")) {
							String s1 = sdf.format(c.getThangLuong());
							java.util.Date date1 = null;
							try {
								date1 = sdf.parse(s1);
							} catch (Exception e2) {
								// TODO: handle exception
							}
							if((cboThang.getSelectedItem().equals(date1.getMonth()+1+"") && (cboNam.getSelectedItem().equals(date1.getYear()+1900+"")) && (cboMaPhong.getSelectedItem().toString().equals(c.getNhanVien().getPhong().getMaPB())))) {
								LuongNhanVien d = new LuongNhanVien(c.getThangLuong(), c.getPhuCap(), c.getThuong(), c.getPhat(), c.getBhxh(), c.getTongLuong());
								LNV.luongNhanVien = d;
								tableModel_lnv.addRow(new Object[] {
										c.getNhanVien().getMaNV(), c.getNhanVien().getHoTen(), c.getNhanVien().getPhong().getMaPB(), c.getNhanVien().getPhong().getTenPhong(),
										c.getMaLNV(), c.getPhuCap(), c.getThuong(), c.getPhat(), c.getBhxh(), c.getSoNgayCong(),c.getTongLuong(), sdf.format(c.getThangLuong())
								});
							}
						}

					}
				}
				table_lnv.setModel(tableModel_lnv);
			}		
		});

		cboNam = new JComboBox();
		cboNam.setModel(new DefaultComboBoxModel(new String[] {"2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "122"}));
		cboNam.setBounds(1072, 153, 87, 21);
		add(cboNam);
		cboNam.setSelectedItem(LocalDate.now().getYear()+"");
		
		JLabel lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblChucVu.setBounds(642, 205, 70, 21);
		add(lblChucVu);
		
		cboChucVu = new JComboBox();
		cboChucVu.setModel(new DefaultComboBoxModel(new String[] {"Nhân viên", "Quản lý", "Phó trưởng phòng", "Trưởng phòng", "Phó giám đốc", "Giám đốc", "Kế toán"}));
		cboChucVu.setBounds(821, 207, 159, 21);
		add(cboChucVu);
		cboChucVu.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				List<NhanVien> n = null;
				boolean flag = true;
				try {
					n = nv_dao.findAll();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (NhanVien s : n) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
						if(!cboChucVu.getSelectedItem().toString().equals("Chọn")) {
							if(cboChucVu.getSelectedItem().equals("Nhân viên")) {
								cboLuong.setSelectedItem("8000000");
							}
							else if(cboChucVu.getSelectedItem().equals("Quản lý")) {
								cboLuong.setSelectedItem("12000000");
							}
							else if(cboChucVu.getSelectedItem().equals("Phó trưởng phòng")) {
								cboLuong.setSelectedItem("15000000");
							}
							else if(cboChucVu.getSelectedItem().equals("Trưởng phòng")) {
								cboLuong.setSelectedItem("17000000");
							}
							else if(cboChucVu.getSelectedItem().equals("Phó giám đốc")) {
								cboLuong.setSelectedItem("22000000");
							}
							else if(cboChucVu.getSelectedItem().equals("Giám đốc")) {
								cboLuong.setSelectedItem("25000000");
							}
							else if(cboChucVu.getSelectedItem().equals("Kế toán")) {
								cboLuong.setSelectedItem("18000000");
							}
						}else {
							if(cboChucVu.getSelectedItem().equals("Chọn"));
							cboChucVu.setSelectedItem("");
						}
					}
				}
			}
		});
		
		cboLuong = new JComboBox();
		cboLuong.setEnabled(false);
		cboLuong.setModel(new DefaultComboBoxModel(new String[] {"8000000", "12000000", "15000000", "17000000", "22000000", "25000000", "18000000"}));
		cboLuong.setBounds(1135, 207, 187, 21);
		add(cboLuong);
		
		lblLuongg = new JLabel("Lương/Tháng:");
		lblLuongg.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblLuongg.setBounds(1017, 205, 121, 21);
		add(lblLuongg);
		cboNam.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int r = table_lnv.getSelectedRow();
				Locale localeVN = new Locale("vi", "VN");
				NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				boolean flag = true;
				tableModel_lnv.setRowCount(0);
				List<LuongNhanVien> l = lnv_dao.getallLuongNhanVien();
				for (LuongNhanVien c : l) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
						if(!cboNam.getSelectedItem().toString().equals("Chọn")) {
							String s1 = sdf.format(c.getThangLuong());
							java.util.Date date1 = null;

							try {
								date1 = sdf.parse(s1);
							} catch (Exception e2) {
								// TODO: handle exception
							}
							if((cboNam.getSelectedItem().equals(date1.getYear()+1900+"")) && (cboThang.getSelectedItem().equals(date1.getMonth()+1+ "") && (cboMaPhong.getSelectedItem().toString().equals(c.getNhanVien().getPhong().getMaPB())))) {
								LuongNhanVien d = new LuongNhanVien(c.getMaLNV(), c.getThangLuong(), c.getPhuCap(), c.getThuong(), c.getPhat(), c.getBhxh(), c.getTongLuong());
								System.out.println(d);
								LNV.luongNhanVien = d;
								tableModel_lnv.addRow(new Object[] {
										c.getNhanVien().getMaNV(), c.getNhanVien().getHoTen(), c.getNhanVien().getPhong().getMaPB(), c.getNhanVien().getPhong().getTenPhong(),
										c.getMaLNV(), currencyVN.format(c.getPhuCap()), currencyVN.format(c.getThuong()), currencyVN.format(c.getPhat()), currencyVN.format(c.getBhxh()), c.getSoNgayCong(),currencyVN.format(c.getTongLuong()), sdf.format(c.getThangLuong())
								});
							}
						}

					}
				}
				table_lnv.setModel(tableModel_lnv);
			}		
		});

		List<PhongBan> l = pb_dao.findAll();
		for (PhongBan p : l) {
			if(!p.getTenPhong().equals("Phòng Sản Xuất")) {
				cboPhong.addItem(p.getTenPhong());
				cboMaPhong.addItem(p.getMaPB());
			}
		}
		cboPhong.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				List<PhongBan> p = pb_dao.findAll();
				List<LuongNhanVien> l = lnv_dao.getallLuongNhanVien();
				List<NhanVien> n = null;
				boolean flag = true;
				try {
					n = nv_dao.findAll();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tableModel_cc.setRowCount(0);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				for (PhongBan s : p) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
						if(!cboPhong.getSelectedItem().toString().equals("Chọn")) {
							if(cboPhong.getSelectedItem().equals(s.getTenPhong())) {
								cboMaPhong.setSelectedItem(s.getMaPB());
								for (NhanVien nv : n) {
									if(nv.getPhong().getMaPB().equals(cboMaPhong.getSelectedItem())) {
										for (LuongNhanVien v : l) {
											if(nv.getMaNV().equals(v.getNhanVien().getMaNV())) {
												flag = false;
												break;
											}
											else {
												flag = true;
											}
										}
										if(flag == true) {
											tableModel_cc.addRow(new Object[] {nv.getMaNV(), nv.getHoTen(), nv.isPhai() == 1 ? "Nam" : "Nữ",
													sdf.format(nv.getNgaySinh()), nv.getDiaChi(), nv.getEmail(), nv.getPhone() ,
													sdf.format(nv.getNgayThamGiaCongTac()), nv.getTrinhDoChuyenMon(), 
													nv.getKinhNghiem(), nv.getChucVu()});
										}
									}
								}
							}
							table_cc.setModel(tableModel_cc);

						}else {
							if(cboPhong.getSelectedItem().equals("Chọn"));
							cboMaPhong.setSelectedItem("");
						}
					}
				}
			}
		});
		cboMaPhong.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				Locale localeVN = new Locale("vi", "VN");
				NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
				cboNam.setSelectedItem(LocalDate.now().getYear()+"");
				cboThang.setSelectedItem((LocalDate.now().getMonthValue())+"");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				boolean flag = true;
				List<NhanVien> n = null;
				try {
					n = nv_dao.findAll();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tableModel_lnv.setRowCount(0);
				List<PhongBan> p = pb_dao.findAll();
				List<LuongNhanVien> l = lnv_dao.getallLuongNhanVien();
				for (PhongBan s : p) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
						if(!cboPhong.getSelectedItem().toString().equals("Chọn")) {
							if(cboPhong.getSelectedItem().equals(s.getTenPhong())) {
								for (LuongNhanVien c : l) {
									if(c.getNhanVien().getPhong().getMaPB().equals(s.getMaPB())) {
										for (NhanVien v : n) {
											if(v.getMaNV().equals(c.getNhanVien().getMaNV())) {
												flag = false;
												break;
											}
											else {
												flag = true;
											}
										}
										String s1 = sdf.format(c.getThangLuong());
										String s2 = sdf.format(cboNgay.getDate());
										java.util.Date date1 = null;
										java.util.Date date2 = null;

										try {
											date1 = sdf.parse(s1);
											date2 = sdf.parse(s2);
										} catch (Exception e2) {
											// TODO: handle exception
										}
										if(date1.getMonth() == date2.getMonth() && date1.getYear() == date2.getYear() ) {
											if(flag == false) {
												LuongNhanVien d = new LuongNhanVien(c.getThangLuong(), c.getPhuCap(), c.getThuong(), c.getPhat(), c.getBhxh(), c.getTongLuong());
												LNV.luongNhanVien = d;
												tableModel_lnv.addRow(new Object[] {
														c.getNhanVien().getMaNV(), c.getNhanVien().getHoTen(), c.getNhanVien().getPhong().getMaPB(), c.getNhanVien().getPhong().getTenPhong(),
														c.getMaLNV(), currencyVN.format(c.getPhuCap()), currencyVN.format(c.getThuong()), currencyVN.format(c.getPhat()), currencyVN.format(c.getBhxh()), c.getSoNgayCong(),currencyVN.format(c.getTongLuong()), sdf.format(c.getThangLuong())
												});
											}
										}
									}
								}
								table_lnv.setModel(tableModel_lnv);
							}
						}else {
							if(cboPhong.getSelectedItem().equals("Chọn"));
							cboMaPhong.setSelectedItem("");
						}
					}
				}
			}
		});
	}
	public String autoMaHD() {
		String str;
		int ma = lnv_dao.getMaHd();
		if (ma < 100)
			str = "LNV000" + ma;
		else if (ma < 1000)
			str = "LNV00" + ma;
		else if (ma < 10000)
			str = "LNV0" + ma;
		else if (ma < 100000)
			str = "LNV" + ma;
		else
			str = "LV" + ma;
		return str;

	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			int row = table_lnv.getSelectedRow();
			NhanVien nv = new NhanVien(table_lnv.getValueAt(row, 0).toString());
			knbcc = new KNBCC();
			knbcc.nhanVien = nv;
			Locale localeVN = new Locale("vi", "VN");
			NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(row >= 0) {
				String id = (String) table_lnv.getValueAt(row, 4);
				LuongNhanVien_DAO dao = new LuongNhanVien_DAO();
				LuongNhanVien cn = dao.findByID(id);
				if(cn != null) {
					txtMa.setText(cn.getMaLNV());
					txtPhat.setText(cn.getPhat()+"");
					lblTongLuong.setText(currencyVN.format(cn.getTongLuong())+"");
				}
			}
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		table_lnv = (JTable) e.getSource();
		java.awt.Point point = e.getPoint();
		//lấy row được click double
		int r = table_lnv.getSelectedRow();
		int row = table_lnv.rowAtPoint(point);
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		if (e.getClickCount() == 2 ) {
			// làm gì khi click double ở đây
			new BangChamCong_GUI().setVisible(true);
			txtMa.setText(autoMaHD());
			
			NhanVien nv = new NhanVien(table_lnv.getValueAt(r, 1).toString());
			KNBCC.nhanVien = nv;
			
			
			try {
				double a = (double) currencyVN.parse(table_lnv.getValueAt(r, 5).toString());
				double b = (double) currencyVN.parse(table_lnv.getValueAt(r, 6).toString());
				double c = (double) currencyVN.parse(table_lnv.getValueAt(r, 7).toString());
				double d = (double) currencyVN.parse(table_lnv.getValueAt(r, 8).toString());
				double f = (double) currencyVN.parse(table_lnv.getValueAt(r, 10).toString());
				LuongNhanVien lnv = new LuongNhanVien(a, b, c, d, f);
				LNV.luongNhanVien = lnv;
			} catch (Exception e2) {
				// TODO: handle exception
			}
//			LuongNhanVien lnv = new LuongNhanVien(Double.parseDouble(table_lnv.getValueAt(r, 5).toString()),
//					Double.parseDouble(table_lnv.getValueAt(r, 6).toString()),
//					Double.parseDouble(table_lnv.getValueAt(r, 7).toString()),
//					Double.parseDouble(table_lnv.getValueAt(r, 8).toString()),
//					Double.parseDouble(table_lnv.getValueAt(r, 10).toString()));
			
		}
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnTinhLuong)) {
			try {
				addTinhLuong();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			txtMa.setText(autoMaHD());
		}
		else if(o.equals(btnCapNhat)) {
			updateLuongNhanVien();
			txtMa.setText(autoMaHD());
		}
		else if(o.equals(btnXoa)) {
			deleteCongNhan();
			txtMa.setText(autoMaHD());
		}
		else if(o.equals(btnTaoMoi)) {
			reset();
			txtMa.setText(autoMaHD());
		}
	}
	public double tinhBHXH(double a) {
		return a * 0.08;
	}
	public boolean regex() {
		if(txtPhat.getText().isEmpty()) {
			txtPhat.setText("0.0");
			return false;
		}
		else if(Double.parseDouble(txtPhat.getText()) < 0) {
			JOptionPane.showMessageDialog(this, "Phạt không được nhập bé hơn 0!");
			txtPhat.requestFocus();
			txtPhat.selectAll();
			return false;
		}
		return true;
	}
	// load bang cham cong

	public int demSoNgayCong(String ma, int month, int year) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<ChamCong_NhanVien> l = cclnv_dao.getAllBangChamCong();
		int so = 0;
		for (ChamCong_NhanVien c : l) {
			String s1 = sdf.format(c.getNgayChamCong());
			java.util.Date date1 = null;
			try {
				date1 = sdf.parse(s1);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			if(ma.equals(c.getNhanVien().getMaNV())) {
				if(date1.getMonth()+1 == month && date1.getYear()+1900 == year) {
					so++;
				}
			}
		}
		return so;
	}
	public void addTinhLuong() throws ParseException {
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		String mal = txtMa.getText();
		double luong = 0;
		int row = table_cc.getSelectedRow();
		String ma = table_cc.getValueAt(row, 0).toString();
		int snc = demSoNgayCong(ma, Integer.parseInt(cboThang.getSelectedItem().toString()), Integer.parseInt(cboNam.getSelectedItem().toString()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		double thuong;
		double phat = Double.parseDouble(txtPhat.getText());
		double phuCap;
		NhanVien nv = new NhanVien(table_cc.getValueAt(row, 0).toString());
		String sn = sdf.format(cboNgay.getDate());
		Date da = Date.valueOf(sn);
		double l = Double.parseDouble(cboLuong.getSelectedItem().toString());
		if(snc > 25) {
			thuong = 500000;
			phuCap = 500000;
		}
		else {
			thuong = 0;
			phuCap = 0;
		}
		
		if(txtPhat.getText() != "0") {
			luong = l / 26 * snc + phuCap + thuong;
		}
		else {
			luong = l / 26 * snc + phuCap + thuong - phat;
		}
		double bhxh = luong*0.08;
		luong = luong - bhxh;
		LuongNhanVien cn = new LuongNhanVien(mal, phuCap, thuong, phat, bhxh, snc, luong, nv, da);
		LuongNhanVien_DAO dao = new LuongNhanVien_DAO();
		if(regex()) {
			if(dao.insert(cn)) {
				tableModel_lnv.addRow(new Object[] {table_cc.getValueAt(row, 0).toString(), table_cc.getValueAt(row, 1).toString(), cboMaPhong.getSelectedItem().toString(), cboPhong.getSelectedItem().toString(),
						cn.getMaLNV(), currencyVN.format(cn.getPhuCap()), currencyVN.format(cn.getThuong()),
						currencyVN.format(cn.getPhat()), currencyVN.format(cn.getBhxh()), cn.getSoNgayCong(), currencyVN.format(cn.getTongLuong()), f.format(cn.getThangLuong())});
				JOptionPane.showMessageDialog(this, "Thêm thành công");
				tableModel_cc.removeRow(row);
				lblTongLuong.setText(currencyVN.format(luong)+"");

			}else {
				JOptionPane.showMessageDialog(this, "Không thêm được");
			}
		}
	}
	// load luong
	public void loadLuong() {
		try {
			tableModel_lnv.setRowCount(0);
			List<LuongNhanVien> l = lnv_dao.getallLuongNhanVien();
			for (LuongNhanVien c : l) {
				tableModel_lnv.addRow(new Object[] {
						c.getNhanVien().getMaNV(), c.getNhanVien().getHoTen(), c.getNhanVien().getPhong().getMaPB(), c.getNhanVien().getPhong().getTenPhong(),
						c.getMaLNV(), c.getPhuCap(), c.getThuong(), c.getPhat(), c.getBhxh(), c.getSoNgayCong(),c.getTongLuong()
				});
			}
			table_lnv.setModel(tableModel_lnv);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// load du lieu len table
	public void loadDataFromTable() throws SQLException, ClassNotFoundException {
		NhanVien_DAO dao = new NhanVien_DAO();
		List<NhanVien> list = dao.findAll();
		tableModel_cc.setRowCount(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (NhanVien nv : list) {
			tableModel_cc.addRow(new Object[] {nv.getMaNV(), nv.getHoTen(), nv.isPhai() == 1 ? "Nam" : "Nữ",
					sdf.format(nv.getNgaySinh()), nv.getDiaChi(), nv.getEmail(), nv.getPhone() ,
					sdf.format(nv.getNgayThamGiaCongTac()), nv.getTrinhDoChuyenMon(), nv.getKinhNghiem(), nv.getChucVu(), nv.getPhong().getMaPB()});
		}
		table_cc.setModel(tableModel_cc);
	}
	//
	public void updateLuongNhanVien() {
		try {
			LuongNhanVien cn = new LuongNhanVien();
			cn.setMaLNV(txtMa.getText());
			cn.setPhat(Double.parseDouble(txtPhat.getText()));
			double luong = Double.parseDouble(lblTongLuong.getText());
			double phat = Double.parseDouble(txtPhat.getText());
			double bhxh = luong * 0.08;
			luong = luong - phat;
			cn.setTongLuong(luong);
			cn.setBhxh(bhxh);
			LuongNhanVien_DAO dao = new LuongNhanVien_DAO();
			int row = table_lnv.getSelectedRow();
			if(row >= 0) {
				if(regex()) {
					if(dao.update(cn)) {
						table_lnv.setValueAt(txtPhat.getText(), row, 7);
						table_lnv.setValueAt(bhxh, row, 8);
						table_lnv.setValueAt(luong, row, 10);
						JOptionPane.showMessageDialog(this, "Cập nhật thành công");
						reset();
					}else {
						JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void deleteCongNhan() {
		try {
			LuongNhanVien_DAO dao = new LuongNhanVien_DAO();
			int row = table_lnv.getSelectedRow();
			if(row >= 0) {
				if(dao.delete(txtMa.getText())) {
					tableModel_lnv.removeRow(row);
					JOptionPane.showMessageDialog(this, "Xóa thành công");
					reset();
				}
				else {
					JOptionPane.showMessageDialog(this, "Xóa không thành công!");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void reset() {
		txtPhat.setText("0");
	}


	public class NhanVienMouse implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			cboNam.setSelectedItem(LocalDate.now().getYear()+"");
			cboThang.setSelectedItem((LocalDate.now().getMonthValue())+"");
			int row = table_cc.getSelectedRow();
			cboChucVu.setSelectedItem(tableModel_cc.getValueAt(row, 10));
			
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
}
