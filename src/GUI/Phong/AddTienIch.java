package GUI.Phong;

import BUS.ChiTietTienIchBUS;
import BUS.TienIchBUS;
import DTO.ChiTietTienIchDTO;
import DTO.TienIchDTO;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.*;

public class AddTienIch extends JFrame {

    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);
    JLabel lbTitle = new JLabel("Chọn tiện ích phòng");
    JPanel pnCenter = new JPanel();
    JPanel pnTop = new JPanel();
    JPanel pnBottom = new JPanel();

    JLabel lbDS = new JLabel("Danh sách tiện ích");
    JPanel pnlb = new JPanel();
    JTextField txtSearch = new JTextField("Tìm kiếm...");
    JScrollPane scp = new JScrollPane();
    JTable tb = new JTable();

    JPanel pnMaTI = new JPanel();
    JPanel pnTenTI = new JPanel();
    JPanel pnSLTI = new JPanel();
    JPanel btn = new JPanel();

    JLabel lbMaTI = new JLabel("Mã tiện ích:");
    JLabel lbTenTI = new JLabel("Tên tiện ích:");
    JLabel lbSLTI = new JLabel("Số lượng:");

    JTextField txtMaTIBottom = new JTextField();
    JTextField txtTenTIBottom = new JTextField();
    JTextField txtSLTIBottom = new JTextField();

    JButton btnAdd = new JButton("Thêm");

    ArrayList<TienIchDTO> listTienIch = TienIchBUS.getListTienIch();

    public AddTienIch(RoomInformation room, String maP) {
        initComponents(room, maP);
    }

    public void initComponents(RoomInformation room, String maP) {
        lbTitle.setFont(sgUI18b);
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        setLayout(new BorderLayout());
        add(lbTitle, BorderLayout.NORTH);
        add(pnCenter, BorderLayout.CENTER);
        pnCenter.setLayout(new BorderLayout());
        pnCenter.add(pnTop, BorderLayout.CENTER);
        pnCenter.add(pnBottom, BorderLayout.SOUTH);
        pnBottom.setLayout(new GridLayout(2, 2, 5, 5));
        pnBottom.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnBottom.setBackground(Color.white);
        pnBottom.add(pnMaTI);
        pnBottom.add(pnSLTI);
        pnBottom.add(pnTenTI);
        pnBottom.add(btn);

        pnMaTI.setLayout(new BorderLayout());
        pnMaTI.setBackground(Color.white);
        pnMaTI.add(lbMaTI, BorderLayout.NORTH);
        pnMaTI.add(txtMaTIBottom, BorderLayout.CENTER);

        pnSLTI.setLayout(new BorderLayout());
        pnSLTI.setBackground(Color.white);
        pnSLTI.add(lbSLTI, BorderLayout.NORTH);
        pnSLTI.add(txtSLTIBottom, BorderLayout.CENTER);
        btn.setBorder(new EmptyBorder(5, 5, 5, 5));
        UIManager.put("TextField.inactiveBackground", new ColorUIResource(new Color(255, 255, 255)));
        pnTenTI.setLayout(new BorderLayout());
        pnTenTI.setBackground(Color.white);
        pnTenTI.add(lbTenTI, BorderLayout.NORTH);
        pnTenTI.add(txtTenTIBottom, BorderLayout.CENTER);
        txtTenTIBottom.setEditable(false);
        txtMaTIBottom.setEditable(false);
        btn.setBackground(Color.white);
        btn.setLayout(new BorderLayout());
        btn.add(btnAdd, BorderLayout.EAST);
        btnAdd.setBackground(Color.white);
        btnAdd.setFocusPainted(false);
        txtMaTIBottom.setFont(sgUI13);
        txtTenTIBottom.setFont(sgUI13);
        txtSLTIBottom.setFont(sgUI13);
        txtMaTIBottom.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.black), new EmptyBorder(5, 5, 5, 5)));
        txtTenTIBottom.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.black), new EmptyBorder(5, 5, 5, 5)));
        txtSLTIBottom.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.black), new EmptyBorder(5, 5, 5, 5)));

        pnTop.setLayout(new BorderLayout());
        pnTop.add(pnlb, BorderLayout.NORTH);
        pnTop.add(scp, BorderLayout.CENTER);
        pnTop.setBackground(Color.white);
        pnlb.setLayout(new GridLayout(1, 2));
        pnlb.add(lbDS);
        pnlb.setBackground(Color.white);
        pnlb.setBorder(new EmptyBorder(5, 5, 5, 5));
        txtSearch.setFont(sgUI13);
        lbDS.setFont(sgUI15);
        pnlb.add(txtSearch);
        scp.setBackground(Color.white);
        scp.getViewport().setBackground(Color.white);
        scp.setViewportView(tb);
        render(tb, listTienIch);
        setTitle("Chọn tiện ích");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        getContentPane().setBackground(Color.decode("#191970"));
        lbTitle.setForeground(Color.white);
        lbTitle.setBorder(new EmptyBorder(5, 0, 5, 0));

        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (txtSearch.getText().trim().length() != 0) {
                    ArrayList<TienIchDTO> list = new ArrayList<>();
                    for (TienIchDTO x : listTienIch) {
                        if (x.getTenTienIch().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                            list.add(x);
                        }
                    }
                    if (list.size() != 0) {
                        render(tb, list);
                    } else {
                        render(tb, listTienIch);
                    }
                } else {
                    render(tb, listTienIch);
                }
            }
        });

        txtSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtSearch.getText().equals("Tìm kiếm...")) {
                    txtSearch.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtSearch.getText().trim().length() == 0) {
                    txtSearch.setText("Tìm kiếm...");
                }
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMaTIBottom.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn tiện ích muốn thêm");
                } else {
                    try {
                        int SL = Integer.parseInt(txtSLTIBottom.getText());
                        if (SL <= 0) {
                            JOptionPane.showMessageDialog(null, "Số lượng là số nguyên lớn hơn 0", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            txtSLTIBottom.setText("");
                            txtSLTIBottom.requestFocus();
                        } else {
                            int check = 0;
                            for (TienIchDTO x : room.listTI) {
                                if (x.getMaTienIch().equals(txtMaTIBottom.getText())) {
                                    check++;
                                }
                            }
                            if (check == 0) {
                                ChiTietTienIchDTO ctti = new ChiTietTienIchDTO();
                                ctti.setMaP(maP);
                                ctti.setMaTienIch(txtMaTIBottom.getText());
                                ctti.setSoLuong(SL);

                                if (ChiTietTienIchBUS.insertChiTietTienIch(ctti)) {
                                    room.listTI = TienIchBUS.getListTienIchCTTI(maP, room.listInt);
                                    room.render(room.tb);
                                    int ans = JOptionPane.showConfirmDialog(null, "Thêm thành công bạn có muôn thoát không", "Thông báo", JOptionPane.YES_NO_OPTION);
                                    if (ans == JOptionPane.YES_OPTION) {
                                        dispose();
                                    } else {
                                        txtSLTIBottom.setText("");
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Phòng đã có tiện ích này", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    } catch (Exception ex) {
                    }
                }
            }
        });
    }

    public void render(JTable tb, ArrayList<TienIchDTO> list) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Mã tiện ích");
        dtm.addColumn("Tên tiện ích");
        int i = 1;
        for (TienIchDTO x : list) {
            Object data[] = {i, x.getMaTienIch(), x.getTenTienIch()};
            dtm.addRow(data);
            i++;
        }
        tb.setModel(dtm);
        tb.setModel(dtm);
        tb.setShowGrid(false);
        tb.setIntercellSpacing(new Dimension(0, 0));
        tb.setRowHeight(25);
        tb.getColumnModel().getColumn(0).setPreferredWidth(10);
        tb.getTableHeader().setPreferredSize(new Dimension(1, 25));
        tb.getTableHeader().setFont(sgUI13b);
        tb.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tb.getTableHeader().setBackground(Color.decode("#6699CC"));
        DefaultTableCellRenderer renderBorder = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                JLabel lb = (JLabel) c;
                lb.setFont(sgUI13);
                lb.setBorder(new MatteBorder(0, 0, 1, 1, Color.decode("#DDDDDD")));
                lb.setHorizontalAlignment(JLabel.CENTER);
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
        tb.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    txtMaTIBottom.setText(tb.getValueAt(tb.getSelectedRow(), tb.getColumnModel().getColumnIndex("Mã tiện ích")).toString());
                    txtTenTIBottom.setText(tb.getValueAt(tb.getSelectedRow(), tb.getColumnModel().getColumnIndex("Tên tiện ích")).toString());
                } catch (Exception ex) {
                    txtMaTIBottom.setText("");
                    txtTenTIBottom.setText("");
                }
            }
        });
    }
}
