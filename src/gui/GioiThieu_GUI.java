package gui;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

public class GioiThieu_GUI extends JPanel {

	/**
	 * Create the panel.
	 */
	public GioiThieu_GUI() {
		setLayout(null);
		
		JLabel lbl_tt = new JLabel("PH\u1EA6N M\u1EC0M N\u00C0Y \u0110\u01AF\u1EE2C TH\u1EF0C HI\u1EC6N B\u1EDEI NH\u00D3M 7");
		lbl_tt.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_tt.setBounds(464, 20, 602, 37);
		add(lbl_tt);
		
		JLabel lbl_pvh = new JLabel("Pham van hau");
		lbl_pvh.setBorder(BorderFactory.createLineBorder(Color.black));
		lbl_pvh.setBounds(40, 128, 318, 370);
		add(lbl_pvh);
		
		JLabel lbl_nnt = new JLabel("New label");
		lbl_nnt.setBounds(426, 128, 318, 370);
		lbl_nnt.setBorder(BorderFactory.createLineBorder(Color.black));
		add(lbl_nnt);
		
		JLabel lbl_nth = new JLabel("New label");
		lbl_nth.setBorder(BorderFactory.createLineBorder(Color.black));
		lbl_nth.setBounds(1185, 128, 318, 370);
		add(lbl_nth);
		
		JLabel lbl_dtv = new JLabel("d");
		lbl_dtv.setBounds(807, 128, 318, 370);
		lbl_dtv.setBorder(BorderFactory.createLineBorder(Color.black));
		add(lbl_dtv);
		
		JLabel pvh = new JLabel("Ph\u1EA1m V\u0103n H\u1EADu");
		pvh.setFont(new Font("Times New Roman", Font.BOLD, 18));
		pvh.setBounds(119, 519, 133, 29);
		add(pvh);
		
		JLabel nnt = new JLabel("Nguy\u1EC5n Ng\u1ECDc Tu\u1EA5n");
		nnt.setFont(new Font("Times New Roman", Font.BOLD, 18));
		nnt.setBounds(516, 519, 164, 29);
		add(nnt);
		
		JLabel dtv = new JLabel("D\u01B0\u01A1ng Th\u1EBF Vinh");
		dtv.setFont(new Font("Times New Roman", Font.BOLD, 18));
		dtv.setBounds(907, 524, 148, 19);
		add(dtv);
		
		JLabel nth = new JLabel("Nguy\u1EC5n Tu\u1EA5n H\u00F9ng");
		nth.setFont(new Font("Times New Roman", Font.BOLD, 18));
		nth.setBounds(1287, 529, 164, 19);
		add(nth);

	}
}
