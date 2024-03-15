package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import connect.connectDB;
import dao.CongNhan_DAO;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.CongNhan;
import entity.NhanVien;
import entity.PhongBan;
import entity.TaiKhoan;
import helper.ImageHepler;

import javax.swing.JTextField;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import com.toedter.calendar.JDateChooser;

public class DoiMatKhau_GUI extends JPanel implements ActionListener, MouseListener{
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField txtTen;
	private JPasswordField txtMKM;
	private JPasswordField txtMKCC;
	private TaiKhoan_DAO nd_dao;
	private JButton btnXoa;
	private JButton btnDMK;
	private JDateChooser txtNT;
	private JDateChooser txtNCN;
	/**
	 * Create the panel.
	 */
	public DoiMatKhau_GUI() {
		connectDB.getInstance().connect();
		nd_dao = new TaiKhoan_DAO();

		setLayout(null);

		JLabel lbl_tt = new JLabel("\u0110\u1ED4I M\u1EACT KH\u1EA8U");
		lbl_tt.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_tt.setBounds(750, 20, 201, 30);
		add(lbl_tt);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 91, 556, 628);
		add(scrollPane);
		String header[] = {"Tên tài khoản", "Mã nhân viên", "Ngày thêm", "Ngày cập nhật", "Vai Trò"};
		tableModel = new DefaultTableModel(header, 0);
		table = new JTable(tableModel);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		scrollPane.setViewportView(table);

		JLabel lblTen = new JLabel("Tên tài khoản:");
		lblTen.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTen.setBounds(612, 236, 115, 30);
		add(lblTen);

		txtTen = new JTextField();
		txtTen.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		txtTen.setBounds(750, 243, 550, 19);
		add(txtTen);
		txtTen.setEnabled(false);
		txtTen.setColumns(10);

		JLabel lblMKC = new JLabel("Mật khẩu cũ: ");
		lblMKC.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMKC.setBounds(612, 311, 108, 30);
		add(lblMKC);

		JLabel lblMKM = new JLabel("Mật khẩu mới: ");
		lblMKM.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMKM.setBounds(612, 390, 125, 30);
		add(lblMKM);

		JLabel lbl_tttk = new JLabel("BẢNG TÀI KHOẢN");
		lbl_tttk.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_tttk.setBounds(232, 51, 186, 30);
		add(lbl_tttk);

		txtMKM = new JPasswordField();
		txtMKM.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtMKM.setBounds(750, 397, 550, 19);
		add(txtMKM);

		txtMKCC = new JPasswordField();
		txtMKCC.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtMKCC.setBounds(750, 318, 550, 19);
		add(txtMKCC);

		btnDMK = new JButton("Đổi mật khẩu");
		btnDMK.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnDMK.setBounds(785, 467, 186, 58);
		add(btnDMK);

		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnXoa.setBounds(1022, 467, 145, 58);
		add(btnXoa);

		btnXoa.addActionListener(this);
		btnDMK.addActionListener(this);
		btnXoa.setIcon(new ImageIcon("image/delete.png"));
		btnDMK.setIcon(new ImageIcon("image/rs.png"));
		btnDMK.setMnemonic(KeyEvent.VK_U);
		btnXoa.setMnemonic(KeyEvent.VK_D);
		//
		DocDuLieuVaoTable();
		table.addMouseListener(this);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		JLabel lblNT = new JLabel("Ngày thêm:");
		lblNT.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNT.setBounds(612, 91, 96, 30);
		add(lblNT);

		JLabel lblNCN = new JLabel("Ngày cập nhật:");
		lblNCN.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNCN.setBounds(612, 158, 118, 30);
		add(lblNCN);
		
		txtNT = new JDateChooser();
		txtNT.getSpinner().setEnabled(false);
		txtNT.setBounds(750, 91, 550, 20);
		add(txtNT);
		
