package GUI;

import BUS.TienIchBUS;
import BUS.BackUpData;
//import DAO.TienIchDAO;
import DTO.TienIchDTO;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PanelTienIch extends JPanel {

    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);
    private JFrame fr = new JFrame();
    private JPanel pnContentTI = new JPanel();
    private JPanel pnContentTITop = new JPanel();
    private JLabel lbTitleTI = new JLabel("Danh sách tiện ích phòng");
    private JPanel pnContentTICenter = new JPanel();
    private JPanel pnContentTITable = new JPanel();
    private JPanel pnContentTIFunction = new JPanel();
    private JScrollPane scpTI = new JScrollPane();
    private JTable tbTI = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JPanel pnFunctionCenter = new JPanel();
    private JPanel pnFunctionTop = new JPanel();
    private JPanel pnFunctionTopTitle = new JPanel();
    private JLabel lbFunctionTopTitle = new JLabel("Tìm kiếm");
    private JPanel pnInputSearch = new JPanel();
    private JPanel pnFunctionBottom = new JPanel();
    private JPanel pnInputSearchCenter = new JPanel();
    private JPanel pnInputSearchEmpty1 = new JPanel();
    private JPanel pnInputSearchEmpty2 = new JPanel();
    private JPanel pnMaTI = new JPanel();
    private JLabel lbMaTI = new JLabel("Mã tiện ích");
    private JPanel pnTxtMaTI = new JPanel();
    private JTextField txtMaTI = new JTextField();
    private JPanel pnTenTI = new JPanel();
    private JLabel lbTenTI = new JLabel("Tên tiện ích");
    private JPanel pnTxtTenTI = new JPanel();
    private JTextField txtTenTI = new JTextField();
    private JPanel pnBtnSearch = new JPanel();
    private JPanel pnBtnSearchEmpty = new JPanel();
    private JPanel pnBtnSearchRight = new JPanel();
    private JButton btnSearchTI = new JButton("Tìm kiếm");
    private JPanel pnFunctionBottomTopTitle = new JPanel();
    private JLabel lbFunctionBottomTopTille = new JLabel("Chức năng");
    private JPanel pnFunctionBottomCenter = new JPanel();
    private JPanel pnFunctionBottomEmpty1 = new JPanel();
    private JPanel pnFunctionBottomEmpty2 = new JPanel();
    private JPanel pnFunctionBottomBottom = new JPanel();
    private JPanel pnMaTIBottom = new JPanel();
    private JLabel lbMaTIBottom = new JLabel("Mã tiện ích:");
    private JPanel pnTxtMaTIBottom = new JPanel();
    private JTextField txtMaTIBottom = new JTextField();
    private JPanel pnTenTIBottom = new JPanel();
    private JLabel lbTenTIBottom = new JLabel("Tên tiện ích:");
    private JPanel pnTxtTenTIBottom = new JPanel();
    private JTextField txtTenTIBottom = new JTextField();
    private JPanel pnFunctionBottomBtn = new JPanel();
    private JPanel pnFunctionBottomEmpty = new JPanel();
    private JPanel pnFunctionBottomRight = new JPanel();
    private JButton btnAddTI = new JButton("Thêm");
    private JButton btnDeleteTI = new JButton("Xoá");
    private JButton btnUpdateTI = new JButton("Sửa");
    JButton btnXuatFile = new JButton("Xuất File");

    public PanelTienIch() {
        initComponents();
    }

    public void initComponents() {
        setLayout(new BorderLayout());
        add(pnContentTI, BorderLayout.CENTER);
//-----pnContentTI
        pnContentTI.setLayout(new BorderLayout());
        pnContentTI.add(pnContentTITop, BorderLayout.NORTH);
        pnContentTI.add(pnContentTICenter, BorderLayout.CENTER);
        //-----pnContentTITop
        pnContentTITop.setLayout(new BorderLayout());
        pnContentTITop.add(lbTitleTI, BorderLayout.CENTER);

        //-----lbTitleTI 
        lbTitleTI.setFont(sgUI15);
        lbTitleTI.setBorder(new EmptyBorder(5, 5, 5, 5));
        //-----pnContentTICenter
        pnContentTICenter.setLayout(new GridLayout(1, 2));
        pnContentTICenter.add(pnContentTITable);
        pnContentTICenter.add(pnContentTIFunction);
        //-----pnContentTITable
        pnContentTITable.setLayout(new BorderLayout());
        pnContentTITable.add(scpTI, BorderLayout.CENTER);
        //-----scpTI
        scpTI.setViewportView(tbTI);

        //-----tbTI
        renderTBTI(tbTI);
        //-----pnContentTIFunction

        pnContentTIFunction.setLayout(new BorderLayout());
        pnContentTIFunction.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnContentTIFunction.add(pnFunctionCenter, BorderLayout.CENTER);
        //-----pnFunctionCenter
        pnFunctionCenter.setLayout(new GridLayout(2, 1, 10, 20));
        pnFunctionCenter.add(pnFunctionTop);
        pnFunctionCenter.add(pnFunctionBottom);
        //-----pnFunctionTop
        pnFunctionTop.setLayout(new BorderLayout());
        pnFunctionTop.add(pnFunctionTopTitle, BorderLayout.NORTH);
        pnFunctionTop.add(pnInputSearch, BorderLayout.CENTER);
        //-----pnFunctionTopTitle
        pnFunctionTopTitle.setLayout(new BorderLayout());
        pnFunctionTopTitle.add(lbFunctionTopTitle, BorderLayout.CENTER);

        lbFunctionTopTitle.setFont(sgUI13b);
        lbFunctionTopTitle.setBorder(new EmptyBorder(10, 10, 10, 10));
        //-----pnInputSearch

        pnInputSearch.setLayout(new BorderLayout());
        pnInputSearch.add(pnInputSearchEmpty1, BorderLayout.WEST);
        JLabel lbempty1 = new JLabel("                                 ");
        JLabel lbempty2 = new JLabel("                                 ");
        pnInputSearch.add(pnInputSearchCenter);
        pnInputSearch.add(pnInputSearchEmpty2, BorderLayout.EAST);
        //-----pnInputSearchEmpty1
        pnInputSearchEmpty1.setLayout(new BorderLayout());
        pnInputSearchEmpty1.add(lbempty1, BorderLayout.CENTER);
        //-----pnInputSearchEmpty2
        pnInputSearchEmpty2.setLayout(new BorderLayout());
        pnInputSearchEmpty2.add(lbempty2, BorderLayout.CENTER);
        //-----pnInputSearchCenter
        pnInputSearchCenter.setLayout(new GridLayout(5, 1));
        pnInputSearchCenter.add(pnMaTI);
        pnInputSearchCenter.add(pnTxtMaTI);
        pnInputSearchCenter.add(pnTenTI);
        pnInputSearchCenter.add(pnTxtTenTI);
        pnInputSearchCenter.add(pnBtnSearch);
        //-----pnMaTI
        pnMaTI.setLayout(new BorderLayout());
        pnMaTI.add(lbMaTI, BorderLayout.CENTER);
        //-----pnTxtMaTI
        pnTxtMaTI.setLayout(new BorderLayout());
        pnTxtMaTI.add(txtMaTI, BorderLayout.CENTER);
        txtMaTI.setMargin(new Insets(5, 5, 5, 5));
        //-----pnTenTI
        pnTenTI.setLayout(new BorderLayout());
        pnTenTI.add(lbTenTI, BorderLayout.CENTER);
        //-----pnTxtTenTI
        txtTenTI.setMargin(new Insets(5, 5, 5, 5));
        pnTxtTenTI.setLayout(new BorderLayout());
        pnTxtTenTI.add(txtTenTI, BorderLayout.CENTER);
        //-----pnBtnSearch
        pnBtnSearch.setLayout(new BorderLayout());
        pnBtnSearch.add(pnBtnSearchEmpty, BorderLayout.CENTER);
        pnBtnSearch.add(pnBtnSearchRight, BorderLayout.EAST);
        //-----pnBtnSearchEmpty
        //-----pnBtnSearchRight
        pnBtnSearchRight.setLayout(new GridLayout(1, 2, 5, 5));
        pnBtnSearchRight.add(btnSearchTI);
        pnBtnSearchRight.add(btnXuatFile);

        pnBtnSearchRight.setBorder(new EmptyBorder(5, 0, 5, 0));
        //-----btnSearchTI
        btnSearchTI.setBackground(Color.white);
        btnSearchTI.setRequestFocusEnabled(false);
        btnXuatFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackUpData bk = new BUS.BackUpData();
                try {
                    String path = System.getProperty("user.dir") + "\\src\\GUI\\excel\\BackUpData_TienIch.xlsx";
                    bk.backup_TienIch(path);
                    JOptionPane.showMessageDialog(null, "Xuất file thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(new File(path));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Xuất file không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnXuatFile.setBackground(Color.white);
        btnXuatFile.setRequestFocusEnabled(false);
        //-----pnFunctionBottom
        pnFunctionBottom.setLayout(new BorderLayout());
        pnFunctionBottom.add(pnFunctionBottomTopTitle, BorderLayout.NORTH);
        pnFunctionBottom.add(pnFunctionBottomCenter, BorderLayout.CENTER);
        //-----pnFunctionBottomTopTitle
        pnFunctionBottomTopTitle.setLayout(new BorderLayout());
        pnFunctionBottomTopTitle.add(lbFunctionBottomTopTille, BorderLayout.CENTER);

        lbFunctionBottomTopTille.setFont(sgUI13b);
        lbFunctionBottomTopTille.setBorder(new EmptyBorder(10, 10, 10, 10));
        //
        JLabel lbempty11 = new JLabel("                                 ");
        JLabel lbempty22 = new JLabel("                                 ");
        //-----pnFuctionBottomCenter
        pnFunctionBottomCenter.setLayout(new BorderLayout());
        pnFunctionBottomCenter.add(pnFunctionBottomEmpty1, BorderLayout.WEST);
        pnFunctionBottomCenter.add(pnFunctionBottomBottom, BorderLayout.CENTER);
        pnFunctionBottomCenter.add(pnFunctionBottomEmpty2, BorderLayout.EAST);
        //-----pnFunctionBotomEmpty1
        pnFunctionBottomEmpty1.setLayout(new BorderLayout());
        pnFunctionBottomEmpty1.add(lbempty11, BorderLayout.CENTER);
        //-----pnFunctionBotomEmpty2
        pnFunctionBottomEmpty2.setLayout(new BorderLayout());
        pnFunctionBottomEmpty2.add(lbempty22, BorderLayout.CENTER);
        //-----pnFunctionBottomBottom
        pnFunctionBottomBottom.setLayout(new GridLayout(5, 1));
        pnFunctionBottomBottom.add(pnMaTIBottom);
        pnFunctionBottomBottom.add(pnTxtMaTIBottom);
        pnFunctionBottomBottom.add(pnTenTIBottom);
        pnFunctionBottomBottom.add(pnTxtTenTIBottom);
        pnFunctionBottomBottom.add(pnFunctionBottomBtn);
        //-----pnMaTIBottom
        pnMaTIBottom.setLayout(new BorderLayout());
        pnMaTIBottom.add(lbMaTIBottom, BorderLayout.CENTER);
        //-----pnMaTIBottom
        pnTxtMaTIBottom.setLayout(new BorderLayout());
        pnTxtMaTIBottom.add(txtMaTIBottom, BorderLayout.CENTER);
        txtMaTIBottom.setMargin(new Insets(5, 5, 5, 5));
        //-----pnTenTIBottom
        pnTenTIBottom.setLayout(new BorderLayout());
        pnTenTIBottom.add(lbTenTIBottom, BorderLayout.CENTER);
        //-----pnMaTIBottom
        pnTxtTenTIBottom.setLayout(new BorderLayout());
        pnTxtTenTIBottom.add(txtTenTIBottom, BorderLayout.CENTER);
        txtTenTIBottom.setMargin(new Insets(5, 5, 5, 5));
        //-----pnFunctionBottomBtn
        pnFunctionBottomBtn.setLayout(new BorderLayout());
        pnFunctionBottomBtn.add(pnFunctionBottomEmpty, BorderLayout.CENTER);
        pnFunctionBottomBtn.add(pnFunctionBottomRight, BorderLayout.EAST);
        //-----pnFunctionBottomRight

        pnFunctionBottomRight.setLayout(new GridLayout(1, 3, 5, 5));
        pnFunctionBottomRight.add(btnAddTI);
        pnFunctionBottomRight.add(btnDeleteTI);
        pnFunctionBottomRight.add(btnUpdateTI);
        pnFunctionBottomRight.setBorder(new EmptyBorder(5, 0, 5, 0));
        txtMaTIBottom.setEditable(false);
        btnSearchTI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMaTI.getText().trim().length() == 0 && txtTenTI.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin cần tìm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    renderTBTI(tbTI);
                } else {
                    ArrayList<TienIchDTO> listTienIch = TienIchBUS.getListTienIch();
                    ArrayList<TienIchDTO> listTemp = new ArrayList<>();
                    if (txtMaTI.getText().trim().length() != 0) {
                        for (TienIchDTO x : listTienIch) {
                            if (x.getMaTienIch().toLowerCase().contains(txtMaTI.getText().toLowerCase())) {
                                listTemp.add(x);
                            }
                        }
                        listTienIch.clear();
                        for (TienIchDTO x : listTemp) {
                            listTienIch.add(x);
                        }
                        listTemp.clear();
                    }
                    if (txtTenTI.getText().trim().length() != 0) {
                        for (TienIchDTO x : listTienIch) {
                            if (x.getTenTienIch().toLowerCase().contains(txtTenTI.getText().toLowerCase())) {
                                listTemp.add(x);
                            }
                        }
                        listTienIch.clear();
                        for (TienIchDTO x : listTemp) {
                            listTienIch.add(x);
                        }
                        listTemp.clear();
                    }
                    renderTBTI(tbTI, listTienIch);
                }
            }
        });
        btnDeleteTI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMaTIBottom.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn tiện ích muốn xóa", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int ans = JOptionPane.showConfirmDialog(null, "Bạn có chắc xóa tiện ích này", "Thông báo", JOptionPane.YES_NO_OPTION);
                        if (ans == JOptionPane.OK_OPTION) {
                            if (TienIchBUS.deleteTienIch(txtMaTIBottom.getText())) {
                                JOptionPane.showMessageDialog(null, "Xóa thành công tiện ích này", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                                renderTBTI(tbTI);
                            } else {
                                JOptionPane.showMessageDialog(null, "Xóa tiện ích này không thành công", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Xóa tiện ích này không thành công", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        btnUpdateTI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String error = "";
                if (txtMaTIBottom.getText().equals("")) {
                    error += "Vui lòng chọn tiện ích muốn sửa";
                }
                if (error.trim().length() == 0) {
                    if (txtTenTIBottom.getText().trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập tên tiện ích", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            TienIchDTO tienIchDTO = new TienIchDTO(txtMaTIBottom.getText(), txtTenTIBottom.getText(), 0);
                            if (TienIchBUS.updateTienIch(tienIchDTO)) {
                                JOptionPane.showMessageDialog(null, "Sửa tiện ích này thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                                renderTBTI(tbTI);
                            } else {
                                JOptionPane.showMessageDialog(null, "Không thể sửa tiện ích này", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Không thể sửa tiện ích này", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, error, "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnAddTI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr.setVisible(true);
                fr.setSize(300, 300);
                fr.setLocationRelativeTo(null);
                fr.setLayout(new BorderLayout());
                fr.setResizable(false);
                JPanel pnCenter = new JPanel();
                fr.add(pnCenter, BorderLayout.CENTER);
                pnCenter.setBackground(Color.decode("#FFFFFF"));
                pnCenter.setLayout(new GridLayout(5, 1, 10, 10));
                pnCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
                JLabel lbmati = new JLabel("Mã tiện ích");
                pnCenter.add(lbmati);
                lbmati.setFont(sgUI15);
                JTextField txtmati = new JTextField();
                pnCenter.add(txtmati);
                txtmati.setFont(sgUI15p);
                txtmati.setMargin(new Insets(0, 10, 0, 10));
                JLabel lbtenti = new JLabel("Tên tiện ích");
                lbtenti.setFont(sgUI15);
                pnCenter.add(lbtenti);
                JTextField txttenti = new JTextField();
                txttenti.setMargin(new Insets(0, 10, 0, 10));
                txttenti.setFont(sgUI15p);
                pnCenter.add(txttenti);
                JPanel pnAdd = new JPanel();
                JButton btnAdd = new JButton("Thêm");
                pnAdd.setBorder(new EmptyBorder(0, 80, 0, 80));
                pnAdd.setLayout(new BorderLayout());
                pnAdd.add(btnAdd, BorderLayout.CENTER);
                pnAdd.setBackground(Color.white);
                pnCenter.add(pnAdd);
                txtmati.setEditable(false);
                int count = TienIchBUS.getSize() + 100;
                txtmati.setText("TienIch" + count);
                fr.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        fr.setVisible(false);
                    }
                });
                btnAdd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (txttenti.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên tiện ích", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                        } else {
                            TienIchDTO tienIchDTO = new TienIchDTO(txtmati.getText(), txttenti.getText(), 0);
                            try {
                                if (TienIchBUS.insertTienIch(tienIchDTO)) {
                                    JOptionPane.showMessageDialog(null, "Thêm tiện ích thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                                    fr.setVisible(false);
                                    int count = TienIchBUS.getSize() + 100;
                                    txtmati.setText("TienIch" + count);
                                    txttenti.setText("");
                                    renderTBTI(tbTI);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Thêm tiện ích thất bại", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Thêm tiện ích thất bại", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                });
            }
        });
    }

    public void renderTBTI(JTable tbTI) {
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
        tbTI.setRowHeight(40);
        tbTI.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbTI.getTableHeader().setPreferredSize(new Dimension(1, 40));
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
        tbTI.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    txtMaTIBottom.setText(tbTI.getValueAt(tbTI.getSelectedRow(), tbTI.getColumnModel().getColumnIndex("Mã tiện ích")).toString());
                    txtTenTIBottom.setText(tbTI.getValueAt(tbTI.getSelectedRow(), tbTI.getColumnModel().getColumnIndex("Tên tiện ích")).toString());
                } catch (Exception ex) {
                    txtMaTIBottom.setText("");
                    txtTenTIBottom.setText("");
                }
            }
        });
    }

    public void renderTBTI(JTable tbTI, ArrayList<TienIchDTO> listTienIch) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Mã tiện ích");
        dtm.addColumn("Tên tiện ích");
        int i = 1;
        for (TienIchDTO x : listTienIch) {
            Object data[] = {i, x.getMaTienIch(), x.getTenTienIch()};
            dtm.addRow(data);
            i++;
        }
        tbTI.setModel(dtm);
        tbTI.setModel(dtm);
        tbTI.setShowGrid(false);
        tbTI.setIntercellSpacing(new Dimension(0, 0));
        tbTI.setRowHeight(40);
        tbTI.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbTI.getTableHeader().setPreferredSize(new Dimension(1, 40));
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
        tbTI.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    txtMaTIBottom.setText(tbTI.getValueAt(tbTI.getSelectedRow(), 1).toString());
                    txtTenTIBottom.setText(tbTI.getValueAt(tbTI.getSelectedRow(), 2).toString());
                } catch (Exception ex) {
                    txtMaTIBottom.setText("");
                    txtTenTIBottom.setText("");
                }
            }
        });
    }

    public void lightDark(int lightDark) {
        if (lightDark == 1) {
            pnContentTITop.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode("#555555")));
            pnContentTITop.setBackground(Color.decode("#555555"));
            lbTitleTI.setForeground(Color.white);
            scpTI.setBorder(new MatteBorder(10, 10, 10, 10, Color.decode("#CCCCCC")));
            scpTI.getViewport().setBackground(Color.decode("#F5F5F5"));
            pnContentTIFunction.setBackground(Color.decode("#CCCCCC"));
            pnFunctionBottomEmpty.setBackground(Color.decode("#F5F5F5"));
            pnTenTIBottom.setBackground(Color.decode("#F5F5F5"));
            pnFunctionBottomEmpty2.setBackground(Color.decode("#F5F5F5"));
            pnFunctionBottomEmpty1.setBackground(Color.decode("#F5F5F5"));
            pnMaTIBottom.setBackground(Color.decode("#F5F5F5"));
            pnFunctionBottomBtn.setBackground(Color.decode("#F5F5F5"));
            pnFunctionBottomRight.setBackground(Color.decode("#F5F5F5"));
            pnInputSearchEmpty2.setBackground(Color.decode("#F5F5F5"));
            pnInputSearchEmpty1.setBackground(Color.decode("#F5F5F5"));
            pnMaTI.setBackground(Color.decode("#F5F5F5"));
            pnTenTI.setBackground(Color.decode("#F5F5F5"));
            pnBtnSearchEmpty.setBackground(Color.decode("#F5F5F5"));
            pnBtnSearchRight.setBackground(Color.decode("#F5F5F5"));
            tbTI.getTableHeader().setBackground(Color.decode("#898989"));
            pnFunctionTopTitle.setBackground(Color.decode("#898989"));
            pnFunctionBottomTopTitle.setBackground(Color.decode("#898989"));
            pnFunctionCenter.setBackground(Color.decode("#CCCCCC"));
        } else {
            pnContentTITop.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode("#BBBBBB")));
            pnContentTITop.setBackground(Color.decode("#BBBBBB"));
            lbTitleTI.setForeground(Color.black);
            scpTI.setBorder(new MatteBorder(10, 10, 10, 10, Color.decode("#EEEEEE")));
            scpTI.getViewport().setBackground(Color.decode("#FFFFFF"));
            pnContentTIFunction.setBackground(Color.decode("#EEEEEE"));
            pnFunctionBottomEmpty.setBackground(Color.decode("#FFFFFF"));
            pnTenTIBottom.setBackground(Color.decode("#FFFFFF"));
            pnFunctionBottomEmpty2.setBackground(Color.decode("#FFFFFF"));
            pnFunctionBottomEmpty1.setBackground(Color.decode("#FFFFFF"));
            pnMaTIBottom.setBackground(Color.decode("#FFFFFF"));
            pnFunctionBottomBtn.setBackground(Color.decode("#FFFFFF"));
            pnFunctionBottomRight.setBackground(Color.decode("#FFFFFF"));
            pnInputSearchEmpty2.setBackground(Color.decode("#FFFFFF"));
            pnInputSearchEmpty1.setBackground(Color.decode("#FFFFFF"));
            pnMaTI.setBackground(Color.decode("#FFFFFF"));
            pnTenTI.setBackground(Color.decode("#FFFFFF"));
            pnBtnSearchEmpty.setBackground(Color.decode("#FFFFFF"));
            pnBtnSearchRight.setBackground(Color.decode("#FFFFFF"));
            tbTI.getTableHeader().setBackground(Color.decode("#6699CC"));
            pnFunctionTopTitle.setBackground(Color.decode("#6699CC"));
            pnFunctionBottomTopTitle.setBackground(Color.decode("#6699CC"));
            pnFunctionCenter.setBackground(Color.decode("#EEEEEE"));
        }
    }
}
