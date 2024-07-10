package GUI;

import BUS.DichVuBUS;
import BUS.BackUpData;
import DTO.DichVuDTO;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;
// link icon icons8.com

public class PanelDichVu extends JPanel {

    private JPanel pnTop = new JPanel();
    private JLabel lbTitle = new JLabel("DANH MỤC DỊCH VỤ");
    private JPanel pnTBDichVu = new JPanel();
    private JPanel pnEdit = new JPanel();
    private JScrollPane scp = new JScrollPane();
    public static JTable tbDichVu;
    private JPanel pnInfo = new JPanel();

    private JPanel pnMaDV = new JPanel();
    private JPanel pnTenDV = new JPanel();

    private JPanel pnTxtMaDV = new JPanel();
    private JPanel pnTxtTenDV = new JPanel();

    private JPanel pnLoaiDV = new JPanel();
    private JPanel pnCbLoaiDV = new JPanel();

    private JPanel pnGiaDV = new JPanel();
    private JPanel pnTxtGiaDV = new JPanel();

    private JLabel lbMaDV = new JLabel("Mã dịch vụ");
    private JTextField txtMaDV = new JTextField();
    private JLabel lbTenDV = new JLabel("Tên dịch vụ");
    private JTextField txtTenDV = new JTextField();

    private JLabel lbLoaiDV = new JLabel("Loại dịch vụ");
    private JComboBox cbLoaiDV = new JComboBox();

    private JLabel lbGiaDV = new JLabel("Giá dịch vụ");
    private JTextField txtGiaDV = new JTextField();

    private JTextField txtMaDVSearch = new JTextField("Mã dịch vụ...");
    private JTextField txtTenDVSearch = new JTextField("Tên dịch vụ...");
    private JComboBox cbGiaDVSearch = new JComboBox();
    private JComboBox cbLoaiDVSearch = new JComboBox();

