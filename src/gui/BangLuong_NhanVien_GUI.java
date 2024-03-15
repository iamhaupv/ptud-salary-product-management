package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;

public class BangLuong_NhanVien_GUI extends JPanel implements ActionListener, MouseListener{
	private JTable table;
	private JTextField textTim;
	private Container btnTim;
	
	

	/**
	 * Create the panel.
	 */
	public BangLuong_NhanVien_GUI() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		JLabel lblBangLuongNV = new JLabel("Thông tin bảng lương nhân viên\r\n");
		lblBangLuongNV.setForeground(new Color(72, 209, 204));
		lblBangLuongNV.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblBangLuongNV.setBounds(610, 24, 427, 58);
		add(lblBangLuongNV);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 100, 1570, 500);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 b\u1EA3ng l\u01B0\u01A1ng nh\u00E2n vi\u00EAn", "M\u00E3 nh\u00E2n vi\u00EAn", "T\u00EAn nh\u00E2n vi\u00EAn", "H\u1EC7 s\u1ED1 l\u01B0\u01A1ng", "M\u00E3 l\u01B0\u01A1ng nh\u00E2n vi\u00EAn", "L\u01B0\u01A1ng"
			}
		));
		table.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ch\u1ECDn t\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(72, 209, 204)));
		panel.setBounds(0, 650, 1570, 104);
		add(panel);
		panel.setLayout(null);
		
		textTim = new JTextField();
		textTim.setBounds(48, 42, 1350, 20);
		panel.add(textTim);
		textTim.setColumns(10);
		
		btnTim = new JButton("Tìm kiếm\r\n");
		btnTim.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnTim.setBounds(1410, 39, 105, 23);
		panel.add(btnTim);


	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		
	}
	public void loadData() {
		
	}
}
