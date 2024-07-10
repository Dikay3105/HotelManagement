package GUI;

import BUS.NhanVienBUS;
import BUS.BackUpData;
import BUS.ImportExcel;
import DTO.NhanVienDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.*;

/**
 *
 * @author ANH KHOA
 */
public class PanelNhanVien extends JPanel {

    private JTable jTable;
    private JScrollPane jScrollPane;
    String gt[] = {"Giới Tính", "Nam", "Nữ"};
    String phongban[] = {"Phòng Ban", "Quản Lý", "Lễ Tân", "Buồng Phòng", "Bảo Vệ", "Bếp", "Kế Toán"};

    private JLabel lbName, lbIdNV, lbPasswd, lbNgaySinh, lbGioiTinh, lbPhongBan, lbHeso;
    private JButton jbADD, jbFIND, jbResetTABLE;
    private JComboBox cbGioiTinh, cbPhongBan;

    NhanVienBUS nv = new NhanVienBUS();
//Set Panel    
    private JPanel pnTitle = new JPanel();
    private JPanel pnTopContent = new JPanel();
    private JPanel pnMidContent = new JPanel();
    private JPanel pnMidContentCenter = new JPanel();
    private JPanel pnMidContentRight = new JPanel();
    private JPanel pnBottomContent = new JPanel();

//Set Label
    private JLabel lbTitle = new JLabel("DANH MỤC NHÂN VIÊN");

//Set Font     
    private Font sgUI18 = new Font("Segoe UI", Font.BOLD, 15);
    private Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    private Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    private Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);
    ArrayList<NhanVienDTO> listNhanVien;
    ArrayList<NhanVienDTO> listTemp;
//Set Jtext
    private JTextField jtf_name, jtf_idNV, jtf_Heso;
    DefaultTableModel model;

    JButton btnXuatFile = new JButton("Xuất File");
    JPanel pnLeft = new JPanel();
    JButton fc = new JButton("Chọn file");

    public PanelNhanVien() {
        initComponents();

    }

    public void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        pnTitle.setPreferredSize(new Dimension(100, 40));

        pnTopContent.setLayout(new BorderLayout());
        pnTopContent.setPreferredSize(new Dimension(100, 50));

        pnMidContent.setPreferredSize(new Dimension(100, 40));
        pnMidContent.setLayout(new BorderLayout());

        pnBottomContent.setPreferredSize(new Dimension(100, 600));

        this.add(pnTitle);
        this.add(pnTopContent);
        this.add(pnMidContent);
        this.add(pnBottomContent);
