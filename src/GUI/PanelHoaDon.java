package GUI;

import BUS.HoaDonBUS;
import BUS.BackUpData;
import BUS.NhanVienBUS;
import DTO.HoaDonDTO;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class PanelHoaDon extends JPanel {

    private JPanel pnTitle = new JPanel();
    private JPanel pnCenter = new JPanel();
    private JPanel pnSearch = new JPanel();
    private JPanel pnContent = new JPanel();
    private JPanel pnList = new JPanel();
    private JPanel pnBtn = new JPanel();
    private JPanel pnInput = new JPanel();
    private JPanel pnTitleTop = new JPanel();
    private JPanel pnMid = new JPanel();
    private JScrollPane scp = new JScrollPane();

    private JPanel pnMaHD = new JPanel();
    private JPanel pnMaCTT = new JPanel();
    private JPanel pnNgayThanhToan1 = new JPanel();
    private JLabel lbNgayThanhToan1 = new JLabel("Từ ngày:      ");
    private JPanel pnNgayThanhToan2 = new JPanel();
    private JLabel lbNgayThanhToan2 = new JLabel("Đến ngày:     ");
    private JPanel pnNguoiLap = new JPanel();
    private JPanel pnTienPhong = new JPanel();
    private JPanel pnTienDV = new JPanel();
    private JPanel pnTongCong = new JPanel();

    private JTextField txtMaHD = new JTextField("Mã hóa đơn");
    private JTextField txtMaCTT = new JTextField("Mã chi tiết thuê");
    private JDateChooser txtNgayThanhToan1 = new JDateChooser();
    private JDateChooser txtNgayThanhToan2 = new JDateChooser();
    private JTextField txtNguoiLap = new JTextField("Người lập");
    private JComboBox txtTienPhong = new JComboBox();
    private JComboBox txtTienDV = new JComboBox();
    private JComboBox txtTongCong = new JComboBox();

    private JPanel pnNgay = new JPanel();
    private JLabel lbNgay = new JLabel("Ngày:    ");
    private JComboBox cbNgay = new JComboBox();

    private JPanel pnThang = new JPanel();
    private JLabel lbThang = new JLabel("Tháng:    ");
    private JComboBox cbThang = new JComboBox();

    private JPanel pnNam = new JPanel();
    private JLabel lbNam = new JLabel("Năm:    ");
    private JComboBox cbNam = new JComboBox();
    private JPanel pnBtnReset = new JPanel();
    private JButton btnReset = new JButton("Làm mới");

    ArrayList<HoaDonDTO> listHD = new ArrayList<>();

    private JTable tbHoaDon = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);

    private JLabel lbTitle = new JLabel("DANH MỤC HÓA ĐƠN");
    private JLabel lbList = new JLabel("Danh sách hóa đơn");

    private JButton btnSearch = new JButton("Tìm kiếm");

    private JPanel pnSearchTop = new JPanel();
    private JPanel pnInputDate = new JPanel();
    private JPanel pnSearchBottom = new JPanel();
    JButton btnXuatFile = new JButton("Xuất File");

    public PanelHoaDon() {
        initComponents();
    }

    public void initComponents() {
        listHD = HoaDonBUS.getListHD();
        setBackground(Color.white);
        setLayout(new BorderLayout());

        pnTitle.setLayout(new BorderLayout());
        pnTitle.add(lbTitle);
        lbTitle.setFont(sgUI18b);
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        pnTitle.setBackground(Color.white);
        add(pnTitle, BorderLayout.NORTH);
        add(pnCenter, BorderLayout.CENTER);

        pnCenter.setLayout(new BorderLayout());
        pnCenter.add(pnSearchTop, BorderLayout.NORTH);
        pnCenter.add(pnContent, BorderLayout.CENTER);

        pnSearchTop.setLayout(new GridLayout(2, 1));
        pnSearchTop.add(pnSearch);
        pnSearchTop.add(pnSearchBottom);

        pnSearch.setLayout(new BorderLayout());
        pnSearch.add(pnBtn, BorderLayout.EAST);
        pnSearch.add(pnInput, BorderLayout.CENTER);

        pnInput.setLayout(new GridLayout(1, 6, 2, 2));
        pnInput.add(pnMaHD);
        pnMaHD.setLayout(new BorderLayout());
        pnMaHD.add(txtMaHD, BorderLayout.CENTER);
        txtMaHD.setMargin(new Insets(5, 5, 5, 5));
        txtMaHD.setFont(sgUI13);
        pnInput.add(pnMaCTT);
        pnMaCTT.setLayout(new BorderLayout());
        pnMaCTT.add(txtMaCTT, BorderLayout.CENTER);
        txtMaCTT.setMargin(new Insets(5, 5, 5, 5));
        txtMaCTT.setFont(sgUI13);
//        pnInput.add(pnNgayThanhToan1);
        pnNgayThanhToan1.setLayout(new BorderLayout());
        pnNgayThanhToan1.add(txtNgayThanhToan1, BorderLayout.CENTER);
        txtNgayThanhToan1.setFont(sgUI13);
//        pnInput.add(pnNgayThanhToan2);

        pnInput.add(pnNguoiLap);
        pnNguoiLap.setLayout(new BorderLayout());
        pnNguoiLap.add(txtNguoiLap, BorderLayout.CENTER);
        txtNguoiLap.setMargin(new Insets(5, 5, 5, 5));
        txtNguoiLap.setFont(sgUI13);

        pnInput.add(pnTienPhong);
        pnTienPhong.setLayout(new BorderLayout());
        pnTienPhong.add(txtTienPhong, BorderLayout.CENTER);
        txtTienPhong.setFont(sgUI13);
        txtTienPhong.setBackground(Color.white);
        txtTienPhong.addItem("Tiền phòng");
        txtTienPhong.addItem("0 - 99999");
        txtTienPhong.addItem("100000 - 299999");
        txtTienPhong.addItem("300000 - 499999");
        txtTienPhong.addItem("500000 - 999999");
        txtTienPhong.addItem("Trên 1000000");

        pnInput.add(pnTienDV);
        pnTienDV.setLayout(new BorderLayout());
        pnTienDV.add(txtTienDV, BorderLayout.CENTER);
        txtTienDV.setFont(sgUI13);
        txtTienDV.setBackground(Color.white);
        txtTienDV.addItem("Tiền dịch vụ");
        txtTienDV.addItem("0 - 99999");
        txtTienDV.addItem("100000 - 299999");
        txtTienDV.addItem("300000 - 499999");
        txtTienDV.addItem("500000 - 999999");
        txtTienDV.addItem("Trên 1000000");

        pnInput.add(pnTongCong);
        pnTongCong.setLayout(new BorderLayout());
        pnTongCong.add(txtTongCong, BorderLayout.CENTER);
        txtTongCong.addItem("Tổng cộng");
        txtTongCong.addItem("0 - 99999");
        txtTongCong.addItem("100000 - 299999");
        txtTongCong.addItem("300000 - 499999");
        txtTongCong.addItem("500000 - 999999");
        txtTongCong.addItem("Trên 1000000");
        txtTongCong.setFont(sgUI13);
        txtTongCong.setBackground(Color.white);
        pnInput.setBorder(new EmptyBorder(5, 8, 5, 8));

        pnBtn.setLayout(new BorderLayout());
        pnBtn.setBackground(Color.white);
        pnBtn.add(btnSearch, BorderLayout.CENTER);
        pnBtn.setBorder(new EmptyBorder(5, 0, 0, 10));
        btnSearch.setRequestFocusEnabled(false);

        pnSearchBottom.setLayout(new GridLayout(1, 5, 10, 10));
        pnSearchBottom.add(pnNgayThanhToan1);
        pnNgayThanhToan1.add(lbNgayThanhToan1, BorderLayout.WEST);
        pnSearchBottom.add(pnNgayThanhToan2);

        pnNgayThanhToan2.setLayout(new BorderLayout());
        pnNgayThanhToan2.add(lbNgayThanhToan2, BorderLayout.WEST);
        pnNgayThanhToan2.add(txtNgayThanhToan2, BorderLayout.CENTER);
        txtNgayThanhToan2.setFont(sgUI13);

        pnSearchBottom.add(pnNam);
        pnSearchBottom.setBorder(new EmptyBorder(5, 8, 5, 9));
        pnNam.setLayout(new BorderLayout());
        pnNam.add(lbNam, BorderLayout.WEST);
        pnNam.add(cbNam, BorderLayout.CENTER);
        pnSearchBottom.add(pnThang);
        pnThang.setLayout(new BorderLayout());
        pnThang.add(lbThang, BorderLayout.WEST);
        pnThang.add(cbThang, BorderLayout.CENTER);
        pnSearchBottom.add(pnNgay);
        pnNgay.setLayout(new BorderLayout());
        pnNgay.add(lbNgay, BorderLayout.WEST);
        pnNgay.add(cbNgay, BorderLayout.CENTER);
        pnSearchBottom.add(pnBtnReset);
        pnBtnReset.setLayout(new GridLayout(1, 2, 5, 5));
        pnBtnReset.add(btnReset);
        pnBtnReset.add(btnXuatFile);

        tbHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                    String maHD = tbHoaDon.getValueAt(tbHoaDon.getSelectedRow(), tbHoaDon.getColumnModel().getColumnIndex("Mã hóa đơn")).toString();
                    String maCTT = tbHoaDon.getValueAt(tbHoaDon.getSelectedRow(), tbHoaDon.getColumnModel().getColumnIndex("Mã chi tiết thuê")).toString();
                    String maNgayThanhToan = tbHoaDon.getValueAt(tbHoaDon.getSelectedRow(), tbHoaDon.getColumnModel().getColumnIndex("Ngày thanh toán")).toString();
                    String tenNV = tbHoaDon.getValueAt(tbHoaDon.getSelectedRow(), tbHoaDon.getColumnModel().getColumnIndex("Người lập")).toString();
                    String tienPhong = tbHoaDon.getValueAt(tbHoaDon.getSelectedRow(), tbHoaDon.getColumnModel().getColumnIndex("Tiền phòng")).toString();
                    String tienDV = tbHoaDon.getValueAt(tbHoaDon.getSelectedRow(), tbHoaDon.getColumnModel().getColumnIndex("Tiền dịch vụ")).toString();
                    String giamGia = tbHoaDon.getValueAt(tbHoaDon.getSelectedRow(), tbHoaDon.getColumnModel().getColumnIndex("Giảm giá")).toString();
                    String total = tbHoaDon.getValueAt(tbHoaDon.getSelectedRow(), tbHoaDon.getColumnModel().getColumnIndex("Tổng cộng")).toString();
                    tbHoaDon.clearSelection();
                    new ChiTietHoaDon(maHD,maCTT,maNgayThanhToan,tenNV,tienPhong,tienDV,giamGia,total);
                }
            }
        });

        btnXuatFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackUpData bk = new BUS.BackUpData();
                try {
                    String path = System.getProperty("user.dir") + "\\src\\GUI\\excel\\BackUpData_HoaDon.xlsx";
                    bk.backup_HoaDon(path);
                    JOptionPane.showMessageDialog(null, "Xuất file thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(new File(path));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Xuất file không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMaCTT.getText().equals("Mã chi tiết thuê")
                        && txtMaHD.getText().equals("Mã hóa đơn")
                        && cbNam.getSelectedIndex() == 0
                        && cbNgay.getSelectedIndex() == 0
                        && cbThang.getSelectedIndex() == 0
                        && txtTienPhong.getSelectedIndex() == 0
                        && txtTienDV.getSelectedIndex() == 0
                        && txtTongCong.getSelectedIndex() == 0
                        && txtNguoiLap.getText().equals("Người lập")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập dữ liệu tìm kiếm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadTable(tbHoaDon, listHD);
                } else {
                    ArrayList<HoaDonDTO> listHD1 = HoaDonBUS.getListHD();
                    ArrayList<HoaDonDTO> listTemp = new ArrayList<>();
                    if (!txtMaCTT.getText().equals("Mã chi tiết thuê")) {
                        for (HoaDonDTO x : listHD1) {
                            if (x.getMaChiTietThue().toLowerCase().contains(txtMaCTT.getText().toLowerCase())) {
                                listTemp.add(x);
                            }
                        }
                        listHD1.clear();
                        for (HoaDonDTO x : listTemp) {
                            listHD1.add(x);
                        }
                        listTemp.clear();
                    }

                    if (!txtMaHD.getText().equals("Mã hóa đơn")) {
                        for (HoaDonDTO x : listHD1) {
                            if (x.getMaHD().toLowerCase().contains(txtMaHD.getText().toLowerCase())) {
                                listTemp.add(x);
                            }
                        }
                        listHD1.clear();
                        for (HoaDonDTO x : listTemp) {
                            listHD1.add(x);
                        }
                        listTemp.clear();
                    }

                    if (!txtNguoiLap.getText().equals("Người lập")) {
                        for (HoaDonDTO x : listHD1) {
                            if (NhanVienBUS.getTenNV(x.getMaNV()).toLowerCase().contains(txtNguoiLap.getText().toLowerCase())) {
                                listTemp.add(x);
                            }
                        }
                        listHD1.clear();
                        for (HoaDonDTO x : listTemp) {
                            listHD1.add(x);
                        }
                        listTemp.clear();
                    }

                    if (txtTienPhong.getSelectedIndex() != 0) {
                        if (txtTienPhong.getSelectedIndex() == 5) {
                            int before = Integer.parseInt(txtTienPhong.getItemAt(txtTienPhong.getSelectedIndex()).toString().split("Trên ")[1]);
                            for (HoaDonDTO x : listHD1) {
                                if (x.getTienPhong() >= before) {
                                    listTemp.add(x);
                                }
                            }
                            listHD1.clear();
                            for (HoaDonDTO x : listTemp) {
                                listHD1.add(x);
                            }
                            listTemp.clear();
                        } else {
                            int before = Integer.parseInt(txtTienPhong.getItemAt(txtTienPhong.getSelectedIndex()).toString().split(" - ")[0]);
                            int after = Integer.parseInt(txtTienPhong.getItemAt(txtTienPhong.getSelectedIndex()).toString().split(" - ")[1]);
                            for (HoaDonDTO x : listHD1) {
                                if (x.getTienPhong() >= before && x.getTienPhong() <= after) {
                                    listTemp.add(x);
                                }
                            }
                            listHD1.clear();
                            for (HoaDonDTO x : listTemp) {
                                listHD1.add(x);
                            }
                            listTemp.clear();
                        }
                    }

                    if (txtTienDV.getSelectedIndex() != 0) {
                        if (txtTienDV.getSelectedIndex() == 5) {
                            int before = Integer.parseInt(txtTienDV.getItemAt(txtTienDV.getSelectedIndex()).toString().split("Trên ")[1]);
                            for (HoaDonDTO x : listHD1) {
                                if (x.getTienDichVu() >= before) {
                                    listTemp.add(x);
                                }
                            }
                            listHD1.clear();
                            for (HoaDonDTO x : listTemp) {
                                listHD1.add(x);
                            }
                            listTemp.clear();
                        } else {
                            int before = Integer.parseInt(txtTienDV.getItemAt(txtTienDV.getSelectedIndex()).toString().split(" - ")[0]);
                            int after = Integer.parseInt(txtTienDV.getItemAt(txtTienDV.getSelectedIndex()).toString().split(" - ")[1]);
                            for (HoaDonDTO x : listHD1) {
                                if (x.getTienDichVu() >= before && x.getTienDichVu() <= after) {
                                    listTemp.add(x);
                                }
                            }
                            listHD1.clear();
                            for (HoaDonDTO x : listTemp) {
                                listHD1.add(x);
                            }
                            listTemp.clear();
                        }
                    }

                    if (txtTongCong.getSelectedIndex() != 0) {
                        if (txtTongCong.getSelectedIndex() == 5) {
                            int before = Integer.parseInt(txtTongCong.getItemAt(txtTongCong.getSelectedIndex()).toString().split("Trên ")[1]);
                            for (HoaDonDTO x : listHD1) {
                                if (x.getTongTien() >= before) {
                                    listTemp.add(x);
                                }
                            }
                            listHD1.clear();
                            for (HoaDonDTO x : listTemp) {
                                listHD1.add(x);
                            }
                            listTemp.clear();
                        } else {
                            int before = Integer.parseInt(txtTongCong.getItemAt(txtTongCong.getSelectedIndex()).toString().split(" - ")[0]);
                            int after = Integer.parseInt(txtTongCong.getItemAt(txtTongCong.getSelectedIndex()).toString().split(" - ")[1]);
                            for (HoaDonDTO x : listHD1) {
                                if (x.getTongTien() >= before && x.getTongTien() <= after) {
                                    listTemp.add(x);
                                }
                            }
                            listHD1.clear();
                            for (HoaDonDTO x : listTemp) {
                                listHD1.add(x);
                            }
                            listTemp.clear();
                        }
                    }
                    if (cbNgay.getSelectedIndex() != 0) {
                        int day = Integer.parseInt(cbNgay.getSelectedItem().toString().split("Ngày ")[1]);
                        for (HoaDonDTO x : listHD1) {
                            if (getDay(x.getNgayThanhToan()) == day) {
                                listTemp.add(x);
                            }
                        }
                        listHD1.clear();
                        for (HoaDonDTO x : listTemp) {
                            listHD1.add(x);
                        }
                        listTemp.clear();
                    }

                    if (cbThang.getSelectedIndex() != 0) {
                        int day = Integer.parseInt(cbThang.getSelectedItem().toString().split("Tháng ")[1]);
                        for (HoaDonDTO x : listHD1) {
                            if (getMonth(x.getNgayThanhToan()) == day) {
                                listTemp.add(x);
                            }
                        }
                        listHD1.clear();
                        for (HoaDonDTO x : listTemp) {
                            listHD1.add(x);
                        }
                        listTemp.clear();
                    }

                    if (cbNam.getSelectedIndex() != 0) {
                        int day = Integer.parseInt(cbNam.getSelectedItem().toString().split("Năm ")[1]);
                        for (HoaDonDTO x : listHD1) {
                            if (getYear(x.getNgayThanhToan()) == day) {
                                listTemp.add(x);
                            }
                        }
                        listHD1.clear();
                        for (HoaDonDTO x : listTemp) {
                            listHD1.add(x);
                        }
                        listTemp.clear();
                    }

                    if (listHD1.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không có hóa đơn thỏa yêu cầu", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        loadTable(tbHoaDon, listHD);
                        reset();
                    } else {
                        loadTable(tbHoaDon, listHD1);
                    }
                }
            }
        });

        pnContent.setLayout(new BorderLayout());
        pnContent.add(pnTitleTop, BorderLayout.NORTH);
        lbList.setFont(sgUI15);
        pnTitleTop.setLayout(new BorderLayout());
        pnTitleTop.add(lbList, BorderLayout.CENTER);

        lbList.setHorizontalAlignment(JLabel.LEFT);
        lbList.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnContent.add(pnMid, BorderLayout.CENTER);
        cbNam.removeAllItems();
        cbNam.addItem("Chọn năm");
        cbThang.addItem("Chọn tháng");
        cbNgay.addItem("Chọn ngày");

        for (int i = 1; i <= 31; i++) {
            cbNgay.addItem("Ngày " + i);
        }

        for (int i = 1; i <= 12; i++) {
            cbThang.addItem("Tháng " + i);
        }

        for (Integer x : HoaDonBUS.getListYear()) {
            cbNam.addItem("Năm " + x);
        }

        pnMid.setLayout(new BorderLayout());
        pnMid.add(scp, BorderLayout.CENTER);
        pnMid.setBorder(new EmptyBorder(0, 10, 10, 10));
        cbNgay.setBackground(Color.white);
        cbNam.setBackground(Color.white);
        cbThang.setBackground(Color.white);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Date date1 = txtNgayThanhToan1.getDate();
