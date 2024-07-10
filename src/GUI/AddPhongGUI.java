package GUI;

import BUS.ChiTietTienIchBUS;
import BUS.PhongBUS;
import BUS.TienIchBUS;
import DTO.ChiTietTienIchDTO;
import DTO.PhongDTO;
import DTO.TienIchDTO;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.*; 

public class AddPhongGUI extends JFrame {

    private JLabel lbTitle = new JLabel("Thêm phòng");
    private JPanel pnCenter = new JPanel();
    private JPanel pnInput = new JPanel();
    private JPanel pnTienIch = new JPanel();

    private JPanel pnMaP = new JPanel();
    private JLabel lbMaP = new JLabel("     Mã phòng");
    private JPanel pnTxtMaP = new JPanel();
    private JTextField txtMaP = new JTextField();

    private JPanel pnTenP = new JPanel();
    private JLabel lbTenP = new JLabel("     Tên phòng");
    private JPanel pnTxtTenP = new JPanel();
    private JTextField txtTenP = new JTextField();

    private JPanel pnLoaiP = new JPanel();
    private JLabel lbLoaiP = new JLabel("     Loại phòng");
    private JPanel pnTxtLoaiP = new JPanel();
    private JRadioButton rdVIP = new JRadioButton("VIP");
    private JRadioButton rdThuong = new JRadioButton("Thường");
    private ButtonGroup bg = new ButtonGroup();

    private JPanel pnGiaP = new JPanel();
    private JLabel lbGiaP = new JLabel("     Giá phòng");
    private JPanel pnTxtGiaP = new JPanel();
    private JTextField txtGiaP = new JTextField();

    private JPanel pnTTP = new JPanel();
    private JLabel lbTTP = new JLabel("     Tình trạng phòng");
    private JPanel pnTxtTTP = new JPanel();
    private JTextField txtTTP = new JTextField("Trống");

    private JPanel pnHTP = new JPanel();
    private JLabel lbHTP = new JLabel("     Hiện trạng phòng");
    private JPanel pnTxtHTP = new JPanel();
    private JTextField txtHTP = new JTextField("Mới");

    private JPanel pnBtn = new JPanel();
    private JButton btnAdd = new JButton("Thêm");

    private JLabel lbError = new JLabel("Chưa thêm tiện ích phòng");

