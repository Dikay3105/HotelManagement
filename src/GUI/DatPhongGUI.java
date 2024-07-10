package GUI;

import BUS.ChiTietThueBUS;
import BUS.KhachHangBUS;
import BUS.PhongBUS;
import BUS.SuDungDichVuBUS;
import BUS.ThuePhongBUS;
import DTO.ChiTietThueDTO;
import DTO.DichVuDTO;
import DTO.KhachHangDTO;
import DTO.PhongDTO;
import DTO.SuDungDichVuDTO;
import DTO.ThuePhongDTO;
import GUI.Phong.PanelSelectDichVu;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import GUI.Phong.PanelSelectPhong;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import javax.swing.table.DefaultTableCellRenderer;

public class DatPhongGUI extends JPanel {

    private JPanel pnRight = new JPanel();
    private JPanel pnRightTop = new JPanel();
    private JLabel lbRightTopLB = new JLabel("Thông tin khách hàng               ");
    private JPanel pnLBRightTop = new JPanel();
    private JPanel pnRigthTopCenter = new JPanel();
    private JPanel pnRightTopCenterPNSDTCenter = new JPanel();
    private JPanel pnRightTopCenterPNSDT = new JPanel();
    private JLabel lbRightTopCenterPNSDT = new JLabel("Tìm kiếm khách hàng");
    private JTextField txtRightTopCenterPNSDT = new JTextField("Nhập CMND");
    private JPanel pnRightTopCenterPNSDTBtn = new JPanel();
    private JPanel pnRightTopCenterPNSDTempty = new JPanel();
    private JPanel pnRightTopCenterPNSDTright = new JPanel();
    private JButton btnSearch = new JButton("Tìm kiếm");
    private JButton btnAdd = new JButton("Thêm mới");

    private JPanel pnCenter = new JPanel();
    private JLabel lbCenter = new JLabel("Phiếu đặt phòng");
    private JPanel pnLBcenter = new JPanel();
    private JPanel pnCenterContent = new JPanel();
    private JPanel pnCenterContentHead = new JPanel();
    private JPanel pnMaCT = new JPanel();
    private JLabel lbMaCT = new JLabel("Mã phiếu thuê:");
    JTextField txtMaCT = new JTextField();
    private JPanel pnMaKH = new JPanel();
    private JLabel lbMaKH = new JLabel("Mã khách hàng:");
    JTextField txtMaKH = new JTextField();
    private JPanel pnTenKH = new JPanel();
    private JLabel lbTenKH = new JLabel("Tên khách hàng:");
    JTextField txtTenKH = new JTextField();
    private JPanel pnMaNV = new JPanel();
    private JLabel lbMaNV = new JLabel("Mã nhân viên:");
    private JTextField txtMaNV = new JTextField();
    private JPanel pnTenNV = new JPanel();
    private JLabel lbTenNV = new JLabel("Tên nhân viên:");
    private JTextField txtTenNV = new JTextField();
    private JPanel pnTienCoc = new JPanel();
    private JLabel lbTienCoc = new JLabel("Tiền cọc:");
    private JTextField txtTienCoc = new JTextField();
    private JPanel pnCenterContentBody = new JPanel();
    private JPanel pnCenterContentBodyPhong = new JPanel();
    private JPanel pnInfo = new JPanel();
    private JPanel pnInfoCenter = new JPanel();
    private JPanel pnInfoCenterTop = new JPanel();

    private JPanel pnLoaiHinhThue = new JPanel();
    private JLabel lbLoaiHinhThue = new JLabel("Loại hình thuê:");
    private JPanel pnRButton = new JPanel();
    private ButtonGroup bg = new ButtonGroup();
    private JRadioButton rbNgay = new JRadioButton("Theo Ngày");
    private JRadioButton rbGio = new JRadioButton("Theo Giờ");

    private JPanel pnNgayThue = new JPanel();
    private JLabel lbNgayThue = new JLabel("Ngày thuê:");
    private JPanel pnNgayThueChoose = new JPanel();
    private JDateChooser dateNgayThue = new JDateChooser();

    private JPanel pnTimeNgayThue = new JPanel();
    private TimeChoose timeNgayThue = new TimeChoose();
    private JLabel lbTimeNgayThue = new JLabel("Giờ thuê:");

    private JPanel pnTimeNgayTra = new JPanel();
    private TimeChoose timeNgayTra = new TimeChoose();
    private JLabel lbTimeNgayTra = new JLabel("Giờ trả:");

    private JPanel pnNgayTra = new JPanel();
    private JLabel lbNgayTra = new JLabel("Ngày trả:");
    private JPanel pnNgayTraChoose = new JPanel();
    private JDateChooser dateNgayTra = new JDateChooser();

    private JPanel pnSelectPhong = new JPanel();
    private JButton btnSelectPhong = new JButton("Chọn phòng");
    static JTextField txtTotal = new JTextField("Tổng cộng: 0");

    private JPanel pnInfoCenterBottom = new JPanel();
    private JPanel pnTable = new JPanel();
    private JScrollPane scp = new JScrollPane();
    public static JTable tb = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JPanel pnCenterContentBodyBottom = new JPanel();
    private JPanel pnCenterContentBodyBottomDichVu = new JPanel();
    private JPanel pnCenterContentBodyBottomDichVuCenter = new JPanel();
    private JScrollPane scpDV = new JScrollPane();
    private JPanel pnXuLy = new JPanel();
    private JLabel lbXuLy = new JLabel("Thêm dịch vụ                 ");
    private JPanel pnAddDV = new JPanel();
    private JPanel pnAddDVbtn = new JPanel();
    private JButton btnAddDV = new JButton("Chọn dịch vụ");
    public static JTable tbDV = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private JPanel pnCenterContentBodyBottomBtn = new JPanel();
    private JPanel pnCenterContentBodyBottomEmpty = new JPanel();
    private JPanel pnCenterContentBodyBottomRight = new JPanel();
    JButton btnXuLy = new JButton("Lưu");

