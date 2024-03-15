package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JFileChooser;

import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.toedter.calendar.JDateChooser;

import connect.connectDB;
import dao.HopDong_DAO;
import dao.SanPham_DAO;
import entity.CongNhan;
import entity.HopDong;
import entity.PhongBan;
import entity.SanPham;
import gui.HopDong_GUI.KeyListerner;
import helper.InBaoCao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.DefaultComboBoxModel;
public class HopDong_GUI extends JPanel implements ActionListener, MouseListener{
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private JTextField txtSoLuong;
	private JTextField txtDonGia;
	private JTable table;
	private JTextField txtTimKiem;
	private JDateChooser txtNgayBD;
	private JDateChooser txtNgayKT;
	private JButton btnCapNhat;
	private JButton btnXoa;
	private JButton btnTaoMoi;
	private JButton btnThem;
	private DefaultTableModel tableModel;
	private JButton btnTimKiem;
	private JLabel lblTongTien;
	private HopDong_DAO hd_dao;
	private SanPham_DAO sp_dao;
	private JComboBox cboMaSanPham;
	private JComboBox cboTenSanPham;
	private JButton btnTaoMoiBang;
	private JButton btnIn;
	private JComboBox cboTrangThai;
	private JButton btnXem;
	private JButton btnTraCuu;

	/**
	 * Create the panel.
	 */
	public HopDong_GUI() {
		connectDB.getInstance().connect();

		hd_dao = new HopDong_DAO();
		sp_dao = new SanPham_DAO();
		setLayout(null);

		JLabel lbl_tt = new JLabel("TH\u00D4NG TIN H\u01A0P \u0110\u1ED2NG");
		lbl_tt.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_tt.setBounds(682, 20, 288, 48);
		add(lbl_tt);

		JLabel lblMa = new JLabel("M\u00E3 h\u1EE3p \u0111\u1ED3ng:");
		lblMa.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMa.setBounds(45, 100, 114, 29);
		add(lblMa);

		JLabel lblTen = new JLabel("T\u00EAn h\u1EE3p \u0111\u1ED3ng:");
		lblTen.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTen.setBounds(45, 165, 104, 29);
		add(lblTen);

		JLabel lblKhachHang = new JLabel("T\u00EAn kh\u00E1ch h\u00E0ng:");
		lblKhachHang.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblKhachHang.setBounds(45, 231, 140, 21);
		add(lblKhachHang);

		JLabel lblSoDienThoai = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i:");
		lblSoDienThoai.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblSoDienThoai.setBounds(45, 287, 114, 19);
		add(lblSoDienThoai);

		JLabel lblMaSanPham = new JLabel("M\u00E3 s\u1EA3n ph\u1EA9m:");
		lblMaSanPham.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMaSanPham.setBounds(574, 165, 114, 29);
		add(lblMaSanPham);

		JLabel lblNgayBD = new JLabel("Ng\u00E0y b\u1EAFt \u0111\u1EA7u:");
		lblNgayBD.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNgayBD.setBounds(574, 227, 119, 29);
		add(lblNgayBD);

		JLabel lblSoLuong = new JLabel("S\u1ED1 l\u01B0\u1EE3ng:");
		lblSoLuong.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblSoLuong.setBounds(1059, 100, 93, 29);
		add(lblSoLuong);

		JLabel lblDonGia = new JLabel("\u0110\u01A1n gi\u00E1 s\u1EA3n ph\u1EA9m: ");
		lblDonGia.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblDonGia.setBounds(1059, 165, 148, 29);
		add(lblDonGia);

		JLabel lblNgayKT = new JLabel("Ng\u00E0y k\u1EBFt th\u00FAc: ");
		lblNgayKT.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNgayKT.setBounds(1059, 227, 126, 29);
		add(lblNgayKT);

		txtMa = new JTextField();
		txtMa.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtMa.setBounds(195, 106, 288, 19);
		txtMa.setText(autoMaHD());
		txtMa.setEnabled(false);
		add(txtMa);
		txtMa.setColumns(10);

		txtTen = new JTextField();
		txtTen.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTen.setBounds(195, 171, 288, 19);
		add(txtTen);
		txtTen.setColumns(10);

		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTenKH.setBounds(195, 233, 288, 19);
		add(txtTenKH);
		txtTenKH.setColumns(10);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtSDT.setBounds(195, 288, 288, 19);
		add(txtSDT);
		txtSDT.setColumns(10);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtSoLuong.setBounds(1209, 106, 288, 19);
		add(txtSoLuong);

		txtSoLuong.addKeyListener(new KeyListerner());

		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtDonGia.setBounds(1209, 171, 288, 19);
		add(txtDonGia);
		txtDonGia.setEnabled(false);


		JLabel lbl_l = new JLabel("T\u1ED5ng ti\u1EC1n thanh to\u00E1n:");
		lbl_l.setForeground(new Color(255, 0, 0));
		lbl_l.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lbl_l.setBounds(560, 288, 164, 29);
		add(lbl_l);

		lblTongTien = new JLabel("");
		lblTongTien.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTongTien.setForeground(new Color(255, 0, 0));
		lblTongTien.setBounds(734, 287, 269, 19);
		add(lblTongTien);

		//

		txtNgayBD = new JDateChooser();
		txtNgayBD.setBounds(715, 232, 288, 20);
		add(txtNgayBD);

		txtNgayKT = new JDateChooser();
		txtNgayKT.setBounds(1209, 231, 288, 20);
		add(txtNgayKT);

		JLabel lbl_tt_tthd = new JLabel("B\u1EA2NG TH\u00D4NG TIN H\u1EE2P \u0110\u1ED2NG");
		lbl_tt_tthd.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_tt_tthd.setBounds(666, 410, 307, 36);
		add(lbl_tt_tthd);

		btnCapNhat = new JButton("C\u1EADp nh\u1EADt");
		btnCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCapNhat.setBounds(574, 358, 166, 42);
		add(btnCapNhat);

		btnXoa = new JButton("X\u00F3a");
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnXoa.setBounds(750, 358, 112, 42);
		add(btnXoa);

		btnTaoMoi = new JButton("T\u1EA1o m\u1EDBi");
		btnTaoMoi.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnTaoMoi.setBounds(872, 358, 164, 42);
		add(btnTaoMoi);

		btnThem = new JButton("Th\u00EAm");
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnThem.setBounds(413, 358, 143, 42);
		add(btnThem);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 456, 1452, 260);
		add(scrollPane);