//Tiêu đề 

        lbTitle.setFont(sgUI18b);
        pnTitle.add(lbTitle);

        jbADD = new JButton("Thêm Nhân Viên");
        ImageIcon iconAdd = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/IconAdd.png")).getImage().getScaledInstance(25, 30, Image.SCALE_SMOOTH));
        jbADD.setIcon(iconAdd);
        jbADD.setPreferredSize(new Dimension(163, 30));
        jbADD.setFont(sgUI13b);
        jbADD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jbADD.setBackground(Color.decode("#5CBBF6"));
            }

            @Override
            public void mouseExited(MouseEvent e) {

                jbADD.setBackground(Color.decode("#87CEFA"));
            }
        });

        btnXuatFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnXuatFile.setBackground(Color.decode("#5CBBF6"));
            }

            @Override
            public void mouseExited(MouseEvent e) {

                btnXuatFile.setBackground(Color.decode("#87CEFA"));
            }
        });
        fc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                fc.setBackground(Color.decode("#5CBBF6"));
            }

            @Override
            public void mouseExited(MouseEvent e) {

                fc.setBackground(Color.decode("#87CEFA"));
            }
        });
        btnXuatFile.setFocusPainted(false);
        btnXuatFile.setPreferredSize(new Dimension(163, 30));
        btnXuatFile.setFont(sgUI13b);
        fc.setFocusPainted(false);
        fc.setPreferredSize(new Dimension(163, 30));
        fc.setFont(sgUI13b);
        jbADD.setBackground(Color.decode("#87CEFA"));
        fc.setBackground(Color.decode("#87CEFA"));
        btnXuatFile.setBackground(Color.decode("#87CEFA"));

        fc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser("src\\GUI\\excel");
                fc.showDialog(null, "Open");
                try {
                    File file = fc.getSelectedFile();
                    ImportExcel ie = new ImportExcel();
                    ie.ImportExcel_NhanVien(file.getPath());
                    loadTalbe(jTable);
                } catch (Exception ex) {
                }
            }
        });

        btnXuatFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackUpData bk = new BackUpData();
                try {
                    String path = System.getProperty("user.dir") + "\\src\\GUI\\excel\\BackUpData_NhanVien.xlsx";
                    bk.backup_NhanVien(path);
                    JOptionPane.showMessageDialog(null, "Xuất file thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(new File(path));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Xuất file không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        pnLeft.setLayout(new GridLayout(1, 3));
        pnLeft.add(jbADD);
        pnLeft.add(btnXuatFile);
        pnLeft.add(fc);
        ImageIcon iconFile = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/ex.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        btnXuatFile.setIcon(iconFile);
        pnTopContent.add(pnLeft, BorderLayout.WEST);
        jTable = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jScrollPane = new JScrollPane(jTable);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        jTable.setFont(sgUI13);
        jTable.getTableHeader().setForeground(Color.white);
        jTable.getTableHeader().setPreferredSize(new Dimension(1, 32));
        jTable.setRowHeight(30);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jScrollPane.setViewportView(jTable);

        pnBottomContent.setLayout(new BorderLayout());
        pnBottomContent.add(jScrollPane, BorderLayout.CENTER);

//Set            
        jtf_idNV = new JTextField("Mã Nhân Viên...");
        jtf_idNV.setPreferredSize(new Dimension(50, 30));
        jtf_idNV.setFont(sgUI13);
        jtf_idNV.setMargin(new Insets(5, 5, 5, 5));
        jtf_idNV.setMargin(new Insets(5, 5, 5, 5));
        jtf_idNV.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jtf_idNV.getText().equals("Mã Nhân Viên...")) {
                    jtf_idNV.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jtf_idNV.getText().trim().length() == 0) {
                    jtf_idNV.setText("Mã Nhân Viên...");
                }
            }
        });

        jtf_name = new JTextField("Họ Tên...");
        jtf_name.setPreferredSize(new Dimension(50, 30));
        jtf_name.setFont(sgUI13);
        jtf_name.setMargin(new Insets(5, 5, 5, 5));
        jtf_name.setMargin(new Insets(5, 5, 5, 5));
        jtf_name.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jtf_name.getText().equals("Họ Tên...")) {
                    jtf_name.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jtf_name.getText().trim().length() == 0) {
                    jtf_name.setText("Họ Tên...");
                }
            }
        });

        jtf_Heso = new JTextField("Hệ Số Lương...");
        jtf_Heso.setPreferredSize(new Dimension(50, 30));
        jtf_Heso.setFont(sgUI13);
        jtf_Heso.setMargin(new Insets(5, 5, 5, 5));
        jtf_Heso.setMargin(new Insets(5, 5, 5, 5));
        jtf_Heso.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jtf_Heso.getText().equals("Hệ Số Lương...")) {
                    jtf_Heso.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jtf_Heso.getText().trim().length() == 0) {
                    jtf_Heso.setText("Hệ Số Lương...");
                }
            }
        });

        jbADD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNhanVienGUI addNhanVienGUI = new AddNhanVienGUI(jTable);

            }
        });

        ///////////////////////////////////////////////////////////////////////
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String maNV = "";
                String passNV = "";
                String nameNV = "";
                String gtinh = "";
                String nsinh = "";
                String pban = "";
                String hso = "";
                String email = "";
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                    int SelectRow = jTable.getSelectedRow();
                    for (int i = 0; i < 9; i++) {
                        // Lấy đối tượng TableColumnModel
                        TableColumnModel columnModel = jTable.getColumnModel();
                        // Lấy đối tượng TableColumn của cột thứ 1 (cột ID)
                        TableColumn column = columnModel.getColumn(i);
                        // Lấy giá trị của header của cột ID
                        Object headerValue = column.getHeaderValue();
                        if (headerValue.equals("Mã Nhân Viên")) {
                            maNV = jTable.getValueAt(jTable.getSelectedRow(), i).toString();
                        } else if (headerValue.equals("Mật Khẩu")) {
                            passNV = jTable.getValueAt(jTable.getSelectedRow(), i).toString();
                        } else if (headerValue.equals("Họ Tên")) {
                            nameNV = jTable.getValueAt(jTable.getSelectedRow(), i).toString();
                        } else if (headerValue.equals("Giới Tính")) {
                            gtinh = jTable.getValueAt(jTable.getSelectedRow(), i).toString();
                        } else if (headerValue.equals("Phòng Ban")) {
                            pban = jTable.getValueAt(jTable.getSelectedRow(), i).toString();
                        } else if (headerValue.equals("Ngày Sinh")) {
                            nsinh = jTable.getValueAt(jTable.getSelectedRow(), i).toString();
                        } else if (headerValue.equals("Email")) {
                            email = jTable.getValueAt(jTable.getSelectedRow(), i).toString();
                        } else if (headerValue.equals("Hệ Số")) {
                            hso = jTable.getValueAt(jTable.getSelectedRow(), i).toString();
                        }

                    }
                    try {
                        new InforNhanVien(maNV, passNV, nameNV, gtinh, nsinh, pban, email, hso, jTable, SelectRow);
                    } catch (ParseException ex) {
                        Logger.getLogger(PanelNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        cbGioiTinh = new JComboBox(gt);
        cbGioiTinh.setFont(sgUI13);
        cbPhongBan = new JComboBox(phongban);
        cbPhongBan.setFont(sgUI13);

        jbFIND = new JButton("Tìm Kiếm");
        ImageIcon find = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/IconFind.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        jbFIND.setIcon(find);
        jbFIND.setBackground(Color.decode("#87CEFA"));
        jbFIND.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jbFIND.setBackground(Color.decode("#5CBBF6"));
            }

            @Override
            public void mouseExited(MouseEvent e) {

                jbFIND.setBackground(Color.decode("#87CEFA"));
            }
        });
