package gui;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class DongHo_GUI extends Thread{
	private JLabel lblClock;
	public DongHo_GUI(JLabel lblClock) {
		// TODO Auto-generated constructor stub
		this.lblClock = lblClock;
	}
	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
		while(true) {
			Date now = new Date();
			String st = sdf.format(now);
			lblClock.setText(st);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
