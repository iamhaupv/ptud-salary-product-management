package gui;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import connect.connectDB;
import dao.CongNhan_DAO;
import dao.NhanVien_DAO;
import dao.PhongBan_DAO;
import entity.CongNhan;
import entity.NhanVien;
import entity.PhongBan;
import helper.ImageHepler;

import javax.swing.DefaultComboBoxModel;

public class NhanVien_GUI extends JPanel implements ActionListener, MouseListener{
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtSoDienThoai;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JTable table;
	private JTextField txtTimKiem;
	private JComboBox cboTrinhDoChuyenMon;
	private JComboBox cboKinhNghiem;
	private JComboBox cboPhong;
	private CongNhan_DAO cn_dao;
	private PhongBan_DAO pb_dao;
	private JButton btnTimKiem;
	private JDateChooser txtNgayThamGia;
	private JComboBox cboChucVu;
	private JRadioButton rdbNu;
	private JRadioButton rdbNam;
	private JButton btnTaoMoi;
	private JButton btnXoa;
	private JButton btnCapNhat;
	private JButton btnThem;
	private JButton btnChonAnh;
	private JComboBox cboTenPhong;
	private byte[] persionalImage;
	private JDateChooser txtNgaySinh;
	private JLabel lblImage;
	private JFileChooser chooser;
	private NhanVien_DAO nv_dao;
	private DefaultTableModel tableModel;
	private JButton btnTaoMoiBang;
	private JButton btnTraCuu;
	private JLabel lblH;
	private JLabel lblTrangThai;
	private JComboBox cboTrangThai;
	/**
	 * Create the panel.
	 */
	public NhanVien_GUI() {
		connectDB.getInstance().connect();
		nv_dao = new NhanVien_DAO();
		pb_dao = new PhongBan_DAO();
		setLayout(null);

		JLabel lbl_tt = new JLabel("TH\u00D4NG TIN NH\u00C2N VI\u00CAN");
		lbl_tt.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_tt.setBounds(750, 20, 299, 30);
		add(lbl_tt);

		lblImage = new JLabel("");
		lblImage.setBounds(45, 20, 231, 297);
		lblImage.setBorder(BorderFactory.createLineBorder(Color.black));
		add(lblImage);
		ImageIcon e = new ImageIcon("image/no.jpg");
		Image i = ImageHepler.resize(e.getImage(), 233, 297);
		ImageIcon ee = new ImageIcon(i);
		lblImage.setIcon(ee);

		JLabel lblMa = new JLabel("M\u00E3 nh\u00E2n vi\u00EAn:");
		lblMa.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMa.setBounds(299, 59, 110, 21);
		add(lblMa);

		JLabel lblTen = new JLabel("T\u00EAn nh\u00E2n vi\u00EAn:");
		lblTen.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTen.setBounds(299, 120, 119, 21);
		add(lblTen);

		JLabel lblPhai = new JLabel("Ph\u00E1i:");
		lblPhai.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblPhai.setBounds(299, 176, 53, 30);
		add(lblPhai);

		JLabel lblNgaySinh = new JLabel("Ng\u00E0y sinh:");
		lblNgaySinh.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNgaySinh.setBounds(299, 242, 93, 21);
		add(lblNgaySinh);

		JLabel lblSoDienThoai = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i:");
		lblSoDienThoai.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblSoDienThoai.setBounds(719, 59, 110, 21);
		add(lblSoDienThoai);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblEmail.setBounds(719, 115, 70, 30);
		add(lblEmail);

		JLabel lblDiaChi = new JLabel("\u0110\u1ECBa ch\u1EC9:");
		lblDiaChi.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblDiaChi.setBounds(719, 182, 79, 19);
		add(lblDiaChi);

		JLabel lblChucVu = new JLabel("Ch\u1EE9c v\u1EE5:");
		lblChucVu.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblChucVu.setBounds(719, 235, 70, 35);
		add(lblChucVu);

		JLabel lblTrinhDoChuyenMon = new JLabel("Tr\u00ECnh \u0111\u1ED9 chuy\u00EAn m\u00F4n:");
		lblTrinhDoChuyenMon.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTrinhDoChuyenMon.setBounds(1117, 59, 174, 21);
		add(lblTrinhDoChuyenMon);

		JLabel lblKinhNghiem = new JLabel("Kinh nghi\u1EC7m:");
		lblKinhNghiem.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblKinhNghiem.setBounds(1117, 120, 119, 21);
		add(lblKinhNghiem);

		JLabel lblNgayThamGia = new JLabel("Ng\u00E0y tham gia:");
		lblNgayThamGia.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNgayThamGia.setBounds(1117, 181, 119, 21);
		add(lblNgayThamGia);

		JLabel lblPhong = new JLabel("Ph\u00F2ng:");
		lblPhong.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblPhong.setBounds(1117, 289, 63, 30);
		add(lblPhong);

		btnChonAnh = new JButton("Ch\u1ECDn \u1EA3nh");
		btnChonAnh.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnChonAnh.setBounds(89, 327, 135, 34);
		add(btnChonAnh);

		btnThem = new JButton("Th\u00EAm");
		btnThem.setIcon(new ImageIcon("image/add.png"));
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnThem.setBounds(486, 333, 126, 42);
		add(btnThem);

		btnCapNhat = new JButton("C\u1EADp nh\u1EADt");
		btnCapNhat.setIcon(new ImageIcon("image/exchange.png"));
		btnCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCapNhat.setBounds(663, 333, 163, 42);
		add(btnCapNhat);

		btnXoa = new JButton("X\u00F3a");
		btnXoa.setIcon(new ImageIcon("image/delete.png"));
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnXoa.setBounds(881, 331, 118, 46);
		add(btnXoa);

		btnTaoMoi = new JButton("T\u1EA1o m\u1EDBi");
		btnTaoMoi.setIcon(new ImageIcon("image/create.png"));
		btnTaoMoi.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnTaoMoi.setBounds(1061, 334, 149, 40);
		add(btnTaoMoi);

		txtMa = new JTextField();
		txtMa.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtMa.setBounds(419, 61, 196, 19);
		txtMa.setText(autoMaHD());
		txtMa.setEnabled(false);
		add(txtMa);
		txtMa.setColumns(10);

		txtTen = new JTextField();
		txtTen.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTen.setBounds(419, 120, 196, 19);
		add(txtTen);
		txtTen.setColumns(10);

		rdbNam = new JRadioButton("Nam");
		rdbNam.setSelected(true);
		rdbNam.setFont(new Font("Times New Roman", Font.BOLD, 15));
		rdbNam.setBounds(419, 186, 65, 21);
		add(rdbNam);

		rdbNu = new JRadioButton("N\u1EEF");
		rdbNu.setFont(new Font("Times New Roman", Font.BOLD, 15));
		rdbNu.setBounds(504, 186, 63, 21);
		add(rdbNu);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbNam);
		group.add(rdbNu);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtSoDienThoai.setBounds(842, 60, 196, 19);
		add(txtSoDienThoai);
		txtSoDienThoai.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtEmail.setBounds(842, 122, 196, 19);
		add(txtEmail);
		txtEmail.setColumns(10);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtDiaChi.setBounds(841, 183, 197, 19);
		add(txtDiaChi);
		txtDiaChi.setColumns(10);