//        jTable.setBackground(Color.white);

        jbResetTABLE = new JButton("THOÁT TÌM");
        ImageIcon reset = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/IconReset.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        jbResetTABLE.setBackground(Color.decode("#87CEFA"));
        jbResetTABLE.setIcon(reset);
        jbResetTABLE.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jbResetTABLE.setBackground(Color.decode("#5CBBF6"));
            }

            @Override
            public void mouseExited(MouseEvent e) {

                jbResetTABLE.setBackground(Color.decode("#87CEFA"));
            }
        });

        pnMidContentCenter.setLayout(new GridLayout(1, 7, 5, 5));
        pnMidContentCenter.add(jtf_idNV);
        pnMidContentCenter.add(jtf_name);
        pnMidContentCenter.add(jtf_Heso);
        pnMidContentCenter.add(cbGioiTinh);
        pnMidContentCenter.add(cbPhongBan);

        pnMidContentRight.setLayout(new GridLayout(1, 2, 5, 5));

        pnMidContentRight.add(jbFIND);
        pnMidContentRight.add(jbResetTABLE);
        pnMidContent.add(pnMidContentCenter, BorderLayout.CENTER);
        pnMidContent.add(pnMidContentRight, BorderLayout.EAST);

//Show table        
        loadTalbe();
