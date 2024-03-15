package gui;

import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connect.connectDB;
import dao.HopDong_DAO;
import dao.SanPham_DAO;
import entity.HopDong;
import entity.SanPham;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class TraCuuHopDong_GUI extends JFrame {

	private JTextField txtMaHopDong;
	private JTextField txtTenHopDong;
	private JTextField txtTenKhachHang;
	private JTextField txtSDT;
	private JTextField txtTT;
	private JTable table;
	private DefaultTableModel tableModel;
	private HopDong_DAO hd_dao;
	private JComboBox cboTenSanPham;
	private JComboBox boMaSanPham;
	private JPanel contentPane;
	/**
	 * Create the panel.
	 */
	public TraCuuHopDong_GUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 50, 1200, 750);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		connectDB.getInstance().connect();
		JLabel lblNewLabel = new JLabel("Thông tin hợp đồng\r\n");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel.setBounds(462, 10, 294, 50);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Tra c\u1EE9u h\u1EE3p \u0111\u1ED3ng", TitledBorder.CENTER, TitledBorder.TOP, null, SystemColor.windowBorder));
		panel.setBounds(10, 70, 1166, 205);
		add(panel);
		panel.setLayout(null);
		
		txtMaHopDong = new JTextField();
		txtMaHopDong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				timKiemTheoMaHopDong();
			}
		});
		txtMaHopDong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtMaHopDong.setBounds(141, 42, 192, 19);
		panel.add(txtMaHopDong);
		txtMaHopDong.setColumns(10);
		
		JLabel lblMaHopDong = new JLabel("Mã hợp đồng :\r\n");
		lblMaHopDong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblMaHopDong.setBounds(31, 46, 80, 13);
		panel.add(lblMaHopDong);
		
		JLabel lblTenHopDong = new JLabel("Tên hợp đồng:\r\n");
		lblTenHopDong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTenHopDong.setBounds(31, 87, 84, 19);
		panel.add(lblTenHopDong);
		
		txtTenHopDong = new JTextField();
		txtTenHopDong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				timKiemTheoTenHopDong();
			}
		});
		txtTenHopDong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTenHopDong.setColumns(10);
		txtTenHopDong.setBounds(141, 86, 192, 19);
		panel.add(txtTenHopDong);
		
		JLabel lblTenSanPham = new JLabel("Mã sản phẩm:\r\n");
		lblTenSanPham.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTenSanPham.setBounds(449, 43, 84, 19);
		panel.add(lblTenSanPham);
		
		JLabel lblTenKhachHang = new JLabel("Tên khách hàng\r\n:\r\n");
		lblTenKhachHang.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTenKhachHang.setBounds(31, 139, 103, 19);
		panel.add(lblTenKhachHang);
		
		txtTenKhachHang = new JTextField();
		txtTenKhachHang.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				timKiemTheoKhachHang();
			}
		});
		txtTenKhachHang.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(141, 138, 192, 19);
		panel.add(txtTenKhachHang);
		
		JLabel lblSinThoi = new JLabel("Số điện thoại:\r\n\r\n");
		lblSinThoi.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblSinThoi.setBounds(866, 43, 103, 19);
		panel.add(lblSinThoi);
		
		JLabel lblTngTin = new JLabel("Tổng tiền:\r\n\r\n");
		lblTngTin.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTngTin.setBounds(866, 89, 103, 19);
		panel.add(lblTngTin);
		
		txtSDT = new JTextField();
		txtSDT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				timKiemTheoSoDienThoai();
			}
		});
		txtSDT.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtSDT.setColumns(10);
		txtSDT.setBounds(964, 42, 192, 19);
		panel.add(txtSDT);
		
		txtTT = new JTextField();
		txtTT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				timKiemTheoTongTien();
			}
		});
		txtTT.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTT.setColumns(10);
		txtTT.setBounds(964, 86, 192, 19);
		panel.add(txtTT);
		
		    boMaSanPham = new JComboBox();
			boMaSanPham.setBounds(548, 41, 192, 22);
			panel.add(boMaSanPham);
			
			
			
		    cboTenSanPham = new JComboBox();
			  cboTenSanPham.addItem("Chọn");
				cboTenSanPham.setBounds(548, 85, 192, 22);
				panel.add(cboTenSanPham);
				
				
				
		

		try {
			SanPham_DAO sp_dao = new SanPham_DAO();
			List<SanPham> sanpham = sanpham = sp_dao.findAll();
			for (SanPham sanPham : sanpham) {
				cboTenSanPham.addItem(sanPham.getTenSanPham());
			}for (SanPham sanPham : sanpham) {
				boMaSanPham.addItem(sanPham.getMaSP());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	    cboTenSanPham.addItemListener(new ItemListener() {
	    	public void itemStateChanged(ItemEvent e) {
	    		String maSanPham = boMaSanPham.getSelectedItem().toString();
				String tenSanPham = cboTenSanPham.getSelectedItem().toString();
				SanPham sp1 = new SanPham(maSanPham, tenSanPham);
				List<SanPham> sp ;
				List<HopDong> hopdong = hd_dao.getallHopDong();
				try {
					SanPham_DAO sp_dao = new SanPham_DAO();

					sp = sp_dao.findAll();
					for (SanPham sanpham : sp) {
						if(e.getStateChange()==ItemEvent.SELECTED) {
							if(!cboTenSanPham.getSelectedItem().toString().equals("Chọn")) {
								if(cboTenSanPham.getSelectedItem().equals(sanpham.getTenSanPham())) {
							boMaSanPham.setSelectedItem(sanpham.getMaSP());
									tableModel.setRowCount(0);
									
									for (HopDong hopDong : hopdong) {
										DecimalFormat df = new DecimalFormat("#,### VND");
										if(hopDong.getSanPham().getTenSanPham().equals(cboTenSanPham.getSelectedItem().toString())) {
											tableModel.addRow(new Object[] {
													
													hopDong.getMaHD(),hopDong.getTenHopDong(),hopDong.getNgayBatDau()+"",hopDong.getNgayKetThuc()+"",hopDong.getSoLuong()+"",hopDong.getDonGiaSanPham()+"",hopDong.getSanPham().getMaSP(),hopDong.getSanPham().getTenSanPham(),hopDong.getKhachHang(),hopDong.getPhone(),df.format(hopDong.getTongTien())
													
											});


										}

									}
									table.setModel(tableModel);

								}
							}
						}

					}
				} catch (Exception e2) {
					// TODO: handle exception

				}
	    	}
	    });
	  
		
		
		
		JLabel lblTnSnPhm = new JLabel("Tên sản phẩm");
		lblTnSnPhm.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTnSnPhm.setBounds(449, 87, 84, 19);
		panel.add(lblTnSnPhm);
		
	  
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 311, 1166, 392);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(tableModel =new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"M\u00E3 h\u1EE3p \u0111\u1ED3ng", "T\u00EAn h\u1EE3p \u0111\u1ED3ng", "Ng\u00E0y b\u1EAFt \u0111\u1EA7u", "Ng\u00E0y k\u1EBFt th\u00FAc", "S\u1ED1 l\u01B0\u1EE3ng", "\u0110\u01A1n gi\u00E1 s\u1EA3n ph\u1EA9m", "M\u00E3 s\u1EA3n ph\u1EA9m","Tên sản phẩm", "T\u00EAn kh\u00E1ch h\u00E0ng", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "T\u1ED5ng ti\u1EC1n"
			}
		));
		scrollPane.setViewportView(table);
		loadHopDong();
		
		contentPane.add(panel);
		
	}
	
	private void loadHopDong()
	{   
		
		
		DecimalFormat df = new DecimalFormat("###,### VND");
		hd_dao = new HopDong_DAO();
		List<HopDong> list = hd_dao.getallHopDong();
		for (HopDong hopDong : list) {
			String [] rowData = {
					hopDong.getMaHD(),hopDong.getTenHopDong(),hopDong.getNgayBatDau()+"",hopDong.getNgayKetThuc()+"",hopDong.getSoLuong()+"",hopDong.getDonGiaSanPham()+"",hopDong.getSanPham().getMaSP(),hopDong.getSanPham().getTenSanPham(),hopDong.getKhachHang(),hopDong.getPhone(),df.format(hopDong.getTongTien())
					};   
			tableModel.addRow(rowData);
		}
		table.setModel(tableModel);
	}
	public void timKiemTheoMaHopDong()
	
	{
		
		

		DecimalFormat df = new DecimalFormat("###,### VND");
	//	df.format(ABORT)
		tableModel.setRowCount(0);
		String maHopDong = txtMaHopDong.getText();
	for (HopDong hopDong : hd_dao.findHopDongByMaHopDong(maHopDong)) {
			
		Object dataRow [] = new Object[11];
		dataRow[0] = hopDong.getMaHD();
		dataRow[1] = hopDong.getTenHopDong();
		dataRow[2] = hopDong.getNgayBatDau();
		dataRow[3] = hopDong.getNgayKetThuc();
		dataRow[4] = hopDong.getSoLuong();
		dataRow[5] = hopDong.getDonGiaSanPham();
		dataRow[6] = hopDong.getSanPham().getMaSP();
		dataRow[7] = hopDong.getSanPham().getTenSanPham();
		dataRow[8] = hopDong.getKhachHang();
		dataRow[9] = hopDong.getPhone();
		dataRow[10] =  df.format(hopDong.getTongTien()) ;
    	tableModel.addRow(dataRow);	

	}
	table.setModel(tableModel);
		
	}
	//-------Tìm kiếm theo tên--------------------//
		public void timKiemTheoTenHopDong() {


			DecimalFormat df = new DecimalFormat("###,### VND");
			tableModel.setRowCount(0);
			String tenhopDong = txtTenHopDong.getText();
			for (HopDong hopDong : hd_dao.findHopDongByTenHopDong(tenhopDong)) {
				
				Object dataRow [] = new Object[11];
				dataRow[0] = hopDong.getMaHD();
				dataRow[1] = hopDong.getTenHopDong();
				dataRow[2] = hopDong.getNgayBatDau();
				dataRow[3] = hopDong.getNgayKetThuc();
				dataRow[4] = hopDong.getSoLuong();
				dataRow[5] = hopDong.getDonGiaSanPham();
				dataRow[6] = hopDong.getSanPham().getMaSP();
				dataRow[7] = hopDong.getSanPham().getTenSanPham();
				dataRow[8] = hopDong.getKhachHang();
				dataRow[9] = hopDong.getPhone();
				dataRow[10] =  df.format(hopDong.getTongTien()) ;
		    	tableModel.addRow(dataRow);	
				
			}
			table.setModel(tableModel);

		}
		//tìm kiếm theo khách hàng
	public void timKiemTheoKhachHang() {


		DecimalFormat df = new DecimalFormat("###,### VND");
			tableModel.setRowCount(0);
			String tenKhachHang = txtTenKhachHang.getText();
			for (HopDong hopDong : hd_dao.findHopDongByKhachHang(tenKhachHang)) {
				
				Object dataRow [] = new Object[11];
				dataRow[0] = hopDong.getMaHD();
				dataRow[1] = hopDong.getTenHopDong();
				dataRow[2] = hopDong.getNgayBatDau();
				dataRow[3] = hopDong.getNgayKetThuc();
				dataRow[4] = hopDong.getSoLuong();
				dataRow[5] = hopDong.getDonGiaSanPham();
				dataRow[6] = hopDong.getSanPham().getMaSP();
				dataRow[7] = hopDong.getSanPham().getTenSanPham();
				dataRow[8] = hopDong.getKhachHang();
				dataRow[9] = hopDong.getPhone();
				dataRow[10] =  df.format(hopDong.getTongTien()) ;
		    	tableModel.addRow(dataRow);		
				
			}
			table.setModel(tableModel);

		}
	//tim kiem theo so dien thoai
	public void timKiemTheoSoDienThoai() {


		DecimalFormat df = new DecimalFormat("###,### VND");
		tableModel.setRowCount(0);
		String sdt = txtSDT.getText();
		for (HopDong hopDong : hd_dao.findHopDongBySoDienThoai(sdt)) {
			
			Object dataRow [] = new Object[11];
			dataRow[0] = hopDong.getMaHD();
			dataRow[1] = hopDong.getTenHopDong();
			dataRow[2] = hopDong.getNgayBatDau();
			dataRow[3] = hopDong.getNgayKetThuc();
			dataRow[4] = hopDong.getSoLuong();
			dataRow[5] = hopDong.getDonGiaSanPham();
			dataRow[6] = hopDong.getSanPham().getMaSP();
			dataRow[7] = hopDong.getSanPham().getTenSanPham();
			dataRow[8] = hopDong.getKhachHang();
			dataRow[9] = hopDong.getPhone();
			dataRow[10] =  df.format(hopDong.getTongTien()) ;
	    	tableModel.addRow(dataRow);			
			
		}
		table.setModel(tableModel);

	}
	//tim kiem theo tong tien
	public void timKiemTheoTongTien() {


		DecimalFormat df = new DecimalFormat("###,### VND");
		tableModel.setRowCount(0);
		String tT = txtTT.getText();
		for (HopDong hopDong : hd_dao.findHopDongByTongTien(tT)) {
			
			Object dataRow [] = new Object[11];
			dataRow[0] = hopDong.getMaHD();
			dataRow[1] = hopDong.getTenHopDong();
			dataRow[2] = hopDong.getNgayBatDau();
			dataRow[3] = hopDong.getNgayKetThuc();
			dataRow[4] = hopDong.getSoLuong();
			dataRow[5] = hopDong.getDonGiaSanPham();
			dataRow[6] = hopDong.getSanPham().getMaSP();
			dataRow[7] = hopDong.getSanPham().getTenSanPham();
			dataRow[8] = hopDong.getKhachHang();
			dataRow[9] = hopDong.getPhone();
			dataRow[10] =  df.format(hopDong.getTongTien()) ;
	    	tableModel.addRow(dataRow);
			
		}
		table.setModel(tableModel);

	}
	public static void main(String[] args) {
		new TraCuuHopDong_GUI().setVisible(true);
	}
	
//	private class SwingAction extends AbstractAction {
//		public SwingAction() {
//			putValue(NAME, "SwingAction");
//			putValue(SHORT_DESCRIPTION, "Some short description");
//		}
//		public void actionPerformed(ActionEvent e) {
//		}
//	}
}
