/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.LeTanGUI;

import BUS.ChiTietThueBUS;
import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import DTO.ChiTietThueDTO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author duyli
 */
public class PanelChiTietThueGUI extends JPanel {

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

    private JPanel pnMaKH = new JPanel();
    private JPanel pnMaNV = new JPanel();
    private JPanel pnMaCTT = new JPanel();
    private JPanel pnTenKH = new JPanel();
    private JPanel pnTenNV = new JPanel();
    private JPanel pnTinhTrang = new JPanel();

    private JTextField txtMaKH = new JTextField("Mã khách hàng");
    private JTextField txtMaNV = new JTextField("Mã nhân viên");
    private JTextField txtTenKH = new JTextField("Tên khách hàng");
    private JTextField txtTenNV = new JTextField("Tên nhân viên");
    private JTextField txtMaCTT = new JTextField("Mã chi tiết thuê");

    private JComboBox txtTinhTrang = new JComboBox();

    private JTable tbCTT = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
    static Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    static Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);

    private JLabel lbList = new JLabel("Danh sách chi tiết đặt phòng");

    private JButton btnSearch = new JButton("Tìm kiếm");
    private JButton btnReset = new JButton("Thoát tìm");

    public PanelChiTietThueGUI(JTabbedPane tb, DatPhongLeTan dp) {
        initComponents();
    }

    public void initComponents() {
        setBackground(Color.white);
        setLayout(new BorderLayout());

        pnTitle.setBackground(Color.white);
        add(pnTitle, BorderLayout.NORTH);
        add(pnCenter, BorderLayout.CENTER);

        pnCenter.setLayout(new BorderLayout());
        pnCenter.add(pnSearch, BorderLayout.NORTH);
        pnCenter.add(pnContent, BorderLayout.CENTER);
        pnCenter.setBackground(Color.white);

        pnSearch.setLayout(new BorderLayout());
        pnSearch.add(pnBtn, BorderLayout.EAST);
        pnSearch.add(pnInput, BorderLayout.CENTER);
        pnSearch.setBackground(Color.white);

        pnInput.setLayout(new GridLayout(1, 8, 2, 2));
        pnInput.setBackground(Color.white);
        pnInput.add(pnMaCTT);
        pnMaCTT.setLayout(new BorderLayout());
        pnMaCTT.add(txtMaCTT, BorderLayout.CENTER);
        txtMaCTT.setMargin(new Insets(5, 5, 5, 5));
        pnMaCTT.setFont(sgUI13);

        pnInput.add(pnMaKH);
        pnMaKH.setLayout(new BorderLayout());
        pnMaKH.add(txtMaKH, BorderLayout.CENTER);
        txtMaKH.setMargin(new Insets(5, 5, 5, 5));
        txtMaKH.setFont(sgUI13);

        pnInput.add(pnTenKH);
        pnTenKH.setLayout(new BorderLayout());
        pnTenKH.add(txtTenKH, BorderLayout.CENTER);
        txtTenKH.setMargin(new Insets(5, 5, 5, 5));
        txtTenKH.setFont(sgUI13);

        pnInput.add(pnMaNV);
        pnMaNV.setLayout(new BorderLayout());
        pnMaNV.add(txtMaNV, BorderLayout.CENTER);
        txtMaNV.setMargin(new Insets(5, 5, 5, 5));
        txtMaNV.setFont(sgUI13);

        pnInput.add(pnTenNV);
        pnTenNV.setLayout(new BorderLayout());
        pnTenNV.add(txtTenNV, BorderLayout.CENTER);
        txtTenNV.setMargin(new Insets(5, 5, 5, 5));
        txtTenNV.setFont(sgUI13);

        pnInput.add(pnTinhTrang);
        pnTinhTrang.setLayout(new BorderLayout());
        pnTinhTrang.add(txtTinhTrang, BorderLayout.CENTER);
        txtTinhTrang.setFont(sgUI13);
        txtTinhTrang.setBackground(Color.white);
        txtTinhTrang.addItem("Tình trạng");
        txtTinhTrang.addItem("Chưa xử lý");
        txtTinhTrang.addItem("Đã xử lý");

        pnInput.setBorder(new EmptyBorder(5, 8, 5, 8));

        pnBtn.setLayout(new BorderLayout());
        pnBtn.setBackground(Color.white);
        ImageIcon iconTim = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/IconFind.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        btnSearch.setIcon(iconTim);
        btnSearch.setFont(sgUI18b);
        btnSearch.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 10, 0, 10, Color.white), new EmptyBorder(10, 10, 10, 10)));
        btnSearch.setBackground(Color.decode("#87CEFA"));
        pnBtn.add(btnSearch, BorderLayout.CENTER);

        btnSearch.setRequestFocusEnabled(false);

        ImageIcon iconThoat = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/IconReset.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        btnReset.setIcon(iconThoat);
        btnReset.setFont(sgUI18b);
        btnReset.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 10, 0, 10, Color.white), new EmptyBorder(10, 10, 10, 10)));
        btnReset.setBackground(Color.decode("#87CEFA"));
        pnBtn.add(btnReset, BorderLayout.EAST);
        btnReset.setRequestFocusEnabled(false);

        pnBtn.setBorder(new EmptyBorder(5, 20, 0, 20));

        pnContent.setLayout(new BorderLayout());
        pnContent.add(pnTitleTop, BorderLayout.NORTH);
        lbList.setFont(sgUI15);
        pnTitleTop.setLayout(new BorderLayout());
        pnTitleTop.add(lbList, BorderLayout.CENTER);

        lbList.setHorizontalAlignment(JLabel.LEFT);
        lbList.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnContent.add(pnMid, BorderLayout.CENTER);
        lbList.setBackground(Color.white);
        lbList.setOpaque(true);
        lbList.setForeground(Color.black);

        pnMid.setLayout(new BorderLayout());
        pnMid.setBackground(Color.white);
        pnMid.add(scp, BorderLayout.CENTER);
        pnMid.setBorder(new EmptyBorder(0, 10, 10, 10));
        pnMid.setBackground(Color.white);
        tbCTT.getTableHeader().setBackground(Color.decode("#79CDCD"));
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        scp.setViewportView(tbCTT);
        loadTable();
        txtMaKH.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtMaKH.getText().equals("Mã khách hàng")) {
                    txtMaKH.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtMaKH.getText().trim().length() == 0) {
                    txtMaKH.setText("Mã khách hàng");
                }
            }
        });
        txtMaNV.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtMaNV.getText().equals("Mã nhân viên")) {
                    txtMaNV.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtMaNV.getText().trim().length() == 0) {
                    txtMaNV.setText("Mã nhân viên");
                }
            }
        });
        txtTenKH.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtTenKH.getText().equals("Tên khách hàng")) {
                    txtTenKH.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtTenKH.getText().trim().length() == 0) {
                    txtTenKH.setText("Tên khách hàng");
                }
            }
        });
        txtTenNV.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtTenNV.getText().equals("Tên nhân viên")) {
                    txtTenNV.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtTenNV.getText().trim().length() == 0) {
                    txtTenNV.setText("Tên nhân viên");
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

        //-----------click chuot vao table-----
        tbCTT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String maCTT = tbCTT.getValueAt(tbCTT.getSelectedRow(), tbCTT.getColumnModel().getColumnIndex("Mã chi tiết thuê")).toString();
                    String maKH = tbCTT.getValueAt(tbCTT.getSelectedRow(), tbCTT.getColumnModel().getColumnIndex("Mã khách hàng")).toString();
                    String tenKH = tbCTT.getValueAt(tbCTT.getSelectedRow(), tbCTT.getColumnModel().getColumnIndex("Tên khách hàng")).toString();
                    String maNV = tbCTT.getValueAt(tbCTT.getSelectedRow(), tbCTT.getColumnModel().getColumnIndex("Mã nhân viên")).toString();
                    String tenNV = tbCTT.getValueAt(tbCTT.getSelectedRow(), tbCTT.getColumnModel().getColumnIndex("Tên nhân viên")).toString();
                    String tien = tbCTT.getValueAt(tbCTT.getSelectedRow(), tbCTT.getColumnModel().getColumnIndex("Tiền đặt cọc")).toString();
                    String tinhtrangxuly = tbCTT.getValueAt(tbCTT.getSelectedRow(), tbCTT.getColumnModel().getColumnIndex("Tình trạng xử lý")).toString();
                    new DatPhongForm(maCTT, maKH, tenKH, maNV, tenNV, tien, tinhtrangxuly);
                }
            }
        });

        btnReset.addActionListener((ActionEvent e) -> {
            DefaultTableModel dm = (DefaultTableModel) tbCTT.getModel();
            dm.setColumnCount(0);
            loadTable();
            txtMaCTT.setText("Mã chi tiết thuê");
            txtMaKH.setText("Mã khách hàng");
            txtMaNV.setText("Mã nhân viên");
            txtTenKH.setText("Tên khách hàng");
            txtTenNV.setText("Tên nhân viên");
            txtTinhTrang.setSelectedIndex(0);
        });
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ChiTietThueDTO> CTTList = new ArrayList<>();

                try {
                    CTTList = new ChiTietThueBUS().LoadCTT2();
                } catch (Exception ex) {
                } 

                if ((txtMaCTT.getText().equals("Mã chi tiết thuê") || txtMaCTT.getText().isBlank())
                        && (txtMaKH.getText().equals("Mã khách hàng") || txtMaKH.getText().isBlank())
                        && (txtMaNV.getText().equals("Mã nhân viên") || txtMaNV.getText().isBlank())
                        && (txtTenKH.getText().equals("Tên khách hàng") || txtTenKH.getText().isBlank())
                        && (txtTenNV.getText().equals("Tên nhân viên") || txtTenNV.getText().isBlank())
                        && txtTinhTrang.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Mời lựa chọn", "Thông báo", 0);
                } else {
                    if (IDCTTisEmty(txtMaCTT.getText())) {
                        int i = 0;
                        for (i = 0; i < CTTList.size(); i++) {
                            if (CTTList.get(i).getMaChiTietThue().contains(txtMaCTT.getText()) == false) {
                                CTTList.remove(CTTList.get(i));
                                i--;
                            }
                        }
                    } else if (IDCTTisEmty(txtMaCTT.getText()) == false && txtMaCTT.getText().equals("Mã chi tiết thuê") == false) {
                        JOptionPane.showMessageDialog(null, "Mã chi tiết thuê không tồn tại", "LỖI", 0);
                    }

                    if (MaKHisEmty(txtMaKH.getText())) {
                        int i = 0;
                        for (i = 0; i < CTTList.size(); i++) {
                            if (CTTList.get(i).getMaKH().contains(txtMaKH.getText()) == false) {
                                CTTList.remove(CTTList.get(i));
                                i--;
                            }
                        }
                    } else if (MaKHisEmty(txtMaKH.getText()) == false && txtMaKH.getText().equals("Mã khách hàng") == false) {
                        JOptionPane.showMessageDialog(null, "Mã khách hàng không tồn tại", "LỖI", 0);
                    }

                    if (MaNVisEmty(txtMaNV.getText())) {
                        int i = 0;
                        for (i = 0; i < CTTList.size(); i++) {
                            if (CTTList.get(i).getMaNV().contains(txtMaNV.getText()) == false) {
                                CTTList.remove(CTTList.get(i));
                                i--;
                            }
                        }
                    } else if (MaNVisEmty(txtMaNV.getText()) == false && txtMaNV.getText().equals("Mã nhân viên") == false) {
                        JOptionPane.showMessageDialog(null, "Mã nhân viên không tồn tại", "LỖI", 0);
                    }
                    if (txtTinhTrang.getSelectedIndex() == 1) {
                        int i = 0;
                        for (i = 0; i < CTTList.size(); i++) {
                            if (CTTList.get(i).getTinhTrangXuLy() == 1) {
                                CTTList.remove(CTTList.get(i));
                                i--;
                            }
                        }
                    }
                    if (txtTinhTrang.getSelectedIndex() == 2) {
                        int i = 0;
                        for (i = 0; i < CTTList.size(); i++) {
                            if (CTTList.get(i).getTinhTrangXuLy() == 0) {
                                CTTList.remove(CTTList.get(i));
                                i--;
                            }
                        }
                    }
                    if (txtTenKH.getText().equals("Tên khách hàng") == false) {
                        int i = 0;
                        for (i = 0; i < CTTList.size(); i++) {
                            if (TenKH(CTTList.get(i).getMaKH()).contains(txtTenKH.getText()) == false) {
                                CTTList.remove(CTTList.get(i));
                                i--;
                            }
                        }
                    } else if (TenKHisEmty(txtTenKH.getText()) == false && txtTenKH.getText().equals("Tên khách hàng") == false) {
                        JOptionPane.showMessageDialog(null, "Tên khách hàng không tồn tại", "LỖI", 0);
                    }
                    if (txtTenNV.getText().equals("Tên nhân viên") == false) {
                        int i = 0;
                        for (i = 0; i < CTTList.size(); i++) {
                            if (TenNV(CTTList.get(i).getMaNV()).contains(txtTenNV.getText()) == false) {
                                CTTList.remove(CTTList.get(i));
                                i--;
                            }
                        }
                    } else if (TenNVisEmty(txtTenNV.getText()) == false && txtTenNV.getText().equals("Tên nhân viên") == false) {
                        JOptionPane.showMessageDialog(null, "Tên nhân viên không tồn tại", "LỖI", 0);
                    }
                    DefaultTableModel dm = (DefaultTableModel) tbCTT.getModel();
                    while (dm.getRowCount() > 0) {
                        dm.removeRow(0);
                    }
                    Object rowData[] = new Object[8];
                    int i = 0;
                    for (i = 0; i < CTTList.size(); i++) {
                        rowData[0] = i + 1;
                        rowData[1] = CTTList.get(i).getMaChiTietThue();
                        rowData[2] = CTTList.get(i).getMaKH();
                        rowData[3] = TenKH(CTTList.get(i).getMaKH());
                        rowData[4] = CTTList.get(i).getMaNV();
                        rowData[5] = TenNV(CTTList.get(i).getMaNV());
                        rowData[6] = CTTList.get(i).getTienDatCoc();
                        if (CTTList.get(i).getTinhTrangXuLy() == 0) {
                            rowData[7] = "Chưa xử lý";
                        } else {
                            rowData[7] = "Đã xử lý";
                        }
                        dm.addRow(rowData);
                    }
                }
            }
        });
    }

    public void loadTable() {
        //   CTTList.clear();
        DefaultTableModel dm = (DefaultTableModel) tbCTT.getModel();
        while (dm.getRowCount() > 0) {
            dm.removeRow(0);
            dm.setColumnCount(0);
        }
        dm.addColumn("STT");
        dm.addColumn("Mã chi tiết thuê");
        dm.addColumn("Mã khách hàng");
        dm.addColumn("Tên khách hàng");
        dm.addColumn("Mã nhân viên");
        dm.addColumn("Tên nhân viên");
        dm.addColumn("Tiền đặt cọc");
        dm.addColumn("Tình trạng xử lý");
        tbCTT.setModel(dm);
        ArrayList<ChiTietThueDTO> CTTList = new ArrayList<>();
        try {
            CTTList = new ChiTietThueBUS().LoadCTT2();
        } catch (Exception ex) {

        }

        Object rowData[] = new Object[8];
        for (int i = 0; i < CTTList.size(); i++) {
            rowData[0] = i + 1;
            rowData[1] = CTTList.get(i).getMaChiTietThue();
            rowData[2] = CTTList.get(i).getMaKH();
            rowData[3] = TenKH(CTTList.get(i).getMaKH());
            rowData[4] = CTTList.get(i).getMaNV();
            rowData[5] = TenNV(CTTList.get(i).getMaNV());

            rowData[6] = CTTList.get(i).getTienDatCoc();
            if (CTTList.get(i).getTinhTrangXuLy() == 0) {
                rowData[7] = "Chưa xử lý";
            } else {
                rowData[7] = "Đã xử lý";
            }
            dm.addRow(rowData);
        }
        tbCTT.setShowGrid(false);
        tbCTT.setIntercellSpacing(new Dimension(0, 0));
        tbCTT.setRowHeight(40);
        tbCTT.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbCTT.getTableHeader().setPreferredSize(new Dimension(1, 40));
        tbCTT.setBackground(Color.white);
        tbCTT.getTableHeader().setBackground(Color.decode("#79CDCD"));
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
                lb.setForeground(Color.black);
                lb.setFont(sgUI13);
                lb.setHorizontalAlignment(JLabel.CENTER);
                if (isSelected) {
                    lb.setBackground(Color.decode("#F5F5F5"));
                } else {
                    lb.setBackground(Color.decode("#FFFFFF"));
                }
                return lb;
            }
        };
        for (int i = 0; i < tbCTT.getColumnCount(); i++) {
            tbCTT.getColumnModel().getColumn(i).setCellRenderer(renderBorder);
        }
        tbCTT.getTableHeader().setFont(sgUI13b);
        tbCTT.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public static void loadTable(JTable tbCTT) {
        //    CTTList.clear();
        DefaultTableModel dm = (DefaultTableModel) tbCTT.getModel();
        while (dm.getRowCount() > 0) {
            dm.removeRow(0); //xóa table để update table mới
            dm.setColumnCount(0);
        }
        dm.addColumn("STT");
        dm.addColumn("Mã chi tiết thuê");
        dm.addColumn("Mã khách hàng");
        dm.addColumn("Tên khách hàng");
        dm.addColumn("Mã nhân viên");
        dm.addColumn("Tên nhân viên");
        dm.addColumn("Tiền đặt cọc");
        dm.addColumn("Tình trạng xử lý");
        tbCTT.setModel(dm);
        ArrayList<ChiTietThueDTO> CTTList = new ArrayList<>();
        try {
            CTTList = new ChiTietThueBUS().LoadCTT2();
        } catch (Exception ex) {

        }

        Object rowData[] = new Object[8];
        for (int i = 0; i < CTTList.size(); i++) {
            rowData[0] = i + 1;
            rowData[1] = CTTList.get(i).getMaChiTietThue();
            rowData[2] = CTTList.get(i).getMaKH();
            rowData[3] = TenKH(CTTList.get(i).getMaKH());
            rowData[4] = CTTList.get(i).getMaNV();
            rowData[5] = TenNV(CTTList.get(i).getMaNV());
            rowData[6] = CTTList.get(i).getTienDatCoc();
            if (CTTList.get(i).getTinhTrangXuLy() == 0) {
                rowData[7] = "Chưa xử lý";
            } else {
                rowData[7] = "Đã xử lý";
            }
            dm.addRow(rowData);
        }
        tbCTT.setShowGrid(false);
        tbCTT.setIntercellSpacing(new Dimension(0, 0));
        tbCTT.setRowHeight(40);
        tbCTT.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbCTT.getTableHeader().setPreferredSize(new Dimension(1, 40));
        tbCTT.setBackground(Color.white);
        tbCTT.getTableHeader().setBackground(Color.decode("#79CDCD"));
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
                lb.setForeground(Color.black);
                lb.setFont(sgUI13);
                lb.setHorizontalAlignment(JLabel.CENTER);
                if (isSelected) {
                    lb.setBackground(Color.decode("#F5F5F5"));
                } else {
                    lb.setBackground(Color.decode("#FFFFFF"));
                }
                return lb;
            }
        };
        for (int i = 0; i < tbCTT.getColumnCount(); i++) {
            tbCTT.getColumnModel().getColumn(i).setCellRenderer(renderBorder);
        }
        tbCTT.getTableHeader().setFont(sgUI13b);
        tbCTT.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public boolean IDCTTisEmty(String id) {
        ArrayList<ChiTietThueDTO> CTTList = new ArrayList<>();
        try {
            CTTList = new ChiTietThueBUS().LoadCTT2();
        } catch (Exception ex) {

        }
        try {
            for (ChiTietThueDTO ctt : CTTList) {
                if (ctt.getMaChiTietThue().contains(id)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean MaKHisEmty(String id) {
        ArrayList<ChiTietThueDTO> CTTList = new ArrayList<>();
        try {
            CTTList = new ChiTietThueBUS().LoadCTT2();
        } catch (Exception ex) {

        }
        try {
            for (ChiTietThueDTO ctt : CTTList) {
                if (ctt.getMaKH().contains(id)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean TenKHisEmty(String ten) {
        ArrayList<ChiTietThueDTO> CTTList = new ArrayList<>();
        try {
            CTTList = new ChiTietThueBUS().LoadCTT2();
        } catch (Exception ex) {
        }
        try {
            for (ChiTietThueDTO ctt : CTTList) {
                if (TenKH(ctt.getMaKH()).contains(ten)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean MaNVisEmty(String id) {
        ArrayList<ChiTietThueDTO> CTTList = new ArrayList<>();
        try {
            CTTList = new ChiTietThueBUS().LoadCTT2();
        } catch (Exception ex) {

        }
        try {
            for (ChiTietThueDTO ctt : CTTList) {
                if (ctt.getMaNV().contains(id)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean TenNVisEmty(String ten) {
        ArrayList<ChiTietThueDTO> CTTList = new ArrayList<>();
        try {
            CTTList = new ChiTietThueBUS().LoadCTT2();
        } catch (Exception ex) {
        } 
        try {
            for (ChiTietThueDTO ctt : CTTList) {
                if (TenNV(ctt.getMaNV()).contains(ten)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String TenKH(String id) {
        ArrayList<KhachHangDTO> KHList = new ArrayList<>();
        try {
            KHList = new KhachHangBUS().LoadKH();
        } catch (Exception ex) {

        }
        for (int i = 0; i < KHList.size(); i++) {
            if (KHList.get(i).getMaKH().equals(id)) {
                return KHList.get(i).getTenKH();
            }
        }
        return null;
    }

    public static String TenNV(String id) {
        ArrayList<NhanVienDTO> NVList = new ArrayList<>();
        try {
            NVList = new NhanVienBUS().LoadNV();
        } catch (Exception ex) {
        }
        for (int i = 0; i < NVList.size(); i++) {
            if (NVList.get(i).getMaNV().equals(id)) {
                return NVList.get(i).getTenNV();
            }
        }
        return null;
    }

}
