/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.ThongKeGUI;

import BUS.PhongBUS;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author ACER
 */
public class PanelPie extends JPanel {
    
    private JPanel pnThInput = new JPanel();
    private JPanel pnThChart = new JPanel();
    
    private JPanel pnTuNgay = new JPanel();
    private JLabel lbTuNgay = new JLabel();
    private JPanel pnTuNgayChoose = new JPanel();
    private JDateChooser dateTuNgay = new JDateChooser();

    private JPanel pnDenNgay = new JPanel();
    private JLabel lbDenNgay = new JLabel();
    private JPanel pnDenNgayChoose = new JPanel();
    private JDateChooser dateDenNgay = new JDateChooser();
    
    ArrayList<String> listStr = new ArrayList<>();
    ArrayList<Integer> listInt = new ArrayList<>();
    
    JFreeChart chart;
    ChartPanel chartPanel;
    
    private JButton btnThongKe = new JButton("Thống kê");
    
    public PanelPie() {
        InitComponents();
    }
    
    private DefaultPieDataset createDataset(String dateTuNgay, String dateDenNgay, ArrayList<String> listStr, ArrayList<Integer> listInt) {

        DefaultPieDataset dataset = new DefaultPieDataset();
        listStr = PhongBUS.getLoaiP_SL(listInt, dateTuNgay, dateDenNgay);
        int i = 0;
        for (String x : listStr) {
            dataset.setValue(x, (double) listInt.get(i));
            i++;
        }
        return dataset;
    }

    private DefaultPieDataset createDataset(ArrayList<String> listStr, ArrayList<Integer> listInt) {

        DefaultPieDataset dataset = new DefaultPieDataset();
        listStr = PhongBUS.getLoaiP_SL(listInt);
        int i = 0;
        for (String x : listStr) {
            dataset.setValue(x, (double) listInt.get(i));
            i++;
        }
        return dataset;
    }

    private JFreeChart createChart(DefaultPieDataset dataset) {

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Thống kê sự tương quan loại phòng",
                dataset,
                true,
                true,
                false
        );
        return pieChart;
    }
    
    public void InitComponents() {
        setLayout(new BorderLayout());
        add(pnThInput, BorderLayout.NORTH);
        add(pnThChart, BorderLayout.CENTER);
        pnThInput.setBackground(Color.white);
        pnThChart.setBackground(Color.yellow);
        
        pnThInput.setLayout(new GridLayout(1, 3, 10, 10));

        pnTuNgay.setLayout(new BorderLayout());
        pnTuNgay.add(lbTuNgay, BorderLayout.WEST);
        lbTuNgay.setText("Từ ngày");
        pnTuNgay.add(pnTuNgayChoose, BorderLayout.CENTER);
        pnTuNgayChoose.setLayout(new BorderLayout());
        pnTuNgayChoose.add(dateTuNgay);

        pnDenNgay.setLayout(new BorderLayout());
        pnDenNgay.add(lbDenNgay, BorderLayout.WEST);
        lbDenNgay.setText("Đến ngày");
        pnDenNgay.add(pnDenNgayChoose, BorderLayout.CENTER);
        pnDenNgayChoose.setLayout(new BorderLayout());
        pnDenNgayChoose.add(dateDenNgay);

        pnThInput.add(pnTuNgay);
        pnThInput.add(pnDenNgay);
        pnThInput.add(btnThongKe);
        
        btnThongKe.setFocusPainted(false);
        
        pnThChart.setLayout(new BorderLayout());
        
        btnThongKe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dateTuNgay.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn từ ngày", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (dateDenNgay.getDate() == null) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn đến ngày", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if (dateTuNgay.getDate().before(dateDenNgay.getDate()) || dateTuNgay.getDate().compareTo(dateDenNgay.getDate()) == 0) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date tuNgay = dateTuNgay.getDate();
                            tuNgay.setHours(0);
                            tuNgay.setMinutes(0);
                            tuNgay.setSeconds(0);

                            Date denNgay = dateDenNgay.getDate();
                            denNgay.setHours(0);
                            denNgay.setMinutes(0);
                            denNgay.setSeconds(0);
                            String tuNgaystr = sdf.format(tuNgay);
                            String denNgaystr = sdf.format(denNgay);
                            System.out.println(tuNgaystr + "," + denNgaystr);
                            ArrayList<String> listString = new ArrayList<>();
                            ArrayList<Integer> listInteger = new ArrayList<>();
                            PhongBUS.getLoaiP_SL(listInteger, tuNgaystr, denNgaystr);
                            DefaultPieDataset dataset = createDataset(tuNgaystr, denNgaystr, listString, listInteger);
                            chart = createChart(dataset);
                            chartPanel = new ChartPanel(chart);
                            pnThChart.removeAll();
                            pnThChart.repaint();
                            pnThChart.revalidate();
                            chartPanel.setPreferredSize(new java.awt.Dimension(360, 350));
                            pnThChart.add(chartPanel,BorderLayout.CENTER);
                        } else {
                            JOptionPane.showMessageDialog(null, "Vui lòng chọn đến ngày phải lớn hơn hoặc bằng từ ngày", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            dateDenNgay.setCalendar(null);
                        }
                    }
                }
            }
        });
        
        DefaultPieDataset dataset = createDataset(listStr, listInt);
        chart = createChart(dataset);

        chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        chartPanel.setPreferredSize(new java.awt.Dimension(360, 350));
        pnThChart.setLayout(new BorderLayout());
        pnThChart.add(chartPanel, BorderLayout.CENTER);
    }
}
