package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connect.connectDB;
import dao.Login_DAO;
import dao.TaiKhoan_DAO;
import entity.TaiKhoan;
import helper.ShareDate;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;

public class DangNhap_GUI extends JFrame implements ActionListener, MouseListener{

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPwd;
	private JButton btnExit;
	private JButton btnLogin;
	private Login_DAO tk_dao;
	private JLabel lblNewLabel_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap_GUI frame = new DangNhap_GUI();
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
	public DangNhap_GUI() {
		connectDB.getInstance().connect();
		tk_dao = new Login_DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 425);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ĐĂNG NHẬP");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setBounds(388, 29, 254, 62);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("G:\\Ki5 HK1(2022-2023)\\PTUD\\Source\\QuanLyLuongSanPham\\image\\keyyy.png"));
		lblNewLabel_1.setBounds(48, 52, 256, 268);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Tài khoản:");
		lblNewLabel_2.setIcon(new ImageIcon("G:\\Ki5 HK1(2022-2023)\\PTUD\\Source\\QuanLyLuongSanPham\\image\\user.png"));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_2.setBounds(309, 125, 185, 53);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Mật khẩu:");
		lblNewLabel_3.setIcon(new ImageIcon("G:\\Ki5 HK1(2022-2023)\\PTUD\\Source\\QuanLyLuongSanPham\\image\\pass.png"));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_3.setBounds(309, 188, 182, 53);
		contentPane.add(lblNewLabel_3);

		txtUser = new JTextField();
		txtUser.setBounds(504, 142, 192, 28);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

		txtPwd = new JPasswordField();
		txtPwd.setBounds(501, 205, 195, 28);
		contentPane.add(txtPwd);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Nhớ mật khẩu");
		chckbxNewCheckBox.setSelected(true);
		chckbxNewCheckBox.setFont(new Font("Times New Roman", Font.BOLD, 13));
		chckbxNewCheckBox.setBounds(503, 239, 109, 21);
		contentPane.add(chckbxNewCheckBox);

		lblNewLabel_4 = new JLabel("<HTML><U>Quên mật khẩu?</U></HTML>");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_4.setBounds(452, 333, 123, 31);
		lblNewLabel_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblNewLabel_4);
		lblNewLabel_4.addMouseListener(this);

		btnLogin = new JButton("Đăng nhập");
		btnLogin.setBackground(new Color(128, 255, 255));
		btnLogin.setIcon(new ImageIcon("G:\\Ki5 HK1(2022-2023)\\PTUD\\Source\\QuanLyLuongSanPham\\image\\login1.png"));
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnLogin.setBounds(309, 271, 182, 47);
		contentPane.add(btnLogin);

		btnExit = new JButton("Thoát");
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.setIcon(new ImageIcon("G:\\Ki5 HK1(2022-2023)\\PTUD\\Source\\QuanLyLuongSanPham\\image\\exit.png"));
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnExit.setBounds(518, 274, 149, 41);
		contentPane.add(btnExit);

		//
		btnLogin.addActionListener(this);
		btnLogin.setMnemonic(KeyEvent.VK_ENTER);
		btnExit.addActionListener(this);
		btnExit.setMnemonic(KeyEvent.VK_X);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnLogin)) {
			if(login() == 1) {
				new Home_GUI().setVisible(true);
				this.setVisible(false);
			}
		}
		else {
			exit();
		}
	}
	//
	private int login() {
		if(txtUser.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập tài khoản!", "Chú ý", JOptionPane.ERROR_MESSAGE);
			txtUser.requestFocus();
			return 0;
		}
		else if(txtPwd.getPassword().length==0) {
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập mật khẩu!", "Chú ý", JOptionPane.ERROR_MESSAGE);
			txtPwd.requestFocus();
			return 0;
		}
		else {
			TaiKhoan l = tk_dao.login(txtUser.getText(), String.valueOf(txtPwd.getPassword()));
			if(l == null) {
				JOptionPane.showMessageDialog(null, "Bạn nhập sai tài khoản hoặc mật khẩu!");
				txtUser.selectAll();
				txtUser.requestFocus();
				return 0;
			}
			else {
				ShareDate.nguoiDangNhap = l;
				System.out.println(ShareDate.nguoiDangNhap);
				this.dispose();
				return 1;
			}
		}
	}
	private void exit() {
		System.exit(0);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.browse(new URI("http://fb.com"));
		} catch (Exception e2) {
			// TODO: handle exception
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
}
