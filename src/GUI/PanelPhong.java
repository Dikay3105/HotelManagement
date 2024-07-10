package GUI;

import BUS.PhongBUS;
import BUS.BackUpData;
import BUS.ImportExcel;
import BUS.TienIchBUS;
import DTO.PhongDTO;
import static GUI.PanelNhanVien.loadTalbe;
import GUI.Phong.RoomInformation;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class PanelPhong extends JPanel {

    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);
    private AddPhongGUI apGUI = new AddPhongGUI();
    private JPanel pnEmpty = new JPanel();
    private JPanel pnBtnAdd = new JPanel();
    private JPanel pnContentCenter = new JPanel();
    private JPanel pnEmptyAdd = new JPanel();
    private JPanel pnBtnAddP = new JPanel();
    private JButton btnAdd = new JButton("Thêm phòng");
    private JPanel pnInput = new JPanel();
    private JPanel pnContentTable = new JPanel();
    private JPanel pnInputCenter = new JPanel();
    private JPanel pnInputRight = new JPanel();
    private JTextField txtMaP = new JTextField("Mã phòng...");
    private JTextField txtTenP = new JTextField("Tên phòng...");
    private JComboBox cbLoaiP = new JComboBox();
    private JComboBox cbGiaP = new JComboBox();
    private JComboBox cbTinhTrangP = new JComboBox();
    private JComboBox cbHienTrangP = new JComboBox();
    private JButton btnSearch = new JButton("Tìm kiếm");
    private JButton btnReset = new JButton("Làm mới");
    private JPanel pnList = new JPanel();
    private JLabel lbList = new JLabel("Danh sách phòng");
    private JPanel pnTable = new JPanel();
    private JScrollPane scp = new JScrollPane();
    public static JTable tbPhong = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    JButton btnXuatFile = new JButton("Xuất File");
    JButton btnImport = new JButton("Chọn File");

    public PanelPhong() {
        initComponents();
    }

    public void initComponents() {
        setLayout(new BorderLayout());
        add(pnBtnAdd, BorderLayout.NORTH);
        add(pnContentCenter, BorderLayout.CENTER);

        //-----pnBtnAdd
        pnBtnAdd.setLayout(new BorderLayout());
        pnBtnAdd.add(pnEmpty, BorderLayout.CENTER);
        pnBtnAdd.add(pnBtnAddP, BorderLayout.WEST);
        //-----pnBtnAddP
        pnBtnAddP.setLayout(new GridLayout(1, 3, 5, 5));

        pnBtnAddP.add(btnAdd);
        pnBtnAddP.add(btnXuatFile);
        pnBtnAddP.add(btnImport);
        ImageIcon iconFile = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/ex.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        btnXuatFile.setFocusPainted(false);
        btnXuatFile.setIcon(iconFile);
        btnImport.setFocusPainted(false);
        btnImport.setIcon(iconFile);
        btnImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser("src\\GUI\\excel");
                fc.showDialog(null, "Open");
                try {
                    File file = fc.getSelectedFile();
                    ImportExcel ie = new ImportExcel();
                    ie.ImportExcel_NhanVien(file.getPath());
                    renderTBPhong(tbPhong);
                } catch (Exception ex) {
                }
            }
        });
        btnXuatFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackUpData bk = new BUS.BackUpData();
                try {
                    String path = System.getProperty("user.dir") + "\\src\\GUI\\excel\\BackUpData_Phong.xlsx";
                    bk.backup_Phong(path);
                    JOptionPane.showMessageDialog(null, "Xuất file thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(new File(path));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Xuất file không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        pnBtnAddP.setBorder(new EmptyBorder(10, 10, 10, 10));
        btnAdd.setBorder(new EmptyBorder(5, 10, 5, 10));
        btnXuatFile.setBorder(new EmptyBorder(5, 10, 5, 10));
        btnImport.setBorder(new EmptyBorder(5, 10, 5, 10));
//        btnAdd.setBackground(Color.white);
        //-----btnAdd
        ImageIcon iconAdd = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/them.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btnAdd.setIcon(iconAdd);
        btnAdd.setRequestFocusEnabled(false);
        btnXuatFile.setRequestFocusEnabled(false);
        //-----pnContentCenter
        pnContentCenter.setLayout(new BorderLayout());
        pnContentCenter.setBorder(new EmptyBorder(0, 10, 10, 10));
        pnContentCenter.add(pnInput, BorderLayout.NORTH);
        pnContentCenter.add(pnContentTable, BorderLayout.CENTER);
        //-----pnInput
        pnInput.setBorder(new EmptyBorder(0, 0, 10, 0));
        pnInput.setLayout(new BorderLayout());
        pnInput.add(pnInputCenter, BorderLayout.CENTER);
        pnInput.add(pnInputRight, BorderLayout.EAST);
        //-----pnInputCenter
        pnInputCenter.setLayout(new GridLayout(1, 6, 5, 5));
        pnInputCenter.add(txtMaP);
        pnInputCenter.add(txtTenP);
        pnInputCenter.add(cbLoaiP);
        pnInputCenter.add(cbGiaP);
        pnInputCenter.add(cbTinhTrangP);
        pnInputCenter.add(cbHienTrangP);
        //-----txtMap tenP
        txtMaP.setMargin(new Insets(5, 5, 5, 5));
        txtTenP.setMargin(new Insets(5, 5, 5, 5));
        //-----cbLoaiP
        cbLoaiP.setBackground(Color.white);
        cbLoaiP.setFont(sgUI13);
        cbLoaiP.removeAllItems();
        cbLoaiP.addItem("Chọn loại phòng...");
        cbLoaiP.addItem("VIP");
        cbLoaiP.addItem("Thường");
        //-----cbGiaP
        cbGiaP.setBackground(Color.white);
        cbGiaP.setFont(sgUI13);
        cbGiaP.removeAllItems();
        cbGiaP.addItem("Chọn giá...");
        cbGiaP.addItem("0 - 99999");
        cbGiaP.addItem("100000 - 299999");
        cbGiaP.addItem("300000 - 499999");
        cbGiaP.addItem("500000 - 999999");
        cbGiaP.addItem("Trên 1000000");
        //-----cbTinhTrang
        cbTinhTrangP.setBackground(Color.white);
        cbTinhTrangP.setFont(sgUI13);
        cbTinhTrangP.removeAllItems();
        cbTinhTrangP.addItem("Chọn tình trạng...");
        cbTinhTrangP.addItem("Trống");
        cbTinhTrangP.addItem("Đang được thuê");
        cbTinhTrangP.addItem("Chưa dọn phòng");
        //-----cbHienTrang
        cbHienTrangP.setBackground(Color.white);
        cbHienTrangP.setFont(sgUI13);
        cbHienTrangP.removeAllItems();
        cbHienTrangP.addItem("Chọn hiện trạng...");
        cbHienTrangP.addItem("Cũ");
        cbHienTrangP.addItem("Mới");
        //-----pnInputRight
        pnInputRight.setLayout(new GridLayout(1, 2, 2, 2));
        pnInputRight.add(btnSearch);
        pnInputRight.add(btnReset);
        ImageIcon iconReset = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/IconReset.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
        btnReset.setIcon(iconReset);
        btnReset.setFocusPainted(false);
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        pnInputRight.setBorder(new EmptyBorder(0, 5, 0, 0));
        //-----btnSearch
        btnSearch.setBackground(Color.decode("#CCFFFF"));
        btnSearch.setRequestFocusEnabled(false);
        ImageIcon iconSearch = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/IconFind.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        btnSearch.setIcon(iconSearch);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMaP.getText().equals("Mã phòng...")
                        && txtTenP.getText().equals("Tên phòng...")
                        && cbLoaiP.getSelectedIndex() == 0
                        && cbGiaP.getSelectedIndex() == 0
                        && cbTinhTrangP.getSelectedIndex() == 0
                        && cbHienTrangP.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập dữ liệu tìm kiếm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    renderTBPhong(tbPhong);
                } else {
                    ArrayList<PhongDTO> listPhong = PhongBUS.getListPhong();
                    ArrayList<PhongDTO> listTemp = new ArrayList<>();
                    if (!txtMaP.getText().equals("Mã phòng...")) {
                        for (PhongDTO x : listPhong) {
                            if (x.getMaP().toUpperCase().contains(txtMaP.getText().toUpperCase())) {
                                listTemp.add(x);
                            }
                        }
                        listPhong.clear();
                        for (PhongDTO x : listTemp) {
                            listPhong.add(x);
                        }
                        listTemp.clear();
                    }
                    if (!txtTenP.getText().equals("Tên phòng...")) {
                        for (PhongDTO x : listPhong) {
                            if (x.getTenP().toUpperCase().contains(txtTenP.getText().toUpperCase())) {
                                listTemp.add(x);
                            }
                        }
                        listPhong.clear();
                        for (PhongDTO x : listTemp) {
                            listPhong.add(x);
                        }
                        listTemp.clear();
                    }

                    if (cbLoaiP.getSelectedIndex() != 0) {
                        if (cbLoaiP.getSelectedIndex() == 1) {
                            for (PhongDTO x : listPhong) {
                                if (x.getLoaiP().equals("VIP")) {
                                    listTemp.add(x);
                                }
                            }
                        } else {
                            for (PhongDTO x : listPhong) {
                                if (x.getLoaiP().equals("Thường")) {
                                    listTemp.add(x);
                                }
                            }
                        }
                        listPhong.clear();
                        for (PhongDTO x : listTemp) {
                            listPhong.add(x);
                        }
                        listTemp.clear();
                    }

                    if (cbHienTrangP.getSelectedIndex() != 0) {
                        if (cbHienTrangP.getSelectedIndex() == 2) {
                            for (PhongDTO x : listPhong) {
                                if (x.getHienTrang().equals("Mới")) {
                                    listTemp.add(x);
                                }
                            }
                        } else {
                            for (PhongDTO x : listPhong) {
                                if (x.getHienTrang().equals("Cũ")) {
                                    listTemp.add(x);
                                }
                            }
                        }
                        listPhong.clear();
                        for (PhongDTO x : listTemp) {
                            listPhong.add(x);
                        }
                        listTemp.clear();
                    }

                    if (cbTinhTrangP.getSelectedIndex() != 0) {
                        if (cbTinhTrangP.getSelectedIndex() == 1) {
                            for (PhongDTO x : listPhong) {
                                if (x.getTinhTrang().equals("Trống")) {
                                    listTemp.add(x);
                                }
                            }
                        } else if (cbTinhTrangP.getSelectedIndex() == 2) {
                            for (PhongDTO x : listPhong) {
                                if (x.getTinhTrang().equals("Đang được thuê")) {
                                    listTemp.add(x);
                                }
                            }
                        } else {
                            for (PhongDTO x : listPhong) {
                                if (x.getTinhTrang().equals("Chưa được dọn")) {
                                    listTemp.add(x);
                                }
                            }
                        }
                        listPhong.clear();
                        for (PhongDTO x : listTemp) {
                            listPhong.add(x);
                        }
                        listTemp.clear();
                    }

                    if (cbGiaP.getSelectedIndex() != 0) {
                        if (cbGiaP.getSelectedIndex() == 5) {
                            int checkGia = Integer.parseInt(cbGiaP.getSelectedItem().toString().split("Trên ")[1]);
                            for (PhongDTO x : listPhong) {
                                if (x.getGiaP() >= checkGia) {
                                    listTemp.add(x);
                                }
                            }
                        } else {
                            int checkDown = Integer.parseInt(cbGiaP.getSelectedItem().toString().split(" - ")[0]);
                            int checkUp = Integer.parseInt(cbGiaP.getSelectedItem().toString().split(" - ")[1]);
                            for (PhongDTO x : listPhong) {
                                if (x.getGiaP() >= checkDown && x.getGiaP() <= checkUp) {
                                    listTemp.add(x);
                                }
                            }
                        }
                        listPhong.clear();
                        for (PhongDTO x : listTemp) {
                            listPhong.add(x);
                        }
                        listTemp.clear();
                    }
                    if (listPhong.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không có phòng như bạn muốn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                    } else {
                        renderTBPhong(tbPhong, listPhong);
                    }
                }
            }
        });
        //-----focus
        txtMaP.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtMaP.getText().equals("Mã phòng...")) {
                    txtMaP.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtMaP.getText().trim().length() == 0) {
                    txtMaP.setText("Mã phòng...");
                }
            }
        });
        txtTenP.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtTenP.getText().equals("Tên phòng...")) {
                    txtTenP.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtTenP.getText().trim().length() == 0) {
                    txtTenP.setText("Tên phòng...");
                }
            }
        });
        //-----pnContentTable
        pnContentTable.setLayout(new BorderLayout());
        pnContentTable.add(pnList, BorderLayout.NORTH);
        pnContentTable.add(pnTable, BorderLayout.CENTER);
        //-----pnList
        pnList.setLayout(new BorderLayout());
        pnList.add(lbList, BorderLayout.CENTER);

        lbList.setFont(sgUI15);

        //-----pntable
        pnTable.setLayout(new BorderLayout());
        pnTable.add(scp, BorderLayout.CENTER);
        //-----scp 

        scp.setViewportView(tbPhong);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (TienIchBUS.getListTienIch().size() == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng thêm tiện ích", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    apGUI.setVisible(true);
                }
            }
        });

        tbPhong.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    JPopupMenu pm = new JPopupMenu();
                    JMenuItem mi = new JMenuItem("Sửa phòng");
                    JMenuItem miD = new JMenuItem("Dọn phòng");
                    pm.add(mi);
                    pm.add(miD);
                    pm.show(tbPhong, e.getX(), e.getY());
                    mi.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                String maP = tbPhong.getValueAt(tbPhong.getSelectedRow(), tbPhong.getColumnModel().getColumnIndex("Mã phòng")).toString();
                                String tenP = tbPhong.getValueAt(tbPhong.getSelectedRow(), tbPhong.getColumnModel().getColumnIndex("Tên phòng")).toString();
                                String loaiP = tbPhong.getValueAt(tbPhong.getSelectedRow(), tbPhong.getColumnModel().getColumnIndex("Loại phòng")).toString();
                                String GiaP = tbPhong.getValueAt(tbPhong.getSelectedRow(), tbPhong.getColumnModel().getColumnIndex("Giá phòng")).toString();
                                String tinhTrangP = tbPhong.getValueAt(tbPhong.getSelectedRow(), tbPhong.getColumnModel().getColumnIndex("Tình trạng")).toString();
                                String hienTrangP = tbPhong.getValueAt(tbPhong.getSelectedRow(), tbPhong.getColumnModel().getColumnIndex("Hiện trạng")).toString();
                                new RoomInformation(maP, tenP, GiaP, loaiP, tinhTrangP, hienTrangP);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng cần sửa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    });
                    miD.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                String tinhTrangP = tbPhong.getValueAt(tbPhong.getSelectedRow(), tbPhong.getColumnModel().getColumnIndex("Tình trạng")).toString();
                                if (tinhTrangP.equalsIgnoreCase("Trống")) {
                                    JOptionPane.showMessageDialog(null, "Phòng đang trống không cần dọn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                } else if (tinhTrangP.equalsIgnoreCase("Đang được thuê")) {
                                    JOptionPane.showMessageDialog(null, "Phòng đang được khách thuê không được dọn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    String maP = tbPhong.getValueAt(tbPhong.getSelectedRow(), tbPhong.getColumnModel().getColumnIndex("Mã phòng")).toString();
                                    if (PhongBUS.updateTT(maP, "Trống")) {
                                        JOptionPane.showMessageDialog(null, "Đã dọn phòng","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                                        renderTBPhong(tbPhong);
                                    }
                                }
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng cần dọn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }

                    });
                }
            }
        });
        renderTBPhong(tbPhong);
    }

    public void reset() {
        txtMaP.setText("Mã phòng...");
        txtTenP.setText("Tên phòng...");
        cbLoaiP.setSelectedIndex(0);
        cbGiaP.setSelectedIndex(0);
        cbHienTrangP.setSelectedIndex(0);
        cbTinhTrangP.setSelectedIndex(0);
        renderTBPhong(tbPhong);
    }

    public static void renderTBPhong(JTable tbPhong) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Mã phòng");
        dtm.addColumn("Tên phòng");
        dtm.addColumn("Loại phòng");
        dtm.addColumn("Giá phòng");
        dtm.addColumn("Tình trạng");
        dtm.addColumn("Hiện trạng");
        int i = 1;
        for (PhongDTO x : PhongBUS.getListPhong()) {
            Object data[] = {i, x.getMaP(), x.getTenP(), x.getLoaiP(), x.getGiaP(), x.getTinhTrang(), x.getHienTrang()};
            dtm.addRow(data);
            i++;
        }
        tbPhong.setModel(dtm);
        tbPhong.setShowGrid(false);
        tbPhong.setIntercellSpacing(new Dimension(0, 0));
        tbPhong.setRowHeight(40);
        tbPhong.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbPhong.getTableHeader().setPreferredSize(new Dimension(1, 40));
        tbPhong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tbPhong.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
                if (column == table.getColumnModel().getColumnIndex("Tình trạng")) {
                    if (lb.getText().equals("Trống")) {
                        lb.setForeground(Color.decode("#339900"));
                    } else if (lb.getText().equals("Đang được thuê")) {
                        lb.setForeground(Color.decode("#CC0000"));
                    } else {
                        lb.setForeground(Color.decode("#FFCC00"));
                    }
                } else {
                    lb.setForeground(Color.black);
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
        for (int j = 0; j < tbPhong.getColumnCount(); j++) {
            tbPhong.getColumnModel().getColumn(j).setCellRenderer(renderBorder);
        }
    }

    public static void renderTBPhong(JTable tbPhong, ArrayList<PhongDTO> listPhong) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Mã phòng");
        dtm.addColumn("Tên phòng");
        dtm.addColumn("Loại phòng");
        dtm.addColumn("Giá phòng");
        dtm.addColumn("Tình trạng");
        dtm.addColumn("Hiện trạng");
        int i = 1;
        for (PhongDTO x : listPhong) {
            Object data[] = {i, x.getMaP(), x.getTenP(), x.getLoaiP(), x.getGiaP(), x.getTinhTrang(), x.getHienTrang()};
            dtm.addRow(data);
            i++;
        }
        tbPhong.setModel(dtm);
        tbPhong.setShowGrid(false);
        tbPhong.setIntercellSpacing(new Dimension(0, 0));
        tbPhong.setRowHeight(40);
        tbPhong.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbPhong.getTableHeader().setPreferredSize(new Dimension(1, 40));
        tbPhong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tbPhong.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
                if (column == table.getColumnModel().getColumnIndex("Tình trạng")) {
                    if (lb.getText().equals("Trống")) {
                        lb.setForeground(Color.decode("#339900"));
                    } else if (lb.getText().equals("Đang được thuê")) {
                        lb.setForeground(Color.decode("#CC0000"));
                    } else {
                        lb.setForeground(Color.decode("#FFCC00"));
                    }
                } else {
                    lb.setForeground(Color.black);
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
        for (int j = 0; j < tbPhong.getColumnCount(); j++) {
            tbPhong.getColumnModel().getColumn(j).setCellRenderer(renderBorder);
        }
    }

    public void lightDark(int ligthDark) {
        if (ligthDark == 1) {
            Color black = new Color(51, 51, 51);
            btnAdd.setBackground(Color.decode("#F8F8FF"));
            btnXuatFile.setBackground(Color.decode("#F8F8FF"));
            btnImport.setBackground(Color.decode("#F8F8FF"));
            pnBtnAddP.setBackground(black);
            pnInputCenter.setBackground(black);
            pnInputRight.setBackground(black);
            pnInput.setBackground(black);
            pnEmpty.setBackground(black);
            pnContentCenter.setBackground(black);
            tbPhong.getTableHeader().setBackground(Color.decode("#898989"));
            lbList.setForeground(Color.white);
            pnList.setBackground(Color.decode("#555555"));
            scp.setBorder(new MatteBorder(0, 1, 1, 1, Color.white));
            scp.getViewport().setBackground(Color.decode("#707070"));
            lbList.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.white), new EmptyBorder(5, 5, 5, 5)));
            pnInputRight.setBackground(black);
            btnReset.setBackground(Color.lightGray);
            btnReset.setForeground(Color.black);
        } else {
            pnBtnAddP.setBackground(Color.white);
            btnAdd.setBackground(Color.decode("#90EE90"));
            btnXuatFile.setBackground(Color.decode("#90EE90"));
            btnImport.setBackground(Color.decode("#90EE90"));
            pnInputCenter.setBackground(Color.white);
            pnInputRight.setBackground(Color.white);
            pnInput.setBackground(Color.white);
            pnContentCenter.setBackground(Color.white);
            tbPhong.getTableHeader().setBackground(Color.decode("#99FFCC"));
            lbList.setForeground(Color.black);
            pnList.setBackground(Color.white);
            pnEmpty.setBackground(Color.white);
            scp.setBorder(new MatteBorder(0, 1, 1, 1, Color.black));
            scp.getViewport().setBackground(Color.white);
            lbList.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.black), new EmptyBorder(5, 5, 5, 5)));
            pnInputRight.setBackground(Color.white);
            btnReset.setBackground(Color.white);
            btnReset.setForeground(Color.black);
        }
    }
}
