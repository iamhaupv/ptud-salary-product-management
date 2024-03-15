package gui;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

public class TrangChu_GUI extends JPanel {

	/**
	 * Create the panel.
	 */
	public TrangChu_GUI() {
		setLayout(null);
		
		JLabel lbl_tt = new JLabel("CH\u01AF\u01A0NG TR\u00CCNH QU\u1EA2N L\u00DD L\u01AF\u01A0NG S\u1EA2N PH\u1EA8M GI\u00C0Y C\u00D4NG TY TNHH ABC");
		lbl_tt.setForeground(new Color(0, 0, 0));
		lbl_tt.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lbl_tt.setBounds(249, 31, 1092, 41);
		add(lbl_tt);
		JLabel lbl_lock = new JLabel("New label");
		lbl_lock.setBounds(1104, 69, 226, 41);
		add(lbl_lock);
		DongHo_GUI dh = new DongHo_GUI(lbl_lock);
		dh.start();
		lbl_lock.setEnabled(true);
		lbl_lock.setFont(new Font("times new roman", Font.BOLD, 30));
		lbl_lock.setForeground(new Color(0, 0, 0));
		
		JLabel lbl_background = new JLabel();
		lbl_background.setBounds(0, 110, 1550, 700);
		add(lbl_background);
		lbl_background.setIcon(new ImageIcon("image/s.jpg"));
		
	}
}