//Tìm Kiếm 
        jbFIND.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mnv = jtf_idNV.getText();
                String namenv = jtf_name.getText();
                String gtinh = cbGioiTinh.getSelectedItem().toString();
                String pban = cbPhongBan.getSelectedItem().toString();
                String hso = jtf_Heso.getText();
                /*if (mnv.equals("Mã Nhân Viên...") && namenv.equals("Họ Tên...") && hso.equals("Hệ Số Lương...") && gtinh.equals("Giới Tính") && pban.equals("Phòng Ban")) {
                    JOptionPane.showMessageDialog(null, "BẠN CHƯA ĐIỀN NỘI DUNG ĐỂ TÌM KIẾM", "Thông Báo", JOptionPane.ERROR_MESSAGE);
                } else {
                    nv.findNhanVien(jTable, mnv, namenv, gtinh, pban, hso);
                }*/
                listNhanVien = new NhanVienBUS().getListNhanVien();
                listTemp = new ArrayList<>();
                if (!mnv.isEmpty() && !mnv.equals("Mã Nhân Viên...")) {
                    for (NhanVienDTO x : listNhanVien) {
                        ///////////////////////////////////////////////
                        if (x.getMaNV().toUpperCase().contains(mnv.toUpperCase())) {
                            listTemp.add(x);
                        }
                    }
                    listNhanVien.clear();
                    for (NhanVienDTO x : listTemp) {
                        listNhanVien.add(x);
                    }
                    listTemp.clear();
                }
                if (!namenv.isEmpty() && !namenv.equals("Họ Tên...")) {
                    for (NhanVienDTO x : listNhanVien) {
                        if (x.getTenNV().toUpperCase().contains(namenv.toUpperCase())) {
                            listTemp.add(x);
                        }
                    }
                    listNhanVien.clear();
                    for (NhanVienDTO x : listTemp) {
                        listNhanVien.add(x);
                    }
                    listTemp.clear();
                }
