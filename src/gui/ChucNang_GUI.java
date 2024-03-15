package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ChucNang_GUI extends JPanel implements ActionListener{
	private JButton btnDangXuat;
	public ChucNang_GUI(JPanel alo) {
		// TODO Auto-generated constructor stub
		alo.add(btnDangXuat = new JButton("Đăng Xuất"));
		btnDangXuat.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getActionCommand();
		if(o == "Đăng Xuất") {
			int choose = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất không?", "Chú ý", JOptionPane.YES_OPTION);
			if(choose == JOptionPane.YES_OPTION) {
				try {
					logout();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	public void logout() throws ClassNotFoundException, SQLException {
//		TrangChu_GUI home  = new TrangChu_GUI();
//		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		new Login_GUI().setVisible(true);
//		new TrangChu_GUI().setVisible(false);
//		new Login_GUI().setVisible(true);
	}
}
