package GUI;

import static DAO.KhachHangDAO.KHList;
import GUI.ThongKeGUI.PanelThongKe;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import java.util.ArrayList;
import javax.swing.border.*;
// link icon icons8.com

public class AdminGUI {

    private JFrame frBackground = new JFrame();
    private JPanel pnTop = new JPanel();
    private JPanel pnMenu = new JPanel();
    private JPanel pnContent = new JPanel();
    private JLabel lbSoDo = new JLabel("Sơ đồ phòng");
    private JLabel lbKhachHang = new JLabel("Khách hàng");
    private ArrayList<JButton> btnList = new ArrayList<>();
    private JPanel pnMenuTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    private JPanel pnMenuBottom = new JPanel();
    private JButton btnDarkLight = new JButton("Dark mode");
    private JButton btnLogOut = new JButton("Đăng xuất");
//Vĩnh Khoa
    private JLabel lblInforJLabel = new JLabel();
    private JButton btnAvatar = new JButton();
    private PanelPhong2 pnPhong;
    private PanelKhachHang pnKhachHang;
    private PanelDichVu pnDichVu;
    private PanelDatThuePhong pnDatThuePhong;
    private PanelHoaDon pnHoaDon;
    private JButton btnMenu = new JButton();
    JPanel pnTop1 = new JPanel();
    int menu = 0;

    int menucheck = 0;
    int check = 0;
    int LightDark = 0;
    JButton pos;
    JPanel pnTop2 = new JPanel();

