package GUI.Phong;

import BUS.DichVuBUS;
import DTO.DichVuDTO;
import DTO.SuDungDichVuDTO;
import GUI.DatPhongGUI;
import GUI.FrameOrderRoom;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class PanelSelectDichVu extends JFrame {

    JProgressBar pb = new JProgressBar();
    JPanel pnSelectDichVu = new JPanel();

    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);
    Font sgUI25b = new Font("Segoe UI", Font.BOLD, 25);

    ArrayList<DichVuDTO> listDV = DichVuBUS.getListDichVu();

    public boolean checkTimeNow(Date date) {

        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        Date d = new Date();

        return true;

    }

    public PanelSelectDichVu() {
        initComponents();
        btnXuly.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dateNow = new Date();
                String error = "";
                if (txtMaDV.getText().trim().length() == 0) {
                    error += "Vui lòng chọn dịch vụ muốn thuê\n";
                }
                if (error.length() == 0) {
                    if (dateNgaySD.getDate() == null) {
                        error += "Vui lòng chọn ngày muốn thuê\n";
                    }
                    if (txtSLDV.getText().trim().length() == 0) {
                        error += "Vui lòng nhập số lượng";
                        txtSLDV.requestFocus();
                    }
                    if (error.length() == 0) {
                        try {
                            int SL = Integer.parseInt(txtSLDV.getText());
                            if (SL <= 0) {
                                JOptionPane.showMessageDialog(null, "Số lượng là số lớn hơn 0", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                if (checkTimeNow(dateNgaySD.getDate())) {
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    SuDungDichVuDTO sdDV = new SuDungDichVuDTO();

                                    sdDV.setMaDV(txtMaDV.getText());
                                    sdDV.setNgaySuDungString(sdf.format(dateNgaySD.getDate()));
                                    sdDV.setSoLuong(SL);
                                    sdDV.setDonGia(SL * Integer.parseInt(txtGiaDV.getText()));
                                    sdDV.setXuLy(0);

                                    DichVuDTO dv = new DichVuDTO();
                                    dv.setMaDV(txtMaDV.getText());
                                    dv.setTenDV(txtTenDV.getText());
                                    dv.setGiaDV(Integer.parseInt(txtGiaDV.getText()));
                                    dv.setXuLy(0);
                                    dv.setTenLoaiDV(txtLoaiDV.getText());

                                    DatPhongGUI.listDV.add(dv);
                                    DatPhongGUI.listSDDV.add(sdDV);
                                    DatPhongGUI.renderDV(DatPhongGUI.tbDV);
                                    dispose();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Ngày sử dụng phải lớn hơn hoặc bằng ngày hiện tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                }

                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Số lượng là số nguyên", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, error, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, error, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public PanelSelectDichVu(int i) {
        initComponents();
        btnXuly.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dateNow = new Date();
                String error = "";
                if (txtMaDV.getText().trim().length() == 0) {
                    error += "Vui lòng chọn dịch vụ muốn thuê\n";
                }
                if (error.length() == 0) {
                    if (dateNgaySD.getDate() == null) {
                        error += "Vui lòng chọn ngày muốn thuê\n";
                    }
                    if (txtSLDV.getText().trim().length() == 0) {
                        error += "Vui lòng nhập số lượng";
                        txtSLDV.requestFocus();
                    }
                    if (error.length() == 0) {
                        try {
                            int SL = Integer.parseInt(txtSLDV.getText());
                            if (SL <= 0) {
                                JOptionPane.showMessageDialog(null, "Số lượng là số lớn hơn 0", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                if (checkTimeNow(dateNgaySD.getDate())) {
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                                    DichVuDTO dv = new DichVuDTO();
                                    dv.setMaDV(txtMaDV.getText());
                                    dv.setTenDV(txtTenDV.getText());
                                    dv.setGiaDV(Integer.parseInt(txtGiaDV.getText()));
                                    dv.setXuLy(0);
                                    dv.setTenLoaiDV(txtLoaiDV.getText());

                                    FrameOrderRoom.dvdto = dv;
                                    FrameOrderRoom.SL = SL;
                                    FrameOrderRoom.ngayDV = sdf.format(dateNgaySD.getDate());
                                    FrameOrderRoom.btnChonDV.doClick();
                                    dispose();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Ngày sử dụng phải lớn hơn hoặc bằng ngày hiện tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                }

                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Số lượng là số nguyên", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, error, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, error, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
    private JPanel pnContent = new JPanel();
    private JPanel pnSearch = new JPanel();
    private JPanel pnSearchInput = new JPanel();
    private JTextField txtSearch = new JTextField();
    private JPanel pnTable = new JPanel();
    private JScrollPane scp = new JScrollPane();
    private JTable tbDV = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JPanel pnInput = new JPanel();
    private JButton btnSearch = new JButton();
    private JPanel pnSelect = new JPanel();
    private JPanel pnCenter = new JPanel();
    private JCheckBox ckbAnUong = new JCheckBox("Ăn uống");
    private JCheckBox ckbSacDep = new JCheckBox("Chăm sóc sắc đẹp");
    private JCheckBox ckbGiaiTri = new JCheckBox("Giải trí");
    private JCheckBox ckbTienIch = new JCheckBox("Tiện ích");
    private JCheckBox ckbTiec = new JCheckBox("Tổ chức tiệc");
    private JButton btnReset = new JButton("Làm mới");
    private JPanel pnDV = new JPanel();

    private JPanel pnMaDV = new JPanel();
    private JLabel lbMaDV = new JLabel("Mã dịch vụ:   ");
    private JTextField txtMaDV = new JTextField();

    private JPanel pnTenDV = new JPanel();
    private JLabel lbTenDV = new JLabel("Tên dịch vụ: ");
    private JTextField txtTenDV = new JTextField();

    private JPanel pnLoaiDV = new JPanel();
    private JLabel lbLoaiDV = new JLabel("Loại dịch vụ: ");
    private JTextField txtLoaiDV = new JTextField();

    private JPanel pnGiaDV = new JPanel();
    private JLabel lbGiaDV = new JLabel("Giá dịch vụ:  ");
    private JTextField txtGiaDV = new JTextField();

    private JPanel pnSLDV = new JPanel();
    private JLabel lbSLDV = new JLabel("Số lượng:     ");
    private JTextField txtSLDV = new JTextField();

    private JPanel pnEmp1 = new JPanel();
    private JPanel pnEmp2 = new JPanel();

    private JPanel pnNgaySDDV = new JPanel();
    private JLabel lbNgaySDDV = new JLabel("Ngày sử dụng:");
    private JDateChooser dateNgaySD = new JDateChooser();

    private JPanel pnBtn = new JPanel();
    private JButton btnXuly = new JButton("Lưu");

    ArrayList<JCheckBox> listCkb = new ArrayList<>();

    public void initComponents() {
        setSize(700, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new BorderLayout());
        setResizable(false);
        add(pnSelectDichVu, BorderLayout.CENTER);
        pnSelectDichVu.setLayout(new BorderLayout());
        pnSelectDichVu.add(pb, BorderLayout.NORTH);
        pb.setValue(0);
        pb.setStringPainted(true);
        pb.setForeground(Color.red);
        pb.setBackground(Color.black);
        pb.setVisible(true);

//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int counter = 0;
        pb.setFont(new Font("MV Boli", Font.BOLD, 18));
        while (counter <= 100) {
            pb.setValue(counter);
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
            counter += 10;
        }
        pb.setString("Danh mục dịch vụ");
        pb.setFont(sgUI25b);
        ckbAnUong.setFocusPainted(false);
        ckbAnUong.setFont(sgUI13b);
        ckbSacDep.setFocusPainted(false);
        ckbSacDep.setFont(sgUI13b);
        ckbGiaiTri.setFocusPainted(false);
        ckbGiaiTri.setFont(sgUI13b);
        ckbTienIch.setFocusPainted(false);
        ckbTienIch.setFont(sgUI13b);
        ckbTiec.setFocusPainted(false);
        ckbTiec.setFont(sgUI13b);

        listCkb.add(ckbAnUong);
        listCkb.add(ckbGiaiTri);
        listCkb.add(ckbSacDep);
        listCkb.add(ckbTiec);
        listCkb.add(ckbTienIch);

        pnSelectDichVu.add(pnContent, BorderLayout.CENTER);
        pnContent.setLayout(new BorderLayout());
        pnContent.add(pnSearch, BorderLayout.NORTH);
        pnContent.add(pnCenter, BorderLayout.CENTER);

        pnSearch.setLayout(new BorderLayout());
        pnSearch.add(pnSearchInput, BorderLayout.EAST);
        pnSearch.setBackground(Color.white);

        pnSearchInput.setLayout(new BorderLayout());
        pnSearchInput.add(pnInput, BorderLayout.CENTER);
//        pnSearchInput.add(btnSearch, BorderLayout.EAST);
        ImageIcon iconFind = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/IconFind.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btnSearch.setIcon(iconFind);
        btnSearch.setBorder(new EmptyBorder(0, 5, 0, 0));
        btnSearch.setBackground(Color.decode("#FCFCFC"));
        btnSearch.setFocusPainted(false);

        pnInput.setLayout(new BorderLayout());
        pnInput.add(txtSearch, BorderLayout.CENTER);
        pnInput.add(btnSearch, BorderLayout.EAST);
        txtSearch.setBackground(Color.decode("#FCFCFC"));
        txtSearch.setBorder(new MatteBorder(1, 0, 0, 0, Color.white));
        pnInput.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 3, 0, Color.decode("#D0D0D0")), new MatteBorder(0, 0, 3, 0, Color.decode("#E0E0E0"))));
        txtSearch.setFont(sgUI13);
        txtSearch.setPreferredSize(new Dimension(200, 35));

        pnCenter.setBackground(Color.white);
        pnCenter.setLayout(new GridLayout(2, 1));
        pnCenter.add(pnTable);
        pnTable.setLayout(new BorderLayout());
        pnTable.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnTable.add(scp, BorderLayout.CENTER);
        pnTable.add(pnSelect, BorderLayout.EAST);
        pnTable.setBackground(Color.white);
        pnSelect.setLayout(new GridLayout(6, 1));
        pnSelect.add(ckbAnUong);
        pnSelect.add(ckbSacDep);
        pnSelect.add(ckbGiaiTri);
        pnSelect.add(ckbTienIch);
        pnSelect.add(ckbTiec);
        pnSelect.add(btnReset);

        pnCenter.add(pnDV);
        pnDV.setLayout(new GridLayout(3, 3));

        pnDV.add(pnMaDV);
        pnMaDV.setBorder(new EmptyBorder(22, 5, 22, 5));
        pnMaDV.setLayout(new BorderLayout());
        pnMaDV.add(lbMaDV, BorderLayout.WEST);
        pnMaDV.add(txtMaDV, BorderLayout.CENTER);

        pnDV.add(pnGiaDV);
        pnGiaDV.setBorder(new EmptyBorder(22, 5, 22, 5));
        pnGiaDV.setLayout(new BorderLayout());
        pnGiaDV.add(lbGiaDV, BorderLayout.WEST);
        pnGiaDV.add(txtGiaDV, BorderLayout.CENTER);

        pnDV.add(pnEmp1);

        pnDV.add(pnTenDV);
        pnTenDV.setBorder(new EmptyBorder(22, 5, 22, 5));
        pnTenDV.setLayout(new BorderLayout());
        pnTenDV.add(lbTenDV, BorderLayout.WEST);
        pnTenDV.add(txtTenDV, BorderLayout.CENTER);

        pnDV.add(pnSLDV);
        pnSLDV.setBorder(new EmptyBorder(22, 5, 22, 5));
        pnSLDV.setLayout(new BorderLayout());
        pnSLDV.add(lbSLDV, BorderLayout.WEST);
        pnSLDV.add(txtSLDV, BorderLayout.CENTER);

        pnDV.add(pnEmp2);

        pnDV.add(pnLoaiDV);
        pnLoaiDV.setBorder(new EmptyBorder(22, 5, 22, 5));
        pnLoaiDV.setLayout(new BorderLayout());
        pnLoaiDV.add(lbLoaiDV, BorderLayout.WEST);
        pnLoaiDV.add(txtLoaiDV, BorderLayout.CENTER);

        pnDV.add(pnNgaySDDV);
        pnNgaySDDV.setBorder(new EmptyBorder(22, 5, 22, 5));
        pnNgaySDDV.setLayout(new BorderLayout());
        pnNgaySDDV.add(lbNgaySDDV, BorderLayout.WEST);
        pnNgaySDDV.add(dateNgaySD, BorderLayout.CENTER);

        pnDV.add(pnBtn);
        pnDV.setBorder(new EmptyBorder(0, 35, 0, 0));
        pnDV.setBackground(Color.white);
        pnBtn.setLayout(new BorderLayout());
        pnBtn.setBorder(new EmptyBorder(22, 50, 22, 50));
        btnXuly.setFocusPainted(false);
        pnBtn.add(btnXuly, BorderLayout.CENTER);

        pnMaDV.setBackground(Color.white);
        pnTenDV.setBackground(Color.white);
        pnLoaiDV.setBackground(Color.white);
        pnGiaDV.setBackground(Color.white);
        pnSLDV.setBackground(Color.white);
        pnNgaySDDV.setBackground(Color.white);
        pnEmp1.setBackground(Color.white);
        pnEmp2.setBackground(Color.white);
        pnBtn.setBackground(Color.white);

        scp.getViewport().setBackground(Color.white);
        scp.setViewportView(tbDV);
        renderTB(listDV);
        btnReset.setFocusPainted(false);

        btnSearch.setMnemonic(KeyEvent.VK_S);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtSearch.getText().trim().length() == 0) {
                    txtSearch.setText("");
                    txtSearch.requestFocus();
                }
            }
        });
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                for (JCheckBox ckb : listCkb) {
                    ckb.setSelected(false);
                }
                ArrayList<DichVuDTO> listTmp = new ArrayList<>();
                for (DichVuDTO x : listDV) {
                    if (x.getTenDV().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                        listTmp.add(x);
                    }
                }
                if (listTmp.size() == 0) {
                    renderTB(listDV);
                } else {
                    renderTB(listTmp);
                }
            }
        });

        for (JCheckBox ckb : listCkb) {
            ckb.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    txtSearch.setText("");
                    ArrayList<DichVuDTO> list = new ArrayList<>();
                    if (ckb.isSelected()) {
                        list = getLoai(ckb);
                        for (JCheckBox ckbox : listCkb) {
                            if (ckbox != ckb) {
                                if (ckbox.isSelected()) {
                                    list = addArray(list, getLoai(ckbox));
                                }
                            }
                        }
                        renderTB(list);
                    } else {
                        for (JCheckBox ckbox : listCkb) {
                            if (ckbox != ckb) {
                                if (ckbox.isSelected()) {
                                    list = addArray(list, getLoai(ckbox));
                                }
                            }
                        }
                        renderTB(list);
                    }
                }
            });
        }
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JCheckBox ckb : listCkb) {
                    ckb.setSelected(false);
                }
                renderTB(listDV);
            }
        });
    }

    public ArrayList<DichVuDTO> addArray(ArrayList<DichVuDTO> listA, ArrayList<DichVuDTO> listB) {
        for (DichVuDTO x : listB) {
            listA.add(x);
        }
        return listA;
    }

    public ArrayList<DichVuDTO> getLoai(JCheckBox ckb) {
        ArrayList<DichVuDTO> list = new ArrayList<>();
        for (DichVuDTO x : listDV) {
            if (x.getTenLoaiDV().equals(ckb.getText())) {
                list.add(x);
            }
        }
        return list;
    }

    public void renderTB(ArrayList<DichVuDTO> listDV) {
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

        int i = 1;
        for (DichVuDTO x : listDV) {
            Object data[] = {i, x.getMaDV(), x.getTenDV(), x.getTenLoaiDV(), x.getGiaDV()};
            dtm.addRow(data);
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
        tbDV.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    txtMaDV.setText(tbDV.getValueAt(tbDV.getSelectedRow(), tbDV.getColumnModel().getColumnIndex("Mã dịch vụ")).toString());
                    txtTenDV.setText(tbDV.getValueAt(tbDV.getSelectedRow(), tbDV.getColumnModel().getColumnIndex("Tên dịch vụ")).toString());
                    txtLoaiDV.setText(tbDV.getValueAt(tbDV.getSelectedRow(), tbDV.getColumnModel().getColumnIndex("Loại dịch vụ")).toString());
                    txtGiaDV.setText(tbDV.getValueAt(tbDV.getSelectedRow(), tbDV.getColumnModel().getColumnIndex("Giá")).toString());
                } catch (Exception ex) {
                    txtMaDV.setText("");
                    txtTenDV.setText("");
                    txtLoaiDV.setText("");
                    txtGiaDV.setText("");
                }
            }
        });
    }

//    public static void main(String[] args) {
//        new PanelSelectDichVu();
//    }
}
