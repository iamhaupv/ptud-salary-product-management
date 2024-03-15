package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connect.connectDB;
import dao.HopDong_DAO;
import dao.SanPham_DAO;
import entity.HopDong;
import entity.SanPham;

public class XemHopDongHoanThanh extends JFrame implements ActionListener{

	private HopDong_DAO hd_dao;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btnIn;
	public static void main(String[] args) {
		new XemHopDongHoanThanh().setVisible(true);
	}
	public XemHopDongHoanThanh() {
		// TODO Auto-generated constructor stub.
		connectDB.getInstance().connect();
		setAlwaysOnTop(true);
		hd_dao = new HopDong_DAO();
		setBounds(100, 100, 999, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_tt = new JLabel("BẢNG HỢP ĐỒNG");
		lbl_tt.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_tt.setBounds(380, 32, 227, 38);
		contentPane.add(lbl_tt);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 74, 909, 400);
		contentPane.add(scrollPane);

		table = new JTable();

		table.setModel(tableModel = new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 h\u1EE3p \u0111\u1ED3ng", "T\u00EAn h\u1EE3p \u0111\u1ED3ng", "Ng\u00E0y b\u1EAFt \u0111\u1EA7u", "Ng\u00E0y k\u1EBFt th\u00FAc", "S\u1ED1 l\u01B0\u1EE3ng", "\u0110\u01A1n gi\u00E1 s\u1EA3n ph\u1EA9m", "S\u1EA3n ph\u1EA9m", "T\u00EAn kh\u00E1ch h\u00E0ng", "T\u00EAn kh\u00E1ch h\u00E0ng", "Tổng tiền", "Tr\u1EA1ng th\u00E1i"
			}
		));
		scrollPane.setViewportView(table);

		btnIn = new JButton("IN");
		btnIn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnIn.setBounds(737, 39, 85, 29);
		contentPane.add(btnIn);
		btnIn.addActionListener(this);
		try {
			loadDataFromTable();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadDataFromTable() throws SQLException, ClassNotFoundException {
		List<HopDong> list = hd_dao.findAll();
		tableModel.setRowCount(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (HopDong cn : list) {
			if(cn.getTrangThai().equals("Hoàn thành")) {
				tableModel.addRow(new Object[] {cn.getMaHD(), cn.getTenHopDong(),
						sdf.format(cn.getNgayBatDau()), sdf.format(cn.getNgayKetThuc()),
						cn.getSoLuong(), cn.getDonGiaSanPham(), cn.getSanPham().getMaSP(),
						cn.getKhachHang(), cn.getPhone(), cn.getTongTien(), cn.getTrangThai()});
			}
		}
		table.setModel(tableModel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnIn)) {
			if(table.getRowCount() <= 0)
				return;
			String thongTin = "DANH SÁCH TOÀN BỘ HỢP ĐỒNG ĐÃ HOÀN THÀNH";

			MessageFormat header = new MessageFormat(thongTin);
			MessageFormat footer = new MessageFormat("Trang{0, number, integer}");
			try {
				table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
