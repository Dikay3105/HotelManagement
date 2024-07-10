package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelPhong2 extends JPanel {

    int lightDark;
    int check = 0;
    private JFrame fr = new JFrame();
    private JPanel pnTop = new JPanel();
    private JLabel lbTop = new JLabel("DANH MỤC PHÒNG");
    private JPanel pnContent = new JPanel();
    private JPanel pnContent1 = new JPanel();
    private JPanel pnContent2 = new JPanel();
    private JPanel pnMenu = new JPanel();
    private JPanel pnEmpty = new JPanel();
    private JButton btnPhong = new JButton("Phòng");
    private JButton btnTienIch = new JButton("Tiện ích");
    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);
    

    public PanelPhong2() {
        initComponents();
    }

    public void initComponents() {
        setLayout(new BorderLayout());
        //----pntop
        pnTop.setLayout(new BorderLayout());
        pnTop.add(lbTop, BorderLayout.CENTER);
        lbTop.setFont(sgUI18b);
        lbTop.setHorizontalAlignment(JLabel.CENTER);
        //----pnContent
        pnContent.setLayout(new BorderLayout());
        pnContent.add(pnContent1, BorderLayout.NORTH);
        pnContent.add(pnContent2, BorderLayout.CENTER);
        //----pnContent1
        pnContent1.setLayout(new BorderLayout());
        pnContent1.add(pnEmpty, BorderLayout.CENTER);
        pnContent1.add(pnMenu, BorderLayout.WEST);
        //----pnMenu
        pnMenu.setLayout(new GridLayout(1, 2));
        pnMenu.add(btnPhong);
        pnMenu.add(btnTienIch);
        //----btnPhong
        btnPhong.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 2, 0, Color.red), new EmptyBorder(10, 20, 8, 20)));
        btnPhong.setFont(sgUI13b);
        btnPhong.setForeground(Color.decode("#C60000"));
        btnPhong.setRequestFocusEnabled(false);
        //----btnTienIch
        btnTienIch.setBorder(new EmptyBorder(10, 20, 10, 20));
        btnTienIch.setFont(sgUI13b);
        btnTienIch.setRequestFocusEnabled(false);

        add(pnTop, BorderLayout.NORTH);
        add(pnContent, BorderLayout.CENTER);
        renderPhong();
        //---action btnPhong
        btnPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lightDark == 0) {
                    btnPhong.setForeground(Color.decode("#C60000"));
                    btnPhong.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 2, 0, Color.red), new EmptyBorder(10, 20, 8, 20)));
                    btnTienIch.setForeground(Color.black);
                } else {
                    btnPhong.setForeground(Color.decode("#C60000"));
                    btnPhong.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 2, 0, Color.red), new EmptyBorder(10, 20, 8, 20)));
                    btnTienIch.setForeground(Color.white);
                }
                btnTienIch.setBorder(new EmptyBorder(10, 20, 10, 20));
                renderPhong();
            }
        });
        btnTienIch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lightDark == 0) {
                    btnTienIch.setForeground(Color.decode("#C60000"));
                    btnTienIch.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 2, 0, Color.red), new EmptyBorder(10, 20, 8, 20)));
                    btnPhong.setForeground(Color.black);
                } else {
                    btnTienIch.setForeground(Color.decode("#C60000"));
                    btnTienIch.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 2, 0, Color.red), new EmptyBorder(10, 20, 8, 20)));
                    btnPhong.setForeground(Color.white);
                }
                btnPhong.setBorder(new EmptyBorder(10, 20, 10, 20));
                renderTienIch();
            }
        });
    }

    public void renderPhong() {
        check = 0;
        pnContent2.removeAll();
        pnContent2.revalidate();
        pnContent2.repaint();
        pnContent2.setLayout(new BorderLayout());
        PanelPhong pnP = new PanelPhong();
        pnContent2.add(pnP,BorderLayout.CENTER);
        pnP.lightDark(lightDark);
    }

    

    public void renderTienIch() {
        check = 1;
        pnContent2.removeAll();
        pnContent2.revalidate();
        pnContent2.repaint();
        pnContent2.setLayout(new BorderLayout());
        PanelTienIch pti = new PanelTienIch();
        pti.lightDark(lightDark);
        pnContent2.add(pti, BorderLayout.CENTER);
    }

    public void lightDark(int lightDark) {
        this.lightDark = lightDark;
        if (lightDark == 1) {
            Color black = new Color(51, 51, 51);
            setBackground(black);
            lbTop.setForeground(Color.white);
            pnTop.setBackground(black);
            pnContent.setBackground(black);
            pnContent1.setBackground(black);
            pnContent2.setBackground(black);
            pnMenu.setBackground(black);
            pnEmpty.setBackground(black);
            pnContent1.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode("#F5F5F5")));
            btnTienIch.setBackground(black);
            btnTienIch.setForeground(Color.white);
            btnPhong.setBackground(black);
            btnPhong.setForeground(Color.white);
            pnEmpty.setBackground(black);
            
            if (check == 1) {
                renderTienIch();
            } else {
                renderPhong();
            }
        } else {
            lbTop.setForeground(Color.black);
            setBackground(Color.white);
            pnTop.setBackground(Color.white);
            pnContent.setBackground(Color.white);
            pnContent1.setBackground(Color.white);
            pnContent2.setBackground(Color.white);
            pnMenu.setBackground(Color.white);
            pnEmpty.setBackground(Color.white);
            pnContent1.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
            btnTienIch.setBackground(Color.white);
            btnTienIch.setForeground(Color.black);
            btnPhong.setBackground(Color.white);
            btnPhong.setForeground(Color.black);
            pnEmpty.setBackground(Color.white);
            if (check == 1) {
                renderTienIch();
            } else {
                renderPhong();
            }
        }
    }
}
