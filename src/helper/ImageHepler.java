package helper;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageHepler {
	// chinh kich thuoc anh
	public static Image resize(Image o, int w, int h) {
		Image rsImg = o.getScaledInstance(w, h, o.SCALE_SMOOTH);
		return rsImg;
	}
	// chuyen hinh anh sang mang de luu vao csdl
	public static byte[] tobyArray(Image i, String type) throws IOException {
		BufferedImage bimge = new BufferedImage(i.getWidth(null), i.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bimge.createGraphics();
		g.drawImage(i, 0, 0, null);
		g.dispose();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bimge, type, baos);
		byte[] im = baos.toByteArray();
		return im;
	}
	// chuyen mang byte[] thanh hinh anh de load len giao dien
 	public static Image crateImageFromByteArray(byte[] data, String type) throws IOException {
		ByteArrayInputStream i = new ByteArrayInputStream(data);
		BufferedImage b = ImageIO.read(i);
		Image im = b.getScaledInstance(b.getWidth(), b.getHeight(), Image.SCALE_SMOOTH);
		return im;
	}
}
