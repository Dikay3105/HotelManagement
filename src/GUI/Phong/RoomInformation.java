package GUI.Phong;

import BUS.ChiTietTienIchBUS;
import BUS.PhongBUS;
import BUS.ThuePhongBUS;
import BUS.TienIchBUS;
import DTO.ChiTietTienIchDTO;
import DTO.PhongDTO;
import DTO.ThuePhongDTO;
import DTO.TienIchDTO;
import GUI.PanelPhong;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class RoomInformation extends JFrame {

    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);
    JLabel lbTitle = new JLabel("Thông tin phòng");

    JLabel lbPhong = new JLabel("Thông tin về phòng");
    JLabel lbTienIch = new JLabel("Tiện ích của phòng");
    JLabel lbKH = new JLabel("Thông tin thuê của phòng                             ");
    JLabel lbXuLy = new JLabel("Chức năng");

    JPanel pnContent = new JPanel();
    JPanel pnPhong = new JPanel();
    JPanel pnKH_XuLy = new JPanel();

    JPanel pnPhongTop = new JPanel();
    JPanel pnPhongBottom = new JPanel();

    JPanel pnKH = new JPanel();
    JPanel pnXuLy = new JPanel();

    JPanel pnPhongCenter = new JPanel();

    JPanel pnMaP = new JPanel();
    JPanel pnTenP = new JPanel();
    JPanel pnLoaiP = new JPanel();
    JPanel pnTinhTrangP = new JPanel();
    JPanel pnHienTrangP = new JPanel();
    JPanel pnGiaP = new JPanel();

    JLabel lbMaP = new JLabel("Mã phòng:");
    JLabel lbTenP = new JLabel("Tên phòng:");
    JLabel lbLoaiP = new JLabel("Loại phòng:");
    JLabel lbGiaP = new JLabel("Giá phòng:");
    JLabel lbTinhTrangP = new JLabel("Tình trạng:");
    JLabel lbHienTrangP = new JLabel("Hiện trạng:");

    JTextField txtMaP = new JTextField();
    JTextField txtTenP = new JTextField();
    JPanel txtLoaiP = new JPanel();
    JRadioButton rbVip = new JRadioButton("VIP");
    JRadioButton rbThuong = new JRadioButton("Thường");
    ButtonGroup bgLoai = new ButtonGroup();
    JTextField txtGiaP = new JTextField();
    JPanel txtTinhTrangP = new JPanel();
    JRadioButton rb1 = new JRadioButton("Trống");
    JRadioButton rb2 = new JRadioButton("Đang được thuê");
    JRadioButton rb3 = new JRadioButton("Chưa dọn phòng");
    ButtonGroup bgTinhTrang = new ButtonGroup();
    JPanel txtHienTrangP = new JPanel();
    JRadioButton rbMoi = new JRadioButton("Mới");
    JRadioButton rbCu = new JRadioButton("Cũ");
    ButtonGroup bgHT = new ButtonGroup();

    JPanel pnKHCenter = new JPanel();

    JPanel pnMaCTT = new JPanel();
    JPanel pnNgayThue = new JPanel();
    JPanel pnNgayTra = new JPanel();
    JPanel pnLoaiHT = new JPanel();
    JPanel pnGia = new JPanel();

    JLabel lbMaCTT = new JLabel("Mã chi tiết thuê:");
    JLabel lbNgayThue = new JLabel("Ngày thuê:");
    JLabel lbNgayTra = new JLabel("Ngày trả:");
    JLabel lbLoaiHT = new JLabel("Loại hình thuê:");
    JLabel lbGia = new JLabel("Giá thuê:");

    JTextField txtMaCTT = new JTextField();
    JTextField txtNgayThue = new JTextField();
    JTextField txtNgayTra = new JTextField();
    JTextField txtLoaiHT = new JTextField();
    JTextField txtGia = new JTextField();

    JScrollPane scp = new JScrollPane();
    JTable tb = new JTable();

    JPanel pnBtn = new JPanel();
    JPanel pnBtn1 = new JPanel();
    JButton btnSave = new JButton("Lưu");

    ArrayList<TienIchDTO> listTI;
    ArrayList<Integer> listInt = new ArrayList<>();

    JPopupMenu pm = new JPopupMenu();
    JMenuItem mItemUpdate = new JMenuItem("Sửa");
    JMenuItem mItemDelete = new JMenuItem("Xóa");
    JMenuItem mItemAdd = new JMenuItem("Thêm");

    JButton btnDelPhong = new JButton("Xóa phòng");

    public RoomInformation(String maP, String tenP, String giaP, String loaiP, String tinhTrang, String hienTrang) {
        initComponents(maP, tenP, giaP, loaiP, tinhTrang, hienTrang);
    }

    public void initComponents(String maP, String tenP, String giaP, String loaiP, String tinhTrang, String hienTrang) {
        txtMaP.setText(maP);
        txtTenP.setText(tenP);
        txtGiaP.setText(giaP);
        if (loaiP.equals("VIP")) {
            rbVip.setSelected(true);
        } else {
            rbThuong.setSelected(true);
        }
        System.out.println(hienTrang);
        if (hienTrang.equals("Mới")) {
            rbMoi.setSelected(true);
        } else {
            rbCu.setSelected(true);
        }
        if (tinhTrang.equals("Trống")) {
            rb1.setSelected(true);
        } else if (tinhTrang.equals("Đang được thuê")) {
            rb2.setSelected(true);
        } else {
            rb3.setSelected(true);
        }
        listTI = TienIchBUS.getListTienIchCTTI(txtMaP.getText(), listInt);
        setResizable(false);
        setTitle("Thông tin phòng");
        setSize(900, 700);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(lbTitle, BorderLayout.NORTH);
        lbTitle.setFont(sgUI18b);
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        lbTitle.setBorder(new EmptyBorder(5, 0, 5, 0));
        lbTitle.setBackground(Color.decode("#009900"));
        lbTitle.setOpaque(true);
        lbTitle.setForeground(Color.white);

        add(pnContent, BorderLayout.CENTER);

        pnContent.setLayout(new BorderLayout(5, 5));
        pnContent.add(pnPhong, BorderLayout.CENTER);
        pnContent.add(pnKH_XuLy, BorderLayout.EAST);

        pnPhong.setLayout(new GridLayout(2, 1, 5, 5));
        pnPhong.add(pnPhongTop);
        pnPhong.add(pnPhongBottom);
        pnPhong.setBorder(new EmptyBorder(5, 5, 5, 0));

        pnPhongTop.setLayout(new BorderLayout());
        pnPhongTop.setBackground(Color.white);
        pnPhongTop.add(lbPhong, BorderLayout.NORTH);

        pnPhongBottom.setLayout(new BorderLayout());
        pnPhongBottom.setBackground(Color.white);
        pnPhongBottom.add(lbTienIch, BorderLayout.NORTH);

        pnKH_XuLy.setLayout(new BorderLayout(5, 5));
        pnKH_XuLy.setBorder(new EmptyBorder(5, 0, 5, 5));
        pnKH_XuLy.add(pnKH, BorderLayout.CENTER);
        pnKH_XuLy.add(pnXuLy, BorderLayout.SOUTH);

        pnKH.setLayout(new BorderLayout());
        pnKH.setBackground(Color.white);
        pnKH.setBorder(new EmptyBorder(0, 5, 0, 5));
        pnKH.add(lbKH, BorderLayout.NORTH);

        pnXuLy.setLayout(new BorderLayout());
        pnXuLy.setBackground(Color.white);
        pnXuLy.setBorder(new EmptyBorder(0, 5, 0, 5));
        pnXuLy.add(lbXuLy, BorderLayout.NORTH);

        lbPhong.setFont(sgUI15);
        lbKH.setFont(sgUI15);
        lbTienIch.setFont(sgUI15);
        lbXuLy.setFont(sgUI15);

        pnPhongTop.add(pnPhongCenter, BorderLayout.CENTER);
        pnPhongCenter.setLayout(new GridLayout(6, 1));
        pnPhongCenter.add(pnMaP);
        pnPhongCenter.add(pnTenP);
        pnPhongCenter.add(pnGiaP);
        pnPhongCenter.add(pnLoaiP);
        pnPhongCenter.add(pnTinhTrangP);
        pnPhongCenter.add(pnHienTrangP);

        pnPhongCenter.setBorder(new EmptyBorder(10, 10, 10, 10));

        pnMaP.setLayout(new BorderLayout());
        pnMaP.setBackground(Color.white);
        pnMaP.add(lbMaP, BorderLayout.NORTH);
        pnMaP.add(txtMaP, BorderLayout.CENTER);

        pnTenP.setLayout(new BorderLayout());
        pnTenP.setBackground(Color.white);
        pnTenP.add(lbTenP, BorderLayout.NORTH);
        pnTenP.add(txtTenP, BorderLayout.CENTER);

        pnLoaiP.setLayout(new BorderLayout());
        pnLoaiP.setBackground(Color.white);
        pnLoaiP.add(lbLoaiP, BorderLayout.NORTH);
        pnLoaiP.add(txtLoaiP, BorderLayout.CENTER);

        pnGiaP.setLayout(new BorderLayout());
        pnGiaP.setBackground(Color.white);
        pnGiaP.add(lbGiaP, BorderLayout.NORTH);
        pnGiaP.add(txtGiaP, BorderLayout.CENTER);

        pnTinhTrangP.setLayout(new BorderLayout());
        pnTinhTrangP.setBackground(Color.white);
        pnTinhTrangP.add(lbTinhTrangP, BorderLayout.NORTH);
        pnTinhTrangP.add(txtTinhTrangP, BorderLayout.CENTER);

        pnHienTrangP.setLayout(new BorderLayout());
        pnHienTrangP.setBackground(Color.white);
        pnHienTrangP.add(lbHienTrangP, BorderLayout.NORTH);
        pnHienTrangP.add(txtHienTrangP, BorderLayout.CENTER);

        pnPhongCenter.setBackground(Color.white);
        txtMaP.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.white), new EmptyBorder(0, 5, 0, 5)));
        txtMaP.setBackground(Color.decode("#F5F5F5"));

        txtTenP.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.white), new EmptyBorder(0, 5, 0, 5)));
        txtTenP.setBackground(Color.decode("#F5F5F5"));

        txtGiaP.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.white), new EmptyBorder(0, 5, 0, 5)));
        txtGiaP.setBackground(Color.decode("#F5F5F5"));

        txtLoaiP.setLayout(new GridLayout(1, 2));
        txtLoaiP.add(rbVip);
        txtLoaiP.add(rbThuong);
        rbVip.setFocusPainted(false);
        rbThuong.setFocusPainted(false);
        bgLoai.add(rbVip);
        bgLoai.add(rbThuong);

        txtTinhTrangP.setLayout(new GridLayout(1, 3));
        txtTinhTrangP.add(rb1);
        txtTinhTrangP.add(rb2);
        txtTinhTrangP.add(rb3);
        bgTinhTrang.add(rb1);
        bgTinhTrang.add(rb2);
        bgTinhTrang.add(rb3);

        txtHienTrangP.setLayout(new GridLayout(1, 2));
        txtHienTrangP.add(rbMoi);
        txtHienTrangP.add(rbCu);
        bgHT.add(rbMoi);
        bgHT.add(rbCu);
        rbMoi.setFocusPainted(false);
        rbCu.setFocusPainted(false);

        rb1.setEnabled(false);
        rb2.setEnabled(false);
        rb3.setEnabled(false);

        txtMaP.setEditable(false);
        pnPhongBottom.add(scp, BorderLayout.CENTER);
        scp.setBackground(Color.white);
        scp.getViewport().setBackground(Color.decode("#F5F5F5"));
        scp.setViewportView(tb);

        scp.setBorder(new EmptyBorder(5, 5, 5, 5));
        render(tb);

        pnKH.add(pnKHCenter, BorderLayout.CENTER);
        pnKH.setBorder(new EmptyBorder(0, 0, 250, 0));
        pnKHCenter.setLayout(new GridLayout(5, 1));
        pnKHCenter.add(pnMaCTT);
        pnKHCenter.add(pnNgayThue);
        pnKHCenter.add(pnNgayTra);
        pnKHCenter.add(pnLoaiHT);
        pnKHCenter.add(pnGia);

        pnKHCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnKHCenter.setBackground(Color.white);

        pnMaCTT.setLayout(new BorderLayout());
        pnMaCTT.setBackground(Color.white);
        pnMaCTT.add(lbMaCTT, BorderLayout.NORTH);
        pnMaCTT.add(txtMaCTT, BorderLayout.CENTER);

        pnNgayThue.setLayout(new BorderLayout());
        pnNgayThue.setBackground(Color.white);
        pnNgayThue.add(lbNgayThue, BorderLayout.NORTH);
        pnNgayThue.add(txtNgayThue, BorderLayout.CENTER);

        pnNgayTra.setLayout(new BorderLayout());
        pnNgayTra.setBackground(Color.white);
        pnNgayTra.add(lbNgayTra, BorderLayout.NORTH);
        pnNgayTra.add(txtNgayTra, BorderLayout.CENTER);

        pnLoaiHT.setLayout(new BorderLayout());
        pnLoaiHT.setBackground(Color.white);
        pnLoaiHT.add(lbLoaiHT, BorderLayout.NORTH);
        pnLoaiHT.add(txtLoaiHT, BorderLayout.CENTER);

        pnGia.setLayout(new BorderLayout());
        pnGia.setBackground(Color.white);
        pnGia.add(lbGia, BorderLayout.NORTH);
        pnGia.add(txtGia, BorderLayout.CENTER);

        pnXuLy.add(pnBtn, BorderLayout.CENTER);

        pnBtn.setLayout(new BorderLayout());
        pnBtn.add(pnBtn1, BorderLayout.EAST);
        pnBtn1.setLayout(new GridLayout(2, 1, 5, 5));
        pnBtn1.setBackground(Color.white);
        pnBtn1.add(btnSave);
        pnBtn1.add(btnDelPhong);
        pnBtn.setBorder(new EmptyBorder(0, 0, 5, 0));
        pnBtn.setBackground(Color.white);
        btnSave.setFocusPainted(false);
        btnSave.setBackground(Color.decode("#FFFF00"));

        btnDelPhong.setFocusPainted(false);
        btnDelPhong.setBackground(Color.decode("#EE0000"));

        txtMaCTT.setBorder(null);
        txtNgayThue.setBorder(null);
        txtNgayTra.setBorder(null);
        txtLoaiHT.setBorder(null);
        txtGia.setBorder(null);
        UIManager.put("TextField.inactiveBackground", new ColorUIResource(new Color(255, 255, 255)));

        txtMaCTT.setEditable(false);
        txtNgayThue.setEditable(false);
        txtNgayTra.setEditable(false);
        txtLoaiHT.setEditable(false);
        txtGia.setEditable(false);

        lbPhong.setBackground(Color.decode("#90EE90"));
        lbPhong.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbPhong.setOpaque(true);

        lbKH.setBackground(Color.decode("#90EE90"));
        lbKH.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbKH.setOpaque(true);

        lbXuLy.setBackground(Color.decode("#90EE90"));
        lbXuLy.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbXuLy.setOpaque(true);

        lbTienIch.setBackground(Color.decode("#90EE90"));
        lbTienIch.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbTienIch.setOpaque(true);

        if (rb2.isSelected()) {
            ThuePhongDTO tp = ThuePhongBUS.getThuePhong(txtMaP.getText());
            if (tp.getMaChiTietThue() != null) {
                txtMaCTT.setText(tp.getMaChiTietThue());
                txtNgayThue.setText(tp.getNgayThue());
                txtNgayTra.setText(tp.getNgayTra());
                txtLoaiHT.setText(tp.getLoaiHinhThue());
                txtGia.setText(tp.getGia() + "");
            }
        }

        pm.add(mItemAdd);
        pm.add(mItemUpdate);
        pm.add(mItemDelete);

        mItemAdd.setFont(sgUI13b);
        mItemUpdate.setFont(sgUI13b);
        mItemDelete.setFont(sgUI13b);

        mItemAdd.setBackground(Color.white);
        mItemUpdate.setBackground(Color.white);
        mItemDelete.setBackground(Color.white);

        mItemAdd.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
        mItemUpdate.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
        tb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    pm.show(tb, e.getX(), e.getY());
                }
            }
        });

        mItemAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTienIch(RoomInformation.this, txtMaP.getText());
            }
        });

        mItemUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String maP = txtMaP.getText();
                    String maTI = tb.getValueAt(tb.getSelectedRow(), tb.getColumnModel().getColumnIndex("Mã tiện ích")).toString();
                    try {
                        int SL = Integer.parseInt(JOptionPane.showInputDialog(null, "Nhập số lượng muốn thay đổi"));
                        if(SL <=0) {
                            JOptionPane.showMessageDialog(null, "Số lượng là số nguyên không âm và lớn hơn 0");
                        } else {
                            ChiTietTienIchDTO ctti = new ChiTietTienIchDTO();
                            ctti.setMaP(maP);
                            ctti.setMaTienIch(maTI);
                            ctti.setSoLuong(SL);
                            if(ChiTietTienIchBUS.updateChiTietTienIch(ctti)) {
                                JOptionPane.showMessageDialog(null, "Sửa thành công","Thành công",JOptionPane.INFORMATION_MESSAGE);
                                tb.clearSelection();
                            listTI.clear();
                            listTI = TienIchBUS.getListTienIchCTTI(txtMaP.getText(), listInt);
                                render(tb);
                            }
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Số lượng là số nguyên");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 tiện ích muốn xóa khỏi phòng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        mItemDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String maP = txtMaP.getText();
                    String maTI = tb.getValueAt(tb.getSelectedRow(), tb.getColumnModel().getColumnIndex("Mã tiện ích")).toString();
                    int ans = JOptionPane.showConfirmDialog(null, "Bạn có chắc xóa tiện ích này khỏi phòng " + txtMaP.getText(), "Thông báo", JOptionPane.YES_NO_OPTION);
                    if (ans == JOptionPane.YES_OPTION) {
                        if (ChiTietTienIchBUS.deleteChiTietTienIch(maTI, maP)) {
                            JOptionPane.showMessageDialog(null, "Xoá thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                            tb.clearSelection();
                            listTI.clear();
                            listTI = TienIchBUS.getListTienIchCTTI(txtMaP.getText(), listInt);
                            render(tb);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Bạn đã bấm hủy", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 tiện ích muốn xóa khỏi phòng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        btnDelPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb2.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Không thể xóa phòng vì phòng đang được thuê", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int ans = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa phòng này?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    if (ans == JOptionPane.YES_OPTION) {
                        if (PhongBUS.deletePhong(txtMaP.getText())) {
                            JOptionPane.showMessageDialog(null, "Xoá thành công phòng " + txtTenP.getText(), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                            PanelPhong.renderTBPhong(PanelPhong.tbPhong);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Bạn đã chọn hủy xóa","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb2.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Không thể sửa phòng vì phòng đang được thuê", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (txtTenP.getText().trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập tên phòng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        txtTenP.requestFocus();
                    } else {
                        if (txtGiaP.getText().trim().length() == 0) {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá phòng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            txtGiaP.requestFocus();
                        } else {
                            try {
                                int gia = Integer.parseInt(txtGiaP.getText());
                                if (gia <= 0) {
                                    JOptionPane.showMessageDialog(null, "Giá phòng phải là số nguyên lớn hơn 0", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                    txtGiaP.setText("");
                                    txtGiaP.requestFocus();
                                } else {
                                    PhongDTO pDTO = new PhongDTO();
                                    String loai = "";
                                    if (rbVip.isSelected()) {
                                        loai = "VIP";
                                    } else {
                                        loai = "Thường";
                                    }
                                    String tinhtrang = "";
                                    if (rb1.isSelected()) {
                                        tinhtrang = "Trống";
                                    } else {
                                        tinhtrang = "Chưa được dọn";
                                    }
                                    String hienTrang = "";
                                    if (rbMoi.isSelected()) {
                                        hienTrang = "Mới";
                                    } else {
                                        hienTrang = "Cũ";
                                    }
                                    pDTO.setMaP(txtMaP.getText());
                                    pDTO.setGiaP(gia);
                                    pDTO.setXuLy(0);
                                    pDTO.setLoaiP(loai);
                                    pDTO.setHienTrang(hienTrang);
                                    pDTO.setTinhTrang(tinhtrang);
                                    pDTO.setTenP(txtTenP.getText());
                                    if (PhongBUS.updatePhong(pDTO)) {
                                        JOptionPane.showMessageDialog(null, "Sửa thành công phòng " + txtMaP.getText(), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                        dispose();
                                        PanelPhong.renderTBPhong(PanelPhong.tbPhong);
                                    }
                                }
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Giá phòng phải là số", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                }
            }
        }
        );
    }

    public void render(JTable tb) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Mã tiện ích");
        dtm.addColumn("Tên tiện ích");
        dtm.addColumn("Số lượng");
        int i = 0;
        for (TienIchDTO x : listTI) {
            Object row[] = {i + 1, x.getMaTienIch(), x.getTenTienIch(), listInt.get(i)};
            dtm.addRow(row);
            i++;
        }
        tb.setModel(dtm);
        tb.setShowGrid(false);
        tb.setIntercellSpacing(new Dimension(0, 0));
        tb.setRowHeight(30);
        tb.getColumnModel().getColumn(0).setPreferredWidth(10);
        tb.getTableHeader().setPreferredSize(new Dimension(1, 30));
        tb.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tb.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
                if (column == 2) {
                    lb.setHorizontalAlignment(JLabel.LEFT);
                } else {
                    lb.setHorizontalAlignment(JLabel.CENTER);
                }
                if (isSelected) {
                    lb.setBackground(Color.decode("#F5F5F5"));
                } else {
                    lb.setBackground(Color.decode("#FFFFFF"));
                }
                return lb;
            }
        };
        for (int j = 0; j < tb.getColumnCount(); j++) {
            tb.getColumnModel().getColumn(j).setCellRenderer(renderBorder);
        }
    }
}