    private JButton btnAdd = new JButton("Thêm");
    private JButton btnDelete = new JButton("Xóa");
    private JButton btnUpdate = new JButton("Sửa");
    private JButton btnSearch = new JButton("Tìm kiếm");
    private AddDichVu addDichVu = new AddDichVu();
    // Label tiêu đề
    JPanel pnTitle1 = new JPanel();
    JPanel pnInput1 = new JPanel();
    JPanel pnTitle2 = new JPanel();
    JPanel pnInput2 = new JPanel();
    JPanel pnButton = new JPanel();
    JPanel pnSearch = new JPanel();

    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);

    TitledBorder ttb1 = new TitledBorder(new MatteBorder(2, 0, 0, 0, Color.black), "Danh sách dịch vụ");
    TitledBorder ttb2 = new TitledBorder(new MatteBorder(2, 0, 0, 0, Color.black), "Thiết lập");

    JPanel pnTimKiem = new JPanel();
    JButton btnXuatFile = new JButton("Xuất File");

    public PanelDichVu() {
        initComponents();
    }

    public void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        lbTitle.setFont(sgUI18b);
        pnTop.add(lbTitle);

        pnTBDichVu.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 10, 10, 10), ttb1));
        pnTBDichVu.setBackground(Color.white);

        pnTBDichVu.setLayout(new BorderLayout());
        pnTBDichVu.add(scp, BorderLayout.CENTER);

        tbDichVu = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        loadTable(tbDichVu);

        scp.getViewport().add(tbDichVu);

        pnEdit.setPreferredSize(new Dimension(100, 200));
        pnEdit.setBackground(Color.white);
        pnEdit.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 10, 10, 10), ttb2));
        pnEdit.setLayout(new BorderLayout());
        pnEdit.add(pnInfo, BorderLayout.CENTER);

        pnInfo.setBackground(Color.decode("#E0F6FD"));
        pnInfo.setLayout(new BoxLayout(pnInfo, BoxLayout.X_AXIS));
        pnInfo.add(pnTitle1);

        pnTitle1.setBackground(Color.white);
        pnTitle1.setPreferredSize(new Dimension(20, 100));
        pnTitle1.setLayout(new GridLayout(2, 1));
        pnTitle1.add(pnMaDV);
        pnTitle1.add(pnTenDV);

        pnMaDV.setLayout(new BorderLayout());
        pnMaDV.add(lbMaDV, BorderLayout.CENTER);
        lbMaDV.setFont(sgUI15);
        lbMaDV.setHorizontalAlignment(JLabel.CENTER);

        pnTenDV.setLayout(new BorderLayout());
        pnTenDV.add(lbTenDV, BorderLayout.CENTER);
        lbTenDV.setFont(sgUI15);
        lbTenDV.setHorizontalAlignment(JLabel.CENTER);

        pnInput1.setPreferredSize(new Dimension(80, 100));
        pnInput1.setLayout(new GridLayout(2, 1));
        pnInput1.add(pnTxtMaDV);
        pnInput1.add(pnTxtTenDV);

        pnTxtMaDV.setLayout(new BorderLayout());
        pnTxtMaDV.add(txtMaDV, BorderLayout.CENTER);
        pnTxtMaDV.setBorder(new EmptyBorder(20, 0, 20, 0));
        txtMaDV.setFont(sgUI15p);
        txtMaDV.setMargin(new Insets(10, 10, 10, 10));
        txtMaDV.setEditable(false);

        pnTxtTenDV.setLayout(new BorderLayout());
        pnTxtTenDV.add(txtTenDV, BorderLayout.CENTER);
        pnTxtTenDV.setBorder(new EmptyBorder(20, 0, 20, 0));
        txtTenDV.setFont(sgUI15p);
        txtTenDV.setMargin(new Insets(10, 10, 10, 10));

        pnTitle2.setBackground(Color.white);
        pnTitle2.setPreferredSize(new Dimension(20, 100));
        pnTitle2.setLayout(new GridLayout(2, 1));
        pnTitle2.add(pnLoaiDV);
        pnTitle2.add(pnGiaDV);

        pnLoaiDV.setLayout(new BorderLayout());
        pnLoaiDV.add(lbLoaiDV, BorderLayout.CENTER);
        lbLoaiDV.setFont(sgUI15);
        lbLoaiDV.setHorizontalAlignment(JLabel.CENTER);

        pnGiaDV.setLayout(new BorderLayout());
        pnGiaDV.add(lbGiaDV, BorderLayout.CENTER);
        lbGiaDV.setFont(sgUI15);
        lbGiaDV.setHorizontalAlignment(JLabel.CENTER);

        pnInput2.setBackground(Color.white);
        pnInput2.setPreferredSize(new Dimension(80, 100));
        pnInput2.setLayout(new GridLayout(2, 1));
        pnInput2.add(pnCbLoaiDV);
        pnInput2.add(pnTxtGiaDV);

        pnCbLoaiDV.setLayout(new BorderLayout());
        pnCbLoaiDV.add(cbLoaiDV, BorderLayout.CENTER);
        pnCbLoaiDV.setBorder(new EmptyBorder(20, 0, 20, 0));
        cbLoaiDV.setFont(sgUI15p);
        cbLoaiDV.setBackground(Color.white);
        cbLoaiDV.addItem("Ăn uống");
        cbLoaiDV.addItem("Chăm sóc sắc đẹp");
        cbLoaiDV.addItem("Giải trí");
        cbLoaiDV.addItem("Tiện ích");
        cbLoaiDV.addItem("Tổ chức tiệc");

        pnTxtGiaDV.setLayout(new BorderLayout());
        pnTxtGiaDV.add(txtGiaDV, BorderLayout.CENTER);
        pnTxtGiaDV.setBorder(new EmptyBorder(20, 0, 20, 0));
        txtGiaDV.setFont(sgUI15p);
        txtGiaDV.setMargin(new Insets(10, 10, 10, 10));

        pnButton.setPreferredSize(new Dimension(50, 100));
        pnButton.setLayout(new GridLayout(3, 1, 0, 10));
        pnButton.setBorder(new EmptyBorder(5, 10, 5, 10));
        ImageIcon iconAdd = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/them.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon iconUpdate = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/sua.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon iconDelete = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/xoa.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        pnButton.add(btnAdd);
        pnButton.add(btnDelete);
        pnButton.add(btnUpdate);

        btnAdd.setIcon(iconAdd);
        btnAdd.setBackground(Color.decode("#90EE90"));

        btnDelete.setIcon(iconDelete);
        btnDelete.setBackground(Color.decode("#F5A89A"));

        btnUpdate.setIcon(iconUpdate);
        btnUpdate.setBackground(Color.decode("#FFFAB3"));

        pnSearch.setPreferredSize(new Dimension(250, 100));
        pnSearch.setLayout(new GridLayout(5, 1, 0, 5));
        pnSearch.add(txtMaDVSearch);
        txtMaDVSearch.setFont(sgUI15p);
        txtMaDVSearch.setMargin(new Insets(0, 10, 0, 10));
        pnSearch.add(txtTenDVSearch);
        txtTenDVSearch.setFont(sgUI15p);
        txtTenDVSearch.setMargin(new Insets(0, 10, 0, 10));
        pnSearch.add(cbLoaiDVSearch);
        cbLoaiDVSearch.setFont(sgUI15p);
        cbLoaiDVSearch.setBackground(Color.white);
        cbLoaiDVSearch.addItem("Chọn loại dịch vụ...");
        cbLoaiDVSearch.addItem("Ăn uống");
        cbLoaiDVSearch.addItem("Chăm sóc sắc đẹp");
        cbLoaiDVSearch.addItem("Giải trí");
        cbLoaiDVSearch.addItem("Tiện ích");
        cbLoaiDVSearch.addItem("Tổ chức tiệc");
        pnSearch.add(cbGiaDVSearch);
        cbGiaDVSearch.setFont(sgUI15p);
        cbGiaDVSearch.setBackground(Color.white);
        cbGiaDVSearch.addItem("Chọn giá...");
        cbGiaDVSearch.addItem("0 - 99999");
        cbGiaDVSearch.addItem("100000 - 299999");
        cbGiaDVSearch.addItem("300000 - 499999");
        cbGiaDVSearch.addItem("500000 - 999999");
        cbGiaDVSearch.addItem("Trên 1000000");
        ImageIcon iconSearch = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/IconFind.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        pnSearch.add(pnTimKiem);

        pnTimKiem.setLayout(new GridLayout(1, 2, 5, 5));
        pnTimKiem.add(btnSearch);
        pnTimKiem.add(btnXuatFile);
        
        ImageIcon iconFile = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/ex.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        btnXuatFile.setFocusPainted(false);
        btnXuatFile.setIcon(iconFile);
        btnXuatFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackUpData bk = new BUS.BackUpData();
                try {
                    String path = System.getProperty("user.dir") + "\\src\\GUI\\excel\\BackUpData_DichVu.xlsx";
                    bk.backup_DichVu(path);
                    JOptionPane.showMessageDialog(null, "Xuất file thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(new File(path));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Xuất file không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnSearch.setIcon(iconSearch);
        btnSearch.setBackground(Color.decode("#BFCAE6"));
        btnXuatFile.setBackground(Color.decode("#BFCAE6"));
        btnXuatFile.setFont(sgUI15);
        btnSearch.setFont(sgUI15);

        pnInfo.add(pnInput1);
        pnInfo.add(pnTitle2);
        pnInfo.add(pnInput2);
        pnInfo.add(pnButton);
        pnInfo.add(pnSearch);
        this.add(pnTop);
        this.add(pnTBDichVu);
        this.add(pnEdit);

        // event 
        txtMaDVSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtMaDVSearch.getText().equals("Mã dịch vụ...")) {
                    txtMaDVSearch.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if (txtMaDVSearch.getText().trim().length() == 0) {
                    txtMaDVSearch.setText("Mã dịch vụ...");
                }
            }
        });

        txtTenDVSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtTenDVSearch.getText().equals("Tên dịch vụ...")) {
                    txtTenDVSearch.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtTenDVSearch.getText().trim().length() == 0) {
                    txtTenDVSearch.setText("Tên dịch vụ...");
                }
            }
        });

        tbDichVu.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    txtMaDV.setText(tbDichVu.getValueAt(tbDichVu.getSelectedRow(), 1).toString());
                    txtTenDV.setText(tbDichVu.getValueAt(tbDichVu.getSelectedRow(), 2).toString());
                    if (tbDichVu.getValueAt(tbDichVu.getSelectedRow(), 3).toString().equals("Ăn uống")) {
                        cbLoaiDV.setSelectedIndex(0);
                    } else if (tbDichVu.getValueAt(tbDichVu.getSelectedRow(), 3).toString().equals("Chăm sóc sắc đẹp")) {
                        cbLoaiDV.setSelectedIndex(1);
                    } else if (tbDichVu.getValueAt(tbDichVu.getSelectedRow(), 3).toString().equals("Giải trí")) {
                        cbLoaiDV.setSelectedIndex(2);
                    } else if (tbDichVu.getValueAt(tbDichVu.getSelectedRow(), 3).toString().equals("Tiện ích")) {
                        cbLoaiDV.setSelectedIndex(3);
                    } else if (tbDichVu.getValueAt(tbDichVu.getSelectedRow(), 3).toString().equals("Tổ chức tiệc")) {
                        cbLoaiDV.setSelectedIndex(4);
                    }
                    txtGiaDV.setText(tbDichVu.getValueAt(tbDichVu.getSelectedRow(), 4).toString());
                } catch (Exception ex) {
                    txtMaDV.setText("");
                    txtTenDV.setText("");
                    cbLoaiDV.setSelectedIndex(0);
                    txtGiaDV.setText("");
                }
            }

        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDichVu.setVisible(true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String info = "";
                if (txtMaDV.getText().equals("")) {
                    info += "Mời chọn dòng muốn xóa";
                }
                if (info.equals("")) {
                    int answer = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa dịch vụ này", "Thông báo", JOptionPane.YES_NO_OPTION);
                    if (answer == 0) {
                        if (DichVuBUS.deleteDV(txtMaDV.getText())) {
                            JOptionPane.showMessageDialog(null, "Xóa dịch vụ thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                            loadTable(tbDichVu);
                            resetTxt();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, info, "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String info = "";
                if (txtMaDV.getText().equals("")) {
                    info += "Vui lòng chọn chọn dòng muốn sửa";
                }
                if (info.equals("")) {
                    if (txtTenDV.getText().equals("")) {
                        info += "Tên dịch vụ không được để trống\n";
                    }
                    if (txtGiaDV.getText().equals("")) {
                        info += "Giá không được để trống";
                    }
                }
                if (info.equals("")) {
                    try {
                        int giaDV = Integer.parseInt(txtGiaDV.getText());
                        DichVuDTO dvDTO = new DichVuDTO(txtMaDV.getText(), txtTenDV.getText(), cbLoaiDV.getSelectedItem().toString(), giaDV, 0);
                        if (DichVuBUS.updateDV(dvDTO)) {
                            JOptionPane.showMessageDialog(null, "Sửa dịch vụ thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                            loadTable(tbDichVu);
                            resetTxt();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Giá là một số nguyên", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, info, "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMaDVSearch.getText().equals("Mã dịch vụ...")
                        && txtTenDVSearch.getText().equals("Tên dịch vụ...")
                        && cbLoaiDVSearch.getSelectedIndex() == 0
                        && cbGiaDVSearch.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng lựa chọn", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                    loadTable(tbDichVu);
                } else {
                    ArrayList<DichVuDTO> listDichVu = DichVuBUS.getListDichVu();
                    ArrayList<DichVuDTO> listTemp = new ArrayList<>();
                    if (!txtMaDVSearch.getText().equals("Mã dịch vụ...")) {
                        for (DichVuDTO dvDTO : listDichVu) {
                            if (dvDTO.getMaDV().toUpperCase().contains(txtMaDVSearch.getText().toUpperCase())) {
                                listTemp.add(dvDTO);
                            }
                        }
                        listDichVu.clear();
                        for (DichVuDTO x : listTemp) {
                            listDichVu.add(x);
                        }
                        listTemp.clear();
                    }
                    if (!txtTenDVSearch.getText().equals("Tên dịch vụ...")) {
                        for (DichVuDTO dvDTO : listDichVu) {
                            if (dvDTO.getTenDV().toUpperCase().contains(txtTenDVSearch.getText().toUpperCase())) {
                                listTemp.add(dvDTO);
                            }
                        }
                        listDichVu.clear();
                        for (DichVuDTO x : listTemp) {
                            listDichVu.add(x);
                        }
                        listTemp.clear();
                    }
                    if (cbLoaiDVSearch.getSelectedIndex() != 0) {
                        for (DichVuDTO dvDTO : listDichVu) {
                            if (dvDTO.getTenLoaiDV().equals(cbLoaiDVSearch.getSelectedItem().toString())) {
                                listTemp.add(dvDTO);
                            }
                        }
                        listDichVu.clear();
                        for (DichVuDTO x : listTemp) {
                            listDichVu.add(x);
                        }
                        listTemp.clear();
                    }
                    if (cbGiaDVSearch.getSelectedIndex() != 0) {
                        try {
                            int start = Integer.parseInt(cbGiaDVSearch.getSelectedItem().toString().split(" - ")[0]);
                            int end = Integer.parseInt(cbGiaDVSearch.getSelectedItem().toString().split(" - ")[1]);
                            for (DichVuDTO dvDTO : listDichVu) {
                                if (dvDTO.getGiaDV() >= start && dvDTO.getGiaDV() <= end) {
                                    listTemp.add(dvDTO);
                                }
                            }
                        } catch (Exception ex) {
                            int start = Integer.parseInt(cbGiaDVSearch.getSelectedItem().toString().split("Trên ")[1]);
                            for (DichVuDTO dvDTO : listDichVu) {
                                if (dvDTO.getGiaDV() >= start) {
                                    listTemp.add(dvDTO);
                                }
                            }
                        }
                        listDichVu.clear();
                        for (DichVuDTO x : listTemp) {
                            listDichVu.add(x);
                        }
                        listTemp.clear();
                    }
                    loadTable(tbDichVu, listDichVu);

                }
            }
        });

    }

    public void resetTxt() {
        txtMaDVSearch.setText("Mã dịch vụ...");
        txtTenDVSearch.setText("Tên dịch vụ...");
        cbLoaiDVSearch.setSelectedIndex(0);
        cbGiaDVSearch.setSelectedIndex(0);
    }

    public void lightDark(int lightDark) {
        if (lightDark == 1) {
            Color BlackCl = new Color(51, 51, 51);
            pnTop.setBackground(BlackCl);
            lbTitle.setForeground(Color.white);
            scp.getViewport().setBackground(BlackCl);
            ttb1.setTitleColor(Color.white);
            ttb2.setTitleColor(Color.white);
            ttb1.setBorder(new MatteBorder(2, 0, 0, 0, Color.white));
            ttb2.setBorder(new MatteBorder(2, 0, 0, 0, Color.white));
            tbDichVu.getTableHeader().setBackground(BlackCl);
            tbDichVu.getTableHeader().setForeground(Color.white);
            pnTBDichVu.setBackground(BlackCl);
            pnEdit.setBackground(BlackCl);
            scp.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(10, 10, 10, 10, Color.decode("#606060")), new MatteBorder(5, 5, 5, 5, Color.decode("#606060"))));
            pnMaDV.setBackground(Color.decode("#606060"));
            pnTenDV.setBackground(Color.decode("#606060"));
            pnInput1.setBackground(Color.decode("#606060"));
            pnTxtMaDV.setBackground(Color.decode("#606060"));
            pnTxtTenDV.setBackground(Color.decode("#606060"));
            pnLoaiDV.setBackground(Color.decode("#606060"));
            pnGiaDV.setBackground(Color.decode("#606060"));
            pnCbLoaiDV.setBackground(Color.decode("#606060"));
            pnTxtGiaDV.setBackground(Color.decode("#606060"));
            pnButton.setBackground(Color.decode("#606060"));
            pnSearch.setBackground(Color.decode("#606060"));
            lbMaDV.setForeground(Color.white);
            lbTenDV.setForeground(Color.white);
            lbGiaDV.setForeground(Color.white);
            lbLoaiDV.setForeground(Color.white);
            pnInfo.setBackground(Color.decode("#606060"));
            tbDichVu.setBackground(Color.decode("#E0E0E0"));
            pnTimKiem.setBackground(BlackCl);
        } else {
            pnTop.setBackground(Color.white);
            lbTitle.setForeground(Color.black);
            scp.getViewport().setBackground(Color.decode("#1E90FF"));
            ttb1.setTitleColor(Color.black);
            ttb1.setBorder(new MatteBorder(2, 0, 0, 0, Color.black));
            ttb2.setBorder(new MatteBorder(2, 0, 0, 0, Color.black));
            ttb2.setTitleColor(Color.black);
            tbDichVu.getTableHeader().setBackground(Color.decode("#87CEFF"));
            tbDichVu.getTableHeader().setForeground(Color.black);
            pnTBDichVu.setBackground(Color.white);
            pnEdit.setBackground(Color.white);
            scp.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(10, 10, 10, 10, Color.decode("#1E90FF")), new MatteBorder(5, 5, 5, 5, Color.decode("#1E90FF"))));
            pnMaDV.setBackground(Color.decode("#E0F6FD"));
            pnTenDV.setBackground(Color.decode("#E0F6FD"));
            pnInput1.setBackground(Color.decode("#E0F6FD"));
            pnTxtMaDV.setBackground(Color.decode("#E0F6FD"));
            pnTxtTenDV.setBackground(Color.decode("#E0F6FD"));
            pnLoaiDV.setBackground(Color.decode("#E0F6FD"));
            pnGiaDV.setBackground(Color.decode("#E0F6FD"));
            pnCbLoaiDV.setBackground(Color.decode("#E0F6FD"));
            pnTxtGiaDV.setBackground(Color.decode("#E0F6FD"));
            pnButton.setBackground(Color.decode("#E0F6FD"));
            pnSearch.setBackground(Color.decode("#E0F6FD"));
            lbMaDV.setForeground(Color.black);
            lbTenDV.setForeground(Color.black);
            lbGiaDV.setForeground(Color.black);
            lbLoaiDV.setForeground(Color.black);
            pnInfo.setBackground(Color.decode("#E0F6FD"));
            tbDichVu.setBackground(Color.white);
            pnTimKiem.setBackground(Color.white);
        }
    }

    public static void loadTable(JTable tbDichVu) {
        DefaultTableModel tbModel = new DefaultTableModel();
        tbModel.addColumn("STT");
        tbModel.addColumn("Mã dịch vụ");
        tbModel.addColumn("Tên dịch vụ");
        tbModel.addColumn("Loại dịch vụ");
        tbModel.addColumn("Giá");
        ArrayList<DichVuDTO> listDichVu = DichVuBUS.getListDichVu();
        int i = 1;
        for (DichVuDTO x : listDichVu) {
            Object data[] = {i, x.getMaDV(), x.getTenDV(), x.getTenLoaiDV(), x.getGiaDV()};
            tbModel.addRow(data);
            i++;
        }
        tbDichVu.setModel(tbModel);
        tbDichVu.setBorder(new MatteBorder(0, 1, 1, 1, Color.black));
        tbDichVu.getTableHeader().setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        tbDichVu.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        tbDichVu.getTableHeader().setPreferredSize(new Dimension(10, 50));
        DefaultTableCellRenderer right = new DefaultTableCellRenderer();
        right.setHorizontalAlignment(JLabel.CENTER);
        tbDichVu.getColumnModel().getColumn(0).setCellRenderer(right);
        tbDichVu.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbDichVu.getColumnModel().getColumn(1).setCellRenderer(right);
        tbDichVu.getColumnModel().getColumn(4).setCellRenderer(right);
        tbDichVu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbDichVu.setRowHeight(50);
        tbDichVu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    }

    public static void loadTable(JTable tbDichVu, ArrayList<DichVuDTO> listDV) {
        DefaultTableModel tbModel = new DefaultTableModel();
        tbModel.addColumn("STT");
        tbModel.addColumn("Mã dịch vụ");
        tbModel.addColumn("Tên dịch vụ");
        tbModel.addColumn("Loại dịch vụ");
        tbModel.addColumn("Giá");
        ArrayList<DichVuDTO> listDichVu = listDV;
        int i = 1;
        for (DichVuDTO x : listDichVu) {
            Object data[] = {i, x.getMaDV(), x.getTenDV(), x.getTenLoaiDV(), x.getGiaDV()};
            tbModel.addRow(data);
            i++;
        }
        tbDichVu.setModel(tbModel);
        tbDichVu.setBorder(new MatteBorder(0, 1, 1, 1, Color.black));
        tbDichVu.getTableHeader().setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        tbDichVu.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        tbDichVu.getTableHeader().setPreferredSize(new Dimension(10, 50));
        DefaultTableCellRenderer right = new DefaultTableCellRenderer();
        right.setHorizontalAlignment(JLabel.CENTER);
        tbDichVu.getColumnModel().getColumn(0).setCellRenderer(right);
        tbDichVu.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbDichVu.getColumnModel().getColumn(1).setCellRenderer(right);
        tbDichVu.getColumnModel().getColumn(4).setCellRenderer(right);
        tbDichVu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbDichVu.setRowHeight(50);
        tbDichVu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    }
}