    private Font sgUI18 = new Font("Segoe UI", Font.BOLD, 15);
    private Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    private Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);
    JLabel lbLogo = new JLabel("LUXURY HOTEL");

    public AdminGUI() throws IOException {
        initComponents();
    }

    public void initComponents() throws IOException {
        frBackground.setVisible(true);
        frBackground.setTitle("QUẢN LÝ KHÁCH SẠN");
        frBackground.setSize(1300, 700);
        frBackground.setLocationRelativeTo(null);
        frBackground.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frBackground.setLayout(new BorderLayout(5, 5));
        frBackground.getContentPane().setBackground(Color.decode("#EEEEEE"));
        frBackground.add(pnTop, BorderLayout.NORTH);
        frBackground.add(pnMenu, BorderLayout.WEST);
        frBackground.add(pnContent, BorderLayout.CENTER);

        pnTop.setPreferredSize(new Dimension(100, 50));
        pnTop.setBackground(Color.white);

        pnMenu.setPreferredSize(new Dimension(200, 100));
        pnMenu.setBackground(Color.white);
        pnMenu.setLayout(new BoxLayout(pnMenu, BoxLayout.Y_AXIS));
        pnMenu.add(pnMenuTop);

        pnMenuTop.setPreferredSize(new Dimension(200, 1200));
        pnMenuTop.setBackground(Color.white);
        pnMenuBottom.setPreferredSize(new Dimension(200, 200));
        pnMenuBottom.setBackground(Color.white);

        pnContent.setPreferredSize(new Dimension(100, 100));
        pnContent.setBackground(Color.white);
        //Vĩnh Khoa
        Home();
        //
        //Vĩnh Khoa
        ImageIcon iconAVT = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/default.jpg")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        btnAvatar.setIcon(iconAVT);
        //

        //@@@@@@@@@@@@ Menu button
        JButton btn1 = new JButton("Sơ đồ phòng");
        btn1.setToolTipText("Sơ đồ phòng");
        JButton btn2 = new JButton("Khách hàng");
        btn2.setToolTipText("Quản lý khách hàng");
        JButton btn3 = new JButton("Nhân viên");
        btn3.setToolTipText("Quản lý nhân viên");
        JButton btn4 = new JButton("Phòng");
        btn4.setToolTipText("Quản lý phòng");
        JButton btn5 = new JButton("Dịch vụ");
        btn5.setToolTipText("Quản lý dịch vụ");
        JButton btn6 = new JButton("Đặt phòng");
        btn6.setToolTipText("Quản lý đặt phòng");
        JButton btn7 = new JButton("Thống kê");
        btn7.setToolTipText("Quản lý thống kê");
        JButton btn8 = new JButton("Hóa đơn");
        btn8.setToolTipText("Quản lý hóa đơn");
        JButton btn9 = new JButton("Điểm danh");
        btn9.setToolTipText("Quản lý Điểm danh");
        JButton btn10 = new JButton("Cài đặt");
        btn10.setToolTipText("Cài đặt");
        //icon 
        ImageIcon icon1 = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/phong.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btn1.setIcon(icon1);
//        btn1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/GUI/asset/khachhang48.png"))));
        ImageIcon icon2 = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/khachhang.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btn2.setIcon(icon2);
        ImageIcon icon3 = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/nhanvien.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btn3.setIcon(icon3);
        ImageIcon icon4 = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/phong.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btn4.setIcon(icon4);
        ImageIcon icon5 = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/dichvu.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btn5.setIcon(icon5);
        ImageIcon icon6 = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/datphong.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btn6.setIcon(icon6);
        ImageIcon icon7 = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/thongke.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btn7.setIcon(icon7);
        ImageIcon icon8 = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/thanhtoan.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btn8.setIcon(icon8);
        ImageIcon icon9 = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/diemdanh.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btn9.setIcon(icon9);
        ImageIcon icon10 = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/caidat.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btn10.setIcon(icon10);
        ImageIcon iconLogout = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/logout.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btnLogOut.setIcon(iconLogout);
        ImageIcon iconMenu = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/menu.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btnMenu.setIcon(iconMenu);
        btnList.add(btn1);
        btnList.add(btn2);
        btnList.add(btn3);
        btnList.add(btn4);
        btnList.add(btn5);
        btnList.add(btn6);
        btnList.add(btn7);
        btnList.add(btn8);
        btnList.add(btn9);
        btnList.add(btn10);
        btnMenu.setFocusPainted(false);

        ImageIcon iconMode = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/moon.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btnDarkLight.setIcon(iconMode);

        for (JButton x : btnList) {
            pnMenuTop.add(x);
            x.setFocusPainted(false);
            x.setBorder(new EmptyBorder(10, 10, 10, 10));
            x.setPreferredSize(new Dimension(200, 40));
            x.setMaximumSize(new Dimension(200, 40));
            x.setFont(sgUI18);
            x.setHorizontalAlignment(SwingConstants.LEFT);
            x.setBackground(Color.white);
            x.setForeground(Color.black);
        }
        btnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menucheck == 0) {
                    ImageIcon iconMenu = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/x.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
                    btnMenu.setIcon(iconMenu);
                    run(btnList);
                    for (JButton x : btnList) {
                        x.setText("");
                    }
                    menucheck = 1;
                    btnDarkLight.setHorizontalAlignment(JLabel.LEFT);
                    btnLogOut.setHorizontalAlignment(JLabel.LEFT);
                    btnDarkLight.setText("");
                    btnLogOut.setText("");
                } else {
                    ImageIcon iconMenu = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/menu.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
                    btnMenu.setIcon(iconMenu);
                    menucheck = 0;
                    btnList.get(0).setText("Sơ đồ phòng");
                    btnList.get(1).setText("Khách hàng");
                    btnList.get(2).setText("Nhân viên");
                    btnList.get(3).setText("Phòng");
                    btnList.get(4).setText("Dịch vụ");
                    btnList.get(5).setText("Đặt phòng");
                    btnList.get(6).setText("Thống kê");
                    btnList.get(7).setText("Hóa đơn");
                    btnList.get(8).setText("Điểm danh");
                    btnList.get(9).setText("Cài đặt");
                    run2(btnList);
                    btnDarkLight.setHorizontalAlignment(JLabel.CENTER);
                    btnLogOut.setHorizontalAlignment(JLabel.CENTER);
                    if (LightDark == 1) {
                        btnDarkLight.setText("Light mode");
                    } else {
                        btnDarkLight.setText("Dark mode");
                    }
                    btnLogOut.setText("Đăng xuất");
                }

            }
        });
        //Vĩnh Khoa

        pnTop1.setLayout(new FlowLayout(FlowLayout.RIGHT));

        ImageIcon iconLogo = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/hotel.png")).getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH));
        lbLogo.setIcon(iconLogo);

        pnTop2.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnTop.setLayout(new BorderLayout());
        pnTop.add(pnTop1, BorderLayout.EAST);
        pnTop.add(pnTop2, BorderLayout.WEST);
        pnTop1.setBackground(Color.white);
        pnTop2.setBackground(Color.white);
        pnTop2.add(lbLogo);
        pnTop2.add(btnMenu);
        lblInforJLabel.setFont(sgUI18);
        lblInforJLabel.setForeground(Color.black);
        lblInforJLabel.setText("Xin chào, " + mainGUI.taiKhoan + " - " + mainGUI.nameNV);
