/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.LeTanGUI;

/**
 *
 * @author duyli
 */
import BUS.DiemDanhBUS;
import BUS.NhanVienBUS;
import DTO.DiemDanhDTO;
import DTO.NhanVienDTO;
import GUI.mainGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.text.ParseException;

import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PanelDiemDanhTong extends JPanel {

    private JPanel pnTitle = new JPanel();
    private JPanel pnCenter = new JPanel();
    private JPanel pnSearch = new JPanel();
    private JPanel pnContent = new JPanel();

    private JPanel pnTitleTop = new JPanel();
    private JPanel pnMid = new JPanel();
    private JScrollPane scp = new JScrollPane();

    JLabel lbTitle = new JLabel("BẢNG ĐIỂM DANH TỔNG");

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

//-----thêm tên nhân viên
    private JLabel lbList = new JLabel("Danh sách điểm danh nhân viên " /*+ TenNV(mainGUI.taiKhoan)*/);

    public PanelDiemDanhTong() {
        initComponents();
    }

    public void initComponents() {
        setBackground(Color.white);
        setLayout(new BorderLayout());

        pnTitle.setBackground(Color.white);
        add(pnTitle, BorderLayout.NORTH);
        lbTitle.setFont(sgUI18b);
        pnTitle.add(lbTitle);

        add(pnCenter, BorderLayout.CENTER);

        pnCenter.setLayout(new BorderLayout());
        pnCenter.add(pnSearch, BorderLayout.NORTH);
        pnCenter.add(pnContent, BorderLayout.CENTER);
        pnCenter.setBackground(Color.white);

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

        scp.setViewportView(tbCTT);

//-------set tạm mã NV1
        String NV1 = mainGUI.taiKhoan;
        //mainGUI.taiKhoan
        loadTable(NV1);

    }

    public void loadTable(String maNV) {

        DefaultTableModel dm = (DefaultTableModel) tbCTT.getModel();
        while (dm.getRowCount() > 0) {
            dm.removeRow(0);
            dm.setColumnCount(0);
        }

        dm.addColumn("Tháng");
        for (int i = 1; i <= 31; i++) {
            dm.addColumn(Integer.toString(i));
        }

        tbCTT.setModel(dm);

        ArrayList<DiemDanhDTO> DDList = new ArrayList<>();

        try {
            DDList = new DiemDanhBUS().LoadDD();
        } catch (SQLException ex) {
            Logger.getLogger(PanelDiemDanhTong.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PanelDiemDanhTong.class.getName()).log(Level.SEVERE, null, ex);
        }

        Object rowData[] = new Object[32];
        ArrayList<Integer> Dem = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {

            for (int j = 0; j < DDList.size(); j++) {

                if (Integer.parseInt(DDList.get(j).getNgayDiLam().substring(3, 5)) == i && DDList.get(j).getMaNV().equals(maNV)) {
                    Dem.add(Integer.parseInt(DDList.get(j).getNgayDiLam().substring(0, 2)));
                }
            }
            rowData[0] = i;
            for (int m = 1; m < 32; m++) {
                rowData[m] = "  ";
            }
            for (int l = 0; l < Dem.size(); l++) {
                rowData[Dem.get(l)] = "*";
            }

            Dem.clear();
            dm.addRow(rowData);
        }

        tbCTT.setShowGrid(false);
        tbCTT.setIntercellSpacing(new Dimension(0, 0));
        tbCTT.setRowHeight(32);
        tbCTT.getColumnModel().getColumn(0).setPreferredWidth(40);

        for (int i = 1; i < tbCTT.getColumnCount(); i++) {
            tbCTT.getColumnModel().getColumn(i).setPreferredWidth(25);
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
