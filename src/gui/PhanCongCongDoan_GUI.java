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
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import connect.connectDB;
import dao.CongDoan_DAO;
import dao.CongNhan_DAO;
import dao.PhanCongCongDoan_DAO;
import dao.SanPham_DAO;
import entity.CongDoan;
import entity.CongNhan;
import entity.PhanCong_CongDoan;
import entity.SanPham;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

import java.awt.Panel;
import javax.swing.border.TitledBorder;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.Action;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;

public class PhanCongCongDoan_GUI extends JPanel implements ActionListener ,MouseListener, EventListener {
	private JTable tableCongDoan;
	private JTable tablePhanCong;
	private JTextField txtTim;
	private JButton btnTim;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnLamMoi;
	private JTable tableCongNhan;
	private DefaultTableModel tableModelCN;
	private DefaultTableModel tableModelCD;
	private CongNhan_DAO congNhan_Dao ;
	private CongDoan_DAO congDoan_Dao ;
	private PhanCongCongDoan_DAO phanCongCD_Dao ;
	//private PhanCs
	private JButton btnTaoMoi;
	private JButton btnPhanCong;
	private JButton btnCapNhat;
	private JDateChooser dateNgayPhanCong;
	private DefaultTableModel tableModelPhanCong;
	private SimpleDateFormat sdf ;
	private JTextField txtMaCongNhan;
	private DefaultTableModel tableModel;
	private JTextField txtTenCongNhan;
	private JTextField txtDonGia;
	private int rowSpan;
	private JComboBox cboMaSanPham;
	private JComboBox cboTenSanPham;
	private SanPham_DAO sp_dao;
	private JComboBox cboChuyenMon;
	private JComboBox cboMaCongDoan;
	/**
	 * Create the panel. 
	 * 
	 */
	public PhanCongCongDoan_GUI() {
		sdf =new SimpleDateFormat("yyyy-MM-dd");

		congNhan_Dao = new CongNhan_DAO();
		congDoan_Dao = new CongDoan_DAO();
		phanCongCD_Dao = new PhanCongCongDoan_DAO();
		sp_dao = new SanPham_DAO();
		connectDB.getInstance().connect();


		setBackground(UIManager.getColor("CheckBox.light"));
		setLayout(null);
		//---------------------TiĂªu Ä‘á»�---------------------------------//
		JLabel lblTieuDe = new JLabel("PHÂN CÔNG CÔNG ĐOẠN");
		lblTieuDe.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTieuDe.setBounds(630, 10, 310, 29);
		add(lblTieuDe);
		//----------------------NgĂ y phĂ¢n cĂ´ng----------------------//
		JLabel lblNgayPhanCong = new JLabel("Ngày phân công:");
		lblNgayPhanCong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNgayPhanCong.setBounds(782, 132, 144, 29);
		add(lblNgayPhanCong);
		dateNgayPhanCong = new JDateChooser();
		dateNgayPhanCong.getSpinner().setEnabled(false);
		//dateNgayPhanCong.getSpinner().setEnabled(false);
		dateNgayPhanCong.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 10));
		dateNgayPhanCong.setBounds(937, 132, 425, 19);
		dateNgayPhanCong.setDateFormatString("yyyy-MM-dd");
		add(dateNgayPhanCong);

		//-----------------------Báº£ng cĂ´ng nhĂ¢n-------------------------//
		JLabel lblBagCongNhan = new JLabel("BẢNG CÔNG NHÂN");
		lblBagCongNhan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBagCongNhan.setBounds(407, 140, 180, 29);
		add(lblBagCongNhan);
		JScrollPane scrollCN = new JScrollPane();
		scrollCN.setBounds(407, 179, 322, 283);
		add(scrollCN);

		tableCongNhan = new JTable();
		String [] headers = "Mã công nhân;Tên công nhân".split(";");
		tableModelCN=new DefaultTableModel(headers,0);
		scrollCN.setViewportView(tableCongNhan = new JTable(tableModelCN));





		//------------------------Báº£ng cĂ´ng Ä‘oáº¡n----------------------//
		JLabel lblMaPhanCong_1_1 = new JLabel("BẢNG CÔNG ĐOẠN");
		lblMaPhanCong_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaPhanCong_1_1.setBounds(83, 140, 187, 29);
		add(lblMaPhanCong_1_1);

		JScrollPane scrollCongDoan = new JScrollPane();
		scrollCongDoan.setBounds(21, 179, 376, 283);
		add(scrollCongDoan);
		//		tableCD = new JTable();
		//		tableCD.setModel(new DefaultTableModel(
		//				new Object[][] {
		//					{null, " ", null},
		//				},
		//				new String[] {
		//						"MĂ£ cĂ´ng Ä‘oáº¡n","TĂªn cĂ´ng Ä‘oáº¡n","Ä�Æ¡n giĂ¡ cĂ´ng Ä‘oáº¡n"
		//				}
		//				));
		String [] headersCongDoan = "Mã công đoạn;Tên công đoạn;Đơn giá;Trạng thái;Thứ tự".split(";");
		tableModelCD=new DefaultTableModel(headersCongDoan,0);
		scrollCongDoan.setViewportView(tableCongDoan = new JTable(tableModelCD));
		tableCongDoan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


		scrollCongDoan.setViewportView(tableCongDoan);



		//--------------------Báº£ng phĂ¢n cĂ´ng----------------------//
		JLabel lblBangPhanCong = new JLabel("BẢNG PHÂN CÔNG CÔNG ĐOẠN");
		lblBangPhanCong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBangPhanCong.setBounds(760, 443, 301, 29);
		add(lblBangPhanCong);

		JScrollPane scrollPhanCong = new JScrollPane();
		scrollPhanCong.setBounds(21, 482, 1483, 262);
		add(scrollPhanCong);

		tablePhanCong = new JTable();
		String [] headerPhanCong ="Mã công nhân;Tên công nhân;Mã công đoạn;Tên công đoạn;Ngày phân công;Đơn giá".split(";");
		tableModelPhanCong = new DefaultTableModel(headerPhanCong,0);
		scrollPhanCong.setViewportView(tablePhanCong = new JTable(tableModelPhanCong) );


		btnPhanCong = new JButton("Phân Công");
		btnPhanCong.setBounds(786, 395, 101, 36);
		add(btnPhanCong);

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBounds(1106, 395, 101, 36);
		add(btnCapNhat);

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(938, 395, 101, 36);
		add(btnXoa);
		btnTaoMoi = new JButton("Tạo mới");

		btnTaoMoi.setBounds(1261, 395, 101, 36);
		add(btnTaoMoi);
		//////		
		//////		


		// Sá»± kiá»‡n




		JLabel lblMaCongNhan = new JLabel("Mã công nhân:");
		lblMaCongNhan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaCongNhan.setBounds(782, 171, 136, 29);
		add(lblMaCongNhan);

		txtMaCongNhan = new JTextField();
		txtMaCongNhan.setEditable(false);
		txtMaCongNhan.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtMaCongNhan.setColumns(10);
		txtMaCongNhan.setBounds(937, 178, 425, 19);
		add(txtMaCongNhan);

		JLabel lblMaCongDoan = new JLabel("Mã công đoạn:");
		lblMaCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaCongDoan.setBounds(782, 249, 136, 29);
		add(lblMaCongDoan);



		JLabel lblTnCngNhn = new JLabel("Tên công nhân:");
		lblTnCngNhn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnCngNhn.setBounds(782, 210, 136, 29);
		add(lblTnCngNhn);

		txtTenCongNhan = new JTextField();
		txtTenCongNhan.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtTenCongNhan.setColumns(10);
		txtTenCongNhan.setBounds(937, 216, 425, 19);
		add(txtTenCongNhan);

		JLabel lblTnCngon = new JLabel("Tên công đoạn: ");
		lblTnCngon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnCngon.setBounds(782, 288, 136, 29);
		add(lblTnCngon);

		String cm [] = "Lên khuôn giày;Khâu dập và may da;Hoàn thiện phần mũi giày;Hoàn thiện phần đế và phần trang trí;Kiểm tra lại thành phẩm".split(";");

		JLabel lblnGi = new JLabel("Đơn giá:");
		lblnGi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblnGi.setBounds(782, 325, 136, 29);
		add(lblnGi);

		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(937, 335, 425, 19);
		add(txtDonGia);
		btnPhanCong.addActionListener(this);
		btnXoa.addActionListener(this);

		cboTenSanPham = new JComboBox();
		cboMaSanPham = new JComboBox();
		cboMaSanPham.addItem("");
		cboTenSanPham.addItem("Chọn");

		try {
			List<SanPham> p = p = sp_dao.findAll();
			for (SanPham s : p) {
				if(s.getTrangThai().equals("Đang tiến hành"))
					cboTenSanPham.addItem(s.getTenSanPham());
			}
			for (SanPham sp : p) {
				cboMaSanPham.addItem(sp.getMaSP());
			}
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}


		cboTenSanPham.setBounds(136, 69, 425, 21);
		add(cboTenSanPham);
		cboTenSanPham.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				List<SanPham> p;
				List<CongDoan> l = congDoan_Dao.getAllCongDoan();
				List<PhanCong_CongDoan> t = phanCongCD_Dao.getallBangPhanCong();
				int i = 1;
				try {
					p = sp_dao.findAll();
					for (SanPham s : p) {
						if(e.getStateChange() == ItemEvent.SELECTED) {
							if(!cboTenSanPham.getSelectedItem().toString().equals("Chọn")) {
								if(cboTenSanPham.getSelectedItem().equals(s.getTenSanPham())) {
									cboMaSanPham.setSelectedItem(s.getMaSP());
									tableModelCD.setRowCount(0);
									for (CongDoan c : l) {
										if(c.getSanPham().getMaSP().equals(cboMaSanPham.getSelectedItem().toString())) {
											if(c.getTrangThai().equals("Chưa hoàn thành")) {
												tableModelCD.addRow(new Object[] {c.getMaCD(),
														c.getTenCongDoan(), c.getDonGia(), c.getTrangThai(), c.getThuTu()});
											}
										}
									}
									tableCongDoan.setModel(tableModelCD);
								}
							}
						}
					}
					tablePhanCong.setModel(tableModelPhanCong);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Tên sản phẩm:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_1.setBounds(21, 64, 105, 27);
		add(lblNewLabel_1);


		cboMaSanPham.setEnabled(false);
		cboMaSanPham.setBounds(136, 104, 425, 21);
		add(cboMaSanPham);

		JLabel lblNewLabel_2 = new JLabel("Mã sản phẩm:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_2.setBounds(21, 103, 105, 19);
		add(lblNewLabel_2);
		String cmm [] = "Chọn;Lên khuôn giày;Khâu dập và may da;Hoàn thiện phần mũi giày;Hoàn thiện phần đế và phần trang trí;Kiểm tra lại thành phẩm".split(";");
		cboChuyenMon = new JComboBox(cmm);
		cboChuyenMon.setBounds(937, 294, 425, 21);
		add(cboChuyenMon);

		cboMaCongDoan = new JComboBox();
		cboMaCongDoan.setBounds(937, 255, 425, 21);
		add(cboMaCongDoan);

		List<CongDoan> d = congDoan_Dao.findAll();
		for (CongDoan a : d) {
			cboMaCongDoan.addItem(a.getMaCD());
		}
		cboMaCongDoan.setEnabled(false);
		cboMaCongDoan.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int row = tableCongDoan.getSelectedRow();
				List<PhanCong_CongDoan> t = phanCongCD_Dao.phanCong();
				CongDoan c = new CongDoan(cboMaCongDoan.getSelectedItem().toString().trim());
				int flag = 0;
				List<CongDoan> q = congDoan_Dao.findAll();
				SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
				CongDoan cd = new CongDoan(cboMaCongDoan.getSelectedItem().toString());
				tableModelPhanCong.setRowCount(0);
				for (PhanCong_CongDoan b : t){
					for (CongDoan d : q) {
						if(b.getCongDoan().getMaCD().equals(cd.getMaCD())){
							flag = 1;
							break;
						}
						else
							flag = 0;
					}
					if(flag == 1) {
						if(kiemTraCongDoanThuocSanPham()) {
							if(b.getCongDoan().getMaCD().equals(cd.getMaCD())) {
								tableModelPhanCong.addRow(new Object[] {b.getCongNhan().getMaCN(),
										b.getCongNhan().getHoTen(),
										b.getCongDoan().getMaCD(),
										b.getCongDoan().getTenCongDoan(),
										sf.format(b.getNgayPhanCong()), b.getCongDoan().getDonGia(), b.getCongDoan().getSanPham().getMaSP(), b.getCongDoan().getSanPham().getTenSanPham()});
							}
						}
					}
				}
				tablePhanCong.setModel(tableModelPhanCong);
			}});

		//
		cboChuyenMon.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int row = tableCongDoan.getSelectedRow();
				int rowCN = tableCongNhan.getSelectedRow();
				List<PhanCong_CongDoan> l = phanCongCD_Dao.getallBangPhanCong();
				List<CongNhan> p;
				List<CongDoan> cd = congDoan_Dao.findAll();
				boolean flag = true;
				p = congNhan_Dao.findAll();
				tableModelCN.setRowCount(0);
				for (CongNhan s : p) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
						if(!cboChuyenMon.getSelectedItem().toString().equals("Chọn")) {
							if(cboChuyenMon.getSelectedItem().equals(s.getChuyenMon())) {
								if(s.getTrangThai().equals("Chưa phân công")) {
									String [] rowData = {
											s.getMaCN(), s.getHoTen()
									};
									tableModelCN.addRow(rowData);
								}
							}
						}
					}
				}
				tableCongNhan.setModel(tableModelCN);
			}
		});
		tableCongNhan.addMouseListener(new CongNhanMouse());
		tableCongDoan.addMouseListener(new CongDoanMouse());
		//		tableModelPhanCong.setRowCount(0);
		loadBangPhanCong();
		loadBangCongNhan();
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnPhanCong)) {
			phanCong();
		}
		else if(o.equals(btnXoa)) {
			delete();
			tableModelCN.setRowCount(0);
			List<CongNhan> l = congNhan_Dao.findAll();
			for (CongNhan c : l) {
				if(c.getChuyenMon().equals(cboChuyenMon.getSelectedItem().toString())) {
					tableModelCN.addRow(new Object[] {c.getMaCN(),
							c.getHoTen()});
				}
			}
			tableCongNhan.setModel(tableModelCN);
		}


	}
	private void loadBangCongNhan()
	{
		boolean flag = true;
		tableModelCN.setRowCount(0);
		List<CongNhan> danhSachCongNhan = congNhan_Dao.findAll();
		List<PhanCong_CongDoan> l = phanCongCD_Dao.getallBangPhanCong();
		for (CongNhan congNhan : danhSachCongNhan) {
			for (PhanCong_CongDoan p : l) {
				if(congNhan.getMaCN().equals(p.getCongNhan().getMaCN())) {
					flag = false;
					break;
				}
				else
					flag = true;
			}
			if(flag == true) {
				String [] rowData = {
						congNhan.getMaCN(), congNhan.getHoTen()
				};
				tableModelCN.addRow(rowData);
			}
		}
		tableCongNhan.setModel(tableModelCN);
	}
	public boolean kiemTraHoanThanh(CongDoan cd) {
		if(cd.getTrangThai().equals("Hoàn thành"))
			return true;
		return false;
	}
	private void loadBangCongDoan()
	{
		tableModelCD.setRowCount(0);
		List<CongDoan> list = congDoan_Dao.getAllCongDoan();
		for (CongDoan congDoan : list) {
			String [] rowData = {
					congDoan.getMaCD(),congDoan.getTenCongDoan()
			};
			tableModelCD.addRow(rowData);
		}
		tableCongDoan.setModel(tableModelCD);
	}
	// ham kiem tra cong doan
	public boolean kiemTraCongDoan(CongDoan cd) {
		if(cd.getThuTu() == 1) {
			return true;
		}
		else if(cd.getThuTu() == 2) {
			if(Integer.parseInt(tableCongDoan.getValueAt(0, 4).toString()) == 2)
				return true;
		}
		else if(cd.getThuTu() == 3) {
			if(Integer.parseInt(tableCongDoan.getValueAt(0, 4).toString()) == 3)
				return true;
		}
		else if(cd.getThuTu() == 4) {
			if(Integer.parseInt(tableCongDoan.getValueAt(0, 4).toString()) == 4)
				return true;
		}
		else if(cd.getThuTu() == 5) {
			if(Integer.parseInt(tableCongDoan.getValueAt(0, 4).toString()) == 5)
				return true;
		}
		return false;
	}
	// ham dem so cong doan cua san pham
	public int demSoCongDoan() {
		List<CongDoan> q = congDoan_Dao.findAll();
		int dem = 0;
		for (CongDoan c : q) {
			if(c.getSanPham().getMaSP().equals(cboMaSanPham.getSelectedItem())) {
				dem++;
			}
		}
		return dem;
	}
	private void loadBangPhanCong() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<PhanCong_CongDoan> danhSachPhanCong = phanCongCD_Dao.phanCong() ;
		CongDoan cd = new CongDoan();
		SanPham sp = new SanPham(cboMaSanPham.getSelectedItem().toString());
		cd.setSanPham(sp);
		tableModelPhanCong.setRowCount(0);
		for (PhanCong_CongDoan phanCongCongDoan : danhSachPhanCong) {
			System.out.println(phanCongCongDoan);
			tableModelPhanCong.addRow(new Object[] {phanCongCongDoan.getCongNhan().getMaCN(),
					phanCongCongDoan.getCongNhan().getHoTen(),
					phanCongCongDoan.getCongDoan().getMaCD(),phanCongCongDoan.getCongDoan().getTenCongDoan(),sdf.format(phanCongCongDoan.getNgayPhanCong()),
					phanCongCongDoan.getCongDoan().getDonGia()+"", phanCongCongDoan.getCongDoan().getSanPham().getMaSP(), phanCongCongDoan.getCongDoan().getSanPham().getTenSanPham()});
		}
		tablePhanCong.setModel(tableModelPhanCong);
	}
	public void delete() {
		int row = tablePhanCong.getSelectedRow();
		if(row>=0)
		{
			String maCongDoan = (String) tablePhanCong.getValueAt(row, 0);
			int hoiNhac = JOptionPane.showConfirmDialog(this,"Bạn có muốn cập nhật không","Chú ý",JOptionPane.YES_NO_OPTION);
			if(hoiNhac == JOptionPane.YES_OPTION)
			{
				if(phanCongCD_Dao.xoaBangPhanCong(maCongDoan))
				{
					tableModelPhanCong.removeRow(row);
					JOptionPane.showMessageDialog(this,"Xóa thành công");
					loadBangCongNhan();
				}else
				{
					JOptionPane.showMessageDialog(this,"Xóa không thành công!");
				}
			}
		}

	}

	public void loadDataPhanCong() {
		List<PhanCong_CongDoan> t = phanCongCD_Dao.findAll();
		tableModelPhanCong.setRowCount(0);
		CongDoan cd = new CongDoan(cboMaCongDoan.getSelectedItem().toString().trim());
		for (PhanCong_CongDoan b : t) {
			System.out.println(b);
			b.setCongDoan(cd);
			if(b.getCongDoan().getMaCD().equals(cd.getMaCD()) && b.getCongNhan().getMaCN().equals(txtMaCongNhan.getText().trim())) {
				tableModelPhanCong.addRow(new Object[] {b.getCongNhan().getMaCN(), b.getCongNhan().getHoTen(),
						b.getCongDoan().getMaCD(),
						b.getCongDoan().getTenCongDoan(),
						b.getNgayPhanCong(), b.getCongDoan().getDonGia()});
			}
		}
		tablePhanCong.setModel(tableModelPhanCong);
	}
	public int kiemTra(CongDoan cd) {
		if(cd.getThuTu() == 1) {
			return 1;
		}
		if(cd.getThuTu() == 2) {
			return 2;
		}
		if(cd.getThuTu() == 3) {
			return 3;
		}
		if(cd.getThuTu() == 4) {
			return 4;
		}
		return 5;
	}
	public void phanCong(){	
		CongNhan_DAO dao = new CongNhan_DAO();
		int row = tableCongDoan.getSelectedRow();
		int rowCN = tableCongNhan.getSelectedRow();
		PhanCong_CongDoan pc = new PhanCong_CongDoan();
		String npc = sdf.format(dateNgayPhanCong.getDate());
		Date ngaySinh = Date.valueOf(npc);
		pc.setNgayPhanCong(ngaySinh);
		CongDoan cd = new CongDoan();
		try {
			cd.setMaCD(cboMaCongDoan.getSelectedItem().toString());
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Phân công không thành công do chưa có công đoạn!");
		}
		cd.setTenCongDoan(cboChuyenMon.getSelectedItem().toString());
		pc.setCongDoan(cd);
		CongNhan cn = new CongNhan();
		cn.setMaCN(txtMaCongNhan.getText());
		cn.setHoTen(txtTenCongNhan.getText());
		pc.setCongNhan(cn);
		cd.setDonGia(Double.parseDouble(txtDonGia.getText()));
		cd.setThuTu(Integer.parseInt(tableCongDoan.getValueAt(row, 4).toString()));
		try {
			int hoiNhac = JOptionPane.showConfirmDialog(this,"Bạn có muốn phân công không?","Chú ý",
					JOptionPane.YES_NO_OPTION);
			if(hoiNhac==JOptionPane.YES_OPTION)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				if(kiemTraCongDoan(cd)) {
					if(phanCongCD_Dao.taoBangPhanCong(pc))

					{
						tableModelPhanCong.addRow(new Object[] {pc.getCongNhan().getMaCN(),pc.getCongNhan().getHoTen(),
								pc.getCongDoan().getMaCD(),pc.getCongDoan().getTenCongDoan(),
								sdf.format(pc.getNgayPhanCong()),pc.getCongDoan().getDonGia(), cboMaSanPham.getSelectedItem().toString(), cboTenSanPham.getSelectedItem().toString()
						});
						JOptionPane.showMessageDialog(this, "Phân công thành công");
						cn.setTrangThai("Đã phân công");
						dao.updateTrangThai(cn);
						tableModelCN.removeRow(rowCN);
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "Công đoạn tiên quyết chưa hoàn thành!");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Phân công không thành công do chưa có công đoạn!");
			e.printStackTrace();
		}
	}

	public class CongNhanMouse implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int row = tableCongNhan.getSelectedRow();
			txtMaCongNhan.setText(tableModelCN.getValueAt(row, 0).toString());
			txtTenCongNhan.setText(tableModelCN.getValueAt(row, 1).toString());
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
	public class CongDoanMouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int row = tableCongDoan.getSelectedRow();
			cboMaCongDoan.setSelectedItem(tableCongDoan.getValueAt(row, 0).toString());
			cboChuyenMon.setSelectedItem(tableCongDoan.getValueAt(row, 1).toString());
			txtDonGia.setText(tableCongDoan.getValueAt(row, 2).toString());
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
	public boolean kiemTraCongDoanThuocSanPham() {
		List<CongDoan> lcd = congDoan_Dao.findAll();
		for (CongDoan cd : lcd) {
			if(cd.getSanPham().getMaSP().equals(cboMaSanPham.getSelectedItem().toString())) {
				return true;
			}
		}
		return false;
	}
	// kiem tra xem cong nhan duoc phan chia cong doan da hoan thanh chua
	public void kiemTraHoanThanhCongDoanCuaCongNhan() {
		List<PhanCong_CongDoan> lpccd = phanCongCD_Dao.phanCong();
		CongNhan_DAO dao = new CongNhan_DAO();
		List<CongNhan> l = congNhan_Dao.findAll();
		for (CongNhan c : l) {
			for (PhanCong_CongDoan pc : lpccd) {
				if(pc.getCongDoan().getTrangThai().equals("Hoàn thành")) {
					c.setTrangThai("Chưa phân công");
					try {
						dao.updateTrangThai(c);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}





