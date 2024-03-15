package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import connect.connectDB;
import dao.ChamCong_NhanVien_DAO;
import dao.NhanVien_DAO;
import dao.PhongBan_DAO;
import entity.ChamCong_NhanVien;
import entity.CongDoan;
import entity.NhanVien;
import entity.PhongBan;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ChamCong_NhanVien_GUI extends JPanel implements ActionListener {
	private JTable tableThongTinPC;
	private DefaultTableModel tableModelBCC;
	private NhanVien_DAO nv_dao;
	private DefaultTableModel tableModelThongTinBCC;
	private DefaultTableModel tableModelNhanVien;
	private JButton btnCapNhat;
	private JButton btnChamCong;
	private JButton btnXoa;
	private JButton btnLayDanhSachChamCong;
	private JTextField txtMaCong;
	private ChamCong_NhanVien_DAO dao_congNhanVien;
	private JDateChooser ngayChamCong;
	private JComboBox cboMaPhongBan;
	private JTable tableNhanVien;
	private JComboBox cboPhongBan;

	/**
	 * Create the panel.
	 */
	public ChamCong_NhanVien_GUI() {
		connectDB.getInstance().connect();
		nv_dao = new NhanVien_DAO();
		dao_congNhanVien = new ChamCong_NhanVien_DAO();

		
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setLayout(null);
		//-------------TiĂªu Ä‘á»�----------------//
		JLabel lblTieuDe = new JLabel("CHẤM CÔNG NHÂN VIÊN");
		lblTieuDe.setForeground(Color.BLACK);
		lblTieuDe.setBackground(new Color(230, 230, 250));
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTieuDe.setBounds(740, 26, 326, 55);
		add(lblTieuDe);

		JLabel lblBangChamCong = new JLabel("BẢNG CHẤM CÔNG");
		lblBangChamCong.setForeground(Color.BLACK);
		lblBangChamCong.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblBangChamCong.setBounds(404, 99, 204, 24);
		add(lblBangChamCong);

		JScrollPane scrollPaneChamCong = new JScrollPane();
		scrollPaneChamCong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}

		});
		scrollPaneChamCong.setBounds(49, 132, 913, 290);
		add(scrollPaneChamCong);

		tableNhanVien = new JTable();
		tableNhanVien.setModel( tableModelNhanVien = new DefaultTableModel(
				new Object[][] {

				},
				new String[] {
						"M\u00E3 nh\u00E2n vi\u00EAn", "T\u00EAn nh\u00E2n vi\u00EAn", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i","Phòng Ban","Trạng thái" ,"\u0110i\u1EC3m danh", "Ph\u00E9p"
				}
				) {
			Class[] columnTypes = new Class[] {
					Object.class, Object.class, Object.class,Object.class,Object.class, Boolean.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPaneChamCong.setViewportView(tableNhanVien);



		btnChamCong = new JButton("Chấm công");
		btnChamCong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		//btnNewButton.setIcon(new ImageIcon(ChamCongCongNhan_GUI.class.getResource("/image/add.png")));
		btnChamCong.setBackground(new Color(224, 255, 255));
		btnChamCong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ChamCong();
			}
		});
		btnChamCong.setBounds(1071, 381, 119, 41);
		add(btnChamCong);

		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 13));
		//btnCpNht.setIcon(new ImageIcon(ChamCongCongNhan_GUI.class.getResource("/image/exchange.png")));
		btnXoa.setBackground(new Color(224, 255, 255));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoa.setBounds(1229, 381, 105, 41);
		add(btnXoa);

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 13));
		//btnXa.setIcon(new ImageIcon(ChamCongCongNhan_GUI.class.getResource("/image/delete.png")));
		btnCapNhat.setBackground(new Color(224, 255, 255));
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCapNhat.setBounds(1366, 381, 112, 41);
		add(btnCapNhat);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		scrollPane.setBounds(49, 487, 1429, 246);
		add(scrollPane);

		tableThongTinPC = new JTable();
		tableThongTinPC.setFont(new Font("Times New Roman", Font.BOLD, 9));
		tableThongTinPC.setModel(tableModelThongTinBCC=new DefaultTableModel(
				new Object[][] {

				},
				new String[] {
						" M\u00E3 ch\u1EA5m c\u00F4ng", "Ng\u00E0y ch\u1EA5m c\u00F4ng", "M\u00E3 nh\u00E2n vi\u00EAn", "T\u00EAn nh\u00E2n vi\u00EAn", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "Tr\u1EA1ng th\u00E1i", "Ph\u00E9p"
				}
				));
		scrollPane.setViewportView(tableThongTinPC);

		btnLayDanhSachChamCong = new JButton("Lấy danh sách nhân viên");
		btnLayDanhSachChamCong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnLayDanhSachChamCong.setBounds(1071, 340, 409, 31);
		add(btnLayDanhSachChamCong);


		// Sá»± kiá»‡n
		btnLayDanhSachChamCong.addActionListener(this);

		JLabel lblNewLabel = new JLabel("Mã công");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel.setBounds(1071, 132, 81, 24);
		add(lblNewLabel);

		txtMaCong = new JTextField();
		txtMaCong.setBounds(1187, 136, 291, 19);
		add(txtMaCong);
		txtMaCong.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Ngày công");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1.setBounds(1071, 193, 95, 20);
		add(lblNewLabel_1);

		ngayChamCong = new JDateChooser();
		ngayChamCong.setBounds(1187, 193, 291, 20);
		add(ngayChamCong);

		JLabel lblCaLmVic = new JLabel("Mã phòng ban");
		lblCaLmVic.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblCaLmVic.setBounds(1071, 232, 95, 31);
		add(lblCaLmVic);

		cboMaPhongBan = new JComboBox();
		cboMaPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		cboMaPhongBan.setBounds(1187, 238, 285, 21);
		cboMaPhongBan.addItem("");
		add(cboMaPhongBan);
		
		
		
		JLabel lblCaLmVic_1 = new JLabel("Tên phòng ban");
		lblCaLmVic_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblCaLmVic_1.setBounds(1071, 273, 95, 31);
		add(lblCaLmVic_1);
		
		cboPhongBan = new JComboBox();
		cboPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		cboPhongBan.setBounds(1187, 278, 285, 21);
		cboPhongBan.addItem("Chọn");
		add(cboPhongBan);
		
		
		try {
			PhongBan_DAO pb_dao = new PhongBan_DAO();
			List<PhongBan> phongBan = phongBan = pb_dao.findAll();
			for (PhongBan phongBan2 : phongBan) {
				cboPhongBan.addItem(phongBan2.getTenPhong());
			}for (PhongBan phongBan1 : phongBan) {
				cboMaPhongBan.addItem(phongBan1.getMaPB());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		cboPhongBan.addItemListener(new ItemListener() {
			PhongBan_DAO pb_dao = new PhongBan_DAO();
			public void itemStateChanged(ItemEvent e) {
				
				String maPhong = cboMaPhongBan.getSelectedItem().toString();
				String tenPhong = cboPhongBan.getSelectedItem().toString();
				PhongBan pb1 = new PhongBan(maPhong, tenPhong);
				List<PhongBan> pb ;
				List<NhanVien> nhanVien = nv_dao.getallNhanVienDangLamViec();
				//List<ChamCong_NhanVien> chamCong = dao_congNhanVien.getAllBangChamCong();
				try {

					pb = pb_dao.findAll();
					for (PhongBan phongBan : pb) {
						if(e.getStateChange()==ItemEvent.SELECTED) {
							if(!cboPhongBan.getSelectedItem().toString().equals("Chọn")) {
								if(cboPhongBan.getSelectedItem().equals(phongBan.getTenPhong())) {
									cboMaPhongBan.setSelectedItem(phongBan.getMaPB());
									 tableModelNhanVien.setRowCount(0);
									for (NhanVien nhanVien2 : nhanVien) {
										
										if(nhanVien2.getPhong().getMaPB().equals(cboMaPhongBan.getSelectedItem().toString())) {
											tableModelNhanVien.addRow(new Object[] {
													nhanVien2.getMaNV(),nhanVien2.getHoTen(),nhanVien2.getPhone(),nhanVien2.getPhong().getTenPhong(),nhanVien2.getTrangThai(),true,false	
											});


										}

									}
									tableNhanVien.setModel(tableModelNhanVien);

								}
							}
						}

					}
				} catch (Exception e2) {
					// TODO: handle exception

				}
			}
		});
		
		
		
		
		
		//		loadBangNhanVien();
		loadDanhSachChamCong();
		btnChamCong.addActionListener(this);
		btnXoa.addActionListener(this);

		JLabel lblThngTinBng = new JLabel("THÔNG TIN BẢNG CHẤM CÔNG");
		lblThngTinBng.setForeground(Color.BLACK);
		lblThngTinBng.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblThngTinBng.setBounds(644, 453, 318, 24);
		add(lblThngTinBng);
		txtMaCong.setText(autoMaHD());
		
	}



	private void ChamCong()


	{   ChamCong_NhanVien congNhanVien = new ChamCong_NhanVien();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String maCong1 = txtMaCong.getText();
	int index = tableNhanVien.getSelectedRow();
	boolean phep1 = Boolean.parseBoolean(tableNhanVien.getValueAt(index, 6).toString());
	System.out.println(phep1);
	String tenNhanVien = tableNhanVien.getValueAt(index, 1).toString();
	String tenPhone = tableNhanVien.getValueAt(index, 2).toString();
	String maNhanVien =  tableNhanVien.getValueAt(index, 0).toString();
	boolean diemDanh1 = Boolean.parseBoolean(tableNhanVien.getValueAt(index, 5).toString());
	System.out.println(diemDanh1);
	NhanVien nhanVien = new NhanVien(maNhanVien,tenNhanVien,tenPhone);
	String ncc = sdf.format(ngayChamCong.getDate());
	Date ngayChamCong = Date.valueOf(ncc);
	

	//int soGioTangCa = Integer.parseInt(txtSoGioTangCa.getText());

	congNhanVien = new ChamCong_NhanVien(maCong1, diemDanh1, phep1, ngayChamCong,  nhanVien);



	try {

		int hoiNhac = JOptionPane.showConfirmDialog(this,"Bạn có muốn chấm công không","Chú ý",JOptionPane.YES_NO_OPTION);
		if(hoiNhac==JOptionPane.YES_OPTION)
		{
			if(dao_congNhanVien.chamCongNhanVien(congNhanVien))
			{
				tableModelThongTinBCC.addRow(new Object []  {
						congNhanVien.getMaCCNV(),congNhanVien.getNgayChamCong(),
						congNhanVien.getNhanVien().getMaNV(),
						congNhanVien.getNhanVien().getHoTen(),congNhanVien.getNhanVien().getPhone(),
						congNhanVien.isDiemDanh()?"Có mặt":"Vắng mặt",congNhanVien.isPhep()?"Có phép":"Không phép"
				});
				tableThongTinPC.setModel(tableModelThongTinBCC);
				JOptionPane.showMessageDialog(this,"Chấm công thành công");
			}else
			{
				JOptionPane.showMessageDialog(this,"Trùng mã!");
			}
		}

	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}





	}

	public void xoaBangChamCong ()
	{
		int row = tableThongTinPC.getSelectedRow();
		if(row>=0)
		{
			String maCong = (String) tableThongTinPC.getValueAt(row, 0);
			int hoiNhac = JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa không","Chú ý",JOptionPane.YES_NO_OPTION);
			if(hoiNhac == JOptionPane.YES_OPTION)
			{
				if(dao_congNhanVien.xoaBangChamCong(maCong))
				{
					tableModelThongTinBCC.removeRow(row);
					JOptionPane.showMessageDialog(this,"Xóa thành công");
				}else
				{
					JOptionPane.showMessageDialog(this,"Xóa không thành công");
				}

			}

		}

	}




	private void setExtendedState(int maximizedBoth) {
		// TODO Auto-generated method stub
		setSize(new Dimension(1540, 847));

	}

	private void loadBangNhanVien()
	{

		tableModelNhanVien.setRowCount(0);
		List<NhanVien> list = nv_dao.getallNhanVienDangLamViec();
		for (NhanVien nhanVien : list) {

			tableModelNhanVien.addRow(new Object[] {
					nhanVien.getMaNV(),nhanVien.getHoTen(),nhanVien.getPhone(),nhanVien.getPhong().getTenPhong(),nhanVien.getTrangThai(),true,false
			});
		}
		tableNhanVien.setModel(tableModelNhanVien);

	}
	public void loadDataFromTable() throws SQLException, ClassNotFoundException {
		NhanVien_DAO dao = new NhanVien_DAO();
		List<NhanVien> list = dao.findAll();
		tableModelNhanVien.setRowCount(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (NhanVien nv : list) {
			tableModelNhanVien.addRow(new Object[] {nv.getMaNV(), nv.getHoTen(), nv.isPhai() == 1 ? "Nam" : "Nữ",
					sdf.format(nv.getNgaySinh()), nv.getDiaChi(), nv.getEmail(), nv.getPhone() ,
					sdf.format(nv.getNgayThamGiaCongTac()), nv.getTrinhDoChuyenMon(), nv.getKinhNghiem(), nv.getChucVu(), nv.getPhong().getMaPB()});
		}
		tableNhanVien.setModel(tableModelNhanVien);
	}
	private void loadDanhSachChamCong()
	{
		List<ChamCong_NhanVien> list = dao_congNhanVien.getAllBangChamCong();
		for (ChamCong_NhanVien congNhanVien : list) {
			String [] rowData = {
					congNhanVien.getMaCCNV(),congNhanVien.getNgayChamCong()+"",
					
					congNhanVien.getNhanVien().getMaNV(),congNhanVien.getNhanVien().getHoTen(),
					congNhanVien.getNhanVien().getPhone(),congNhanVien.isDiemDanh()?"Có mặt":"Vắng mặt",
							congNhanVien.isPhep()?"Có phép":"Không phép" 
			};
			tableModelThongTinBCC.addRow(rowData);
		}
		tableThongTinPC.setModel(tableModelThongTinBCC);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnLayDanhSachChamCong))
		{   
			loadBangNhanVien();
		}else if (o.equals(btnChamCong)) {
			ChamCong();
			txtMaCong.setText(autoMaHD());
		}else if(o.equals(btnXoa))
		{
			xoaBangChamCong();
			txtMaCong.setText(autoMaHD());
		}
	}
	public String autoMaHD() {
		String str;
		int ma = dao_congNhanVien.getMaHd();
		if (ma < 100)
			str = "CC0000" + ma;
		else if (ma < 1000)
			str = "CC000" + ma;
		else if (ma < 10000)
			str = "CC00" + ma;
		else if (ma < 100000)
			str = "CC0" + ma;
		else
			str = "CC" + ma;
		return str;

	}
}