//                Date date2 = txtNgayThanhToan2.getDate();
//                System.out.println(date1.after(date2));
            }
        });

        scp.setViewportView(tbHoaDon);
        loadTable(tbHoaDon, listHD);
        txtMaHD.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtMaHD.getText().equals("Mã hóa đơn")) {
                    txtMaHD.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtMaHD.getText().trim().length() == 0) {
                    txtMaHD.setText("Mã hóa đơn");
                }
            }
        });
        txtMaCTT.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtMaCTT.getText().equals("Mã chi tiết thuê")) {
                    txtMaCTT.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtMaCTT.getText().trim().length() == 0) {
                    txtMaCTT.setText("Mã chi tiết thuê");
                }
            }
        });
        txtNguoiLap.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtNguoiLap.getText().equals("Người lập")) {
                    txtNguoiLap.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtNguoiLap.getText().trim().length() == 0) {
                    txtNguoiLap.setText("Người lập");
                }
            }
        });
        loadTable(tbHoaDon, listHD);
    }

    public void reset() {
        txtMaHD.setText("Mã hóa đơn");
        txtMaCTT.setText("Mã chi tiết thuê");
        txtNgayThanhToan1.setCalendar(null);
        txtNgayThanhToan2.setCalendar(null);
        txtNguoiLap.setText("Người lập");
        txtTienDV.setSelectedIndex(0);
        txtTienPhong.setSelectedIndex(0);
        txtTongCong.setSelectedIndex(0);
        cbNam.setSelectedIndex(0);
        cbNgay.setSelectedIndex(0);
        cbThang.setSelectedIndex(0);
    }

    public int getDay(String date) {
        return Integer.parseInt(date.split("-")[0]);
    }

    public int getMonth(String date) {
        return Integer.parseInt(date.split("-")[1]);
    }

    public int getYear(String date) {
        return Integer.parseInt(date.split("-")[2]);
    }
