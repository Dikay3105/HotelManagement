package GUI;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import BUS.BackUpData;
import BUS.ImportExcel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 *
 * @author duyli
 */
public class PanelKhachHang extends JPanel {
    
    private JPanel pnTitle = new JPanel();
    
    private JPanel pnMidContent = new JPanel();
    private JPanel pnBottomContent = new JPanel();
    private ArrayList<JTextField> txtList = new ArrayList<>();
    private static Font sgUI18 = new Font("Segoe UI", Font.BOLD, 15);
    private static Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    private static Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    private Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);
    //private ArrayList<KhachHangDTO> KHList;
    JLabel lbTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG");
    
    JTextField txt_maKH = new JTextField("Mã khách hàng...");
    JTextField txt_tenKH = new JTextField("Tên khách hàng...");
    JTextField txt_cmnd = new JTextField("CMND...");
    private JPanel pnMaKH = new JPanel();
    private JPanel pnTenKH = new JPanel();
    private JPanel pnCmnd = new JPanel();
    private JPanel pnGt = new JPanel();
    private JPanel pnTim = new JPanel();
    
    JButton btn_tim = new JButton("TÌM KIẾM");
    JButton btn_thoattim = new JButton("THOÁT TÌM");
    JComboBox gioitinh;
    String gt[] = {"Giới tính", "Nam", "Nữ"};
    private JTable jt;
    private JScrollPane scpn = new JScrollPane();
    JButton btnXuatFile = new JButton("Xuất File");
    JButton btnImport = new JButton("Chọn File");
    
    public PanelKhachHang() {
        
        initComponents();
        
    }
    
    public void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        pnTitle.setPreferredSize(new Dimension(100, 40));
        
        pnMidContent.setPreferredSize(new Dimension(10, 30));
        pnBottomContent.setPreferredSize(new Dimension(100, 600));
        
        this.add(pnTitle);
        this.add(pnMidContent);
        this.add(pnBottomContent);
        
        lbTitle.setFont(sgUI18b);
        pnTitle.add(lbTitle);
        
        jt = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        loaddata();
        
        scpn.setViewportView(jt);
        scpn = new JScrollPane(jt);
        
        pnBottomContent.setLayout(new BorderLayout());
        
        pnBottomContent.add(scpn, BorderLayout.CENTER);
        
        pnMidContent.setLayout(new BoxLayout(pnMidContent, BoxLayout.X_AXIS));
        
        pnMidContent.setLayout(new GridLayout(1, 5, 5, 2));
        pnMidContent.add(pnMaKH);
        pnMaKH.setBorder(new EmptyBorder(0, 8, 0, 0));
        pnMaKH.setLayout(new BorderLayout());
        pnMaKH.add(txt_maKH, BorderLayout.CENTER);
        txt_maKH.setMargin(new Insets(5, 10, 5, 10));
        pnMaKH.setFont(sgUI13);
        
        pnMidContent.add(pnTenKH);
        pnTenKH.setLayout(new BorderLayout());
        pnTenKH.add(txt_tenKH, BorderLayout.CENTER);
        txt_tenKH.setMargin(new Insets(5, 10, 5, 10));
        pnTenKH.setFont(sgUI13);
        
        pnMidContent.add(pnCmnd);
        pnCmnd.setLayout(new BorderLayout());
        pnCmnd.add(txt_cmnd, BorderLayout.CENTER);
        txt_cmnd.setMargin(new Insets(5, 10, 5, 10));
        pnCmnd.setFont(sgUI13);
        btnImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser("src\\GUI\\excel");
                fc.showDialog(null, "Open");
                try {
                    File file = fc.getSelectedFile();
                    ImportExcel ie = new ImportExcel();
                    ie.ImportExcel_KhachHang(file.getPath());
                    loaddata();
                } catch (Exception ex) {
                }
            }
        });
        
        gioitinh = new JComboBox(gt);
        pnMidContent.add(pnGt);
        pnGt.setLayout(new BorderLayout());
        pnGt.add(gioitinh, BorderLayout.CENTER);
        gioitinh.setFont(sgUI13);
        
        txt_maKH.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt_maKH.getText().equals("Mã khách hàng...")) {
                    txt_maKH.setText("");
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (txt_maKH.getText().trim().length() == 0) {
                    txt_maKH.setText("Mã khách hàng...");
                }
            }
        });
        txt_tenKH.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt_tenKH.getText().equals("Tên khách hàng...")) {
                    txt_tenKH.setText("");
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (txt_tenKH.getText().trim().length() == 0) {
                    txt_tenKH.setText("Tên khách hàng...");
                }
            }
        });
        txt_cmnd.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt_cmnd.getText().equals("CMND...")) {
                    txt_cmnd.setText("");
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (txt_cmnd.getText().trim().length() == 0) {
                    txt_cmnd.setText("CMND...");
                }
            }
            
        });
        
        ImageIcon iconTim = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/IconFind.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        btn_tim.setIcon(iconTim);
        btn_tim.setFont(sgUI18);
        
        pnMidContent.add(btn_tim);
        ImageIcon iconThoat = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/IconReset.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        btn_thoattim.setIcon(iconThoat);
        btn_thoattim.setFont(sgUI18);
        
        pnMidContent.add(btn_thoattim);
        
        pnMidContent.add(btnXuatFile);
        pnMidContent.add(btnImport);
        
        ImageIcon iconFile = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/ex.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        btnXuatFile.setIcon(iconFile);
        btnXuatFile.setFont(sgUI18);
        btnXuatFile.setFocusPainted(false);
        
        btnImport.setIcon(iconFile);
        btnImport.setFont(sgUI18);
        btnImport.setFocusPainted(false);
        
        btnXuatFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackUpData bk = new BackUpData();
                try {
                    String path = System.getProperty("user.dir") + "\\src\\GUI\\excel\\BackUpData_KhachHang.xlsx";
                    bk.backup_KhachHang(path);
                    JOptionPane.showMessageDialog(null, "Xuất file thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(new File(path));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Xuất file không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1) {
                    int SelectRow = jt.getSelectedRow();
                    String maKH = jt.getValueAt(jt.getSelectedRow(), 1).toString();
                    String nameKH = jt.getValueAt(jt.getSelectedRow(), 2).toString();
                    String cmnd = jt.getValueAt(jt.getSelectedRow(), 3).toString();
                    String gtinh = jt.getValueAt(jt.getSelectedRow(), 4).toString();
                    String sdt = jt.getValueAt(jt.getSelectedRow(), 5).toString();
                    try {
                        new InfoKhachHang(maKH, nameKH, cmnd, gtinh, sdt, jt, SelectRow);
                    } catch (ParseException ex) {
                        Logger.getLogger(PanelNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        btn_thoattim.addActionListener((ActionEvent e) -> {
            DefaultTableModel dm = (DefaultTableModel) jt.getModel();
            dm.setColumnCount(0);
            loaddata();
            txt_maKH.setText("Mã khách hàng...");
            txt_tenKH.setText("Tên khách hàng...");
            txt_cmnd.setText("CMND...");
            gioitinh.setSelectedIndex(0);
            
        });
        btn_tim.addActionListener((ActionEvent e) -> {
            ArrayList<KhachHangDTO> KHList = new ArrayList<>();
            try {
                KHList = new KhachHangBUS().LoadKH();
            } catch (SQLException ex) {
                Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            if ((txt_maKH.getText().equals("Mã khách hàng...") || txt_maKH.getText().isBlank())
                    && (txt_tenKH.getText().equals("Tên khách hàng...") || txt_tenKH.getText().isBlank())
                    && (txt_cmnd.getText().equals("CMND...") || txt_cmnd.getText().isBlank()) && gioitinh.getSelectedIndex() == 0) {
                
                JOptionPane.showMessageDialog(null, "Mời lựa chọn", "Thông báo", 0);
            } else {
                if (find((txt_maKH.getText()))) {
                    
                    int i = 0;
                    for (i = 0; i < KHList.size(); i++) {
                        if (KHList.get(i).getMaKH().contains(txt_maKH.getText()) == false) {
                            KHList.remove(KHList.get(i));
                            i--;
                        }
                    }
                    
                } else if (find((txt_maKH.getText())) == false && txt_maKH.getText().equals("Mã khách hàng...") == false || txt_maKH.getText().isBlank()) {
                    
                    JOptionPane.showMessageDialog(null, "Mã khách hàng không tồn tại", "Thông báo", 0);
                }
                if (findName((txt_tenKH.getText()))) {
                    int i = 0;
                    for (i = 0; i < KHList.size(); i++) {
                        if (KHList.get(i).getTenKH().contains(txt_tenKH.getText()) == false) {
                            KHList.remove(KHList.get(i));
                            i--;
                        }
                    }
                } else if (findName((txt_tenKH.getText())) == false && txt_tenKH.getText().equals("Tên khách hàng...") == false || txt_tenKH.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Tên khách hàng không tồn tại", "Thông báo", 0);
                }
                if (findCmnd(txt_cmnd.getText())) {
                    int i = 0;
                    for (i = 0; i < KHList.size(); i++) {
                        if (KHList.get(i).getCmnd().contains(txt_cmnd.getText()) == false) {
                            KHList.remove(KHList.get(i));
                            i--;
                        }
                    }
                    
                } else if (findCmnd(txt_cmnd.getText()) == false && txt_cmnd.getText().equals("CMND...") == false || txt_cmnd.getText().isBlank()) {
                    
                    JOptionPane.showMessageDialog(null, "CMND không tồn tại", "Thông báo", 0);
                }
                if (gioitinh.getSelectedIndex() == 1) {
                    int i = 0;
                    for (i = 0; i < KHList.size(); i++) {
                        if (KHList.get(i).getGioiTinh().equals("Nữ")) {
                            KHList.remove(KHList.get(i));
                            i--;
                        }
                    }
                    
                }
                if (gioitinh.getSelectedIndex() == 2) {
                    int i = 0;
                    for (i = 0; i < KHList.size(); i++) {
                        if (KHList.get(i).getGioiTinh().equals("Nam")) {
                            KHList.remove(KHList.get(i));
                            i--;
                        }
                    }
                }
                DefaultTableModel dm = (DefaultTableModel) jt.getModel();
                while (dm.getRowCount() > 0) {
                    dm.removeRow(0);
                }
                Object rowData[] = new Object[6];
                
                for (int i = 0; i < KHList.size(); i++) {
                    
                    rowData[0] = i + 1;
                    rowData[1] = KHList.get(i).getMaKH();
                    rowData[2] = KHList.get(i).getTenKH();
                    rowData[3] = KHList.get(i).getCmnd();
                    rowData[4] = KHList.get(i).getGioiTinh();
                    rowData[5] = KHList.get(i).getSdt();
                    dm.addRow(rowData);
                    
                }
            }
        });
    }
    
    public boolean find(String a) {
        ArrayList<KhachHangDTO> KHList = new ArrayList<>();
        try {
            KHList = new KhachHangBUS().LoadKH();
        } catch (SQLException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < KHList.size(); i++) {
            if (KHList.get(i).getMaKH().contains(a) && KHList.get(i).getXuLy() == 0) {
                
                return true;
            }
        }
        return false;
    }
    
    public boolean findName(String a) {
        ArrayList<KhachHangDTO> KHList = new ArrayList<>();
        try {
            KHList = new KhachHangBUS().LoadKH();
        } catch (SQLException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < KHList.size(); i++) {
            String tenKH = KHList.get(i).getTenKH();
            if (tenKH != null && tenKH.contains(a) && KHList.get(i).getXuLy() == 0) {
                return true;
            }
        }
        return false;
    }
    
    public boolean findCmnd(String a) {
        ArrayList<KhachHangDTO> KHList = new ArrayList<>();
        try {
            KHList = new KhachHangBUS().LoadKH();
        } catch (SQLException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < KHList.size(); i++) {
            if (KHList.get(i).getCmnd().equals(a) && KHList.get(i).getXuLy() == 0) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checknonNum(String a) {
        try {
            double d = Double.parseDouble(a);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }
    
    public void loaddata() {
        ArrayList<KhachHangDTO> KHList = new ArrayList<>();
        try {
            KHList = new KhachHangBUS().LoadKH();
        } catch (SQLException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultTableModel dm = (DefaultTableModel) jt.getModel();
        while (dm.getRowCount() > 0) {
            dm.removeRow(0);
            dm.setColumnCount(0);
        }
        dm.addColumn("STT");
        dm.addColumn("Mã khách hàng");
        dm.addColumn("Tên khách hàng");
        dm.addColumn("CMND");
        dm.addColumn("Giới tính");
        dm.addColumn("SĐT");
        jt.setModel(dm);
        int i = 1;
        for (KhachHangDTO x : KHList) {
            Object[] data = {i, x.getMaKH(), x.getTenKH(), x.getCmnd(), x.getGioiTinh(), x.getSdt()};
            dm.addRow(data);
            i++;
        }
        
        jt.setShowGrid(false);
        jt.setIntercellSpacing(new Dimension(0, 0));
        jt.setRowHeight(30);
        jt.getColumnModel().getColumn(jt.getColumnModel().getColumnIndex("STT")).setPreferredWidth(1);
        jt.getColumnModel().getColumn(1).setPreferredWidth(50);
        jt.getTableHeader().setPreferredSize(new Dimension(1, 32));
        jt.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        jt.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
                    lb.setBackground(Color.decode("#D3D3D3"));
                } else {
                    if (row % 2 == 1) {
                        lb.setBackground(Color.decode("#FDFDFD"));
                    } else {
                        lb.setBackground(Color.white);
                    }
                }
                
                return lb;
            }
        };
        for (int j = 0; j < jt.getColumnCount(); j++) {
            jt.getColumnModel().getColumn(j).setCellRenderer(renderBorder);
        }
        jt.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
    }
    
    public static void loaddata(JTable jt) {
        ArrayList<KhachHangDTO> KHList = new ArrayList<>();
        try {
            KHList = new KhachHangBUS().LoadKH();
        } catch (SQLException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultTableModel dm = (DefaultTableModel) jt.getModel();
        while (dm.getRowCount() > 0) {
            dm.removeRow(0); //xóa table để update table mới
            dm.setColumnCount(0);
        }
        dm.addColumn("STT");
        dm.addColumn("Mã khách hàng");
        dm.addColumn("Tên khách hàng");
        dm.addColumn("CMND");
        dm.addColumn("Giới tính");
        dm.addColumn("SĐT");
        jt.setModel(dm);
        int i = 1;
        for (KhachHangDTO x : KHList) {
            Object[] data = {i, x.getMaKH(), x.getTenKH(), x.getCmnd(), x.getGioiTinh(), x.getSdt()};
            dm.addRow(data);
            i++;
        }
        jt.setShowGrid(false);
        jt.setIntercellSpacing(new Dimension(0, 0));
        jt.setRowHeight(30);
        jt.getColumnModel().getColumn(jt.getColumnModel().getColumnIndex("STT")).setPreferredWidth(1);
        jt.getColumnModel().getColumn(1).setPreferredWidth(50);
        jt.getTableHeader().setPreferredSize(new Dimension(1, 32));
        jt.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        jt.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
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
                    lb.setBackground(Color.decode("#C0C0C0"));
                } else {
                    if (row % 2 == 1) {
                        lb.setBackground(Color.decode("#FDFDFD"));
                    } else {
                        lb.setBackground(Color.white);
                    }
                }
                return lb;
            }
        };
        for (int j = 0; j < jt.getColumnCount(); j++) {
            jt.getColumnModel().getColumn(j).setCellRenderer(renderBorder);
        }
        jt.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
    }
    
    public void lightDark(int lightDark) {
        if (lightDark == 1) {
            Color black = new Color(51, 51, 51);
            pnTitle.setBackground(black);
            pnMidContent.setBackground(black);
            pnBottomContent.setBackground(black);
            scpn.setBackground(black);
            jt.setBackground(black);
            jt.setBackground(black);
            lbTitle.setForeground(Color.white);
            jt.getTableHeader().setBackground(Color.decode("#528B8B"));
            gioitinh.setBackground(Color.white);
            btn_tim.setBackground(Color.decode("#BFEFFF"));
            btn_thoattim.setBackground(Color.decode("#BFEFFF"));
            pnBottomContent.setBorder(BorderFactory.createLineBorder(black, 10));
            btn_tim.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 10, 0, 10, black), new EmptyBorder(10, 10, 10, 10)));
            btn_thoattim.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 5, 0, 10, black), new EmptyBorder(10, 10, 10, 10)));
            pnMaKH.setBackground(black);
            btnXuatFile.setBackground(Color.decode("#BFEFFF"));
            btnXuatFile.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 10, 0, 10, black), new EmptyBorder(10, 10, 10, 10)));
            btnImport.setBackground(Color.decode("#BFEFFF"));
            btnImport.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 10, 0, 10, black), new EmptyBorder(10, 10, 10, 10)));
        } else {
            pnTitle.setBackground(Color.white);
            pnMidContent.setBackground(Color.white);
            pnBottomContent.setBackground(Color.white);
            scpn.setBackground(Color.white);
            jt.setBackground(Color.white);
            jt.setBackground(Color.white);
            lbTitle.setForeground(Color.black);
            jt.getTableHeader().setBackground(Color.decode("#79CDCD"));
            gioitinh.setBackground(Color.white);
            btn_tim.setBackground(Color.decode("#87CEFA"));
            btn_thoattim.setBackground(Color.decode("#87CEFA"));
            pnBottomContent.setBorder(BorderFactory.createLineBorder(Color.white, 10));
            btn_tim.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 10, 0, 10, Color.white), new EmptyBorder(10, 10, 10, 10)));
            btn_thoattim.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 5, 0, 10, Color.white), new EmptyBorder(10, 10, 10, 10)));
            pnMaKH.setBackground(Color.white);
            btnXuatFile.setBackground(Color.decode("#87CEFA"));
            btnXuatFile.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 10, 0, 10, Color.white), new EmptyBorder(10, 10, 10, 10)));
            btnImport.setBackground(Color.decode("#87CEFA"));
            btnImport.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 10, 0, 10, Color.white), new EmptyBorder(10, 10, 10, 10)));
        }
    }
}
