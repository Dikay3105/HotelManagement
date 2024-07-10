package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelDatThuePhong extends JPanel {

    int lightDark;
    private JPanel pnTop = new JPanel();
    private JLabel lbTop = new JLabel("DANH MỤC CHI TIẾT ĐẶT PHÒNG");
    private JPanel pnContent = new JPanel();
    private JPanel pnTab = new JPanel();
    private JPanel pnCenter = new JPanel();
    private JPanel pnTabBtn = new JPanel();
    private JPanel pnEmpty = new JPanel();
    private JButton btnCTT = new JButton("Chi tiết thuê");
    private JButton btnDP = new JButton("Đặt phòng");

    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);

    private DatPhongGUI dpGUI;
    private PanelCTT pnCTT;

    public PanelDatThuePhong() {
        initComponents();
    }

    public void initComponents() {
        setLayout(new BorderLayout());
        add(pnTop, BorderLayout.NORTH);
        add(pnContent, BorderLayout.CENTER);
        //--------pnTop
        pnTop.setLayout(new BorderLayout());
        pnTop.add(lbTop, BorderLayout.CENTER);
        lbTop.setFont(sgUI18b);
        lbTop.setHorizontalAlignment(JLabel.CENTER);
        //--------pnContent
        pnContent.setLayout(new BorderLayout());
        pnContent.add(pnTab, BorderLayout.NORTH);
        pnContent.add(pnCenter, BorderLayout.CENTER);
        //--------pnTab
        pnTab.setLayout(new BorderLayout());
        pnTab.add(pnTabBtn, BorderLayout.WEST);
        pnTab.add(pnEmpty, BorderLayout.CENTER);
        pnTab.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
        //--------pnTabBtn
        pnTabBtn.setLayout(new GridLayout(1, 2));
        pnTabBtn.add(btnCTT);
        pnTabBtn.add(btnDP);
        //----btnCCT
        btnCTT.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 2, 0, Color.red), new EmptyBorder(10, 20, 8, 20)));
        btnCTT.setFont(sgUI13b);
        btnCTT.setForeground(Color.decode("#C60000"));
        btnCTT.setRequestFocusEnabled(false);
        btnCTT.setBackground(Color.white);
        //----btnDP
        btnDP.setBorder(new EmptyBorder(10, 20, 10, 20));
        btnDP.setFont(sgUI13b);
        btnDP.setRequestFocusEnabled(false);
        btnDP.setBackground(Color.white);
        renderCTT();
        btnCTT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCTT.setForeground(Color.decode("#C60000"));
                btnCTT.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 2, 0, Color.red), new EmptyBorder(10, 20, 8, 20)));
                btnDP.setForeground(Color.black);
                btnDP.setBorder(new EmptyBorder(10, 20, 10, 20));
                lbTop.setText("DANH MỤC CHI TIẾT ĐẶT PHÒNG");
                renderCTT();
            }
        });
        btnDP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDP.setForeground(Color.decode("#C60000"));
                btnDP.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 2, 0, Color.red), new EmptyBorder(10, 20, 8, 20)));
                btnCTT.setForeground(Color.black);
                btnCTT.setBorder(new EmptyBorder(10, 20, 10, 20));
                lbTop.setText("DANH MỤC ĐẶT PHÒNG");
                renderDP();
            }
        });
    }

    public void renderCTT() {
        pnCenter.removeAll();
        pnCenter.revalidate();
        pnCenter.repaint();
        // Giao diện pnCenter
        pnCTT = new PanelCTT();
        pnCenter.setLayout(new BorderLayout());
        pnCenter.add(pnCTT, BorderLayout.CENTER);
        check = 0;
    }
    public int check = 0;

    public void renderDP() {
        check = 1;
        pnCenter.removeAll();
        pnCenter.revalidate();
        pnCenter.repaint();
        pnCenter.setLayout(new BorderLayout());
        dpGUI = new DatPhongGUI();
        pnCenter.add(dpGUI, BorderLayout.CENTER);
        dpGUI.lightDark(lightDark);
        dpGUI.lightDark(lightDark);
    }

    public void lightDark(int lightDark) {
        this.lightDark = lightDark;
        if (lightDark == 1) {
            Color black = new Color(51, 51, 51);
            pnTop.setBackground(black);
            pnContent.setBackground(black);
            pnTab.setBackground(black);
            pnCenter.setBackground(black);
            lbTop.setForeground(Color.white);
            pnTabBtn.setBackground(black);
            pnEmpty.setBackground(black);
            if (check == 0) {
                renderCTT();
            }
            else {
                renderDP();
            }
        } else {
            pnTop.setBackground(Color.white);
            lbTop.setForeground(Color.black);
            pnTab.setBackground(Color.white);
            pnTabBtn.setBackground(Color.white);
            pnEmpty.setBackground(Color.white);
            pnCenter.setBackground(Color.white);
            pnContent.setBackground(Color.white);
            if (check == 0) {
                renderCTT();
            }
            else {
                renderDP();
            }
        }
    }
}
