package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import connect.connectDB;
import dao.NhanVien_DAO;
import dao.PhongBan_DAO;
import entity.CongDoan;
import entity.NhanVien;
import entity.PhongBan;
import entity.SanPham;
import oracle.net.aso.n;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;

public class TraCuuNhanVien_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaNhanVien;
	private JTextField txtTenNhanVien;
	private JTextField txtSoDienThoai;
	private JTextField txtDiaChi;
	private JTextField txtGioiTinh;
	private JTable table;
	private DefaultTableModel tableModel;
	private NhanVien_DAO nv_dao;
	private JTextField txtEmail;
	private JTextField txtKinhNghiem;
	private JTextField txtTrinhDo;
	private JTextField txtChucVu;
	private JComboBox cboTenPhong;
	private JComboBox cboMaPhong;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TraCuuNhanVien_GUI frame = new TraCuuNhanVien_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TraCuuNhanVien_GUI() {

		connectDB.getInstance().connect();
		nv_dao = new NhanVien_DAO();
		setBounds(150, 50, 1200, 750);
		setAlwaysOnTop(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel.setBounds(462, 10, 294, 50);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "TRA C\u1EE8U NH\u00C2N VI\u00CAN", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(10, 70, 1166, 205);
		contentPane.add(panel);
		panel.setLayout(null);

		txtMaNhanVien = new JTextField();
		txtMaNhanVien.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				timKiemTheoMaNhanVien();
			}
		});
		txtMaNhanVien.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtMaNhanVien.setBounds(125, 42, 192, 19);
		panel.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);

		JLabel lblMaNhanVien = new JLabel("");
		lblMaNhanVien.setBounds(31, 42, 45, 13);
		panel.add(lblMaNhanVien);

		JLabel lblNewLabel_1 = new JLabel("Mã nhân viên");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1.setBounds(31, 43, 84, 19);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Tên nhân viên");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(31, 87, 84, 19);
		panel.add(lblNewLabel_1_1);

		txtTenNhanVien = new JTextField();
		txtTenNhanVien.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				timKiemTheoTen();
			}
		});
		txtTenNhanVien.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTenNhanVien.setColumns(10);
		txtTenNhanVien.setBounds(125, 86, 192, 19);
		panel.add(txtTenNhanVien);

		JLabel lblNewLabel_1_2 = new JLabel("Số điện thoại");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_2.setBounds(393, 43, 84, 19);
		panel.add(lblNewLabel_1_2);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				timKiemTheoSoDienThoai();
			}
		});
		txtSoDienThoai.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBounds(506, 42, 207, 19);
		panel.add(txtSoDienThoai);

		JLabel lblNewLabel_1_2_1 = new JLabel("Địa chỉ");
		lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_2_1.setBounds(393, 87, 84, 19);
		panel.add(lblNewLabel_1_2_1);

		txtDiaChi = new JTextField();
		txtDiaChi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				timKiemTheoDiaChi();
			}
		});
		txtDiaChi.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(506, 86, 207, 19);
		panel.add(txtDiaChi);

		JLabel lblNewLabel_1_2_2 = new JLabel("Giới tính");
		lblNewLabel_1_2_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_2_2.setBounds(751, 43, 84, 19);
		panel.add(lblNewLabel_1_2_2);

		txtGioiTinh = new JTextField();
		txtGioiTinh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				timKiemTheoGioiTinh();
			}
		});
		txtGioiTinh.setBounds(865, 43, 254, 19);
		panel.add(txtGioiTinh);
		txtGioiTinh.setColumns(10);

		JLabel lblNewLabel_1_2_2_2 = new JLabel("Kinh nghiệm");
		lblNewLabel_1_2_2_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_2_2_2.setBounds(751, 128, 84, 19);
		panel.add(lblNewLabel_1_2_2_2);

		JLabel lblNewLabel_1_2_2_2_1 = new JLabel("Trình độ");
		lblNewLabel_1_2_2_2_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_2_2_2_1.setBounds(393, 128, 84, 19);
		panel.add(lblNewLabel_1_2_2_2_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Chức vụ");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(31, 128, 84, 19);
		panel.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Email");
		lblNewLabel_1_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_1_1_1.setBounds(751, 87, 84, 19);
		panel.add(lblNewLabel_1_1_1_1);

		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				timKiemTheoEmail();
			}
		});
		txtEmail.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtEmail.setColumns(10);
		txtEmail.setBounds(865, 86, 254, 19);
		panel.add(txtEmail);

		txtKinhNghiem = new JTextField();
		txtKinhNghiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				timKiemTheoKinhNghiem();
			}
		});
		txtKinhNghiem.setColumns(10);
		txtKinhNghiem.setBounds(865, 128, 254, 19);
		panel.add(txtKinhNghiem);

		txtTrinhDo = new JTextField();
		txtTrinhDo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		txtTrinhDo.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTrinhDo.setColumns(10);
		txtTrinhDo.setBounds(506, 128, 207, 19);
		panel.add(txtTrinhDo);

		txtChucVu = new JTextField();
		txtChucVu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				timKiemTheoChucVu();
			}
		});
		txtChucVu.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtChucVu.setColumns(10);
		txtChucVu.setBounds(125, 128, 192, 19);
		panel.add(txtChucVu);

		JLabel lblPhong = new JLabel("Phòng ban:");
		lblPhong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblPhong.setBounds(393, 157, 84, 29);
		panel.add(lblPhong);

		cboTenPhong = new JComboBox();
		cboTenPhong.setBounds(506, 161, 207, 21);
		panel.add(cboTenPhong);

		cboMaPhong = new JComboBox();
		cboMaPhong.setBounds(865, 161, 254, 21);
		panel.add(cboMaPhong);
		cboTenPhong.addItem("Chọn");
		cboMaPhong.addItem("");
		PhongBan_DAO pb_dao = new PhongBan_DAO();
		List<PhongBan> l = pb_dao.findAll();
		for (PhongBan n : l) {
			cboTenPhong.addItem(n.getTenPhong());
		}
		for (PhongBan n : l) {
			cboMaPhong.addItem(n.getMaPB());
		}

		cboMaPhong.setEnabled(false);
		cboTenPhong.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				try {
					List<PhongBan> p;
					p = pb_dao.findAll();
					List<NhanVien> n = nv_dao.findAll();
					for (PhongBan s : p) {
						if(e.getStateChange() == ItemEvent.SELECTED) {
							if(!cboTenPhong.getSelectedItem().toString().equals("Chọn")) {
								if(cboTenPhong.getSelectedItem().equals(s.getTenPhong()))
									cboMaPhong.setSelectedItem(s.getMaPB());
								tableModel.setRowCount(0);
								SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
								for (NhanVien nv : n) {
									if(nv.getPhong().getMaPB().equals(cboMaPhong.getSelectedItem().toString())) {
										tableModel.addRow(new Object[] {nv.getMaNV(), nv.getHoTen(), nv.isPhai() == 1 ? "Nam" : "Nữ",
												sdf.format(nv.getNgaySinh()), nv.getDiaChi(), nv.getEmail(), nv.getPhone() ,
												sdf.format(nv.getNgayThamGiaCongTac()), nv.getTrinhDoChuyenMon(), nv.getKinhNghiem(),
												nv.getChucVu(), nv.getPhong().getMaPB()});
									}
								}
								table.setModel(tableModel);

							}
						}
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}});
		JLabel lblMaPhong = new JLabel("Mã phòng:");
		lblMaPhong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblMaPhong.setBounds(751, 157, 74, 21);
		panel.add(lblMaPhong);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 311, 1166, 392);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setModel(tableModel = new DefaultTableModel(
				new Object[][] {

				},
				new String[] {
						"M\u00E3 nh\u00E2n vi\u00EAn", "T\u00EAn nh\u00E2n vi\u00EAn", "Gi\u1EDBi t\u00EDnh", "Ng\u00E0y sinh", "\u0110\u1ECBa ch\u1EC9", "Email", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "Ng\u00E0y tham gia", "Tr\u00ECnh \u0111\u1ED9", "Kinh nghi\u1EC7m", "Ch\u1EE9c v\u1EE5", "Ph\u00F2ng ban"
				}
				));
		scrollPane.setViewportView(table);

		loadNhanVien();

	}
	private void loadNhanVien()
	{
		tableModel.setRowCount(0);
		try {
			List<NhanVien> list = nv_dao.findAll();
			for (NhanVien nhanVien : list) {
				String [] rowData = {
						nhanVien.getMaNV(),nhanVien.getHoTen(),nhanVien.isPhai()==1?"Nam":"Nữ",nhanVien.getNgaySinh()+"",nhanVien.getDiaChi(),nhanVien.getEmail(),nhanVien.getPhone(),nhanVien.getNgayThamGiaCongTac()+"",nhanVien.getTrinhDoChuyenMon(),nhanVien.getKinhNghiem(),nhanVien.getChucVu(),nhanVien.getPhong().getMaPB()
				};
				tableModel.addRow(rowData);
			}
			table.setModel(tableModel);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	//-------Tìm kiếm theo tên--------------------//
	public void timKiemTheoTen() {


		tableModel.setRowCount(0);
		String tenNhanVien = txtTenNhanVien.getText();
		//		for (CongDoan congDoan : congDoan_Dao.timKiemTheoTenCongDoan(tenCongDoan)) {
		//			Object dataRow [] = new Object[4];
		//			dataRow[0] = congDoan.getSanPham().getMaSP();
		//			dataRow[1] = congDoan.getMaCD();
		//			dataRow[2] = congDoan.getTenCongDoan();
		//			dataRow[3] = congDoan.getDonGia();
		//			tableModel.addRow(dataRow);
		//		}
		//		tableCongDoan.setModel(tableModel);
		for (NhanVien nhanVien : nv_dao.findNhanVienByTagName(tenNhanVien)) {

			Object dataRow [] = new Object[12];
			dataRow[0] = nhanVien.getMaNV();
			dataRow[1] = nhanVien.getHoTen();
			dataRow[2] = nhanVien.isPhai()==1?"Nam":"Nữ";
			dataRow[3] = nhanVien.getNgaySinh()+"";
			dataRow[4] = nhanVien.getDiaChi();
			dataRow[5] = nhanVien.getEmail();
			dataRow[6] = nhanVien.getPhone();
			dataRow[7] = nhanVien.getNgayThamGiaCongTac();
			dataRow[8] = nhanVien.getKinhNghiem();
			dataRow[9] =nhanVien.getTrinhDoChuyenMon();
			dataRow[10] =nhanVien.getChucVu();
			dataRow[11] = nhanVien.getPhong().getMaPB();
			tableModel.addRow(dataRow);	

		}
		table.setModel(tableModel);

	}	

	public void timKiemTheoGioiTinh()
	{
		tableModel.setRowCount(0);
		int gioiTinh = Integer.parseInt(txtGioiTinh.getText());
		for (NhanVien nhanVien : nv_dao.findNhanVienByGioiTinh(gioiTinh)) {

			Object dataRow [] = new Object[12];
			dataRow[0] = nhanVien.getMaNV();
			dataRow[1] = nhanVien.getHoTen();
			dataRow[2] = nhanVien.isPhai()==1?"Nam":"Nữ";
			dataRow[3] = nhanVien.getNgaySinh()+"";
			dataRow[4] = nhanVien.getDiaChi();
			dataRow[5] = nhanVien.getEmail();
			dataRow[6] = nhanVien.getPhone();
			dataRow[7] = nhanVien.getNgayThamGiaCongTac();
			dataRow[8] = nhanVien.getKinhNghiem();
			dataRow[9] =nhanVien.getTrinhDoChuyenMon();
			dataRow[10] =nhanVien.getChucVu();
			dataRow[11] = nhanVien.getPhong().getMaPB();
			tableModel.addRow(dataRow);	

		}
		table.setModel(tableModel);

	}
	//-------------Tìm kiếm theo email------------------//
	public void timKiemTheoEmail()
	{
		tableModel.setRowCount(0);
		String email = txtEmail.getText();
		for (NhanVien nhanVien : nv_dao.findNhanVienByEmail(email)) {

			Object dataRow [] = new Object[12];
			dataRow[0] = nhanVien.getMaNV();
			dataRow[1] = nhanVien.getHoTen();
			dataRow[2] = nhanVien.isPhai()==1?"Nam":"Nữ";
			dataRow[3] = nhanVien.getNgaySinh()+"";
			dataRow[4] = nhanVien.getDiaChi();
			dataRow[5] = nhanVien.getEmail();
			dataRow[6] = nhanVien.getPhone();
			dataRow[7] = nhanVien.getNgayThamGiaCongTac();
			dataRow[8] = nhanVien.getKinhNghiem();
			dataRow[9] =nhanVien.getTrinhDoChuyenMon();
			dataRow[10] =nhanVien.getChucVu();
			dataRow[11] = nhanVien.getPhong().getMaPB();
			tableModel.addRow(dataRow);	

		}
		table.setModel(tableModel);

	}
	//------------Tìm kiếm theo địa chỉ------------------//

	public void timKiemTheoDiaChi()
	{
		tableModel.setRowCount(0);
		String diaChi = txtDiaChi.getText();
		for (NhanVien nhanVien : nv_dao.findNhanVienByDiaChi(diaChi)) {

			Object dataRow [] = new Object[12];
			dataRow[0] = nhanVien.getMaNV();
			dataRow[1] = nhanVien.getHoTen();
			dataRow[2] = nhanVien.isPhai()==1?"Nam":"Nữ";
			dataRow[3] = nhanVien.getNgaySinh()+"";
			dataRow[4] = nhanVien.getDiaChi();
			dataRow[5] = nhanVien.getEmail();
			dataRow[6] = nhanVien.getPhone();
			dataRow[7] = nhanVien.getNgayThamGiaCongTac();
			dataRow[8] = nhanVien.getKinhNghiem();
			dataRow[9] =nhanVien.getTrinhDoChuyenMon();
			dataRow[10] =nhanVien.getChucVu();
			dataRow[11] = nhanVien.getPhong().getMaPB();
			tableModel.addRow(dataRow);	

		}
		table.setModel(tableModel);

	}
	//------------Tìm kiếm theo kinh nghiệm-------------//
	public void timKiemTheoKinhNghiem()
	{
		tableModel.setRowCount(0);
		String kinhNghiem = txtKinhNghiem.getText();
		for (NhanVien nhanVien : nv_dao.findNhanVienByKinhNghiem(kinhNghiem)) {

			Object dataRow [] = new Object[12];
			dataRow[0] = nhanVien.getMaNV();
			dataRow[1] = nhanVien.getHoTen();
			dataRow[2] = nhanVien.isPhai()==1?"Nam":"Nữ";
			dataRow[3] = nhanVien.getNgaySinh()+"";
			dataRow[4] = nhanVien.getDiaChi();
			dataRow[5] = nhanVien.getEmail();
			dataRow[6] = nhanVien.getPhone();
			dataRow[7] = nhanVien.getNgayThamGiaCongTac();
			dataRow[8] = nhanVien.getKinhNghiem();
			dataRow[9] =nhanVien.getTrinhDoChuyenMon();
			dataRow[10] =nhanVien.getChucVu();
			dataRow[11] = nhanVien.getPhong().getMaPB();
			tableModel.addRow(dataRow);	

		}
		table.setModel(tableModel);

	}
	//---------------Tìm kiếm theo số didenj thoai--------------------//
	public void timKiemTheoSoDienThoai()
	{
		tableModel.setRowCount(0);
		String soDienthoai = txtSoDienThoai.getText();
		for (NhanVien nhanVien : nv_dao.findNhanVienBySoDienThoai(soDienthoai)) {

			Object dataRow [] = new Object[12];
			dataRow[0] = nhanVien.getMaNV();
			dataRow[1] = nhanVien.getHoTen();
			dataRow[2] = nhanVien.isPhai()==1?"Nam":"Nữ";
			dataRow[3] = nhanVien.getNgaySinh()+"";
			dataRow[4] = nhanVien.getDiaChi();
			dataRow[5] = nhanVien.getEmail();
			dataRow[6] = nhanVien.getPhone();
			dataRow[7] = nhanVien.getNgayThamGiaCongTac();
			dataRow[8] = nhanVien.getKinhNghiem();
			dataRow[9] =nhanVien.getTrinhDoChuyenMon();
			dataRow[10] =nhanVien.getChucVu();
			dataRow[11] = nhanVien.getPhong().getMaPB();
			tableModel.addRow(dataRow);	

		}
		table.setModel(tableModel);

	}
	//-------------------Tìm kiếm theo mã ---------------------------//
	public void timKiemTheoMaNhanVien()
	{
		tableModel.setRowCount(0);
		String maNhanVien = txtMaNhanVien.getText();
		for (NhanVien nhanVien : nv_dao.findNhanVienByMaNhanVien(maNhanVien)) {

			Object dataRow [] = new Object[12];
			dataRow[0] = nhanVien.getMaNV();
			dataRow[1] = nhanVien.getHoTen();
			dataRow[2] = nhanVien.isPhai()==1?"Nam":"Nữ";
			dataRow[3] = nhanVien.getNgaySinh()+"";
			dataRow[4] = nhanVien.getDiaChi();
			dataRow[5] = nhanVien.getEmail();
			dataRow[6] = nhanVien.getPhone();
			dataRow[7] = nhanVien.getNgayThamGiaCongTac();
			dataRow[8] = nhanVien.getKinhNghiem();
			dataRow[9] =nhanVien.getTrinhDoChuyenMon();
			dataRow[10] =nhanVien.getChucVu();
			dataRow[11] = nhanVien.getPhong().getMaPB();
			tableModel.addRow(dataRow);	

		}
		table.setModel(tableModel);

	}
	//-----------------------Tìm kiếm theo chức vụ----------------------------------//
	public void timKiemTheoChucVu()
	{
		tableModel.setRowCount(0);
		String chucVu = txtChucVu.getText();
		for (NhanVien nhanVien : nv_dao.findNhanVienByChucVu(chucVu)) {

			Object dataRow [] = new Object[12];
			dataRow[0] = nhanVien.getMaNV();
			dataRow[1] = nhanVien.getHoTen();
			dataRow[2] = nhanVien.isPhai()==1?"Nam":"Nữ";
			dataRow[3] = nhanVien.getNgaySinh()+"";
			dataRow[4] = nhanVien.getDiaChi();
			dataRow[5] = nhanVien.getEmail();
			dataRow[6] = nhanVien.getPhone();
			dataRow[7] = nhanVien.getNgayThamGiaCongTac();
			dataRow[8] = nhanVien.getKinhNghiem();
			dataRow[9] =nhanVien.getTrinhDoChuyenMon();
			dataRow[10] =nhanVien.getChucVu();
			dataRow[11] = nhanVien.getPhong().getMaPB();
			tableModel.addRow(dataRow);	

		}
		table.setModel(tableModel);

	}
}
