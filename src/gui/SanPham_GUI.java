package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connect.connectDB;
import dao.HopDong_DAO;
import dao.SanPham_DAO;
import entity.CongNhan;
import entity.HopDong;
import entity.SanPham;
import helper.ImageHepler;

import javax.swing.DefaultComboBoxModel;

public class SanPham_GUI extends JPanel implements ActionListener, MouseListener{
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtDonGia;
	private JTextField txtDonViTinh;
	private JTable table;
	private JTextField txtTimKiem;
	private byte[] persionalImage;
	private JComboBox cboSoCongDoan;
	private JComboBox cboChatLieu;
	private JComboBox cboMauSac;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoa;
	private JButton btnTaoMoi;
	/**
	 * Create the panel.
	 */
	private DefaultTableModel tableModel;
	private JButton btnTimKiem;
	private JButton btnChonAnh;
	private JButton btnTaoMoiBang;
	private JLabel lblImage;
	private JComboBox cboKichThuoc;
	private SanPham_DAO sp_dao;
	private JFileChooser chooser;
	private JLabel lblTrangThai;
	private JComboBox cboTrangThai;
	private JButton btnXem;
	private JButton btnIn;
	private JTable table_HD;
	private DefaultTableModel tableModel_HD;
	private HopDong_DAO hd_dao;
	public SanPham_GUI() {
		connectDB.getInstance().connect();
		sp_dao = new SanPham_DAO();
		hd_dao = new HopDong_DAO();
		setLayout(null);

		JLabel lbl_tt = new JLabel("TH\u00D4NG TIN S\u1EA2N PH\u1EA8M");
		lbl_tt.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_tt.setBounds(720, 20, 299, 54);
		add(lbl_tt);

		JLabel lblMa = new JLabel("M\u00E3 s\u1EA3n ph\u1EA9m:");
		lblMa.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMa.setBounds(564, 71, 111, 31);
		add(lblMa);

		JLabel lblTen = new JLabel("T\u00EAn s\u1EA3n ph\u1EA9m: ");
		lblTen.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTen.setBounds(564, 129, 111, 21);
		add(lblTen);

		JLabel lblChatLieu = new JLabel("Ch\u1EA5t li\u1EC7u:");
		lblChatLieu.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblChatLieu.setBounds(889, 71, 82, 31);
		add(lblChatLieu);

		JLabel lblDonGia = new JLabel("\u0110\u01A1n gi\u00E1:");
		lblDonGia.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblDonGia.setBounds(889, 124, 72, 31);
		add(lblDonGia);

		txtMa = new JTextField();
		txtMa.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtMa.setBounds(694, 84, 166, 19);
		txtMa.setText(autoMaHD());
		txtMa.setEnabled(false);
		add(txtMa);
		txtMa.setColumns(10);

		txtTen = new JTextField();
		txtTen.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTen.setBounds(694, 131, 166, 19);
		add(txtTen);
		txtTen.setColumns(10);

		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtDonGia.setBounds(978, 131, 166, 19);
		add(txtDonGia);
		txtDonGia.setColumns(10);

		String cl [] = {"Da tổng hợp", "Da PU", "Da bò", "Vải", "Cao su"};
		cboChatLieu = new JComboBox(cl);
		cboChatLieu.setFont(new Font("Times New Roman", Font.BOLD, 13));
		cboChatLieu.setBounds(978, 78, 166, 21);
		add(cboChatLieu);

		JLabel lblDonViTinh = new JLabel("\u0110\u01A1n v\u1ECB t\u00EDnh:");
		lblDonViTinh.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblDonViTinh.setBounds(1180, 76, 98, 21);
		add(lblDonViTinh);

		JLabel lblSoCongDoan = new JLabel("S\u1ED1 c\u00F4ng \u0111o\u1EA1n:");
		lblSoCongDoan.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblSoCongDoan.setBounds(1180, 124, 111, 31);
		add(lblSoCongDoan);

		JLabel lblKichThuoc = new JLabel("K\u00EDch th\u01B0\u1EDBc:");
		lblKichThuoc.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblKichThuoc.setBounds(564, 183, 98, 21);
		add(lblKichThuoc);

		JLabel lblMauSac = new JLabel("M\u00E0u s\u1EAFc:");
		lblMauSac.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMauSac.setBounds(889, 178, 72, 31);
		add(lblMauSac);

		txtDonViTinh = new JTextField();
		txtDonViTinh.setText("Đôi");
		txtDonViTinh.setEnabled(false);
		txtDonViTinh.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtDonViTinh.setBounds(1331, 78, 166, 19);
		add(txtDonViTinh);
		txtDonViTinh.setColumns(10);

		String scd [] = {"1", "2", "3", "4", "5"};
		cboSoCongDoan = new JComboBox(scd);
		cboSoCongDoan.setFont(new Font("Times New Roman", Font.BOLD, 13));
		cboSoCongDoan.setBounds(1331, 131, 166, 21);
		cboSoCongDoan.setSelectedItem("5");
		add(cboSoCongDoan);

		String kt[] = {"35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45"};
		cboKichThuoc = new JComboBox(kt);
		cboKichThuoc.setFont(new Font("Times New Roman", Font.BOLD, 13));
		cboKichThuoc.setBounds(694, 185, 166, 21);
		add(cboKichThuoc);

		String ms [] = {"Xanh đen", "Xanh", "Đen", "Vàng", "Đỏ", "Đen Vàng", "Trắng"};
		cboMauSac = new JComboBox(ms);
		cboMauSac.setFont(new Font("Times New Roman", Font.BOLD, 13));
		cboMauSac.setBounds(978, 185, 166, 21);
		add(cboMauSac);

		JLabel lbl_ttsp = new JLabel("B\u1EA2NG TH\u00D4NG TIN S\u1EA2N PH\u1EA8M");
		lbl_ttsp.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_ttsp.setBounds(664, 500, 307, 36);
		add(lbl_ttsp);

		btnThem = new JButton("Th\u00EAm");
		btnThem.setIcon(new ImageIcon("image/add.png"));
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnThem.setBounds(564, 250, 128, 44);
		add(btnThem);

		btnCapNhat = new JButton("C\u1EADp nh\u1EADt");
		btnCapNhat.setIcon(new ImageIcon("image/exchange.png"));
		btnCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCapNhat.setBounds(720, 251, 147, 42);
		add(btnCapNhat);

		btnXoa = new JButton("X\u00F3a");
		btnXoa.setIcon(new ImageIcon("image/delete.png"));
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnXoa.setBounds(889, 250, 107, 44);
		add(btnXoa);

		btnTaoMoi = new JButton("T\u1EA1o m\u1EDBi");
		btnTaoMoi.setIcon(new ImageIcon("image/create.png"));
		btnTaoMoi.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnTaoMoi.setBounds(1029, 251, 152, 42);
		add(btnTaoMoi);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 546, 1452, 161);
		add(scrollPane);

