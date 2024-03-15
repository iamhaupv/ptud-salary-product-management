package gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.ThongKeHopDong_DAO;
import entity.HopDong;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ThongKeHopDong_GUI extends JPanel implements ActionListener{

	private ThongKeHopDong_DAO tkhd_dao;
	private JButton btn_tktsl;
	private JButton btn_tktt;
	private JPanel jpane_Top;
	/**
	 * Create the panel.
	 */
	public ThongKeHopDong_GUI() {
		setLayout(null);

		jpane_Top = new JPanel();
		jpane_Top.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jpane_Top.setBounds(20, 82, 1497, 668);
		add(jpane_Top);
		tkhd_dao = new ThongKeHopDong_DAO();



		btn_tktsl = new JButton("Thống kê theo số lượng hợp đồng");
		btn_tktsl.setBounds(322, 20, 253, 52);
		add(btn_tktsl);
		btn_tktsl.addActionListener(this);
		btn_tktt = new JButton("Thống kê theo tổng tiền hợp đồng");
		btn_tktt.setBounds(671, 20, 233, 52);
		add(btn_tktt);
		btn_tktt.addActionListener(this);

		
	}
	// thong ke hop dong theo so luong hop dong/ ngay
	public void thongKeTheoSoLuongHopDong(JPanel j) {
		List<HopDong> list = tkhd_dao.thongKeHopDongTheoSoLuong();
		if(list != null) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (HopDong hd : list) {
				dataset.addValue(hd.getSoLuong(), "Tổng hợp đồng", hd.getNgayBatDau());
			}
			JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ SỐ LƯỢNG HỢP ĐỒNG", "Thời gian",
					"Số lượng", dataset, PlotOrientation.VERTICAL, true, true, false);
			ChartPanel chart_p = new ChartPanel(chart);
			chart_p.setPreferredSize(new Dimension(j.getWidth(), 300));
			j.removeAll();
			j.setLayout(new CardLayout());
			j.add(chart_p);
			j.validate();
			j.repaint();
		}
	}
	// thong ke hop dong theo tong tien hop dong/ ngay
	public void thongKeTheoTongTienHopDong(JPanel j) {
		List<HopDong> list = tkhd_dao.thongKeHopDongTheoTongTien();
		if(list != null) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (HopDong hd : list) {
				dataset.addValue(hd.getTongTien(), "Tổng hợp đồng", hd.getNgayBatDau());
			}
			JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ TỔNG TIỀN HỢP ĐỒNG", "Thời gian", "Tổng tiền", dataset, PlotOrientation.VERTICAL, false, true, true);
			ChartPanel chart_p = new ChartPanel(chart);
			chart_p.setPreferredSize(new Dimension(j.getWidth(), 300));
			j.removeAll();
			j.setLayout(new CardLayout());
			j.add(chart_p);
			j.validate();
			j.repaint();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btn_tktsl)) {
			thongKeTheoSoLuongHopDong(jpane_Top);
		}
		else if(o.equals(btn_tktt)) {
			thongKeTheoTongTienHopDong(jpane_Top);
		}
	}
}