		String header [] = {"Mã hợp đồng", "Tên hợp đồng", "Ngày bắt đầu", "Ngày kết thúc",
				"Số lượng", "Đơn giá sản phẩm", "Sản phẩm", "Tên khách hàng", "Số điện thoại", "Tổng tiền", "Trạng thái"};
		tableModel = new DefaultTableModel(header, 0);
		table = new JTable();

		table.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		scrollPane.setViewportView(table);

		JLabel lblTimKiem = new JLabel("Nhập để tìm kiếm:");
		lblTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTimKiem.setBounds(45, 730, 148, 21);
		add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 17));
		txtTimKiem.setBounds(188, 726, 1105, 29);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);

		btnTimKiem = new JButton();
		btnTimKiem.setIcon(new ImageIcon("image/search.png"));
		btnTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnTimKiem.setBounds(1303, 722, 85, 36);
		add(btnTimKiem);

		cboMaSanPham = new JComboBox();
		cboMaSanPham.addItem("");
		cboMaSanPham.setFont(new Font("Times New Roman", Font.BOLD, 13));
		cboMaSanPham.setBounds(715, 171, 288, 21);
		add(cboMaSanPham);
		List<SanPham> p;
		try {
			p = sp_dao.findAll();
			for (SanPham s : p) {
				cboMaSanPham.addItem(s.getMaSP());
			}
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		cboMaSanPham.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				List<SanPham> p;
				try {
					p = sp_dao.findAll();
					for (SanPham s : p) {
						if(e.getStateChange() == ItemEvent.SELECTED) {
							if(!cboMaSanPham.getSelectedItem().toString().equals("d")) {
								if(cboMaSanPham.getSelectedItem().equals(s.getMaSP()))
									txtDonGia.setText(Double.valueOf(s.getDonGia()).toString());
							}
						}
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		cboMaSanPham.setEnabled(false);
		cboMaSanPham.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				List<SanPham> p;
				try {
					p = sp_dao.findAll();
					for (SanPham s : p) {
						if(e.getStateChange() == ItemEvent.SELECTED) {
							if(!cboMaSanPham.getSelectedItem().toString().equals("d")) {
								if(cboMaSanPham.getSelectedItem().equals(s.getMaSP()))
									cboTenSanPham.setSelectedItem(s.getTenSanPham());
							}
						}
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThem.addActionListener(this);
		btnThem.setIcon(new ImageIcon("image/add.png"));
		table.addMouseListener(this);
		btnCapNhat.addActionListener(this);
		btnCapNhat.setIcon(new ImageIcon("image/exchange.png"));
		btnXoa.addActionListener(this);
		btnXoa.setIcon(new ImageIcon("image/delete.png"));
		btnTaoMoi.addActionListener(this);
		btnTaoMoi.setIcon(new ImageIcon("image/create.png"));
		btnTimKiem.addActionListener(this);
		btnThem.setMnemonic(KeyEvent.VK_A);
		btnXoa.setMnemonic(KeyEvent.VK_D);
		btnCapNhat.setMnemonic(KeyEvent.VK_U);
		btnTaoMoi.setMnemonic(KeyEvent.VK_R);
		btnTimKiem.setMnemonic(KeyEvent.VK_S);

		JLabel lblTenSanPham = new JLabel("Tên sản phẩm:");
		lblTenSanPham.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTenSanPham.setBounds(574, 105, 119, 19);
		add(lblTenSanPham);

		cboTenSanPham = new JComboBox();
		cboTenSanPham.addItem("Chọn");
		try {
			p = sp_dao.findAll();
			for (SanPham s : p) {
				cboTenSanPham.addItem(s.getTenSanPham());
			}
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		cboTenSanPham.setBounds(715, 106, 288, 21);
		add(cboTenSanPham);

		btnTaoMoiBang = new JButton(" ");
		btnTaoMoiBang.addActionListener(this);
		btnTaoMoiBang.setIcon(new ImageIcon("image/rs.png"));
		btnTaoMoiBang.setBounds(1409, 722, 85, 36);
		add(btnTaoMoiBang);
		cboTenSanPham.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				List<SanPham> p;
				try {
					p = sp_dao.findAll();
					for (SanPham s : p) {
						if(e.getStateChange() == ItemEvent.SELECTED) {
							if(!cboTenSanPham.getSelectedItem().toString().equals("Chọn")) {
								if(cboTenSanPham.getSelectedItem().equals(s.getTenSanPham()))
									cboMaSanPham.setSelectedItem(s.getMaSP());
							}
						}
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTaoMoiBang.setMnemonic(KeyEvent.VK_N);
		
		btnIn = new JButton("In");
		btnIn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnIn.setBounds(1059, 358, 104, 42);
		add(btnIn);
		btnIn.addActionListener(this);
		
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTrangThai.setBounds(1059, 284, 112, 24);
		add(lblTrangThai);
		
		String header_tt [] = "Chưa hoàn thành;Hoàn thành".split(";");
		cboTrangThai = new JComboBox(header_tt);
		cboTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Đang tiến hành", "Chưa hoàn thành", "Hoàn thành"}));
		cboTrangThai.setBounds(1209, 288, 288, 21);
		add(cboTrangThai);
		
		btnXem = new JButton("Xem hợp đồng đã hoàn thành");
		btnXem.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnXem.setBounds(1194, 360, 194, 42);
		add(btnXem);
		btnXem.addActionListener(this);
		
		btnTraCuu = new JButton("Tra cứu");
		btnTraCuu.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnTraCuu.setBounds(1409, 358, 104, 42);
		add(btnTraCuu);
		btnTraCuu.addActionListener(this);
		//
		loadDataFromTable();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			int row = table.getSelectedRow();
			if(row >= 0) {
				String id = (String) table.getValueAt(row, 0);
				HopDong_DAO dao = new HopDong_DAO();
				HopDong cn = dao.findByID(id);
				if(cn != null) {
					txtMa.setText(cn.getMaHD());
					txtTen.setText(cn.getTenHopDong());
					txtNgayBD.setDate(cn.getNgayBatDau());
					txtNgayKT.setDate(cn.getNgayKetThuc());
					txtSoLuong.setText(table.getValueAt(row, 4).toString());
					txtDonGia.setText(table.getValueAt(row, 5).toString());
					cboMaSanPham.setSelectedItem(cn.getSanPham().getMaSP());
					txtTenKH.setText(cn.getKhachHang());
					txtSDT.setText(cn.getPhone());
					lblTongTien.setText(tongTienThanhToan(Integer.parseInt(txtSoLuong.getText()),
							Double.parseDouble(txtDonGia.getText()))+"");
					cboTrangThai.setSelectedItem(cn.getTrangThai());
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
		// them
		if(o.equals(btnThem)) {
			int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?", "Chú Ý", JOptionPane.YES_NO_OPTION);
			if(luaChon == JOptionPane.YES_OPTION) {
				addHopDong();
				txtMa.setText(autoMaHD());
				SanPham_GUI s = new SanPham_GUI();
				try {
					s.kiemTraSanPhamDangTienHanh();
					s.loadDataFromTable();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		// cap nhat
		else if(o.equals(btnCapNhat)) {
			int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật không?", "Chú Ý", JOptionPane.YES_NO_OPTION);
			if(luaChon == JOptionPane.YES_OPTION) {
				updateHopDong();
				reset();
				txtMa.setText(autoMaHD());
				SanPham_GUI s = new SanPham_GUI();
				try {
					s.kiemTraSanPhamDangTienHanh();
					s.loadDataFromTable();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}
		// xoa
		else if(o.equals(btnXoa)) {
			int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?", "Chú Ý", JOptionPane.YES_NO_OPTION);
			if(luaChon == JOptionPane.YES_OPTION) {
				deleteHopDong();
				txtMa.setText(autoMaHD());
			}
		}
		// tao moi
		else if(o.equals(btnTaoMoi)) {
			reset();
			txtMa.setText(autoMaHD());
		}
		// tim kiem
		else if(o.equals(btnTimKiem)) {
			searchHopDong();
		}
		else if(o.equals(txtSoLuong)) {
			double number = tongTienThanhToan(Integer.parseInt(txtSoLuong.getText()), Double.parseDouble(txtDonGia.getText()));
			lblTongTien.setText(number+ "");
		}
		else if(o.equals(btnIn)) {
			if(table.getRowCount() <= 0)
				return;
			String thongTin = "Danh sách toàn bộ hợp đồng";
			
			MessageFormat header = new MessageFormat(thongTin);
			MessageFormat footer = new MessageFormat("Trang{0, number, integer}");
			try {
				table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		else if(o.equals(btnXem)) {
			new XemHopDongHoanThanh().setVisible(true);
		}
		else if(o.equals(btnTraCuu)) {
			new TraCuuHopDong_GUI().setVisible(true);
		}
	}
	
	// load du lieu len table
	public void loadDataFromTable(){
		try {
			HopDong_DAO dao = new HopDong_DAO();
			List<HopDong> list = dao.findAll();
			tableModel.setRowCount(0);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			for (HopDong cn : list) {
				if(cn.getTrangThai().equals("Đang tiến hành")) {
					tableModel.addRow(new Object[] {cn.getMaHD(), cn.getTenHopDong(),
							sdf.format(cn.getNgayBatDau()), sdf.format(cn.getNgayKetThuc()),
							cn.getSoLuong(), cn.getDonGiaSanPham(), cn.getSanPham().getMaSP(),
							cn.getKhachHang(), cn.getPhone(), cn.getTongTien(), cn.getTrangThai()});
				}
			}
			table.setModel(tableModel);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	// them hop dong
	public void addHopDong() {
		try {
			HopDong cn = new HopDong();
			cn.setMaHD(txtMa.getText());
			cn.setTenHopDong(txtTen.getText());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sn = sdf.format(txtNgayBD.getDate());
			cn.setNgayBatDau(Date.valueOf(sn));
			String ntg = sdf.format(txtNgayKT.getDate());
			cn.setNgayKetThuc(Date.valueOf(ntg));
			cn.setDonGiaSanPham(Double.parseDouble(txtDonGia.getText()));
			cn.setTrangThai(cboTrangThai.getSelectedItem().toString());
			try {
				cn.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "Số lượng phải nhập số nguyên dương!");
				txtSoLuong.selectAll();
				txtSoLuong.requestFocus();
			}
			SanPham sp = new SanPham(cboMaSanPham.getSelectedItem().toString());
			cn.setSanPham(sp);
			cn.setKhachHang(txtTenKH.getText());
			cn.setPhone(txtSDT.getText());
			cn.setTongTien(Double.parseDouble(lblTongTien.getText()));
			HopDong_DAO dao = new HopDong_DAO();
			if(regex()) {
				if(dao.insert(cn)) {
					SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
					tableModel.addRow(new Object[] {cn.getMaHD(), cn.getTenHopDong(),
							s.format(cn.getNgayBatDau()), s.format(cn.getNgayKetThuc()), cn.getSoLuong(), cn.getDonGiaSanPham(),
							cn.getSanPham().getMaSP(), cn.getKhachHang(), cn.getPhone(), cn.getTongTien(), cn.getTrangThai()});
					JOptionPane.showMessageDialog(this, "Thêm thành công");
					reset();
				}else {
					JOptionPane.showMessageDialog(this, "Không thêm được");
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	// cap nhat cong nhan
	public void updateHopDong() {
		try {
			HopDong cn = new HopDong();
			cn.setMaHD(txtMa.getText());
			cn.setTenHopDong(txtTen.getText());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sn = sdf.format(txtNgayBD.getDate());
			cn.setNgayBatDau(Date.valueOf(sn));
			String ntg = sdf.format(txtNgayKT.getDate());
			cn.setNgayKetThuc(Date.valueOf(ntg));
			cn.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
			cn.setDonGiaSanPham(Double.parseDouble(txtDonGia.getText()));
			SanPham p = new SanPham(cboMaSanPham.getSelectedItem().toString());
			cn.setSanPham(p);
			cn.setKhachHang(txtTenKH.getText());
			cn.setPhone(txtSDT.getText());
			cn.setTongTien(Double.parseDouble(lblTongTien.getText()));
			cn.setTrangThai(cboTrangThai.getSelectedItem().toString());
			HopDong_DAO dao = new HopDong_DAO();
			int row = table.getSelectedRow();
			if(row >= 0) {
				if(dao.update(cn)) {
					table.setValueAt(txtTen.getText(), row, 1);
					SimpleDateFormat ns = new SimpleDateFormat("dd/MM/yyyy");
					String ngaySinh = ns.format(txtNgayBD.getDate());
					table.setValueAt(ngaySinh, row, 2);
					String nkt = ns.format(txtNgayKT.getDate());
					table.setValueAt(nkt, row, 3);
					table.setValueAt(txtSoLuong.getText(), row, 4);
					table.setValueAt(txtDonGia.getText(), row, 5);
					table.setValueAt(cboMaSanPham.getSelectedItem(), row, 6);
					table.setValueAt(txtTenKH.getText(), row, 7);
					table.setValueAt(txtSDT.getText(), row, 8);
					table.setValueAt(lblTongTien.getText(), row, 9);
					table.setValueAt(cboTrangThai.getSelectedItem(), row, 10);
					JOptionPane.showMessageDialog(this, "Cập nhật thành công");
				}else {
					JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void deleteHopDong() {
		try {
			HopDong_DAO dao = new HopDong_DAO();
			int row = table.getSelectedRow();
			if(row >= 0) {
				if(dao.delete(txtMa.getText())) {
					tableModel.removeRow(row);
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
	// nut tao moi
	public void reset() {
		txtMa.setText("");
		txtTen.setText("");
		txtNgayBD.setDate(new java.util.Date());
		txtNgayKT.setDate(new java.util.Date());
		txtSoLuong.setText("");
		txtDonGia.setText("");
		txtTenKH.getText();
		txtSDT.getText();
		txtTenKH.setText("");
		txtSDT.setText("");
		lblTongTien.setText("");
	}
	// tim kiem bang so dien thoai
	public void searchHopDong() {
		String tim = txtTimKiem.getText();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(tim.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập thông tin để tìm kiếm!");
			return;
		}
		else if(tim.length() > 0 && !tim.matches("\\d{10}")) {
			JOptionPane.showMessageDialog(null, "Bạn vui lòng nhập vào số điện thoại cần tìm!");
			return;
		}
		else if(hd_dao.findHopDongByTagName(tim).size() == 0) {
			JOptionPane.showMessageDialog(null, "Không tìm thấy số điện thoại!");
		}
		else {
			for (HopDong cn : hd_dao.findHopDongByTagName(tim)) {
				tableModel.setRowCount(0);
				tableModel.addRow(new Object[] {
						cn.getMaHD(), cn.getTenHopDong(),
						sdf.format(cn.getNgayBatDau()), sdf.format(cn.getNgayKetThuc()), cn.getSoLuong(), cn.getDonGiaSanPham(),
						cn.getSanPham().getMaSP(), cn.getKhachHang(), cn.getPhone(), cn.getTongTien(), cn.getTrangThai()
				});
			}
			table.setModel(tableModel);
		}
	}
	// load lai ma san pham khi them
	public void loadData() {
		cboMaSanPham.removeAllItems();
		List<SanPham> p;
		try {
			p = sp_dao.findAll();
			for (SanPham s : p) {
				cboMaSanPham.addItem(s.getMaSP());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// regex
	public boolean regex() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date1 = txtNgayBD.getDate();
		java.util.Date date2 = txtNgayKT.getDate();
		int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
		if(txtTen.getText().isEmpty()) {
			JOptionPane.showConfirmDialog(this, "Tên hợp đồng không được để trống!");
			txtTen.requestFocus();
			return false;
		}
		else if(txtTen.getText().length() > 0 && !(txtTen.getText().matches("[A-Z][a-z]*(\\s[A-Z][a-z]*)*"))) {
			JOptionPane.showMessageDialog(null, "Tên hợp đồng phải viết hoa đầu từ!");
			txtTen.requestFocus();
			txtTen.selectAll();
			return false;
		}
		else if(txtTenKH.getText().isEmpty()) {
			JOptionPane.showConfirmDialog(this, "Tên khách hàng không được để trống!");
			txtTenKH.requestFocus();
			return false;
		}
		else if(txtTenKH.getText().length() > 0 && !(txtTenKH.getText().matches("[A-Z][a-z]*(\\s[A-Z][a-z]*)*"))) {
			JOptionPane.showMessageDialog(null, "Tên khách hàng phải viết hoa đầu từ!");
			txtTenKH.requestFocus();
			txtTenKH.selectAll();
			return false;
		}
		else if(txtSDT.getText().isEmpty()) {
			JOptionPane.showConfirmDialog(this, "Số điện thoại không được để trống!");
			txtSDT.requestFocus();
			return false;
		}
		else if(txtSDT.getText().length() > 0 && !(txtSDT.getText().matches("0\\d{9}"))) {
			JOptionPane.showMessageDialog(null, "Số điện thoại phải nhập đúng 10 chữ số và bắt đầu là 0!");
			txtSDT.requestFocus();
			txtSDT.selectAll();
			return false;
		}
		//  số lượng
		else if(txtSoLuong.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Số lượng không được để trống!");
			txtSoLuong.requestFocus();
			return false;
		}
		else if(soLuong <= 0 || soLuong > 10000) {
			JOptionPane.showMessageDialog(null, "Số lượng phải nhập lớn hơn 0 và bé hơn 10000!");
			txtSoLuong.requestFocus();
			txtSoLuong.selectAll();
			return false;
		}
		else if((date1.getDate() == date2.getDate())) {
			JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu ít nhất 1 ngày!");
			txtNgayKT.requestFocus();
			return false;
		}
		return true;
	}
	public double tongTienThanhToan(int a, double b) {
		return a * b;
	}
	// class hop tro khi nhap so luong thi tu dong tinh tien
	public class KeyListerner implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			try {
				lblTongTien.setText(tongTienThanhToan(Integer.parseInt(txtSoLuong.getText()), Double.parseDouble(txtDonGia.getText()))+"");
			} catch (Exception e2) {
				// TODO: handle exception
				lblTongTien.setText("");
			}

		}

	}
	public void xuatReport() {
		if(txtMa.getText().length() <= 0)
			return;
		else if(table.getRowCount() == 0)
			return;
		try {
			Hashtable map = new Hashtable();
			
			JasperReport report = JasperCompileManager.compileReport("src\\Helper\\HopDong_Report.jrxml");
			map.put("MaHD", txtMa.getText());
			Connection con = connectDB.getConnection();
			JasperPrint p = JasperFillManager.fillReport(report, map, con);
			JasperViewer.viewReport(p, false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void phamVan(){
		InBaoCao x = new InBaoCao();
		try {
			x.xuatHD(hd_dao.findAll());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public String autoMaHD() {
		String str;
		int ma = hd_dao.getMaHd();
		if (ma < 100)
			str = "HD0000" + ma;
		else if (ma < 1000)
			str = "HD000" + ma;
		else if (ma < 10000)
			str = "HD00" + ma;
		else if (ma < 100000)
			str = "HD0" + ma;
		else
			str = "HD" + ma;
		return str;

	}
}
