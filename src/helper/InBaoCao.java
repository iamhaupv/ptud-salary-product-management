package helper;

import java.util.List;

import com.itextpdf.text.pdf.codec.Base64.InputStream;

import connect.connectDB;
import entity.HopDong;
import entity.SanPham;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class InBaoCao {
	public void xuatHD(List<HopDong> list) {
		java.io.InputStream is = InBaoCao.class.getResourceAsStream("Helper/HopDong_Report.jrxml");
		try {
			JasperReport report = JasperCompileManager.compileReport(is);
			JasperPrint print = JasperFillManager.fillReport(report, null, connectDB.getConnection());
			JasperViewer.viewReport(print, false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void xuatSP(List<SanPham> list) {
		java.io.InputStream is = InBaoCao.class.getResourceAsStream("Helper/HopDong_Report.jrxml");
		try {
			JasperReport report = JasperCompileManager.compileReport(is);
			JasperPrint print = JasperFillManager.fillReport(report, null, connectDB.getConnection());
			JasperViewer.viewReport(print, false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
