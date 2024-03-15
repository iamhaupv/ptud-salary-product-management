package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import connect.connectDB;
import dao.ChamCong_NhanVien_DAO;
import entity.ChamCong_NhanVien;
import entity.LuongNhanVien;
import helper.KNBCC;
import helper.LNV;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class BangChamCong_GUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private ChamCong_NhanVien_DAO dao_congNhanVien;
	private JLabel lblTongLuong;
	private JLabel lblPhat;
	private JLabel lblBHXH;
	private JLabel lblThuong;
	private JLabel lblPhuCap;
	private JLabel lblMa;
	private JLabel lblNS;
	private JLabel lblTen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BangChamCong_GUI frame = new BangChamCong_GUI();
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
	public BangChamCong_GUI() {
		connectDB.getInstance().connect();
		dao_congNhanVien = new ChamCong_NhanVien_DAO();
		setBounds(100, 100, 857, 641);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setAlwaysOnTop(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lbl_tt = new JLabel("BẢNG CHẤM CÔNG");
		lbl_tt.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_tt.setBounds(324, 283, 245, 31);
		contentPane.add(lbl_tt);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(93, 340, 638, 254);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(tableModel = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {"Mã chấm công", "Ngày chấm công",
				"Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Điểm danh", "Phép"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Tên nhân viên:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel.setBounds(107, 111, 116, 21);
		contentPane.add(lblNewLabel);
		
		lblTen = new JLabel("");
		lblTen.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTen.setBounds(236, 111, 151, 21);
		contentPane.add(lblTen);
		
		JLabel lblNewLabel_1 = new JLabel("Ngày sinh:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_1.setBounds(107, 157, 84, 21);
		contentPane.add(lblNewLabel_1);
		
		lblNS = new JLabel("");
		lblNS.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNS.setBounds(236, 158, 126, 19);
		contentPane.add(lblNS);
		
		JLabel lblNewLabel_2 = new JLabel("Mã nhân viên:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_2.setBounds(109, 62, 114, 25);
		contentPane.add(lblNewLabel_2);
		
		lblMa = new JLabel("");
		lblMa.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMa.setBounds(229, 64, 129, 23);
		contentPane.add(lblMa);
		
		JLabel lblNewLabel_3 = new JLabel("Phụ cấp: ");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_3.setBounds(107, 211, 73, 21);
		contentPane.add(lblNewLabel_3);
		
		lblPhuCap = new JLabel("");
		lblPhuCap.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblPhuCap.setBounds(236, 206, 137, 31);
		contentPane.add(lblPhuCap);
		
		JLabel lblNewLabel_4 = new JLabel("Thưởng:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_4.setBounds(543, 64, 73, 21);
		contentPane.add(lblNewLabel_4);
		
		lblThuong = new JLabel("");
		lblThuong.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblThuong.setBounds(648, 66, 126, 17);
		contentPane.add(lblThuong);
		
		JLabel lblNewLabel_5 = new JLabel("Phạt:");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_5.setBounds(543, 111, 45, 21);
		contentPane.add(lblNewLabel_5);
		
		lblPhat = new JLabel("");
		lblPhat.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblPhat.setBounds(648, 111, 126, 21);
		contentPane.add(lblPhat);
		
		JLabel lblNewLabel_6 = new JLabel("BHXH: ");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_6.setBounds(543, 152, 59, 31);
		contentPane.add(lblNewLabel_6);
		
		lblBHXH = new JLabel("");
		lblBHXH.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblBHXH.setBounds(648, 157, 111, 21);
		contentPane.add(lblBHXH);
		
		JLabel lblNewLabel_7 = new JLabel("Tổng lương:");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_7.setBounds(543, 211, 95, 21);
		contentPane.add(lblNewLabel_7);
		
		lblTongLuong = new JLabel("");
		lblTongLuong.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTongLuong.setBounds(648, 211, 137, 20);
		contentPane.add(lblTongLuong);
		
		JLabel lblNewLabel_8 = new JLabel("PHIẾU LƯƠNG");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel_8.setBounds(295, 5, 260, 49);
		contentPane.add(lblNewLabel_8);
		loadChamCong();
	}
	// lay thang nam
	public void loadChamCong() {
		
		List<ChamCong_NhanVien> list = dao_congNhanVien.getAllBangChamCong();
		for (ChamCong_NhanVien congNhanVien : list) {
			if(KNBCC.nhanVien.getMaNV().equals(congNhanVien.getNhanVien().getMaNV())) {
				if(LNV.luongNhanVien.getThangLuong().getMonth()+1 == congNhanVien.getNgayChamCong().getMonth()+1) {
					load();
					if(congNhanVien.isDiemDanh()) {
						congNhanVien.setPhep(false);
					}
					else {
						congNhanVien.setPhep(true);
					}
					String [] rowData = {
							congNhanVien.getMaCCNV(),congNhanVien.getNgayChamCong()+"",
							
							congNhanVien.getNhanVien().getMaNV(),congNhanVien.getNhanVien().getHoTen(),
							congNhanVien.getNhanVien().getPhone(),congNhanVien.isDiemDanh()?"Có mặt":"",
									congNhanVien.isPhep()?"Có phép":"" 
					};
					tableModel.addRow(rowData);
				}
			}
		}
		table.setModel(tableModel);
	}
	public void load() {
		lblMa.setText(KNBCC.nhanVien.getMaNV());
		lblTen.setText(KNBCC.nhanVien.getHoTen());
		lblNS.setText(KNBCC.nhanVien.getNgaySinh()+"");
		lblPhuCap.setText(LNV.luongNhanVien.getPhuCap()+"");
		lblThuong.setText(LNV.luongNhanVien.getPhuCap()+"");
		lblPhat.setText(LNV.luongNhanVien.getPhat()+"");
		lblBHXH.setText(LNV.luongNhanVien.getBhxh()+"");
		lblTongLuong.setText(LNV.luongNhanVien.getTongLuong()+"");
	}
}
