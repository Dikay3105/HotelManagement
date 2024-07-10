package GUI.Phong;

import BUS.PhongBUS;
import BUS.TienIchBUS;
import DTO.PhongDTO;
import DTO.ThuePhongDTO;
import DTO.TienIchDTO;
import GUI.DatPhongGUI;
import GUI.FrameOrderRoom;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class PanelSelectPhong extends JFrame {

    JPanel pnContent = new JPanel();
    JLabel lbTitle = new JLabel("Danh sách phòng trống");
    JPanel pnCenter = new JPanel();
    JPanel pnCenterTop = new JPanel();
    JPanel pnLeft = new JPanel();
    JButton btnLeft = new JButton();
    JPanel pnMid = new JPanel();
    JPanel pnRight = new JPanel();
    JButton btnRight = new JButton();
    JPanel pnCenterBottom = new JPanel();
    JLabel lbCenterBottom = new JLabel("Danh sách tiện ích");
    JScrollPane scp = new JScrollPane();
    JTable tb = new JTable();
    JPanel pnBottom = new JPanel();
    JButton btnSelect = new JButton("Đồng ý");
    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);
    ArrayList<PhongDTO> listPhong = new ArrayList<>();
    ArrayList<TienIchDTO> listTI = new ArrayList<>();
    ArrayList<Integer> listInt = new ArrayList<>();
    boolean check = false;
    PhongDTO phongDTO = new PhongDTO();

    public PanelSelectPhong(ThuePhongDTO tp, int hourCount, int dayCount, String dateNgayThue, String dateNgayTra) {
        initComponents(tp, hourCount, dayCount, dateNgayThue, dateNgayTra);
        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check) {
                    int ans = 0;
                    for (PhongDTO x : DatPhongGUI.listP) {
                        if (x.getMaP().equals(phongDTO.getMaP())) {
                            ans++;
                            break;
                        }
                    }
                    if (ans != 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng khác, phòng này đã chọn trước đó", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    } else {
                        DatPhongGUI.listP.add(phongDTO);
                        int hour = hourCount;
                        int day = dayCount;
                        if (hour < 0) {
                            hour = 24 + hour;
                            day = day - 1;
                        }
                        if (hour == 0) {
                            tp.setGia(phongDTO.getGiaP() * day);
                        } else if (hour == 1) {
                            tp.setGia(phongDTO.getGiaP() * 45 / 100);
                            if (day > 0) {
                                tp.setGia(phongDTO.getGiaP() * day + tp.getGia());
                            }
                        } else if (hour == 2) {
                            tp.setGia((phongDTO.getGiaP() * 45 / 100) - 20000);
                            if (day > 0) {
                                tp.setGia(phongDTO.getGiaP() * day + tp.getGia());
                            }
                        } else if (hour == 3) {
                            tp.setGia((phongDTO.getGiaP() * 45 / 100) - 40000);
                            if (day > 0) {
                                tp.setGia(phongDTO.getGiaP() * day + tp.getGia());
                            }
                        } else if (hour >= 4 && hour <= 12) {
                            tp.setGia(phongDTO.getGiaP() / 2);
                            if (day > 0) {
                                tp.setGia(phongDTO.getGiaP() * day + tp.getGia());
                            }
                        } else {
                            tp.setGia(phongDTO.getGiaP());
                            if (day > 0) {
                                tp.setGia(phongDTO.getGiaP() * day + tp.getGia());
                            }
                        }
                        DatPhongGUI.listTP.add(tp);
                        DatPhongGUI.render(DatPhongGUI.tb);
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng muốn thuê", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public PanelSelectPhong(int i, String tra, String thue) {
        initComponents(new ThuePhongDTO(), 0, 0, thue, tra);
        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check) {
                    int ans = 0;
                    for (PhongDTO x : DatPhongGUI.listP) {
                        if (x.getMaP().equals(phongDTO.getMaP())) {
                            ans++;
                            break;
                        }
                    }
                    if (ans != 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng khác, phòng này đã chọn trước đó", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    } else {
                        System.out.println(phongDTO.getMaP());
                        FrameOrderRoom.MaPhong = phongDTO.getMaP();
                        FrameOrderRoom.giaTmp = phongDTO.getGiaP();
                        FrameOrderRoom.txtSoPhong.setText(phongDTO.getMaP());
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng muốn thuê", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public PanelSelectPhong(String a, String tra, String thue) {
        initComponents(new ThuePhongDTO(), 0, 0, thue, tra);
        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check) {
                    int ans = 0;
                    for (PhongDTO x : DatPhongGUI.listP) {
                        if (x.getMaP().equals(phongDTO.getMaP())) {
                            ans++;
                            break;
                        }
                    }
                    if (ans != 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng khác, phòng này đã chọn trước đó", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    } else {
                        System.out.println(phongDTO.getMaP());
                        FrameOrderRoom.MaPhong = phongDTO.getMaP();
                        FrameOrderRoom.giaTmp = phongDTO.getGiaP();
                        FrameOrderRoom.btnTmp.doClick();
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng muốn thuê", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public void initComponents(ThuePhongDTO tp, int hourCount, int dayCount, String dateNgayThue, String dateNgayTra) {
        for (PhongDTO x : PhongBUS.getListPhong(dateNgayThue, dateNgayTra)) {
            listPhong.add(x);
        }
        setVisible(true);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(pnContent, BorderLayout.CENTER);
        pnContent.setBackground(Color.white);
        pnContent.setLayout(new BorderLayout());
        pnContent.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnContent.add(lbTitle, BorderLayout.NORTH);
        pnContent.add(pnCenter, BorderLayout.CENTER);
        pnContent.add(pnBottom, BorderLayout.SOUTH);

        lbTitle.setFont(sgUI18b);
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        lbTitle.setBackground(Color.decode("#CCFFCC"));
        lbTitle.setOpaque(true);
        lbTitle.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.black), new EmptyBorder(5, 0, 5, 0)));

        pnCenter.setLayout(new GridLayout(2, 1));
        pnCenter.add(pnCenterTop);
        pnCenter.add(pnCenterBottom);

        pnCenterTop.setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK));
        pnCenterTop.setLayout(new BorderLayout());
        pnCenterTop.add(pnLeft, BorderLayout.WEST);
        pnCenterTop.add(pnMid, BorderLayout.CENTER);
        pnCenterTop.add(pnRight, BorderLayout.EAST);

        pnMid.setLayout(new GridLayout(1, 3, 5, 5));
        pnMid.setBackground(Color.white);
        pnMid.setBorder(new EmptyBorder(5, 5, 5, 5));

        pnLeft.setLayout(new BorderLayout());
        pnLeft.setBorder(new EmptyBorder(80, 10, 80, 10));
        pnLeft.setBackground(Color.white);
        btnLeft.setBackground(Color.white);
        btnLeft.setFocusPainted(false);
        btnLeft.setBorder(null);
        pnLeft.add(btnLeft, BorderLayout.CENTER);

        ImageIcon iconLeft = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/double-left.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        btnLeft.setIcon(iconLeft);

        pnRight.setLayout(new BorderLayout());
        pnRight.setBorder(new EmptyBorder(80, 10, 80, 10));
        pnRight.setBackground(Color.white);
        btnRight.setBackground(Color.white);
        btnRight.setFocusPainted(false);
        btnRight.setBorder(null);
        pnRight.add(btnRight, BorderLayout.CENTER);

        ImageIcon iconRight = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/double-right.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        btnRight.setIcon(iconRight);

        pnCenterBottom.setLayout(new BorderLayout());
        pnCenterBottom.add(lbCenterBottom, BorderLayout.NORTH);
        pnCenterBottom.add(scp);

        lbCenterBottom.setFont(sgUI15);
        lbCenterBottom.setBackground(Color.decode("#CCFFCC"));
        lbCenterBottom.setOpaque(true);
        lbCenterBottom.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.black), new EmptyBorder(5, 10, 5, 0)));

        scp.getViewport().setBackground(Color.white);
        scp.setViewportView(tb);
        scp.setBorder(new MatteBorder(0, 1, 1, 1, Color.BLACK));

        renderTB();

        pnBottom.setLayout(new BorderLayout());
        pnBottom.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnBottom.add(btnSelect, BorderLayout.EAST);
        pnBottom.setBackground(Color.white);

        btnSelect.setFocusPainted(false);

        renderSlider();
        btnLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnMid.removeAll();
                pnMid.revalidate();
                PhongDTO x = new PhongDTO();
                x = listPhong.remove(listPhong.size() - 1);
                listPhong.add(0, x);
                check = false;
                renderSlider();
            }
        });
        btnRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnMid.removeAll();
                pnMid.revalidate();
                PhongDTO x = listPhong.remove(0);
                listPhong.add(x);
                renderSlider();
                check = false;
            }
        });

        btnSelect.setBackground(Color.decode("#00CC00"));
        btnSelect.setFont(sgUI13b);
        btnSelect.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#006633")), new EmptyBorder(5, 20, 5, 20)));
        btnSelect.setForeground(Color.white);
