package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connect.connectDB;
import dao.CongDoan_DAO;
import dao.CongNhan_DAO;
import entity.CongNhan;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TraCuuCongNhan_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaCongNhan;
	private JTextField txtTenCongNhan;
	private JTextField txtDiaChi;
	private JTextField txtSoDienThoai;
	private JTable table;
	private DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TraCuuCongNhan_GUI frame = new TraCuuCongNhan_GUI();
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
	public TraCuuCongNhan_GUI() {
		connectDB.getInstance().connect();
		setBounds(150, 50, 1200, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("THÔNG TIN CÔNG NHÂN");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(489, 10, 260, 50);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.GRAY, 3));
		panel.setBounds(32, 85, 1144, 203);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtMaCongNhan = new JTextField();
		txtMaCongNhan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				timKiemTheoMaCongNhan();
			}
		});
		txtMaCongNhan.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtMaCongNhan.setBounds(199, 38, 316, 19);
		panel.add(txtMaCongNhan);
		txtMaCongNhan.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Mã công nhân");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1.setBounds(74, 40, 107, 16);
		panel.add(lblNewLabel_1);
		
		txtTenCongNhan = new JTextField();
		txtTenCongNhan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				timKiemTheoTenCongNhan();
			}
		});
		txtTenCongNhan.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTenCongNhan.setColumns(10);
		txtTenCongNhan.setBounds(199, 93, 316, 19);
		panel.add(txtTenCongNhan);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên công nhân");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(74, 95, 107, 16);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Địa chỉ ");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_2.setBounds(578, 40, 74, 16);
		panel.add(lblNewLabel_1_2);
		
		txtDiaChi = new JTextField();
		txtDiaChi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				timKiemTheoDiaChiCongNhan();
			}
		});
		txtDiaChi.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(695, 38, 403, 19);
		panel.add(txtDiaChi);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Số điện thoại");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(578, 95, 107, 16);
		panel.add(lblNewLabel_1_1_1);
		
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				timKiemTheoSoDienThoai();
			}
		});
		txtSoDienThoai.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBounds(695, 93, 403, 19);
		panel.add(txtSoDienThoai);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 337, 1144, 366);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setModel(tableModel = new DefaultTableModel (
			new Object[][] {
				
			},
			new String[] {
				"MaCN", "HoTen", "Phai", "Ngay Sinh", "\u0110\u1ECBa ch\u1EC9 ", "S\u0110T", "Ng\u00E0y TGCT", "PhongBan", "Tr\u1EA1ng Th\u00E1i"
			}
		));
		scrollPane.setViewportView(table);
		loadData();
	}
	
	public void loadData()
	{   
		CongNhan_DAO cn_dao = new CongNhan_DAO();
		ArrayList<CongNhan> listCongNhan = cn_dao.getAllCongNhan();
		for (CongNhan congNhan : listCongNhan) {
			String rowData [] = {
				congNhan.getMaCN(),congNhan.getHoTen(),congNhan.isPhai()==1?"Nam":"Nữ",congNhan.getNgaySinh()+"",congNhan.getDiaChi(),congNhan.getPhone(),congNhan.getNgayThamGiaCongTac()+"",congNhan.getPhong().getTenPhong(),congNhan.getTinhTrang()
			};
			tableModel.addRow(rowData);
		}
	   table.setModel(tableModel);
	}
	
	public void timKiemTheoTenCongNhan()
	{   tableModel.setRowCount(0);
		String name = txtTenCongNhan.getText();
		CongNhan_DAO cn_dao = new CongNhan_DAO();
		ArrayList<CongNhan> listCongNhan = (ArrayList<CongNhan>) cn_dao.findCongNhanByName(name);
		for (CongNhan congNhan : listCongNhan) {
			String rowData [] = {
				congNhan.getMaCN(),congNhan.getHoTen(),congNhan.isPhai()==1?"Nam":"Nữ",congNhan.getNgaySinh()+"",congNhan.getDiaChi(),congNhan.getPhone(),congNhan.getNgayThamGiaCongTac()+"",congNhan.getPhong().getTenPhong(),congNhan.getTinhTrang()
			};
			tableModel.addRow(rowData);
		}
	   table.setModel(tableModel);
	}
	public void timKiemTheoDiaChiCongNhan()
	{   tableModel.setRowCount(0);
		String diaChi = txtDiaChi.getText();
		CongNhan_DAO cn_dao = new CongNhan_DAO();
		ArrayList<CongNhan> listCongNhan = (ArrayList<CongNhan>) cn_dao.findCongNhanByDiaChi(diaChi);
		for (CongNhan congNhan : listCongNhan) {
			String rowData [] = {
				congNhan.getMaCN(),congNhan.getHoTen(),congNhan.isPhai()==1?"Nam":"Nữ",congNhan.getNgaySinh()+"",congNhan.getDiaChi(),congNhan.getPhone(),congNhan.getNgayThamGiaCongTac()+"",congNhan.getPhong().getTenPhong(),congNhan.getTinhTrang()
			};
			tableModel.addRow(rowData);
		}
	   table.setModel(tableModel);
	}
	public void timKiemTheoMaCongNhan()
	{   tableModel.setRowCount(0);
		String ma = txtMaCongNhan.getText();
		CongNhan_DAO cn_dao = new CongNhan_DAO();
		ArrayList<CongNhan> listCongNhan = (ArrayList<CongNhan>) cn_dao.findCongNhanByMa(ma);
		for (CongNhan congNhan : listCongNhan) {
			String rowData [] = {
				congNhan.getMaCN(),congNhan.getHoTen(),congNhan.isPhai()==1?"Nam":"Nữ",congNhan.getNgaySinh()+"",congNhan.getDiaChi(),congNhan.getPhone(),congNhan.getNgayThamGiaCongTac()+"",congNhan.getPhong().getTenPhong(),congNhan.getTinhTrang()
			};
			tableModel.addRow(rowData);
		}
	   table.setModel(tableModel);
	}
	public void timKiemTheoSoDienThoai()
	{   tableModel.setRowCount(0);
		String sdt = txtSoDienThoai.getText();
		CongNhan_DAO cn_dao = new CongNhan_DAO();
		ArrayList<CongNhan> listCongNhan = (ArrayList<CongNhan>) cn_dao.findCongNhanBySoDienThoai(sdt);
		for (CongNhan congNhan : listCongNhan) {
			String rowData [] = {
				congNhan.getMaCN(),congNhan.getHoTen(),congNhan.isPhai()==1?"Nam":"Nữ",congNhan.getNgaySinh()+"",congNhan.getDiaChi(),congNhan.getPhone(),congNhan.getNgayThamGiaCongTac()+"",congNhan.getPhong().getTenPhong(),congNhan.getTinhTrang()
			};
			tableModel.addRow(rowData);
		}
	   table.setModel(tableModel);
	}
}