    private TitledBorder ttb = new TitledBorder("Thông tin thuê phòng");
    private TitledBorder ttbTI = new TitledBorder("Thông tin tiện ích");

    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);

    private JScrollPane scpKH = new JScrollPane();
    private JTable tbKH = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public static ArrayList<ThuePhongDTO> listTP = new ArrayList<>();

    public static ArrayList<PhongDTO> listP = new ArrayList<>();

    public static ArrayList<SuDungDichVuDTO> listSDDV = new ArrayList<>();

    public static ArrayList<DichVuDTO> listDV = new ArrayList<>();

    ArrayList<KhachHangDTO> listKHSearch;

    public DatPhongGUI() {
        listTP.clear();
        listP.clear();
        listDV.clear();
        listSDDV.clear();
        initComponents();
    }

    public void initComponents() {
        UIManager.put("TextField.inactiveBackground", new ColorUIResource(new Color(255, 255, 255)));
        setLayout(new BorderLayout());
        add(pnRight, BorderLayout.WEST);
        add(pnCenter, BorderLayout.CENTER);

        pnRight.setLayout(new BorderLayout());
        pnRight.add(pnRightTop, BorderLayout.CENTER);

        pnRightTop.setLayout(new BorderLayout());
        pnRightTop.add(pnLBRightTop, BorderLayout.NORTH);
        pnRightTop.add(pnRightTopCenterPNSDTCenter, BorderLayout.CENTER);
        pnLBRightTop.setLayout(new BorderLayout());
        pnLBRightTop.add(lbRightTopLB, BorderLayout.CENTER);
        lbRightTopLB.setFont(sgUI15);
        lbRightTopLB.setBorder(new EmptyBorder(5, 5, 5, 5));

        pnRightTopCenterPNSDTCenter.setBorder(new EmptyBorder(5, 5, 5, 0));
        pnRightTopCenterPNSDTCenter.setLayout(new GridLayout(4, 1));
        pnRightTopCenterPNSDTCenter.add(pnRightTopCenterPNSDT);
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                render();
            }
        });

        pnRightTopCenterPNSDT.setLayout(new GridLayout(3, 1, 5, 5));
        pnRightTopCenterPNSDT.add(lbRightTopCenterPNSDT);
        pnRightTopCenterPNSDT.add(txtRightTopCenterPNSDT);
        pnRightTopCenterPNSDT.add(pnRightTopCenterPNSDTBtn);

        txtRightTopCenterPNSDT.setMargin(new Insets(0, 5, 0, 5));
        txtRightTopCenterPNSDT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtRightTopCenterPNSDT.getText().equals("Nhập CMND")) {
                    txtRightTopCenterPNSDT.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtRightTopCenterPNSDT.getText().trim().length() == 0) {
                    txtRightTopCenterPNSDT.setText("Nhập CMND");
                }
            }
        });
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtRightTopCenterPNSDT.getText().equals("Nhập CMND")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập CMND cần tìm");
                    txtRightTopCenterPNSDT.setText("");
                    txtRightTopCenterPNSDT.requestFocus();
                } else {
                    listKHSearch = new ArrayList<>();
                    listKHSearch = KhachHangBUS.searchSDT(txtRightTopCenterPNSDT.getText());
                    if (listKHSearch.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không có khách hàng dùng CMND này", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        pnRightTopCenterPNSDTright.removeAll();
                        pnRightTopCenterPNSDTright.revalidate();
                        pnRightTopCenterPNSDTright.repaint();
                        pnRightTopCenterPNSDTright.setLayout(new GridLayout(1, 2, 5, 5));
                        pnRightTopCenterPNSDTright.add(btnSearch);
                        pnRightTopCenterPNSDTright.add(btnAdd);
                    } else {
                        DanhSachKH dsKH = new DanhSachKH(listKHSearch, DatPhongGUI.this);
                        dsKH.setVisible(true);
                    }
                }
            }
        });

        btnAddDV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PanelSelectDichVu();
            }
        });

        pnRightTopCenterPNSDTBtn.setLayout(new BorderLayout());
        pnRightTopCenterPNSDTBtn.add(pnRightTopCenterPNSDTempty, BorderLayout.CENTER);
        pnRightTopCenterPNSDTBtn.add(pnRightTopCenterPNSDTright, BorderLayout.EAST);

        pnRightTopCenterPNSDTright.setLayout(new BorderLayout());
        pnRightTopCenterPNSDTright.add(btnSearch, BorderLayout.CENTER);
        ImageIcon iconSearch = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/IconFind.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
        btnSearch.setIcon(iconSearch);
        btnSearch.setFocusPainted(false);

        pnCenter.setLayout(new BorderLayout());
        pnCenter.add(pnLBcenter, BorderLayout.NORTH);
        pnCenter.add(pnCenterContent, BorderLayout.CENTER);
        pnLBcenter.setLayout(new BorderLayout());
        pnLBcenter.add(lbCenter, BorderLayout.CENTER);

        lbCenter.setFont(sgUI15);
        lbCenter.setHorizontalAlignment(JLabel.CENTER);
        lbCenter.setBorder(new EmptyBorder(5, 5, 5, 0));

        pnCenterContent.setLayout(new BorderLayout());

        pnCenterContent.add(pnCenterContentHead, BorderLayout.NORTH);
        pnCenterContent.add(pnCenterContentBody, BorderLayout.CENTER);

        pnCenterContentHead.setLayout(new GridLayout(2, 3, 5, 5));
        pnCenterContentHead.add(pnMaCT);
        pnCenterContentHead.add(pnMaKH);
        pnCenterContentHead.add(pnTenKH);
        pnCenterContentHead.add(pnMaNV);
        pnCenterContentHead.add(pnTenNV);
        pnCenterContentHead.add(pnTienCoc);

        pnMaCT.setLayout(new BorderLayout());
        pnMaCT.add(lbMaCT, BorderLayout.WEST);
        pnMaCT.add(txtMaCT, BorderLayout.CENTER);

        pnMaKH.setLayout(new BorderLayout());
        pnMaKH.add(lbMaKH, BorderLayout.WEST);
        pnMaKH.add(txtMaKH, BorderLayout.CENTER);

        pnTenKH.setLayout(new BorderLayout());
        pnTenKH.add(lbTenKH, BorderLayout.WEST);
        pnTenKH.add(txtTenKH, BorderLayout.CENTER);

        pnMaNV.setLayout(new BorderLayout());
        pnMaNV.add(lbMaNV, BorderLayout.WEST);
        pnMaNV.add(txtMaNV, BorderLayout.CENTER);

        pnTenNV.setLayout(new BorderLayout());
        pnTenNV.add(lbTenNV, BorderLayout.WEST);
        pnTenNV.add(txtTenNV, BorderLayout.CENTER);

        pnTienCoc.setLayout(new BorderLayout());
        pnTienCoc.add(lbTienCoc, BorderLayout.WEST);
        pnTienCoc.add(txtTienCoc, BorderLayout.CENTER);

        txtMaCT.setBorder(null);
        txtMaKH.setBorder(null);
        txtTenKH.setBorder(null);
        txtMaNV.setBorder(null);
        txtTenNV.setBorder(null);
        txtTienCoc.setBorder(null);

        txtMaCT.setFont(sgUI13);
        txtMaNV.setFont(sgUI13);
        txtMaKH.setFont(sgUI13);
        txtTenNV.setFont(sgUI13);
        txtTenKH.setFont(sgUI13);
        txtTienCoc.setFont(sgUI13);

        txtMaCT.setBorder(new EmptyBorder(0, 5, 0, 0));
        txtMaNV.setBorder(new EmptyBorder(0, 5, 0, 0));
        txtMaKH.setBorder(new EmptyBorder(0, 5, 0, 0));
        txtTenNV.setBorder(new EmptyBorder(0, 5, 0, 0));
        txtTenKH.setBorder(new EmptyBorder(0, 5, 0, 0));
        txtTienCoc.setBorder(new EmptyBorder(0, 5, 0, 0));

        rbGio.setFocusPainted(false);
        rbNgay.setFocusPainted(false);

        pnCenterContentHead.setBorder(new EmptyBorder(5, 5, 5, 5));

        pnCenterContentBody.setLayout(new GridLayout(2, 1));
        pnCenterContentBody.add(pnCenterContentBodyPhong);
        pnCenterContentBody.add(pnCenterContentBodyBottom);

        pnCenterContentBodyBottom.setLayout(new BorderLayout());
        pnCenterContentBodyBottom.add(pnCenterContentBodyBottomDichVu, BorderLayout.CENTER);
        pnCenterContentBodyBottom.add(pnCenterContentBodyBottomBtn, BorderLayout.SOUTH);

        pnCenterContentBodyBottomBtn.setLayout(new BorderLayout());
        pnCenterContentBodyBottomBtn.add(pnCenterContentBodyBottomEmpty, BorderLayout.CENTER);
        pnCenterContentBodyBottomBtn.add(pnCenterContentBodyBottomRight, BorderLayout.EAST);

        pnCenterContentBodyBottomRight.setLayout(new BorderLayout());
        pnCenterContentBodyBottomRight.add(btnXuLy, BorderLayout.CENTER);
        pnCenterContentBodyBottomRight.setBorder(new EmptyBorder(5, 0, 5, 5));
        btnXuLy.setFocusPainted(false);

        pnCenterContentBodyPhong.setLayout(new GridLayout(2, 1));
        pnCenterContentBodyPhong.add(pnInfo);
        pnCenterContentBodyPhong.add(pnTable);

        ttb.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        pnInfo.setLayout(new BorderLayout());
        pnInfo.add(pnInfoCenter, BorderLayout.CENTER);
        pnInfoCenter.setBorder(new EmptyBorder(0, 5, 5, 5));

        pnInfoCenter.setLayout(new GridLayout(2, 1));
        pnInfoCenter.add(pnInfoCenterTop);
        pnInfoCenter.add(pnInfoCenterBottom);

        pnInfoCenterTop.setLayout(new GridLayout(1, 3));
        pnInfoCenterTop.add(pnLoaiHinhThue);
        pnInfoCenterTop.add(pnNgayThue);
        pnInfoCenterTop.add(pnTimeNgayThue);
        timeNgayThue.setBorder(new EmptyBorder(5, 10, 5, 15));

        pnInfoCenterBottom.setLayout(new GridLayout(1, 3));
        pnInfoCenterBottom.add(pnNgayTra);
        pnInfoCenterBottom.add(pnTimeNgayTra);
        pnInfoCenterBottom.add(pnSelectPhong);
        timeNgayTra.setBorder(new EmptyBorder(5, 10, 5, 25));

        pnLoaiHinhThue.setLayout(new BorderLayout());
        pnLoaiHinhThue.add(lbLoaiHinhThue, BorderLayout.WEST);
        pnLoaiHinhThue.add(pnRButton, BorderLayout.CENTER);

        pnRButton.setLayout(new GridLayout(1, 2));

        pnRButton.add(rbNgay);
        pnRButton.add(rbGio);
        bg.add(rbNgay);
        bg.add(rbGio);
        rbNgay.setSelected(true);

        pnNgayThue.setLayout(new BorderLayout());
        pnNgayThue.add(lbNgayThue, BorderLayout.WEST);
        pnNgayThue.add(pnNgayThueChoose, BorderLayout.CENTER);
        pnNgayThueChoose.setLayout(new BorderLayout());
        pnNgayThueChoose.add(dateNgayThue, BorderLayout.CENTER);
        pnNgayThueChoose.setBorder(new EmptyBorder(5, 30, 5, 60));

        pnTimeNgayThue.setLayout(new BorderLayout());

        pnTimeNgayThue.add(lbTimeNgayThue, BorderLayout.WEST);
        pnTimeNgayThue.add(timeNgayThue, BorderLayout.CENTER);

        pnNgayTra.setLayout(new BorderLayout());
        pnNgayTra.add(lbNgayTra, BorderLayout.WEST);
        pnNgayTra.add(pnNgayTraChoose, BorderLayout.CENTER);
        pnNgayTraChoose.setLayout(new BorderLayout());
        pnNgayTraChoose.add(dateNgayTra, BorderLayout.CENTER);
        pnNgayTraChoose.setBorder(new EmptyBorder(5, 30, 5, 60));

        pnTimeNgayTra.setLayout(new BorderLayout());

        pnTimeNgayTra.add(lbTimeNgayTra, BorderLayout.WEST);

        pnTimeNgayTra.add(timeNgayTra, BorderLayout.CENTER);

        pnSelectPhong.setLayout(new BorderLayout());
        pnSelectPhong.add(btnSelectPhong, BorderLayout.WEST);
        pnSelectPhong.add(txtTotal, BorderLayout.CENTER);

        pnSelectPhong.setBorder(new EmptyBorder(5, 0, 5, 10));
        btnSelectPhong.setFocusPainted(false);
        txtTotal.setBorder(new EmptyBorder(0, 50, 0, 10));
        txtTotal.setEnabled(false);
        txtTotal.setFont(sgUI15);

        pnInfo.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 5, 5, 5), ttb));
        pnTable.setLayout(new BorderLayout());
        pnTable.add(scp, BorderLayout.CENTER);
        scp.setViewportView(tb);
        pnTable.setBorder(new EmptyBorder(0, 5, 0, 5));
        render(tb);
        ttbTI.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        pnCenterContentBodyBottomDichVu.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 5, 0, 5), ttbTI));
        pnCenterContentBodyBottomDichVu.setLayout(new BorderLayout());
        pnCenterContentBodyBottomDichVu.add(pnCenterContentBodyBottomDichVuCenter, BorderLayout.CENTER);
        pnCenterContentBodyBottomDichVuCenter.setBorder(new EmptyBorder(0, 5, 5, 5));
        pnCenterContentBodyBottomDichVuCenter.setLayout(new BorderLayout());
        pnCenterContentBodyBottomDichVuCenter.add(scpDV, BorderLayout.CENTER);
        pnCenterContentBodyBottomDichVuCenter.add(pnXuLy, BorderLayout.EAST);

        pnXuLy.setLayout(new BorderLayout());
        pnXuLy.add(lbXuLy, BorderLayout.NORTH);
        pnXuLy.add(pnAddDV, BorderLayout.CENTER);
        lbXuLy.setFont(sgUI15);
        pnLBRightTop.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 0, 0), new MatteBorder(1, 1, 1, 1, Color.black)));
        pnLBcenter.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 0, 5), new MatteBorder(1, 1, 1, 1, Color.black)));
        pnCenterContent.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 5, 5, 5), new MatteBorder(0, 1, 1, 1, Color.black)));
        lbXuLy.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.black), new EmptyBorder(5, 5, 5, 5)));

        pnAddDV.setLayout(new BorderLayout());
        pnAddDV.add(pnAddDVbtn, BorderLayout.NORTH);

        pnAddDVbtn.setLayout(new BorderLayout());
        pnAddDVbtn.add(btnAddDV, BorderLayout.CENTER);
        pnAddDVbtn.setBorder(new EmptyBorder(5, 30, 0, 30));

        btnAddDV.setFocusPainted(false);

        scpDV.setViewportView(tbDV);
        renderDV(tbDV);

        lbXuLy.setOpaque(true);

        btnXuLy.setEnabled(false);
        txtMaCT.setEditable(false);
        txtMaKH.setEditable(false);
        txtTenKH.setEditable(false);
        txtMaNV.setEditable(false);

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

        btnXuLy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listTP.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng thuê", "Báo lỗi", JOptionPane.ERROR);
                } else {
                    try {
                        int tienCoc = Integer.parseInt(txtTienCoc.getText());
                        if (tienCoc < 0) {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập tiền cọc là số dương");
                            txtTienCoc.requestFocus();
                            txtTienCoc.setText("");
                        } else {
                            ChiTietThueDTO ctt = new ChiTietThueDTO();
                            ctt.setMaChiTietThue(txtMaCT.getText());
                            ctt.setMaKH(txtMaKH.getText());
                            ctt.setMaNV(txtMaNV.getText());
                            ctt.setTienDatCoc(tienCoc);
                            ctt.setTinhTrangXuLy(0);
                            ctt.setXuLy(0);
                            if (ChiTietThueBUS.insertCTT(ctt)) {
                                int i = 0;
                                int check = 0;
                                for (ThuePhongDTO x : listTP) {
                                    x.setMaChiTietThue(ctt.getMaChiTietThue());
                                    x.setMaP(listP.get(i).getMaP());
                                    x.setNgayThue(dateChange(x.getNgayThue()));
                                    x.setNgayTra(dateChange(x.getNgayTra()));
                                    x.setNgayCheckOut(x.getNgayTra());
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = new Date();
                                    String datestr = sdf.format(date);
//                                    if (x.getNgayThue().contains(datestr)) {
//                                        if (!PhongBUS.updateTT(x.getMaP(), "Đang được thuê")) {
//                                            JOptionPane.showMessageDialog(null, "Phòng không thể thuê");
//                                        } else {
//                                            x.setTinhTrang(1);
//                                        }
//                                    }
                                    if (!ThuePhongBUS.insertTP(x)) {
                                        check++;
                                    } else {

                                    }
                                    i++;
                                }
                                for (SuDungDichVuDTO x : listSDDV) {
                                    x.setMaChiTietThue(ctt.getMaChiTietThue());
                                    if (!SuDungDichVuBUS.insertSDDV(x)) {
                                        check++;
                                    }
                                }
                                if (check == 0) {
                                    JOptionPane.showMessageDialog(null, "Thuê phòng thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                                    btnXuLy.setEnabled(false);
                                    reset();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Không thể thêm chi tiết này được", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập tiền cọc là số", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        btnSelectPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (rbNgay.isSelected()) {
                    timeNgayTra.setHour(timeNgayThue.getHour());
                    timeNgayTra.setMinute(timeNgayThue.getMinute());
                }
                String error = "";
                if (dateNgayThue.getDate() == null) {
                    error += "Vui lòng nhập ngày thuê\n";
                }
                if (dateNgayTra.getDate() == null) {
                    error += "Vui lòng nhập ngày trả\n";
                }
                if (error.length() == 0) {
                    if (!checkTimeNow(dateNgayThue.getDate(), timeNgayThue)) {
                        error += "Vui lòng chọn ngày, giờ thuê phải lớn hơn ngày giờ hiện tại\n";
                    }
                    if (!checkTimeNow(dateNgayTra.getDate(), timeNgayTra)) {
                        error += "Vui lòng chọn ngày, giờ trả phải lớn hơn ngày giờ hiện tại\n";
                    }
                    if (error.length() == 0) {
                        if (checkTimeTra_Thue()) {
                            if (rbNgay.isSelected()) {
                                long time = dateNgayTra.getDate().getTime() - dateNgayThue.getDate().getTime();
                                int dayCount = (int) (time / (1000 * 60 * 60 * 24));
                                if (dayCount > 0) {
                                    ThuePhongDTO x = new ThuePhongDTO();
                                    x.setTinhTrang(0);
                                    x.setLoaiHinhThue(rbNgay.getText());
                                    x.setNgayThue(date(dateNgayThue.getDate(), timeNgayThue));
                                    x.setNgayTra(date(dateNgayTra.getDate(), timeNgayTra));
                                    PanelSelectPhong pnSelectP = new PanelSelectPhong(x, 0, dayCount, dateChange(x.getNgayThue()), dateChange(x.getNgayTra()));
                                } else {
                                    JOptionPane.showMessageDialog(null, "Ngày, giờ trả phải lớn hơn ngày, giờ thuê", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                }
                            } else {
                                Calendar dateThue = Calendar.getInstance();
                                dateThue.setTime(dateNgayTra.getDate());
                                dateThue.set(Calendar.HOUR, timeNgayThue.getHour());
                                dateThue.set(Calendar.MINUTE, timeNgayThue.getMinute());
                                dateThue.set(Calendar.SECOND, 0);

                                Calendar dateTra = Calendar.getInstance();
                                dateTra.setTime(dateNgayTra.getDate());
                                dateTra.set(Calendar.HOUR, timeNgayTra.getHour());
                                dateTra.set(Calendar.MINUTE, timeNgayTra.getMinute());
                                dateTra.set(Calendar.SECOND, 0);

                                long time = dateTra.getTime().getTime() - dateThue.getTime().getTime();
                                int hourCount = (int) (time / (60 * 60 * 1000));

                                long date = dateNgayTra.getDate().getTime() - dateNgayThue.getDate().getTime();
                                int dayCount = (int) (date / (1000 * 60 * 60 * 24));
                                if (hourCount == 0) {
                                    if (dayCount < 1) {
                                        JOptionPane.showMessageDialog(null, "Vui lòng nhập giờ thuê ít nhất 1h", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        int ans = JOptionPane.showConfirmDialog(null, "Có lẽ bạn muốn thuê theo ngày", "Thông báo", JOptionPane.YES_NO_OPTION);
                                        if (ans == JOptionPane.YES_OPTION) {
                                            rbNgay.setSelected(true);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Vui lòng chọn giờ thuê ít nhất 1h", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                    }
                                } else {
                                    ThuePhongDTO x = new ThuePhongDTO();
                                    x.setTinhTrang(0);
                                    x.setLoaiHinhThue(rbNgay.getText());
                                    x.setNgayThue(date(dateNgayThue.getDate(), timeNgayThue));
                                    x.setNgayTra(date(dateNgayTra.getDate(), timeNgayTra));
                                    PanelSelectPhong pnSelectP = new PanelSelectPhong(x, hourCount, dayCount, dateChange(x.getNgayThue()), dateChange(x.getNgayTra()));
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Ngày, giờ trả phải lớn hơn ngày, giờ thuê", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, error, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, error, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        );

        tb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                    int ans = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa phòng thuê này?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    if (ans == JOptionPane.YES_OPTION) {
                        if (listTP.get(tb.getSelectedRow()).getTinhTrang() == 1 || listTP.get(tb.getSelectedRow()).getTinhTrang() == 2) {
                            JOptionPane.showMessageDialog(null, "Không thể xóa phòng thuê này", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            try {
                                listP.remove(tb.getSelectedRow());
                                listTP.remove(tb.getSelectedRow());
                                JOptionPane.showMessageDialog(null, "Xóa thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                                render(tb);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Xóa không thành công", "Thất bại", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                }
            }
        }
        );

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@        
        txtMaNV.setText(mainGUI.taiKhoan);

        txtTenNV.setEditable(
                false);
        txtTenNV.setText(
                "Admin");
        txtTienCoc.setText(
                "0");
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    }

    public String dateChange(String date) {
        String date1[] = date.split(" ");
        String date2[] = date1[0].split("-");
        String day = date2[0];
        String month = date2[1];
        String year = date2[2];
        return year + "-" + month + "-" + day + " " + date1[1];
    }

    public String date(Date d, TimeChoose tc) {
        String date = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        date += sdf.format(d);
        String time = " ";
        if (tc.getHour() < 10) {
            time += "0" + tc.getHour() + ":";
        } else {
            time += tc.getHour() + ":";
        }
        if (tc.getMinute() < 10) {
            time += "0" + tc.getMinute() + ":00";
        } else {
            time += tc.getMinute() + ":00";
        }
        date += time;
        return date;
    }

    public boolean checkTimeNow(Date date, TimeChoose tc) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.set(Calendar.HOUR, tc.getHour());
        cd.set(Calendar.MINUTE, tc.getMinute());
        cd.set(Calendar.SECOND, 0);
        Date d = new Date();
        return cd.getTime().after(d);
    }

    public boolean checkTimeTra_Thue() {
        Calendar timeThue = Calendar.getInstance();
        timeThue.setTime(dateNgayThue.getDate());
        timeThue.set(Calendar.HOUR, timeNgayThue.getHour());
        timeThue.set(Calendar.MINUTE, timeNgayThue.getMinute());
        timeThue.set(Calendar.SECOND, 0);

        Calendar timeTra = Calendar.getInstance();
        timeTra.setTime(dateNgayTra.getDate());
        timeTra.set(Calendar.HOUR, timeNgayTra.getHour());
        timeTra.set(Calendar.MINUTE, timeNgayTra.getMinute());
        timeTra.set(Calendar.SECOND, 0);

        return timeTra.getTime().after(timeThue.getTime());
    }

    public static int total = 0;

    public static void render(JTable tb) {
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
        tb.setModel(dtm);
        tb.setShowGrid(false);
        tb.setIntercellSpacing(new Dimension(0, 0));
        tb.setRowHeight(30);
        tb.getColumnModel().getColumn(0).setPreferredWidth(5);
        tb.getColumnModel().getColumn(1).setPreferredWidth(50);
        tb.getTableHeader().setPreferredSize(new Dimension(1, 30));
        tb.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tb.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        int i = 0;
        for (PhongDTO x : listP) {
            String tinhTrang = "";
            if (listTP.get(i).getTinhTrang() == 0) {
                tinhTrang = "Đang xử lý";
            } else if (listTP.get(i).getTinhTrang() == 1) {
                tinhTrang = "Đang được thuê";
            } else {
                tinhTrang = "Đã trả phòng";
            }
            Object row[] = {i + 1, x.getMaP(), x.getTenP(), tinhTrang, listTP.get(i).getLoaiHinhThue(), listTP.get(i).getNgayThue(), listTP.get(i).getNgayTra(), x.getGiaP(), listTP.get(i).getGia()};
            dtm.addRow(row);
            i++;
        }

        for (ThuePhongDTO x : listTP) {
            total += x.getGia();
        }
        txtTotal.setText("Tổng cộng: " + total);
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
                if (column == tb.getColumnModel().getColumnIndex("STT")) {
                    lb.setBorder(new MatteBorder(0, 0, 1, 1, Color.decode("#99FF99")));
                    lb.setBackground(Color.decode("#99FF99"));
                } else {
                    lb.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode("#DDDDDD")));
                }
                return lb;
            }
        };
        for (int j = 0; j < tb.getColumnCount(); j++) {
            tb.getColumnModel().getColumn(j).setCellRenderer(renderBorder);
        }
    }

    public static void renderDV(JTable tbDV) {
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
            txtTotal.setText("Tổng cộng: " + total);
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

    private JPanel pnmakh = new JPanel();
    private JLabel lbmakh = new JLabel("Mã khách hàng");
    private JTextField txtmakh = new JTextField();

    private JPanel pntenkh = new JPanel();
    private JLabel lbtenkh = new JLabel("Tên khách hàng");
    private JTextField txttenkh = new JTextField();

    private JPanel pncmndkh = new JPanel();
    private JLabel lbcmndkh = new JLabel("CMND");
    private JTextField txtcmndkh = new JTextField();

    private JPanel pngioitinhkh = new JPanel();
    private JLabel lbgioitinhkh = new JLabel("Giới tính");
    private JPanel pngioitinhkhrd = new JPanel();
    private JRadioButton rbNam = new JRadioButton("Nam");
    private JRadioButton rbNu = new JRadioButton("Nữ");
    private ButtonGroup bgGT = new ButtonGroup();

    private JPanel pnsdtkh = new JPanel();
    private JLabel lbsdtkh = new JLabel("SĐT khách hàng");
    private JTextField txtsdtkh = new JTextField();

    private JPanel pnButton = new JPanel();
    private JPanel Pn1 = new JPanel();
    private JPanel Pn2 = new JPanel();
    private JButton btnReturn = new JButton("Quay lại");
    private JButton btnAddKH = new JButton("Lưu");
    private JLabel lb1 = new JLabel();
    private JLabel lb2 = new JLabel();

    public void render() {
        pnRightTopCenterPNSDTCenter.removeAll();
        pnRightTopCenterPNSDTCenter.revalidate();
        pnRightTopCenterPNSDTCenter.repaint();
        pnRightTopCenterPNSDTCenter.setLayout(new GridLayout(6, 1));

        pnRightTopCenterPNSDTCenter.add(pnmakh);
        pnRightTopCenterPNSDTCenter.add(pntenkh);
        pnRightTopCenterPNSDTCenter.add(pncmndkh);
        pnRightTopCenterPNSDTCenter.add(pnsdtkh);
        pnRightTopCenterPNSDTCenter.add(pngioitinhkh);
        pnRightTopCenterPNSDTCenter.add(pnButton);

        pnmakh.setLayout(new GridLayout(2, 1, 5, 5));
        pnmakh.add(lbmakh);
        pnmakh.add(txtmakh);

        pntenkh.setLayout(new GridLayout(2, 1, 5, 5));
        pntenkh.add(lbtenkh);
        pntenkh.add(txttenkh);

        pncmndkh.setLayout(new GridLayout(2, 1, 5, 5));
        pncmndkh.add(lbcmndkh);
        pncmndkh.add(txtcmndkh);

        pngioitinhkh.setLayout(new GridLayout(2, 1, 5, 5));
        pngioitinhkh.add(lbgioitinhkh);
        pngioitinhkh.add(pngioitinhkhrd);

        pngioitinhkhrd.setLayout(new GridLayout(1, 2));
        pngioitinhkhrd.add(rbNam);
        pngioitinhkhrd.add(rbNu);
        bgGT.add(rbNam);
        bgGT.add(rbNu);

        pnsdtkh.setLayout(new GridLayout(2, 1, 5, 5));
        pnsdtkh.add(lbsdtkh);
        pnsdtkh.add(txtsdtkh);

        rbNam.setSelected(true);
        rbNam.setFocusPainted(false);
        rbNu.setFocusPainted(false);
        txtmakh.setMargin(new Insets(0, 5, 0, 5));
        txttenkh.setMargin(new Insets(0, 5, 0, 5));
        txtcmndkh.setMargin(new Insets(0, 5, 0, 5));
        txtsdtkh.setMargin(new Insets(0, 5, 0, 5));

        pnButton.setLayout(new GridLayout(2, 1, 5, 5));
        pnButton.add(Pn1);
        Pn1.setLayout(new GridLayout(1, 2));
        Pn1.add(btnAddKH);
        Pn1.add(lb1);
        pnButton.add(Pn2);
        Pn2.setLayout(new GridLayout(1, 2));
        Pn2.add(btnReturn);
        Pn2.add(lb2);
        btnAddKH.setFocusPainted(false);
        btnReturn.setFocusPainted(false);
        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderSearch();
            }
        });
        txtmakh.setEditable(false);
        int countKH = 1;
        try {
            countKH += new KhachHangBUS().LoadKH().size();
        } catch (SQLException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 1000 + countKH;
        txtmakh.setText("KH" + count);
        txtRightTopCenterPNSDT.setText("Nhập CMND");

        btnAddKH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String error = "";

                if (txtsdtkh.getText().trim().length() == 0) {
                    txtsdtkh.setText("");
                    txtsdtkh.requestFocus();
                    error += "Vui lòng nhập sđt khách hàng\n";
                }
                if (txtcmndkh.getText().trim().length() == 0) {
                    txtcmndkh.setText("");
                    txtcmndkh.requestFocus();
                    error += "Vui lòng nhập cmnd khách hàng\n";
                }
                if (txttenkh.getText().trim().length() == 0) {
                    txttenkh.setText("");
                    txttenkh.requestFocus();
                    error += "Vui lòng nhập tên khách hàng";
                }

                if (error.length() == 0) {
                    if (txtsdtkh.getText().trim().length() != 10) {
                        error += "Số điện phải gồm 10 số\n";
                        txtsdtkh.setText("");
                        txtsdtkh.requestFocus();
                    }
                    if (txtcmndkh.getText().trim().length() >=10) {
                        error += "Cmnd phải gồm dưới 10 kí tự";
                        txtcmndkh.setText("");
                        txtcmndkh.requestFocus();
                    }
                    if (error.length() == 0) {
                        String gioiTinh = "Nam";
                        if (!rbNam.isSelected()) {
                            gioiTinh = "Nữ";
                        }
                        KhachHangDTO x = new KhachHangDTO(txtmakh.getText(), txttenkh.getText(), txtcmndkh.getText().trim(), gioiTinh, txtsdtkh.getText().trim(), 0);
                        try {
                            new KhachHangBUS().InsertKH(x);
                        } catch (SQLException ex) {
                            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(null, "Thêm khách hàng mới thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        txttenkh.setEditable(false);
                        txtcmndkh.setEditable(false);
                        txtsdtkh.setEditable(false);
                        rbNam.setEnabled(false);
                        rbNu.setEnabled(false);
                        txtMaKH.setText(txtmakh.getText().trim());
                        txtTenKH.setText(txttenkh.getText().trim());
                        btnAddKH.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(null, error, "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, error, "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        txtMaCT.setText("CTT" + ChiTietThueBUS.getSize());
        btnXuLy.setEnabled(true);
        rbNgay.setBorderPainted(false);
        rbGio.setBorderPainted(false);
    }

    public void renderSearch() {
        pnRightTopCenterPNSDTCenter.removeAll();
        pnRightTopCenterPNSDTCenter.revalidate();
        pnRightTopCenterPNSDTCenter.repaint();
        pnRightTopCenterPNSDTCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnRightTopCenterPNSDTCenter.setLayout(new GridLayout(4, 1));
        pnRightTopCenterPNSDTCenter.add(pnRightTopCenterPNSDT);
    }

    public void reset() {
        txtMaCT.setText("");
        listP.clear();
        listTP.clear();
        listP.clear();
        listDV.clear();
        listSDDV.clear();
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtTienCoc.setText("0");
        total = 0;
        render(tb);
        renderSearch();
        renderDV(tbDV);
    }

    public void lightDark(int lightDark) {
        if (lightDark == 1) {
            Color black = new Color(51, 51, 51);
            pnLBRightTop.setBackground(black);
            pnLBRightTop.setBackground(black);
            pnRightTopCenterPNSDTCenter.setBackground(black);
            btnAdd.setBackground(Color.decode("#CDC9A5"));
            pnRightTopCenterPNSDT.setBackground(black);
            pnRightTopCenterPNSDTBtn.setBackground(black);
            pnRightTopCenterPNSDTright.setBackground(black);
            pnRightTopCenterPNSDTempty.setBackground(black);
            btnSearch.setBackground(Color.decode("#CDC9A5"));
            pnLBcenter.setBackground(black);
            pnCenterContent.setBackground(black);
            pnCenterContentHead.setBackground(black);
            pnMaCT.setBackground(black);
            pnMaKH.setBackground(black);
            pnTenKH.setBackground(black);
            pnMaNV.setBackground(black);
            pnTenNV.setBackground(black);
            pnTienCoc.setBackground(black);
            pnCenterContentBodyBottomEmpty.setBackground(black);
            pnCenterContentBodyBottomRight.setBackground(black);
            btnXuLy.setBackground(black);
            pnCenterContentBodyPhong.setBackground(black);
            pnInfo.setBackground(black);
            pnInfoCenter.setBackground(black);
            lbRightTopLB.setBackground(Color.decode("#D3D3D3"));
            lbRightTopLB.setOpaque(true);
            lbCenter.setBackground(Color.decode("#D3D3D3"));
            lbCenter.setOpaque(true);
            lbRightTopCenterPNSDT.setForeground(Color.white);
            lbMaCT.setForeground(Color.white);
            lbMaKH.setForeground(Color.white);
            lbTenKH.setForeground(Color.white);
            lbTenNV.setForeground(Color.white);
            lbTienCoc.setForeground(Color.white);
            lbMaNV.setForeground(Color.white);
            txtMaCT.setBackground(black);
            txtMaKH.setBackground(black);
            txtMaNV.setBackground(black);
            txtTenKH.setBackground(black);
            txtTenNV.setBackground(black);
            txtTienCoc.setBackground(black);
            txtMaCT.setForeground(Color.white);
            txtMaKH.setForeground(Color.white);
            txtMaNV.setForeground(Color.white);
            txtTenKH.setForeground(Color.white);
            txtTenNV.setForeground(Color.white);
            txtTienCoc.setForeground(Color.white);
            rbGio.setBackground(black);
            rbGio.setForeground(Color.white);
            rbNgay.setBackground(black);
            rbNgay.setForeground(Color.white);
            pnRButton.setBackground(black);
            pnLoaiHinhThue.setBackground(black);
            pnNgayThue.setBackground(black);
            pnNgayThueChoose.setBackground(black);
            pnNgayTra.setBackground(black);
            pnNgayTraChoose.setBackground(black);
            pnTimeNgayThue.setBackground(black);
            timeNgayThue.setBackground(black);
            pnTimeNgayTra.setBackground(black);
            timeNgayTra.setBackground(black);
            pnSelectPhong.setBackground(black);
            scp.getViewport().setBackground(Color.decode("#BEBEBE"));
            pnTable.setBackground(black);
            pnCenterContentBodyBottomDichVu.setBackground(black);
            pnCenterContentBodyBottomDichVuCenter.setBackground(black);
            pnXuLy.setBackground(black);
            pnAddDV.setBackground(black);
            pnAddDVbtn.setBackground(black);
            btnAddDV.setBackground(Color.decode("#CDC9A5"));
            scpDV.getViewport().setBackground(Color.decode("#BEBEBE"));
            lbXuLy.setForeground(Color.black);
            lbXuLy.setBackground(Color.decode("#D3D3D3"));
            lbLoaiHinhThue.setForeground(Color.white);
            lbNgayThue.setForeground(Color.white);
            lbNgayTra.setForeground(Color.white);
            lbTimeNgayThue.setForeground(Color.white);
            lbTimeNgayTra.setForeground(Color.white);
            MatteBorder mt = new MatteBorder(1, 1, 1, 1, Color.white);
            ttb.setBorder(mt);
            ttb.setTitleColor(Color.white);
            ttbTI.setBorder(mt);
            ttbTI.setTitleColor(Color.white);
            pnLBRightTop.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 0, 0), new MatteBorder(1, 1, 1, 1, Color.white)));
            pnLBcenter.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 0, 5), new MatteBorder(1, 1, 1, 1, Color.white)));
            pnCenterContent.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 5, 5, 5), new MatteBorder(0, 1, 1, 1, Color.white)));
            lbXuLy.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.white), new EmptyBorder(5, 5, 5, 5)));
            btnAddDV.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.white), new EmptyBorder(5, 20, 5, 20)));
            pnLoaiHinhThue.setBackground(black);
            pnmakh.setBackground(black);
            pntenkh.setBackground(black);
            pncmndkh.setBackground(black);
            pngioitinhkh.setBackground(black);
            pnsdtkh.setBackground(black);
            rbNam.setBackground(black);
            rbNu.setBackground(black);
            pnButton.setBackground(black);
            Pn1.setBackground(black);
            Pn2.setBackground(black);
            lbXuLy.setOpaque(true);
            lbmakh.setForeground(Color.white);
            lbtenkh.setForeground(Color.white);
            lbcmndkh.setForeground(Color.white);
            lbsdtkh.setForeground(Color.white);
            lbgioitinhkh.setForeground(Color.white);
            rbNam.setForeground(Color.white);
            rbNu.setForeground(Color.white);
            txtTotal.setBackground(black);
        } else {
            pnLBRightTop.setBackground(Color.white);
            pnLBRightTop.setBackground(Color.white);
            btnAdd.setBackground(Color.decode("#FFFACD"));
            pnRightTopCenterPNSDT.setBackground(Color.white);
            pnRightTopCenterPNSDTBtn.setBackground(Color.white);
            pnRightTopCenterPNSDTright.setBackground(Color.white);
            pnRightTopCenterPNSDTempty.setBackground(Color.white);
            btnSearch.setBackground(Color.decode("#FFFACD"));
            pnLBcenter.setBackground(Color.white);
            pnCenterContent.setBackground(Color.white);
            pnCenterContentHead.setBackground(Color.white);
            pnMaCT.setBackground(Color.white);
            pnMaKH.setBackground(Color.white);
            pnTenKH.setBackground(Color.white);
            pnMaNV.setBackground(Color.white);
            pnTenNV.setBackground(Color.white);
            pnTienCoc.setBackground(Color.white);
            pnCenterContentBodyBottomEmpty.setBackground(Color.white);
            pnCenterContentBodyBottomRight.setBackground(Color.white);
            btnXuLy.setBackground(Color.white);
            pnCenterContentBodyPhong.setBackground(Color.white);
            pnInfo.setBackground(Color.white);
            pnInfoCenter.setBackground(Color.white);
            lbRightTopLB.setBackground(Color.decode("#F5F5F5"));
            lbRightTopLB.setOpaque(true);
            lbCenter.setBackground(Color.decode("#F5F5F5"));
            lbCenter.setOpaque(true);
            lbRightTopCenterPNSDT.setForeground(Color.black);
            lbMaCT.setForeground(Color.black);
            lbMaKH.setForeground(Color.black);
            lbTenKH.setForeground(Color.black);
            lbTenNV.setForeground(Color.black);
            lbTienCoc.setForeground(Color.black);
            lbMaNV.setForeground(Color.black);
            txtMaCT.setBackground(Color.white);
            txtMaKH.setBackground(Color.white);
            txtMaNV.setBackground(Color.white);
            txtTenKH.setBackground(Color.white);
            txtTenNV.setBackground(Color.white);
            txtTienCoc.setBackground(Color.white);
            txtMaCT.setForeground(Color.black);
            txtMaKH.setForeground(Color.black);
            txtMaNV.setForeground(Color.black);
            txtTenKH.setForeground(Color.black);
            txtTenNV.setForeground(Color.black);
            txtTienCoc.setForeground(Color.black);

            rbGio.setBackground(Color.white);
            rbGio.setForeground(Color.black);
            rbNgay.setBackground(Color.white);
            rbNgay.setForeground(Color.black);
            pnRButton.setBackground(Color.white);
            pnLoaiHinhThue.setBackground(Color.white);
            pnNgayThue.setBackground(Color.white);
            pnNgayThueChoose.setBackground(Color.white);
            pnNgayTra.setBackground(Color.white);
            pnNgayTraChoose.setBackground(Color.white);
            pnTimeNgayThue.setBackground(Color.white);
            timeNgayThue.setBackground(Color.white);
            pnTimeNgayTra.setBackground(Color.white);
            timeNgayTra.setBackground(Color.white);
            pnSelectPhong.setBackground(Color.white);
            scp.getViewport().setBackground(Color.white);
            pnTable.setBackground(Color.white);
            pnCenterContentBodyBottomDichVu.setBackground(Color.white);
            pnCenterContentBodyBottomDichVuCenter.setBackground(Color.white);
            pnXuLy.setBackground(Color.white);
            pnAddDV.setBackground(Color.white);
            pnAddDVbtn.setBackground(Color.white);
            scpDV.getViewport().setBackground(Color.white);
            lbXuLy.setBackground(Color.white);
            lbLoaiHinhThue.setForeground(Color.black);
            lbNgayThue.setForeground(Color.black);
            lbNgayTra.setForeground(Color.black);
            lbTimeNgayThue.setForeground(Color.black);
            lbTimeNgayTra.setForeground(Color.black);
            MatteBorder mt = new MatteBorder(1, 1, 1, 1, Color.BLACK);
            ttb.setBorder(mt);
            ttb.setTitleColor(Color.black);
            ttbTI.setBorder(mt);
            ttbTI.setTitleColor(Color.black);
            pnLBRightTop.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 0, 0), new MatteBorder(1, 1, 1, 1, Color.black)));
            pnLBcenter.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 0, 5), new MatteBorder(1, 1, 1, 1, Color.black)));
            pnCenterContent.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 5, 5, 5), new MatteBorder(0, 1, 1, 1, Color.black)));
            lbXuLy.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.black), new EmptyBorder(5, 5, 5, 5)));
            btnAddDV.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.black), new EmptyBorder(5, 20, 5, 20)));
            pnLoaiHinhThue.setBackground(Color.white);
            pnmakh.setBackground(Color.white);
            pntenkh.setBackground(Color.white);
            pncmndkh.setBackground(Color.white);
            pngioitinhkh.setBackground(Color.white);
            pnsdtkh.setBackground(Color.white);
            rbNam.setBackground(Color.white);
            rbNu.setBackground(Color.white);
            pnButton.setBackground(Color.white);
            Pn1.setBackground(Color.white);
            Pn2.setBackground(Color.white);
            lbXuLy.setOpaque(true);
            lbXuLy.setForeground(Color.black);
            pnRightTopCenterPNSDTCenter.setBackground(Color.white);
            lbmakh.setForeground(Color.black);
            lbtenkh.setForeground(Color.black);
            lbcmndkh.setForeground(Color.black);
            lbsdtkh.setForeground(Color.black);
            lbgioitinhkh.setForeground(Color.black);

            rbNam.setForeground(Color.black);
            rbNu.setForeground(Color.black);
            btnAddDV.setBackground(Color.decode("#FFFACD"));
            txtTotal.setBackground(Color.white);
        }
    }
}
