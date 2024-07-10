package GUI.ThongKeGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import BUS.HoaDonBUS;
import BUS.PhongBUS;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author ACER
 */
public class PanelThongKe extends JPanel {

//    private JFrame fr = new JFrame();
    private JPanel pn = new JPanel();
    private JPanel pnTop = new JPanel();
    private JPanel pnContain = new JPanel();

    private JPanel pnTkTong = new JPanel();
    private JPanel pnTongThu = new JPanel();
    private JPanel pnTongPhong = new JPanel();
    private JPanel pnTongDV = new JPanel();

    private JPanel pnImageTong = new JPanel();
    private JPanel pnImagePh = new JPanel();
    private JPanel pnImageDV = new JPanel();

    private JPanel pnTongText = new JPanel();
    private JPanel pnPhongText = new JPanel();
    private JPanel pnDVText = new JPanel();

    private JPanel pnChart = new JPanel();
    private JPanel pnPie = new JPanel();
    private JPanel pnTkNamTh = new JPanel();
    private JPanel pnInput = new JPanel();
    private JPanel pnBarLine = new JPanel();

    private JPanel pnTuNgay = new JPanel();
    private JLabel lbTuNgay = new JLabel();
    private JPanel pnTuNgayChoose = new JPanel();
    private JDateChooser dateTuNgay = new JDateChooser();

    private JPanel pnDenNgay = new JPanel();
    private JLabel lbDenNgay = new JLabel();
    private JPanel pnDenNgayChoose = new JPanel();
    private JDateChooser dateDenNgay = new JDateChooser();
    
    private JLabel lbIconSu = new JLabel();
    private JLabel lbIconPh = new JLabel();
    private JLabel lbIconDV = new JLabel();

    JPanel pnCbNam = new JPanel();
    JPanel pnCbThang = new JPanel();
    JPanel pnCbNgay = new JPanel();

