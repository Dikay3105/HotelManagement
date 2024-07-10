package GUI;

import BUS.ChiTietThueBUS;
import BUS.KhachHangBUS;
import BUS.SuDungDichVuBUS;
import BUS.ThuePhongBUS;
import DTO.ChiTietThueDTO;
import DTO.DichVuDTO;
import DTO.KhachHangDTO;
import DTO.PhongDTO;
import DTO.SuDungDichVuDTO;
import DTO.ThuePhongDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class LapHoaDonGUI extends JFrame {

    private JPanel pnTop = new JPanel();
    private JPanel pnContent = new JPanel();
    private JPanel pnTitle = new JPanel();
    private JPanel pnRadio = new JPanel();

    private JLabel lbTitle = new JLabel("Lập hóa đơn");

    private JRadioButton rbtnKH = new JRadioButton("Thông tin khách hàng");
    private JRadioButton rbtnP = new JRadioButton("Thông tin thuê phòng");
    private JRadioButton rbtnDV = new JRadioButton("Thông tin dịch vụ");
    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);
    ArrayList<PhongDTO> listPhong = new ArrayList<>();
    ArrayList<ThuePhongDTO> listThuePhongDTO = new ArrayList<>();

    ArrayList<DichVuDTO> listDV = new ArrayList<>();
    ArrayList<SuDungDichVuDTO> listSuDungDV = new ArrayList<>();
    String maCTT;

    public LapHoaDonGUI(String maKH, String tenKH, String CMND, String SDT, int gt, String maCTT) {
        listThuePhongDTO = ThuePhongBUS.getListTP_P(maCTT, listPhong);
        listSuDungDV = SuDungDichVuBUS.getListSDDV_maDV(maCTT, listDV);
        this.maCTT = maCTT;
        initComponents(maKH, tenKH, CMND, SDT, gt, maCTT);
    }

    public void initComponents(String maKH, String tenKH, String CMND, String SDT, int gt, String maCTT) {
        setVisible(true);
        setSize(1400, 800);
        getContentPane().setBackground(Color.white);
        setTitle("Lập hóa đơn");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        lbTitle.setFont(sgUI18b);
        pnTop.setBackground(Color.white);
        pnTop.setLayout(new GridLayout(2, 1));
        pnTitle.setBackground(Color.white);
        pnTitle.add(lbTitle);
        pnTop.add(pnTitle);
        pnTop.add(pnRadio);

        pnRadio.add(rbtnKH);
        pnRadio.add(rbtnP);
        pnRadio.add(rbtnDV);

        rbtnKH.setSelected(true);
        rbtnKH.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                rbtnKH.setSelected(true);
                rbtnP.setEnabled(false);
                rbtnP.setSelected(false);
                rbtnDV.setEnabled(false);
                rbtnDV.setSelected(false);
                pnContent.removeAll();
                pnContent.revalidate();
                pnContent.repaint();
                renderKH();
            }
        });
        rbtnP.setEnabled(false);
        rbtnDV.setEnabled(false);
        rbtnP.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (rbtnP.isEnabled()) {
                    rbtnP.setSelected(true);
                } else {
                    rbtnP.setSelected(false);
                }
                rbtnDV.setEnabled(false);
                rbtnDV.setSelected(false);
                pnContent.removeAll();
                pnContent.revalidate();
                pnContent.repaint();
                renderP();
            }
        });

        cbGioiTinh.removeAllItems();
        cbGioiTinh.addItem("Nam");
        cbGioiTinh.addItem("Nữ");
        txtMaKH.setText(maKH);
        txtMaKH.setEditable(false);
        txtTenKH.setText(tenKH);
        txtCMND.setText(CMND);
        txtSDT.setText(SDT);
        cbGioiTinh.setSelectedIndex(gt);

        rbtnDV.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (rbtnP.isSelected() && rbtnDV.isEnabled()) {
                    rbtnDV.setSelected(true);
                } else {
                    rbtnDV.setSelected(false);
                }
            }
        });

        add(pnTop, BorderLayout.NORTH);
        add(pnContent, BorderLayout.CENTER);
        renderKH();
    }

    JTextField txtMaKH = new JTextField();
    JTextField txtTenKH = new JTextField();
    JTextField txtCMND = new JTextField();
    JTextField txtSDT = new JTextField();
    JComboBox cbGioiTinh = new JComboBox();

    public void renderKH() {
        pnContent.removeAll();
        pnContent.revalidate();
        pnContent.repaint();
        JPanel pnEmpty1 = new JPanel();
        JPanel pnCenter = new JPanel();
        JPanel pnEmpty2 = new JPanel();

//        pnEmpty1.setBackground(Color.white);
//        pnEmpty2.setBackground(Color.white);
        pnContent.setLayout(new GridLayout(1, 3));
        pnContent.add(pnEmpty1);
        pnEmpty1.setBackground(Color.decode("#DDDDDD"));
        pnEmpty2.setBackground(Color.decode("#DDDDDD"));
        pnContent.add(pnCenter);
        pnContent.add(pnEmpty2);

        pnCenter.setBackground(Color.white);

        JPanel pnTitle1 = new JPanel();
        JLabel lbTitle1 = new JLabel("Thông tin khách hàng");
        lbTitle1.setFont(sgUI15);
        pnTitle1.setBackground(Color.white);
        pnTitle1.add(lbTitle1);

        JPanel pnInfoKH = new JPanel();
        pnInfoKH.setBorder(new EmptyBorder(10, 10, 10, 10));
//        pnInfoKH.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.black), new EmptyBorder(10, 10, 10, 10)));
        pnInfoKH.setBackground(Color.white);
        pnCenter.setLayout(new BorderLayout());
        pnCenter.add(pnTitle1, BorderLayout.NORTH);
        pnCenter.add(pnInfoKH, BorderLayout.CENTER);
        pnCenter.setBorder(new EmptyBorder(0, 0, 10, 0));

        pnInfoKH.setLayout(new BorderLayout());

        JPanel pnInfo = new JPanel();
        pnInfo.setBackground(Color.white);
        JPanel pnBtn = new JPanel();
        pnBtn.setBackground(Color.white);

        pnInfoKH.add(pnInfo, BorderLayout.CENTER);
        pnInfoKH.add(pnBtn, BorderLayout.SOUTH);
        pnBtn.setLayout(new BorderLayout());
        JPanel pnEmptyBtn1 = new JPanel();
        JPanel pnEmptyBtn2 = new JPanel();
        JPanel pnBtnNext = new JPanel();

        pnBtn.add(pnEmptyBtn1, BorderLayout.WEST);
        pnBtn.add(pnEmptyBtn2, BorderLayout.CENTER);
        pnBtn.add(pnBtnNext, BorderLayout.EAST);
        pnEmptyBtn1.setBackground(Color.white);
        pnEmptyBtn2.setBackground(Color.white);
        pnBtnNext.setBackground(Color.white);

        pnBtn.setBackground(Color.white);
        JButton btnNext = new JButton("Tiếp theo");
        btnNext.setFocusPainted(false);

        btnNext.setBackground(new Color(51, 51, 51));
        btnNext.setForeground(Color.white);
        pnBtnNext.add(btnNext);

        pnInfo.setLayout(new GridLayout(6, 1));
        //mã khách hàng =========================================================
        JPanel pnMaKH = new JPanel();
        pnMaKH.setBackground(Color.white);
        pnMaKH.setLayout(new GridLayout(2, 1));

        JPanel pnMaKHinput = new JPanel();
        pnMaKHinput.setBackground(Color.white);

        JLabel lbMaKHeror = new JLabel("Mã khách hàng đã được nhập");
        lbMaKHeror.setForeground(Color.decode("#00DD00"));
        JPanel pnMaKHeror = new JPanel();
        pnMaKHeror.setLayout(new BorderLayout());
        pnMaKHeror.add(lbMaKHeror, BorderLayout.CENTER);
        pnMaKHeror.setBackground(Color.white);
        pnMaKHinput.setLayout(new BorderLayout());

        JLabel lbMaKH = new JLabel("Mã khách hàng:             ");
        lbMaKH.setHorizontalAlignment(JLabel.LEFT);
        pnMaKHinput.add(lbMaKH, BorderLayout.WEST);
        JPanel pnInputMaKH = new JPanel();
        pnMaKHinput.add(pnInputMaKH, BorderLayout.CENTER);
        pnInputMaKH.setLayout(new BorderLayout());
        pnInputMaKH.setBorder(new EmptyBorder(5, 10, 5, 0));
        pnInputMaKH.setBackground(Color.white);
        pnInputMaKH.add(txtMaKH, BorderLayout.CENTER);
        txtMaKH.setMargin(new Insets(0, 10, 0, 10));
        txtTenKH.setMargin(new Insets(0, 10, 0, 10));
        txtCMND.setMargin(new Insets(0, 10, 0, 10));
        txtSDT.setMargin(new Insets(0, 10, 0, 10));

        pnMaKH.add(pnMaKHinput);
        pnMaKH.add(pnMaKHeror);
        pnInfo.add(pnMaKH);
        //ten khách hàng =========================================================
        JPanel pnTenKH = new JPanel();
        pnTenKH.setBackground(Color.white);
        pnTenKH.setLayout(new GridLayout(2, 1));

        JPanel pnTenKHinput = new JPanel();
        pnTenKHinput.setBackground(Color.white);

        JLabel lbTenKHeror = new JLabel("Tên khách hàng đã được nhập");
        lbTenKHeror.setForeground(Color.decode("#00DD00"));
        JPanel pnTenKHeror = new JPanel();
        pnTenKHeror.setLayout(new BorderLayout());
        pnTenKHeror.add(lbTenKHeror, BorderLayout.CENTER);
        pnTenKHeror.setBackground(Color.white);
        pnTenKHinput.setLayout(new BorderLayout());

        JLabel lbTenKH = new JLabel("Tên khách hàng:            ");
        lbTenKH.setHorizontalAlignment(JLabel.LEFT);
        pnTenKHinput.add(lbTenKH, BorderLayout.WEST);
        JPanel pnInputTenKH = new JPanel();
        pnTenKHinput.add(pnInputTenKH, BorderLayout.CENTER);
        pnInputTenKH.setLayout(new BorderLayout());
        pnInputTenKH.setBorder(new EmptyBorder(5, 10, 5, 0));
        pnInputTenKH.setBackground(Color.white);
        pnInputTenKH.add(txtTenKH, BorderLayout.CENTER);

        pnTenKH.add(pnTenKHinput);
        pnTenKH.add(pnTenKHeror);
        pnInfo.add(pnTenKH);
        //Cmnd =========================================================
        JPanel pnCMND = new JPanel();
        pnCMND.setBackground(Color.white);
        pnCMND.setLayout(new GridLayout(2, 1));

        JPanel pnCMNDinput = new JPanel();
        pnCMNDinput.setBackground(Color.white);

        JLabel lbCMNDeror = new JLabel("Chứng minh nhân dân đã được nhập");
        lbCMNDeror.setForeground(Color.decode("#00DD00"));
        JPanel pnCMNDeror = new JPanel();
        pnCMNDeror.setLayout(new BorderLayout());
        pnCMNDeror.add(lbCMNDeror);
        pnCMNDeror.setBackground(Color.white);
        pnCMNDinput.setLayout(new BorderLayout());

        JLabel lbCMND = new JLabel("Chứng minh nhân dân: ");
        pnCMNDinput.add(lbCMND, BorderLayout.WEST);
        JPanel pnInputCMND = new JPanel();
        pnCMNDinput.add(pnInputCMND, BorderLayout.CENTER);
        pnInputCMND.setLayout(new BorderLayout());
        pnInputCMND.setBorder(new EmptyBorder(5, 10, 5, 0));
        pnInputCMND.setBackground(Color.white);
        pnInputCMND.add(txtCMND, BorderLayout.CENTER);

        pnCMND.add(pnCMNDinput);
        pnCMND.add(pnCMNDeror);
        pnInfo.add(pnCMND);
        //Gioi tinh =========================================================
        JPanel pnGioiTinh = new JPanel();
        pnGioiTinh.setBackground(Color.white);
        pnGioiTinh.setLayout(new GridLayout(2, 1));

        JPanel pnGioiTinhinput = new JPanel();
        pnGioiTinhinput.setBackground(Color.white);

        JLabel lbGioiTinheror = new JLabel("Giới tính đã được chọn");
        lbGioiTinheror.setForeground(Color.decode("#00DD00"));
        JPanel pnGioiTinheror = new JPanel();
        pnGioiTinheror.setLayout(new BorderLayout());
        pnGioiTinheror.add(lbGioiTinheror);
        pnGioiTinheror.setBackground(Color.white);
        pnGioiTinhinput.setLayout(new BorderLayout());

        JLabel lbGioiTinh = new JLabel("Giới tính:                        ");
        lbGioiTinh.setHorizontalAlignment(JLabel.LEFT);
        pnGioiTinhinput.add(lbGioiTinh, BorderLayout.WEST);
        JPanel pnInputGioiTinh = new JPanel();
        pnGioiTinhinput.add(pnInputGioiTinh, BorderLayout.CENTER);
        pnInputGioiTinh.setLayout(new BorderLayout());
        pnInputGioiTinh.setBorder(new EmptyBorder(5, 10, 5, 0));
        pnInputGioiTinh.setBackground(Color.white);
        pnInputGioiTinh.add(cbGioiTinh, BorderLayout.CENTER);

        cbGioiTinh.setBackground(Color.white);

        pnGioiTinh.add(pnGioiTinhinput);
        pnGioiTinh.add(pnGioiTinheror);
        pnInfo.add(pnGioiTinh);
        //sdt =========================================================
        JPanel pnSDT = new JPanel();
        pnSDT.setBackground(Color.white);
        pnSDT.setLayout(new GridLayout(2, 1));

        JPanel pnSDTinput = new JPanel();
        pnSDTinput.setBackground(Color.white);

        JLabel lbSDTeror = new JLabel("Số điện thoại đã được nhập");
        lbSDTeror.setForeground(Color.decode("#00DD00"));
        JPanel pnSDTeror = new JPanel();
        pnSDTeror.setLayout(new BorderLayout());
        pnSDTeror.add(lbSDTeror, BorderLayout.CENTER);
        pnSDTeror.setBackground(Color.white);
        pnSDTinput.setLayout(new BorderLayout());

        JLabel lbSDT = new JLabel("Số điện thoại:                 ");
        pnSDTinput.add(lbSDT, BorderLayout.WEST);
        JPanel pnInputSDT = new JPanel();
        pnSDTinput.add(pnInputSDT, BorderLayout.CENTER);
        pnInputSDT.setLayout(new BorderLayout());
        pnInputSDT.setBorder(new EmptyBorder(5, 10, 5, 0));
        pnInputSDT.setBackground(Color.white);
        pnInputSDT.add(txtSDT, BorderLayout.CENTER);

        pnSDT.add(pnSDTinput);
        pnSDT.add(pnSDTeror);
        pnInfo.add(pnSDT);

        JPanel pnBtnBottom = new JPanel();
        pnBtnBottom.setBackground(Color.white);
        pnBtnBottom.setLayout(new GridLayout(2, 1));
        pnBtnBottom.setBackground(Color.white);

        JPanel pnBtnBottomTop = new JPanel();
        pnBtnBottomTop.setLayout(new GridLayout(1, 3));
        pnBtnBottomTop.add(new JPanel());
        JPanel pnBtnSave = new JPanel();
        pnBtnSave.setBackground(Color.red);
        pnBtnSave.setLayout(new BorderLayout());
        pnBtnBottomTop.add(pnBtnSave, BorderLayout.CENTER);
        pnBtnBottomTop.setBorder(new EmptyBorder(10, 5, 10, 5));
        pnBtnBottomTop.add(new JPanel());
        pnBtnBottom.add(pnBtnBottomTop);

        JButton btnSave = new JButton("Lưu");
        pnBtnSave.add(btnSave);
        btnSave.setFocusPainted(false);
        btnSave.setBackground(Color.WHITE);

        pnInfo.add(pnBtnBottom);

        txtTenKH.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtTenKH.getText().trim().length() == 0) {
                    lbTenKHeror.setText("Tên khách hàng không được để trống");
                    lbTenKHeror.setForeground(Color.red);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtTenKH.getText().trim().length() != 0) {
                    lbTenKHeror.setText("Tên khách hàng đã được nhập");
                    lbTenKHeror.setForeground(Color.decode("#00DD00"));
                } else {
                    lbTenKHeror.setText("Tên khách hàng không được để trống");
                    lbTenKHeror.setForeground(Color.red);
                }
            }
        });

        txtSDT.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtSDT.getText().trim().length() == 0) {
                    lbSDTeror.setText("Số điện thoại không được để trống");
                    lbSDTeror.setForeground(Color.red);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtSDT.getText().trim().length() != 0) {
                    lbSDTeror.setText("Số điện thoại đã được nhập");
                    lbSDTeror.setForeground(Color.decode("#00DD00"));
                } else {
                    lbSDTeror.setText("Số điện thoại không được để trống");
                    lbSDTeror.setForeground(Color.red);
                }
            }
        });

        txtCMND.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtCMND.getText().trim().length() == 0) {
                    lbCMNDeror.setText("Chứng minh nhân dân không được để trống");
                    lbCMNDeror.setForeground(Color.red);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtCMND.getText().trim().length() != 0) {
                    lbCMNDeror.setText("Chứng minh nhân dân đã được nhập");
                    lbCMNDeror.setForeground(Color.decode("#00DD00"));
                } else {
                    lbCMNDeror.setText("Chứng minh nhân dân không được để trống");
                    lbCMNDeror.setForeground(Color.red);
                }
            }
        });
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMaKH.getText().trim().length() != 0
                        && txtTenKH.getText().trim().length() != 0
                        && txtSDT.getText().trim().length() != 0
                        && txtCMND.getText().trim().length() != 0) {
                    rbtnP.setEnabled(true);
                    rbtnP.setSelected(true);
                    renderP();
                } else {
                    JOptionPane.showMessageDialog(null, "Mời nhập thông tin đầy đủ", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean check = false;
                if (txtTenKH.getText().trim().length() != 0
                        && txtCMND.getText().trim().length() != 0
                        && txtSDT.getText().trim().length() != 0) {
                    check = true;
                } else {
                    check = false;
                }
                if (check) {
                    if (txtCMND.getText().trim().length() > 10) {
                        JOptionPane.showMessageDialog(null, "Chứng minh nhân dân phải dưới 10 ký tự", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        txtCMND.setText("");
                        txtCMND.requestFocus();
                    } else {
                        if (txtSDT.getText().trim().length() != 10) {
                            JOptionPane.showMessageDialog(null, "Số điện thoại sai", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            txtSDT.setText("");
                            txtSDT.requestFocus();
                        } else {
                            KhachHangDTO khDTO = new KhachHangDTO();
                            khDTO.setMaKH(txtMaKH.getText());
                            khDTO.setTenKH(txtTenKH.getText().trim());
                            khDTO.setCmnd(txtCMND.getText().trim());
                            khDTO.setSdt(txtSDT.getText().trim());
                            khDTO.setGioiTinh(cbGioiTinh.getItemAt(cbGioiTinh.getSelectedIndex()).toString());
                            KhachHangBUS khBUS = new KhachHangBUS();
                            try {
                                khBUS.UpdateKH(khDTO);
                                JOptionPane.showMessageDialog(null, "Sửa thông tin " + txtMaKH.getText() + " thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Sửa thông tin " + txtMaKH.getText() + " không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public void renderP() {
        pnContent.removeAll();
        pnContent.revalidate();
        pnContent.repaint();

        pnContent.setLayout(new BorderLayout());
        JPanel pnEmpty1 = new JPanel();
        JPanel pnCenter = new JPanel();
        JPanel pnEmpty2 = new JPanel();
//        pnContent.add(pnEmpty1, BorderLayout.WEST);
        pnContent.add(pnCenter, BorderLayout.CENTER);
        pnContent.add(pnEmpty2, BorderLayout.EAST);
        pnEmpty1.setBackground(Color.decode("#DDDDDD"));
        pnEmpty2.setBackground(Color.decode("#DDDDDD"));
        pnCenter.setBackground(Color.white);
        pnEmpty2.setBackground(Color.white);

        JLabel lbTitle = new JLabel("Thông tin phòng thuê");

        JPanel pnContentCenter = new JPanel();
        pnContentCenter.setBackground(Color.white);
        JPanel pnBtn = new JPanel();

        JPanel pnTop = new JPanel();
        pnTop.setLayout(new BorderLayout());
        pnTop.add(lbTitle, BorderLayout.CENTER);
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        pnTop.setBackground(Color.white);
        lbTitle.setFont(sgUI15);

        pnCenter.setLayout(new BorderLayout());
        pnCenter.add(pnTop, BorderLayout.NORTH);
        pnCenter.add(pnContentCenter, BorderLayout.CENTER);

        pnBtn.setBackground(Color.white);
        pnBtn.setLayout(new BorderLayout());
        pnBtn.setBorder(new EmptyBorder(20, 20, 20, 20));
        pnCenter.add(pnBtn, BorderLayout.SOUTH);

        JPanel pnEmptyBtn1 = new JPanel();
        pnEmptyBtn1.setLayout(new BorderLayout());

        JPanel pnEmptyBtn2 = new JPanel();

        JPanel pnEmptyBtn3 = new JPanel();
        pnEmptyBtn3.setLayout(new BorderLayout());

        pnBtn.add(pnEmptyBtn1, BorderLayout.WEST);
        pnEmptyBtn1.setBackground(Color.black);
        pnEmptyBtn3.setBackground(Color.yellow);
        pnBtn.add(pnEmptyBtn2, BorderLayout.CENTER);
        pnEmptyBtn2.setBackground(Color.white);
        pnBtn.add(pnEmptyBtn3, BorderLayout.EAST);

        JButton btnReturn = new JButton("Quay lại");
        JButton btnNext = new JButton("Tiếp theo");
        pnEmptyBtn1.add(btnReturn, BorderLayout.CENTER);
        pnEmptyBtn3.add(btnNext, BorderLayout.CENTER);

        btnReturn.setFocusPainted(false);
        btnReturn.setBackground(new Color(51, 51, 51));
        btnReturn.setForeground(Color.white);

        btnNext.setFocusPainted(false);
        btnNext.setBackground(new Color(51, 51, 51));
        btnNext.setForeground(Color.white);

        pnContentCenter.setLayout(new BorderLayout());
        pnContentCenter.setBorder(new EmptyBorder(5, 20, 5, 20));
        JLabel lbTopTitle = new JLabel("DANH SÁCH PHÒNG THUÊ");
        lbTopTitle.setFont(sgUI13b);

        JPanel pnTopTitle = new JPanel();
        pnTopTitle.setBackground(Color.white);
        pnTopTitle.setLayout(new BorderLayout());
        pnTopTitle.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 0, 1, Color.black), new EmptyBorder(10, 10, 10, 0)));
        pnTopTitle.add(lbTopTitle, BorderLayout.CENTER);

        JScrollPane scp = new JScrollPane();
        scp.getViewport().setBackground(Color.white);
        JPanel pnTable = new JPanel();
        pnTable.setLayout(new BorderLayout());
        pnTable.add(scp, BorderLayout.CENTER);
        scp.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));

        JTable tbPhong = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        scp.getViewport().add(tbPhong);
        renderTable(tbPhong);
        pnContentCenter.add(pnTopTitle, BorderLayout.NORTH);
        pnContentCenter.add(pnTable, BorderLayout.CENTER);

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rbtnDV.setEnabled(true);
                rbtnDV.setSelected(true);
                renderDV();
            }
        });

        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rbtnP.setEnabled(false);
                rbtnP.setSelected(false);
                rbtnDV.setEnabled(false);
                rbtnDV.setSelected(false);
                if (rbtnP.isEnabled()) {
                    rbtnP.setSelected(true);
                } else {
                    rbtnP.setSelected(false);
                }
                rbtnDV.setEnabled(false);
                rbtnDV.setSelected(false);
                renderKH();
            }
        });

        tbPhong.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String tenP = tbPhong.getValueAt(tbPhong.getSelectedRow(), tbPhong.getColumnModel().getColumnIndex("Tên phòng")).toString();
                String ngayThue = tbPhong.getValueAt(tbPhong.getSelectedRow(), tbPhong.getColumnModel().getColumnIndex("Ngày thuê")).toString();
                String ngayTra = tbPhong.getValueAt(tbPhong.getSelectedRow(), tbPhong.getColumnModel().getColumnIndex("Ngày trả")).toString();
                String loaiP = tbPhong.getValueAt(tbPhong.getSelectedRow(), tbPhong.getColumnModel().getColumnIndex("Loại phòng")).toString();
                String loaiHinhThue = tbPhong.getValueAt(tbPhong.getSelectedRow(), tbPhong.getColumnModel().getColumnIndex("Loại hình thuê")).toString();
                String giaP = tbPhong.getValueAt(tbPhong.getSelectedRow(), tbPhong.getColumnModel().getColumnIndex("Giá phòng")).toString();
                String ngayCheckOut = tbPhong.getValueAt(tbPhong.getSelectedRow(), tbPhong.getColumnModel().getColumnIndex("Ngày checkout")).toString();
                renderInfo(pnEmpty2, tenP, ngayThue, ngayTra, loaiP, loaiHinhThue, giaP, ngayCheckOut);
            }
        });

    }

    public void renderInfo(JPanel pnEmpty2, String tenP, String ngayThue, String ngayTra, String loaiP, String loaiHinhThue, String giaP, String ngayCheckOut) {
        pnEmpty2.setLayout(new BorderLayout());
        pnEmpty2.removeAll();
        pnEmpty2.revalidate();
        pnEmpty2.repaint();

        JPanel pnContent = new JPanel();

        pnEmpty2.setBorder(new EmptyBorder(25, 0, 20, 20));
        pnEmpty2.add(pnContent, BorderLayout.CENTER);

        pnContent.setLayout(new BorderLayout());

        JLabel lbTitle = new JLabel("Thông tin phòng đặt                                        ");
        lbTitle.setFont(sgUI13b);
        JPanel pnTitle = new JPanel();
        pnTitle.setLayout(new BorderLayout());
        pnTitle.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.black), new EmptyBorder(5, 5, 5, 5)));
        pnTitle.add(lbTitle, BorderLayout.CENTER);

        pnContent.add(pnTitle, BorderLayout.NORTH);
        JPanel pnCenter = new JPanel();
        pnContent.add(pnCenter, BorderLayout.CENTER);

        pnCenter.setLayout(new GridLayout(2, 1));

        JPanel pnInfoPhong = new JPanel();
        pnInfoPhong.setBackground(Color.white);
        JPanel pnTienIch = new JPanel();
        pnInfoPhong.setLayout(new GridLayout(7, 1));
        //tên phòng
        JPanel pnTenP = new JPanel();
        pnTenP.setLayout(new BorderLayout());
        JPanel pnlbTenP = new JPanel();

        pnTenP.add(pnlbTenP, BorderLayout.WEST);
        JLabel lbTenP = new JLabel("Tên phòng:");
        pnlbTenP.setLayout(new BorderLayout());
        lbTenP.setFont(sgUI13b);
        pnlbTenP.add(lbTenP, BorderLayout.CENTER);

        JPanel pnTxtTenP = new JPanel();
        pnTenP.add(pnTxtTenP, BorderLayout.CENTER);
        JLabel txtTenP = new JLabel(tenP);
        txtTenP.setFont(sgUI13);
        pnTxtTenP.setLayout(new BorderLayout());
        pnTxtTenP.add(txtTenP, BorderLayout.CENTER);

        pnTenP.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
        pnlbTenP.setBorder(new EmptyBorder(0, 10, 0, 10));
        pnlbTenP.setBackground(Color.white);
        pnTxtTenP.setBackground(Color.white);

        pnInfoPhong.add(pnTenP);
        //Loại phòng
        JPanel pnLoaiP = new JPanel();
        pnLoaiP.setLayout(new BorderLayout());
        JPanel pnlbLoaiP = new JPanel();

        pnLoaiP.add(pnlbLoaiP, BorderLayout.WEST);
        JLabel lbLoaiP = new JLabel("Loại phòng:");
        pnlbLoaiP.setLayout(new BorderLayout());
        lbLoaiP.setFont(sgUI13b);
        pnlbLoaiP.add(lbLoaiP, BorderLayout.CENTER);

        JPanel pnTxtLoaiP = new JPanel();
        pnLoaiP.add(pnTxtLoaiP, BorderLayout.CENTER);
        JLabel txtLoaiP = new JLabel(loaiP);
        txtLoaiP.setFont(sgUI13);
        pnTxtLoaiP.setLayout(new BorderLayout());
        pnTxtLoaiP.add(txtLoaiP, BorderLayout.CENTER);

        pnLoaiP.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
        pnlbLoaiP.setBorder(new EmptyBorder(0, 10, 0, 10));
        pnlbLoaiP.setBackground(Color.white);
        pnTxtLoaiP.setBackground(Color.white);
        pnInfoPhong.add(pnLoaiP);
        //ngày thuê phòng
        JPanel pnNgayThueP = new JPanel();
        pnNgayThueP.setLayout(new BorderLayout());
        JPanel pnlbNgayThueP = new JPanel();

        pnNgayThueP.add(pnlbNgayThueP, BorderLayout.WEST);
        JLabel lbNgayThueP = new JLabel("Ngày thuê:");
        pnlbNgayThueP.setLayout(new BorderLayout());
        lbNgayThueP.setFont(sgUI13b);
        pnlbNgayThueP.add(lbNgayThueP, BorderLayout.CENTER);

        JPanel pnTxtNgayThueP = new JPanel();
        pnNgayThueP.add(pnTxtNgayThueP, BorderLayout.CENTER);
        JLabel txtNgayThueP = new JLabel(ngayThue);
        txtNgayThueP.setFont(sgUI13);
        pnTxtNgayThueP.setLayout(new BorderLayout());
        pnTxtNgayThueP.add(txtNgayThueP, BorderLayout.CENTER);

        pnNgayThueP.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
        pnlbNgayThueP.setBorder(new EmptyBorder(0, 10, 0, 10));
        pnlbNgayThueP.setBackground(Color.white);
        pnTxtNgayThueP.setBackground(Color.white);
        pnInfoPhong.add(pnNgayThueP);
        //ngày trả phòng
        JPanel pnNgayTraP = new JPanel();
        pnNgayTraP.setLayout(new BorderLayout());
        JPanel pnlbNgayTraP = new JPanel();

        pnNgayTraP.add(pnlbNgayTraP, BorderLayout.WEST);
        JLabel lbNgayTraP = new JLabel("Ngày trả:");
        pnlbNgayTraP.setLayout(new BorderLayout());
        lbNgayTraP.setFont(sgUI13b);
        pnlbNgayTraP.add(lbNgayTraP, BorderLayout.CENTER);

        JPanel pnTxtNgayTraP = new JPanel();
        pnNgayTraP.add(pnTxtNgayTraP, BorderLayout.CENTER);
        JLabel txtNgayTraP = new JLabel(ngayTra);
        txtNgayTraP.setFont(sgUI13);
        pnTxtNgayTraP.setLayout(new BorderLayout());
        pnTxtNgayTraP.add(txtNgayTraP, BorderLayout.CENTER);

        pnNgayTraP.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
        pnlbNgayTraP.setBorder(new EmptyBorder(0, 10, 0, 10));
        pnlbNgayTraP.setBackground(Color.white);
        pnTxtNgayTraP.setBackground(Color.white);
        pnInfoPhong.add(pnNgayTraP);
        //Loại hinh phòng
        JPanel pnLoaiHinh = new JPanel();
        pnLoaiHinh.setLayout(new BorderLayout());
        JPanel pnlbLoaiHinh = new JPanel();

        pnLoaiHinh.add(pnlbLoaiHinh, BorderLayout.WEST);
        JLabel lbLoaiHinh = new JLabel("Loại hình thuê:");
        pnlbLoaiHinh.setLayout(new BorderLayout());
        lbLoaiHinh.setFont(sgUI13b);
        pnlbLoaiHinh.add(lbLoaiHinh, BorderLayout.CENTER);

        JPanel pnTxtLoaiHinh = new JPanel();
        pnLoaiHinh.add(pnTxtLoaiHinh, BorderLayout.CENTER);
        JLabel txtLoaiHinh = new JLabel(loaiHinhThue);
        txtLoaiHinh.setFont(sgUI13);
        pnTxtLoaiHinh.setLayout(new BorderLayout());
        pnTxtLoaiHinh.add(txtLoaiHinh, BorderLayout.CENTER);

        pnLoaiHinh.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
        pnlbLoaiHinh.setBorder(new EmptyBorder(0, 10, 0, 10));
        pnlbLoaiHinh.setBackground(Color.white);
        pnTxtLoaiHinh.setBackground(Color.white);
        pnInfoPhong.add(pnLoaiHinh);
        //Ngay checkout
        JPanel pnGiaP = new JPanel();
        pnGiaP.setLayout(new BorderLayout());
        JPanel pnlbGiaP = new JPanel();

        pnGiaP.add(pnlbGiaP, BorderLayout.WEST);
        JLabel lbGiaP = new JLabel("Giá phòng:");
        pnlbGiaP.setLayout(new BorderLayout());
        lbGiaP.setFont(sgUI13b);
        pnlbGiaP.add(lbGiaP, BorderLayout.CENTER);

        JPanel pnTxtGiaP = new JPanel();
        pnGiaP.add(pnTxtGiaP, BorderLayout.CENTER);
        JLabel txtGiaP = new JLabel(giaP);
        txtGiaP.setFont(sgUI13);
        pnTxtGiaP.setLayout(new BorderLayout());
        pnTxtGiaP.add(txtGiaP, BorderLayout.CENTER);

        pnGiaP.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
        pnlbGiaP.setBorder(new EmptyBorder(0, 10, 0, 10));
        pnlbGiaP.setBackground(Color.white);
        pnTxtGiaP.setBackground(Color.white);
        pnInfoPhong.add(pnGiaP);
        //Ngay checkout
        JPanel pnNgayCheckOutP = new JPanel();
        pnNgayCheckOutP.setLayout(new BorderLayout());
        JPanel pnlbNgayCheckOutP = new JPanel();

        pnNgayCheckOutP.add(pnlbNgayCheckOutP, BorderLayout.WEST);
        JLabel lbNgayCheckOutP = new JLabel("Ngày checkout:");
        pnlbNgayCheckOutP.setLayout(new BorderLayout());
        lbNgayCheckOutP.setFont(sgUI13b);
        pnlbNgayCheckOutP.add(lbNgayCheckOutP, BorderLayout.CENTER);

        JPanel pnTxtNgayCheckOutP = new JPanel();
        pnNgayCheckOutP.add(pnTxtNgayCheckOutP, BorderLayout.CENTER);
        JLabel txtNgayCheckOutP = new JLabel(ngayCheckOut);
        txtNgayCheckOutP.setFont(sgUI13);
        pnTxtNgayCheckOutP.setLayout(new BorderLayout());
        pnTxtNgayCheckOutP.add(txtNgayCheckOutP, BorderLayout.CENTER);

        pnNgayCheckOutP.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
        pnlbNgayCheckOutP.setBorder(new EmptyBorder(0, 10, 0, 10));

        pnInfoPhong.add(pnNgayCheckOutP);
        pnlbNgayCheckOutP.setBackground(Color.white);
        pnTxtNgayCheckOutP.setBackground(Color.white);
        pnCenter.add(pnInfoPhong);
        pnCenter.add(pnTienIch);

    }

    public void renderDV() {
        pnContent.removeAll();
        pnContent.revalidate();
        pnContent.repaint();
        pnContent.setLayout(new BorderLayout());
        JPanel pnEmpty1 = new JPanel();
        JPanel pnCenter = new JPanel();
        JPanel pnEmpty2 = new JPanel();
        pnContent.add(pnEmpty1, BorderLayout.WEST);
        pnContent.add(pnCenter, BorderLayout.CENTER);
        pnContent.add(pnEmpty2, BorderLayout.EAST);
        pnEmpty1.setBackground(Color.decode("#DDDDDD"));
        pnEmpty2.setBackground(Color.decode("#DDDDDD"));
        pnCenter.setBackground(Color.white);
        JLabel lbEmpty1 = new JLabel("                                          ");
        JLabel lbEmpty2 = new JLabel("                                          ");
        pnEmpty1.add(lbEmpty1);
        pnEmpty2.add(lbEmpty2);
        JLabel lbTitle = new JLabel("Thông tin dịch vụ");

        JPanel pnContentCenter = new JPanel();
        pnContentCenter.setBackground(Color.white);
        JPanel pnBtn = new JPanel();

        JPanel pnTop = new JPanel();
        pnTop.setLayout(new BorderLayout());
        pnTop.add(lbTitle, BorderLayout.CENTER);
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        pnTop.setBackground(Color.white);
        lbTitle.setFont(sgUI15);

        pnCenter.setLayout(new BorderLayout());
        pnCenter.add(pnTop, BorderLayout.NORTH);
        pnCenter.add(pnContentCenter, BorderLayout.CENTER);

        pnBtn.setBackground(Color.white);
        pnBtn.setLayout(new BorderLayout());
        pnBtn.setBorder(new EmptyBorder(20, 20, 20, 20));
        pnCenter.add(pnBtn, BorderLayout.SOUTH);

        JPanel pnEmptyBtn1 = new JPanel();
        pnEmptyBtn1.setLayout(new BorderLayout());

        JPanel pnEmptyBtn2 = new JPanel();

        JPanel pnEmptyBtn3 = new JPanel();
        pnEmptyBtn3.setLayout(new BorderLayout());

        pnBtn.add(pnEmptyBtn1, BorderLayout.WEST);
        pnEmptyBtn1.setBackground(Color.black);
        pnEmptyBtn3.setBackground(Color.yellow);
        pnBtn.add(pnEmptyBtn2, BorderLayout.CENTER);
        pnEmptyBtn2.setBackground(Color.white);
        pnBtn.add(pnEmptyBtn3, BorderLayout.EAST);

        JButton btnReturn = new JButton("Quay lại");
        JButton btnNext = new JButton("Tiếp theo");
        pnEmptyBtn1.add(btnReturn, BorderLayout.CENTER);
        pnEmptyBtn3.add(btnNext, BorderLayout.CENTER);

        btnReturn.setFocusPainted(false);
        btnReturn.setBackground(new Color(51, 51, 51));
        btnReturn.setForeground(Color.white);

        btnNext.setFocusPainted(false);
        btnNext.setBackground(new Color(51, 51, 51));
        btnNext.setForeground(Color.white);

        pnContentCenter.setLayout(new BorderLayout());
        pnContentCenter.setBorder(new EmptyBorder(5, 20, 5, 20));
        JLabel lbTopTitle = new JLabel("DANH SÁCH DỊCH VỤ");
        lbTopTitle.setFont(sgUI13b);

        JPanel pnTopTitle = new JPanel();
        pnTopTitle.setBackground(Color.white);
        pnTopTitle.setLayout(new BorderLayout());
        pnTopTitle.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 0, 1, Color.black), new EmptyBorder(10, 10, 10, 0)));
        pnTopTitle.add(lbTopTitle, BorderLayout.CENTER);

        JScrollPane scp = new JScrollPane();
        scp.getViewport().setBackground(Color.white);
        JPanel pnTable = new JPanel();
        pnTable.setLayout(new BorderLayout());
        pnTable.add(scp, BorderLayout.CENTER);
        scp.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        JTable tbPhong = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        scp.getViewport().add(tbPhong);
        renderTableDV(tbPhong);
        pnContentCenter.add(pnTopTitle, BorderLayout.NORTH);
        pnContentCenter.add(pnTable, BorderLayout.CENTER);

        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rbtnDV.setEnabled(false);
                rbtnDV.setSelected(false);
                renderP();
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        ArrayList<ChiTietThueDTO> ChiTietThueList = ChiTietThueBUS.getListCTT();
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                for (ChiTietThueDTO CTT : ChiTietThueList) {
                    if (CTT.getMaChiTietThue().equals(maCTT)) {
                        new FormHoaDon(CTT);
                    }
                }
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    public void renderTableDV(JTable tbPhong) {
        tbPhong.setBorder(null);
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Mã dịch vụ");
        dtm.addColumn("Tên dịch vụ");
        dtm.addColumn("Loại dịch vụ");
        dtm.addColumn("Ngày sử dụng");
        dtm.addColumn("Số lượng");
        dtm.addColumn("Đơn giá");
        dtm.addColumn("Giá dịch vụ");
        tbPhong.setModel(dtm);
        tbPhong.setRowHeight(40);
        int i = 0;
        for (SuDungDichVuDTO x : listSuDungDV) {
            Object row[] = {i + 1, x.getMaDV(), listDV.get(i).getTenDV(), listDV.get(i).getTenLoaiDV(), x.getNgaySuDungString(), x.getSoLuong(), listDV.get(i).getGiaDV(), x.getDonGia()};
            dtm.addRow(row);
            i++;
        }
        tbPhong.setShowGrid(false);
        tbPhong.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbPhong.getTableHeader().setFont(sgUI13b);
        tbPhong.setIntercellSpacing(new Dimension(0, 0));
        DefaultTableCellRenderer RendererBorder = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//                String txt = (String) table.getValueAt(row, column);
                JLabel lb = (JLabel) c;
                lb.setHorizontalAlignment(JLabel.CENTER);
                lb.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
                if (isSelected) {
                    lb.setBackground(Color.decode("#F5F5F5"));
                } else {
                    lb.setBackground(Color.decode("#FFFFFF"));
                }
                return lb;
            }
        };
        tbPhong.getTableHeader().setPreferredSize(new Dimension(1, 40));
        for (int j = 0; j < tbPhong.getColumnCount(); j++) {
            tbPhong.getColumnModel().getColumn(j).setCellRenderer(RendererBorder);
        }
    }

    public void renderTable(JTable tbPhong) {
        tbPhong.setBorder(null);
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Mã phòng");
        dtm.addColumn("Tên phòng");
        dtm.addColumn("Loại phòng");
        dtm.addColumn("Ngày thuê");
        dtm.addColumn("Ngày trả");
        dtm.addColumn("Loại hình thuê");
        dtm.addColumn("Giá phòng");
        dtm.addColumn("Ngày checkout");
        tbPhong.setModel(dtm);
        tbPhong.setRowHeight(40);
        int j = 0;
        for (ThuePhongDTO x : listThuePhongDTO) {
            Object row[] = {j + 1, x.getMaP(), listPhong.get(j).getTenP(), listPhong.get(j).getLoaiP(), x.getNgayThue(), x.getNgayTra(), x.getLoaiHinhThue(), x.getGia(), x.getNgayCheckOut()};
            dtm.addRow(row);
            j++;
        }

        tbPhong.setShowGrid(false);
        tbPhong.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbPhong.getTableHeader().setFont(sgUI13b);
        DefaultTableCellRenderer Renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String txt = (String) table.getValueAt(row, column);
                JLabel lb = (JLabel) c;
                lb.setHorizontalAlignment(JLabel.CENTER);
                if (txt.equals("Theo ngày")) {
                    lb.setForeground(Color.decode("#3366CC"));
                    lb.setFont(sgUI13);
                    lb.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
                } else if (txt.equals("Theo giờ")) {
                    lb.setForeground(Color.decode("#00CC00"));
                    lb.setFont(sgUI13);
                    lb.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
                }
                if (isSelected) {
                    lb.setBackground(Color.decode("#F5F5F5"));
                } else {
                    lb.setBackground(Color.decode("#FFFFFF"));
                }
                return lb;
            }
        };
        tbPhong.setIntercellSpacing(new Dimension(0, 0));
        DefaultTableCellRenderer RendererBorder = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//                String txt = (String) table.getValueAt(row, column);
                JLabel lb = (JLabel) c;
                lb.setHorizontalAlignment(JLabel.CENTER);
                lb.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
                if (lb.getText().equals("Theo Ngày")) {
                    lb.setForeground(Color.decode("#3366CC"));
                } else if (lb.getText().equals("Theo Giờ")) {
                    lb.setForeground(Color.decode("#00CC00"));
                } else {
                    lb.setForeground(Color.black);
                }
                if (isSelected) {
                    lb.setBackground(Color.decode("#F5F5F5"));
                } else {
                    lb.setBackground(Color.decode("#FFFFFF"));
                }
                return lb;
            }
        };

        Renderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tbPhong.getColumnCount(); i++) {
            tbPhong.getColumnModel().getColumn(i).setCellRenderer(RendererBorder);
        }

        tbPhong.getTableHeader().setPreferredSize(new Dimension(1, 40));
    }
}