    private JPanel pnTitle = new JPanel();
    private JLabel lbSTT = new JLabel("STT");
    private JLabel lbTen = new JLabel("Tên tiện ích");
    private JLabel lbSL = new JLabel("Số lượng");

    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);
    private JPanel pnTable = new JPanel();
    private JLabel lbTable = new JLabel("Danh sách tiện ích");
    private JScrollPane scp = new JScrollPane();
    private JTable tbTI = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private JPanel pnTienIchPhong = new JPanel();
    private JLabel lbTienIch = new JLabel("Danh sách tiện ích được thêm");
    private JScrollPane scp1 = new JScrollPane();
    private JPanel pnScrol = new JPanel();
    private JPanel pnScrolChange = new JPanel();
    private JTextField txtMaTIchange = new JTextField("Mã tiện ích");
    private JTextField txtTenTIchange = new JTextField("Tên tiện ích");
    private JTextField txtSLTIchange = new JTextField("Số lượng");
    private JButton btnUpdateChange = new JButton("Sửa");
    private JButton btnDeleteChange = new JButton("Xóa");

    private ArrayList<TienIchDTO> listTemp = new ArrayList<>();
    private ArrayList<Integer> listInt = new ArrayList<>();
    private JTable tbAdded = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public AddPhongGUI() {
        initComponents();
    }

    public void initComponents() {
        setSize(950, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(51, 51, 51));
        setLayout(new BorderLayout());
        add(lbTitle, BorderLayout.NORTH);
        add(pnCenter, BorderLayout.CENTER);
        //-----lbTitle
        lbTitle.setForeground(Color.white);
        lbTitle.setFont(sgUI18b);
        lbTitle.setBorder(new EmptyBorder(5, 0, 5, 0));
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        //------pnCenter
        pnCenter.setLayout(new GridLayout(1, 2));
        pnCenter.add(pnInput);
        pnCenter.add(pnTienIch);
        //------pnInput
        pnInput.setLayout(new GridLayout(8, 1));
        pnInput.setBackground(Color.decode("#CCCCCC"));

        //------pnMaP
        pnMaP.setLayout(new BorderLayout());
        pnMaP.add(lbMaP, BorderLayout.NORTH);
        pnMaP.add(pnTxtMaP, BorderLayout.CENTER);
        pnMaP.setBackground(Color.decode("#CCCCCC"));
        //------pnTxtMaP
        txtMaP.setEditable(false);
        lbMaP.setFont(sgUI15);
        pnTxtMaP.setLayout(new BorderLayout());
        pnTxtMaP.setBackground(Color.decode("#CCCCCC"));
        pnTxtMaP.add(txtMaP, BorderLayout.CENTER);
        pnTxtMaP.setBorder(new EmptyBorder(0, 20, 0, 20));
        txtMaP.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#CCCCCC")), new EmptyBorder(0, 10, 0, 10)));
        pnInput.add(pnMaP);
        txtMaP.setFont(sgUI15p);

        //------pnTenP
        pnTenP.setLayout(new BorderLayout());
        pnTenP.add(lbTenP, BorderLayout.NORTH);
        pnTenP.add(pnTxtTenP, BorderLayout.CENTER);
        pnTenP.setBackground(Color.decode("#CCCCCC"));
        //------pnTxtTenP
        lbTenP.setFont(sgUI15);
        pnTxtTenP.setLayout(new BorderLayout());
        pnTxtTenP.setBackground(Color.decode("#CCCCCC"));
        pnTxtTenP.add(txtTenP, BorderLayout.CENTER);
        pnTxtTenP.setBorder(new EmptyBorder(0, 20, 0, 20));
        txtTenP.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#CCCCCC")), new EmptyBorder(0, 10, 0, 10)));
        pnInput.add(pnTenP);
        txtTenP.setFont(sgUI15p);

        //------pnLoaiP
        pnLoaiP.setLayout(new BorderLayout());
        pnLoaiP.add(lbLoaiP, BorderLayout.NORTH);
        pnLoaiP.add(pnTxtLoaiP, BorderLayout.CENTER);
        pnLoaiP.setBackground(Color.decode("#CCCCCC"));
        //------pnTxtLoaiP
        lbLoaiP.setFont(sgUI15);
        pnTxtLoaiP.setLayout(new GridLayout(1, 2));
        pnTxtLoaiP.setBackground(Color.decode("#CCCCCC"));
        pnTxtLoaiP.add(rdVIP);
        rdVIP.setFont(sgUI15p);
        rdVIP.setBackground(Color.decode("#CCCCCC"));
        rdThuong.setFont(sgUI15p);
        rdThuong.setBackground(Color.decode("#CCCCCC"));
        rdThuong.setFocusPainted(false);
        rdVIP.setFocusPainted(false);
        pnTxtLoaiP.add(rdThuong);
        rdVIP.setSelected(true);
        pnTxtLoaiP.setBorder(new EmptyBorder(0, 20, 0, 20));
        bg.add(rdVIP);
        bg.add(rdThuong);
        pnInput.add(pnLoaiP);

        //------pnGiaP
        pnGiaP.setLayout(new BorderLayout());
        pnGiaP.add(lbGiaP, BorderLayout.NORTH);
        pnGiaP.add(pnTxtGiaP, BorderLayout.CENTER);
        pnGiaP.setBackground(Color.decode("#CCCCCC"));
        //------pnTxtGiaP
        lbGiaP.setFont(sgUI15);
        pnTxtGiaP.setLayout(new BorderLayout());
        pnTxtGiaP.setBackground(Color.decode("#CCCCCC"));
        pnTxtGiaP.add(txtGiaP, BorderLayout.CENTER);
        pnTxtGiaP.setBorder(new EmptyBorder(0, 20, 0, 20));
        txtGiaP.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#CCCCCC")), new EmptyBorder(0, 10, 0, 10)));
        pnInput.add(pnGiaP);
        txtGiaP.setFont(sgUI15p);

        //------pnTTP
        pnTTP.setLayout(new BorderLayout());
        pnTTP.add(lbTTP, BorderLayout.NORTH);
        pnTTP.add(pnTxtTTP, BorderLayout.CENTER);
        pnTTP.setBackground(Color.decode("#CCCCCC"));
        //------pnTxtTTP
        lbTTP.setFont(sgUI15);
        pnTxtTTP.setLayout(new BorderLayout());
        pnTxtTTP.setBackground(Color.decode("#CCCCCC"));
        pnTxtTTP.add(txtTTP, BorderLayout.CENTER);
        pnTxtTTP.setBorder(new EmptyBorder(0, 20, 0, 20));
        txtTTP.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#CCCCCC")), new EmptyBorder(0, 10, 0, 10)));
        pnInput.add(pnTTP);
        txtTTP.setFont(sgUI15p);

        //------pnHTP
        pnHTP.setLayout(new BorderLayout());
        pnHTP.add(lbHTP, BorderLayout.NORTH);
        pnHTP.add(pnTxtHTP, BorderLayout.CENTER);
        pnHTP.setBackground(Color.decode("#CCCCCC"));
        //------pnTxtHTP
        lbHTP.setFont(sgUI15);
        pnTxtHTP.setLayout(new BorderLayout());
        pnTxtHTP.setBackground(Color.decode("#CCCCCC"));
        pnTxtHTP.add(txtHTP, BorderLayout.CENTER);
        pnTxtHTP.setBorder(new EmptyBorder(0, 20, 0, 20));
        txtHTP.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#CCCCCC")), new EmptyBorder(0, 10, 0, 10)));
        pnInput.add(pnHTP);
        txtHTP.setFont(sgUI15p);

        pnInput.add(lbError);
        lbError.setFont(sgUI15);
        lbError.setForeground(Color.red);
        lbError.setHorizontalAlignment(JLabel.CENTER);
        txtMaP.setText(maP());

        rdVIP.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                txtMaP.setText(maP());
            }
        });

        rdThuong.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                txtMaP.setText(maP());
            }
        });

        pnInput.add(pnBtn);
        pnBtn.setLayout(new BorderLayout());
        pnBtn.add(btnAdd, BorderLayout.CENTER);
        pnBtn.setBorder(new EmptyBorder(0, 100, 20, 100));
        pnBtn.setBackground(Color.decode("#CCCCCC"));
        btnAdd.setFocusPainted(false);
        ImageIcon iconAdd = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/them.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btnAdd.setIcon(iconAdd);
        btnAdd.setBackground(Color.decode("#90EE90"));

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String error = "";
                if (txtTenP.getText().trim().length() == 0) {
                    error += "Vui lòng nhập tên phòng\n";
                }
                if (txtGiaP.getText().trim().length() == 0) {
                    error += "Vui lòng nhập giá phòng\n";
                }
                if (lbError.getText().equals("Chưa thêm tiện ích phòng")) {
                    error += "Vui lòng chọn tiện ích phòng";
                }
                if (!error.equals("")) {
                    JOptionPane.showMessageDialog(null, error, "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int gia = Integer.parseInt(txtGiaP.getText());
                        if (gia > 0) {
                            PhongDTO pDTO = new PhongDTO();
                            pDTO.setMaP(txtMaP.getText());
                            pDTO.setTenP(txtTenP.getText());
                            pDTO.setGiaP(gia);
                            String loaiP = "";
                            if(rdVIP.isSelected()) {
                                loaiP = rdVIP.getText();
                            }
                            else if(rdThuong.isSelected()) {
                                loaiP = rdThuong.getText();
                            }
                            pDTO.setLoaiP(loaiP);
                            pDTO.setTinhTrang(txtTTP.getText());
                            pDTO.setHienTrang(txtHTP.getText());
                            pDTO.setXuLy(0);
                            String wrong = "";
                            if(!PhongBUS.insertPhong(pDTO)) {
                                wrong += "Không thể thêm phòng mới này";
                            }
                            int i = 0;
                            int check = 0;
                            for(TienIchDTO x:listTemp) {
                                ChiTietTienIchDTO ctti = new ChiTietTienIchDTO();
                                ctti.setMaP(txtMaP.getText());
                                ctti.setMaTienIch(x.getMaTienIch());
                                ctti.setSoLuong(listInt.get(i));
                                i++;
                                if(!ChiTietTienIchBUS.insertChiTietTienIch(ctti)){
                                    check++;
                                }
                            }
                            if(check == 0 && wrong.length() == 0) {
                                int ans = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát không","Thành công",JOptionPane.YES_NO_OPTION);
                                if(ans == JOptionPane.YES_OPTION) {
                                    setVisible(false);
                                }
                                reset();
                                PanelPhong.renderTBPhong(PanelPhong.tbPhong);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, wrong, "Báo lỗi",JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Giá là một số không âm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            txtGiaP.setText("");
                            txtGiaP.requestFocus();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Giá là một số nguyên", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        txtGiaP.setText("");
                        txtGiaP.requestFocus();
                    }
                }
            }
        });

        txtTTP.setEditable(false);
        txtHTP.setEditable(false);

        //-----pnTienIch
        pnTienIch.setLayout(new GridLayout(2, 1));
        pnTienIch.add(pnTable);
        //-----pnTable
        pnTable.setLayout(new BorderLayout());
        pnTable.add(lbTable, BorderLayout.NORTH);
        lbTable.setFont(sgUI13b);
        lbTable.setBorder(new EmptyBorder(5, 0, 5, 0));
        lbTable.setBackground(Color.decode("#CCCCCC"));
        lbTable.setOpaque(true);
        pnTable.add(scp);
        scp.setBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#CCCCCC")));
        scp.setViewportView(tbTI);
        renderTBTI();

        //-----pnTienIch
        pnTienIch.setBorder(new EmptyBorder(0, 0, 20, 20));
        pnTienIch.setBackground(Color.decode("#CCCCCC"));
        pnTienIch.add(pnTienIchPhong);
        //-----pnTienIchPhong
        pnTienIchPhong.setLayout(new BorderLayout());
        pnTienIchPhong.add(lbTienIch, BorderLayout.NORTH);
        lbTienIch.setFont(sgUI13b);
        lbTienIch.setBorder(new EmptyBorder(5, 0, 5, 0));
        lbTienIch.setBackground(Color.decode("#CCCCCC"));
        lbTienIch.setOpaque(true);
        pnTienIchPhong.add(pnScrol, BorderLayout.CENTER);
        pnScrol.setLayout(new BorderLayout());
        pnScrol.add(scp1, BorderLayout.CENTER);
        pnScrol.add(pnScrolChange, BorderLayout.NORTH);
        UIManager.put("TextField.inactiveBackground", new ColorUIResource(new Color(255, 255, 255)));

        pnScrolChange.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 1, 0, 1, Color.decode("#CCCCCC")), new EmptyBorder(5, 5, 5, 5)));
                
        pnScrolChange.setLayout(new GridLayout(1, 5, 5, 5));
        pnScrolChange.add(txtMaTIchange);
        pnScrolChange.add(txtTenTIchange);
        pnScrolChange.add(txtSLTIchange);
        pnScrolChange.add(btnUpdateChange);
        pnScrolChange.add(btnDeleteChange);

        txtMaTIchange.setBorder(new EmptyBorder(0, 5, 0, 5));
        txtTenTIchange.setBorder(new EmptyBorder(0, 5, 0, 5));
        txtSLTIchange.setBorder(new EmptyBorder(0, 5, 0, 5));

        btnUpdateChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMaTIchange.getText().equals("Mã tiện ích")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 tiện ích đã được thêm vào phòng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (txtSLTIchange.getText().equals("Số lượng")) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng muốn thay đổi", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        txtSLTIchange.requestFocus();
                    } else {
                        try {
                            int sl = Integer.parseInt(txtSLTIchange.getText());
                            if (sl > 0) {
                                listInt.set(tbAdded.getSelectedRow(), sl);
                                txtMaTIchange.setText("Mã tiện ích");
                                txtTenTIchange.setText("Tên tiện ích");
                                txtSLTIchange.setText("Số lượng");
                                renderTBTIadded();
                            } else {
                                JOptionPane.showMessageDialog(null, "Vui lòng nhập một số dương", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                txtSLTIchange.setText("");
                                txtSLTIchange.requestFocus();
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập số", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            txtSLTIchange.setText("");
                            txtSLTIchange.requestFocus();
                        }
                    }
                }
            }
        });
        btnDeleteChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMaTIchange.getText().equals("Mã tiện ích")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 tiện ích đã được thêm vào phòng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    listTemp.remove(tbAdded.getSelectedRow());
                    listInt.remove(tbAdded.getSelectedRow());
                    txtMaTIchange.setText("Mã tiện ích");
                    txtTenTIchange.setText("Tên tiện ích");
                    txtSLTIchange.setText("Số lượng");
                    if (listTemp.size() == 0) {
                        lbError.setText("Chưa thêm tiện ích phòng");
                        lbError.setForeground(Color.red);
                    }
                    renderTBTIadded();
                }
            }
        });

        txtMaTIchange.setEditable(false);
        txtTenTIchange.setEditable(false);
        btnUpdateChange.setBackground(Color.white);
        btnDeleteChange.setBackground(Color.white);
        btnUpdateChange.setFocusPainted(false);
        btnDeleteChange.setFocusPainted(false);
        txtSLTIchange.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtSLTIchange.getText().equals("Số lượng")) {
                    txtSLTIchange.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtSLTIchange.getText().equals("")) {
                    txtSLTIchange.setText("Số lượng");
                }
            }
        });

        scp1.setBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#CCCCCC")));
        scp1.setViewportView(tbAdded);
        listTemp = new ArrayList<>();
        listInt = new ArrayList<>();
        scp1.getVerticalScrollBar().setUnitIncrement(16);
        renderTBTIadded();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                reset();
            }
        });
    }

    public void reset() {
        lbError.setText("Chưa thêm tiện ích phòng");
        lbError.setForeground(Color.red);
        listTemp.clear();
        listInt.clear();
        renderTBTIadded();
        txtTenP.setText("");
        txtGiaP.setText("");
        txtMaP.setText(maP());
    }

    public void renderTBTI() {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Mã tiện ích");
        dtm.addColumn("Tên tiện ích");
        int i = 1;
        for (TienIchDTO x : TienIchBUS.getListTienIch()) {
            Object data[] = {i, x.getMaTienIch(), x.getTenTienIch()};
            dtm.addRow(data);
            i++;
        }
        tbTI.setModel(dtm);
        tbTI.setModel(dtm);
        tbTI.setShowGrid(false);
        tbTI.setIntercellSpacing(new Dimension(0, 0));
        tbTI.setRowHeight(30);
        tbTI.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbTI.getTableHeader().setPreferredSize(new Dimension(1, 30));
        tbTI.getTableHeader().setFont(sgUI13b);
        tbTI.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        for (int j = 0; j < tbTI.getColumnCount(); j++) {
            tbTI.getColumnModel().getColumn(j).setCellRenderer(renderBorder);
        }
        tbTI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == 1) {
                    TienIchDTO x = new TienIchDTO(tbTI.getValueAt(tbTI.getSelectedRow(), tbTI.getColumnModel().getColumnIndex("Mã tiện ích")).toString(), tbTI.getValueAt(tbTI.getSelectedRow(), tbTI.getColumnModel().getColumnIndex("Tên tiện ích")).toString(), 0);
                    tbTI.clearSelection();
                    int check = 0;
                    for (TienIchDTO item : listTemp) {
                        if (item.getMaTienIch().equals(x.getMaTienIch())) {
                            check++;
                            break;
                        }
                    }
                    if (check == 0) {
                        lbError.setText("Đã thêm tiện ích");
                        lbError.setForeground(Color.decode("#339900"));
                        listTemp.add(x);
                        listInt.add(1);
                        renderTBTIadded();
                    }
                }
            }
        });
    }

    public void renderTBTIadded() {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Mã tiện ích");
        dtm.addColumn("Tên tiện ích");
        dtm.addColumn("Số lượng");
        int i = 1;
        for (TienIchDTO x : listTemp) {
            Object data[] = {i, x.getMaTienIch(), x.getTenTienIch(), listInt.get(i - 1)};
            dtm.addRow(data);
            i++;
        }
        tbAdded.setModel(dtm);
        tbAdded.setModel(dtm);
        tbAdded.setShowGrid(false);
        tbAdded.setIntercellSpacing(new Dimension(0, 0));
        tbAdded.setRowHeight(30);
        tbAdded.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbAdded.getTableHeader().setPreferredSize(new Dimension(1, 30));
        tbAdded.getTableHeader().setFont(sgUI13b);
        tbAdded.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        for (int j = 0; j < tbAdded.getColumnCount(); j++) {
            tbAdded.getColumnModel().getColumn(j).setCellRenderer(renderBorder);
        }
        tbAdded.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    txtMaTIchange.setText(tbAdded.getValueAt(tbAdded.getSelectedRow(), tbAdded.getColumnModel().getColumnIndex("Mã tiện ích")).toString());
                    txtTenTIchange.setText(tbAdded.getValueAt(tbAdded.getSelectedRow(), tbAdded.getColumnModel().getColumnIndex("Tên tiện ích")).toString());

                } catch (Exception ex) {
                    txtMaTIchange.setText("Mã tiện ích");
                    txtTenTIchange.setText("Tên tiện ích");
                    txtSLTIchange.setText("Số lượng");
                }
            }
        });
    }

    public String maP() {
        String maP = "";
        if (rdVIP.isSelected()) {
            maP += "PV";
        } else {
            maP += "PT";
        }
        int count = 100 + PhongBUS.getSize() + 1;
        return maP + count;
    }
}