//
//    public String changeDate(String date) {
//        String dateList[] = date.split("-");
//        String dateChange = dateList[2] + "-" + dateList[1] + "-" + dateList[0];
//        return dateChange;
//    }

    public void loadTable(JTable tbHoaDon, ArrayList<HoaDonDTO> listHD) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Mã hóa đơn");
        dtm.addColumn("Mã chi tiết thuê");
        dtm.addColumn("Ngày thanh toán");
        dtm.addColumn("Người lập");
        dtm.addColumn("Tiền phòng");
        dtm.addColumn("Tiền dịch vụ");
        dtm.addColumn("Giảm giá");
        dtm.addColumn("Tổng cộng");
        tbHoaDon.setModel(dtm);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        int i = 1;
        for (HoaDonDTO hd : listHD) {
            Object row[] = {i, hd.getMaHD(), hd.getMaChiTietThue(), hd.getNgayThanhToan(), NhanVienBUS.getTenNV(hd.getMaNV()), hd.getTienPhong(), hd.getTienDichVu(), hd.getGiamGia(), hd.getTongTien()};
            dtm.addRow(row);
            i++;
        }
        tbHoaDon.setShowGrid(false);
        tbHoaDon.setIntercellSpacing(new Dimension(0, 0));
        tbHoaDon.setRowHeight(40);
        tbHoaDon.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbHoaDon.getTableHeader().setPreferredSize(new Dimension(1, 40));
        DefaultTableCellRenderer renderBorder = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                JLabel lb = (JLabel) c;
                if (column == 0) {
                    lb.setBorder(new MatteBorder(0, 0, 1, 1, Color.decode("#DDDDDD")));
                    lb.setBackground(Color.decode("#97FFFF"));
                } else {
                    lb.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode("#DDDDDD")));
                }
                if (column == tbHoaDon.getColumnModel().getColumnIndex("Tiền phòng")) {
                    lb.setForeground(Color.red);
                    lb.setFont(sgUI13b);
                } else if (column == tbHoaDon.getColumnModel().getColumnIndex("Tiền dịch vụ")) {
                    lb.setForeground(Color.decode("#CD950C"));
                    lb.setFont(sgUI13b);
                } else if (column == tbHoaDon.getColumnModel().getColumnIndex("Giảm giá")) {
                    lb.setForeground(Color.decode("#489620"));
                    lb.setFont(sgUI13b);
                } else {
                    lb.setForeground(Color.black);
                    lb.setFont(sgUI13);
                }
                lb.setHorizontalAlignment(JLabel.CENTER);
                if (isSelected) {
                    lb.setBackground(Color.decode("#F5F5F5"));
                } else {
                    lb.setBackground(Color.decode("#FFFFFF"));
                }
                return lb;
            }
        };
        for (int j = 0; j < tbHoaDon.getColumnCount(); j++) {
            tbHoaDon.getColumnModel().getColumn(j).setCellRenderer(renderBorder);
        }

        tbHoaDon.getTableHeader().setFont(sgUI13b);
        tbHoaDon.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void lightDark(int lightDark) {
        if (lightDark == 1) {
            Color black = new Color(51, 51, 51);
            tbHoaDon.setBackground(Color.decode("#E0E0E0"));
            pnTitle.setBackground(black);
            lbTitle.setForeground(Color.white);
            pnCenter.setBackground(black);
            pnInput.setBackground(black);
            pnMid.setBackground(black);
            lbList.setBackground(Color.decode("#555555"));
            lbList.setOpaque(true);
            lbList.setForeground(Color.decode("#FFFFFF"));
            pnTitleTop.setBackground(black);
            pnTitleTop.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 0, 10), new MatteBorder(1, 1, 1, 1, Color.white)));
            scp.setBorder(new MatteBorder(0, 1, 1, 1, Color.white));
            scp.getViewport().setBackground(Color.decode("#707070"));
            pnBtn.setBackground(black);
            tbHoaDon.getTableHeader().setBackground(Color.decode("#898989"));
            pnSearchBottom.setBackground(black);
            pnNgay.setBackground(black);
            pnThang.setBackground(black);
            pnNam.setBackground(black);
            pnNgayThanhToan1.setBackground(black);
            pnNgayThanhToan2.setBackground(black);
            lbNgay.setForeground(Color.white);
            lbNgayThanhToan1.setForeground(Color.white);
            lbNgayThanhToan2.setForeground(Color.white);
            lbThang.setForeground(Color.white);
            lbNam.setForeground(Color.white);
            pnBtnReset.setBackground(black);
        } else {
            tbHoaDon.setBackground(Color.white);
            pnTitle.setBackground(Color.white);
            lbTitle.setForeground(Color.black);
            pnCenter.setBackground(Color.white);
            pnInput.setBackground(Color.white);
            pnMid.setBackground(Color.white);
            lbList.setBackground(Color.white);
            lbList.setOpaque(true);
            lbList.setForeground(Color.black);
            pnTitleTop.setBackground(Color.white);
            pnTitleTop.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 0, 10), new MatteBorder(1, 1, 1, 1, Color.black)));
            scp.setBorder(new MatteBorder(0, 1, 1, 1, Color.black));
            scp.getViewport().setBackground(Color.white);
            tbHoaDon.getTableHeader().setBackground(Color.decode("#79CDCD"));
            pnBtn.setBackground(Color.white);
            pnSearchBottom.setBackground(Color.white);
            pnNgay.setBackground(Color.white);
            pnThang.setBackground(Color.white);
            pnNam.setBackground(Color.white);
            pnNgayThanhToan1.setBackground(Color.white);
            pnNgayThanhToan2.setBackground(Color.white);
            lbNgay.setForeground(Color.black);
            lbNgayThanhToan1.setForeground(Color.black);
            lbNgayThanhToan2.setForeground(Color.black);
            lbThang.setForeground(Color.black);
            lbNam.setForeground(Color.black);
            pnBtnReset.setBackground(Color.white);
        }
    }
}