		txtNCN = new JDateChooser();
		txtNCN.getSpinner().setEnabled(false);
		txtNCN.setBounds(750, 168, 550, 20);
		add(txtNCN);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			int row = table.getSelectedRow();
			if(row >= 0) {
				String id = (String) table.getValueAt(row, 0);
				TaiKhoan_DAO dao = new TaiKhoan_DAO();
				TaiKhoan cn = dao.findByID(id);
				if(cn != null) {
					txtTen.setText(cn.getTaiKhoan());
					txtNT.setDate(cn.getNgayThem());
					try {
						txtNCN.setDate(cn.getNgayCapNhat());
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
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
		if(o == btnDMK){
			int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn đổi mật khẩu không?", "Chú Ý", JOptionPane.YES_NO_OPTION);
			if(luaChon == JOptionPane.YES_OPTION) {
				updateTaiKhoan();
			}
		}
		else if(o == btnXoa) {
			int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?", "Chú Ý", JOptionPane.YES_NO_OPTION);
			if(luaChon == JOptionPane.YES_OPTION) {
				deleteNguoiDung();
			}
		}
	}
	public void reset() {
		txtTen.setText("");
		txtMKM.setText("");
		txtMKCC.setText("");
	}
	public void exit() {
		System.exit(0);
	}
	//	public void updateNguoiDung() {
	//		int row = table.getSelectedRow();
	//		if(row < 0 || (txtTen.getText().equals(""))) {
	//			JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản muốn cập nhật!");
	//			return;
	//		}
	//		else {
	//			TaiKhoan t = new TaiKhoan();
	//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	//			t.setTaiKhoan(txtTen.getText());
	//			String nt = sdf.format(txtNT.getDate());
	//			t.setMatKhau(String.valueOf(txtMKM.getPassword()));
	//			t.setNgayThem(java.sql.Date.valueOf(nt));
	//			//			t.setNgayThem(java.sql.Date.valueOf(txtNT.getText()));
	//			//			long millis=System.currentTimeMillis();
	//			//			Date ngayCapNhat = new Date(millis);
	//			//			String sn = sdf.format(ngayCapNhat);
	//			t.setNgayCapNhat(txtNCN.getDate());
	//			if(row >= 0) {
	//				if(regex()) {
	//					SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
	//					if(nd_dao.update(t)) {
	//						table.setValueAt(s.format(t.getNgayCapNhat()), row, 3);
	//						return;
	//					}	
	//				}
	//			}
	//		}	
	//	}
	//
	public void updateTaiKhoan() {
		try {
			TaiKhoan cn = new TaiKhoan();
			cn.setTaiKhoan(txtTen.getText());
			cn.setMatKhau(String.valueOf(txtMKM.getPassword()));
			//
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sn = sdf.format(txtNT.getDate());
			cn.setNgayThem(Date.valueOf(sn));
			//
			String ntg = sdf.format(txtNCN.getDate());
			
			cn.setNgayCapNhat(Date.valueOf(ntg));
			//
			TaiKhoan_DAO dao = new TaiKhoan_DAO();
			int row = table.getSelectedRow();
			if(regex()) {
				if(row >= 0) {
					if(dao.update(cn)) {
						SimpleDateFormat ns = new SimpleDateFormat("dd/MM/yyyy");
						String ngaySinh = ns.format(txtNT.getDate());
						table.setValueAt(ngaySinh, row, 2);
						String ng = ns.format(txtNCN.getDate());
						table.setValueAt(ng, row, 3);
						//
						JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
					}else {
						JOptionPane.showMessageDialog(this, "Đổi mật khẩu không thành công");
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public int deleteNguoiDung() {
		TaiKhoan_DAO tk_dao = new TaiKhoan_DAO();
		int row = table.getSelectedRow();
		if(row < 0 || (txtTen.getText().equals(""))) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản muốn xóa!");
			return 0;
		}
		if(row >= 0) {
			if(tk_dao.delete(txtTen.getText())) {
				tableModel.removeRow(row);
				reset();
				JOptionPane.showMessageDialog(this, "Xóa thành công");
				return 1;
			}
			else
				JOptionPane.showMessageDialog(this, "Xóa không thành công!");
		}
		return 0;
	}
	//
	private void DocDuLieuVaoTable() {
		TaiKhoan_DAO ds = new TaiKhoan_DAO();
		List<TaiKhoan> list = ds.getAllNguoiDung();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (TaiKhoan nd : list) {
			if(sdf.format(nd.getNgayCapNhat()).equals("09/06/2002")) {
				String [] dataRow = {nd.getTaiKhoan(), nd.getNvhc().getMaNV(), sdf.format(nd.getNgayThem()), "", nd.getVaiTro()};
				tableModel.addRow(dataRow);
			}
			else{
				String [] dataRow = {nd.getTaiKhoan(), nd.getNvhc().getMaNV(), sdf.format(nd.getNgayThem()), sdf.format(nd.getNgayCapNhat()), nd.getVaiTro()};
				tableModel.addRow(dataRow);
			}
		}
		table.setModel(tableModel);
	}
	public void reset_table() {
		tableModel.setRowCount(0);
		DocDuLieuVaoTable();
	}
	public boolean regex() {
		if(txtTen.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn tài khoản muốn cập nhập trên bảng tài khoản!");
			return false;
		}
		else if(String.valueOf(txtMKCC.getPassword()).equals("")) {
			txtMKCC.requestFocus();
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập mật khẩu cũ!");
			return false;
		}
		else if(String.valueOf(txtMKCC.getPassword()).length() > 0 && !kiemTraTaiKhoan(String.valueOf(txtMKCC.getPassword()))) {
			JOptionPane.showMessageDialog(this, "Mật khẩu cũ không đúng!");
			txtMKCC.selectAll();
			System.out.println(String.valueOf(txtMKCC.getPassword()));
			txtMKCC.requestFocus();
			return false;
		}
		else if(String.valueOf(txtMKM.getPassword()).equals("")) {
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập mật khẩu mới!");
			txtMKM.requestFocus();
			return false;
		}
		return true;
	}
	public boolean kiemTraTaiKhoan(String mk) {
		List<TaiKhoan> tk;
		try {
			tk = nd_dao.findAll();
			for (TaiKhoan t : tk) {
				if(t.getMatKhau().contains(mk))
					return true;
			}
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
