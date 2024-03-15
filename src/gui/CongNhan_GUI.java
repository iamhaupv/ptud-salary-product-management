package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connect.connectDB;
import dao.CongNhan_DAO;
import dao.PhongBan_DAO;
import entity.CongNhan;
import entity.PhongBan;
import entity.SanPham;
import helper.ImageHepler;

public class CongNhan_GUI extends JPanel implements ActionListener, MouseListener{

	private JTextField txtTimKiem;
	private JTable table;
	private JTextField txtSoDienThoai;
	private JTextField txtDiaChi;
	private JTextField txtMa;
	private JTextField txtTen;
	private CongNhan_DAO cn_dao;
	private PhongBan_DAO pb_dao;
	private JRadioButton rdbNam;
	private JRadioButton rdbNu;
	private JButton btnCapNhat;
	private JButton btnXoa;
	private JButton btnTaoMoi;
	private JButton btnThem;
	private JButton btnChonAnh;
	private JDateChooser txtNgaySinh;
	private JDateChooser txtNgayThamGia;
	private JButton btnTimKiem;
	private JComboBox cboPhong;
	private byte[] persionalImage;
	private JLabel lblImage;
	private DefaultTableModel tableModel;
	private JFileChooser chooser;
	private JTextField txtChucVu;
	private JComboBox cboTenPhong;
	private JButton btnTaoMoiBang;
	private JComboBox cboChuyenMon;
	private JComboBox cboTrangThai;
	private JLabel lblSL;
	private JLabel lblH;
	private JButton btnTraCuu;

	/**
	 * Create the panel.
	 */
	public CongNhan_GUI() {
		//
		connectDB.getInstance().connect();
		cn_dao = new CongNhan_DAO();
		pb_dao = new PhongBan_DAO();
		setLayout(null);
		JLabel lbl_tt = new JLabel("THÔNG TIN CÔNG NHÂN");
		lbl_tt.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_tt.setBounds(750, 20, 299, 30);
		add(lbl_tt);

		lblImage = new JLabel("");
		lblImage.setBounds(45, 20, 233, 297);
		lblImage.setBorder(BorderFactory.createLineBorder(Color.black));
		ImageIcon e = new ImageIcon("image/no.jpg");
		Image i = ImageHepler.resize(e.getImage(), 233, 297);
		ImageIcon ee = new ImageIcon(i);
		lblImage.setIcon(ee);
		add(lblImage);

		JLabel lblMa = new JLabel("M\u00E3 nh\u00E2n vi\u00EAn:");
		lblMa.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMa.setBounds(299, 59, 110, 21);
		add(lblMa);

		JLabel lblTen = new JLabel("Tên công nhân:");
		lblTen.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTen.setBounds(299, 120, 119, 21);
		add(lblTen);

		JLabel lblPhai = new JLabel("Ph\u00E1i:");
		lblPhai.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblPhai.setBounds(708, 54, 53, 30);
		add(lblPhai);

		JLabel lblNgaySinh = new JLabel("Ng\u00E0y sinh:");
		lblNgaySinh.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNgaySinh.setBounds(299, 181, 93, 21);
		add(lblNgaySinh);

		JLabel lblSoDienThoai = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i:");
		lblSoDienThoai.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblSoDienThoai.setBounds(1181, 120, 110, 21);
		add(lblSoDienThoai);

		JLabel lblDiaChi = new JLabel("\u0110\u1ECBa ch\u1EC9:");
		lblDiaChi.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblDiaChi.setBounds(708, 121, 79, 19);
		add(lblDiaChi);

		JLabel lblChucVu = new JLabel("Ch\u1EE9c v\u1EE5:");
		lblChucVu.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblChucVu.setBounds(1181, 174, 70, 35);
		add(lblChucVu);

		JLabel lblNgayThamGia = new JLabel("Ng\u00E0y tham gia:");
		lblNgayThamGia.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNgayThamGia.setBounds(708, 181, 119, 21);
		add(lblNgayThamGia);

		JLabel lblPhong = new JLabel("Mã Phòng:");
		lblPhong.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblPhong.setBounds(1181, 234, 85, 30);
		add(lblPhong);

		btnChonAnh = new JButton("Ch\u1ECDn \u1EA3nh");
		btnChonAnh.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnChonAnh.setBounds(92, 339, 135, 34);
		add(btnChonAnh);

		btnThem = new JButton("Th\u00EAm");
		btnThem.setIcon(new ImageIcon("image/add.png"));
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnThem.setBounds(525, 333, 141, 46);
		add(btnThem);

		btnCapNhat = new JButton("C\u1EADp nh\u1EADt");
		btnCapNhat.setIcon(new ImageIcon("image/exchange.png"));
		btnCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCapNhat.setBounds(708, 333, 162, 46);
		add(btnCapNhat);

		btnXoa = new JButton("X\u00F3a");
		btnXoa.setIcon(new ImageIcon("image/delete.png"));
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnXoa.setBounds(907, 333, 128, 46);
		add(btnXoa);

		btnTaoMoi = new JButton("T\u1EA1o m\u1EDBi");
		btnTaoMoi.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnTaoMoi.setIcon(new ImageIcon("image/create.png"));
		btnTaoMoi.setBounds(1066, 333, 179, 46);
		add(btnTaoMoi);

		txtMa = new JTextField();
		txtMa.setText(autoMaHD());
		txtMa.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtMa.setBounds(419, 61, 196, 19);
		add(txtMa);
		txtMa.setEnabled(false);
		txtMa.setColumns(10);

		txtTen = new JTextField();
		txtTen.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTen.setBounds(419, 120, 196, 19);
		add(txtTen);
		txtTen.setColumns(10);

		rdbNam = new JRadioButton("Nam");
		rdbNam.setSelected(true);
		rdbNam.setFont(new Font("Times New Roman", Font.BOLD, 15));
		rdbNam.setBounds(841, 60, 65, 21);
		add(rdbNam);

		rdbNu = new JRadioButton("N\u1EEF");
		rdbNu.setFont(new Font("Times New Roman", Font.BOLD, 15));
		rdbNu.setBounds(919, 60, 63, 21);
		add(rdbNu);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbNam);
		group.add(rdbNu);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtSoDienThoai.setBounds(1301, 122, 196, 19);
		add(txtSoDienThoai);
		txtSoDienThoai.setColumns(10);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtDiaChi.setBounds(841, 122, 267, 19);
		add(txtDiaChi);
		txtDiaChi.setColumns(10);