		String cv [] = {"Nhân viên", "Trưởng phòng", "Quản lý", "Kế toán"};
		cboChucVu = new JComboBox(cv);
		cboChucVu.setModel(new DefaultComboBoxModel(new String[] {"Nhân viên", "Phó trưởng phòng", "Trưởng phòng", "Quản lý", "Kế toán", "Giám đốc", "Phó giám đốc"}));
		cboChucVu.setFont(new Font("Times New Roman", Font.BOLD, 13));
		cboChucVu.setBounds(842, 244, 196, 21);
		add(cboChucVu);

		String tdcm [] = {"Trung cấp", "Cao đẳng", "Đại học", "Thạc sĩ", "Tiến sĩ"};
		cboTrinhDoChuyenMon = new JComboBox(tdcm);
		cboTrinhDoChuyenMon.setFont(new Font("Times New Roman", Font.BOLD, 13));
		cboTrinhDoChuyenMon.setBounds(1301, 61, 196, 21);
		add(cboTrinhDoChuyenMon);

		String kn [] = {"Không", "6 Tháng", "12 Tháng", "Trên 12 Tháng"};
		cboKinhNghiem = new JComboBox(kn);
		cboKinhNghiem.setFont(new Font("Times New Roman", Font.BOLD, 13));
		cboKinhNghiem.setBounds(1301, 122, 196, 21);
		add(cboKinhNghiem);