//        pnTop1.add(btnMenu);
        pnTop1.add(lblInforJLabel);
        pnTop1.add(btnAvatar);

        btnAvatar.setBorder(new EmptyBorder(10, 10, 10, 10));
        btnAvatar.setPreferredSize(new Dimension(50, 40));
        btnAvatar.setMaximumSize(new Dimension(50, 40));
        btnAvatar.setFont(sgUI18);
        btnAvatar.setHorizontalAlignment(SwingConstants.RIGHT);
        btnAvatar.setBackground(Color.white);
        btnAvatar.setForeground(Color.black);
        btnAvatar.setOpaque(false);
        btnAvatar.setContentAreaFilled(false);
        btnAvatar.setBorderPainted(false);

        btnMenu.setBorder(new EmptyBorder(10, 10, 10, 10));
        btnMenu.setPreferredSize(new Dimension(50, 40));
        btnMenu.setMaximumSize(new Dimension(50, 40));
        btnMenu.setFont(sgUI18);
        btnMenu.setHorizontalAlignment(SwingConstants.RIGHT);
        btnMenu.setBackground(Color.white);
        btnMenu.setForeground(Color.black);
        btnMenu.setOpaque(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setBorderPainted(false);

        btnDarkLight.setFocusPainted(false);
        btnLogOut.setFocusPainted(false);

        btnDarkLight.setBorder(new EmptyBorder(10, 10, 10, 10));
        btnDarkLight.setPreferredSize(new Dimension(200, 40));
        btnDarkLight.setMaximumSize(new Dimension(200, 40));
        btnDarkLight.setFont(sgUI18);
        btnDarkLight.setBackground(Color.decode("#FFCDD2"));
        btnDarkLight.setBorder(new MatteBorder(0, 10, 0, 10, Color.white));
        btnDarkLight.setMnemonic(KeyEvent.VK_D);
        btnDarkLight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LightDark == 0) {
                    lblInforJLabel.setForeground(Color.white);
                    btnDarkLight.setText("Light mode");
                    pnTop.setBackground(new Color(51, 51, 51));
                    pnContent.setBackground(new Color(51, 51, 51));
                    frBackground.getContentPane().setBackground(Color.decode("#424242"));
                    pnMenuTop.setBackground(new Color(51, 51, 51));
                    pnMenuBottom.setBackground(new Color(51, 51, 51));
                    btnDarkLight.setBorder(new MatteBorder(0, 10, 0, 10, new Color(51, 51, 51)));
                    btnDarkLight.setBackground(Color.decode("#616161"));
                    btnDarkLight.setForeground(Color.white);
                    btnLogOut.setBorder(new MatteBorder(0, 10, 0, 10, new Color(51, 51, 51)));
                    btnLogOut.setBackground(Color.decode("#616161"));
                    btnLogOut.setForeground(Color.white);
                    pnTop2.setBackground(new Color(51, 51, 51));
                    pnTop1.setBackground(new Color(51, 51, 51));
                    lbLogo.setForeground(Color.white);
                    setBackground();
                    if (pos != null) {
                        pos.setBackground(Color.decode("#757575"));
                        pos.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 3, 0, 0, Color.decode("#FFFFFF")), new EmptyBorder(10, 10, 10, 10)));
                    }
                    LightDark = 1;
                    if (menu == 2) {
                        pnKhachHang.lightDark(LightDark);
                    }
                    if (menu == 3) {
                        pnNhanVien.lightDark(LightDark);
                    }
                    if (menu == 4) {
                        pnPhong.lightDark(LightDark);
                    }
                    if (menu == 5) {
                        pnDichVu.lightDark(LightDark);
                    }
                    if (menu == 6) {
                        pnDatThuePhong.lightDark(LightDark);
                    }
                    if (menu == 8) {
                        pnHoaDon.lightDark(LightDark);
                    }
                } else {
                    btnDarkLight.setText("Dark mode");
                    lblInforJLabel.setForeground(Color.black);
                    pnTop.setBackground(Color.white);
                    pnContent.setBackground(Color.white);
                    frBackground.getContentPane().setBackground(Color.decode("#EEEEEE"));
                    pnMenuTop.setBackground(Color.white);
                    pnMenuBottom.setBackground(Color.white);
                    btnDarkLight.setBorder(new MatteBorder(0, 10, 0, 10, Color.white));
                    btnDarkLight.setBackground(Color.decode("#FFCDD2"));
                    btnDarkLight.setForeground(Color.black);
                    btnLogOut.setBorder(new MatteBorder(0, 10, 0, 10, Color.white));
                    btnLogOut.setBackground(Color.decode("#FFCDD2"));
                    btnLogOut.setForeground(Color.black);
                    pnTop1.setBackground(Color.white);
                    pnTop2.setBackground(Color.white);
                    lbLogo.setForeground(Color.black);
                    setBackground();
                    if (pos != null) {
                        pos.setBackground(Color.decode("#EF9A9A"));
                        pos.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 3, 0, 0, Color.decode("#C62828")), new EmptyBorder(10, 10, 10, 10)));
                    }
                    LightDark = 0;
                    if (menu == 2) {
                        pnKhachHang.lightDark(LightDark);
                    }
                    if (menu == 3) {
                        pnNhanVien.lightDark(LightDark);
                    }
                    if (menu == 4) {
//                        contentPhong();
                        pnPhong.lightDark(LightDark);
                    }
                    if (menu == 5) {
                        pnDichVu.lightDark(LightDark);
                    }
                    if (menu == 6) {
                        pnDatThuePhong.lightDark(LightDark);
                    }
                    if (menu == 8) {
                        pnHoaDon.lightDark(LightDark);
                    }
                }
            }
        });

        btnLogOut.setBorder(new EmptyBorder(10, 10, 10, 10));
        btnLogOut.setPreferredSize(new Dimension(200, 40));
        btnLogOut.setMaximumSize(new Dimension(200, 40));
        btnLogOut.setFont(sgUI18);
        btnLogOut.setBackground(Color.decode("#FFCDD2"));
        btnLogOut.setBorder(new MatteBorder(0, 10, 0, 10, Color.white));
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frBackground.setVisible(false);
                new LoginGUI();
            }
        });
        ///Vĩnh Khoa
        frBackground.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                Component[] components = pnContent.getComponents();
                for (Component component : components) {
                    if (component instanceof PanelHome) {

                        try {
                            Home();
                        } catch (IOException ex) {
                            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }

                    }
                }

            }
        });
        /////

        pnMenuBottom.add(btnDarkLight);
        pnMenuBottom.add(btnLogOut);
        pnMenu.add(pnMenuBottom);
        setAction();
        setMouse();
    }

    public void setBackground() {
        if (LightDark == 1) {
            for (JButton x : btnList) {
                x.setBackground(Color.white);
                x.setForeground(Color.black);
                x.setBorder(new EmptyBorder(10, 10, 10, 10));
            }
        } else {
            for (JButton x : btnList) {
                x.setBackground(new Color(51, 51, 51));
                x.setForeground(Color.white);
                x.setBorder(new EmptyBorder(10, 10, 10, 10));
            }
        }
    }

    public synchronized void run(JButton btn, Color color) {
        new Thread(() -> {
            btn.setLayout(null);
            JPanel pn = new JPanel();
            pn.setBackground(color);
            btn.add(pn);
            for (int i = 0; i <= btn.getWidth(); i++) {
                pn.setBounds(0, btn.getHeight() - 3, i, 3);
                frBackground.repaint();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            }
            btn.remove(pn);
        }).start();
    }

    public synchronized void run(ArrayList<JButton> listbtn) {
        new Thread(() -> {
            for (int i = 200; i >= 50; i--) {
                for (JButton x : listbtn) {
                    x.setPreferredSize(new Dimension(i, 40));
                    x.setMaximumSize(new Dimension(i, 40));
                }
                pnMenu.setPreferredSize(new Dimension(i, 100));
                pnMenuTop.setPreferredSize(new Dimension(i, 1200));
                pnMenuBottom.setPreferredSize(new Dimension(i, 200));
                btnDarkLight.setPreferredSize(new Dimension(i, 40));
                btnDarkLight.setMaximumSize(new Dimension(i, 40));
                btnLogOut.setPreferredSize(new Dimension(i, 40));
                btnLogOut.setMaximumSize(new Dimension(i, 40));
                if (menu == 0) {
                    pnContent.removeAll();
                    pnContent.revalidate();
                    pnContent.repaint();
                    JPanel a = new PanelHome((int) pnContent.getWidth() + 220, (int) pnContent.getHeight() + 100);
                    pnContent.setLayout(new BorderLayout());
                    pnContent.add(a, BorderLayout.CENTER);
                }
                pnMenu.repaint();
                pnMenu.revalidate();
                frBackground.repaint();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            }
        }).start();
    }

    public synchronized void run2(ArrayList<JButton> listbtn) {
        new Thread(() -> {
            for (int i = 50; i <= 200; i++) {
                for (JButton x : listbtn) {
                    x.setPreferredSize(new Dimension(i, 40));
                    x.setMaximumSize(new Dimension(i, 40));
                }
                btnDarkLight.setPreferredSize(new Dimension(i, 40));
                btnDarkLight.setMaximumSize(new Dimension(i, 40));
                btnLogOut.setPreferredSize(new Dimension(i, 40));
                btnLogOut.setMaximumSize(new Dimension(i, 40));
                pnMenu.setPreferredSize(new Dimension(i, 100));
                pnMenuTop.setPreferredSize(new Dimension(i, 1200));
                pnMenuBottom.setPreferredSize(new Dimension(i, 200));
                if (menu == 0) {
                    pnContent.removeAll();
                    pnContent.revalidate();
                    pnContent.repaint();
                    JPanel a = new PanelHome((int) frBackground.getWidth(), (int) frBackground.getHeight());
                    pnContent.setLayout(new BorderLayout());
                    pnContent.add(a, BorderLayout.CENTER);
                }
                pnMenu.repaint();
                pnMenu.revalidate();
                pnContent.repaint();
                pnContent.revalidate();
                frBackground.repaint();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            }
        }).start();
    }

    //Vĩnh Khoa
    public void Home() throws IOException {
        pnContent.removeAll();
        pnContent.revalidate();
        pnContent.repaint();
        JPanel a = new PanelHome((int) frBackground.getWidth(), (int) frBackground.getHeight());
        pnContent.setLayout(new BorderLayout());
        pnContent.add(a, BorderLayout.CENTER);
    }
    //

    public void setAction() {
        for (JButton x : btnList) {
            x.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (LightDark == 0) {
                        LightDark = 1;
                        setBackground();
                        x.setBackground(Color.decode("#EF9A9A"));
                        x.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 3, 0, 0, Color.decode("#C62828")), new EmptyBorder(10, 10, 10, 10)));
                        pos = x;
                        LightDark = 0;
                    } else {
                        LightDark = 0;
                        setBackground();
                        x.setBackground(Color.decode("#757575"));
                        x.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 3, 0, 0, Color.decode("#FFFFFF")), new EmptyBorder(10, 10, 10, 10)));
                        pos = x;
                        LightDark = 1;
                    }
                    if (e.getSource() == btnList.get(0)) {
                        if (LightDark == 0) {
                            run(x, Color.decode("#FF4500"));
                        } else {
                            run(x, Color.decode("#FFFFFF"));
                        }
                        menu = 1;
                        contentSoDoPhong();
                    } else if (e.getSource() == btnList.get(1)) {
                        if (LightDark == 0) {
                            run(x, Color.decode("#FF4500"));
                        } else {
                            run(x, Color.decode("#FFFFFF"));
                        }
                        menu = 2;
                        contentKhachHang();
                        pnKhachHang.lightDark(LightDark);
                    } else if (e.getSource() == btnList.get(2)) {
                        if (LightDark == 0) {
                            run(x, Color.decode("#FF4500"));
                        } else {
                            run(x, Color.decode("#FFFFFF"));
                        }
                        menu = 3;
                        contentNhanVien();
                        pnNhanVien.lightDark(LightDark);
                    } else if (e.getSource() == btnList.get(3)) {
                        if (LightDark == 0) {
                            run(x, Color.decode("#FF4500"));
                        } else {
                            run(x, Color.decode("#FFFFFF"));
                        }
                        menu = 4;
                        contentPhong();
                        pnPhong.lightDark(LightDark);
                    } else if (e.getSource() == btnList.get(4)) {
                        if (LightDark == 0) {
                            run(x, Color.decode("#FF4500"));
                        } else {
                            run(x, Color.decode("#FFFFFF"));
                        }
                        menu = 5;
                        contentDichVu();
                        pnDichVu.lightDark(LightDark);
                    } else if (e.getSource() == btnList.get(5)) {
                        if (LightDark == 0) {
                            run(x, Color.decode("#FF4500"));
                        } else {
                            run(x, Color.decode("#FFFFFF"));
                        }
                        menu = 6;
                        contentDatPhong();
                        pnDatThuePhong.lightDark(LightDark);
                    } else if (e.getSource() == btnList.get(6)) {
                        if (LightDark == 0) {
                            run(x, Color.decode("#FF4500"));
                        } else {
                            run(x, Color.decode("#FFFFFF"));
                        }
                        menu = 7;
                        contentThongKe();
//                        pnHoaDon.lightDark(LightDark);
                    } else if (e.getSource() == btnList.get(7)) {
                        if (LightDark == 0) {
                            run(x, Color.decode("#FF4500"));
                        } else {
                            run(x, Color.decode("#FFFFFF"));
                        }
                        menu = 8;
                        contentHoaDon();
                        pnHoaDon.lightDark(LightDark);
                    } else if (e.getSource() == btnList.get(8)) {
                        if (LightDark == 0) {
                            run(x, Color.decode("#FF4500"));
                        } else {
                            run(x, Color.decode("#FFFFFF"));
                        }
                        menu = 9;
                        contentDiemDanh();
                    } else if (e.getSource() == btnList.get(9)) {
                        if (LightDark == 0) {
                            run(x, Color.decode("#FF4500"));
                        } else {
                            run(x, Color.decode("#FFFFFF"));
                        }
                        new Settings();
                    }
                }
            });
        }

    }

    public void setMouse() {
        for (JButton x : btnList) {
            x.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (x != pos && LightDark == 0) {
                        x.setBackground(Color.decode("#F5F5F5"));
                    } else {
                        x.setBackground(Color.decode("#A0A0A0"));
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (LightDark == 0) {
                        if (x != pos) {
                            x.setBackground(Color.white);
                        } else {
                            x.setBackground(Color.decode("#EF9A9A"));
                        }
                    } else {
                        if (x != pos) {
                            x.setBackground(new Color(51, 51, 51));
                        } else {
                            x.setBackground(Color.decode("#757575"));
                        }
                    }
                }
            });
        }
    }

    //-------------------------------------------------------------------NHÂN VIÊN------------------------------------------------
    private PanelNhanVien pnNhanVien;

    public void contentNhanVien() {
        pnContent.removeAll();
        pnContent.revalidate();
        pnContent.repaint();
        pnNhanVien = new PanelNhanVien();
        pnContent.setLayout(new BorderLayout());
        pnContent.add(pnNhanVien, BorderLayout.CENTER);
    }
    //--------------------------------------------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------Khách hàng-----------------------------------------------------------
    public boolean checkNULL() {
        Component myComps[] = pnContent.getComponents();
        for (int i = 0; i < myComps.length; i++) {
            if (myComps[i] instanceof JTextField) {
                JTextField myTextField = (JTextField) myComps[i];
                if (myTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Chưa nhập đủ");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean find(String a) {
        for (int i = 0; i < KHList.size(); i++) {
            if (KHList.get(i).getMaKH().contains(a) && KHList.get(i).getXuLy() == 0) {

                return true;
            }
        }
        return false;
    }

    public void contentKhachHang() {
        pnContent.removeAll();
        pnContent.revalidate();
        pnContent.repaint();
        pnKhachHang = new PanelKhachHang();
        pnContent.setLayout(new BorderLayout());
        pnContent.add(pnKhachHang, BorderLayout.CENTER);
    }

    public void contentPhong() {
        pnContent.removeAll();
        pnContent.revalidate();
        pnContent.repaint();
        pnPhong = new PanelPhong2();
        pnContent.setLayout(new BorderLayout());
        pnContent.add(pnPhong, BorderLayout.CENTER);
    }

    public void contentDichVu() {
        pnContent.removeAll();
        pnContent.revalidate();
        pnContent.repaint();
        pnDichVu = new PanelDichVu();
        pnContent.setLayout(new BorderLayout());
        pnContent.add(pnDichVu, BorderLayout.CENTER);
    }

    public void contentDatPhong() {
        pnContent.removeAll();
        pnContent.revalidate();
        pnContent.repaint();
        pnDatThuePhong = new PanelDatThuePhong();
        pnContent.setLayout(new BorderLayout());
        pnContent.add(pnDatThuePhong, BorderLayout.CENTER);
    }

    PanelSoDoPhong pnSoDoPhong;

    public void contentSoDoPhong() {
        pnContent.removeAll();
        pnContent.revalidate();
        pnContent.repaint();
        pnSoDoPhong = new PanelSoDoPhong();
        pnContent.setLayout(new BorderLayout());
        pnContent.add(pnSoDoPhong, BorderLayout.CENTER);

    }

    public void contentHoaDon() {
        pnContent.removeAll();
        pnContent.revalidate();
        pnContent.repaint();
        pnHoaDon = new PanelHoaDon();
        pnContent.setLayout(new BorderLayout());
        pnContent.add(pnHoaDon, BorderLayout.CENTER);
    }

    private PanelDiemDanh pnDiemDanh;

    public void contentDiemDanh() {
        pnContent.removeAll();
        pnContent.revalidate();
        pnContent.repaint();
        pnDiemDanh = new PanelDiemDanh();

        pnContent.setLayout(new BorderLayout());
        pnContent.add(pnDiemDanh, BorderLayout.CENTER);
    }

    public void contentThongKe() {
        pnContent.removeAll();
        pnContent.revalidate();
        pnContent.repaint();
        PanelThongKe pnTk = new PanelThongKe();
        pnContent.setLayout(new BorderLayout());
        pnContent.add(pnTk, BorderLayout.CENTER);
    }
}