    JComboBox cbNam = new JComboBox();
    String thang[] = {"Chọn ngày", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    JComboBox cbThang = new JComboBox(thang);

    ArrayList<Integer> listNum = HoaDonBUS.getListYear_1();

    private JLabel lbTitle = new JLabel();
    private JLabel lbTongThu1 = new JLabel();
    private JLabel lbTongThu2 = new JLabel();
    private JLabel lbTongPhong1 = new JLabel();
    private JLabel lbTongPhong2 = new JLabel();
    private JLabel lbTongDV1 = new JLabel();
    private JLabel lbTongDV2 = new JLabel();
    private JLabel lbComboBox = new JLabel();

    JButton btnThongKe = new JButton("Thống kê");

    JLabel lbCbNam = new JLabel();
    JLabel lbCbThang = new JLabel();
    JLabel lbCbNgay = new JLabel();

    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);
    Font sgUI24b = new Font("Segoe UI", Font.BOLD, 24);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);

    ArrayList<String> listStr = new ArrayList<>();
    ArrayList<Integer> listInt = new ArrayList<>();

    PanelLine pnLineChart;
    PanelPie pnPieChart = new PanelPie();
    PanelBar pnBarChart;

    JFreeChart chart;
    ChartPanel chartPanel;

    public PanelThongKe() {
        initComp();
    }

    //
    public void initComp() {
//        fr.setSize(1050, 650);
//        fr.setLocationRelativeTo(null);
//        fr.setLayout(new BorderLayout());

        pn.setLayout(new BorderLayout());
        pn.add(pnTop, BorderLayout.NORTH);

        lbTitle.setText("THỐNG KÊ");
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        lbTitle.setFont(sgUI18b);
        
        ImageIcon iconTong = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/tuitien.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        ImageIcon iconPhong = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/phongicon.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        ImageIcon iconDV = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/dichvuicon.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));

        
        pnTop.add(lbTitle);
        pnTop.setBackground(Color.decode("#FFFAFA"));

        pnContain.setLayout(new BorderLayout());
        pnContain.add(pnTkTong, BorderLayout.NORTH);
        pnTkTong.setBackground(Color.blue);

        pnTkTong.setLayout(new GridLayout(1, 3, 10, 10));
        pnTkTong.setBackground(Color.decode("#FFFAFA"));
        pnTkTong.setBorder(new EmptyBorder(10, 10, 10, 10));

        pnTongThu.setBackground(Color.white);
        pnTkTong.add(pnTongThu);
        //Khúc đây s?a nè
        pnTongThu.setLayout(new BorderLayout());
        pnTongThu.add(lbIconSu, BorderLayout.WEST);
        lbIconSu.setIcon(iconTong);
        pnTongThu.add(pnTongText, BorderLayout.CENTER);
        pnTongText.setBackground(Color.white);
        pnTongText.setLayout(new BorderLayout());
        //
        pnTongText.add(lbTongThu1, BorderLayout.NORTH);
        pnTongText.add(lbTongThu2, BorderLayout.CENTER);

        lbTongThu1.setText("Tổng doanh thu");
        lbTongThu1.setFont(sgUI18b);
        lbTongThu2.setText(HoaDonBUS.tongTien() + "");
        lbTongThu2.setFont(sgUI24b);
        lbTongThu1.setHorizontalAlignment(JLabel.CENTER);
        lbTongThu2.setHorizontalAlignment(JLabel.CENTER);
        pnTongThu.setBorder(new EmptyBorder(0, 5, 0, 0));

        pnTongPhong.setBackground(Color.white);
        pnTkTong.add(pnTongPhong);
        //Khúc đây
        pnTongPhong.setLayout(new BorderLayout());
        pnTongPhong.add(lbIconPh, BorderLayout.WEST);
        lbIconPh.setIcon(iconPhong);
        pnTongPhong.add(pnPhongText, BorderLayout.CENTER);
        pnPhongText.setLayout(new BorderLayout());
        pnPhongText.setBackground(Color.white);
        //
        pnTongPhong.add(lbTongPhong1, BorderLayout.NORTH);
        pnTongPhong.add(lbTongPhong2, BorderLayout.CENTER);
        lbTongPhong1.setText("Tổng doanh thu phòng");
        lbTongPhong1.setFont(sgUI18b);
        lbTongPhong2.setText(HoaDonBUS.tienPhong() + "");
        lbTongPhong2.setFont(sgUI24b);
        lbTongPhong1.setHorizontalAlignment(JLabel.CENTER);
        lbTongPhong2.setHorizontalAlignment(JLabel.CENTER);
        pnTongPhong.setBorder(new EmptyBorder(0, 5, 0, 5));

        pnTongDV.setBackground(Color.white);
        pnTkTong.add(pnTongDV);
        //Khúc đây
        pnTongDV.setLayout(new BorderLayout());
        pnTongDV.add(lbIconDV, BorderLayout.WEST);
        lbIconDV.setIcon(iconDV);
        pnTongDV.add(pnDVText, BorderLayout.CENTER);
        pnDVText.setBackground(Color.white);
        pnDVText.setLayout(new BorderLayout());
        //
        pnTongDV.add(lbTongDV1, BorderLayout.NORTH);
        pnTongDV.add(lbTongDV2, BorderLayout.CENTER);
        lbTongDV1.setText("Tổng doanh thu dịch vụ");
        lbTongDV1.setFont(sgUI18b);
        lbTongDV2.setText(HoaDonBUS.tienDichVu() + "");
        lbTongDV2.setFont(sgUI24b);
        lbTongDV1.setHorizontalAlignment(JLabel.CENTER);
        lbTongDV2.setHorizontalAlignment(JLabel.CENTER);
        pnTongThu.setBorder(new EmptyBorder(0, 0, 0, 5));

        pnChart.setLayout(new BorderLayout());
        pnChart.add(pnPie, BorderLayout.WEST);
        pnChart.add(pnTkNamTh, BorderLayout.CENTER);

        pnTkNamTh.setLayout(new BorderLayout());
        pnTkNamTh.add(pnInput, BorderLayout.NORTH);

        //pnInput
        pnInput.setLayout(new GridLayout(1, 3, 5, 5));
        pnInput.add(pnCbNam);
        pnInput.add(pnCbThang);

        pnCbNam.setLayout(new BorderLayout());
        pnCbNam.add(lbCbNam, BorderLayout.WEST);
        pnCbNam.add(cbNam, BorderLayout.CENTER);
        cbNam.removeAllItems();
        cbNam.addItem("Chọn năm");
        for (Integer i : listNum) {
            cbNam.addItem(i);
        }
        lbCbNam.setText("Chọn năm");

        cbThang.setEnabled(false);
        pnCbThang.setLayout(new BorderLayout());
        pnCbThang.add(lbCbThang, BorderLayout.WEST);
        pnCbThang.add(cbThang, BorderLayout.CENTER);
        lbCbThang.setText("Chọn Tháng");

        cbNam.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (!cbNam.getSelectedItem().equals("Chọn năm")) {
                    cbThang.setEnabled(true);
                } else {
                    cbThang.setEnabled(false);
                }

            }
        });

        pnInput.add(btnThongKe);
        //
        lbIconSu.setIcon(iconTong);
        lbIconPh.setIcon(iconPhong);
        lbIconDV.setIcon(iconDV);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        chart = ChartFactory.createBarChart(
                "Thống kê sự tương quan phòng và dịch vụ",
                "Tháng",
                "Giá tiền",
                dataset
        );

        pnTkNamTh.add(pnBarLine, BorderLayout.CENTER);

        btnThongKe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if (!((String) cbNam.getSelectedItem()).equals("Chọn năm")) {
                if (cbThang.getSelectedItem().equals("Chọn ngày")) {
                    pnBarChart = new PanelBar((int) cbNam.getSelectedItem());
                    pnBarLine.removeAll();
                    pnBarLine.repaint();
                    pnBarLine.revalidate();
                    pnBarLine.setLayout(new BorderLayout());
                    pnBarLine.add(pnBarChart, BorderLayout.CENTER);
                } else {
                    int thang = Integer.parseInt((String) cbThang.getSelectedItem());
                    pnLineChart = new PanelLine(thang, (int) cbNam.getSelectedItem());
                    pnBarLine.removeAll();
                    pnBarLine.repaint();
                    pnBarLine.revalidate();
                    pnBarLine.setLayout(new BorderLayout());
                    pnBarLine.add(pnLineChart, BorderLayout.CENTER);
                }
//                }
//                else {
//                    JOptionPane.showMessageDialog(null, "Vui lòng chọn năm", "Thông báo", 0);
//                }
            }
        });

        pnPie.setLayout(new BorderLayout());
        pnPie.add(pnPieChart, BorderLayout.CENTER);

        pnBarLine.setLayout(new BorderLayout());
        chartPanel = new ChartPanel(chart);
        pnBarLine.add(chartPanel, BorderLayout.CENTER);

        pnContain.add(pnTkTong, BorderLayout.NORTH);
        pnContain.add(pnChart, BorderLayout.CENTER);

        pn.add(pnContain, BorderLayout.CENTER);

//        fr.add(pn, BorderLayout.CENTER);
//
//        fr.setVisible(true);
//        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(pn, BorderLayout.CENTER);
    }
}