//////////////////////////////////////////////////  
                if (!pban.isEmpty() && !pban.equals("Phòng Ban")) {
                    for (NhanVienDTO x : listNhanVien) {
                        if (x.getTenPB().equals(pban)) {
                            listTemp.add(x);
                        }
                    }
                    listNhanVien.clear();
                    for (NhanVienDTO x : listTemp) {
                        listNhanVien.add(x);
                    }
                    listTemp.clear();
                }
                if (!gtinh.isEmpty() && !gtinh.equals("Giới Tính")) {
                    for (NhanVienDTO x : listNhanVien) {
                        if (x.getGioiTinh().equals(gtinh)) {
                            listTemp.add(x);
                        }
                    }
                    listNhanVien.clear();
                    for (NhanVienDTO x : listTemp) {
                        listNhanVien.add(x);
                    }
                    listTemp.clear();
                }
                if (!hso.isEmpty() && !hso.equals("Hệ Số Lương...")) {
                    for (NhanVienDTO x : listNhanVien) {
                        if ((x.getHeSoLuong() + "").equals(hso)) {
                            listTemp.add(x);
                        }
                    }
                    listNhanVien.clear();
                    for (NhanVienDTO x : listTemp) {
                        listNhanVien.add(x);
                    }
                    listTemp.clear();
                }
                if (listNhanVien.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không Có Nhân Viên Như Bạn Muốn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    reset();
                } else {
                    model = (DefaultTableModel) jTable.getModel();
                    for (int count = 0; count < model.getRowCount(); count++) {
                        model.setRowCount(0);
                    }
                    loadTalbe(listNhanVien);
                }
            }
        });
        jbResetTABLE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
    }

    public void loadTalbe() {
        ArrayList<NhanVienDTO> NVLIST = new ArrayList<>();
        int i = 1;
        NVLIST = new NhanVienBUS().getListNhanVien();
        model = (DefaultTableModel) jTable.getModel();
        model.setColumnIdentifiers(new Object[]{
            "STT", "Mã Nhân Viên", "Mật Khẩu", "Họ Tên", "Giới Tính", "Ngày Sinh", "Phòng Ban", "Email", "Hệ Số"
        });

        for (NhanVienDTO s : NVLIST) {
            if (s.getXuLy() != 1) {
                model.addRow(new Object[]{
                    i++, s.getMaNV(), s.getmKNV(), s.getTenNV(), s.getGioiTinh(), s.getNgaySinhString(), s.getTenPB(), s.getEmail(), s.getHeSoLuong()
                });

            }
        }
        jTable.getColumnModel().getColumn(jTable.getColumnModel().getColumnIndex("STT")).setPreferredWidth(10);
        jTable.getColumnModel().getColumn(jTable.getColumnModel().getColumnIndex("Email")).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(jTable.getColumnModel().getColumnIndex("Mật Khẩu")).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(jTable.getColumnModel().getColumnIndex("Mã Nhân Viên")).setPreferredWidth(50);

        DefaultTableCellRenderer renderBorder = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                JLabel lb = (JLabel) c;
                if (column == table.getColumnModel().getColumnIndex("STT")) {
                    lb.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 1, 1, Color.decode("#DDDDDD")), new EmptyBorder(0, 5, 0, 5)));
                    lb.setHorizontalAlignment(JLabel.CENTER);
                } else {
                    lb.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 1, 0, Color.decode("#DDDDDD")), new EmptyBorder(0, 5, 0, 5)));
                    if (column == table.getColumnModel().getColumnIndex("Ngày Sinh")) {
                        lb.setHorizontalAlignment(JLabel.CENTER);
                    } else {
                        lb.setHorizontalAlignment(JLabel.LEFT);
                    }
                }
                if (isSelected) {
                    lb.setBackground(Color.decode("#F5F5F5"));
                } else {
                    lb.setBackground(Color.white);
                }
                return lb;
            }
        };

        for (int j = 0; j < jTable.getColumnCount(); j++) {
            TableColumn column = jTable.getColumnModel().getColumn(j);
            if (column.getHeaderValue().toString().startsWith("STT") || column.getHeaderValue().toString().equals("Mã Nhân Viên") || column.getHeaderValue().toString().equals("Ngày Sinh") || column.getHeaderValue().toString().equals("Giới Tính") || column.getHeaderValue().toString().equals("Hệ Số")) {
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//                renderer.setHorizontalAlignment(JLabel.CENTER);
                //                renderer.setForeground(Color.BLUE); 
//                column.setCellRenderer(renderer);
                column.setCellRenderer(renderBorder);
            }
            if (column.getHeaderValue().toString().equals("Ngày Sinh")) {
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//                renderer.setHorizontalAlignment(JLabel.LEFT);
//                column.setCellRenderer(renderer);
                column.setCellRenderer(renderBorder);
            } else {
                column.setCellRenderer(renderBorder);
            }
        }
        jTable.setIntercellSpacing(new Dimension(0, 0));
        jTable.setShowGrid(false);
    }

    public void loadTalbe(ArrayList<NhanVienDTO> listNhanVien) {
        ArrayList<NhanVienDTO> NVLIST = new ArrayList<>();
        int i = 1;
        model = (DefaultTableModel) jTable.getModel();
        model.setColumnIdentifiers(new Object[]{
            "STT", "Mã Nhân Viên", "Mật Khẩu", "Họ Tên", "Giới Tính", "Ngày Sinh", "Phòng Ban", "Email", "Hệ Số"
        });
        for (NhanVienDTO s : listNhanVien) {
            if (s.getXuLy() != 1) {
                model.addRow(new Object[]{
                    i++, s.getMaNV(), s.getmKNV(), s.getTenNV(), s.getGioiTinh(), s.getNgaySinhString(), s.getTenPB(), s.getEmail(), s.getHeSoLuong()
                });

            }
        }
        jTable.getColumnModel().getColumn(jTable.getColumnModel().getColumnIndex("STT")).setPreferredWidth(10);
        jTable.getColumnModel().getColumn(jTable.getColumnModel().getColumnIndex("Email")).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(jTable.getColumnModel().getColumnIndex("Mật Khẩu")).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(jTable.getColumnModel().getColumnIndex("Mã Nhân Viên")).setPreferredWidth(50);
        DefaultTableCellRenderer renderBorder = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                JLabel lb = (JLabel) c;
                if (column == table.getColumnModel().getColumnIndex("STT")) {
                    lb.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 1, 1, Color.decode("#DDDDDD")), new EmptyBorder(0, 5, 0, 5)));
                    lb.setHorizontalAlignment(JLabel.CENTER);
                } else {
                    lb.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 1, 0, Color.decode("#DDDDDD")), new EmptyBorder(0, 5, 0, 5)));
                    if (column == table.getColumnModel().getColumnIndex("Ngày Sinh")) {
                        lb.setHorizontalAlignment(JLabel.CENTER);
                    } else {
                        lb.setHorizontalAlignment(JLabel.LEFT);
                    }
                }
                if (isSelected) {
                    lb.setBackground(Color.decode("#F5F5F5"));
                } else {
                    lb.setBackground(Color.white);
                }
                return lb;
            }
        };
        for (int j = 0; j < jTable.getColumnCount(); j++) {
            TableColumn column = jTable.getColumnModel().getColumn(j);
            if (column.getHeaderValue().toString().startsWith("STT") || column.getHeaderValue().toString().equals("Mã Nhân Viên") || column.getHeaderValue().toString().equals("Ngày Sinh") || column.getHeaderValue().toString().equals("Giới Tính") || column.getHeaderValue().toString().equals("Hệ Số")) {
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//                renderer.setHorizontalAlignment(JLabel.CENTER);
                //                renderer.setForeground(Color.BLUE); 
//                column.setCellRenderer(renderer);
                column.setCellRenderer(renderBorder);
            }
            if (column.getHeaderValue().toString().equals("Ngày Sinh")) {
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//                renderer.setHorizontalAlignment(JLabel.LEFT);
//                column.setCellRenderer(renderer);
                column.setCellRenderer(renderBorder);
            } else {
                column.setCellRenderer(renderBorder);
            }
        }
        jTable.setIntercellSpacing(new Dimension(0, 0));
        jTable.setShowGrid(false);
    }

    public static void loadTalbe(JTable table) {
        ArrayList<NhanVienDTO> NVLIST = new ArrayList<>();
        int i = 1;
        DefaultTableModel model = new DefaultTableModel();
        NVLIST = new NhanVienBUS().getListNhanVien();
        model.setColumnIdentifiers(new Object[]{
            "STT", "Mã Nhân Viên", "Mật Khẩu", "Họ Tên", "Giới Tính", "Ngày Sinh", "Phòng Ban", "Email", "Hệ Số"
        });
        table.setModel(model);
        for (NhanVienDTO s : NVLIST) {
            if (s.getXuLy() != 1) {
                model.addRow(new Object[]{
                    i++, s.getMaNV(), s.getmKNV(), s.getTenNV(), s.getGioiTinh(), s.getNgaySinhString(), s.getTenPB(), s.getEmail(), s.getHeSoLuong()
                });

            }
        }

        table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("STT")).setPreferredWidth(10);
        table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Email")).setPreferredWidth(150);
        table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Mật Khẩu")).setPreferredWidth(50);
        table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Mã Nhân Viên")).setPreferredWidth(50);

        DefaultTableCellRenderer renderBorder = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                JLabel lb = (JLabel) c;
                if (column == table.getColumnModel().getColumnIndex("STT")) {
                    lb.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 1, 1, Color.decode("#DDDDDD")), new EmptyBorder(0, 5, 0, 5)));
                    lb.setHorizontalAlignment(JLabel.CENTER);
                } else {
                    lb.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 1, 0, Color.decode("#DDDDDD")), new EmptyBorder(0, 5, 0, 5)));
                    if (column == table.getColumnModel().getColumnIndex("Ngày Sinh")) {
                        lb.setHorizontalAlignment(JLabel.CENTER);
                    } else {
                        lb.setHorizontalAlignment(JLabel.LEFT);
                    }
                }
                if (isSelected) {
                    lb.setBackground(Color.decode("#F5F5F5"));
                } else {
                    lb.setBackground(Color.white);
                }
                return lb;
            }
        };
        for (int j = 0; j < table.getColumnCount(); j++) {
            TableColumn column = table.getColumnModel().getColumn(j);
            if (column.getHeaderValue().toString().startsWith("STT") || column.getHeaderValue().toString().equals("Mã Nhân Viên") || column.getHeaderValue().toString().equals("Ngày Sinh") || column.getHeaderValue().toString().equals("Giới Tính") || column.getHeaderValue().toString().equals("Hệ Số")) {
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//                renderer.setHorizontalAlignment(JLabel.CENTER);
                //                renderer.setForeground(Color.BLUE); 
//                column.setCellRenderer(renderer);
                column.setCellRenderer(renderBorder);
            }
            if (column.getHeaderValue().toString().equals("Ngày Sinh")) {
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//                renderer.setHorizontalAlignment(JLabel.LEFT);
//                column.setCellRenderer(renderer);
                column.setCellRenderer(renderBorder);
            } else {
                column.setCellRenderer(renderBorder);
            }
        }
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setShowGrid(false);
    }

    public void reset() {
        jtf_idNV.setText("Mã Nhân Viên...");
        jtf_name.setText("Họ Tên...");
        jtf_Heso.setText("Hệ Số Lương...");
        cbGioiTinh.setSelectedIndex(0);
        cbPhongBan.setSelectedIndex(0);
        model = (DefaultTableModel) jTable.getModel();
        for (int count = 0; count < model.getRowCount(); count++) {
            model.setRowCount(0);
        }
        loadTalbe();
    }

    public void lightDark(int lightDark) {
        if (lightDark == 1) {
            Color black = new Color(51, 51, 51);
            lbTitle.setForeground(Color.white);
            pnTitle.setBackground(black);
            pnTopContent.setBackground(black);
            pnMidContent.setBackground(black);
            pnBottomContent.setBackground(black);
            jTable.getTableHeader().setBackground(Color.decode("#1E90FF"));
            btnXuatFile.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(9, 9, 9, 0, black), new EmptyBorder(10, 10, 10, 10)));
            fc.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(9, 9, 9, 0, black), new EmptyBorder(10, 10, 10, 10)));
            jbADD.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(9, 9, 9, 0, black), new EmptyBorder(10, 10, 10, 10)));
            pnLeft.setBackground(black);
            pnMidContentCenter.setBackground(black);
            pnMidContentRight.setBackground(black);
            pnBottomContent.setBorder(BorderFactory.createLineBorder(black, 10));
            pnMidContentCenter.setBorder(new MatteBorder(0, 9, 0, 9, black));
            pnMidContentRight.setBorder(new MatteBorder(0, 9, 0, 9, black));
            jtf_name.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(3, 3, 3, 3, Color.decode("#606060")), new EmptyBorder(5, 5, 5, 5)));
            jtf_Heso.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(3, 3, 3, 3, Color.decode("#606060")), new EmptyBorder(5, 5, 5, 5)));
            jtf_idNV.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(3, 3, 3, 3, Color.decode("#606060")), new EmptyBorder(5, 5, 5, 5)));
        } else {
            lbTitle.setForeground(Color.black);
            pnTitle.setBackground(Color.white);
            pnTopContent.setBackground(Color.white);
            pnMidContent.setBackground(Color.white);
            pnBottomContent.setBackground(Color.white);
            jTable.getTableHeader().setBackground(Color.decode("#1E90FF"));
            pnLeft.setBackground(Color.white);
            btnXuatFile.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(9, 9, 9, 0, Color.white), new EmptyBorder(10, 10, 10, 10)));
            fc.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(9, 9, 9, 0, Color.white), new EmptyBorder(10, 10, 10, 10)));
            jbADD.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(9, 9, 9, 0, Color.white), new EmptyBorder(10, 10, 10, 10)));
            pnMidContentCenter.setBackground(Color.white);
            pnMidContentRight.setBackground(Color.white);
            pnBottomContent.setBorder(BorderFactory.createLineBorder(Color.white, 10));
            pnMidContentCenter.setBorder(new MatteBorder(0, 9, 0, 9, Color.white));
            pnMidContentRight.setBorder(new MatteBorder(0, 9, 0, 9, Color.white));
            jtf_name.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 3, 3, 1, Color.lightGray), new EmptyBorder(5, 5, 5, 5)));
            jtf_Heso.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 3, 3, 1, Color.lightGray), new EmptyBorder(5, 5, 5, 5)));
            jtf_idNV.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 3, 3, 1, Color.lightGray), new EmptyBorder(5, 5, 5, 5)));
            cbGioiTinh.setBackground(Color.white);
            cbPhongBan.setBackground(Color.white);
        }
    }

    class import_file extends JFrame {

        JFileChooser fc = new JFileChooser("src\\GUI\\excel");

        public import_file() {
            initComponents();
        }

        public void initComponents() {
            setSize(1000, 500);
            setVisible(true);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());
            add(fc, BorderLayout.CENTER);
            int ans = fc.showOpenDialog(null);
            System.out.println(ans);
        }
    }
}
