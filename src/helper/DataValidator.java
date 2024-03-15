package helper;

import java.awt.Color;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DataValidator {
	public static void validateEmpty(JTextField f, StringBuilder sb, String e) {
		if(f.getText().equals("")) {
			sb.append(e).append("\n");
			f.setBackground(Color.red);
			f.requestFocus();
		}else {
			f.setBackground(Color.white);
		}
	}
	
	public static void validateEmpty(JPasswordField f, StringBuilder sb, String e) {
		String pass = new String(f.getPassword());
		if(pass.equals("")) {
			sb.append(e).append("\n");
			f.setBackground(Color.red);
			f.requestFocus();
		}else {
			f.setBackground(Color.white);
		}
	}
}
