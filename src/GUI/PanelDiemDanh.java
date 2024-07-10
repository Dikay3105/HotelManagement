package GUI;

import BUS.DiemDanhBUS;
import BUS.NhanVienBUS;
import DTO.DiemDanhDTO;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PanelDiemDanh extends JPanel {

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

    private JPanel pnMaNV = new JPanel();
    private JPanel pnTenNV = new JPanel();
    private JPanel pnNam = new JPanel();
    private JPanel pnThang = new JPanel();

    private JTextField txtMaNV = new JTextField("Mã nhân viên");
    private JTextField txtTenNV = new JTextField("Tên nhân viên");
    private JComboBox cbNam = new JComboBox();
    private JComboBox cbThang = new JComboBox();
    JLabel lbTitle = new JLabel("DANH SÁCH ĐIỂM DANH");

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

    private JLabel lbList = new JLabel("Danh sách điểm danh");

    private JButton btnSearch = new JButton("Tìm kiếm");
    private JButton btnReset = new JButton("Thoát tìm");
    private JButton btnxuatexcel = new JButton("Xuất excel");

    public PanelDiemDanh() {
        initComponents();
    }

    public void initComponents() {
        lbTitle.setFont(sgUI18b);
        setBackground(Color.white);
        setLayout(new BorderLayout());

        pnTitle.setBackground(Color.white);
        add(pnTitle, BorderLayout.NORTH);
        pnTitle.add(lbTitle);

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
        pnInput.add(pnMaNV);

        pnMaNV.setLayout(new BorderLayout());
        pnMaNV.add(txtMaNV, BorderLayout.CENTER);
        txtMaNV.setMargin(new Insets(5, 5, 5, 5));
        pnMaNV.setFont(sgUI13);

        pnInput.add(pnTenNV);
        pnTenNV.setLayout(new BorderLayout());
        pnTenNV.add(txtTenNV, BorderLayout.CENTER);
        txtTenNV.setMargin(new Insets(5, 5, 5, 5));
        txtTenNV.setFont(sgUI13);

        pnInput.add(pnNam);
        pnNam.setLayout(new BorderLayout());
        pnNam.add(cbNam, BorderLayout.CENTER);
        cbNam.setFont(sgUI13);
        cbNam.setBackground(Color.white);
        cbNam.addItem("Năm 2023");

        pnInput.add(pnThang);
        pnThang.setLayout(new BorderLayout());
        pnThang.add(cbThang, BorderLayout.CENTER);
        cbThang.setFont(sgUI13);
        cbThang.setBackground(Color.white);
        cbThang.addItem("Tháng ");
        for (int i = 1; i <= 12; i++) {
            cbThang.addItem("Tháng " + i);
        }

        pnInput.setBorder(new EmptyBorder(5, 8, 5, 8));

        pnBtn.setLayout(new BorderLayout());
        pnBtn.setBackground(Color.white);
        ImageIcon iconTim = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/IconFind.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        btnSearch.setIcon(iconTim);
        btnSearch.setFont(sgUI18b);
        btnSearch.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 10, 0, 10, Color.white), new EmptyBorder(10, 10, 10, 10)));
        btnSearch.setBackground(Color.decode("#87CEFA"));
        pnBtn.add(btnSearch, BorderLayout.WEST);

        btnSearch.setRequestFocusEnabled(false);

        ImageIcon iconThoat = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/IconReset.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        btnReset.setIcon(iconThoat);
        btnReset.setFont(sgUI18b);
        btnReset.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 10, 0, 10, Color.white), new EmptyBorder(10, 10, 10, 10)));
        btnReset.setBackground(Color.decode("#87CEFA"));
        pnBtn.add(btnReset, BorderLayout.CENTER);
        btnReset.setRequestFocusEnabled(false);

        ImageIcon iconExcel = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/ex.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        btnxuatexcel.setIcon(iconExcel);
        btnxuatexcel.setFont(sgUI18b);
        btnxuatexcel.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 10, 0, 10, Color.white), new EmptyBorder(10, 10, 10, 10)));
        btnxuatexcel.setBackground(Color.decode("#87CEFA"));
        pnBtn.add(btnxuatexcel, BorderLayout.EAST);
        btnxuatexcel.setRequestFocusEnabled(false);

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
        //scp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        loadTable();

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

        //-----------click chuot vao table-----
        tbCTT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Hello");
            }
        });

        btnReset.addActionListener((ActionEvent e) -> {
            DefaultTableModel dmt = (DefaultTableModel) tbCTT.getModel();
            dmt.setColumnCount(0);
            loadTable();
            txtMaNV.setText("Mã nhân viên");
            txtTenNV.setText("Tên nhân viên");
            cbThang.setSelectedIndex(0);
            txtMaNV.setFocusable(false);
            txtTenNV.setFocusable(false);
            txtMaNV.setFocusable(true);
            txtTenNV.setFocusable(true);
        });
        btnxuatexcel.addActionListener((ActionEvent e) -> {
            XSSFWorkbook workbook = new XSSFWorkbook();
            if (cbThang.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn tháng", "LỖI", 0);
            } else
        try {
                String a = Integer.toString(cbThang.getSelectedIndex());
                FileOutputStream outputStream = new FileOutputStream("C:\\Users\\huhuh\\Desktop\\Diemdanhthang" + a + ".xlsx");
                Row row;
                Cell cell;
                int rowNum = 0;
                int colNum = 0;

                //create a new sheet
                workbook.createSheet("Employee Attendance");
                //add title row
                row = workbook.getSheetAt(0).createRow(rowNum++);
                cell = row.createCell(colNum);
                cell.setCellValue("BẢN ĐIỂM DANH NHÂN VIÊN");
                workbook.getSheetAt(0).addMergedRegion(CellRangeAddress.valueOf("A1:F1"));

                row = workbook.getSheetAt(0).createRow(rowNum++);
                cell = row.createCell(colNum++);
                cell.setCellValue("Mã NV");
                cell = row.createCell(colNum++);
                cell.setCellValue("Tên NV");
                cell = row.createCell(colNum++);
                cell.setCellValue("Năm");
                cell = row.createCell(colNum++);
                cell.setCellValue("Tháng");
                for (int i = 1; i <= 31; i++) {
                    cell = row.createCell(colNum++);
                    cell.setCellValue(i);
                }

                ArrayList<DiemDanhDTO> DDList = new ArrayList<>();

                try {
                    DDList = new DiemDanhBUS().LoadDD();
                } catch (SQLException ex) {
                    Logger.getLogger(PanelDiemDanh.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(PanelDiemDanh.class.getName()).log(Level.SEVERE, null, ex);
                }

                ArrayList<String> tempNVList = new ArrayList<>();
                for (int i = 0; i < DDList.size(); i++) {
                    if (tempNVList.contains(DDList.get(i).getMaNV()) == false) {
                        tempNVList.add(DDList.get(i).getMaNV());
                    }
                }
                ArrayList<Integer> Dem = new ArrayList<>();
                for (int i = 0; i < tempNVList.size(); i++) {

                    for (int j = 0; j < DDList.size(); j++) {

                        if (Integer.parseInt(DDList.get(j).getNgayDiLam().substring(3, 5)) == cbThang.getSelectedIndex() && DDList.get(j).getMaNV().equals(tempNVList.get(i))) {
                            Dem.add(Integer.parseInt(DDList.get(j).getNgayDiLam().substring(0, 2)));
                        }
                    }
                    row = workbook.getSheetAt(0).createRow(rowNum++);
                    colNum = 0;

                    cell = row.createCell(colNum++);
                    cell.setCellValue(tempNVList.get(i));
                    cell = row.createCell(colNum++);
                    cell.setCellValue(TenNV(tempNVList.get(i)));
                    cell = row.createCell(colNum++);
                    cell.setCellValue("2023");
                    cell = row.createCell(colNum++);
                    cell.setCellValue(cbThang.getSelectedIndex());
                    for (int l = 1; l <= 31; l++) {
                        cell = row.createCell(colNum++);
                        for (int g = 0; g < Dem.size(); g++) {
                            if (Dem.get(g) == l) {
                                cell.setCellValue("*");
                            }
                        }
                    }
                    Dem.clear();

                }
                ArrayList<NhanVienDTO> NVList = new ArrayList<>();
                try {
                    NVList = new NhanVienBUS().LoadNV();
                } catch (Exception ex) {
                }
                int n = tempNVList.size();
                for (int i = 0; i < NVList.size(); i++) {
                    int check = 0;
                    for (int j = 0; j < tempNVList.size(); j++) {
                        if (NVList.get(i).getMaNV().equals(tempNVList.get(j))) {
                            check = 1;
                        }
                    }
                    if (check == 0) {
                        row = workbook.getSheetAt(0).createRow(rowNum++);
                        colNum = 0;

                        cell = row.createCell(colNum++);
                        cell.setCellValue(NVList.get(i).getMaNV());
                        cell = row.createCell(colNum++);
                        cell.setCellValue(TenNV(NVList.get(i).getMaNV()));
                        cell = row.createCell(colNum++);
                        cell.setCellValue("2023");
                        cell = row.createCell(colNum++);
                        cell.setCellValue(cbThang.getSelectedIndex());

                    }
                }

                workbook.write(outputStream);
                workbook.close();
                JOptionPane.showMessageDialog(null, "Thành Công", "Thông Báo", JOptionPane.PLAIN_MESSAGE);
                Desktop.getDesktop().open(new File("C:\\Users\\huhuh\\Desktop\\Diemdanhthang" + a + ".xlsx"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel dm = (DefaultTableModel) tbCTT.getModel();
                if (cbThang.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn tháng", "LỖI", 0);
                } else if ((txtMaNV.getText().equals("Mã nhân viên") || txtMaNV.getText().isBlank())
                        && (txtTenNV.getText().equals("Tên nhân viên") || txtTenNV.getText().isBlank())
                        && cbThang.getSelectedIndex() != 0) {
                    dm.setColumnCount(0);
                    loadTable(cbThang.getSelectedIndex());
                } else {

                    if (MaNVisEmty(txtMaNV.getText()) && (txtTenNV.getText().equals("Tên nhân viên") || txtTenNV.getText().isBlank())) {
                        loadTable(cbThang.getSelectedIndex(), txtMaNV.getText());

                    } else if (MaNVisEmty(txtMaNV.getText()) == false && MaNVisEmty2(txtMaNV.getText()) && txtMaNV.getText().equals("Mã nhân viên") == false) {
                        loadTable(cbThang.getSelectedIndex(), txtMaNV.getText());

                    } else if (MaNVisEmty(txtMaNV.getText()) == false && MaNVisEmty2(txtMaNV.getText()) == false && txtMaNV.getText().equals("Mã nhân viên") == false) {
                        JOptionPane.showMessageDialog(null, "Mã nhân viên không tồn tại", "LỖI", 0);

                    } else if ((txtMaNV.getText().equals("Mã nhân viên") || txtMaNV.getText().isBlank())
                            && TenNVisEmty(txtTenNV.getText())) {
                        loadTable2(cbThang.getSelectedIndex(), txtTenNV.getText());
                    } else if (TenNVisEmty(txtTenNV.getText()) == false && TenNVisEmty2(txtTenNV.getText()) && txtTenNV.getText().equals("Tên nhân viên") == false) {
                        loadTable2(cbThang.getSelectedIndex(), txtTenNV.getText());

                    } else if (TenNVisEmty(txtTenNV.getText()) == false && TenNVisEmty2(txtTenNV.getText()) == false && txtTenNV.getText().equals("Tên nhân viên") == false) {
                        JOptionPane.showMessageDialog(null, "Tên nhân viên không tồn tại", "LỖI", 0);

                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi nhập thông tin", "LỖI", 0);
                    }
                }

            }
        });
    }

    //Update thêm
    public void loadTable() {
        DefaultTableModel dm = (DefaultTableModel) tbCTT.getModel();
        while (dm.getRowCount() > 0) {
            dm.removeRow(0);
            dm.setColumnCount(0);
        }

        dm.addColumn("STT");
        dm.addColumn("Mã NV");
        dm.addColumn("Tên NV");
        dm.addColumn("Năm");
        dm.addColumn("Tháng");
        for (int i = 1; i <= 31; i++) {
            dm.addColumn(Integer.toString(i));
        }
        tbCTT.setModel(dm);
        tbCTT.setShowGrid(false);
        tbCTT.setIntercellSpacing(new Dimension(0, 0));
        tbCTT.setRowHeight(32);
        tbCTT.getColumnModel().getColumn(0).setPreferredWidth(25);

        tbCTT.getColumnModel().getColumn(1).setPreferredWidth(45);
        tbCTT.getColumnModel().getColumn(2).setPreferredWidth(120);
        tbCTT.getColumnModel().getColumn(3).setPreferredWidth(50);
        tbCTT.getColumnModel().getColumn(4).setPreferredWidth(40);

        for (int i = 5; i < tbCTT.getColumnCount(); i++) {
            tbCTT.getColumnModel().getColumn(i).setPreferredWidth(22);
        }
        tbCTT.getTableHeader().setPreferredSize(new Dimension(1, 40));
        tbCTT.setBackground(Color.white);
        tbCTT.getTableHeader().setBackground(Color.decode("#79CDCD"));

        tbCTT.getTableHeader().setFont(sgUI13b);
        tbCTT.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public void loadTable(int thang) {
        //   CTTList.clear();
        DefaultTableModel dm = (DefaultTableModel) tbCTT.getModel();
        while (dm.getRowCount() > 0) {
            dm.removeRow(0);
            dm.setColumnCount(0);
        }

        dm.addColumn("STT");
        dm.addColumn("Mã NV");
        dm.addColumn("Tên NV");
        dm.addColumn("Năm");
        dm.addColumn("Tháng");
        for (int i = 1; i <= 31; i++) {
            dm.addColumn(Integer.toString(i));
        }

        tbCTT.setModel(dm);

        ArrayList<DiemDanhDTO> DDList = new ArrayList<>();

        try {
            DDList = new DiemDanhBUS().LoadDD();
        } catch (SQLException ex) {
            Logger.getLogger(PanelDiemDanh.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PanelDiemDanh.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<String> tempNVList = new ArrayList<>();
        for (int i = 0; i < DDList.size(); i++) {
            if (tempNVList.contains(DDList.get(i).getMaNV()) == false) {
                tempNVList.add(DDList.get(i).getMaNV());
            }
        }
        Object rowData[] = new Object[36];

        ArrayList<Integer> Dem = new ArrayList<>();
        for (int i = 0; i < tempNVList.size(); i++) {

            for (int j = 0; j < DDList.size(); j++) {

                if (Integer.parseInt(DDList.get(j).getNgayDiLam().substring(3, 5)) == thang && DDList.get(j).getMaNV().equals(tempNVList.get(i))) {
                    Dem.add(Integer.parseInt(DDList.get(j).getNgayDiLam().substring(0, 2)));
                }
            }
            rowData[0] = i + 1;
            rowData[1] = tempNVList.get(i);
            rowData[2] = TenNV(tempNVList.get(i));
            rowData[3] = "2023";
            rowData[4] = thang;
            for (int m = 5; m < 36; m++) {
                rowData[m] = "  ";
            }
            for (int l = 0; l < Dem.size(); l++) {
                rowData[4 + Dem.get(l)] = "*";
            }

            Dem.clear();
            dm.addRow(rowData);
        }
        ArrayList<NhanVienDTO> NVList = new ArrayList<>();
        try {
            NVList = new NhanVienBUS().LoadNV();
        } catch (Exception ex) {
        }
        int n = tempNVList.size();
        for (int i = 0; i < NVList.size(); i++) {
            int check = 0;
            for (int j = 0; j < tempNVList.size(); j++) {
                if (NVList.get(i).getMaNV().equals(tempNVList.get(j))) {
                    check = 1;
                }
            }
            if (check == 0) {
                rowData[0] = ++n;
                rowData[1] = NVList.get(i).getMaNV();
                rowData[2] = TenNV(NVList.get(i).getMaNV());
                rowData[3] = "2023";
                rowData[4] = thang;
                for (int m = 5; m < 36; m++) {
                    rowData[m] = "  ";
                }
                dm.addRow(rowData);
            }
        }

        tbCTT.setShowGrid(false);
        tbCTT.setIntercellSpacing(new Dimension(0, 0));
        tbCTT.setRowHeight(32);
        tbCTT.getColumnModel().getColumn(0).setPreferredWidth(25);

        tbCTT.getColumnModel().getColumn(1).setPreferredWidth(45);
        tbCTT.getColumnModel().getColumn(2).setPreferredWidth(120);
        tbCTT.getColumnModel().getColumn(3).setPreferredWidth(50);
        tbCTT.getColumnModel().getColumn(4).setPreferredWidth(40);

        for (int i = 5; i < tbCTT.getColumnCount(); i++) {
            tbCTT.getColumnModel().getColumn(i).setPreferredWidth(22);
        }
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
                    lb.setBorder(new MatteBorder(0, 0, 1, 1, Color.decode("#DDDDDD")));
                    lb.setBackground(Color.decode("#97FFFF"));
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

    public void loadTable(int thang, String id) {
        //   CTTList.clear();
        DefaultTableModel dm = (DefaultTableModel) tbCTT.getModel();
        while (dm.getRowCount() > 0) {
            dm.removeRow(0);
            dm.setColumnCount(0);
        }

        dm.addColumn("STT");
        dm.addColumn("Mã NV");
        dm.addColumn("Tên NV");
        dm.addColumn("Năm");
        dm.addColumn("Tháng");
        for (int i = 1; i <= 31; i++) {
            dm.addColumn(Integer.toString(i));
        }

        tbCTT.setModel(dm);

        Object rowData[] = new Object[36];
        ArrayList<DiemDanhDTO> DDList = new ArrayList<>();

        try {
            DDList = new DiemDanhBUS().LoadDD();
        } catch (SQLException ex) {
            Logger.getLogger(PanelDiemDanh.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PanelDiemDanh.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (MaNVisEmty(id) == false) {
            rowData[0] = 1;
            rowData[1] = id;
            rowData[2] = TenNV(id);
            rowData[3] = "2023";
            rowData[4] = thang;
            for (int m = 5; m < 36; m++) {
                rowData[m] = "  ";
            }
            dm.addRow(rowData);
        } else {
            ArrayList<Integer> Dem = new ArrayList<>();
            for (int j = 0; j < DDList.size(); j++) {

                if (Integer.parseInt(DDList.get(j).getNgayDiLam().substring(3, 5)) == thang && DDList.get(j).getMaNV().equals(id)) {
                    Dem.add(Integer.parseInt(DDList.get(j).getNgayDiLam().substring(0, 2)));
                }
            }
            rowData[0] = 1;
            rowData[1] = id;
            rowData[2] = TenNV(id);
            rowData[3] = "2023";
            rowData[4] = thang;
            for (int m = 5; m < 36; m++) {
                rowData[m] = "  ";
            }
            for (int l = 0; l < Dem.size(); l++) {
                rowData[4 + Dem.get(l)] = "*";
            }

            Dem.clear();
            dm.addRow(rowData);
        }

        tbCTT.setShowGrid(false);
        tbCTT.setIntercellSpacing(new Dimension(0, 0));
        tbCTT.setRowHeight(32);
        tbCTT.getColumnModel().getColumn(0).setPreferredWidth(25);

        tbCTT.getColumnModel().getColumn(1).setPreferredWidth(45);
        tbCTT.getColumnModel().getColumn(2).setPreferredWidth(120);
        tbCTT.getColumnModel().getColumn(3).setPreferredWidth(50);
        tbCTT.getColumnModel().getColumn(4).setPreferredWidth(40);

        for (int i = 5; i < tbCTT.getColumnCount(); i++) {
            tbCTT.getColumnModel().getColumn(i).setPreferredWidth(22);
        }
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
                    lb.setBorder(new MatteBorder(0, 0, 1, 1, Color.decode("#DDDDDD")));
                    lb.setBackground(Color.decode("#97FFFF"));
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

    public void loadTable2(int thang, String ten) {

        DefaultTableModel dm = (DefaultTableModel) tbCTT.getModel();
        while (dm.getRowCount() > 0) {
            dm.removeRow(0);
            dm.setColumnCount(0);
        }

        dm.addColumn("STT");
        dm.addColumn("Mã NV");
        dm.addColumn("Tên NV");
        dm.addColumn("Năm");
        dm.addColumn("Tháng");
        for (int i = 1; i <= 31; i++) {
            dm.addColumn(Integer.toString(i));
        }

        tbCTT.setModel(dm);

        ArrayList<DiemDanhDTO> DDList = new ArrayList<>();

        try {
            DDList = new DiemDanhBUS().LoadDD();
        } catch (SQLException ex) {
            Logger.getLogger(PanelDiemDanh.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PanelDiemDanh.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<NhanVienDTO> NVList = new ArrayList<>();
        NVList = new NhanVienBUS().LoadNV();

        Object rowData[] = new Object[36];
        String tmpMa = null;
        if (TenNVisEmty(ten) == false) {
            rowData[0] = 1;
            rowData[1] = MaNV(ten);
            rowData[2] = ten;
            rowData[3] = "2023";
            rowData[4] = thang;
            for (int m = 5; m < 36; m++) {
                rowData[m] = "  ";
            }
            dm.addRow(rowData);
        } else {
            ArrayList<Integer> Dem = new ArrayList<>();
            for (int j = 0; j < DDList.size(); j++) {

                if (Integer.parseInt(DDList.get(j).getNgayDiLam().substring(3, 5)) == thang && TenNV(DDList.get(j).getMaNV()).equals(ten)) {
                    Dem.add(Integer.parseInt(DDList.get(j).getNgayDiLam().substring(0, 2)));
                    tmpMa = DDList.get(j).getMaNV();
                }
            }
            rowData[0] = 1;
            rowData[1] = tmpMa;
            rowData[2] = ten;
            rowData[3] = "2023";
            rowData[4] = thang;
            for (int m = 5; m < 36; m++) {
                rowData[m] = "  ";
            }
            for (int l = 0; l < Dem.size(); l++) {
                rowData[4 + Dem.get(l)] = "*";
            }

            Dem.clear();
            dm.addRow(rowData);
        }

        tbCTT.setShowGrid(false);
        tbCTT.setIntercellSpacing(new Dimension(0, 0));
        tbCTT.setRowHeight(32);
        tbCTT.getColumnModel().getColumn(0).setPreferredWidth(25);

        tbCTT.getColumnModel().getColumn(1).setPreferredWidth(45);
        tbCTT.getColumnModel().getColumn(2).setPreferredWidth(120);
        tbCTT.getColumnModel().getColumn(3).setPreferredWidth(50);
        tbCTT.getColumnModel().getColumn(4).setPreferredWidth(40);

        for (int i = 5; i < tbCTT.getColumnCount(); i++) {
            tbCTT.getColumnModel().getColumn(i).setPreferredWidth(22);
        }
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
                    lb.setBorder(new MatteBorder(0, 0, 1, 1, Color.decode("#DDDDDD")));
                    lb.setBackground(Color.decode("#97FFFF"));
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

    public boolean MaNVisEmty(String id) {
        ArrayList<DiemDanhDTO> CTTList = new ArrayList<>();

        try {
            CTTList = new DiemDanhBUS().LoadDD();
        } catch (SQLException ex) {
            Logger.getLogger(PanelDiemDanh.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PanelDiemDanh.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            for (DiemDanhDTO ctt : CTTList) {
                if (ctt.getMaNV().equals(id)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean TenNVisEmty(String ten) {
        ArrayList<DiemDanhDTO> CTTList = new ArrayList<>();
        try {
            CTTList = new DiemDanhBUS().LoadDD();
        } catch (SQLException ex) {
            Logger.getLogger(PanelDiemDanh.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PanelDiemDanh.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            for (DiemDanhDTO ctt : CTTList) {
                if (TenNV(ctt.getMaNV()).equals(ten)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean MaNVisEmty2(String id) {
        ArrayList<NhanVienDTO> CTTList = new ArrayList<>();

        CTTList = new NhanVienBUS().LoadNV();

        try {
            for (NhanVienDTO ctt : CTTList) {
                if (ctt.getMaNV().equals(id)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean TenNVisEmty2(String ten) {
        ArrayList<NhanVienDTO> CTTList = new ArrayList<>();

        CTTList = new NhanVienBUS().LoadNV();
        try {
            for (NhanVienDTO ctt : CTTList) {
                if (TenNV(ctt.getMaNV()).equals(ten)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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

    public static String MaNV(String ten) {
        ArrayList<NhanVienDTO> NVList = new ArrayList<>();
        try {
            NVList = new NhanVienBUS().LoadNV();
        } catch (Exception ex) {
        }
        for (int i = 0; i < NVList.size(); i++) {
            if (NVList.get(i).getTenNV().equals(ten)) {
                return NVList.get(i).getMaNV();
            }
        }
        return null;
    }

}
