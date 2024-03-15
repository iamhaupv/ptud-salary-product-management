package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import connect.connectDB;
import dao.ChamCong_CongNhan_DAO;
import dao.CongDoan_DAO;
import dao.CongNhan_DAO;
import entity.ChamCong_NhanVien;
import entity.CongDoan;
import entity.ChamCong_CongNhan;
import entity.CongNhan;
import entity.NhanVien;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ChamCong_CongNhan_GUI extends JPanel implements ActionListener {
	private JTable tableBangCongCN;
	private JTextField txtMaCong;
	private JTextField txtSoCongDoanHoanThanh;
	private JTable tableThongTinBangCC;
	private JScrollPane scrollPaneCongNhan;
	private DefaultTableModel tableModelBangCongCN;
	private JDateChooser dateNgayCong;
	private JComboBox cboCa;
	private JButton btnLayDanhSachChamCong;
	private JButton btnChamCong;
	private JButton btnXoa;
	private JButton btnCapNhat;
	private CongNhan_DAO congNhan_dao ;
	private ChamCong_CongNhan_DAO congCongNhan_dao;
	private DefaultTableModel tableModelThongTinBangCC;
	private JComboBox cboCongdoan;
	/**
	 * Create the panel.
	 */
	public ChamCong_CongNhan_GUI() {

		connectDB.getInstance().connect();
		congNhan_dao = new CongNhan_DAO();
		congCongNhan_dao = new ChamCong_CongNhan_DAO();
		setLayout(null);

		scrollPaneCongNhan = new JScrollPane();
		scrollPaneCongNhan.setBounds(103, 136, 989, 227);
		add(scrollPaneCongNhan);

		tableBangCongCN = new JTable();
		tableBangCongCN.setModel(tableModelBangCongCN = new DefaultTableModel(
				new Object[][] {

				},
				new String[] {
						"M\u00E3 c\u00F4ng nh\u00E2n", "T\u00EAn c\u00F4ng nh\u00E2n", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i","Phòng ban","Trạng thái" ,"Có mặt", "Có phép"
				}
				) {
			Class[] columnTypes = new Class[] {
					Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableBangCongCN.setFont(new Font("Times New Roman", Font.BOLD, 12));
		scrollPaneCongNhan.setViewportView(tableBangCongCN);

		JLabel lblNewLabel = new JLabel("BẢNG CÔNG NHÂN");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(476, 94, 204, 53);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã chấm công:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1.setBounds(1128, 122, 88, 25);
		add(lblNewLabel_1);

		txtMaCong = new JTextField();
		txtMaCong.setBackground(new Color(255, 255, 255));
		txtMaCong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtMaCong.setBounds(1281, 124, 177, 19);
		//txtMaCong.setText(autoMaHD());
		add(txtMaCong);
		txtMaCong.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Số công đoạn hoàn thành:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(1127, 212, 144, 25);
		add(lblNewLabel_1_1);

		dateNgayCong = new JDateChooser();
		dateNgayCong.setBounds(1280, 153, 178, 20);
		add(dateNgayCong);

		JLabel lblNewLabel_1_2 = new JLabel("Ca làm việc:");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_2.setBounds(1128, 181, 88, 25);
		add(lblNewLabel_1_2);

		cboCa = new JComboBox();
		cboCa.setModel(new DefaultComboBoxModel(new String[] {"Ca 1", "Ca 2", "Ca 3"}));
		cboCa.setFont(new Font("Times New Roman", Font.BOLD, 13));
		cboCa.setBounds(1281, 183, 177, 21);
		add(cboCa);

		JLabel lblNewLabel_1_1_1 = new JLabel("Ngày chấm công:");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(1128, 153, 99, 25);
		add(lblNewLabel_1_1_1);

		txtSoCongDoanHoanThanh = new JTextField();
		txtSoCongDoanHoanThanh.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtSoCongDoanHoanThanh.setColumns(10);
		txtSoCongDoanHoanThanh.setBackground(Color.WHITE);
		txtSoCongDoanHoanThanh.setBounds(1281, 214, 177, 19);
		add(txtSoCongDoanHoanThanh);

		btnLayDanhSachChamCong = new JButton("Lấy danh sách chấm công");
		btnLayDanhSachChamCong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLayDanhSachChamCong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnLayDanhSachChamCong.setBounds(1128, 273, 330, 40);
		add(btnLayDanhSachChamCong);

		btnChamCong = new JButton("Chấm công");
		btnChamCong.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnChamCong.setBounds(1128, 323, 99, 40);
		add(btnChamCong);

		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnXoa.setBounds(1253, 323, 96, 40);
		add(btnXoa);

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnCapNhat.setBounds(1359, 323, 99, 40);
		add(btnCapNhat);

		JScrollPane scrollPaneTTBangCC = new JScrollPane();
		scrollPaneTTBangCC.setBounds(103, 407, 1355, 359);
		add(scrollPaneTTBangCC);

		tableThongTinBangCC = new JTable();
		tableThongTinBangCC.setModel(tableModelThongTinBangCC =new DefaultTableModel(
				new Object[][] {

				},
				new String[] {
						"M\u00E3 c\u00F4ng", "Ng\u00E0y ch\u1EA5m c\u00F4ng", "Ca l\u00E0m vi\u1EC7c", "M\u00E3 nh\u00E2n vi\u00EAn", "T\u00EAn nh\u00E2n vi\u00EAn", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "S\u1ED1 c\u00F4ng \u0111o\u1EA1n ho\u00E0n th\u00E0nh", "\u0110i\u1EC3m danh ", "Ph\u00E9p"
				}
				));
		tableThongTinBangCC.setFont(new Font("Times New Roman", Font.BOLD, 13));
		scrollPaneTTBangCC.setViewportView(tableThongTinBangCC);

		JLabel lblThngTinBng = new JLabel("THÔNG TIN BẢNG CHẤM CÔNG CÔNG NHÂN");
		lblThngTinBng.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblThngTinBng.setBounds(574, 365, 437, 53);
		add(lblThngTinBng);

		JLabel lblChmCngCng = new JLabel("CHẤM CÔNG CÔNG NHÂN");
		lblChmCngCng.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblChmCngCng.setBounds(701, 10, 260, 53);
		add(lblChmCngCng);


		btnLayDanhSachChamCong.addActionListener(this);
		btnChamCong.addActionListener(this);
		btnXoa.addActionListener(this);
		
		JLabel lblNewLabel_1_1_2 = new JLabel(" Công đoạn");
		lblNewLabel_1_1_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_1_2.setBounds(1128, 238, 144, 25);
		add(lblNewLabel_1_1_2);
		
		cboCongdoan = new JComboBox();
		cboCongdoan.setFont(new Font("Times New Roman", Font.BOLD, 13));
		cboCongdoan.setBounds(1281, 240, 177, 21);
		add(cboCongdoan);
		CongDoan_DAO cd_dao = new CongDoan_DAO();
		ArrayList<CongDoan> list = cd_dao.getAllCongDoan();
		{
			for (CongDoan congDoan : list) {
				cboCongdoan.addItem(congDoan.getMaCD());
			}
		}
		
		
		loadBangThongTinChamCong();	
		//		loadDataFromTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnLayDanhSachChamCong)) {
			loadBangCongCongNhan();
		}else if(o.equals(btnChamCong))
		{
			ChamCong();
			//txtMaCong.setText(autoMaHD());
		}else if(o.equals(btnXoa))
		{   xoaChamCong();
			//txtMaCong.setText(autoMaHD());
		}
	}

	// load báº£ng nhĂ¢n viĂªn Ä‘á»ƒ cháº¥m cĂ´ng 

	private void loadBangCongCongNhan()
	{

		//tableModelCD.setRowCount(0);
		List<CongNhan> list = congNhan_dao.getAllCongNhanDangLamViec();
		for (CongNhan congNhan : list) {
			
			tableModelBangCongCN.addRow( new Object[] {
					congNhan.getMaCN(),congNhan.getHoTen(),congNhan.getPhone(),congNhan.getPhong().getTenPhong(),congNhan.getTinhTrang(),true ,false
			});
			
			
			
		}
		tableBangCongCN.setModel(tableModelBangCongCN);

	}
	private void loadBangThongTinChamCong()
	{
		List<ChamCong_CongNhan> list = congCongNhan_dao.getAllBangChamCongCongNhan();
		for (ChamCong_CongNhan congCongNhan : list) {
			String [] rowData = {
					congCongNhan.getMaCCCN(),congCongNhan.getNgayChamCong()+"",congCongNhan.getCaLamViec(),congCongNhan.getCongNhan().getMaCN(),congCongNhan.getCongNhan().getHoTen(),congCongNhan.getCongNhan().getPhone(),congCongNhan.getSoCongDoanHoanThanh()+"",congCongNhan.isDiemDanh()?"Có mặt":"Vắng mặt",congCongNhan.isPhep()?"Có phép":"Không phép" 
			};
			tableModelThongTinBangCC.addRow(rowData);
		}
		tableThongTinBangCC.setModel(tableModelThongTinBangCC);
	}

	private void ChamCong()


	{   ChamCong_CongNhan congCongNhan = new ChamCong_CongNhan();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String maChamCong = txtMaCong.getText();

	String caLamViec = cboCa.getSelectedItem().toString();

	int index = tableBangCongCN.getSelectedRow();

	//		boolean diemDanh1 = Boolean.parseBoolean(tableNhanVien.getValueAt(index, 3).toString());
	//		System.out.println(diemDanh1);
	//		boolean phep1 = Boolean.parseBoolean(tableNhanVien.getValueAt(index, 4).toString());
	//		System.out.println(phep1);
	//		String tenNhanVien = tableNhanVien.getValueAt(index, 1).toString();
	//		String tenPhone = tableNhanVien.getValueAt(index, 2).toString();
	//		String maNhanVien =  tableNhanVien.getValueAt(index, 0).toString();

	//		NhanVien nhanVien = new NhanVien(maNhanVien,tenNhanVien,tenPhone);

	String maCongNhan = tableBangCongCN.getValueAt(index, 0).toString();
	String tenCongNhan = tableBangCongCN.getValueAt(index, 1).toString();
	String phone = tableBangCongCN.getValueAt(index, 2).toString();
	CongNhan congNhan = new CongNhan(maCongNhan, tenCongNhan, phone);
	boolean diemDanh = Boolean.parseBoolean(tableBangCongCN.getValueAt(index, 5).toString());
	boolean phep = Boolean.parseBoolean(tableBangCongCN.getValueAt(index, 6).toString());
	int soCongDoanHoanThanh = Integer.parseInt(txtSoCongDoanHoanThanh.getText());
	String ncc = sdf.format(dateNgayCong.getDate());
	Date ngayChamCong = Date.valueOf(ncc);
    String congDoan = cboCongdoan.getSelectedItem().toString();
    CongDoan cd = new CongDoan(congDoan);


	//CongNhan = new Cham

	congCongNhan = new ChamCong_CongNhan(maChamCong, diemDanh, ngayChamCong, caLamViec, soCongDoanHoanThanh, congNhan,cd,phep);



	try {

		int hoiNhac = JOptionPane.showConfirmDialog(this,"Bạn có muốn chấm công không","Chú ý",JOptionPane.YES_NO_OPTION);
		if(hoiNhac==JOptionPane.YES_OPTION)
		{
			if(congCongNhan_dao.chamCongCongNhan(congCongNhan))
			{
				tableModelThongTinBangCC.addRow(new Object []  {
						congCongNhan.getMaCCCN(),congCongNhan.getNgayChamCong(),congCongNhan.getCaLamViec(),congCongNhan.getCongNhan().getMaCN(),congCongNhan.getCongNhan().getHoTen(),congCongNhan.getCongNhan().getPhone(),congCongNhan.getSoCongDoanHoanThanh(),congCongNhan.isDiemDanh()?"Có mặt":"Văng mặt",congCongNhan.isPhep()?"Có phép":"Không phép"
				});
				tableThongTinBangCC.setModel(tableModelThongTinBangCC);
				JOptionPane.showMessageDialog(this,"Chấm công thành công");
			}else
			{
				JOptionPane.showMessageDialog(this,"Trùng mã");
			}
		}

	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	}
	public void xoaChamCong()
	{
		int row = tableThongTinBangCC.getSelectedRow();
		if( row >= 0)
		{
			String maCong = (String) tableThongTinBangCC.getValueAt(row, 0);
			int hoiNhac = JOptionPane.showConfirmDialog(this,"Bạn có chéc muốn xóa không","Chú ý",JOptionPane.YES_NO_OPTION);
			if(hoiNhac == JOptionPane.YES_OPTION)
			{
				if(congCongNhan_dao.xoaBangChamCong(maCong))
				{
					tableModelThongTinBangCC.removeRow(row);
					JOptionPane.showMessageDialog(this,"Xóa thành công");
				}else
				{
					JOptionPane.showMessageDialog(this,"Xóa không thành công");
				}
		}
		}
	}
//	public String autoMaHD() {
//		String str;
//		int ma = congCongNhan_dao.getMaCCTD();
//		if (ma < 100)
//			str = "CC0000" + ma;
//		else if (ma < 1000)
//			str = "CC000" + ma;
//		else if (ma < 10000)
//			str = "CC00" + ma;
//		else if (ma < 100000)
//			str = "CC0" + ma;
//		else
//			str = "CC" + ma;
//		return str;
//
//	}
}
