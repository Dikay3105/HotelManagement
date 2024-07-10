package GUI.LeTanGUI;

import BUS.ChiTietThueBUS;
import BUS.KhachHangBUS;
import BUS.PhongBUS;
import BUS.ThuePhongBUS;
import DTO.ChiTietThueDTO;
import DTO.DichVuDTO;
import DTO.KhachHangDTO;
import DTO.PhongDTO;
import DTO.SuDungDichVuDTO;
import DTO.ThuePhongDTO;
import GUI.TimeChoose;
import GUI.mainGUI;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class DatPhongLeTan extends JPanel {

    int total = 0;
    JPanel pnKH = new JPanel();
    JLabel lbTitle = new JLabel("Thông tin khách hàng                ");
    JPanel pnCenterKH = new JPanel();
    JLabel lbMaKH = new JLabel("Mã khách hàng:");
    JLabel lbTenKH = new JLabel("Tên khách hàng:");
    JLabel lbCMNDKH = new JLabel("CMND khách hàng:");
    JLabel lbSDTKH = new JLabel("SĐT khách hàng:");
    JLabel lbGTKH = new JLabel("Giới tính:");

    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);

    JTextField txtMaKH = new JTextField();
    JTextField txtTenKH = new JTextField();
    JTextField txtCMNDKH = new JTextField();
    JTextField txtSDTKH = new JTextField();
    JPanel pnGioiTinh = new JPanel();
    ButtonGroup bgGT = new ButtonGroup();
    JRadioButton rbNam = new JRadioButton("Nam");
    JRadioButton rbNu = new JRadioButton("Nữ");

    JPanel pnBtn = new JPanel();
    JButton btnSave = new JButton("Lưu KH");

    JPanel pnCenter = new JPanel();
    JLabel lbCenter = new JLabel("Phiếu đặt phòng");
    JPanel pnContent = new JPanel();
    JPanel pnBtnCenter = new JPanel();
    JButton btnXuLy = new JButton("Đặt phòng");
    boolean check = false;

    public DatPhongLeTan(boolean New) {
        initComponents(New);
    }

    JPanel pnctt = new JPanel();
    JPanel pnmakh = new JPanel();
    JPanel pntenkh = new JPanel();
    JPanel pnmanv = new JPanel();
    JPanel pntennv = new JPanel();

    JPanel pnCenterTop = new JPanel();
    JLabel lbmactt = new JLabel("Mã chi tiết thuê:");
    JLabel lbmakh = new JLabel("Mã khách hàng:");
    JLabel lbtenkh = new JLabel("Tên khách hàng:");
    JLabel lbmanv = new JLabel("Mã nhân viên:");
    JLabel lbtennv = new JLabel("Tên nhân viên:");

    JTextField txtmactt = new JTextField();
    JTextField txtmakh = new JTextField();
    JTextField txttenkh = new JTextField();
    JTextField txtmanv = new JTextField();
    JTextField txttennv = new JTextField();

    JPanel pnCenterBottom = new JPanel();
    JPanel pnLeft = new JPanel();
    JLabel lbLeft = new JLabel("Thông tin phòng thuê      ");
    JPanel pnLeftBottom = new JPanel();
    JPanel pnRight = new JPanel();
    JPanel pnBtnLeft = new JPanel();
    JButton btnSelect = new JButton("Chọn phòng");

    JLabel lbHinhThucThue = new JLabel("Hình thức thuê:");
    JLabel lbNgayThue = new JLabel("Ngày thuê:");
    JLabel lbGioThue = new JLabel("Giờ thuê:");
    JLabel lbNgayTra = new JLabel("Ngày trả:");
    JLabel lbGioTra = new JLabel("Giờ trả:");

    JPanel pnHinhThueThue = new JPanel();
    JRadioButton rbNgay = new JRadioButton("Theo Ngày");
    JRadioButton rbGio = new JRadioButton("Theo Giờ");
    JDateChooser dateNgayThue = new JDateChooser();
    TimeChoose timeNgayThue = new TimeChoose();
    JDateChooser dateNgayTra = new JDateChooser();
    TimeChoose timeNgayTra = new TimeChoose();
    ButtonGroup bgHT = new ButtonGroup();

    JPanel pnP_DV = new JPanel();
    JPanel pnP_DVTop = new JPanel();
    JPanel pnP_DVBottom = new JPanel();

    JPanel pnPhong = new JPanel();
    JLabel lbPhong = new JLabel("Danh sách phòng thuê");
    JScrollPane scpP = new JScrollPane();
    JTable tbP = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    JLabel lbDV = new JLabel("Danh sách dịch vụ thuê");

    JPanel pnDV = new JPanel();
    JScrollPane scpDV = new JScrollPane();
    JTable tbDV = new JTable();

    JPanel pnDVS = new JPanel();
    JScrollPane scpDVS = new JScrollPane();
    JTable tbDVS = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    JPanel pnTop = new JPanel();
    JButton btnTop = new JButton("Chọn dịch vụ");

    JPanel pnChange = new JPanel();

    ArrayList<ThuePhongDTO> listThuePhong = new ArrayList<>();
    ArrayList<PhongDTO> listPhong = new ArrayList<>();
    ArrayList<DichVuDTO> listDV = new ArrayList<>();
    ArrayList<SuDungDichVuDTO> listSDDV = new ArrayList<>();

    public void initComponents(boolean New) {
        listPhong.clear();
        listThuePhong.clear();
        setBackground(Color.decode("#CCCCCC"));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(5, 5));
        if (New) {
            add(pnKH, BorderLayout.WEST);
            pnKH.setLayout(new BorderLayout());
            pnKH.add(lbTitle, BorderLayout.NORTH);
            pnKH.add(pnCenterKH, BorderLayout.CENTER);
            pnKH.add(pnBtn, BorderLayout.SOUTH);
            pnKH.setBackground(Color.white);

            lbTitle.setFont(sgUI15);
            lbTitle.setBorder(new EmptyBorder(5, 5, 5, 5));

            pnCenterKH.setBackground(Color.decode("#F5F5F5"));
            pnCenterKH.setLayout(new GridLayout(10, 1));
            pnCenterKH.add(lbMaKH);
            pnCenterKH.add(txtMaKH);
            pnCenterKH.add(lbTenKH);
            pnCenterKH.add(txtTenKH);
            pnCenterKH.add(lbCMNDKH);
            pnCenterKH.add(txtCMNDKH);
            pnCenterKH.add(lbSDTKH);
            pnCenterKH.add(txtSDTKH);
            pnCenterKH.add(lbGTKH);
            pnCenterKH.add(pnGioiTinh);
            pnGioiTinh.setLayout(new GridLayout(1, 2));
            pnGioiTinh.add(rbNam);
            pnGioiTinh.add(rbNu);
            rbNam.setBackground(Color.decode("#F5F5F5"));
            rbNu.setBackground(Color.decode("#F5F5F5"));
            rbNam.setFocusPainted(false);
            rbNam.setBorderPainted(false);
            rbNu.setFocusPainted(false);
            rbNu.setBorderPainted(false);
            pnGioiTinh.setBackground(Color.decode("#F5F5F5"));

            txtMaKH.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(5, 0, 5, 0, Color.decode("#F5F5F5")), new EmptyBorder(0, 10, 0, 10)));
            txtTenKH.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(5, 0, 5, 0, Color.decode("#F5F5F5")), new EmptyBorder(0, 10, 0, 10)));
            txtCMNDKH.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(5, 0, 5, 0, Color.decode("#F5F5F5")), new EmptyBorder(0, 10, 0, 10)));
            txtSDTKH.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(5, 0, 5, 0, Color.decode("#F5F5F5")), new EmptyBorder(0, 10, 0, 10)));

            pnCenterKH.setBorder(new EmptyBorder(30, 10, 30, 10));

            txtCMNDKH.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    ArrayList<KhachHangDTO> listKHSearch = new ArrayList<>();
                    listKHSearch = KhachHangBUS.searchSDT(txtCMNDKH.getText().trim());
                    if (listKHSearch.size() != 0) {
                        new DanhSachKHLeTan(listKHSearch, DatPhongLeTan.this);
                    }
                }
            });

            pnBtn.setBorder(new EmptyBorder(10, 10, 10, 10));
            pnBtn.setBackground(Color.decode("#F5F5F5"));
            rbNam.setSelected(true);
            bgGT.add(rbNam);
            bgGT.add(rbNu);

            pnBtn.setLayout(new BorderLayout());
            pnBtn.add(btnSave, BorderLayout.EAST);
            btnSave.setFocusPainted(false);
            btnSave.setBackground(Color.decode("#2E8B57"));
            btnSave.setForeground(Color.white);
            int countKH = 1;
            try {
                countKH += new KhachHangBUS().LoadKH().size();
            } catch (Exception ex) {
            }
            int count = 1000 + countKH;
            txtMaKH.setText("KH" + count);
            txtMaKH.setEditable(false);
        }
        txtmactt.setEditable(false);
        txtmakh.setEditable(false);
        txttenkh.setEditable(false);
        txtmanv.setEditable(false);
        txttennv.setEditable(false);
        add(pnCenter, BorderLayout.CENTER);
        pnCenter.setLayout(new BorderLayout());
        pnCenter.add(lbCenter, BorderLayout.NORTH);
        pnCenter.add(pnContent, BorderLayout.CENTER);
        pnCenter.setBackground(Color.white);

        lbCenter.setFont(sgUI15);
        lbCenter.setHorizontalAlignment(JLabel.CENTER);
        lbCenter.setBorder(new EmptyBorder(5, 5, 5, 5));

        pnBtnCenter.setLayout(new BorderLayout());
        pnBtnCenter.add(btnXuLy, BorderLayout.EAST);
        pnBtnCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
        btnXuLy.setFocusPainted(false);
        btnXuLy.setBackground(Color.decode("#2E8B57"));
        btnXuLy.setForeground(Color.white);

        pnContent.setBackground(Color.decode("#F5F5F5"));
        pnContent.setLayout(new BorderLayout());
        pnContent.add(pnCenterTop, BorderLayout.NORTH);
        pnContent.add(pnCenterBottom, BorderLayout.CENTER);

        pnCenterTop.setLayout(new GridLayout(1, 5, 5, 5));
        pnCenterTop.add(pnctt);
        pnCenterTop.add(pnmakh);
        pnCenterTop.add(pntenkh);
        pnCenterTop.add(pnmanv);
        pnCenterTop.add(pntennv);

        pnctt.setLayout(new BorderLayout());
        pnmakh.setLayout(new BorderLayout());
        pntenkh.setLayout(new BorderLayout());
        pnmanv.setLayout(new BorderLayout());
        pntennv.setLayout(new BorderLayout());

        pnctt.add(lbmactt, BorderLayout.WEST);
        pnmakh.add(lbmakh, BorderLayout.WEST);
        pntenkh.add(lbtenkh, BorderLayout.WEST);
        pnmanv.add(lbmanv, BorderLayout.WEST);
        pntennv.add(lbtennv, BorderLayout.WEST);

        pnctt.add(txtmactt, BorderLayout.CENTER);
        pnmakh.add(txtmakh, BorderLayout.CENTER);
        pntenkh.add(txttenkh, BorderLayout.CENTER);
        pnmanv.add(txtmanv, BorderLayout.CENTER);
        pntennv.add(txttennv, BorderLayout.CENTER);

        pnCenterTop.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbmactt.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbmakh.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbtenkh.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbmanv.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbtennv.setBorder(new EmptyBorder(5, 5, 5, 5));

        txtmactt.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 0, 0, Color.decode("#FFFFFF")), new EmptyBorder(0, 10, 0, 10)));
        txtmakh.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 0, 0, Color.decode("#FFFFFF")), new EmptyBorder(0, 10, 0, 10)));
        txttenkh.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 0, 0, Color.decode("#FFFFFF")), new EmptyBorder(0, 10, 0, 10)));
        txtmanv.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 0, 0, Color.decode("#FFFFFF")), new EmptyBorder(0, 10, 0, 10)));
        txttennv.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 0, 0, Color.decode("#FFFFFF")), new EmptyBorder(0, 10, 0, 10)));

        pnCenterBottom.setLayout(new BorderLayout(5, 5));
        pnCenterBottom.add(pnLeft, BorderLayout.WEST);
        pnCenterBottom.setBackground(Color.decode("#CCCCCC"));
        pnCenterBottom.setBorder(new EmptyBorder(5, 0, 0, 0));
        pnLeft.setLayout(new BorderLayout());
        pnLeft.add(lbLeft, BorderLayout.NORTH);
        pnLeft.add(pnLeftBottom, BorderLayout.CENTER);
        pnLeft.add(pnBtnLeft, BorderLayout.SOUTH);
        pnLeft.setBackground(Color.white);
        pnBtnLeft.setBackground(Color.decode("#F5F5F5"));

        pnBtnLeft.setLayout(new BorderLayout());
        pnBtnLeft.add(btnSelect, BorderLayout.EAST);
        pnBtnLeft.setBorder(new EmptyBorder(10, 10, 10, 10));
        btnSelect.setFocusPainted(false);
        btnSelect.setBackground(Color.decode("#2E8B57"));
        btnSelect.setForeground(Color.white);

        lbLeft.setFont(sgUI15);
        lbLeft.setBorder(new EmptyBorder(5, 5, 5, 5));

        pnLeftBottom.setLayout(new GridLayout(10, 1));
        pnLeftBottom.setBackground(Color.decode("#F5F5F5"));
        pnLeftBottom.add(lbHinhThucThue);
        pnLeftBottom.add(pnHinhThueThue);
        pnLeftBottom.add(lbNgayThue);
        pnLeftBottom.add(dateNgayThue);
        pnLeftBottom.add(lbGioThue);
        pnLeftBottom.add(timeNgayThue);
        pnLeftBottom.add(lbNgayTra);
        pnLeftBottom.add(dateNgayTra);
        pnLeftBottom.add(lbGioTra);
        pnLeftBottom.add(timeNgayTra);
        pnLeftBottom.setBorder(new EmptyBorder(0, 5, 80, 5));

        rbNgay.setSelected(true);
        rbNgay.setBackground(Color.decode("#F5F5F5"));
        rbGio.setBackground(Color.decode("#F5F5F5"));

        pnHinhThueThue.setLayout(new GridLayout(1, 2));
        pnHinhThueThue.add(rbNgay);
        pnHinhThueThue.add(rbGio);

        bgHT.add(rbNgay);
        bgHT.add(rbGio);

        rbNgay.setFocusPainted(false);
        rbGio.setFocusPainted(false);

        pnCenterBottom.add(pnRight, BorderLayout.CENTER);
        pnRight.setLayout(new BorderLayout(5, 5));
        pnRight.setBackground(Color.decode("#CCCCCC"));
        pnRight.add(pnBtnCenter, BorderLayout.SOUTH);
        pnBtnCenter.setBackground(Color.decode("#F5F5F5"));

        pnRight.add(pnP_DV, BorderLayout.CENTER);
        pnP_DV.setLayout(new GridLayout(2, 1));
        pnP_DV.add(pnP_DVTop);
        pnP_DV.add(pnP_DVBottom);
        pnP_DVTop.setLayout(new BorderLayout());
        pnP_DVTop.setBackground(Color.white);
        pnP_DVTop.add(lbPhong, BorderLayout.NORTH);
        pnP_DVTop.add(scpP, BorderLayout.CENTER);
        lbPhong.setFont(sgUI15);
        lbPhong.setBorder(new EmptyBorder(5, 5, 5, 5));
        scpP.setViewportView(tbP);
        scpP.getViewport().setBackground(Color.decode("#F5F5F5"));
        scpP.setBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#F5F5F5")));
        renderP(tbP);

        pnP_DVBottom.setLayout(new BorderLayout());
        pnP_DVBottom.setBackground(Color.white);
        pnP_DVBottom.add(lbDV, BorderLayout.NORTH);
        pnP_DVBottom.add(pnDV, BorderLayout.CENTER);

        lbDV.setFont(sgUI15);
        lbDV.setBorder(new EmptyBorder(5, 5, 5, 5));

        pnDV.setLayout(new BorderLayout());
        pnDV.setBackground(Color.decode("#F5F5F5"));
        pnDV.add(scpDV, BorderLayout.CENTER);
        scpDV.getViewport().setBackground(Color.decode("#F5F5F5"));
        scpDV.setBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#F5F5F5")));
        scpDV.setViewportView(tbDV);
        renderDV(tbDV);
        pnP_DVBottom.add(pnTop, BorderLayout.EAST);
        pnTop.setBackground(Color.decode("#F5F5F5"));
        pnTop.setLayout(new BorderLayout());
        pnTop.add(btnTop, BorderLayout.NORTH);
        btnTop.setFocusPainted(false);
        btnTop.setBackground(Color.decode("#2E8B57"));
        btnTop.setForeground(Color.white);
        btnTop.setFont(sgUI15p);
        btnTop.setEnabled(false);
        pnRight.setBackground(Color.decode("#CCCCCC"));

        if (!New) {
            pnRight.add(pnChange, BorderLayout.EAST);
            renderDSP(New);
            btnTop.setEnabled(true);
        } else {
            btnTop.setEnabled(false);
        }

        btnTop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PanelSelectDVLeTan(DatPhongLeTan.this);
            }
        });

        btnXuLy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLy(New);
            }
        });

        btnXuLy.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    xuLy(New);
                }
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtTenKH.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khách hàng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    txtTenKH.requestFocus();
                } else {
                    if (txtCMNDKH.getText().trim().length() > 10) {
                        JOptionPane.showMessageDialog(null, "Chứng minh nhân dân phải dưới 10 kí tự", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        txtCMNDKH.setText("");
                        txtCMNDKH.requestFocus();
                    } else if (txtCMNDKH.getText().trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập chứng minh nhân dân", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        txtCMNDKH.requestFocus();
                    } else {
                        if (txtSDTKH.getText().trim().length() == 0) {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            txtSDTKH.requestFocus();
                        } else if (txtSDTKH.getText().trim().length() != 10) {
                            JOptionPane.showMessageDialog(null, "Nhập số điện thoại sai", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            txtSDTKH.setText("");
                            txtSDTKH.requestFocus();
                        } else {
                            int countKH = 1;
                            try {
                                countKH += new KhachHangBUS().LoadKH().size();
                            } catch (Exception ex) {
                            }
                            int count = 1000 + countKH;
                            txtMaKH.setText("KH" + count);
                            txtMaKH.setEditable(false);
                            String gioitinh = "";
                            if (rbNam.isSelected()) {
                                gioitinh = "Nam";
                            } else {
                                gioitinh = "Nữ";
                            }
                            KhachHangDTO x = new KhachHangDTO(txtMaKH.getText(), txtTenKH.getText().trim(), txtCMNDKH.getText().trim(), gioitinh, txtSDTKH.getText().trim(), 0);
                            try {
                                new KhachHangBUS().InsertKH(x);
                                JOptionPane.showMessageDialog(null, "Thêm khách hàng mới thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                                txtmactt.setText("CTT" + ChiTietThueBUS.getSize());
                                txtmakh.setText(txtMaKH.getText());
                                txttenkh.setText(txtTenKH.getText());
                                remove(pnKH);
                                revalidate();
                                pnRight.add(pnChange, BorderLayout.EAST);
                                renderDSP(New);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Thêm khách hàng mới thất bại\nVui lòng thêm lại", "Thất bại", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                }
            }
        });
        /////////////////@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        txtmanv.setText(mainGUI.taiKhoan);
        txttennv.setText(mainGUI.nameNV);
        /////////////////@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        timeNgayTra.setEnable(false);
        rbNgay.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (rbNgay.isSelected()) {
                    timeNgayTra.setEnable(false);
                } else {
                    timeNgayTra.setEnable(true);
                }
            }
        });

        tbP.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                    int ans = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa phòng thuê này?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    if (ans == JOptionPane.YES_OPTION) {
                        if (listThuePhong.get(tbP.getSelectedRow()).getTinhTrang() == 1 || listThuePhong.get(tbP.getSelectedRow()).getTinhTrang() == 2) {
                            JOptionPane.showMessageDialog(null, "Không thể xóa phòng thuê này", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            try {
                                listPhong.remove(tbP.getSelectedRow());
                                listThuePhong.remove(tbP.getSelectedRow());
                                JOptionPane.showMessageDialog(null, "Xóa thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                                renderP(tbP);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Xóa không thành công", "Thất bại", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                }
            }
        });

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2
//        tbDV.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
//                    int ans = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa dịch vụ thuê này?", "Thông báo", JOptionPane.YES_NO_OPTION);
//                    if (ans == JOptionPane.YES_OPTION) {
//                        if (listThuePhong.get(tbP.getSelectedRow()).getTinhTrang() == 1 || listThuePhong.get(tbP.getSelectedRow()).getTinhTrang() == 2) {
//                            JOptionPane.showMessageDialog(null, "Không thể xóa phòng thuê này", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                        } else {
//                            try {
//                                listPhong.remove(tbP.getSelectedRow());
//                                listThuePhong.remove(tbP.getSelectedRow());
//                                JOptionPane.showMessageDialog(null, "Xóa thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
//                                renderP(tbP);
//                            } catch (Exception ex) {
//                                JOptionPane.showMessageDialog(null, "Xóa không thành công", "Thất bại", JOptionPane.INFORMATION_MESSAGE);
//                            }
//                        }
//                    }
//                }
//            }
//        });
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dateNgayThue.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày thuê", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (dateNgayTra.getDate() == null) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày trả");
                    } else {
                        if (rbNgay.isSelected()) {
                            timeNgayTra.setHour(timeNgayThue.getHour());
                            timeNgayTra.setMinute(timeNgayThue.getMinute());
                            int hour = 0;
                            if (timeNgayThue.getHour() == 24) {
                                hour = 0;
                            } else {
                                hour = timeNgayThue.getHour();
                            }
                            int hourTra = 0;
                            if (timeNgayTra.getHour() == 24) {
                                hourTra = 0;
                            } else {
                                hourTra = timeNgayTra.getHour();
                            }
                            LocalDate ThueDate = LocalDate.of(dateNgayThue.getDate().getYear() + 1900, dateNgayThue.getDate().getMonth() + 1, dateNgayThue.getDate().getDate());
                            LocalTime ThueTime = LocalTime.of(hour, timeNgayThue.getMinute(), 0);
                            LocalDateTime ldtThue = LocalDateTime.of(ThueDate, ThueTime);

                            LocalDate TraDate = LocalDate.of(dateNgayTra.getDate().getYear() + 1900, dateNgayTra.getDate().getMonth() + 1, dateNgayTra.getDate().getDate());
                            LocalTime TraTime = LocalTime.of(hourTra, timeNgayTra.getMinute(), 0);
                            LocalDateTime ldtTra = LocalDateTime.of(TraDate, TraTime);

                            LocalDateTime ldtNow = LocalDateTime.now();
                            if (ldtThue.isAfter(ldtNow)) {
                                if (ldtTra.isAfter(ldtThue)) {
                                    //2023-04-30T20:00
                                    long dayCount = ChronoUnit.DAYS.between(ldtThue, ldtTra);
//                                    listPhong.clear();
                                    String dateThue = ldtThue.toString().replace("T", " ");
                                    dateThue += ":00";

                                    String dateTra = ldtTra.toString().replace("T", " ");
                                    dateTra += ":00";
//                                    for (PhongDTO x : PhongBUS.getListPhong(dateThue, dateTra)) {
//                                        listPhong.add(x);
//                                    }
//                                    renderPhong(tbPhong, listPhong);
                                    ThuePhongDTO x = new ThuePhongDTO();
                                    x.setMaChiTietThue(txtmactt.getText());
                                    x.setLoaiHinhThue(rbNgay.getText());
                                    x.setNgayThue(dateThue);
                                    x.setNgayTra(dateTra);
                                    x.setNgayCheckOut(dateTra);
                                    x.setXuLy(0);
                                    x.setTinhTrang(0);
                                    listThuePhong.add(x);
                                    PanelSelectPhongLeTan slPhong = new PanelSelectPhongLeTan(x, listPhong, DatPhongLeTan.this, (int) dayCount, 0, tbP);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Ngày, giờ trả phải lớn hơn ngày, giờ thuê");
                                    dateNgayTra.setCalendar(null);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Ngày, giờ thuê phải lớn hơn ngày, giờ hiện tại");
                                dateNgayThue.setCalendar(null);
                            }
                        } else {
                            timeNgayTra.setEnable(true);
                            LocalDate ThueDate = LocalDate.of(dateNgayThue.getDate().getYear() + 1900, dateNgayThue.getDate().getMonth() + 1, dateNgayThue.getDate().getDate());
                            int hour = 0;
                            if (timeNgayThue.getHour() == 24) {
                                hour = 0;
                            } else {
                                hour = timeNgayThue.getHour();
                            }
                            LocalTime ThueTime = LocalTime.of(hour, timeNgayThue.getMinute(), 0);
                            LocalDateTime ldtThue = LocalDateTime.of(ThueDate, ThueTime);

                            int hourTra = 0;
                            if (timeNgayTra.getHour() == 24) {
                                hourTra = 0;
                            } else {
                                hourTra = timeNgayTra.getHour();
                            }
                            LocalDate TraDate = LocalDate.of(dateNgayTra.getDate().getYear() + 1900, dateNgayTra.getDate().getMonth() + 1, dateNgayTra.getDate().getDate());
                            LocalTime TraTime = LocalTime.of(hourTra, timeNgayTra.getMinute(), 0);
                            LocalDateTime ldtTra = LocalDateTime.of(TraDate, TraTime);

                            LocalDateTime ldtNow = LocalDateTime.now();
                            if (ldtThue.isAfter(ldtNow)) {
                                if (ldtTra.isAfter(ldtThue)) {
                                    long hourCount = ChronoUnit.HOURS.between(ldtThue, ldtTra);
                                    long dayCount = ChronoUnit.DAYS.between(ldtThue, ldtTra);
                                    if (hourCount == 0) {
                                        JOptionPane.showMessageDialog(null, "Phải thuê ít nhất 1h");
                                    } else {
//                                        listPhong.clear();
                                        String dateThue = ldtThue.toString().replace("T", " ");
                                        dateThue += ":00";
                                        String dateTra = ldtTra.toString().replace("T", " ");
                                        dateTra += ":00";
//                                        for (PhongDTO x : PhongBUS.getListPhong(dateThue, dateTra)) {
//                                            listPhong.add(x);
//                                        }
//                                        renderPhong(tbPhong, listPhong);
                                        ThuePhongDTO x = new ThuePhongDTO();
                                        x.setMaChiTietThue(txtmactt.getText());
                                        x.setLoaiHinhThue(rbGio.getText());
                                        x.setNgayThue(dateThue);
                                        x.setNgayTra(dateTra);
                                        x.setNgayCheckOut(dateTra);
                                        x.setXuLy(0);
                                        x.setTinhTrang(0);
                                        listThuePhong.add(x);
                                        PanelSelectPhongLeTan slPhong = new PanelSelectPhongLeTan(x, listPhong, DatPhongLeTan.this, (int) dayCount, (int) hourCount, tbP);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Ngày, giờ trả phải lớn hơn ngày, giờ thuê");
                                    dateNgayTra.setCalendar(null);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Ngày, giờ thuê phải lớn hơn ngày, giờ hiện tại");
                                dateNgayThue.setCalendar(null);
                            }
                        }
                    }
                }
//                if (rbNgay.isSelected()) {
//                    if (dateNgayThue.getDate() == null) {
//                        JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày thuê", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                    } else {
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//                        Calendar now = Calendar.getInstance();
//                        String Now_str = sdf.format(now.getTime());
//                        try {
//                            Date dateNow = sdf.parse(Now_str);
//                            Calendar cdThue = Calendar.getInstance();
//                            cdThue.setTime(dateNgayThue.getDate());
//                            cdThue.set(Calendar.HOUR, timeNgayThue.getHour());
//                            cdThue.set(Calendar.MINUTE, timeNgayThue.getMinute());
//                            cdThue.set(Calendar.SECOND, 0);
//                            String Thue_str = sdf.format(cdThue.getTime());
//                            Date Thue_date = sdf.parse(Thue_str);
//                            if (Thue_date.after(dateNow)) {
//                                if (dateNgayTra.getDate() == null) {
//                                    timeNgayTra.setHour(timeNgayThue.getHour());
//                                    timeNgayTra.setMinute(timeNgayThue.getMinute());
//                                    JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày trả", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                                } else {
//                                    Calendar cdTra = Calendar.getInstance();
//                                    cdTra.setTime(dateNgayTra.getDate());
//                                    cdTra.set(Calendar.HOUR, timeNgayThue.getHour());
//                                    cdTra.set(Calendar.MINUTE, timeNgayThue.getMinute());
//                                    cdTra.set(Calendar.SECOND, 0);
//                                    String Tra_str = sdf.format(cdTra.getTime());
//                                    Date Tra_date = sdf.parse(Tra_str);
//                                    long time = dateNgayTra.getDate().getTime() - dateNgayThue.getDate().getTime();
//                                    int dayCount = (int) (time / (1000 * 60 * 60 * 24));
//                                    if (Tra_date.after(Thue_date)) {
//                                        ThuePhongDTO x = new ThuePhongDTO();
//                                        x.setMaChiTietThue(txtmactt.getText());
//                                        x.setLoaiHinhThue(rbNgay.getText());
//                                        x.setNgayThue(Thue_str);
//                                        x.setNgayTra(Tra_str);
//                                        x.setNgayCheckOut(Tra_str);
//                                        x.setXuLy(0);
//                                        x.setTinhTrang(0);
//                                        listThuePhong.add(x);
//                                        PanelSelectPhongLeTan slPhong = new PanelSelectPhongLeTan(x, listPhong, DatPhongLeTan.this, dayCount, 0, tbP);
//                                    } else {
//                                        JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày trả lớn hơn ngày thuê");
//                                    }
//                                }
//                            } else {
//                                JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày, giờ thuê lớn hơn ngày, giờ hiện tại");
//                            }
//                        } catch (ParseException ex) {
//                        }
//                    }
//                } else {
//                    if (dateNgayThue.getDate() == null) {
//                        JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày thuê", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                    } else {
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//                        Calendar now = Calendar.getInstance();
//                        String Now_str = sdf.format(now.getTime());
//                        try {
//                            Date dateNow = sdf.parse(Now_str);
//                            Calendar cdThue = Calendar.getInstance();
//                            cdThue.setTime(dateNgayThue.getDate());
//                            cdThue.set(Calendar.HOUR, timeNgayThue.getHour());
//                            cdThue.set(Calendar.MINUTE, timeNgayThue.getMinute());
//                            cdThue.set(Calendar.SECOND, 0);
//                            String Thue_str = sdf.format(cdThue.getTime());
//                            Date Thue_date = sdf.parse(Thue_str);
//                            if (Thue_date.after(dateNow)) {
//                                if (dateNgayTra.getDate() == null) {
//                                    JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày trả", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                                } else {
//                                    Calendar cdTra = Calendar.getInstance();
//                                    cdTra.setTime(dateNgayTra.getDate());
//                                    cdTra.set(Calendar.HOUR, timeNgayTra.getHour());
//                                    cdTra.set(Calendar.MINUTE, timeNgayTra.getMinute());
//                                    cdTra.set(Calendar.SECOND, 0);
//                                    String Tra_str = sdf.format(cdTra.getTime());
//                                    Date Tra_date = sdf.parse(Tra_str);
//                                    long date = dateNgayTra.getDate().getTime() - dateNgayThue.getDate().getTime();
//                                    int dayCount = (int) (date / (1000 * 60 * 60 * 24));
//                                    long time = Tra_date.getTime() - Thue_date.getTime();
//                                    int hourCount = (int) (time / (60 * 60 * 1000));
//                                    hourCount = hourCount - 24;
//                                    if (hourCount == 0) {
//                                        if (dayCount < 1) {
//                                            JOptionPane.showMessageDialog(null, "Vui lòng nhập giờ thuê ít nhất 1h", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                                        } else {
//                                            int ans = JOptionPane.showConfirmDialog(null, "Có lẽ bạn muốn thuê theo ngày", "Thông báo", JOptionPane.YES_NO_OPTION);
//                                            if (ans == JOptionPane.YES_OPTION) {
//                                                rbNgay.setSelected(true);
//                                            } else {
//                                                JOptionPane.showMessageDialog(null, "Vui lòng chọn giờ thuê ít nhất 1h", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                                            }
//                                        }
//                                    } else {
//                                        if (Tra_date.after(Thue_date)) {
//                                            ThuePhongDTO x = new ThuePhongDTO();
//                                            x.setMaChiTietThue(txtmactt.getText());
//                                            x.setLoaiHinhThue(rbGio.getText());
//                                            x.setNgayThue(Thue_str);
//                                            x.setNgayTra(Tra_str);
//                                            x.setNgayCheckOut(Tra_str);
//                                            x.setXuLy(0);
//                                            x.setTinhTrang(0);
//                                            listThuePhong.add(x);
//                                            PanelSelectPhongLeTan slPhong = new PanelSelectPhongLeTan(x, listPhong, DatPhongLeTan.this, dayCount, hourCount, tbP);
//                                        } else {
//                                            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày trả lớn hơn ngày thuê");
//                                        }
//                                    }
//                                }
//                            } else {
//                                JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày, giờ thuê lớn hơn ngày, giờ hiện tại");
//                            }
//                        } catch (ParseException ex) {
//                        }
//                    }
//                }
            }
        }
        );
    }

    JLabel topJLabel = new JLabel("          Thanh toán          ");
    JPanel centerJPanel = new JPanel();
    JLabel lbTienCoc = new JLabel("Tiền cọc");
    JLabel lbTongCong = new JLabel("Tổng cộng");
    JTextField txtTienCoc = new JTextField();
    JTextField txtTongCong = new JTextField();

    public void renderDSP(boolean New) {
        if (!New) {
            txtTienCoc.setEnabled(false);
        } else {
            txtTienCoc.setEnabled(true);
        }
        pnChange.removeAll();
        pnChange.repaint();
        pnChange.revalidate();
        pnChange.setLayout(new BorderLayout());
        pnChange.add(topJLabel, BorderLayout.NORTH);
        pnChange.add(centerJPanel, BorderLayout.CENTER);
        centerJPanel.setBackground(Color.decode("#F5F5F5"));
        topJLabel.setFont(sgUI15);
        topJLabel.setHorizontalAlignment(JLabel.CENTER);
        topJLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnChange.setBackground(Color.white);
        centerJPanel.setLayout(new GridLayout(11, 1));
        centerJPanel.add(lbTienCoc);
        centerJPanel.add(txtTienCoc);
        centerJPanel.add(lbTongCong);
        centerJPanel.add(txtTongCong);
        centerJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        txtTienCoc.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#F5F5F5")), new EmptyBorder(0, 5, 0, 5)));
        txtTongCong.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#F5F5F5")), new EmptyBorder(0, 5, 0, 5)));
        txtTongCong.setBackground(Color.white);
        txtTongCong.setEnabled(false);
    }

    public void xuLy(boolean New) {
        if (New) {
            if (listThuePhong.size() != 0) {
                try {
                    int datcoc = Integer.parseInt(txtTienCoc.getText());
                    if (datcoc < 0) {
                        JOptionPane.showMessageDialog(null, "Tiền cọc phải là số dương", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        ChiTietThueDTO ctt = new ChiTietThueDTO();
                        ctt.setMaChiTietThue(txtmactt.getText());
                        ctt.setMaKH(txtmakh.getText());
                        ctt.setMaNV(txtmanv.getText());
                        ctt.setTienDatCoc(datcoc);
                        ctt.setXuLy(0);
                        ctt.setTinhTrangXuLy(0);
                        if (ChiTietThueBUS.insertCTT(ctt)) {
                            int check = 0;
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = new Date();
                            String datestr = sdf.format(date);

                            for (ThuePhongDTO x : listThuePhong) {
                                SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd");
                                Date dateNow = new Date();
                                String datestring = sdff.format(date);
//                                if (x.getNgayThue().contains(datestring)) {
//                                    if (!PhongBUS.updateTT(x.getMaP(), "Đang được thuê")) {
//                                        JOptionPane.showMessageDialog(null, "Phòng không thể thuê");
//                                    } else {
//                                        x.setTinhTrang(1);
//                                    }
//                                }
                                if (!ThuePhongBUS.insertTP(x)) {
                                    check++;
                                }
                            }
                            if (check == 0) {
                                btnXuLy.setEnabled(false);
                                JOptionPane.showMessageDialog(null, "Thuê phòng thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Thuê phòng thất bại", "Thất bại", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Tiền cọc phải là số nguyên", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng thuê");
            }
        } else {

        }
    }

    public void renderDV(JTable tbDV) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Mã dịch vụ");
        dtm.addColumn("Tên dịch vụ");
        dtm.addColumn("Ngày sử dụng");
        dtm.addColumn("Số lượng");
        dtm.addColumn("Đơn giá");
        dtm.addColumn("Giá dịch vụ");
        tbDV.setModel(dtm);
        tbDV.setShowGrid(false);
        tbDV.setIntercellSpacing(new Dimension(0, 0));
        tbDV.setRowHeight(30);
        tbDV.getColumnModel().getColumn(0).setPreferredWidth(5);
        tbDV.getColumnModel().getColumn(1).setPreferredWidth(50);
        tbDV.getTableHeader().setPreferredSize(new Dimension(1, 32));
        tbDV.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tbDV.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        int i = 0;
        for (DichVuDTO x : listDV) {
            Object row[] = {i + 1, x.getMaDV(), x.getTenDV(), listSDDV.get(i).getNgaySuDungString(), listSDDV.get(i).getSoLuong(), x.getGiaDV(), listSDDV.get(i).getDonGia()};
            dtm.addRow(row);
            total += listSDDV.get(i).getDonGia();
            txtTongCong.setText("" + total);
            i++;
        }
        DefaultTableCellRenderer renderBorder = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                JLabel lb = (JLabel) c;

                lb.setHorizontalAlignment(JLabel.CENTER);
                if (isSelected) {
                    lb.setBackground(Color.decode("#F5F5F5"));
                } else {
                    lb.setBackground(Color.decode("#FFFFFF"));
                }
                if (column == tbDV.getColumnModel().getColumnIndex("STT")) {
                    lb.setBorder(new MatteBorder(0, 0, 1, 1, Color.decode("#99FF99")));
                    lb.setBackground(Color.decode("#99FF99"));
                } else {
                    lb.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode("#DDDDDD")));
                }
                return lb;
            }
        };
        for (int j = 0; j < tbDV.getColumnCount(); j++) {
            tbDV.getColumnModel().getColumn(j).setCellRenderer(renderBorder);
        }
    }

    public void renderP(JTable tbP) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Mã phòng");
        dtm.addColumn("Tên phòng");
        dtm.addColumn("Tình trạng");
        dtm.addColumn("Loại hình thuê");
        dtm.addColumn("Ngày thuê");
        dtm.addColumn("Ngày trả");
        dtm.addColumn("Giá phòng");
        dtm.addColumn("Giá thực");
        tbP.setModel(dtm);
        tbP.setShowGrid(false);
        tbP.setIntercellSpacing(new Dimension(0, 0));
        tbP.setRowHeight(30);
        tbP.getColumnModel().getColumn(0).setPreferredWidth(5);
        tbP.getColumnModel().getColumn(1).setPreferredWidth(50);
        tbP.getTableHeader().setPreferredSize(new Dimension(1, 30));
        tbP.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tbP.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        int i = 0;
        for (PhongDTO x : listPhong) {
            String tinhTrang = "";
            if (listThuePhong.get(i).getTinhTrang() == 0) {
                tinhTrang = "Đang xử lý";
            } else if (listThuePhong.get(i).getTinhTrang() == 1) {
                tinhTrang = "Đang được thuê";
            } else {
                tinhTrang = "Đã trả phòng";
            }
            Object row[] = {i + 1, x.getMaP(), x.getTenP(), tinhTrang, listThuePhong.get(i).getLoaiHinhThue(), listThuePhong.get(i).getNgayThue(), listThuePhong.get(i).getNgayTra(), x.getGiaP(), listThuePhong.get(i).getGia()};
            dtm.addRow(row);
            i++;
        }
        for (ThuePhongDTO x : listThuePhong) {
            total += x.getGia();
        }
        txtTongCong.setText(total + "");

        DefaultTableCellRenderer renderBorder = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                JLabel lb = (JLabel) c;

                lb.setHorizontalAlignment(JLabel.CENTER);
                if (isSelected) {
                    lb.setBackground(Color.decode("#F5F5F5"));
                } else {
                    lb.setBackground(Color.decode("#FFFFFF"));
                }
                if (column == tbP.getColumnModel().getColumnIndex("STT")) {
                    lb.setBorder(new MatteBorder(0, 0, 1, 1, Color.decode("#99FF99")));
                    lb.setBackground(Color.decode("#99FF99"));
                } else {
                    lb.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode("#DDDDDD")));
                }
                return lb;
            }
        };
        for (int j = 0; j < tbP.getColumnCount(); j++) {
            tbP.getColumnModel().getColumn(j).setCellRenderer(renderBorder);
        }
    }

    public void renderDVSearch(JTable tbDV) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Mã dịch vụ");
        dtm.addColumn("Tên dịch vụ");
        dtm.addColumn("Loại dịch vụ");
        dtm.addColumn("Giá");
        tbDV.setModel(dtm);
        tbDV.setShowGrid(false);
        tbDV.setIntercellSpacing(new Dimension(0, 0));
        tbDV.setRowHeight(30);
        tbDV.getColumnModel().getColumn(0).setPreferredWidth(5);
        tbDV.getColumnModel().getColumn(1).setPreferredWidth(50);
        tbDV.getTableHeader().setPreferredSize(new Dimension(1, 30));
        tbDV.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tbDV.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void setMaCCT(String maCTT) {
        txtmactt.setText(maCTT);
    }
}