		cboPhong = new JComboBox();
		cboPhong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		cboPhong.setBounds(1301, 241, 196, 21);
		add(cboPhong);
		ArrayList<PhongBan> p = pb_dao.getallPhongBan();
		for (PhongBan s : p) {
			cboPhong.addItem(s.getMaPB());
		}
		cboPhong.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				List<PhongBan> p;
				p = pb_dao.findAll();
				for (PhongBan s : p) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
						if(!cboPhong.getSelectedItem().toString().equals("d")) {
							if(cboPhong.getSelectedItem().equals(s.getMaPB()))
								cboTenPhong.setSelectedItem(s.getTenPhong());
						}
					}
				}
			}
		});
		cboPhong.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 432, 1452, 275);
		add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		String header[] = {"Mã công nhân", "Họ tên", "Phái", "Ngày sinh", "Địa chỉ",
				"Số điện thoại", "Ngày tham gia", "Phòng ban", "Chức vụ", "Chuyên môn", "Trạng thái"};
		tableModel = new DefaultTableModel(header, 0);
		scrollPane.setViewportView(table);

		txtNgaySinh = new JDateChooser();
		txtNgaySinh.setBounds(419, 181, 196, 20);
		add(txtNgaySinh);

		txtNgayThamGia = new JDateChooser();
		txtNgayThamGia.setBounds(837, 181, 271, 20);
		add(txtNgayThamGia);


		JLabel lbl_ttnv = new JLabel("BẢNG THÔNG TIN CÔNG NHÂN");
		lbl_ttnv.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_ttnv.setBounds(675, 393, 307, 29);
		add(lbl_ttnv);

		JLabel lblTimKiem = new JLabel("Nhập để tìm kiếm:");
		lblTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTimKiem.setBounds(45, 717, 141, 31);
		add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTimKiem.setBounds(199, 721, 1091, 27);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);

		btnTimKiem = new JButton();
		btnTimKiem.setIcon(new ImageIcon("image/search.png"));
		btnTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnTimKiem.setBounds(1301, 717, 93, 36);
		add(btnTimKiem);
		//
		btnThem.setMnemonic(KeyEvent.VK_A);
		btnXoa.setMnemonic(KeyEvent.VK_D);
		btnCapNhat.setMnemonic(KeyEvent.VK_U);
		btnTaoMoi.setMnemonic(KeyEvent.VK_R);
		btnTimKiem.setMnemonic(KeyEvent.VK_S);
		btnChonAnh.setMnemonic(KeyEvent.VK_F);

		//
		btnThem.addActionListener(this);
		btnChonAnh.addActionListener(this);
		table.addMouseListener(this);
		btnCapNhat.addActionListener(this);
		btnXoa.addActionListener(this);
		btnTaoMoi.addActionListener(this);
		btnTimKiem.addActionListener(this);

		txtChucVu = new JTextField();
		txtChucVu.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtChucVu.setText("Công nhân");
		txtChucVu.setEnabled(false);
		txtChucVu.setBounds(1301, 184, 196, 19);
		add(txtChucVu);
		txtChucVu.setColumns(10);

		cboTenPhong = new JComboBox();
		cboTenPhong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		cboTenPhong.setBounds(1301, 61, 196, 21);
		add(cboTenPhong);
		for (PhongBan s : p) {
			cboTenPhong.addItem(s.getTenPhong());
		}
		cboTenPhong.setEnabled(false);
		cboTenPhong.setSelectedItem("Phòng sản xuất");
		//
		cboTenPhong.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				List<PhongBan> p;
				p = pb_dao.findAll();
				for (PhongBan s : p) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
						if(!cboTenPhong.getSelectedItem().toString().equals("d")) {
							if(cboTenPhong.getSelectedItem().equals(s.getTenPhong()))
								cboPhong.setSelectedItem(s.getMaPB());
						}
					}
				}
			}
		});
		//
		JLabel lblTenPhong = new JLabel("Tên phòng:");
		lblTenPhong.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTenPhong.setBounds(1181, 59, 93, 21);
		add(lblTenPhong);

		btnTaoMoiBang = new JButton("");
		btnTaoMoiBang.setBounds(1412, 717, 85, 35);
		add(btnTaoMoiBang);
		btnTaoMoiBang.setIcon(new ImageIcon("image/rs.png"));
		btnTaoMoiBang.addActionListener(this);
		btnTaoMoiBang.setMnemonic(KeyEvent.VK_N);
		
		JLabel lblChuyenMon = new JLabel("Chuyên môn:");
		lblChuyenMon.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblChuyenMon.setBounds(708, 234, 110, 30);
		add(lblChuyenMon);
		
		String cm [] = "Lên khuôn giày;Khâu dập và may da;Hoàn thiện phần mũi giày;Hoàn thiện phần đế và phần trang trí;Kiểm tra lại thành phẩm".split(";");
		cboChuyenMon = new JComboBox(cm);
		cboChuyenMon.setBounds(841, 241, 267, 21);
		add(cboChuyenMon);
		
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTrangThai.setBounds(299, 240, 97, 19);
		add(lblTrangThai);
		
		String trangThai [] = "Chưa phân công;Đã phân công".split(";");
		cboTrangThai = new JComboBox(trangThai);
		cboTrangThai.setBounds(419, 241, 196, 21);
		add(cboTrangThai);
		
		lblSL = new JLabel("SL:");
		lblSL.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblSL.setBounds(45, 403, 33, 19);
		add(lblSL);
		
		lblH = new JLabel("");
		lblH.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblH.setBounds(75, 403, 45, 19);
		add(lblH);
		lblH.setText(sl()+"");
		
		btnTraCuu = new JButton("Tra cứu");
		btnTraCuu.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnTraCuu.setBounds(1268, 339, 126, 34);
		add(btnTraCuu);
		btnTraCuu.addActionListener(this);
		//
		loadDataFromTable();
		//		docDuLieuVaoTable();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			int row = table.getSelectedRow();
			if(row >= 0) {
				String id = (String) table.getValueAt(row, 0);
				CongNhan_DAO dao = new CongNhan_DAO();
				CongNhan cn = dao.findByID(id);
				if(cn != null) {
					txtMa.setText(cn.getMaCN());
					txtTen.setText(cn.getHoTen());
					rdbNam.setSelected(cn.isPhai() == 1 ? true : false);
					rdbNu.setSelected(cn.isPhai() == 0 ? true : false);
					txtNgaySinh.setDate(cn.getNgaySinh());
					txtDiaChi.setText(cn.getDiaChi());
					txtSoDienThoai.setText(cn.getPhone());
					cboPhong.setSelectedItem(cn.getPhong().getMaPB());
					cboChuyenMon.setSelectedItem(cn.getChuyenMon());
					cboTrangThai.setSelectedItem(cn.getTrangThai());
					if(cn.getHinhAnh() != null) {
						Image img = ImageHepler.crateImageFromByteArray(cn.getHinhAnh(), "jpg");
						lblImage.setIcon(new ImageIcon(img));
						persionalImage = cn.getHinhAnh();
					}
					else {
						persionalImage = cn.getHinhAnh();
						ImageIcon eee = new ImageIcon("image/no.jpg");
						Image i = ImageHepler.resize(eee.getImage(), 233, 297);
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
				addCongNhan();
				txtMa.setText(autoMaHD());
				lblH.setText(sl()+"");
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
				updateCongNhan();
				txtMa.setText(autoMaHD());
			}
		}
		// xoa
		else if(o.equals(btnXoa)) {
			int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?", "Chú Ý", JOptionPane.YES_NO_OPTION);
			if(luaChon == JOptionPane.YES_OPTION) {
				deleteCongNhan();
				txtMa.setText(autoMaHD());
				lblH.setText(sl()+"");
			}
		}
		// tim kiem
		else if(o.equals(btnTimKiem)) {
			searchNhanVien();
		}
		else if(o.equals(btnTaoMoi)) {
			reset();
			txtMa.setText(autoMaHD());
		}
		else if(o.equals(btnTaoMoiBang)) {
			loadDataFromTable();
		}
		else if(o.equals(btnTraCuu)) {
			new TraCuuCongNhan_GUI().setVisible(true);
		}
	}
	// doc du lieu vao table
	public void docDuLieuVaoTable(){
		List<CongNhan> cn = cn_dao.findAll();
		for (CongNhan nv : cn) {
			PhongBan p = new PhongBan(cboTenPhong.getSelectedItem().toString());
			String dataRow[] = {nv.getMaCN(), nv.getHoTen(), nv.isPhai() == 1 ? "Nam" : "Nữ",
					nv.getNgaySinh()+"", nv.getDiaChi(), nv.getPhone(), nv.getNgayThamGiaCongTac()+"",
					nv.getPhong().getMaPB(), nv.getChucVu(), p.getTenPhong(), nv.getChuyenMon(), nv.getTrangThai()};
			tableModel.addRow(dataRow);
		}
		table.setModel(tableModel);
	}
	// load du lieu len table
	public void loadDataFromTable(){
		CongNhan_DAO dao = new CongNhan_DAO();
		List<CongNhan> list = dao.findAll();
		tableModel.setRowCount(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		for (CongNhan nv : list) {
			tableModel.addRow(new Object[] {nv.getMaCN(), nv.getHoTen(), nv.isPhai() == 1 ? "Nam" : "Nữ",
					sdf.format(nv.getNgaySinh()), nv.getDiaChi(), nv.getPhone() ,
					sdf.format(nv.getNgayThamGiaCongTac()), nv.getPhong().getMaPB(), nv.getChucVu(), nv.getChuyenMon(), nv.getTrangThai()});
		}
		table.setModel(tableModel);
	}
	// nut chon hinh anh
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
			Image img = ImageHepler.resize(icon.getImage(), 233, 297); // chinh kich thuoc anh
			ImageIcon ic = new ImageIcon(img);
			lblImage.setIcon(ic); // dua anh len giao dien
			persionalImage = ImageHepler.tobyArray(img, "jpg"); // chuyen hinh anh thanh mang de luu vao csdl
		} catch (Exception e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Lỗi chọn ảnh");
			e2.printStackTrace();
		}
	}
	// them cong nhan
	public void addCongNhan() {
		try {
			CongNhan cn = new CongNhan();
			cn.setMaCN(txtMa.getText());
			cn.setHoTen(txtTen.getText());
			cn.setPhai(rdbNam.isSelected() ? 1 : 0);
			//
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sn = sdf.format(txtNgaySinh.getDate());
			cn.setNgaySinh(Date.valueOf(sn));
			//
			cn.setDiaChi(txtDiaChi.getText());
			cn.setPhone(txtSoDienThoai.getText());
			//
			String ntg = sdf.format(txtNgayThamGia.getDate());
			cn.setNgayThamGiaCongTac(Date.valueOf(ntg));

			PhongBan p = new PhongBan(cboPhong.getSelectedItem().toString());
			cn.setPhong(p);
			cn.setChucVu(txtChucVu.getText());
			cn.setHinhAnh(persionalImage);
			cn.setChuyenMon(cboChuyenMon.getSelectedItem().toString());
			cn.setTrangThai(cboTrangThai.getSelectedItem().toString());
			CongNhan_DAO dao = new CongNhan_DAO();
			if(regex()) {
				if(dao.insert(cn)) {
					SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
					tableModel.addRow(new Object[] {cn.getMaCN(), cn.getHoTen(), cn.isPhai() == 1 ? "Nam" : "Nữ",
							s.format(cn.getNgaySinh()), cn.getDiaChi(), cn.getPhone(),
							s.format(cn.getNgayThamGiaCongTac()), cn.getPhong().getMaPB(), cn.getChucVu(), cn.getChuyenMon(), cn.getTrangThai()});
					JOptionPane.showMessageDialog(this, "Thêm thành công");
					reset();
				}else {
					JOptionPane.showMessageDialog(this, "Không thêm được");
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
		txtDiaChi.setText("");
		txtSoDienThoai.setText("");
		txtNgaySinh.setDate(new java.util.Date());
		txtNgayThamGia.setDate(new java.util.Date());
		rdbNam.isSelected();
		persionalImage = null;
		ImageIcon e = new ImageIcon("image/no.jpg");
		Image i = ImageHepler.resize(e.getImage(), 233, 297);
		ImageIcon ee = new ImageIcon(i);
		lblImage.setIcon(ee);
	}
	// cap nhat cong nhan
	public void updateCongNhan() {
		try {
			CongNhan cn = new CongNhan();
			cn.setMaCN(txtMa.getText());
			cn.setHoTen(txtTen.getText());
			cn.setPhai(rdbNam.isSelected() ? 1 : 0);
			//
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sn = sdf.format(txtNgaySinh.getDate());
			cn.setNgaySinh(Date.valueOf(sn));
			//
			cn.setDiaChi(txtDiaChi.getText());
			cn.setPhone(txtSoDienThoai.getText());
			//
			String ntg = sdf.format(txtNgayThamGia.getDate());
			cn.setNgayThamGiaCongTac(Date.valueOf(ntg));
			//				cn.setNgayThamGiaCongTac(Date.valueOf(txtNgayThamGia.getDateFormatString()));
			PhongBan p = new PhongBan(cboPhong.getSelectedItem().toString());
			cn.setHinhAnh(persionalImage);
			cn.setPhong(p);
			cn.setChucVu(txtChucVu.getText());
			cn.setChuyenMon(cboChuyenMon.getSelectedItem().toString());
			cn.setTrangThai(cboTrangThai.getSelectedItem().toString());
			CongNhan_DAO dao = new CongNhan_DAO();
			int row = table.getSelectedRow();
			if(row >= 0) {
				if(regex()) {
					if(dao.update(cn)) {
						table.setValueAt(txtTen.getText(), row, 1);
						table.setValueAt(rdbNam.isSelected() ? "Nam" : "Nữ", row, 2);
						SimpleDateFormat ns = new SimpleDateFormat("dd/MM/yyyy");
						String ngaySinh = ns.format(txtNgaySinh.getDate());
						table.setValueAt(ngaySinh, row, 3);
						table.setValueAt(txtDiaChi.getText(), row, 4);
						table.setValueAt(txtSoDienThoai.getText(), row, 5);
						//						SimpleDateFormat ntgct = new SimpleDateFormat("yyyy-MM-dd");
						String ng = ns.format(txtNgayThamGia.getDate());
						table.setValueAt(ng, row, 6);
						table.setValueAt(cboPhong.getSelectedItem(), row, 7);
						table.setValueAt(txtChucVu.getText(), row, 8);
						table.setValueAt(cboChuyenMon.getSelectedItem(), row, 9);
						table.setValueAt(cboTrangThai.getSelectedItem().toString(), row, 10);
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
	// ham xoa cong nhan 
	public void deleteCongNhan() {
		try {
			CongNhan_DAO dao = new CongNhan_DAO();
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
	// tim kiem bang so dien thoai
	public void searchNhanVien() {
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
		else if(cn_dao.findCongNhanByTagName(tim).size() == 0) {
			JOptionPane.showMessageDialog(null, "Không tìm thấy số điện thoại!");
		}
		else {
			for (CongNhan b : cn_dao.findCongNhanByTagName(tim)) {
				tableModel.setRowCount(0);
				tableModel.addRow(new Object[] {
						b.getMaCN(), b.getHoTen(), b.isPhai() == 1 ? "Nam" : "Nữ" , sdf.format(b.getNgaySinh()), b.getDiaChi(),
								b.getPhone(), sdf.format(b.getNgayThamGiaCongTac()), b.getPhong().getMaPB(), b.getChucVu(), b.getHinhAnh(), b.getChuyenMon()
				});
			}
			table.setModel(tableModel);
		}
	}
	// regex
	public boolean regex() {
		// tên nhân viên
		if(txtTen.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Tên công nhân không được để trống!");
			txtTen.requestFocus();
			return false;
		}
		else if(txtTen.getText().length() > 0 && !(txtTen.getText().matches("[A-Z][a-z]*(\\s[A-Z][a-z]*)*"))) {
			JOptionPane.showMessageDialog(this, "Tên công nhân phải viết hoa đầu từ!");
			txtTen.requestFocus();
			txtTen.selectAll();
			return false;
		}
		// số điện thoại
		else if(txtSoDienThoai.getText().isEmpty()) {
			JOptionPane.showConfirmDialog(this, "Số điện thoại không được để trống!");
			txtSoDienThoai.requestFocus();
			return false;
		}
		else if(txtSoDienThoai.getText().length() > 0 && !(txtSoDienThoai.getText().matches("0\\d{9}"))) {
			JOptionPane.showMessageDialog(null, "Số điện thoại phải nhập đúng 10 chữ số và bắt đầu là 0!");
			txtSoDienThoai.requestFocus();
			txtSoDienThoai.selectAll();
			return false;
		}
		// địa chỉ
		else if(txtDiaChi.getText().isEmpty()) {
			JOptionPane.showConfirmDialog(this, "Địa chỉ không được để trống!");
			txtDiaChi.requestFocus();
			return false;
		}
		//				\\w[\\w]*\\@gmail\\.com
		else if(txtDiaChi.getText().length() > 0 && !(txtDiaChi.getText().matches("[A-Z][a-z]*(\\s[A-Z][a-z]*)*"))) {
			JOptionPane.showMessageDialog(null, "Địa chỉ phải nhập hoa đầu từ!");
			txtDiaChi.requestFocus();
			txtDiaChi.selectAll();
			return false;
		}
		return true;
	}
	// auto ma cong nhan
	public String autoMaHD() {
		String str;
		int ma = cn_dao.getMaHd();
		if (ma < 100)
			str = "CN0000" + ma;
		else if (ma < 1000)
			str = "CN000" + ma;
		else if (ma < 10000)
			str = "CN00" + ma;
		else if (ma < 100000)
			str = "CN0" + ma;
		else
			str = "CN" + ma;
		return str;

	}
	// so luong cong nhan hien co
	public int sl() {
		int s = 0;
		List<CongNhan> l = cn_dao.findAll();
		for (CongNhan congNhan : l) {
			s++;
		}
		return s;
	}
}
