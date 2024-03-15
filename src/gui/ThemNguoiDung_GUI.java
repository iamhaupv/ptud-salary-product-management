package gui;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;

import connect.connectDB;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.NhanVien;
import entity.TaiKhoan;
import helper.ImageHepler;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class ThemNguoiDung_GUI extends JPanel implements ActionListener, MouseListener{
	private JTextField txtMa;
	private JTextField txtTen;
	private JLabel lblImage;
	private JButton btnThem;
	private JTable table;
	private DefaultTableModel tableModel;
	private JLabel lbl_ttcn;
	private byte[] persionalImage;
	private JPasswordField txtMatKhau;
	private JPasswordField txtNLMatKhau;
	private TaiKhoan_DAO nd_dao;
	private JLabel lblVaiTro;
	private JComboBox cboVaiTro;
	/**
	 * Create the panel.
	 */
	public ThemNguoiDung_GUI() {
		connectDB.getInstance().connect();
		nd_dao = new TaiKhoan_DAO();
		setLayout(null);

		JLabel lbl_tt = new JLabel("TH\u00CAM T\u00C0I KHO\u1EA2N");
		lbl_tt.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_tt.setBounds(731, 20, 240, 39);
		add(lbl_tt);

		JLabel lblMa = new JLabel("M\u00E3 nh\u00E2n vi\u00EAn:");
		lblMa.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMa.setBounds(426, 63, 112, 32);
		add(lblMa);

		txtMa = new JTextField();
		txtMa.setEnabled(false);
		txtMa.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtMa.setBounds(609, 71, 611, 19);
		add(txtMa);
		txtMa.setColumns(10);

		JLabel lblTenTaiKhoan = new JLabel("T\u00EAn t\u00E0i kho\u1EA3n:");
		lblTenTaiKhoan.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTenTaiKhoan.setBounds(426, 131, 112, 39);
		add(lblTenTaiKhoan);

		txtTen = new JTextField();
		txtTen.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTen.setBounds(609, 142, 611, 19);
		add(txtTen);
		txtTen.setColumns(10);

		JLabel lblMatKhau = new JLabel("M\u1EADt kh\u1EA9u:");
		lblMatKhau.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMatKhau.setBounds(426, 210, 96, 32);
		add(lblMatKhau);

		JLabel lblNhapLaiMatKhau = new JLabel("Nh\u1EADp l\u1EA1i m\u1EADt kh\u1EA9u:");
		lblNhapLaiMatKhau.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNhapLaiMatKhau.setBounds(426, 283, 158, 32);
		add(lblNhapLaiMatKhau);

		lblImage = new JLabel("");
		lblImage.setBounds(151, 65, 233, 297);
		lblImage.setBorder(BorderFactory.createLineBorder(Color.black));
		ImageIcon e = new ImageIcon("image/no.jpg");
		Image i = ImageHepler.resize(e.getImage(), 233, 297);
		ImageIcon ee = new ImageIcon(i);
		lblImage.setIcon(ee);
		add(lblImage);

		btnThem = new JButton("Th\u00EAm");
		btnThem.setIcon(new ImageIcon("image/add.png"));
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnThem.setBounds(784, 388, 138, 57);
		add(btnThem);
		btnThem.addActionListener(this);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 497, 1451, 297);
		add(scrollPane);

		String header[] = {"Mã nhân viên", "Họ tên", "Phái", "Ngày sinh", "Địa chỉ",
				"Email", "Số điện thoại", "Ngày tham gia",
				"TĐ chuyên môn", "Kinh nghiệm", "Chức vụ", "Phòng ban"};
		tableModel = new DefaultTableModel(header, 0);
		table = new JTable(tableModel);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		scrollPane.setViewportView(table);

		lbl_ttcn = new JLabel("BẢNG THÔNG TIN NHÂN VIÊN");
		lbl_ttcn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_ttcn.setBounds(662, 455, 309, 32);
		add(lbl_ttcn);

		txtMatKhau = new JPasswordField();
		txtMatKhau.setBounds(609, 219, 611, 19);
		add(txtMatKhau);

		txtNLMatKhau = new JPasswordField();
		txtNLMatKhau.setBounds(609, 292, 611, 19);
		add(txtNLMatKhau);
		//
		try {
			loadDataFromTable();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//
		table.addMouseListener(this);
		btnThem.setMnemonic(KeyEvent.VK_A);
		
		lblVaiTro = new JLabel("Vai trò:");
		lblVaiTro.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblVaiTro.setBounds(426, 362, 68, 19);
		add(lblVaiTro);
		
		String vt [] = {"Quản lý", "Quản trị hệ thống", "Kế toán"};
		cboVaiTro = new JComboBox(vt);
		cboVaiTro.setBounds(609, 358, 611, 21);
		
		add(cboVaiTro);
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
					if(nv.getHinhAnh() != null) {
						Image img = ImageHepler.crateImageFromByteArray(nv.getHinhAnh(), "jpg");
						lblImage.setIcon(new ImageIcon(img));
						persionalImage = nv.getHinhAnh();
					}
					else {
						persionalImage = nv.getHinhAnh();
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
		if(o == btnThem) {
			if(regex()) {
				int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?", "Chú ý", JOptionPane.YES_NO_OPTION);
				if(choose == JOptionPane.YES_OPTION) {
					addNguoiDung();
					DoiMatKhau_GUI d = new DoiMatKhau_GUI();
					d.reset_table();
					reset();
				}

			}
		}
	}
	public void reset() {
		txtMatKhau.setText("");
		txtTen.setText("");
		txtNLMatKhau.setText("");
		txtMa.setText("");
	}
	public void addNguoiDung() {
		String tk = txtTen.getText();
		String mk = String.valueOf( txtMatKhau.getPassword());
		NhanVien nvhc = new NhanVien(txtMa.getText());
		long millis=System.currentTimeMillis();  
		Date ngayThem = new Date(millis);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sn = sdf.format(ngayThem);
//		cn.setNgaySinh(Date.valueOf(sn));
		String vaiTro = cboVaiTro.getSelectedItem().toString();
		TaiKhoan nd = new TaiKhoan(tk, mk, nvhc, java.sql.Date.valueOf(sn), null, vaiTro);
		try {
			if(nd_dao.createTaiKhoan(nd)) {
				JOptionPane.showMessageDialog(this, "Thêm thành công");
			}
			else {
				JOptionPane.showMessageDialog(this, "Thêm không thành công do tên tài khoản đã tồn tại!");
				txtTen.requestFocus();
				txtTen.selectAll();
				return;
			}
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean regex() {
		if(txtMa.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên trong bảng Nhân viên!");
			return false;
		}
		else if(!kiemTraTaiKhoan(txtMa.getText())) {
			JOptionPane.showMessageDialog(this, "Nhân viên này đã có tài khoản, vui lòng chọn nhân viên khác!");
			return false;
		}
		//
		else if(txtTen.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tài khoản");
			txtTen.requestFocus();
			return false;
		}
		else if(txtTen.getText().length() > 30 || txtTen.getText().length() < 5) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tài khoản nhỏ hơn 30 ký tự và lớn hơn 5 ký tự");
			txtTen.requestFocus();
			return false;
		}
		else if(txtTen.getText().length() >= 5 && !txtTen.getText().matches("\\w+")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tài khoản không có khoảng trắng và không có ký tự đặc biệt");
			txtTen.requestFocus();
			return false;
		}
		// mat khau
		else if(String.valueOf(txtMatKhau.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu");
			txtMatKhau.requestFocus();
			return false;
		}
		else if(String.valueOf(txtMatKhau.getPassword()).length() > 30 || String.valueOf(txtMatKhau.getPassword()).length() < 5) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu nhỏ hơn 30 ký tự và lớn hơn 5 ký tự");
			txtMatKhau.requestFocus();
			return false;
		}
		else if(String.valueOf(txtMatKhau.getPassword()).length() >= 5 && !String.valueOf(txtMatKhau.getPassword()).matches("\\w+")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tài khoản không có khoảng trắng và không có ký tự đặc biệt");
			txtMatKhau.requestFocus();
			return false;
		}
		// nhap lai mat khau
		else if(String.valueOf(txtNLMatKhau.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập lại mật khẩu!");
			txtNLMatKhau.requestFocus();
			return false;
		}
		else if(!String.valueOf(txtNLMatKhau.getPassword()).equalsIgnoreCase(String.valueOf(txtMatKhau.getPassword()))) {
			JOptionPane.showMessageDialog(this, "Bạn nhập không khớp với mật khẩu");
			txtNLMatKhau.requestFocus();
			txtNLMatKhau.selectAll();
			return false;
		}
		return true;
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
					sdf.format(nv.getNgayThamGiaCongTac()), nv.getTrinhDoChuyenMon(), nv.getKinhNghiem(), nv.getChucVu(), nv.getPhong().getMaPB()});
		}
		table.setModel(tableModel);
	}
	
	// kiemTra nhan vien co tai khoan chua
	public boolean kiemTraTaiKhoan(String ma) {
		List<TaiKhoan> tk = nd_dao.getAllNguoiDung();
		for (TaiKhoan t : tk) {
			if(t.getNvhc().getMaNV().contains(ma))
				return false;
		}
		return true;
	}
}
