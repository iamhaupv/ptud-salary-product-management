package helper;

import java.awt.Component;

import javax.swing.JOptionPane;

public class MessageDialogHelper {
	public static void showMessageDialog(Component c, String a, String b) {
		JOptionPane.showMessageDialog(c, a, b, JOptionPane.INFORMATION_MESSAGE);
	}
	public static void showErrorDialog(Component c, String a, String b) {
		JOptionPane.showMessageDialog(c, a, b, JOptionPane.ERROR_MESSAGE);
	}
	public static int showConfirmDialog(Component c, String a, String b) {
		int choose = JOptionPane.showConfirmDialog(c, b, a, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return choose;
	}
}