		cboPhong = new JComboBox();
		cboPhong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		cboPhong.setBounds(1301, 296, 196, 21);
		add(cboPhong);
		ArrayList<PhongBan> p = pb_dao.getallPhongBan();
		for (PhongBan s : p) {
			cboPhong.addItem(s.getMaPB());
		}
		cboPhong.setEnabled(false);
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 429, 1452, 278);
		add(scrollPane);

		String header[] = {"Mã nhân viên", "Họ tên", "Phái", "Ngày sinh", "Địa chỉ",
				"Email", "Số điện thoại", "Ngày tham gia",
				"TĐ chuyên môn", "Kinh nghiệm", "Chức vụ", "Phòng ban", "Trạng thái"};
		tableModel = new DefaultTableModel(header, 0);
		table = new JTable(tableModel);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		scrollPane.setViewportView(table);

		txtNgaySinh = new JDateChooser();
		txtNgaySinh.setBounds(419, 243, 196, 20);
		add(txtNgaySinh);

		txtNgayThamGia = new JDateChooser();
		txtNgayThamGia.setBounds(1301, 186, 196, 20);
		add(txtNgayThamGia);

		JLabel lbl_ttnv = new JLabel("BẢNG THÔNG TIN NHÂN VIÊN");
		lbl_ttnv.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_ttnv.setBounds(663, 392, 307, 29);
		add(lbl_ttnv);

		JLabel lblTimKiem = new JLabel("Nhập để tìm kiếm:");
		lblTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTimKiem.setBounds(45, 717, 141, 31);
		add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTimKiem.setBounds(199, 721, 1073, 27);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);

		btnTimKiem = new JButton();
		btnTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnTimKiem.setIcon(new ImageIcon("image/search.png"));
		btnTimKiem.setBounds(1282, 714, 110, 36);

		add(btnTimKiem);

		JLabel lblTenPhong = new JLabel("Tên phòng:");
		lblTenPhong.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTenPhong.setBounds(1117, 240, 93, 25);
		add(lblTenPhong);

		cboTenPhong = new JComboBox();
		cboTenPhong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		cboTenPhong.setBounds(1301, 244, 196, 21);
		add(cboTenPhong);
		for (PhongBan s : p) {
			cboTenPhong.addItem(s.getTenPhong());
		}
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

		btnTaoMoiBang = new JButton("");
		btnTaoMoiBang.setBounds(1412, 713, 85, 37);
		add(btnTaoMoiBang);
		btnTaoMoiBang.setIcon(new ImageIcon("image/rs.png"));
		btnTaoMoiBang.addActionListener(this);
		btnTaoMoiBang.setMnemonic(KeyEvent.VK_N);
		
		btnTraCuu = new JButton("Tra cứu");
		btnTraCuu.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnTraCuu.setBounds(1263, 336, 129, 39);
		add(btnTraCuu);
		btnTraCuu.addActionListener(this);
		
		JLabel lblSL = new JLabel("SL:");
		lblSL.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblSL.setBounds(72, 388, 35, 17);
		add(lblSL);
		
		lblH = new JLabel("");
		lblH.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblH.setBounds(106, 383, 47, 27);
		add(lblH);
		lblH.setText(sl()+"");
		
		lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTrangThai.setBounds(719, 300, 93, 21);
		add(lblTrangThai);
		
		cboTrangThai = new JComboBox();
		cboTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Đang làm việc", "Nghĩ việc"}));
		cboTrangThai.setBounds(849, 296, 185, 21);
		add(cboTrangThai);
		try {
			loadDataFromTable();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(txtNgaySinh.getDate().getYear());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			int row = table.getSelectedRow();
			if(row >= 0) {
				String id = (String) table.getValueAt(row, 0);
				NhanVien_DAO dao = new NhanVien_DAO();
				NhanVien nv = dao.findByID(id);
				if(nv != null) {
					txtMa.setText(nv.getMaNV());
					txtTen.setText(nv.getHoTen());
					rdbNam.setSelected(nv.isPhai() == 1 ? true : false);
					rdbNu.setSelected(nv.isPhai() == 0 ? true : false);
					txtNgaySinh.setDate(nv.getNgaySinh());
					txtDiaChi.setText(nv.getDiaChi());
					txtEmail.setText(nv.getEmail());
					txtSoDienThoai.setText(nv.getPhone());
					txtNgayThamGia.setDate(nv.getNgayThamGiaCongTac());
					cboTrinhDoChuyenMon.setSelectedItem(nv.getTrinhDoChuyenMon());
					cboKinhNghiem.setSelectedItem(nv.getKinhNghiem());
					cboChucVu.setSelectedItem(nv.getChucVu());
					cboPhong.setSelectedItem(nv.getPhong().getMaPB());
					cboTrangThai.setSelectedItem(nv.getTrangThai());
					if(nv.getHinhAnh() != null) {
						Image img = ImageHepler.crateImageFromByteArray(nv.getHinhAnh(), "jpg");
						lblImage.setIcon(new ImageIcon(img));
						persionalImage = nv.getHinhAnh();
					}
					else {
						persionalImage = nv.getHinhAnh();
						ImageIcon eee = new ImageIcon("image/no.jpg");
						Image i = ImageHepler.resize(eee.getImage(), 231, 297);
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
		if(o.equals(btnChonAnh)) {
			chonFile();
		}
		else if(o.equals(btnThem)) {
			int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?", "Chú Ý", JOptionPane.YES_NO_OPTION);
			if(luaChon == JOptionPane.YES_OPTION) {
				addNhanVien();
				ThemNguoiDung_GUI tk = new ThemNguoiDung_GUI();
				txtMa.setText(autoMaHD());
				try {
					tk.loadDataFromTable();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
				updateNhanVien();
				txtMa.setText(autoMaHD());
			}
		}
		// xoa
		else if(o.equals(btnXoa)) {
			int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?", "Chú Ý", JOptionPane.YES_NO_OPTION);
			if(luaChon == JOptionPane.YES_OPTION) {
				deleteNhanVien();
				txtMa.setText(autoMaHD());
				lblH.setText(sl()+"");
			}
		}
		// tao moi
		else if(o.equals(btnTaoMoi)) {
			reset();
			txtMa.setText(autoMaHD());
		}
		// tim kiem
		else if(o.equals(btnTimKiem)) {
			searchNhanVien();
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
		else if(o.equals(btnTraCuu)) {
			new TraCuuNhanVien_GUI().setVisible(true);
		}
	}
	// them nhan vien
	public void addNhanVien() {
		try {
			NhanVien cn = new NhanVien();
			cn.setMaNV(txtMa.getText());
			cn.setHoTen(txtTen.getText());
			cn.setPhai(rdbNam.isSelected() ? 1 : 0);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sn = sdf.format(txtNgaySinh.getDate());
			cn.setNgaySinh(Date.valueOf(sn));
			cn.setDiaChi(txtDiaChi.getText());
			cn.setEmail(txtEmail.getText());
			cn.setPhone(txtSoDienThoai.getText());
			//
			//			SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd");
			String ntg = sdf.format(txtNgayThamGia.getDate());
			cn.setNgayThamGiaCongTac(Date.valueOf(ntg));
			cn.setTrinhDoChuyenMon(cboTrinhDoChuyenMon.getSelectedItem().toString());
			cn.setKinhNghiem(cboKinhNghiem.getSelectedItem().toString());
			cn.setChucVu(cboChucVu.getSelectedItem().toString());
			PhongBan p = new PhongBan(cboPhong.getSelectedItem().toString());
			cn.setPhong(p);
			cn.setHinhAnh(persionalImage);
			cn.setTrangThai(cboTrangThai.getSelectedItem().toString());
			NhanVien_DAO dao = new NhanVien_DAO();
			if(regex()) {
				if(dao.insert(cn)) {
					//				if(cn.getPhong().getMaPB() == "PB123451") {
					//					cn.getPhong().getMaPB() == "Phòng kĩ thuật";
					//				}
					SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
					tableModel.addRow(new Object[] {cn.getMaNV(), cn.getHoTen(), cn.isPhai() == 1 ? "Nam" : "Nữ",
							s.format(cn.getNgaySinh()), cn.getDiaChi(), cn.getEmail(), cn.getPhone(), s.format(cn.getNgayThamGiaCongTac()),
							cn.getTrinhDoChuyenMon(), cn.getKinhNghiem(), cn.getChucVu(), cn.getPhong().getMaPB(), cn.getTrangThai()});
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
	public void updateNhanVien() {
		try {
			NhanVien cn = new NhanVien();
			cn.setMaNV(txtMa.getText());
			cn.setHoTen(txtTen.getText());
			cn.setPhai(rdbNam.isSelected() ? 1 : 0);
			//
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sn = sdf.format(txtNgaySinh.getDate());
			cn.setNgaySinh(Date.valueOf(sn));
			cn.setDiaChi(txtDiaChi.getText());
			cn.setEmail(txtEmail.getText());
			cn.setPhone(txtSoDienThoai.getText());
			String ntg = sdf.format(txtNgayThamGia.getDate());
			cn.setNgayThamGiaCongTac(Date.valueOf(ntg));
			cn.setTrinhDoChuyenMon(cboTrinhDoChuyenMon.getSelectedItem().toString());
			cn.setKinhNghiem(cboKinhNghiem.getSelectedItem().toString());
			cn.setChucVu(cboChucVu.getSelectedItem().toString());
			PhongBan p = new PhongBan(cboPhong.getSelectedItem().toString());
			cn.setHinhAnh(persionalImage);
			cn.setPhong(p);
			cn.setTrangThai(cboTrangThai.getSelectedItem().toString());
			NhanVien_DAO dao = new NhanVien_DAO();
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
						table.setValueAt(txtEmail.getText(), row, 5);
						table.setValueAt(txtSoDienThoai.getText(), row, 6);
						String ng = ns.format(txtNgayThamGia.getDate());
						table.setValueAt(ng, row, 7);
						table.setValueAt(cboTrinhDoChuyenMon.getSelectedItem(), row, 8);
						table.setValueAt(cboKinhNghiem.getSelectedItem(), row, 9);
						table.setValueAt(cboChucVu.getSelectedItem(), row, 10);
						table.setValueAt(cboPhong.getSelectedItem(), row, 11);
						table.setValueAt(cboTrangThai.getSelectedItem(), row, 12);
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
	public void deleteNhanVien() {
		try {
			NhanVien_DAO dao = new NhanVien_DAO();
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
		txtDiaChi.setText("");
		txtSoDienThoai.setText("");
		txtNgaySinh.setDate(new java.util.Date());
		txtNgayThamGia.setDate(new java.util.Date());
		rdbNam.isSelected();
		persionalImage = null;
		ImageIcon e = new ImageIcon("image/no.jpg");
		Image i = ImageHepler.resize(e.getImage(), 231, 297);
		ImageIcon ee = new ImageIcon(i);
		lblImage.setIcon(ee);
		txtEmail.setText("");
	}
	// tim kiem bang so dien thoai
	public void searchNhanVien() {
		try {
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
			else if(nv_dao.findCongNhanByTagName(tim).size() == 0) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy số điện thoại!");
			}
			else {
				for (NhanVien b : nv_dao.findNhanVienBySoDienThoai(tim)) {
					tableModel.setRowCount(0);
					tableModel.addRow(new Object[] {
							b.getMaNV(), b.getHoTen(), b.isPhai() == 1 ? "Nam" : "Nữ" , sdf.format(b.getNgaySinh()), b.getDiaChi(), b.getEmail(),
									b.getPhone(), sdf.format(b.getNgayThamGiaCongTac()), b.getTrinhDoChuyenMon(), b.getKinhNghiem(), b.getChucVu(), b.getPhong().getMaPB(), b.getChucVu(), b.getHinhAnh()
					});
				}
				table.setModel(tableModel);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	// chon file
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
			Image img = ImageHepler.resize(icon.getImage(), 231, 297); // chinh kich thuoc anh
			ImageIcon ic = new ImageIcon(img);
			lblImage.setIcon(ic); // dua anh len giao dien
			persionalImage = ImageHepler.tobyArray(img, "jpg"); // chuyen hinh anh thanh mang de luu vao csdl
		} catch (Exception e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Lỗi chọn ảnh");
			e2.printStackTrace();
		}
	}
	// doc du lieu vao table
	public void docDuLieuVaoTable() throws SQLException {
		ArrayList<NhanVien> cn = nv_dao.getallNhanVien();
		for (NhanVien nv : cn) {
			String dataRow[] = {nv.getMaNV(), nv.getHoTen(), nv.isPhai() == 1 ? "Nam" : "Nữ",
					nv.getNgaySinh()+"", nv.getDiaChi(), nv.getPhone(), nv.getNgayThamGiaCongTac()+"", nv.getPhong().getMaPB()};
			tableModel.addRow(dataRow);
		}
		table.setModel(tableModel);
	}
	// load du lieu len table
	public void loadDataFromTable() throws SQLException, ClassNotFoundException {
		NhanVien_DAO dao = new NhanVien_DAO();
		List<NhanVien> list = dao.findAll();
		tableModel.setRowCount(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (NhanVien nv : list) {
			tableModel.addRow(new Object[] {nv.getMaNV(), nv.getHoTen(), nv.isPhai() == 1 ? "Nam" : "Nữ",
					sdf.format(nv.getNgaySinh()), nv.getDiaChi(), nv.getEmail(), nv.getPhone() ,
					sdf.format(nv.getNgayThamGiaCongTac()), nv.getTrinhDoChuyenMon(), nv.getKinhNghiem(), nv.getChucVu(), nv.getPhong().getMaPB(), nv.getTrangThai()});
		}
		table.setModel(tableModel);
	}
	// regex
	public boolean regex() {
		LocalDateTime date = LocalDateTime.now();
		int nh = date.getYear();
		// tên nhân viên
		if(txtTen.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Tên nhân viên không được để trống!");
			txtTen.requestFocus();
			return false;
		}
		else if(txtTen.getText().length() > 0 && !(txtTen.getText().matches("[A-Z][a-z]*(\\s[A-Z][a-z]*)*"))) {
			JOptionPane.showMessageDialog(this, "Tên nhân viên phải viết hoa đầu từ!");
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
		// email
		else if(txtEmail.getText().isEmpty()) {
			JOptionPane.showConfirmDialog(this, "Email không được để trống!");
			txtEmail.requestFocus();
			return false;
		}
		//		\\w[\\w]*\\@gmail\\.com
		else if(txtEmail.getText().length() > 0 && !(txtEmail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))) {
			JOptionPane.showMessageDialog(null, "Phải nhập đúng định dạng email!");
			txtEmail.requestFocus();
			txtEmail.selectAll();
			return false;
		}
		//
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
		else if(txtNgaySinh.getDate().getYear() > nh - 18) {
			JOptionPane.showMessageDialog(this, "Năm sinh phải nhập bé hơn năm hiện tại 18 năm!");
			txtNgaySinh.requestFocus();
			return false;
		}
		return true;
	}
	public String autoMaHD() {
		String str;
		int ma = nv_dao.getMaHd();
		if (ma < 100)
			str = "NV0000" + ma;
		else if (ma < 1000)
			str = "NV000" + ma;
		else if (ma < 10000)
			str = "NV00" + ma;
		else if (ma < 100000)
			str = "NV0" + ma;
		else
			str = "NV" + ma;
		return str;

	}
	public int sl() {
		int s = 0;
		List<NhanVien> dsnv;
		try {
			dsnv = nv_dao.findAll();
			for (NhanVien n : dsnv) {
				s++;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
}
