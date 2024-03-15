package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connect.connectDB;
import dao.SanPham_DAO;
import entity.SanPham;

import javax.swing.JButton;

public class XemSanPhamHoanThanh_GUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable table;
	private SanPham_DAO sp_dao;
	private DefaultTableModel tableModel;
	private JButton btnIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new XemSanPhamHoanThanh_GUI().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public XemSanPhamHoanThanh_GUI() {
		connectDB.getInstance().connect();
		setAlwaysOnTop(true);
		sp_dao = new SanPham_DAO();
		setBounds(100, 100, 999, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_tt = new JLabel("BẢNG SẢN PHẨM");
		lbl_tt.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_tt.setBounds(354, 26, 227, 38);
		contentPane.add(lbl_tt);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 74, 909, 400);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		table.setModel(tableModel = new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "\u0110\u01A1n v\u1ECB t\u00EDnh", "S\u1ED1 c\u00F4ng \u0111o\u1EA1n", "Ch\u1EA5t li\u1EC7u", "K\u00EDch th\u01B0\u1EDBc", "M\u00E0 s\u1EAFc", "\u0110\u01A1n gi\u00E1", "Tr\u1EA1ng th\u00E1i"
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
		SanPham_DAO dao = new SanPham_DAO();
		List<SanPham> list = dao.findAll();
		tableModel.setRowCount(0);
		for (SanPham nv : list) {
			if(nv.getTrangThai().equals("Hoàn thành")) {
				tableModel.addRow(new Object[] {nv.getMaSP(), nv.getTenSanPham(),
						nv.getDonViTinh(), nv.getSoCongDoan(), nv.getChatLieu(),
						nv.getKichThuoc(), nv.getMauSac(), nv.getDonGia(), nv.getTrangThai()});
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
			String thongTin = "DANH SÁCH TOÀN BỘ SẢN PHẨM ĐÃ HOÀN THÀNH";
			
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
