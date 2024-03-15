package gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.mail.search.MessageNumberTerm;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import dao.TaiKhoan_DAO;
import entity.CongNhan;
import entity.TaiKhoan;
import helper.ShareDate;

public class Home_GUI extends JFrame implements ActionListener{

	private JLabel lblTitle;
	private JMenuItem menu_GioiThieu;
	private JMenuItem menuitemThemNguoiDung;
	private JMenuItem menuitemDoiMatKhau;
	private JMenuItem menuitemCongNhan;
	private JMenuItem menuitemNhanVien;
	private JMenuItem menuitemCongDoan;
	private JMenuItem menuitemChamCongCongNhan;
	private JMenuItem menuitemChamCongNhanVien;
	private JMenuItem menuitemLuongCongNhan;
	private JMenuItem menuitemLuongNhanVien;
	private JMenuItem menuitem_SanPham;
	private JMenuItem menuitem_HopDong;
	private JMenuItem menuitem_BangLuongNhanVien;
	private JMenuItem menuitem_BangLuongCongNhan;
	private JMenuItem menuitem_ThongKeLuongNhanVien;
	private JMenuItem menuitem_ThongKeLuongCongNhan;
	private JMenuItem menuitemPhanCongCongDoan;
	private JMenuItem menu_DangXuat;
	private JMenuItem menuitemThongKeHopDong;
	private JMenuItem acc;
	private JMenu menuTroGiup;
	private JMenuItem menuitemGioiThieu;
	private JMenuItem menuitemCachSuDung;
	private JMenu menuChamCong;
	private JMenu menuQuanLyHopDong;
	private JMenu menuQuanSanPham;
	private JMenu menuHeThong;
	private JMenu menuTrangChu;
	private JMenu menuTinhLuong;
	private JMenu menuQuanLyNhanSu;
	private JMenuItem menuitem_TrangChu;
	private JLabel lblAcc;
	public Home_GUI() {
		// TODO Auto-generated constructor stub
		setTitle("Trang chủ");
		//		setSize(1370, 750);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		//
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		// Trang chủ
		menuTrangChu = new JMenu("Trang chủ");


		menubar.add(menuTrangChu);
		menuTrangChu.setFont(new Font("Times new roman", Font.BOLD, 18));
		menuTrangChu.setIcon(new ImageIcon("image//home_.png"));
		// trangchu
		menuTrangChu.add(menuitem_TrangChu = new JMenuItem("Trang chủ"));
		menuitem_TrangChu.setFont(new Font("Times new roman", Font.BOLD, 18));
		menuitem_TrangChu.setIcon(new ImageIcon("image/home.png"));
		menuitem_TrangChu.addActionListener(this);
//		// gioi thieu
//		menuTrangChu.add(menu_GioiThieu = new JMenuItem("Giới thiệu"));
//		menu_GioiThieu.setFont(new Font("Times new roman", Font.BOLD, 18));
//		menu_GioiThieu.setIcon(new ImageIcon("image/info.png"));
//		menuTrangChu.addActionListener(this);
		// Hệ thống
		menuHeThong = new JMenu("Hệ thống");
		menuHeThong.setFont(new Font("Times new roman", Font.BOLD, 18));
		menuHeThong.setIcon(new ImageIcon("image//system_.png"));
		//
		menuHeThong.add(menuitemThemNguoiDung = new JMenuItem("Thêm người dùng"));
		menuitemThemNguoiDung.setFont(new Font("times new roman", Font.BOLD, 15));
		menuitemThemNguoiDung.setIcon(new ImageIcon("image//add_people.png"));
		//
		menuHeThong.add(menuitemDoiMatKhau = new JMenuItem("Đổi mật khẩu"));
		menuitemDoiMatKhau.setFont(new Font("times new roman", Font.BOLD, 15));
		menuitemDoiMatKhau.setIcon(new ImageIcon("image//change.png"));
		//
		menuitemThemNguoiDung.addActionListener(this);
		menuitemDoiMatKhau.addActionListener(this);

		// quản lý nhân sự
		menuQuanLyNhanSu = new JMenu("Nhân sự");
		menuQuanLyNhanSu.setFont(new Font("Times new roman", Font.BOLD, 18));
		menuQuanLyNhanSu.setIcon(new ImageIcon("image//person.png"));
		// nhan vien
		menuQuanLyNhanSu.add(menuitemNhanVien = new JMenuItem("Nhân viên"));
		menuitemNhanVien.setFont(new Font("times new roman", Font.BOLD, 15));
		menuitemNhanVien.setIcon(new ImageIcon("image//nv.png"));
		// cong nhan
		menuQuanLyNhanSu.add(menuitemCongNhan = new JMenuItem("Công nhân"));
		menuitemCongNhan.setFont(new Font("times new roman", Font.BOLD, 15));
		menuitemCongNhan.setIcon(new ImageIcon("image//woker1.png"));
		// dang ky su kien

		//  Quản lý sản phẩm
		menuQuanSanPham = new JMenu("Sản phẩm");
		menuQuanSanPham.setFont(new Font("Times new roman", Font.BOLD, 18));
		menuQuanSanPham.setIcon(new ImageIcon("image//shoe_.png"));

		// san pham
		menuQuanSanPham.add(menuitem_SanPham = new JMenuItem("Sản phẩm"));
		menuitem_SanPham.setFont(new Font("times new roman", Font.BOLD, 15));
		menuitem_SanPham.setIcon(new ImageIcon("image//shoe_.png"));
		// Cong doan
		menuQuanSanPham.add(menuitemCongDoan = new JMenuItem("Công đoạn"));
		menuitemCongDoan.setFont(new Font("times new roman", Font.BOLD, 15));
		menuitemCongDoan.setIcon(new ImageIcon("image//i.png"));
		// phan cong cong doan
		menuQuanSanPham.add(menuitemPhanCongCongDoan = new JMenuItem("Phân công - Công đoạn"));
		menuitemPhanCongCongDoan.setFont(new Font("times new roman", Font.BOLD, 15));
		menuitemPhanCongCongDoan.setIcon(new ImageIcon("image//o.png"));
		// Quản lý hợp đồng
		menuQuanLyHopDong = new JMenu("Hợp đồng");
		menuQuanLyHopDong.setFont(new Font("Times new roman", Font.BOLD, 18));
		menuQuanLyHopDong.setIcon(new ImageIcon("image//contract_.png"));
		//
		menuQuanLyHopDong.add(menuitem_HopDong = new JMenuItem("Hợp đồng"));
		menuitem_HopDong.setFont(new Font("times new roman", Font.BOLD, 15));
		menuitem_HopDong.setIcon(new ImageIcon("image//contract_.png"));
		// thong ke hop dong
		menuQuanLyHopDong.add(menuitemThongKeHopDong = new JMenuItem("Thống kê hợp đồng"));
		menuitemThongKeHopDong.setFont(new Font("times new roman", Font.BOLD, 15));
		menuitemThongKeHopDong.setIcon(new ImageIcon("image//statistic.png"));
		menuitemThongKeHopDong.addActionListener(this);
		// Chấm công
		menuChamCong = new JMenu("Chấm công");
		menuChamCong.setFont(new Font("Times new roman", Font.BOLD, 18));
		menuChamCong.setIcon(new ImageIcon("image//timekeep.png"));
		//
		menuChamCong.add(menuitemChamCongNhanVien = new JMenuItem("Nhân niên"));
		menuitemChamCongNhanVien.setFont(new Font("times new roman", Font.BOLD, 15));
		menuitemChamCongNhanVien.setIcon(new ImageIcon("image//nv.png"));
		//
		menuChamCong.add(menuitemChamCongCongNhan = new JMenuItem("Công nhân"));
		menuitemChamCongCongNhan.setFont(new Font("times new roman", Font.BOLD, 15));
		menuitemChamCongCongNhan.setIcon(new ImageIcon("image//woker1.png"));
		// Tính lương
		menuTinhLuong = new JMenu("Lương");
		menuTinhLuong.setFont(new Font("Times new roman", Font.BOLD, 18));
		menuTinhLuong.setIcon(new ImageIcon("image//money.png"));
		// tinh luong nhan vien
		menuTinhLuong.add(menuitemLuongNhanVien = new JMenuItem("Tính lương nhân viên"));
		menuitemLuongNhanVien.setFont(new Font("times new roman", Font.BOLD, 15));
		menuitemLuongNhanVien.setIcon(new ImageIcon("image//nv.png"));
		// tinh luong cong nhan
		menuTinhLuong.add(menuitemLuongCongNhan = new JMenuItem("Tính lương công nhân"));
		menuitemLuongCongNhan.setFont(new Font("times new roman", Font.BOLD, 15));
		menuitemLuongCongNhan.setIcon(new ImageIcon("image//woker1.png"));
		// bang luong nhan vien
//		menuTinhLuong.add(menuitem_BangLuongNhanVien = new JMenuItem("Bảng lương nhân viên"));
//		menuitem_BangLuongNhanVien.setFont(new Font("times new roman", Font.BOLD, 15));
//		menuitem_BangLuongNhanVien.setIcon(new ImageIcon("image//staff.png"));
		// bang luong cong nhan
//		menuTinhLuong.add(menuitem_BangLuongCongNhan = new JMenuItem("Bảng lương công nhân"));
//		menuitem_BangLuongCongNhan.setFont(new Font("times new roman", Font.BOLD, 15));
//		menuitem_BangLuongCongNhan.setIcon(new ImageIcon("image//staff.png"));
		// thong ke luong nhan vien
//		menuTinhLuong.add(menuitem_ThongKeLuongNhanVien = new JMenuItem("Thống kê lương nhân viên"));
//		menuitem_ThongKeLuongNhanVien.setFont(new Font("times new roman", Font.BOLD, 15));
//		menuitem_ThongKeLuongNhanVien.setIcon(new ImageIcon("image//staff.png"));
		// thong ke luong cong nhan
//		menuTinhLuong.add(menuitem_ThongKeLuongCongNhan = new JMenuItem("Thống kê lương công nhân"));
//		menuitem_ThongKeLuongCongNhan.setFont(new Font("times new roman", Font.BOLD, 15));
//		menuitem_ThongKeLuongCongNhan.setIcon(new ImageIcon("image//staff.png"));
		// ho tro
		menubar.add(menuHeThong);
		menubar.add(menuQuanLyNhanSu);
		menubar.add(menuQuanSanPham);
		menubar.add(menuQuanLyHopDong);
		menubar.add(menuChamCong);
		menubar.add(menuTinhLuong);
		menubar.add(menuTroGiup = new JMenu("Trợ giúp"));
		menubar.add(Box.createHorizontalStrut(20));
		menubar.add(lblAcc = new JLabel("Helelo"));
		lblAcc.setFont(new Font("times new roman", Font.ITALIC, 20));
		lblAcc.setIcon(new ImageIcon("image/user__.png"));
		menubar.add(Box.createHorizontalStrut(5));
		menuTroGiup.setIcon(new ImageIcon("image/help_.png"));
		menuTroGiup.add(menuitemGioiThieu = new JMenuItem("Giới thiệu"));
		menuitemGioiThieu.setFont(new Font("Times new roman", Font.BOLD, 15));
		menuTroGiup.setFont(new Font("Times new roman", Font.BOLD, 18));
		menuitemGioiThieu.setIcon(new ImageIcon("image/c_.png"));
		menuTroGiup.add(menuitemCachSuDung = new JMenuItem("Cách sử dụng"));
		menuitemCachSuDung.setFont(new Font("Times new roman", Font.BOLD, 15));

		menuitemCachSuDung.setIcon(new ImageIcon("image/csd.png"));
		menuitemGioiThieu.addActionListener(this);
		menuitemCachSuDung.addActionListener(this);
		menubar.add(acc = new JMenuItem());
		acc.setFont(new Font("times new roman", Font.ITALIC, 15));
		acc.setEnabled(true);
		
		acc.setIcon(new ImageIcon("image/user_.png"));
		// dang xuat
		acc.add(menu_DangXuat = new JMenuItem("Đăng xuất"));
		menu_DangXuat.setFont(new Font("Times new roman", Font.BOLD, 18));
		menu_DangXuat.setIcon(new ImageIcon("image/logout_.png"));
		menu_DangXuat.addActionListener(this);
		// dang ky su kien
		menuitemNhanVien.addActionListener(this);
		menuitemCongNhan.addActionListener(this);
		menuitemDoiMatKhau.addActionListener(this);
//		menu_GioiThieu.addActionListener(this);
		menuitem_SanPham.addActionListener(this);
		menuitem_HopDong.addActionListener(this);
		menuitemThemNguoiDung.addActionListener(this);
		menuitemDoiMatKhau.addActionListener(this);
//		menuitem_BangLuongCongNhan.addActionListener(this);
//		menuitem_BangLuongNhanVien.addActionListener(this);
//		menuitem_ThongKeLuongCongNhan.addActionListener(this);
//		menuitem_ThongKeLuongNhanVien.addActionListener(this);
		menuitemPhanCongCongDoan.addActionListener(this);
		menuitemLuongNhanVien.addActionListener(this);
		menuitemLuongCongNhan.addActionListener(this);
		menuitemCongDoan.addActionListener(this);
		menuitemChamCongCongNhan.addActionListener(this);
		menuitemChamCongNhanVien.addActionListener(this);
		
		//
		showMenu(new TrangChu_GUI());
		//

		JLabel j = new JLabel("CCCCCCCC");
		add(j);
		//
		phanQuyen();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(menuitemCongNhan)) {
			showMenu(new CongNhan_GUI());
		}
		else if(o.equals(menuitemNhanVien)) {
			showMenu(new NhanVien_GUI());
		}
		else if(o.equals(menuitem_SanPham)) {
			showMenu(new SanPham_GUI());
		}
		else if(o.equals(menuitem_HopDong)) {
			showMenu(new HopDong_GUI());
		}
		else if(o.equals(menuitemDoiMatKhau)) {
			showMenu(new DoiMatKhau_GUI());
		}
		else if(o.equals(menuitemThemNguoiDung)) {
			showMenu(new ThemNguoiDung_GUI());
		}
		else if(o.equals(menu_DangXuat)) {
			logout();
		}
		else if(o.equals(menuitem_BangLuongCongNhan)) {

		}
		else if(o.equals(menuitem_BangLuongNhanVien)) {
			showMenu(new BangLuong_NhanVien_GUI());
		}
		else if(o.equals(menuitemPhanCongCongDoan)) {
			showMenu(new PhanCongCongDoan_GUI());
		}
		else if(o.equals(menuitemThongKeHopDong)) {
			showMenu(new ThongKeHopDong_GUI());
		}
		else if(o.equals(menuitemLuongNhanVien)) {
			showMenu(new LuongNhanVien_GUI());
		}
		else if(o.equals(menuitemLuongCongNhan)) {
			showMenu(new LuongCongNhan());
		}
		else if(o.equals(menuitemCongDoan)) {
			try {
				showMenu(new CongDoan_GUI());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(o.equals(menu_GioiThieu)) {
			showMenu(new GioiThieu_GUI());
		}
		else if(o.equals(menuitemChamCongNhanVien)) {
			showMenu(new ChamCong_NhanVien_GUI());
		}
		else if(o.equals(menuitemChamCongCongNhan)) {
			showMenu(new ChamCong_CongNhan_GUI());
		}
		else if(o.equals(menuitem_TrangChu)) {
			showMenu(new TrangChu_GUI());
		}
		else if(o.equals(menuitemCachSuDung)) {
			String url = "https://duongthevinh.github.io/phatrienungdung/";

			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.browse(new URI(url));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec("xdg-open " + url);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(o.equals(menuitemGioiThieu)) {
			String url = "https://duongthevinh.github.io/aboutt/";

			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.browse(new URI(url));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec("xdg-open " + url);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}
	public void showMenu(JPanel cn) {
		setContentPane(cn);
		validate();
		invalidate();
	}
	public void logout() {
		int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất không?", "Chú ý", JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			new DangNhap_GUI().setVisible(true);
			this.setVisible(false);
		}
	}
	public static void main(String[] args) {
		new Home_GUI().setVisible(true);
	}
	public void phanQuyen() {
		lblAcc.setText(ShareDate.nguoiDangNhap.getTaiKhoan());
		if(ShareDate.nguoiDangNhap.getVaiTro().equals("Người quản trị")) {
			menuQuanLyNhanSu.setEnabled(false);
			menuQuanLyHopDong.setEnabled(false);
			menuQuanSanPham.setEnabled(false);
			menuChamCong.setEnabled(false);
			menuTinhLuong.setEnabled(false);
			System.out.println(ShareDate.nguoiDangNhap.getTaiKhoan());
		}
		else if(ShareDate.nguoiDangNhap.getVaiTro().equals("Quản lý")) {
			menuHeThong.setEnabled(false);
			menuTinhLuong.setEnabled(false);
		}
		else {
			menuHeThong.setEnabled(false);
			menuQuanLyNhanSu.setEnabled(false);
			menuQuanLyHopDong.setEnabled(false);
			menuQuanSanPham.setEnabled(false);
			menuChamCong.setEnabled(false);
		}
		System.out.println(ShareDate.nguoiDangNhap);
	}
}
