/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.ThongKeGUI;

import DAO.HoaDonDAO;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ACER
 */
public class PanelBar extends JPanel {

//    JPanel 
    
    public PanelBar(int nam) {
        InitComponents(nam);
    }
    
    private static CategoryDataset createDataset(int nam) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<Integer> tienPh = new ArrayList<>();
        ArrayList<Integer> tienDV = new ArrayList<>();
        ArrayList<Integer> listTien = new ArrayList<>();
        ArrayList<Integer> listTh = HoaDonDAO.getTienNam(tienPh, tienDV, listTien, nam);
        int[][] a = new int[3][12];
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 12; l++) {
                a[k][l] = 0;
            }
        }
        for (int j = 0; j < 12; j++) {
            for (int k = 0; k < listTh.size(); k++) {
                if (listTh.get(k) - 1 == j) {
                    a[0][j] = tienPh.get(k);
                    a[1][j] = tienDV.get(k);
                    a[2][j] = listTien.get(k);
                }
            }
        }
        for (int i = 0; i < 12; i++) {
            dataset.addValue(a[0][i], "Tien Phong", i + 1 + "");
            dataset.addValue(a[1][i], "Tien Dich Vu", i + 1 + "");
            dataset.addValue(a[2][i], "Tong Tien", i + 1 + "");
        }
        return dataset;
    }
    
    public static JFreeChart createChart(int nam) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ THỐNG KÊ NĂM "+ nam, 
                "So Tien", 
                "Thang", 
                
                
                
                
                
                createDataset(nam), PlotOrientation.VERTICAL, true, true, false);
        
        return barChart;
    }

    public void InitComponents(int nam) {
        setLayout(new BorderLayout());
        ChartPanel chartPanel = new ChartPanel(createChart(nam));
        add(chartPanel, BorderLayout.CENTER);
    }
}
