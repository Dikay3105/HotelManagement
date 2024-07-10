/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.ThongKeGUI;


import BUS.HoaDonBUS;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class PanelLine extends JPanel {

    public PanelLine(int thang, int nam) {
        initComponents(thang, nam);
    }

    static boolean checkNamNhuan(int n) {
        if (n % 4 == 0 && n % 100 != 0 || n % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    private static CategoryDataset createDataset(int thang, int nam) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<Integer> tienPh = new ArrayList<>();
        ArrayList<Integer> tienDV = new ArrayList<>();
        ArrayList<Integer> listNg = HoaDonBUS.getTienThang(tienPh, tienDV, thang, nam);
        int[][] a;
        if (thang == 1 || thang == 3 || thang == 5 || thang == 7 || thang == 8 || thang == 10 || thang == 12) {
            a = new int[2][31];
            for (int k = 0; k < 2; k++) {
                for (int l = 0; l < 31; l++) {
                    a[k][l] = 0;
                }
            }
            for (int j = 0; j < 31; j++) {
                for (int k = 0; k < listNg.size(); k++) {
                    if (listNg.get(k) - 1 == j) {
                        a[0][j] = tienPh.get(k);
                        a[1][j] = tienDV.get(k);
                    }
                }
            }
            for (int i = 0; i < 31; i++) {
                dataset.addValue(a[0][i], "Tien Phong", i + 1 + "");
                dataset.addValue(a[1][i], "Tien Dich Vu", i + 1 + "");
            }
        }

        if (thang == 4 || thang == 6 || thang == 9 || thang == 11) {
            a = new int[2][30];
            for (int k = 0; k < 2; k++) {
                for (int l = 0; l < 30; l++) {
                    a[k][l] = 0;
                }
            }
            for (int j = 0; j < 30; j++) {
                for (int k = 0; k < listNg.size(); k++) {
                    if (listNg.get(k) - 1 == j) {
                        a[0][j] = tienPh.get(k);
                        a[1][j] = tienDV.get(k);
                    }
                }
            }
            for (int i = 0; i < 30; i++) {
                dataset.addValue(a[0][i], "Tien Phong", i + 1 + "");
                dataset.addValue(a[1][i], "Tien Dich Vu", i + 1 + "");
            }
        }

        if (thang == 2) {
            if (checkNamNhuan(nam)) {
                a = new int[2][29];
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 29; l++) {
                        a[k][l] = 0;
                    }
                }
                for (int j = 0; j < 29; j++) {
                    for (int k = 0; k < listNg.size(); k++) {
                        if (listNg.get(k) - 1 == j) {
                            a[0][j] = tienPh.get(k);
                            a[1][j] = tienDV.get(k);
                        }
                    }
                }
                for (int i = 0; i < 29; i++) {
                    dataset.addValue(a[0][i], "Tien Phong", i + 1 + "");
                    dataset.addValue(a[1][i], "Tien Dich Vu", i + 1 + "");
                }
            } else {
                a = new int[2][28];
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 28; l++) {
                        a[k][l] = 0;
                    }
                }
                for (int j = 0; j < 28; j++) {
                    for (int k = 0; k < listNg.size(); k++) {
                        if (listNg.get(k) - 1 == j) {
                            a[0][j] = tienPh.get(k);
                            a[1][j] = tienDV.get(k);
                        }
                    }
                }
                for (int i = 0; i < 28; i++) {
                    dataset.addValue(a[0][i], "Tien Phong", i + 1 + "");
                    dataset.addValue(a[1][i], "Tien Dich Vu", i + 1 + "");
                }
            }
        }

        return dataset;
    }

    public static JFreeChart createChart(int thang, int nam) {
        JFreeChart barChart = ChartFactory.createLineChart(
                "BIỂU ĐỒ THỐNG KÊ THÁNG " + thang,
                "So Tien",
                "Thang",
                createDataset(thang, nam), PlotOrientation.VERTICAL, true, true, false);

        return barChart;
    }

    public void initComponents(int thang, int nam) {
        setLayout(new BorderLayout());
        ChartPanel chartPanel = new ChartPanel(createChart(thang, nam));
        add(chartPanel, BorderLayout.CENTER);
    }

}
