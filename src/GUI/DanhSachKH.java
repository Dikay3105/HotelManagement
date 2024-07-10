package GUI;

import BUS.ChiTietThueBUS;
import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class DanhSachKH extends JFrame {

    ArrayList<KhachHangDTO> listKH;
    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    private JLabel lb = new JLabel("Danh sách khách hàng");
    private JPanel pnCenter = new JPanel();
    private JScrollPane scp = new JScrollPane();
    private JPanel pnMaCT = new JPanel();
    private JLabel lbMaCT = new JLabel("Mã chi tiết thuê:");
    JPanel pnCTT = new JPanel();
    JLabel lbCTT = new JLabel();
    JButton btnAcept = new JButton("Chọn");
    private JLabel txtMaCT = new JLabel();
    private JButton btn = new JButton();
    private JTable tbKH = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public DanhSachKH(ArrayList<KhachHangDTO> listKH,DatPhongGUI a) {
        setSize(500, 300);
        setLocationRelativeTo(null);
        this.listKH = listKH;
        setLayout(new BorderLayout());
        add(lb, BorderLayout.NORTH);
        add(pnCenter, BorderLayout.CENTER);
        lb.setFont(sgUI15);
        lb.setHorizontalAlignment(JLabel.CENTER);
        lb.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK), new EmptyBorder(5, 5, 5, 5)));
        pnCenter.setLayout(new BorderLayout());
        pnCenter.setBackground(Color.white);
        pnCenter.add(scp, BorderLayout.CENTER);
        pnCenter.add(pnMaCT, BorderLayout.SOUTH);
        pnMaCT.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbMaCT.setFont(sgUI15);
        pnMaCT.setBackground(Color.white);
        pnMaCT.setLayout(new BorderLayout());
        pnMaCT.add(lbMaCT, BorderLayout.WEST);
        scp.setViewportView(tbKH);
        scp.getViewport().setBackground(Color.white);
        getContentPane().setBackground(Color.white);
        render();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        tbKH.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    String maKH = tbKH.getValueAt(tbKH.getSelectedRow(), tbKH.getColumnModel().getColumnIndex("Mã KH")).toString();
                    tbKH.clearSelection();
                    String maCTT = "";
                    maCTT = ChiTietThueBUS.getMaCTT(maKH);
                    if (maCTT.length() == 0) {
                        int ans = JOptionPane.showConfirmDialog(null, "Bạn có muốn tạo mới thuê phòng", "Thông báo", JOptionPane.YES_NO_OPTION);
                        if (ans == JOptionPane.YES_OPTION) {
                            a.btnXuLy.setEnabled(true);
                            a.txtMaCT.setText("CTT" + ChiTietThueBUS.getSize());
                            a.txtMaKH.setText(maKH);
                            a.txtTenKH.setText(KhachHangBUS.getKH_MaKH(maKH).getTenKH());
                            dispose();
                        }
                    } else {
                        pnMaCT.add(pnCTT, BorderLayout.CENTER);
                        pnCTT.setLayout(new BorderLayout(5, 5));
                        pnCTT.add(lbCTT, BorderLayout.CENTER);
                        pnCTT.setBackground(Color.white);
                        pnCTT.add(btnAcept, BorderLayout.EAST);
                        lbCTT.setText(maCTT);
                        btnAcept.setFocusPainted(false);
                        btnAcept.setBackground(Color.white);
                        lbCTT.setFont(sgUI15);
                        lbCTT.setHorizontalAlignment(JLabel.RIGHT);
                        FrameOrderRoom.maCTT = maCTT;
                        try {
                            new FrameOrderRoom();
                        } catch (IOException ex) {
                            Logger.getLogger(PanelCTT.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(PanelCTT.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(PanelCTT.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        dispose();
                    }
                } catch (Exception ex) {
                }
            }
        });
    }

    public void render() {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã KH");
        dtm.addColumn("Tên KH");
        dtm.addColumn("SDT");
        dtm.addColumn("CMND");
        for (KhachHangDTO x : listKH) {
            Object row[] = {x.getMaKH(), x.getTenKH(), x.getCmnd(), x.getSdt()};
            dtm.addRow(row);
        }

        tbKH.setModel(dtm);
        tbKH.setShowGrid(false);
        tbKH.setIntercellSpacing(new Dimension(0, 0));
        tbKH.setRowHeight(30);
        tbKH.getColumnModel().getColumn(0).setPreferredWidth(5);
        tbKH.getColumnModel().getColumn(1).setPreferredWidth(50);
        tbKH.getTableHeader().setPreferredSize(new Dimension(1, 32));
        tbKH.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tbKH.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        for (int j = 0; j < tbKH.getColumnCount(); j++) {
            tbKH.getColumnModel().getColumn(j).setCellRenderer(renderBorder);
        }
    }
}