//        btnSelect.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (check) {
//                    int ans = 0;
//                    for (PhongDTO x : DatPhongGUI.listP) {
//                        if (x.getMaP().equals(phongDTO.getMaP())) {
//                            ans++;
//                            break;
//                        }
//                    }
//                    if (ans != 0) {
//                        JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng khác, phòng này đã chọn trước đó", "Thông báo", JOptionPane.ERROR_MESSAGE);
//                    } else {
//                        DatPhongGUI.listP.add(phongDTO);
//                        int hour = hourCount;
//                        int day = dayCount;
//                        if (hour < 0) {
//                            hour = 24 + hour;
//                            day = day - 1;
//                        }
//                        if (hour == 0) {
//                            tp.setGia(phongDTO.getGiaP() * day);
//                        } else if (hour == 1) {
//                            tp.setGia(phongDTO.getGiaP() * 45 / 100);
//                            if (day > 0) {
//                                tp.setGia(phongDTO.getGiaP() * day + tp.getGia());
//                            }
//                        } else if (hour == 2) {
//                            tp.setGia((phongDTO.getGiaP() * 45 / 100) - 20000);
//                            if (day > 0) {
//                                tp.setGia(phongDTO.getGiaP() * day + tp.getGia());
//                            }
//                        } else if (hour == 3) {
//                            tp.setGia((phongDTO.getGiaP() * 45 / 100) - 40000);
//                            if (day > 0) {
//                                tp.setGia(phongDTO.getGiaP() * day + tp.getGia());
//                            }
//                        } else if (hour >= 4 && hour <= 12) {
//                            tp.setGia(phongDTO.getGiaP() / 2);
//                            if (day > 0) {
//                                tp.setGia(phongDTO.getGiaP() * day + tp.getGia());
//                            }
//                        } else {
//                            tp.setGia(phongDTO.getGiaP());
//                            if (day > 0) {
//                                tp.setGia(phongDTO.getGiaP() * day + tp.getGia());
//                            }
//                        }
//                        DatPhongGUI.listTP.add(tp);
//                        DatPhongGUI.render(DatPhongGUI.tb);
//                        dispose();
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng muốn thuê", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                }
//            }
//        });
    }

    public void renderTB() {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Tên tiện ích");
        dtm.addColumn("Số lượng");
        tb.setModel(dtm);
        tb.setModel(dtm);
        tb.setShowGrid(false);
        tb.setIntercellSpacing(new Dimension(0, 0));
        tb.setRowHeight(30);
        tb.getTableHeader().setPreferredSize(new Dimension(1, 30));
        tb.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tb.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        int i = 0;
        for (TienIchDTO x : listTI) {
            Object row[] = {i + 1, x.getTenTienIch(), listInt.get(i)};
            dtm.addRow(row);
            i++;
        }
        tb.getTableHeader().setBackground(Color.decode("#33CC33"));
        tb.getColumnModel().getColumn(0).setPreferredWidth(1);
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
                if (column == 0) {
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
    ArrayList<JPanel> listPn = new ArrayList<>();

    public void renderSlider() {
        listPn.clear();
        int count = 0;
        for (PhongDTO x : listPhong) {
            JPanel pn = new JPanel();
            pn.setBackground(Color.decode("#EEEEEE"));
            JPanel pnCen = new JPanel();
            pnCen.setBorder(BorderFactory.createRaisedBevelBorder());
            pnCen.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (JPanel k : listPn) {
                        k.setBorder(BorderFactory.createRaisedBevelBorder());
                        phongDTO.setMaP(x.getMaP());
                        phongDTO.setTenP(x.getTenP());
                        phongDTO.setLoaiP(x.getLoaiP());
                        phongDTO.setGiaP(x.getGiaP());
                        phongDTO.setTinhTrang(x.getTinhTrang());
                        phongDTO.setHienTrang(x.getHienTrang());
                    }
                    pnCen.setBorder(BorderFactory.createLoweredBevelBorder());
                    check = true;
                    listTI = TienIchBUS.getListTienIchCTTI(x.getMaP(), listInt);
                    renderTB();
                }
            });
            pnCen.setLayout(new BorderLayout());
            pnCen.add(pn, BorderLayout.CENTER);
            pn.setBorder(new EmptyBorder(5, 5, 5, 5));
            JLabel lb = new JLabel("ID: " + x.getMaP());
            lb.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 3, 1, 1, Color.decode("#33CC33")), new EmptyBorder(5, 5, 5, 5)));
            lb.setBackground(Color.decode("#CCFFCC"));
            lb.setOpaque(true);
            pn.setLayout(new BorderLayout());
            pn.add(lb, BorderLayout.NORTH);
            JPanel pnInfo = new JPanel();
            pn.add(pnInfo, BorderLayout.CENTER);
            pnInfo.setLayout(new GridLayout(4, 1));
            JPanel pnTenP = new JPanel();
            JPanel pnLoaiP = new JPanel();
            JPanel pnGiaP = new JPanel();
            JPanel pnHTP = new JPanel();

            pnTenP.setBackground(Color.decode("#5BBD2B"));
            pnLoaiP.setBackground(Color.decode("#83C75D"));
            pnGiaP.setBackground(Color.decode("#AFD788"));
            pnHTP.setBackground(Color.decode("#C8E2B1"));

            JLabel lbTenP = new JLabel("" + x.getTenP());
            JLabel lbLoaiP = new JLabel("Loại: " + x.getLoaiP());
            JLabel lbGiaP = new JLabel("Giá: " + x.getGiaP());
            JLabel lbHTP = new JLabel("Hiện trạng: " + x.getHienTrang());

            pnTenP.setLayout(new BorderLayout());
            pnTenP.add(lbTenP, BorderLayout.CENTER);

            pnGiaP.setLayout(new BorderLayout());
            pnGiaP.add(lbGiaP, BorderLayout.CENTER);

            pnLoaiP.setLayout(new BorderLayout());
            pnLoaiP.add(lbLoaiP, BorderLayout.CENTER);

            pnHTP.setLayout(new BorderLayout());
            pnHTP.add(lbHTP, BorderLayout.CENTER);

            pnInfo.add(pnTenP);
            pnInfo.add(pnLoaiP);
            pnInfo.add(pnGiaP);
            pnInfo.add(pnHTP);

            pnMid.add(pnCen);
            listPn.add(pnCen);
            count++;
            if (count == 3) {
                break;
            }
        }
    }
}
