package GUI.LeTanGUI;

import BUS.ChiTietThueBUS;
import DTO.KhachHangDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class DanhSachKHLeTan extends JFrame {

    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    private JLabel lb = new JLabel("Danh sách khách hàng");
    private JPanel pnCenter = new JPanel();
    private JScrollPane scp = new JScrollPane();
    private JLabel txtMaCT = new JLabel();
    private JButton btn = new JButton();
    private JTable tbKH = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public DanhSachKHLeTan(ArrayList<KhachHangDTO> listKH, DatPhongLeTan dp) {
        setVisible(true);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(lb, BorderLayout.NORTH);
        add(pnCenter, BorderLayout.CENTER);
        lb.setFont(sgUI15);
        lb.setHorizontalAlignment(JLabel.CENTER);
        lb.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK), new EmptyBorder(5, 5, 5, 5)));
        pnCenter.setLayout(new BorderLayout());
        pnCenter.setBackground(Color.white);
        pnCenter.add(scp, BorderLayout.CENTER);
        scp.setViewportView(tbKH);
        scp.getViewport().setBackground(Color.white);
        getContentPane().setBackground(Color.white);
        render(listKH);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        tbKH.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 || e.getClickCount() == 2) {
                    int ans = JOptionPane.showConfirmDialog(null, "Bạn có chắc là khách hàng này?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    if (ans == JOptionPane.YES_OPTION) {
                        dp.txtmactt.setText("CTT" + ChiTietThueBUS.getSize());
                        dp.txtmakh.setText(tbKH.getValueAt(tbKH.getSelectedRow(),tbKH.getColumnModel().getColumnIndex("Mã KH")).toString());
                        dp.txttenkh.setText(tbKH.getValueAt(tbKH.getSelectedRow(),tbKH.getColumnModel().getColumnIndex("Tên KH")).toString());
                        dp.remove(dp.pnKH);
                        revalidate();
                        dp.pnRight.add(dp.pnChange, BorderLayout.EAST);
                        dp.renderDSP(true);
                        dispose();
                    }
                }
            }
        });

    }

    public void render(ArrayList<KhachHangDTO> listKH) {
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