		table = new JTable();
		String header []  = {"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Số công đoạn", "Chất liệu", "Kích thước", "Màu sắc", "Đơn giá"};
		tableModel = new DefaultTableModel(header, 0);

		table.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		scrollPane.setViewportView(table);

		JLabel lblTimKiem = new JLabel("Nhập để tìm kiếm:");
		lblTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTimKiem.setBounds(45, 717, 141, 31);
		add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTimKiem.setBounds(199, 721, 1079, 27);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);

		btnTimKiem = new JButton("");

		btnTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnTimKiem.setBounds(1294, 714, 111, 36);
		add(btnTimKiem);
		btnTimKiem.setIcon(new ImageIcon("image/search.png"));

		lblImage = new JLabel("");
		lblImage.setBounds(45, 20, 460, 290);
		lblImage.setBorder(BorderFactory.createLineBorder(Color.black));
		add(lblImage);
		ImageIcon eee = new ImageIcon("image/no.jpg");
		Image i = ImageHepler.resize(eee.getImage(), 460, 290);
		ImageIcon ee = new ImageIcon(i);
		lblImage.setIcon(ee);

		btnChonAnh = new JButton("Chọn ảnh");
		btnChonAnh.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnChonAnh.setBounds(221, 320, 122, 36);
		add(btnChonAnh);

		btnTaoMoiBang = new JButton("");
		btnTaoMoiBang.setBounds(1415, 714, 82, 36);
		add(btnTaoMoiBang);
		btnTaoMoiBang.setIcon(new ImageIcon("image/rs.png"));
		//
		btnThem.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnChonAnh.addActionListener(this);
		btnXoa.addActionListener(this);
		btnTaoMoi.addActionListener(this);
		btnTaoMoiBang.addActionListener(this);
		btnTimKiem.addActionListener(this);
		table.addMouseListener(this);
		//
		btnThem.setMnemonic(KeyEvent.VK_A);
		btnXoa.setMnemonic(KeyEvent.VK_D);
		btnCapNhat.setMnemonic(KeyEvent.VK_U);
		btnTaoMoi.setMnemonic(KeyEvent.VK_R);
		btnTimKiem.setMnemonic(KeyEvent.VK_S);
		btnChonAnh.setMnemonic(KeyEvent.VK_F);
		btnTaoMoiBang.setMnemonic(KeyEvent.VK_N);

		lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTrangThai.setBounds(1180, 183, 98, 20);
		add(lblTrangThai);

		String tt [] = "Hoàn thành;Chưa hoàn thành".split(";");
		cboTrangThai = new JComboBox(tt);
		cboTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Đang tiến hành", "Hoàn thành", "Chưa hoàn thành"}));
		cboTrangThai.setBounds(1331, 185, 166, 21);
		add(cboTrangThai);
		

		btnXem = new JButton("Xem Sản Phẩm Hoàn Thành");
		btnXem.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnXem.setBounds(1204, 251, 194, 46);
		add(btnXem);
		btnXem.addActionListener(this);

		btnIn = new JButton("IN");
		btnIn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnIn.setBounds(1415, 250, 85, 44);
		add(btnIn);
		btnIn.addActionListener(this);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(365, 342, 1129, 159);
		add(scrollPane_2);

		table_HD = new JTable();
		table_HD.setModel(tableModel_HD = new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null, null, null},
				},
				new String[] {
						"M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "\u0110\u01A1n v\u1ECB t\u00EDn", "S\u1ED1 c\u00F4ng \u0111o\u1EA1n", "Ch\u1EA5t li\u1EC7u", "K\u00EDch th\u01B0\u1EDBc", "M\u00E0u s\u1EAFc", "\u0110\u01A1n gi\u00E1", "Tr\u1EA1ng th\u00E1i", "T\u1ED5ng s\u1ED1 l\u01B0\u1EE3ng"
				}
				));
		scrollPane_2.setViewportView(table_HD);
		//
		try {
			loadDataFromTable();
			loadDataFromTableHD();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			int row = table.getSelectedRow();
			txtMa.setText(table.getValueAt(row, 0).toString());
			txtTen.setText(table.getValueAt(row, 1).toString());
			txtDonViTinh.setText(table.getValueAt(row, 2).toString());
			cboSoCongDoan.setSelectedItem(table.getValueAt(row, 3).toString());
			cboChatLieu.setSelectedItem(table.getValueAt(row, 4).toString());
			cboKichThuoc.setSelectedItem(table.getValueAt(row, 5).toString());
			cboMauSac.setSelectedItem(table.getValueAt(row, 6).toString());
			txtDonGia.setText(table.getValueAt(row, 7).toString());
			cboTrangThai.setSelectedItem(table.getValueAt(row, 8));
			if(row >= 0) {
				String id = (String) table.getValueAt(row, 0);
				SanPham_DAO dao = new SanPham_DAO();
				SanPham nv = dao.findByID(id);
				if(nv != null) {
					if(nv.getHinhAnh() != null) {
						Image img = ImageHepler.crateImageFromByteArray(nv.getHinhAnh(), "jpg");
						lblImage.setIcon(new ImageIcon(img));
						persionalImage = nv.getHinhAnh();
					}
					else {
						persionalImage = nv.getHinhAnh();
						ImageIcon eee = new ImageIcon("image/no.jpg");
						Image i = ImageHepler.resize(eee.getImage(), 460, 290);
						ImageIcon ee = new ImageIcon(i);
						lblImage.setIcon(ee);
					}
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
				addSanPham();
				HopDong_GUI hd;
				hd = new HopDong_GUI();
				hd.loadData();
				txtMa.setText(autoMaHD());
			}
		}
		// chon file
		else if(o.equals(btnChonAnh)) {
			chonFile();
		}
		// cap nhat
		else if(o.equals(btnCapNhat)) {
			int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật không?", "Chú Ý", JOptionPane.YES_NO_OPTION);
			if(luaChon == JOptionPane.YES_OPTION) {
				updateSanPham();
				reset();
				txtMa.setText(autoMaHD());
			}
		}
		// xoa
		else if(o.equals(btnXoa)) {
			int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?", "Chú Ý", JOptionPane.YES_NO_OPTION);
			if(luaChon == JOptionPane.YES_OPTION) {
				deleteSanPham();
				HopDong_GUI hd;
				hd = new HopDong_GUI();
				hd.loadData();
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
			searchSanPham();
		}
		else if(o.equals(btnTaoMoiBang)) {
			try {
				loadDataFromTable();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(o.equals(btnXem)) {
			new XemSanPhamHoanThanh_GUI().setVisible(true);
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
	}
	// ham chon anh
	public void chonFile() {
		chooser = new JFileChooser();
		chooser.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return "Image File (*.jpg)";
			}

			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				if(f.isDirectory()) {
					return true;
				}else {
					return f.getName().toLowerCase().endsWith(".jpg"); // dinh dang file .jpg
				}
			}
		});
		if(chooser.showOpenDialog(this) == JFileChooser.CANCEL_OPTION) {
			return;
		}
		File file = chooser.getSelectedFile(); // lay duong dan file anh duoc chon
		try {
			ImageIcon icon = new ImageIcon(file.getPath()); // truyen duong dan file anh duoc chon
			Image img = ImageHepler.resize(icon.getImage(), 460, 290); // chinh kich thuoc anh
			ImageIcon ic = new ImageIcon(img);
			lblImage.setIcon(ic); // dua anh len giao dien
			persionalImage = ImageHepler.tobyArray(img, "jpg"); // chuyen hinh anh thanh mang de luu vao csdl
		} catch (Exception e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Lỗi chọn ảnh");
			e2.printStackTrace();
		}
	}
	// load du lieu len table san pham
	public void loadDataFromTable() throws SQLException, ClassNotFoundException {
		SanPham_DAO dao = new SanPham_DAO();
		List<SanPham> list = dao.findAll();
		tableModel.setRowCount(0);
		for (SanPham nv : list) {
			if(nv.getTrangThai().equals("Chưa hoàn thành")) {
				tableModel.addRow(new Object[] {nv.getMaSP(), nv.getTenSanPham(),
						nv.getDonViTinh(), nv.getSoCongDoan(), nv.getChatLieu(),
						nv.getKichThuoc(), nv.getMauSac(), nv.getDonGia()});
			}
		}
		table.setModel(tableModel);
	}
	// load du lieu len table san pham HD
	public void loadDataFromTableHD() throws SQLException, ClassNotFoundException {
		SanPham_DAO dao = new SanPham_DAO();
		List<SanPham> list = dao.findAll();
		tableModel_HD.setRowCount(0);
		for (SanPham nv : list) {
			if(nv.getTrangThai().equals("Đang tiến hành")) {
				tableModel_HD.addRow(new Object[] {nv.getMaSP(), nv.getTenSanPham(),
						nv.getDonViTinh(), nv.getSoCongDoan(), nv.getChatLieu(),
						nv.getKichThuoc(), nv.getMauSac(), nv.getDonGia(), nv.getTrangThai(), demSoLuongSanPham(nv.getMaSP())});
			}
		}
		table_HD.setModel(tableModel_HD);
	}
	// them nhan vien
	public void addSanPham() {
		try {
			SanPham cn = new SanPham();
			cn.setMaSP(txtMa.getText().trim());
			cn.setTenSanPham(txtTen.getText().trim());
			cn.setDonViTinh(txtDonViTinh.getText());
			cn.setSoCongDoan(Integer.parseInt(cboSoCongDoan.getSelectedItem().toString().trim()));
			cn.setChatLieu(cboChatLieu.getSelectedItem().toString().trim());
			cn.setKichThuoc(Integer.parseInt(cboKichThuoc.getSelectedItem().toString().trim()));
			cn.setMauSac(cboMauSac.getSelectedItem().toString().trim());
			cn.setTrangThai(cboTrangThai.getSelectedItem().toString().trim());
			try {
				cn.setDonGia(Double.parseDouble(txtDonGia.getText()));
				if(cn.getDonGia() <= 0){
					JOptionPane.showMessageDialog(this, "Phải nhập số nguyên dương!");
					txtDonGia.selectAll();
					txtDonGia.requestFocus();
					return;
				}
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "Đơn giá phải nhập số nguyên dương!");
				txtDonGia.requestFocus();
				txtDonGia.selectAll();
			}
			cn.setHinhAnh(persionalImage);
			SanPham_DAO dao = new SanPham_DAO();
			if(regex()) {
				if(dao.insert(cn)) {
					tableModel.addRow(new Object[] {cn.getMaSP(), cn.getTenSanPham(), cn.getDonViTinh(),
							cn.getSoCongDoan(),cn.getChatLieu(), cn.getKichThuoc(), cn.getMauSac(), cn.getDonGia()});
				}else {
					JOptionPane.showMessageDialog(this, "Không thêm được");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void updateSanPham() {
		try {
			SanPham cn = new SanPham();
			cn.setMaSP(txtMa.getText().trim());
			cn.setTenSanPham(txtTen.getText().trim());
			cn.setDonViTinh(txtDonViTinh.getText());
			cn.setSoCongDoan(Integer.parseInt(cboSoCongDoan.getSelectedItem().toString().trim()));
			cn.setChatLieu(cboChatLieu.getSelectedItem().toString().trim());
			cn.setKichThuoc(Integer.parseInt(cboKichThuoc.getSelectedItem().toString().trim()));
			cn.setMauSac(cboMauSac.getSelectedItem().toString().trim());
			cn.setTrangThai(cboTrangThai.getSelectedItem().toString().trim());
			try {
				cn.setDonGia(Double.parseDouble(txtDonGia.getText().trim()));
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "Đơn giá phải nhập số nguyên dương!");
				txtDonGia.requestFocus();
				txtDonGia.selectAll();
			}
			cn.setHinhAnh(persionalImage);
			SanPham_DAO dao = new SanPham_DAO();
			int row = table.getSelectedRow();
			if(row >= 0) {
				if(dao.update(cn)) {
					table.setValueAt(txtTen.getText(), row, 1);
					table.setValueAt(txtDonViTinh.getText(), row, 2);
					table.setValueAt(cboSoCongDoan.getSelectedItem(), row, 3);
					table.setValueAt(cboChatLieu.getSelectedItem(), row, 4);
					table.setValueAt(cboKichThuoc.getSelectedItem(), row, 5);
					table.setValueAt(cboMauSac.getSelectedItem(), row, 6);
					table.setValueAt(txtDonGia.getText(), row, 7);
					table.setValueAt(cboTrangThai.getSelectedItem(), row, 8);
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
	public void deleteSanPham() {
		try {
			SanPham_DAO dao = new SanPham_DAO();
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
			e.printStackTrace();
		}
	}
	// nut tao moi
	public void reset() {
		txtMa.setText("");
		txtTen.setText("");
		txtDonGia.setText("");
		persionalImage = null;
		ImageIcon e = new ImageIcon("image/no.jpg");
		Image i = ImageHepler.resize(e.getImage(), 460, 290);
		ImageIcon ee = new ImageIcon(i);
		lblImage.setIcon(ee);
	}
	// tim kiem bang so dien thoai
	public void searchSanPham() {
		String tim = txtTimKiem.getText();
		if(tim.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập thông tin để tìm kiếm!");
			return;
		}
		else if(tim.length() > 0 && !tim.matches("\\d{10}")) {
			JOptionPane.showMessageDialog(null, "Bạn vui lòng nhập vào số điện thoại cần tìm!");
			return;
		}
		else if(sp_dao.findSanPhamByTagName(tim).size() == 0) {
			JOptionPane.showMessageDialog(null, "Không tìm thấy số điện thoại!");
		}
		else {
			for (SanPham b : sp_dao.findSanPhamByTagName(tim)) {
				tableModel.setRowCount(0);
				tableModel.addRow(new Object[]{b.getMaSP(), b.getTenSanPham(),
						b.getDonViTinh(), b.getSoCongDoan(), b.getChatLieu(),
						b.getKichThuoc(), b.getMauSac(), b.getDonGia(), b.getHinhAnh()
				});
			}
			table.setModel(tableModel);
		}
	}
	// regex
	public boolean regex() {
		double donGia = Double.parseDouble(txtDonGia.getText().trim());
		if(txtTen.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống!");
			txtTen.requestFocus();
			return false;
		}
		else if(txtTen.getText().length() > 0 && !txtTen.getText().matches("[A-Z][a-z]*(\\s[A-Z][a-z]*)*")) {
			txtTen.requestFocus();
			txtTen.selectAll();
			JOptionPane.showMessageDialog(this, "Tên phải viết hoa đầu từ!");
			return false;
		}
		else if(donGia <= 0) {
			JOptionPane.showMessageDialog(this, "Đơn giá phải lớn hơn 0!");
			txtDonGia.selectAll();
			txtDonGia.requestFocus();
			return false;
		}
		else if(txtDonGia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Đơn giá không được để trổng!");
			txtDonGia.requestFocus();
			return false;
		}
		else if((txtDonGia.getText().length() > 0) && !(txtDonGia.getText().matches("^[0-9]+"))) {
			JOptionPane.showMessageDialog(this, "Đơn giá phải nhập số nguyên dương!");
			txtDonGia.requestFocus();
			txtDonGia.selectAll();
			return false;
		}

		return true;
	}
	public String autoMaHD() {
		String str;
		int ma = sp_dao.getMaHd();
		if (ma < 100)
			str = "SP0000" + ma;
		else if (ma < 1000)
			str = "SP000" + ma;
		else if (ma < 10000)
			str = "SP00" + ma;
		else if (ma < 100000)
			str = "SP0" + ma;
		else
			str = "SP" + ma;
		return str;

	}
	// kiem tra san pham dang tien hanh
	public void kiemTraSanPhamDangTienHanh() throws ClassNotFoundException, SQLException {
		List<HopDong> l = hd_dao.findAll();
		List<SanPham> s = sp_dao.findAll();
		for (HopDong d : l) {
			if(d.getTrangThai().equals("Đang tiến hành")) {
				for (SanPham p : s) {
					if(d.getSanPham().getMaSP().equals(p.getMaSP())) {
						p.setTrangThai("Đang tiến hành");
						sp_dao.updateTrangThai(p);
					}
				}
			}
		}
	}
	public int demSoLuongSanPham(String ma) {
		int sum = 0;
		try {
			List<HopDong> l = hd_dao.findAll();
			List<SanPham> j = sp_dao.findAll();
			for (HopDong d : l) {
				if(ma.equals(d.getSanPham().getMaSP())) {
					if(d.getTrangThai().equals("Đang tiến hành")) {
						sum+=d.getSoLuong();
					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}

}
