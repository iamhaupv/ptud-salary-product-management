package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.CongDoan_DAO;
import dao.CongNhan_DAO;
import dao.PhanCongCongDoan_DAO;
//import dao.CongDoan_DAO;
import dao.SanPham_DAO;
import entity.CongDoan;
import entity.CongNhan;
import entity.PhanCong_CongDoan;
import entity.PhongBan;
import entity.SanPham;
import helper.ImageHepler;

import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class CongDoan_GUI extends JPanel implements ActionListener , MouseListener {
	private JTextField txtMaCongDoan;
	private JTable tableCongDoan;

	/**
	 * Create the panel.
	 */
	private SanPham_DAO sp_dao ;

	private DefaultTableModel tableModel;
	private JButton btnThem;
	private CongDoan_DAO congDoan_Dao;
	private JButton btnCapNhat;
	private JButton btnXoa;
	private JButton btnTaoMoi;
	private JComboBox cboTenSanPham;
	private JComboBox cboSanPham;
	private JLabel lblThuTu;
	private JComboBox cboThuTu;
	private JLabel lblTrangThai;
	private JComboBox cboTrangThai;
	private JComboBox cboTenCongDoan;
	private JComboBox cboDonGia;
	public CongDoan_GUI() throws ClassNotFoundException, SQLException {
		setBackground(Color.WHITE);
		//setBackground(new Color(0, 206, 209));
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		congDoan_Dao = new CongDoan_DAO();
		sp_dao = new SanPham_DAO();
		// Káº¿t ná»‘i cÆ¡ sá»Ÿ dá»¯ liá»‡u
		connect.connectDB.getInstance().connect();
		setLayout(null);
		// ----------------------TiĂªu Ä‘á»�--------------------------//
		JLabel lblTieuDe = new JLabel("THÔNG TIN CÔNG ĐOẠN");
		lblTieuDe.setForeground(Color.BLACK);
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieuDe.setBounds(635, 25, 322, 31);
		add(lblTieuDe);
		//---------------------MĂ£ cĂ´ng Ä‘oáº¡n----------------------//
		JLabel lblMaCongDoan = new JLabel("Mã công đoạn:");
		lblMaCongDoan.setForeground(Color.BLACK);
		lblMaCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaCongDoan.setBounds(25, 229, 133, 24);
		add(lblMaCongDoan);

		txtMaCongDoan = new JTextField();
		txtMaCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtMaCongDoan.setBounds(196, 231, 387, 22);
		txtMaCongDoan.setText(autoMaHD());
		txtMaCongDoan.setEnabled(false);
		add(txtMaCongDoan);
		txtMaCongDoan.setColumns(10);

		//---------------------TĂªn cĂ´ng Ä‘oáº¡n----------------------//
		JLabel lblTenCongDoan = new JLabel("Tên công đoạn:");
		lblTenCongDoan.setForeground(Color.BLACK);
		lblTenCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenCongDoan.setBounds(25, 296, 128, 24);
		add(lblTenCongDoan);

		String cm [] = "Lên khuôn giày;Khâu dập và may da;Hoàn thiện phần mũi giày;Hoàn thiện phần đế và phần trang trí;Kiểm tra lại thành phẩm".split(";");
		cboTenCongDoan = new JComboBox(cm);
		cboTenCongDoan.setBounds(196, 300, 387, 21);
		add(cboTenCongDoan);
		cboTenCongDoan.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				List<CongDoan> l = congDoan_Dao.getAllCongDoan();
				for (CongDoan s : l) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
						if(!cboTenCongDoan.getSelectedItem().toString().equals("Chọn")) {
							if(cboTenCongDoan.getSelectedItem().equals("Lên khuôn giày")) {
								cboThuTu.setSelectedItem("1");;
								cboDonGia.setSelectedItem("450");
							}
							else if(cboTenCongDoan.getSelectedItem().equals("Khâu dập và may da")) {
								cboThuTu.setSelectedItem("2");;
								cboDonGia.setSelectedItem("550");
							}
							else if(cboTenCongDoan.getSelectedItem().equals("Hoàn thiện phần mũi giày")) {
								cboThuTu.setSelectedItem("3");;
								cboDonGia.setSelectedItem("400");
							}
							else if(cboTenCongDoan.getSelectedItem().equals("Hoàn thiện phần đế và phần trang trí")) {
								cboThuTu.setSelectedItem("4");;
								cboDonGia.setSelectedItem("450");
							}
							else if(cboTenCongDoan.getSelectedItem().equals("Kiểm tra lại thành phẩm")) {
								cboThuTu.setSelectedItem("5");;
								cboDonGia.setSelectedItem("350");
							}
						}
					}
				}
			}
		});
		JLabel lblSanPham = new JLabel("Mã sản phẩm:");
		lblSanPham.setForeground(Color.BLACK);
		lblSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSanPham.setBounds(25, 155, 133, 24);
		add(lblSanPham);



		//---------------------Ä�Æ¡n giĂ¡d----------------------//
		JLabel lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setForeground(Color.BLACK);
		lblDonGia.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDonGia.setBounds(25, 361, 88, 24);
		add(lblDonGia);




		//---------------------TiĂªu Ä‘á»� báº£ng thĂ´ng tin cĂ´ng Ä‘oáº¡n------------//
		JLabel lblTieuDeBang = new JLabel("BẢNG THÔNG TIN CÔNG ĐOẠN");
		lblTieuDeBang.setBackground(Color.WHITE);
		lblTieuDeBang.setForeground(UIManager.getColor("FormattedTextField.selectionBackground"));
		lblTieuDeBang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTieuDeBang.setBounds(985, 79, 296, 24);
		add(lblTieuDeBang);


		//-------------------------Báº£ng thĂ´ng tin cĂ´ng Ä‘oáº¡n--------------//





		String [] headers = "Sản phẩm;Mã công đoạn;Tên công đoạn; Đơn giá;Thứ tự;Trạng thái".split(";");
		tableModel=new DefaultTableModel(headers,0);
		tableCongDoan = new JTable(tableModel);
		JScrollPane scrollCongDoan = new JScrollPane();
		scrollCongDoan.setBounds(647, 113, 848, 616);
		scrollCongDoan.setViewportView(tableCongDoan);



		add(scrollCongDoan);

		//------------------------------Chá»©c nÄƒng thĂªm-------------------------------------//
		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnThem.setBounds(10, 574, 126, 41);
		add(btnThem);
		btnThem.setIcon(null);
		//------------------------------Chá»©c nÄƒng cáº­p nháº­t----------------------------------//
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnCapNhat.setBounds(175, 574, 125, 41);
		add(btnCapNhat);
		btnCapNhat.setIcon(null);
		//-------------------------------Chá»©c nÄƒng xĂ³a----------------------------------------//
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnXoa.setBounds(334, 574, 124, 41);
		add(btnXoa);
		btnXoa.setIcon(null);
		//------------------------------Chá»©c nÄƒng táº¡o má»›i------------------------------------//
		btnTaoMoi = new JButton("Tạo mới");
		btnTaoMoi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnTaoMoi.setBounds(497, 574, 128, 41);
		add(btnTaoMoi);
		btnTaoMoi.setIcon(null);


		//Sá»± kiá»‡n
		//		loadBangCongDoan();
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnCapNhat.addActionListener(this);
		tableCongDoan.addMouseListener(this);
		btnTaoMoi.addActionListener(this);
		JLabel lblTenSanPham = new JLabel("Tên sản phẩm:");
		lblTenSanPham.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTenSanPham.setBounds(30, 74, 123, 24);
		add(lblTenSanPham);

		String c [] = "450;550;400;450;350".split(";");
		cboDonGia = new JComboBox(c);
		cboDonGia.setBounds(196, 365, 387, 21);
		add(cboDonGia);
		cboTenSanPham = new JComboBox();
		cboSanPham = new JComboBox();
		cboSanPham.setEnabled(false);
		cboTenSanPham.addItem("Chọn");
		cboSanPham.addItem("");
		cboTenSanPham.setBounds(196, 77, 387, 21);
		add(cboTenSanPham);
		List<SanPham> dssp = sp_dao.findAll();
		for (SanPham sanpham : dssp) {
			if(sanpham.getTrangThai().equals("Đang tiến hành"))
				cboSanPham.addItem(sanpham.getMaSP());
		}
		cboSanPham.setBounds(196, 159, 387, 21);
		add(cboSanPham);

		lblThuTu = new JLabel("Thứ tự:");
		lblThuTu.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblThuTu.setBounds(25, 412, 63, 31);
		add(lblThuTu);

		String tt [] = "1;2;3;4;5".split(";");
		cboThuTu = new JComboBox(tt);
		cboThuTu.setBounds(196, 419, 387, 21);
		add(cboThuTu);
		cboThuTu.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				List<CongDoan> l = congDoan_Dao.getAllCongDoan();
				for (CongDoan s : l) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
						if(!cboThuTu.getSelectedItem().toString().equals("Chọn")) {
							if(cboThuTu.getSelectedItem().equals("1")) {
								cboTenCongDoan.setSelectedItem("Lên khuôn giày");
								cboDonGia.setSelectedItem("450");
							}
							else if(cboThuTu.getSelectedItem().equals("2")) {
								cboTenCongDoan.setSelectedItem("Khâu dập và may da");
								cboDonGia.setSelectedItem("550");
							}
							else if(cboThuTu.getSelectedItem().equals("3")) {
								cboTenCongDoan.setSelectedItem("Hoàn thiện phần mũi giày");
								cboDonGia.setSelectedItem("400");
							}
							else if(cboThuTu.getSelectedItem().equals("4")) {
								cboTenCongDoan.setSelectedItem("Hoàn thiện phần đế và phần trang trí");
								cboDonGia.setSelectedItem("450");
							}
							else if(cboThuTu.getSelectedItem().equals("5")) {
								cboTenCongDoan.setSelectedItem("Kiểm tra lại thành phẩm");
								cboDonGia.setSelectedItem("350");
							}
						}
					}
				}
			}
		});
		String t [] = "Hoàn thành;Chưa hoàn thành".split(";");
		lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTrangThai.setBounds(26, 475, 98, 24);
		add(lblTrangThai);
		cboDonGia.setEnabled(false);
		cboTrangThai = new JComboBox(t);
		cboTrangThai.setBounds(196, 479, 387, 21);
		cboTrangThai.setSelectedItem("Chưa hoàn thành");
		add(cboTrangThai);
		for (SanPham sanpham : dssp) {
			if(sanpham.getTrangThai().equals("Đang tiến hành"))
				cboTenSanPham.addItem(sanpham.getTenSanPham());
		}
		cboTenSanPham.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				List<CongDoan> l = congDoan_Dao.getAllCongDoan();
				List<SanPham> p;
				try {
					p = sp_dao.findAll();
					for (SanPham s : p) {
						if(e.getStateChange() == ItemEvent.SELECTED) {
							if(!cboTenSanPham.getSelectedItem().toString().equals("Chọn")) {
								if(cboTenSanPham.getSelectedItem().equals(s.getTenSanPham())) {
									cboSanPham.setSelectedItem(s.getMaSP());
									tableModel.setRowCount(0);
									for (CongDoan c : l) {
										if(c.getSanPham().getMaSP().equals(cboSanPham.getSelectedItem().toString())) {
											if(c.getTrangThai().equals("Chưa hoàn thành")) {
												tableModel.addRow(new Object[] {c.getSanPham().getMaSP(), c.getMaCD(),
														c.getTenCongDoan(), c.getDonGia(), c.getThuTu(), c.getTrangThai()});
											}

											if(ktHoanThanh() == 5) {
												SanPham sp = new SanPham(cboSanPham.getSelectedItem().toString());
												sp.setTrangThai("Hoàn thành");
												SanPham_DAO dao = new SanPham_DAO();
												dao.updateTrangThai(sp);
												SanPham_GUI spgui = new SanPham_GUI();
												spgui.loadDataFromTable();
												tableModel.setRowCount(0);
												JOptionPane.showMessageDialog(null, "Sản phẩm này đã được hoàn thành vui lòng chọn sản phẩm khác");
												break;
											}
										}
									}
									tableCongDoan.setModel(tableModel);
								}
							}
							else {
								if(cboTenSanPham.getSelectedItem().equals("Chọn"));
								cboSanPham.setSelectedItem("");
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
	}
	private void setExtendedState(int maximizedBoth) {
		// TODO Auto-generated method stub

	}
	//Hiá»ƒn thá»‹ mĂ n hĂ¬nh trĂªn trang chá»§
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableCongDoan.getSelectedRow();
		cboSanPham.setSelectedItem(tableModel.getValueAt(row, 0).toString());
		txtMaCongDoan.setText(tableModel.getValueAt(row, 1).toString());
		cboTenCongDoan.setSelectedItem(tableModel.getValueAt(row, 2).toString());
		cboDonGia.setSelectedItem(tableModel.getValueAt(row, 3).toString());
		cboThuTu.setSelectedItem(tableModel.getValueAt(row, 4));
		cboTrangThai.setSelectedItem(tableModel.getValueAt(row, 5));

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

	/// Sá»± kiá»‡n
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			themCongDoan();
			reset();
			txtMaCongDoan.setText(autoMaHD());
		}
		else if(o.equals(btnXoa)) {
			xoaCongDoan();
			reset();
		}
		else if(o.equals(btnCapNhat)) {
			capNhatCongDoan();
			reset();
			txtMaCongDoan.setText(autoMaHD());
		}
		else if(o.equals(btnTaoMoi)) {
			reset();
			txtMaCongDoan.setText(autoMaHD());
		}

	}
	// Load database
	private void loadBangCongDoan() {
		List<CongDoan> dsCongDoan = congDoan_Dao.getAllCongDoan();
		for (CongDoan congDoan : dsCongDoan) {
			String rowData [] = {
					congDoan.getSanPham().getMaSP(),congDoan.getMaCD(),congDoan.getTenCongDoan(),congDoan.getDonGia()+"", congDoan.getThuTu()+"" + congDoan.getTrangThai()
			};
			tableModel.addRow(rowData);
		}
		tableCongDoan.setModel(tableModel);
	}

	// ThĂªm cĂ´ng Ä‘oáº¡n
	private void themCongDoan() {
		String maCongDoan = txtMaCongDoan.getText();
		String tenCongDoan = cboTenCongDoan.getSelectedItem().toString();
		String maSanPham = cboSanPham.getSelectedItem().toString();
		SanPham sanPham = new SanPham(maSanPham);
		double donGia = Double.parseDouble(cboDonGia.getSelectedItem().toString());
		int tt = Integer.parseInt(cboThuTu.getSelectedItem().toString());
		String t = cboTrangThai.getSelectedItem().toString();
		CongDoan congDoan = new CongDoan(maCongDoan, tenCongDoan, donGia, sanPham, tt, t);
		int hoiNhac = JOptionPane.showConfirmDialog(this,"Bạn có muốn thêm công đoạn này vào không?","Chú ý",JOptionPane.YES_NO_OPTION);
		if(hoiNhac==JOptionPane.YES_OPTION)
		{	
			if(kiemTraCongDoanKhiThem(congDoan)) { // kiemTraCongDoanKhiThem(congDoan)
				if(kiemTraSoLuongCongDoan(congDoan)) {
					if(congDoan_Dao.addCongDoan(congDoan))
					{
						tableModel.addRow(new Object []  {
								congDoan.getSanPham().getMaSP(),congDoan.getMaCD(),congDoan.getTenCongDoan(),congDoan.getDonGia(), congDoan.getThuTu(), congDoan.getTrangThai()
						});
						tableCongDoan.setModel(tableModel);
						JOptionPane.showMessageDialog(this,"Thêm thành công");
						try {
							List<SanPham> lsp = sp_dao.findAll();
							for (SanPham s : lsp) {
								if(ktHoanThanh() == 5) {
									tableModel.setRowCount(0);
									JOptionPane.showMessageDialog(null, "Sản phẩm này đã được hoàn thành vui lòng chọn sản phẩm khác");
									break;
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}else
					{
						JOptionPane.showMessageDialog(this,"Trùng mã!");
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "Vượt qua số lượng công đoạn cho phép!");
				}
			}else {
				JOptionPane.showMessageDialog(this, "Phải thêm đúng thứ tự và không được lặp lại công đoạn đã thêm!");
			}
		}
	}
	// XĂ³a cĂ´ng Ä‘oáº¡n
	public void xoaCongDoan ()
	{
		int row = tableCongDoan.getSelectedRow();
		if(row>=0)
		{
			String maCongDoan = (String) tableCongDoan.getValueAt(row, 1);
			int hoiNhac = JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa công đoạn này không?", "Chú ý",JOptionPane.YES_NO_OPTION);
			if(hoiNhac == JOptionPane.YES_OPTION)
			{
				if(congDoan_Dao.deleteCongDoan(maCongDoan))
				{
					tableModel.removeRow(row);
					JOptionPane.showMessageDialog(this,"Xóa thành công");
				}else
				{
					JOptionPane.showMessageDialog(this,"Xóa không thành công!");
				}
			}
		}
	}
	// Sá»­a cĂ´ng Ä‘oáº¡n
	public void capNhatCongDoan() {
		int row = tableCongDoan.getSelectedRow();
		String maCongDoan = txtMaCongDoan.getText();
		String tenCongDoan = cboTenCongDoan.getSelectedItem().toString();
		String maSanPham = cboSanPham.getSelectedItem().toString();
		SanPham sanPham = new SanPham(maSanPham);
		double donGia = Double.parseDouble(cboDonGia.getSelectedItem().toString().toString());
		int tt = Integer.parseInt(cboThuTu.getSelectedItem().toString());
		String t = cboTrangThai.getSelectedItem().toString();
		CongDoan congDoan = new CongDoan(maCongDoan, tenCongDoan, donGia, sanPham, tt, t);
		try {
			if(row>= 0)
			{
				int hoiNhac =JOptionPane.showConfirmDialog(this,"Bạn có muốn cập nhật không?","Chú ý",JOptionPane.YES_NO_OPTION);
				if(hoiNhac == JOptionPane.YES_OPTION)
				{
					if(kiemTraThuTuHoanThanh(congDoan)) {
						if(congDoan_Dao.updateCongDoan(congDoan))
						{   tableCongDoan.setValueAt(cboSanPham.getSelectedItem().toString(), row, 0);
						tableCongDoan.setValueAt(cboTenCongDoan.getSelectedItem(), row, 2);
						tableCongDoan.setValueAt(cboDonGia.getSelectedItem().toString(), row, 3);
						tableCongDoan.setValueAt(cboThuTu.getSelectedItem(), row, 4);
						tableCongDoan.setValueAt(cboTrangThai.getSelectedItem(), row, 5);
						JOptionPane.showMessageDialog(this,"Cập nhật thành công");
						if(congDoan.getTrangThai().equals("Hoàn thành"))
							tableModel.removeRow(row);
							hoanThanhCongDoan();

						}else
						{
							JOptionPane.showMessageDialog(this,"Cập nhật không thành công!");

						}
					}
					else
						JOptionPane.showMessageDialog(this, "Công đoạn tiên quyết chưa hoàn thành!");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public String autoMaHD() {
		String str;
		int ma = congDoan_Dao.getMaHd();
		if (ma < 100)
			str = "CD0000" + ma;
		else if (ma < 1000)
			str = "CD000" + ma;
		else if (ma < 10000)
			str = "CD00" + ma;
		else if (ma < 100000)
			str = "CD0" + ma;
		else
			str = "CD" + ma;
		return str;

	}
	public void reset() {
		txtMaCongDoan.setText("");
	}
	public SanPham soLuongCD() {
		SanPham_DAO sp = new SanPham_DAO();
		try {
			List<SanPham> l = sp.findAll();
			for (SanPham s : l) {
				if(cboSanPham.getSelectedItem().equals(s.getMaSP())) {
					return s;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	// kiem tra xem so luong cong doan them vao co bi thua hay k
	public boolean kiemTraSoLuongCongDoan(CongDoan cd) {
		int sl = 0;
		SanPham sp = soLuongCD();
		List<CongDoan> l = congDoan_Dao.getAllCongDoan();
		for (CongDoan c : l) {
			if(sp.getMaSP().equalsIgnoreCase(c.getSanPham().getMaSP()))
				sl++;
		}
		if(sl < soLuongCD().getSoCongDoan())
			return true;
		return false;
	}
	// kiem tra khi them vao phai dung thu tu va khong duoc trung lai cong doan vua them
	public boolean kiemTraCongDoanKhiThem(CongDoan cd) {
		List<CongDoan> l = congDoan_Dao.findAll();
		for (CongDoan c : l) {
			if(soCongDoan() == 0) {
				if(cd.getThuTu() == 1) {
					return true;
				}
			}
			if(soCongDoan() == 1) {
				if(cd.getThuTu() == 2) {
					return true;
				}
			}
			if(soCongDoan() == 2) {
				if(cd.getThuTu() == 3) {
					return true;
				}
			}
			if(soCongDoan() == 3) {
				if(cd.getThuTu() == 4) {
					return true;
				}
			}
			if(soCongDoan() == 4) {
				if(cd.getThuTu() == 5) {
					return true;
				}
			}
		}
		return false;
	}
	// dem xem san pham co bao nhieu cong doan
	public int soCongDoan() {
		int dem = 0;
		List<CongDoan> l = congDoan_Dao.findAll();
		for (CongDoan c : l) {
			if(c.getSanPham().getMaSP().equals(cboSanPham.getSelectedItem().toString())) {
				dem++;
			}
		}
		return dem;
	}
	// kiem tra xem cong doan thuoc san pham nao
	public boolean congDoanThuocSanPham(CongDoan cd) {
		if(cd.getSanPham().getMaSP().equals(cboSanPham.getSelectedItem().toString())) {
			return true;
		}
		return false;
	}
	// dem xem san pham co bao nhieu cong doan hoan thanh neu du 5 thi san pham do dc hoan thanh
	public int ktHoanThanh() {
		int ss = 0;
		List<CongDoan> l = congDoan_Dao.findAll();
		for (CongDoan c : l) {
			if(c.getSanPham().getMaSP().equals(cboSanPham.getSelectedItem().toString())) {
				if(c.getTrangThai().equals("Hoàn thành")) {
					ss++;
				}
			}
		}
		return ss;
	}
	// kiem tra thu tu cap nhat cua cong doan phai dung thu tu
	public boolean kiemTraThuTuHoanThanh(CongDoan cd) {
		int row = tableCongDoan.getSelectedRow();
		List<CongDoan> l = congDoan_Dao.findAll();
		for (CongDoan c : l){
			if(cd.getThuTu() == 1)
				return true;
			else if(row == 0) {
				return true;
			}
			else {
				if(congDoanThuocSanPham(cd) && cd.getThuTu() == 2) {
					if(tableCongDoan.getValueAt(row - 1, 4).toString().equals("1") && tableCongDoan.getValueAt(row - 1, 5).toString().equals("Hoàn thành"))
						return true;
				}
				else if(congDoanThuocSanPham(cd) && cd.getThuTu() == 3) {
					if(tableCongDoan.getValueAt(row - 1, 4).toString().equals("2") && tableCongDoan.getValueAt(row - 1, 5).toString().equals("Hoàn thành"))
						return true;
				}
				else if(congDoanThuocSanPham(cd) && cd.getThuTu() == 4) {
					if(tableCongDoan.getValueAt(row - 1, 4).toString().equals("3") && tableCongDoan.getValueAt(row - 1, 5).toString().equals("Hoàn thành"))
						return true;
				}
				else if(congDoanThuocSanPham(cd) && cd.getThuTu() == 5) {
					if(tableCongDoan.getValueAt(row - 1, 4).toString().equals("4") && tableCongDoan.getValueAt(row - 1, 5).toString().equals("Hoàn thành"))
						return true;
				}
			}
		}

		return false;
	}
	public void hoanThanhCongDoan() {
		CongNhan_DAO cn_dao = new CongNhan_DAO();
		List<CongNhan> lcn = cn_dao.findAll();
		PhanCongCongDoan_DAO dao = new PhanCongCongDoan_DAO();
		List<PhanCong_CongDoan> lpc = dao.phanCong();
		CongNhan cn = new CongNhan();
		cn.setTrangThai("Chưa phân công");
		
		for (PhanCong_CongDoan pc : lpc) {
			if(pc.getCongDoan().getTrangThai().equals("Hoàn thành")) {
				try {
					cn.setMaCN(pc.getCongNhan().getMaCN());
					cn.setTrangThai("Chưa phân công");
					cn_dao.updateTrangThai(cn);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(pc);
			}
		}
	}
}
