package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connect.connectDB;
import dao.ChamCong_CongNhan_DAO;
import dao.CongDoan_DAO;
import dao.CongNhan_DAO;
import dao.LuongCongNhan_DAO;
import dao.LuongNhanVien_DAO;
import entity.ChamCong_CongNhan;
import entity.ChamCong_NhanVien;
import entity.CongDoan;
import entity.CongNhan;
import entity.LuongNhanVien;
import entity.NhanVien;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class LuongCongNhan extends JPanel implements ActionListener, MouseListener{
	private JTable table_cc;
	private JTable table_lcn;
	private JTextField txtMa;
	private JTextField txtThuong;
	private JTextField txtPhat;
	private JTextField txtPhuCap;
	private JTextField txtBHXH;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private JButton btnTaoMoi;
	private JButton btnXoa;
	private JButton btnCapNhat;
	private JButton btnTinhLuong;
	private JButton btnLamMoiBang;
	private CongNhan_DAO cn_dao;
	private DefaultTableModel tableModel_lcn;
	private DefaultTableModel tableModel_cn;
	private JComboBox cboMaCongDoan;
	private JComboBox cboTenCongDoan;
	private CongDoan_DAO cd_dao;
	private ChamCong_CongNhan_DAO cccn_dao;
	private LuongCongNhan_DAO lcn_dao;
	private JLabel lblNewLabel;
	private JComboBox cboThang;
	private JComboBox cboNam;

	/**
	 * Create the panel.
	 */
	public LuongCongNhan() {
		connectDB.getInstance().connect();
		cn_dao = new CongNhan_DAO();
		cd_dao = new CongDoan_DAO();
		cccn_dao = new ChamCong_CongNhan_DAO();
		lcn_dao = new LuongCongNhan_DAO();
		setLayout(null);

		JLabel lbl_tt_bcc = new JLabel("B\u1EA2NG CH\u1EA4M C\u00D4NG");
		lbl_tt_bcc.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_tt_bcc.setBounds(89, 197, 248, 35);
		add(lbl_tt_bcc);

		JScrollPane scroll_cc = new JScrollPane();
		scroll_cc.setBounds(20, 248, 383, 502);
		add(scroll_cc);

		table_cc = new JTable();
		table_cc.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		table_cc.setModel(tableModel_cn = new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
				},
				new String[] {
						"M\u00E3 c\u00F4ng nh\u00E2n", "H\u1ECD t\u00EAn", "Ph\u00E1i", "Ng\u00E0y sinh", "\u0110\u1ECBa ch\u1EC9", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "Chuy\u00EAn m\u00F4n"
				}
				));
		scroll_cc.setViewportView(table_cc);

		JLabel lbl_tt_lcn = new JLabel("THÔNG TIN LƯƠNG CÔNG NHÂN");
		lbl_tt_lcn.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_tt_lcn.setBounds(788, 263, 440, 35);
		add(lbl_tt_lcn);

		JScrollPane scroll_lcn = new JScrollPane();
		scroll_lcn.setBounds(428, 308, 1090, 378);
		add(scroll_lcn);

		table_lcn = new JTable();
		table_lcn.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		table_lcn.setModel(tableModel_lcn = new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 LCN", "Ph\u1EE5 c\u1EA5p", "Th\u01B0\u1EDFng", "Ph\u1EA1t", "BHXH", "S\u1ED1 ng\u00E0y c\u00F4ng", "M\u00E3 CD", "S\u1ED1 c\u00F4ng \u0111o\u1EA1n ho\u00E0n th\u00E0nh", "M\u00E3 CN", "Tháng lương", "T\u1ED5ng l\u01B0\u01A1ng"
			}
		));
		scroll_lcn.setViewportView(table_lcn);

		JLabel lblPhuCap = new JLabel("Phụ cấp:");
		lblPhuCap.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblPhuCap.setBounds(900, 124, 73, 29);
		add(lblPhuCap);

		JLabel lblThuong = new JLabel("Thưởng:");
		lblThuong.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblThuong.setBounds(900, 42, 84, 29);
		add(lblThuong);

		JLabel lblPhat = new JLabel("Phạt:");
		lblPhat.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblPhat.setBounds(1261, 50, 55, 29);
		add(lblPhat);

		JLabel lblBHXH = new JLabel("BHXH:");
		lblBHXH.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblBHXH.setBounds(1261, 124, 73, 29);
		add(lblBHXH);

		JLabel lblMa = new JLabel("Mã lương công nhân:");
		lblMa.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMa.setBounds(428, 50, 163, 29);
		add(lblMa);

		txtMa = new JTextField();
		txtMa.setEnabled(false);
		txtMa.setFont(new Font("Times New Roman", Font.BOLD, 17));
		txtMa.setBounds(601, 56, 223, 22);
		add(txtMa);
		txtMa.setText(autoMaHD());
		txtMa.setColumns(10);

		txtThuong = new JTextField();
		txtThuong.setFont(new Font("Times New Roman", Font.BOLD, 17));
		txtThuong.setBounds(994, 49, 207, 19);
		add(txtThuong);
		txtThuong.setColumns(10);

		txtPhat = new JTextField();
		txtPhat.setFont(new Font("Times New Roman", Font.BOLD, 17));
		txtPhat.setBounds(1344, 48, 174, 22);
		add(txtPhat);
		txtPhat.setColumns(10);

		txtPhuCap = new JTextField();
		txtPhuCap.setFont(new Font("Times New Roman", Font.BOLD, 17));
		txtPhuCap.setBounds(994, 131, 207, 19);
		add(txtPhuCap);
		txtPhuCap.setColumns(10);

		txtBHXH = new JTextField();
		txtBHXH.setFont(new Font("Times New Roman", Font.BOLD, 17));
		txtBHXH.setBounds(1344, 131, 174, 19);
		add(txtBHXH);
		txtBHXH.setColumns(10);

		btnTinhLuong = new JButton("Tính lương");
		btnTinhLuong.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnTinhLuong.setBounds(620, 193, 174, 46);
		add(btnTinhLuong);

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCapNhat.setBounds(818, 193, 166, 46);
		add(btnCapNhat);

		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnXoa.setBounds(1020, 193, 121, 46);
		add(btnXoa);

		btnTaoMoi = new JButton("Tạo mới");
		btnTaoMoi.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnTaoMoi.setBounds(1174, 193, 145, 46);
		add(btnTaoMoi);

		JLabel lblTimKiem = new JLabel("Nhập để tìm:");
		lblTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTimKiem.setBounds(428, 696, 114, 40);
		add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtTimKiem.setBounds(540, 705, 734, 27);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);

		btnTimKiem = new JButton();
		btnTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnTimKiem.setBounds(1284, 696, 116, 40);
		add(btnTimKiem);
		//
		btnTimKiem.setIcon(new ImageIcon("image/search.png"));
		btnTinhLuong.setIcon(new ImageIcon("image/salaryh_.png"));
		btnTaoMoi.setIcon(new ImageIcon("image/create.png"));
		btnXoa.setIcon(new ImageIcon("image/delete.png"));
		btnCapNhat.setIcon(new ImageIcon("image/exchange.png"));
		btnTimKiem.setIcon(new ImageIcon("image/search.png"));

		btnLamMoiBang = new JButton("");
		btnLamMoiBang.setBounds(1412, 696, 106, 40);
		add(btnLamMoiBang);
		btnLamMoiBang.setIcon(new ImageIcon("image/rs.png"));

		JLabel lblTenCongDoan = new JLabel("Tên công đoan:");
		lblTenCongDoan.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTenCongDoan.setBounds(30, 42, 121, 29);
		add(lblTenCongDoan);

		JLabel lblMaCongDoan = new JLabel("Mã công đoạn:");
		lblMaCongDoan.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMaCongDoan.setBounds(31, 110, 120, 29);
		add(lblMaCongDoan);
		
		List<CongDoan> l = cd_dao.findAll();

		cboTenCongDoan = new JComboBox();
		cboTenCongDoan.setBounds(177, 48, 160, 21);
		add(cboTenCongDoan);
		cboTenCongDoan.addItem("Chọn");
		// load ten cong doan
		for (CongDoan c : l) {
			cboTenCongDoan.addItem(c.getTenCongDoan());
		}

		cboMaCongDoan = new JComboBox();
		cboMaCongDoan.setBounds(177, 130, 160, 21);
		add(cboMaCongDoan);
		cboMaCongDoan.addItem("");
		
		lblNewLabel = new JLabel("Tháng:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel.setBounds(428, 127, 61, 22);
		add(lblNewLabel);
		
		cboThang = new JComboBox();
		cboThang.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cboThang.setBounds(499, 130, 61, 21);
		add(cboThang);
		
		JLabel lblNewLabel_1 = new JLabel("Năm:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_1.setBounds(601, 127, 55, 22);
		add(lblNewLabel_1);
		
		cboNam = new JComboBox();
		cboNam.setModel(new DefaultComboBoxModel(new String[] {"2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"}));
		cboNam.setBounds(667, 130, 67, 21);
		add(cboNam);
		// load ma cong doan
		for (CongDoan c : l) {
			cboMaCongDoan.addItem(c.getMaCD());
		}
		//
		loadDataFromTable();
		loadLuong();
	}
	// ham load cong nhan
	public void loadDataFromTable(){
		CongNhan_DAO dao = new CongNhan_DAO();
		List<CongNhan> list = dao.findAll();
		tableModel_cn.setRowCount(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		for (CongNhan nv : list) {
			tableModel_cn.addRow(new Object[] {nv.getMaCN(),
					nv.getHoTen(), nv.isPhai() == 1 ? "Nam" : "Nữ",
							sdf.format(nv.getNgaySinh()), nv.getDiaChi(), nv.getPhone() ,
							nv.getChuyenMon()});
		}
		table_cc.setModel(tableModel_cn);
	}

	//
	// auto ma luong cong nhan
	public String autoMaHD() {
		String str;
		int ma = lcn_dao.getMaHd();
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
	// tinh so cong doan lam duoc
	public int soNgayCong(String ma, int month, int year) {
		int sum = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<ChamCong_CongNhan> l = cccn_dao.getAllBangChamCongCongNhan();
		for (ChamCong_CongNhan c : l) {
			String s1 = sdf.format(c.getNgayChamCong());
			java.util.Date date1 = null;
			try {
				date1 = sdf.parse(s1);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			if(ma.equals(c.getCongNhan().getMaCN())) {
				if(date1.getMonth()+1 == month && date1.getYear()+1900 == year) {
					sum++;
				}
			}
		}
		return sum;
	}
	//
	public int soCongDoanHoanThanh(String ma, int month, int year) {
		int sum = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<ChamCong_CongNhan> l = cccn_dao.getAllBangChamCongCongNhan();
		for (ChamCong_CongNhan c : l) {
			String s1 = sdf.format(c.getNgayChamCong());
			java.util.Date date1 = null;
			try {
				date1 = sdf.parse(s1);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			if(ma.equals(c.getCongNhan().getMaCN())) {
				if(date1.getMonth()+1 == month && date1.getYear()+1900 == year) {
					sum += c.getSoCongDoanHoanThanh();
				}
			}
		}
		return sum;
	}
	//
	
	// load tat ca luong 
	public void loadLuong() {
		tableModel_lcn.setRowCount(0);
		ArrayList<entity.LuongCongNhan> l = lcn_dao.getallLuongCongNhan();
		for (entity.LuongCongNhan c : l) {
			tableModel_lcn.addRow(new Object[] {
					c.getMaLCN(), c.getPhuCap(), c.getThuong(), c.getPhat(),
					c.getBhxh(), c.getSoNgayCong(),c.getTongLuong(), c.getThangLuong()
			});
		}
		table_lcn.setModel(tableModel_lcn);
	}
	//
//	public void addTinhLuong() throws ParseException {
//		Locale localeVN = new Locale("vi", "VN");
//		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
//		String mal = txtMa.getText();
//		double luong = 0;
//		int row = table_cc.getSelectedRow();
//		String ma = table_cc.getValueAt(row, 0).toString();
//		int snc = soNgayCong(ma, Integer.parseInt(cboThang.getSelectedItem().toString()),
//				Integer.parseInt(cboNam.getSelectedItem().toString()));
//		int cdht = soCongDoanHoanThanh(ma, Integer.parseInt(cboThang.getSelectedItem().toString()),
//				Integer.parseInt(cboNam.getSelectedItem().toString()));
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
//		double thuong;
//		double phat = Double.parseDouble(txtPhat.getText());
//		double phuCap;
//		CongNhan nv = new CongNhan(table_cc.getValueAt(row, 0).toString());
//		if(snc > 25) {
//			thuong = 500000;
//			phuCap = 500000;
//		}
//		else {
//			thuong = 0;
//			phuCap = 0;
//		}
//		
//		if(txtPhat.getText() != "0") {
//			luong = cdht *  + phuCap + thuong;
//		}
//		else {
//			luong = l / 26 * snc + phuCap + thuong - phat;
//		}
//		double bhxh = luong*0.08;
//		luong = luong - bhxh;
//		LuongNhanVien cn = new LuongNhanVien(mal, phuCap, thuong, phat, bhxh, snc, luong, nv, da);
//		LuongNhanVien_DAO dao = new LuongNhanVien_DAO();
//		if(regex()) {
//			if(dao.insert(cn)) {
//				tableModel_lnv.addRow(new Object[] {table_cc.getValueAt(row, 0).toString(), table_cc.getValueAt(row, 1).toString(), cboMaPhong.getSelectedItem().toString(), cboPhong.getSelectedItem().toString(),
//						cn.getMaLNV(), currencyVN.format(cn.getPhuCap()), currencyVN.format(cn.getThuong()),
//						currencyVN.format(cn.getPhat()), currencyVN.format(cn.getBhxh()), cn.getSoNgayCong(), currencyVN.format(cn.getTongLuong()), f.format(cn.getThangLuong())});
//				JOptionPane.showMessageDialog(this, "Thêm thành công");
//				tableModel_cc.removeRow(row);
//				lblTongLuong.setText(currencyVN.format(luong)+"");
//
//			}else {
//				JOptionPane.showMessageDialog(this, "Không thêm được");
//			}
//		}
//	}
	@Override
	public void mouseClicked(MouseEvent e) {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnTinhLuong)) {
			
		}
		else if(o.equals(btnCapNhat)) {
			
		}
		else if(o.equals(btnTaoMoi)) {
			
		}
	}
	// class mouse click table cn
	public class CongNhan_MouseClick implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int row = table_cc.getSelectedRow();
			
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
